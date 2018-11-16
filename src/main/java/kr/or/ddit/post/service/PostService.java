package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.post.dao.PostDaoInf;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.model.PageVo;

@Service
public class PostService implements PostServiceInf{

	@Resource(name="postDao")
	private PostDaoInf postDao;
	
	@Override
	public PostVo selectPost(int post_id) {
		return postDao.selectPost(post_id);
	}
	
	/**
	* Method : selectPostPageList
	* 작성자 : sohyoung
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 게시글 페이징 조회
	*/
	@Override
	public Map<String, Object> selectPostPageList(PageVo pageVo) {
		//페이지에 해당하는 게시글 리스트(1~10건 사이)
		List<PostVo> pageList = postDao.selectPostPageList(pageVo);
		
		//페이지 네비게이션을 위한 전체 게시글 리스트 조회
		int postCnt = postDao.getPostCnt(pageVo);
		
		//결과를 담는 map 객체
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("postPageList", pageList);
		resultMap.put("postPageCnt", (int)Math.ceil((double)postCnt / pageVo.getPageSize()));
		
		return resultMap;
	}

	@Override
	public int insertPost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}
	
	@Override
	public int insertReply(PostVo postVo) {
		return postDao.insertReply(postVo);
	}

	@Override
	public int updatePost(PostVo postVo) {
		return postDao.updatePost(postVo);
	}


	@Override
	public int getPostCnt(PageVo pageVo) {
		return postDao.getPostCnt(pageVo);
	}

	




}
