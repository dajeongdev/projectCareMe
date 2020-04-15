package com.careme.model.command;

import java.time.LocalDateTime;
import java.util.List;

import com.careme.model.dto.PetCareFileDto;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryFileDto;

public class StoryCommand {
	private StoryBoardDto dto;
	private List<StoryFileDto> files;
	
	public StoryBoardDto getFileDto() {
		return dto;
	}
	public void setFileDto(StoryBoardDto dto) {
		this.dto = dto;
	}
	public List<StoryFileDto> getFiles() {
		return files;
	}
	public void setFiles(List<StoryFileDto> files) {
		this.files = files;
	}
	
}
