package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	public static void main(String[] args) {
		// 1. spring 컨테이너를 구동한다.
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. spring 컨테이너로부터 BoardServiceImp 객체를 Lookup한다.
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		// 3. 글 등록 기능 테스트
		BoardVO vo = new BoardVO();
		
//		[transaction test : 임시로 100번째 seq생성]
//		vo.setSeq(100); 
		
		vo.setTitle("임시 제목");
		vo.setWriter("홍길동");
		vo.setContent("임시내용.........");
//		[transaction test 시 사용 xx 주석처리]
		boardService.insertBoard(vo);

		// 4. 글 목록 검색 기능 테스트
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println("---->" + board.toString());			
		}
		
		// 5. spring 컨테이너 종료
		container.close();
	}
	
}
