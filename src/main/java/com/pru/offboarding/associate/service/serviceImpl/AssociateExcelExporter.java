package com.pru.offboarding.associate.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.pru.offboarding.associate.service.entity.Associate;
import com.pru.offboarding.associate.service.model.SkillExcelExport;

import io.vavr.collection.Iterator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Service
public class AssociateExcelExporter {
    private XSSFWorkbook workbook;
    private String type;
    private XSSFSheet sheet;
    private XSSFSheet secondSheet;
    private List<Associate> listAssociates;
    private List<SkillExcelExport> listAssociateSkills;
    private Map<String, List<SkillExcelExport>>  skillsMap;

	public AssociateExcelExporter() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AssociateExcelExporter(List<Associate> listAssociates, List<SkillExcelExport> listAssociateSkills,Map<String, List<SkillExcelExport>>  skillsMap,String type) {
        this.listAssociates = listAssociates;
        this.listAssociateSkills = listAssociateSkills;
        this.skillsMap=skillsMap;
        this.type=type;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Resource Backup");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(10);
        //font.setColor(IndexedColors.BLUE.getIndex());
        style.setFont(font);
        
        createCell(row, 0, "Engagement Name", style); 
        createCell(row, 1, "Major Function", style); 
        createCell(row, 2, "Associate Name", style);  
        createCell(row, 3, "Band", style); 
        createCell(row, 4, "Primary Contact", style); 
        createCell(row, 5, "Email IBM", style); 
        createCell(row, 6, "Email Client", style); 
        createCell(row, 7, "XID", style); 
        createCell(row, 8, "Client Manager", style); 
        //end date
        createCell(row, 9, "Location", style); 
        createCell(row, 10, "City", style); 
        createCell(row, 11, "Bill Type", style); 
        createCell(row, 12, "Bill Code", style); 
        createCell(row, 13, "Team/Role", style); 
        //As on Date	
        //Pru Exp Date	
        //IT Exp Date	
        //IBM Date
        createCell(row, 14, "Experience with Client", style); 
        createCell(row, 15, "Career Experience", style); 
        createCell(row, 16, "Experience with IBM	Skillset", style); 
        createCell(row, 17, "Resource Criticality", style); 
        createCell(row, 18, "At Immigration / visa risks	", style); 
        createCell(row, 19, "Backup/Successor Resource", style); 
        createCell(row, 20, "Key Contingency Group", style); 
        createCell(row, 21, "Additional  Contingency", style); 
        createCell(row, 22, "Visa Type", style); 
        createCell(row, 23, "Work Permit Valid Until", style); 
        createCell(row, 24, "Extension Updates", style); 
        //Visa Max Out Date
        createCell(row, 25, "Time Left in US", style); 
        createCell(row, 26, "H1B Nominations	Risk/Mitigation/Comments", style); 
        createCell(row, 27, "Plan in case of extension/amendment rejection", style); 
        createCell(row, 28, "Active/Inactive", style);   
        
        
     // THIS IS SHEET 2
        secondSheet = workbook.createSheet("Delivery-Digital Services");
        Row secondRow = secondSheet.createRow(0);
        
        CellStyle sRowStyle = workbook.createCellStyle();
        XSSFFont sRowFont = workbook.createFont();
        sRowFont.setBold(true);
        sRowFont.setFontHeight(10);
        sRowStyle.setFont(sRowFont);
              
        createCell(secondRow, 0, "Resource Names", sRowStyle);
        createCell(secondRow, 1, "Client XID", sRowStyle);
//        createCell(secondRow, 2, "Skill Name", sRowStyle);
//        createCell(secondRow, 3, "Skill Rating", sRowStyle);
        
       // System.out.println("listAssociateSkills SIZE :::::: >>>>>"+listAssociateSkills.size());
       // System.out.println("listAssociateSkills DATA :::::: >>>>>"+listAssociateSkills.toString());
        
        int columnCount = 2;
        if(this.type.equalsIgnoreCase("ALL")) {
        	List<SkillExcelExport> Skills= skillsMap.entrySet().iterator().next().getValue();
        	for (SkillExcelExport skillTabheader : Skills) {
                createCell(secondRow, columnCount++, skillTabheader.getSkillName(), sRowStyle);
                
            }
        }else {
        	for (SkillExcelExport skillTabheader : listAssociateSkills) {
                createCell(secondRow, columnCount++, skillTabheader.getSkillName(), sRowStyle);
                
            }
        }
        
        
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
        int secondRowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);
                 
