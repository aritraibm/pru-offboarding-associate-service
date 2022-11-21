package com.pru.offboarding.associate.service.repo;
//package com.pru.test.associate.service.repo;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import org.springframework.stereotype.Repository;
//
//import com.pru.test.associate.service.entity.Associate;
//import com.pru.test.associate.service.model.SkillExcelExport;
//
//@Repository
//public class AssociateSkillWithoutJPARepo {
//
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	public List<SkillExcelExport> listAssociateSkillDetailsForExcelExportIbmId(String ibmId){
//		SkillExcelExport skillExcelExportModel= null;
//		
//		
//		
//		StringBuffer sqlQuery = new StringBuffer("SELECT asso.associate_name, asso.xid,base.skill_name,base.rating from (select sub.associate_id,sub.skill_id,sub.skill_name,coalesce(assoOut.skill_rating ,'') as rating \n"
//				+ "from (select associate_id,skill_id,skill_name from (select distinct asso.associate_id  from tbl_associate_skills asso) foo\n"
//				+ "cross join tbl_skills) sub\n"
//				+ "LEFT JOIN tbl_associate_skills assoOut\n"
//				+ "ON assoOut.skill_id=sub.skill_id and assoOut.associate_id=sub.associate_id) base\n"
//				+ "INNER JOIN tbl_associate_details asso\n"
//				+ "ON asso.associate_id = base.associate_id\n"
//				+ "WHERE asso.ibm_id=:ibm_id\n"
//				+ "ORDER BY base.skill_name ASC");
//		
//		
//		Query query= entityManager.createNativeQuery(sqlQuery.toString());
//		query.setParameter("ibm_id", ibmId);
//		
//			
//			List<Object[]> obj= query.getResultList();
//			List<SkillExcelExport> skillExcelExportModelList= new ArrayList<>();
//			
//			if(!obj.isEmpty()) {
//				for(Object[] record : obj) {
//					skillExcelExportModel= new SkillExcelExport();
//					skillExcelExportModel.setAssociateName(String.valueOf(record[0]));
//					skillExcelExportModel.setXid(String.valueOf(record[1]));
//					skillExcelExportModel.setSkillName(String.valueOf(record[2]));
//					skillExcelExportModel.setSkillRating(String.valueOf(record[3]));
//					skillExcelExportModelList.add(skillExcelExportModel);
//				}
//			}
//				
//			return skillExcelExportModelList;
//		
//	}
//	
//	public Map<String,List<SkillExcelExport>> listAssociateSkillDetailsForExcelExport(){
//		SkillExcelExport skillExcelExportModel= null;
//		
//		
//		
//		StringBuffer sqlQuery = new StringBuffer("SELECT asso.associate_name, asso.xid,base.skill_name,base.rating from (select sub.associate_id,sub.skill_id,sub.skill_name,coalesce(assoOut.skill_rating ,'') as rating \n"
//				+ "from (select associate_id,skill_id,skill_name from (select distinct asso.associate_id  from tbl_associate_skills asso) foo\n"
//				+ "cross join tbl_skills) sub\n"
//				+ "LEFT JOIN tbl_associate_skills assoOut\n"
//				+ "ON assoOut.skill_id=sub.skill_id and assoOut.associate_id=sub.associate_id) base\n"
//				+ "INNER JOIN tbl_associate_details asso\n"
//				+ "	ON asso.associate_id = base.associate_id\n"
//				+ "ORDER BY base.skill_name ASC");
//		
//		
//		Query query= entityManager.createNativeQuery(sqlQuery.toString());
//		// query.setParameter("ibm_id", ibmId);
//		
//			
//			List<Object[]> obj= query.getResultList();
//			List<SkillExcelExport> skillExcelExportModelList= new ArrayList<>();
//			
//			if(!obj.isEmpty()) {
//				for(Object[] record : obj) {
//					skillExcelExportModel= new SkillExcelExport();
//					skillExcelExportModel.setAssociateName(String.valueOf(record[0]));
//					skillExcelExportModel.setXid(String.valueOf(record[1]));
//					skillExcelExportModel.setSkillName(String.valueOf(record[2]));
//					skillExcelExportModel.setSkillRating(String.valueOf(record[3]));
//					skillExcelExportModelList.add(skillExcelExportModel);
//				}
//			}
//			
//			Map<String, List<SkillExcelExport>> skillsPerUser = skillExcelExportModelList.stream()
//					  .collect(Collectors.groupingBy(SkillExcelExport::getXid));
//			return skillsPerUser;
//		
//	}
//
//	
//	
//}
