package com.careme.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

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


}
