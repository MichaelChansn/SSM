package com.ks.ssm.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenCheck {
	/**打在需要token校验的controller上*/
	boolean check() default false;
	/**打在需要产生token的controller上*/
	boolean generateToken() default false;

}
