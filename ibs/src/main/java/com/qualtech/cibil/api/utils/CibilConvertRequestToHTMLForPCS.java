package com.qualtech.cibil.api.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONObject;
public class CibilConvertRequestToHTMLForPCS implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CibilConvertRequestToHTMLForPCS.class);
	public void  insertCorrectDatainRequest(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			String requestJson = request.getParameter("requestJson");
			if (requestJson == null)
			{
				try {
					InputStream ins = request.getInputStream();
					InputStreamReader insreader = new InputStreamReader(ins);
					BufferedReader breader = new BufferedReader(insreader);
					//					List uriResult = URLEncodedUtils.parse(breader.readLine(), Charset.defaultCharset());
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest = uriResult.get(0).toString();
					StringTokenizer stk = new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					requestJson = stk.nextToken();
				} catch (Exception ec) {
					logger.error(" Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || accessing Input Stream " + ec);
				}
			}

			JSONObject requestJsonData=new JSONObject(requestJson);
			JSONArray transaction=new JSONArray(new JSONObject(requestJsonData.get("payload").toString()).get("transaction").toString());
			JSONObject GeneralUserInfo=new JSONObject(transaction.get(0).toString());
			request.setAttribute("GeneralUserInfo", GeneralUserInfo);
			String finalResponseJson=request.getAttribute("responseFromService").toString();
			JSONObject mainJSOnObject=new JSONObject(finalResponseJson);
			JSONObject responsetransaction=new JSONObject( new JSONObject(mainJSOnObject.get("payload").toString()).get("transaction").toString());
//			JSONObject responsetransaction=new JSONObject(mainJSOnObject.get("payload").toString());
			request.setAttribute("responsetransaction", responsetransaction);
			//for getting score//
			try
			{
				JSONObject score=responsetransaction.getJSONObject("score");
				request.setAttribute("score", score);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || setting score :: "+ec);
			}
			//for getting score//

			//for getting Account Details //
			try
			{
				JSONArray accounts=new JSONArray(responsetransaction.getJSONObject("accountdetails").get("account").toString());
				request.setAttribute("account", accounts);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || setting accounts :: "+ec);
			}

			//for getting Account Details//

			//for getting Account Summary
			try
			{
				JSONObject accountSuymmary=responsetransaction.getJSONObject("accountsummary");
				request.setAttribute("accountsummary", accountSuymmary);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || setting account summary :: "+ec);
			}
			//for getting account summary

			//for getting Enquiries
			try
			{
				JSONArray enquiries=responsetransaction.getJSONArray("enquiries");
				request.setAttribute("enquiries", enquiries);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || setting enquiries :: "+ec);
			}

			//for getting enquiries

			//for Getting Enquiry Summary
			try
			{
				JSONObject enquirysummary=responsetransaction.getJSONObject("enquirysummary");
				request.setAttribute("enquirysummary", enquirysummary);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || setting enquiry summary :: "+ec);
			}

			//for Getting enquiry Summary

			//for Id and Contact Info Variations
			try
			{
				JSONObject idandcontactinfo=responsetransaction.getJSONObject("idandcontactinfo");
				request.setAttribute("idandcontactinfo", idandcontactinfo);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || setting id and contact info :: "+ec);
			}


			//for Id and Contact info variations

			//for getting Disclaimer
			try
			{
				String disclaimer=responsetransaction.get("disclaimer").toString();
				request.setAttribute("disclaimer", disclaimer);
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || setting disclaimer :: "+ec);
			}


			//for Getting Disclaimer


			//for scoring Elements

			JSONArray scoringElements=responsetransaction.getJSONObject("scoringelements").getJSONArray("scoringelement");
			request.setAttribute("scoringelements", scoringElements);


			//for scoring Elements

			/////////////////////////////////////////////Other key Ind Type///////////////////////////////////////////

			JSONObject otherkeyInd= responsetransaction.getJSONObject("otherkeyind");
			request.setAttribute("otherkeyind", otherkeyInd);

			/////////////////////////////////////////////Other key Ind Type////////////////////////////////////////////


			////////////////////////////////////////////recent activities////////////////////////////////////////////
			JSONObject recentActivity= responsetransaction.getJSONObject("recentactivities");
			request.setAttribute("recentactivities", recentActivity);

			////////////////////////////////////////////recent activities////////////////////////////////////////////


		}
		catch(Exception ec)
		{
			logger.error("CibilConvertRequestToHTMLForPCS || insertCorrectDatainRequest() || accesing Request || Exception :: "+ec);
		}

	}



}
