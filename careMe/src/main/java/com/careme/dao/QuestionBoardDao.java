package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.dto.QuestionBoardDto;

public class QuestionBoardDao extends SqlSessionDaoSupport {

	public List<QuestionBoardDto> getArticle(){
		return getSqlSession().selectList("doctorQuestionBrd.getArt");
	}
	
	public List<QuestionBoardDto> getArticlePro(){
		return getSqlSession().selectList("doctorQuestionBrd.getArtPro");
	}
	
	public QuestionBoardDto getArtContents(int question_table_idx){
		return getSqlSession().selectOne("doctorQuestionBrd.getArtContent", question_table_idx);
	}
	
	public void getArtViews(int question_table_idx) {
		getSqlSession().update("doctorQuestionBrd.getArtView", question_table_idx);
	}
	
}
