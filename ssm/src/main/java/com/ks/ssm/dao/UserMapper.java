package com.ks.ssm.dao;

import java.util.List;

import com.ks.ssm.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    
    User selectByUserName(String username );
    
    List<User> selectByEmail(String email );

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}