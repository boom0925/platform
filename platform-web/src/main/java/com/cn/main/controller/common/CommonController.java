package com.cn.main.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/common")
public class CommonController {
	private Logger logger=LoggerFactory.getLogger(CommonController.class);
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	@ResponseBody
	public String test(){
		logger.debug("-------------begining-------------");
		return "1";
	}
	
}
