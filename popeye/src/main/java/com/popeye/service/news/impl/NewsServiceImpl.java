package com.popeye.service.news.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeye.dao.mapper.NewsMapper;
import com.popeye.entity.news.NewsPojo;
import com.popeye.service.news.INewsService;

@Service
public class NewsServiceImpl implements INewsService{

	@Autowired NewsMapper newsMapper;
	
	@Override
	public List<NewsPojo> getAllNews() {
		// TODO Auto-generated method stub
		return newsMapper.getAllNews();
	}

}
