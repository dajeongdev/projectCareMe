package com.careme.view.download;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.careme.dao.AdminDao;
import com.careme.model.dto.MemberDto;
import com.careme.service.AdminService;


@Component("memberListToXls")
public class MemberExcelDownloadView extends AbstractXlsView {
	@Autowired
	AdminDao adminDao;
	public void setDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		HashMap<String, Object> params = (HashMap<String, Object>) model.get("params");
		
		List<MemberDto> list;
		if (params.get("searchColumn") != null && params.get("searchColumn") != "") {
			list = adminDao.searchMemberList(params);
		} else {
			list = adminDao.selectMemberList(params);
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"members.xls\";");
		Sheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet);
		
		int rowNum = 1;
		for (MemberDto member : list) {
			createMemberRow(sheet, member, rowNum++);
		}
		
	}
	
	private Sheet createFirstSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "members");
		sheet.setColumnWidth(1, 256 * 20);
		
		return sheet;
	}
	
	private void createColumnLabel(Sheet sheet) {
		Row firstRow = sheet.createRow(0);
		Cell cell = null;

		cell = firstRow.createCell(0);
		cell.setCellValue("MemberIdx");

		cell = firstRow.createCell(1);
		cell.setCellValue("ID");
		
		cell = firstRow.createCell(2);
		cell.setCellValue("Email");
		
		cell = firstRow.createCell(3);
		cell.setCellValue("Phone");
		
		cell = firstRow.createCell(4);
		cell.setCellValue("Nick");
		
		cell = firstRow.createCell(5);
		cell.setCellValue("RegDate");
	}
	
	private void createMemberRow(Sheet sheet, MemberDto member, int rowNum) {
		Row row = sheet.createRow(rowNum);	
		Cell cell = row.createCell(0);
		
		cell.setCellValue(member.getMember_idx());

		cell = row.createCell(1);
		cell.setCellValue(member.getMember_id());
		
		cell = row.createCell(2);
		cell.setCellValue(member.getMember_email());
		
		cell = row.createCell(3);
		cell.setCellValue(member.getMember_phone());
		
		cell = row.createCell(4);
		cell.setCellValue(member.getMember_nick());
		
		cell = row.createCell(5);
		cell.setCellValue(member.getReg_date());
	}
	

}



