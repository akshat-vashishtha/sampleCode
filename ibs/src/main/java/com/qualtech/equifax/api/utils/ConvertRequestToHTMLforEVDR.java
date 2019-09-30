package com.qualtech.equifax.api.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConvertRequestToHTMLforEVDR 
{
	public void insertCorrectDatainRequest(HttpServletRequest request,HttpServletResponse response)
	{
        try
        {
        	String responseString=request.getAttribute("serviceResponse").toString();
        	
//        	System.out.println("The response String is"+responseString);
        	JSONObject responseJSON=new JSONObject(responseString);
        	JSONObject transaction=responseJSON.getJSONObject("payload").getJSONObject("transaction"); 
        	JSONObject enquiryResponse=transaction;//transaction.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse");
        	
        	
        	JSONArray addressesjson= enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONArray("addressinfo");
        	request.setAttribute("addressinfo", addressesjson);
        	
        	
        	JSONObject personalInfo= enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("personalinfo");
        	
        	request.setAttribute("personalInfo", personalInfo);
        	
            JSONObject identityinfo= enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("identityinfo");
        	
        	request.setAttribute("identityinfo", identityinfo);
        	
        	JSONArray phoneinfo= enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONArray("phoneinfo");
         	 request.setAttribute("phoneinfo", phoneinfo);
         	 
         	JSONArray emailaddressinfo=null;;
         	try{
         		  emailaddressinfo=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("emailaddressinfo"));
         	}catch(Exception ee){
         		try{
         		emailaddressinfo= enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONArray("emailaddressinfo");
         		}catch(Exception eee){emailaddressinfo=null;}
         	}
         	request.setAttribute("emailaddressinfo", emailaddressinfo);
         	 
         	JSONArray nsdldataA=null;
        	try
        	{
        		nsdldataA=enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONArray("vidnsdlresponses");
        	}
        	catch(Exception ee)
        	{
        		try
        		{
        			nsdldataA=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONObject("vidnsdlresponses"));;
        		}
        		catch(Exception ex)
        		{
        			nsdldataA=null;
        		}
        	}
        	request.setAttribute("nsdldata", nsdldataA);
        	
        	
        	
        	JSONObject voterdata=enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONObject("vidvoterresponses");
        	request.setAttribute("voterdata", voterdata);
        	
        	String disclaimer=enquiryResponse.getJSONObject("reportdata").get("disclaimer")+"";
        	request.setAttribute("disclaimer", disclaimer);
        	
        	request.setAttribute("responsetransaction", enquiryResponse.get("inquiryresponseheader")+"");
        	request.setAttribute("inquiryresponseheader", enquiryResponse.get("inquiryresponseheader")+"");
        	
        
        	
        }
        catch(Exception  ec)
        {
        	ec.printStackTrace();
        }
	}
}
