package com.atguigu.crm.mapper;

import com.atguigu.crm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @InterfaceName: UserMapper
 * @author: admin
 * @since: 2020/3/12 21:16
 */
@Resource
@Mapper
public interface UserMapper {
   User login(@Param("name") String userName, @Param("password") String password);
   User getUserByName(@Param("name")String name);
   int postUserInfo(User user);
}
