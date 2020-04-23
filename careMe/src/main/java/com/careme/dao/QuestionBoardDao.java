package com.careme.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.command.TagCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.BoardFileDto;
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
	
	public int getTotalDoctor() {
		return getSqlSession().selectOne("doctorQuestionBrd.selectTotal");
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx){
		return getSqlSession().selectOne("doctorQuestionBrd.getArtContent", question_table_idx);
	}
	
	public void getDoctorBoardViews(int question_table_idx) {
		getSqlSession().update("doctorQuestionBrd.getArtView", question_table_idx);
	}
	
	public List<BoardFileDto> getDoctorBoardFiles(int question_table_idx) {
		return getSqlSession().selectList("doctorQuestionBrd.getArtFiles", question_table_idx);
	}
	
	public List<QuestionBoardDto> getDoctorBoardSearch(SearchBoardCommand sbc){
		return getSqlSession().selectList("doctorQuestionBrd.getSrchArticle", sbc);
	}

	
	
// Doctor Board 작성, 수정, 삭제
	
	public int insertArticleForDoctor(QuestionBoardDto dto){
		return getSqlSession().insert("doctorQuestionBrd.insertArt", dto);
	}
	
	public int insertFileForDoctor(BoardFileDto dto) {
		return getSqlSession().insert("doctorQuestionBrd.insertFile", dto);
	}
	
	public int updateArticlesForDoctor(QuestionBoardDto dto) {
		return getSqlSession().update("doctorQuestionBrd.updateArticle", dto);
	}
	
	public int deleteArticlesForDoctor(int idx) {
		return getSqlSession().delete("doctorQuestionBrd.deleteArticle", idx);
		}
	
// Doctor Comment 작성, 수정, 삭제, 추천

	public int insertCommentForDoctor(BoardCommentDto commentDto){
		return getSqlSession().insert("doctorQuestionBrd.insertComment", commentDto);
	}
				
	public int updateCommentForDoctor(BoardCommentDto commentDto) {
		return getSqlSession().update("doctorQuestionBrd.updateComment", commentDto);
	}
				
	public int deleteCommentForDoctor(int idx) {
		return getSqlSession().delete("doctorQuestionBrd.deleteComment", idx);
	}
	
	public List<BoardCommentDto> getDoctorBoardComments(int question_table_idx){
		return getSqlSession().selectList("doctorQuestionBrd.getArtComments", question_table_idx);
	}
	
	public BoardCommentDto getDoctorComment(int question_board_comment_idx){
		return getSqlSession().selectOne("doctorQuestionBrd.getArtCommentsIdx", question_board_comment_idx);
	}
	
	public BoardCommentDto getHeartInfoDoctor(int question_board_comment_idx) {
		return getSqlSession().selectOne("doctorQuestionBrd.getHeartInfo", question_board_comment_idx);
	}
	
// Heart 추천
	
	public int addHeartForDoctor(int idx) {
		return getSqlSession().update("doctorQuestionBrd.addHeart", idx);
	}
		
	public int subHeartForDoctor(int idx) {
		return getSqlSession().update("doctorQuestionBrd.subHeart", idx);
	}
	
	public int updateDoctorCheckHeart(BoardCommentDto cdto) {
		return getSqlSession().update("doctorQuestionBrd.updateCheckHeart", cdto);
	}
	
	
// Hashtag 확인
	
	public List<TagDto> getHashtagDoctor(String tagValue){
		return getSqlSession().selectList("doctorQuestionBrd.hashtagFind", tagValue);
	}
		
	public List<TagDto> addHashtagDoctor(TagCommand tc) {
		return getSqlSession().selectList("doctorQuestionBrd.hashtagAdd", tc);
	}

		
// Page Numbering
		
	public List<PageNumberCommand> getPagesDoctor(){
		return getSqlSession().selectList("doctorQuestionBrd.contentCount");
	}
		
	public List<QuestionBoardDto> getContentsDoctor(){
		return getSqlSession().selectList("doctorQuestionBrd.contentDivide");
	}

	
/////////////////////////////////////////////////////////////////////////////////////////
	
	
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
	
	public List<BoardFileDto> getCasualBoardFiles(int question_table_idx) {
		return getSqlSession().selectList("casualQuestionBrd.getArtFiles", question_table_idx);
	}
	
	public void getCasualBoardViews(int question_table_idx) {
		getSqlSession().update("casualQuestionBrd.getArtView", question_table_idx);
	}

	public List<QuestionBoardDto> getCasualBoardSearch(SearchBoardCommand sbc){
		return getSqlSession().selectList("casualQuestionBrd.getSrchArticle", sbc);
	}
	
	
// Casual Board 작성, 수정, 삭제
	
	public int insertArticleForCasual(QuestionBoardDto dto){
		return getSqlSession().insert("casualQuestionBrd.insertArt", dto);
	
	}
	
	public int insertFileForCasual(BoardFileDto dto) {
		return getSqlSession().insert("casualQuestionBrd.insertFile", dto);
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
	
	public List<BoardCommentDto> getCasualBoardComments(int question_table_idx){
		return getSqlSession().selectList("casualQuestionBrd.getArtComments", question_table_idx);
	}
	
	public BoardCommentDto getCasualComment(int question_board_comment_idx){
		return getSqlSession().selectOne("casualQuestionBrd.getArtCommentsIdx", question_board_comment_idx);
	}
	
	public BoardCommentDto getHeartInfo(int question_board_comment_idx) {
		return getSqlSession().selectOne("casualQuestionBrd.getHeartInfo", question_board_comment_idx);
	}

	
// Heart 추천

	public int addHeartForCasual(int question_board_comment_idx) {
		return getSqlSession().update("casualQuestionBrd.addHeart", question_board_comment_idx);
	}
	
	public int subHeartForCasual(int question_board_comment_idx) {
		return getSqlSession().update("casualQuestionBrd.subHeart", question_board_comment_idx);
	}
	
	public int updateCheckHeart(BoardCommentDto cdto) {
		return getSqlSession().update("casualQuestionBrd.updateCheckHeart", cdto);
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
		return getSqlSession().selectList("casualQuestionBrd.contentCount");
	}
	
	public List<QuestionBoardDto> getContents(){
		return getSqlSession().selectList("casualQuestionBrd.contentDivide");
	}
	
// Tag 가져오기
	
	public List<TagDto> getTagContent(int board_idx){
		return getSqlSession().selectList("doctorQuestionBrd.getTagsDoctor", board_idx);
	}
	
// 회원 작성글 가져오기(최근 순)
	public List<QuestionBoardDto> getMemberDoctorBoard (Map<String,Integer>param){
		return getSqlSession().selectList("doctorQuestionBrd.getMemberDoctorBrd", param);
	}
	
	public List<QuestionBoardDto> getMemberCasualBoard (Map<String,Integer>param){
		return getSqlSession().selectList("casualQuestionBrd.getMemberCasualBrd", param);
	}
		
}
