package com.careme.model.dto;

import java.time.LocalDateTime;

public class DoctorDto {

	private int doctor_idx;
	private int member_idx;
	private String doctor_name;
	private String doctor_license;
	private String doctor_profile_image;
	private String doctor_hospital_name;
	private String doctor_hospital_tel;
	private String doctor_hospital_address;
	private int doctor_hospital_zipcode;
	private String certification_document;
	private String is_certified;
	private LocalDateTime reg_date;
	private LocalDateTime update_date;
	private String del_yn;
	private String doctor_hospital_address_detail;

	public int getDoctor_idx() {
		return doctor_idx;
	}

	public void setDoctor_idx(int doctor_idx) {
		this.doctor_idx = doctor_idx;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDoctor_license() {
		return doctor_license;
	}

	public void setDoctor_license(String doctor_license) {
		this.doctor_license = doctor_license;
	}

	public String getDoctor_profile_image() {
		return doctor_profile_image;
	}

	public void setDoctor_profile_image(String doctor_profile_image) {
		this.doctor_profile_image = doctor_profile_image;
	}

	public String getDoctor_hospital_name() {
		return doctor_hospital_name;
	}

	public void setDoctor_hospital_name(String doctor_hospital_name) {
		this.doctor_hospital_name = doctor_hospital_name;
	}

	public String getDoctor_hospital_tel() {
		return doctor_hospital_tel;
	}

	public void setDoctor_hospital_tel(String doctor_hospital_tel) {
		this.doctor_hospital_tel = doctor_hospital_tel;
	}

	public String getDoctor_hospital_address() {
		return doctor_hospital_address;
	}

	public void setDoctor_hospital_address(String doctor_hospital_address) {
		this.doctor_hospital_address = doctor_hospital_address;
	}

	public int getDoctor_hospital_zipcode() {
		return doctor_hospital_zipcode;
	}

	public void setDoctor_hospital_zipcode(int doctor_hospital_zipcode) {
		this.doctor_hospital_zipcode = doctor_hospital_zipcode;
	}

	public String getCertification_document() {
		return certification_document;
	}

	public void setCertification_document(String certification_document) {
		this.certification_document = certification_document;
	}

	public String getIs_certified() {
		return is_certified;
	}

	public void setIs_certified(String is_certified) {
		this.is_certified = is_certified;
	}

	public LocalDateTime getReg_date() {
		return reg_date;
	}

	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getDoctor_hospital_address_detail() {
		return doctor_hospital_address_detail;
	}

	public void setDoctor_hospital_address_detail(String doctor_hospital_address_detail) {
		this.doctor_hospital_address_detail = doctor_hospital_address_detail;
	}

	@Override
	public String toString() {
		return "DoctorDto [doctor_idx=" + doctor_idx + ", member_idx=" + member_idx + ", doctor_name=" + doctor_name
				+ ", doctor_license=" + doctor_license + ", doctor_profile_image=" + doctor_profile_image
				+ ", doctor_hospital_name=" + doctor_hospital_name + ", doctor_hospital_tel=" + doctor_hospital_tel
				+ ", doctor_hospital_address=" + doctor_hospital_address + ", doctor_hospital_zipcode="
				+ doctor_hospital_zipcode + ", certification_document=" + certification_document + ", is_certified="
				+ is_certified + ", reg_date=" + reg_date + ", update_date=" + update_date + ", del_yn=" + del_yn
				+ ", doctor_hospital_address_detail=" + doctor_hospital_address_detail + "]";
	}

}
