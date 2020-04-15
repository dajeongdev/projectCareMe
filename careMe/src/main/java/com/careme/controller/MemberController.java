package com.careme.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.careme.dao.MemberDao;
import com.careme.model.command.LoginCommand;
import com.careme.model.command.SessionCommand;
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

	// 메인
	@RequestMapping("/main")
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		List<MemberDto> members = memberDao.selectAll();
		mav.addObject("members", members);
		return mav;
	}

	// 로그인폼
	@RequestMapping(value = "login/loginform", method = RequestMethod.GET)
	public String form() {
		return "login/loginform";
	}

	// 로그인 성공
	@RequestMapping(value = "login/loginok")
	public String loginOk(LoginCommand lc, HttpSession session) {
		int i = memberService.loginOk(lc);// 1이나 0리턴
		// System.out.println(i);
		if (i == 0) {
			return "redirect:loginform";
		} else {
			session.setAttribute("OK", lc.getMember_id());
			return "redirect:/main";
		}
	}

	// 회원가입폼
	@RequestMapping(value = "login/signup")
	public String form2() {
		return "login/signup";
	}

	// 아이디 중복체크
	@RequestMapping(value = "login/idChk", method = RequestMethod.POST)
	@ResponseBody
	public int idcheck(LoginCommand lc) {
		int i2 = memberService.idcheck(lc);
		return i2;
	}

	// 이메일 인증

	
	// 회원가입 성공
	@RequestMapping(value = "login/insertok")
	public String insertOk(MemberDto mdto, HttpSession session) {
		// System.out.println("test"+ mdto);
		int i3 = memberService.insertOk(mdto);// 0이나 1리턴
		// System.out.println(i3);
		if (i3 == 0) { // 없으면 가입
			session.setAttribute("sussess", mdto.getMember_id());
			return "login/signupsu"; // 성공
		} else {
			return "redirect:signup"; // 실패
		}
	}
}