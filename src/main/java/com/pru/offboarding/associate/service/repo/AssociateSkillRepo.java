package com.pru.offboarding.associate.service.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pru.offboarding.associate.service.entity.AssociateSkill;

@Repository
public interface AssociateSkillRepo extends MongoRepository<AssociateSkill, Long> {
	
	
}
