package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;

public class PetDao  extends SqlSessionDaoSupport {
	public List<PetSpeciesDto> selectPetSpeciesLevel1 () {
		return getSqlSession().selectList("pet.selectSpeciesLevel1"); 
	}
	
	public List<PetSpeciesDto> selectPetSpeciesLevel2 (int level1) {
		return getSqlSession().selectList("pet.selectSpeciesLevel2", level1); 
	}
	
	public int insertPet(PetDto dto) {
		return getSqlSession().insert("pet.insertPet", dto);
	}
	
	public PetDto selectPet(int petIdx) {
		return getSqlSession().selectOne("pet.selectPet", petIdx);
	}

}
