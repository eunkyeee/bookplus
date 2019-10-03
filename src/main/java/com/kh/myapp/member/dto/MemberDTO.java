package com.kh.myapp.member.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class MemberDTO {
	
	@NotNull
	@Pattern(regexp="^\\w+@\\w+\\.\\w+(\\.\\.w+)?$",message="ex)aa@bb.com")
	private String id;       //아이디(이메일)
	
	@NotNull
	@Size(min=6,max=10,message="비밀번호는 6~10자리로 입력하세요.")
	private String pw;       //비밀번호
	
	@NotNull
	@Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$",message="ex)010-1234-5678")
	private String tel;      //전화번호
	
	@NotNull
	@Size(min=2,max=10,message="닉네임은 2~10자리로 입력하세요.")
	private String nickname; //닉네임
	
	private String gender;   //성별
	private String region;   //지역	
	private String birth;    //생년월일
	private Timestamp cdate;    //가입일
	private Timestamp udate;    //수정일
	
}

