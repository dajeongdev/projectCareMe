package com.careme.model.dto;

public class DefecationDto {
	private int defecation_idx;
	private String defecation_type;
	private String defecation_color;
	private String defecation_quality;
	
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
	public String getDefecation_color() {
		return defecation_color;
	}
	public void setDefecation_color(String defecation_color) {
		this.defecation_color = defecation_color;
	}
	public String getDefecation_quality() {
		return defecation_quality;
	}
	public void setDefecation_quality(String defecation_quality) {
		this.defecation_quality = defecation_quality;
	}
	
	@Override
	public String toString() {
		return "DefecationDto [defecation_idx=" + defecation_idx + ", defecation_type=" + defecation_type
				+ ", defecation_color=" + defecation_color + ", defecation_quality=" + defecation_quality + "]";
	}

}
