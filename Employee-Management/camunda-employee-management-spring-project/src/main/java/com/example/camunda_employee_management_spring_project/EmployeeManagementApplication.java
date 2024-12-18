package com.example.camunda_employee_management_spring_project;

import com.example.camunda_employee_management_spring_project.Services.EmployeeLeaveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.example.camunda_employee_management_spring_project")
public class EmployeeManagementApplication {
	@Autowired
	private EmployeeLeaveService employeeLeaveService;
	@Autowired
	private ZeebeClient zeebeClient;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);

	}
	@JobWorker(type="check-leave-balance")
	public void handleLeaveBalanceJob(JobClient client, ActivatedJob job) throws ParseException {
		String name = job.getVariablesAsMap().get("name").toString();
		String department = job.getVariablesAsMap().get("department").toString();
		int empID = Integer.parseInt(job.getVariablesAsMap().get("empID").toString());
		String startDate = job.getVariablesAsMap().get("startDate").toString();
		String endDate = job.getVariablesAsMap().get("endDate").toString();

		System.out.println("Employee ID in JobWorker" +empID);
		int numberOfDays = employeeLeaveService.CheckNumberOfDays(startDate,endDate);
		int leaveBalance = employeeLeaveService.CheckLeaveBalance(empID,department);


		Map<String, Object> variables = new HashMap<>();
		variables.put("numberOfDays", numberOfDays); // Explicitly an Integer
		variables.put("leaveBalance", leaveBalance);

		client.newCompleteCommand(job.getKey()).variables(variables).send().join();
	}

}
