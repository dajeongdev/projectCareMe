package com.careme.service;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.StoryDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.StoryCommand;
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
	
	StoryCommand com;
	
	public void setCom(StoryCommand com) {
		this.com = com;
	}
	
	@Autowired
	FileUploadService fileUpload;

	public void setFileUpload(FileUploadService fileUpload) {
		this.fileUpload = fileUpload;
	}

	@Override
	public List<StoryCommand> list() {
		return dao.listing();
	}

	@Override
	public int insert(MultipartHttpServletRequest request) {
		com = requesting(request);
		return dao.insert(com);
	}

	@Override
	public StoryCommand read(int story_board_idx) {
		return dao.read(story_board_idx);
	}

	@Override
	public int counting(int story_board_idx) {
		return dao.counting(story_board_idx);
	}

	@Override
	public int update(MultipartHttpServletRequest request) {
		int i = 0;
		com = requesting(request);
		i = dao.update(com);
		request.getSession().removeAttribute("story_board_idx");
		return i;
	}

	@Override
	public int delete(HttpServletRequest request) {
		return dao.delete((int) request.getSession().getAttribute("story_board_idx"));
	}

	
	 public StoryCommand requesting(MultipartHttpServletRequest request) { 
		 com = new StoryCommand();
	  
		 int member_idx = 1;
		 
		 if(request.getParameter("p") != null && request.getParameter("p") != "") 
			 com.setStory_board_idx(Integer.parseInt(request.getParameter("p"))); 
		 
		 Integer story_board_idx = (Integer)request.getSession().getAttribute("story_board_idx");
		 if(story_board_idx != null) { 
			 com.setStory_board_idx(story_board_idx); 
		 }
		 
		 com.setMember_idx(member_idx); 
		 com.setTitle(request.getParameter("title"));
		 com.setContent(request.getParameter("content"));
		 com.setHeart(Integer.parseInt(request.getParameter("heart")));
		 com.setView_count(Integer.parseInt(request.getParameter("view_count")));
		 com.setTag_idx(Integer.parseInt(request.getParameter("tag_idx")));
		 com.setStory_file_idx(Integer.parseInt(request.getParameter("story_file_idx")));
		 com.setMember_id(request.getParameter("member_id"));
		 com.setHeart(Integer.parseInt(request.getParameter("heart")));
		 com.setView_count(Integer.parseInt(request.getParameter("view_count")));
		 
		 if(!request.getFile(request.getFileNames().next()).isEmpty()) {
			 List<FileUploadCommand> files = fileUpload.upload(request,"/img/story/upload/"); 
			 FileUploadCommand file = files.get(0);
			 com.setOrigin_file_name(file.getFileOriginName());
			 com.setFile_size(file.getFileSize());
			 com.setFile_path(file.getFilePath());
		 } 
		 return com; 
	}



}
