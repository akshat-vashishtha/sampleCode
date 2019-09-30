package com.qualtech.creditvidya.api.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.creditvidya.api.common.dto.EmailSaveRequest;
import com.qualtech.creditvidya.api.common.dto.EmailVerificationRequest;
import com.qualtech.creditvidya.api.dbInt.DBConnectionCreditInt;
import com.qualtech.creditvidya.api.request.CreditReqRes;
import com.qualtech.creditvidya.api.request.Header;
import com.qualtech.creditvidya.api.request.HttpCallDetails;
import com.qualtech.creditvidya.api.response.EmailSaveResponse;
import com.qualtech.creditvidya.api.response.EmailSaveResponsePayload;
import com.qualtech.creditvidya.api.response.EmailVerificationPayload;
import com.qualtech.creditvidya.api.response.EmailVerificationResponse;
import com.qualtech.creditvidya.api.response.MessageInfo;
import com.qualtech.creditvidya.api.service.CreditVidyaService;
import com.qualtech.creditvidya.api.utils.StringConstants;
import com.qualtech.creditvidya.api.utils.XTrustProvider;

@Service
public class CreditVidyaServiceImpl implements CreditVidyaService {
	static Logger logger = Logger.getLogger(CreditVidyaServiceImpl.class.getName());
	
	@Autowired
	private PropertyFile resProp;
	
	@Autowired
	DBConnectionCreditInt dbconnection;

