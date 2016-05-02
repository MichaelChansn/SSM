package com.ks.ssm.interceptors;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.constant.RetInfos;
import com.ks.ssm.domain.User;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.utils.CommonUtils;
import com.ks.ssm.utils.SSMUtils;

public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	private static Log log = LogFactory.getLog(TokenInterceptor.class);
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			TokenCheck annotation = method.getAnnotation(TokenCheck.class);
			HttpSession session = request.getSession();
			if (annotation != null) {

				boolean tokenCheck = annotation.check();
				if (tokenCheck) {
					if (isRepeatSubmit(request)) {
						String rootPath = session.getServletContext().getContextPath();
						response.sendRedirect(rootPath + "/user/tokenError");
						return false;
					}
					//session.removeAttribute(CommonConstants.SSM_TOKEN);
				}
				boolean generateToken = annotation.generateToken();
				if (generateToken) {
					session.setAttribute(CommonConstants.SSM_TOKEN, UUID.randomUUID().toString());
				}

			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> model=modelAndView.getModel();
		if(model.containsKey(RetInfos.SUCCESS))
		{
			request.getSession().removeAttribute( CommonConstants.SSM_TOKEN );
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession().getAttribute(CommonConstants.SSM_TOKEN);
		System.err.println("server:"+serverToken);
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter(CommonConstants.SSM_TOKEN);
		System.err.println("client:"+clinetToken);
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}
