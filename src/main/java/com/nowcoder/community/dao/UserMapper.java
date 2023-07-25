package com.nowcoder.community.dao;
import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {

    User selectById(int id);    //根据id查询用户的方法

    User selectByName(String username); //根据用户名查用户，用户名是唯一的

    User selectByEmail(String email);   //根据邮箱查用户

    int insertUser(User user);

    int updateStatus(int id, int status);   //修改用户状态，返回修改几行数据

    int updateHeader(int id, String headerUrl); //更新头像

    int updatePassword(int id, String password);

}
