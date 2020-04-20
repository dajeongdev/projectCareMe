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
	
	TagDto tagDto;
	
	public void setTagDto(TagDto tagDto) {
		this.tagDto = tagDto;
	}
	
	
	@Override
	public List<StoryBoardDto> list() {
		return dao.listing();
	}
	public List<StoryFileDto> fileList() {
		return dao.fileListing();
	}
	
	@Override
	public List<StoryBoardDto> totalListing(Map<String, Integer> map) {
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
	public StoryFileDto readFile(int story_board_idx) {
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
			com.setSearchType("member_id");
		} else if(searchType == 1) {
			com.setSearchType("title");
		} else if(searchType == 2) {
			com.setSearchType("content");
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
		
		int member_idx = 1;
		if (request.getParameter("story_board_idx") != null && request.getParameter("story_board_idx") != "") {
			dto.setStory_board_idx(Integer.parseInt(request.getParameter("story_board_idx")));
		}
		Integer story_board_idx = (Integer) request.getSession().getAttribute("story_board_idx");
		if(story_board_idx != null) {
			dto.setStory_board_idx(story_board_idx);
		}
		dto.setMember_idx(member_idx);
		dto.setContent(request.getParameter("content"));
		dto.setTitle(request.getParameter("title"));
		int tag_idx = 1;
		dto.setTag_idx(tag_idx);
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
	public int update(MultipartHttpServletRequest request)  {
		dto	= requesting(request);
		int i = dao.update(dto);
		request.getSession().removeAttribute("story_board_idx");
		return i;
	}

	@Override
	public void updateFile(StoryFileDto fileDto, Integer[] fileDelete, MultipartHttpServletRequest request) {
		int i = dao.updateFfile(fileDto);
		int story_board_idx = dto.getStory_board_idx();
		System.out.println("삭제파일 길이: " + fileDelete.length);
		if (i == 1) {
			if (fileDelete.length > 0) {
				System.out.println("삭제파일 길이: " + fileDelete.length);
				Map<String, Object> deleteList = new HashMap<String, Object>();
				List<Integer> list = Arrays.asList(fileDelete);
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
	public List<TagDto> readTag() {
		return dao.readTag();
	}
	
	@Override
	public int insertTag(HttpServletRequest request) {
		List<TagDto> list = new ArrayList<>();
		tagDto = new TagDto();
		for(TagDto tagDto : list) {
			int member_idx = 1;
			if(request.getParameter("tag_idx") != null && request.getParameter("tag_idx") != "") {
				tagDto.setTag_idx((int)request.getSession().getAttribute("tag_idx"));
			}
			tagDto.setMember_idx(member_idx);
			tagDto.setTag_name(request.getParameter("tag_name"));
		}
		return dao.insertTag(tagDto);
	}
	
	@Override
	public int insertTagType(int tag_idx, HttpServletRequest request) {
		List<TagDto> list = new ArrayList<TagDto>();
		
		for(TagDto tagDto : list) {
			tagDto = new TagDto();
			int board_idx = dto.getStory_board_idx();
			if(request.getParameter("tag_idx") != null && request.getParameter("tag_idx") != "") {
				tagDto.setTag_idx((int)request.getSession().getAttribute("tag_idx"));
			}
			tagDto.setBoard_idx(board_idx);
		}
		return dao.insertTagType(tagDto);
	}
	
	@Override
	public int updateTag(TagDto tagDto) {
		return dao.updateTag(tagDto);
	}
	@Override
	public int deleteTag(int tag_idx) {
		return dao.deleteTag(tag_idx);
	}
	
	
	@Override
	public List<StoryFileDto> mainImageList() {
		return dao.mainImageList();
	}
	
	public void tag(StoryTagCommand com, HttpServletRequest request) {
		String[] tags = com.getTag_name();
		for(int  i = 0; i < tags.length; i++) {
			TagDto tagDto = new TagDto();
			tagDto.setTag_name(tags[i]);
			tagDto.setBoard_idx(board_idx);
			
		}
	} 
}
