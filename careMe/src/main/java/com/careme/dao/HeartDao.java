package com.careme.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.HeartDto;

public class HeartDao extends SqlSessionDaoSupport {

	public HeartDto getHeartInfo (HeartDto hdto) {
		return getSqlSession().selectOne("heartBrd.getHeartInfo", hdto);
	}
	
	public void insertHeartInfo(HeartDto hdto) {
		getSqlSession().selectOne("heartBrd.newHeartInfo", hdto);
	}
	
	public void updateHeartCheck (HeartDto hdto) {
		getSqlSession().selectOne("heartBrd.updateHeartCheck", hdto);
	}
	
	public int memberCheck(int member_idx) {
		return getSqlSession().selectOne("heartBrd.memberCheck", member_idx);
	}
	
	
}
