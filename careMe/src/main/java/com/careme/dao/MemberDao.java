package com.careme.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.LoginCommand;
import com.careme.model.dto.MemberDto;

public class MemberDao extends SqlSessionDaoSupport {
	
	
	
	public List<MemberDto> selectAll() {
		return getSqlSession().selectList("member.selectAll");		
	}
	
	//로그인
	public List<MemberDto> login(LoginCommand lc){
		return getSqlSession().selectList("member.login", lc);
	}
	
	//
	public MemberDto selectMember(String id) {
		return getSqlSession().selectOne("member.selectMember", id);
	}
	
	//아이디 중복체크
	public int idChk(LoginCommand lc){
		return getSqlSession().selectOne("member.idchk", lc);
	}
	
	//닉네임 중복체크
	public int nickChk(LoginCommand lc) {
		return getSqlSession().selectOne("member.nickchk", lc);
	}
	
	//회원가입
	public List<MemberDto> insert(MemberDto mdto){
		return getSqlSession().selectList("member.insert", mdto);
	}
	
	//정보수정
	public List<MemberDto> update(MemberDto mdto){
		return getSqlSession().selectList("member.update", mdto);
	}
	
	//회원탈퇴
	public List<MemberDto> delete(MemberDto mdto){
		return getSqlSession().selectList("member.delete",mdto);
	}
}
