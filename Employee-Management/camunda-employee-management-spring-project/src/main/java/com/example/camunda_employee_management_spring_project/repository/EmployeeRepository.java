package com.example.camunda_employee_management_spring_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.camunda_employee_management_spring_project.model.Employee;
import org.springframework.data.jpa.repository.Query;
import java.lang.*;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //int employeeID = 12;
  @Query(value = "SELECT LeaveBalance FROM employees WHERE EmployeeID = :employeeID", nativeQuery = true)
//    Employee findByEmployeeID(int employeeID);
    int getEmployeeLeaveBalance(int employeeID);

}
