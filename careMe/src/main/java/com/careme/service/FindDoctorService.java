package com.careme.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.FindDoctorDao;
import com.careme.model.command.DoctorCommand;
import com.careme.model.command.PageNumberCommand;
import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.DoctorMajorWithSpeciesName;

@Service
public class FindDoctorService {
	
	@Autowired
	FindDoctorDao findDoctorDao;
	public void setFindDoctorDao(FindDoctorDao findDoctorDao) {
		this.findDoctorDao = findDoctorDao;
	}
	
	@Autowired
	PageNumberService pageService;
	public void setPageNumberService(PageNumberService pageService) {
		this.pageService = pageService;
	}
	
	
	// 전체 의사 리스트(비회원일때)
	public Map<String, Object> getDoctors(int currentPage, int contentPerPage, Integer petSpec) {
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		int start = pageService.getStartIdx(currentPage, contentPerPage);
		params.put("start", start);
		params.put("contentPerPage", contentPerPage);
		if (petSpec != null) params.put("pet_species_idx", petSpec);
		
		List<DoctorDto> doctorDtoList = findDoctorDao.getDoctorDtoList(params);
		int totalCount = findDoctorDao.selectTotalCount();
		
		List<DoctorCommand> doctors = getDoctorCommand(doctorDtoList);
		data.put("doctors", doctors);
		
		String path = "findDoctor?page=";
		if (petSpec != null) path = "findDoctor?petSpec=" + petSpec + "page=";

		PageNumberCommand paging = pageService.paging(totalCount, contentPerPage, currentPage, path);
		data.put("paging", paging);
		
		return data;
	}
	
	// member_idx를 넣으면 그 회원이 등록한 pet과 관련된 의사를 소환함
	public Map<String, Object> getDoctors(int currentPage, int contentPerPage, Integer petSpec, int member_idx) {
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		int start = pageService.getStartIdx(currentPage, contentPerPage);
		params.put("start", start);
		params.put("contentPerPage", contentPerPage);
		params.put("member_idx", member_idx);
		if (petSpec != null) params.put("pet_species_idx", petSpec);
		
		List<DoctorDto> doctorDtoList = findDoctorDao.getDoctorDtoListOrderByMatching(params);
		int totalCount = findDoctorDao.selectTotalCount();
		
		List<DoctorCommand> doctors = getDoctorCommand(doctorDtoList);
		data.put("doctors", doctors);
		
		String path = "findDoctor?page=";
		if (petSpec != null) path = "findDoctor?petSpec=" + petSpec + "page=";
		PageNumberCommand paging = pageService.paging(totalCount, contentPerPage, currentPage, path);
		data.put("paging", paging);
		
		return data;
	}
	
	// 한달동안 답변에 좋아요 많이 받은 top3 의사 
	public List<DoctorCommand> getPopularDoctors() {
		LocalDate now = LocalDate.now();
		String monthAgo = now.minusMonths(1).toString(); 
		List<DoctorDto> doctorDtoList = findDoctorDao.getPopularDoctorDtoList(monthAgo);
		
		List<DoctorCommand> doctors = getDoctorCommand(doctorDtoList);
		return doctors;
	}
	
	public List<DoctorCommand> getDoctorCommand(List<DoctorDto> doctorDtoList) {
		List<DoctorCommand> list = new ArrayList<DoctorCommand>();
		
		for (DoctorDto doctorDto : doctorDtoList) {
			DoctorCommand command = new DoctorCommand();
			command.setDoctorDto(doctorDto);
			command.setDoctorMajor(getDoctorMajors(doctorDto.getDoctor_idx()));
			list.add(command);
		}
		return list;
	}
	
	public List<DoctorMajorWithSpeciesName> getDoctorMajors(int doctor_idx) {
		List<DoctorMajorWithSpeciesName> list = new ArrayList<DoctorMajorWithSpeciesName>();
		list = findDoctorDao.getDoctorMajorWithSpeciesName(doctor_idx);
		return list;
	}
	
	
	

}
