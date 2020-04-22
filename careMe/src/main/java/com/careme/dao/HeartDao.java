package com.careme.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.HeartDto;

public class HeartDao extends SqlSessionDaoSupport {

	public HeartDto getHeartInfo (int idx) {
		return getSqlSession().selectOne("heartBrd.getHeartInfo", idx);
	}
	
	public void insertHeartInfo(HeartDto hdto) {
		getSqlSession().selectOne("heartBrd.newHeartInfo", hdto);
	}
	
	public int updateHeartInfo (HeartDto hdto) {
		return getSqlSession().selectOne("heartBrd.updateCheckHeart", hdto);
	}
	
	
}
