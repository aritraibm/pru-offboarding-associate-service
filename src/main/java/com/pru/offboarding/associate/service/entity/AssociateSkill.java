package com.pru.offboarding.associate.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("associate_skill")
public class AssociateSkill {

	@Id
	private String associateSkillId;
	private String associateId;
	private String skillId;
	private String skillRating;
    
}
