package com.careme.model.dto;

import java.time.LocalDateTime;

public class StoryFileDto {
	private int story_file_idx;
	private int story_board_idx;
	private String file_name;
	private String file_path;
	private int file_size;
	private LocalDateTime reg_date;
	private String del_yn;

	public int getStory_file_idx() {
		return story_file_idx;
	}
	public void setStory_file_idx(int story_file_idx) {
		this.story_file_idx = story_file_idx;
	}
	public int getStory_board_idx() {
		return story_board_idx;
	}
	public void setStory_board_idx(int story_board_idx) {
		this.story_board_idx = story_board_idx;
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
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
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
	
}
