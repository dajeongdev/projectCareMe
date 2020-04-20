package com.careme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.command.TagCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.PetSpeciesDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.model.dto.TagDto;
import com.careme.service.FileUploadService;
import com.careme.service.MemberService;
import com.careme.service.PageNumberService;
import com.careme.service.PetService;
import com.careme.service.QuestionBoardService;
import com.google.gson.Gson;


@Controller
public class CasualBoardController {
	
	@Autowired
	QuestionBoardService bs;
	
	public void setQuestionBoardService(QuestionBoardService bs) {
		this.bs = bs;
	}
	
	@Autowired
	PetService ps;
	
	public void setPetService(PetService ps) {
		this.ps = ps;
	}
	
	@Autowired
	FileUploadService fus;
	
	public void setFileUploadService(FileUploadService fus) {
		this.fus = fus;
	}
	
	@Autowired
	PageNumberService pns;
	
	public void setPageNumberService(PageNumberService pns) {
		this.pns = pns;
	}
	
	@Autowired
	MemberService ms;
	
	public void setMemberService(MemberService ms) {
		this.ms=ms;
	}
	
	
//게시판 뿌리기(게시글 / 댓글 / 글개수)
	@RequestMapping(value = "/view/casualBoardView/casualBoard")
	public ModelAndView toCasualBoard(int currentPage, HttpSession session) {
		ModelAndView list = new ModelAndView("/casualBoardView/casualBoard");
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		list.addObject("info", info);
		
//		System.out.println(info.getMember_idx());
//		System.out.println(info.getMember_nick());
//		System.out.println(info.getMember_id());
		
		// 내용 및 페이지 번호
		PageNumberCommand paging = new PageNumberCommand();
		int contentPerPage = 10;

		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start_idx", pns.getStartIdx(currentPage, contentPerPage));
		param.put("contentPerPage", contentPerPage);
		
		List<QuestionBoardDto> getArticles = bs.getCasualBoardPage(param);
		paging = pns.paging(bs.getTotal(), contentPerPage, currentPage, "casualBoardView/casualBoard?currentPage=");
		
		
		list.addObject("list", getArticles);
		list.addObject("paging", paging);
		
		return list;
	}
	

//게시글 내용 불러오기
	@RequestMapping(value = "/view/casualBoardView/casualBoardContent", method = RequestMethod.GET)
	public ModelAndView casualBoardContents(@RequestParam int question_table_idx) throws Exception {
		ModelAndView mav = new ModelAndView("casualBoardView/casualBoardContent");
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		mav.addObject("info", info);
		
		//글내용 불러오기
		bs.getCasualBoardViews(question_table_idx);
		QuestionBoardDto mlist = bs.getCasualBoardContents(question_table_idx);
		List<BoardCommentDto> clist = bs.getCasualBoardComments(question_table_idx);
		int commentCount = clist.size();
		mav.addObject("mlist", mlist);
		mav.addObject("clist", clist);
		mav.addObject("commCount", commentCount);
		return mav;
	}
	
		

// 게시판 검색기능
	@RequestMapping(value = "/view/casualBoardView/casualBoardSearch")
	public ModelAndView casualBoardSearch(@RequestParam int searchn, String searchKeyword, int currentPage) {
		ModelAndView list = new ModelAndView("/casualBoardView/casualBoardSearch");
		int contentPerPage = 10;
		 
		//검색 정보 처리
		SearchBoardCommand sbc = new SearchBoardCommand();
		sbc=bs.listSearchInfo(searchn, searchKeyword);
		
		int start_idx=pns.getStartIdx(currentPage, contentPerPage);
		sbc.setStart_idx(start_idx);
		sbc.setContentPerPage(contentPerPage);
		System.out.println(currentPage);
		System.out.println(sbc.getSearch_option());
		System.out.println(sbc.getContentPerPage());
		System.out.println(sbc.getSearchKeyword());
		System.out.println(sbc.getStart_idx());
		
		List<QuestionBoardDto> items = bs.getCasualBoardSearch(sbc);
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("searchn", searchn);
		searchInfo.put("searchKeyword", searchKeyword);
		
		// 내용 및 페이지 번호
		PageNumberCommand paging = new PageNumberCommand();
		paging = pns.paging(bs.getTotal(), contentPerPage, currentPage, "casualBoardView/casualBoardSearch?currentPage="+currentPage+"&searchn="+searchn+"&searchKeyword="+searchKeyword);
		
		list.addObject("list", items);
		list.addObject("paging", paging);
		//'list.addObject("searchInfo", searchInfo);
		list.addObject("searchn", searchn);
		list.addObject("searchKeyword", searchKeyword);
		
		
		return list;
	}

	
// 게시글 작성
	@RequestMapping(value = "/view/casualBoardView/casualWriteForm", method = RequestMethod.GET)
	public ModelAndView toWriteForm() throws Exception {
		ModelAndView write = new ModelAndView("casualBoardView/casualWriteForm");
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		
		write.addObject("info", info);
		write.addObject("speciesOption", ps.selectPetSpeciesLevel1());
		return write;
	}
	
