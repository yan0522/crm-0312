package com.atguigu.crm.controller;

/**
 * TODO
 *
 * @ClassName: UserHandler
 * @author: admin
 * @since: 2020/3/12 21:15
 */
public class UserHandler {

   /* @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/add")
    public int postUserInfo(User user){
        // shiro 自带的工具类生成salt
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();

        String encodedPassword = ShiroEncryption.shiroEncryption(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        return userMapper.postUserInfo(user);
    }*/
}
