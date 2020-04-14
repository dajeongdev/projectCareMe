package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.DefecationDto;
import com.careme.model.dto.PetCareDto;
import com.careme.model.dto.PetCareFileDto;

public class CarediaryDao  extends SqlSessionDaoSupport {
	
	public List<DefecationDto> selectSmallDef() {
		return getSqlSession().selectList("carediary.selectSmallDef");
	}
	
	public List<DefecationDto> selectBigDef() {
		return getSqlSession().selectList("carediary.selectBigDef");
	}
	
	public int writeDiary(PetCareDto dto) {
		return getSqlSession().insert("carediary.writeDiary", dto);
	}
	
	public int writeDiaryFile(PetCareFileDto dto) {
		return getSqlSession().insert("carediary.writeDiaryFile", dto);
	}

}
