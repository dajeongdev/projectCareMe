package com.careme.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.command.CarediaryCommand;
import com.careme.model.dto.PetCareDto;
import com.careme.service.CarediaryService;

@Controller
public class CarediaryController {
	@Autowired
	private CarediaryService carediaryService;
	
	public void setDao(CarediaryService carediaryService) {
		this.carediaryService = carediaryService;
	}
	
	@RequestMapping("/carediary/{pet_idx}")
	public ModelAndView toCarediaryMain(@PathVariable("pet_idx") int pet_idx, HttpServletRequest request) {
		//carediary 화면으로 들어와서 선택한 펫idx session에 저장
		//SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		//sc.setPet_idx(pet_idx);
		List<CarediaryCommand> list = carediaryService.getCarediaryListByPetIdx(pet_idx, 1);
		ModelAndView mav = new ModelAndView("/carediary/main");
		mav.addObject("articles", list);
		
		return mav;
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
	public String writeDairy(PetCareDto dto, MultipartHttpServletRequest request) throws SQLException, Exception {
		carediaryService.writeCarediary(dto, request);
		return "/carediary/main";
	}
	
	@RequestMapping(value= "/carediary/update", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam("d_id") int carediaryIdx) {
		ModelAndView mav = new ModelAndView("/carediary/update");
		//HttpServletResponse response = new HttpServletResponse
		
		mav.addObject("smallDef", carediaryService.selectSmallDef());
		mav.addObject("bigDef", carediaryService.selectBigDef());
		mav.addObject("diaryInfo", carediaryService.getCarediaryByIdx(carediaryIdx));
		
		return mav;
	}
	
	@RequestMapping(value= "/carediary/update", method = RequestMethod.POST)
	public String updateDiary(PetCareDto dto, Integer[] deletedFiles, MultipartHttpServletRequest request) {
		carediaryService.updateCarediary(dto, deletedFiles, request);
		return "/carediary/main";
	}
	
}
