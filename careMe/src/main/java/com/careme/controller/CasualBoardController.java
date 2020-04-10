package com.careme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.careme.dao.QuestionBoardDao;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.service.QuestionBoardService;

@Controller
public class CasualBoardController {

	@Autowired
	QuestionBoardService bs;
	QuestionBoardDao boardDao;

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
		
		mav.addObject("mlist", mlist);
		mav.addObject("clist", clist);
		mav.setViewName("casualBoardView/casualBoardContent");
		return mav;
	}
		

// 게시판 검색기능
	@RequestMapping(value = "/view/casualBoardView/casualBoardSearch")
	public ModelAndView doctorBoardSearch(@RequestParam int searchn, String searchKeyword) {
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
	@RequestMapping(value = "/view/casualBoardView/casualWriteForm")
	public ModelAndView toWriteForm() throws Exception {
		ModelAndView write = new ModelAndView();
		List<QuestionBoardDto> getSpecs = bs.getSpeciesForCasual();

		if (getSpecs == null) {
			write.setViewName("casualBoardView/casualWriteForm");
			return write;
		} else {
			write.addObject("specs", getSpecs);
			write.setViewName("casualBoardView/casualWriteForm");
			System.out.println(write);
			return write;
		}
	}

	@RequestMapping(value = "/view/casualBoardView/casualBoardWriteAdd", method = RequestMethod.POST)
	public String writeDoctorBoardArticle(QuestionBoardDto boardDto) throws Exception {
		int result = bs.addCasualArticles(boardDto);
		System.out.println(boardDto);
		if (result > 0) {
			return "redirect:/view/casualBoardView/casualBoard";
		} else {
			return "redirect:/view/casualBoardView/casualBoard";
		}
	}

// 게시글 수정
	@RequestMapping(value = "/view/casualBoardView/casualBoardUpdateForm")
	public ModelAndView toCasualUpdate(@RequestParam int question_table_idx) throws Exception {
		ModelAndView update = new ModelAndView();
		List<QuestionBoardDto> getSpecs = bs.getSpeciesForCasual();
		int idx = question_table_idx;

		if (getSpecs == null) {
			update.setViewName("casualBoardView/casualBoardUpdateForm");
			return update;
		} else {
			update.addObject("specs", getSpecs);
			update.addObject("idx", idx);
			update.setViewName("casualBoardView/casualBoardUpdateForm");
			return update;
		}
	}

	@RequestMapping(value = "/view/casualBoardView/casualBoardUpdateAdd", method = RequestMethod.POST)
	public String updateCasualArticle(QuestionBoardDto boardDto) throws Exception {
		int result = bs.updateCasualArticle(boardDto);
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
