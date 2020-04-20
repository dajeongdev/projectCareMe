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
	
	public HashMap<String, Object> getCarediaryListByPetIdx(int petIdx, int currentPage, int contentPerPage) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		//String path = petIdx + "?page="; 
		// 리턴받아야 하는 항목 =  totalCount
		// 쿼리실행시 필요한 항목 startIndex, 조건, contentPerPage
		// startIndex 를 static으로 해서 command 구하기전에 얻기
		//PageNumberCommand pageCommand = pageService.paging(totalCount, currentPage);
		//contentPerPage, , path
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
		System.out.println("토탈카운트:: " + totalCount);
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
