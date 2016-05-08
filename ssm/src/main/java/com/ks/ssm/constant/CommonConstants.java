package com.ks.ssm.constant;

import org.springframework.web.context.ContextLoader;

public class CommonConstants {
	
	public final static String SALT="copy right by ks";
	
	/**用户上传的文件存放的路径*/
	public static final String IMG_VIRTUAL_PATH="C:/ssm/";
	public static final String headImg = "headerImg";
	public static final String articleImg = "articleImg";
	public static final String USER_IMG_ROOT_PATH=ContextLoader.getCurrentWebApplicationContext().getServletContext().getContextPath()+ "/user/" + "img/";
/*
 * request.getSession().getServletContext().getContextPath() + "/user" + "/"
						+ "img/" + userID + "/" + articleImg + "/" + file.getName()
 * */
	/**
     * session中存储的信息
     */
    public static final String SESSION_USER_NAME="SESSION_USER_NAME";
    public static final String SESSION_USEREMAIL="SESSION_USEREMAIL";
    public static final String SESSION_USERID="SESSION_USERID";
    public static final String SESSION_AUTH="SESSION_AUTH";
    public static final String SESSION_ISLOGIN="SESSION_ISLOGIN";
    
    public static final boolean SESSION_LOGIN=true;
    public static final boolean SESSION_NOT_LOGIN=false;

    /**
     * 用户权限
     */
    public static final int USER_AUTH_ERROR=0x00;//未登录用户
    public static final int USER_AUTH_ADMIN=0x01;//管理员
    public static final int USER_AUTH_USER=0x02;//普通用户
    
    /**跳轉指令，/表示绝对定位，不加/表示相对定位*/
    public static final String WEB_REDIRECT_ABS="redirect:/";
    public static final String WEB_REDIRECT_REL="redirect:";
    
    /**记住密码存放在cookie中的键值*/
    public static final String REMEMBER_ME_USERID="userID";
    public static final String REMEMBER_ME_TOKEN="token";
    
    public static final String SSM_TOKEN="SSM_TOKEN";
    
    /**分页大小*/
    public static final int PAGE_SIZE=10;
    public static final boolean ARTICLE_ISPASS=true;
    public static final boolean COMMENT_ISPASS=true;
    public static final boolean COMMENT_STATUS_PASS=true;
    public static final boolean ARTICLE_STATUS_PASS=true;
    public static final boolean COMMENT_STATUS_NOPASS=false;
    public static final boolean ARTICLE_STATUS_NOPASS=false;
    
    
    public static final String COMMENT_AJAX="COMMENT_AJAX";
    
    public static final String ADMIN_EMAIL="564289319@qq.com";
    
    /**
     *放在cookie中的点赞结果，防止重复点赞，暂时放在cookie中的
     *要想从根本上解决点赞的高并发和重复点赞的问题，必须得有一张独立的点赞数据表
	 *id articleid fromuserid touserid  time ip loginuserid…
	 *基于IP的判断也是可以通过伪造IP绕过
	 *但是基于登陆用户ID的判断是绝对不会被绕过的，只有登陆的用户才能点赞。
	 *现在好多是把点赞信息放到cookie或session中，完全没有任何意义
     * */
    public static final String USER_LIKE_IN_COOKIE="USER_LIKE_IN_COOKIE";
    public static final String USER_LIKE_SEPARATOR="<#LIKE#>";
    public static final String USER_ALREADY_LIKE="USER_ALREADY_LIKE";
    public static final String REPORT_COMMENT="REPORT_COMMENT";
    
    
}
