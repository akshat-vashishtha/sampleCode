package com.qualtech.creditvidya.api.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CreditVidyaMVCServlet extends HttpServlet
{
	private static final long serialVersionUID = -3923184869483000032L;
	Logger logger=Logger.getLogger(CreditVidyaMVCServlet.class.getName());
	static ResourceBundle env ;//= PropertyFile.getInstance().getResourceBundel();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			//CreditVidyaService  creditVidyaMVCService = new CreditVidyaServiceImpl();
			String requestUrl= request.getRequestURL().toString();
			if(requestUrl.indexOf("getCreditVidyaOutPut")!=-1)
			{
				//creditVidyaMVCService.doPost(request, "CONSUMER");
				RequestDispatcher rd = request.getRequestDispatcher("creditVidyaUserView.jsp");
				rd.forward(request, response);
			}
			else if(requestUrl.indexOf("criffResponseforMicroFinance")!=-1)
			{
				
				//criffMVCService.doPost(request,  "MFI");
				RequestDispatcher rd = request.getRequestDispatcher("crifOutput.jsp");
				rd.forward(request, response);
			}
			else if(requestUrl.indexOf("downloadrequestJson")!=-1)
			{
				//criffMVCService.getCriffFile(request, response);
			}
			else if(requestUrl.indexOf("downloadRequestJsonForMFI")!=-1)
			{
				//criffMVCService.getCrifMFIFile(request, response);
			}
		}
		catch(Exception exception)
		{
			logger.error("CreditVidyaMVCServlet || doPost() || Exception occurs "+ exception);
		}
		
		
	}
}
