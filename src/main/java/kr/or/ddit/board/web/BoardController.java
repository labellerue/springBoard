package kr.or.ddit.board.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private Logger logger = LoggerFactory.getLogger(BoardController.class);

	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	
	@GetMapping(value="/detailBoard")
	public String updateBoardView(HttpServletRequest request) {
		request.getServletContext().setAttribute("boardList",boardService.selectAllBoard());
		return "board/detailBoard";
	}
	
	/**
	* Method : updateBoard
	* 작성자 : Bella
	* 변경이력 :
	* @param userVo
	* @param boardVo
	* @return
	* Method 설명 : 게시판 신규/수정
	*/
	@PostMapping(value="/updateBoard")
	public String updateBoard(@RequestParam("userId") String userId, BoardVo boardVo) {
		
		//logger.debug("이거 봐봐 {}",boardVo.getBoard_id());
		//logger.debug("유저는 {}", userId);
				
		//게시판명을 작성하지 않았을 시
		if(boardVo.getBoard_subject().equals(""))
			return "redirect:/board/detailBoard";
		
		//신규 게시판 생성
		if(boardVo.getBoard_id() < 1 ){
			boardVo.setBoard_userid(userId);
			boardService.insertBoard(boardVo);
			
		//기존 게시판 수정
		}else if(boardVo.getBoard_id() > 0){
			boardService.updateBoard(boardVo);
		}
		
		return "redirect:/board/detailBoard";
	}
	
	
	
	
	
	
	
	
	
	
	
}



















