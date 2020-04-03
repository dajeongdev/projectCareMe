package com.careme.model.dto;

import java.time.LocalDateTime;

public class PetDto {
	private int pet_idx;
	private int member_idx;
	private String name;
	private int pet_species_idx;
	private String profile_image;
	private String is_neutralized;
	private String birth;
	private String gender;
	private Double weight;
	private String vaccination;
	private String blood_type;
	private int registration_number;
	private String memo;
	private LocalDateTime reg_date;
	private String del_yn;
	
	public int getPet_idx() {
		return pet_idx;
	}
	public void setPet_idx(int pet_idx) {
		this.pet_idx = pet_idx;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPet_species_idx() {
		return pet_species_idx;
	}
	public void setPet_species_idx(int pet_species_idx) {
		this.pet_species_idx = pet_species_idx;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getIs_neutralized() {
		return is_neutralized;
	}
	public void setIs_neutralized(String is_neutralized) {
		this.is_neutralized = is_neutralized;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getVaccination() {
		return vaccination;
	}
	public void setVaccination(String vaccination) {
		this.vaccination = vaccination;
	}
	public String getBlood_type() {
		return blood_type;
	}
	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}
	public int getRegistration_number() {
		return registration_number;
	}
	public void setRegistration_number(int registration_number) {
		this.registration_number = registration_number;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public LocalDateTime getReg_date() {
		return reg_date;
	}
	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
	@Override
	public String toString() {
		return "PetDto [pet_idx=" + pet_idx + ", member_idx=" + member_idx + ", name=" + name + ", pet_species_idx="
				+ pet_species_idx + ", profile_image=" + profile_image + ", is_neutralized=" + is_neutralized
				+ ", birth=" + birth + ", gender=" + gender + ", weight=" + weight + ", vaccination=" + vaccination
				+ ", blood_type=" + blood_type + ", registration_number=" + registration_number + ", memo=" + memo
				+ ", reg_date=" + reg_date + ", del_yn=" + del_yn + "]";
	}

}
