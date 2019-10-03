package com.kh.myapp.member.service;

import java.util.List;

import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.dto.PasswordDTO;

public interface MemberSvc {
	//회원 등록
	int insert(MemberDTO memberDTO);
	
	//회원 수정
	int modify(MemberDTO memberDTO);
	
	//회원 삭제(회원용:탈퇴)
	int delete(String id, String pw);
	
	//회원 삭제(관리자용)
	int delete(String id);
	
	//회원 개인 조회
	MemberDTO getMember(String id);
	
	//회원 목록 조회(관리용)
	List<MemberDTO> getMemberList();
	
	//회원 비밀번호 변경
	int changePw(PasswordDTO passwdDTO);
}
