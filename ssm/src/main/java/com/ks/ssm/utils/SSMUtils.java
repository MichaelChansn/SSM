package com.ks.ssm.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.domain.User;
import com.ks.ssm.service.IUserService;
import com.ks.ssm.service.ISendEmailService;

public class SSMUtils {

	private static Logger log4j = Logger.getLogger(SSMUtils.class);

	private static String[] imgs = { "jpg", "jpeg", "bmp", "gif", "png" };
	private static List<String> imgsList = Arrays.asList(imgs);
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
	public static final int CookieExpiresTime = 10 * 365 * 24 * 60 * 60;// cookie过期时间10年，记住密码
	private static String InfoSeparator = "<#nankai#>";

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!CommonUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!CommonUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 把信息存放近session 权限 ID Email 姓名 公司 手机
	 * 
	 * @param session
	 * @param userInfo
	 */
	public static void storeSession(HttpSession session, User userInfo) {
		session.setAttribute(CommonConstants.SESSION_ISLOGIN, CommonConstants.SESSION_LOGIN);
		session.setAttribute(CommonConstants.SESSION_AUTH, CommonConstants.USER_AUTH_USER);
		session.setAttribute(CommonConstants.SESSION_USERID, userInfo.getId());
		session.setAttribute(CommonConstants.SESSION_USEREMAIL, userInfo.getEmail());
		session.setAttribute(CommonConstants.SESSION_USER_NAME, userInfo.getUsername());
		session.setAttribute(CommonConstants.SESSION_WEB_ROOT_PATH, CommonConstants.WEB_ROOT_PATH);
	}

	public static boolean isLogin(HttpSession session) {
		Object userId = session.getAttribute(CommonConstants.SESSION_USERID);
		Object auth = session.getAttribute(CommonConstants.SESSION_AUTH);
		Object isLogin = session.getAttribute(CommonConstants.SESSION_ISLOGIN);
		if (userId != null && auth != null && isLogin != null && (boolean) isLogin)
			return true;

		return false;

	}
	public static  boolean isSelf(HttpSession session,Long userId)
	{
		if(isLogin(session) && userId==getUserId(session))
			return true;
		return false;
	}
	public static int getAuth(HttpSession session) {
		Object auth = session.getAttribute(CommonConstants.SESSION_AUTH);
		return auth == null ? CommonConstants.USER_AUTH_ERROR : (int) auth;
	}
	
	public static long getUserId(HttpSession session) {
		Object userId = session.getAttribute(CommonConstants.SESSION_USERID);
		return userId == null ? CommonConstants.USER_AUTH_ERROR : (long) userId;
	}

