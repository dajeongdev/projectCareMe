package com.careme.service;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.command.StoryCommand;
import com.careme.model.command.StoryContentCommand;
import com.careme.model.command.TagListCommand;
import com.careme.model.dto.HeartDto;
import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryCommentDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.model.dto.TagDto;

public interface StoryService {
	// 글목록
	public List<StoryBoardDto> list(StoryCommand com);
	public List<StoryBoardDto> listPaging(Map<String, Integer> map);
	public int getTotal();
	public List<StoryFileDto> fileList();
	public StoryCommand searchList(int searchType, String keyword);
	public List<StoryBoardDto> searching(StoryCommand com);
	public StoryContentCommand getContent(int story_board_idx);
	// 인기글
	public List<StoryBoardDto> hitList();
		
	// 글 상세보기
	public StoryBoardDto read(int story_board_idx);
	public List<StoryFileDto> readFile(int story_board_idx);
	public List<StoryCommentDto> readCom(int story_board_idx);
	public StoryCommentDto readComIdx(int story_comment_idx);
	public List<TagDto> readTags(int story_board_idx);
	// 태그 리스트
	public List<TagDto> readTagList(Map<String, Integer> map);
	public List<StoryFileDto> readTagFileList(int story_board_idx);
	public TagListCommand tagInfo(int tag_idx);
	public List<TagDto> tagSelect(TagListCommand tagListCom);
	// 조회수
	public int counting(int story_board_idx);
	// 좋아요
	public int addHeart(int idx);
	public int subHeart(int idx);
	public int addComHeart(int idx);
	public int subComHeart(int idx);
	public void hearting(HeartDto heart, int story_comment_idx);
	
	// 작성
	public int insert(StoryBoardDto dto, MultipartHttpServletRequest request);
	public void fileRequesting(int story_board_idx, MultipartHttpServletRequest request);
	public int insertCom(StoryCommentDto comDto);
	
	// 수정
	public void update(StoryBoardDto dto, StoryFileDto fileDto, Integer[] deletedFiles, MultipartHttpServletRequest request);
	public void updateFile(Map<String, Integer> map);
	public int updateCom(StoryCommentDto comDto);
	
	// 삭제
	public int delete(int story_board_idx);
	public int deleteFile(int story_board_idx);
	public int deleteCom(int story_comment_idx);
}
