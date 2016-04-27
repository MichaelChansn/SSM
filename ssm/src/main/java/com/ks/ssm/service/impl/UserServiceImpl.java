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

	@Override
	public User getUserById(long userId) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public User selectByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.selectByUserName(username);
	}

	@Override
	public  User selectByEmail(String email ) {
		// TODO Auto-generated method stub
		return userDao.selectByEmail(email);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return userDao.insert(record);
	}


	

	

}
