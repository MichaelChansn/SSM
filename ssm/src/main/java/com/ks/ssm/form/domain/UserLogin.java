package com.ks.ssm.form.domain;

import org.hibernate.validator.constraints.NotBlank;

import com.ks.ssm.utils.EscapeUtils;

public class UserLogin {
	
	@NotBlank(message="用户名不能为空")
	private String userName;
	
	@NotBlank(message="密码不能为空")
	private String password;	
	
	private boolean rememberMe;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = EscapeUtils.escapeString(userName.trim());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	
	

}
