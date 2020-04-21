package com.careme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.BoardFileDto;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.PetSpeciesDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.service.FileUploadService;
import com.careme.service.MemberService;
import com.careme.service.PageNumberService;
import com.careme.service.PetService;
import com.careme.service.QuestionBoardService;
import com.google.gson.Gson;

@Controller
public class DoctorBoardController {

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
	@RequestMapping(value = "/view/doctorBoardView/doctorBoard")
	public ModelAndView toDoctorBoard(int currentPage) {
		ModelAndView listPro = new ModelAndView("/doctorBoardView/doctorBoard");
		
		MemberDto info = ms.memberInfo("hellojava");
		System.out.println(info.getMember_id());
		System.out.println(info.getMember_idx());
		
		PageNumberCommand paging = new PageNumberCommand();
		int contentPerPage = 10;
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start_idx", pns.getStartIdx(currentPage, contentPerPage));
		param.put("contentPerPage", contentPerPage);
		
		List<QuestionBoardDto> getArticles = bs.getDoctorBoardPage(param);
		paging = pns.paging(getArticles.size(), contentPerPage, currentPage, "doctorBoardView/doctorBoard?currentPage=");
		
		listPro.addObject("listPro", getArticles);
		listPro.addObject("paging", paging);
		return listPro;
	}

//게시글 내용 불러오기
	@RequestMapping(value = "/view/doctorBoardView/doctorBoardContent", method = RequestMethod.GET)
	public ModelAndView doctorBoardContents(@RequestParam int question_table_idx) throws Exception {
		ModelAndView mav = new ModelAndView("doctorBoardView/doctorBoardContent");
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		mav.addObject("info", info);
		
		//글내용 불러오기
		bs.getDoctorBoardViews(question_table_idx);
		QuestionBoardDto mlist=bs.getDoctorBoardContents(question_table_idx);
		List<BoardFileDto> flist = bs.getDoctorBoardFiles(question_table_idx);
		List<BoardCommentDto> clist = bs.getDoctorBoardComments(question_table_idx);
		
		String idx = String.valueOf(question_table_idx);
		
		int commentCount = clist.size();
		mav.addObject("mlist", mlist);
		mav.addObject("flist", flist);
		mav.addObject("idx", idx);
		mav.addObject("clist", clist);
		mav.addObject("commCount", commentCount);
		return mav;
	}

	
// 게시판 검색기능
	@RequestMapping(value = "/view/doctorBoardSearch")
	public ModelAndView doctorBoardSearch(@RequestParam int searchn, String searchKeyword, int currentPage) {
		ModelAndView list = new ModelAndView("/doctorBoardView/doctorBoardSearch");
		int contentPerPage = 10;
		 
		//검색 정보 처리
		SearchBoardCommand sbc = new SearchBoardCommand();
		sbc=bs.listSearchInfo(searchn, searchKeyword);
		
		int start_idx=pns.getStartIdx(currentPage, contentPerPage);
		sbc.setStart_idx(start_idx);
		sbc.setContentPerPage(contentPerPage);
		
		List<QuestionBoardDto> items = bs.getDoctorBoardSearch(sbc);
		
		// 내용 및 페이지 번호
		PageNumberCommand paging = new PageNumberCommand();
		paging = pns.paging(bs.getTotal(), contentPerPage, currentPage, "doctorBoardView/doctorBoardSearch?currentPage="+currentPage+"&searchn="+searchn+"&searchKeyword="+searchKeyword);
		
		list.addObject("list", items);
		list.addObject("paging", paging);
		list.addObject("searchn", searchn);
		list.addObject("searchKeyword", searchKeyword);
		
		
		return list;
	}

	// 게시글 작성
		@RequestMapping(value = "/view/doctorBoardView/doctorWriteForm", method = RequestMethod.GET)
		public ModelAndView toWriteForm() throws Exception {
			ModelAndView write = new ModelAndView("doctorBoardView/doctorWriteForm");
			
			//회원 정보 및 확인
//			String currentId = session.getAttribute("id");
			MemberDto info = ms.memberInfo("testmin");
			
			write.addObject("info", info);
			write.addObject("speciesOption", ps.selectPetSpeciesLevel1());
			return write;
		}
		
		@RequestMapping(value = "/view/doctorBoardView/doctorWriteForm/pet_species_idx", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String getDoctorPetSpeciesList(int level, int ancestor) {
			List<PetSpeciesDto> items = null;
			
			if (level == 1) items = ps.selectPetSpeciesLevel1();
			else if (level == 2) items = ps.selectPetSpeciesLevel2(ancestor);
			
			Gson json = new Gson();
			return json.toJson(items);	
		}
		
		
		@RequestMapping(value = "/view/doctorBoardView/doctorBoardWriteAdd", method = RequestMethod.POST)
		public String writeDoctorBoardArticle(QuestionBoardDto dto, MultipartHttpServletRequest request) throws Exception {
			bs.addDoctorArticles(dto, request);
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
		}
	
	
// 게시글 수정
	@RequestMapping(value="/view/doctorBoardView/doctorBoardUpdateForm")
	public ModelAndView toDoctorUpdate(@RequestParam int question_table_idx) throws Exception {
		ModelAndView update = new ModelAndView("doctorBoardView/doctorBoardUpdateForm");
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
		MemberDto info = ms.memberInfo("hellojava");
		update.addObject("info", info);
		
		QuestionBoardDto mlist = bs.getDoctorBoardContents(question_table_idx);
		
		int idx = question_table_idx;
			update.addObject("speciesOption", ps.selectPetSpeciesLevel1());
			update.addObject("idx", idx);

			update.addObject("mlist", mlist);
			
			return update;
	}


	@RequestMapping(value = "/view/doctorBoardView/doctorBoardUpdateAdd", method = RequestMethod.POST)
	public String updateDoctorArticle(QuestionBoardDto dto) throws Exception {
		int result = bs.updateDoctorArticle(dto);
		if (result > 0) {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
		} else {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
		}
	}

	
	// 게시글 삭제
		@RequestMapping(value="/view/doctorBoardView/deleteDoctorArticle")
		public String deleteDoctorArticle(@RequestParam int question_table_idx) {
			int idx = question_table_idx;
			int result = bs.deleteDoctorArticle(idx);
			if(result>0) {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
			}else {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
			}
		}

//==================================================================================
		
	// comment 작성
		@RequestMapping(value="/view/doctorBoardView/doctorCommentAdd")
		public String writeDoctorComment(BoardCommentDto commentDto) throws Exception {
			int result = bs.addDoctorComment(commentDto);
			int backToPage=commentDto.getQuestion_table_idx();
			if (result > 0) {
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			} else {
				System.out.println("no!!!");
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			}
		}

			
	// comment 수정
		@RequestMapping(value = "/view/doctorBoardView/doctorCommentUpdate", method = RequestMethod.POST)
		public String updateDoctorComment(BoardCommentDto commentDto) throws Exception {
			int result = bs.updateDoctorComment(commentDto);
			int backToPage=commentDto.getQuestion_table_idx();
			if (result > 0) {
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			} else {
				System.out.println("no!!!");
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			}
		}
			
			
	// comment 삭제
		@RequestMapping(value="/view/doctorBoardView/doctorCommentDelete")
		public String deleteDoctorComment(@RequestParam int question_board_comment_idx) {
			int idx = question_board_comment_idx;
			int result = bs.deleteDoctorComment(idx);
			if(result>0) {
			return "redirect:history.go(-1)";
			}else {
				System.out.println("no!!!");
			return "redirect:history.go(-1)";
			}
		}
}
