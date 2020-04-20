package com.careme.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.AdminDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.PageNumberCommand;
import com.careme.model.dto.MemberDto;

@Service
public class AdminService {
	
	@Autowired
	FileUploadService fileUploadService;
	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}
	
	@Autowired
	AdminDao adminDao;
	public void setDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	@Autowired
	PageNumberService pageService;
	public void setPageService(PageNumberService pageNumberService) {
		this.pageService = pageNumberService;
	}
	
	public MemberDto getMember(int memberIdx) {
		
		return adminDao.selectMember(memberIdx);
	}
	
	public HashMap<String, Object> getMemberList(int page, int contentPerPage) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		String path = "member?page=";
		int pageStart = pageService.getStartIdx(page, contentPerPage);
		
		HashMap<String, Object> params = getParams("", "", page, contentPerPage, pageStart);
		
		List<MemberDto> list = adminDao.selectMemberList(params);
		data.put("list", list);
		
		int totalCount = adminDao.selectTotalCount();
		
		PageNumberCommand paging = pageService.paging(totalCount, contentPerPage, page, path);
		data.put("paging", paging);
		return data;
	}
	
	public HashMap<String, Object> searchMemberList(String searchType, String searchText, int page, int contentPerPage) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		String path = "search?searchType=" + searchType + "&searchText=" + searchText + "&page=";
		int pageStart = pageService.getStartIdx(page, contentPerPage);
		
		HashMap<String, Object> params = getParams(searchType, searchText, page, contentPerPage, pageStart);
		
		List<MemberDto> list = adminDao.searchMemberList(params);
		data.put("list", list);
		
		int totalCount = adminDao.selectTotalCount();
		
		PageNumberCommand paging = pageService.paging(totalCount, contentPerPage, page, path);
		
		data.put("paging", paging);
		return data;
	}
	
	public HashMap<String, Object> getParams(String searchType, String searchText, int page, int contentPerPage, int pageStart) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if (searchType != "") {
			String searchColumn;
			
			switch(searchType) {
	        case "id" : 
	        	searchColumn = "member_id"; 
	            break;
	        case "email" : 
	        	searchColumn = "member_email"; 
	            break;  
	        case "phone" : 
	        	searchColumn = "member_phone";
	            break;  
	        case "nick" : 
	        	searchColumn = "member_nick";
	            break;  
	        default :
	        	searchColumn = "id";
			}
			
			params.put("searchColumn", searchColumn);
			params.put("searchText", searchText);
			
		}

		params.put("pageStart", pageStart);
		params.put("contentPerPage", contentPerPage);
		
		return params;
	}
	

	
	public int updateMember(MemberDto memberDto, MultipartHttpServletRequest request) {
		int res = 0;
		String path = "/img/member/profile/";
		List<FileUploadCommand> fileUploadcommand = fileUploadService.upload(request, path);
		if (fileUploadcommand.size() > 0) {
			memberDto.setMember_profile(fileUploadcommand.get(0).getFilePath());
		}
		
		res = adminDao.updateMember(memberDto);
		return res;
	}
	
	public int deleteMember(int memberIdx) {
		return adminDao.deleteMember(memberIdx);
	}

}
