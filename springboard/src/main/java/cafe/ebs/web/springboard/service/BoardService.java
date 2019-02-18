package cafe.ebs.web.springboard.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.ebs.web.springboard.mapper.BoardMapper;
import cafe.ebs.web.springboard.vo.Board;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	// 게시글 수정 화면
	public Board getBoard(int boardNo) {
		return boardMapper.selectBoard(boardNo);
	}


	//페이징 작업
	
	  public Map<String, Object> selectgetBoardList(int currentPage){ //1 첫번째 final
	  int ROW_PER_PAGE = 10; Map<String, Integer> map = new HashMap<String,
	  Integer>(); map.put("currentPage", currentPage*ROW_PER_PAGE);
	  map.put("rowPerPage", ROW_PER_PAGE); //2번째
	  int boardCount = boardMapper.selectBoardCount(); 
	  int lastpage = (int)(Math.ceil(boardCount/ROW_PER_PAGE));
	  
	  Map<String, Object> returnMap = new HashMap<String, Object>();
	  
	  returnMap.put("list",boardMapper.selectgetBoardList(map));
	  returnMap.put("boardCount", boardCount);
	  returnMap.put("lastpage", lastpage);
	  return returnMap; }
	 
	//전체 페이지 
	public int getBoardCount() {
		return boardMapper.selectBoardCount();
	}
	// 게시글 입력 폼	
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	// 게시글 삭제
	public int removeBoard(Board board) {
		return boardMapper.deleteBoard(board);
	}
	// 게시글 수정 처리 
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);	
	}
}
