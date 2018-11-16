package kr.or.ddit.post.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.model.PageVo;

@Repository
public class PostDao implements PostDaoInf {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate tamplate;


	@Override
	public PostVo selectPost(int post_id) {
		
		PostVo postVo = tamplate.selectOne("post.selectPost", post_id);
		
		return postVo;
	}
	
	@Override
	public List<PostVo> selectPostPageList(PageVo pageVo) {
		
		List<PostVo> postPageList = tamplate.selectList("post.selectPostPageList", pageVo);
		
		return postPageList;
	}

	@Override
	public int insertPost(PostVo postVo) {
		
		return tamplate.insert("post.insertPost", postVo);
	}
	
	@Override
	public int insertReply(PostVo postVo) {
		
		return tamplate.insert("post.insertReply", postVo);
	}
	
	@Override
	public int updatePost(PostVo postVo) {
		
		return tamplate.update("post.updatePost", postVo);
	}
	
	@Override
	public int getPostCnt(PageVo pageVo) {
		
		return tamplate.selectOne("post.getPostCnt", pageVo);
	}


	

	

}






















