package com.shiro.core;

import com.alibaba.fastjson.JSONObject;
import com.shiro.entity.UserInfo;
import com.shiro.service.LoginService;
import com.shiro.utils.JWTUtil;
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
 * @author: hxy
 * @description: 自定义Realm
 * @date: 2017/10/24 10:06
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private LoginService loginService;

//	@Autowired 
//	public void setCredentialsMatcher(HashedCredentialsMatcher credentialsMatcher){
//		super.setCredentialsMatcher(credentialsMatcher);
//	}


	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		Session session = SecurityUtils.getSubject().getSession();
//		//查询用户的权限
//		JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
//		logger.info("permission的值为:" + permission);
//		logger.info("本用户权限为:" + permission.get("permissionList"));
//		//为当前用户设置角色和权限
//		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
//		Set<String> roles=new HashSet<String>();
//		roles.add("aaa");
//		roles.add("bbb");
//		roles.add("guess");
//		authorizationInfo.setRoles(roles);
//		//	        List<Role> rolesByUserName = roleDao.getRolesByUserName(userName);
//		//	        for(Role role:rolesByUserName) {
//		//	            roles.add(role.getRoleName());
//		//	        }
//		return authorizationInfo;
//	}
//
//	/**
//	 * 验证当前登录的Subject
//	 * LoginController.login()方法中执行Subject.login()时 执行此方法
//	 */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//		String loginName = (String) authcToken.getPrincipal();
//		// 获取用户密码
//		String password = new String((char[]) authcToken.getCredentials());
//		UserInfo user = loginService.queryUser(loginName, password);
//		if (user == null) {
//			//没找到帐号
//			throw new UnknownAccountException();
//		}
//		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//				user.getUsername(),
//				user.getPassword(),
//				ByteSource.Util.bytes(Constants.SHIRO_SALT), 
//				getName()
//				);
//		//session中不需要保存密码
//		user.setPassword(null);
//		//将用户信息放入session中
//		SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
//		return authenticationInfo;
//	}
	
	
	
	/**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String loginName = (String) authenticationToken.getPrincipal();
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        return new SimpleAuthenticationInfo(token, token,getName());
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> menuList = new HashSet<>();
		menuList.add("aa");
		menuList.add("bb");
		Set<String> permissionList = new HashSet<>();
		permissionList.add("aa:cre");
		permissionList.add("bb:del");
		JSONObject info = new JSONObject();
		info.put("menuList", menuList);
		info.put("permissionList", permissionList);
		authorizationInfo.addStringPermissions((Collection<String>)info);
		Set<String> roles=new HashSet<String>();
		roles.add("aaa");
		roles.add("bbb");
		roles.add("guess");
		authorizationInfo.setRoles(roles);
		//	        List<Role> rolesByUserName = roleDao.getRolesByUserName(userName);
		//	        for(Role role:rolesByUserName) {
		//	            roles.add(role.getRoleName());
		//	        }
        return authorizationInfo;
    }
	
	
	
	
	
	
}
