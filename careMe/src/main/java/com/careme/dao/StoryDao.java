package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.StoryBoardDto;

public class StoryDao extends SqlSessionDaoSupport {
	public List<StoryBoardDto> selectAll() {
		return getSqlSession().selectList("story.selectAll");
	}
}
