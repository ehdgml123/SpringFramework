package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor // <- 생성자 자동 생성
@RequiredArgsConstructor // <-- 생성자 자동 주입 그리고 변수에다가 final 작성해야됨.
// <-객체를 정의하고 그 객체를 생성할때 보통 생성자를
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;

	@Override
	public void register(BoardVO vo) {
		log.info("register..........." + vo);
		mapper.insertSelectKey(vo);

	}

	@Override
	public BoardVO get(Long bno) {

		log.info("get..............");

		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO vo) {

		log.info("midify...........");

		// 업데에트 성공하면 1 값 넘어오고, 실패하면 0값 이 넘어온다
		// 1값 이면 true , 0값 이면 flase
		return mapper.update(vo) == 1;
	}

	@Override
	public boolean remove(Long bno) {

		log.info("remove......");

		return mapper.delete(bno) == 1;
	}

	/*
	 * @Override public List<BoardVO> getList() {
	 * 
	 * log.info("List.......");
	 * 
	 * List<BoardVO> list = mapper.getList(); return list;
	 * 
	 * 
	 * return mapper.getList(); }
	 */
	
	@Override
	public List<BoardVO> getList(Criterial cri) {

		log.info("getList.......");
		/*
		 * List<BoardVO> list = mapper.getList(); return list;
		 */

		return mapper.getListwithPaging(cri);
	}

	@Override
	public int getTotal(Criterial cri) {
	
		return mapper.getTotalCount(cri);
	}

}
