package com.springbook.biz.board.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.common.JDBCUtil;

//DAO(Data Access Object)
@Repository("boardDAO") //Repository : component보다 데이터처리에 효율적(why? 예외처리포함) // BoardDAOSpring사용을 위한 annotation 주석
public class BoardDAO {

	//JDBC 변수
	private Connection conn = null;
	private PreparedStatement stmt = null; 	//쿼리인자값 전달
	private ResultSet rs = null; 			//쿼리 결과값
	
	//SQL명령어(final로 확정한다.)
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq),0)+1 from board), ?, ?, ?)";
	private final String BOARD_UPDATE = "update board set title=?, content=?, writer=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	private final String BOARD_LIST_T = "select * from board where title like '%' || ? || '%' order by seq desc";
	private final String BOARD_LIST_C = "select * from board where content like '%' || ? || '%' order by seq desc";
	
	//CRUD 기능의 메소드 구현
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard()기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT); // BOARD_INSERT : 전달 인자값이 3개
			stmt.setString(1, vo.getTitle()); 			//3개 값에 따른 setString()
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//글 수정
		public void updateBoard(BoardVO vo) {
			System.out.println("===> JDBC로 updateBoard()기능 처리 ");
			
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_UPDATE);
				stmt.setString(1, vo.getTitle());
				stmt.setString(2, vo.getContent());
				stmt.setString(3, vo.getWriter());
				stmt.setInt(4, vo.getSeq());
				stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
	//글 삭제
		public void deleteBoard(BoardVO vo) {
			System.out.println("===> JDBC로 deleteBoard()기능 처리 ");
			
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_DELETE);
				stmt.setInt(1, vo.getSeq());
				stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
	// 글 상세 조회
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("===> JDBD로 getBoard()기능 처리");
			BoardVO board = null;
			
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_GET);
				stmt.setInt(1, vo.getSeq());
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					board = new BoardVO();
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegDate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);		//rs가 추가되어 같이 닫아주어야 한다.
			}
			return board;
		}
			
		
	// 글 목록 조회
		public List<BoardVO> getBoardList(BoardVO vo) {			// 결과값이 여러개이므로 List로 return
			System.out.println("===> JDBD로 getBoardList()기능 처리");
			
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			
			try {
				conn = JDBCUtil.getConnection();
				
				if(vo.getSearchCondition().equals("TITLE")) {
					stmt = conn.prepareStatement(BOARD_LIST_T);					
				} else if(vo.getSearchCondition().equals("CONTENT")) {
					stmt = conn.prepareStatement(BOARD_LIST_C);
				}
				
				//stmt = conn.prepareStatement(BOARD_LIST);
				stmt.setString(1, vo.getSearchKeyword());
				rs = stmt.executeQuery();

				while(rs.next()) {
					// BoardVO board 생성은 이 시점에. 계속 새로운 BoardVO를 받아야 하므로 
					BoardVO board = new BoardVO();
					board = new BoardVO();
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegDate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
					boardList.add(board);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return boardList;
		}
	
}
