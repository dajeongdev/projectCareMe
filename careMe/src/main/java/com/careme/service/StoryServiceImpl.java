package com.careme.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.StoryDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.StoryCommand;
import com.careme.model.command.StoryContentCommand;
import com.careme.model.command.StoryTagCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;
import com.careme.service.FileUploadService;

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
	
	@Autowired
	FileUploadService service;
	
	public void setService(FileUploadService service) {
		this.service = service;
	}
	
	PageNumberCommand pageCom;

	public void setPageCom(PageNumberCommand pageCom) {
		this.pageCom = pageCom;
	}
	
	PageNumberService pageService;
	
	public void setPageService(PageNumberService pageService) {
		this.pageService = pageService;
	}

	
	@Override
	public List<StoryBoardDto> list(StoryCommand com) {
		return dao.listing(com);
	}
	public List<StoryFileDto> fileList() {
		return dao.fileListing();
	}
	
	@Override
	public List<StoryBoardDto> listPaging(Map<String, Integer> map) {
		return dao.totalListing(map);
	}

	@Override
	public int getTotal() {
		return dao.getTotal();
	}
	
	@Override
	public StoryBoardDto read(int story_board_idx) {
		return dao.read(story_board_idx);
	}
	@Override
	public List<StoryFileDto> readFile(int story_board_idx) {
		return dao.readFile(story_board_idx);
	}
	@Override
	public List<StoryCommentDto> readCom(int story_board_idx) {
		return dao.readCom(story_board_idx);
	}

	@Override
	public int counting(int story_board_idx) {
		return dao.counting(story_board_idx);
	}
	
	@Override
	public int heart(int story_board_idx) {
		return dao.heart(story_board_idx);
	}

	@Override
	public int comHeart(int story_comment_idx) {
		return dao.comHeart(story_comment_idx);
	}
	
	@Override
	public List<StoryBoardDto> hitList() {
		return dao.hitList();
	}
	
	@Override
	public List<StoryBoardDto> searching(StoryCommand com) {
		return dao.searching(com);
	}
	
	@Override
	public StoryCommand searchList(int searchType, String keyword) {
		StoryCommand com = new StoryCommand();
		if(searchType == 0) {
			com.setSearhType("member_id");
		} else if(searchType == 1) {
			com.setSearhType("title");
		} else if(searchType == 2) {
			com.setSearhType("content");
		}
		com.setKeyword(keyword);
		return com;
	}
	@Override
	public int insert(MultipartHttpServletRequest request) {
		dto = requesting(request);
		return dao.insert(dto);
	}

	public StoryBoardDto requesting(MultipartHttpServletRequest request) {
		dto = new StoryBoardDto();
		
		if (request.getParameter("story_board_idx") != null && request.getParameter("story_board_idx") != "") {
			dto.setStory_board_idx(Integer.parseInt(request.getParameter("story_board_idx")));
		}
		Integer story_board_idx = (Integer) request.getSession().getAttribute("story_board_idx");
		if(story_board_idx != null) {
			dto.setStory_board_idx(story_board_idx);
		}
		dto.setMember_idx(Integer.parseInt(request.getParameter("member_idx")));
		dto.setContent(request.getParameter("content"));
		dto.setTitle(request.getParameter("title"));
		dto.setTag_idx(Integer.parseInt(request.getParameter("tag_idx")));
		dto.setReg_date(LocalDateTime.now());
		return dto;
	}
	
	@Override
	public void insertFile(StoryFileDto dto, MultipartHttpServletRequest request) {
		int story_board_idx = dto.getStory_board_idx();
		if(story_board_idx > 0) fileRequesting(story_board_idx, request);
		
	}
	
	public void fileRequesting(int story_board_idx, MultipartHttpServletRequest request) {
		List<FileUploadCommand> files = service.upload(request, "/img/story/");
		
		for(FileUploadCommand file : files) {
			StoryFileDto dto = new StoryFileDto();
			dto.setStory_board_idx(story_board_idx);
			dto.setFile_name(file.getFileOriginName());
			dto.setFile_path(file.getFilePath());
			dto.setFile_size(file.getFileSize());
			
			dao.insertFile(dto);
		}
	}

	@Override
	public int insertCom(StoryCommentDto comDto) {
		comDto.setReg_date(LocalDateTime.now());
		return dao.insertCom(comDto);
	}
	
	
	@Override
	public int update(StoryBoardDto dto)  {
		dto.setReg_date(LocalDateTime.now());
		return dao.update(dto);
	}
	
	@Override
	public void updateFile(StoryFileDto fileDto, Integer[] deletedFiles, MultipartHttpServletRequest request) {
		int i = dao.updateFfile(fileDto);
		int story_board_idx = dto.getStory_board_idx();
		System.out.println("삭제파일 길이: " + deletedFiles.length);
		
		if (i == 1) {
			if (deletedFiles.length > 0) {
				System.out.println("삭제파일 길이: " + deletedFiles.length);
				Map<String, Object> deleteList = new HashMap<String, Object>();
				List<Integer> list = Arrays.asList(deletedFiles);
				deleteList.put("deleteList", list);
				
				dao.deleteFile(deleteList);
			}
			if (request.getFileMap().size() > 0) fileRequesting(story_board_idx, request);
		}
	}

	@Override
	public int updateCom(StoryCommentDto comDto) {
		comDto.setReg_date(LocalDateTime.now());
		return dao.updateCom(comDto);
	}
	
	@Override
	public int delete(HttpServletRequest request) {
		return dao.delete((int)request.getSession().getAttribute("story_board_idx")); 
	}

	@Override
	public int deleteCom(int story_comment_idx) {
		return dao.deleteCom(story_comment_idx);
	}


	@Override
	public StoryContentCommand getContent(int story_board_idx) {
		StoryContentCommand storyCom = new StoryContentCommand();
		
		StoryBoardDto dto = dao.read(story_board_idx);
		List<StoryFileDto> fileDto = dao.readFile(story_board_idx);
		storyCom.setDto(dto);
		storyCom.setFileDto(fileDto);
		
		return storyCom;
	}

}
