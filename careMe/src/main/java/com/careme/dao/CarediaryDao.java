package com.careme.dao;

import java.util.List;
import java.util.Map;

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
	
	public int writeCarediary(PetCareDto dto) {
		return getSqlSession().insert("carediary.writeCarediary", dto);
	}
	
	public int writeCarediaryFile(PetCareFileDto dto) {
		return getSqlSession().insert("carediary.writeCarediaryFile", dto);
	}
	
	public PetCareDto selectCarediaryByIdx(int idx) {
		return getSqlSession().selectOne("carediary.selectCarediaryByIdx", idx);
	}
	
	public List<PetCareDto> selectCarediaryListByPetIdx(int petIdx) {
		return getSqlSession().selectList("carediary.selectCarediaryByIdx", petIdx);
	}
	
	public List<PetCareFileDto> selectCarediaryFileList(int carediaryIdx) {
		return getSqlSession().selectList("carediary.selectCarediaryFileList", carediaryIdx);
	}
	
	public int updateCarediary(PetCareDto dto) {
		return getSqlSession().update("carediary.updateDiary", dto);
	}
	
	public int deleteCarediaryFiles(Map<Object, String> list) {
		return getSqlSession().update("carediary.deleteCarediaryFiles", list);
	}
	

}
