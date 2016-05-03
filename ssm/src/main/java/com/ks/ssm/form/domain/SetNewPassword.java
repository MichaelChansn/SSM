package com.ks.ssm.form.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class SetNewPassword {
	
	@NotBlank(message="密码不能为空6到15字符")
	@Length(min=6,max=15)
	private String newPassword;
	
	@NotBlank(message="重复密码不能为空")
	@Length(min=6,max=15)
	private String newPasswordConfirm;

	@NotBlank(message="请填写验证码")
	private String captcha;
	
	private String token;
	
	
	public boolean isSamePassword()
	{
		return this.newPassword.equals(this.newPasswordConfirm);
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
