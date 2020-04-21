package com.careme.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.command.StoryCommand;
import com.careme.model.command.StoryContentCommand;
import com.careme.model.command.StoryTagCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;

public interface StoryService {
	// 글목록
	public List<StoryBoardDto> list(StoryCommand com);
	public List<StoryBoardDto> listPaging(Map<String, Integer> map);
	public int getTotal();
	public List<StoryFileDto> fileList();
	public StoryCommand searchList(int searchType, String keyword);
	public List<StoryBoardDto> searching(StoryCommand com);
	public StoryContentCommand getContent(int story_board_idx);
	// 인기글
	public List<StoryBoardDto> hitList();
		
	// 글 상세보기
	public StoryBoardDto read(int story_board_idx);
	public List<StoryFileDto> readFile(int story_board_idx);
	public List<StoryCommentDto> readCom(int story_board_idx);
	// 조회수
	public int counting(int story_board_idx);
	// 좋아요
	public int heart(int story_board_idx);
	public int comHeart(int story_comment_idx);
	
	// 작성
	public int insert(MultipartHttpServletRequest request);
	public void insertFile(StoryFileDto fileDto, MultipartHttpServletRequest request);
	public int insertCom(StoryCommentDto comDto);
	
	
	// 수정
	public int update(StoryBoardDto dto);
	public void updateFile(StoryFileDto fileDto, Integer[] deletedFiles, MultipartHttpServletRequest request);
	public int updateCom(StoryCommentDto comDto);
	
	
	// 삭제
	public int delete(HttpServletRequest request);
	public int deleteCom(int story_comment_idx);
	
	
	// 태그
	public List<StoryTagCommand> readTag(StoryTagCommand tagCom);
	public int insertTag(StoryTagCommand tagCom);
	public int updateTag(StoryTagCommand tagCom);

}
