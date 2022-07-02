package com.mindex.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.impl.ReportingStructureServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {
	
	@Autowired
    private ReportingStructureServiceImpl reportingStructureServiceImpl;
	
	@Autowired
    private CompensationService compensationService;

	@Test
	public void contextLoads() {
		
		/** Task 1 */
		ReportingStructure reportingStructure1 = reportingStructureServiceImpl.read("62c1084e-6e34-4630-93fd-9153afb65309");
        assertNotNull(reportingStructure1.getEmployee());
        assertEquals(0, reportingStructure1.getNumberOfReports());
        
        ReportingStructure reportingStructure2 = reportingStructureServiceImpl.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(reportingStructure2.getEmployee());
        assertEquals(4, reportingStructure2.getNumberOfReports());
        
        /** Task 2 */
		
        // Add Compensation record
        Compensation testCompensation = new Compensation();
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(50000.00);
        testCompensation.setEffectiveDate(LocalDate.now());
        
        Compensation createdCompensation = compensationService.create(testCompensation);
        assertNotNull(createdCompensation);
        assertNotNull(createdCompensation.getEmployee());
        assertCompensation(testCompensation, createdCompensation);
        
        // Read Record
        Compensation readCompensation = compensationService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(readCompensation);
        assertNotNull(readCompensation.getEmployee());
        assertCompensation(testCompensation, readCompensation);
	}
	
	/**
	 * Test if the actual compensation matches the expected value
	 * 
	 * @param expected The expected value
	 * @param actual The actual value
	 */
	private void assertCompensation(final Compensation expected, final Compensation actual) {
		
        assertEquals(actual.getEmployee().getEmployeeId(), expected.getEmployee().getEmployeeId());
        assertEquals(actual.getSalary(), expected.getSalary());
        assertEquals(actual.getEffectiveDate(), expected.getEffectiveDate());
	}

}
