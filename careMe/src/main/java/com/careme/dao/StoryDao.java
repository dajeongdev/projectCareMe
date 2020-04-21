package com.careme.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.careme.model.command.StoryCommand;
import com.careme.model.command.StoryTagCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;

public class StoryDao extends SqlSessionDaoSupport {
	// 글목록
	public List<StoryBoardDto> listing(StoryCommand com) {
		return getSqlSession().selectList("story.list", com);
	}
	
	public List<StoryBoardDto> totalListing(Map<String, Integer> map) {
		return getSqlSession().selectList("story.list", map);
	}
	
	public int getTotal() {
		return getSqlSession().selectOne("story.selectTotal");
	}
	
	public List<StoryFileDto> fileListing() {
		return getSqlSession().selectList("story.fileList");
	}
	public List<StoryBoardDto> searching(StoryCommand com){
		return getSqlSession().selectList("story.search");
	}
	
	// 글 상세보기
	public StoryBoardDto read(int story_board_idx) {
		return getSqlSession().selectOne("story.read", story_board_idx);
	}	
	public List<StoryFileDto> readFile(int story_board_idx) {
		return getSqlSession().selectList("story.readFile", story_board_idx);
	}
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return getSqlSession().selectList("story.readCom", story_board_idx);
	}
	// 조회수
	public int counting(int story_board_idx) {
		return getSqlSession().update("story.viewCount", story_board_idx);
	}
	// 좋아요
	public int heart(int story_board_idx) {
		return getSqlSession().update("story.heart", story_board_idx);
	}
	public int comHeart(int story_comment_idx) {
		return getSqlSession().update("story.comHeart", story_comment_idx);
	}
	
	// 인기글
	public List<StoryBoardDto> hitList() {
		return getSqlSession().selectList("story.hit");
	}
	
	// 작성
	public int insert(StoryBoardDto dto) {
		getSqlSession().insert("story.insert", dto);
		return dto.getStory_board_idx();
	}
	public int insertFile(StoryFileDto fileDto) {
		return getSqlSession().insert("story.insertFile", fileDto);
	}
	public int insertCom(StoryCommentDto comDto) {
		return getSqlSession().insert("story.insertCom", comDto);
	}
	
	// 수정
	public int update(StoryBoardDto dto) {
		return getSqlSession().update("story.update", dto);
	}
	public int updateFfile(StoryFileDto fileDto) {
		return getSqlSession().update("story.updateFile", fileDto);
	} 
	public int updateCom(StoryCommentDto comDto) {
		return getSqlSession().update("story.updateCom", comDto);
	}
	
	// 삭제(del_yn 'y')
	public int delete(int story_board_idx) {
		return getSqlSession().delete("story.delete", story_board_idx);
	}
	public int deleteFile(Map<String, Object> list) {
		return getSqlSession().delete("story.deleteFile", list);
	}
	public int deleteCom(int story_comment_idx) {
		return getSqlSession().delete("story.deleteCom", story_comment_idx);
	}


	// 태그
	public List<StoryTagCommand> readTag(StoryTagCommand tagCom) {
		return getSqlSession().selectList("story.readTag");
	}
	public int insertTag(StoryTagCommand tagCom) {
		return getSqlSession().insert("story.insertTag", tagCom);
	}
	public int updateTag(StoryTagCommand tagCom) {
		return getSqlSession().update("story.updateTag", tagCom);
	}

}
