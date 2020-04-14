package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.DefecationDto;

public class CarediaryDao  extends SqlSessionDaoSupport {
	
	public List<DefecationDto> selectSmallDef() {
		return getSqlSession().selectList("carediary.selectSmallDef");
	}
	
	public List<DefecationDto> selectBigDef() {
		return getSqlSession().selectList("carediary.selectBigDef");
	}
	

}
