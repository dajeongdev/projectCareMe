package com.careme.model.command;

import java.util.List;

import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.DoctorMajorWithSpeciesName;

public class DoctorCommand {
	private DoctorDto doctorDto;
	private List<DoctorMajorWithSpeciesName> doctorMajor;
	public DoctorDto getDoctorDto() {
		return doctorDto;
	}
	public void setDoctorDto(DoctorDto doctorDto) {
		this.doctorDto = doctorDto;
	}
	public List<DoctorMajorWithSpeciesName> getDoctorMajor() {
		return doctorMajor;
	}
	public void setDoctorMajor(List<DoctorMajorWithSpeciesName> doctorMajor) {
		this.doctorMajor = doctorMajor;
	}
	@Override
	public String toString() {
		return "DoctorCommand [doctorDto=" + doctorDto + ", doctorMajor=" + doctorMajor + "]";
	}

}
