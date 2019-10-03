package com.kh.myapp.login.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.myapp.login.dao.LoginDAO;
import com.kh.myapp.member.dto.MemberDTO;

@Service
public class LoginSvcImpl implements LoginSvc {

	@Inject
	@Qualifier("loginDAOImplXML")
	private LoginDAO loginDAO;
	
	@Override
	public int isMember(String id, String pw) {
		
		return loginDAO.isMember(id, pw);
	}

	@Override
	public MemberDTO getMember(String id, String pw) {
		
		return loginDAO.getMember(id, pw);
	}

}
