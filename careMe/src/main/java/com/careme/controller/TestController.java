package com.careme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/main")
	public String goMain() {
		System.out.println("goMain()");
		return "main";
	}
	
	
}
