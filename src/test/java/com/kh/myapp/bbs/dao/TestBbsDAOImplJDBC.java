package com.kh.myapp.bbs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.myapp.bbs.dto.BbsDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestBbsDAOImplJDBC {

	private static Logger logger=LoggerFactory.getLogger(TestBbsDAOImplJDBC.class);

	@Inject
	BbsDAO bbsDAO;
	
	//게시글 작성
	@Test
	@Disabled
	void testWrite() {
		BbsDTO bbsDTO=new BbsDTO();
		bbsDTO.setBtitle("제목1");
		bbsDTO.setBid("test1@test.com");
		bbsDTO.setBnickname("nick1");
		bbsDTO.setBcontent("내용1");
		
		int cnt=bbsDAO.write(bbsDTO);
		assertEquals(1, cnt);
	}
	
	//게시글 수정
	@Test
	@Disabled
	void testModify() {
		BbsDTO bbsDTO=new BbsDTO();
		bbsDTO.setBnum(1);
		bbsDTO.setBtitle("[수정]제목1");
		bbsDTO.setBcontent("[수정]내용1");
		
		int cnt=bbsDAO.modify(bbsDTO);
		assertEquals(1, cnt);
	}
	
	//게시글 삭제
	@Test
	@Disabled
	void TestDelete() {
		
		int cnt=bbsDAO.delete("1");
		assertEquals(1,cnt);
		
	}
	
	
	//게시글 보기
	@Test
	@Disabled
	void testview() {
		
		BbsDTO bbsDTO=bbsDAO.view("2");
		boolean result=bbsDTO.getBnum()==2;
		assertTrue(result);
		logger.info(bbsDTO.toString());
	}
	
	//게시글 목록보기
	//전체
	@Test
	@Disabled
	void testList() {
		List<BbsDTO> list=bbsDAO.list();
		logger.info(list.toString());
		
		assertEquals(6, list.size());
	}
	
	//검색어 없는 경우
	@Test
	@Disabled
	void testList2(){ 
		List<BbsDTO> list=bbsDAO.list(11,20); //(11,20)-> 2페이지
		logger.info(list.toString());
		
		assertEquals(5, list.size()); //11~20사이에서 5개
	} 
	
	//검색어 있는 경우
	@Test
	@Disabled
	void testList3(){
		List<BbsDTO> list=bbsDAO.list(11,20,"T","제목");
		logger.info(list.toString());
		
		assertEquals(5, list.size());
	}
	
	//게시글 페이징
	//게시글 검색
	
	//게시글 답글
	@Test
	@Disabled
	void testReply() {
		BbsDTO bbsDTO=new BbsDTO();
		bbsDTO.setBtitle("[답글]10번글");
		bbsDTO.setBid("reply@test.com");
		bbsDTO.setBnickname("답글작성자");
		bbsDTO.setBcontent("10번글에 대한 답글내용");
		bbsDTO.setBgroup(10);
		bbsDTO.setBstep(0);
		bbsDTO.setBindent(0);
		
		int cnt=bbsDAO.reply(bbsDTO);
		assertEquals(1,cnt);
	}
	
	//게시글 총계
	//1)검색어 없는 경우
	@Test
	@Disabled
	void testTotalRec() {
		int cnt=bbsDAO.totalRec();
		assertEquals(16,cnt);
	}
	
	//2)검색어 있는 경우
	@Test
	@Disabled
	void testSearchTotalRec() {
		int cnt=bbsDAO.searchTotalRec("C", "답글");
		assertEquals(1,cnt);
	}
}
