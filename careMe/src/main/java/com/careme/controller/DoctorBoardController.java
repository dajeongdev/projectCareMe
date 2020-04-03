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
public class DoctorBoardController {
	
	@Autowired
	QuestionBoardService bs;
	QuestionBoardDao boardDao;
	
	//main 불러오기
	
//		@RequestMapping(value="main")
//		public ModelAndView toMain() {
//			ModelAndView mav = new ModelAndView();
//			mav.setViewName("main");
//			return mav;
//		}
		
	//게시판 뿌리기
		@RequestMapping(value="/view/doctorBoardView/doctorBoard")
		public ModelAndView toBoardPro() {
			List<QuestionBoardDto> getArts = bs.getArticlesPro();
			ModelAndView listPro = new ModelAndView();
			listPro.addObject("listPro", getArts);
			listPro.addObject("countPro", getArts.size());
			listPro.setViewName("/doctorBoardView/doctorBoard");
			return listPro;
		}
		
	//게시글 내용 불러오기
		@RequestMapping(value="/doctorBoardView/doctorBoardContentPro", method=RequestMethod.GET)
		public ModelAndView contentsPro(@RequestParam int question_table_idx, HttpSession session) throws Exception{
			ModelAndView list = new ModelAndView();
			list.addObject("list", bs.getArtContents(question_table_idx, session));
			bs.getArtViews(question_table_idx, session);
			list.setViewName("contentPro");
			return list;
		}
		
	//게시판 글쓰기	
//		@RequestMapping(value="/view/writeForm")
//		public String toWrite() throws Exception {
//			return "writeForm";
//		}
		
//		@RequestMapping(value="/view/write")
//		public int writeArticle(@RequestParam String title, String content, String member_id, HttpSession session) {
//			int result = bs.addArticles(title, content, member_id);
//			return result;
//		}
		
	//게시판 글수정
//		@RequestMapping(value="/view/update")
//		public void updateArticle(@RequestParam String title, String content, HttpSession session) {
//			bs.updateArticle(title, content);
//			}
		
	//게시판 글삭제
//		@RequestMapping(value="/view/delete")
//		public void deleteArticle() {
//			bs.deleteArticle();
//			}
		
	//게시글 검색
//		@RequestMapping(value="/view/searchPro")
//		public ModelAndView searchListPro(@RequestParam int searchn, String searchKeyword, HttpSession session) {
//			SearchCommand sc = new SearchCommand();
//			ModelAndView listPro = new ModelAndView();
//			List<BoardDto> items = null;
			
//			if (searchn==0) {
				
//				sc.search_option="member_id";
//				sc.searchKeyword=searchKeyword;			
//				items=bs.getArtSearch(sc);
//				listPro.addObject("listPro", items);
//				listPro.addObject("countPro", items.size());
//				listPro.setViewName("listPro");
				
//			}else if(searchn==1) {
				
//				sc.search_option="title";
//				sc.searchKeyword=searchKeyword;
//				items=bs.getArtSearch(sc);
//				listPro.addObject("listPro", items);
//				listPro.addObject("countPro", items.size());
//				listPro.setViewName("listPro");
				
//			}else if(searchn==2) {
				
//				sc.search_option="content";
//				sc.searchKeyword=searchKeyword;
//				items=bs.getArtSearch(sc);
//				listPro.addObject("listPro", items);
//				listPro.addObject("countPro", items.size());
//				listPro.setViewName("listPro");
				
//			}
//			return listPro;
//		}

}
