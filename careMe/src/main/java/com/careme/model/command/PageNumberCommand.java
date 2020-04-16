package com.careme.model.command;

public class PageNumberCommand {
	
	
	private int totalCount;
	private int contentPerPage;
	private int currentPage;
	private int startPage;
	private int endPage;
	private int totalPage;
	private String path;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getContentPerPage() {
		return contentPerPage;
	}
	public void setContentPerPage(int contentPerPage) {
		this.contentPerPage = contentPerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "PageNumberCommand [totalCount=" + totalCount + ", contentPerPage=" + contentPerPage + ", currentPage="
				+ currentPage + ", startPage=" + startPage + ", endPage=" + endPage + ", totalPage=" + totalPage
				+ ", path=" + path + "]";
	}
	
	
}
