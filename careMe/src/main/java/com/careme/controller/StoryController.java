package com.careme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.dto.StoryDto;
import com.careme.service.StoryService;

@Controller
public class StoryController {
	@Autowired
	StoryService service;

	public void setService(StoryService service) {
		this.service = service;
	}
	
	@RequestMapping("/story/story_main")
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		List<StoryDto> list = service.selectAll();
		mav.setViewName("story/story_main");
		mav.addObject("story", list);
		return mav;
	}
}
