package com.qualtech.cibil.api.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibil.api.service.CibilMVCService;
import com.qualtech.cibil.api.utils.CibilConvertRequestToHTMLForPCS;
import com.qualtech.cibil.api.utils.Commons;
import com.qualtech.cibil.api.utils.XTrustProvider;

public class CibilMVCServiceImpl implements CibilMVCService
{
	private static Logger logger = Logger.getLogger(CibilMVCServiceImpl.class);
	@Autowired PropertyFile env;
	@SuppressWarnings({ "unused", "static-access" })
	public String doPostforCibilMVC(HttpServletRequest request, HttpServletResponse response) 
	{
		String Response = "";
		String responseFromService=request.getParameter("responseFromService");
		if(responseFromService!=null && !"".equals(responseFromService) &&  responseFromService.length()>10)
		{
			try
			{
			JSONObject payloadObj=new JSONObject(responseFromService+"").getJSONObject("payload");
			request.setAttribute("payload", payloadObj);
			JSONArray telephones=payloadObj.getJSONArray("telephones");
			request.setAttribute("telephones", telephones);
			JSONArray addresses=payloadObj.getJSONArray("addresses");
			request.setAttribute("addresses", addresses);
			JSONArray accountDetails=payloadObj.getJSONArray("accountDetails");
			request.setAttribute("accountDetails", accountDetails);
			JSONArray enquiries=payloadObj.getJSONArray("enquiries");
			request.setAttribute("enquiries", enquiries);

			//////////////////////******************* for output to Cibil*******************////////////////////////////////////

			request.setAttribute("responseFromService", responseFromService);
			new CibilConvertRequestToHTMLForPCS().insertCorrectDatainRequest(request, response);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilMVCServiceImpl || doPostforCibilMVC() || PDF Creation "+ec);
			}
			Response = "cibilResportsPage";
		}
		else
		{
		try 
		{
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String servername = request.getParameter("ServerPath");
			//String strURL = env.getString("com.qc.cibil.liveURL") + "/ib/cibil/api/v1/cibilRequestwithoutpdf";
			String strURL = env.getString("com.qc.cibil.liveURL") + "/ib/cibil/api/v1/cibilRequest";
			logger.info(" URL  to hit is: "+strURL);
			String param = null;
			String requestJson = request.getParameter("requestJson");
			if(requestJson==null){
				requestJson=CibilApiServiceImpl.tempRequestJson;
			}
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
					//            List uriResult=  URLEncodedUtils.parse(breader.readLine(), Charset.defaultCharset());
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest=uriResult.get(0).toString();
					StringTokenizer stk=new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param=stk.nextToken();
				}
				catch(Exception ec)
				{
					logger.error("Exception in CibilMVCServiceImpl || doPostforCibilMVC() || accesing input Stream "+ec);
				}
			}
			//			JSONObject requestJsonData = new JSONObject(param);
			//			JSONObject payload = new JSONObject(requestJsonData.get("payload").toString());
			//			JSONArray transaction = new JSONArray(payload.get("transaction").toString());
			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			/*
			 * for enable disable development mode String DevMode =
			 * env.getProperty("spring.enable.proxy.development"); Proxy proxy;
			 * if(DevMode!=null && !DevMode.equalsIgnoreCase("") &&
			 * DevMode.equalsIgnoreCase("Y")) {
			 * logger.info("We are running in Development Mode So Proxy Enabled"
			 * ); proxy = new Proxy(Proxy.Type.HTTP, new
			 * InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			 * conn = (HttpURLConnection) url.openConnection(proxy); } else {
			 * conn = (HttpURLConnection) url.openConnection(); }
			 */
			httpsConnection = (HttpURLConnection) url.openConnection();
			httpsConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type","application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("CibilMVCServiceImpl || doPostforCibilMVC() || Https Connection content type -->" + httpsConnection.getContentType());
			logger.info("CibilMVCServiceImpl || doPostforCibilMVC() || Https status code " + httpsConnection.getResponseCode());
			try {
				InputStream inputStream = httpsConnection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

				//	BufferedReader inputStream = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(strURL)).openConnection()).getInputStream(), Charset.forName("UTF-8")));

				logger.info("CibilMVCServiceImpl || doPostforCibilMVC() || Input Stream :: " + inputStream.toString());
				byte[] _extData = null;
				Commons common = new Commons();
				_extData = common.getBytesFromInputStream(inputStream);
				responseFromService = new String(_extData, "UTF-8");
				try {
					//////////////////////******************* for output to Cibil*******************////////////////////////////////////
					JSONObject payloadObj=new JSONObject(responseFromService+"").getJSONObject("payload");
					request.setAttribute("payload", payloadObj);
					JSONArray telephones=payloadObj.getJSONArray("telephones");
					request.setAttribute("telephones", telephones);
					JSONArray addresses=payloadObj.getJSONArray("addresses");
					request.setAttribute("addresses", addresses);
					JSONArray accountDetails=payloadObj.getJSONArray("accountDetails");
					request.setAttribute("accountDetails", accountDetails);
					JSONArray enquiries=payloadObj.getJSONArray("enquiries");
					request.setAttribute("enquiries", enquiries);

					//////////////////////******************* for output to Cibil*******************////////////////////////////////////

					request.setAttribute("responseFromService", responseFromService);
					new CibilConvertRequestToHTMLForPCS().insertCorrectDatainRequest(request, response);
					Response = "cibilResportsPage";
				} catch (Exception e) {
					logger.error("Exception in CibilMVCServiceImpl || doPostforCibilMVC() || setting response "+e);
				}
			} catch (IOException io) {
				io.printStackTrace();

				logger.error("Exception in CibilMVCServiceImpl || doPostforCibilMVC() || accesing input Stream "+io);
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());

			}
			httpsConnection.disconnect();
		} catch (Exception ec) {
			logger.error("CibilMVCServiceImpl || doPostforCibilMVC() || Exception Occurs :: "+ec);
		}
		}
		return Response;
	}




}
