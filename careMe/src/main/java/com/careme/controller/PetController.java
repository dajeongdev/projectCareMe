package com.careme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	public String registPet(MultipartHttpServletRequest request) {
		petService.insertPet(request);
		return "/main";
	}
	
	@RequestMapping(value = "/pet/update", method = RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest reqeust, int p) {
		ModelAndView mav = new ModelAndView("pet/update");
		PetDto pet = petService.selectPet(p); 
		mav.addObject("speciesOption", petService.selectPetSpeciesLevel1());
		mav.addObject("speciesOption2", petService.selectPetSpeciesLevel2(1));
		mav.addObject("pet", pet);
		
		return mav;
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
