package com.ks.ssm.dao;

import com.ks.ssm.domain.Admin;

public interface AdminMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);
    
    Admin selectByAdminName(String adminname );

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}