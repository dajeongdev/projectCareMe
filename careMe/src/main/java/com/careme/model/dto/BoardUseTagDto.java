package com.careme.model.dto;

public class BoardUseTagDto {

	int board_use_tag_idx;
	int board_idx;
	int tag_idx;
	String board_type;
	
	
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
	public int getTag_idx() {
		return tag_idx;
	}
	public void setTag_idx(int tag_idx) {
		this.tag_idx = tag_idx;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	
	@Override
	public String toString() {
		return "boardUseTagDto [board_use_tag_idx=" + board_use_tag_idx + ", board_idx=" + board_idx + ", tag_idx="
				+ tag_idx + ", board_type=" + board_type + "]";
	}
	
	
	
}
