package com.careme.model.command;

public class SessionCommand {
	private int member_idx;
	private String member_id;
	private String member_nick;
	private int doctor_idx;
	private int pet_idx;
	
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public int getDoctor_idx() {
		return doctor_idx;
	}
	public void setDoctor_idx(int doctor_idx) {
		this.doctor_idx = doctor_idx;
	}
	public int getPet_idx() {
		return pet_idx;
	}
	public void setPet_idx(int pet_idx) {
		this.pet_idx = pet_idx;
	}
	@Override
	public String toString() {
		return "SessionCommand [member_idx=" + member_idx + ", member_id=" + member_id + ", member_nick=" + member_nick
				+ ", doctor_idx=" + doctor_idx + ", pet_idx=" + pet_idx + "]";
	}
	
	

}
