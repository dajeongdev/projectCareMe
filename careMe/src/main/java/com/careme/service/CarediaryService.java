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
import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.SessionCommand;
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
	
	@Autowired
	PageNumberService pageService;
	public void setPageService(PageNumberServiceImpl pageNumberServiceImple) {
		this.pageService = pageNumberServiceImple;
	}
	

	public List<DefecationDto> selectSmallDef() {
		return carediaryDao.selectSmallDef();
	}
	
	public List<DefecationDto> selectBigDef() {
		return carediaryDao.selectBigDef();
	}
	
	public void writeCarediary(PetCareDto dto, MultipartHttpServletRequest request) throws SQLException {
		carediaryDao.writeCarediary(dto);
		
		int diaryIdx = dto.getPet_care_idx();
		if (diaryIdx > 0) processFile(diaryIdx, request);		
	}
	
	public void updateCarediary(PetCareDto dto, Integer[] deletedFiles, MultipartHttpServletRequest request) {
		int res = carediaryDao.updateCarediary(dto);
		int diaryIdx = dto.getPet_care_idx();
		if (res == 1) {
			// 파일삭제
			if (deletedFiles.length > 0) {
				Map<String, Object> deleteList = new HashMap<String, Object>();
				List<Integer> list = Arrays.asList(deletedFiles);
				deleteList.put("deleteList", list);
				
				carediaryDao.deleteCarediaryFiles(deleteList);
			}
			
			// 추가된 파일 등록
			if (request.getFileMap().size() > 0) processFile(diaryIdx, request);
		}
	}
	
	// 케어다이어리 하나 정보
	public CarediaryCommand getCarediaryByIdx(int carediaryIdx) {
		CarediaryCommand command = new CarediaryCommand();
		
		PetCareDto dto = carediaryDao.selectCarediaryByIdx(carediaryIdx);
		List<PetCareFileDto> filesDto = carediaryDao.selectCarediaryFileList(carediaryIdx);
		
		command.setDiary(dto);
		command.setFiles(filesDto);
		
		return command;
	}
	
	public HashMap<String, Object> getCarediaryListByPetIdx(int petIdx, int currentPage, int contentPerPage) {
		HashMap<String, Object> data = new HashMap<String, Object>();

		List<CarediaryCommand> list = new ArrayList<CarediaryCommand>();
		HashMap<String, Integer> param = new HashMap<String, Integer>();
		
		int start = pageService.getStartIdx(currentPage, contentPerPage);
		param.put("pet_idx", petIdx);
		param.put("start", start);
		param.put("contentPerPage", contentPerPage);
		list = carediaryDao.selectCarediaryListByPetIdx(param);
		
		int totalCount = carediaryDao.selectTotalCount();
		
		for (CarediaryCommand command : list) {
			int diaryIdx = command.getDiary().getPet_care_idx();
			command.setFiles(carediaryDao.selectCarediaryFileList(diaryIdx));
		}
		
		String path = petIdx + "?page=";
		PageNumberCommand paging = pageService.paging(totalCount, contentPerPage, currentPage, path);
		
		data.put("list", list);
		data.put("paging", paging);
		return data;
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
