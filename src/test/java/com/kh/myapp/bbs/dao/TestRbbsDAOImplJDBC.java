package com.kh.myapp.bbs.dao;

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

import com.kh.myapp.bbs.dto.RbbsDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestRbbsDAOImplJDBC {
	private static Logger logger=LoggerFactory.getLogger(TestRbbsDAOImplJDBC.class);

	@Inject
	RbbsDAO rbbsDAO;
	
	//댓글 등록
	@Test
	@Disabled
	void testWrite() {
		RbbsDTO rbbsDTO=new RbbsDTO();
		rbbsDTO.setBnum(10);
		rbbsDTO.setRid("test@test.com");
		rbbsDTO.setRnickname("댓글작성자");
		rbbsDTO.setRcontent("댓글작성내용");
		
		int cnt=rbbsDAO.write(rbbsDTO);
		assertEquals(1,cnt);
	}
	
	@Test
	@Disabled
	void testWrite2() {
		for(int i=0; i<25; i++) {
			
		RbbsDTO rbbsDTO=new RbbsDTO();
		rbbsDTO.setBnum(10);
		rbbsDTO.setRid("rereply_"+i+"@test.com");
		rbbsDTO.setRnickname("댓글작성자_"+i);
		rbbsDTO.setRcontent("댓글작성내용_"+i);
		
		int cnt=rbbsDAO.write(rbbsDTO);
//		assertEquals(1,cnt);
		}
	}
	
	//댓글 목록
	@Test
	@Disabled
	void testList() {
		List<RbbsDTO> list=rbbsDAO.list("10");
		logger.info(list.toString());
	}
	
	//댓글 목록2
	@Test
//	@Disabled
	void testList2() {
		List<RbbsDTO> list=rbbsDAO.list("10",11,20);
		logger.info(list.toString());
		assertEquals(10,list.size());
	}
	
	//댓글 수정
	@Test
	@Disabled
	void testModify() {
		RbbsDTO rbbsDTO=new RbbsDTO();
		rbbsDTO.setRnum(22);
		rbbsDTO.setRcontent("[수정]댓글작성내용_10");
		
		int cnt=rbbsDAO.modify(rbbsDTO);
		assertEquals(1,cnt);
	}
	
	//댓글 삭제
	@Test
	@Disabled
	void testDelete() {
		int cnt=rbbsDAO.delete("21");
		assertEquals(1,cnt);
		
	}
	
	//댓글 호감 비호감
	@Test
	@Disabled
	void testGoodOrBad() {
		int cnt=rbbsDAO.goodOrBad("22", "bad");
		assertEquals(1,cnt);
	}
	
	//대댓글 등록
	@Test
	@Disabled
	void testReply() {
//		RbbsDTO rbbsDTO=new RbbsDTO();
//		rbbsDTO.setRnum(122);
//		rbbsDTO.setRid("rereply2@test.com");
//		rbbsDTO.setRnickname("대댓글등록자");
//		rbbsDTO.setRcontent("대댓글내용");
//		
//		int cnt=rbbsDAO.reply(rbbsDTO);
//		assertEquals(1,cnt);
//		
		RbbsDTO rbbsDTO=new RbbsDTO();
		rbbsDTO.setRnum(147);
		rbbsDTO.setRid("rereply3@test.com");
		rbbsDTO.setRnickname("대댓글등록자2");
		rbbsDTO.setRcontent("대댓글내용");
		rbbsDTO.setRrnum(1);
		
		int cnt=rbbsDAO.reply(rbbsDTO);
		assertEquals(1,cnt);

	}
	
	//대댓글 총계
	@Test
	@Disabled
	void testReplyTotalRec() {
		int cnt=rbbsDAO.replyTotalRec("10");
		assertEquals(24,cnt);
	}
	

}
