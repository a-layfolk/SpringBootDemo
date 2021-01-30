package com.layfolk.chat.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    // 3. ShiroFilterFactoryBean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);


         // 添加 shiro 的内置过滤器
         /*
            anon: 无需认证就可以访问
            authc: 必须认证才能访问
            user: 必须拥有 记住我 功能才能用
            perms: 拥有对某资源的权限才能访问
            role: 拥有某个角色权限访问
         */

        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/user/add", "authc");
        filterMap.put("/user/update", "authc");

        // 设置过滤链
        bean.setFilterChainDefinitionMap(filterMap);
        // 设置等率请求, 不满足认证条件全部跳转到 login 页面
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    // 2. DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联  realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    // 1. realm 对象, 需要自定义
    @Bean(name="userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }
}
