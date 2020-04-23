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
import com.careme.model.command.TagListCommand;
import com.careme.model.dto.BoardCommentDto;
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
	
	// 검색
	@RequestMapping(value = "/view/story/storySearch")
	public ModelAndView searching(int searchType, String keyword, int currentPage) {
		ModelAndView mav = new ModelAndView("story/storySearch");
		int contentPerPage = 12;

		StoryCommand com = new StoryCommand();
		com = service.searchList(searchType, keyword);
		int start_idx = page.getStartIdx(currentPage, contentPerPage);
		System.out.println("currentPage1: " + currentPage);
		com.setStart_idx(start_idx);
		com.setContentPerPage(contentPerPage);
		List<StoryBoardDto> list = service.searching(com);
		List<StoryFileDto> fList = service.fileList();
		
		// 페이징
		PageNumberCommand paging = new PageNumberCommand();
		paging = page.paging(service.getTotal(), contentPerPage, currentPage, 
					"story/storySearch?currentPage="+currentPage+"&searchType="+searchType+"&keyword="+keyword);
		mav.addObject("list", list);
		mav.addObject("fList", fList);
		mav.addObject("paging", paging);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		mav.addObject("currentPage", currentPage);
		return mav;
	}
	
	// 상세보기
	@RequestMapping(value = "/view/story/storyDetail", method = RequestMethod.GET)
	public ModelAndView articleDetail(int story_board_idx, String tag_name, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		MemberDto info = sc.getMemberDto();
		int member_idx = sc.getMemberDto().getMember_idx();
		int contentPerPage = 12;
		StoryBoardDto dto = service.read(story_board_idx);
		List<StoryFileDto> fileDto = service.readFile(story_board_idx);
		List<StoryCommentDto> comList = service.readCom(story_board_idx);
		story_board_idx = dto.getStory_board_idx();
		List<TagDto> tagList = service.readTags(story_board_idx);
		int currentPage = 1;
		int comCount = comList.size();
		service.counting(story_board_idx);
		
		mav.addObject("info", info);
		mav.addObject("member_idx", member_idx);
		mav.addObject("dto", dto);
		mav.addObject("fileDto", fileDto);
		mav.addObject("comList", comList);
		mav.addObject("comCount", comCount);
		mav.addObject("tags", tagList);
		mav.addObject("currentPage", currentPage);
		mav.setViewName("/story/storyDetail");
		return mav;
	}
	 
	
	// 글작성
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.GET) 
	public ModelAndView articleInsert(HttpServletRequest request) { 
		ModelAndView mav = new ModelAndView("/story/storyForm");
		SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		MemberDto info = sc.getMemberDto();
		mav.addObject("info", info);
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyForm", method = RequestMethod.POST)
	public String insertForm(StoryBoardDto dto, StoryFileDto fileDto, int[] rdTag, MultipartHttpServletRequest request) throws Exception {
		SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		dto.setMember_idx(sc.getMemberDto().getMember_idx());
		
		int i = service.insert(dto, request);
		int story_board_idx = dto.getStory_board_idx();
		
		tagService.insertUseTag("s", story_board_idx, rdTag);
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
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		comDto.setMember_idx(member_idx);
		service.insertCom(comDto);
		int b = comDto.getStory_board_idx();
		int res = comDto.getStory_comment_idx();
		
		HeartDto heart = new HeartDto();
		heart.setBoard_comment_idx(res);
		heart.setBoard_type("s");
		heart.setHeartCheck("n");
		heart.setMember_idx(member_idx);
		heartSer.insertHeartInfo(heart);
		
		return "redirect:/view/story/storyDetail?story_board_idx=" + b;
	}
	
	
	// 글수정
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest request, int story_board_idx) throws Exception {
		ModelAndView mav = new ModelAndView("/story/storyEdit");
		StoryBoardDto dto = service.read(story_board_idx);
		List<StoryFileDto> fileDto = service.readFile(story_board_idx);
		List<TagDto> tagList = service.readTags(story_board_idx);
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();

		mav.addObject("getContent", service.getContent(story_board_idx));
		mav.addObject("member_idx", member_idx);
		mav.addObject("story_board_idx", dto.getStory_board_idx());
		mav.addObject("title", dto.getTitle());
		mav.addObject("content", dto.getContent());
		mav.addObject("fileDto", fileDto);
		mav.addObject("tags", tagList);
		return mav;
	}
	
	@RequestMapping(value = "/view/story/storyEdit", method = RequestMethod.POST)
	public String articleUpdate(StoryBoardDto dto, StoryFileDto fileDto, MultipartHttpServletRequest request, Integer[] deletedFiles) throws Exception {
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		int b = dto.getStory_board_idx();
		service.update(dto, fileDto, deletedFiles, request);
		return "/story/storyDetail?story_board_idx=" + b;
	}
	
	// 댓글 수정
	@RequestMapping(value = "/view/story/updateCom")
	public ModelAndView updateCom(HttpServletRequest request, StoryCommentDto comDto, int story_board_idx) {
		ModelAndView mav = new ModelAndView();
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		StoryBoardDto dto = service.read(story_board_idx);
		mav.addObject("story_board_idx", dto.getStory_board_idx());
		List<StoryCommentDto> com = service.readCom(story_board_idx);
		mav.addObject("com", com);
		mav.addObject("member_idx", member_idx);
		return mav;
	}	
	
	// 글삭제
	@RequestMapping(value = "/view/story/delete")
	public String articleDelete(HttpServletRequest request, StoryBoardDto dto, StoryCommentDto comDto) throws Exception {
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		int story_board_idx = dto.getStory_board_idx();
		int story_comment_idx = comDto.getStory_comment_idx();
		service.deleteFile(story_board_idx);
		service.deleteCom(story_comment_idx);
		service.delete(story_board_idx);
		return "redirect:/view/story/storyMain";
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/view/story/deleteCom")
	public String deleteCom(HttpServletRequest request, StoryCommentDto comDto) {
		SessionCommand sc = (SessionCommand)request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		int c = comDto.getStory_comment_idx();
		int i = comDto.getStory_board_idx();
		service.deleteCom(c);
		
		return "redirect:/view/story/storyDetail?story_board_idx=" + i;
	}
	
	// 댓글 좋아요
	@RequestMapping(value = "/view/story/addSubComHeart", method = RequestMethod.GET)
	@ResponseBody
	public String updateHeart(HttpServletRequest request, int story_comment_idx, int story_board_idx) {
		MemberDto info = mem.memberInfo("hellojava");
		int member_idx = info.getMember_idx();
		int check = heartSer.memberCheck(member_idx);
		
		HeartDto heart = new HeartDto();
		heart.setBoard_comment_idx(story_comment_idx);
		heart.setMember_idx(member_idx);
		
		if(check == 0) {
			heart.setBoard_type("c");
			heart.setHeartCheck("n");
			heartSer.insertHeartInfo(heart);
			System.out.println("없을 경우 가져오는 hdto"  +heart);
			service.hearting(heart, story_comment_idx);
		}else {
			heart = heartSer.getHeartInfo(heart);
			System.out.println("있을 경우 가져오는 hdto"+heart);
			service.hearting(heart, story_comment_idx);
		}
		
		StoryCommentDto comDto = new StoryCommentDto();
		comDto.setStory_comment_idx(story_comment_idx);
		comDto = service.readComIdx(story_comment_idx);
		
		int currentHeart = comDto.getHeart();
		System.out.println(currentHeart);
		
		Gson json = new Gson();
		return json.toJson(currentHeart);
	}
	
	// 태그 리스트
	@RequestMapping(value = "/view/story/storyTag")
	public ModelAndView tagListing(StoryBoardDto dto, HttpSession session, int tag_idx, int currentPage) {
		ModelAndView mav = new ModelAndView("/story/storyTag");
		
		int contentPerPage = 12;
		currentPage = 1;
		TagListCommand tagListCom = new TagListCommand();
		tagListCom = service.tagInfo(tag_idx);
		int start_idx = page.getStartIdx(currentPage, contentPerPage);
		tagListCom.setStart_idx(start_idx);
		tagListCom.setContentPerPage(contentPerPage);
		
		List<TagDto> tagList = service.tagSelect(tagListCom);
		List<StoryFileDto> fList = service.fileList();
		PageNumberCommand paging = new PageNumberCommand();
		paging = page.paging(service.getTotal(), contentPerPage, currentPage, "story/storyTag?currentPage="+currentPage+"&tag_idx="+tag_idx);
		
		mav.addObject("tagList", tagList);
		mav.addObject("tagFileList", fList);
		mav.addObject("tag_idx", tag_idx);
		mav.addObject("currentPage", currentPage);
		return mav;
	}

}
