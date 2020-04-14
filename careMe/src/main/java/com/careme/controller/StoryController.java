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
import com.careme.model.dto.StoryCommentDto;
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
		List<StoryBoardDto> hlist = service.hitList();
		mav.addObject("list", list);
		mav.addObject("hlist", hlist);
		mav.setViewName("/story/storyMain");
		return mav;
	}
	
	// 상세보기
	@RequestMapping(value = "/view/story/storyDetail", method = RequestMethod.GET)
	public ModelAndView articleDetail(int story_board_idx) {
		ModelAndView mav = new ModelAndView();
		service.counting(story_board_idx);
		StoryBoardDto list = service.read(story_board_idx);
		List<StoryCommentDto> comList = service.readCom(story_board_idx);
		int comCount = comList.size();
		int viewCount = service.counting(story_board_idx);
		mav.addObject("list", list);
		mav.addObject("comList", comList);
		mav.addObject("comCount", comCount);
		mav.addObject("viewCount", viewCount);
		mav.setViewName("story/storyMain");
		return mav;
	}
	
	// 글작성
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.GET)
	public String insertForm() {
		return "/story/storyForm";
	}
	
	@RequestMapping(value = "/view/story/storyFormAdd", method = RequestMethod.POST) 
	public ModelAndView articleInsert(StoryBoardDto dto, MultipartHttpServletRequest request) { 
		ModelAndView mav = new ModelAndView("redirect:/view/story/storyDetail");
		mav.addObject("insert", service.insert(dto));
		mav.addObject("file", file.upload(request, "/img/story/"));
		return mav;
	}
	
	// 글수정
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.GET)
	public ModelAndView updateForm(int story_board_idx) {
		ModelAndView mav = new ModelAndView("/story/storyUpdate");
		mav.addObject("story_board_idx", story_board_idx);
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.POST)
	public String articleUpdate(StoryBoardDto dto) throws Exception {
		int result = service.update(dto);
		if(result > 0) {
			return "redirect:/view/story/storyDetail";
		} else {
			return "redirect:/view/story/storyMain";
		}
	}
	
	// 글삭제
	@RequestMapping(value = "/story/delete")
	public String articleDelete(int story_board_idx) throws Exception {
		int result = service.delete(story_board_idx);
		if(result > 0) {
			return "redirect:/story/storyMain";
		} else {
			System.out.println("error");
			return "redirect:/story/storyMain";
		}
	}
	
}
