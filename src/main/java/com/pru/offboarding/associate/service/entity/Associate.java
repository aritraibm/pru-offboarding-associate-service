package com.pru.offboarding.associate.service.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "associate")
public class Associate {

	@Id
	private String associateId;
	
    private String associateName;
    private String ibmId;
    private Long projectId;
    private String firstName;
    private String lastName;
    private String engagementName;
    private String majorFunction;
    private String band;
    private String primaryContact;
    private String emailIbm;
    private String emailClient;
    private String xid;
    private String clientManager;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String location;
    private String city;
    private String billType;
    private String billCode;
    private String teamOrRole;
    private String role;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date asOnDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date clientExpDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date itExpDate;
    @JsonFormat(pattern="yyyy-MM-dd")
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date visaMaxOutDate;
    private String timeLeftInUs;
    private String h1bNominations;
    private String riskMitigationComments;
    private String planInCaseOfExtensionAmendmentRejection;
    private String activeInactive;    
   
    public Associate(String firstName, String lastName, String ibmId, String emailIbm, String activeInactive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ibmId = ibmId;
        this.emailIbm = emailIbm;
        this.activeInactive = activeInactive;
    }
}
