package com.careme.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;

public interface PetService {
	
	public List<PetSpeciesDto> selectPetSpeciesLevel1();
	
	List<PetSpeciesDto> selectPetSpeciesLevel2(int level1);
	
	public PetDto selectPet(int petIdx);
	
	public int insertPet(MultipartHttpServletRequest request);
	
	public int updatePet();
	
	public int deletePut();
	
}
