package com.shiro.core;

import com.alibaba.fastjson.JSONObject;
import com.shiro.entity.UserInfo;
import com.shiro.service.LoginService;
import com.shiro.utils.constants.Constants;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @description: 自定义Realm
 * 
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private LoginService loginService;

	@Autowired 
	public void setCredentialsMatcher(HashedCredentialsMatcher credentialsMatcher){
		super.setCredentialsMatcher(credentialsMatcher);
	}


	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		//查询用户的权限
		JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
		logger.info("permission的值为:" + permission);
		logger.info("本用户权限为:" + permission.get("permissionList"));
		//为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
		Set<String> roles=new HashSet<String>();
		roles.add("normal");
		roles.add("guess");
		//roles.add("manager");
		authorizationInfo.setRoles(roles);
		//	        List<Role> rolesByUserName = roleDao.getRolesByUserName(userName);
		//	        for(Role role:rolesByUserName) {
		//	            roles.add(role.getRoleName());
		//	        }
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * LoginController.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		String loginName = (String) authcToken.getPrincipal();
		// 获取用户密码
		String password = new String((char[]) authcToken.getCredentials());
		UserInfo user = loginService.queryUser(loginName, password);
		if (user == null) {
			//没找到帐号
			throw new UnknownAccountException();
		}
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUsername(),
				user.getPassword(),
				ByteSource.Util.bytes(Constants.SHIRO_SALT), 
				getName()
				);
		//session中不需要保存密码
		user.setPassword(null);
		//将用户信息放入session中
		SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
		return authenticationInfo;
	}
	
	
	
	
	
}
