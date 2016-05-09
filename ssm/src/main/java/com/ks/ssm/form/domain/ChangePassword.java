package com.ks.ssm.form.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ks.ssm.utils.CommonUtils;

public class ChangePassword {
	
	@NotBlank(message="密码不为空")
	@Length(min=6,max=15,message="密码必须是6-15个字符")
	private String password;
	
	@NotBlank(message="密码不为空")
	@Length(min=6,max=15,message="密码必须是6-15个字符")
	private String passwordConfirm;

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public  boolean isSame()
	{
		if(CommonUtils.isBlank(password) || CommonUtils.isBlank(passwordConfirm))
			return false;
		
		return password.equals(passwordConfirm);
	}
	
}
