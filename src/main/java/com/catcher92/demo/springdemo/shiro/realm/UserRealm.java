package com.catcher92.demo.springdemo.shiro.realm;


import com.catcher92.demo.springdemo.constant.UserConstant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by caoxuedong on 2017/2/20.
 */
public class UserRealm extends AuthorizingRealm{

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        if ("admin".equals(userName)) {
            if (!"123456".equals(password)) {
                throw new IncorrectCredentialsException();
            }
            return new SimpleAuthenticationInfo(userName, password, getName());
        } else if ("test".equals(userName)){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
            SimplePrincipalCollection collection = new SimplePrincipalCollection();
            if ("111111".equals(password)) {
                collection.add(userName, getName());
            } else if ("123456".equals(password)) {
                collection.add(userName, getName());
                collection.add("test2", getName());
            }
            info.setPrincipals(collection);
            info.setCredentials(password);
            return info;
        } else if ("caoxuedong".equals(userName)) {
            throw new LockedAccountException();
        } else {
            throw new UnknownAccountException();
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 如果登录认证时候有多个用户，这里会返回集合中的第一个
        String username = (String) getAvailablePrincipal(principals);
        Set<String> roles = null;
        Set<String> permissions = null;
        if ("admin".equals(username)) {
            // 角色
            roles = new HashSet<>(2);
            roles.add(UserConstant.ROLES_ADMIN);
            roles.add(UserConstant.ROLES_USER);
            // 权限
            permissions = new HashSet<>(1);
            permissions.add(UserConstant.PERMISSIONS_USER_ALL);
        } else if ("test".equals(username)) {
            // 角色
            roles = new HashSet<>(1);
            roles.add(UserConstant.ROLES_USER);
            // 权限
            permissions = new HashSet<>(1);
            permissions.add(UserConstant.PERMISSIONS_USER_GET);
        }
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }
}
