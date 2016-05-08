package com.ks.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.PageQuery;
import com.ks.ssm.domain.UserAndArticle;
import com.ks.ssm.interceptors.LoginCheck;
import com.ks.ssm.service.IArticleService;
import com.ks.ssm.service.IUserAndArticleService;
import com.ks.ssm.utils.SSMUtils;

/**
 * @author KS
 *
 */
@Controller
public class IndexController {
	
	private static Logger logger = Logger.getLogger(IndexController.class);
	
	@Resource
	private IArticleService articleService;
	
	@Resource
	private IUserAndArticleService userAndArticleService;

	@RequestMapping({ "/", "/index" })
	@LoginCheck(saveInfo=true,autoLogin=true)
	public String toIndex(HttpServletRequest request,HttpServletResponse response, HttpSession session, Model model) {
		String retWeb = "index";
		logger.info(System.getProperty("catalina.base"));
//		System.err.println(System.getProperty("ssm.root"));
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
		PageQuery pageQuery=new PageQuery(CommonConstants.PAGE_SIZE, 1);
		List<Article> articleList=articleService.selectByPageWithPublish(pageQuery);
		List<UserAndArticle> userAndarticleList=userAndArticleService.getUserAndArticleByArticle(articleList);
		model.addAttribute("userAndarticleList", userAndarticleList);
		return retWeb;
	}
	
	
	/**无限滚动加载的controller，使用velocity模板技术返回html内容*/
	@RequestMapping(value="/getPages")
	@LoginCheck(saveInfo=true,autoLogin=true)
	public String getPages(HttpServletRequest request,HttpServletResponse response, HttpSession session, Model model) {
		String retWeb = "scrollPageTemplate";
		try{
		int pageNum=Integer.parseInt(request.getParameter("page"));
		System.err.println(pageNum);
		PageQuery pageQuery=new PageQuery(CommonConstants.PAGE_SIZE, pageNum);
		List<Article> articleList=articleService.selectByPageWithPublish(pageQuery);
		List<UserAndArticle> userAndarticleList=userAndArticleService.getUserAndArticleByArticle(articleList);
		model.addAttribute("userAndarticleList", userAndarticleList);
		}
		catch(Exception e)
		{
			logger.error("getpageError", e);
		}
		return retWeb;
	}
	
	

}