        for (Associate associate : listAssociates) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             

            createCell(row, columnCount++, associate.getEngagementName(), style);
            createCell(row, columnCount++, associate.getMajorFunction(), style);
            createCell(row, columnCount++, associate.getAssociateName(), style);
            createCell(row, columnCount++, associate.getBand(), style);
            createCell(row, columnCount++, associate.getPrimaryContact(), style);
            createCell(row, columnCount++, associate.getEmailIbm(), style);
            createCell(row, columnCount++, associate.getEmailClient(), style);
            createCell(row, columnCount++, associate.getXid(), style);
            createCell(row, columnCount++, associate.getClientManager(), style);
            createCell(row, columnCount++, associate.getLocation(), style);
            createCell(row, columnCount++, associate.getCity(), style);
            createCell(row, columnCount++, associate.getBillType(), style);
            createCell(row, columnCount++, associate.getBillCode(), style);
            createCell(row, columnCount++, associate.getTeamOrRole(), style);
            createCell(row, columnCount++, associate.getExperienceWithClient(), style);
            createCell(row, columnCount++, associate.getCareerExperience(), style);
            createCell(row, columnCount++, associate.getExperienceWithIbm(), style);
            createCell(row, columnCount++, associate.getResourceCriticality(), style);
            createCell(row, columnCount++, associate.getAtImmigrationVisaRisks(), style);
            createCell(row, columnCount++, String.valueOf(associate.getBackupSuccessorResource()), style);
            createCell(row, columnCount++, associate.getKeyContingencyGroup(), style);
            createCell(row, columnCount++, associate.getAdditionalContingency(), style);
            createCell(row, columnCount++, associate.getVisaType(), style);
            createCell(row, columnCount++, associate.getWorkPermitValidUntil(), style);
            createCell(row, columnCount++, associate.getExtensionUpdates(), style);
            createCell(row, columnCount++, associate.getTimeLeftInUs(), style);
            createCell(row, columnCount++, associate.getH1bNominations(), style);
            createCell(row, columnCount++, associate.getPlanInCaseOfExtensionAmendmentRejection(), style);
            createCell(row, columnCount++, associate.getActiveInactive(), style);
             
        }
        
        
        
        
        if(type.equalsIgnoreCase("ALL")) {
        	java.util.Iterator<Entry<String, List<SkillExcelExport>>> iterator = this.skillsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, List<SkillExcelExport>> entry = iterator.next();
                System.out.println(entry.getKey() + ":" + entry.getValue());
                
                Row row = secondSheet.createRow(secondRowCount++);
                int columnCount = 0;
            	 createCell(row, columnCount++, entry.getValue().get(0).getAssociateName(), style);
                 createCell(row, columnCount++, entry.getValue().get(0).getXid(), style);

                 for (SkillExcelExport skillTabData : entry.getValue()) {
                     createCell(row, columnCount++, skillTabData.getSkillRating(), style);
                 }
                
            }
        	
        	
        }else {
        	Row row = secondSheet.createRow(secondRowCount++);
            int columnCount = 0;
        	 createCell(row, columnCount++, listAssociateSkills.get(0).getAssociateName(), style);
             createCell(row, columnCount++, listAssociateSkills.get(0).getXid(), style);

             for (SkillExcelExport skillTabData : listAssociateSkills) {
                 createCell(row, columnCount++, skillTabData.getSkillRating(), style);
             }
        }
       
        
        
        
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
