package com.atguigu.crm.component;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * TODO
 *
 * @ClassName: ShiroEncryption
 * @author: admin
 * @since: 2020/3/18 16:52
 */
public class ShiroEncryption {
    /***
     * 对用户的密码进行MD5加密
     * 做成工具类
     */
    public static String shiroEncryption(String password, String salt) {

        // shiro 自带的工具类生成salt
        //String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 加密次数
        int times = 1024;
        // 算法名称
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
        System.out.println("加密后的密码:"+encodedPassword);
        // 返回加密后的密码
        return encodedPassword;
    }
}
