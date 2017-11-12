package com.seki.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seki.bean.Role;
import com.seki.bean.UserLogin;
import com.seki.service.RoleService;
import com.seki.service.UserLoginService;

@Component
public class LoginRealm extends AuthorizingRealm {

	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private RoleService roleService;

	/**
	 * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息 当调用权限验证时，就会调用此方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)  {

		String username = (String) getAvailablePrincipal(principalCollection);

		Role role = null;

		try {
			UserLogin userLogin = userLoginService.findByName(username);
			// 获取角色对象
			role = roleService.findByid(userLogin.getRole());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 通过用户名从数据库获取权限/角色信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> r = new HashSet<String>();
		if (role != null) {
			r.add(role.getRoleName());
			info.setRoles(r);
		}

		return info;
	}

	/**
	 * 在这个方法中，进行身份验证 login时调用
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 用户名
		String username = (String) upToken.getUsername();
		// 密码
		String password = new String((char[])upToken.getPassword());

		UserLogin userLogin = null;
		try {
			userLogin = userLoginService.findByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (userLogin == null || !password.equals(userLogin.getPassword())) {
			throw new AuthenticationException();
		} 
		
		String hashAlgorithmName = "MD5";
		int hashIterations = 1024;
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		Object result = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
		
		// 身份验证通过,返回一个身份信息
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, result, credentialsSalt, getName());

		return info;
	}

}
