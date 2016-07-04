package com.jd.MethodLimit.iface.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.bean.MethodExecuteLogBean;
import com.jd.MethodLimit.exception.LimitSystemException;
import com.jd.MethodLimit.iface.HandleMethodExecuteLogIface;

@Component
public class InsertHbaseHandleMethodExecuteLog implements HandleMethodExecuteLogIface{
	
	
	
	@Override
	public void handMethodExecuteLog(MethodExecuteLogBean methodExecuteLogBean) throws LimitSystemException {
 		
	}

	@Override
	public void handMethodExecuteLog(List<MethodExecuteLogBean> methodExecuteLogBeans) throws LimitSystemException {
 		
	}

	@Override
	public void handMethodLimitLog(MethodExecuteLimitBean limitBean)
			throws LimitSystemException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void handMethodLimitLog(List<MethodExecuteLimitBean> limitBeans)
			throws LimitSystemException {
		// TODO Auto-generated method stub
		
	}
	
}
