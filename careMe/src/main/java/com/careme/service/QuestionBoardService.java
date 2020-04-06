package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.QuestionBoardDao;
import com.careme.model.dto.QuestionBoardDto;

@Service
public class QuestionBoardService {
	
	@Autowired
	QuestionBoardDao dao = new QuestionBoardDao();

// Doctor Board 가져오기	
	public List<QuestionBoardDto> getDoctorBoard(){
		return dao.getDoctorBoard();
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx, HttpSession session){
		return dao.getDoctorBoardContents(question_table_idx);
	}

	public void getDoctorBoardViews (int question_table_idx, HttpSession session) {
		dao.getDoctorBoardViews(question_table_idx);
	}
	
// Casual Board 가져오기	
	public List<QuestionBoardDto> getCasualBoard(){
		return dao.getCasualBoard();
	}
	
	public QuestionBoardDto getCasualBoardContents(int question_table_idx, HttpSession session){
		return dao.getCasualBoardContents(question_table_idx);
	}

	public void getCasualBoardViews (int question_table_idx, HttpSession session) {
		dao.getCasualBoardViews(question_table_idx);
	}
	
	
	
//	public void updateArticle(String title, String content) {
//		dao.updateArticles(title, content);
//	}
	
//	public void deleteArticle() {
//		dao.deleteArticles();
//	}
	
//	public List<Question_BoardDto>getArtSearch(SearchCommand sc){
//		return dao.getArtSearch(sc);
//	}
	
//	public int getArtSearchCount() {
//		return dao.getArtSearchCount();
//	}

//	public int addArticles(String title, String content, String member_id) {//보드 디티오에 있는데 에스큐엘에는 없는 변수를 추가
//		return dao.insertArticle(title, content, member_id);
//	}

}
