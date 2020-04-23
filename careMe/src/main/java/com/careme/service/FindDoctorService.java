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
	PageNumberService pageNumberService;
	public void setPageNumberService(PageNumberService pageNumberService) {
		this.pageNumberService = pageNumberService;
	}
	
	
	// 전체 의사 리스트
	public Map<String, Object> getDoctors(int currentPage, int contentPerPage) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<DoctorDto> doctorDtoList = findDoctorDao.getDoctorDtoList();
		int totalCount = findDoctorDao.selectTotalCount();
		
		List<DoctorCommand> doctors = getDoctorCommand(doctorDtoList);
		data.put("doctors", doctors);
		
		String path = "findDoctor?page=";
		PageNumberCommand paging = pageNumberService.paging(totalCount, contentPerPage, 1, path);
		data.put("paging", paging);
		
		return data;
	}
	
	// member_idx를 넣으면 그 회원이 등록한 pet과 관련된 의사를 소환함
	public Map<String, Object> getDoctors(int currentPage, int contentPerPage, int member_idx) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<DoctorDto> doctorDtoList = findDoctorDao.getDoctorDtoListOrderByMatching(member_idx);
		int totalCount = findDoctorDao.selectTotalCount();
		
		List<DoctorCommand> doctors = getDoctorCommand(doctorDtoList);
		data.put("doctors", doctors);
		
		String path = "findDoctor?page=";
		PageNumberCommand paging = pageNumberService.paging(totalCount, contentPerPage, currentPage, path);
		data.put("paging", paging);
		
		return data;
	}
	
	public List<DoctorCommand> getPopularDoctors() {
		LocalDate now = LocalDate.now();
		String monthAgo = now.minusMonths(1).toString(); 
		List<DoctorDto> doctorDtoList = findDoctorDao.getPopularDoctorDtoList(monthAgo);
		System.out.println("인기의사:::::::" + doctorDtoList);
		
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
