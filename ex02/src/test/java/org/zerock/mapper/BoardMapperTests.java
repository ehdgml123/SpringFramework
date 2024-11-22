package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;

	// 리스트 가져오기;
	@Test
	public void testGetList() {
		mapper.getList().forEach(list -> log.info(list));
	}

	
	  @Test public void TestInsert() {
	  
	  BoardVO board = new BoardVO();
	  
	  board.setTitle("새로 작성하는 글"); board.setContent("새로 작성하는 내용");
	  board.setWriter("newbie");
	
	  mapper.insert(board);
	 
	  log.info(board); }
	 

	@Test
	public void testRead() {

		Long bno = 2L;

		BoardVO board = mapper.read(bno);

		log.info(board);
	}

	@Test
	public void testDelete() {
		int result = mapper.delete(8L);

		log.info("result :" + result);

	}

	@Test
	public void testUpdate() {

		Long bno = 7L;

		BoardVO vo = mapper.read(bno);

		vo.setTitle("수정된 글");
		vo.setContent("수정된 내용");
		vo.setWriter("수정자");

		int result = mapper.update(vo);
		log.info(vo);

	}
	
	@Test
	public void testPaging() {
		
		Criterial cri = new Criterial(5,10);
		
		  mapper.getListwithPaging(cri)
		.forEach(list -> log.info(list));
	}
	
	@Test
	public void testSearch() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("T", "등록하쟝");
		map.put("C", "등록하쟝");
		map.put("W", "등록하쟝");
		
		Map<String, Map<String,String>> outer = new HashMap<>();
		
		outer.put("map", map);
		
		List<BoardVO> list = mapper.searchTest(outer);
		
		list.forEach(l -> log.info(l));
	}
	
	
	@Test
	public void testSearch2() {
		
		Criterial cri = new Criterial();
		
		cri.setKeyword("테스트");
		cri.setType("TCW");
		
		mapper.getListwithPaging(cri)
		.forEach(list -> log.info(list));
		
	}
	
	@Test
	public void testTotalCount() {
		
		Criterial cri = new Criterial();
		cri.setKeyword("테스트");
		cri.setType("TCW");
		int totalCount = mapper.getTotalCount(cri);
		log.info("totalCount"+ totalCount);
	}

}
