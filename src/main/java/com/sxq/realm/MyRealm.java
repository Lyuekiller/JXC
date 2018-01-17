package com.sxq.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.sxq.entity.User;
import com.sxq.repository.UserRepository;

/**
 * 自定义realm
 * @author wzd
 *
 */
public class MyRealm extends AuthorizingRealm{
	@Resource
	private UserRepository userRepository;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 是否有权限
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();//获得用户名
		User user = userRepository.findByUserName(userName);
		if(user!=null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"sxq");
			return authcInfo;
		}else {
			return null;
		}
	}

}
