package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.careme.model.dto.PetSpeciesDto;

public class PetDao  extends SqlSessionDaoSupport {
	public List<PetSpeciesDto> selectPetSpeciesLevel1 () {
		return getSqlSession().selectList("selectSpeciesLevel1"); 
	}
	
	public List<PetSpeciesDto> selectPetSpeciesLevel2 (int level1) {
		return getSqlSession().selectList("selectSpeciesLevel2", level1); 
	}

}
