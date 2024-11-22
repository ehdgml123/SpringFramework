package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/sample")
public class SampleController {
	
	 @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	    public void basic() {
	        log.info("basic..........");
	    }
	    
	    @GetMapping("basicOnlyGet")
	    public void basicGet2() {
	        log.info("basic get only get..........");
	    }

	    @GetMapping("/ex01")
	    public String ex01(SampleDTO dto) { // SampleDTO로 수정
	        log.info(dto + "");
	        log.info(dto.getName());
	        log.info(dto.getAge());
	        return "ex01";
	    }
	    
	    @GetMapping("/ex02")
	    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
	        log.info(name);
	        log.info(age);
	        return "ex02";
	    }

	    @GetMapping("/ex02List")
	    public String ex02List(@RequestParam("idx") ArrayList<String> idx) {
	        log.info("idx :" + idx);
	        return "ex02List";
	    }

	    @GetMapping("/ex02Array")
	    public String ex02Array(@RequestParam("ids") String[] ids) {
	        log.info("ex02Array :" + Arrays.toString(ids));
	        return "ex02Array";
	    }

	    @GetMapping("/ex02Bean")
	    public String ex02Bean(SampleDTOList list) {
	        log.info("list :" + list);
	        return "ex02Bean";
	    }

	    @GetMapping("/ex03")
	    public String ex03(TodoDTO todo) {
	        log.info("todo :" + todo);
	        return "ex03";
	    }

	    @GetMapping("/ex04")
	    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) { // SampleDTO로 수정
	        log.info("dto :" + dto);
	        log.info("page :" + page);
	        return "sample/ex04";
	    }

	    @GetMapping("/ex05")
	    public void ex05() {
	        log.info("ex05.............");
	    }

	    @GetMapping("/ex06")
	    public @ResponseBody SampleDTO ex06() { // SampleDTO로 수정
	        log.info("/ex06........");
	        
	        SampleDTO dto = new SampleDTO(); // SampleDTO로 수정
	        dto.setName("홍길동");
	        dto.setAge(15);
	        
	        return dto;
	    }

	    @GetMapping("ex06_1")
	    public String ex06_1(@RequestBody SampleDTO dto) { // SampleDTO로 수정
	        log.info("/ex06_1.........");
	        log.info("dto:" + dto);
	        return "ex06_1";
	    }
	    
	    @GetMapping("/exUpload")
	    public void exUpload() {
	    	log.info("exUpload......");
	    }
	    
	    @PostMapping("/exUploadPost")
	    public void exUploadPost(ArrayList<MultipartFile> files) {
	    	 
	    	files.forEach(file->{
	    		log.info("-------------------");
	    		log.info("name :" + file.getOriginalFilename());
	    		log.info("size :" + file.getSize());
	    	});
	    }

}
