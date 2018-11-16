package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserServiceInf;

@Controller
public class LoginController {
	
	@Resource(name="userService")
	private UserServiceInf userService;
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	@RequestMapping(value="/loginView")
	public String loginView() {
		return "login/login";
	}
	
	/**
	* Method : loginProcess
	* 작성자 : Bella
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 입력한 아이디 존재, 아이디와 비밀번호 일치 여부 확인 후 로그인 실행
	*/
	@PostMapping(value="/loginProcess")
	public String loginProcess(UserVo userVo, HttpSession session, 
								HttpServletRequest request) {
		String url = "login/login";
		UserVo checkUser = userService.selectUser(userVo.getUserId());
		
		if(checkUser != null || checkUser.getPass() == userVo.getPass()) {
			url = "main";
			
			session.setAttribute("userVo", checkUser);
			request.getServletContext().setAttribute("boardList",boardService.selectAllBoard());
		}
		return url;
	}

	
	
	
	
	
	
}
