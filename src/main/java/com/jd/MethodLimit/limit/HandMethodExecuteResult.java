package com.jd.MethodLimit.limit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.bean.MethodExecuteLogBean;
import com.jd.MethodLimit.iface.HandleMethodExecuteLogIface;

@Component
public class HandMethodExecuteResult {
	
	@Autowired
	@Qualifier("printHandleMethodExecuteLog")
	private HandleMethodExecuteLogIface handleMethodExecuteLog ;
	private static long total = 0 ;

	//Seconds Minutes Hours DayofMonth Month DayofWeek 
 	@Scheduled(cron = "*/59 * * * * ?")
	public void timerHandMethodExcuteLog(){
 		ConcurrentMap<String, MethodExecuteLogBean> datas = MethodLimitSolve.methodLimitLogCache.asMap();
		Iterator<Entry<String, MethodExecuteLogBean>> it = datas.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, MethodExecuteLogBean> entry = it.next();
			MethodExecuteLogBean bean = entry.getValue();
			long currentTime = System.currentTimeMillis();
			if(bean.getEndTime()  <  currentTime + 1000
					&& bean.getCountExecute().get() == (bean.getCountExecuteFailed().get() + bean.getCountExecuteSucess().get())){
 				datas.remove(entry.getKey());
 				total += bean.getCountExecute().get();
 				handleMethodExecuteLog.handMethodExecuteLog(bean);
  			}
		}
	}
 	@Scheduled(cron = "*/10 * * * * ?")
	public void timerHandMethodLimitLog(){
 		ConcurrentMap<String, MethodExecuteLimitBean> datas = MethodLimitSolve.methodLimitCache.asMap();
 		Iterator<Entry<String, MethodExecuteLimitBean>> it = datas.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, MethodExecuteLimitBean> entry = it.next();
			MethodExecuteLimitBean limitBean = entry.getValue();
			long currentTime = System.currentTimeMillis();
			if(limitBean.getEndSeconds() <  (currentTime / 1000)+5){
 				datas.remove(entry.getKey());
  				handleMethodExecuteLog.handMethodLimitLog(limitBean);
  			}
		}
	}
}
