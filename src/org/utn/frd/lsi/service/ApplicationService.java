package org.utn.frd.lsi.service;

import java.util.Map;
import java.util.logging.Logger;

import com.opensymphony.xwork2.ActionContext;

public class ApplicationService {
	
	private static final Logger log = Logger.getLogger(ApplicationService.class.getName());
	
	public static String getNamespace() {
		return (String) session().get("namespace");
	}
	
	private static Map<String, Object> session() {
		return ActionContext.getContext().getSession();
	}

}
