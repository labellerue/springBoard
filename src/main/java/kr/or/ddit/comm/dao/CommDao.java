package kr.or.ddit.comm.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.comm.model.CommVo;

@Repository
public class CommDao implements CommDaoInf {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate tamplate;

	@Override
	public List<CommVo> selectComm(int post_id) {
		
		List<CommVo> commList = tamplate.selectList("comm.selectComm", post_id);
		return commList;
	}

	@Override
	public int insertComm(CommVo commVo) {
		return tamplate.insert("comm.insertComm", commVo);
	}

	@Override
	public int updateComm(int comm_id) {
		return tamplate.update("comm.updateComm", comm_id);
	}

}
