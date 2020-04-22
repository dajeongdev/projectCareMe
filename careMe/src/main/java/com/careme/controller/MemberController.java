package com.careme.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.dao.MemberDao;
import com.careme.model.command.LoginCommand;
import com.careme.model.command.SessionCommand;
import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.EmailDto;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.PetSpeciesDto;
import com.careme.service.EmailPwService;
import com.careme.service.EmailService;
import com.careme.service.MemberService;
import com.careme.service.PetService;

@Controller
public class MemberController {

	@Autowired
	MemberDao memberDao;

	@Autowired
	MemberService memberService;

	@Autowired
	EmailService emailService;

	@Autowired
	EmailPwService emailPwService;

	@Autowired
	PetService petService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	private void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setPetService(PetService petService) {
		this.petService = petService;
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
			memberService.setSession(session, lc);// 아이디, 비번 저장
			return "redirect:/main";
		}
	}

	// 로그아웃
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

	// 이메일 인증 창 열기
	@RequestMapping(value = "login/mailform")
	public String form3() {
		return "login/mailform";
	}

	// 인증 이메일 보내기
	@RequestMapping(value = "login/sendMail", produces = "application/json; charset=utf8")
	@ResponseBody()
	public String sendMail(@RequestParam() String getemail) throws Exception {
		// System.out.println("getemail::" + getemail);
		EmailDto email = new EmailDto();
		String receiver = getemail; // Receiver.메일 받을 주소
		String subject = "[CARE ME!]인증메일입니다";
		String content = "";
		email.setReceiver(receiver);
		email.setSubject(subject);
		email.setContent(content);
		int result = emailService.sendMail(email);
		return result + "";
	}

	// 비밀번호 찾기 창 열기
	@RequestMapping(value = "login/pwFind", method = RequestMethod.GET)
	public String form9() {
		return "login/pwFind";
	}

	// 비밀번호 찾기 이메일 보내기
	@RequestMapping(value = "login/find_pass")
	@ResponseBody
	public String sendMail2(String member_email) throws Exception {
		EmailDto email2 = new EmailDto();
		System.out.println(member_email);
		String receiver = member_email; // Receiver. //회원 계정 이메일//
		String subject = "[CARE ME!] 임시 비밀번호 안내 이메일 입니다."; // 제목
		String content = "[CARE ME!]"; // 내용
		email2.setReceiver(receiver);
		email2.setSubject(subject);
		email2.setContent(content);
		String result = emailPwService.sendMail2(email2);
		return "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\">\r\n"
				+ "  <style>\r\n" + "    .a {\r\n" + "        margin: 40px 0px;\r\n" + "        text-align: center;\r\n"
				+ "        font-size: 130px;\r\n" + "        color: #808080;\r\n" + "      }\r\n"
				+ "</head></style></head><body><div align=\"center\"><div><h1 style=\"\r\n"
				+ "    text-align: center;\r\n" + "    color: #222222;\r\n" + "    padding-top: 50px;\r\n" + "\">"
				+ result + "</h1><div class=\"a\">\r\n" + "<i class=\"fas fa-envelope-open-text\"></i><br>\r\n" + "\r\n"
				+ "\r\n"
				+ "<button type=\"button\" onclick=\"window.close();\" style=\"border-radius: 10px;width: 120px;background-color: #111111;border: none;color:#fff;padding: 15px 0;text-align: center;text-decoration: none;display: inline-block;font-size: 15px;margin: 4px;\">닫 기</button></div></div><div align=\"center\"></div></div></body>";

	}

	// 회원가입 성공
	@RequestMapping(value = "login/insertok")
	public String insertOk(MemberDto mdto, HttpSession session) {
		// System.out.println("test" + mdto);
		int i3 = memberService.insertOk(mdto);// 0이나 1리턴
		// System.out.println(i3);
		if (i3 == 0) { // 없으면 가입
			session.setAttribute("sussess", mdto.getMember_id());
			return "login/signupsu"; // 성공
		} else {
			return "redirect:signup"; // 실패
		}
	}

	// 의사등록폼
	@RequestMapping(value = "login/doctorInsertForm")
	public String form4(HttpServletRequest request, Model model) {
		List<PetSpeciesDto> selectPet = petService.selectPetSpeciesLevel1();
		model.addAttribute("addpet", selectPet); // 모델에 추가

		return "login/doctorInsertForm";
	}

	// 의사등록
	@RequestMapping(value = "login/dinsertok")
	public String dinsertOk(DoctorDto ddto, MultipartHttpServletRequest request) {
		SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		int member_idx = sc.getMemberDto().getMember_idx();
		ddto.setMember_idx(member_idx);
		System.out.println("dtest" + ddto);
		int i4 = memberService.dinsertOk(ddto, request);// 0이나 1리턴
		if (i4 == 1) { // 없으면 가입
			return "login/doctorOk"; // 성공
		} else {
			return "redirect:doctorInsert"; // 실패
		}
	}

	// 마이페이지
	@RequestMapping(value = "login/mypage", method = RequestMethod.GET)
	public String form7() {
		return "login/mypage";
	}

	// 정보수정폼-일반
	@RequestMapping(value = "login/memberUpdateForm", method = RequestMethod.GET)
	public String form5() {
		// System.out.println("멤버 업데이트폼");
		return "login/memberUpdateForm";
	}

	// 정보수정-비밀번호 변경
	@RequestMapping(value = "login/update")
	public String updateOk(MemberDto mdto, HttpSession session) {
		List<MemberDto> list = memberService.updateOk(mdto);
		int i5 = list.size();
		// System.out.println(i5);
		if (i5 == 0) { // 일치하면 수정
			memberService.updateOk(mdto);
			return "redirect:/main";
		} else {
			session.setAttribute("message", "비밀번호를 다시 입력해주세요");
			return "redirect:/memberUpdateForm";
		}

	}

	// 정보수정폼-의사
	@RequestMapping(value = "login/doctorUpdateForm")
	public ModelAndView form6(HttpSession session, Model model) {
		List<PetSpeciesDto> selectPet = petService.selectPetSpeciesLevel1();// 체크박스
		model.addAttribute("addpet", selectPet); // 모델에 추가
		System.out.println("받아온동물" + selectPet);
		ModelAndView mav = new ModelAndView("login/doctorUpdateForm");
		int member_idx = ((SessionCommand) session.getAttribute("sc")).getMemberDto().getMember_idx();
		DoctorDto doctorDto = memberService.selectDoctor(member_idx);
		mav.addObject("doctorDto", doctorDto);
		return mav;
	}

	// 정보수정-의사
	@RequestMapping(value = "login/dupdateok")
	public String dupdateOk(DoctorDto ddto, HttpSession session) {
		String m_id = ((SessionCommand) session.getAttribute("sc")).getMemberDto().getMember_id();
		List<DoctorDto> list = memberService.dupdateOk(ddto);
		DoctorDto selectDoctor = memberService.selectDoctor(ddto.getMember_idx());
		MemberDto selectMember = memberService.memberInfo(m_id);
		SessionCommand sc = new SessionCommand();
		sc.setDoctorDto(selectDoctor);
		sc.setMemberDto(selectMember);
		session.setAttribute("sc", sc);
		int i6 = list.size();
		if (i6 == 0) {
			// memberService.dupdateOk(ddto);
			return "redirect:/main";
		} else {
			session.setAttribute("msg", "수정에 실패했습니다. 수정 화면으로 돌아갑니다");
			return "redirect:/doctorUpdateForm";
		}
	}

	// 회원탈퇴 폼
	@RequestMapping(value = "login/deleteMember", method = RequestMethod.GET)
	public String form8() {
		return "login/deleteMember";
	}

	// 회원탈퇴 성공
	@RequestMapping(value = "login/deleteMemberOk")
	public String deleteOk(MemberDto mdto, HttpSession session) {
		int i6 = memberService.deleteOk(mdto);
		System.out.println(i6);
		if (i6 == 1) {
			memberService.deleteOk(mdto);
			session.invalidate();
			return "redirect:/main";
		} else {
			session.setAttribute("mes", "비밀번호가 틀렸습니다.");
			return "redirect:/deleteMember";
		}
	}

}