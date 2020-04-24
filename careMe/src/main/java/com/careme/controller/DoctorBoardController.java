package com.careme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.CarediaryCommand;
import com.careme.model.command.PageNumberCommand;
import com.careme.model.command.SearchBoardCommand;
import com.careme.model.command.SessionCommand;
import com.careme.model.dto.BoardCommentDto;
import com.careme.model.dto.BoardFileDto;
import com.careme.model.dto.HeartDto;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.model.dto.TagDto;
import com.careme.service.CarediaryService;
import com.careme.service.FileUploadService;
import com.careme.service.HashTagService;
import com.careme.service.HeartService;
import com.careme.service.MemberService;
import com.careme.service.PageNumberService;
import com.careme.service.PetService;
import com.careme.service.QuestionBoardService;
import com.google.gson.Gson;

@Controller
public class DoctorBoardController {

	@Autowired
	QuestionBoardService bs;
	
	public void setQuestionBoardService(QuestionBoardService bs) {
		this.bs = bs;
	}
	
	@Autowired
	PetService ps;
	
	public void setPetService(PetService ps) {
		this.ps = ps;
	}
	
	@Autowired
	FileUploadService fus;
	
	public void setFileUploadService(FileUploadService fus) {
		this.fus = fus;
	}
	
	@Autowired
	PageNumberService pns;
	
	public void setPageNumberService(PageNumberService pns) {
		this.pns = pns;
	}
	
	@Autowired
	MemberService ms;
	
	public void setMemberService(MemberService ms) {
		this.ms=ms;
	}
	
	@Autowired
	HashTagService hs;
	public void setHashTagService(HashTagService hs) {
		this.hs = hs;
	}
	
	@Autowired
	HeartService hts;
	public void setHeartService(HeartService hts) {
		this.hts = hts;
	}
	
	@Autowired
	CarediaryService cds;
	public void setCarediaryService(CarediaryService cds) {
		this.cds = cds;
	}
	
	
//게시판 뿌리기(게시글 / 댓글 / 글개수)
	@RequestMapping(value = "/view/doctorBoardView/doctorBoard")
	public ModelAndView toDoctorBoard(int currentPage) {
		ModelAndView listPro = new ModelAndView("/doctorBoardView/doctorBoard");
		
		// 내용 및 페이지 번호
		PageNumberCommand paging = new PageNumberCommand();
		int contentPerPage = 10;
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start_idx", pns.getStartIdx(currentPage, contentPerPage));
		param.put("contentPerPage", contentPerPage);
		
		List<QuestionBoardDto> getArticles = bs.getDoctorBoardPage(param);
		paging = pns.paging(bs.getTotalDoctor(), contentPerPage, currentPage, "doctorBoard?currentPage=");
		
		listPro.addObject("listPro", getArticles);
		listPro.addObject("paging", paging);
		return listPro;
	}

//게시글 내용 불러오기
	@RequestMapping(value = "/view/doctorBoardView/doctorBoardContent", method = RequestMethod.GET)
	public ModelAndView doctorBoardContents(@RequestParam int question_table_idx, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("doctorBoardView/doctorBoardContent");
		
		//회원 정보 및 확인
		SessionCommand sc = (SessionCommand) session.getAttribute("sc");
		MemberDto info = sc.getMemberDto();
		mav.addObject("info", info);
		
		//글내용 불러오기
		bs.getDoctorBoardViews(question_table_idx);
		QuestionBoardDto mlist=bs.getDoctorBoardContents(question_table_idx);
		List<BoardFileDto> flist = bs.getDoctorBoardFiles(question_table_idx);
		List<BoardCommentDto> clist = bs.getDoctorBoardComments(question_table_idx);
		List<TagDto> tlist = bs.getTagContent(question_table_idx);
		
		int carediaryIdx = mlist.getPet_care_idx();
		CarediaryCommand dlist = cds.getCarediaryByIdx(carediaryIdx);
		String idx = String.valueOf(question_table_idx);
		int commentCount = clist.size();
		
		mav.addObject("info", info);
		mav.addObject("mlist", mlist);
		mav.addObject("flist", flist);
		mav.addObject("dlist", dlist);
		mav.addObject("tlist", tlist);
		mav.addObject("idx", idx);
		mav.addObject("clist", clist);
		mav.addObject("commCount", commentCount);
		return mav;
	}

	
// 게시판 검색기능
	@RequestMapping(value = "/view/doctorBoardView/doctorBoardSearch")
	public ModelAndView doctorBoardSearch(@RequestParam int searchn, String searchKeyword, int currentPage) {
		ModelAndView list = new ModelAndView("/doctorBoardView/doctorBoardSearch");
		int contentPerPage = 10;
		 
		//검색 정보 처리
		SearchBoardCommand sbc = new SearchBoardCommand();
		sbc=bs.listSearchInfo(searchn, searchKeyword);
		
		int start_idx=pns.getStartIdx(currentPage, contentPerPage);
		sbc.setStart_idx(start_idx);
		sbc.setContentPerPage(contentPerPage);
		
		List<QuestionBoardDto> items = bs.getDoctorBoardSearch(sbc);
		
		// 내용 및 페이지 번호
		PageNumberCommand paging = new PageNumberCommand();
		paging = pns.paging(bs.getTotalDoctor(), contentPerPage, currentPage, "doctorBoardView/doctorBoardSearch?currentPage="+currentPage+"&searchn="+searchn+"&searchKeyword="+searchKeyword);
		
		list.addObject("list", items);
		list.addObject("paging", paging);
		list.addObject("searchn", searchn);
		list.addObject("searchKeyword", searchKeyword);
		
		return list;
	}

	
	// 게시글 작성
		@RequestMapping(value = "/view/doctorBoardView/doctorWriteForm", method = RequestMethod.GET)
		public ModelAndView toWriteForm(HttpSession session) throws Exception {
			ModelAndView write = new ModelAndView("doctorBoardView/doctorWriteForm");
			
			//회원 정보 및 확인
			SessionCommand sc = (SessionCommand)session.getAttribute("sc");
			MemberDto info = sc.getMemberDto(); 
			int member_idx = info.getMember_idx();
			
			//pet정보 가져오기
			List<PetDto> petInfo = ps.selectPetList(member_idx);
			
			write.addObject("info", info);
			write.addObject("myPet", petInfo);
			write.addObject("speciesOption", ps.selectPetSpeciesLevel1());
			return write;
		}
		
