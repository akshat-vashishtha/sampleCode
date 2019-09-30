package com.qualtech.experiankickoff.api.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.experiankickoff.api.common.dto.ERRORSTATUS;
import com.qualtech.experiankickoff.api.common.dto.ErrorInfo;
import com.qualtech.experiankickoff.api.common.dto.ExperianRequest;
import com.qualtech.experiankickoff.api.common.dto.MaskExperianRequest;
import com.qualtech.experiankickoff.api.db.DBConnection;
import com.qualtech.experiankickoff.api.request.AuthExperianRequest;
import com.qualtech.experiankickoff.api.request.ExperianGenQuestionRequest;
import com.qualtech.experiankickoff.api.request.ExperianKickoffReqRes;
import com.qualtech.experiankickoff.api.request.Header;
import com.qualtech.experiankickoff.api.request.HttpCallDetails;
import com.qualtech.experiankickoff.api.response.AuthExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianAuthResPayload;
import com.qualtech.experiankickoff.api.response.ExperianAuthResPayloadResult;
import com.qualtech.experiankickoff.api.response.ExperianGenQuestionResponse;
import com.qualtech.experiankickoff.api.response.ExperianGenResPayloadResult;
import com.qualtech.experiankickoff.api.response.ExperianGenResponsePayload;
import com.qualtech.experiankickoff.api.response.ExperianMaskResPayloadResult;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponse;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponsePayload;
import com.qualtech.experiankickoff.api.response.ExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianResponsePayload;
import com.qualtech.experiankickoff.api.response.ExperianResponseResultBlock;
import com.qualtech.experiankickoff.api.service.ExperianService;
import com.qualtech.experiankickoff.api.utils.StringConstants;

@Service
public class ExperianKickOffServiceImpl implements ExperianService {

	private static Logger logger = Logger.getLogger(ExperianKickOffServiceImpl.class);
	
	@Autowired
	private PropertyFile resProp;
	@Autowired
	private DBConnection dbConnection;
	

