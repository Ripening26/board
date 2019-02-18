package cafe.ebs.web.springboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.ebs.web.springboard.service.BoardService;
import cafe.ebs.web.springboard.vo.Board;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	 // 리스트 요청
    @GetMapping("/boardList")
    public String boardList(Model model, 
                    @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
    	Map<String, Object> returnMap = boardService.selectgetBoardList(currentPage);
		/* returnMap.put("currentPage", currentPage); */ 
    	model.addAttribute("returnMap", returnMap);
		 model.addAttribute("currentPage",currentPage); 
        return "boardList";
    }
    // 입력페이지 요청
    @GetMapping("/boardAdd")
    public String boardAdd() {
        System.out.println("boardAdd 폼 요청");
        return "boardAdd";
    }		
    // 입력(액션) 요청
    @PostMapping("/boardAdd")
    public String boardAdd(Board board) {
    	// 커맨드 객체 -> 필드 =name -> setter
    	boardService.addBoard(board);
    	System.out.println("boardaction 실행");
        return "redirect:/boardList"; // 글입력후 "/boardList"로 리다이렉트(재요청)
    }
    
    //페이지 삭제 요청
    @GetMapping("/boardDelete")
    public String boardDelete(Board board) {
		return "boardDelete";
    }
    //페이지 삭제 액션
    @PostMapping("/boardDelete")
    public String boardDelete(Model model,Board board) {
    	boardService.removeBoard(board);
		return "redirect:/boardList";
    }
    // 페이지 수정 요청
    @GetMapping("/boardUpdate")
    public String boardUpdate(@RequestParam(value="boardNo")int boardNo,Model model) {
    	Board board = boardService.getBoard(boardNo);
		System.out.println("boardService.modifyBoard(board)==>"+boardService.getBoard(boardNo));
		model.addAttribute("board", board);
    	return "boardUpdate";
    	
    }
    // 페이지 수정 액션
    @PostMapping("/boardUpdate")
    public String boardUpdate(Board board) {
    	boardService.modifyBoard(board);
		return "redirect:/boardList";
    }
    // 페이지 상세보기 요청
    @GetMapping("/boardTitleView")
    public String boardTitleView(@RequestParam(value="boardNo")int boardNo,Model model) {
    	Board board = boardService.getBoard(boardNo);
    	model.addAttribute("board", board);
		return "boardTitleView";
    	
    }
}
