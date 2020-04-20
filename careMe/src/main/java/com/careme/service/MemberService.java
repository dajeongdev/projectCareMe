package com.careme.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careme.dao.MemberDao;
import com.careme.model.command.LoginCommand;
import com.careme.model.command.SessionCommand;
import com.careme.model.dto.DocterDto;
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

	//서비스
	public void setSession(HttpSession session, LoginCommand lc) {
		
		MemberDto member = memberInfo(lc.getMember_id());
		System.out.println(member);
		session.setAttribute("sc", member);
		session.setAttribute("MINFO", member.getMember_nick());// 이제 닉네임 들고다님
	}
	
	//중복아이디체크
	public int idcheck(LoginCommand lc) {
		return dao.idChk(lc);
	}
	
	//중복 닉네임체크
	public int ncheck(LoginCommand lc) {
		return dao.nickChk(lc);
	}
	
	//중복 이메일 체크
	public int mailcheck(LoginCommand lc) {
		return dao.mailChk(lc);
	}
	
	//회원가입 성공
	public int insertOk(MemberDto mdto) {
		List<MemberDto> lok = dao.insert(mdto);
		return lok.size();
	}
	
	//의사등록 성공
	public int dinsertOk(DocterDto ddto) {
		List<DocterDto> dok= dao.dinsert(ddto);
		return dok.size();
	}
	
	//정보수정
	public int updateOk(MemberDto mdto){
		return dao.update(mdto);
	}
	
	//의사 정보수정
	public List<DocterDto> dupdate(DocterDto ddto){
		return dao.dupdate(ddto);
	}
	
	//회원탈퇴
	public int deleteOk(MemberDto mdto) {
		return dao.delete(mdto);
	}
	
}
