package com.careme.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.MemberDto;

public class AdminDao  extends SqlSessionDaoSupport {
	
	public List<MemberDto> selectMemberList() {
		return getSqlSession().selectList("admin.selectMemberList");
	}
	
	public List<MemberDto> searchMemberList(HashMap<String, Object> params) {
		return getSqlSession().selectList("admin.searchMemberList", params);
	}
	
	public int selectTotalCount() {
		return getSqlSession().selectOne("admin.selectTotalCount");
	}


}
