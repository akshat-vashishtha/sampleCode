package com.qualtech.crif.filters;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
public class XSSFilter implements Filter {

   
    public void init(FilterConfig filterConfig) throws ServletException {
    }

   
    public void destroy() {
    }

  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    	
    	String userName=null;
    	userName=request.getParameter("userName");
    
	chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);    
    	
    	
    }
}