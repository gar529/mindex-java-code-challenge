package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {
	
	private Employee employee;
	private Double salary;
	private LocalDate effectiveDate;
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}
	
	public Double getSalary( ) {
		return salary;
	}
	
	public void setSalary(final Double salary) {
		this.salary = salary;
	}
	
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setEffectiveDate(final LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

}
