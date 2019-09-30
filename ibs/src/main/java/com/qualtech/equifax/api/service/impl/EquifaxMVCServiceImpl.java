package com.qualtech.equifax.api.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.equifax.api.service.EquifaxMVCService;
import com.qualtech.equifax.api.utils.Commons;
import com.qualtech.equifax.api.utils.ConvertRequestToHTMLForMFI;
import com.qualtech.equifax.api.utils.ConvertRequestToHTMLForPCS;
import com.qualtech.equifax.api.utils.ConvertRequestToHTMLforEVDR;
import com.qualtech.equifax.api.utils.XTrustProvider;

public class EquifaxMVCServiceImpl implements EquifaxMVCService {

	private static Logger logger = Logger.getLogger(EquifaxMVCServiceImpl.class);
	@Autowired PropertyFile resProp;
	@SuppressWarnings({ "static-access", "unused" })
	public String doPostforEquifaxEVDR(HttpServletRequest request, HttpServletResponse response)
	{
		String Response = "";
		String responseFromService = request.getParameter("responseFromService");
		if(responseFromService!=null && !"".equals(responseFromService) &&  !" ".equals(responseFromService))
		{
			try
			{
				request.setAttribute("serviceResponse", responseFromService);
				ConvertRequestToHTMLforEVDR requestToHTml = new ConvertRequestToHTMLforEVDR();
				requestToHTml.insertCorrectDatainRequest(request, response);
			} 
			catch (Exception e)
			{
				logger.error("We are in Exception : +e");
			}
		}
		else
		{
		try
		{
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String strURL = resProp.getString("com.qc.liveURL")+ "/ib/equifax/api/v1/equifaxrequest/enhanceVerificationId";
			String param = null;
			String requestJson = request.getParameter("requestJson");
			if (null != requestJson && !"".equals(requestJson)) 
			{
				param = requestJson;
			}
			if(param==null)
			{
				try
				{
					InputStream ins=request.getInputStream();
					InputStreamReader insreader=new InputStreamReader(ins);
					BufferedReader breader=new BufferedReader(insreader);
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest=uriResult.get(0).toString();
					StringTokenizer stk=new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param=stk.nextToken();
				}
				catch(Exception ec)
				{
					logger.error("Error in Exception "+ec);
				}
			}
			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			httpsConnection = (HttpURLConnection) url.openConnection();
			httpsConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try 
			{
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				Commons common = new Commons();
				_extData = common.getBytesFromInputStream(inputStream);
				
				responseFromService = new String(_extData, "UTF-8");
				try
				{
					request.setAttribute("serviceResponse", responseFromService);
					ConvertRequestToHTMLforEVDR requestToHTml = new ConvertRequestToHTMLforEVDR();
					requestToHTml.insertCorrectDatainRequest(request, response);
				} 
				catch (Exception e)
				{
					logger.error("We are in Exception :"+e);
				}
				
			} 
			catch (IOException io) 
			{
				io.printStackTrace();
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());
			}
			httpsConnection.disconnect();
		} catch (Exception ec) {
			logger.error("There is error in doPostforEquifaxEVDR " + ec);
		}
		}
		return Response;
	}
