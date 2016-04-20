package com.ks.ssm.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ks.ssm.dao.UserMapper;
import com.ks.ssm.domain.User;
import com.ks.ssm.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userDao;

	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

	

	

}