	/**
	 * 退出逻辑
	 * 
	 * @param session
	 */
	public static void logOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// 清除运原来的session
		session.invalidate();
		Cookie cookieRememberMeUserID = new Cookie(CommonConstants.REMEMBER_ME_USERID, null);
		Cookie cookieRememberMeToken = new Cookie(CommonConstants.REMEMBER_ME_TOKEN, null);
		cookieRememberMeUserID.setPath("/");
		cookieRememberMeToken.setPath("/");
		cookieRememberMeToken.setMaxAge(0);
		cookieRememberMeUserID.setMaxAge(0);
		response.addCookie(cookieRememberMeUserID);
		response.addCookie(cookieRememberMeToken);
		// 返回新的session
		session = request.getSession(true);
	}

	public static void clearRememberMe(HttpServletResponse response)
	{
		Cookie cookieRememberMeUserID = new Cookie(CommonConstants.REMEMBER_ME_USERID, null);
		Cookie cookieRememberMeToken = new Cookie(CommonConstants.REMEMBER_ME_TOKEN, null);
		cookieRememberMeUserID.setPath("/");
		cookieRememberMeToken.setPath("/");
		cookieRememberMeToken.setMaxAge(0);
		cookieRememberMeUserID.setMaxAge(0);
		response.addCookie(cookieRememberMeUserID);
		response.addCookie(cookieRememberMeToken);
	}
	public static File getFile(MultipartFile imgFile, String userID, String articleImg) {
		String fileName = imgFile.getOriginalFilename();
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 对扩展名进行小写转换
		ext = ext.toLowerCase();
		File file = null;
		if (imgsList.contains(ext)) { // 如果扩展名属于允许上传的类型，则创建文件
			fileName = df.format(new Date()) + "." + ext;
			file = creatFolder(userID, articleImg, fileName);
			try {
				imgFile.transferTo(file); // 保存上传的文件
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public static File creatFolder(String userID, String articleImg, String fileName) {
		File file = null;

		File firstFolder = new File(CommonConstants.IMG_VIRTUAL_PATH + userID); // 一级文件夹
		if (firstFolder.exists()) { // 如果一级文件夹存在，则检测二级文件夹
			File secondFolder = new File(firstFolder, articleImg);
			if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件
				file = new File(secondFolder, fileName);
			} else { // 如果二级文件夹不存在，则创建二级文件夹
				secondFolder.mkdir();
				file = new File(secondFolder, fileName); // 创建完二级文件夹后，再合建文件
			}
		} else { // 如果一级不存在，则创建一级文件夹
			firstFolder.mkdir();
			File secondFolder = new File(firstFolder, articleImg);
			if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件
				file = new File(secondFolder, fileName);
			} else { // 如果二级文件夹不存在，则创建二级文件夹
				secondFolder.mkdir();
				file = new File(secondFolder, fileName);
			}
		}
		return file;
	}

	/** cookie中不能存放中文，必须转码，所以不存放用户名了 */
	public static void rememberMe(IUserService userService, boolean isRemember, HttpServletResponse response,
			HttpServletRequest request, User user) {

		if (isRemember) {
			String token = MD5Encoding.MD5Encode(UUID.randomUUID().toString() + user.getUsername() + user.getId());
			user.setToken(token);
			Cookie cookieRememberMeUserID = new Cookie(CommonConstants.REMEMBER_ME_USERID, user.getId() + "");
			Cookie cookieRememberMeToken = new Cookie(CommonConstants.REMEMBER_ME_TOKEN, token);
			cookieRememberMeUserID.setPath("/");
			cookieRememberMeToken.setPath("/");
			cookieRememberMeUserID.setMaxAge(CookieExpiresTime);
			cookieRememberMeToken.setMaxAge(CookieExpiresTime);
			response.addCookie(cookieRememberMeUserID);
			response.addCookie(cookieRememberMeToken);

		}
		user.setLoginip(SSMUtils.getIpAddr(request));
		user.setLogintime(new Date());
		userService.updateByPrimaryKeySelective(user);

	}

	/**
	 * Cookie 取得
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCookieByName(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static String generateForgetPasswordToken(HttpServletRequest request, HttpSession session,
			IUserService userService, ISendEmailService sendEmailService, User user) {
		String uuid = UUID.randomUUID().toString();
		String token = MD5Encoding.MD5Encode(uuid + CommonConstants.SALT + user.getUsername() + user.getId());
		String tokenAndUserId = user.getId() + InfoSeparator + token;
		String base64String = Base64.getUrlEncoder().encodeToString(tokenAndUserId.getBytes());
		System.err.println(sendEmailService == null);
		sendEmailService.setTo(user.getEmail());
		sendEmailService.setSubject("找回密码");
		sendEmailService.setTemplateName("mail.vm");
		Map<String, Comparable> model = new HashMap<String, Comparable>();
		model.put("userName", user.getUsername());
		model.put("forgetPasswordTime", new Date());
		String rootPath = "http://" + request.getLocalAddr() + ":" + request.getLocalPort()
				+ session.getServletContext().getContextPath();
		model.put("forgetPasswordUrl", rootPath + "/user/forgetPassword" + "?token=" + base64String);
		model.put("adminEmail", CommonConstants.ADMIN_EMAIL);
		try {
			sendEmailService.sendWithTemplate(model);
			System.out.println("邮件发送成功！");
			log4j.info(new Date() + ":" + user.getUsername() + ":" + "通过邮箱找回密码,IP是：" + user.getLoginip());
			user.setToken(token);
			user.setLoginip(getIpAddr(request));
			user.setModifytime(new Date());
			userService.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			log4j.error(
					"send email failed:" + new Date() + ":" + user.getUsername() + "通过邮箱找回密码,IP是：" + user.getLoginip(),
					e);
		}

		return null;
	}

	public static boolean verifyToken(String tokenFromUrl, IUserService userService,ArrayList<User> list) {
		try {
			String userAndTokenDecodeFromBase64 = new String(Base64.getUrlDecoder().decode(tokenFromUrl));
			String[] usrIdAndTokenInString = userAndTokenDecodeFromBase64.split(InfoSeparator);
			if (usrIdAndTokenInString.length == 2) {

				long userId = Long.parseLong(usrIdAndTokenInString[0]);
				String token = usrIdAndTokenInString[1];
				User user = userService.getUserById(userId);
				if (user != null && user.getToken() != null && user.getToken().equals(token)) {
					if(list!=null)
						list.add(user);
					
					return true;
				}

			}
		} catch (Exception e) {
			log4j.error("解析userid和token错误", e);
		}

		return false;
	}
	public static boolean verifyTokenAndSaveNewPassword(String tokenFromUrl, IUserService userService,String newPassword)
	{
	    ArrayList<User> list=new ArrayList<User>();
		if(verifyToken(tokenFromUrl,userService,list))
		{
			User user=list.get(0);
			user.setModifytime(new Date());
			user.setToken("");
			user.setPassword(MD5Encoding.MD5Encode(newPassword+ CommonConstants.SALT));
			userService.updateByPrimaryKeySelective(user);
			return true;
			
		}
		return false;
	}
}
