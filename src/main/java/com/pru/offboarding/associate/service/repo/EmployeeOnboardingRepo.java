package com.pru.offboarding.associate.service.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pru.offboarding.associate.service.entity.EmployeeOnboarding;

@Repository
public interface EmployeeOnboardingRepo extends MongoRepository<EmployeeOnboarding, Long> {

}
