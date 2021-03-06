package com.ks.ssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.ks.ssm.constant.RetInfos;
import com.ks.ssm.domain.User;
import com.ks.ssm.form.domain.ForgetPassword;
import com.ks.ssm.form.domain.SetNewPassword;
import com.ks.ssm.interceptors.LoginCheck;
import com.ks.ssm.interceptors.TokenCheck;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.service.ISendEmailService;
import com.ks.ssm.utils.CommonUtils;
import com.ks.ssm.utils.SSMUtils;

@Controller
@RequestMapping("/user")
public class ForgetPasswordController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private ISendEmailService sendEmailService;

	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	@LoginCheck(saveInfo=true,autoLogin = true)
	@TokenCheck(generateToken=true)
	public String forgetPasswordGet(HttpServletRequest request, HttpSession session, Model model) {
		String token=request.getParameter("token");
		if(!CommonUtils.isBlank(token))
		{
			if(SSMUtils.verifyToken(token, userService,null)){
				//把token带下去，用于提交验证
			model.addAttribute(RetInfos.FORGET_PASSWORD_TOKEN,token);
			}
		}
		

		return "forgetPassword";
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	@LoginCheck(saveInfo=true,autoLogin = true)
	@TokenCheck(check=true)
	public String forgetPasswordPost(HttpServletRequest request, HttpSession session, Model model,
			@ModelAttribute("forgetPassword") @Valid ForgetPassword forgetPassword, BindingResult result) {
		
		String retWeb = "forgetPassword";
		do {
			String email=forgetPassword.getEmail();
			model.addAttribute("email", email);
			if (!result.hasErrors()) {
				
				String captchaInSession = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY); 
				if(forgetPassword.getCaptcha()==null || !forgetPassword.getCaptcha().equals(captchaInSession))
				{
					model.addAttribute(RetInfos.ERROR, RetInfos.FORGET_PASSWORD_ERROR_MSG2);
					break;
				}
				
				User user=userService.selectByEmail(email);
				if(user==null){
					model.addAttribute(RetInfos.ERROR, RetInfos.FORGET_PASSWORD_ERROR_MSG);
				}
				else
				{
					SSMUtils.generateForgetPasswordToken(request,session, userService, sendEmailService, user);
					model.addAttribute(RetInfos.SUCCESS, RetInfos.FORGET_PASSWORD_SUCCESS_MSG);
				}
					break;
			} else {
				//model.addAttribute(RetInfos.ERROR, result.getAllErrors());
				break;
			}

		} while (false);
		return retWeb;
		
	}

	@RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
	@LoginCheck(autoLogin = true)
	@TokenCheck(check=true)
	public String setNewPasswordPost(HttpServletRequest request, HttpSession session, Model model,
			@ModelAttribute("setNewPassword") @Valid SetNewPassword setNewPassword, BindingResult result) {
		
		String retWeb = "forgetPassword";
		model.addAttribute(RetInfos.FORGET_PASSWORD_TOKEN,setNewPassword.getToken());
		do {
			if (!result.hasErrors()) 
			{
				String captchaInSession = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY); 
				if(setNewPassword.getCaptcha()==null || !setNewPassword.getCaptcha().equals(captchaInSession))
				{
					model.addAttribute(RetInfos.ERROR, RetInfos.FORGET_PASSWORD_ERROR_MSG2);
					break;
				}
				if(!setNewPassword.isSamePassword())
				{
					model.addAttribute(RetInfos.ERROR,RetInfos.PASSWORD_NOT_SAME);	
					break;
				}
				if(SSMUtils.verifyTokenAndSaveNewPassword(setNewPassword.getToken(), userService,setNewPassword.getNewPassword()))
				{
					
					model.addAttribute(RetInfos.SUCCESS,RetInfos.SET_NEW_PASSWORD_SUCCESS_MSG);
					break;
				}
			}
			else
			{
				//model.addAttribute(RetInfos.ERROR, result.getAllErrors());
				break;
			}
			
		} while (false);
		return retWeb;
		
	}
}
