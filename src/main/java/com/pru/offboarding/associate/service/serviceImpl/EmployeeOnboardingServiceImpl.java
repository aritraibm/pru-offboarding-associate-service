package com.pru.offboarding.associate.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pru.offboarding.associate.service.entity.EmployeeOnboarding;
import com.pru.offboarding.associate.service.repo.EmployeeOnboardingRepo;
import com.pru.offboarding.associate.service.service.EmployeeOnboardingService;

@Service
public class EmployeeOnboardingServiceImpl implements EmployeeOnboardingService {
	
	@Autowired
	private EmployeeOnboardingRepo onboardingRepo;

	@Override
	public EmployeeOnboarding saveEmployeeOnboarding(EmployeeOnboarding onboarding) {
		return onboardingRepo.save(onboarding);
	}

	@Override
	public List<EmployeeOnboarding> saveAllOnBoardingChecklist(List<EmployeeOnboarding> onboardings) {
		return onboardingRepo.saveAll(onboardings);
	}

	@Override
	public EmployeeOnboarding getEmployeeOnboardingById(Long Id) {
		Optional<EmployeeOnboarding> emp = onboardingRepo.findById(Id);
		return emp.get();
	}

	@Override
	public List<EmployeeOnboarding> getAllEmployeeOnboarding() {
		return onboardingRepo.findAll();
	}

	@Override
	public void deleteEmployeeOnboarding(Long employeeOnboardingId) {
		onboardingRepo.deleteById(employeeOnboardingId);
	}

}
