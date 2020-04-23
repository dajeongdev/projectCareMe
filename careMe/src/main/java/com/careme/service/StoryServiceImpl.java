package com.careme.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.careme.model.command.TagListCommand;
import com.careme.model.dto.BoardUseTagDto;
import com.careme.model.dto.HeartDto;
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
	
	@Autowired
	HeartService heartSer;

	public void setHeartSer(HeartService heartSer) {
		this.heartSer = heartSer;
	}
	
	@Autowired
	StoryService ser;
	
	public void setSer(StoryService ser) {
		this.ser = ser;
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
	public StoryCommentDto readComIdx(int story_comment_idx) {
		return dao.readComIdx(story_comment_idx);
	}
	
	@Override
	public int counting(int story_board_idx) {
		return dao.counting(story_board_idx);
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
	public int insert(StoryBoardDto dto, MultipartHttpServletRequest request) {
		dto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		int i = dao.insert(dto);
		int d = dto.getStory_board_idx();
		if(d > 0) {
			fileRequesting(d, request);
		}
		return i;
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
		dto.setContent(request.getParameter("content"));
		dto.setTitle(request.getParameter("title"));
		dto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dto;
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
		comDto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.insertCom(comDto);
	}
	
	
	@Override
	public void update(StoryBoardDto dto, StoryFileDto fileDto, Integer[] deletedFiles, MultipartHttpServletRequest request)  {
		int i = dao.update(dto);
		int story_board_idx = dto.getStory_board_idx();
		System.out.println("삭제파일 길이: " + deletedFiles.length);
		
		if (i == 1) {
			if (deletedFiles.length > 0) {
				System.out.println("삭제파일 길이: " + deletedFiles.length);
				Map<String, Object> deleteList = new HashMap<String, Object>();
				List<Integer> list = Arrays.asList(deletedFiles);
				deleteList.put("deleteList", list);
				
				dao.updateFfile(deleteList);
			}
			if (request.getFileMap().size() > 0) fileRequesting(story_board_idx, request);
		}
	}
	
	@Override
	public void updateFile(Map<String, Integer> map) {}
	
	@Override
	public int updateCom(StoryCommentDto comDto) {
		comDto.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return dao.updateCom(comDto);
	}
	
	@Override
	public int delete(int story_board_idx) {
		return dao.delete(story_board_idx); 
	}
	@Override
	public int deleteFile(int story_board_idx) {
		return dao.deleteFile(story_board_idx);
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


	@Override
	public int addHeart(int idx) {
		return dao.addHeart(idx);
	}


	@Override
	public int subHeart(int idx) {
		return dao.subHeart(idx);
	}


	@Override
	public int addComHeart(int idx) {
		return dao.addComHeart(idx);
	}


	@Override
	public int subComHeart(int idx) {
		return dao.subComHeart(idx);
	}

	@Override
	public void hearting(HeartDto heart, int story_comment_idx) {
		String check = heart.getHeartCheck();
		if(check.equals("n")) {
			addHeart(story_comment_idx);
			heart.setHeartCheck("y");
			heartSer.updateHeartCheck(heart);
			System.out.println(check);

		}else if(check.equals("y")) {
			subHeart(story_comment_idx);
			heart.setHeartCheck("n");
			heartSer.updateHeartCheck(heart);
		}
	}

	// 태그 리스트
	@Override
	public List<TagDto> readTagList(Map<String, Integer> map) {
		return dao.readTagList(map);
	}

	@Override
	public List<StoryFileDto> readTagFileList(int story_board_idx) {
		return dao.readTagFileList(story_board_idx);
	}
	
	@Override
	public List<TagDto> tagSelect(TagListCommand tagListCom) {
		return dao.tagSelect(tagListCom);
	}
	
	@Override
	public TagListCommand tagInfo(int tag_idx) {
		TagListCommand tagListCom = new TagListCommand();
		tagListCom.setTag_idx(tag_idx);
		return tagListCom;
	}

	@Override
	public List<TagDto> readTags(int story_board_idx) {
		return dao.readTags(story_board_idx);
	}


}
