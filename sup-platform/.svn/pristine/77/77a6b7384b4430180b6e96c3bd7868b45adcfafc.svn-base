package cn.com.ssj.shiro;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ssj.mybatis.model.User;
import cn.com.ssj.service.UserService;

@Service
public class ShiroRealm extends AuthorizingRealm {
	private static final Logger logger = LogManager.getLogger(ShiroRealm.class);

	@Autowired
	UserService userService;


	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		logger.info("doGetAuthorizationInfo");
		Object object = arg0.fromRealm(getName()).iterator().next();
		SimpleAuthorizationInfo authorizationInfo = null;
		if(object instanceof User) {
			User user = (User) object;

		}
		return authorizationInfo;
	}

	/**
	 * 登录认证;
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		logger.info("doGetAuthenticationInfo");
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		//User user = userService.findByUsername(token.getUsername());
		User user = new User();
		user.setUserId(1);
		if (user != null) {
			return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		}
		return null;
	}

}
