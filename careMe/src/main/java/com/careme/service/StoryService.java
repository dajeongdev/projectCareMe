package com.careme.service;

import java.util.List;

import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;

public interface StoryService {
	public List<StoryBoardDto> list();
	
	public int insert(StoryBoardDto dto);
	
	public int insertTag(TagDto tagDto);
	
	public int insertFile(StoryFileDto fileDto) throws Exception;
	
	public StoryBoardDto selectOne(int story_board_idx);
	
	public List<StoryCommentDto> readCom(int story_board_idx);
	
	
	
}
