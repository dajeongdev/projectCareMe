package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;

public interface StoryService {
	public List<StoryBoardDto> list();
	
	public void insert(StoryBoardDto dto);

	public StoryBoardDto read(int story_board_idx);
	
	public List<StoryCommentDto> readCom(int story_board_idx);
	
	public void counting(int story_board_idx);
	
	public void update(StoryBoardDto dto);
	
	public void delete(int story_board_idx);
	
}