/*
	@SuppressWarnings({ "unused", "static-access" })
	public String doPostforEquifaxPCS(String requestJson, String responseFromService) 
	{
		String Response = "";
		//String responseFromService = request.getParameter("responseFromService");
        if(responseFromService!=null && !"".equals(responseFromService) && !" ".equals(responseFromService))
        {
        	try 
			{
				logger.info("The JSon OutPut is" + responseFromService);
				//request.setAttribute("responseFromService", responseFromService);
				new ConvertRequestToHTMLForPCS().insertCorrectDatainRequest(requestJson, responseFromService);
				Response = "EquifaxUserFriendlyOutputPCS";
			} 
			catch (Exception e)
			{
				logger.error(e);
			}
        }
        else
        {
		try 
		{
			Added By Rohit Kumar for convert 2 Request into One Starts on 19-August-2017 Starts  
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String strURL = resProp.getString("com.qc.liveURL") + "/ib/equifax/api/v1/equifaxrequest";
			String param = null;
			//String requestJson = request.getParameter("requestJson");

			if (null != requestJson && !"".equals(requestJson)) {
				param = requestJson;
			}
			if(param==null)
			{
				try
				{
					InputStream ins=request.getInputStream();
					InputStreamReader insreader=new InputStreamReader(ins);
					BufferedReader breader=new BufferedReader(insreader);
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest=uriResult.get(0).toString();
					StringTokenizer stk=new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param=stk.nextToken();
				}
				catch(Exception ec)
				{
					logger.error("Error in Exception "+ec);
				}
			}
			JSONObject requestJsonData = new JSONObject(param);
			JSONObject payload = new JSONObject(requestJsonData.get("payload").toString());
			JSONArray transaction = new JSONArray(payload.get("transaction").toString());
			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			httpsConnection = (HttpURLConnection) url.openConnection();
			httpsConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try 
			{
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				Commons common = new Commons();
				_extData = common.getBytesFromInputStream(inputStream);
				responseFromService = new String(_extData, "UTF-8");
				try 
				{
					logger.info("The JSon OutPut is" + responseFromService);
					request.setAttribute("responseFromService", responseFromService);
					new ConvertRequestToHTMLForPCS().insertCorrectDatainRequest(request, response);
					Response = "EquifaxUserFriendlyOutputPCS";
				} 
				catch (Exception e)
				{
					logger.error(e);
				}
			} 
			catch (IOException io)
			{
				io.printStackTrace();
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());
			}
			httpsConnection.disconnect();
			Added By Rohit Kumar for convert 2 Request into One Starts on 19-August-2017 Ends  	
		}
		catch (Exception ec)
		{
			logger.error("There is error in doPostforEquifaxPCS" + ec);
		}
        }
		return Response;
	}

	

*/
	@SuppressWarnings({ "unused", "static-access" })
	public String doPostforEquifaxPCS(HttpServletRequest request, HttpServletResponse response) 
	{
		String Response = "";
		String responseFromService = request.getParameter("responseFromService");
        if(responseFromService!=null && !"".equals(responseFromService) && !" ".equals(responseFromService))
        {
        	try 
			{
				logger.info("The JSon OutPut is" + responseFromService);
				request.setAttribute("responseFromService", responseFromService);
				new ConvertRequestToHTMLForPCS().insertCorrectDatainRequest(request, response);
				Response = "EquifaxUserFriendlyOutputPCS";
			} 
			catch (Exception e)
			{
				logger.error(e);
			}
        }
        else
        {
		try 
		{
			//Added By Rohit Kumar for convert 2 Request into One Starts on 19-August-2017 Starts  
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String strURL = resProp.getString("com.qc.liveURL") + "/ib/equifax/api/v1/equifaxrequest";
			String param = null;
			String requestJson = request.getParameter("requestJson");

			if (null != requestJson && !"".equals(requestJson)) {
				param = requestJson;
			}
			if(param==null)
			{
				try
				{
					InputStream ins=request.getInputStream();
					InputStreamReader insreader=new InputStreamReader(ins);
					BufferedReader breader=new BufferedReader(insreader);
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest=uriResult.get(0).toString();
					StringTokenizer stk=new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param=stk.nextToken();
				}
				catch(Exception ec)
				{
					logger.error("Error in Exception "+ec);
				}
			}
			JSONObject requestJsonData = new JSONObject(param);
			JSONObject payload = new JSONObject(requestJsonData.get("payload").toString());
			JSONArray transaction = new JSONArray(payload.get("transaction").toString());
			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			httpsConnection = (HttpURLConnection) url.openConnection();
			httpsConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try 
			{
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				Commons common = new Commons();
				_extData = common.getBytesFromInputStream(inputStream);
				responseFromService = new String(_extData, "UTF-8");
				try 
				{
					logger.info("The JSon OutPut is" + responseFromService);
					request.setAttribute("responseFromService", responseFromService);
					new ConvertRequestToHTMLForPCS().insertCorrectDatainRequest(request, response);
					Response = "EquifaxUserFriendlyOutputPCS";
				} 
				catch (Exception e)
				{
					logger.error(e);
				}
			} 
			catch (IOException io)
			{
				io.printStackTrace();
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());
			}
			httpsConnection.disconnect();
			//Added By Rohit Kumar for convert 2 Request into One Starts on 19-August-2017 Ends  	
		}
		catch (Exception ec)
		{
			logger.error("There is error in doPostforEquifaxPCS" + ec);
		}
        }
		return Response;
	}

	@SuppressWarnings({ "static-access", "unused" })
	public String doPostforEquifaxMCR(HttpServletRequest request, HttpServletResponse response) {
		String Response = "";
		String responseFromService = request.getParameter("responseFromService");
		 if(responseFromService!=null && !"".equals(responseFromService) && !" ".equals(responseFromService))
		{
			try 
			{
				logger.info("The JSon OutPut is" + responseFromService);
				request.setAttribute("responseFromService", responseFromService);
				new ConvertRequestToHTMLForMFI().insertCorrectDatainRequest(request, response);
				Response = "EquifaxUserFriendlyOutPutForMCR";
			}
			catch (Exception e)
			{
				logger.error(e);
			}
		}
		else{
		try 
		{
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String strURL = resProp.getString("com.qc.liveURL") + "/ib/equifax/api/v1/equifaxMFIrequest";
			String param = null;
			String requestJson = request.getParameter("requestJson");
			if (null != requestJson && !"".equals(requestJson)) {
				param = requestJson;
			}
			if(param==null)
			{
				try
				{
					InputStream ins=request.getInputStream();
					InputStreamReader insreader=new InputStreamReader(ins);
					BufferedReader breader=new BufferedReader(insreader);
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest=uriResult.get(0).toString();
					StringTokenizer stk=new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param=stk.nextToken();
				}
				catch(Exception ec)
				{
					logger.error("Error in Exception "+ec);
				}
			}
			JSONObject requestJsonData = new JSONObject(param);
			JSONObject payload = new JSONObject(requestJsonData.get("payload").toString());
			JSONArray transaction = new JSONArray(payload.get("transaction").toString());
			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			httpsConnection = (HttpURLConnection) url.openConnection();
			httpsConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try 
			{
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				Commons common = new Commons();
				_extData = common.getBytesFromInputStream(inputStream);
				responseFromService = new String(_extData, "UTF-8");
				try 
				{
					logger.info("The JSon OutPut is" + responseFromService);
					request.setAttribute("responseFromService", responseFromService);
					new ConvertRequestToHTMLForMFI().insertCorrectDatainRequest(request, response);
					Response = "EquifaxUserFriendlyOutPutForMCR";
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
			catch (IOException io)
			{
				io.printStackTrace();
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());

			}
			httpsConnection.disconnect();
			
		} 
		catch (Exception ec)
		{
			logger.error("There is Error in  doPostforEquifaxMCR" + ec);
		}
		}
		return Response;
	}

	@SuppressWarnings({ "static-access", "unused" })
	public String doPostforEquifaxVID(HttpServletRequest request, HttpServletResponse response) {
		String Response = "";
		String responseFromService = request.getParameter("responseFromService");
		 if(responseFromService!=null && !"".equals(responseFromService) && !" ".equals(responseFromService))
		{
			try 
			{
				logger.info("The JSon OutPut is" + responseFromService);
				JSONObject transaction = new JSONObject(responseFromService).getJSONObject("payload").getJSONObject("transaction");
				JSONObject verifyIdResponse=transaction.getJSONObject("verifyidresponse").getJSONObject("vidnsdlresponses");
				String disclaimer =transaction.get("disclaimer").toString();
				String nsdlRequest = verifyIdResponse.getJSONObject("nsdlrequest").toString();
				JSONArray nsdlResponse=null;
	        	try
	        	{
	        		nsdlResponse=transaction.getJSONObject("verifyidresponse").getJSONArray("vidnsdlresponses");
	        	}
	        	catch(Exception ee)
	        	{try
	        		{
	        			nsdlResponse=new JSONArray().put(transaction.getJSONObject("verifyidresponse").getJSONObject("vidnsdlresponses"));;
	        		}
	        		catch(Exception ex)
	        		{
	        			nsdlResponse=null;
	        		}
	        	}
	        	request.setAttribute("nsdldata", nsdlResponse);
				String inquiryresponseheader = transaction.getJSONObject("inquiryresponseheader").toString();
				request.setAttribute("disclaimer", disclaimer);
				request.setAttribute("nsdlrequest", nsdlRequest);
				request.setAttribute("inquiryresponseheader", inquiryresponseheader);
			}
			catch (Exception e)
			{
				logger.info("We are in Exception : "+e);
			}
		}
		else {
		try 
		{
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String strURL = resProp.getString("com.qc.liveURL") + "/ib/equifax/api/v1/equifaxrequest/verificationId";
			String param = null;
			String requestJson = request.getParameter("requestJson");

			if (null != requestJson && !"".equals(requestJson)) {
				param = requestJson;
			}
			if(param==null)
			{
				try
				{
					InputStream ins=request.getInputStream();
					InputStreamReader insreader=new InputStreamReader(ins);
					BufferedReader breader=new BufferedReader(insreader);
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest=uriResult.get(0).toString();
					StringTokenizer stk=new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param=stk.nextToken();
				}
				catch(Exception ec)
				{
					logger.error("Error in Exception "+ec);
				}
			}

			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			httpsConnection = (HttpURLConnection) url.openConnection();
			httpsConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try 
			{
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				Commons common = new Commons();
				_extData = common.getBytesFromInputStream(inputStream);
				responseFromService = new String(_extData, "UTF-8");
				try {
					logger.info("The JSon OutPut is" + responseFromService);
					JSONObject transaction = new JSONObject(responseFromService).getJSONObject("payload").getJSONObject("transaction");
					JSONObject verifyIdResponse=transaction.getJSONObject("verifyidresponse").getJSONObject("vidnsdlresponses");
					String disclaimer =transaction.get("disclaimer").toString();
					String nsdlRequest = verifyIdResponse.getJSONObject("nsdlrequest").toString();
					JSONArray nsdlResponse=null;
		        	try
		        	{
		        		nsdlResponse=transaction.getJSONObject("verifyidresponse").getJSONArray("vidnsdlresponses");
		        	}
		        	catch(Exception ee){
		        		try
		        		{
		        			nsdlResponse=new JSONArray().put(transaction.getJSONObject("verifyidresponse").getJSONObject("vidnsdlresponses"));;
		        		}catch(Exception ex){
		        			nsdlResponse=null;
		        		}
		        	}
		        	request.setAttribute("nsdldata", nsdlResponse);
					String inquiryresponseheader = transaction.getJSONObject("inquiryresponseheader").toString();
					request.setAttribute("disclaimer", disclaimer);
					request.setAttribute("nsdlrequest", nsdlRequest);
					request.setAttribute("inquiryresponseheader", inquiryresponseheader);
				}
				catch (Exception e)
				{
					logger.info("We are in Exception : "+e);
				}
			}
			catch (IOException io) 
			{
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());
			}
			httpsConnection.disconnect();
		} 
		catch (Exception ec) 
		{
			logger.error("There is error in doPostforEquifaxVID  " + ec);
		}
		}
		return Response;
	}
	@Override
	public void downloadrequestJsonConsumer(HttpServletRequest request,
			HttpServletResponse response) {
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			String filepath =  resProp.getString("com.qc.equifaxPCS.requestXlsFilePath");
			File file = new File(filepath);
			fileIn=new FileInputStream(file);

			response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=EquifaxPCSRequestFile.xlsx");
			out=response.getOutputStream();
			int i = 0;
			while ((i = fileIn.read()) != -1) {
				out.write(i);
			}
			
			
		} 
		catch (Exception ec) 
		{
			logger.error(ec);
		}finally {
			if(fileIn!=null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					logger.error("There is error during close FileInputStream in downloadrequestJsonConsumer()" + e);	
				}
			}
            if(out!=null) {
            	try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.error("There is error during close ServletOutputStream in downloadrequestJsonConsumer() " + e);	
				}
    			
			}
		}
	}
	@Override
	public void downloadrequestJsonEVDR(HttpServletRequest request,
			HttpServletResponse response) {
		FileInputStream fileIn = null;
		ServletOutputStream out =null;
		try {
			String filepath =  resProp.getString("com.qc.equifaxEVDR.requestXlsFilePath");
			File file = new File(filepath);
			fileIn=new FileInputStream(file);
			response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=EquifaxEVDRRequestFile.xlsx");
			out= response.getOutputStream();
			int i = 0;
			while ((i = fileIn.read()) != -1)
			{
				out.write(i);
			}
		}
		catch (Exception ec) 
		{
			logger.error(ec);
		}finally {
			if(fileIn!=null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					logger.error("There is error during close FileInputStream in downloadrequestJsonEVDR()" + e);	
				}
			}
            if(out!=null) {
            	try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.error("There is error during close  ServletOutputStream in downloadrequestJsonEVDR() " + e);	
				}
    			
			}
		}
	}
	@Override
	public void downloadrequestJsonMFI(HttpServletRequest request,
			HttpServletResponse response) {
		FileInputStream fileIn = null;
		ServletOutputStream out =null;
		try 
		{
			String filepath =  resProp.getString("com.qc.equifaxMCR.requestXlsFilePath");
			File file = new File(filepath);
			fileIn=new FileInputStream(file);

			response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=EquifaxMCRRequestFile.xlsx");
			out= response.getOutputStream();
			int i = 0;
			while ((i = fileIn.read()) != -1) {
				out.write(i);
			}
		} 
		catch (Exception ec)
		{
			logger.error(ec);
		}
		finally {
			if(fileIn!=null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					logger.error("There is error during close FileInputStream" + e);	
				}
			}
            if(out!=null) {
            	try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.error("There is error during close  ServletOutputStream  " + e);	
				}
    			
			}
		}
	}

	@Override
	public void downloadrequestJsonVID(HttpServletRequest request,
			HttpServletResponse response) {
		FileInputStream fileIn =null;
		ServletOutputStream out = null;
		try {
			String filepath = resProp.getString("com.qc.equifaxVID.requestXlsFilePath");
			File file = new File(filepath);
			fileIn= new FileInputStream(file);
			response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=EquifaxVIDRequestFile.xlsx");
			out=response.getOutputStream();
			int i = 0;
			while ((i = fileIn.read()) != -1) {
				out.write(i);
			}
		} catch (Exception ec) {
			logger.error(ec);
		}
		finally {
			if(fileIn!=null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					logger.error("There is error during close FileInputStream" + e);	
				}
			}
            if(out!=null) {
            	try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.error("There is error during close  ServletOutputStream  " + e);	
				}
    			
			}
		}
	}
}
