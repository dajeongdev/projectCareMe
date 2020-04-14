package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.CarediaryDao;
import com.careme.model.command.CarediaryCommand;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.dto.DefecationDto;
import com.careme.model.dto.PetCareDto;

@Service
public class CarediaryService {
	@Autowired
	CarediaryDao careDiaryDao;
	
	public void setDao(CarediaryDao careDiaryDao) {
		this.careDiaryDao = careDiaryDao;
	}
	
	@Autowired
	FileUploadService fileuploadService;

	public void setFileuploadService(FileUploadService fileuploadService) {
		this.fileuploadService = fileuploadService;
	}

	public List<DefecationDto> selectSmallDef() {
		return careDiaryDao.selectSmallDef();
	}
	
	public List<DefecationDto> selectBigDef() {
		return careDiaryDao.selectBigDef();
	}
	
	public int writeDiary(CarediaryCommand command, MultipartHttpServletRequest request) {
		PetCareDto dto = new PetCareDto();
		
		dto.setPet_idx((int) request.getSession().getAttribute("selectedPet"));
		dto.setTitle(command.getTitle());
		dto.setExercise(command.getExercise());
		dto.setWeight(command.getWeight());
		dto.setUrine(command.getUrine());
		dto.setFeces(command.getFeces());
		dto.setMemo(command.getMemo());
		dto.setDiary_date(command.getDiaryDate());
				
		int diaryIdx = careDiaryDao.writeDiary(dto);
		
		if (diaryIdx > 0) {
			List<FileUploadCommand> files; 
			files = fileuploadService.upload(request, "/img/pet/carediary/");
		}
		
		// file loop 파일 data insert
		
		
		return 0;
	}

}
