package com.careme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarediaryController {
	@RequestMapping("/carediary")
	public String toCarediaryMain() {
		return "/carediary/main";
	}
}
