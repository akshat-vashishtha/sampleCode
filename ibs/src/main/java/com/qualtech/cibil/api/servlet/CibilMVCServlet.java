package com.qualtech.cibil.api.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qualtech.cibil.api.service.CibilMVCService;
import com.qualtech.cibil.api.service.impl.CibilMVCServiceImpl;
//--- only for java 8 use this and remove from web.xml or vice-versa
//@WebServlet(value = "/getCibilConsumerReport") 
public class CibilMVCServlet extends HttpServlet
{
	private static final long serialVersionUID = -3923184869483000032L;
	Logger logger=Logger.getLogger(CibilMVCServlet.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		CibilMVCService  cibilmvcservice = new CibilMVCServiceImpl();
		cibilmvcservice.doPostforCibilMVC(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("cibilReportPageNew.jsp");
		rd.forward(request, response);
	}
}
