package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.command.StoryCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;

public interface StoryService {
	public List<StoryCommand> list();
	
	public int insert(MultipartHttpServletRequest request);

	public StoryCommand read(int story_board_idx);
	
	public int counting(int story_board_idx);
	
	public int update(MultipartHttpServletRequest request);
	
	public int delete(HttpServletRequest request);
	
}
