package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;

public interface PetService {
	
	public List<PetSpeciesDto> selectPetSpeciesLevel1();
	
	List<PetSpeciesDto> selectPetSpeciesLevel2(int level1);
	
	List<PetSpeciesDto> selectSpeciesLevel2BySelfIdx(int level2);
	
	public PetDto selectPet(int petIdx);
	
	public List<PetDto> selectPetList(int memberIdx);
	
	public int insertPet(MultipartHttpServletRequest request);
	
	public int updatePet(MultipartHttpServletRequest request);
	
	public int deletePet(HttpServletRequest request);
	
	public int findSeletedPet(int memberIdx);
	
	public int updateToselectedPet(int petIdx);
	
	public void deSelectPet(int memberIdx);
	
}
