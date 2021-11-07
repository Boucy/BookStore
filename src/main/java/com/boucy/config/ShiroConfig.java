package com.boucy.config;

import com.boucy.realms.UserRealms;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //    1.创建shiroFilter   负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        配置系统受限资源
//        配置系统公共资源
        Map<String, String> map = new HashMap<String, String>();
//        map.put("/user/login", "anon");//anon设置为公共资源 放行资源放在下面
//        map.put("/user/register", "anon");//anon设置为公共资源 放行资源放在下面
//        //swagger资源放行
//        map.put("/swagger-ui.html", "anon");
//        map.put("/swagger-resources", "anon");
//        map.put("/v2/api-docs", "anon");
//        map.put("/webjars/springfox-swagger-ui/**", "anon");
//        map.put("/configuration/security", "anon");
//        map.put("/swagger-resources/configuration/ui", "anon");
//        map.put("/configuration/ui", "anon");

//        map.put("/**", "authc");//authc 请求这个资源需要认证和授权
//        默认认证界面路径---当认证不通过时跳转
//        shiroFilterFactoryBean.setLoginUrl("/login.html");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //    2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    //    3.创建自定义realm
    @Bean
    public Realm getRealm() {
        UserRealms userRealms = new UserRealms();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        userRealms.setCredentialsMatcher(credentialsMatcher);
        return userRealms;
    }


}
