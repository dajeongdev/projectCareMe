package com.careme.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.careme.model.dto.StoryBoardDto;
import com.careme.service.StoryService;

@Controller
public class StoryController {
	@Autowired
	StoryService service;

	public void setService(StoryService service) {
		this.service = service;
	}

	// 글목록
	@RequestMapping(value = "/view/story/storyMain", method = RequestMethod.GET)
	public String listing(Model model) {
		model.addAttribute("list", service.list());
		return "story/storyMain";
	}
	
	// 글작성
	@RequestMapping(value = "/storyForm") 
	public String insertView() { 
		return "/story/storyForm";
	}
		
	@RequestMapping(value = "/view/story/storyForm")
	public String articleInsert(StoryBoardDto dto) {
		service.insert(dto);
		return "redirect:/story/storyDetail";
	}
	
	// 상세보기
	@RequestMapping(value = "/view/story/storyDetail", method = RequestMethod.POST)
	public String articleDetail(StoryBoardDto dto, Model model) {
		model.addAttribute("read", service.read(dto.getStory_board_idx()));
		return "story/storyDetail";
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
	@RequestMapping(value = "/storyEdit", method = RequestMethod.GET)
	public String updateForm(StoryBoardDto dto, Model model) {
		model.addAttribute("update", service.read(dto.getStory_board_idx()));
		return "story/updateForm";
	}
	
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.POST)
	public String articleUpdate(StoryBoardDto dto) throws Exception {
		service.update(dto);
		return "redirect:/storyMain";
	}
	
	// 글삭제
	@RequestMapping(value = "/story/delete", method = RequestMethod.POST)
	public String articleDelete(StoryBoardDto dto) throws Exception {
		service.delete(dto.getStory_board_idx());
		return "redirect:/story/storyMain";
	}
	
}
