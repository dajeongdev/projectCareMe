package com.careme.model.dto;

public class PetCareDto {
	private int pet_care_idx;
	private int pet_idx;
	private String title;
	private int exercise;
	private int urine;
	private int feces;
	private String memo;
	private Double weight;
	private String diary_date;
	private String reg_date;
	
	public int getPet_care_idx() {
		return pet_care_idx;
	}
	public void setPet_care_idx(int pet_care_idx) {
		this.pet_care_idx = pet_care_idx;
	}
	public int getPet_idx() {
		return pet_idx;
	}
	public void setPet_idx(int pet_idx) {
		this.pet_idx = pet_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getExercise() {
		return exercise;
	}
	public void setExercise(int exercise) {
		this.exercise = exercise;
	}
	public int getUrine() {
		return urine;
	}
	public void setUrine(int urine) {
		this.urine = urine;
	}
	public int getFeces() {
		return feces;
	}
	public void setFeces(int feces) {
		this.feces = feces;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(String diary_date) {
		this.diary_date = diary_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "PetCareDto [pet_care_idx=" + pet_care_idx + ", pet_idx=" + pet_idx + ", title=" + title + ", exercise="
				+ exercise + ", urine=" + urine + ", feces=" + feces + ", memo=" + memo + ", weight=" + weight
				+ ", diary_date=" + diary_date + ", reg_date=" + reg_date + "]";
	}
}
