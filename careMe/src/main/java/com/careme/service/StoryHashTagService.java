package com.careme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.careme.dao.StoryTagDao;
import com.careme.model.dto.BoardUseTagDto;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.TagDto;

@Service
public class StoryHashTagService {
	@Autowired
	StoryTagDao tagDao;
	
	public void setTagDao(StoryTagDao tagDao) {
		this.tagDao = tagDao;
	}

	public TagDto checkTag(String tag_name, int member_idx) {
		TagDto tagDto = tagDao.selectHashTag(tag_name);
		
		if (tagDto == null) {
			tagDto = new TagDto();
			tagDto.setTag_name(tag_name);
			tagDto.setMember_idx(member_idx);
			
			tagDao.insertHashTag(tagDto);
		}
		return tagDto;
	}
	
	public int insertTag(String tag_name, int member_idx) {
		TagDto tagDto = new TagDto();
		tagDto.setTag_name(tag_name);
		tagDto.setMember_idx(member_idx);
		return tagDao.insertHashTag(tagDto);
	}
	
	public BoardUseTagDto insertTagType(StoryBoardDto dto, TagDto tagDto, String board_type) {
		BoardUseTagDto useTag = new BoardUseTagDto();
		int story_board_idx = dto.getStory_board_idx();
		int tag_idx = tagDto.getTag_idx();
		useTag.setBoard_idx(story_board_idx);
		useTag.setTag_idx(tag_idx);
		useTag.setBoard_type(board_type);
		tagDao.insertTagType(useTag);
		
		return useTag;
	}
}
