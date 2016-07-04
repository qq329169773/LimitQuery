package com.jd.MethodLimit.limit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrui25
 */
public class ThreadLocalParams {
	
	//线程安全变量
	private static ThreadLocal<Map<String, Object>> params = new ThreadLocal<Map<String, Object>>(){
		protected synchronized java.util.Map<String,Object> initialValue() {
			return new HashMap<String, Object>();
		};
	};
	
	public static void add(String key , Object value){
		Map<String,Object> datas = params.get();
		datas.put(key, value);
		params.set(datas);
		
	}
	public static Object get(String key){
		Map<String,Object> datas = params.get();
		return datas.get(key);
	}
	public static String getString(String key){
		Object obj = get(key) ;
 		return obj != null ?obj.toString():"";
	}
}
