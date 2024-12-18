package com.example.camunda_employee_management_spring_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @Column(name="employeeID")
    private int employeeID;

    @Column(name="LeaveBalance")
    private int LeaveBalance;

    public int getEmployeeID() {
        System.out.println("Sysouts - Employee ID in setEmployeeID" +employeeID);
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
        System.out.println("Sysouts - Employee ID in setEmployeeID" +employeeID);
    }

    public int getLeaveBalance() {
        return LeaveBalance;
    }

    public void setLeaveBalance(int LeaveBalance) {
        this.LeaveBalance = LeaveBalance;
    }

}
