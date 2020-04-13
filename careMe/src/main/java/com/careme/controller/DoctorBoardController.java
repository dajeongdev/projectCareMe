package com.careme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.careme.dao.QuestionBoardDao;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.PetSpeciesDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.service.PetService;
import com.careme.service.QuestionBoardService;
import com.google.gson.Gson;

@Controller
public class DoctorBoardController {

	@Autowired
	QuestionBoardService bs;
	
	@Autowired
	QuestionBoardDao boardDao;
	
	@Autowired
	PetService petService;
	
	public void setPetService(PetService petService) {
		this.petService = petService;
	}

//게시판 뿌리기(게시글 / 댓글 / 글개수)
	@RequestMapping(value = "/view/doctorBoardView/doctorBoard")
	public ModelAndView toDoctorBoard() {
		List<QuestionBoardDto> getArts = bs.getDoctorBoard();
		ModelAndView listPro = new ModelAndView();
		listPro.addObject("listPro", getArts);
		listPro.addObject("countPro", getArts.size());
		listPro.setViewName("/doctorBoardView/doctorBoard");
		return listPro;
	}

//게시글 내용 불러오기
	@RequestMapping(value = "/view/doctorBoardView/doctorBoardContent", method = RequestMethod.GET)
	public ModelAndView doctorBoardContents(@RequestParam int question_table_idx) throws Exception {
		ModelAndView mav = new ModelAndView();
		bs.getDoctorBoardViews(question_table_idx);
		QuestionBoardDto mlist=bs.getDoctorBoardContents(question_table_idx);
		List<BoardCommentDto> clist = bs.getDoctorBoardComments(question_table_idx);
		int commentCount = clist.size();
		mav.addObject("mlist", mlist);
		mav.addObject("clist", clist);
		mav.addObject("commCount", commentCount);
		mav.setViewName("doctorBoardView/doctorBoardContent");
		return mav;
	}

	
// 게시판 검색기능
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

// 게시글 작성
	@RequestMapping(value="/view/doctorBoardView/doctorWriteForm")
	public ModelAndView toWriteForm() throws Exception {
		ModelAndView write = new ModelAndView("doctorBoardView/doctorWriteForm");
		write.addObject("speciesOption", petService.selectPetSpeciesLevel1());
		return write;
	}
	
	@RequestMapping(value="/view/doctorBoardView/doctorWriteForm/pet_species_idx", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getDoctorPetSpeciesList(int level, int ancestor) {
		List<PetSpeciesDto> items = null;
		
		if(level==1) items = petService.selectPetSpeciesLevel1();
		else if(level==2) items = petService.selectPetSpeciesLevel2(ancestor);
		
		Gson json = new Gson();
		return json.toJson(items);
	}
	
	
	
	@RequestMapping(value="/view/doctorBoardView/doctorBoardWriteAdd", method=RequestMethod.POST)
	public String writeDoctorBoardArticle(QuestionBoardDto boardDto) throws Exception {
		int result = bs.addDoctorArticles(boardDto);
		if(result>0) {
			return "redirect:/view/doctorBoardView/doctorBoard";
		}else {
			return "redirect:/view/doctorBoardView/doctorBoard";
		}
	}
	
// 게시글 수정
	@RequestMapping(value="/view/doctorBoardView/doctorBoardUpdateForm")
	public ModelAndView toDoctorUpdate(@RequestParam int question_table_idx) throws Exception {
		ModelAndView update = new ModelAndView("doctorBoardView/doctorBoardUpdateForm");
		int idx = question_table_idx;
			update.addObject("speciesOption", petService.selectPetSpeciesLevel1());
			update.addObject("idx", idx);
			return update;
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

	
// 게시글 삭제
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
