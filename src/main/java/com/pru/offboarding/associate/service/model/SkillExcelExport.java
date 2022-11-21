package com.pru.offboarding.associate.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SkillExcelExport {

	private String associateName;
    private String xid;
    private String skillName;
    private String skillRating;
	
}
