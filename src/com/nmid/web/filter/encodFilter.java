package com.nmid.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class encodFilter implements Filter {

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest re = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		res.setContentType("text/html;charset=utf-8");
		if(re.getMethod().equalsIgnoreCase("GET")){
			encodRequest encodrequest = new encodRequest(re);
			filterchain.doFilter(encodrequest,response);
		}
		if(re.getMethod().equalsIgnoreCase("POST")){
			request.setCharacterEncoding("utf-8");
			filterchain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig config) throws ServletException {
	}

}
