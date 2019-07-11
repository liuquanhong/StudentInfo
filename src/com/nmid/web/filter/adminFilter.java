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

public class adminFilter implements Filter {

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest re = (HttpServletRequest)request;
		res.sendRedirect(re.getContextPath()+"/jsps/login.jsp");
		filterchain.doFilter(request, response);
	}

	
	public void init(FilterConfig arg0) throws ServletException {
	}

}
