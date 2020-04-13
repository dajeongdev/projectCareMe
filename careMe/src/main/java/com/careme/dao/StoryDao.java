package com.careme.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;

public class StoryDao extends SqlSessionDaoSupport {
	
	public List<StoryBoardDto> listing() {
		return getSqlSession().selectList("story.list");
	}
	
	public void insert(StoryBoardDto dto) {
		getSqlSession().insert("story.insert", dto);
	}
	
	public void insertTag(TagDto tagDto) {
		getSqlSession().insert("insert.insertTag", tagDto);
	}
	
	public void insertFile(Map<String, Object> map) throws Exception {
		getSqlSession().insert("story.insertFile", map);
	}
	
	public void insertCom(StoryCommentDto comDto) {
		getSqlSession().insert("story.insertCom", comDto);
	}
	
	public StoryBoardDto read(int story_board_idx) {
		return getSqlSession().selectOne("story.read", story_board_idx);
	}
	
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return getSqlSession().selectList("story.readCom", story_board_idx);
	}
	
	public void counting(int story_board_idx) {
		getSqlSession().update("story.viewCount", story_board_idx);
	}
	
	public void update(StoryBoardDto dto) {
		getSqlSession().update("story.update", dto);
	}
	
	public void updateFfile(StoryFileDto fileDto) {
		getSqlSession().update("story.updateFile", fileDto);
	} 
	
	public void updateTag(TagDto tagDto) {
		getSqlSession().update("story.updateTag", tagDto);
	}
	
	public void delete(int story_board_idx) {
		getSqlSession().update("story.delete", story_board_idx);
	}
	
	public void deleteTag(int tag_idx) {
		getSqlSession().update("story.deleteTag", tag_idx);
	}
	

}
