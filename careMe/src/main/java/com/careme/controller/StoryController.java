package com.careme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.StoryCommand;
import com.careme.model.dto.StoryBoardDto;
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
	public ModelAndView listing(Model model) {
		ModelAndView mav = new ModelAndView();
		List<StoryCommand> list = service.list();
		mav.addObject("list", list);
		mav.setViewName("/story/storyMain");
		return mav;
	}
	
	// 글작성
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.POST) 
	public String insertView(MultipartHttpServletRequest request) { 
		service.insert(request);
		return "/story/storyForm";
	}
		
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.GET)
	public ModelAndView articleInsert(MultipartHttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/story/storyForm");
		mav.addObject("file", )
		mav.addObject("insert", service.list());
		return mav;
	}
	
	// 상세보기
	@RequestMapping(value = "/view/story/storyDetail", method = RequestMethod.POST)
	public ModelAndView articleDetail(StoryCommand dto) {
		ModelAndView mav = new ModelAndView("/story/storyDetail");
		mav.addObject("read", service.read(dto.getStory_board_idx()));
		return mav;
	}

	/*
	// 댓글 보기
	@RequestMapping(value = "/insertCom")
	public ModelAndView insertCom(int story_board_idx) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<StoryCommentDto> list = service.readCom(story_board_idx);
		mav.addObject("story_board_idx", list);
		mav.setViewName("redirect:/story/storyDetail");
		return mav;
	}
	
	*/
	// 글수정
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.POST)
	public String updateForm(MultipartHttpServletRequest request) {
		service.update(request);
		return "redirect:/story/storyDetail";
	}
	
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.GET)
	public ModelAndView articleUpdate(MultipartHttpServletRequest request, int i) throws Exception {
		ModelAndView mav = new ModelAndView("/story/storyUpdate");
		
		StoryCommand com = service.read(i);
		request.getSession().setAttribute("story_board_idx", i);
		
		mav.addObject("list", service.list());
		mav.addObject("update", com);
		return mav;
	}
	
	// 글삭제
	@RequestMapping(value = "/story/delete")
	public String articleDelete(HttpServletRequest request) throws Exception {
		service.delete(request);
		return "redirect:/story/storyMain";
	}
	
}
