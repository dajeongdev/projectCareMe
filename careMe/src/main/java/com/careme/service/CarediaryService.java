package com.careme.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.CarediaryDao;
import com.careme.model.command.CarediaryCommand;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.dto.DefecationDto;
import com.careme.model.dto.PetCareDto;
import com.careme.model.dto.PetCareFileDto;

@Service
public class CarediaryService {
	@Autowired
	CarediaryDao carediaryDao;
	
	public void setDao(CarediaryDao carediaryDao) {
		this.carediaryDao = carediaryDao;
	}
	
	@Autowired
	FileUploadService fileuploadService;

	public void setFileuploadService(FileUploadService fileuploadService) {
		this.fileuploadService = fileuploadService;
	}

	public List<DefecationDto> selectSmallDef() {
		return carediaryDao.selectSmallDef();
	}
	
	public List<DefecationDto> selectBigDef() {
		return carediaryDao.selectBigDef();
	}
	
	public void writeCarediary(PetCareDto dto, MultipartHttpServletRequest request) throws SQLException {
		dto.setPet_idx((int) request.getSession().getAttribute("pet_idx"));
		
		// carediary 메인 테이블에 insert 하고 insert 한 데이터 idx를 pet_care_idx 멤버에 저장
		carediaryDao.writeCarediary(dto);
		
		int diaryIdx = dto.getPet_care_idx();
		
		if (diaryIdx > 0) processFile(diaryIdx, request);		
	}
	
	public void updateCarediary(PetCareDto dto, Integer[] deletedFiles, MultipartHttpServletRequest request) {
		System.out.println("서비스 도착");
		int res = carediaryDao.updateCarediary(dto);
		int diaryIdx = dto.getPet_care_idx();
		System.out.println("삭제파일 길이: " + deletedFiles.length);
		if (res == 1) {
			// 파일삭제
			if (deletedFiles.length > 0) {
				System.out.println("삭제파일 길이: " + deletedFiles.length);
				Map<String, Object> deleteList = new HashMap<String, Object>();
				List<Integer> list = Arrays.asList(deletedFiles);
				deleteList.put("deleteList", list);
				
				carediaryDao.deleteCarediaryFiles(deleteList);
			}
			
			// 추가된 파일 등록
			if (request.getFileMap().size() > 0) processFile(diaryIdx, request);
		}
	}
	
	public CarediaryCommand getCarediaryByIdx(int carediaryIdx) {
		CarediaryCommand command = new CarediaryCommand();
		
		PetCareDto dto = carediaryDao.selectCarediaryByIdx(carediaryIdx);
		List<PetCareFileDto> filesDto = carediaryDao.selectCarediaryFileList(carediaryIdx);
		
		command.setDiary(dto);
		command.setFiles(filesDto);
		
		return command;
	}
	
	public List<CarediaryCommand> getCarediaryListByPetIdx(int petIdx) {
		List<CarediaryCommand> list = new ArrayList<CarediaryCommand>();
		List<PetCareDto> dtoList = carediaryDao.selectCarediaryListByPetIdx(petIdx);
		
		for (PetCareDto dto : dtoList) {
			CarediaryCommand command = new CarediaryCommand();
			command.setDiary(dto);
			command.setFiles(carediaryDao.selectCarediaryFileList(dto.getPet_care_idx()));
			
			list.add(command);
		}
		
		return list;
	}
	
	public void processFile(int diaryIdx, MultipartHttpServletRequest request) {
		
		List<FileUploadCommand> files;			
		files = fileuploadService.upload(request, "/img/pet/carediary/");
		
		for (FileUploadCommand file : files) {
			PetCareFileDto fileDto = new PetCareFileDto();
			fileDto.setPet_care_idx(diaryIdx);
			fileDto.setFile_name(file.getFileOriginName());
			fileDto.setFile_path(file.getFilePath());
			fileDto.setFile_size(file.getFileSize());
			
			carediaryDao.writeCarediaryFile(fileDto);
		}
		
	}
	
	

	

}
