package com.ks.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ks.ssm.interceptors.LoginCheck;

@Controller
@RequestMapping("/user")
public class UserCenterController {
	
	@RequestMapping(value = "/userManager")
	@LoginCheck(check=true,autoLogin=true)
	public String userManager(HttpServletRequest request, Model model) {
		return "userManager";
	}

}