	@Override
	public ExperianResponse experianrequestService(ExperianRequest experianRequest,ExperianKickoffReqRes reqRes) {
		ExperianResponse  expResponse  = new ExperianResponse();
		ExperianResponsePayload payload=new ExperianResponsePayload();
		HttpCallDetails httpCallDetails =new HttpCallDetails();
		ErrorInfo errorInfo =  new ErrorInfo();
		ObjectMapper om = new ObjectMapper();
		String price=null;
		String url=resProp.getString("com.kickoff.singleaction.url");
		
		logger.info("Inside Service Experian KickOff Single Action.");
		
		try
		{
			if (experianRequest != null && experianRequest.getPayload() != null ) {

				
					boolean result = checkValidation(experianRequest.getHeader());
					if (result == true ) 
					{
						try
						{
							reqRes.setInternal_req(experianRequest.getPayload().asFormData());

						} 
						catch (Exception e) 
						{
							logger.error("ExperianKickOffServiceImpl || experianrequestService() || Error while setting Experian KickOff Single Action internal req:" + e);
						}
						errorInfo=checkRequestValidation(experianRequest);
						if(errorInfo==null)
						{
								
							errorInfo= new ErrorInfo();
	
							String hardCodedEnv="N";
							ResourceBundle res=null;
							try 
							{
								res = ResourceBundle.getBundle("hardcode");
								hardCodedEnv = resProp.getString("com.Kickoff.experianSingleAction.demo.development");	
								logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
							}
							catch(Exception ex)
							{
								hardCodedEnv="N";
							}
							
							if(hardCodedEnv.equalsIgnoreCase("N"))
							{
								httpCallDetails = httpCall(experianRequest.getPayload().asFormData(), url, resProp.getString("com.kickoff.contenttype"));	
							}
							else
							{
								httpCallDetails.setResponse(res.getString("EXPERIAN_KICK_OFF_SINGLE_ACTION"));
								httpCallDetails.setResponseCode(200);
								httpCallDetails.setSessionId("hardcodedid");
							}
							
							expResponse.setSessionId(httpCallDetails.getSessionId());
	
							try 
							{
								reqRes.setInternal_res(httpCallDetails.getResponse());
							}
							catch (Exception e)
							{
								logger.error("ExperianKickOffServiceImpl || experianrequestService() || Error while setting  Experian KickOff Single Action internal  res:" + e);
							}
	
							if (httpCallDetails.getResponseCode() == 200)
							{
								
								
								String str =httpCallDetails.getResponse();
								
								int firstIndex=str.indexOf(",\"showHtmlReportForCreditReport\":")+33;
								int lastIndex=str.indexOf(",\"newUserId\":");
								String string1=str.substring(0,firstIndex);
								String string2=str.substring(firstIndex,lastIndex);
								
								String string3= str.substring(lastIndex);
								
								String finalResponse=string1+"null"+string3;
								payload.setResult(om.readValue(finalResponse, ExperianResponseResultBlock.class));
								if(!string2.equals("null"))
									payload.getResult().setShowHtmlReportForCreditReport(string2);
								expResponse.setPayload(payload);
								
								
								if(payload.getResult().getShowHtmlReportForCreditReport()!=null){
									price=resProp.getString("com.kickoff.singleaction.success.price");
									errorInfo.setStatus(ERRORSTATUS.SUCCESS);
									errorInfo.setCode(StringConstants.C_200.toString());
									errorInfo.setMessage(StringConstants.MESSAGE200.toString());
									errorInfo.setDescription(StringConstants.MESSAGE200.toString());
								}else{
									price=resProp.getString("com.kickoff.singleaction.failure.price");
									errorInfo.setStatus(ERRORSTATUS.FAILURE);
									errorInfo.setCode(StringConstants.C_101.toString());
									errorInfo.setMessage(StringConstants.MESSAGE101.toString());
									errorInfo.setDescription(expResponse.getPayload().getResult().getErrorString());
								}
								
								
								expResponse.setErrorInfo(errorInfo);
								expResponse.setHeader(experianRequest.getHeader());
								
								if (!httpCallDetails.getResponse().toString().contains("consumer record not found"))
								{
	
									if (!httpCallDetails.getResponse().toString().contains("Email Validation Failed or phone Validation Failed. Please try to invoke CRQ externally"))
									{
										if (!httpCallDetails.getResponse().toString().contains("The server did not respond"))
										{
											if (!httpCallDetails.getResponse().toString().contains("Validation with Experian Failed"))
											{
												if (!httpCallDetails.getResponse().toString().contains("Voucher Code is invalid"))
												{
				
												} else {
													errorInfo.setStatus(ERRORSTATUS.FAILURE);
													errorInfo.setCode(StringConstants.C_101.toString());
													errorInfo.setMessage(StringConstants.MESSAGE101.toString());
													errorInfo.setDescription(expResponse.getPayload().getResult().getErrorString());
													logger.error(expResponse.getPayload().getResult().getErrorString());
												}
			
											} else {
												errorInfo.setStatus(ERRORSTATUS.FAILURE);
												errorInfo.setCode(StringConstants.C_101.toString());
												errorInfo.setMessage(StringConstants.MESSAGE101.toString());
												errorInfo.setDescription(expResponse.getPayload().getResult().getErrorString());
												logger.error(expResponse.getPayload().getResult().getErrorString());
											}
		
										} else {
											errorInfo.setStatus(ERRORSTATUS.FAILURE);
											errorInfo.setCode(StringConstants.C_101.toString());
											errorInfo.setMessage(StringConstants.MESSAGE101.toString());
											errorInfo.setDescription(expResponse.getPayload().getResult().getErrorString());
											logger.error(expResponse.getPayload().getResult().getErrorString());
										}
	
									} else {
										errorInfo.setStatus(ERRORSTATUS.FAILURE);
										errorInfo.setCode(StringConstants.C_101.toString());
										errorInfo.setMessage(StringConstants.MESSAGE101.toString());
										errorInfo.setDescription(expResponse.getPayload().getResult().getErrorString());
										logger.error(expResponse.getPayload().getResult().getErrorString());
									}
								}
								else {
									errorInfo.setStatus(ERRORSTATUS.FAILURE);
									errorInfo.setCode(StringConstants.C_101.toString());
									errorInfo.setMessage(StringConstants.MESSAGE101.toString());
									errorInfo.setDescription(expResponse.getPayload().getResult().getErrorString());
									logger.error(expResponse.getPayload().getResult().getErrorString());
									
								}
							}else {
								
								errorInfo.setCode(StringConstants.C_500.toString());
								errorInfo.setStatus(ERRORSTATUS.FAILURE);
								errorInfo.setMessage(StringConstants.MESSAGE500.toString());
								errorInfo.setDescription(StringConstants.MESSAGE500.toString());
								logger.error("ExperianKickOffServiceImpl || experianrequestService() || Error while creating OutPut from calling API");
							}
					}else {
						/*errorInfo.setCode(StringConstants.C_400.toString());
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setMessage(StringConstants.MESSAGE400.toString());
						errorInfo.setDescription(StringConstants.MESSAGE400.toString());*/
						logger.error("ExperianKickOffServiceImpl || experianrequestService() || Bad Request :: "+errorInfo.getDescription());
						
					}
					} else {
						errorInfo.setCode(StringConstants.C_400.toString());
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setMessage(StringConstants.MESSAGE400.toString());
						errorInfo.setDescription(StringConstants.MESSAGE400.toString());
						logger.error(errorInfo.getDescription());
					}
				
			} else {
				errorInfo.setCode("400");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Bad Request");
				errorInfo.setDescription("Bad Request");
				logger.error("ExperianKickOffServiceImpl || experianrequestService() || Bad Request from Header authentication ");
			}
		
		}
		catch (Exception e) 
		{
			logger.error("ExperianKickOffServiceImpl || experianrequestService() || Exception while processing : " + e);
			errorInfo.setCode(StringConstants.C_600.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE600.toString());
			errorInfo.setDescription(StringConstants.MESSAGE600.toString());
		}
		expResponse.setErrorInfo(errorInfo);
		reqRes.setPrice(price);
		expResponse.setHeader(experianRequest.getHeader());
		return expResponse;
	}
	
