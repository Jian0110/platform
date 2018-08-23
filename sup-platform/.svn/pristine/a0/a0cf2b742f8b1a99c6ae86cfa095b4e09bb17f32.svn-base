import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	private static final Logger logger = LogManager.getLogger(ShiroConfig.class);

	/*
	@Autowired
	private ShiroRealm shiroRealm;
	*/
	
	

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Value("${shiro.loginUrl}") String loginUrl, @Value("${shiro.unauthorizedUrl}") String unauthorizedUrl,
			@Value("${shiro.successUrl}") String successUrl, @Value("${shiro.hashAlgorithmName}") String hashAlgorithmName, @Value("${shiro.hashIterations}") int hashIterations,DefaultWebSecurityManager defaultWebSecurityManager) {

		logger.info("shiroFilterFactoryBean");
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		//filterChainDefinitionMap.put("/refuse", "anon");
		//filterChainDefinitionMap.put("/login", "anon");
		//filterChainDefinitionMap.put("/assets/**", "anon");
		//filterChainDefinitionMap.put("/ace/**", "anon");
		//filterChainDefinitionMap.put("/demo/**", "anon");
		//filterChainDefinitionMap.put("/websocket/**", "anon");
		//filterChainDefinitionMap.put("/**", "user");
		//filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "anon");

		

		// 加密管理器 这里是指密码加密，在shiro.properties里配置的，，md5 可以指定加密的次数
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
		hashedCredentialsMatcher.setHashIterations(hashIterations);
		// 加密管理器先关掉，，这样比对 的是密码的明文
		// shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);

		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/login");

		Map<String, Filter> filters = new HashMap<>();
		filters.put("logout", logoutFilter);

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		//shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		shiroFilterFactoryBean.setSuccessUrl(successUrl);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm, SessionManager sessionManager) {
		// cookie
		SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
		rememberMeCookie.setMaxAge(604800); // 7天
		rememberMeCookie.setHttpOnly(true);
		// cookie 管理
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie);

		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
		defaultWebSecurityManager.setSessionManager(sessionManager);
		defaultWebSecurityManager.setRealm(realm);
		return defaultWebSecurityManager;
	}
	
	@Bean  
    public SessionManager getSessionManager(){
		SimpleCookie sessionIdCookie = new SimpleCookie("sid");
		//sessionIdCookie.setMaxAge(-1000);
		//sessionIdCookie.setMaxAge(1);
		sessionIdCookie.setHttpOnly(true);

		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(sessionIdCookie);
		sessionManager.setSessionIdUrlRewritingEnabled(false);// JSESSIONID
		return sessionManager;
    }  

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}

}
