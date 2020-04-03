package com.careme.model.dto;

import java.time.LocalDateTime;

public class QuestionBoardDto {
	
	int question_table_idx;
	int member_idx;
	String member_id;
	String title;
	String question_type;
	int doctor_idx;
	int pet_idx;
	int pet_species_idx;
	String content;
	int view_count;
	String is_private;
	LocalDateTime reg_date;
	LocalDateTime update_date;
	String del_yn;
	
	public int getQuestion_table_idx() {
		return question_table_idx;
	}
	public void setQuestion_table_idx(int question_table_idx) {
		this.question_table_idx = question_table_idx;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public int getDoctor_idx() {
		return doctor_idx;
	}
	public void setDoctor_idx(int doctor_idx) {
		this.doctor_idx = doctor_idx;
	}
	public int getPet_idx() {
		return pet_idx;
	}
	public void setPet_idx(int pet_idx) {
		this.pet_idx = pet_idx;
	}
	public int getPet_species_idx() {
		return pet_species_idx;
	}
	public void setPet_species_idx(int pet_species_idx) {
		this.pet_species_idx = pet_species_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public String getIs_private() {
		return is_private;
	}
	public void setIs_private(String is_private) {
		this.is_private = is_private;
	}
	public LocalDateTime getReg_date() {
		return reg_date;
	}
	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}
	public LocalDateTime getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	@Override
	public String toString() {
		return "Question_BoardDto [question_table_idx=" + question_table_idx + ", member_idx=" + member_idx + ", title="
				+ title + ", question_type=" + question_type + ", doctor_idx=" + doctor_idx + ", pet_idx=" + pet_idx
				+ ", pet_species_idx=" + pet_species_idx + ", content=" + content + ", view_count=" + view_count
				+ ", is_private=" + is_private + ", reg_date=" + reg_date + ", update_date=" + update_date + ", del_yn="
				+ del_yn + "]";
	}
}