	@Override
	public ExperianMaskResponse experianMaskService(MaskExperianRequest experianRequest,ExperianKickoffReqRes reqRes) {
		ExperianMaskResponse  expMaskResponse  = new ExperianMaskResponse();
		ExperianMaskResponsePayload payload=new ExperianMaskResponsePayload();
		HttpCallDetails httpCallDetails =new HttpCallDetails();
		ErrorInfo errorInfo = new ErrorInfo();
		String price=null;
		String url=resProp.getString("com.kickoff.maskeddata.url");
		
		logger.info("Inside Service ExperianMaskService.");
		try
		{
			if (experianRequest != null && experianRequest.getPayload() != null ) {

				
					boolean result = checkValidation(experianRequest.getHeader());
					if (result == true 
							&& experianRequest.getPayload().getStgOneHitId()!=null && !experianRequest.getPayload().getStgOneHitId().equals("")
							&& experianRequest.getPayload().getStgTwoHitId()!=null && !experianRequest.getPayload().getStgTwoHitId().equals("")
							&& experianRequest.getPayload().getClientName()!=null && !experianRequest.getPayload().getClientName().equals("")) {
						try {
							reqRes.setInternal_req(experianRequest.getPayload().asFormData());

						} catch (Exception e) {
							logger.error("ExperianKickOffServiceImpl || experianMaskService() || Error while setting Experian KickOff MaskService internal req:" + e);
						}
						
						
							httpCallDetails = httpCall(experianRequest.getPayload().asFormData(), url, resProp.getString("com.kickoff.contenttype"));
	
							try {
								reqRes.setInternal_res(httpCallDetails.getResponse());
	
							} catch (Exception e) {
								logger.error("ExperianKickOffServiceImpl || experianMaskService() || Error while setting  Experian KickOff MaskService internal  res:" + e);
							}
	
							if (httpCallDetails.getResponseCode() == 200)
							{
								
								ObjectMapper om=new ObjectMapper();
								payload.setResult(om.readValue(httpCallDetails.getResponse(), ExperianMaskResPayloadResult.class));
								expMaskResponse.setPayload(payload);
								if(expMaskResponse.getPayload().getResult().getMaskedEmailADDR()!=null || expMaskResponse.getPayload().getResult().getMaskMobileno()!=null){
									price=resProp.getString("com.kickoff.maskeddata.success.price");
									errorInfo.setCode(StringConstants.C_200.toString());
									errorInfo.setStatus(ERRORSTATUS.SUCCESS);
									errorInfo.setMessage(StringConstants.MESSAGE200.toString());
									errorInfo.setDescription(StringConstants.MESSAGE200.toString());
								}else{
									price=resProp.getString("com.kickoff.maskeddata.failure.price");
									errorInfo.setCode(StringConstants.C_101.toString());
									errorInfo.setStatus(ERRORSTATUS.FAILURE);
									errorInfo.setMessage(StringConstants.MESSAGE101.toString());
									errorInfo.setDescription(expMaskResponse.getPayload().getResult().getResponseJson());
								}
								
								
							}else {
								
								errorInfo.setCode(StringConstants.C_500.toString());
								errorInfo.setStatus(ERRORSTATUS.FAILURE);
								errorInfo.setMessage(StringConstants.MESSAGE500.toString());
								errorInfo.setDescription(StringConstants.MESSAGE500.toString());
								logger.error(" ExperianKickOffServiceImpl || experianMaskService() || Error while creating OutPut from calling API");
							}
				
					} else {
						errorInfo.setCode(StringConstants.C_400.toString());
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setMessage(StringConstants.MESSAGE400.toString());
						errorInfo.setDescription(StringConstants.MESSAGE400.toString());
					}
				
			} else {
				errorInfo.setCode("400");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Bad Request");
				errorInfo.setDescription("Bad Request");
			}
		
		}
		catch (Exception e) 
		{
			logger.error("ExperianKickOffServiceImpl || experianMaskService() || Exception while processing : " + e);
			errorInfo.setCode(StringConstants.C_600.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE600.toString());
			errorInfo.setDescription(StringConstants.MESSAGE600.toString());
		}
		expMaskResponse.setErrorInfo(errorInfo);
		reqRes.setPrice(price);
		expMaskResponse.setHeader(experianRequest.getHeader());
		return expMaskResponse;

	}
	

