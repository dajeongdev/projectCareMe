package com.careme.model.dto;

import java.time.LocalDateTime;

public class BoardCommentDto {

	int question_board_comment_idx;
	int question_table_idx;
	int member_idx;
	String member_id;
	String content;
	int heart;
	LocalDateTime reg_date;
	String del_yn;

	public int getQuestion_board_comment_idx() {
		return question_board_comment_idx;
	}
	public void setQuestion_board_comment_idx(int question_board_comment_idx) {
		this.question_board_comment_idx = question_board_comment_idx;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart = heart;
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
		return "BoardCommentDto [question_board_comment_idx=" + question_board_comment_idx + ", question_table_idx="
				+ question_table_idx + ", member_idx=" + member_idx + ", member_id=" + member_id + ", content="
				+ content + ", heart=" + heart + ", reg_date=" + reg_date + ", del_yn=" + del_yn + "]";
	}
}
