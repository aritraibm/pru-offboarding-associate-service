package com.pru.offboarding.associate.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pru.offboarding.associate.service.entity.EmployeeOnboarding;
import com.pru.offboarding.associate.service.service.EmployeeOnboardingService;

@RestController
@RequestMapping("/date-filter")
public class EmployeeOnboardingController {

	@Autowired
	private EmployeeOnboardingService service;
	
	@PostMapping("/add-onboarding-employee")
	public EmployeeOnboarding addOnboardEmp(@RequestBody EmployeeOnboarding onboardEmp) {
		return service.saveEmployeeOnboarding(onboardEmp);
	}
	
	@PostMapping("/add-all-onboarding-employee")
	public List<EmployeeOnboarding> addAllOnboardEmp(@RequestBody List<EmployeeOnboarding> onboardEmps) {
		return service.saveAllOnBoardingChecklist(onboardEmps);
	}
	
	@GetMapping("/get-all-onboarding-employee")
	public List<EmployeeOnboarding> getAllOnboardEmp(){
		return service.getAllEmployeeOnboarding();
	}
	
	@GetMapping("/{id}")
	public EmployeeOnboarding getOnboardEmp(@PathVariable Long id){
		return service.getEmployeeOnboardingById(id);
	}
	
	@PutMapping("/onboarding-employee/{id}")
	public EmployeeOnboarding updateOnboardEmp(@PathVariable Long id,@RequestBody EmployeeOnboarding onboarding ) {
		EmployeeOnboarding ep = service.getEmployeeOnboardingById(id);
		ep.setBgc(onboarding.getBgc());
		ep.setEmpId(onboarding.getEmpId());
		ep.setIntranetId(onboarding.getIntranetId());
		ep.setLocation(onboarding.getLocation());
		ep.setName(onboarding.getName());
		ep.setOnboardingDate(onboarding.getOnboardingDate());
		ep.setSite(onboarding.getSite());
		ep.setXid(onboarding.getXid());
		return service.saveEmployeeOnboarding(ep);
	}
	
	@DeleteMapping("/{id}")
	public String deleteOnboardEmp(@PathVariable Long id) {
		service.deleteEmployeeOnboarding(id);
		return id+" is removed from record";
	}
	
	
}
