package com.springbook.biz.board.impl;

//import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
//import com.springbook.biz.board.common.JDBCUtil;

//DAO(Data Access Object)
//@Repository("baordDAO") //Repository : component보다 데이터처리에 효율적(why? 예외처리포함)
public class BoardDAOSpring { // extends JdbcDaoSupport : jdbcTemplate사용시에는 getTemplate의 객체 리턴이 없으므로 부모상속 X

	// SQL명령어(final로 확정한다.)

//	[transaction test]
//	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values(?, ?, ?, ?)";
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq),0)+1 from board), ?, ?, ?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	private final String BOARD_LIST_T = "select * from board where title like '%' || ? || '%' order by seq desc";
	private final String BOARD_LIST_C = "select * from board where content like '%' || ? | |'%' order by seq desc";

	// getJdbcTemplate()메소드가 JdbcTemplate 객체를 리턴하려면, 부모클래스의 setDataSource() 메소드를
	// 호출하여 데이터 소스 객체를 의존성 주입 해야 한다.
	/*
	 * public void setSuperDataSource(DataSource dataSource) {
	 * super.setDataSource(dataSource); }
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// CRUD 기능의 메소드 구현
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC template로 insertBoard()기능 처리 ");
//		getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
//		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
		jdbcTemplate.update(BOARD_INSERT, vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getContent());

	}

	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC template로 updateBoard()기능 처리 ");
//			getJdbcTemplate().update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());

	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC template로 deleteBoard()기능 처리 ");
//			getJdbcTemplate().update(BOARD_DELETE, vo.getSeq());
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());

	}

	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC template로 getBoard()기능 처리");
		Object[] args = { vo.getSeq() };
//			return getJdbcTemplate().queryForObject(BOARD_GET, args, new BoardRowMapper());
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) { // 결과값이 여러개이므로 List로 return

		System.out.println("===> JDBC template로 getBoardList()기능 처리");
//			return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
		if (vo.getSearchCondition().equals("TITLE")) {
			return jdbcTemplate.query(BOARD_LIST_T, new BoardRowMapper());

		}
		if (vo.getSearchCondition().equals("CONTENT")) {
			return jdbcTemplate.query(BOARD_LIST_C, new BoardRowMapper());

		}
		return null;
		// return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());

	}

}
