package com.careme.service;

import java.util.List;
import java.util.Map;

import com.careme.model.command.SearchBoardCommand;
import com.careme.model.command.TagCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.model.dto.TagDto;

public interface QuestionBoardService {

// Doctor Board 게시글 뿌리기
	public List<QuestionBoardDto> getDoctorBoard();
	
	public List<QuestionBoardDto> getDoctorBoardPage(Map<String,Integer>param);
	
	public int getTotal();
	
	public QuestionBoardDto getDoctorBoardContents (int question_table_idx);
	
	public void getDoctorBoardViews (int question_table_idx);
	
	public List<QuestionBoardDto> getDoctorBoardSearch (SearchBoardCommand sbc);
	
	public List<BoardCommentDto> getDoctorBoardComments (int question_table_idx);
	
	
// Doctor Board 작성, 수정, 삭제
	public int addDoctorArticles (QuestionBoardDto dto);
	
	public int updateDoctorArticle (QuestionBoardDto dto);
	
	public int deleteDoctorArticle (int idx);
	
	
// Doctor Board Comments 작성, 수정, 삭제
	public int addDoctorComment (BoardCommentDto commentDto);
	
	public int updateDoctorComment (BoardCommentDto commentDto);
	
	public int deleteDoctorComment (int idx);
	
	
// Casual Board 내용 구현
	public List<QuestionBoardDto> getCasualBoard();
	
	public List<QuestionBoardDto> getCasualBoardPage(Map<String,Integer>param);
	
	public QuestionBoardDto getCasualBoardContents (int question_table_idx);
	
	public void getCasualBoardViews (int question_table_idx);
	
	public List<QuestionBoardDto> getCasualBoardSearch (SearchBoardCommand sbc);
	
	public List<BoardCommentDto> getCasualBoardComments (int question_table_idx);
	
	
// Casual Board 작성, 수정, 삭제
	public int addCasualArticles (QuestionBoardDto dto);
	
	public int addArtFileForCasual (QuestionBoardDto dto);
	
	public int updateCasualArticle (QuestionBoardDto dto);
	
	public int deleteCasualArticle (int idx);
	
// Casual Board Comments 작성, 수정, 삭제
	public int addCasualComment (BoardCommentDto commentDto);
	
	public int updateCasualComment (BoardCommentDto commentDto);
	
	public int deleteCasualComment (int idx);
	
// Casual Board Hashtags
	public List<TagDto> compareHashtag(String tagValue);
	
	public List<TagDto> addHashtag(TagCommand tc);
	
}

