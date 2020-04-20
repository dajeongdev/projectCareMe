package com.careme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.StoryCommand;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;
import com.careme.service.FileUploadService;
import com.careme.service.PageNumberService;
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
	
	@Autowired
	PageNumberService page;

	public void setPage(PageNumberService page) {
		this.page = page;
	}
	
	TagDto tagDto;

	public void setTagDto(TagDto tagDto) {
		this.tagDto = tagDto;
	}


	// 글목록
	@RequestMapping(value = "/view/story/storyMain")
	public ModelAndView listing(int currentPage, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		PageNumberCommand paging = new PageNumberCommand();
		int contentPerPage = 10;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start_idx", page.getStartIdx(currentPage, contentPerPage));
		map.put("contentPerPage", contentPerPage);
		
		List<StoryBoardDto> slist = service.totalListing(map);
		paging = page.paging(service.getTotal(), contentPerPage, currentPage, "story/storyMain?currentPage=");
		
		List<StoryFileDto> fList = service.fileList();
		List<StoryBoardDto> hlist = service.hitList();
		/*int i = service.insertTag(request);
		tagDto.setTag_idx(i);
		int j = service.insertTagType(i, request);*/
		mav.addObject("start_idx", page.getStartIdx(currentPage, contentPerPage));
		mav.addObject("slist", slist);
		mav.addObject("fList", fList);
		mav.addObject("hlist", hlist);
		mav.addObject("paging", paging);
		mav.setViewName("/story/storyMain");
		return mav;
	}
	
	
	@RequestMapping(value = "/view/story/storyMainSearch")
	public ModelAndView searching(@RequestParam int searchType, @RequestParam String keyword) {
		StoryCommand com = new StoryCommand();
		ModelAndView mav = new ModelAndView();
		List<StoryBoardDto> list = null;
		if(searchType == 0) {
			com.setSearchType("member_id");
			com.setKeyword(keyword);
			list = service.searching(com);
			mav.addObject("list", list);
			mav.addObject("count", list.size());
			mav.setViewName("/story/storyMain");
		} else if(searchType == 1) {
			com.setSearchType("title");
			com.setKeyword(keyword);
			list = service.searching(com);
			mav.addObject("list", list);
			mav.addObject("count", list.size());
			mav.setViewName("/story/storyMain");
		} else if(searchType == 2) {
			com.setSearchType("content");
			com.setKeyword(keyword);
			list = service.searching(com);
			mav.addObject("list", list);
			mav.addObject("count", list.size());
			mav.setViewName("/story/storyMain");
		}
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
		//mav.addObject("heart", service.heart(story_board_idx));
		//mav.addObject("comHeart", service.comHeart(story_comment_idx));
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
	
	// 댓글 작성
	@RequestMapping(value = "/view/story/insertCom")
	public String insertCom(StoryCommentDto comDto) {
		int i = service.insertCom(comDto);
		int b = comDto.getStory_board_idx();
		if(i > 0) {
			return "redirect:/view/story/storyDetail?story_board_idx="+b;
		} else {
			System.out.println("error");
			return "redirect:/view/story/storyDetail?story_board_idx="+b;
		}
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
		int i = service.insert(request);
		dto.setStory_board_idx(i);
		mav.addObject("update", service.update(dto));
		mav.setViewName("/story/storyDetail");
		return mav;
	}
	
	// 댓글 수정
	@RequestMapping(value = "/story/updateCom")
	public String updateCom(StoryCommentDto comDto) {
		int i = service.updateCom(comDto);
		int b = comDto.getStory_board_idx();
		if(i > 0) {
			return "redirect:/view/story/storyDetail?story_board_idx="+b;
		} else {
			System.out.println("error");
			return "redirect:/view/story/storyDetail?story_board_idx="+b;
		}
	}
	
	
	// 글삭제
	@RequestMapping(value = "/view/story/delete")
	public String articleDelete(HttpServletRequest request) throws Exception {
		service.delete(request);
		return "redirect:/view/story/storyMain";
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/view/story/deleteCom")
	public String deleteCom(int story_comment_idx) {
		service.deleteCom(story_comment_idx);
		return "redirect:/view/story/storyDetail";
	}
	
	@RequestMapping(value = "/view/main")
	public ModelAndView mainImageList() {
		ModelAndView mav = new ModelAndView();
		List<StoryFileDto> fList = service.mainImageList();
		mav.addObject("fList", fList);
		mav.setViewName("redirect:/view/main");
		return mav;
	}
	
}
