package com.careme.model.dto;

import java.time.LocalDateTime;

public class StoryCommentDto {
	private int story_comment_idx;
	private int story_board_idx;
	private int member_idx;
	private String content;
	private int heart;
	private String member_id;
	private LocalDateTime reg_date;
	private LocalDateTime update_date;
	private String del_yn;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public int getStory_comment_idx() {
		return story_comment_idx;
	}
	public void setStory_comment_idx(int story_comment_idx) {
		this.story_comment_idx = story_comment_idx;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
