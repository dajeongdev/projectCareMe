package com.careme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.careme.model.command.LoginCommand;
import com.careme.model.dto.MemberDto;



@Controller
public class LoginController {

	// 로그인화면으로 가기
	@RequestMapping(value = "login/loginform", method = RequestMethod.GET)
	public String login() {
		return "login/loginform";
	}
	
	// 회원가입화면으로 가기
		@RequestMapping(value = "login/signup", method = RequestMethod.GET)
		public String signup(Model model) {
			MemberDto memberDto = new MemberDto();
			model.addAttribute("MemberDto", memberDto);
			return "login/signup";
		}
	
	/*
	 * @RequestMapping(method = RequestMethod.POST) public String
	 * submit(@ModelAttribute("login") LoginCommand loginCommand, BindingResult
	 * result) { new LoginCommandValidator().validate(loginCommand, result); if
	 * (result.hasErrors()) { return "login/loginForm"; } try {
	 * authenticator.authenticate(loginCommand.getMember_id(),
	 * loginCommand.getMember_pass()); return "main"; } catch
	 * (AuthenticationException ex) { result.reject("invalidIdOrPassword", new
	 * Object[] { loginCommand.getMember_id()}, null); return "login/loginForm"; } }
	 * 
	 * @Autowired public void setAuthenticator(Authenticator loginService) {
	 * this.authenticator = loginService; }
	 * 
	 * @InitBinder protected void initBinder(WebDataBinder binder) {
	 * binder.setValidator(new LoginCommandValidator()); }
	 */
}
 