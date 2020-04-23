package com.careme.model.command;

import java.util.List;

import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.DoctorMajorDto;

public class DoctorCommand {
	private DoctorDto doctorDto;
	private List<DoctorMajorDto> doctorMajorDto;
	public DoctorDto getDoctorDto() {
		return doctorDto;
	}
	public void setDoctorDto(DoctorDto doctorDto) {
		this.doctorDto = doctorDto;
	}
	public List<DoctorMajorDto> getDoctorMajorDto() {
		return doctorMajorDto;
	}
	public void setDoctorMajorDto(List<DoctorMajorDto> doctorMajorDto) {
		this.doctorMajorDto = doctorMajorDto;
	}
	
	@Override
	public String toString() {
		return "DoctorCommand [doctorDto=" + doctorDto + ", doctorMajorDto=" + doctorMajorDto + "]";
	}

}
