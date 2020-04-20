package com.careme.model.dto;

public class TagDto {
	private int tag_idx; // 태그 번호
	private String tag_name; // 태그명
	private int member_idx; // 사용자 번호
	private String del_yn;
	private int board_use_tag_idx;
	private int board_idx; // 사용된 글번호
	private String board_type; // 글타입('d'/ 's')
	
	public int getBoard_use_tag_idx() {
		return board_use_tag_idx;
	}
	public void setBoard_use_tag_idx(int board_use_tag_idx) {
		this.board_use_tag_idx = board_use_tag_idx;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public int getTag_idx() {
		return tag_idx;
	}
	public void setTag_idx(int tag_idx) {
		this.tag_idx = tag_idx;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
}
