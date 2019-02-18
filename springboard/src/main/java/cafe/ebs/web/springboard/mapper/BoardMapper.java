package cafe.ebs.web.springboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.ebs.web.springboard.vo.Board;

@Mapper
public interface BoardMapper {
	
	//수정 및 상세보기
	Board selectBoard(int boardNo);
	
	
	//목록
	List<Board> selectgetBoardList(Map<String, Integer> map); /* int currentPage, int pagePerRow==>Map<String, Integer> map 타입으로묶음*/
	
	
	//전체글이 몇인지(페이징)
	int selectBoardCount();
	
	
	//입력
	int insertBoard(Board board);
	
	
	//업데이트
	int updateBoard(Board board);
	
	
	//삭제
	int deleteBoard(Board board); /* int boardNo, String boardPw ==> Board 타입으로 묶음*/
}
