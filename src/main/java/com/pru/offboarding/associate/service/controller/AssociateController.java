package com.pru.offboarding.associate.service.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pru.offboarding.associate.service.VO.AssociateWithSkillTemplateVO;
import com.pru.offboarding.associate.service.entity.Associate;
import com.pru.offboarding.associate.service.model.SearchAssociateRequest;
import com.pru.offboarding.associate.service.service.AssociateService;

@RestController
@RequestMapping(value = "/pru-associate")
public class AssociateController {
	
	@Autowired
	private AssociateService associateService;
	
	final Logger logger= LoggerFactory.getLogger(AssociateController.class);
	
	@PostMapping(value = "/save-associate")
	@PreAuthorize("hasAnyRole({'ROLE_ONBOARDING_REVIEWER','ROLE_ONBOARDING_MANAGER'})")
	public AssociateWithSkillTemplateVO saveUser(@RequestBody AssociateWithSkillTemplateVO formData) {
		// System.out.println(":::::::: >>>>>>"+formData);
		return associateService.saveAssociateDetails(formData);
	}

	@GetMapping(value = "/get-associate-with-skill-details-by-id/{associateId}")
	@PreAuthorize("hasAnyRole({'ROLE_ONBOARDING_REVIEWER','ROLE_ONBOARDING_MANAGER'})")
	public AssociateWithSkillTemplateVO getAssociateWithSkill(@PathVariable Long associateId) {
		
		return associateService.getAssociateWithSkillDetails(associateId);
	}
	
	@GetMapping(value = "/get-associate-details-by-id/{associateId}")
	@PreAuthorize("hasAnyRole({'ROLE_ONBOARDING_REVIEWER','ROLE_ONBOARDING_MANAGER'})")
	public Associate getAssociate(@PathVariable Long associateId) {
		
		return associateService.getAssociateDetails(associateId);
	}
	
	
	@GetMapping(value = "/get-all-associates")
	@PreAuthorize("hasAnyRole({'ROLE_ONBOARDING_REVIEWER','ROLE_ONBOARDING_MANAGER'})")
	public List<Associate> getAllAssociates() {
		
		return associateService.getAllAssociateDetails();
	}
	
	
	@PostMapping(value = "/search-associate")
	@PreAuthorize("hasAnyRole({'ROLE_ONBOARDING_REVIEWER','ROLE_ONBOARDING_MANAGER'})")
	public List<Associate> searchAssociate(@RequestBody SearchAssociateRequest formData) {
		
		return associateService.searchAssociateDetails(formData);
	}
	
	@GetMapping(value = "/search-associate-bydate/{date}")
	@PreAuthorize("hasAnyRole({'ROLE_ONBOARDING_REVIEWER','ROLE_ONBOARDING_MANAGER'})")
	public List<Associate> searchAssociateByDate(@PathVariable(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		return associateService.searchAssociateDetailsByDate(date);
	}
	/*
	@GetMapping("/export-excel/{ibmId}")
    public void exportToExcelByIbmId(@PathVariable String ibmId, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Associates_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Associate> listAssociates = associateService.getAssociateDetailsForExcelExportIbmId(ibmId);
        List<SkillExcelExport> listAssociateSkills = associateService.getAssociateSkillDetailsForExcelExportIbmId(ibmId);
        
        //getAssociateSkillDetailsForExcelExportIbmId(String ibmId)
        AssociateExcelExporter excelExporter = new AssociateExcelExporter(listAssociates, listAssociateSkills,null,"INDIVIDUAL");
        excelExporter.export(response);    
    }  
	
	
	@GetMapping("/export-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Associates_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Associate> listAssociates = associateService.getAssociateDetailsForExcelExport();
        
        Map<String, List<SkillExcelExport>>  skillsMap=associateService.getlistAssociateSkillDetailsForExcelExport();
        System.out.println("skillsMap: "+skillsMap);
        AssociateExcelExporter excelExporter = new AssociateExcelExporter(listAssociates, null,skillsMap,"ALL");
        excelExporter.export(response);    
    }  
	*/
	
	public String invokeFallbackMethod(Exception ex) {
		return "Service Error : "+ex.getMessage();
	}

}
