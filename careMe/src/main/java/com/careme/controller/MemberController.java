package com.careme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.careme.dao.MemberDao;
import com.careme.model.command.LoginCommand;

import com.careme.model.dto.EmailDto;
import com.careme.model.dto.MemberDto;
import com.careme.service.EmailService;
import com.careme.service.MemberService;
import com.google.gson.Gson;

@Controller
public class MemberController {

	@Autowired
	MemberDao memberDao;

	@Autowired
	MemberService memberService;

	@Autowired
	EmailService emailService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	private void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
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
		if (i == 0) {// 실패
			return "redirect:loginform";
		} else {// 성공
			memberService.setSession(session, lc);
			return "redirect:/main";
		}
	}
	
	//로그아웃
	@RequestMapping(value = "login/logoutok")
	public String logoutOk(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
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

	// 닉네임 중복체크
	@RequestMapping(value = "login/nickChk", method = RequestMethod.POST)
	@ResponseBody
	public int ncheck(LoginCommand lc) {
		int i4 = memberService.ncheck(lc);
		return i4;
	}

	// 이메일 중복체크
	@RequestMapping(value = "login/mailChk", method = RequestMethod.POST)
	@ResponseBody
	public int mailcheck(LoginCommand lc) {
		int i5 = memberService.mailcheck(lc);
		return i5;
	}
	
	
	//이메일 인증 창 열기
	@RequestMapping(value = "login/mailform")
	public String form3() {
		return "login/mailform";
	}

	// 이메일 보내기
	@RequestMapping(value="login/sendMail", produces = "application/json; charset=utf8")
	@ResponseBody()
	public String sendMail(@RequestParam() String getemail) throws Exception {
		System.out.println("getemail::"+getemail);
		EmailDto email = new EmailDto();

		String receiver = getemail; // Receiver.메일 받을 주소
		String subject = "[CAREME]인증메일입니다";
		String content = "";

		email.setReceiver(receiver);
		email.setSubject(subject);
		email.setContent(content);

		int result = emailService.sendMail(email);
		return result+"";

	}
	
	

	// 회원가입 성공
	@RequestMapping(value = "login/insertok")
	public String insertOk(MemberDto mdto, HttpSession session) {
		System.out.println("test" + mdto);
		int i3 = memberService.insertOk(mdto);// 0이나 1리턴
		System.out.println(i3);
		if (i3 == 0) { // 없으면 가입
			session.setAttribute("sussess", mdto.getMember_id());
			return "login/signupsu"; // 성공
		} else {
			return "redirect:signup"; // 실패
		}
	}
}