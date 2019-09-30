package com.qualtech.crif.api.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qualtech.crif.api.service.CriffMVCService;
import com.qualtech.crif.api.service.impl.CriffMVCServiceImpl;

public class CrifMVCServlet extends HttpServlet
{
	private static final long serialVersionUID = -3923184869483000032L;
	Logger logger=Logger.getLogger(CrifMVCServlet.class.getName());
	static ResourceBundle env = ResourceBundle.getBundle("application");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		CriffMVCService  criffMVCService = new CriffMVCServiceImpl();
		String requestUrl= request.getRequestURL().toString();
		if(requestUrl.indexOf("getCrifOutPut")!=-1)
		{
			criffMVCService.doPost(request, "CONSUMER");
			RequestDispatcher rd = request.getRequestDispatcher("crifOutput.jsp");
			rd.forward(request, response);
		}
		else if(requestUrl.indexOf("criffResponseforMicroFinance")!=-1)
		{
			
			criffMVCService.doPost(request,  "MFI");
			RequestDispatcher rd = request.getRequestDispatcher("crifOutput.jsp");
			rd.forward(request, response);
		}
		else if(requestUrl.indexOf("downloadrequestJson")!=-1)
		{
			criffMVCService.getCriffFile(request, response);
		}
		else if(requestUrl.indexOf("downloadRequestJsonForMFI")!=-1)
		{
			criffMVCService.getCrifMFIFile(request, response);
		}
	}
}
