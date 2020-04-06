package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.PetDao;
import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;

@Service
public class PetService {
	
	@Autowired
	PetDao dao;
	
	public void setDao(PetDao dao) {
		this.dao = dao;
	}
	
	public List<PetSpeciesDto> selectPetSpeciesLevel1() {
		return dao.selectPetSpeciesLevel1();
	}
	
	public List<PetSpeciesDto> selectPetSpeciesLevel2(int level1) {
		return dao.selectPetSpeciesLevel2(level1);
	}
	
	public int registPet(PetDto dto) {
		return 1;
	}

}
