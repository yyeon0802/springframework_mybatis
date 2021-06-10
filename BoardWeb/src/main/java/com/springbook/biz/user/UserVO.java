package com.springbook.biz.user;


//VO(value object)
public class UserVO {

	private String id;
	private String password;
	private String name;
	private String role;
	//비교를 위한 id가져오기 : 비교할 일이 아니면, column명과 맞춰서 변수선언하는 것이 낫다.
	private String id2;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
	
	
	
}