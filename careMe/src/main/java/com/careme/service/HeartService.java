package com.careme.service;

import org.springframework.stereotype.Service;

import com.careme.model.dto.HeartDto;

@Service
public interface HeartService {

	public HeartDto getHeartInfo (HeartDto hdto);
	
	public void insertHeartInfo(HeartDto hdto);

	public void updateHeartCheck(HeartDto hdto);
	
	public int memberCheck(int member_idx);

}
