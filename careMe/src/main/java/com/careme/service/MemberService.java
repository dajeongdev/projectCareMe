package com.careme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.MemberDao;
import com.careme.model.command.LoginCommand;
import com.careme.model.dto.MemberDto;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;

	public MemberDao getDao() {
		return dao;
	}

	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	//로그인 성공
	public int loginOk(LoginCommand lc) {
		List<MemberDto> lok = dao.login(lc);
		return lok.size();
	}
	//중복아이디체크
	public int idcheck(LoginCommand lc) {
		List<MemberDto> lok = dao.idChk(lc);
		return lok.size();
	}
	
	//회원가입 성공
	public int insertOk(MemberDto mdto) {
		List<MemberDto> lok = dao.insert(mdto);
		return lok.size();
	}
}
