package com.careme.service;

import java.util.List;

import com.careme.model.command.SearchBoardCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.QuestionBoardDto;


@Service
public class QuestionBoardService {
	
	@Autowired
	QuestionBoardDao dao = new QuestionBoardDao();

	public List<QuestionBoardDto> getDoctorBoard(){
		return dao.getDoctorBoard();
	}
	
	public QuestionBoardDto getDoctorBoardContents(int question_table_idx, HttpSession session){
		return dao.getDoctorBoardContents(question_table_idx);
	}

public interface QuestionBoardService {


// Doctor Board 게시글 뿌리기
	public List<QuestionBoardDto> getDoctorBoard();
	

	public List<QuestionBoardDto>getDoctorBoardSearch(SearchBoardCommand sbc){
		return dao.getDoctorBoardSearch(sbc);
	}

	public List<QuestionBoardDto> getSpeciesForDoctor(){
		return dao.getSpeciesForDoctor();
	}
		
	public int addDoctorArticles(QuestionBoardDto boardDto) {
		boardDto.setReg_date(LocalDateTime.now());
		return dao.insertArticleForDoctor(boardDto);
	}
	
	public int updateDoctorArticle(QuestionBoardDto boardDto) {
		boardDto.setUpdate_date(LocalDateTime.now());
		return dao.updateArticlesForDoctor(boardDto);
	}
		
	public int deleteDoctorArticle(int idx) {
		return dao.deleteArticlesForDoctor(idx);
	}
	
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

	public List<QuestionBoardDto> getSpeciesForCasual(){
		return dao.getSpeciesForCasual();
	}
			
	public int addCasualArticles(QuestionBoardDto boardDto) {
		boardDto.setReg_date(LocalDateTime.now());
		return dao.insertArticleForCasual(boardDto);
	}
		
	public int updateCasualArticle(QuestionBoardDto boardDto) {
		boardDto.setUpdate_date(LocalDateTime.now());
		return dao.updateArticlesForCasual(boardDto);
	}
			
	public int deleteCasualArticle(int idx) {
		return dao.deleteArticlesForCasual(idx);
	}
}
=======
	public QuestionBoardDto getDoctorBoardContents (int question_tbale_idx);
	
	public void getDoctorBoardViews (int question_table_idx);
	
	public List<QuestionBoardDto> getDoctorBoardSearch (SearchBoardCommand sbc);
	
	public List<BoardCommentDto> getDoctorBoardComments (int question_table_idx);
	
	
// Doctor Board 작성, 수정, 삭제
	public int addDoctorArticles (QuestionBoardDto boardDto);
	
	public int updateDoctorArticle (QuestionBoardDto boardDto);
	
	public int deleteDoctorArticle (int idx);
	
	
// Doctor Board Comments 작성, 수정, 삭제
	public int addDoctorComment (BoardCommentDto commentDto);
	
	public int updateDoctorComment (BoardCommentDto commentDto);
	
	public int deleteDoctorComment (int idx);
	
	
// Casual Board 내용 구현
	public List<QuestionBoardDto> getCasualBoard();
	
	public QuestionBoardDto getCasualBoardContents (int question_table_idx);
	
	public void getCasualBoardViews (int question_table_idx);
	
	public List<QuestionBoardDto> getCasualBoardSearch (SearchBoardCommand sbc);
	
	public List<BoardCommentDto> getCasualBoardComments (int question_table_idx);
	
	
// Casual Board 작성, 수정, 삭제
	public int addCasualArticles (QuestionBoardDto boardDto);
	
	public int updateCasualArticle (QuestionBoardDto boardDto);
	
	public int deleteCasualArticle (int idx);
	
// Casual Board Comments 작성, 수정, 삭제
	public int addCasualComment (BoardCommentDto commentDto);
	
	public int updateCasualComment (BoardCommentDto commentDto);
	
	public int deleteCasualComment (int idx);
	
	
}

