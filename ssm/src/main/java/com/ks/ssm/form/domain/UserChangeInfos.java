package com.ks.ssm.form.domain;

import com.ks.ssm.utils.CommonUtils;
import com.ks.ssm.utils.EscapeUtils;

public class UserChangeInfos {
	
	private String userName;
	
	private String email;
	
	private Boolean gender;
	
	private String birthday;


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		if(!CommonUtils.isBlank(userName))
		this.userName = EscapeUtils.escapeString(userName.trim());
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		if(!CommonUtils.isBlank(email))
		this.email =EscapeUtils.escapeString(email.trim());;
	}


	public Boolean isGender() {
		return gender;
	}


	public void setGender(Boolean gender) {
		if(gender!=null)
		this.gender = gender;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String  birthday) {
		if(birthday!=null)
		this.birthday = birthday;
	}
	
	public boolean isAllBlank()
	{
		return CommonUtils.isBlank(userName) && CommonUtils.isBlank(email) && CommonUtils.isBlank(birthday) && gender==null;
	}

}
