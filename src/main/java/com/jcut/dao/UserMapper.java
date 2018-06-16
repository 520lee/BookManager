package com.jcut.dao;

import com.jcut.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    int checkPhone(String phone);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username")String username, @Param("passwordNew")String passwordNew);

    int checkPassword(@Param("password")String password, @Param("userId")Integer userId);

    int checkPhoneByUserId(@Param("phone")String phone, @Param("userId")Integer userId);
}