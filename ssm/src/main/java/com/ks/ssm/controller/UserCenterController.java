package com.ks.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.User;
import com.ks.ssm.interceptors.LoginCheck;
import com.ks.ssm.interceptors.TokenCheck;
import com.ks.ssm.service.IArticleService;
import com.ks.ssm.service.ICommentService;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.utils.SSMUtils;

@Controller
@RequestMapping("/user")
public class UserCenterController {
	
	private static Logger log4j = Logger.getLogger(UserCenterController.class);
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IArticleService articleService;

	@Resource
	private ICommentService commentService;
	
	@RequestMapping(value = "/userManager",method=RequestMethod.GET)
	@LoginCheck(check=true,autoLogin=true)
	@TokenCheck(generateToken=true)
	public String userManagerGet(HttpServletRequest request,HttpSession session, Model model) {
		
		User user=userService.getUserById(SSMUtils.getUserId(session));
		model.addAttribute("user", user);
		return "userManager";
	}

	@RequestMapping(value = "/userManager",method=RequestMethod.POST)
	@LoginCheck(check=true,autoLogin=true)
	@TokenCheck(generateToken=true)
	public String userManagerPost(HttpServletRequest request,HttpSession session, Model model) {
		
		User user=userService.getUserById(SSMUtils.getUserId(session));
		model.addAttribute("user", user);
		return "userManager";
	}
	
	@RequestMapping(value = "/userPublishArticle")
	@LoginCheck(check=true,autoLogin=true)
	public String userPublishArticle(HttpServletRequest request,HttpSession session, Model model) {
		
		List<Article> articles=articleService.selectByUserID(SSMUtils.getUserId(session));
		model.addAttribute("articles", articles);
		return "userPublishArticle";
	}
	@RequestMapping(value = "/userPublishComment")
	@LoginCheck(check=true,autoLogin=true)
	public String userPublishComment(HttpServletRequest request,HttpSession session, Model model) {
		
		List<Comment> comments=commentService.selectByUserID(SSMUtils.getUserId(session));
		model.addAttribute("comments", comments);
		return "userPublishComment";
	}
	
	
	
	/**查看别人的主页 restful风格的url*/
	@RequestMapping(value = "/{userId}")
	@LoginCheck(saveInfo=true,autoLogin=true)
	public String user(@PathVariable Long userId,HttpServletRequest request,HttpSession session, Model model) {
		
		/**进入了自己的主页*/
		if(userId==SSMUtils.getUserId(session))
		{
			
		}
		else/**别人的主页*/
		{
			
		}
		return "userManager";
	}
	
}
