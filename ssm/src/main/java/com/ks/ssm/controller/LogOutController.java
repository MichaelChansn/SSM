package com.ks.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.utils.SSMUtils;

@Controller
public class LogOutController {

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String doGet(HttpSession session, HttpServletRequest request) {
		String web = "index";
		SSMUtils.logOut(session, request);
		return CommonConstants.WEB_REDIRECT_ABS + web;
	}

}
