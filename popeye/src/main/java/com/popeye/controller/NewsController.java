package com.popeye.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.popeye.entity.news.NewsPojo;
import com.popeye.service.news.INewsService;


@Controller
@RequestMapping("/news")
public class NewsController {
	private Logger logger=LoggerFactory.getLogger(NewsController.class);
	
	@Autowired INewsService iNewsService;
	
	@RequestMapping(value = "/getAllNews.json",method = RequestMethod.GET)
	@ResponseBody
	public String getAllNews(){
		logger.debug("-------------begining-------------");
		List<NewsPojo> list=iNewsService.getAllNews();
		for (int i = 0; i < list.size(); i++) {
			logger.info(list.get(i).getId()+"---"+list.get(i).getTitle());
		}
		return "1";
	}
	
	@RequestMapping(value = "/forword")
	public String forword(Model model){
		logger.debug("-------------begining2-------------");
		model.addAttribute("name","subin");
		return "news";
	}
	
}
