package com.ks.ssm.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.constant.RetInfos;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.ArticleAndCommentNum;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.CommentAndArticle;
import com.ks.ssm.domain.PageQuery;
import com.ks.ssm.domain.User;
import com.ks.ssm.domain.UserAndArticle;
import com.ks.ssm.domain.UserAndComment;
import com.ks.ssm.domain.UserIdAndPageQuery;
import com.ks.ssm.form.domain.ChangePassword;
import com.ks.ssm.form.domain.UserChangeInfos;
import com.ks.ssm.interceptors.LoginCheck;
import com.ks.ssm.interceptors.TokenCheck;
import com.ks.ssm.service.IArticleAndCommentNumService;
import com.ks.ssm.service.IArticleService;
import com.ks.ssm.service.ICommentAndArticleService;
import com.ks.ssm.service.ICommentService;
import com.ks.ssm.service.IUserAndCommentService;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.utils.CommonUtils;
import com.ks.ssm.utils.MD5Encoding;
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
	
	@Resource
	private IArticleAndCommentNumService articleAndCommentNumService;
	
	@Resource
	private ICommentAndArticleService commentAndArticleService;
	
	@Resource
	private IUserAndCommentService userAndCommentService;


	@RequestMapping(value = "/userManagerHeaderImg", method = RequestMethod.POST)
	@LoginCheck(check = true, autoLogin = true)
	public String userManagerPost(HttpServletRequest request, HttpSession session, Model model) {

		String retWeb = "userManager";
		do {
			model.addAttribute(CommonConstants.MY_SELF, true);
			User user = userService.getUserById(SSMUtils.getUserId(session));
			File file = null;
			String fileName = null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile imgFile = multipartRequest.getFile(CommonConstants.headImg);
			if (!CommonUtils.isBlank(imgFile.getOriginalFilename())) {
				file = SSMUtils.getFile(imgFile, SSMUtils.getUserId(session) + "", CommonConstants.headImg);
				if (file != null) {
					fileName = file.getName();
				} else {
					model.addAttribute(RetInfos.ERROR, RetInfos.UPLOAD_PIC_ERROR_MSG);
					model.addAttribute("headerImgError", RetInfos.UPLOAD_PIC_ERROR_MSG);
					model.addAttribute("user", user);
					retWeb = "userManager";
					break;
				}

				if (fileName != null) {
					user.setTitlepic(fileName);
					user.setModifytime(new Date());
					userService.updateByPrimaryKeySelective(user);
				}
				model.addAttribute("user", user);
				retWeb = CommonConstants.WEB_REDIRECT_REL + user.getId();
			} else {
				model.addAttribute(RetInfos.ERROR, RetInfos.UPLOAD_PIC_ERROR_MSG);
				model.addAttribute("headerImgError", RetInfos.UPLOAD_PIC_ERROR_MSG);
				model.addAttribute("user", user);
				retWeb = "userManager";
				break;
			}
		} while (false);
		return retWeb;
	}

	@RequestMapping(value = "/userManagerInfo", method = RequestMethod.POST)
	@LoginCheck(check = true, autoLogin = true)
	public String userManagerInfoPost(HttpServletRequest request, HttpSession session, Model model,
			UserChangeInfos userChangeInfos, BindingResult result) {

		model.addAttribute(CommonConstants.MY_SELF, true);
		System.err.println(userChangeInfos.getUserName() + " " + userChangeInfos.getEmail() + " "
				+ userChangeInfos.getBirthday() + " " + userChangeInfos.isGender());
		String retWeb = "userManager";
		do {
			User user = userService.getUserById(SSMUtils.getUserId(session));
			try {
				if (userChangeInfos.isAllBlank()) {
					model.addAttribute("user", user);
					model.addAttribute(RetInfos.ERROR, "您没有填写任何信息");
					model.addAttribute("userChangeInfoError", "您没有填写任何信息");
					break;
				} else {
					String userName = userChangeInfos.getUserName();
					String email = userChangeInfos.getEmail();
					if (!CommonUtils.isBlank(userName)) {
						User userTemp = userService.selectByUserName(userName);
						if (userTemp != null) {

							model.addAttribute("user", user);
							model.addAttribute("userChangeInfos", userChangeInfos);
							model.addAttribute(RetInfos.ERROR, "用户名已存在");
							model.addAttribute("userChangeInfoError", "用户名已存在");
							break;
						}
						user.setUsername(userName);
					}
					if (!CommonUtils.isBlank(email)) {
						User userTemp = userService.selectByEmail(email);
						if (userTemp != null) {
							model.addAttribute("user", user);
							model.addAttribute("userChangeInfos", userChangeInfos);
							model.addAttribute(RetInfos.ERROR, "email已经注册");
							model.addAttribute("userChangeInfoError", "email已经注册");
							break;
						}
						user.setEmail(email);
					}
					if (!CommonUtils.isBlank(userChangeInfos.getBirthday())) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(userChangeInfos.getBirthday());
						user.setBirthday(date);
					}
					if (userChangeInfos.isGender() != null) {
						user.setGender(userChangeInfos.isGender());
					}

					user.setModifytime(new Date());
					userService.updateByPrimaryKeySelective(user);
					SSMUtils.storeSession(session, user);
					model.addAttribute("user", user);
					retWeb = CommonConstants.WEB_REDIRECT_REL + user.getId();

				}
			} catch (Exception e) {
				log4j.error("userChangeInfo date",e);

			}
		} while (false);

		return retWeb;
	}
	
	
	@RequestMapping(value = "/userManagerPassword", method = RequestMethod.POST)
	@LoginCheck(check = true, autoLogin = true)
	public String userManagerPasswordPost(HttpServletRequest request, HttpSession session, Model model,@ModelAttribute("changePassword") @Valid ChangePassword changePassword, BindingResult result) {
		String retWeb="userManager";
		do{
			model.addAttribute(CommonConstants.MY_SELF, true);
			User user = userService.getUserById(SSMUtils.getUserId(session));
			if(!result.hasErrors())
			{
				if(!changePassword.isSame())
				{
					model.addAttribute(RetInfos.ERROR, "两次密码不一致");
					model.addAttribute("changePasswordError", true);
					model.addAttribute("user", user);
					break;
				}
				user.setPassword(MD5Encoding.MD5Encode(changePassword.getPassword() + CommonConstants.SALT));
				user.setModifytime(new Date());
				userService.updateByPrimaryKeySelective(user);
				model.addAttribute("user", user);
				retWeb = CommonConstants.WEB_REDIRECT_REL + user.getId();
				break;
				
			}
			else
			{
				model.addAttribute("user", user);
				break;
			}
			
		}
		while(false);
		return retWeb;
		
	}
	
	

	@RequestMapping(value = "/{userId}/userPublishComment")
	@LoginCheck(saveInfo = true, autoLogin = true)
	public String userPublishArticle(@PathVariable Long userId,HttpServletRequest request, HttpSession session, Model model) {

		String retWeb="error";
		do{
			try{
				String pageString=request.getParameter("page");
				
				if (SSMUtils.isSelf(session, userId))
				{
					/**自己，显示所有文章，包括匿名和未通过审核的*/
					model.addAttribute(CommonConstants.MY_SELF, true);
				}
				else
				{   /**查看别人的只显示通过审核的，和非匿名的*/
					model.addAttribute(CommonConstants.OTHER, true);
				}
				User user = userService.getUserById(userId);
				model.addAttribute("user", user);
				if(CommonUtils.isBlank(pageString))//没有page，表示第一页
				{
					List<Comment> comments=commentService.selectByPageWithUserId(new UserIdAndPageQuery(userId, CommonConstants.PAGE_SIZE, 1));
					model.addAttribute("comments", comments);
					retWeb="userPublishComment";
					break;
					
				}
				else
				{
					int page=Integer.parseInt(pageString);
					List<Comment> comments=commentService.selectByPageWithUserId(new UserIdAndPageQuery(userId, CommonConstants.PAGE_SIZE, page));
					model.addAttribute("comments", comments);
					retWeb="userCommentScrollPageTemplate";
					break;
				}
				}
				catch(Exception e)
				{
					log4j.error("getpageError", e);
				}
		}
		while(false);
			
		return retWeb;
	}

	@RequestMapping(value = "/{userId}/userPublishArticle")
	@LoginCheck(saveInfo=true,autoLogin = true)
	public String userPublishComment(@PathVariable Long userId,HttpServletRequest request, HttpSession session, Model model) {

		String retWeb="error";
		do{
			try{
				String pageString=request.getParameter("page");
				
					if (SSMUtils.isSelf(session, userId))
					{
						/**自己，显示所有文章，包括匿名和未通过审核的*/
						model.addAttribute(CommonConstants.MY_SELF, true);
					}
					else
					{   /**查看别人的只显示通过审核的，和非匿名的*/
						model.addAttribute(CommonConstants.OTHER, true);
					}
					User user = userService.getUserById(userId);
					model.addAttribute("user", user);
					
					if(CommonUtils.isBlank(pageString))//没有page，表示第一页
					{
						List<ArticleAndCommentNum> articleAndCommentNums=articleAndCommentNumService.getArticleWithCommentNumByPage(new UserIdAndPageQuery(userId, CommonConstants.PAGE_SIZE, 1));
						model.addAttribute("articleAndCommentNums", articleAndCommentNums);
						retWeb="userPublishArticle";
						break;
						
					}
					else
					{
						int page=Integer.parseInt(pageString);
						List<ArticleAndCommentNum> articleAndCommentNums=articleAndCommentNumService.getArticleWithCommentNumByPage(new UserIdAndPageQuery(userId, CommonConstants.PAGE_SIZE, page));
						model.addAttribute("articleAndCommentNums", articleAndCommentNums);
						retWeb="userArticleScrollPageTemplate";
						break;
					}
					
				}
				catch(Exception e)
				{
					log4j.error("getpageError", e);
				}
		}
		while(false);
			
		return retWeb;
	}

	
	
	/** 查看别人的主页 restful风格的url */
	@RequestMapping(value = "/{userId}",method = RequestMethod.GET)
	@LoginCheck(saveInfo = true, autoLogin = true)
	@TokenCheck(generateToken = true)
	public String user(@PathVariable Long userId, HttpServletRequest request, HttpSession session, Model model) {

		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		/** 进入了自己的主页 */
		if (SSMUtils.isSelf(session, userId)) {
			model.addAttribute(CommonConstants.MY_SELF, true);
		} else/** 别人的主页 */
		{
			model.addAttribute(CommonConstants.OTHER, true);
		}
		return "userManager";
	}

	/** 查看别人的主页 restful风格的url */
	@RequestMapping(value = "/{userId}/article/{articleId}",method = RequestMethod.GET)
	@LoginCheck(saveInfo = true, autoLogin = true)
	public String article(@PathVariable Long userId,@PathVariable Long articleId, HttpServletRequest request, HttpSession session, Model model) {

		/** 进入了自己的主页 */
		if (SSMUtils.isSelf(session, userId)) {
			model.addAttribute(CommonConstants.MY_SELF, true);
		} else/** 别人的主页 */
		{
			model.addAttribute(CommonConstants.OTHER, true);
		}
		model.addAttribute("userId", userId);
		Article article=articleService.selectByPrimaryKey(articleId);
		User user=userService.selectByPrimaryKey(article.getUserid());
		List<Comment> comments=commentService.selectByArticleID(articleId);
		List<UserAndComment> userAndComments=userAndCommentService.getUserAndCommentByComment(comments);
		model.addAttribute("article", article);
		model.addAttribute("user", user);
		model.addAttribute("userAndComments", userAndComments);
		return "articleInfo";
	}
}
