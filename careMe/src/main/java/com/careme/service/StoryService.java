package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.StoryDao;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;

@Service
public class StoryService {
	@Autowired
	StoryDao dao;
	
	public void setDao(StoryDao dao) {
		this.dao = dao;
	}

	// 게시글 목록
	public List<StoryBoardDto> listing() {
		return dao.listing();
	}

	// 게시글 작성
	public void insert(StoryBoardDto dto, MultipartHttpServletRequest mpRequest) {
		dao.insert(dto);
	}
	
	// 게시글 상세보기
	public StoryBoardDto select(int story_board_idx) {
		return dao.select(story_board_idx);
	}
	
	// 게시글 수정
	public void update(StoryBoardDto dto) {
		dao.update(dto);
	}
	
	// 게시글 삭제
	public void delete(int story_board_idx) {
		dao.delete(story_board_idx);
	}
	
	// 댓글 조회
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return dao.readCom(story_board_idx);
	}
	
	// 댓글 작성
	public void insertCom(StoryCommentDto dto) {
		dao.insertCom(dto);
	}
}
