package com.ks.ssm.form.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserRegister {
	
	@NotEmpty
	@NotBlank
	@Length(min=3,max=50)
	private String userNickName;
	
	@NotEmpty
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@NotEmpty
	@Length(min=6,max=50)
	private String password;
	
	@NotBlank
	@NotEmpty
	@Length(min=6,max=50)
	private String passwordConfirm;

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSamePassword()
	{
		return this.password.equals(this.passwordConfirm);
	}
}
