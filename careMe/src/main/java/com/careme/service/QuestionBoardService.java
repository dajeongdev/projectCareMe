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
	
	public List<QuestionBoardDto>getDoctorBoardSearch(SearchBoardCommand sbc){
		return dao.getDoctorBoardSearch(sbc);
	}

// Doctor Board 작성, 수정, 삭제 기능
	
	// 작성
		public List<QuestionBoardDto> getSpeciesForDoctor(){
			return dao.getSpeciesForDoctor();
		}
		
		public int addDoctorArticles(QuestionBoardDto boardDto) {
			boardDto.setReg_date(LocalDateTime.now());
			return dao.insertArticleForDoctor(boardDto);
		}
	
	// 수정
	
		public int updateDoctorArticle(QuestionBoardDto boardDto) {
			boardDto.setUpdate_date(LocalDateTime.now());
			return dao.updateArticlesForDoctor(boardDto);
		}
		
	// 삭제	
		public int deleteDoctorArticle(int idx) {
			return dao.deleteArticlesForDoctor(idx);
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
	
	public List<QuestionBoardDto>getCasualBoardSearch(SearchBoardCommand sbc){
		return dao.getCasualBoardSearch(sbc);
	}

	// Casual Board 작성, 수정, 삭제 기능
	
		// 작성
			public List<QuestionBoardDto> getSpeciesForCasual(){
				return dao.getSpeciesForCasual();
			}
			
			public int addCasualArticles(QuestionBoardDto boardDto) {
				boardDto.setReg_date(LocalDateTime.now());
				return dao.insertArticleForCasual(boardDto);
			}
		
		// 수정
		
			public int updateCasualArticle(QuestionBoardDto boardDto) {
				boardDto.setUpdate_date(LocalDateTime.now());
				return dao.updateArticlesForCasual(boardDto);
			}
			
		// 삭제	
			public int deleteCasualArticle(int idx) {
				return dao.deleteArticlesForCasual(idx);
			}
}