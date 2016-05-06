package com.ks.ssm.interceptors;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.domain.User;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.utils.CommonUtils;
import com.ks.ssm.utils.SSMUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static Log log = LogFactory.getLog(LoginInterceptor.class);
	@Resource
	private IUserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			LoginCheck annotation = method.getAnnotation(LoginCheck.class);
			HttpSession session = request.getSession();
			if (annotation != null) {

				boolean rememberMe = annotation.autoLogin();
				if (rememberMe && !SSMUtils.isLogin(session)) {
					
					String sUserId = SSMUtils.getCookieByName(CommonConstants.REMEMBER_ME_USERID, request);
					String token = SSMUtils.getCookieByName(CommonConstants.REMEMBER_ME_TOKEN, request);

					if (!CommonUtils.isBlank(sUserId) && !CommonUtils.isBlank(token)) {
						try {
							long userId = Long.parseLong(sUserId);
							User userLogin = userService.getUserById(userId);
							if(token.equals(userLogin.getToken()))
							{
								SSMUtils.storeSession(session, userLogin);
								userLogin.setLoginip(SSMUtils.getIpAddr(request));
								userLogin.setLogintime(new Date());
								userService.updateByPrimaryKeySelective(userLogin);
							}

						} catch (Exception e) {
							SSMUtils.clearRememberMe(response);
							log.error("auto login error", e);
						}
					}
				}

				boolean needCheckAuth = annotation.check();
				if (needCheckAuth) {

					if (SSMUtils.isLogin(session)) {
						session.setAttribute(CommonConstants.SESSION_ISLOGIN, true);
						String userName = (String) session.getAttribute(CommonConstants.SESSION_USER_NAME);
						/*if (userName.length() > 10) {
							userName = userName.substring(0, 10)+"..";
						}*/
						session.setAttribute(CommonConstants.SESSION_USER_NAME, userName);
					} else {
						String rootPath = session.getServletContext().getContextPath();
						response.sendRedirect(rootPath + "/user/login");
						return false;
					}

				}

				boolean needSaveInfo = annotation.saveInfo();
				if (needSaveInfo && SSMUtils.isLogin(session)) {
					session.setAttribute(CommonConstants.SESSION_ISLOGIN, true);
					String userName = (String) session.getAttribute(CommonConstants.SESSION_USER_NAME);
					/*if (userName.length() > 10) {
						userName = userName.substring(0, 10)+"..";
					}*/
					session.setAttribute(CommonConstants.SESSION_USER_NAME, userName);
				}

			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

}
