package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board") //null업데이트 방지 SessionAttributes 사용 => 즉, session에 board라는 객체가 저장되어 있으면 사용하겠다.
public class BoardController {
	
	/* return type 비교
	 *  1) ModelAndView : 검색된 Model(데이터)와 View 이름(문자열 이름)을 모두 저장(ex.mav)하여 return
	 *  2) String 		: 완벽한 View이름을 모두 저장하여 return <간결하여 주로 이용>
	 * */
	
	/* Controller보다 Impl 객체가 먼저 생성되어 구동되어야 한다. => presentation-layer 수정
	 * */
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo){ //List<BoardVO>
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		
		return boardListVO;
		// return boardList;
	}
	
	
	// 글등록 하기
	@RequestMapping(value="/insertBoard.do") 
	public String  insertBoard(BoardVO vo) throws IOException { //BoardDAO boardDAO
		
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/DEV2/" + fileName));
		}
		System.out.println("글 등록 처리");
		//boardDAO.insertBoard(vo);
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	/*현재 파라미터 정보가 전달 되지 않으면(ex.수정시 writer정보) null로 전달되어 수정이 된다. 
		이를 방지하기 위해 SessionAttribute에 사용자가 입력한 정보를 저장한다. 
	*/
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard (@ModelAttribute("board")BoardVO vo) { //, BoardDAO boardDAO
		//System.out.println("작성자 이름 : " + vo.getWriter());
		System.out.println("글 수정 처리");
		System.out.println("번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());		
		
		//boardDAO.updateBoard(vo);
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 삭제
		@RequestMapping("/deleteBoard.do")
		public String deleteBoard(BoardVO vo) {// BoardDAO boardDAO
			//boardDAO.deleteBoard(vo);
			boardService.deleteBoard(vo);
			return "getBoardList.do";
		}
	
	// 글 상세 조회
		@RequestMapping("/getBoard.do")
		public String getBoard(BoardVO vo, Model model) { // ModelAndView getBoard(동일, ModelAndView mav , BoardDAO boardDAO)
			model.addAttribute("board", boardService.getBoard(vo)); 	// Model정보 저장 : "board"로 저장(현재 글 상세조회는 수정 메소드 전에 먼저 실행되서 상세창에서 수정가능하다)
			return "getBoard.jsp";		// view 이름 리턴
			
			/*mav.addObject("board", boardDAO.getBoard(vo)); //Model 정보 저장
			  mav.setViewName("getBoard.jsp"); //view 정보 저장 
			  return mav;*/
			
			
		}
	
	
	/* ModelAttrubute 2) View(JSP)에서 사용할 데이터를 설정하는 용도
	 *  - 설정하게되면, @RequestMapping 어노테이션이 적용된 메소드 보다 먼저 호출 
	 *  - @ModelAttribute의 실행 결과로 리턴된 객체는 자동으로 Model에 저장 => 실행결과로 리턴된 객체를 View페이지에서 사용 가능
	 *  ex) model 객체에 : { conditionMap(데이터)  : {"제목" : "TITLE"}, 
	 *  										{"내용" : "CONTENT"} } ,
	 *  				{boardList : { {seq,title, writer, content} , 
	 *  								{seq,title, writer, content}  ... }
	*/	
		
	// 검색 조건 목록 설정
		@ModelAttribute("conditionMap") 
		public Map<String, String>searchConditionMap() { 
			Map<String, String> conditionMap = new HashMap<String,String>(); 
			conditionMap.put("제목", "TITLE"); 
			conditionMap.put("내용","CONTENT"); 
			return conditionMap; 
		}
		 
		
		// 글 목록 검색
		@RequestMapping("/getBoardList.do")
		 public String getBoardList(BoardVO vo, Model model) { // ModelAndView getBoardList(동일, ModelAndView mav) , BoardDAO boardDAO
			
			// Null check
			if(vo.getSearchCondition() == null) {
				vo.setSearchCondition("TITLE");
			} 
			if(vo.getSearchKeyword() == null) {
				vo.setSearchKeyword("");
			}
			
			model.addAttribute("boardList", boardService.getBoardList(vo));	 // Model 정보 저장
			return "getBoardList.jsp";			// view 이름 리턴
			 
		}
		
		/* Command 객체를 사용하려면 요청 파라미터와 매핑될 변수와 Setter 메소드가 Command 클래스에 선언되어 있어야 한다.
		 * 다만, command객체에 없는 파라미터를 Controller 클래스에서 사용하려면,(ex.private String keyword선언, getter/setter 생성)
		 *  1) VO등 해당 클래스로 돌아가 변수 추가 후 getter/setter 메소드 생성
		 *  2) @RequestParam을 통하여 Command 클래스에 없는 파라미터 정보를 추출할 수 있다. 글목록 검색의 경우,
		 * 
		 * @RequestParma(value="searchCondition", 	=> value : 화면으로부터 전달될 파라미터 이름
		 * 				defaultValue="TITLE",		=> defaultVale : 화면으로부터 전달될 파라미터 정보가 없을때, 설정할 기본값
		 * 				required=false) String condition => required : 파라미터의 생략 여부 
		 * */ 
	
		//글 목록 검색 : @RequestParam 이용 ver.
		
		//@RequestMapping("/getBoardList.do")
		/* public String getBoardList(
				@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition,
				@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
				BoardVO vo, BoardDAO boardDAO, Model model) { // ModelAndView getBoardList(동일, ModelAndView mav)
			
			System.out.println("검색 조건 : " + condition);
			System.out.println("검색 단어 : " + keyword);
			// Model 정보 저장
			model.addAttribute("boardList", boardDAO.getBoardList(vo));	
			return "getBoardList.jsp";			// view 이름 리턴
			
			/*
			 * mav.addObject("boardList", boardDAO.getBoardList(vo)); //Model 정보 저장
			 * mav.setViewName("getBoardList.jsp"); // view 정보 저장 return mav;
			 */
		//}
		
}