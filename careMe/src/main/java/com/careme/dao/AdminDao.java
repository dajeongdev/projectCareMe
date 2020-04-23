package com.careme.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.MemberDto;

public class AdminDao  extends SqlSessionDaoSupport {
	public MemberDto selectMember(int memberIdx) {
		return getSqlSession().selectOne("admin.selectMember", memberIdx);
	}
	
	public List<MemberDto> selectMemberList(HashMap<String, Object> params) {
		return getSqlSession().selectList("admin.selectMemberList", params);
	}
	
	public List<MemberDto> searchMemberList(HashMap<String, Object> params) {
		return getSqlSession().selectList("admin.searchMemberList", params);
	}
	
	public int selectTotalCount() {
		return getSqlSession().selectOne("admin.selectTotalCount");
	}
	
	public int updateMember(MemberDto memberDto) {
		return getSqlSession().update("admin.updateMember", memberDto);
	}
	
	public int deleteMember(int memberIdx) {
		return getSqlSession().update("admin.deleteMember", memberIdx);
	}
	
	public List<DoctorDto> getDoctorList(HashMap<String, Object> params) {
		return getSqlSession().selectList("admin.selectDoctorList", params);
	}
	
	public String getDoctorCert(int doctor_idx) {
		return getSqlSession().selectOne("admin.selectDoctorCert", doctor_idx);
	}

	public int updateDoctorCert(HashMap<String, Object> params) {
		return getSqlSession().update("admin.updateDoctorCert", params);
	}
}
