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
import com.careme.model.command.SessionCommand;
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
	
	// 메인화면
	@RequestMapping("/carediary/{petIdx}")
	public ModelAndView toCarediaryMain(@PathVariable("petIdx") int petIdx, Integer page, HttpServletRequest request) {
		if (page == null) page = 1;
		
		// header script 에서 session에 pet_idx 없으면 로그인화면으로 이동하게 처리
		SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		if (sc == null) {
			return new ModelAndView("/main");
		}
		int memberIdx = sc.getMemberDto().getMember_idx();
		
		// pet Info
		PetDto petDto = petService.selectPet(petIdx);
		// 선택펫 변경
		petService.changeSelectedPet(memberIdx, petIdx);
		// session에 저장
		sc.setPet_idx(petIdx);
		// pet list
		List<PetDto> pets = petService.selectPetList(memberIdx);
		
		// diary list
		HashMap<String, Object> data = carediaryService.getCarediaryListByPetIdx(petIdx, page, 5);
		@SuppressWarnings("unchecked")
		List<CarediaryCommand> articles = (List<CarediaryCommand>) data.get("list");
		PageNumberCommand paging = (PageNumberCommand) data.get("paging");
		
		//pet species name
		HashMap<String, Object> petSpecName = petService.getPetSpecName(petIdx);
		
		ModelAndView mav = new ModelAndView("/carediary/main");
		mav.addObject("pet", petDto);
		mav.addObject("pets", pets);
		mav.addObject("petSpecName", petSpecName);
		mav.addObject("articles", articles);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	@RequestMapping("/carediary")
	public String toCarediaryMain(HttpServletRequest request) {
		SessionCommand sc = (SessionCommand) request.getSession().getAttribute("sc");
		
		if (sc != null) {
			int memberIdx = sc.getMemberDto().getMember_idx();
			int petIdx = petService.findSelectedPet(memberIdx);		
			sc.setPet_idx(petIdx);
			request.getSession().setAttribute("sc", sc);
			return "redirect:/carediary/" + petIdx;
		}
		
		return "/main";
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
		return "redirect:/carediary/" + dto.getPet_idx();
	}
	
	@RequestMapping(value= "/carediary/update", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam("d_id") int carediaryIdx) {
		ModelAndView mav = new ModelAndView("/carediary/update");
		
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
