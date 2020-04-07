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
public class DoctorBoardController {
	
	@Autowired
	QuestionBoardService bs;
	QuestionBoardDao boardDao;
	
		
	//�Խ��� �Ѹ���
		@RequestMapping(value="/view/doctorBoardView/doctorBoard")
		public ModelAndView toDoctorBoard() {
			List<QuestionBoardDto> getArts = bs.getDoctorBoard();
			ModelAndView listPro = new ModelAndView();
			listPro.addObject("listPro", getArts);
			listPro.addObject("countPro", getArts.size());
			listPro.setViewName("/doctorBoardView/doctorBoard");
			return listPro;
		}
		
	//�Խñ� ���� �ҷ�����
		@RequestMapping(value="/view/doctorBoardView/doctorBoardContent", method=RequestMethod.GET)
		public ModelAndView doctorBoardContents(@RequestParam int question_table_idx, HttpSession session) throws Exception{
			ModelAndView list = new ModelAndView();
			list.addObject("list", bs.getDoctorBoardContents(question_table_idx, session));
			bs.getDoctorBoardViews(question_table_idx, session);
			list.setViewName("doctorBoardContent");
			return list;
		}
		
		// �Խ��� �˻�
		@RequestMapping(value="/view/doctorBoardSearch")
		public ModelAndView doctorBoardSearch(@RequestParam int searchn, String searchKeyword) {
			SearchBoardCommand sbc = new SearchBoardCommand();
			ModelAndView list = new ModelAndView();
			List<QuestionBoardDto> items = null;
			
			if (searchn==0) {
				sbc.setSearch_option("member_id");
				sbc.setSearchKeyword(searchKeyword);			
				items=bs.getDoctorBoardSearch(sbc);
				list.addObject("list", items);
				list.addObject("count", items.size());
				list.setViewName("redirect:view/doctorBoardView/doctorBoard");
				
			}else if(searchn==1) {
				sbc.setSearch_option("title");
				sbc.setSearchKeyword(searchKeyword);
				items=bs.getDoctorBoardSearch(sbc);
				list.addObject("list", items);
				list.addObject("count", items.size());
				list.setViewName("redirect:view/doctorBoardView/doctorBoard");
				
			}else if(searchn==2) {
				sbc.setSearch_option("content");
				sbc.setSearchKeyword(searchKeyword);
				items=bs.getDoctorBoardSearch(sbc);
				list.addObject("list", items);
				list.addObject("count", items.size());
				list.setViewName("redirect:view/doctorBoardView/doctorBoard");
			}
			return list;
		}
		
	//�Խ��� �۾���	
//		@RequestMapping(value="/view/writeForm")
//		public String toWrite() throws Exception {
//			return "writeForm";
//		}
		
//		@RequestMapping(value="/view/write")
//		public int writeArticle(@RequestParam String title, String content, String member_id, HttpSession session) {
//			int result = bs.addArticles(title, content, member_id);
//			return result;
//		}
		
	//�Խ��� �ۼ���
//		@RequestMapping(value="/view/update")
//		public void updateArticle(@RequestParam String title, String content, HttpSession session) {
//			bs.updateArticle(title, content);
//			}
		
	//�Խ��� �ۻ���
//		@RequestMapping(value="/view/delete")
//		public void deleteArticle() {
//			bs.deleteArticle();
//			}
		


}
