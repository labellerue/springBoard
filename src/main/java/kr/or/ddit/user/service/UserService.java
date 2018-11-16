package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.UserDaoInf;
import kr.or.ddit.user.model.UserVo;

@Service
public class UserService implements UserServiceInf {

	@Resource(name="userDao")
	private UserDaoInf userDao;
	
	public List<UserVo> selectUserAll() {
		return userDao.selectUserAll();
	}

	@Override
	public UserVo selectUser(String userId) {
		return userDao.selectUser(userId);
	}

	
	

}
