package com.careme.service;

import com.careme.model.command.PageNumberCommand;

public interface PageNumberService {

	public PageNumberCommand paging (int totalCount, int contentPerPage, int currentPage, String path);
	
	public int getStartIdx(int currentPage, int contentPerPage);
	
}
