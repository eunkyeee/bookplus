package com.kh.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test2")
public class TestController2 {
	private final static Logger logger=LoggerFactory.getLogger(TestController2.class);
	
	@GetMapping("/member/{name}/{age}")
	public String member(@PathVariable String name,
											 @PathVariable String age) {
		
		logger.info("name="+name);
		logger.info("age="+age);
		
		return "/test/member";
	}
}
