package com.example.camunda_employee_management_spring_project.Services;

import com.example.camunda_employee_management_spring_project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class EmployeeLeaveService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public int CheckNumberOfDays(String startDate,String endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateParse = dateFormat.parse(startDate);
        Date endDateParse = dateFormat.parse(endDate);
        LocalDate startLocalDate = startDateParse.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDateParse.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();


        long numberOfDays = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

        return (int) numberOfDays;
    }

    public int CheckLeaveBalance(int EmpID,String Department){

        System.out.println("Sysouts - Employee ID in CheckLeaveBalance XYZDEDF" +EmpID);
        return employeeRepository.getEmployeeLeaveBalance(EmpID);
        //return 15;
    }
}
