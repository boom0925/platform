package com.shiro.controller;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.service.LoginService;


@RestController
@RequestMapping("/login")
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	/**
	 * 测试角色
	 * @return
	 */
	@RequiresRoles("normal")
	@RequestMapping(value="/normal")
	public String normal(){
		//查看session时间 
		long timeout = SecurityUtils.getSubject().getSession().getTimeout();
		System.out.println(timeout+"毫秒");
		logger.info("进入了normal 方法！ ");
		return "succ";
	}
	
	
	@RequiresRoles("admin")
	@RequestMapping(value="/admin")
	public String admin(){
		logger.info("进入了admin 方法！ ");
		return "succ";
	}
	
	/**
	 * 测试多个角色
	 * @return
	 */
	@RequiresRoles(value = {"admin","normal"},logical = Logical.OR)
	@GetMapping("/more")
	public String more() {
		logger.info("进入了more 方法！ ");
		return "succ";
	}
	
	/**
	 * 测试权限
	 */
	
	@RequiresPermissions("buy:update")
	@GetMapping("/update")
	public String update() {
		logger.info("进入了update 方法！ ");
		return "succ";
	}
	
	
	@RequiresPermissions("buy:insert")
	@GetMapping("/insert")
	public String insert() {
		logger.info("进入了insert 方法！ ");
		return "succ";
	}
	

	/**
	 * 登录
	 */
	@GetMapping("/auth")
	public String authLogin(String username,String password) {
		return loginService.authLogin(username,password);
	}
	
	
	/**
	 * 退出
	 */
	@GetMapping("/logout")
	public String logout() {
		loginService.logout();
		return "succ";
	}
	

	/**
	 *  查看用户权限
	 */
	
	@GetMapping("/getUserInfo")
	public String getUserInfo() {
		loginService.getUserInfo();
		return "succ";
	}
	
	/**
	 * 需要manager角色
	 */
	
	@GetMapping("/manager")
	public String manager() {
		logger.info("进入了manager 方法！ ");
		return "succ";
	}
	
	
}
