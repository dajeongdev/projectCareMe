package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.StoryDto;

public class StoryDao extends SqlSessionDaoSupport {
	public List<StoryDto> selectAll() {
		return getSqlSession().selectList("story.selectAll");
	}
}
