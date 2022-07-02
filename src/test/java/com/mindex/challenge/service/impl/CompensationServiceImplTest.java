package com.mindex.challenge.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @Autowired
    private CompensationService compensationService;

    @Test
    public void testCreateReadUpdate() {
    	Compensation testCompensation = new Compensation();
    	
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId(UUID.randomUUID().toString());
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(50000.00);
        testCompensation.setEffectiveDate(LocalDate.now());

        // Create checks
        Compensation createdCompensation = compensationService.create(testCompensation);
        Employee createdEmployee = createdCompensation.getEmployee();
        
        assertNotNull(createdEmployee);
        assertNotNull(createdCompensation.getSalary());
        assertNotNull(createdCompensation.getEffectiveDate());
        assertEmployeeEquivalence(testEmployee, createdEmployee);


        // Read checks
        Compensation readCompensation = compensationService.read(createdEmployee.getEmployeeId());
        Employee readEmployee = readCompensation.getEmployee();
        assertEquals(createdEmployee.getEmployeeId(), readEmployee.getEmployeeId());
        assertEmployeeEquivalence(createdEmployee, readEmployee);
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
