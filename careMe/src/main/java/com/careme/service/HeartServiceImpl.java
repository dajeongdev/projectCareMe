package com.careme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.HeartDao;
import com.careme.model.dto.HeartDto;

@Service("HeartService")
public class HeartServiceImpl implements HeartService{

	@Autowired
	HeartDao heartDao;
	
	public void setHeartDao(HeartDao heartDao) {
		this.heartDao = heartDao;
	}
	
	public HeartDto getHeartInfo (int idx) {
		return heartDao.getHeartInfo(idx);
	}
	
	public void insertHeartInfo(HeartDto hdto) {
		heartDao.insertHeartInfo(hdto);
	}
	
	public int updateHeartInfo(HeartDto hdto) {
		return heartDao.updateHeartInfo(hdto);
	}
	
}
