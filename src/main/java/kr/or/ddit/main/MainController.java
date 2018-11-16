package kr.or.ddit.main;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.board.service.BoardServiceInf;

@Controller
public class MainController {
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	/**
	* Method : main
	* 작성자 : Bella
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 메인 화면으로 이동
	*/
	@GetMapping(value="/main")
	public String main() {
		return "main";
	}
}
