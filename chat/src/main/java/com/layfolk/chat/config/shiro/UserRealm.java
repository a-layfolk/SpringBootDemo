package com.layfolk.chat.config.shiro;

import com.layfolk.chat.pojo.User;
import com.layfolk.chat.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("------授权------");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("------认证------");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        // 连接真实的数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if(user == null) {  // 没有这个人
            return null;    // 抛出异常
        }

        // 密码认证 shiro 做

        return new SimpleAuthenticationInfo("", user.getPassword(), "");
    }
}
