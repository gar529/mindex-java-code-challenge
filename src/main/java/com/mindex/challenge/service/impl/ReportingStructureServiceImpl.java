package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Retrieving reporting structure with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        
        DirectReportCounter counter = new DirectReportCounter(employee);
        int numReports = counter.count();

        return new ReportingStructure(employee, numReports);
    }
    
    /**
     * Class to count the number of distinct reports and build out the reporting structure of an employee
     */
    private class DirectReportCounter {
		
		// the number of distinct reports a single employee has
		private int numReports;
		
		// the employee for the Reporting Structure
		private Employee employee;
		
		// used to keep track of distinct reports
		private Map<String, Employee> employeeMap; 
		
		private DirectReportCounter(final Employee employee) {
			this.employee = employee;
		}
		
		/**
		 * Builds out the Reporting Structure and counts each of the employee's direct reports 
		 * using the recursive traverse() method. Returns the total number of distinct reports
		 * 
		 * @return The total number of distinct reports
		 */
		private int count() {
			
			//reset the number of reports each time the method is called
			numReports = 0; 
			employeeMap = new HashMap<>();
			
			if (employee != null) {
				employeeMap.put(employee.getEmployeeId(), employee);
				
				List<Employee> directReports = employee.getDirectReports();
				
				if (directReports != null) {
					for (Employee report : directReports) {
						
						traverse(report);
					}
				}
			}
			return numReports;
		}
		
		/**
		 * Recursive method that traverses all children in a node and counts the total number of children
		 * 
		 * @param employee The node to be traversed
		 */
		private void traverse(Employee employee) {
			
			if (employee != null) {
				
				// only count and traverse unique records
				if (!employeeMap.containsKey(employee.getEmployeeId())) {
					
					// increment the numReports counter
					numReports++;
					
					// get the saved employee object and apply it to the existing object
					employee.clone(employeeRepository.findByEmployeeId(employee.getEmployeeId()));
					
					// add the unique record to the map
					employeeMap.put(employee.getEmployeeId(), employee);
					
					List<Employee> directReports = employee.getDirectReports();
					
					if (directReports != null) {
						for (Employee report : directReports) {
							
							traverse(report);
						}
					}
				}
				// if it's a duplicate, copy its data from the map
				else if (employeeMap.containsKey(employee.getEmployeeId())) {
					employee.clone(employeeMap.get(employee.getEmployeeId()));
				}
			}
		}
	}
}
