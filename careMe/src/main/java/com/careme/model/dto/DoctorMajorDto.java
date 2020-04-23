package com.careme.model.dto;

public class DoctorMajorDto {
	private int doctor_major_idx;
	private int doctor_idx;
	private int pet_species_idx;
	public int getDoctor_major_idx() {
		return doctor_major_idx;
	}
	public void setDoctor_major_idx(int doctor_major_idx) {
		this.doctor_major_idx = doctor_major_idx;
	}
	public int getDoctor_idx() {
		return doctor_idx;
	}
	public void setDoctor_idx(int doctor_idx) {
		this.doctor_idx = doctor_idx;
	}
	public int getPet_species_idx() {
		return pet_species_idx;
	}
	public void setPet_species_idx(int pet_species_idx) {
		this.pet_species_idx = pet_species_idx;
	}
	@Override
	public String toString() {
		return "DoctorMajorDto [doctor_major_idx=" + doctor_major_idx + ", doctor_idx=" + doctor_idx + ", pet_species_idx="
				+ pet_species_idx + "]";
	}
	
	

}
