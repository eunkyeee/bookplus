package com.kh.myapp.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kh.myapp.bbs.dto.BbsDTO;


public interface BbsSvc {
	//게시글 작성
	int write(BbsDTO bbsDTO);
	//게시글 수정
	int modify(BbsDTO bbsDTO);
	//게시글 삭제
	int delete(String bnum);
	//게시글 보기
	BbsDTO view(String bnum);
	//게시글 목록보기
//	void list(HttpServletRequest request, Model model);
	void list(String reqPage, String searchType, String keyword, Model model);
	List<BbsDTO> list(); //전체
	List<BbsDTO> list(int startRec, int endRec); //검색어 없는 경우
	List<BbsDTO> list(int startRec, int endRec, String searchType, String keyword); //검색어 있는 경우
	//게시글 페이징
	
	//게시글 검색
	
	//게시글 답글
	int reply(BbsDTO bbsDTO);
	//게시글 총계
	int totalRec(); //1)검색어 없는 경우
	int searchTotalRec(String searchType, String keyword); //2)검색어 있는 경우
	
	
}
