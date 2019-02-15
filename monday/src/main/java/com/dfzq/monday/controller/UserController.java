package com.dfzq.monday.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value="/mon")
	public String index(){
		return "subin";
	}
	
}
