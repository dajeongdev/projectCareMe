package com.careme.model.command;

import java.util.List;

import com.careme.model.dto.PetCareDto;
import com.careme.model.dto.PetCareFileDto;

public class CarediaryCommand {
	private PetCareDto diary;
	private List<PetCareFileDto> files;
	private String urineContent;
	private String fecesContent;
	
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
	public String getUrineContent() {
		return urineContent;
	}
	public void setUrineContent(String urineContent) {
		this.urineContent = urineContent;
	}
	public String getFecesContent() {
		return fecesContent;
	}
	public void setFecesContent(String fecesContent) {
		this.fecesContent = fecesContent;
	}
	@Override
	public String toString() {
		return "CarediaryCommand [diary=" + diary + ", files=" + files + ", urineContent=" + urineContent
				+ ", fecesContent=" + fecesContent + "]";
	}
	
	
}
