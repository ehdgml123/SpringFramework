package org.zerock.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
@WebAppConfiguration // <- 가상서버
public class BoardControllerTests {

	@Autowired
	private  WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}
	
   @Test
   public void testRegister() throws Exception{
	             //경로로 POST 요청을 생성. perform 메서드를 통해 MockMvc가 해당 요청을 수행
	   String reusltPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
			   .param("title", "테스트 새글 제목")
			   .param("content", "테스트 새글 내용")
			   .param("writer", "user00"))
	   .andReturn().getModelAndView().getViewName();
	   //.andReturn() ->메서드를 호출하여 그 결과를 MvcResult 객체로 반환
	   //getModelAndView() ->MvcResult 객체에서 getModelAndView() 
	   //메서드를 호출하여 ModelAndView 객체를 가져옴
	   //getViewName() ->ModelAndView 객체에서 getViewName() 
	   // 메서드를 호출하여 뷰의 이름을 가져온다
	   // -> 이 뷰 이름은 컨트롤러가 리턴한 뷰의 논리적 이름
	   
	   log.info("resultPage :" + reusltPage);
   }
   
   @Test
   public void testGet() throws Exception{
	   
	 ModelMap modelMap = mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
			   .param("bno", "12"))
	   .andReturn()
	   .getModelAndView()
	   .getModelMap();
	 
	 log.info("model :" + modelMap);
   }
   
   @Test
   public void testRemove() throws Exception{
	   
	 String msg = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
			   .param("bno", "12"))
	   .andReturn()
	   .getModelAndView()
	   .getViewName();
	 
	 log.info("model :" + msg);
   }
	
}
