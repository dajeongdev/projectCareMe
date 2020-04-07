package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.QuestionBoardDto;

public class QuestionBoardDao extends SqlSessionDaoSupport {

	
// Doctor Board 가져오기	
	
	public List<QuestionBoardDto> getDoctorBoard(){
		return getSqlSession().selectList("doctorQuestionBrd.getArtPro");
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx){
		return getSqlSession().selectOne("doctorQuestionBrd.getArtContent", question_table_idx);
	}
	
	public void getDoctorBoardViews(int question_table_idx) {
		getSqlSession().update("doctorQuestionBrd.getArtView", question_table_idx);
	}
	
	public List<QuestionBoardDto> getDoctorBoardSearch(SearchBoardCommand sbc){
		getSqlSession().selectList("doctorQuestionBrd.get")
	}
	
	
// Casual Board 가져오기
	public List<QuestionBoardDto> getCasualBoard(){
		return getSqlSession().selectList("casualQuestionBrd.getArt");
	}
	
	public QuestionBoardDto getCasualBoardContents(int question_table_idx){
		return getSqlSession().selectOne("casualQuestionBrd.getArtContent", question_table_idx);
	}
	
	public void getCasualBoardViews(int question_table_idx) {
		getSqlSession().update("casualQuestionBrd.getArtView", question_table_idx);
	}
	
	
	
}
