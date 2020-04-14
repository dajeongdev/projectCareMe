package com.careme.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.StoryCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;

public class StoryDao extends SqlSessionDaoSupport {
	
	public List<StoryCommand> listing() {
		return getSqlSession().selectList("story.list");
	}
	
	public int insert(StoryCommand com) {
		return getSqlSession().insert("story.insert", com);
	}
	
	public int insertTag(TagDto tagDto) {
		return getSqlSession().insert("insert.insertTag", tagDto);
	}
	
	public int insertFile(Map<String, Object> map) throws Exception {
		return getSqlSession().insert("story.insertFile", map);
	}
	
	public int insertCom(StoryCommentDto comDto) {
		return getSqlSession().insert("story.insertCom", comDto);
	}
	
	public StoryCommand read(int story_board_idx) {
		return getSqlSession().selectOne("story.read", story_board_idx);
	}
	
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return getSqlSession().selectList("story.readCom", story_board_idx);
	}
	
	public int counting(int story_board_idx) {
		return getSqlSession().update("story.viewCount", story_board_idx);
	}
	
	public int update(StoryCommand com) {
		return getSqlSession().update("story.update", com);
	}
	
	public int updateFfile(StoryFileDto fileDto) {
		return getSqlSession().update("story.updateFile", fileDto);
	} 
	
	public int updateTag(TagDto tagDto) {
		return getSqlSession().update("story.updateTag", tagDto);
	}
	
	public int delete(int story_board_idx) {
		return getSqlSession().update("story.delete", story_board_idx);
	}
	
	public int deleteTag(int tag_idx) {
		return getSqlSession().update("story.deleteTag", tag_idx);
	}
	
	public int deleteCom(int story_comment_idx) {
		return getSqlSession().update("story.deleteCom", story_comment_idx);
	}
	

}
