package com.kh.myapp.login.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.myapp.member.dao.MemberDAOImplJDBC;
import com.kh.myapp.member.dao.TestMemberDAOImplJDBC;
import com.kh.myapp.member.dto.MemberDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestLoginDAOImplJDBC {
	
	private final static Logger logger=LoggerFactory.getLogger(TestMemberDAOImplJDBC.class);
	
	@Inject
	private LoginDAO ldao;

	@Test
	void testisMember() {
		int cnt=ldao.isMember("test@test.com","1234");
		assertEquals(1, cnt);
	}

	@Test
	void testgetMember() {
		MemberDTO mdto=ldao.getMember("test@test.com","1234");
		logger.info(mdto.toString());
	}
}
