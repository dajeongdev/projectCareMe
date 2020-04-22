package com.careme.service;

import org.springframework.stereotype.Service;

import com.careme.model.dto.HeartDto;

@Service
public interface HeartService {

	public HeartDto getHeartInfo (int idx);
	
	public void insertHeartInfo(HeartDto hdto);

	public int updateHeartInfo(HeartDto hdto);

}
