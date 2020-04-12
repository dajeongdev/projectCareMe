package com.careme.controller;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;
import javax.annotation.RegEx;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.StoryFileCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.service.StoryService;
import com.google.gson.Gson;
import com.lowagie.text.pdf.codec.Base64.InputStream;

@Controller
public class StoryController {
	@Autowired
	StoryService service;

	public void setService(StoryService service) {
		this.service = service;
	}

	// 글목록
	@RequestMapping(value = "/view/story/storyMain", method = RequestMethod.GET)
	public ModelAndView listing() {
		ModelAndView mav = new ModelAndView();
		List<StoryBoardDto> list = service.listing();
		mav.setViewName("/story/storyMain");
		mav.addObject("list", list);
		return mav;
	}
	
	// 상세보기
	@RequestMapping(value = "/view/story/storyDetail", method = RequestMethod.GET)
	public ModelAndView articleDetail(StoryBoardDto dto) {
		ModelAndView mav = new ModelAndView();
		dto = service.select(dto.getStory_board_idx());
		List<StoryCommentDto> comList = service.readCom(dto.getStory_board_idx());
		mav.setViewName("/story/storyDetail");
		mav.addObject("detail", dto);
		mav.addObject("comList", comList);
		return mav;
	}
	
	// 글수정
	@RequestMapping(value = "/storyEdit", method = RequestMethod.GET)
	public String updateForm() {
		return "/story/storyEdit";
	}
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.POST)
	public ModelAndView articleUpdate(@ModelAttribute("update") StoryBoardDto dto) throws Exception {
		ModelAndView mav = new ModelAndView();
		service.update(dto);
		mav.setViewName("redirect:/view/story/storyDetail");
		return mav;
	}
	
	// 글삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView articleDelete(StoryBoardDto dto, int story_board_idx) throws Exception {
		ModelAndView mav = new ModelAndView();
		service.delete(story_board_idx);
		mav.setViewName("redirect:/view/story/storyMain");
		return mav;
	}
	
	// 글작성
	@RequestMapping(value = "/storyForm", method = RequestMethod.GET)
	public String insertView() {
		return "/view/story/storyForm";
	}
	
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.POST)
	public ModelAndView articleInsert(@ModelAttribute("insert")StoryBoardDto dto, StoryFileDto fileDto) throws Exception {
		ModelAndView mav = new ModelAndView();
		int i = service.insert(dto, fileDto);
		mav.addObject("insert", i);
		mav.setViewName("/story/storyDetail");
		return mav;
	}
	
	@RequestMapping(value = "/insertCom")
	public ModelAndView insertCom(StoryCommentDto dto) throws Exception {
		ModelAndView mav = new ModelAndView();
		service.insertCom(dto);
		mav.addObject("story_board_idx", dto.getStory_board_idx());
		mav.setViewName("redirect:/story/storyDetail");
		return mav;
	}
	
}
