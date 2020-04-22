package com.careme.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.BoardUseTagDto;
import com.careme.model.dto.TagDto;

public class HashTagDao  extends SqlSessionDaoSupport {
	public TagDto selectHashTag(String tag_name) {
		return getSqlSession().selectOne("hashTag.selectHashTag", tag_name);
	}
	
	public int insertHashTag(TagDto tagDto) {
		return getSqlSession().insert("hashTag.insertHashTag", tagDto);
	}

	
	public int insertTagType(BoardUseTagDto bdto) {
		return getSqlSession().insert("hashTag.insertTagType", bdto);
	}
	
}
