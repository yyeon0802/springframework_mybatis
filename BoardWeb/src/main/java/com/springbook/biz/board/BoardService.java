package com.springbook.biz.board;

import java.util.List;

public interface BoardService {

	// 인터페이스이기에, 여기서는 메소드를 구현 XX 선언만 한다.

	// CRUD 기능의 메소드 구현

	// 글등록
	void insertBoard(BoardVO vo);

	// 글 수정
	void updateBoard(BoardVO vo);

	// 글 삭제
	void deleteBoard(BoardVO vo);

	// 글 상세 조회
	BoardVO getBoard(BoardVO vo);

	// 글 목록 조회
	List<BoardVO> getBoardList(BoardVO vo);
}
