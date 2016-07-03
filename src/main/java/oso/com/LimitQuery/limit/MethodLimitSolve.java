package oso.com.LimitQuery.limit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import oso.com.LimitQuery.bean.MethodExecuteLimitBean;
import oso.com.LimitQuery.bean.MethodExecuteLogBean;
import oso.com.LimitQuery.holdValue.HoldDoubleValue;
import oso.com.LimitQuery.iface.HandRemovalNotificationIface;
import oso.com.LimitQuery.iface.impl.PrintHandleMethodExecuteLog;
import oso.com.LimitQuery.utils.CacheUtils;
import oso.com.LimitQuery.utils.Utils;
 
//正常执行的 before->执行代码->after->afterReturning
//报错的执行before->(执行代码throw处)->after->handException
public class MethodLimitSolve {
 	
	
	private static Logger logger = LoggerFactory.getLogger(MethodLimitSolve.class);

	public static int logSummaryMill  = 1000 * 1  ;			//日志1分钟统计一次
	
 	private boolean doMethodLimitSolve = true  ;

	//public static Map<String,MethodExecuteLogBean> methodLimitLogCache = new HashMap<String,MethodExecuteLogBean>();
	
	//public static ConcurrentHashMap<String,MethodExecuteLogBean> methodLimitLogCache = new ConcurrentHashMap<String,MethodExecuteLogBean>(); //
	
 	public static LoadingCache<String,MethodExecuteLogBean> methodLimitLogCache   =
	         CacheBuilder.newBuilder()
	        .build(new CacheLoader<String, MethodExecuteLogBean>() {
	             @Override
	             public MethodExecuteLogBean load(String seconds) throws Exception {
	                 return createBean();
	             }
	        });

	private static MethodExecuteLogBean createBean(){
		MethodExecuteLogBean logBean = new MethodExecuteLogBean();
		firstRecodeMethodExeceute(logBean,ThreadLocalParams.get(MethodLimitContants.METHOD_SIGN).toString());
		methodLimitLogCache.put(ThreadLocalParams.get(MethodLimitContants.METHOD_TIME_KEY).toString(), logBean);
		return logBean ;
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
    		HoldDoubleValue<Boolean, Method> canDoMethodLimitValue = getCanDoMethodLimit(joinPoint);
   			Method method = canDoMethodLimitValue.b ;
  			if(canDoMethodLimitValue.a){
  				ThreadLocalParams.add(MethodLimitContants.METHOD_START_TIME , System.currentTimeMillis());
  				String logKey = getMD5MethodLimitLogCode(method.toGenericString());
  				ThreadLocalParams.add(MethodLimitContants.METHOD_TIME_KEY, logKey);
  				ThreadLocalParams.add(MethodLimitContants.METHOD_SIGN, method.toGenericString());
				MethodExecuteLogBean logBean = methodLimitLogCache.get(logKey)  ;
				logBean.addCountExecute();
     		}else{
     			logger.error(joinPoint+"");
      			System.err.println("herere");
     		}
  		}else{
  			System.err.println("herere");
  		}
  	}
		

 
 	/**
 	 * 1.为了节省空间采用(IP_MethodSign_Time)生产Code作为一个KEY
 	 * @return
 	 */
 	private String getMD5MethodLimitLogCode(String methodSign){
  		String requestIpAdderss = ThreadLocalParams.get(MethodLimitContants.IP).toString();
 		StringBuilder sbuilder = new StringBuilder(requestIpAdderss);
 		sbuilder.append("_").append(methodSign).append(System.currentTimeMillis()/(logSummaryMill));
 		return Utils.MD5(sbuilder.toString());
 	}
 	/**
 	 * 在某个时间(某个IP地址,某个方法)段第一次访问
 	 * @param logBean
 	 */
 	private static MethodExecuteLogBean firstRecodeMethodExeceute(MethodExecuteLogBean logBean,String methodSign){
  		long currentTime = System.currentTimeMillis() ;
 		logBean.setHostIp(Utils.getHost());
 		logBean.setIp(ThreadLocalParams.get(MethodLimitContants.IP).toString());
 		logBean.setMethodSign(methodSign);
 		Object webRequestUri = ThreadLocalParams.get(MethodLimitContants.WEB_REQUEST_URI);
 		Object methodRequestType = ThreadLocalParams.get(MethodLimitContants.METHOD_REQUEST_TYPE);
 		logBean.setUri(webRequestUri != null ? webRequestUri.toString() : "");
 		logBean.setRequestType(methodRequestType != null ? methodRequestType.toString() : "");
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
 	private HoldDoubleValue<Boolean, Method> getCanDoMethodLimit(JoinPoint joinPoint){
 		Boolean isDoCanLimit = false ; 
 		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
 		Method method = methodSignature.getMethod();
 		MethodLimit annotation =	method.getAnnotation(MethodLimit.class);
 		if(annotation == null){
  	 		Annotation[] classAnnotations = joinPoint.getTarget().getClass().getAnnotations();
 	 		String  annotations = Arrays.asList(classAnnotations).toString();
 	 		isDoCanLimit = annotations.indexOf("RestController") > -1 || annotations.indexOf("Controller")> -1 ;
 		}else{
 			isDoCanLimit = true ;
 		}
 		return new HoldDoubleValue<Boolean, Method>(isDoCanLimit, method) ;
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
			Object logKey = ThreadLocalParams.get(MethodLimitContants.METHOD_TIME_KEY);
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
			Object logKey = ThreadLocalParams.get(MethodLimitContants.METHOD_TIME_KEY);
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
