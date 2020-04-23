package com.careme.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.DoctorCommand;
import com.careme.model.command.SessionCommand;
import com.careme.service.FindDoctorService;

@Controller
public class FindDoctorController {
	@Autowired
	FindDoctorService findDoctorService;
	public void setFindDoctorService(FindDoctorService findDoctorService) {
		this.findDoctorService = findDoctorService;
	}
	
	
	@RequestMapping("/findDoctor")
	public ModelAndView toFindDoctorMain(HttpServletRequest request, Integer page) {
		if (page == null) page = 1;
		
		SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		
		Map<String, Object> items;
		// 로그인상태면(세션 확인) 자신의 펫정보와 매칭되는순서로 의사출력
		if (sc != null) {
			int member_idx = sc.getMemberDto().getMember_idx();
			items = findDoctorService.getDoctors(page, 10, member_idx);
		} else {
			items = findDoctorService.getDoctors(page, 10);
		}
		
		List<DoctorCommand> popularDoctors = findDoctorService.getPopularDoctors();
		
		ModelAndView mav = new ModelAndView("/findDoctor/list");
		mav.addObject("popularDoctors", popularDoctors);
		mav.addObject("doctors", items.get("doctors"));
		mav.addObject("paging", items.get("paging"));
		
		
		return mav;
	}
}
