package com.careme.service;

import org.springframework.stereotype.Service;

import com.careme.model.command.PageNumberCommand;

@Service("PageNumberService")
public class PageNumberServiceImpl implements PageNumberService {
	
	public PageNumberCommand paging (int totalCount, int contentPerPage, int currentPage, String path) {
		PageNumberCommand pnc = new PageNumberCommand();
		
		int pageBlock = 10;
		
		int totalPage = (totalCount/contentPerPage)+1;
		
		int startPage = (currentPage / pageBlock) + 1;
		int endPage = (totalPage / pageBlock) * pageBlock + (totalPage % pageBlock);
		
		if(totalPage<endPage) {
			endPage = totalPage;
		}
		
		pnc.setContentPerPage(contentPerPage);
		pnc.setCurrentPage(currentPage);
		pnc.setEndPage(endPage);
		pnc.setStartPage(startPage);
		pnc.setTotalCount(totalCount);
		pnc.setPath(path);
		pnc.setTotalPage(totalPage);
		
		return pnc;
	}
	
	public int getStartIdx(int currentPage, int contentPerPage) {
		int start_idx=(currentPage-1)*contentPerPage;
		return start_idx;
	}
	
}