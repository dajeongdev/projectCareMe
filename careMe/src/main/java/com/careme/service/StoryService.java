package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.StoryDao;
import com.careme.model.dto.StoryBoardDto;

@Service
public class StoryService {
	@Autowired
	StoryDao dao;
	
	public void setDao(StoryDao dao) {
		this.dao = dao;
	}

	public List<StoryBoardDto> selectAll() {
		return dao.selectAll();
	}
}
