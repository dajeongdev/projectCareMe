package com.careme.service;

import java.sql.SQLException;
import java.util.List;

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
	
	public void writeDiary(CarediaryCommand command, MultipartHttpServletRequest request) throws SQLException {
		PetCareDto dto = new PetCareDto();
		dto.setPet_idx((int) request.getSession().getAttribute("pet_idx"));
		dto.setTitle(command.getTitle());
		dto.setExercise(command.getExercise());
		dto.setWeight(command.getWeight());
		dto.setUrine(command.getUrine());
		dto.setFeces(command.getFeces());
		dto.setMemo(command.getMemo());
		dto.setDiary_date(command.getDiaryDate());
		
		System.out.println("지금 선택된 pet ::" + dto.getPet_idx());
				
		careDiaryDao.writeDiary(dto);
		int diaryIdx = dto.getPet_care_idx();
		
		if (diaryIdx > 0) {
			List<FileUploadCommand> files;			
			files = fileuploadService.upload(request, "/img/pet/carediary/");
			
			for (FileUploadCommand file : files) {
				PetCareFileDto fileDto = new PetCareFileDto();
				fileDto.setPet_care_idx(diaryIdx);
				fileDto.setFile_name(file.getFileOriginName());
				fileDto.setFile_path(file.getFilePath());
				fileDto.setFile_size(file.getFileSize());
				
				careDiaryDao.writeDiaryFile(fileDto);
			}
		}
	}

}