	@Override
	public AuthExperianResponse experianAuthService(AuthExperianRequest experianRequest, ExperianKickoffReqRes reqRes) {

		AuthExperianResponse  expAuthResponse  = new AuthExperianResponse();
		ExperianAuthResPayload payload=new ExperianAuthResPayload();
		
		ErrorInfo errorInfo = new ErrorInfo();
		String url=resProp.getString("com.kickoff.authdelivery.url");
		String price=null;
		logger.info("Inside Service ExperianAuthService.");
		
		try
		{
			if (experianRequest != null && experianRequest.getPayload()!=null)
			{
				boolean result = checkValidation(experianRequest.getHeader());
				
				if(result==true
						&& experianRequest.getPayload().getStgOneHitId()!=null && !experianRequest.getPayload().getStgOneHitId().equals("")
						&& experianRequest.getPayload().getStgTwoHitId()!=null && !experianRequest.getPayload().getStgTwoHitId().equals("")
						&& experianRequest.getPayload().getSingleActionSessionId()!=null && !experianRequest.getPayload().getSingleActionSessionId().equals("")
						&& ((experianRequest.getPayload().getActualMobileNumber()!=null && !experianRequest.getPayload().getActualMobileNumber().equals(""))
								|| ( experianRequest.getPayload().getActualEmailADDR()!=null && !experianRequest.getPayload().getActualEmailADDR().equals(""))))
				{
					ObjectMapper om = new ObjectMapper();
							
					HttpCallDetails httpCallDetails = httpCall(experianRequest.getPayload().asFormData(), url, resProp.getString("com.kickoff.contenttype"));
					try
					{
						
						reqRes.setInternal_req(experianRequest.getPayload().asFormData());
						reqRes.setInternal_res(httpCallDetails.getResponse());
						
					}
					catch(Exception e)
					{
						logger.error("ExperianKickOffServiceImpl || experianAuthService() || Error while seting Experian KickOff AuthService internal req res:"+e);
					}
					
					
					
					if(httpCallDetails.getResponseCode()==200)
					{
						payload.setResult(om.readValue(httpCallDetails.getResponse(), ExperianAuthResPayloadResult.class));
						expAuthResponse.setPayload(payload);
						
						if(expAuthResponse.getPayload().getResult().getShowHtmlReportForCreditReport()!=null){
							price=resProp.getString("com.kickoff.authdelivery.success.price");
							errorInfo.setCode(StringConstants.C_200.toString());
							errorInfo.setStatus(ERRORSTATUS.SUCCESS);
							errorInfo.setMessage(StringConstants.MESSAGE200.toString());
							errorInfo.setDescription(StringConstants.MESSAGE200.toString());
						}else{
							price=resProp.getString("com.kickoff.authdelivery.failure.price");
							errorInfo.setCode(StringConstants.C_101.toString());
							errorInfo.setStatus(ERRORSTATUS.FAILURE);
							errorInfo.setMessage(StringConstants.MESSAGE101.toString());
							errorInfo.setDescription(expAuthResponse.getPayload().getResult().getResponseJson());
						}
						
	                  
					}
					else
					{
						errorInfo.setCode(StringConstants.C_500.toString());
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setMessage(StringConstants.MESSAGE500.toString());
						errorInfo.setDescription(StringConstants.MESSAGE500.toString());
						logger.error("ExperianKickOffServiceImpl || experianAuthService() || Error while creating OutPut from calling API");
					
					}
				}
				else
				{
					errorInfo.setCode(StringConstants.C_400.toString());
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage(StringConstants.MESSAGE400.toString());
					errorInfo.setDescription(StringConstants.MESSAGE400.toString());
				
				}
			
			}else
			{
				errorInfo.setCode("400");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Bad Request");
				errorInfo.setDescription("Bad Request");
			
			}
		}
		catch (Exception e) 
		{
			logger.error("We are in Exception while processing : " + e);
			errorInfo.setCode(StringConstants.C_600.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE600.toString());
			errorInfo.setDescription(StringConstants.MESSAGE600.toString());
		}
		
		expAuthResponse.setErrorInfo(errorInfo);
		reqRes.setPrice(price);
		expAuthResponse.setHeader(experianRequest.getHeader());
		return expAuthResponse;
	
	}
	
