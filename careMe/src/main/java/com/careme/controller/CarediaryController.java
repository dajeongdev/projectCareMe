package com.careme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.careme.dao.CarediaryDao;
import com.careme.service.CarediaryService;

@Controller
public class CarediaryController {
	@Autowired
	private CarediaryService carediaryService;
	
	public void setDao(CarediaryService carediaryService) {
		this.carediaryService = carediaryService;
	}
	
	
	@RequestMapping("/carediary")
	public String toCarediaryMain() {
		return "/carediary/main";
	}
	
	@RequestMapping(value= "/carediary/write", method = RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView("/carediary/write");
		mav.addObject("smallDef", carediaryService.selectSmallDef());
		mav.addObject("bigDef", carediaryService.selectBigDef());
		return mav;
	}
}
