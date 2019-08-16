package com.shiro.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.service.LoginService;
import com.shiro.utils.JWTUtil;


@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequiresRoles("bbb")
	@RequestMapping(value="/mon")
	public String index(){
		long timeout = SecurityUtils.getSubject().getSession().getTimeout();
		System.out.println(timeout+"毫秒");
		//Subject currentUser = SecurityUtils.getSubject();
//		System.out.println(currentUser.hasAllRoles(Arrays.asList("aaa,bbb,cc,guess")));
//		System.out.println(currentUser.hasRole("aaa"));
//		System.out.println(currentUser.hasRole("bbb"));
//		System.out.println(currentUser.hasRole("guess"));
//		System.out.println(currentUser.hasRole("ccc"));
		return "subin";
	}
	
	
	@RequestMapping(value="/test",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String com(@RequestBody String data){
		
		System.out.println(data);
		return "subin";
	}
	


	/**
	 * 登录
	 */
	@GetMapping("/auth")
	public String authLogin(String username,String password) {
		//return loginService.authLogin(username,password);
		try {
			return JWTUtil.createToken(username);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	  @RequestMapping(path = "/unauthorized/{message}")
	    public String unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
	        return message;
	    }
	
	/**
	 * 退出
	 */
	@RequiresPermissions("aa:cre")
	@GetMapping("/logout")
	public String logout() {
		loginService.logout();
		return "succ";
	}
	
	//@RequiresRoles(value = {"zzz","nnn"},logical = Logical.OR)
	@GetMapping("/exam")
	public String exam() {
//		long timeout = SecurityUtils.getSubject().getSession().getTimeout();
//		System.out.println(timeout+"毫秒");
		System.out.println("exam");
		RandomNumberGenerator randomNumberGenerator =new SecureRandomNumberGenerator();
		//5dec2477061af54165687790621cdacb-------------ca3b37e750628ee0c8d28702a659ad8a
		String stal="5dec2477061af54165687790621cdacb";
		String newPassword = new SimpleHash("md5","111111",ByteSource.Util.bytes(stal),2).toHex();
		System.out.println(stal+"-------------"+newPassword);
		return "succ";
	}
	/**
	 * 退出
	 */
	
	@GetMapping("/getUserInfo")
	public String getUserInfo() {
		loginService.getUserInfo();
		return "succ";
	}
	
}
