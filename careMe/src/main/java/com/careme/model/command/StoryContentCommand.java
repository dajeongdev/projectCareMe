package com.careme.model.command;

import java.util.List;

import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryFileDto;

public class StoryContentCommand {
	private StoryBoardDto dto;
	private List<StoryFileDto> fileDto;
	
	public StoryBoardDto getDto() {
		return dto;
	}
	public void setDto(StoryBoardDto dto) {
		this.dto = dto;
	}
	public List<StoryFileDto> getFileDto() {
		return fileDto;
	}
	public void setFileDto(List<StoryFileDto> fileDto) {
		this.fileDto = fileDto;
	}
	
}
