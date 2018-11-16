package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVo;

@Repository
public class BoardDao implements BoardDaoInf {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate tamplate;

	@Override
	public int insertBoard(BoardVo boardVo) {
		
		return tamplate.insert("board.insertBoard", boardVo);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		
		return tamplate.update("board.updateBoard", boardVo);
	}

	@Override
	public List<BoardVo> selectAllBoard() {
		List<BoardVo> boardList = tamplate.selectList("board.selectAllBoard");
		
		return boardList;
		
	}

	@Override
	public BoardVo selectBoard(int board_id) {
		
		BoardVo boardVo = tamplate.selectOne("board.selectBoard",board_id);
		
		return boardVo;
	}
	


}






















