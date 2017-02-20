package com.catcher92.demo.springdemo.shiro.realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;

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
            return new SimpleAuthenticationInfo(userName, password, getName());
        } else if ("test".equals(userName)){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
            SimplePrincipalCollection collection = new SimplePrincipalCollection();
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
        return null;
    }
}