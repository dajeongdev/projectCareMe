package com.careme.model.command;

import java.util.List;

import com.careme.model.dto.PetCareDto;
import com.careme.model.dto.PetCareFileDto;

public class CarediaryCommand {
	private PetCareDto diary;
	private List<PetCareFileDto> files;
	public PetCareDto getDiary() {
		return diary;
	}
	public void setDiary(PetCareDto diary) {
		this.diary = diary;
	}
	public List<PetCareFileDto> getFiles() {
		return files;
	}
	public void setFiles(List<PetCareFileDto> files) {
		this.files = files;
	}
	
	
}