	@Override
	public EmailSaveResponse emailService(final EmailSaveRequest emailSaveRequest,CreditReqRes reqRes)
	{
		EmailSaveResponse emailSaveResponse = new EmailSaveResponse();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave=resProp.getString("com.credit.email.save.url");
		
		logger.info(" CreditVidyaServiceImpl || emailService() || STARTS ");
		
		try
		{
			if (emailSaveRequest != null)
			{
				if (emailSaveRequest.getPayload()!=null 
						&& emailSaveRequest.getPayload().getUniqueId()!=null
						&& !emailSaveRequest.getPayload().getUniqueId().equals("")
						&& emailSaveRequest.getPayload().getEmail()!=null
						&& !emailSaveRequest.getPayload().getEmail().equals("")
						&& emailSaveRequest.getPayload().getCompanyName()!=null
						&& !emailSaveRequest.getPayload().getCompanyName().equals("")
						)
				{
					
					
					if(emailSaveRequest.getPayload().getFirstName()!=null
							&& !emailSaveRequest.getPayload().getFirstName().equals("") || emailSaveRequest.getPayload().getLastName()!=null
									&& !emailSaveRequest.getPayload().getLastName().equals("")) {
						
					
				
				
				boolean result = checkValidation(emailSaveRequest.getHeader());
				if(result==true)
				{
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					ObjectMapper om = new ObjectMapper();
						
					String hardCodedEnv="N";
					ResourceBundle res=null;
					try 
					{
						res = ResourceBundle.getBundle("hardcode");
						hardCodedEnv = resProp.getString("com.ibs.allapi.demo.development");	
						logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
					}
					catch(Exception ex)
					{
						hardCodedEnv="N";
					}	
					HttpCallDetails httpCallDetails;
					if(hardCodedEnv.equalsIgnoreCase("N"))
					{
						httpCallDetails = httpCall(ow.writeValueAsString(emailSaveRequest.getPayload()), urlSave);
					}
					else
					{
						httpCallDetails = new HttpCallDetails();
						httpCallDetails.setResponse(res.getString("CREDIT_VIDYA_EMAIL_SAVE"));
						httpCallDetails.setResponseCode(200);
					}
					
					try
					{
						String price=null;
						reqRes.setIntReq(ow.writeValueAsString(emailSaveRequest.getPayload()));
						reqRes.setIntRes(httpCallDetails.getResponse());
						if (httpCallDetails.getResponseCode() == 200)
						{
							price=resProp.getString("com.credit.validation.success.price");
						}
						else 
						{
							price=resProp.getString("com.credit.validation.failure.price");
						}
						reqRes.setPrice(price);
						
					}
					catch(Exception e)
					{
						logger.error("CreditVidyaServiceImpl || emailService() || updating credit internal req res: "+e);
					}
					
					
					if(httpCallDetails.getResponseCode()==200)
					{
						emailSaveResponse.setMsgInfo(om.readValue(httpCallDetails.getResponse(), MessageInfo.class));
						msgInfo.setStatus(StringConstants.SUCCESS.toString());
						msgInfo.setMessage(StringConstants.MESSAGE200.toString());
						msgInfo.setCode(StringConstants.C_200.toString());
	                   
					}
					else
					{
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(""+StringConstants.MESSAGE500);
						msgInfo.setCode(StringConstants.C_500.toString());
						logger.info("Failure from 3rd Party API : "+httpCallDetails.getResponse());
					}
				}
				else
				{
					msgInfo.setStatus(StringConstants.FAILURE.toString());
					msgInfo.setMessage(StringConstants.MESSAGE401.toString());
					msgInfo.setCode(StringConstants.C_401.toString());
				}
			
			
				
				}else {
					
					msgInfo.setStatus(StringConstants.FAILURE.toString());
					msgInfo.setMessage(StringConstants.MESSAGE601.toString());
					msgInfo.setCode(StringConstants.C_601.toString());
					
				}
				
				
				}else {
				
				msgInfo.setStatus(StringConstants.FAILURE.toString());
				msgInfo.setMessage(StringConstants.MESSAGE601.toString());
				msgInfo.setCode(StringConstants.C_601.toString());
				
			}
			
			}
			else
			{
				msgInfo.setStatus(StringConstants.FAILURE.toString());
				msgInfo.setMessage(StringConstants.MESSAGE400.toString());
				msgInfo.setCode(StringConstants.C_400.toString());
			}
		}
		catch (Exception e) 
		{
			logger.error("CreditVidyaServiceImpl || emailService() || Exception Occurs :: " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(StringConstants.C_600.toString());
		}
		emailSaveResponse.setMsgInfo(msgInfo);
		EmailSaveResponsePayload payload = new EmailSaveResponsePayload();
		payload.setUniqueId(emailSaveRequest.getPayload().getUniqueId());
		emailSaveResponse.setPayload(payload);
		emailSaveResponse.setHeader(emailSaveRequest.getHeader());
		
		
		logger.info(" CreditVidyaServiceImpl || emailService() || ENDS ");
		return emailSaveResponse;
	}

	@Override
	public EmailVerificationResponse emailVerificationService(final EmailVerificationRequest emailVerificationRequest,CreditReqRes reqRes){
		EmailVerificationResponse emailVerificationResponse = new EmailVerificationResponse();
		MessageInfo msgInfo = new MessageInfo();
		String urlVerify=resProp.getString("com.credit.email.verification.url");
		logger.info(" CreditVidyaServiceImpl || emailVerificationResponse() || STARTS ");
		try 
		{
			if (emailVerificationRequest != null)
			{
				if (emailVerificationRequest.getPayload()!=null 
						&& emailVerificationRequest.getPayload().getUniqueId()!=null
						&& !emailVerificationRequest.getPayload().getUniqueId().equals("")
						)
				{
				
				boolean result = checkValidation(emailVerificationRequest.getHeader());
				if(result==true)
				{
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					ObjectMapper om = new ObjectMapper();
					
					String hardCodedEnv="N";
					ResourceBundle res=null;
					try 
					{
						res = ResourceBundle.getBundle("hardcode");
						hardCodedEnv = resProp.getString("com.ibs.allapi.demo.development");	
						logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
					}
					catch(Exception ex)
					{
						hardCodedEnv="N";
					}	
					HttpCallDetails httpCallDetails;
					if(hardCodedEnv.equalsIgnoreCase("N"))
					{
						httpCallDetails = httpCall(ow.writeValueAsString(emailVerificationRequest.getPayload()), urlVerify);
					}
					else
					{
						httpCallDetails = new HttpCallDetails();
						httpCallDetails.setResponse(res.getString("CREDIT_VIDYA_EMAIL_VERIFICATION"));
						httpCallDetails.setResponseCode(200);
					}
					
					try
					{
						String price=null;
					
						reqRes.setIntReq(ow.writeValueAsString(emailVerificationRequest.getPayload()));
						reqRes.setIntRes(httpCallDetails.getResponse());
						if (httpCallDetails.getResponseCode() == 200)
						{
							price=resProp.getString("com.credit.verification.success.price");
						}
						else 
						{
							price=resProp.getString("com.credit.verification.failure.price");
						}
						reqRes.setPrice(price);
						
					}
					catch(Exception e)
					{
						logger.error("CreditVidyaServiceImpl || emailVerificationResponse() || updating credit internal req res: "+e);
						
					}
					
					if(httpCallDetails.getResponseCode()==200)
					{
						String apiResponse=httpCallDetails.getResponse().replaceAll("content-type", "contentType");
						emailVerificationResponse.setPayload(om.readValue(apiResponse,EmailVerificationPayload.class));
						
						msgInfo.setStatus(StringConstants.SUCCESS.toString());
						msgInfo.setCode(StringConstants.C_200.toString());
						msgInfo.setMessage(StringConstants.MESSAGE200.toString());

					}
					else
					{
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(""+StringConstants.MESSAGE500);
						msgInfo.setCode(StringConstants.C_500.toString());
						logger.info("Failure from 3rd Party API : "+httpCallDetails.getResponse());
					}
				}
				else
				{
					msgInfo.setStatus(StringConstants.FAILURE.toString());
					msgInfo.setMessage(StringConstants.MESSAGE401.toString());
					msgInfo.setCode(StringConstants.C_401.toString());
				}
			
			
			}else {
				msgInfo.setStatus(StringConstants.FAILURE.toString());
				msgInfo.setMessage(StringConstants.MESSAGE601.toString());
				msgInfo.setCode(StringConstants.C_601.toString());
			}
			
			}
			
			else
			{
				msgInfo.setStatus(StringConstants.FAILURE.toString());
				msgInfo.setMessage(StringConstants.MESSAGE400.toString());
				msgInfo.setCode(StringConstants.C_400.toString());
			}
		}
		catch (Exception e) 
		{
			logger.error("CreditVidyaServiceImpl || emailVerificationResponse() || Exception Occurs : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(StringConstants.C_600.toString());
		}
		emailVerificationResponse.setMsgInfo(msgInfo);
		emailVerificationResponse.setHeader(emailVerificationRequest.getHeader());
		
		logger.info(" CreditVidyaServiceImpl || EmailVerificationResponse() || ENDS ");
		return emailVerificationResponse;
	}
	
	
	private boolean checkValidation(Header header) 
	{
		if (header != null)
		{
			AuthValidator auth = dbconnection.validateAuth(header);
			if (auth.getAppid() == null)
			{
				return false;
			} 
			else 
			{
				return true;
			}
		} 
		else
		{
			return true;
		}

	}

	@Override
	public HttpCallDetails httpCall(String request, String pUrl)
	{
		logger.info(" CreditVidyaServiceImpl || httpCall() || Internal Request "+request);
		
		HttpCallDetails httpCallDetails = new HttpCallDetails();
		String output = new String();
		StringBuilder result = new StringBuilder();

		try
		{
			XTrustProvider.install();
			String apiKey=resProp.getString("com.credit.email.apikey");
			String inetSocketAddress=resProp.getString("com.qc.proxy.ip");
			String port=resProp.getString("com.qc.proxy.port");
			String proxyCall=resProp.getString("spring.enable.proxy.development");
			
			URL url = new URL(pUrl);
			HttpURLConnection conn = null;
			String DevMode = proxyCall;
			if (DevMode != null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y"))
			{
				Proxy proxy = new Proxy(Proxy.Type.HTTP,
						new InetSocketAddress(inetSocketAddress, Integer.parseInt(port)));
				conn = (HttpURLConnection) url.openConnection(proxy);
			} 
			else
			{
				conn = (HttpURLConnection) url.openConnection();
			}

			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(Integer.parseInt(resProp.getString("com.qc.timeout.credit")));
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("APIKEY", apiKey);
			OutputStream os = conn.getOutputStream();
			os.write(request.getBytes());
			os.flush();
			try 
			{
				os.close();
			}
			catch (Exception e1)
			{
				logger.error("Exception in CreditVidyaServiceImpl || httpCall() || closing OutputStream : " + e1);
			}

			int apiResponseCode = conn.getResponseCode();
			logger.info("CreditVidyaServiceImpl || httpCall() || Credit vidya Internal Response code : "+apiResponseCode);
			httpCallDetails.setResponseCode(apiResponseCode);
			if (apiResponseCode == 200)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null)
				{
					result.append(output);
				}
				conn.disconnect();
				br.close();
				logger.debug(result.toString());
			}
			else
			{
				try
				{
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
					while ((output = br.readLine()) != null) 
					{
						result.append(output);
					}
					br.close();
				}
				catch(Exception ex)
				{
					logger.error("Exception in CreditVidyaServiceImpl || httpCall() || accessing input stream : "+ex);
				}
			}
		}
		catch (Exception e)
		{
			logger.error("Exception in CreditVidyaServiceImpl || httpCall() || calling API :: " + e);
		}
		httpCallDetails.setResponse(result.toString());
		logger.info("Internal Response "+httpCallDetails.getResponse());
		return httpCallDetails;
	}


}
