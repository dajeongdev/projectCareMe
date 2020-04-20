package com.careme.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.QuestionBoardDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.command.TagCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.BoardFileDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.model.dto.TagDto;

@Service("QuestionBoardService")
public class QuestionBoardServiceImpl implements QuestionBoardService {
	@Autowired
	QuestionBoardDao dao = new QuestionBoardDao();

	public void setDao(QuestionBoardDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	QuestionBoardService bs;
	
	public void setQuestionBoardService(QuestionBoardService bs) {
		this.bs = bs;
	}
	
	@Autowired
	FileUploadService fus;
	
	public void setFileUploadService(FileUploadService fus) {
		this.fus = fus;
	}
	
	// Doctor Board 게시글 뿌리기
	public List<QuestionBoardDto> getDoctorBoard() {
		return dao.getDoctorBoard();
	}

	public List<QuestionBoardDto> getDoctorBoardPage(Map<String,Integer>param){
		return dao.getDoctorBoardList(param);
	}

	public QuestionBoardDto getDoctorBoardContents(int question_table_idx) {
		return dao.getDoctorBoardContents(question_table_idx);
	}

	public void getDoctorBoardViews(int question_table_idx) {
		dao.getDoctorBoardViews(question_table_idx);
	}

	public List<QuestionBoardDto> getDoctorBoardSearch(SearchBoardCommand sbc) {
		return dao.getDoctorBoardSearch(sbc);
	}

	public List<BoardCommentDto> getDoctorBoardComments(int question_table_idx) {
		return dao.getDoctorBoardComments(question_table_idx);
	}

// Doctor Board 작성, 수정, 삭제

	// 게시글 작성

	public void addDoctorArticles(QuestionBoardDto dto, MultipartHttpServletRequest request) {
		dto.setReg_date(LocalDateTime.now());
		dao.insertArticleForDoctor(dto);
		int idx = dto.getQuestion_table_idx();
		System.out.println("idx:::"+idx);
		if (idx>0) {
			bs.addFileForDoctor(idx, request);
		}
	}

	public void addFileForDoctor(int question_table_idx, MultipartHttpServletRequest request) {
		List<FileUploadCommand> addfiles;
		addfiles = fus.upload(request, "/img/boardUpload");
		for (FileUploadCommand file : addfiles) {
			BoardFileDto bdto = new BoardFileDto();
			bdto.setQuestion_table_idx(question_table_idx);
			bdto.setFile_name(file.getFileOriginName());
			bdto.setFile_path(file.getFilePath());
			bdto.setFile_size(file.getFileSize());
			bdto.setReg_date(LocalDateTime.now());
			dao.insertFileForDoctor(bdto);
		}
	}

	// 게시글 수정

	public int updateDoctorArticle(QuestionBoardDto dto) {
		dto.setReg_date(LocalDateTime.now());
		return dao.updateArticlesForDoctor(dto);
	}

	// 게시글 삭제
	public int deleteDoctorArticle(int idx) {
		return dao.deleteArticlesForDoctor(idx);
	}

// Doctor Board Comments 작성, 수정, 삭제

	// comment 작성
	public int addDoctorComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now());
		return dao.insertCommentForDoctor(commentDto);
	}

	// comment 수정
	public int updateDoctorComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now());
		return dao.updateCommentForDoctor(commentDto);
	}

	// comment 삭제
	public int deleteDoctorComment(int idx) {
		return dao.deleteCommentForDoctor(idx);
	}

// Casual Board 내용 구현
	public List<QuestionBoardDto> getCasualBoard() {
		return dao.getCasualBoard();
	}

	public List<QuestionBoardDto> getCasualBoardPage(Map<String,Integer>param){
		return dao.getCasualBoardList(param);
	}
	
	public int getTotal() {
		return dao.getTotal();
	}
	
	public QuestionBoardDto getCasualBoardContents(int question_table_idx) {
		return dao.getCasualBoardContents(question_table_idx);
	}

	public void getCasualBoardViews(int question_table_idx) {
		dao.getCasualBoardViews(question_table_idx);
	}
	
	public SearchBoardCommand listSearchInfo (int searchn, String searchKeyword) {
		SearchBoardCommand sbc = new SearchBoardCommand();
			if (searchn == 0) {
				sbc.setSearch_option("member_id");
			} else if (searchn == 1) {
				sbc.setSearch_option("title");
			} else if (searchn == 2) {
				sbc.setSearch_option("content");
			}
		sbc.setSearchKeyword(searchKeyword);
		return sbc;
	}
	
	public List<QuestionBoardDto> getCasualBoardSearch(SearchBoardCommand sbc) {
		return dao.getCasualBoardSearch(sbc);
	}

	public List<BoardCommentDto> getCasualBoardComments(int question_table_idx) {
		return dao.getCasualBoardComments(question_table_idx);
	}

// Casual Board 작성, 수정, 삭제

	// 게시글 작성

	public void addCasualArticles(QuestionBoardDto dto, MultipartHttpServletRequest request) {
		dto.setReg_date(LocalDateTime.now());
		dao.insertArticleForCasual(dto);
		int idx = dto.getQuestion_table_idx();
		System.out.println(idx);
		if (idx>0) {
			bs.addFileForCasual(idx, request);
		}
	}

	public void addFileForCasual(int question_table_idx, MultipartHttpServletRequest request) {
		List<FileUploadCommand> addfiles;
		addfiles = fus.upload(request, "/img/boardUpload");
		System.out.println(addfiles);
		for (FileUploadCommand file : addfiles) {
			BoardFileDto bdto = new BoardFileDto();
			bdto.setQuestion_table_idx(question_table_idx);
			bdto.setFile_name(file.getFileOriginName());
			bdto.setFile_path(file.getFilePath());
			bdto.setFile_size(file.getFileSize());
			bdto.setReg_date(LocalDateTime.now());
			dao.insertFileForCasual(bdto);
		}
	}
		

	// 게시글 수정

	public int updateCasualArticle(QuestionBoardDto dto) {

		dto.setUpdate_date(LocalDateTime.now());
		return dao.updateArticlesForCasual(dto);
	}

	// 게시글 삭제
	public int deleteCasualArticle(int idx) {
		return dao.deleteArticlesForCasual(idx);
	}

// Casual Board Comments 작성, 수정, 삭제	

	// comment 작성
	public int addCasualComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now());
		return dao.insertCommentForCasual(commentDto);
	}

	// comment 수정
	public int updateCasualComment(BoardCommentDto commentDto) {
		commentDto.setReg_date(LocalDateTime.now());
		return dao.updateCommentForCasual(commentDto);
	}

	// comment 삭제
	public int deleteCasualComment(int idx) {
		return dao.deleteCommentForCasual(idx);
	}

// Hashtag 추가 및 비교
	public List<TagDto> compareHashtag(String tagValue){
		return dao.getHashtag(tagValue);
	}
	
	public List<TagDto> addHashtag(TagCommand tc) {
		return dao.addHashtag(tc);
	}
	

}
