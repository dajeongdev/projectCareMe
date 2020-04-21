package com.careme.model.command;

public class StoryCommand {
	private String searchType;
	private String keyword;
	private int start_idx;
	private int contentPerPage;
	
	public String getSearhType() {
		return searchType;
	}
	public void setSearhType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
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
	@Override
	public String toString() {
		return "StoryCommand [searchType=" + searchType + ", keyword=" + keyword + ", start_idx=" + start_idx
				+ ", contentPerPage=" + contentPerPage + "]";
	}
	
}

