package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;


public interface BoardServiceInf {

	/**
	* Method : insertBoard
	* 작성자 : sohyoung
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 등록
	*/
	int insertBoard(BoardVo boardVo);
	
	/**
	* Method : updateBoard
	* 작성자 : sohyoung
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 수정
	*/
	int updateBoard(BoardVo boardVo);
	
	/**
	* Method : selectAllBoard
	* 작성자 : sohyoung
	* 변경이력 :
	* @return
	* Method 설명 : 
	*/
	List<BoardVo> selectAllBoard();
	
	/**
	* Method : selectBoard
	* 작성자 : sohyoung
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 게시판 아이디로 조회
	*/
	BoardVo selectBoard(int board_id);
	
	
	
	
}

























