package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private Employee employee;
    private double salary;
    private Date effectiveDate;

    public Compensation(Employee employee, double salary, Date effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {return effectiveDate;}

    public void setEffectiveDate(Date effectiveDate) {this.effectiveDate = effectiveDate;}

    public double getSalary() {return salary;}

    public void setSalary(long salary) {this.salary = salary;}

    public Employee getEmployee() {return employee;}

    public void setEmployee(Employee employee) {this.employee = employee;}
}
