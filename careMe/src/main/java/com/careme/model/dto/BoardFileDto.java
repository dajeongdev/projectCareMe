package com.careme.model.dto;

import java.time.LocalDateTime;

public class BoardFileDto {

	int table_ref_idx;
	int question_table_idx;
	String file_name;
	String file_path;
	long file_size;
	String reg_date;
	String del_yn;
	
	public int getTable_ref_idx() {
		return table_ref_idx;
	}
	public void setTable_ref_idx(int table_ref_idx) {
		this.table_ref_idx = table_ref_idx;
	}
	public int getQuestion_table_idx() {
		return question_table_idx;
	}
	public void setQuestion_table_idx(int question_table_idx) {
		this.question_table_idx = question_table_idx;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
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
		return "BoardFileDto [table_ref_idx=" + table_ref_idx + ", question_table_idx=" + question_table_idx
				+ ", file_name=" + file_name + ", file_path=" + file_path + ", file_size=" + file_size + ", reg_date="
				+ reg_date + ", del_yn=" + del_yn + "]";
	}
	
}
