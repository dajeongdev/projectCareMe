package com.careme.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.StoryDao;
import com.careme.model.command.StoryFileCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;

@Service
public class StoryService {
	@Autowired
	StoryDao dao;
	
	@Resource
	StoryFileCommand com;

	// 게시글 목록
	public List<StoryBoardDto> listing() {
		return dao.listing();
	}

	// 게시글 작성
	public void insert(StoryBoardDto dto, MultipartHttpServletRequest mpRequest, Map<String, Object> map) throws Exception {
		dao.insert(dto);
		dao.insertFile(map);
		List<Map<String, Object>> list = com.fileInfo(dto, mpRequest);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			dao.insertFile(list.get(i));
		}
	}
	
	// 게시글 상세보기
	public StoryBoardDto select(int story_board_idx) {
		return dao.selectOne(story_board_idx);
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
