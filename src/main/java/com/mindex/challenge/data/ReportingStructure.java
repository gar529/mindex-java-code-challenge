package com.mindex.challenge.data;

public class ReportingStructure {
		
	private Employee employee;
	private int numberOfReports;
	
	public Employee getEmployee() {
		
		return employee;
	}
	
	public void setEmployee(final Employee employee) {
		
		this.employee = employee;
	}
	
	public int getNumberOfReports() {
		
		return numberOfReports;
	}
	
	public void setNumberOfReports(final int numberOfReports) {
		
		this.numberOfReports = numberOfReports;
	}
	
	public ReportingStructure(final Employee employee, final int numberOfReports) {
		
		this.employee = employee;
		this.numberOfReports = numberOfReports;
	}
}