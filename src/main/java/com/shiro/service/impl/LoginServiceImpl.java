package com.shiro.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shiro.dao.LoginMapper;
import com.shiro.entity.UserInfo;
import com.shiro.service.LoginService;
import com.shiro.utils.constants.Constants;

@Service
public class LoginServiceImpl implements LoginService{
	
	private  Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private LoginMapper mapper;
	
	@Override
	public UserInfo queryUser(String username, String password) {
		// TODO Auto-generated method stub
		return mapper.queryUser(username, password);
	}

	@Override
	public String authLogin(String username, String password) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			currentUser.login(token);
			 log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@Override
	public void logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getUserInfo() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			 log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
			 Session session = SecurityUtils.getSubject().getSession();
				UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_INFO);
				System.out.println(userInfo.toString());
				Set<String> menuList = new HashSet<>();
				menuList.add("aa");
				menuList.add("bb");
				Set<String> permissionList = new HashSet<>();
				permissionList.add("aa:cre");
				permissionList.add("bb:del");
				JSONObject info = new JSONObject();
				info.put("menuList", menuList);
				info.put("permissionList", permissionList);
				session.setAttribute(Constants.SESSION_USER_PERMISSION, info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
