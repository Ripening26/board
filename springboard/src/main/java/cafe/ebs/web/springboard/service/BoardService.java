package cafe.ebs.web.springboard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cafe.ebs.web.springboard.mapper.BoardMapper;
import cafe.ebs.web.springboard.vo.Board;
import cafe.ebs.web.springboard.vo.BoardFile;

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
	
	  public Map<String, Object> selectgetBoardList(int currentPage){ 
		//1 첫번째 final
	  int ROW_PER_PAGE = 10; Map<String, Integer> map = new HashMap<String,
	  Integer>(); map.put("currentPage", currentPage*ROW_PER_PAGE);
	  map.put("rowPerPage", ROW_PER_PAGE); 
	  //2번째
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

	/*
	 * // 게시글 입력 폼 public int addBoard(Board board) { return
	 * boardMapper.insertBoard(board); }
	 */
	// 게시글 입력 및 파일 폼
	public int addBoard(Board boardRequest, String path){
		/*
		 * 1. boardRequest를 분리 : board,file,file 정보
		 * 2. board에서 boardVo
		 * 3. file정보 > boardFileVo
		 * 4. file > path를 이용해 물리적 장치 저장
		 */
		//1
		Board board =  new Board();
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardPw(boardRequest.getBoardPw());
		board.setBoardContent(boardRequest.getBoardContent());
		board.setBoardUser(boardRequest.getBoardUser());
		//board.getBoardNO()
		boardMapper.insertBoard(board);  //jjev cate 1280
		//2
		List<MultipartFile> files = boardRequest.getFileList();
		for(MultipartFile f : files) {
			//f > board file()
			BoardFile boardfile = new BoardFile();
			boardfile.setBoardNo(board.getBoardNo());
			boardfile.setFileSize(f.getSize());
			boardfile.setFileTpye(f.getContentType());
			boardfile.setFileName("");
			String originalFilename = f.getOriginalFilename();
			int i  = originalFilename.lastIndexOf(",");
			String ext = originalFilename.substring(i+1);
			boardfile.setFileExt(ext);
			String fileName = UUID.randomUUID().toString();
			boardfile.setFileName("fileName");
			
			// 전체작업이 롤백되면 파일삭제작업은 직접 해야된다.
			
			//3 파일저장
			try {
				f.transferTo(new File(path+"/"+fileName+"."+ext));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boardMapper.insertBoard(board);
		// boardFileMapper.insertBoard(boardFile);
		
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
