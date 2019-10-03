package com.kh.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {
	private final static Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	//GET방식
	@RequestMapping("/member")
	public String member(@RequestParam("name") String name,
											 @RequestParam("age") String age) {
		logger.info("member() 호출");
		logger.info("name="+name);
		logger.info("age="+age);
		return "/test/member";
		//"/member/member"
	}
	
	//post방식
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public String memberPost(@RequestParam("name") String name,
													 @RequestParam("age") String age) {
		
		logger.info("memberPost() 호출");
		logger.info("name="+name);
		logger.info("age="+age);
		return "/test/member";
	}
	
	//4.3버전
//	@RequestMapping("/member") ==> @GetMapping("/member")
//	@RequestMapping(value="/member", method=RequestMethod.POST) ==> @PostMapping("/member")
}
