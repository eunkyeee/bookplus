package com.kh.myapp.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.myapp.member.dao.MemberDAO;
import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.dto.PasswordDTO;
@Service
public class MemberSvcImpl implements MemberSvc {
	
	@Inject
	@Qualifier("memberDAOImplXML") //동일타입이 있는 경우 id값으로 빈객체를 주입받을 때 사용
	MemberDAO memberDAO;

	@Override
	public int insert(MemberDTO memberDTO) {
		
		return memberDAO.insert(memberDTO);
	}

	@Override
	public int modify(MemberDTO memberDTO) {

		return memberDAO.modify(memberDTO);
	}

	@Override
	public int delete(String id, String pw) {

		return memberDAO.delete(id);
	}

	@Override
	public int delete(String id) {

		return memberDAO.delete(id);
	}

	@Override
	public MemberDTO getMember(String id) {

		return memberDAO.getMember(id);
	}

	@Override
	public List<MemberDTO> getMemberList() {

		return memberDAO.getMemberList();
	}

	@Override
	public int changePw(PasswordDTO passwdDTO) {
		
		return memberDAO.changePw(passwdDTO);
	}

}
