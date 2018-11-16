package kr.or.ddit.comm.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.comm.model.CommVo;
import kr.or.ddit.comm.service.CommServiceInf;

@RequestMapping("/comm")
@Controller
public class CommController {
	
	@Resource(name="commService")
	private CommServiceInf commService;

	/**
	* Method : insertComm
	* 작성자 : Bella
	* 변경이력 :
	* @param commVo
	* @return
	* Method 설명 : 댓글 등록
	*/
	@PostMapping(value="/insertComm")
	public String insertComm(CommVo commVo) {

		commService.insertComm(commVo);
		return "redirect:/post/detailPost?post_id=" + commVo.getPost_id();
	}

	@PostMapping(value="/deleteComm")
	public String deleteComm(CommVo commVo) {

		commService.updateComm(commVo.getComm_id());
		return "redirect:/post/detailPost?post_id=" + commVo.getPost_id();
	}
	
	
	
	
	
	
	
}











