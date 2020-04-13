package com.careme.service;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.StoryDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;

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
	
	StoryFileDto fileDto;

	public void setFileDto(StoryFileDto fileDto) {
		this.fileDto = fileDto;
	}
	
	@Override
	public List<StoryBoardDto> list() {
		return dao.listing();
	}

	@Override
	public void insert(StoryBoardDto dto) {
		dao.insert(dto);
	}

	@Override
	public StoryBoardDto read(int story_board_idx) {
		return dao.read(story_board_idx);
	}

	@Override
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return dao.readCom(story_board_idx);
	}
	
	@Override
	public void counting(int story_board_idx) {
		dao.counting(story_board_idx);
	}


	@Override
	public void update(StoryBoardDto dto) {
		dao.update(dto);
	}

	@Override
	public void delete(int story_board_idx) {
		dao.delete(story_board_idx);
	}
	
	 /*private StoryBoardDto requesting(MultipartHttpServletRequest request) { 
		 fileDto = new StoryFileDto();
	  
		 int member_idx = 1;
		 
		 if(request.getParameter("p") != null && request.getParameter("p") != "") {
		 dto.setStory_board_idx(Integer.parseInt(request.getParameter("p"))); }
		 
		 Integer story_board_idx =
		 (Integer)request.getSession().getAttribute("story_board_idx");
		 if(story_board_idx != null) { dto.setStory_board_idx(story_board_idx); }
		  
		 dto.setMember_idx(member_idx); dto.setTitle(request.getParameter("title"));
		 dto.setContent(request.getParameter("content"));
		 dto.setHeart(Integer.parseInt(request.getParameter("heart")));
		 dto.setView_count(Integer.parseInt(request.getParameter("view_count")));
		 dto.setTag_idx(Integer.parseInt(request.getParameter("tag_idx")));
		 if(!request.getFile(request.getFileNames().next()).isEmpty()) {
		 List<FileUploadCommand> files = service.upload(request,"/img/story/upload/"); 
		 FileUploadCommand file = files.get(0);
		 fileDto.setFile_path(file.getFilePath()); 
		 } 
		 return dto; 
	}*/



}
