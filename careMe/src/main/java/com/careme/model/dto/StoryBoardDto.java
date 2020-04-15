package com.careme.model.dto;

import java.time.LocalDateTime;

public class StoryBoardDto {
	private int story_board_idx;
	private int member_idx;
	private int tag_idx;
	private String title;
	private String content;
	private int heart;
	private int view_count;
	private LocalDateTime reg_date;
	private LocalDateTime update_date;
	private String del_yn;
	
	private String member_id;
	
	private int story_file_idx;
	private String file_name;
	private String file_path;
	private Long file_size;
	
	
	public int getStory_file_idx() {
		return story_file_idx;
	}
	public void setStory_file_idx(int story_file_idx) {
		this.story_file_idx = story_file_idx;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_id() {
		return member_id;
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
	public int getTag_idx() {
		return tag_idx;
	}
	public void setTag_idx(int tag_idx) {
		this.tag_idx = tag_idx;
	}
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart = heart;
	}
	public int getStory_board_idx() {
		return story_board_idx;
	}
	public void setStory_board_idx(int story_board_idx) {
		this.story_board_idx = story_board_idx;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}
