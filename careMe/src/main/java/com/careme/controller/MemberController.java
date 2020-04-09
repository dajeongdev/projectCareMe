package com.careme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.careme.dao.MemberDao;
import com.careme.model.command.LoginCommand;
import com.careme.model.dto.MemberDto;
import com.careme.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	private void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@RequestMapping("/main")
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		List<MemberDto> members = memberDao.selectAll();
		mav.addObject("members", members);
		return mav;
	}

	// �α���
	@RequestMapping(value = "login/loginform", method = RequestMethod.GET)
	public String form() {
		return "login/loginform";
	}

	// ȸ������
	@RequestMapping(value = "login/signup", method = RequestMethod.GET)
	public String form(Model model) {
		return "login/signup";
	}
	
	@RequestMapping(value = "login/loginok")
	public String loginOk(LoginCommand lc, HttpSession session) {
	int i = memberService.loginOk(lc);//1이나 0리턴
	System.out.println(i);
		if(i==0) {
			return "redirect:loginform";
		}else {
			session.setAttribute("OK", lc.getMember_id());
			return "redirect:/main";
		}
		
	}

}
