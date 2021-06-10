package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	//UserDAO를 가지고 DB와 연동하기 위함
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	//userService interface override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
}