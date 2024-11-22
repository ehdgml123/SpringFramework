package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	
	/*
	 * @GetMapping("/list") public void list(Model model) {
	 * 
	 * log.info("list.......");
	 * 
	 * List<BoardVO> list = service.getList();
	 * 
	 * model.addAttribute("list", list); //model.addAttribute() ->컨트롤러가 뷰(View)에
	 * 데이터를 전달할 때 }
	 */
	
	@GetMapping("/list")
	public void list( Criterial cri, Model model ) {
		
	log.info("list......."+ cri);
	
	List<BoardVO> list = service.getList(cri);
	
	model.addAttribute("list", list);
	
	int total = service.getTotal(cri);
	model.addAttribute("pageMaker" , new PageDTO(cri, total));
	//model.addAttribute() ->컨트롤러가 뷰(View)에 데이터를 전달할 때
	
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
//	@RequestMapping(value = "/register", method= {RequestMethod.POST})
	@PostMapping("/register")  //RedirectAttributes ->  리다이렉트 방식으로 데이터를 전달할 때
	public String register(BoardVO vo, RedirectAttributes rttr) {
		 
		log.info("register");
		
		service.register(vo);
		
		rttr.addFlashAttribute("result" , vo.getBno());
		
		return "redirect:/board/list";
	}
	
	/*  addFlashAttribute
	 * 메서드는 리다이렉트가 완료된 후 소멸되는 "플래시 속성"을 추가합니다. 플래시 속성은 일회성으로, 
	 * 새로 고침하거나 다시 접속해도 유지되지 않습니다.
	 */
	
	// 조회 페이지 
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criterial cri  ,Model model) {
		
	   log.info("get or modify" + bno + " :" + cri);
		
       BoardVO vo = service.get(bno);
       
       model.addAttribute("board", vo);
       
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criterial cri ,RedirectAttributes rttr) {
		
		log.info("remove.... or modify......." + bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		};
		rttr.addAttribute("pageNum" , cri.getPageNum());
		rttr.addAttribute("amount" , cri.getAmount());
		rttr.addAttribute("type" , cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		
		return "redirect:/board/list";
	}
	

	@PostMapping("/modify")
	public String modify(BoardVO vo,@ModelAttribute("cri") Criterial cri  , RedirectAttributes rttr) {
		  
		log.info("modify............"+ vo);
		
		if(service.modify(vo)) {
			rttr.addFlashAttribute("result" , "success");
		}
		rttr.addAttribute("pageNum" , cri.getPageNum());
		rttr.addAttribute("amount" , cri.getAmount());
		rttr.addAttribute("type" , cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	

	
}
