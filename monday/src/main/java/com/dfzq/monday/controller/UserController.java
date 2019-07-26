package com.dfzq.monday.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dfzq.monday.utils.RedisUtil;

@RestController
public class UserController {

	 private static int ExpireTime = 60;   // redis中存储的过期时间60s

	    @Resource
	    private RedisUtil redisUtil;
	
	@RequestMapping(value="/mon")
	public String index(){
		return "subin";
	}
	
	@RequestMapping(value="/test",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String com(@RequestBody String data){
		
		System.out.println(data);
		return "subin";
	}
	
	

	@RequestMapping(value="/setObject")
	public String setObject(String key,String value){
		 System.out.println( redisUtil.set(key,value,ExpireTime));;
		return "subin";
	}
	
	

	@RequestMapping(value="/getObject")
	public String getObject(String key){
		System.out.println(redisUtil.get(key));
		return redisUtil.get(key).toString();
	}
	
	
	
}
