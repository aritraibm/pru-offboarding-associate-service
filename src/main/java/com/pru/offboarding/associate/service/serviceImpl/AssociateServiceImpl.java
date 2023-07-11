package com.pru.offboarding.associate.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.pru.offboarding.associate.service.VO.AssociateWithSkillTemplateVO;
import com.pru.offboarding.associate.service.entity.Associate;
import com.pru.offboarding.associate.service.entity.AssociateSkill;
import com.pru.offboarding.associate.service.model.SearchAssociateRequest;
import com.pru.offboarding.associate.service.model.SkillExcelExport;
import com.pru.offboarding.associate.service.repo.AssociateRepo;
import com.pru.offboarding.associate.service.repo.AssociateSkillRepo;
import com.pru.offboarding.associate.service.service.AssociateService;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class AssociateServiceImpl implements AssociateService {

	@Autowired
	private AssociateRepo associateRepo;

	// @Autowired
	// private AssociateSkillWithoutJPARepo associateSkillWithoutJPARepo;

	@Autowired
	private AssociateSkillRepo associateSkillRepo;

	final Logger logger = LoggerFactory.getLogger(AssociateServiceImpl.class);

	@Override
	public AssociateWithSkillTemplateVO saveAssociateDetails(AssociateWithSkillTemplateVO formData) {

		Associate formAssociate = formData.getAssociate();
		System.out.println("formData associatid "+formAssociate.getAssociateId());
		if (associateRepo.existsByIbmId(formAssociate.getIbmId())){
			Associate associate = associateRepo.findByIbmId(formAssociate.getIbmId()).orElseThrow(() -> new RuntimeException("User not found"));
			formAssociate.setAssociateId(associate.getAssociateId());
		}
		formAssociate.setActiveInactive("Active");
		Associate associateResponse = associateRepo.save(formAssociate);
		List<AssociateSkill> uiAssociateSkill = formData.getAssociateSkill();
		System.out.println("uiAssociateSkill "+uiAssociateSkill);
		List<AssociateSkill> dbAssociateSkill = associateSkillRepo.findByAssociateId(associateResponse.getAssociateId());
		if (dbAssociateSkill.size()>=1){
			System.out.println("skil > 1 "+dbAssociateSkill);
			for (AssociateSkill associateSkill : uiAssociateSkill) {
				String skilid = associateSkill.getSkillId();
				Optional<AssociateSkill> asoa = dbAssociateSkill.stream().filter(s -> s.getSkillId().equals(skilid)).findFirst();
				if (asoa.isPresent()) {
					System.out.println("asoa " + asoa.get());
					associateSkill.setAssociateSkillId(asoa.get().getAssociateSkillId());
					associateSkill.setAssociateId(asoa.get().getAssociateId());
				} else {
					System.out.println("not present skillid " + skilid);
					associateSkill.setAssociateId(associateResponse.getAssociateId());
				}
			}
			System.out.println("aftr uiAssociateSkill "+uiAssociateSkill);
		}else {
			System.out.println("skil < 1 "+dbAssociateSkill);
			uiAssociateSkill = uiAssociateSkill.stream().peek(a-> a.setAssociateId(associateResponse.getAssociateId())).collect(Collectors.toList());
		}
		List<AssociateSkill> associateSkillResponse = associateSkillRepo.saveAll(uiAssociateSkill);
		AssociateWithSkillTemplateVO responseVO = new AssociateWithSkillTemplateVO();
		responseVO.setAssociate(associateResponse);
		responseVO.setAssociateSkill(associateSkillResponse);
		return responseVO;
	}

	@Override
	public boolean saveAllAssociateDetails(List<AssociateWithSkillTemplateVO> newAssociates) {
		logger.info("save all associate called  ... " + newAssociates);
		AtomicBoolean isAllAssociateSaved = new AtomicBoolean(false);
		newAssociates.forEach(associate -> {
			Associate aso = associate.getAssociate();
			if (!associateRepo.existsByIbmId(aso.getIbmId())){
				aso.setActiveInactive("Active");
				Associate associateResponse = associateRepo.save(aso);

				List<AssociateSkill> assocSkill = associate.getAssociateSkill();
				List<AssociateSkill> saveAssocSkill = new ArrayList<>();
				for (AssociateSkill skillThreader : assocSkill) {
					skillThreader.setAssociateId(associateResponse.getAssociateId());
					saveAssocSkill.add(skillThreader);
				}
				associateSkillRepo.saveAll(saveAssocSkill);
				isAllAssociateSaved.set(true);
			}else{
				Associate asos = associateRepo.findByIbmId(aso.getIbmId()).get();
				aso.setAssociateId(asos.getAssociateId());
				Associate associateResponse = associateRepo.save(aso);

				List<AssociateSkill> assocSkill = associate.getAssociateSkill();
				List<AssociateSkill> saveAssocSkill = new ArrayList<>();
				for (AssociateSkill skillThreader : assocSkill) {
					skillThreader.setAssociateId(associateResponse.getAssociateId());
					saveAssocSkill.add(skillThreader);
				}
				associateSkillRepo.saveAll(saveAssocSkill);
				isAllAssociateSaved.set(true);
			}
		});
		return isAllAssociateSaved.get();
	}
	
	public HttpHeaders restHeader() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	@Override
	public AssociateWithSkillTemplateVO getAssociateWithSkillDetails(String associateId) {

		logger.info("associateId is :: >" + associateId);
		AssociateWithSkillTemplateVO responseTemplateVO = new AssociateWithSkillTemplateVO();
		Associate associate = getAssociateDetails(associateId);
		List<AssociateSkill> associateSkills = associateSkillRepo.findByAssociateId(associate.getAssociateId());
		responseTemplateVO.setAssociate(associate);
		responseTemplateVO.setAssociateSkill(associateSkills);

		return responseTemplateVO;
	}

	@Override
	public Associate getAssociateDetails(String associateId) {
		return associateRepo.findByAssociateId(associateId).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public List<Associate> searchAssociateDetails(SearchAssociateRequest formData) {
		// TODO Auto-generated method stub

		String associateName = formData.getAssociateName();
//		String band = formData.getBand();
//		String emailIbm = formData.getEmailIbm();
//		String xid = formData.getXid();

		// return
		// associateRepo.searchAssociateDetailsSQL(associateName,band,emailIbm,xid);
		return associateRepo.findByFirstNameLike(associateName);
		// return null;
	}

	@Override
	public List<Associate> getAssociateDetailsForExcelExportIbmId(String ibmId) {
		// TODO Auto-generated method stub
		return associateRepo.findByIbmIdLike(ibmId);
	}

	@Override
	public List<Associate> getAssociateDetailsForExcelExport() {
		// TODO Auto-generated method stub
		return findAllAssociates();
	}

	@Override
	public List<SkillExcelExport> getAssociateSkillDetailsForExcelExportIbmId(String ibmId) {
		// TODO Auto-generated method stub
		// associateSkillWithoutJPARepo.listAssociateSkillDetailsForExcelExportIbmId(ibmId);
		return null;
	}

	@Override
	public Map<String, List<SkillExcelExport>> getlistAssociateSkillDetailsForExcelExport() {
		// TODO Auto-generated method stub
		// associateSkillWithoutJPARepo.listAssociateSkillDetailsForExcelExport();
		return null;
	}

	@Override
	public List<Associate> searchAssociateDetailsByDate(Date date) {
		// TODO Auto-generated method stub
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		return associateRepo.findByAsOnDate(sqlStartDate);
		// return null;
	}

	@Override
	public List<Associate> getAllAssociateDetails() {
		// TODO Auto-generated method stub
		return findAllAssociates();
	}

	private List<Associate> findAllAssociates() {
		// TODO Auto-generated method stub
		return associateRepo.findAll();
	}

	@Override
	public Associate newAssociateDetails(Associate formData) {
		// TODO Auto-generated method stub
		return associateRepo.save(new Associate(formData.getFirstName(),formData.getLastName(), formData.getIbmId(),
				formData.getEmailIbm(), formData.getActiveInactive()));
	}
}