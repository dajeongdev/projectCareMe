package com.careme.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.command.TagCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.model.dto.TagDto;

public class QuestionBoardDao extends SqlSessionDaoSupport {

	
// Doctor Board 내용 및 검색
	
	public List<QuestionBoardDto> getDoctorBoard(){
		return getSqlSession().selectList("doctorQuestionBrd.getTotal");
	}
	
	public List<QuestionBoardDto> getDoctorBoardList(Map<String,Integer>param){
		return getSqlSession().selectList("doctorQuestionBrd.getArticles", param);
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
	
	public int insertArticleForDoctor(QuestionBoardDto dto){
		return getSqlSession().insert("doctorQuestionBrd.insertArt", dto);
	}
	
	public int insertFileforDoctor(QuestionBoardDto dto) {
		return getSqlSession().insert("doctorQuestionBrd.insertArtFile", dto);
	}
	
	public int updateArticlesForDoctor(QuestionBoardDto dto) {
		return getSqlSession().update("doctorQuestionBrd.updateArticle", dto);
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
		return getSqlSession().selectList("casualQuestionBrd.getTotal");
	}
	
	public List<QuestionBoardDto> getCasualBoardList(Map<String,Integer>param){
		return getSqlSession().selectList("casualQuestionBrd.getArticles", param);
	}
	
	public int getTotal() {
		return getSqlSession().selectOne("casualQuestionBrd.selectTotal");
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
	
	public int insertArticleForCasual(QuestionBoardDto dto){
		int idx = getSqlSession().insert("casualQuestionBrd.insertArt", dto);
		return idx;
	}
	
	public int insertArtFileForCasual(QuestionBoardDto dto) {
		return getSqlSession().insert("casualQuestionBrd.insertArtFile", dto);
	}
		
	public int updateArticlesForCasual(QuestionBoardDto dto) {
		return getSqlSession().update("casualQuestionBrd.updateArticle", dto);
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
	
// Hashtag 확인
	
	public List<TagDto> getHashtag(String tagValue){
		return getSqlSession().selectList("casualQuestionBrd.hashtagFind", tagValue);
	}
	
	public List<TagDto> addHashtag(TagCommand tc) {
		return getSqlSession().selectList("casualQuestionBrd.hashtagAdd", tc);
	}

// Page Numbering
	
	public List<PageNumberCommand> getPages(){
		return getSqlSession().selectList("causalQuestionBrd.contentCount");
	}
	
	public List<QuestionBoardDto> getContents(){
		return getSqlSession().selectList("casualQuestionBrd.contentDivide");
	}
		
}