	@Override
	public ExperianGenQuestionResponse experianGenService(ExperianGenQuestionRequest experianRequest, ExperianKickoffReqRes reqRes) {

		ExperianGenQuestionResponse  expAuthResponse  = new ExperianGenQuestionResponse();
		ExperianGenResponsePayload payload=new ExperianGenResponsePayload();
		String price=null;
		ErrorInfo errorInfo = new ErrorInfo();
		String url=resProp.getString("com.kickoff.generatequestion.url");
		
		logger.info("Inside Service Experian KickOff Generate Question.");
		
		try
		{
			if (experianRequest != null && experianRequest.getPayload()!=null)
			{
			
				boolean result = checkValidation(experianRequest.getHeader());
				
				if(result==true
						&& experianRequest.getPayload().getStgOneHitId()!=null && !experianRequest.getPayload().getStgOneHitId().equals("")
						&& experianRequest.getPayload().getStgTwoHitId()!=null && !experianRequest.getPayload().getStgTwoHitId().equals("")
						&& ((experianRequest.getPayload().getSingleActionSessionId()!=null && !experianRequest.getPayload().getSingleActionSessionId().equals(""))
								|| ( experianRequest.getPayload().getAnswer()!=null && !experianRequest.getPayload().getAnswer().equals("")
										&& experianRequest.getPayload().getQuestionId()!=null && !experianRequest.getPayload().getQuestionId().equals("")))
						)
				{
					ObjectMapper om = new ObjectMapper();
							
					HttpCallDetails httpCallDetails = httpCall(experianRequest.getPayload().asFormData(), url, resProp.getString("com.kickoff.contenttype"));
					try
					{
						
						reqRes.setInternal_req(experianRequest.getPayload().asFormData());
						reqRes.setInternal_res(httpCallDetails.getResponse());
						

					}
					catch(Exception e)
					{
						logger.error("ExperianKickOffServiceImpl || experianGenService() || Error while setting KickOff Generate Question internal req res:"+e);
					}
					
					
					
					if(httpCallDetails.getResponseCode()==200)
					{
						payload.setResult(om.readValue(httpCallDetails.getResponse(), ExperianGenResPayloadResult.class));
						expAuthResponse.setPayload(payload);
						
						if(expAuthResponse.getPayload().getResult().getShowHtmlReportForCreditReport()!=null 
								|| expAuthResponse.getPayload().getResult().getResponseJson().contains("next")
								|| expAuthResponse.getPayload().getResult().getResponseJson().contains("passedReport")){
							price=resProp.getString("com.kickoff.crq.success.price");
							errorInfo.setCode(StringConstants.C_200.toString());
							errorInfo.setStatus(ERRORSTATUS.SUCCESS);
							errorInfo.setMessage(StringConstants.MESSAGE200.toString());
							errorInfo.setDescription(StringConstants.MESSAGE200.toString());
						}else{
							price=resProp.getString("com.kickoff.crq.failure.price");
							errorInfo.setCode(StringConstants.C_101.toString());
							errorInfo.setStatus(ERRORSTATUS.FAILURE);
							errorInfo.setMessage(StringConstants.MESSAGE101.toString());
							errorInfo.setDescription(expAuthResponse.getPayload().getResult().getResponseJson());
						}
						
						
	                  
					}
					else
					{
						errorInfo.setCode(StringConstants.C_500.toString());
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setMessage(StringConstants.MESSAGE500.toString());
						errorInfo.setDescription(StringConstants.MESSAGE500.toString());
						logger.error("ExperianKickOffServiceImpl || experianGenService() || Error while creating OutPut from calling API");
					}
				}
				else
				{
					errorInfo.setCode(StringConstants.C_400.toString());
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage(StringConstants.MESSAGE400.toString());
					errorInfo.setDescription(StringConstants.MESSAGE400.toString());
				}
			
 			
			}else
			{
				errorInfo.setCode("400");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Bad Request");
				errorInfo.setDescription("Bad Request");
				
			}
		}
		catch (Exception e) 
		{
			logger.error("ExperianKickOffServiceImpl || experianGenService() || Exception while processing : " + e);
			errorInfo.setCode(StringConstants.C_600.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE600.toString());
			errorInfo.setDescription(StringConstants.MESSAGE600.toString());
			
		}
		expAuthResponse.setErrorInfo(errorInfo);
		reqRes.setPrice(price);
		expAuthResponse.setHeader(experianRequest.getHeader());
		return expAuthResponse;
	
	
	}

	
	private boolean checkValidation(Header header) {
		if (header != null) {
			AuthValidator auth = dbConnection.validateAuth(header);
			if (auth.getAppid() == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

    @SuppressWarnings("deprecation")
	public static HttpCallDetails httpCall(String request,String url,String contentType)
    {
    	logger.info("ExperianServiceImpl || HttpCallDetails ()|| start");
    	HttpCallDetails info=new HttpCallDetails();
    	String readResponse="";
    	try{
    		
    	SSLContext context = SSLContext.getInstance("TLSv1.2");
		context.init(null, null, new java.security.SecureRandom());
		SSLContext.setDefault(context );
    	}catch(Exception e){
    		logger.error("WSClient | Error in setting TLSv2");
    	}
		HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter("http.useragent","Web Service Test Client");
        BufferedReader br = null;
       
        PostMethod methodPost = new PostMethod(url);
        methodPost.setRequestBody(request);
       
        methodPost.setRequestHeader("Content-Type",contentType);
        
        methodPost.setRequestHeader("Accept-Encoding","deflate");
        methodPost.setRequestHeader("User-Agent","Apache-HttpClient/4.1.1");

        try {
        	
            int returnCode = httpClient.executeMethod(methodPost);
            
            methodPost.getResponseHeaders();
            org.apache.commons.httpclient.Header[] headers = methodPost.getResponseHeaders();
            String sessionId="";
            for(org.apache.commons.httpclient.Header h: headers){
            	if(h.getName().contains("Set-Cookie")){
            		sessionId=h.getValue().substring(11,43);
            		break;
            	}
            }
            info.setSessionId(sessionId);
            
            info.setResponseCode(returnCode);
            if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
            	logger.error("WSClient  | error in calling Experian KickOff service " + returnCode);
                methodPost.getResponseBodyAsString();
            } else {
                br = new BufferedReader(new InputStreamReader(methodPost.getResponseBodyAsStream()));
                String read = "";
                while (((read = br.readLine()) != null)) {
                    readResponse+=read;
                }
            }
        } catch (Exception e) {
        	logger.error("ExperianKickOffServiceImpl || httpCall() || WSClient | error in calling Experian KickOff service" + e.getMessage());
        } finally {
            methodPost.releaseConnection();
            if (br != null)             try {
                    br.close();
                } catch (Exception fe) {
                	logger.error("ExperianKickOffServiceImpl || httpCall() || ExperianKickOffServiceImpl || httpCall() ||WSClient | error in calling  Experian KickOff service" + fe.getMessage());
                }
        }
        logger.info("Internal Request :: "+request);
        logger.info("Internal Response :: "+readResponse);
        
        
        info.setResponse(readResponse);
        logger.info("ExperianServiceImpl || HttpCallDetails ()|| END");
		return info;
        
    	
    }
    
    private ErrorInfo checkRequestValidation(ExperianRequest experianRequest) {
    	boolean flag=false;
    	boolean valid = true; 
    	ErrorInfo errorInfo=new ErrorInfo();
	    if(experianRequest.getPayload().getFirstName()!=null) {
			for (int i = 0; i < experianRequest.getPayload().getFirstName().length(); i++)
			{
			   char  ch = experianRequest.getPayload().getFirstName().charAt(i);
			       
			     if (!Character.isLetter(ch) && !Character.isWhitespace(ch))
			     {
			         valid = false;
			         break;
			     } 
			}  
			if(experianRequest.getPayload().getMiddleName()!=null && valid && experianRequest.getPayload().getFirstName().trim().length()>0 && experianRequest.getPayload().getFirstName().length()<=26) {      
				for (int i = 0; i < experianRequest.getPayload().getMiddleName().length(); i++)
				{
				   char  ch = experianRequest.getPayload().getMiddleName().charAt(i);
				       
				     if (!Character.isLetter(ch) && !Character.isWhitespace(ch))
				     {
				         valid = false;
				         break;
				     } 
				} 
				
				if(experianRequest.getPayload().getSurName()!=null && valid && experianRequest.getPayload().getMiddleName().length()<=26) {
					
					
					for (int i = 0; i < experianRequest.getPayload().getSurName().length(); i++)
					{
					   char  ch = experianRequest.getPayload().getSurName().charAt(i);
					       
					     if (!Character.isLetter(ch) && !Character.isWhitespace(ch))
					     {
					         valid = false;
					         break;
					     } 
					} 
					
					if(valid && experianRequest.getPayload().getSurName().trim().length()>0 && experianRequest.getPayload().getSurName().length()<=26)
					{
						
						if(experianRequest.getPayload().getDateOfBirth()!=null && experianRequest.getPayload().getDateOfBirth().trim().length()>0) {
							if(experianRequest.getPayload().getGender()!=null && experianRequest.getPayload().getGender().trim().length()>0) {
								
								if(experianRequest.getPayload().getFlatno()!=null 
										&& experianRequest.getPayload().getCity()!=null  
										&& experianRequest.getPayload().getFlatno().trim().length()>0 
										&& experianRequest.getPayload().getCity().trim().length()>0
										&& experianRequest.getPayload().getFlatno().trim().length()<=40
										&& experianRequest.getPayload().getCity().trim().length()<=40
										&&  Pattern.matches("^[A-Za-z\\\\0-9,\\]\\[./]+$",experianRequest.getPayload().getFlatno())
										&&  Pattern.matches("^[A-Za-z\\\\0-9,\\]\\[./]+$",experianRequest.getPayload().getCity())) {
									
									if( experianRequest.getPayload().getBuildingName()!=null 
											&& experianRequest.getPayload().getRoadName()!=null 
											&& experianRequest.getPayload().getBuildingName().trim().length()<=40
											&& experianRequest.getPayload().getRoadName().trim().length()<=40
											&&  Pattern.matches("^[A-Za-z\\\\0-9,\\]\\[./]+$",experianRequest.getPayload().getBuildingName())
											&&  Pattern.matches("^[A-Za-z\\\\0-9,\\]\\[./]+$",experianRequest.getPayload().getRoadName())) {
										
											
										if(experianRequest.getPayload().getState()!=null && experianRequest.getPayload().getState().trim().length()>0 ) {
											
											
											if((experianRequest.getPayload().getBuildingName().trim().length()>0 
													|| experianRequest.getPayload().getRoadName().trim().length()>0) 
													&& (experianRequest.getPayload().getPincode()==null
													|| experianRequest.getPayload().getPincode().trim().length()<6)) {
												flag=true;
												errorInfo.setDescription("Pincode 6 Digits Numeric value,1st Digit should be from 1-9 and Last Three Digits should not be zero");
												
											}else if(experianRequest.getPayload().getPincode()!=null 
													&& experianRequest.getPayload().getPincode().trim().length()==6
													&&  Pattern.matches("^[1-9][0-9]{5}$",experianRequest.getPayload().getPincode())
													&&  !experianRequest.getPayload().getPincode().trim().substring(3).equals("000")) {
												
												if((experianRequest.getPayload().getTelephoneNo()==null ||  !Pattern.matches("^[0-9]{5,13}$",experianRequest.getPayload().getTelephoneNo()))
														&& (experianRequest.getPayload().getPassport()==null || !Pattern.matches("^[A-Z][A-Z0-9]{6}$",experianRequest.getPayload().getPassport()))
														&& (experianRequest.getPayload().getVoterid()==null || experianRequest.getPayload().getVoterid().trim().length()<=1)
														&& (experianRequest.getPayload().getPan()==null ||  !Pattern.matches("^[A-Z]{3}[ABCFHLJPR][A-Z][0-9]{4}[A-Z]$",experianRequest.getPayload().getPan()))
														&& (experianRequest.getPayload().getMobileNo()==null || experianRequest.getPayload().getMobileNo().trim().length()<10 
																|| !Pattern.matches("^[7-9][0-9]{9}$",experianRequest.getPayload().getMobileNo()))){
													flag=true;
													errorInfo.setDescription("Mobile No./ Telephone No./Passport No./ Pan No./ VoterId No. one is mandatory");
													
												}else {
														
													if(experianRequest.getPayload().getMobileNo()!=null && experianRequest.getPayload().getMobileNo().trim().length()>0 && !Pattern.matches("^[7-9][0-9]{9}$",experianRequest.getPayload().getMobileNo().trim())){
														flag=true;
														errorInfo.setDescription("Mobile no. 10 Digits Numeric value and 1st Digit should be from 7,8,9");
													}
													if(experianRequest.getPayload().getTelephoneNo()!=null &&  experianRequest.getPayload().getTelephoneNo().trim().length()>0 &&  !Pattern.matches("^[0-9]{5,13}$",experianRequest.getPayload().getTelephoneNo())){
														flag=true;
														errorInfo.setDescription("Telephone no. should be minimum 5 digits");
													}
													if(experianRequest.getPayload().getPassport()!=null && experianRequest.getPayload().getPassport().trim().length()>0 &&  !Pattern.matches("^[A-Z][A-Z0-9]{6}$",experianRequest.getPayload().getPassport())){
														flag=true;
														errorInfo.setDescription("Passport no. must be at least 7 characters and the first character must be a letter");
													}
													if(experianRequest.getPayload().getPan()!=null && experianRequest.getPayload().getPan().trim().length()>0 &&  !Pattern.matches("^[A-Z]{3}[ABCFHLJPR][A-Z][0-9]{4}[A-Z]$",experianRequest.getPayload().getPan())){
														flag=true;
														errorInfo.setDescription("Pan no. must be a minimum of 10 characters, The first five characters must be letters, followed by four numbers, followed by a character and The fourth letter must be either P, F, C, A, H, B, L, J or R");
													}
													if(experianRequest.getPayload().getEmail()!=null && Pattern.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b",experianRequest.getPayload().getEmail())){
														///Next step here
														if(experianRequest.getPayload().getAadhaar()!=null && experianRequest.getPayload().getAadhaar().trim().length()>0 &&  !Pattern.matches("^[1-9][0-9]{11}$",experianRequest.getPayload().getAadhaar())){
															flag=true;
															errorInfo.setDescription("Adhar no. must be a minimum of 12 digit number and 1st digit should not be zero.");
														}
													}
													else{
														
														flag=true;
														errorInfo.setDescription("Please enter a proper email id");
													}
												}
												
											}else {
												flag=true;
												errorInfo.setDescription("Pincode 6 Digits Numeric value,1st Digit should be from 1-9 and Last Three Digits should not be zero");
											}
										}else {
											flag=true;
											errorInfo.setDescription("State Can't be empty");
										}
									}else {
										flag=true;
										errorInfo.setDescription("Building / Society Name /Road / Area   1. Length should be less than or equal 40 2. Allowed [],./\\ Symbols");
									}
								}else {
									flag=true;
									errorInfo.setDescription("FlatNo / PlotNo / HouseNo /City  1. Can't be empty 2. Length should be less than or equal 40 3. Allowed [],./\\ Symbols");
								}
								
							}else {
								flag=true;
								errorInfo.setDescription("Gender can't be empty");
							}
						}else {
							flag=true;
							errorInfo.setDescription("Date of birth can't be empty");
						}
						
					}else {
						flag=true;
						errorInfo.setDescription("SurName contains Allow only alphabets and space and length should be less than or equal 26, can't be empty");
					}
						
				}else {
					flag=true;
					errorInfo.setDescription("Middle Name contains Allow only alphabets and space and length should be less than or equal 26");
				}
			}else {
				flag=true;
				errorInfo.setDescription("First Name contains only alphabets and space and length should be less than or equal 26, can't be empty");
			}
	    }
	    else
	    {
	    	flag=true;
			errorInfo.setDescription("First Name  can't be empty");
    	}
	    String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Kickoff.experianSingleAction.demo.development");	
			logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		
		if(hardCodedEnv.equalsIgnoreCase("N"))
		{
			if(flag) 
			{
				errorInfo.setCode(StringConstants.C_400.toString());
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage(StringConstants.MESSAGE400.toString());
				logger.info(" Request Validated in checkRequestValidation method : "+ERRORSTATUS.FAILURE);
				return errorInfo;
			}	
		}
		else
		{
			//need to set error info null for success data
		}
		return null;
    }

}
