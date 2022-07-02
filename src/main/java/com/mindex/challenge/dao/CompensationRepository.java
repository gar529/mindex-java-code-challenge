package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
	
	// custom query to search by employee ID
	@Query("{ 'employee.employeeId' : ?0 }")
    Compensation findByEmployeeId(String employeeId);
}
