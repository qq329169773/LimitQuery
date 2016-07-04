package com.jd.MethodLimit.iface.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.bean.MethodExecuteLogBean;
import com.jd.MethodLimit.exception.LimitSystemException;
import com.jd.MethodLimit.iface.HandleMethodExecuteLogIface;

@Component("printHandleMethodExecuteLog")
public class PrintHandleMethodExecuteLog implements HandleMethodExecuteLogIface{
  	
	private static Logger requestLog = Logger.getLogger("method_request_log");
	private static Logger limitLog = Logger.getLogger("method_limit_log");
	@Override
	public void handMethodExecuteLog(MethodExecuteLogBean methodExecuteLogBean) throws LimitSystemException {
		requestLog.info(methodExecuteLogBean);
 	}

	@Override
	public void handMethodExecuteLog(List<MethodExecuteLogBean> methodExecuteLogBeans) throws LimitSystemException {
 		
	}

	@Override
	public void handMethodLimitLog(MethodExecuteLimitBean limitBean)
			throws LimitSystemException {
		limitLog.info(limitBean);
	}

	@Override
	public void handMethodLimitLog(List<MethodExecuteLimitBean> limitBeans)
			throws LimitSystemException {
	}
	
}
