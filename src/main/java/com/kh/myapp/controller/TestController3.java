package com.kh.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.myapp.member.dto.MemberDTO;

@Controller
@RequestMapping("/test3")
public class TestController3 {
	private final static Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	//case1) Model, String
	@PostMapping("/member")
	public String member(MemberDTO mdto, Model model) {
		logger.info(mdto.toString());
		model.addAttribute("mdto", mdto);
		return "/test/member";
	}
	
	//case2) ModelAndView객체 사용
	@PostMapping("/member2")
	public ModelAndView member2(MemberDTO mdto) {
		logger.info(mdto.toString());
		ModelAndView mav=new ModelAndView();
		mav.addObject("mdto",mdto);
		mav.setViewName("/test/member");
		return mav;
	}
}
