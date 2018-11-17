package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import org.junit.Test;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.test.ServiceDaoTestConfig;

public class BoardDaoTest extends ServiceDaoTestConfig{

	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_id(1);
		boardVo.setBoard_avail(1);
		boardVo.setBoard_subject("수정성공");

		/***When***/
		int updateCnt = boardDao.updateBoard(boardVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void insertBoardTest(){
		/***Given***/
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_subject("새 게시판");
		boardVo.setBoard_avail(0);
		boardVo.setBoard_userid("sally");

		/***When***/
		int insertCnt = boardDao.insertBoard(boardVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
	}
	
	@Test
	public void selectBoardTest(){
		/***Given***/
		int board_id = 2;

		/***When***/
		BoardVo boardVo = boardDao.selectBoard(board_id);
		String board_subject = boardVo.getBoard_subject();

		/***Then***/
		assertEquals("자유게시판", board_subject);
		
	}
	

}












