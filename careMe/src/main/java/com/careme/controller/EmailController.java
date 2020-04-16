package com.careme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.careme.model.dto.EmailDto;
import com.careme.service.EmailService;

public class EmailController {

	@Autowired
	private EmailService emailService;

	@RequestMapping("login/send")
    @ResponseBody
    public String sendMail() throws Exception {

         EmailDto email = new EmailDto();
    
        String receiver = "testjava@gmail.com"; //Receiver.메일 받을 주소
        String subject = "[CAREME]인증메일입니다";
        String content = "";

        email.setReceiver(receiver);
        email.setSubject(subject);
        email.setContent(content);

        boolean result = emailService.sendMail(email);

        return "Mail Send: "+result;

    }

}


