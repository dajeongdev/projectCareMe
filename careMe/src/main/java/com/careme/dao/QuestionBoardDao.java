package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.QuestionBoardDto;

public class QuestionBoardDao extends SqlSessionDaoSupport {

	
// Doctor Board 내용 및 검색
	
	public List<QuestionBoardDto> getDoctorBoard(){
		return getSqlSession().selectList("doctorQuestionBrd.getArt");
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx){
		return getSqlSession().selectOne("doctorQuestionBrd.getArtContent", question_table_idx);
	}
	
	public void getDoctorBoardViews(int question_table_idx) {
		getSqlSession().update("doctorQuestionBrd.getArtView", question_table_idx);
	}
	
	public List<QuestionBoardDto> getDoctorBoardSearch(SearchBoardCommand sbc){
		return getSqlSession().selectList("doctorQuestionBrd.getSrchArticle");
	}
	
	public List<BoardCommentDto> getDoctorBoardComments(int question_table_idx){
		return getSqlSession().selectList("doctorQuestionBrd.getArtComments", question_table_idx);
	}
	

	
// Doctor Board 작성, 수정, 삭제
	
	public int insertArticleForDoctor(QuestionBoardDto boardDto){
		return getSqlSession().insert("doctorQuestionBrd.insertArt", boardDto);
		}
	
	public int updateArticlesForDoctor(QuestionBoardDto boardDto) {
		return getSqlSession().update("doctorQuestionBrd.updateArticle", boardDto);
	}
	
	public int deleteArticlesForDoctor(int idx) {
		return getSqlSession().delete("doctorQuestionBrd.deleteArticle", idx);
		}
	
// Doctor Comment 작성, 수정, 삭제

	public int insertCommentForDoctor(BoardCommentDto commentDto){
		return getSqlSession().insert("doctorQuestionBrd.insertComment", commentDto);
	}
				
	public int updateCommentForDoctor(BoardCommentDto commentDto) {
		return getSqlSession().update("doctorQuestionBrd.updateComment", commentDto);
	}
				
	public int deleteCommentForDoctor(int idx) {
		return getSqlSession().delete("doctorQuestionBrd.deleteComment", idx);
	}
	
	
	
// Casual Board 내용 및 검색
	
	public List<QuestionBoardDto> getCasualBoard(){
		return getSqlSession().selectList("casualQuestionBrd.getArt");
	}
	
	public QuestionBoardDto getCasualBoardContents(int question_table_idx){
		return getSqlSession().selectOne("casualQuestionBrd.getArtContent", question_table_idx);
	}
	
	public void getCasualBoardViews(int question_table_idx) {
		getSqlSession().update("casualQuestionBrd.getArtView", question_table_idx);
	}

	public List<QuestionBoardDto> getCasualBoardSearch(SearchBoardCommand sbc){
		return getSqlSession().selectList("casualQuestionBrd.getSrchArticle");
	}
	
	public List<BoardCommentDto> getCasualBoardComments(int question_table_idx){
		return getSqlSession().selectList("casualQuestionBrd.getArtComments", question_table_idx);
	}
	

// Casual Board 작성, 수정, 삭제
	
	public int insertArticleForCasual(QuestionBoardDto boardDto){
		return getSqlSession().insert("casualQuestionBrd.insertArt", boardDto);
	}
		
	public int updateArticlesForCasual(QuestionBoardDto boardDto) {
		return getSqlSession().update("casualQuestionBrd.updateArticle", boardDto);
	}
		
	public int deleteArticlesForCasual(int idx) {
		return getSqlSession().delete("casualQuestionBrd.deleteArticle", idx);
	}

// Casual Comment 작성, 수정, 삭제

	public int insertCommentForCasual(BoardCommentDto commentDto){
		return getSqlSession().insert("casualQuestionBrd.insertComment", commentDto);
	}
			
	public int updateCommentForCasual(BoardCommentDto commentDto) {
		return getSqlSession().update("casualQuestionBrd.updateComment", commentDto);
	}
			
	public int deleteCommentForCasual(int idx) {
		return getSqlSession().delete("casualQuestionBrd.deleteComment", idx);
	}
	
		
		
}
