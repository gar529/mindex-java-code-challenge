package com.mindex.challenge;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.impl.ReportingStructureServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBootstrapTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ReportingStructureServiceImpl reportingStructureServiceImpl;

    @Test
    public void test() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Lennon", employee.getLastName());
        assertEquals("Development Manager", employee.getPosition());
        assertEquals("Engineering", employee.getDepartment());
        
        ReportingStructure reportingStructure1 = reportingStructureServiceImpl.read("62c1084e-6e34-4630-93fd-9153afb65309");
        assertNotNull(reportingStructure1.getEmployee());
        assertEquals(0, reportingStructure1.getNumberOfReports());
        
        ReportingStructure reportingStructure2 = reportingStructureServiceImpl.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(reportingStructure2.getEmployee());
        assertEquals(4, reportingStructure2.getNumberOfReports());        
    }
}
