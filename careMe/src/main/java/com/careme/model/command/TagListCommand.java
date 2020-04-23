package com.careme.model.command;

public class TagListCommand {
	private int tag_idx;
	private int start_idx;
	private int contentPerPage;
	
	public int getTag_idx() {
		return tag_idx;
	}
	public void setTag_idx(int tag_idx) {
		this.tag_idx = tag_idx;
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
		return "TagListCommand [tag_idx=" + tag_idx + ", start_idx=" + start_idx + ", contentPerPage=" + contentPerPage
				+ "]";
	}
	
}
