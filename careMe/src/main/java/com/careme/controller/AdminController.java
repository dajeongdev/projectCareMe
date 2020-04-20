package com.careme.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.dto.MemberDto;
import com.careme.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminSevice;
	public void setAdminService(AdminService adminSevice) {
		this.adminSevice = adminSevice;
	}
	
	
	@RequestMapping("/admin")
	public String toMain() {
		return "/admin/main";
	}
	
	@RequestMapping("/admin/member")
	public ModelAndView toMemberList() {
		ModelAndView mav = new ModelAndView("/admin/member/list");
		List<MemberDto> list = adminSevice.getMemberList();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/admin/member/search")
	public ModelAndView searchMemberList(@RequestParam("searchType") String searchType
													, @RequestParam("searchText") String searchText
													, @RequestParam("page") int page
													) {
		ModelAndView mav = new ModelAndView("/admin/member/list");
		HashMap<String, Object> data = adminSevice.searchMemberList(searchType, searchText, page);
		mav.addObject("list", data.get("list"));
		mav.addObject("paging", data.get("paging"));
		return mav;
	}
	
	@RequestMapping(value = "/admin/member/update", method=RequestMethod.GET)
	public String toMemberUpdateForm(@RequestParam("memberIdx") int memberIdx) {
		return "/admin/member/update";
	}
	
	@RequestMapping("/admin/doctor")
	public String toDoctorList() {
		return "/admin/doctor/list";
	}

}
