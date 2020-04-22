package com.careme.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.dao.MemberDao;
import com.careme.model.command.FileUploadCommand;
import com.careme.model.command.LoginCommand;
import com.careme.model.command.SessionCommand;
import com.careme.model.dto.DoctorDto;
import com.careme.model.dto.DoctorMajor;
import com.careme.model.dto.MemberDto;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;

	@Autowired
	FileUploadService fileUploadService;

	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	public MemberDao getDao() {
		return dao;
	}

	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	@Autowired
	PetService petService;

	public void setPetService(PetService petService) {
		this.petService = petService;
	}

	// 로그인 성공
	public int loginOk(LoginCommand lc) {
		List<MemberDto> lok = dao.login(lc);
		return lok.size();
	}

	// 회원정보 가져오기
	public MemberDto memberInfo(String id) {
		return dao.selectMember(id);
	}

	// 의사정보
	public DoctorDto selectDoctor(int member_idx) {
		return dao.selectDoctor(member_idx);
	}

	// 서비스
	public void setSession(HttpSession session, LoginCommand lc) {
		MemberDto member = memberInfo(lc.getMember_id());

		SessionCommand sc = new SessionCommand();
		sc.setMemberDto(member);
		// System.out.println(member);
		DoctorDto doctorDto = dao.selectDoctor(member.getMember_idx());
		sc.setDoctorDto(doctorDto);
		System.out.println("닥터 정보" + doctorDto);
		System.out.println(doctorDto);
		// int pet_idx = petService.findSelectedPet(member_idx);
		// sc.setPet_idx(pet_idx);

		session.setAttribute("sc", sc);
		// System.out.println(sc);
		session.setAttribute("MINFO", member.getMember_nick());// 이제 닉네임 들고다님
	}

	// 중복아이디체크
	public int idcheck(LoginCommand lc) {
		return dao.idChk(lc);
	}

	// 중복 닉네임체크
	public int ncheck(LoginCommand lc) {
		return dao.nickChk(lc);
	}

	// 중복 이메일 체크
	public int mailcheck(LoginCommand lc) {
		return dao.mailChk(lc);
	}

	// 회원가입 성공
	public int insertOk(MemberDto mdto) {
		List<MemberDto> lok = dao.insert(mdto);
		return lok.size();
	}

	// 의사등록 성공
	public int dinsertOk(DoctorDto ddto, MultipartHttpServletRequest request) {
		//System.out.println("서비스 도착1");
		List<FileUploadCommand> list = fileUploadService.upload(request, "/img/doctor/");
		if (list.size() > 0) {
			FileUploadCommand file = list.get(0);
			ddto.setCertification_document(file.getFilePath());
		}
		//System.out.println("서비스 도착2");
		int dok = dao.dinsert(ddto);
		return dok;
	}


	// 정보수정
	public List<MemberDto> updateOk(MemberDto mdto) {
		return dao.update(mdto);
	}

	// 비밀번호 수정
	public List<MemberDto> updatePwOk(MemberDto mdto) {
		return dao.updatePw(mdto);
	}

	// 의사 정보수정
	public List<DoctorDto> dupdateOk(DoctorDto ddto) {
		return dao.dupdate(ddto);
	}

	// 회원탈퇴
	public int deleteOk(MemberDto mdto) {
		return dao.delete(mdto);
	}

}
