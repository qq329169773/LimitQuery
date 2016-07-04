package com.jd.MethodLimit.limit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.bean.MethodExecuteLogBean;
import com.jd.MethodLimit.holdValue.HoldThirdValue;
import com.jd.MethodLimit.util.Utils;
 
//正常执行的 before->执行代码->after->afterReturning
//报错的执行before->(执行代码throw处)->after->handException
public class MethodLimitSolve {
 
	public static int logSummaryMill  = 1000 * 15  ;			//日志1分钟统计一次
	
 	private boolean doMethodLimitSolve = true  ;

	//public static Map<String,MethodExecuteLogBean> methodLimitLogCache = new HashMap<String,MethodExecuteLogBean>();
	
	//public static ConcurrentHashMap<String,MethodExecuteLogBean> methodLimitLogCache = new ConcurrentHashMap<String,MethodExecuteLogBean>(); //
	
 	public static LoadingCache<String,MethodExecuteLogBean> methodLimitLogCache   =
	         CacheBuilder.newBuilder()
	        .build(new CacheLoader<String, MethodExecuteLogBean>() {
	             @Override
	             public MethodExecuteLogBean load(String seconds) throws Exception {
 	                 return createLogBean();
	             }
	        });
	public static LoadingCache<String,MethodExecuteLimitBean> methodLimitCache   =
	         CacheBuilder.newBuilder()
	        .build(new CacheLoader<String, MethodExecuteLimitBean>() {
	             @Override
	             public MethodExecuteLimitBean load(String seconds) throws Exception {
	                 return createLimitBean();
	             }
	        });
	private static MethodExecuteLogBean createLogBean(){
		MethodExecuteLogBean logBean = new MethodExecuteLogBean();
		firstRecodeMethodExeceute(logBean,ThreadLocalParams.get(MethodLimitContants.METHOD_SIGN).toString());
		methodLimitLogCache.put(ThreadLocalParams.get(MethodLimitContants.METHOD_LOG_TIME_KEY).toString(), logBean);
		return logBean ;
	}
	private static MethodExecuteLimitBean createLimitBean(){
		
 		MethodLimit methodLimit = (MethodLimit) ThreadLocalParams.get(MethodLimitContants.METHOD_LIMIT);
		
		MethodExecuteLimitBean limitBean = new MethodExecuteLimitBean(methodLimit.limitWarning(),
				methodLimit.limitUnavailable(),(Long)ThreadLocalParams.get(MethodLimitContants.METHOD_LIMIT_START_SECENDS));
		
		limitBean.setHostIp(Utils.getHost());
		limitBean.setIp(ThreadLocalParams.getString(MethodLimitContants.IP));
		limitBean.setMethodSign(ThreadLocalParams.getString(MethodLimitContants.METHOD_SIGN));
		methodLimitCache.put(ThreadLocalParams.getString(MethodLimitContants.METHOD_LOG_LIMIT_KEY), limitBean);
		return limitBean;
	}
 	/**
	 * 1.记录方法开始的时间
	 * 2.记录是否需要做拦截(只有Controller，或者是有MethodLimit的才能做限制)
	 * 3.获取当前的IP+Method(Sign)+Time，转成MD5
	 * @param joinPoint
 	 * @throws InterruptedException 
 	 * @throws ExecutionException 
	 */
 	public void before(JoinPoint joinPoint  ) throws ExecutionException{
    	if(doMethodLimitSolve){
   			HoldThirdValue<Boolean, Method,MethodLimit> canDoMethodLimitValue = getCanDoMethodLimit(joinPoint);
   			Method method = canDoMethodLimitValue.b ;
  			if(canDoMethodLimitValue.a){
  				//方法日志
  				long currentTime = System.currentTimeMillis() ;
  				ThreadLocalParams.add(MethodLimitContants.METHOD_START_TIME , currentTime);
  				String logKey = getMD5MethodLimitLogKey(method.toGenericString(),currentTime);
  				ThreadLocalParams.add(MethodLimitContants.METHOD_LOG_TIME_KEY, logKey);
  				ThreadLocalParams.add(MethodLimitContants.METHOD_SIGN, method.toGenericString());
  				ThreadLocalParams.add(MethodLimitContants.METHOD_LIMIT, canDoMethodLimitValue.c);
  				ThreadLocalParams.add(MethodLimitContants.METHOD_LIMIT_START_SECENDS, currentTime / 1000 );
   				MethodExecuteLogBean logBean = methodLimitLogCache.get(logKey)  ;
				logBean.addCountExecute();
				//方法限制
  				String limitKey = getMD5MethodLimitKey(method.toGenericString(),currentTime);
   				ThreadLocalParams.add(MethodLimitContants.METHOD_LOG_LIMIT_KEY, limitKey);
  				MethodExecuteLimitBean limitBean = methodLimitCache.get(limitKey)  ;
				limitBean.addQps();
     		} 
  		} 
   	}
		

 
 	/**
 	 * 1.为了节省空间采用(IP_MethodSign_Time)生产Code作为一个KEY
 	 * @return
 	 */
 	private static String getMD5MethodLimitLogKey(String methodSign,long currentTime){
  		String requestIpAdderss = ThreadLocalParams.getString(MethodLimitContants.IP);
 		StringBuilder sbuilder = new StringBuilder(requestIpAdderss);
 		sbuilder.append("_").append(methodSign).append(currentTime/(logSummaryMill));
   		return Utils.MD5(sbuilder.toString());
 	}
 	/**
 	 * 1.为了节省空间采用(IP_MethodSign_Time)生产Code作为一个KEY
 	 * @return
 	 */
 	private static String getMD5MethodLimitKey(String methodSign , long currentTime){
  		String requestIpAdderss = ThreadLocalParams.getString(MethodLimitContants.IP);
 		StringBuilder sbuilder = new StringBuilder(requestIpAdderss);
 		sbuilder.append("_").append(methodSign).append(currentTime/(1000));
 		return Utils.MD5(sbuilder.toString());
 	}
 	/**
 	 * 在某个时间(某个IP地址,某个方法)段第一次访问
 	 * @param logBean
 	 */
 	private static MethodExecuteLogBean firstRecodeMethodExeceute(MethodExecuteLogBean logBean,String methodSign){
  		long currentTime = System.currentTimeMillis() ;
 		logBean.setHostIp(Utils.getHost());
 		logBean.setIp(ThreadLocalParams.getString(MethodLimitContants.IP));
 		logBean.setMethodSign(methodSign);
   		logBean.setUri(ThreadLocalParams.getString(MethodLimitContants.WEB_REQUEST_URI));
 		logBean.setRequestType(ThreadLocalParams.getString(MethodLimitContants.METHOD_REQUEST_TYPE));
 		logBean.setStartTime(currentTime);
 		logBean.setEndTime(currentTime + logSummaryMill);
 		return logBean ;
 	}
 	/**
 	 * 是否可以做方法的拦截
 	 * 类上有Controller , 或者 RestController(因为我本地是不会引入SpringMVC-所以我是用字符串判断的)
 	 * 方法上有MethodLimit注解
 	 * @param joinPoint
 	 * @return
 	 */
 	private HoldThirdValue<Boolean, Method,MethodLimit> getCanDoMethodLimit(JoinPoint joinPoint){
 		Boolean isDoCanLimit = false ; 
 		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
 		Method method = methodSignature.getMethod();
 		MethodLimit methodLimit = method.getAnnotation(MethodLimit.class);  //优先读取方法上面的设置
 		if(methodLimit == null){
 			Class<?> clazz = joinPoint.getTarget().getClass();
  	 		Annotation[] classAnnotations = clazz.getAnnotations();
 	 		String  annotations = Arrays.asList(classAnnotations).toString();
 	 		isDoCanLimit = annotations.indexOf("RestController") > -1 || annotations.indexOf("Controller")> -1 ;
 	 		methodLimit = clazz.getAnnotation(MethodLimit.class);
 		}else{
 			isDoCanLimit = true ;
 		}
 		return new HoldThirdValue<Boolean, Method, MethodLimit>(isDoCanLimit, method,methodLimit) ;
 	}
  	/**
 	 * 方法执行完,马上执行如果报错，就从报错点结束->然后执行他
 	 * 1.记录方法的最后执行时间
 	 * @param joinPoint
 	 */
	public void after(JoinPoint joinPoint){
  	}
 	//方法正常执行  && return 
	public void afterReturning(JoinPoint joinPoint) throws ExecutionException{
		if(doMethodLimitSolve){
			Object logKey = ThreadLocalParams.get(MethodLimitContants.METHOD_LOG_TIME_KEY);
			if(null != logKey){
				MethodExecuteLogBean logBean = methodLimitLogCache.get(logKey.toString());
				if(null != logBean && null != logBean.getMethodSign()){
					long start = Long.parseLong(ThreadLocalParams.get(MethodLimitContants.METHOD_START_TIME).toString());
					long durationTime = System.currentTimeMillis() - start ;
 					logBean.appendMethodExecuteTime(durationTime);
 					logBean.addCountExecuteSucess();
				}
			}
		}
 	}
	//方法执行报错的情况
	public void handException(JoinPoint joinPoint , Exception e) throws ExecutionException{
		if(doMethodLimitSolve){
			Object logKey = ThreadLocalParams.get(MethodLimitContants.METHOD_LOG_TIME_KEY);
			if(null != logKey){
				MethodExecuteLogBean logBean = methodLimitLogCache.get(logKey.toString());
				if(null != logBean && null != logBean.getMethodSign()){
					long start = Long.parseLong(ThreadLocalParams.get(MethodLimitContants.METHOD_START_TIME).toString());
					long durationTime = System.currentTimeMillis() - start ;
 					logBean.addcountExecuteFailed();
 					logBean.appendMethodExecuteTime(durationTime);
				}
			}
		}
 	}
	//
}
