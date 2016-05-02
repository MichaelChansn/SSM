package com.ks.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.interceptors.LoginCheck;
import com.ks.ssm.test.TestMyBatis;
import com.ks.ssm.utils.SSMUtils;

/**
 * @author KS
 *
 */
@Controller
public class IndexController {
	
	private static Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping({ "/", "/index" })
	@LoginCheck(saveInfo=true,autoLogin=true)
	public String toIndex(HttpServletRequest request,HttpServletResponse response, HttpSession session, Model model) {
		String retWeb = "index";
		logger.info("this is a test");
		//System.err.println(System.getProperty("ssm.root"));
		//System.err.println(session.getServletContext().getContextPath());
		if (SSMUtils.isLogin(session)) {
			switch (SSMUtils.getAuth(session)) {
			case CommonConstants.USER_AUTH_ADMIN:
				retWeb = CommonConstants.WEB_REDIRECT_ABS + "admin";
				break;
			case CommonConstants.USER_AUTH_USER:
				/**一下工作是为了把登录成功的用户信息传到前台，已经写进拦截器了，就需要手工为每个controller添加了*/
				/*String userName = (String) session.getAttribute(CommonConstants.SESSION_USER_NAME);
				if (userName.length() > 5) {
					userName = userName.substring(0, 5) + "...";
				}
				model.addAttribute(CommonConstants.SESSION_USER_NAME, userName);*/
				break;
			default:
				SSMUtils.logOut(session, request,response);
				break;
			}

		}
		return retWeb;
	}

}
