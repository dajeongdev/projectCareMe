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
import com.careme.model.dto.QuestionBoardDto;
import com.careme.service.QuestionBoardService;

@Controller
public class CasualBoardController {

	@Autowired
	QuestionBoardService bs;
	QuestionBoardDao boardDao;

	
//�Խ��� �Ѹ���(�Խñ� / �۰���)
	@RequestMapping(value="/view/casualBoardView/casualBoard")
	public ModelAndView toCasualBoard() {
		List<QuestionBoardDto> getArts = bs.getCasualBoard();
		ModelAndView list = new ModelAndView();
		list.addObject("list", getArts);
		list.addObject("count", getArts.size());
		list.setViewName("/casualBoardView/casualBoard");
		return list;
	}

//�Խñ� ���� �ҷ�����
	@RequestMapping(value="/casualBoardView/casualBoardContent", method=RequestMethod.GET)
	public ModelAndView contents(@RequestParam int question_table_idx, HttpSession session) throws Exception{
		ModelAndView list = new ModelAndView();
		list.addObject("list", bs.getCasualBoardContents(question_table_idx, session));
		bs.getCasualBoardViews(question_table_idx, session);
		list.setViewName("content");
		return list;
	}
	
//�Խ��� �˻�
//	@RequestMapping(value="/view/search")
//	public ModelAndView searchList(@RequestParam int searchn, String searchKeyword) {
//		SearchCommand sc = new SearchCommand();
//		ModelAndView list = new ModelAndView();
//		List<BoardDto> items = null;
		
//		if (searchn==0) {
			
//			sc.search_option="member_id";
//			sc.searchKeyword=searchKeyword;			
//			items=bs.getArtSearch(sc);
//			list.addObject("list", items);
//			list.addObject("count", items.size());
//			list.setViewName("list");
			
//		}else if(searchn==1) {
			
//			sc.search_option="title";
//			sc.searchKeyword=searchKeyword;
//			items=bs.getArtSearch(sc);
//			list.addObject("list", items);
//			list.addObject("count", items.size());
//			list.setViewName("list");
			
//		}else if(searchn==2) {
			
//			sc.search_option="content";
//			sc.searchKeyword=searchKeyword;
//			items=bs.getArtSearch(sc);
//			list.addObject("list", items);
//			list.addObject("count", items.size());
//			list.setViewName("list");
			
//		}
//		return list;
//	}
	
}
