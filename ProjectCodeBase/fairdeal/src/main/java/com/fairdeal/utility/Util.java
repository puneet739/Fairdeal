package com.fairdeal.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import com.fairdeal.util.LoggerUtil;

public class Util {

	public static InputStream getImageStream(UploadedFile uploadedFile){
		InputStream stream=null;
		try{
			if (uploadedFile!=null)
				stream = uploadedFile.getInputstream();
		}catch(IOException e){
			LoggerUtil.debug("No Image exist",e);
		}catch(NullPointerException e){
			LoggerUtil.error("Image is not uploaded. Null Pointer Exceptoin",e);
		}
		return stream;
	}
	
	public static HttpSession getCurrentSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session;
	}
	
	public static void doInternalRedirect(String url) throws IOException{
		doInternalRedirect(url,true);
	}
	
	public synchronized static void returntoSamePage() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String,String> map = context.getExternalContext().getRequestParameterMap();
	    String url = map.get("urlString");
	    String query = map.get("queryString");
	    String finalUrl=url;
	    if (query.trim()!=null && !query.isEmpty() && !query.equals("null")) 
	    	finalUrl= finalUrl+"?"+query;
	    doInternalRedirect(finalUrl,false);
	}
	
	public static void doInternalRedirect(String url, boolean appendContextPath) throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
	    String contextPath = origRequest.getContextPath();
	    String finalUrl =(appendContextPath)?contextPath+url:url;
		FacesContext.getCurrentInstance().getExternalContext().redirect(finalUrl);
	}
}
