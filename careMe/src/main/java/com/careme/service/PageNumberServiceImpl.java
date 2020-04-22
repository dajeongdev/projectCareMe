package com.careme.service;

import org.springframework.stereotype.Service;

import com.careme.model.command.PageNumberCommand;

@Service("PageNumberService")
public class PageNumberServiceImpl implements PageNumberService {
	public PageNumberCommand paging (int totalCount, int contentPerPage, int currentPage, String path) {
		PageNumberCommand pnc = new PageNumberCommand();
		
		int pageBlock = 10;
		
		int totalPage = (totalCount/contentPerPage);
		if (totalCount % contentPerPage > 0) totalPage++;

		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		int prevPage = startPage - pageBlock;
		
		if (currentPage == 1) {
			prevPage = 0;
		}
		
		int nextPage = endPage + 1;
		
		if (endPage > totalPage) {
			endPage = totalPage;
			nextPage = 0;
		}
		
		pnc.setContentPerPage(contentPerPage);
		pnc.setCurrentPage(currentPage);
		pnc.setEndPage(endPage);
		pnc.setStartPage(startPage);
		pnc.setTotalCount(totalCount);
		pnc.setPath(path);
		pnc.setTotalPage(totalPage);
		pnc.setBlockSize(pageBlock);
		pnc.setPrevPage(prevPage);
		pnc.setNextPage(nextPage);
		
		return pnc;
	}
	
	public int getStartIdx(int currentPage, int contentPerPage) {
		int start_idx=(currentPage-1)*contentPerPage;
		return start_idx;
	}
	
}