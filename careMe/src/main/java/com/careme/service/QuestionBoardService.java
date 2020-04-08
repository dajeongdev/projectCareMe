package com.careme.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.QuestionBoardDao;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.QuestionBoardDto;

@Service
public class QuestionBoardService {
	
	@Autowired

	QuestionBoardDao dao = new QuestionBoardDao();

// Doctor Board °¡Á®¿À±â	
	public List<QuestionBoardDto> getDoctorBoard(){
		return dao.getDoctorBoard();
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx, HttpSession session){
		return dao.getDoctorBoardContents(question_table_idx);
	}

	public void getDoctorBoardViews (int question_table_idx, HttpSession session) {
		dao.getDoctorBoardViews(question_table_idx);
	}
	
	public List<QuestionBoardDto>getDoctorBoardSearch(SearchBoardCommand sbc){
		return dao.getDoctorBoardSearch(sbc);
	}

// Doctor Board ÀÛ¼º, ¼öÁ¤, »èÁ¦ ±â´É
	
	// ÀÛ¼º
		public List<QuestionBoardDto> getSpecies(){
			return dao.getSpecies();
		}
		
		public int addArticles(QuestionBoardDto boardDto) {
			boardDto.setReg_date(LocalDateTime.now());
			return dao.insertArticle(boardDto);
		}
	
	// ¼öÁ¤
	
		public int updateArticle(QuestionBoardDto boardDto) {
			boardDto.setUpdate_date(LocalDateTime.now());
			return dao.updateArticles(boardDto);
		}
		
	// »èÁ¦	
		public int deleteArticle(int idx) {
			return dao.deleteArticles(idx);
		}
	
	
	
// Casual Board °¡Á®¿À±â	
	public List<QuestionBoardDto> getCasualBoard(){
		return dao.getCasualBoard();
	}
	
	public QuestionBoardDto getCasualBoardContents(int question_table_idx, HttpSession session){
		return dao.getCasualBoardContents(question_table_idx);
	}

	public void getCasualBoardViews (int question_table_idx, HttpSession session) {
		dao.getCasualBoardViews(question_table_idx);
	}
	
	public List<QuestionBoardDto>getCasualBoardSearch(SearchBoardCommand sbc){
		return dao.getCasualBoardSearch(sbc);
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

//	public int addArticles(String title, String content, String member_id) {//ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Æ¼ï¿½ï¿½ï¿½ï¿½ ï¿½Ö´Âµï¿½ ï¿½ï¿½ï¿½ï¿½Å¥ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ß°ï¿½
//		return dao.insertArticle(title, content, member_id);
//	}

}
