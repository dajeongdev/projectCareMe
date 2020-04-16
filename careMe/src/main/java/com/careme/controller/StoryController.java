package com.careme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.StoryCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.service.FileUploadService;
import com.careme.service.StoryService;

@Controller
public class StoryController {
	@Autowired
	StoryService service;

	public void setService(StoryService service) {
		this.service = service;
	}
	
	@Autowired
	FileUploadService file;

	public void setFile(FileUploadService file) {
		this.file = file;
	}

	// 글목록
	@RequestMapping(value = "/view/story/storyMain", method = RequestMethod.GET)
	public ModelAndView listing() {
		ModelAndView mav = new ModelAndView();
		List<StoryBoardDto> list = service.list();
		List<StoryFileDto> fList = service.fileList();
		List<StoryBoardDto> hlist = service.hitList();
		mav.addObject("list", list);
		mav.addObject("fList", fList);
		mav.addObject("hlist", hlist);
		mav.setViewName("/story/storyMain");
		System.out.println(hlist);
		return mav;
	}
	
	// 상세보기
	@RequestMapping(value = "/view/story/storyDetail", method = RequestMethod.GET)
	public ModelAndView articleDetail(int story_board_idx) {
		ModelAndView mav = new ModelAndView();
		StoryBoardDto dto = service.read(story_board_idx);
		StoryFileDto fileDto = service.readFile(story_board_idx);
		List<StoryCommentDto> comList = service.readCom(story_board_idx);
		int comCount = comList.size();
		service.counting(story_board_idx);
		mav.addObject("dto", dto);
		mav.addObject("fileDto", fileDto);
		mav.addObject("comList", comList);
		mav.addObject("comCount", comCount);
		mav.setViewName("/story/storyDetail");
		return mav;
	}
	
	// 글작성
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.GET) 
	public ModelAndView articleInsert() { 
		ModelAndView mav = new ModelAndView("/story/storyForm");
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.POST)
	public String insertForm(StoryFileDto dto, MultipartHttpServletRequest request) {
		int no = service.insert(request);
		dto.setStory_board_idx(no);
		service.insertFile(dto, request);
		return "/story/storyDetail";
	}
	
	// 글수정
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.GET)
	public ModelAndView updateForm(int story_board_idx) {
		ModelAndView mav = new ModelAndView("/story/storyEdit");
		mav.addObject("story_board_idx", story_board_idx);
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.POST)
	public ModelAndView articleUpdate(StoryBoardDto dto, MultipartHttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		dto.getStory_board_idx();
		mav.addObject("update", service.update(dto));
		mav.setViewName("/story/storyDetail");
		return mav;
	}
	
	// 글삭제
	@RequestMapping(value = "/story/delete")
	public String articleDelete(int story_board_idx) throws Exception {
		service.delete(story_board_idx);
		return "redirect:/story/storyMain";
	}
	
	// 댓글 작성
	@RequestMapping(value = "/view/story/insertCom")
	public String insertCom(StoryCommentDto comDto) {
		int result = service.insertCom(comDto);
		int i = comDto.getStory_board_idx();
		if(result > 0) {
			return "redirect:/view/story/storyDetail?story_board_idx=" + i;
		} else {
			System.out.println("error");
			return "redirect:/view/story/storyDetail?story_board_idx=" + i;
		}
	}
	
	// 댓글 수정
	@RequestMapping(value = "/view/story/updateCom", method = RequestMethod.POST)
	public String updateCom(StoryCommentDto comDto) {
		int result = service.updateCom(comDto);
		int i = comDto.getStory_board_idx();
		if(result > 0) {
			return "redirect:/view/story/storyDetail?story_board_idx=" + i;
		} else {
			System.out.println("error");
			return "redirect:/view/story/storyDetail?story_board_idx=" + i;
		}
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/view/story/deleteCom")
	public String deleteCom(int story_comment_idx) {
		int result = service.deleteCom(story_comment_idx);
		if(result > 0) {
			return "redirect:/history.go(-1)";
		} else {
			System.out.println("error");
			return "redirect:/history.go(-1)";
		}
	}
	
}
