package com.ks.ssm.constant;

import org.springframework.web.context.ContextLoader;

public class CommonConstants {
	
	public final static String SALT="copy right by ks";
	
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
    public static final boolean ISPASS=true;
    
    
}
