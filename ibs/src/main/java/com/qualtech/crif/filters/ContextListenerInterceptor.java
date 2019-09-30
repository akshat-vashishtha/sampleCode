package com.qualtech.crif.filters;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class ContextListenerInterceptor implements HandlerInterceptor
{
	static Logger _oLogger = Logger.getLogger(ContextListenerInterceptor.class.getName());
	@Autowired ServletContext servletContext;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
//        System.out.println("Pre-handle");
        String eqc_context = (String)servletContext.getAttribute("EQC_CONTEXT");
//        System.out.println("eqc_context : - : - "+eqc_context);
        if(eqc_context == null  || !eqc_context.equalsIgnoreCase("Y"))
        {
        	_oLogger.debug("Context Initialized Start");
        	
//        	Params.setMyFlowHost(servletContext.getInitParameter("MyFlowHost"));
//			Params.setMyFlowPort(servletContext.getInitParameter("MyFlowPort"));
//			Params.setMyFlowUser(servletContext.getInitParameter("MyFlowUser"));
//			Params.setMyFlowPwd(servletContext.getInitParameter("MyFlowPwd"));
//			Params.setStartSeq(Integer.parseInt(servletContext.getInitParameter("StartSeq")));
//			Params.setEndSeq(Integer.parseInt(servletContext.getInitParameter("EndSeq")));
//			Params.setXServiceHost(servletContext.getInitParameter("X_SERVICE_HOST"));
//			Params.setXServicePort(servletContext.getInitParameter("X_SERVICE_PORT"));
        	
        	try
    		{
    			ServletContext context = servletContext;			
    			initContext(context);			
    			/*String _strStartThread = context.getInitParameter("StartThread");
    			String _strEndThread = context.getInitParameter("EndThread");
    			_oLogger.debug("Context Start Thread : "+_strStartThread);
    			_oLogger.debug("Context End Thread : "+_strEndThread);
    			System.out.println("Context Start Thread : "+_strStartThread);
    			System.out.println("Context End Thread : "+_strEndThread);*/
    		}
    		catch(Exception ex)
    		{
    			_oLogger.error("Exception while Initializing context..."+ex.getMessage());
    			ex.printStackTrace();
    		}
    		_oLogger.debug("Context Initialized End");
        }
//        System.out.println("Host"+Params.getMyFlowHost());
//        System.out.println("Port"+Params.getMyFlowPort());
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception 
    {
//        System.out.println("Post-handle");
//    	System.out.println("Host -Post "+Params.getMyFlowHost());
//        System.out.println("Port -Post "+Params.getMyFlowPort());
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
//        System.out.println("After completion handle");
    }
	public void initContext(ServletContext context)
	{/*

		File fopPath = null;
		FopFactory fopFactory = null;
		FOUserAgent foUserAgent = null;
		TransformerFactory factory = null;
		try
		{
			_oLogger.debug("Came to set inital parameter in Context...");
			fopPath = new File(context.getRealPath(context.getInitParameter("FOPConfig")));			

			fopFactory = FopFactory.newInstance();
			DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();			
			FileInputStream dis = new FileInputStream(fopPath); 
			Configuration cfg = cfgBuilder.build(dis);

			fopFactory.setUserConfig(cfg);			
			foUserAgent = fopFactory.newFOUserAgent();
			factory = TransformerFactory.newInstance();

			context.setAttribute("EQC_CONTEXT", "Y");
			context.setAttribute("FACTORY", factory);
			context.setAttribute("FOPFACTORY", fopFactory);
			context.setAttribute("FOUSERAGENT", foUserAgent);

			Params.setMyFlowHost(context.getInitParameter("MyFlowHost"));
			Params.setMyFlowPort(context.getInitParameter("MyFlowPort"));
			Params.setMyFlowUser(context.getInitParameter("MyFlowUser"));
			Params.setMyFlowPwd(context.getInitParameter("MyFlowPwd"));
			Params.setStartSeq(Integer.parseInt(context.getInitParameter("StartSeq")));
			Params.setEndSeq(Integer.parseInt(context.getInitParameter("EndSeq")));
			Params.setXServiceHost(context.getInitParameter("X_SERVICE_HOST"));
			Params.setXServicePort(context.getInitParameter("X_SERVICE_PORT"));

			_oLogger.debug("Setting inital parameter in Context end...");
		}
		catch(SAXException sax)
		{
			_oLogger.error("SAXException Occured while Setting inital parameter in Context : "+sax.getMessage());
			sax.printStackTrace();
		}
		catch(IOException iox)
		{
			_oLogger.error("IOException Occured while Setting inital parameter in Context : "+iox.getMessage());
			iox.printStackTrace();
		}
		catch(Exception ex)
		{
			_oLogger.error("Exception Occured while Setting inital parameter in Context : "+ex.getMessage());
			ex.printStackTrace();
		}
	*/}

}
