package com.careme.model.command;


public class FileUploadCommand {
	private String fileOriginName; // 업로드할때 fileName
	private String filePath; // 파일이 저장된 경로 (프로젝트root) /resource/upload/... + 실제 파일 저장이름 
	private Long fileSize;
	
	public String getFileOriginName() {
		return fileOriginName;
	}
	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Override
	public String toString() {
		return "FileUploadCommand [fileOriginName=" + fileOriginName + ", filePath=" + filePath + ", fileSize="
				+ fileSize + "]";
	}

}
