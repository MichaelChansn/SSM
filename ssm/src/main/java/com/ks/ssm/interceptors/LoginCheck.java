package com.ks.ssm.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {

	/**用户记住密码自动登录*/
	boolean autoLogin() default false;
	
	/**必须登录才能访问的页面使用check 打标，进行访问控制*/
	boolean check() default false;
	/**登录或不登录都能访问的页面，使用saveInfo打标，主要是向前台传递登录用户信息*/
	boolean saveInfo() default false;

	
	
	
}
