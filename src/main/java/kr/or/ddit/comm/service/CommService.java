package kr.or.ddit.comm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.comm.dao.CommDaoInf;
import kr.or.ddit.comm.model.CommVo;

@Service
public class CommService implements CommServiceInf {
	
	@Resource(name="commDao")
	private CommDaoInf commDao;

	@Override
	public List<CommVo> selectComm(int post_id) {
		return commDao.selectComm(post_id);
	}

	@Override
	public int insertComm(CommVo commVo) {
		return commDao.insertComm(commVo);
	}

	@Override
	public int updateComm(int comm_id) {
		return commDao.updateComm(comm_id);
	}

}
