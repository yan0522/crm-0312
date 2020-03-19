package com.atguigu.crm.service.impl;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @ClassName: UserServiceImpl
 * @author: admin
 * @since: 2020/3/12 21:36
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        return userMapper.login(userName,password);
    }

    @Override
    public User getUserByName(String name) {
        User user = userMapper.getUserByName(name);
        return user;
    }

    @Override
    public int postUserInfo(User user) {
        return 0;
    }
}
