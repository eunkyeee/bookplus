package com.kh.myapp.bbs.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class BbsDTO {

	private int bnum;         //게시글 번호
	
	@NotNull
	@Size(min=4,max=30,message="제목은 4~30자까지 입력 가능합니다.")
	private String btitle;    //제목
	@NotNull
	private String bid;       //작성자ID
	@NotNull
	private String bnickname; //작성자(별칭)
	private Date bcdate;      //작성일
	private Date budate;      //수정일
	private int bhit;         //조회수
	
	@NotNull
	@Size(min=4,max=100,message="내용은10~1000자까지 입력 가능합니다.")
	private String bcontent;  //본문내용
	private int bgroup;       //답글그룹
	private int bstep;        //답글의 단계
	private int bindent;      //답글의 줄여쓰기
}
