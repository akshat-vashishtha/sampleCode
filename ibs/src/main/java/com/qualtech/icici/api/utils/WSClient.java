package com.qualtech.icici.api.utils;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import javax.net.ssl.SSLContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.qualtech.icici.api.response.IciciInfo;

public class WSClient {

	private static final Logger logger = Logger.getLogger(WSClient.class.getName());
	
	public IciciInfo callSoapService(String xmlInput,String url,String contentType)
	{  
		IciciInfo info=new IciciInfo();
		String readResponse="";
		try
		{
			SSLContext context = SSLContext.getInstance("TLSv1.2");
			context.init(null, null, new java.security.SecureRandom());
			SSLContext.setDefault(context );
		}
		catch(Exception e)
		{
			
			logger.error("WSClient | Error in setting TLSv2 : "+e);
		}
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter("http.useragent","Web Service Test Client");
		BufferedReader br = null;

		PostMethod methodPost = new PostMethod(url);
		methodPost.setRequestBody(xmlInput);
		methodPost.setRequestHeader("Content-Type",contentType);
		methodPost.setRequestHeader("Accept-Encoding","deflate");
	   // methodPost.setRequestHeader("SOAPAction",SOAPAction);
		methodPost.setRequestHeader("User-Agent","Apache-HttpClient/4.1.1");
		try
		{
			int returnCode = httpClient.executeMethod(methodPost);
			 
			info.setResponseCode(returnCode);
			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) 
			{
				logger.error("WSClient | error in calling soap ICICI service " + returnCode);
				methodPost.getResponseBodyAsString();
			}
			else
			{
				br = new BufferedReader(new InputStreamReader(methodPost.getResponseBodyAsStream()));
				String read = "";
				while (((read = br.readLine()) != null)) 
				{
					readResponse+=read;
				}
			}
		} 
		catch (Exception e) 
		{  
			logger.error("WSClient | error in calling soap service" + e);
		} 
		finally
		{
			try 
			{

				if (br != null) {          
					br.close();
				}
				methodPost.releaseConnection();
			     
				
			}
			catch (Exception fe)
			{  
				logger.error("WSClient | error in calling soap Kotak service" + fe);
			}
		}
		logger.info("Soap Request :: "+xmlInput);
		logger.info("Soap Response :: "+readResponse);
		info.setResponse(readResponse);
		return info;
	}
}
