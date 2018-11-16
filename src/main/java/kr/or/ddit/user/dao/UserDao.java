package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

@Repository
public class UserDao implements UserDaoInf{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate temaplate;
	
	/**
	 * 
	* Method : selectUserAll
	* 작성자 : pc02
	* 변경이력 :
	* @return 테이블 전체 list
	* Method 설명 : 테이블 데이터 전체 조회
	 */
	@Override
	public List<UserVo> selectUserAll(){
		
		List<UserVo> userList = temaplate.selectList("user.selectUserAll");
		
		return userList;
	}
	
	// 회원정보 조회 
	@Override
	public UserVo selectUser(String userId){
		UserVo userVo = temaplate.selectOne("user.selectUser", userId);
		
		return userVo;
	}
	
	

	
	
	
}











