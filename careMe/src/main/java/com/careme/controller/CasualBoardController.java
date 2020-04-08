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
import com.careme.model.dto.QuestionBoardDto;
import com.careme.service.QuestionBoardService;

@Controller
public class CasualBoardController {

	@Autowired
	QuestionBoardService bs;
	QuestionBoardDao boardDao;

	
//게시판 뿌리기(게시글 / 글개수)
	@RequestMapping(value="/view/casualBoardView/casualBoard")
	public ModelAndView toCasualBoard() {
		List<QuestionBoardDto> getArts = bs.getCasualBoard();
		ModelAndView list = new ModelAndView();
		list.addObject("list", getArts);
		list.addObject("count", getArts.size());
		list.setViewName("/casualBoardView/casualBoard");
		return list;
	}

//게시글 내용 불러오기
	@RequestMapping(value="/view/casualBoardView/casualBoardContent", method=RequestMethod.GET)
	public ModelAndView contents(@RequestParam int question_table_idx, HttpSession session) throws Exception{
		ModelAndView list = new ModelAndView();
		list.addObject("list", bs.getCasualBoardContents(question_table_idx, session));
		bs.getCasualBoardViews(question_table_idx, session);
		list.setViewName("casualBoardView/casualBoardContent");
		return list;
	}
	
// 게시판 검색
	@RequestMapping(value="/view/casualBoardView/casualBoardSearch")
	public ModelAndView doctorBoardSearch(@RequestParam int searchn, String searchKeyword) {
		SearchBoardCommand sbc = new SearchBoardCommand();
		ModelAndView list = new ModelAndView();
		List<QuestionBoardDto> items = null;
		
		if (searchn==0) {
			
			sbc.setSearch_option("member_id");
			sbc.setSearchKeyword(searchKeyword);			
			items=bs.getCasualBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("list");
			
		}else if(searchn==1) {
			
			sbc.setSearch_option("title");
			sbc.setSearchKeyword(searchKeyword);
			items=bs.getCasualBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("list");
			
		}else if(searchn==2) {
			
			sbc.setSearch_option("content");
			sbc.setSearchKeyword(searchKeyword);
			items=bs.getCasualBoardSearch(sbc);
			list.addObject("list", items);
			list.addObject("count", items.size());
			list.setViewName("list");
			
		}
		return list;
	}
	
}
