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
public class DoctorBoardController {

	@Autowired
	QuestionBoardService bs;
	QuestionBoardDao boardDao;

	// 게시판 뿌리기
	@RequestMapping(value = "/view/doctorBoardView/doctorBoard")
	public ModelAndView toDoctorBoard() {
		List<QuestionBoardDto> getArts = bs.getDoctorBoard();
		ModelAndView listPro = new ModelAndView();
		listPro.addObject("listPro", getArts);
		listPro.addObject("countPro", getArts.size());
		listPro.setViewName("/doctorBoardView/doctorBoard");
		return listPro;
	}

	// 게시글 내용 불러오기
	@RequestMapping(value = "/view/doctorBoardView/doctorBoardContent", method = RequestMethod.GET)
	public ModelAndView doctorBoardContents(@RequestParam int question_table_idx, HttpSession session) throws Exception {
		ModelAndView list = new ModelAndView();
		list.setViewName("doctorBoardView/doctorBoardContent");
		bs.getDoctorBoardViews(question_table_idx, session);
		list.addObject("list", bs.getDoctorBoardContents(question_table_idx, session));
		
		int commentCounts = bs.getDoctorCommentCount(question_table_idx);		
		
		if(commentCounts>0) {
			list.addObject("commentList", bs.getDoctorBoardComments(question_table_idx));
			return list;
		}else {
			return list;
		}
		
	}

	// 게시판 검색
	@RequestMapping(value = "/view/doctorBoardSearch")
	public ModelAndView doctorBoardSearch(@RequestParam int searchn, String searchKeyword) {
		SearchBoardCommand sbc = new SearchBoardCommand();
		ModelAndView list = new ModelAndView();
		List<QuestionBoardDto> items = null;

		if (searchn == 0) {
			sbc.setSearch_option("member_id");
			sbc.setSearchKeyword(searchKeyword);
			items = bs.getDoctorBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("redirect:view/doctorBoardView/doctorBoard");

		} else if (searchn == 1) {
			sbc.setSearch_option("title");
			sbc.setSearchKeyword(searchKeyword);
			items = bs.getDoctorBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("redirect:view/doctorBoardView/doctorBoard");

		} else if (searchn == 2) {
			sbc.setSearch_option("content");
			sbc.setSearchKeyword(searchKeyword);
			items = bs.getDoctorBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("redirect:view/doctorBoardView/doctorBoard");
		}
		return list;
	}

	//게시판 글쓰기
	@RequestMapping(value="/view/doctorBoardView/doctorWriteForm")
	public ModelAndView toWriteForm() throws Exception {
		ModelAndView write = new ModelAndView();
		List<QuestionBoardDto> getSpecs = bs.getSpeciesForDoctor();
		
		if(getSpecs==null) {
			write.setViewName("doctorBoardView/doctorWriteForm");
			return write;
		}else {
			write.addObject("specs", getSpecs);
			write.setViewName("doctorBoardView/doctorWriteForm");
			System.out.println(write);
			return write;
		}
	}

	@RequestMapping(value="/view/doctorBoardView/doctorBoardWriteAdd", method=RequestMethod.POST)
	public String writeDoctorBoardArticle(QuestionBoardDto boardDto) throws Exception {
		int result = bs.addDoctorArticles(boardDto);
		System.out.println(boardDto);
		if(result>0) {
			return "redirect:/view/doctorBoardView/doctorBoard";
		}else {
			return "redirect:/view/doctorBoardView/doctorBoard";
		}
	}
	
	// 게시판 글수정
	@RequestMapping(value="/view/doctorBoardView/doctorBoardUpdateForm")
	public ModelAndView toDoctorUpdate(@RequestParam int question_table_idx) throws Exception {
		ModelAndView update = new ModelAndView();
		List<QuestionBoardDto> getSpecs = bs.getSpeciesForDoctor();
		int idx = question_table_idx;

		if (getSpecs == null) {
			update.setViewName("doctorBoardView/doctorBoardUpdateForm");
			return update;
		} else {
			update.addObject("specs", getSpecs);
			update.addObject("idx", idx);
			update.setViewName("doctorBoardView/doctorBoardUpdateForm");
			return update;
		}
	}

	@RequestMapping(value = "/view/doctorBoardView/doctorBoardUpdateAdd", method = RequestMethod.POST)
	public String updateDoctorArticle(QuestionBoardDto boardDto) throws Exception {
		int result = bs.updateDoctorArticle(boardDto);
		if (result > 0) {
			return "redirect:/view/doctorBoardView/doctorBoard";
		} else {
			return "redirect:/view/doctorBoardView/doctorBoard";
		}
	}

	
	//게시판 글삭제
		@RequestMapping(value="/view/doctorBoardView/deleteArticle")
		public String deleteDoctorArticle(@RequestParam int question_table_idx) {
			int idx = question_table_idx;
			int result = bs.deleteDoctorArticle(idx);
			if(result>0) {
			return "redirect:/view/doctorBoardView/doctorBoard";
			}else {
				System.out.println("no!!!");
			return "redirect:/view/doctorBoardView/doctorBoard";
			}
		}

//		===================================================================================================================
		
	// comment 작성
		@RequestMapping(value="/view/doctorBoardView/doctorCommentAdd")
		public String writeDoctorComment(BoardCommentDto commentDto) throws Exception {
			int result = bs.addDoctorComment(commentDto);
			if (result > 0) {
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx='${list.question_table_idx}'";
			} else {
				System.out.println("no!!!");
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx='${list.question_table_idx}'";
			}
		}

			
	// comment 글수정
		@RequestMapping(value = "/view/doctorBoardView/doctorCommentUpdate", method = RequestMethod.POST)
		public String updateDoctorComment(BoardCommentDto commentDto) throws Exception {
			int result = bs.updateDoctorComment(commentDto);
			if (result > 0) {
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx='${list.question_table_idx}'";
			} else {
				System.out.println("no!!!");
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx='${list.question_table_idx}'";
			}
		}
			
			
	// comment 글삭제
		@RequestMapping(value="/view/doctorBoardView/doctorCommentDelete")
		public String deleteDoctorComment(@RequestParam int question_board_comment_idx) {
			int idx = question_board_comment_idx;
			int result = bs.deleteDoctorComment(idx);
			if(result>0) {
			return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx='${list.question_table_idx}'";
			}else {
				System.out.println("no!!!");
			return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx='${list.question_table_idx}'";
			}
		}
}
