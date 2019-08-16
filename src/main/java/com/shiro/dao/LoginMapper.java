package com.shiro.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shiro.entity.UserInfo;

@Mapper
public interface LoginMapper {
	
	public UserInfo queryUser(@Param("username")String username,@Param("password")String password);

}
