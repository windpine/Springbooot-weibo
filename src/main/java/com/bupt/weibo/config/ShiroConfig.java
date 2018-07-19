package com.bupt.weibo.config;

import com.bupt.weibo.shiro.ShiroSessionDao;
import com.bupt.weibo.shiro.ShiroSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * LifecycleBeanPostProcessor,DestructionAwareBeanPostProcessor的子类
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期，初始化和销毁
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置Realm
        securityManager.setRealm(myShiroRealm(matcher));
        //设置会话管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * ShirpFilterFactoryBean, 生成ShiroFilter
     * securityManager,filters,filterChainDefinitionManager
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager(hashedCredentialsMatcher()));

        Map<String, Filter> filters = new LinkedHashMap<String,Filter>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setLoginUrl("/notAuthc");

        Map<String,String> filterChainDefinitionManager = new LinkedHashMap<String,String>();
        filterChainDefinitionManager.put("/userInfo","authc");
        filterChainDefinitionManager.put("/users/**","authc");
        filterChainDefinitionManager.put("/admin/**","roles[Admin]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/notAuthz");
        return shiroFilterFactoryBean;
    }


    @Bean(name = "myShiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm myShiroRealm(HashedCredentialsMatcher matcher){
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }

    /**
     * CachingShiroSessionDao
     */
    @Bean(name = "shiroSessionDao")
    public ShiroSessionDao shiroSessionDao(){
        return new ShiroSessionDao();
    }
    /**
     * EhCacheManager,缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来
     */
    @Bean(name = "sessionFactory")
    public ShiroSessionFactory shiroSessionFactory() {
        return new ShiroSessionFactory();
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        //manager.setCacheManager(cacheManager);// 加入缓存管理器
        manager.setSessionFactory(shiroSessionFactory());//设置sessionFactory
        manager.setSessionDAO(shiroSessionDao());// 设置SessionDao
        manager.setDeleteInvalidSessions(true);// 删除过期的session
        manager.setGlobalSessionTimeout(shiroSessionDao().getExpireTime());// 设置全局session超时时间
        manager.setSessionValidationSchedulerEnabled(true);// 是否定时检查session
        return manager;
    }



    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

}
