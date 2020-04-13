package com.careme.model.dto;

public class DefecationDto {
	private int defecation_idx;
	private String defecation_type;
	private String defecation_content;
	
	public int getDefecation_idx() {
		return defecation_idx;
	}
	public void setDefecation_idx(int defecation_idx) {
		this.defecation_idx = defecation_idx;
	}
	public String getDefecation_type() {
		return defecation_type;
	}
	public void setDefecation_type(String defecation_type) {
		this.defecation_type = defecation_type;
	}
	public String getDefecation_content() {
		return defecation_content;
	}
	public void setDefecation_content(String defecation_content) {
		this.defecation_content = defecation_content;
	}
	@Override
	public String toString() {
		return "DefecationDto [defecation_idx=" + defecation_idx + ", defecation_type=" + defecation_type
				+ ", defecation_content=" + defecation_content + "]";
	}
	


}
