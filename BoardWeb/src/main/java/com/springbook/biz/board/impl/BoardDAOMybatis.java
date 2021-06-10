package com.springbook.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

// DAO(Data Access Object)
@Repository
public class BoardDAOMybatis { //extends SqlSessionDaoSupport
   
	@Autowired
	private SqlSessionTemplate mybatis;
   
   /*
	 * public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) { //
	 * SqlSession재정의함 super.setSqlSessionFactory(sqlSessionFactory); // 메소드
	 * 위에 @autowired를 붙여주게되면 의존성 주입이 아닌 자동으로 호출 // 이렇게 하면 CURD에 해당하는 메소드들 다 사용가능 }
	 */
   
   //CRUD 기능의 메소드 구현
   // 글 등록
   public void insertBoard(BoardVO vo) {
      System.out.println("===> Mybatis로 insertBoard() 기능 처리");
      mybatis.insert("BoardDAO.insertBoard", vo);
//      getSqlSession().insert("BoardDAO.insertBoard", vo);
   }
   
   // 글 수정
   public void updateBoard(BoardVO vo) {
      System.out.println("===> Mybatis로 updateBoard() 기능 처리");
      mybatis.update("BoardDAO.updateBoard", vo);
//      getSqlSession().update("BoardDAO.updateBoard", vo);
   }
   
   // 글 삭제 
   public void deleteBoard(BoardVO vo) {
      System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
      mybatis.delete("BoardDAO.deleteBoard", vo);
//      getSqlSession().delete("BoardDAO.deleteBoard", vo);
   }
   
   // 글 상세 조희
   public BoardVO getBoard(BoardVO vo) {
      System.out.println("===> Mybatis로 getBoard() 기능 처리");
      return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo);
//      return (BoardVO) getSqlSession().selectOne("BoardDAO.getBoard", vo);
   }
   
   // 글 목록 조회
   public List<BoardVO> getBoardList(BoardVO vo){
      System.out.println("===> Mybatis로 getBoardList() 기능 처리");
      return mybatis.selectList("BoardDAO.getBoardList", vo);
//      return getSqlSession().selectList("BoardDAO.getBoardList", vo);
   }
   
}