		@RequestMapping(value = "/view/doctorBoardView/doctorWriteForm/pet_species_idx", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String getDoctorPetSpeciesList(int level, int ancestor) {
			List<PetSpeciesDto> items = null;
			
			if (level == 1) items = ps.selectPetSpeciesLevel1();
			else if (level == 2) items = ps.selectPetSpeciesLevel2(ancestor);
			
			Gson json = new Gson();
			return json.toJson(items);	
		}
		
		@RequestMapping(value = "/view/doctorBoardView/doctorWriteForm/pet_idx", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String getCasualPetListLevel2(int level, int selectPet) {
			
			HashMap<String, Object> petItems = null;
			int currentPage=1;
			int contentPerPage=10;
			if (level == 2) petItems = cds.getCarediaryListByPetIdx(selectPet, currentPage, contentPerPage);
			
			@SuppressWarnings("unchecked")
			List<CarediaryCommand> plist = (List<CarediaryCommand>) petItems.get("list");
			
			System.out.println("plisting::::"+plist);
			Gson json = new Gson();
			return json.toJson(plist);	
		}
		
		@RequestMapping(value = "/view/doctorBoardView/doctorBoardWriteAdd", method = RequestMethod.POST)
		public String writeDoctorBoardArticle(QuestionBoardDto dto, int[] rdTag, MultipartHttpServletRequest request) throws Exception {
			bs.addDoctorArticles(dto, request);
			int result = dto.getQuestion_table_idx();
			hs.insertUseTag("d", result, rdTag);
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
		}
	
		// hashtag 기능
		@RequestMapping(value="/view/doctorBoardView/doctorWriteForm/hashCheck", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String hashtagCompare(HttpServletRequest request, String tag_name) {
			SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
			int member_idx = sc.getMemberDto().getMember_idx();
			
			// 없으면 넣고, 있으면 찾아서 TagDto로 리턴!
			TagDto tagDto = hs.checkTag(tag_name, member_idx);		
			Gson json = new Gson();
			return json.toJson(tagDto);
		}
		
		@RequestMapping(value="/view/doctorBoardView/doctorWriteForm/hashInsert", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String hashTagInsert(HttpServletRequest request, String tag_name, int board_idx) {
			SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
			int member_idx = sc.getMemberDto().getMember_idx();
			
			TagDto tdto = new TagDto();
			
			tdto.setTag_name(tag_name);
			hs.insertTag(tag_name, member_idx);
			
			Gson json = new Gson();
			return json.toJson(tdto);
		}
		
		
// 게시글 수정
	@RequestMapping(value="/view/doctorBoardView/doctorBoardUpdateForm")
	public ModelAndView toDoctorUpdate(@RequestParam int question_table_idx) throws Exception {
		ModelAndView update = new ModelAndView("doctorBoardView/doctorBoardUpdateForm");
		
		//회원 정보 및 확인
//		String currentId = session.getAttribute("id");
//		MemberDto info = ms.memberInfo("hellojava");
//		update.addObject("info", info);
		
		QuestionBoardDto mlist = bs.getDoctorBoardContents(question_table_idx);
		
		int idx = question_table_idx;
			update.addObject("speciesOption", ps.selectPetSpeciesLevel1());
			update.addObject("idx", idx);

			update.addObject("mlist", mlist);
			
			return update;
	}


	@RequestMapping(value = "/view/doctorBoardView/doctorBoardUpdateAdd", method = RequestMethod.POST)
	public String updateDoctorArticle(QuestionBoardDto dto) throws Exception {
		int result = bs.updateDoctorArticle(dto);
		if (result > 0) {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
		} else {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
		}
	}

	
	// 게시글 삭제
		@RequestMapping(value="/view/doctorBoardView/deleteDoctorArticle")
		public String deleteDoctorArticle(@RequestParam int question_table_idx) {
			int idx = question_table_idx;
			int result = bs.deleteDoctorArticle(idx);
			if(result>0) {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
			}else {
			return "redirect:/view/doctorBoardView/doctorBoard?currentPage=1";
			}
		}

//==================================================================================
		
	// comment 작성
		@RequestMapping(value="/view/doctorBoardView/doctorCommentAdd")
		public String writeDoctorComment(BoardCommentDto commentDto) throws Exception {
			
			//member 확인
			MemberDto info = ms.memberInfo("testmin");
			int member_idx = info.getMember_idx();
			
			//comment 내용 테이블에 추가
			bs.addDoctorComment(commentDto);
			int result = commentDto.getQuestion_board_comment_idx();
			int backToPage=commentDto.getQuestion_table_idx();
			
			//하트 기능 테이블에 정보 추가
			HeartDto hdto = new HeartDto();
			hdto.setBoard_comment_idx(result);
			hdto.setBoard_type("c");
			hdto.setHeartCheck("n");
			hdto.setMember_idx(member_idx);
			hts.insertHeartInfo(hdto);
			
			if (result > 0) {
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			} else {
				System.out.println("no!!!");
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			}
		}

			
	// comment 수정
		@RequestMapping(value = "/view/doctorBoardView/doctorCommentUpdate", method = RequestMethod.POST)
		public String updateDoctorComment(BoardCommentDto commentDto) throws Exception {
			int result = bs.updateDoctorComment(commentDto);
			int backToPage=commentDto.getQuestion_table_idx();
			if (result > 0) {
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			} else {
				System.out.println("no!!!");
				return "redirect:/view/doctorBoardView/doctorBoardContent?question_table_idx="+backToPage;
			}
		}
			
			
	// comment 삭제
		@RequestMapping(value="/view/doctorBoardView/doctorCommentDelete")
		public String deleteDoctorComment(@RequestParam int question_board_comment_idx) {
			int idx = question_board_comment_idx;
			int result = bs.deleteDoctorComment(idx);
			if(result>0) {
			return "redirect:history.go(-1)";
			}else {
				System.out.println("no!!!");
			return "redirect:history.go(-1)";
			}
		}
		
	// comment heart 업데이트
		
		@RequestMapping(value ="/view/doctorBoardView/updateHeart", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		@ResponseBody
		public String updateHeart(int question_board_comment_idx, HttpSession session) {
			
			//회원 정보 및 확인
			SessionCommand sc = new SessionCommand();
			int member_idx = sc.getMemberDto().getMember_idx();
			int check = hts.memberCheck(member_idx);
			
			HeartDto hdto = new HeartDto();
			hdto.setBoard_comment_idx(question_board_comment_idx);
			hdto.setMember_idx(member_idx);
			
			if(check == 0) {
				hdto.setBoard_type("c");
				hdto.setHeartCheck("n");
				hts.insertHeartInfo(hdto);
				System.out.println("없을 경우 가져오는 hdto"+hdto);
				bs.heartProcess(hdto, question_board_comment_idx);
			}else {
				hdto=hts.getHeartInfo(hdto);
				System.out.println("있을 경우 가져오는 hdto"+hdto);
				bs.heartProcess(hdto, question_board_comment_idx);
			}
			
			BoardCommentDto cdto = new BoardCommentDto();
			cdto.setQuestion_board_comment_idx(question_board_comment_idx);
			cdto=bs.getDoctorComment(question_board_comment_idx);
			
			int currentHeart = cdto.getHeart();
			System.out.println(currentHeart);
			
			Gson json = new Gson();
			return json.toJson(currentHeart);
			
		}
}
