package oso.com.LimitQuery.limit;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import oso.com.LimitQuery.bean.MethodExecuteLimitBean;
import oso.com.LimitQuery.bean.MethodExecuteLogBean;
import oso.com.LimitQuery.iface.HandleMethodExecuteLogIface;

@Component
public class HandMethodExecuteResult {
	
	@Autowired
	@Qualifier("printHandleMethodExecuteLog")
	private HandleMethodExecuteLogIface handleMethodExecuteLog ;
	private static long total = 0 ;

	//Seconds Minutes Hours DayofMonth Month DayofWeek 
 	@Scheduled(cron = "*/4 * * * * ?")
	public void timerHandMethodExcuteLog(){
		System.out.println("------------------" +(total)  );
			Map<String, MethodExecuteLogBean> datas = MethodLimitSolve.methodLimitLogCache.asMap();
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
 	@Scheduled(cron = "*/4 * * * * ?")
	public void timerHandMethodLimitLog(){
 			Map<String, MethodExecuteLimitBean> datas = MethodLimitSolve.methodLimitCache.asMap();
			Iterator<Entry<String, MethodExecuteLimitBean>> it = datas.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, MethodExecuteLimitBean> entry = it.next();
				MethodExecuteLimitBean limitBean = entry.getValue();
				long currentTime = System.currentTimeMillis();
				if(limitBean.getEndSeconds() <  (currentTime / 1000)){
	 				datas.remove(entry.getKey());
	 				handleMethodExecuteLog.handMethodLimitLog(limitBean);
	  			}
			}
	}
}
