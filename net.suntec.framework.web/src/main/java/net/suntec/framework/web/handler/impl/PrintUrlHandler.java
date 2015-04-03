package net.suntec.framework.web.handler.impl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.suntec.framework.web.handler.LogFilterHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintUrlHandler implements LogFilterHandler {
	Logger logger = LoggerFactory.getLogger(PrintUrlHandler.class);
	static String CON_STR = ".";
	static String EMPTY_STR = ".";

	@Override
	public void execute(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI();
		StringBuilder msg = new StringBuilder();
		msg.append("start");
		msg.append(EMPTY_STR);
		msg.append(path);
		msg.append(EMPTY_STR);
		msg.append("success");
		msg.append(EMPTY_STR);
		msg.append(CON_STR);
		msg.append(CON_STR);
		msg.append(CON_STR);
		msg.append(CON_STR);
		msg.append(CON_STR);
		logger.info(msg.toString());
		logger.info("session id is :" + req.getSession().getId());
	}
}
