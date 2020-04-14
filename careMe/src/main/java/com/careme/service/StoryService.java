package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.command.StoryCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;

public interface StoryService {
	// 글목록
	public List<StoryBoardDto> list();
	
	// 글 상세보기
	public StoryBoardDto read(int story_board_idx);
	
	// 조회수
	public int counting(int story_board_idx);
	
	// 댓글 보기
	public List<StoryCommentDto> readCom(int story_board_idx);
	
	// 인기글
	public List<StoryBoardDto> hitList();
	
	// 작성
	public int insert(StoryBoardDto dto);
	
	public int insertFile(StoryBoardDto dto);
	
	public int insertCom(StoryCommentDto comDto);
	
	// 수정
	public int update(StoryBoardDto dto);
	
	public int updateCom(StoryCommentDto comDto);
	
	// 삭제
	public int delete(int story_board_idx);
	
	public int deleteCom(int story_comment_idx);
	
}
