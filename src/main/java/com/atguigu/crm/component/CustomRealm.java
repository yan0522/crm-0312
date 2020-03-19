package com.atguigu.crm.component;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * TODO
 *
 * @ClassName: CustomRealm
 * @author: admin
 * @since: 2020/3/16 21:12
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录用户名
        String name = (String) principals.getPrimaryPrincipal();
        System.out.println("获取用户名成功");
        //根据用户名去数据库查询用户信息
        User user = userService.getUserByName(name);
        System.out.println("查询数据库成功");
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<Role> roleSet = user.getRoles();
        for (Role role : roleSet){
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            System.out.println("添加角色成功");
            //添加权限
            for (Authority authority : role.getAuthorities()){
                simpleAuthorizationInfo.addStringPermission(authority.getName());
            }
            System.out.println("添加权限成功");
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String name = usernamePasswordToken.getUsername();
        //上下两种都可以
        //String name = token.getPrincipal().toString();
        User user = userService.getUserByName(name);
        if (user == null) {
            throw new UnknownAccountException("用户名[" + name + "]不存在!");
        }else{
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            Object principal = user;
            Object credentials = user.getPassword();
            String realName = getName();
            ByteSource salt = ByteSource.Util.bytes(name);
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,credentials, salt,realName);
            System.out.println("认证成功了");
            return simpleAuthenticationInfo;
        }
    }
}
