package com.pru.offboarding.associate.service.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssociateRequest {

	private String associateName;
    private String engagementName;
    private String majorFunction;
    private String band;
    private String primaryContact;
    private String emailIbm;
    private String emailClient;
    private String xid;
    private String clientManager;
    private Date endDate;
    private String location;
    private String city;
    private String billType;
    private String billCode;
    private String role;
    private Date asOnDate;
    private Date clientExpDate;
    private Date itExpDate;
    private Date ibmDate;
    private String experienceWithClient;
    private String careerExperience;
    private String experienceWithIbm;
    private String skillset;
    private String resourceCriticality;
    private String atImmigrationVisaRisks;
    private Long backupSuccessorResource;
    private String keyContingencyGroup;
    private String additionalContingency;
    private String visaType;
    private String workPermitValidUntil;
    private String extensionUpdates;
    private Date visaMaxOutDate;
    private String timeLeftInUs;
    private String h1bNominations;
    private String riskMitigationComments;
    private String planInCaseOfExtensionAmendmentRejection;    
	
}
