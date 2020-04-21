package com.careme.controller;

import java.sql.SQLException;
import java.util.HashMap;
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
import com.careme.model.command.PageNumberCommand;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.PetCareDto;
import com.careme.model.dto.PetDto;
import com.careme.service.CarediaryService;
import com.careme.service.PetService;

@Controller
public class CarediaryController {
	@Autowired
	private CarediaryService carediaryService;
	public void setCarediaryService(CarediaryService carediaryService) {
		this.carediaryService = carediaryService;
	}
	
	@Autowired
	private PetService petService;
	public void setPetService(PetService petService) {
		this.petService = petService;
	}
	
	@RequestMapping("/carediary/{pet_idx}")
	public ModelAndView toCarediaryMain(@PathVariable("pet_idx") int pet_idx, Integer page, HttpServletRequest request) {
		if (page == null) page = 1;
		
		// header script 에서 session에 pet_idx 없으면 로그인화면으로 이동하게 처리
		MemberDto mSession = (MemberDto) request.getSession().getAttribute("sc");
		int memberIdx = mSession.getMember_idx();
		
		// pet list
		List<PetDto> pets = petService.selectPetList(memberIdx);
		System.out.println(pets);
		
		// diary list
		HashMap<String, Object> data = carediaryService.getCarediaryListByPetIdx(pet_idx, page, 2);
		@SuppressWarnings("unchecked")
		List<CarediaryCommand> articles = (List<CarediaryCommand>) data.get("list");
		PageNumberCommand paging = (PageNumberCommand) data.get("paging");
		
		ModelAndView mav = new ModelAndView("/carediary/main");
		mav.addObject("pets", pets);
		mav.addObject("articles", articles);
		mav.addObject("paging", paging);
		
		System.out.println(paging);
		
		return mav;
	}
	
	@RequestMapping("/carediary")
	public String toCarediaryMain(HttpServletRequest request) {
		MemberDto mSession = (MemberDto) request.getSession().getAttribute("sc");
		if (mSession != null) {
			int memberIdx = mSession.getMember_idx();
		}
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
