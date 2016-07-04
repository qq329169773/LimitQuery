package com.jd.MethodLimit.limit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
/**
 * 设置 
 * @author zhangrui25
 *
 */
public @interface MethodLimit {
	
	/**
	 * 设置(单位时间1S)警告的访问数量
	 * @return
	 */
	int limitWarning() ;	 
		
	/**
	 * 设置(单位时间1S)最大访问数量<br >
	 * 超过当前的数量访问不可调用
	 * @return
	 */
	int limitUnavailable() ; 
	
}
