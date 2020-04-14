package com.careme.service;

import java.time.LocalDateTime;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.StoryDao;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;

@Service("StoryService")
public class StoryServiceImpl implements StoryService {
	@Autowired
	StoryDao dao;
	
	public void setDao(StoryDao dao) {
		this.dao = dao;
	}
	
	StoryBoardDto dto;

	public void setDto(StoryBoardDto dto) {
		this.dto = dto;
	}

	@Override
	public List<StoryBoardDto> list() {
		return dao.listing();
	}

	@Override
	public StoryBoardDto read(int story_board_idx) {
		return dao.read(story_board_idx);
	}

	@Override
	public int counting(int story_board_idx) {
		return dao.counting(story_board_idx);
	}

	@Override
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return dao.readCom(story_board_idx);
	}

	@Override
	public int insert(StoryBoardDto dto) {
		dto.setReg_date(LocalDateTime.now());
		return dao.insert(dto);
	}

	@Override
	public int insertFile(StoryBoardDto dto) {
		return dao.insertFile(dto);
	}

	@Override
	public int insertCom(StoryCommentDto comDto) {
		comDto.setReg_date(LocalDateTime.now());
		return dao.insertCom(comDto);
	}

	@Override
	public int update(StoryBoardDto dto) {
		dto.setReg_date(LocalDateTime.now());
		return dao.update(dto);
	}

	@Override
	public int updateCom(StoryCommentDto comDto) {
		comDto.setReg_date(LocalDateTime.now());
		return dao.updateCom(comDto);
	}

	@Override
	public int delete(int story_board_idx) {
		return dao.delete(story_board_idx);
	}

	@Override
	public int deleteCom(int story_comment_idx) {
		return dao.deleteCom(story_comment_idx);
	}

	@Override
	public List<StoryBoardDto> hitList() {
		return dao.hitList();
	}

}
