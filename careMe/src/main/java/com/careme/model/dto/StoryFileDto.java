package com.careme.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class StoryFileDto {
	private int story_file_idx;
	private int story_board_idx;
	private String file_path;
	private String del_yn;
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
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
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
}
