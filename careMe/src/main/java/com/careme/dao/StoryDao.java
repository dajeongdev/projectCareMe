package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;

public class StoryDao extends SqlSessionDaoSupport {
	public List<StoryBoardDto> listing() {
		return getSqlSession().selectList("story.list");
	}
	
	public void insert(StoryBoardDto dto) {
		getSqlSession().insert("story.insert", dto);
	}
	
	public StoryBoardDto select(int story_board_idx) {
		return getSqlSession().selectOne("story.select", story_board_idx);
	}
	
	public void update(StoryBoardDto dto) {
		getSqlSession().update("story.update", dto);
	}
	
	public void delete(int story_board_idx) {
		getSqlSession().delete("story.delete", story_board_idx);
	}
	
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return getSqlSession().selectList("story.readCom", story_board_idx);
	}
	
	public void insertCom(StoryCommentDto dto) {
		getSqlSession().insert("story.insertCom", dto);
	}
}
