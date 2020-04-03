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
	
	public List<QuestionBoardDto> getArticles(){
		return dao.getArticle();
	}
	
	public List<QuestionBoardDto> getArticlesPro(){
		return dao.getArticlePro();
	}
	
	public QuestionBoardDto getArtContents(int question_table_idx, HttpSession session){
		return dao.getArtContents(question_table_idx);
	}

	public void getArtViews (int question_table_idx, HttpSession session) {
		dao.getArtViews(question_table_idx);
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
