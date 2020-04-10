package com.careme.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.TagDto;

public class StoryDao extends SqlSessionDaoSupport {
	public List<StoryBoardDto> listing() {
		return getSqlSession().selectList("story.list");
	}
	
	public void insert(StoryBoardDto dto) {
		getSqlSession().insert("story.insert", dto);
	}
	
	public void insertTag(TagDto dto) {
		getSqlSession().insert("insert.insertTag", dto);
	}
	
	public void insertFile(Map<String, Object> map) throws Exception {
		getSqlSession().insert("story.insertFile", map);
	} // *
	
	public StoryBoardDto selectOne(int story_board_idx) {
		return getSqlSession().selectOne("story.select", story_board_idx);
	}
	
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return getSqlSession().selectList("story.readCom", story_board_idx);
	}
	
	public void update(StoryBoardDto dto) {
		getSqlSession().update("story.update", dto);
	}
	
	public void delete(int story_board_idx) {
		getSqlSession().delete("story.delete", story_board_idx);
	}
	
	public void insertCom(StoryCommentDto dto) {
		getSqlSession().insert("story.insertCom", dto);
	}
}
