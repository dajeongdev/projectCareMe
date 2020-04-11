package com.careme.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.PetDao;
import com.careme.model.dto.PetDto;

@Service
public class PetRegistService {
	@Autowired
	PetDao dao;
	
	public int registPet(MultipartHttpServletRequest request) {
		HttpSession session = request.getSession();
		int memberIdx = 1; // 임시로 저장
		
		PetDto dto = new PetDto();
		
		
		return 1;
	}

}
