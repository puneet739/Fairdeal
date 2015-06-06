package com.fairdeal.utility;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ObjectRepository{

	public static Object getSpringBean(String name) {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
		
		return ctx.getBean(name);
	}
	
	public static <T> T getSpringBean(Class<T> name) {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
		return ctx.getBean(name);
	}
}
