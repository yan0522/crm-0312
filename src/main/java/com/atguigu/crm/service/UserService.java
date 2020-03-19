package com.atguigu.crm.service;

import com.atguigu.crm.entity.User;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @ClassName: UserService
 * @author: admin
 * @since: 2020/3/12 21:15
 */
@Service
public interface UserService {

    User login(String userName, String password);
    User getUserByName(String name);
    int postUserInfo(User user);
}
