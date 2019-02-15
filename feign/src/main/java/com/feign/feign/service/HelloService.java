package com.feign.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feign.feign.rpc.GetHello;

@Service
public class HelloService {
	
	@Autowired GetHello getHello;  
	public String sayHello(){
		return getHello.sayHello();
	}

}
