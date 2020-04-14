package com.careme.controller;

import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.CarediaryCommand;
import com.careme.service.CarediaryService;

@Controller
public class CarediaryController {
	@Autowired
	private CarediaryService carediaryService;
	
	public void setDao(CarediaryService carediaryService) {
		this.carediaryService = carediaryService;
	}
	
	@RequestMapping("/carediary/{pet_idx}")
	public String toCarediaryMain(@PathVariable("pet_idx") int pet_idx, HttpServletRequest request) {
		System.out.println("pet 선택:: " + pet_idx);
		request.getSession().setAttribute("pet_idx", pet_idx);
		return "/carediary/main";
	}
	
	@RequestMapping("/carediary")
	public String toCarediaryMain() {
		System.out.println("pet 선택:: 안함");
		// login했을때 pet idx 구해서 넣기
		return "redirect:/carediary/9";
	}
	
	@RequestMapping(value= "/carediary/write", method = RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView("/carediary/write");
		mav.addObject("smallDef", carediaryService.selectSmallDef());
		mav.addObject("bigDef", carediaryService.selectBigDef());
		return mav;
	}
	
	@RequestMapping(value= "/carediary/write", method = RequestMethod.POST)
	public String writeDairy(CarediaryCommand command, MultipartHttpServletRequest request) throws SQLException, Exception {
		carediaryService.writeDiary(command, request);
		return "/carediary/main";
	}
	
}
