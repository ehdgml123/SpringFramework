package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin")
public class AdminController {
 
	
	 @GetMapping("/adminloginJoin")
	    public String showAdminLoginPage() {
		 
		 log.info("addlogin Page.......");
		 
		 return "admin/adminloginJoin";
	        
	    }
	 
	 @GetMapping("/Adminmain")
	 public String AdminMain() {
		 
		 log.info("AdminMain page.......");
		 
		 return "admin/Adminmain";
	 }
	 
	 
	 
	
}
