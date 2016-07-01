package oso.com.LimitQuery.limit;

import org.springframework.stereotype.Component;

enum MethodLimitRequestFromEnum {WEBRequest,JSFRequest }

 
public class MethodLimitContants {

	public static final String METHOD_REQUEST_TYPE = "MREQUESTTYPE" ;
	 
	public static final String METHOD_START_TIME = "MSTARTTIME";	//方法开始执行的时间
	
	public static final String METHOD_END_TIME = "MENDTIME";	//方法结束执行的时间

	public static final String WEB_REQUEST_URI = "WEB_REQUEST_URI";	//方法开始执行的时间

	public static final String METHOD_TIME_KEY = "METHOD_TIME_KEY";	//Map 中的Key
	
	public static final String METHOD_SIGN = "METHOD_SIGN";	//Map 中的Key

	public static final String IP = "IP";
 
	
}
