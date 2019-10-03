package com.kh.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.myapp.bbs.dto.RbbsDTO;
import com.kh.myapp.bbs.service.RbbsSvc;
import com.kh.myapp.common.PageCriteria;
import com.kh.myapp.common.RecordCriteria;

@RestController
@RequestMapping("/rbbs")
public class RbbsController {

	private static Logger logger=LoggerFactory.getLogger(RbbsController.class);
	
	@Inject
	RbbsSvc rbbsSvc;
	
	
	//댓글 작성
	@PostMapping(value="", produces = "application/json")
	public ResponseEntity<String> write(@RequestBody(required = true) RbbsDTO rbbsDTO){
		
		ResponseEntity<String> res = null;
		logger.info("write() 호출!");
		logger.info("rbbsDTO : " + rbbsDTO.toString()); 
		
		//필수값 체크
		if( rbbsDTO.getBnum() > 0 && 
				rbbsDTO.getRid() !=null &&
				rbbsDTO.getRnickname() !=null &&
				rbbsDTO.getRcontent() !=null) {
			
			rbbsSvc.write(rbbsDTO);
			res = new ResponseEntity<String>("success",HttpStatus.OK); //200			
		}else {
			res = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST); //400
		}
			
		return res;
	}
	
	//댓글 목록 조회
	@GetMapping(value="/{page}/{bnum}",produces="application/json")
	public ResponseEntity<Map<String,Object>> list(@PathVariable String page, 
																								 @PathVariable(required=true) String bnum){
		ResponseEntity<Map<String,Object>> res=null;
		Map<String,Object> map=new HashMap<>();
		
		try{
			
			RecordCriteria rc=new RecordCriteria(Integer.parseInt(page),10);
			PageCriteria pc=new PageCriteria(rc,rbbsSvc.replyTotalRec(bnum),10);		
			List<RbbsDTO> list=rbbsSvc.list(bnum, rc.getStartRecord(), rc.getEndRecord());
			map.put("item", list);
			map.put("pc", pc);
			res=new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch(Exception e) {
			res=new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
		
		return res;
	}
	
	//댓글 수정
	@PutMapping(value="",produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> modify(@RequestBody RbbsDTO rbbsDTO){
		ResponseEntity<String> res=null;
		
		if(rbbsDTO.getRcontent()==null || String.valueOf(rbbsDTO.getRnum())==null) {
			res=new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return res;
		}
		
		
		int cnt=rbbsSvc.modify(rbbsDTO);
		if(cnt==1) {
			res=new ResponseEntity<String>("success",HttpStatus.OK);
		
		}else {
			res=new ResponseEntity<String>("변경 실패.",HttpStatus.BAD_REQUEST);
		}
	
		return res;
	}
	
	//댓글 삭제
	@DeleteMapping(value="/{rnum}",produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> delete(@PathVariable String rnum){
		ResponseEntity<String> res=null;
		
		int cnt=rbbsSvc.delete(rnum);
		if(cnt==1) {
			res=new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			res=new ResponseEntity<String>("삭제 실패.",HttpStatus.BAD_REQUEST);
		}
		
		
		return res;
	}
	
	//대댓글 작성
	@PostMapping(value="/reply",produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> reply(@RequestBody(required=true) RbbsDTO rbbsDTO){
		ResponseEntity<String> res=null;
		
		if(String.valueOf(rbbsDTO.getRnum())==null||
				rbbsDTO.getRid()==null||
				rbbsDTO.getRnickname()==null||
				rbbsDTO.getRcontent()==null||
				rbbsDTO.getRrnum()==null) {
			return new ResponseEntity<String>("필수값 누락",HttpStatus.BAD_REQUEST);
		}	
		int cnt=rbbsSvc.reply(rbbsDTO);
		if(cnt==1) {
			res=new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			res=new ResponseEntity<String>("대댓글 실패.",HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	//호감 비호감
	@PutMapping(value="/{rnum}/{goodOrBad}",produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> goodOrBad(@PathVariable(required = true) String rnum,
																					@PathVariable(required = true) String goodOrBad){
		
		ResponseEntity<String>	res=null;
		if(rbbsSvc.goodOrBad(rnum, goodOrBad)==1) {
			res=new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			res=new ResponseEntity<String>("호감,비호감 실패.",HttpStatus.BAD_REQUEST);
		}
		
		return res;
	}
	
	
}
