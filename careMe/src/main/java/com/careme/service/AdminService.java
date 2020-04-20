package com.careme.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.AdminDao;
import com.careme.dao.MemberDao;
import com.careme.model.command.PageNumberCommand;
import com.careme.model.dto.MemberDto;

@Service
public class AdminService {
	
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
	
	public List<MemberDto> getMemberList() {
		return adminDao.selectMemberList();
	}
	
	public HashMap<String, Object> searchMemberList(String searchType, String searchText, int page) {
		HashMap<String, Object> params = new HashMap<String, Object>();
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
		
		int contentPerPage = 20;
		int pageStart = pageService.getStartIdx(page, contentPerPage);
		
		params.put("searchColumn", searchColumn);
		params.put("searchText", searchText);
		params.put("pageStart", pageStart);
		params.put("limit", contentPerPage);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		List<MemberDto> list = adminDao.searchMemberList(params);
		System.out.println(list);
		data.put("list", list);
		
		int totalCount = adminDao.selectTotalCount();
		String path = "search?searchType=" + searchType + "&searchText=" + searchText + "&page=";
		PageNumberCommand paging = pageService.paging(totalCount, contentPerPage, page, path);
		data.put("paging", paging);
		
		return data;
	}

}
