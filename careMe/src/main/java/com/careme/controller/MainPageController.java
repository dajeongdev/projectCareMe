package com.careme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.SessionCommand;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.PetDto;
import com.careme.model.dto.QuestionBoardDto;
import com.careme.service.FindDoctorService;
import com.careme.service.MemberService;
import com.careme.service.PageNumberService;
import com.careme.service.PetService;
import com.careme.service.QuestionBoardService;

@Controller
public class MainPageController {

	@Autowired
	MemberService ms;
	public void setMs(MemberService ms) {
		this.ms = ms;
	}
	
	@Autowired
	PetService ps;
	public void setPs(PetService ps) {
		this.ps = ps;
	}
	
	@Autowired
	FindDoctorService fds;
	public void setFds(FindDoctorService fds) {
		this.fds = fds;
	}
	
	@Autowired
	QuestionBoardService qbs;
	public void setQbs(QuestionBoardService qbs) {
		this.qbs = qbs;
	}
	
	@Autowired	
	PageNumberService pns;
	public void setPns(PageNumberService pns) {
		this.pns = pns;
	}

	@RequestMapping(value="/myPageCasual")
	public ModelAndView toMyPageCasual(HttpSession session) {
		ModelAndView mav = new ModelAndView("/myPageView/myPageCasual");
		
		// 회원 정보
		SessionCommand sc = (SessionCommand)session.getAttribute("sc");
		MemberDto mdto = sc.getMemberDto();
		int memberIdx = mdto.getMember_idx();
		int petIdx=sc.getPet_idx();
		
		// pet 정보
		List<PetDto> plist = ps.selectPetList(memberIdx);
		PetDto selected = ps.selectPet(petIdx);
		
		// 관련 의사 정보
		int currentPage= 1;
		int contentPerPage= 3;
		int petSpec = 0;
		if(selected!=null) {
			petSpec = selected.getPet_species_idx();
		}
		
		Map<String, Object> dlist = fds.getDoctors(currentPage, contentPerPage, petSpec, memberIdx);
		
		// 전문 상담 정보
		HashMap<String,Integer> doctorParam = new HashMap<String,Integer>();
		
		List<QuestionBoardDto> dblist= qbs.getMemberDoctorBoard(memberIdx, doctorParam);
		
		// 일반 게시판 정보
		HashMap<String,Integer> casualParam = new HashMap<String,Integer>();
		
		List<QuestionBoardDto> cblist= qbs.getMemberCasualBoard(memberIdx, casualParam);
		
		mav.addObject("mlist", mdto);
		mav.addObject("plist", plist);
		mav.addObject("dlist", dlist);
		mav.addObject("dblist", dblist);
		mav.addObject("cblist", cblist);
		
		return mav;
	}
	
	
	
	
		
	
}
