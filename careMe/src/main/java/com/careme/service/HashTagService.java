package com.careme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.HashTagDao;
import com.careme.model.dto.BoardUseTagDto;
import com.careme.model.dto.TagDto;

@Service
public class HashTagService {
	@Autowired
	HashTagDao hashTagDao;
	
	public void setHashTagDao(HashTagDao hashTagDao) {
		this.hashTagDao = hashTagDao;
	}
	
	public TagDto checkTag(String tag_name, int member_idx) {
		TagDto tagDto = hashTagDao.selectHashTag(tag_name);
		
		if (tagDto == null) {
			tagDto = new TagDto();
			tagDto.setTag_name(tag_name);
			tagDto.setMember_idx(member_idx);
			
			hashTagDao.insertHashTag(tagDto);
		}
		return tagDto;
	}
	
	public TagDto insertTag(String tag_name, int member_idx) {
		TagDto tdto = new TagDto();
		tdto.setTag_name(tag_name);
		tdto.setMember_idx(member_idx);
		hashTagDao.insertHashTag(tdto);
		return tdto;

	}
	
	public int insertTagType(BoardUseTagDto bdto) {
		int result = hashTagDao.insertTagType(bdto);
		return result;
		
	}
	
	public int insertUseTag(String board_type, int board_idx, int[] tag_idx) {
		BoardUseTagDto bdto = new BoardUseTagDto();
		bdto.setBoard_idx(board_idx);
		bdto.setBoard_type(board_type);
		int result=0;
		int i;
		for(i=0; i<tag_idx.length; i++) {
			bdto.setTag_idx(tag_idx[i]);
			System.out.println("tagidx[]="+tag_idx[i]);
			result+=insertTagType(bdto);
		}
		return result;
	}

}
