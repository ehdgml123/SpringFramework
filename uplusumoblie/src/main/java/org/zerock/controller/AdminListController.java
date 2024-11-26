package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminListController {

    @GetMapping("/notice")
    public String noticList() {
        log.info("관리자 공지사항 페이지...");
        return "admin/notice";  
    }
    
    @GetMapping("/register")
    public String noticRegister() {
        log.info("공지사항 작성 페이지...");
        return "admin/register";  
    }
    
    @GetMapping("/modify")
    public String noticModify() {
        log.info("공지사항 수정 페이지...");
        return "admin/modify";  
    }
    
    
    @GetMapping("/read")
    public String noticRead() {
        log.info("공지사항 상세 페이지...");
        return "admin/read";  
    }
    
    
    
	// 관리자 페이지 에서 고객이 핸드폰 신청한 내역 만 볼수 있다. 
	  @GetMapping("/PhoneApplicationdetails") 
	  public String AddphoneList() {
	  
	     log.info("User phone List......");
	  
	  return "admin/PhoneApplicationdetails";
	  
	  }
	 
}
