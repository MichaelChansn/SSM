package com.ks.ssm.service;

import java.util.List;

import com.ks.ssm.domain.User;

public interface IUserService {
	public User getUserById(long userId);
	public User selectByUserName(String username );
	public List<User> selectByEmail(String email );

}
