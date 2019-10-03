package com.kh.myapp.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.member.dao.TestMemberDAOImplJDBC;
import com.kh.myapp.member.dto.MemberDTO;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestMemberSvcImpl {
	private static final Logger logger=LoggerFactory.getLogger(TestMemberDAOImplJDBC.class);
	
	@Inject
	MemberSvc memberSvc; //new붙일 필요 없음
	
	@Test
	@Disabled
	void testInsert() {
		MemberDTO mdto=new MemberDTO();
		mdto.setId("test@test.com");
		mdto.setPw("1234");
		mdto.setTel("010-1234-5678");
		mdto.setNickname("한아름");
		mdto.setGender("여");
		mdto.setRegion("울산");
		mdto.setBirth("2000-01-01");
		
		int cnt=memberSvc.insert(mdto);
		assertEquals(1, cnt);
		
	}
	@Test
	void testModify() {
		MemberDTO mdto=new MemberDTO();
		mdto.setId("test@test.com");
		mdto.setPw("1234");
		mdto.setTel("010-1234-5678");
		mdto.setNickname("한아름2");
		mdto.setGender("여");
		mdto.setRegion("울산");
		mdto.setBirth("2000-01-01");
		
		int cnt=memberSvc.modify(mdto);
		assertEquals(1, cnt);
		
	}
	
	@Test
	@Disabled
	void testDelete() {
		int cnt=memberSvc.delete("test@test.com");
		assertEquals(1, cnt);
	}
	
	@Test

	void TestGetMember() {
		MemberDTO mdto=memberSvc.getMember("test@test.com");
		logger.info(mdto.toString());
		
	}
	
	@Test
	void TestGetMemberList() {
		List<MemberDTO> list=memberSvc.getMemberList();
		logger.info(list.toString());
	}
}
