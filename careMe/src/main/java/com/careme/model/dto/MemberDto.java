package com.careme.model.dto;

import java.time.LocalDateTime;

public class MemberDto {
	private int member_idx;
	private String member_id;
	private String member_pass;
	private String member_email;
	private String member_phone;
	private String member_token;
	private String member_join_type;
	private LocalDateTime reg_date;
	private String member_nick;
	private String del_yn;
	private String check_num;
	
	
	
	public String getCheck_num() {
		return check_num;
	}

	public void setCheck_num(String check_num) {
		this.check_num = check_num;
	}

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
	
	public String getMember_pass() {
		return member_pass;
	}
	
	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}
	
	public String getMember_email() {
		return member_email;
	}
	
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	
	public String getMember_phone() {
		return member_phone;
	}
	
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	
	public String getMember_token() {
		return member_token;
	}
	
	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}
	
	public String getMember_join_type() {
		return member_join_type;
	}
	
	public void setMember_join_type(String member_join_type) {
		this.member_join_type = member_join_type;
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

	public String getMember_nick() {
		return member_nick;
	}

	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}

	@Override
	public String toString() {
		return "MemberDto [member_idx=" + member_idx + ", member_id=" + member_id + ", member_pass=" + member_pass
				+ ", member_email=" + member_email + ", member_phone=" + member_phone + ", member_token=" + member_token
				+ ", member_join_type=" + member_join_type + ", reg_date=" + reg_date + ", member_nick=" + member_nick
				+ ", del_yn=" + del_yn + ", check_num=" + check_num + "]";
	}

}
