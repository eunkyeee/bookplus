package com.kh.myapp.member.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.dto.PasswordDTO;

@Repository   //@Controller, @Service  => @Component
public class MemberDAOImplJDBC implements MemberDAO {

	private static final Logger logger=LoggerFactory.getLogger(MemberDAOImplJDBC.class);
	
	@Inject //자바표준 어노테이션: 컨텍스트에 등록된 빈을 참조한다.  
	//or @Autowired //스프링 프레임워크에서 제공하는 어노테이션
	private JdbcTemplate jdbcTemplate;
	
	//회원 등록
	@Override
	public int insert(MemberDTO memberDTO) {
		int cnt=0;
		logger.info(memberDTO.toString());
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into member (id,pw,tel,gender,region,nickname,birth,cdate) ");
		sql.append("values(?,?,?,?,?,?,?,sysdate)"); 
		
		cnt=jdbcTemplate.update(sql.toString(), memberDTO.getId(), memberDTO.getPw(),
												memberDTO.getTel(),memberDTO.getGender(),memberDTO.getRegion(),memberDTO.getNickname(),memberDTO.getBirth());
		
		
		return cnt;
	}

	//회원 수정
	@Override
	public int modify(MemberDTO memberDTO) {
		int cnt=0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("update member ");
		sql.append("   set tel=?, nickname=?, gender=?, region=?, birth=?, udate=sysdate ");
		sql.append(" where id=? and pw=? ");
		
		cnt=jdbcTemplate.update(sql.toString(), memberDTO.getTel(), memberDTO.getNickname(),memberDTO.getGender(),
														memberDTO.getRegion(),memberDTO.getBirth(),memberDTO.getId(), memberDTO.getPw());
		return cnt;
	}

	//회원 삭제(회원용:탈퇴)
	@Override
	public int delete(String id, String pw) {
		int cnt=0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from member where id=? and pw=? ");
		
		cnt=jdbcTemplate.update(sql.toString(), id,pw);
		return cnt;
	}

	//회원 삭제(관리자용)
	@Override
	public int delete(String id) {
		int cnt=0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from member where id=? ");
		
		cnt=jdbcTemplate.update(sql.toString(), id);
		return cnt;
	}

	//회원 개인 조회
	@Override
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO=null;
		StringBuffer sql = new StringBuffer();
		sql.append("select id,pw,tel,nickname,gender,region,birth ");
		sql.append("  from member ");
		sql.append(" where id=? ");
		
		memberDTO=jdbcTemplate.queryForObject(sql.toString(), new Object[] {id},new BeanPropertyRowMapper<>(MemberDTO.class));
		return memberDTO;
	}

	//회원 목록 조회(관리자용)
	@Override
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> list=new ArrayList<MemberDTO>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select id,pw,tel,nickname,gender,region,birth,cdate,udate from member ");
		
		list=jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MemberDTO.class));
		return list;
	}

	@Override
	public int changePw(PasswordDTO passwdDTO) {
		
		return 0;
	}

}
