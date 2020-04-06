package com.careme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;
import com.careme.service.PetService;
import com.google.gson.Gson;

@Controller
public class PetController {
	@Autowired
	PetService petService;

	public void setPetService(PetService petService) {
		this.petService = petService;
	}
	
	@RequestMapping(value = "/pet/regist", method = RequestMethod.POST)
	public String registPet(PetDto dto) throws Exception {
		System.out.println(dto);
		return "/main";
	}


	@RequestMapping(value = "/pet/regist", method = RequestMethod.GET)
	public ModelAndView registForm() {
		ModelAndView mav = new ModelAndView("pet/regist");
		mav.addObject("speciesOption", petService.selectPetSpeciesLevel1()); 		
		return mav;
	}
	
	@RequestMapping(value = "/api/pet/species", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getPetSpeciesList(int level, int ancestor) {
		List<PetSpeciesDto> items = null;
		
		if (level == 1) items = petService.selectPetSpeciesLevel1();
		else if (level == 2) items = petService.selectPetSpeciesLevel2(ancestor);
		
		Gson json = new Gson();
		return json.toJson(items);
	}

}
