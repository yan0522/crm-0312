package com.atguigu.crm.controller;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * TODO
 *
 * @ClassName: LoginController
 * @author: admin
 * @since: 2020/3/16 17:26
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    User user;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @PostMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password,
                        HttpSession session){
        Subject subject = SecurityUtils.getSubject();

        System.out.println("/user/login进入了--------");
        if (!subject.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,password);
            //usernamePasswordToken.setRememberMe(true);
            try {
                subject.login(usernamePasswordToken);
                System.out.println("开始登录");
                Object user = subject.getPrincipals().getPrimaryPrincipal();
                session.setAttribute("user",user);

            }catch (AuthenticationException e){
                session.setAttribute("msg","用户密码错误！");
                System.out.println("登陆失败:"+ e.getMessage());
                return "redirect:/index";
            }
        }
        return "redirect:/main.html";
    }
    @PostMapping("/add")
    public String postUserInfo(@RequestParam("name") String name, @RequestParam("password") String password, Map<String,String> map){
        System.out.println("添加用户开始啦------------");
        //盐值
        ByteSource salt = ByteSource.Util.bytes(name);
        //加密次数
        int times = 1024;
        // 算法名称
        String algorithmName = "md5";
        //计算加密后的密码
        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
        User username = userService.getUserByName(name);
        if(username != null && !"".equals(username)){
            map.put("msg","用户名已存在！");
            return "redirect:/index";
        }
        user.setName(name);
        user.setSalt(salt.toString());
        user.setPassword(encodedPassword);
        userMapper.postUserInfo(user);
        return "redirect:/index";
    }
}
