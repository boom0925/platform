package com.shiro.core;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 测试加密密码
 * @author wbsubin
 *
 */
public class TestRoleFilter{
  public static void main(String args[]){
	  String password = md5("111111", "shiro");
	    System.out.println(password);
  }
  
  public static final String md5(String password, String salt){
	    //加密方式
	    String hashAlgorithmName = "md5";
	    //盐：为了即使相同的密码不同的盐加密后的结果也不同
	    ByteSource byteSalt = ByteSource.Util.bytes(salt);
	    //密码
	    Object source = password;
	    //加密次数
	    int hashIterations = 2;
	    SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
	    return result.toString();
	}
}
