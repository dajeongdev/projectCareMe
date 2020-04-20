package com.careme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.careme.model.command.LoginCommand;
import com.careme.model.dto.MemberDto;
import com.careme.model.dto.DocterDto;

public class MemberDao extends SqlSessionDaoSupport {

	public List<MemberDto> selectAll() {
		return getSqlSession().selectList("member.selectAll");
	}

	// 로그인
	public List<MemberDto> login(LoginCommand lc) {
		return getSqlSession().selectList("member.login", lc);
	}

	//
	public MemberDto selectMember(String id) {
		return getSqlSession().selectOne("member.selectMember", id);
	}

	// 아이디 중복체크
	public int idChk(LoginCommand lc) {
		return getSqlSession().selectOne("member.idchk", lc);
	}

	// 닉네임 중복체크
	public int nickChk(LoginCommand lc) {
		return getSqlSession().selectOne("member.nickchk", lc);
	}

	// 이메일 중복체크
	public int mailChk(LoginCommand lc) {
		return getSqlSession().selectOne("member.mailchk", lc);
	}

	// 회원가입
	public List<MemberDto> insert(MemberDto mdto) {
		return getSqlSession().selectList("member.insert", mdto);
	}

	// 의사등록
	public List<DocterDto> dinsert(DocterDto ddto) {
		return getSqlSession().selectList("doctor.dinsert", ddto);
	}

	// 정보수정 일반
	public int update(MemberDto mdto) {
		return getSqlSession().update("member.update", mdto);
	}

	// 정보수정 의사
	public List<DocterDto> dupdate(DocterDto ddto) {
		return getSqlSession().selectList("doctor.dupdate", ddto);
	}


	// 회원탈퇴
	public int delete(MemberDto mdto) {
		return getSqlSession().update("member.delete", mdto);
	}
}
