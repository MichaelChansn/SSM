package com.ks.ssm.form.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ks.ssm.utils.EscapeUtils;

public class UserRegister {
	
	@NotBlank(message="昵称不能为空")
	@Length(min=2,max=15,message="必须在2到15个字符")
	private String userNickName;
	
	@NotBlank(message="email不能为空")
	@Email(message="email格式不正确")
	private String email;
	
	@NotBlank(message="密码不能为空")
	@Length(min=6,max=15,message="密码长度在6到15个字符之间")
	private String password;
	
	@NotBlank(message="密码确认不能为空")
	@Length(min=6,max=15,message="密码长度在6到15个字符之间")
	private String passwordConfirm;

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm.trim();
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = EscapeUtils.escapeString(userNickName.trim());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public boolean isSamePassword()
	{
		return this.password.equals(this.passwordConfirm);
	}
}
