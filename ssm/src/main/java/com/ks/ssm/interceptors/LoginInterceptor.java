package com.ks.ssm.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.utils.SSMUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter {

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
				boolean needCheckAuth = annotation.check();
				if (needCheckAuth) {

					if (SSMUtils.isLogin(session)) {
						session.setAttribute(CommonConstants.SESSION_ISLOGIN, true);
						String userName = (String) session.getAttribute(CommonConstants.SESSION_USER_NAME);
						if (userName.length() > 5) {
							userName = userName.substring(0, 5) + "...";
						}
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
					if (userName.length() > 5) {
						userName = userName.substring(0, 5) + "...";
					}
					session.setAttribute(CommonConstants.SESSION_USER_NAME, userName);
				}

			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

}
