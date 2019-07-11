package com.nmid.web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class encodRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	public encodRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	public String getParameter(String name){
		String value = request.getParameter(name);
		try {
			value = new String(value.getBytes("iso-8859-1"),"utf-8");
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return value;
	}
	

}
