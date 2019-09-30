package com.qualtech.crif.filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ResponseFilter implements Filter 
{
  private FilterConfig filterConfig = null;

  public void doFilter(ServletRequest request, ServletResponse response,
    FilterChain chain)
    throws IOException, ServletException 
  {
	  HttpServletRequest httpRequest = ((HttpServletRequest) request);
	  HttpServletResponse httpResponse = ((HttpServletResponse) response);
	  httpResponse.setHeader("X-Frame-Options", "deny"); // http response header decide weather browser allow <Iframe>
	  httpResponse.setHeader("X-Content-Type-Options", "nosniff"); 
	  httpResponse.setHeader("cache-control", "no-cache,no-store,max-age=-1,must-revalidate,private,pre-check=0,post-check=0");
	  httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains"); // allow browser commission to application only with https
	  httpResponse.setHeader("X-XSS-Protection", "1; mode=block");  //wheather Xss filter will be active or not

	  httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	  httpResponse.setDateHeader("Expires", 0); 	  
	  
	
	  
      chain.doFilter(request, response);
 
  }

  public void destroy() { }

  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }
  
  
  
  
}
