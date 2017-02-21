package com.catcher92.demo.springdemo.shiro.realm;


import com.catcher92.demo.springdemo.constant.UserConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
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
        // todo 这里应该连接数据库进行查询
        if ("admin".equals(userName)) {
            if (!"123456".equals(password)) {
                throw new IncorrectCredentialsException();
            }
            // 角色
            Set<String> roles = new HashSet<>(2);
            roles.add(UserConstant.ROLES_ADMIN);
            roles.add(UserConstant.ROLES_USER);
            SecurityUtils.getSubject().getSession(false).setAttribute(UserConstant.ROLES, roles);
            // 权限
            Set<String> permissions = new HashSet<>(1);
            permissions.add(UserConstant.PERMISSIONS_USER_ALL);
            SecurityUtils.getSubject().getSession(false).setAttribute(UserConstant.PERMISSIONS, permissions);
            return new SimpleAuthenticationInfo(userName, password, getName());
        } else if ("test".equals(userName)){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
            SimplePrincipalCollection collection = new SimplePrincipalCollection();
            // 角色
            Set<String> roles = new HashSet<>(1);
            roles.add(UserConstant.ROLES_USER);
            SecurityUtils.getSubject().getSession(false).setAttribute(UserConstant.ROLES, roles);
            // 权限
            Set<String> permissions = new HashSet<>(1);
            permissions.add(UserConstant.PERMISSIONS_USER_GET);
            SecurityUtils.getSubject().getSession(false).setAttribute(UserConstant.PERMISSIONS, permissions);
            if ("111111".equals(password)) {
                collection.add("test1", getName());
            } else if ("123456".equals(password)) {
                collection.add("test2", getName());
                collection.add("test2@qq.com", getName());
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
        Session session = SecurityUtils.getSubject().getSession(false);
        info.setRoles((Set<String>)session.getAttribute(UserConstant.ROLES));
        info.setStringPermissions((Set<String>)session.getAttribute(UserConstant.PERMISSIONS));
        return info;
    }
}
