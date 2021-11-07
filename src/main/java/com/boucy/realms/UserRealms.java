package com.boucy.realms;

import com.boucy.pojo.User;
import com.boucy.service.UserService;
import com.boucy.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;

public class UserRealms extends AuthorizingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //根据身份信息,从传过来的token获取得到email
        String principal = (String) token.getPrincipal();
        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        //根据身份信息查询
        User user = userService.findUserByEmail(principal);
        //用户不为空
        if (!ObjectUtils.isEmpty(user)) {
            //返回数据库信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();

        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findUserByEmail(primaryPrincipal);
        if (!ObjectUtils.isEmpty(user.getUserType())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(user.getUserType());
            return simpleAuthorizationInfo;
        }
        return null;
    }

}
