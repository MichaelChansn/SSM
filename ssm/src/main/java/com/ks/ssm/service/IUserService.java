package com.ks.ssm.service;

import com.ks.ssm.domain.User;

public interface IUserService {
	public User getUserById(long userId);
	public User selectByUserName(String username );
	public User selectByEmail(String email );
	public int insertSelective(User record);
	public int updateByPrimaryKeySelective(User record);

}
