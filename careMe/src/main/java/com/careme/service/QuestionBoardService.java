package com.careme.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.BoardFileDto;
import com.careme.model.dto.HeartDto;
import com.careme.model.dto.QuestionBoardDto;


public interface QuestionBoardService {
	
// 전문 상담 게시판 구현
	public List<QuestionBoardDto> getDoctorBoard();
	
	public List<QuestionBoardDto> getDoctorBoardPage(Map<String,Integer>param);
	
	public int getTotalDoctor();
	
	public QuestionBoardDto getDoctorBoardContents (int question_table_idx);
	
	public List<BoardFileDto> getDoctorBoardFiles (int question_table_idx);	
	
	public void getDoctorBoardViews (int question_table_idx);

	public List<QuestionBoardDto> getDoctorBoardSearch (SearchBoardCommand sbc);

	public BoardCommentDto getDoctorComment(int question_board_comment_idx);
	
	public List<BoardCommentDto> getDoctorBoardComments (int question_table_idx);
	
	
// Doctor Board 작성, 수정, 삭제
	public int addDoctorArticles (QuestionBoardDto dto, MultipartHttpServletRequest request);
	
	public void addFileForDoctor(int question_table_idx, MultipartHttpServletRequest request);
	
	public int updateDoctorArticle (QuestionBoardDto dto);
	
	public int deleteDoctorArticle (int idx);
	
	
// Doctor Board Comments 작성, 수정, 삭제
	public int addDoctorComment (BoardCommentDto commentDto);
	
	public int updateDoctorComment (BoardCommentDto commentDto);
	
	public int deleteDoctorComment (int idx);
	
	public int addHeartForDoctor(int idx);
	
	public int subHeartForDoctor(int idx);
	
	public void heartProcessDoctor(HeartDto hdto, int question_board_comment_idx);

	
	
// 고민 게시판 구현
	public List<QuestionBoardDto> getCasualBoard();
	
	public List<QuestionBoardDto> getCasualBoardPage(Map<String,Integer>param);
	
	public int getTotal();
	
	public QuestionBoardDto getCasualBoardContents (int question_table_idx);
	
	public List<BoardFileDto> getCasualBoardFiles (int question_table_idx);
	
	public void getCasualBoardViews (int question_table_idx);
	
	public List<QuestionBoardDto> getCasualBoardSearch (SearchBoardCommand sbc);
	
	public List<BoardCommentDto> getCasualBoardComments (int question_table_idx);
	
	public BoardCommentDto getCasualComment(int question_board_comment_idx);
	
	
// Casual Board 작성, 수정, 삭제
	public int addCasualArticles (QuestionBoardDto dto, MultipartHttpServletRequest request);
	
	public void addFileForCasual(int question_table_idx, MultipartHttpServletRequest request);
	
	public int updateCasualArticle (QuestionBoardDto dto);
	
	public int deleteCasualArticle (int idx);
	
	public List<QuestionBoardDto> getMemberDoctorBoard (int member_idx, Map<String,Integer>param);
	
	public List<QuestionBoardDto> getMemberCasualBoard (int member_idx, Map<String,Integer>param);

	
// Casual Board Comments 작성, 수정, 삭제
	public int addCasualComment (BoardCommentDto commentDto);
	
	public int updateCasualComment (BoardCommentDto commentDto);
	
	public int deleteCasualComment (int idx);
	
	public int addHeartForCasual(int idx);
	
	public int subHeartForCasual(int idx);
	
	public void heartProcess(HeartDto hdto, int question_board_comment_idx);	
	


// 공통	
	public SearchBoardCommand listSearchInfo (int searchn, String searchKeyword);	
	
}

