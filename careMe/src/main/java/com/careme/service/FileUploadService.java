package com.careme.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.command.FileUploadCommand;

@Service
public class FileUploadService {
	
	public List<FileUploadCommand> upload(MultipartHttpServletRequest request, String path) {
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String resourcesPath = "resources/upload";
		
		// upload 한 file정보를 담고있는 command
		List<FileUploadCommand> files = new ArrayList<FileUploadCommand>(); 
		Iterator<String> fileNames = request.getFileNames();
		
		while (fileNames.hasNext()) {
			List<MultipartFile> uploadFiles = request.getFiles(fileNames.next());
			
			for (MultipartFile file : uploadFiles) {
				FileUploadCommand command = new FileUploadCommand();
				String originName = file.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf("."), originName.length());
				Long size = file.getSize();
				
				// 저장할 파일이름을 랜덤하게 변경
				String saveFileName = getSaveFileName(ext);
				// 실제 저장경로
				String fileSavePath = rootPath + resourcesPath + path;
				//System.out.println("fileSavePath:" + fileSavePath);
				try {
					writeFile(file, saveFileName, fileSavePath);
					command.setFileOriginName(originName);
					command.setFilePath(resourcesPath + path + saveFileName);
					command.setFileSize(size);
					files.add(command);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return files;
	}
	
	private void writeFile(MultipartFile multipartFile, String saveFileName, String path) throws IOException {
			File dest = new File(path + saveFileName); 
			
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdirs();
				System.out.println("폴더생성됨");
			} else {
				System.out.println("존재하는 폴더");
			}	
			
			try {
				multipartFile.transferTo(dest);
				System.out.println("파일저장 성공");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private String getSaveFileName(String extName) {
		return System.currentTimeMillis() + ((int) (Math.random() * 10000)) + extName;
	}

}

