package com.kh.myapp.bbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kh.myapp.bbs.dao.RbbsDAO;
import com.kh.myapp.bbs.dto.RbbsDTO;

@Service
public class RbbsSvcImpl implements RbbsSvc {

private static Logger logger=LoggerFactory.getLogger(BbsSvcImpl.class);
	
	@Inject
	RbbsDAO rbbsDAO;
	
	@Override
	public int write(RbbsDTO rbbsDTO) {
		
		return rbbsDAO.write(rbbsDTO);
	}

	@Override
	public List<RbbsDTO> list(String bnum) {
		
		return rbbsDAO.list(bnum);
	}

	@Override
	public List<RbbsDTO> list(String bnum, int startRec, int endRec) {
		
		return rbbsDAO.list(bnum, startRec, endRec);
	}

	@Override
	public int modify(RbbsDTO rbbsDTO) {
		
		return rbbsDAO.modify(rbbsDTO);
	}

	@Override
	public int delete(String rnum) {
		
		return rbbsDAO.delete(rnum);
	}

	@Override
	public int goodOrBad(String rnum, String goodOrBad) {
		
		return rbbsDAO.goodOrBad(rnum, goodOrBad);
	}

	@Override
	public int reply(RbbsDTO rbbsDTO) {
		
		return rbbsDAO.reply(rbbsDTO);
	}

	@Override
	public int replyTotalRec(String bnum) {
		
		return rbbsDAO.replyTotalRec(bnum);
	}

}
