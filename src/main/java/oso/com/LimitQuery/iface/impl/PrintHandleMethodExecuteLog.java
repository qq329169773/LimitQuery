package oso.com.LimitQuery.iface.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import oso.com.LimitQuery.bean.MethodExecuteLogBean;
import oso.com.LimitQuery.exception.LimitSystemException;
import oso.com.LimitQuery.iface.HandleMethodExecuteLogIface;

@Component("printHandleMethodExecuteLog")
public class PrintHandleMethodExecuteLog implements HandleMethodExecuteLogIface{
	/**
	 * SLF4J logger
	 */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(PrintHandleMethodExecuteLog.class);	
	@Override
	public void handMethodExecuteLog(MethodExecuteLogBean methodExecuteLogBean) throws LimitSystemException {
  		LOGGER.info(methodExecuteLogBean.toString());
	}

	@Override
	public void handMethodExecuteLog(List<MethodExecuteLogBean> methodExecuteLogBeans) throws LimitSystemException {
 		
	}
}
