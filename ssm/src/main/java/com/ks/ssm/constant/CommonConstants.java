package com.ks.ssm.constant;

public class CommonConstants {
	
	public final static String SALT="copy right by ks";

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
    
}
