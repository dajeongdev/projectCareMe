package com.careme.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.BoardUseTagDto;
import com.careme.model.dto.TagDto;

public class StoryTagDao extends SqlSessionDaoSupport {
	public TagDto selectHashTag(String tag_name) {
		return getSqlSession().selectOne("hashTag.selectHashTag", tag_name);
	}
	
	public int insertHashTag(TagDto tagDto) {
		getSqlSession().insert("hashTag.insertHashTag", tagDto);
		return tagDto.getTag_idx();
	}

	public int insertTagType(BoardUseTagDto useTag) {
		return getSqlSession().insert("hashTag.insertTagType", useTag);
	}
}
