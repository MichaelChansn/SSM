package com.ks.ssm.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.constant.RetInfos;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.ArticleIdAndStatus;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.User;
import com.ks.ssm.domain.UserAndComment;
import com.ks.ssm.form.domain.ArticleComment;
import com.ks.ssm.form.domain.ArticlePublish;
import com.ks.ssm.form.domain.UserLogin;
import com.ks.ssm.form.domain.UserRegister;
import com.ks.ssm.interceptors.LoginCheck;
import com.ks.ssm.interceptors.TokenCheck;
import com.ks.ssm.service.IArticleService;
import com.ks.ssm.service.ICommentService;
import com.ks.ssm.service.IUserAndCommentService;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.utils.CommonUtils;
import com.ks.ssm.utils.MD5Encoding;
import com.ks.ssm.utils.SSMUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger log4j = Logger.getLogger(UserController.class);
	@Resource
	private IUserService userService;

	@Resource
	private IArticleService articleService;

	@Resource
	private ICommentService commentService;

	@Resource
	private IUserAndCommentService userAndCommentService;
	/* 域模型的校验，框架提供，自动对网页提交的参数检验 validator和@Valid标注只要使用一个就可以 */
	/*
	 * private Validator validator; public Validator getValidator() { return
	 * validator; }
	 * 
	 * public void setValidator(Validator validator) { this.validator =
	 * validator; }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@LoginCheck(autoLogin = true)
	public String userLogin(HttpServletRequest request, HttpSession session, Model model) {
		if (SSMUtils.isLogin(session))
			return CommonConstants.WEB_REDIRECT_ABS + "index";
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model,
			@ModelAttribute("user") @Valid UserLogin user, BindingResult result) {
		String retWeb = "login";
		do {
			model.addAttribute("userName", user.getUserName());
			if (!result.hasErrors()) {
				// validator.validate(user, result);
				User userLogin = userService.selectByUserName(user.getUserName());
				if (userLogin == null || !userLogin.getPassword()
						.equals(MD5Encoding.MD5Encode(user.getPassword() + CommonConstants.SALT))) {
					model.addAttribute(RetInfos.ERROR, RetInfos.LOGIN_FAIL_MSG);
					break;
				} else {
					SSMUtils.storeSession(session, userLogin);
					model.addAttribute("userName", null);// 去掉url显示的参数
					// 记住密码
					SSMUtils.rememberMe(userService, user.isRememberMe(), response, request, userLogin);
					retWeb = CommonConstants.WEB_REDIRECT_ABS + "index";
					break;
				}
			} else {
				// model.addAttribute(RetInfos.ERROR, result.getAllErrors());
				break;
			}

		} while (false);
		return retWeb;
	}

	@RequestMapping(value = "/addArticle", method = RequestMethod.GET)
	@LoginCheck(check = true, autoLogin = true)
	@TokenCheck(generateToken = true)
	public String userAddArticle(HttpServletRequest request, Model model) {
		return "addArticle";
	}

	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	@LoginCheck(check = true, autoLogin = true)
	@TokenCheck(check = true)
	public String userAddArticle(HttpServletRequest request, HttpSession session, Model model,
			@ModelAttribute("articlePublish") @Valid ArticlePublish articlePublish, BindingResult result) {
		String retWeb = "addArticle";
		do {
			try {

				if (!result.hasErrors()) {
					File file = null;
					String fileName = "";
					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
					MultipartFile imgFile = multipartRequest.getFile("articleImg");
					if (!CommonUtils.isBlank(imgFile.getOriginalFilename())) {
						file = SSMUtils.getFile(imgFile, SSMUtils.getUserId(session) + "", CommonConstants.articleImg);
						if (file != null) {
							fileName = file.getName();
						} else {
							model.addAttribute(RetInfos.ERROR, RetInfos.UPLOAD_PIC_ERROR_MSG);
							model.addAttribute("articleContent", articlePublish.getArticleContent().replaceAll("<br>","\r\n"));
							model.addAttribute("articleAnonymous", articlePublish.isArticleAnonymous());
							break;
						}
					}
					Article article = new Article();
					article.setUserid(SSMUtils.getUserId(session));
					article.setAnonymous(articlePublish.isArticleAnonymous());
					/* 保存的时候不能转译，否则无法搜索 */
					// String
					// escapeHtml=StringEscapeUtils.escapeHtml(articlePublish.getArticleContent());
					// String
					// escapeJavaScript=StringEscapeUtils.escapeJavaScript(escapeHtml);
					article.setContent(articlePublish.getArticleContent());
					article.setPic(fileName);
					article.setWritetime(new Date());
					article.setModifytime(new Date());
					article.setPublishtime(new Date());
					articleService.insertSelective(article);
					model.addAttribute(RetInfos.SUCCESS, RetInfos.ARTICLE_PUBLISH_SUCCESS_MSG);
					break;

				} else {
					// model.addAttribute(RetInfos.ERROR,
					// result.getAllErrors());
					model.addAttribute("articleContent", articlePublish.getArticleContent());
					model.addAttribute("articleAnonymous", articlePublish.isArticleAnonymous());

					break;
				}
			} catch (Exception e) {
				log4j.error(SSMUtils.getUserId(session) + ":pubic article error", e);
			}

		} while (false);

		return retWeb;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@TokenCheck(generateToken = true)
	public String userRegister(HttpServletRequest request, Model model) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@TokenCheck(check = true)
	public String userRegister(HttpServletRequest request, HttpSession session, Model model,
			@ModelAttribute("user") @Valid UserRegister user, BindingResult result) {
		String retWeb = "register";
		do {
			model.addAttribute("userNickName", user.getUserNickName());
			model.addAttribute("email", user.getEmail());
			if (!result.hasErrors()) {
				// validator.validate(user, result);
				if (!user.isSamePassword()) {
					model.addAttribute(RetInfos.ERROR, RetInfos.PASSWORD_NOT_SAME);
					break;
				}
				User user1 = userService.selectByUserName(user.getUserNickName());
				User user2 = userService.selectByEmail(user.getEmail());
				if (user1 != null) {
					model.addAttribute(RetInfos.ERROR, RetInfos.USER_EXIST);
					break;
				}
				if (user2 != null) {
					model.addAttribute(RetInfos.ERROR, RetInfos.EMAIL_EXIST);
					break;
				}

				User userStroage = new User();
				userStroage.setUsername(user.getUserNickName());
				// 密码以MD5的形式存放，另外添加salt
				userStroage.setPassword(MD5Encoding.MD5Encode(user.getPassword() + CommonConstants.SALT));
				userStroage.setEmail(user.getEmail());
				userStroage.setEnrolltime(new Date());
				userStroage.setLoginip(SSMUtils.getIpAddr(request));
				userStroage.setLogintime(new Date());
				userStroage.setModifytime(new Date());
				userService.insertSelective(userStroage);// 返回影响的行数，会自动的把id返回到user的id参数中
				// System.err.println(userStroage.getId());
				model.addAttribute(RetInfos.SUCCESS, RetInfos.REGISTER_SUCCESS_INFO);
				SSMUtils.storeSession(session, userStroage);

				break;
			} else {
				// model.addAttribute(RetInfos.ERROR, result.getAllErrors());
				break;
			}

		} while (false);
		return retWeb;
	}

	@RequestMapping(value = "/upArticle", method = RequestMethod.POST)
	@LoginCheck(autoLogin = true)
	public String upArticle(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		do {
			try {
				long articleId = Long.parseLong(request.getParameter("upArticle"));
				String userLikeInCookieInBase64 = SSMUtils.getCookieByName(CommonConstants.USER_LIKE_IN_COOKIE,
						request);
				if (userLikeInCookieInBase64 == null) {
					String articleIdInBase64 = Base64.encodeBase64URLSafeString((articleId + "").getBytes());
					Cookie cookieUserLike = new Cookie(CommonConstants.USER_LIKE_IN_COOKIE, articleIdInBase64);
					cookieUserLike.setPath("/");
					cookieUserLike.setMaxAge(SSMUtils.CookieExpiresTime);
					response.addCookie(cookieUserLike);
				} else {
					String userLikeInCookie = new String(Base64.decodeBase64(userLikeInCookieInBase64));
					String[] articleIdsInString = userLikeInCookie.split(CommonConstants.USER_LIKE_SEPARATOR);
					List<String> articleIdList = Arrays.asList(articleIdsInString);
					if (articleIdList.contains(articleId + ""))// 该文章已经点赞过了
					{
						model.addAttribute(CommonConstants.USER_ALREADY_LIKE, true);
						break;
					}
					else
					{
						String articleIdInBase64Add=Base64.encodeBase64URLSafeString((userLikeInCookie+CommonConstants.USER_LIKE_SEPARATOR+articleId).getBytes());
						Cookie cookieUserLike = new Cookie(CommonConstants.USER_LIKE_IN_COOKIE, articleIdInBase64Add);
						cookieUserLike.setPath("/");
						cookieUserLike.setMaxAge(SSMUtils.CookieExpiresTime);
						response.addCookie(cookieUserLike);
					}
				}

				Article article = articleService.selectByPrimaryKeyWithoutBLOBs(articleId);
				int upNum = article.getUp() + 1;
				article.setUp(upNum);
				articleService.updateByPrimaryKey(article);
				model.addAttribute(RetInfos.USER_LIKE_AJAX_RESULT, upNum);
			} catch (Exception e) {
				log4j.error("up article error", e);
			}
		} while (false);
		return "ajaxResultTemplate";
	}

	@RequestMapping(value = "/articleComment", method = RequestMethod.POST)
	@LoginCheck(autoLogin = true)
	public String articleComment(HttpServletRequest request, HttpSession session, Model model) {
		do {
			if (!SSMUtils.isLogin(session)) {
				model.addAttribute(RetInfos.NOT_LOGIN, true);
				// break;
			}
			try {
				long articleId = Long.parseLong(request.getParameter("articleComment"));
				Article article=articleService.selectByPrimaryKeyWithoutBLOBs(articleId);
				List<Comment> commentList = commentService.selectByArticleIDWithStatus(new ArticleIdAndStatus(articleId,CommonConstants.COMMENT_STATUS_PASS));
				List<UserAndComment> userAndcommentList = userAndCommentService.getUserAndCommentByComment(commentList);
				model.addAttribute("userAndcommentList", userAndcommentList);
				model.addAttribute("articleId", articleId);
				model.addAttribute("article", article);
			} catch (Exception e) {
				log4j.error("comment article error", e);
			}
		} while (false);

		return "commentTemplate";
	}

	@RequestMapping(value = "/reportComment", method = RequestMethod.POST)
	@LoginCheck(autoLogin = true)
	public String reportComment(HttpServletRequest request, HttpSession session, Model model) {
			try {
				long commentId = Long.parseLong(request.getParameter("reportComment"));
				Comment comment=commentService.selectByPrimaryKeyWithOutBLOBs(commentId);
				if(comment!=null){
				comment.setStatus(CommonConstants.COMMENT_STATUS_NOPASS);
				comment.setModifytime(new Date());
				commentService.updateByPrimaryKeySelective(comment);
				model.addAttribute(CommonConstants.REPORT_COMMENT, "举报成功");
				}
			} catch (Exception e) {
				log4j.error("comment report error", e);
			}

		return "ajaxResultTemplate";
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@LoginCheck(check = true, autoLogin = true)
	public String addComment(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model,
			ArticleComment articleComment) {
		String retWeb = "ajaxResultTemplate";

		model.addAttribute(CommonConstants.COMMENT_AJAX, "评论AJAX返回");
		do {
			if (CommonUtils.isBlank(articleComment.getCommentContent())) {
				model.addAttribute(RetInfos.ERROR, "评论内容不能为空啊！");
				break;
			}
			Comment comment = new Comment();
			comment.setAnonymous(articleComment.isCommentAnonymous());
			comment.setArticleid(articleComment.getArticleId());
			comment.setCommenttime(new Date());
			comment.setFromuserid(SSMUtils.getUserId(session));
			comment.setContent(articleComment.getCommentContent());
			comment.setModifytime(new Date());
			commentService.insertSelective(comment);

			UserAndComment userAndComment = new UserAndComment();
			userAndComment.setComment(comment);
			User user = userService.getUserById(comment.getFromuserid());
			userAndComment.setUser(user);
			model.addAttribute("userAndComment", userAndComment);
			model.addAttribute(RetInfos.SUCCESS, "评论成功！");
		} while (false);
		return retWeb;
	}

}
