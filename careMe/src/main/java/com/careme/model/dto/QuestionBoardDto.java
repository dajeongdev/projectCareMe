package com.careme.model.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class QuestionBoardDto {
	
	int question_table_idx;
	int member_idx;
	int doctor_idx;
	String member_id;
	Map<String,Integer> param;
	
	int start_idx;
	int contentPerPage;
	
	String title;
	String question_type;
	String content;
	int view_count;
	String is_private;
	LocalDateTime reg_date;
	LocalDateTime update_date;
	String del_yn;
	int pet_care_idx;
	
	private String file_name;
	private String file_path;
	private Long file_size;
	
	int pet_idx;
	int pet_species_idx;
	int pet_species_level;
	String pet_species_name;
	
	
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
	public int getDoctor_idx() {
		return doctor_idx;
	}
	public void setDoctor_idx(int doctor_idx) {
		this.doctor_idx = doctor_idx;
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
	public Long getFile_size() {
		return file_size;
	}
	public void setFile_size(Long file_size) {
		this.file_size = file_size;
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
	public int getPet_species_level() {
		return pet_species_level;
	}
	public void setPet_species_level(int pet_species_level) {
		this.pet_species_level = pet_species_level;
	}
	public String getPet_species_name() {
		return pet_species_name;
	}
	public void setPet_species_name(String pet_species_name) {
		this.pet_species_name = pet_species_name;
	}
	public Map<String, Integer> getParam() {
		return param;
	}
	public void setParam(Map<String, Integer> param) {
		this.param = param;
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
	
	public int getPet_care_idx() {
		return pet_care_idx;
	}
	public void setPet_care_idx(int pet_care_idx) {
		this.pet_care_idx = pet_care_idx;
	}
	@Override
	public String toString() {
		return "QuestionBoardDto [question_table_idx=" + question_table_idx + ", member_idx=" + member_idx
				+ ", doctor_idx=" + doctor_idx + ", member_id=" + member_id + ", param=" + param + ", start_idx="
				+ start_idx + ", contentPerPage=" + contentPerPage + ", title=" + title + ", question_type="
				+ question_type + ", content=" + content + ", view_count=" + view_count + ", is_private=" + is_private
				+ ", reg_date=" + reg_date + ", update_date=" + update_date + ", del_yn=" + del_yn + ", pet_care_idx="
				+ pet_care_idx + ", file_name=" + file_name + ", file_path=" + file_path + ", file_size=" + file_size
				+ ", pet_idx=" + pet_idx + ", pet_species_idx=" + pet_species_idx + ", pet_species_level="
				+ pet_species_level + ", pet_species_name=" + pet_species_name + "]";
	}
	
}