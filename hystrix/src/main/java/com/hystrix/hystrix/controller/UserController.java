package com.hystrix.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hystrix.hystrix.service.HelloService;


@RestController
public class UserController {

	@Autowired HelloService helloService;
	
	@GetMapping(value="/hello")
	public String index(String name){
		return helloService.sayHello(name);
	}
	
}
