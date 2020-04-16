package com.careme.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.careme.model.dto.EmailDto;


@Service("emailService")
public class EmailService {

	@Autowired
	protected JavaMailSender mailSender;

	public boolean sendMail(EmailDto email) throws Exception {

		try {
			MimeMessage msg = mailSender.createMimeMessage();

				int checkNum = 1;
				while (true) {
					checkNum = ((int) (Math.random() * 1000000));
					if (checkNum > 99999) {
						break;
					}
				}
			
			msg.setSubject(email.getSubject());

			// 일반 텍스트만 전송하려는 경우
			msg.setText(email.getContent());

			// HTML 컨텐츠를 전송하려면.
			msg.setContent("\"<h1>인증번호 : \"+checkNum+\"</h1>\"", "text/html;charset=utf-8");

			msg.setRecipient(RecipientType.TO, new InternetAddress(email.getReceiver()));//수신자 setting

			mailSender.send(msg);

			return true;

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return false;

	}

}