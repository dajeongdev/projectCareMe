package com.careme.model.command;

import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.MemberDto;

public class SessionCommand {
	private MemberDto memberDto;
	private DoctorDto doctorDto;
	private int pet_idx;
	
	public MemberDto getMemberDto() {
		return memberDto;
	}
	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}
	public DoctorDto getDoctorDto() {
		return doctorDto;
	}
	public void setDoctorDto(DoctorDto doctorDto) {
		this.doctorDto = doctorDto;
	}
	public int getPet_idx() {
		return pet_idx;
	}
	public void setPet_idx(int pet_idx) {
		this.pet_idx = pet_idx;
	}
	
	@Override
	public String toString() {
		return "SessionCommand [memberDto=" + memberDto + ", doctorDto=" + doctorDto + ", pet_idx=" + pet_idx + "]";
	}
	

}
