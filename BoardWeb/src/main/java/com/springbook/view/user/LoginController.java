package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController  { //implements Controller

	//ModelAttribute 1) : controller 메소드의 매개변수로 선언된 Command객체의 이름을 변경할때 사용(ex. UserVO => user) 
	
	//annotation ver. controller 통합 => ModelAttribute 사용 
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(@ModelAttribute("user")UserVO vo) { //UserVO vo
		System.out.println("로그인 처리");
		System.out.println("로그인 화면으로 이동");
		vo.setId("test");
		vo.setPassword("test1234");
		return "login.jsp";
	}
	
	//servlet API 사용
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		
		//예외처리 1)
		if(vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야합니다.");
		}
		UserVO user = userDAO.getUser(vo);
		
		System.out.println("로그인 처리");
		System.out.println("로그인 인증 처리...");
		
		if(userDAO.getUser(vo) != null) {
			// parameter의 session에 {"userName" : "관리자"} => key:value로 mapping한 하나의 변수가 생성
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}
	
	/*
	 * @RequestMapping("/login.do") public String login(UserVO vo, UserDAO userDAO)
	 * { if(userDAO.getUser(vo) != null) return "getBoardList.do"; else return
	 * "login.jsp"; }
	 */
	
	
	/*
	 * @Override public ModelAndView handleRequest(HttpServletRequest request,
	 * HttpServletResponse response) { System.out.println("로그인 처리");
	 * 
	 * // 1. 사용자 입력 정보 추출 String id = request.getParameter("id"); String password =
	 * request.getParameter("password");
	 * 
	 * // 2. DB 연동 처리 UserVO vo = new UserVO(); vo.setId(id);
	 * vo.setPassword(password);
	 * 
	 * UserDAO userDAO = new UserDAO(); UserVO user = userDAO.getUser(vo);
	 * 
	 * // 3. 화면 네비게이션 =>login단계에선 model에 담을 객체는 없다. ModelAndView mav = new
	 * ModelAndView(); if (user != null) { // return "getBoardList.do"; //
	 * mav.setViewName("getBoardList.do");
	 * 
	 * // presentation-layer의 ViewResolver를 사용하기 위한 변경
	 * mav.setViewName("redirect:getBoardList.do");
	 * 
	 * } else { // return "login";
	 * 
	 * // mav.setViewName("login.jsp");
	 * 
	 * // presentation-layer의 ViewResolver를 사용하기 위한 변경
	 * mav.setViewName("redirect:login.jsp"); } return mav; }
	 */

}