package com.shiro.controller;

import java.io.UnsupportedEncodingException;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.utils.JWTUtil;


@RestController
@RequestMapping("/login")
public class LoginController {

	


	/**
	 * 登录
	 */
	@GetMapping("/auth")
	public String authLogin(String username,String password) {
		try {
			if(username.equals("su") && password.equals("111111")) {
				return JWTUtil.createToken(username);	
			}
			
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
	
	
	
	@GetMapping("/test")
	public String test() {
		return "succ";
	}
	
}
