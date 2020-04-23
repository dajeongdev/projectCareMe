package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.PetDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.SessionCommand;
import com.careme.model.dto.PetDto;
import com.careme.model.dto.PetSpeciesDto;

@Service("PetService")
public class PetServiceImpl implements PetService  {
	@Autowired
	private PetDao dao;
	
	public void setDao(PetDao dao) {
		this.dao = dao;
	}
	
	PetDto pet;
	public void setpet(PetDto pet) {
		this.pet = pet;
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
	public List<PetSpeciesDto> selectSpeciesLevel2BySelfIdx(int level2) {
		return dao.selectSpeciesLevel2BySelfIdx(level2);
	}
	
	@Override
	public PetDto selectPet(int petIdx) {
		return dao.selectPet(petIdx);
	}
	
	@Override
	public List<PetDto> selectPetList(int memberIdx) {
		return dao.selectPetList(memberIdx);
	}
	
	// 펫등록
	@Override
	public int insertPet(MultipartHttpServletRequest request) {
		SessionCommand sc = (SessionCommand) request.getAttribute("sc");
		pet = requestToPetDto(request);
		int pet_idx = 0;
		try {
			pet_idx = dao.insertPet(pet);
			// 회원의 펫리스트 선택해제
			deSelectPet(sc.getMemberDto().getMember_idx());
			// 지금 등록한 펫으로 선택
			updateToselectedPet(pet_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pet_idx; 
	}
	
	@Override
	public int updatePet(MultipartHttpServletRequest request) {
		int res = 0;
		pet = requestToPetDto(request);
		res = dao.updatePet(pet);
		request.getSession().removeAttribute("pet_idx");
		return res;
	};
	
	@Override
	public int deletePet(HttpServletRequest request) {
		return dao.deletePet((int) request.getSession().getAttribute("pet_idx")); 
	};
	
	public PetDto requestToPetDto(MultipartHttpServletRequest request) {
		pet = new PetDto();
		
		SessionCommand sc =  (SessionCommand) request.getSession().getAttribute("sc");
		int memberIdx = sc.getMemberDto().getMember_idx();
		
		if (request.getParameter("p") != null && request.getParameter("p") != "") {
			pet.setPet_idx(Integer.parseInt(request.getParameter("p")));
		}
		
		Integer pet_idx = (Integer) request.getSession().getAttribute("pet_idx");
		if (pet_idx != null) {
			pet.setPet_idx(pet_idx);
		}
		
		pet.setMember_idx(memberIdx);
		pet.setName(request.getParameter("name"));
		pet.setPet_species_idx(Integer.parseInt(request.getParameter("species")));
		pet.setNeutralized(request.getParameter("neutralized"));
		pet.setBirth(request.getParameter("birth"));
		pet.setGender(request.getParameter("gender"));
		
		if (request.getParameter("weight") != null && !request.getParameter("weight").isEmpty()) {
			pet.setWeight(Double.parseDouble(request.getParameter("weight")));
		}
			
		pet.setVaccination(request.getParameter("vaccination"));
		pet.setBlood_type(request.getParameter("bloodType"));
		pet.setRegistration_number(request.getParameter("registrationNumber"));
		pet.setMemo(request.getParameter("memo"));
		
		if (!request.getFile(request.getFileNames().next()).isEmpty()) {
			
			List<FileUploadCommand> files = fileUploadService.upload(request, "/img/pet/profile/");
			FileUploadCommand file = files.get(0);
			pet.setProfile_image_file_name(file.getFileOriginName());
			pet.setProfile_image_file_path(file.getFilePath());
			pet.setProfile_image_file_size(file.getFileSize());
		}
		
		return pet;
	}
	
	public void changeSelectedPet(int memberIdx, int petIdx) {
		// 선택된 펫 전부 취소
		deSelectPet(memberIdx	);
		// 새로 펫 선택
		updateToselectedPet(petIdx);
	}
	
	public int findSelectedPet(int memberIdx) {
		return dao.findSelectedPet(memberIdx);
	}
	
	public int updateToselectedPet(int petIdx) {
		return dao.updateToselectedPet(petIdx);
	}
	
	public void deSelectPet(int memberIdx) {
		dao.deSelectPet(memberIdx);
	}
}
