package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.CarediaryDao;
import com.careme.model.dto.DefecationDto;

@Service
public class CarediaryService {
	@Autowired
	CarediaryDao careDiaryDao;
	
	public void setDao(CarediaryDao careDiaryDao) {
		this.careDiaryDao = careDiaryDao;
	}
	
	public List<DefecationDto> selectSmallDef() {
		return careDiaryDao.selectSmallDef();
	}
	
	public List<DefecationDto> selectBigDef() {
		return careDiaryDao.selectBigDef();
	}

}
