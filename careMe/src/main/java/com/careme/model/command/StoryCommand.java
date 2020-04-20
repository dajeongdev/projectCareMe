package com.careme.model.command;

public class StoryCommand {
	private String searchType;
	private String keyword;
	int start_idx;
	int contentPerPage;
	
	public int getStart_idx() {
		return start_idx;
	}
	public void setStart_idx(int start_idx) {
		this.start_idx = start_idx;
	}
	public int getContentPerPage() {
		return contentPerPage;
	}
	public void setContentPerPage(int contentPerPage) {
		this.contentPerPage = contentPerPage;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "StoryCommand [searchType=" + searchType + ", keyword=" + keyword + ", start_idx=" + start_idx
				+ ", contentPerPage=" + contentPerPage + "]";
	}
	
}

