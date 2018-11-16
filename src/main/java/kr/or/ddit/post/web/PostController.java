package kr.or.ddit.post.web;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.board.web.BoardController;
import kr.or.ddit.comm.model.CommVo;
import kr.or.ddit.comm.service.CommServiceInf;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostServiceInf;
import kr.or.ddit.uploadFile.model.FilesVo;
import kr.or.ddit.uploadFile.service.FilesServiceInf;
import kr.or.ddit.uploadFile.util.FileUtil;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/post")
@Controller
public class PostController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Resource(name="boardService")
	private BoardServiceInf boardService;
	@Resource(name="postService")
	private PostServiceInf postService;
	@Resource(name="commService")
	private CommServiceInf commService;
	@Resource(name="filesService")
	private FilesServiceInf filesService;

	/**
	* Method : postPageList
	* 작성자 : Bella
	* 변경이력 :
	* @param pageVo
	* @param model
	* @param session
	* @return
	* Method 설명 : 게시글 목록
	*/
	@GetMapping(value="/postPageList")
	public String postPageList(PageVo pageVo, Model model, HttpSession session) {

		// 해당 board_id의 게시판 명 가져오기
		BoardVo boardVo = boardService.selectBoard(pageVo.getBoard_id());
		
		//logger.debug("보드 정보는 {} ",boardVo.toString());
		
		model.addAttribute("boardVo", boardVo);

		// 해당 보드 아이디의 모든 게시글들을 가져오는 쿼리문
		Map<String, Object> resultMap = postService.selectPostPageList(pageVo);

		// model, session 객체에 저장
		model.addAllAttributes(resultMap);
		session.setAttribute("board_id", pageVo.getBoard_id());

		return "post/postPageList";
	}
	
	/**
	* Method : detailPost
	* 작성자 : Bella
	* 변경이력 :
	* @param post_id
	* @param model
	* @return
	* Method 설명 : 게시글 상세 내용 열람
	*/
	@GetMapping(value="/detailPost")
	public String detailPost(@RequestParam("post_id") String post_id, Model model) {
		
		logger.debug("게시글 아이디 받아오기 {}",post_id);
		
		// 게시글vo 가져오기
		PostVo postVo = postService.selectPost(Integer.parseInt(post_id));
//		// 댓글들 가져오기
//		List<CommVo> commList = commService.selectComm(Integer.parseInt(post_id));
		// 파일들 가져오기
		List<FilesVo> filesList = filesService.selectFiles(Integer.parseInt(post_id));
		
		// model 객체로 보내기
		model.addAttribute("detailPost", postVo);
//		model.addAttribute("commList", commList);
		model.addAttribute("filesList", filesList);
		
		return "post/detailPost";
	}
	
	@GetMapping(value="/getComm")
	public String getComm(Model model, String post_id) {
		//logger.debug("post_id 왔나 {}", post_id);
		// 댓글들 가져오기
		List<CommVo> commList = commService.selectComm(Integer.parseInt(post_id));
		model.addAttribute("commList", commList);
		return "jsonView";
	}
	
	
	/**
	* Method : insertReplyPost
	* 작성자 : Bella
	* 변경이력 :
	* @param post_id
	* @param post_groupid
	* @param model
	* @return
	* Method 설명 : 답글 정보를 넘기기 위해 부모글번호, 조상글번호 값 넘기기
	*/
	@GetMapping(value="/insertReplyPost")
	public String insertReplyPost(@RequestParam("post_id") String post_id,
								@RequestParam("post_groupid") String post_groupid,
								Model model) {
		// 답글일 경우
		if(!post_id.equals("") && !post_groupid.equals("")) {
			Map<String, String> postIds = new HashMap<String, String>();
			postIds.put("post_pid", post_id);
			postIds.put("post_groupid", post_groupid);
			model.addAllAttributes(postIds);
			model.addAttribute("title", "답글 작성");
			
			//logger.debug("답글용 post_pid: {}, post_groupid: {}",post_id, post_groupid);
		}
		//답글이 아닐 경우

		return "post/insertPost";
	}
	
	/**
	* Method : insertPostView
	* 작성자 : Bella
	* 변경이력 :
	* @param postVo
	* @return
	* Method 설명 : 새 글 작성 페이지로 연결
	*/
	@GetMapping(value="/insertPost")
	public String insertPostView(Model model) {
		model.addAttribute("title", "새 글 작성");
		return "post/insertPost";
	}
	
	/**
	* Method : insertPost
	* 작성자 : Bella
	* 변경이력 :
	* @param postVo
	* @return
	* Method 설명 : 새 글 등록 및 새 답글 등록
	*/
	@PostMapping(value="/insertPost")
	public String insertPost(PostVo postVo, HttpServletRequest request, Model model,
							@RequestPart("files") Collection<MultipartFile> parts) {

		// 새로운 답글일 경우
		if (postVo.getPost_pid() >0 && postVo.getPost_groupid() >0) {
			//logger.debug("답글 실행");
			postService.insertReply(postVo);

		} else {
			// 새로운 게시글일 경우
			//logger.debug("postVo.getPost_article() ? {}",postVo.getPost_article());
			//logger.debug("postVo.getPost_userid() ? {}",postVo.getPost_userid());
			postService.insertPost(postVo);
		}
		// 게시글 작업 끝--------------------------------------------
		// 파일 작업 시작
		//logger.debug("파일 갯수 : {}",parts.size());
		// 1. File객체 생성(경로+파일명 ==> 파일명 충돌방지를 위한 유니크한 임의의 파일명 생성)
		for (MultipartFile part : parts) {
			String path = request.getServletContext().getRealPath("/fileupload"); // 저장될 경로
			String fileName = UUID.randomUUID().toString(); // 임의의 이름으로 문자와 숫자의 조합으로 만들어 줍니다.
			String originalFileName = part.getOriginalFilename(); // 사용자가 업로드한 실제 파일명
			String fileExt = kr.or.ddit.uploadFile.util.FileUtil.getFileExt2(originalFileName);
			File file = new File(path + File.separator + fileName + fileExt);

			
			//logger.debug("realPath는? {}", path);
			
			FilesVo filesVo = new FilesVo();
			filesVo.setFile_name(fileName + fileExt);
			filesVo.setFile_path("/fileupload/" + fileName + fileExt);
			filesVo.setOrg_file_name(originalFileName);
			filesVo.setFile_ext(fileExt);
			// 위에서 게시글 아이디 받기
			int post_id = postVo.getTofile_id();
			filesVo.setPost_id(post_id);

			logger.debug("fileVo : {}", filesVo);

			try {
				if (part.getSize() > 0) {
					// 파일 객체를 인자로 받아 정해진 path에 업로드 파일을 작성
					part.transferTo(file);

					// 데이터베이스에 첨부파일 정보 저장
					filesService.insertFiles(filesVo);
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// 파일 작업 끝
		
		// 해당 게시글 상세 페이지로 이동
		return "redirect:/post/detailPost?post_id=" + postVo.getTofile_id();
	}
	
	
	/**
	* Method : deletePost
	* 작성자 : Bella
	* 변경이력 :
	* @param post_id
	* @param board_id
	* @return
	* Method 설명 : 게시글 삭제 -> '삭제된 게시글'로 전환
	*/
	@PostMapping(value="/deletePost")
	public String deletePost(@RequestParam("post_id") String post_id,
							@RequestParam("board_id") String board_id) {
		
		PostVo postVo = new PostVo();
		postVo.setPost_id(Integer.parseInt(post_id));
		postVo.setBoard_id(Integer.parseInt(board_id));
		postVo.setPost_del(1);

		// del 수정하기
		postService.updatePost(postVo);
		// 파일 삭제하기
		filesService.deleteFiles(Integer.parseInt(post_id));

		return "redirect:/post/postPageList?page=1&pageSize=10&board_id="+board_id;
	}
	
	/**
	* Method : updatePost
	* 작성자 : Bella
	* 변경이력 :
	* @param post_id
	* @param model
	* @return
	* Method 설명 : 수정할 게시글vo를 수정 폼으로 넘기기
	*/
	@GetMapping(value="/updatePost")
	public String updatePostView(@RequestParam("post_id") String post_id, Model model) {
		// 수정할 게시글vo를 수정폼으로 넘겨줍니다.
		PostVo postVo = postService.selectPost(Integer.parseInt(post_id));
		List<FilesVo> filesList = filesService.selectFiles(Integer.parseInt(post_id));
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("filesList", filesList);

		return "post/updatePost";
	}
	
	@PostMapping(value="/updatePost")
	public String updatePost(PostVo postVo, @RequestPart("files") Collection<MultipartFile> parts,
							HttpServletRequest request) {

		// 수정 게시글 저장하기
		postService.updatePost(postVo);
		logger.debug("내용 와라 {}", postVo.getPost_article());
		
		// 파일 작업 시작
		//logger.debug("파일 갯수 : {}", parts.size());
		// 1. File객체 생성(경로+파일명 ==> 파일명 충돌방지를 위한 유니크한 임의의 파일명 생성)
		for (MultipartFile part : parts) {
			String path = request.getServletContext().getRealPath("/fileupload"); // 저장될 경로
			String fileName = UUID.randomUUID().toString(); // 임의의 이름으로 문자와 숫자의 조합으로 만들어 줍니다.
			String originalFileName = part.getOriginalFilename(); // 사용자가 업로드한 실제 파일명
			String fileExt = FileUtil.getFileExt2(originalFileName);
			File file = new File(path + File.separator + fileName + fileExt);

			FilesVo filesVo = new FilesVo();
			filesVo.setFile_name(fileName + fileExt);
			filesVo.setFile_path("/fileupload/" + fileName + fileExt);
			filesVo.setOrg_file_name(originalFileName);
			filesVo.setFile_ext(fileExt);
			// 위에서 게시글 아이디 받기
			filesVo.setPost_id(postVo.getPost_id());

			logger.debug("fileVo : {}", filesVo);

			try {
				if (part.getSize() > 0) {
					// 파일 객체를 인자로 받아 정해진 path에 업로드 파일을 작성
					part.transferTo(file);

					// 데이터베이스에 첨부파일 정보 저장
					filesService.insertFiles(filesVo);
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// 파일 작업 끝

		return "redirect:/post/detailPost?post_id=" + postVo.getPost_id();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}












