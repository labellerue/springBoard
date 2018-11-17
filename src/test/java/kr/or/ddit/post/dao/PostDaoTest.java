package kr.or.ddit.post.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import kr.or.ddit.post.dao.PostDaoInf;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.test.ServiceDaoTestConfig;

import org.junit.Test;

public class PostDaoTest extends ServiceDaoTestConfig{

	@Resource(name="postDao")
	private PostDaoInf postDao;
	
	@Test
	public void insertPostTest(){
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setBoard_id(1);
		postVo.setPost_title("Test");
		postVo.setPost_article("Test");
		postVo.setPost_userid("moon");
		
		/***When***/
		//새로운 게시글 저장하기
		int insertCnt = postDao.insertPost(postVo);
				
		/***Then***/
		assertEquals(1, insertCnt);
	}

	@Test
	public void updatePostTest() {
		/*** Given ***/
		PostVo postVo = new PostVo();
		postVo.setPost_id(41);
		postVo.setPost_title("업데이트 테스트");
		postVo.setPost_article("업데이트 테스트 입니다.");

		/***When***/
		int updateCnt = postDao.updatePost(postVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	

}












