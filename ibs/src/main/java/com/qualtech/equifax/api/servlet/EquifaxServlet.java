package com.qualtech.equifax.api.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qualtech.equifax.api.service.EquifaxMVCService;
import com.qualtech.equifax.api.service.impl.EquifaxMVCServiceImpl;
//--- only for java 8 use this and remove from web.xml or vice-versa
/*@WebServlet(urlPatterns = {"/equifaxPcrHtml", "/equifaxMfiHtml", "/equifaxUserFriendlyOutPutForVID", "/equifaxuserfriendlyoutputforEVDR"
		, "/downloadrequestJsonConsumer", "/downloadrequestJsonMFI", "/downloadrequestJsonEVDR","/downloadrequestJsonVID"})*/
public class EquifaxServlet extends HttpServlet
{
	private static final long serialVersionUID = -3923184869483000032L;
	Logger logger=Logger.getLogger(EquifaxServlet.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String requestUrl= request.getRequestURL().toString();
		if(requestUrl.indexOf("equifaxPcrHtml")!=-1)
		{
		EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
		equifaxmvcservice.doPostforEquifaxPCS(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("EquifaxPCRNew.jsp");
		rd.forward(request, response);
		}
		else if(requestUrl.indexOf("equifaxMfiHtml")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.doPostforEquifaxMCR(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("EquifaxMFINew.jsp");
			rd.forward(request, response);
		}
		else if(requestUrl.indexOf("equifaxUserFriendlyOutPutForVID")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.doPostforEquifaxVID(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("EquifaxUserFriendlyOutPutForVID.jsp");
			rd.forward(request, response);
		}
		else if(requestUrl.indexOf("equifaxuserfriendlyoutputforEVDR")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.doPostforEquifaxEVDR(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("EquifaxUserFriendlyOutPutForEVDR.jsp");
			rd.forward(request, response);
		}
		else if(requestUrl.indexOf("downloadrequestJsonVID")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.downloadrequestJsonVID(request, response);
		}
		else if(requestUrl.indexOf("downloadrequestJsonEVDR")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.downloadrequestJsonEVDR(request, response);
		}
		else if(requestUrl.indexOf("downloadrequestJsonMFI")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.downloadrequestJsonMFI(request, response);
		}
		else if(requestUrl.indexOf("downloadrequestJsonConsumer")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.downloadrequestJsonConsumer(request, response);
		}
	
	}
}
