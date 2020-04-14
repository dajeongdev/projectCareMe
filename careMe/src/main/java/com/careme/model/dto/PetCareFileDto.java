package com.careme.model.dto;

import java.time.LocalDateTime;

public class PetCareFile {
	private int pet_care_file_idx;
	private int pet_care_idx;
	private String file_path;
	private int file_size;
	private String file_name;
	private LocalDateTime reg_date;
	private String del_yn;
	
	public int getPet_care_file_idx() {
		return pet_care_file_idx;
	}
	public void setPet_care_file_idx(int pet_care_file_idx) {
		this.pet_care_file_idx = pet_care_file_idx;
	}
	public int getPet_care_idx() {
		return pet_care_idx;
	}
	public void setPet_care_idx(int pet_care_idx) {
		this.pet_care_idx = pet_care_idx;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public LocalDateTime getReg_date() {
		return reg_date;
	}
	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
	@Override
	public String toString() {
		return "PetCareFile [pet_care_file_idx=" + pet_care_file_idx + ", pet_care_idx=" + pet_care_idx + ", file_path="
				+ file_path + ", file_size=" + file_size + ", file_name=" + file_name + ", reg_date=" + reg_date
				+ ", del_yn=" + del_yn + "]";
	}

}
