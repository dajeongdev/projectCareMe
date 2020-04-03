package com.careme.model.dto;

import java.time.LocalDateTime;

public class StoryDto {
	private int story_board_idx;
	private int member_idx;
	private int pet_idx;
	private String title;
	private String content;
	private int like;
	private int view_count;
	private LocalDateTime reg_date;
	private LocalDateTime update_date;
	private String del_yn;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
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
