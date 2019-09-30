package com.qualtech.cibil.api.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
@WebServlet(value = "/downloadRequestJSONForCibil") 
public class DownloadReqJsonServlet extends HttpServlet
{
	private static final long serialVersionUID = -3923184869483000032L;
	Logger logger=Logger.getLogger(DownloadReqJsonServlet.class.getName());
	@Autowired PropertyFile env;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FileInputStream fileIn = null;
		ServletOutputStream out =null;
		try {
			String filepath = env.getString("com.qc.cibilConsumer.requestXlsFilePath");
			File file = new File(filepath);
			fileIn = new FileInputStream(file);
			response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=CibilConsumerRequestJson.xlsx");
			out = response.getOutputStream();
			int i = 0;
			while ((i = fileIn.read()) != -1)
			{
				out.write(i);
			}
			fileIn.close();
			out.flush();
			out.close();
		} 
		catch (Exception ec)
		{
			logger.error("Exception in DownloadReqJsonServlet || doPost(req,res) "+ec);
		}
		finally {
			if(fileIn!=null) {
				try {
					fileIn.close();
				} catch (Exception e) {
					logger.error("Exception to close FileInputStream in doPost(req,res) "+e);
				}
			
			}
			if(out!=null) {
				try {
					out.flush();
					out.close();
				} catch (Exception e) {
					logger.error("Exception to close ServletOutputStream in doPost(req,res) "+e);
				}
			}
		}
	}
}
