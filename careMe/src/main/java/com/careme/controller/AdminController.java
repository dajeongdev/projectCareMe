package com.careme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.MemberDto;
import com.careme.service.AdminService;
import com.careme.service.MemberService;
import com.google.gson.Gson;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Autowired
	MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/admin")
	public String toMain() {
		return "/admin/main";
	}

	// S: 일반회원
	@RequestMapping("/admin/member")
	public ModelAndView toMemberList(String page) {
		int currentPage = 1;
		if (page != null)
			currentPage = Integer.parseInt(page);
		ModelAndView mav = new ModelAndView("/admin/member/list");
		HashMap<String, Object> data = adminService.getMemberList(currentPage, 5);

		mav.addObject("list", data.get("list"));
		mav.addObject("paging", data.get("paging"));

		return mav;
	}

	@RequestMapping("/admin/member/search")
	public ModelAndView searchMemberList(@RequestParam("searchType") String searchType,
			@RequestParam("searchText") String searchText, @RequestParam("page") int page) {
		ModelAndView mav = new ModelAndView("/admin/member/list");
		HashMap<String, Object> data = adminService.searchMemberList(searchType, searchText, page, 5);

		mav.addObject("list", data.get("list"));
		mav.addObject("paging", data.get("paging"));
		return mav;
	}

	// 회원정보 수정폼
	@RequestMapping(value = "/admin/member/update", method = RequestMethod.GET)
	public ModelAndView toMemberUpdateForm(@RequestParam("memberIdx") int memberIdx) {
		ModelAndView mav = new ModelAndView("/admin/member/update");
		MemberDto memberDto = adminService.getMember(memberIdx);
		mav.addObject("member", memberDto);
		return mav;
	}

	// 회원정보 수정
	@RequestMapping(value = "/admin/member/update", method = RequestMethod.POST)
	public String memberUpdate(MemberDto memberDto, MultipartHttpServletRequest request) {
		int res = 0;
		res = adminService.updateMember(memberDto, request);
		return "redirect:/admin/member";
	}

	@RequestMapping(value = "/admin/member/delete", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteMember(HttpServletRequest request) {
		int memberIdx = Integer.parseInt(request.getParameter("memberIdx"));
		int res = adminService.deleteMember(memberIdx);
		String resultText = "fail";
		if (res == 1)
			resultText = "success";

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultText", resultText);
		resultMap.put("result", res);
		Gson json = new Gson();
		return json.toJson(resultMap);
	}

	// /admin/download/*
	@RequestMapping("/admin/download/member")
	public ModelAndView downloadMemberXls(String searchType, String searchText, String page) {
		Map<String, Object> params = new HashMap<String, Object>();
		String searchColumn = null;

		if (searchType != null)
			searchColumn = adminService.getSearchColumn(searchType);

		params.put("searchColumn", searchColumn);
		params.put("searchText", searchText);
		params.put("page", page);
		System.out.println(params);
		return new ModelAndView("memberListToXls", "params", params);
	}
	// E: 일반회원

	// S: 의사회원
	@RequestMapping("/admin/doctor")
	public ModelAndView toDoctorList(Integer page, String searchType, String searchText) {
		if (page == null) page = 1;
		ModelAndView mav = new ModelAndView("/admin/doctor/list");
		HashMap<String, Object> data = adminService.getDoctorList(page, searchType, searchText, 5);
		
		mav.addObject("doctors", data.get("doctors"));
		mav.addObject("paging", data.get("paging"));
		
		return mav;
	}
	
	@RequestMapping(value = "/admin/doctor/changeCert", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeDoctorCertification(int doctor_idx) {
		
		int res = adminService.changeDoctorCertification(doctor_idx);
		String resultText;
		if (res == 1) resultText = "success";
		else resultText = "fail";
			
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultText", resultText);
		resultMap.put("result", res);
		Gson json = new Gson();
		return json.toJson(resultMap);
	}

}
