package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.MemberDao;
import com.careme.model.command.LoginCommand;
import com.careme.model.command.SessionCommand;
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
	
	//회원정보 가져오기
	public MemberDto memberInfo(String id) {
		return dao.selectMember(id);
	}

	
	public void setSession(HttpSession session, LoginCommand lc) {
		MemberDto member = memberInfo(lc.getMember_id());
		
		SessionCommand sc = new SessionCommand();
		session.setAttribute("sc", sc);
		
		sc.setMember_idx(member.getMember_idx());
		sc.setMember_id(member.getMember_id());
		//sc.setMember_nick(member.getMember_nick());
	}
	
	
	
	//중복아이디체크
	public int idcheck(LoginCommand lc) {
		return dao.idChk(lc);
	}
	
	//회원가입 성공
	public int insertOk(MemberDto mdto) {
		List<MemberDto> lok = dao.insert(mdto);
		return lok.size();
	}
	
	
}