	@RequestMapping(value = "/view/casualBoardView/casualWriteForm/pet_species_idx", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCasualPetSpeciesList(int level, int ancestor) {
		List<PetSpeciesDto> items = null;
		
		if (level == 1) items = ps.selectPetSpeciesLevel1();
		else if (level == 2) items = ps.selectPetSpeciesLevel2(ancestor);
		
		Gson json = new Gson();
		return json.toJson(items);	
	}
	
	
	@RequestMapping(value = "/view/casualBoardView/casualBoardWriteAdd", method = RequestMethod.POST)
	public String writeCasualBoardArticle(QuestionBoardDto dto, MultipartHttpServletRequest request) throws Exception {
		System.out.println("컨트롤러 도착");
		bs.addCasualArticles(dto, request);
		return "redirect:/view/casualBoardView/casualBoard?currentPage=1";
	}

	// hashtag 기능
	@RequestMapping(value="/view/casualBoardView/casualWriteForm/hashCheck", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String hashtagCompare(@RequestParam String tagValue, int member_idx) {
		List<TagDto> compared = bs.compareHashtag(tagValue);
		Gson json = new Gson();
		int listsize = compared.size();
		int idx = member_idx;
		
		if(listsize==0){
			
			TagCommand tc = new TagCommand();
			tc.setTag_name(tagValue);
			tc.setMember_idx(idx);
			tc.setDel_yn("n");
			List<TagDto> added = bs.addHashtag(tc);
			compared=added;
			return json.toJson(compared);
			
		}else {
		return json.toJson(compared);
		}
	}
	
	
	
	
// 게시글 수정
	@RequestMapping(value = "/view/casualBoardView/casualBoardUpdateForm")
	public ModelAndView toCasualUpdate(@RequestParam int question_table_idx) throws Exception {
		ModelAndView update = new ModelAndView("casualBoardView/casualBoardUpdateForm");
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		update.addObject("info", info);
		
		int idx = question_table_idx;
			update.addObject("speciesOption", ps.selectPetSpeciesLevel1());
			update.addObject("idx", idx);
			return update;
	}
	

	@RequestMapping(value = "/view/casualBoardView/casualBoardUpdateAdd", method = RequestMethod.POST)
	public String updateCasualArticle(QuestionBoardDto dto) throws Exception {
		int result = bs.updateCasualArticle(dto);
		if (result > 0) {
			return "redirect:/view/casualBoardView/casualBoard?currentPage=1";
		} else {
			return "redirect:/view/casualBoardView/casualBoard?currentPage=1";
		}
	}
	
	
// 게시글 삭제
	@RequestMapping(value="/view/casualBoardView/deleteCasualArticle")
	public String deleteCasualArticle(@RequestParam int question_table_idx) {
		int idx = question_table_idx;
		int result = bs.deleteCasualArticle(idx);
		if(result>0) {
		return "redirect:/view/casualBoardView/casualBoard?currentPage=1";
		}else {
		return "redirect:/view/casualBoardView/casualBoard?currentPage=1";
		}
	}
	
	
//	===================================================================================================================
	
	
	// comment 작성
		@RequestMapping(value="/view/casualBoardView/casualCommentAdd")
		public String writeCasualComment(BoardCommentDto commentDto) throws Exception {
			int result = bs.addCasualComment(commentDto);
			int backToPage=commentDto.getQuestion_table_idx();
			if (result > 0) {
				return "redirect:/view/casualBoardView/casualBoardContent?question_table_idx="+backToPage;
			} else {
				System.out.println("no!!!");
				return "redirect:/view/casualBoardView/casualBoardContent?question_table_idx="+backToPage;
			}
		}

		
	// comment 수정
		@RequestMapping(value = "/view/casualBoardView/casualCommentUpdate", method = RequestMethod.POST)
		public String updateCasualComment(BoardCommentDto commentDto) throws Exception {
			int result = bs.updateCasualComment(commentDto);
			int backToPage=commentDto.getQuestion_table_idx();
			if (result > 0) {
				return "redirect:/view/casualBoardView/casualBoardContent?question_table_idx="+backToPage;
			} else {
				System.out.println("no!!!");
				return "redirect:/view/casualBoardView/casualBoardContent?question_table_idx="+backToPage;
			}
		}
		
		
	// comment 삭제
		@RequestMapping(value="/view/casualBoardView/casualCommentDelete")
		public String deleteCasualComment(@RequestParam int question_board_comment_idx) {
			int idx = question_board_comment_idx;
			
			int result = bs.deleteCasualComment(idx);
			if(result>0) {
			return "redirect:/view/casualBoardView/casualBoard";
			}else {
				System.out.println("no!!!");
			return "redirect:/view/casualBoardView/casualBoard";
			}
		}
	

		
		
	
// 임시 마이페이지 링크
	@RequestMapping(value = "/view/myPageView/myPageDoctor")
	public ModelAndView doctorMyPage() throws Exception{
		ModelAndView mav = new ModelAndView();
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		mav.addObject("info", info);
		
		//페이지뷰
		mav.setViewName("myPageView/myPageDoctor");
		return mav;
	}
	
	@RequestMapping(value = "/view/myPageView/myPageCasual")
	public ModelAndView infoLink() throws Exception{
		ModelAndView mav = new ModelAndView();
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		mav.addObject("info", info);
		
		//페이지뷰
		mav.setViewName("myPageView/myPageCasual");
		return mav;
	}
	
	
	
}