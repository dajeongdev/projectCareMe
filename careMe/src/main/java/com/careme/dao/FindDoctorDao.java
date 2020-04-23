package com.careme.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.DoctorMajorDto;
import com.careme.model.dto.DoctorMajorWithSpeciesName;

public class FindDoctorDao extends SqlSessionDaoSupport {
	public List<DoctorDto> getDoctorDtoList(HashMap<String, Object> params) {
		return getSqlSession().selectList("findDoctor.selectDoctorList", params);
	}
	
	public List<DoctorDto> getDoctorDtoListOrderByMatching(HashMap<String, Object> params) {
		return getSqlSession().selectList("findDoctor.selectDoctorListOrderByMatching", params);
	}
	
	public List<DoctorDto> getPopularDoctorDtoList(String date) {
		return getSqlSession().selectList("findDoctor.selectPopularDoctorList", date);
	}
	
	public List<DoctorMajorDto> getDoctorMajorDtoList(int doctor_idx) {
		return getSqlSession().selectList("findDoctor.selectDoctorMajor", doctor_idx);
	}
	
	public List<DoctorMajorWithSpeciesName> getDoctorMajorWithSpeciesName(int doctor_idx) {
		return getSqlSession().selectList("findDoctor.selectDoctorMajorWithSpeciesName", doctor_idx);
	}
	
	public int selectTotalCount() {
		return getSqlSession().selectOne("findDoctor.selectTotalCount");
	}
	

}
