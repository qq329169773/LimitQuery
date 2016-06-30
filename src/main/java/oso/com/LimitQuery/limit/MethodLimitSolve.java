package oso.com.LimitQuery.limit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import oso.com.LimitQuery.bean.MethodExecuteLimitBean;
import oso.com.LimitQuery.bean.MethodExecuteLogBean;
import oso.com.LimitQuery.holdValue.HoldDoubleValue;
import oso.com.LimitQuery.iface.HandRemovalNotificationIface;
import oso.com.LimitQuery.utils.CacheUtils;
import oso.com.LimitQuery.utils.Utils;
 
//正常执行的 before->执行代码->after->afterReturning
//报错的执行before->(执行代码throw处)->after->handException
public class MethodLimitSolve {
	
	
 	
	private int logSummaryMin  = 1  ;			//日志1分钟统计一次
	private boolean doMethodLimitSolve = true  ;
	
	@Autowired
	@Qualifier("printNotifycation")
	private HandRemovalNotificationIface<String, MethodExecuteLogBean> handRemoveNotifycation;
	
	private LoadingCache<String,MethodExecuteLogBean> methodLimitLogCache = 
			CacheUtils.cached(new CacheLoader<String, MethodExecuteLogBean>(){
				@Override
				public MethodExecuteLogBean load(String key) throws Exception {
		 			return new MethodExecuteLogBean();
				}
			} ,logSummaryMin , handRemoveNotifycation) ; 	 //记录一段时间内的某个IP，访问的记录
	 	
 	/**
	 * 1.记录方法开始的时间
	 * 2.记录是否需要做拦截(只有Controller，或者是有MethodLimit的才能做限制)
	 * 3.获取当前的IP+Method(Sign)+Time，转成MD5
	 * @param joinPoint
	 */
 	public void before(JoinPoint joinPoint  ){
   		if(doMethodLimitSolve){
  			HoldDoubleValue<Boolean, Method> canDoMethodLimitValue = getCanDoMethodLimit(joinPoint);
  			if(canDoMethodLimitValue.a){
  				ThreadLocalParams.add(MethodLimitContants.METHOD_START_TIME , System.currentTimeMillis());
  				String logKey = getMD5MethodLimitLogCode(canDoMethodLimitValue.b);
  				MethodExecuteLogBean logBean =	methodLimitLogCache.getIfPresent(logKey);
  				if(logBean == null){
  					logBean =  new MethodExecuteLogBean();
  				}else{
  					//累计时间
  				}
   			}
  		}
  	}
 	/**
 	 * 1.为了节省空间采用(IP_MethodSign_Time)生产Code作为一个KEY
 	 * @return
 	 */
 	private String getMD5MethodLimitLogCode(Method method){
  		String requestIpAdderss = ThreadLocalParams.get(MethodLimitContants.IP).toString();
 		StringBuilder sbuilder = new StringBuilder(requestIpAdderss);
 		sbuilder.append("_").append(method.toGenericString()).append(System.currentTimeMillis()/(logSummaryMin * 6000));
 		return Utils.MD5(sbuilder.toString());
 	}
 	/**
 	 * 在某个时间(某个IP地址,某个方法)段第一次访问
 	 * @param logBean
 	 */
 	private void firstRecodeMethodExeceute(MethodExecuteLogBean logBean){
 		
 		
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
 		Annotation annotation =	method.getAnnotation(MethodLimit.class);
 		if(annotation == null){
 	 		Annotation[] classAnnotations = joinPoint.getTarget().getClass().getAnnotations();
 	 		String  annotations = Arrays.asList(classAnnotations).toString();
 	 		isDoCanLimit = annotations.indexOf("RestController") > -1 || annotations.indexOf("Controller")> -1 ;
 		}else{
 			isDoCanLimit = true ;
 		}
 		return new HoldDoubleValue<Boolean, Method>(isDoCanLimit, method) ;
 	}
	//方法执行完,马上执行如果报错，就从报错点结束->然后执行他
	public void after(JoinPoint joinPoint){
		
		Annotation[] annotations =  joinPoint.getTarget().getClass().getAnnotations();
		
 	}
	@MethodLimit(limitUnavailable = 1 , limitWarning = 2)
	//方法正常执行  && return 
	public void afterReturning(JoinPoint joinPoint){
 	}
	//方法执行报错的情况
	public void handException(JoinPoint joinPoint , Exception e){
 	}
	
	//
}
