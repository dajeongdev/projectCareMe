package com.careme.model.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CarediaryCommand {
	private String diaryDate;
	private String title;
	private int exercise;
	private Double weight;
	private int urine;
	private int feces;
	private String memo;
	private List<MultipartFile> files;
	
	public String getDiaryDate() {
		return diaryDate;
	}
	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getExercise() {
		return exercise;
	}
	public void setExercise(int exercise) {
		this.exercise = exercise;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public int getUrine() {
		return urine;
	}
	public void setUrine(int urine) {
		this.urine = urine;
	}
	public int getFeces() {
		return feces;
	}
	public void setFeces(int feces) {
		this.feces = feces;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "CarediaryCommand [diaryDate=" + diaryDate + ", title=" + title + ", exercise=" + exercise + ", weight="
				+ weight + ", urine=" + urine + ", feces=" + feces + ", memo=" + memo + ", files=" + files + "]";
	}

}
