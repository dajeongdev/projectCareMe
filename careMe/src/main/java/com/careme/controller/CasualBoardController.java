package com.careme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.PetSpeciesDto;
import com.careme.model.dto.QuestionBoardDto;
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
	
//게시판 뿌리기(게시글 / 댓글 / 글개수)
	@RequestMapping(value = "/view/casualBoardView/casualBoard")
	public ModelAndView toCasualBoard() {
		List<QuestionBoardDto> getArts = bs.getCasualBoard();
		ModelAndView list = new ModelAndView();
		list.addObject("list", getArts);
		list.addObject("count", getArts.size());
		list.setViewName("/casualBoardView/casualBoard");
		return list;
	}


//게시글 내용 불러오기
	@RequestMapping(value = "/view/casualBoardView/casualBoardContent", method = RequestMethod.GET)
	public ModelAndView casualBoardContents(@RequestParam int question_table_idx) throws Exception {
		ModelAndView mav = new ModelAndView();
		bs.getCasualBoardViews(question_table_idx);
		QuestionBoardDto mlist = bs.getCasualBoardContents(question_table_idx);
		List<BoardCommentDto> clist = bs.getCasualBoardComments(question_table_idx);
		int commentCount = clist.size();
		mav.addObject("mlist", mlist);
		mav.addObject("clist", clist);
		mav.addObject("commCount", commentCount);
		mav.setViewName("casualBoardView/casualBoardContent");
		return mav;
	}
		

// 게시판 검색기능
	@RequestMapping(value = "/view/casualBoardView/casualBoardSearch")
	public ModelAndView casualBoardSearch(@RequestParam int searchn, String searchKeyword) {
		SearchBoardCommand sbc = new SearchBoardCommand();
		ModelAndView list = new ModelAndView();
		List<QuestionBoardDto> items = null;

		if (searchn == 0) {

			sbc.setSearch_option("member_id");
			sbc.setSearchKeyword(searchKeyword);
			items = bs.getCasualBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("list");

		} else if (searchn == 1) {

			sbc.setSearch_option(""
					+ ""
					+ "");
			sbc.setSearchKeyword(searchKeyword);
			items = bs.getCasualBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("list");

		} else if (searchn == 2) {

			sbc.setSearch_option("content");
			sbc.setSearchKeyword(searchKeyword);
			items = bs.getCasualBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("list");

		}
		return list;
	}

	
// 게시글 작성
	@RequestMapping(value = "/view/casualBoardView/casualWriteForm", method = RequestMethod.GET)
	public ModelAndView toWriteForm() throws Exception {
		ModelAndView write = new ModelAndView("casualBoardView/casualWriteForm");
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
	public String writeCasualBoardArticle(MultipartHttpServletRequest request) throws Exception {
		
		bs.addCasualArticles(request);
		int result = bs.addArtFileForCasual(request);
		
		if (result > 0) {
			return "redirect:/view/casualBoardView/casualBoard";
		} else {
			return "redirect:/view/casualBoardView/casualBoard";
		}
	}
	


	
// 게시글 수정
	@RequestMapping(value = "/view/casualBoardView/casualBoardUpdateForm")
	public ModelAndView toCasualUpdate(@RequestParam int question_table_idx) throws Exception {
		ModelAndView update = new ModelAndView("casualBoardView/casualBoardUpdateForm");
		int idx = question_table_idx;
			update.addObject("speciesOption", ps.selectPetSpeciesLevel1());
			update.addObject("idx", idx);
			return update;
	}
	

	@RequestMapping(value = "/view/casualBoardView/casualBoardUpdateAdd", method = RequestMethod.POST)
	public String updateCasualArticle(MultipartHttpServletRequest request) throws Exception {
		int result = bs.updateCasualArticle(request);
		if (result > 0) {
			return "redirect:/view/casualBoardView/casualBoard";
		} else {
			return "redirect:/view/casualBoardView/casualBoard";
		}
	}
	
	
// 게시글 삭제
	@RequestMapping(value="/view/casualBoardView/deleteArticle")
	public String deleteCasualArticle(@RequestParam int question_table_idx) {
		int idx = question_table_idx;
		int result = bs.deleteCasualArticle(idx);
		if(result>0) {
		return "redirect:/view/casualBoardView/casualBoard";
		}else {
			System.out.println("no!!!");
		return "redirect:/view/casualBoardView/casualBoard";
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
			return "redirect:history.go(-1)";
			}else {
				System.out.println("no!!!");
			return "redirect:history.go(-1)";
			}
		}
	
	
	
// 자주 묻는 질문 링크
	@RequestMapping(value = "/view/infoBoardView/infoBoard")
	public ModelAndView infoLink() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infoBoardView/infoBoard");
		return mav;
	}
	
}