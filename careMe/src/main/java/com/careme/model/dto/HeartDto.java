package com.careme.model.dto;

public class HeartDto {

	private int board_comment_idx;
	private int member_idx;
	private String board_type;
	private String heartCheck;
	
	public int getBoard_comment_idx() {
		return board_comment_idx;
	}
	public void setBoard_comment_idx(int board_comment_idx) {
		this.board_comment_idx = board_comment_idx;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getHeartCheck() {
		return heartCheck;
	}
	public void setHeartCheck(String heartCheck) {
		this.heartCheck = heartCheck;
	}
	@Override
	public String toString() {
		return "HeartDto [board_comment_idx=" + board_comment_idx + ", member_idx=" + member_idx + ", board_type="
				+ board_type + ", heartCheck=" + heartCheck + "]";
	}
}
