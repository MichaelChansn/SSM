package com.ks.ssm.form.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ForgetPassword {
	
	@NotBlank(message="填写绑定的email")
	@Email(message="email格式错误")
	private String email;
	
	@NotBlank(message="请填写验证码")
	private String captcha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
