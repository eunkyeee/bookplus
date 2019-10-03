package com.kh.myapp.login.dao;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.myapp.member.dto.MemberDTO;

@Repository
public class LoginDAOImplJDBC implements LoginDAO {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public int isMember(String id, String pw) {
		int cnt=0;
		StringBuffer sql=new StringBuffer();
		sql.append("select count(id) from member where id=? and pw=? ");
		
		cnt=jdbcTemplate.queryForObject(sql.toString(), new String[] {id, pw}, Integer.class);
		
		return cnt;
	}

	@Override
	public MemberDTO getMember(String id, String pw) {
		MemberDTO memberDTO= new MemberDTO();
		StringBuffer sql=new StringBuffer();
		sql.append("select id,pw,tel,nickname,gender,region ");
		sql.append("from member ");
		sql.append("where id=? and pw=? ");
		
		memberDTO=jdbcTemplate.queryForObject(sql.toString(), new String[] {id,pw}, new BeanPropertyRowMapper<>(MemberDTO.class));
		
		return memberDTO;
	}

}
