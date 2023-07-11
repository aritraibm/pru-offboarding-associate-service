package com.pru.offboarding.associate.service.VO;

import java.util.List;

import com.pru.offboarding.associate.service.entity.Associate;
import com.pru.offboarding.associate.service.entity.AssociateSkill;



public class AssociateWithSkillTemplateVO {

	private Associate associate;
	private List<AssociateSkill> associateSkill;
	
	public Associate getAssociate() {
		return associate;
	}
	public void setAssociate(Associate associate) {
		this.associate = associate;
	}
	public List<AssociateSkill> getAssociateSkill() {
		return associateSkill;
	}
	public void setAssociateSkill(List<AssociateSkill> associateSkill) {
		this.associateSkill = associateSkill;
	}
	public AssociateWithSkillTemplateVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AssociateWithSkillTemplateVO(Associate associate, List<AssociateSkill> associateSkill) {
		super();
		this.associate = associate;
		this.associateSkill = associateSkill;
	}
	
	@Override
	public String toString() {
		return "AssociateWithSkillTemplateVO [associate=" + associate + ", associateSkill=" + associateSkill + "]";
	}
	
	
}
