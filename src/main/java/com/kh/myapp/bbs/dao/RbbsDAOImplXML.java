package com.kh.myapp.bbs.dao;

import java.util.List;

import com.kh.myapp.bbs.dto.RbbsDTO;

public class RbbsDAOImplXML implements RbbsDAO {

	@Override
	public int write(RbbsDTO rbbsDTO) {

		return 0;
	}

	@Override
	public List<RbbsDTO> list(String bnum) {

		return null;
	}

	@Override
	public List<RbbsDTO> list(String bnum, int startRec, int endRec) {

		return null;
	}

	@Override
	public int modify(RbbsDTO rbbsDTO) {

		return 0;
	}

	@Override
	public int delete(String rnum) {

		return 0;
	}

	@Override
	public int goodOrBad(String rnum, String goodOrBad) {

		return 0;
	}

	@Override
	public int reply(RbbsDTO rbbsDTO) {

		return 0;
	}

	@Override
	public int replyTotalRec(String bnum) {

		return 0;
	}

}
