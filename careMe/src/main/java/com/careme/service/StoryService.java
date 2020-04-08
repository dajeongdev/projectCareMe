package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

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
	
	public int articleInsert() {
		return dao.articleInsert();
	}
	
}
