package com.shiro.service;


import com.shiro.entity.UserInfo;

public interface LoginService {
	
	public UserInfo queryUser(String username,String password);

	public String authLogin(String username,String password);
	
	public void logout();

	public void getUserInfo();

}
