package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        
        validateCompensation(compensation);
        Compensation createdCompensation = compensationRepository.insert(compensation);

        return createdCompensation;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Creating compensation with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }
    
    private void validateCompensation(final Compensation compensation) {
    	
    	String errMsg = null;
    	
    	if (compensation.getEmployee() == null) {
    		errMsg = "Employee is required";
    	}
    	else if (compensation.getEmployee().getEmployeeId() == null || 
    			compensation.getEmployee().getEmployeeId().trim().equals("")) {
    		errMsg = "Employee ID is required";
    	}
    	else if (compensation.getSalary() == null) {
    		errMsg = "Salary is required";
    	}
    	else if (compensation.getEffectiveDate() == null) {
    		errMsg = "Effective Date is required";
    	}
    	
    	if (errMsg != null) {
    		throw new RuntimeException(errMsg);
    	}
    }
}
