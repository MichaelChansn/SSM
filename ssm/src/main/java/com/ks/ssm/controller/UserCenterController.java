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

import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.User;
import com.ks.ssm.interceptors.LoginCheck;
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
	
	/**用户自己的主页*/
	@RequestMapping(value = "/userManager")
	@LoginCheck(check=true,autoLogin=true)
	public String userManager(HttpServletRequest request,HttpSession session, Model model) {
		
		User user=userService.getUserById(SSMUtils.getUserId(session));
		List<Article> articles=articleService.selectByUserID(user.getId());
		List<Comment> comments=commentService.selectByUserID(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("articles", articles);
		model.addAttribute("comments", comments);
		return "userManager";
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
