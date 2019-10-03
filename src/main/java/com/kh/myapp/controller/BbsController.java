package com.kh.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.myapp.bbs.dto.BbsDTO;
import com.kh.myapp.bbs.service.BbsSvc;
import com.kh.myapp.common.Code;

@Controller
@RequestMapping("/bbs")
public class BbsController {

	private static Logger logger=LoggerFactory.getLogger(BbsController.class);
	
	@Inject
	BbsSvc bbsSvc;
	
	@ModelAttribute
	public void initData(Model model) {
		//검색유형
		List<Code> searchType=new ArrayList<>();
		searchType.add(new Code("TC","제목+내용"));
		searchType.add(new Code("T","제목"));
		searchType.add(new Code("C","내용"));
		searchType.add(new Code("N","작성자"));
		searchType.add(new Code("I","아이디"));
		
		model.addAttribute("searchType",searchType);

	}
	
	//글쓰기 양식
	@GetMapping("/write")
	public String write(Model model) {
		
		model.addAttribute("bbsDTO", new BbsDTO());
		return "/bbs/writeForm";
	}
	
	//글쓰기 처리
	@PostMapping("/write")
	public String writeOk(@Valid @ModelAttribute("bbsDTO") BbsDTO bbsDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			return "/bbs/writeForm";
		}
		
		bbsSvc.write(bbsDTO);
		
		return "redirect:/bbs/list";
	}
	
	//글 목록 조회
//	@GetMapping("/list")
//	public String list(HttpServletRequest request,Model model) {
//		logger.info("list 호출");
//		bbsSvc.list(request, model);
//		return "/bbs/list";
//	}
	
	@GetMapping({"/list",
							 "/list/{reqPage}",
							 "/list/{reqPage}/{searchType}",
							 "/list/{reqPage}/{searchType}/{keyword}"})
	public String list2(@PathVariable(required=false) String reqPage, 
											@PathVariable(required=false) String searchType,
											@PathVariable(required=false) String keyword, 
											Model model) {
		logger.info("list 호출");
		bbsSvc.list(reqPage, searchType,keyword,model);
		return "/bbs/list";
		
	}
	
	//게시글 클릭시 상세 조회
	@GetMapping("/read/{page}/{bnum}")
	public String read(@PathVariable String bnum, @PathVariable String page, Model model) {
		BbsDTO bbsDTO=bbsSvc.view(bnum);
		
		model.addAttribute("bbsDTO",bbsDTO);
		model.addAttribute("page",page);
		return "/bbs/readForm";
	}
	
	
	//게시글 삭제 처리
	@GetMapping("/delete/{page}/{bnum}")
	public String delete(@PathVariable String bnum,@PathVariable String page) {
		logger.info("delete 호출");
		bbsSvc.delete(bnum);
		
		return "redirect:/bbs/list/"+page;
	}
	
	//글 수정처리
	@PostMapping("/modify/{page}")
	public String modify(@Valid @ModelAttribute("bbsDTO") BbsDTO bbsDTO,
											 BindingResult result,@PathVariable String page) {
		
		if(result.hasErrors()) {
			return "/bbs/readForm";
		}
		
		bbsSvc.modify(bbsDTO);
		return "redirect:/bbs/read/"+page+"/"+bbsDTO.getBnum();
//		return "/bbs/readForm";
	}
	
	//답글 양식
	@GetMapping("/reply/{page}/{bnum}")
	public String reply(@PathVariable String bnum,@PathVariable String page,Model model) {
		
		BbsDTO bbsDTO=bbsSvc.view(bnum);
		model.addAttribute("bbsDTO",bbsDTO);
		model.addAttribute("page",page);
		
		return "/bbs/replyForm";
	}
	
	//답글 처리
	@PostMapping("/reply")
	public String reply(@Valid @ModelAttribute BbsDTO bbsDTO,BindingResult result) {
		
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/bbs/replyForm";
		}
		
		bbsSvc.reply(bbsDTO);
		return "redirect:/bbs/list";
	}
}
