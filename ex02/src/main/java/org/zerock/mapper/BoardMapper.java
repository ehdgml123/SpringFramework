package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

public interface BoardMapper {
	
//	 @Select("select * from tbl_board where bno > 0")
     public List<BoardVO> getList();
     
     public List<BoardVO> getListwithPaging(Criterial cri);
     
     public void insert(BoardVO board);
     
     public void insertSelectKey(BoardVO vo);
     
     public BoardVO read(Long bno);
     
     public int delete(Long bno);
     
     public int update(BoardVO board);
     
     public int getTotalCount(Criterial cri);
     
     //test 용
     public List<BoardVO> searchTest(Map<String, Map<String, String>> map);
}
