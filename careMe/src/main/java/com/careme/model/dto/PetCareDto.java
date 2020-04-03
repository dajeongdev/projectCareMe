package com.careme.model.dto;

import java.time.LocalDateTime;

public class PetCareDto {
	private int pet_care_idx;
	private int pet_idx;
	private int exercise;
	private int urine;
	private int feces;
	private String memo;
	private Double weight;
	private String del_yn;
	private LocalDateTime reg_date;
	
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
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public LocalDateTime getReg_date() {
		return reg_date;
	}
	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "PetCareDto [pet_care_idx=" + pet_care_idx + ", pet_idx=" + pet_idx + ", exercise=" + exercise
				+ ", urine=" + urine + ", feces=" + feces + ", memo=" + memo + ", weight=" + weight + ", del_yn="
				+ del_yn + ", reg_date=" + reg_date + "]";
	}

}
