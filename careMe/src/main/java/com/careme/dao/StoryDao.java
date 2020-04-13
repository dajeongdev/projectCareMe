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
	
	public int insert(StoryBoardDto dto) {
		return getSqlSession().insert("story.insert", dto);
	}
	
	public int insertTag(TagDto tagDto) {
		return getSqlSession().insert("insert.insertTag", tagDto);
	}
	
	public int insertFile(StoryFileDto fileDto) throws Exception {
		return getSqlSession().insert("story.insertFile", fileDto);
	}
	
	public StoryBoardDto selectOne(int story_board_idx) {
		return getSqlSession().selectOne("story.select", story_board_idx);
	}
	
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return getSqlSession().selectList("story.readCom", story_board_idx);
	}
	
	public int update(StoryBoardDto dto) {
		return getSqlSession().update("story.update", dto);
	}
	
	public int updateTag(TagDto tagDto) {
		return getSqlSession().update("story.updateTag", tagDto);
	}
	
	public int delete(int story_board_idx) {
		return getSqlSession().delete("story.delete", story_board_idx);
	}
	
	public int deleteTag(String tag_name) {
		return getSqlSession().delete("story.deleteTag", tag_name);
	}
	
	public void insertCom(StoryCommentDto dto) {
		getSqlSession().insert("story.insertCom", dto);
	}
}
