package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.PetDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;

@Service("PetService")
public class PetServiceImpl implements PetService  {
	@Autowired
	private PetDao dao;
	
	public void setDao(PetDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	private FileUploadService fileUploadService;
	
	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}
	
	
	
	@Override
	public List<PetSpeciesDto> selectPetSpeciesLevel1() {
		return dao.selectPetSpeciesLevel1();
	}
	
	@Override
	public List<PetSpeciesDto> selectPetSpeciesLevel2(int level1) {
		return dao.selectPetSpeciesLevel2(level1);
	}
	
	@Override
	public List<PetDto> selectPet() {
		List<PetDto> pets = null;
		return pets;
	};
	
	@Override
	public int insertPet(MultipartHttpServletRequest request) {
		
		PetDto pet = new PetDto();
		
		// MemberDto member =  (MemberDto) request.getSession().getAttribute("member");
		int memberIdx = 1;
		
		pet.setMember_idx(memberIdx);
		pet.setName(request.getParameter("name"));
		pet.setPet_species_idx(Integer.parseInt(request.getParameter("species")));
		pet.setNeutralized(request.getParameter("neutralized"));
		pet.setBirth(request.getParameter("birth"));
		pet.setGender(request.getParameter("gender"));
		
		/*
		 * if (request.getParameter("weight") != null)
		 * pet.setWeight(Double.parseDouble(request.getParameter("weight")));
		 */
			
		pet.setVaccination(request.getParameter("vaccination"));
		pet.setBlood_type(request.getParameter("bloodType"));
		pet.setRegistration_number(request.getParameter("registrationNumber"));
		pet.setMemo(request.getParameter("memo"));
		
		List<FileUploadCommand> files = fileUploadService.upload(request, "/img/pet/profile/");
		FileUploadCommand file = files.get(0);
		pet.setProfile_image_file_name(file.getFileOriginName());
		pet.setProfile_image_file_path(file.getFilePath());
		pet.setProfile_image_file_size(file.getFileSize());
		
		
		int res = dao.insertPet(pet);
		System.out.println(res);
		return res;
	};
	
	@Override
	public int updatePet() { return 0; };
	
	@Override
	public int deletePut() { return 0; };

}
