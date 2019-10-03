package com.kh.myapp.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.myapp.bbs.dto.BbsDTO;


@Repository
public class BbsDAOImplXML implements BbsDAO {

	@Inject
	SqlSession sqlSession; //SqlSessionTemplate 접근
	
	//게시글 작성
	@Override
	public int write(BbsDTO bbsDTO) {

															//매퍼파일의 네이스페이스.매퍼파일내의 id명
		return sqlSession.insert("mappers.bbsdaoimplxml-mapper.write",bbsDTO);
	}

	//게시글 수정
	@Override
	public int modify(BbsDTO bbsDTO) {

		return sqlSession.update("mappers.bbsdaoimplxml-mapper.modify",bbsDTO);
	}

	//게시글 삭제
	@Override
	public int delete(String bnum) {

		return sqlSession.delete("mappers.bbsdaoimplxml-mapper.delete",bnum);
	}

	//게시글 보기
	@Override
	public BbsDTO view(String bnum) {
		BbsDTO bbsDTO=null;
		bbsDTO=sqlSession.selectOne("mappers.bbsdaoimplxml-mapper.view",bnum);
		
		//조회수증가
		updateHit(bnum);
		
		return bbsDTO;
	}
	//조회수 증가
	private void updateHit(String bnum) {
		sqlSession.update("mappers.bbsdaoimplxml-mapper.updateHit",bnum);
	}

	//게시글 목록
	//1)전체
	@Override
	public List<BbsDTO> list() {

		return sqlSession.selectList("mappers.bbsdaoimplxml-mapper.list");
		
	}

	//2)검색어 없는 경우
	@Override
	public List<BbsDTO> list(int startRec, int endRec) {
		Map<String,Object> map=new HashMap<>();
		map.put("startRec",startRec);
		map.put("endRec",endRec);
		return sqlSession.selectList("mappers.bbsdaoimplxml-mapper.list2",map);
	}

	//3)검색어 있는 경우
	@Override
	public List<BbsDTO> list(int startRec, int endRec, String searchType, String keyword) {
		Map<String,Object> map=new HashMap<>();
		map.put("startRec",startRec);
		map.put("endRec",endRec);
		map.put("searchType",searchType);
		map.put("keyword",keyword);
		return sqlSession.selectList("mappers.bbsdaoimplxml-mapper.list3",map);
	}

	//게시글 답글
	@Override
	public int reply(BbsDTO bbsDTO) {

		//1)이전 답글 step업데이트
		updateStep(bbsDTO.getBgroup(), bbsDTO.getBstep());
		//2)답글 달기
		return sqlSession.insert("mappers.bbsdaoimplxml-mapper.reply",bbsDTO);
	}

	//이전 답글 step업데이트
	private void updateStep(int bgroup, int bstep) {
		Map<String,Object> map=new HashMap<>();
		map.put("bgroup",bgroup);
		map.put("bstep",bstep);
		sqlSession.update("mappers.bbsdaoimplxml-mapper.updateStep",map);
	}

	//게시글 총계
	//1)검색어 없는 경우
	@Override
	public int totalRec() {

		return sqlSession.selectOne("mappers.bbsdaoimplxml-mapper.totalRec");
	}

	//2)검색어 있는 경우
	@Override
	public int searchTotalRec(String searchType, String keyword) {
		Map<String,Object> map=new HashMap<>();
		map.put("searchType",searchType);
		map.put("keyword",keyword);
		return sqlSession.selectOne("mappers.bbsdaoimplxml-mapper.searchTotalRec",map);
	}

}
