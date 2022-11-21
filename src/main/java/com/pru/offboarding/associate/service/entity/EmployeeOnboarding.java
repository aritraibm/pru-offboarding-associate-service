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
@Document("employee_onboarding")
public class EmployeeOnboarding {
	
	@Id
	private Long empOnbrdId;	
	private String empId;
	private String xid;
	private String name;
	private String intranetId;
	private String location;
	private String site;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date onboardingDate;
	private String bgc;
	
}
