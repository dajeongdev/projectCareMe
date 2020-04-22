package com.careme.controller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.SessionCommand;
import com.careme.model.command.StoryCommand;
import com.careme.model.dto.HeartDto;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;
import com.careme.service.FileUploadService;
import com.careme.service.HashTagService;
import com.careme.service.HeartService;
import com.careme.service.MemberService;
import com.careme.service.PageNumberService;
import com.careme.service.StoryService;
import com.google.gson.Gson;

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
	
	@Autowired
	MemberService mem;

	public void setMem(MemberService mem) {
		this.mem = mem;
	}

	@Autowired
	HashTagService tagService;

	public void setTagService(HashTagService tagService) {
		this.tagService = tagService;
	}
	
	@Autowired
	HeartService heartSer;
	
	public void setHeartSer(HeartService heartSer) {
		this.heartSer = heartSer;
	}

	// 글목록
	@RequestMapping(value = "/view/story/storyMain")
	public ModelAndView listing(HttpSession session, int currentPage) {
		ModelAndView mav = new ModelAndView();
		PageNumberCommand paging = new PageNumberCommand();
		int contentPerPage = 9;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start_idx", page.getStartIdx(currentPage, contentPerPage));
		map.put("contentPerPage", contentPerPage);
		
		List<StoryBoardDto> list = service.listPaging(map);
		paging = page.paging(service.getTotal(), contentPerPage, currentPage, "story/storyMain?currentPage=");
		List<StoryFileDto> fList = service.fileList();
		List<StoryBoardDto> hlist = service.hitList();
		
		mav.addObject("list", list);
		mav.addObject("fList", fList);
		mav.addObject("hlist", hlist);
		mav.addObject("paging", paging);
		MemberDto info = mem.memberInfo("hellojava");
		mav.addObject("info", info);
		mav.setViewName("/story/storyMain");
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyMainSearch")
	public ModelAndView searching(@RequestParam int searchType, @RequestParam String keyword, int currentPage) {
		ModelAndView mav = new ModelAndView("/story/storyMain");
		int contentPerPage = 9;
		StoryCommand com = new StoryCommand();
		com = service.searchList(searchType, keyword);
		int start_idx = page.getStartIdx(currentPage, contentPerPage);
		com.setStart_idx(start_idx);
		com.setContentPerPage(contentPerPage);
		List<StoryBoardDto> list = service.searching(com);
		PageNumberCommand paging = new PageNumberCommand();
		paging = page.paging(service.getTotal(), contentPerPage, currentPage, 
					"storyMain?currentPage="+currentPage+"&searchType="+searchType+"&keyword="+keyword);
		mav.addObject("list", list);
		mav.addObject("paging", paging);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		return mav;
	}
	
	// 상세보기
	@RequestMapping(value = "/view/story/storyDetail", method = RequestMethod.GET)
	public ModelAndView articleDetail(int story_board_idx) {
		ModelAndView mav = new ModelAndView();
		StoryBoardDto dto = service.read(story_board_idx);
		List<StoryFileDto> fileDto = service.readFile(story_board_idx);
		List<StoryCommentDto> comList = service.readCom(story_board_idx);
		int comCount = comList.size();
		service.counting(story_board_idx);
		mav.addObject("dto", dto);
		mav.addObject("fileDto", fileDto);
		mav.addObject("comList", comList);
		mav.addObject("comCount", comCount);
		mav.setViewName("/story/storyDetail");
		MemberDto info = mem.memberInfo("hellojava");
		mav.addObject("info", info);
		return mav;
	}
	
	// 좋아요
	@RequestMapping(value = "/view/story/addSubHeart", method = RequestMethod.GET)
	@ResponseBody
	public String addSubHeart(HttpServletRequest request, int story_board_idx) {
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		
		HeartDto heart = heartSer.getHeartInfo(story_board_idx);
		String check = heart.getHeartCheck();
		
		if(heart.equals("n")) {
			service.addHeart(story_board_idx);
			heart.setHeartCheck("y");
			heartSer.updateHeartInfo(heart);
		} else if(heart.equals("y")) {
			service.subHeart(story_board_idx);
			heart.setHeartCheck("n");
			heartSer.updateHeartInfo(heart);
		}
		
		StoryBoardDto dto = new StoryBoardDto();
		dto.setStory_board_idx(story_board_idx);
		dto = service.read(story_board_idx);
		
		int currentHeart = dto.getHeart();
		System.out.println(currentHeart);
		
		Gson json = new Gson();
		return json.toJson(currentHeart);
	}
	
	// 댓글 좋아요
	@RequestMapping(value = "/view/story/addSubComHeart", method = RequestMethod.GET)
	@ResponseBody
	public String addSubComHeart(HttpServletRequest request, int story_comment_idx, int story_board_idx) {
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		
		HeartDto heart = heartSer.getHeartInfo(story_comment_idx);
		String check = heart.getHeartCheck();
		
		if(heart.equals("n")) {
			service.addComHeart(story_comment_idx);
			heart.setHeartCheck("y");
			heartSer.updateHeartInfo(heart);
		} else if(heart.equals("y")) {
			service.subComHeart(story_comment_idx);
			heart.setHeartCheck("n");
			heartSer.updateHeartInfo(heart);
		}
		StoryBoardDto dto = new StoryBoardDto();
		dto.setStory_board_idx(story_board_idx);
		
		StoryCommentDto comDto = new StoryCommentDto();
		comDto.setStory_comment_idx(story_comment_idx);
		comDto = service.readComIdx(story_comment_idx);
		
		int currentHeart = comDto.getHeart();
		System.out.println(currentHeart);
		
		Gson json = new Gson();
		return json.toJson(currentHeart);
	}
	
	// 글작성
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.GET) 
	public ModelAndView articleInsert(HttpServletRequest request) { 
		ModelAndView mav = new ModelAndView("/story/storyForm");
		MemberDto info = mem.memberInfo("hellojava");
		mav.addObject("info", info);
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.POST)
	public String insertForm(StoryBoardDto dto, StoryFileDto fileDto, int[] rdTag, MultipartHttpServletRequest request) throws Exception {
		
		int no = service.insert(request);
		fileDto.setStory_board_idx(no);
		System.out.println("nnononono"+no);
		service.insertFile(fileDto, request);
		tagService.insertUseTag("s", dto.getStory_board_idx(), rdTag);
		return "/story/storyMain";
	}
	
	// 태그 
	@RequestMapping(value = "/view/story/storyForm/hashCheck", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String hashTagCompare(HttpServletRequest request, String tag_name) {
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		
		// 없으면 넣고, 있으면 찾아서 TagDto로 리턴함
		TagDto tagDto = tagService.checkTag(tag_name, member_idx);
		Gson json = new Gson();
		return json.toJson(tagDto);
	}
	
	@RequestMapping(value = "/view/story/storyForm/hashInsert", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String hashTagInsert(HttpServletRequest request, String tag_name, int board_idx) {
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		
		tagDto = new TagDto();
		tagDto.setTag_name(tag_name);
		tagService.insertTag(tag_name, member_idx);
		Gson json = new Gson();
		return json.toJson(tagDto);
	}
	
	
	// 댓글 작성
	@RequestMapping(value = "/view/story/insertCom")
	public String insertCom(HttpServletRequest request, StoryCommentDto comDto) {
		service.insertCom(comDto);
		int b = comDto.getStory_board_idx();
		return "/story/storyDetail?story_board_idx=" + b;
	}
	
	
	// 글수정
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.GET)
	public ModelAndView updateForm(int story_board_idx) throws Exception {
		ModelAndView mav = new ModelAndView("/story/storyEdit");
		StoryBoardDto dto = service.read(story_board_idx);
		List<StoryFileDto> fileDto = service.readFile(story_board_idx);
		
		mav.addObject("getContent", service.getContent(story_board_idx));
		mav.addObject("member_idx", dto.getMember_idx());
		mav.addObject("story_board_idx", dto.getStory_board_idx());
		mav.addObject("title", dto.getTitle());
		mav.addObject("content", dto.getContent());
		mav.addObject("fileDto", fileDto);
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.POST)
	public String articleUpdate(StoryBoardDto dto, StoryFileDto fileDto, MultipartHttpServletRequest request, Integer[] deletedFiles) throws Exception {
		
		service.update(dto, request);
		service.updateFile(fileDto, deletedFiles, request);
		return "/story/storyMain";
	}
	
	// 댓글 수정
	@RequestMapping(value = "/view/story/updateCom")
	public ModelAndView updateCom(StoryCommentDto comDto, int story_board_idx) {
		ModelAndView mav = new ModelAndView();
		StoryBoardDto dto = service.read(story_board_idx);
		mav.addObject("story_board_idx", dto.getStory_board_idx());
		List<StoryCommentDto> com = service.readCom(story_board_idx);
		mav.addObject("com", com);
		return mav;
	}
	
	
	// 글삭제
	@RequestMapping(value = "/view/story/delete")
	public String articleDelete(int story_board_idx) throws Exception {
		service.delete(story_board_idx);
		return "redirect:/view/story/storyMain";
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/view/story/deleteCom")
	public String deleteCom(int story_comment_idx) {
		service.deleteCom(story_comment_idx);
		return "redirect:/view/story/storyDetail";
	}
	

}
