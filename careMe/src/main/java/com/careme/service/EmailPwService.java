package com.careme.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.careme.dao.MemberDao;
import com.careme.model.dto.EmailDto;
import com.careme.model.dto.MemberDto;

@Service("emailPwService")
public class EmailPwService {

	@Autowired
	protected JavaMailSender mailSender;

	MemberDao dao;

	@Autowired
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	// 임시 비밀번호
	public String sendMail2(EmailDto email) throws Exception {

		try {
			MemberDto dto = new MemberDto(); // dto 객체 생성
			dto.setMember_email(email.getReceiver()); // 회원 이메일 가져옴
			String ranPw = pwRandom(); // pw 랜덤 생성
			System.out.println(ranPw);
			dto.setMember_pass(ranPw);
			dao.updatePw(dto);
			System.out.println(dto);

			MimeMessage msg = mailSender.createMimeMessage();

			msg.setSubject(email.getSubject());

			// 일반 텍스트만 전송하려는 경우
			msg.setText(email.getContent());

			// HTML 컨텐츠를 전송하려면.
			msg.setContent("<p>안녕하세요. [CARE ME!] 입니다.<br><br><br><br><br> " + "회원님의 임시 비밀번호는 " + ranPw + " 입니다.<br><br>"
					+ "보안을 위해 비밀번호 변경을 권장드립니다.<br>"
					+ "<br><br><br><a href=http://localhost:8080/careMe/login/loginform ><b><h3>▼▼▼▼▼변경하러 가기▼▼▼▼▼<h3></b></a></p>"
			// 마이페이지 비밀번호 변경
					, "text/html;charset=utf-8");

			msg.setRecipient(RecipientType.TO, new InternetAddress(email.getReceiver()));// 수신자 setting

			mailSender.send(msg);

			return "메일을 확인해주세요";

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return "이메일을 보내는데 실패했습니다";

	}

	// 랜덤값 생성
	private String pwRandom() {
		char passwd[] = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', };

		String randomPw = "";

		for (int i = 0; i < 10; i++) {
			int ran = (int) (Math.random() * (passwd.length));

			randomPw += passwd[ran];
		}
		return randomPw;

	}
}