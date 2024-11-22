package com.mvc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.ToString;


public class MainClass {
	
	public static void main(String[] args) {
		
		String resource = "com/mvc/controller/mybatis-config.xml";
		InputStream inputStream;
		
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			System.out.println("sqlSessionFactory :" + sqlSessionFactory);
			
		    SqlSession session = sqlSessionFactory.openSession(true);
		    System.out.println("session :" + session);
		    
		    BoardDTO dto = new BoardDTO();
		    
		    BoardMapper mapper = session.getMapper(BoardMapper.class);
		    
		    /* 단건 조회
 		    dto = mapper.selectOne(15);
	   System.out.println("dto :" + dto); 
		*/
		   
		    /* -- 전체 조회
		    List<BoardDTO> lists = mapper.selectAllList();
		    
		    for(BoardDTO list : lists)
		    	System.out.println(list);
		    */
		    
		   /* -- 삭제
		   int result =  mapper.deleteBoard(51);
		   System.out.println("result :" + result); 
		    */
		   
		    
		    dto.setTitle("마이바티스");
		    dto.setContent("아미바티스로 입력중");
		    dto.setNum(15);
		    
		    int result = mapper.updateBoard(dto);
		    System.out.println("result :" + result);
		    
		    
		  
		  //  dto.setNum(1000);
		 //   dto.setTitle("마이바티스");
		 //   dto.setContent("아미바티스로 입력중");
		 //   dto.setId("mybatis00");
		  //  dto.setVisitcount(0);
		//    dto.setPostdate(new Date());
		    
		    
		    
		 //   Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
				
		    
		    
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
