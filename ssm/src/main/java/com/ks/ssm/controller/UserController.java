package com.ks.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.constant.RetInfos;
import com.ks.ssm.domain.User;
import com.ks.ssm.form.domain.UserLogin;
import com.ks.ssm.form.domain.UserRegister;
import com.ks.ssm.interceptors.LoginCheck;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.utils.MD5Encoding;
import com.ks.ssm.utils.SSMUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	/* 域模型的校验，框架提供，自动对网页提交的参数检验 validator和@Valid标注只要使用一个就可以 */
	/*
	 * private Validator validator; public Validator getValidator() { return
	 * validator; }
	 * 
	 * public void setValidator(Validator validator) { this.validator =
	 * validator; }
	 */
	

	private final String userID = "1";
	private final String headImg = "headerImg";
	private final String articleImg = "articleImg";

	@RequestMapping(value = "/addArticle", method = RequestMethod.GET)
	@LoginCheck(check=true)
	public String userAddArticle(HttpServletRequest request,HttpSession session, Model model) {
		if(!SSMUtils.isLogin(session))
		return "login";
		else
			return "addArticle";
	}
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	@LoginCheck(check=true)
	public String userAddArticle(HttpServletRequest request, Model model) {
		String retWeb = "error";
		File file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("articleImg");
		if (!(imgFile.getOriginalFilename() == null || "".equals(imgFile.getOriginalFilename()))) {
			file = SSMUtils.getFile(imgFile, userID, articleImg);
		}
		String textContent = request.getParameter("articleContent");
		if ((null != textContent && !"".equals(textContent.trim())) || file != null) {
			if (file != null)
				model.addAttribute("img", request.getSession().getServletContext().getContextPath() + "/user" + "/"
						+ "img/" + userID + "/" + articleImg + "/" + file.getName());
			model.addAttribute("text", textContent);
			retWeb = "showUser";
		}
		return retWeb;
	}

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegister(HttpServletRequest request, Model model) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegister(HttpServletRequest request, HttpSession session, Model model,
			@ModelAttribute("user") @Valid UserRegister user, BindingResult result) {
		String retWeb = "register";
		do {
			model.addAttribute("userNickName", user.getUserNickName());
			model.addAttribute("email", user.getEmail());
			if (!result.hasErrors()) {
				// validator.validate(user, result);
				if (!user.isSamePassword()) {
					model.addAttribute(RetInfos.REGISTER_ERROR, RetInfos.PASSWORD_NOT_SAME);
					break;
				}
				User user1 = userService.selectByUserName(user.getUserNickName());
				User user2 = userService.selectByEmail(user.getEmail());
				if (user1 != null) {
					model.addAttribute(RetInfos.REGISTER_ERROR, RetInfos.USER_EXIST);
					break;
				}
				if (user2 != null) {
					model.addAttribute(RetInfos.REGISTER_ERROR, RetInfos.EMAIL_EXIST);
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
				model.addAttribute(RetInfos.REGISTER_SUCCESS, RetInfos.REGISTER_SUCCESS_INFO);
				SSMUtils.storeSession(session, userStroage);

				break;
			} else {
				model.addAttribute(RetInfos.VALIDATOR_ERROR, result.getAllErrors());
				break;
			}

		} while (false);
		return retWeb;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(HttpServletRequest request, Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, HttpSession session, Model model,
			@ModelAttribute("user") @Valid UserLogin user, BindingResult result) {
		String retWeb = "login";
		do {
			model.addAttribute("userName", user.getUserName());
			if (!result.hasErrors()) {
				// validator.validate(user, result);
				User userLogin = userService.selectByUserName(user.getUserName());
				if (userLogin == null ||!userLogin.getPassword()
						.equals(MD5Encoding.MD5Encode(user.getPassword() + CommonConstants.SALT))) {
					model.addAttribute(RetInfos.LOGIN_ERROR, RetInfos.LOGIN_FAIL_MSG);
					break;
				} else {
					SSMUtils.storeSession(session, userLogin);
					model.addAttribute("userName", null);//去掉url显示的参数
					retWeb = CommonConstants.WEB_REDIRECT_ABS + "index";
					break;
				}
			} else {
				model.addAttribute(RetInfos.VALIDATOR_ERROR, result.getAllErrors());
				break;
			}

		} while (false);
		return retWeb;
	}

}
