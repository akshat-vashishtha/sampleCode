package com.qualtech.experiankickoff.api.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualtech.experiankickoff.api.common.dto.ERRORSTATUS;
import com.qualtech.experiankickoff.api.common.dto.ErrorInfo;
import com.qualtech.experiankickoff.api.common.dto.ExperianDto;
import com.qualtech.experiankickoff.api.common.dto.ExperianRequest;
import com.qualtech.experiankickoff.api.common.dto.MaskExperianRequest;
import com.qualtech.experiankickoff.api.db.DBConnection;
import com.qualtech.experiankickoff.api.request.AuthExperianRequest;
import com.qualtech.experiankickoff.api.request.ExperianGenQuestionReqPayload;
import com.qualtech.experiankickoff.api.request.ExperianGenQuestionRequest;
import com.qualtech.experiankickoff.api.request.ExperianKickOffFullReqRes;
import com.qualtech.experiankickoff.api.request.ExperianMaskReqPayload;
import com.qualtech.experiankickoff.api.request.ExperianKickoffReqRes;
import com.qualtech.experiankickoff.api.response.AuthExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianGenQuestionResponse;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponse;
import com.qualtech.experiankickoff.api.response.ExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianKickOffResponse;
import com.qualtech.experiankickoff.api.service.ExperianService;
import com.qualtech.experiankickoff.api.utils.PDFConverterInterface;
import com.qualtech.experiankickoff.api.utils.StringConstants;
import com.qualtech.experiankickoff.api.utils.UniqueId;

@Controller
@RequestMapping("/experianKickOff/api")
public class ExperianKickOffRestWebService
{
	static Logger logger = Logger.getLogger(ExperianKickOffRestWebService.class.getName());
	@Autowired
	private ExperianService experianService;
	
	@Autowired
	private DBConnection dbConnection;

	@Autowired
	private PDFConverterInterface pdfConverter;
	
	@RequestMapping(value="/v1/experianSingleAction", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ExperianKickOffResponse singleAction(@RequestBody ExperianRequest experianRequest)

	{

		final ExperianKickOffFullReqRes fullReqRes=new ExperianKickOffFullReqRes();
		ExperianResponse expResponse=new ExperianResponse();
		ErrorInfo errorInfo=new ErrorInfo();
		String uniqueId = UniqueId.getUniqueId();
		ExperianKickoffReqRes reqRes = new ExperianKickoffReqRes();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		try 
		{
			NDC.push("experianRequest : "+uniqueId);
			logger.info("Experian KickOff SingleAction Request :: STARTS");
			if(experianRequest.getPayload().getTelephoneType()==null || experianRequest.getPayload().getTelephoneType().trim().equals("")){
				experianRequest.getPayload().setTelephoneType("02");
			}
			NDC.push(experianRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+om.writeValueAsString(experianRequest));
		}
		catch(Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || singleAction() || Error While Reading Request Data : Invalid Request tried to hit : "+ex);
		}
	
		
		try 
		{
			reqRes.setUniqueId(uniqueId);
			reqRes.setRequest(om.writeValueAsString(experianRequest));
			reqRes.setTag(dbConnection.getServiceTagName("/v1/experianSingleAction"));
			reqRes.setCorelationid(experianRequest.getHeader().getCorrelationId());
			reqRes.setAppid(experianRequest.getHeader().getAppId());
			reqRes.setToken(experianRequest.getHeader().getToken());
			reqRes.setCreated(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || singleAction() ||  Error While setting  req in ReqRes from service : " + ex);
		}
		try 
		{
			expResponse = experianService.experianrequestService(experianRequest,reqRes);
			errorInfo = expResponse.getErrorInfo();
			String pdfPath="";
			if(expResponse.getErrorInfo().getCode() !=null){
				if(expResponse.getErrorInfo().getCode().equals("200"))
				{
					if(expResponse.getPayload() != null)
					{
						if(expResponse.getPayload().getResult()!=null){
							
						
							ExperianDto experiandto =pdfConverter.convertHtmlToPDF(expResponse.getPayload().getResult().getShowHtmlReportForCreditReport(),dbConnection.getPdfName(reqRes.getTag())+reqRes.getCorelationid());
							pdfPath=experiandto.getFilePath();
							expResponse.getPayload().setPdfPath(experiandto.getFilePath());
							expResponse.getPayload().setByteArray(experiandto.getByteArray());
						}
					}
				}
			}
		

			try {
				reqRes.setStatus(errorInfo.getStatus().toString());
				reqRes.setResponse(om.writeValueAsString(expResponse));
				reqRes.setUpdated_time(cal.getTime());
				if(pdfPath!=null)
					reqRes.setPdfPath(pdfPath);
			} catch (Exception e) {
				logger.error("ExperianKickOffRestWebService || singleAction() || Error While setting  res in ReqRes from service : " + e);
			}
			

		} 
		catch (Exception e)
		{
			errorInfo.setCode(StringConstants.C_500.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE500.toString());
			errorInfo.setDescription(StringConstants.MESSAGE500.toString());
			expResponse.setErrorInfo(errorInfo);
			logger.error("ExperianKickOffRestWebService || singleAction() || Service unable to process request" + e.getMessage());
			
		}
		finally
		{
			 fullReqRes.setExperianRequest(experianRequest);
			 fullReqRes.setExperianResponse(expResponse);
			 fullReqRes.setReqRes(reqRes);
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					logger.info("Inside Service insertAllDataToDb thread");
					insertAlltoDB(fullReqRes);
				}

				
			}).start();
			logger.info("Experian KickOff single action :: END");
			NDC.pop();
			NDC.pop();
		}
		try{
			if(expResponse.getPayload()!=null){
			
				if(expResponse.getPayload().getResult()!=null 
						&& expResponse.getPayload().getResult().getErrorString()!=null
						&& expResponse.getPayload().getResult().getErrorString().contains("Email Validation Failed or phone Validation Failed. Please try to invoke CRQ externally")
						&& expResponse.getPayload().getResult().getShowHtmlReportForCreditReport()==null){
					logger.info("Calling MaskedDelivery method in single action ");
					MaskExperianRequest maskExperianRequest=new MaskExperianRequest();
					ExperianMaskReqPayload exMaskReqPayload= new ExperianMaskReqPayload();
					
					exMaskReqPayload.setClientName(experianRequest.getPayload().getClientName());
					exMaskReqPayload.setStgOneHitId(expResponse.getPayload().getResult().getStageOneId_());
					exMaskReqPayload.setStgTwoHitId(expResponse.getPayload().getResult().getStageTwoId_());
					
					
					maskExperianRequest.setHeader(experianRequest.getHeader());
					maskExperianRequest.setPayload(exMaskReqPayload);
					
					ExperianMaskResponse maskResponse=(ExperianMaskResponse) maskedDeliveryData(maskExperianRequest);
					maskResponse.getPayload().getResult().setSessionId(expResponse.getSessionId());
					return  maskResponse;
				}
			}
		}
		catch(Exception e){
		}
		return  expResponse;
	}
	
	
	@RequestMapping(value="/v1/experianMaskedDelivery", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ExperianMaskResponse maskedDeliveryData(@RequestBody MaskExperianRequest experianRequest)
	
	{

		final ExperianKickOffFullReqRes fullReqRes=new ExperianKickOffFullReqRes();
		ExperianMaskResponse expResponse=null;
		ErrorInfo errorInfo=null;
		String uniqueId = UniqueId.getUniqueId();
		ExperianKickoffReqRes reqRes = new ExperianKickoffReqRes();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		try 
		{
			NDC.push("experianRequest : "+uniqueId);
			logger.info("Experian KickOff MaskedDelivery Request :: STARTS");
			
			NDC.push(experianRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+om.writeValueAsString(experianRequest));
		}
		catch(Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || maskedDeliveryData() || Error While Reading Request Data : Invalid Request tried to hit : "+ex);
		}
	
		
		try 
		{
			reqRes.setUniqueId(uniqueId);
			reqRes.setRequest(om.writeValueAsString(experianRequest));
			reqRes.setTag(dbConnection.getServiceTagName("/v1/experianMaskedDelivery"));
			reqRes.setCorelationid(experianRequest.getHeader().getCorrelationId());
			reqRes.setAppid(experianRequest.getHeader().getAppId());
			reqRes.setToken(experianRequest.getHeader().getToken());
			reqRes.setCreated(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || maskedDeliveryData() || Error While setting req in ReqRes: " + ex);
		}
		try 
		{
			expResponse = experianService.experianMaskService(experianRequest,reqRes);
			errorInfo = expResponse.getErrorInfo();
			String pdfPath="";
			
			try
			{
				
				expResponse.getPayload().setByteArray("");
				reqRes.setStatus(errorInfo.getStatus().toString());
				reqRes.setResponse(om.writeValueAsString(expResponse));
				reqRes.setUpdated_time(cal.getTime());
				reqRes.setPdfPath(pdfPath);

			}
			catch(Exception e)
			{
				logger.error("ExperianKickOffRestWebService || maskedDeliveryData() || Error While setting in ReqRes from service : " + e);
			}
		} 
		catch (Exception e)
		{
			errorInfo.setCode(StringConstants.C_500.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE500.toString());
			errorInfo.setDescription(StringConstants.MESSAGE500.toString());
			expResponse.setErrorInfo(errorInfo);
			logger.error("ExperianKickOffRestWebService || maskedDeliveryData() || Service unable to process request" + e.getMessage());
		}
		finally
		{
			 fullReqRes.setExpMaskRequest(experianRequest);
			 fullReqRes.setExpMaskResponse(expResponse);
			 fullReqRes.setReqRes(reqRes);
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					logger.info("Inside Service insertAllDataToDb thread");
					insertAlltoDB(fullReqRes);
				}
			}).start();
			logger.info("Experian KickOff MaskedDelivery:: END");
			NDC.pop();
			NDC.pop();
		}
		
		return expResponse;
	}
	

	@RequestMapping(value="/v1/experianAuthDelivery", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ExperianKickOffResponse authDeliveryData(@RequestBody AuthExperianRequest experianRequest)
	
	{
		
		final ExperianKickOffFullReqRes fullReqRes=new ExperianKickOffFullReqRes();
		AuthExperianResponse expResponse=null;
		ErrorInfo errorInfo=null;
		String uniqueId = UniqueId.getUniqueId();
		ExperianKickoffReqRes reqRes = new ExperianKickoffReqRes();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		try 
		{
			NDC.push("experianRequest : "+uniqueId);
			logger.info("Experian KickOff AuthDelivery Request :: STARTS");
			
			NDC.push(experianRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+om.writeValueAsString(experianRequest));
		}
		catch(Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || authDeliveryData() || Error While Reading Request Data : Invalid Request tried to hit : "+ex);
		}
	
		
		try 
		{
			reqRes.setUniqueId(uniqueId);
			reqRes.setRequest(om.writeValueAsString(experianRequest));
			reqRes.setTag(dbConnection.getServiceTagName("/v1/experianAuthDelivery"));
			reqRes.setCorelationid(experianRequest.getHeader().getCorrelationId());
			reqRes.setAppid(experianRequest.getHeader().getAppId());
			reqRes.setToken(experianRequest.getHeader().getToken());
			reqRes.setCreated(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || authDeliveryData() || Error While setting  req in ReqRes from service : " + ex);
		}
		try 
		{
			expResponse = experianService.experianAuthService(experianRequest,reqRes);
			errorInfo = expResponse.getErrorInfo();
			String pdfPath="";
			if(expResponse.getErrorInfo().getCode() !=null){
				if(expResponse.getErrorInfo().getCode().equals("200"))
				{
					if(expResponse.getPayload() != null)
					{
						if(expResponse.getPayload().getResult()!=null){
							
							ExperianDto experiandto =pdfConverter.convertHtmlToPDF(expResponse.getPayload().getResult().getShowHtmlReportForCreditReport(),dbConnection.getPdfName(reqRes.getTag())+reqRes.getCorelationid());
							pdfPath=experiandto.getFilePath();
							expResponse.getPayload().setPdfPath(experiandto.getFilePath());
							expResponse.getPayload().setByteArray(experiandto.getByteArray());
							
						}
					}
				}
			}
			
			try
			{
				reqRes.setStatus(errorInfo.getStatus().toString());
				reqRes.setResponse(om.writeValueAsString(expResponse));
				reqRes.setUpdated_time(cal.getTime());
				reqRes.setPdfPath(pdfPath);
			}
			catch(Exception e)
			{
				logger.error("ExperianKickOffRestWebService || authDeliveryData() || Error While setting  res in ReqRes from service : " + e);
			}
		} 
		catch (Exception e)
		{
			errorInfo.setCode(StringConstants.C_500.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE500.toString());
			errorInfo.setDescription(StringConstants.MESSAGE500.toString());
			expResponse.setErrorInfo(errorInfo);
			logger.error("ExperianKickOffRestWebService || authDeliveryData() || Service unable to process request" + e.getMessage());
		}
		finally
		{
			 fullReqRes.setExpAuthRequest(experianRequest);
			 fullReqRes.setExpAuthResponse(expResponse);
			 fullReqRes.setReqRes(reqRes);
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					logger.info("Inside Service insertAllDataToDb thread");
					insertAlltoDB(fullReqRes);
				}
			}).start();
			logger.info("Experian AuthDelivery :: END");
			NDC.pop();
			NDC.pop();
		}
		try{
			if(expResponse.getPayload()!=null){
			
				if(expResponse.getPayload().getResult()!=null 
						&& expResponse.getPayload().getResult().getResponseJson().contains("Authentication Failed")
						&& expResponse.getPayload().getResult().getShowHtmlReportForCreditReport()==null){
					logger.info("Calling Experian Generate question inside AuthDelivery method");
					ExperianGenQuestionRequest experianQueRequest=new ExperianGenQuestionRequest();
					ExperianGenQuestionReqPayload payload=new ExperianGenQuestionReqPayload();
							
					payload.setStgOneHitId(expResponse.getPayload().getResult().getStgOneHitId());
					payload.setStgTwoHitId(expResponse.getPayload().getResult().getStgTwoHitId());
					payload.setSingleActionSessionId(experianRequest.getPayload().getSingleActionSessionId());
					
					experianQueRequest.setHeader(experianRequest.getHeader());
					experianQueRequest.setPayload(payload);
					
					ExperianGenQuestionResponse expGenQuestionResponse= genrateQuestion(experianQueRequest);
					return  expGenQuestionResponse;
				}
			}
		}
		catch(Exception e){
			logger.error("ExperianKickOffRestWebService || authDeliveryData() || Exception while setting ExperianGenQuestionRequest " +e);
		}
		
		return expResponse;
	}
	
	

	@RequestMapping(value="/v1/experianGenerateQuestion", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ExperianGenQuestionResponse genrateQuestion(@RequestBody ExperianGenQuestionRequest experianRequest)
	
	{
		final ExperianKickOffFullReqRes fullReqRes=new ExperianKickOffFullReqRes();
		ExperianGenQuestionResponse expResponse=null;
		ErrorInfo errorInfo=null;
		String uniqueId = UniqueId.getUniqueId();
		ExperianKickoffReqRes reqRes = new ExperianKickoffReqRes();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		try 
		{
			NDC.push("experianRequest : "+uniqueId);
			logger.info("Experian KickOff GenerateQuestion Request :: STARTS");
			
			NDC.push(experianRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+om.writeValueAsString(experianRequest));
		}
		catch(Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || genrateQuestion() || Exception While Reading Request Data : Invalid Request tried to hit : "+ex);
		}
	
		
		try 
		{
			reqRes.setUniqueId(uniqueId);
			reqRes.setRequest(om.writeValueAsString(experianRequest));
			reqRes.setTag(dbConnection.getServiceTagName("/v1/experianGenerateQuestion"));
			reqRes.setCorelationid(experianRequest.getHeader().getCorrelationId());
			reqRes.setAppid(experianRequest.getHeader().getAppId());
			reqRes.setToken(experianRequest.getHeader().getToken());
			reqRes.setCreated(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || genrateQuestion() || Error While setting  req in ReqRes from service : " + ex);
		}
		try 
		{
			expResponse = experianService.experianGenService(experianRequest,reqRes);
			errorInfo = expResponse.getErrorInfo();
			String pdfPath="";
			if(expResponse.getErrorInfo().getCode() !=null){
				if(expResponse.getErrorInfo().getCode().equals("200"))
				{
					if(expResponse.getPayload() != null)
					{
						if(expResponse.getPayload().getResult()!=null){
							
							ExperianDto experiandto =pdfConverter.convertHtmlToPDF(expResponse.getPayload().getResult().getShowHtmlReportForCreditReport(),dbConnection.getPdfName(reqRes.getTag())+reqRes.getCorelationid());
							pdfPath=experiandto.getFilePath();
							expResponse.getPayload().setPdfPath(experiandto.getFilePath());
							expResponse.getPayload().setByteArray(experiandto.getByteArray());
							
						}
					}
				}
			}
			try
			{
				reqRes.setStatus(errorInfo.getStatus().toString());
				reqRes.setResponse(om.writeValueAsString(expResponse));
				reqRes.setUpdated_time(cal.getTime());
				reqRes.setPdfPath(pdfPath);
			}
			catch(Exception e)
			{
				logger.error("ExperianKickOffRestWebService || genrateQuestion() || Error While setting  res in ReqRes from service : " + e);
			}
		} 
		catch (Exception e)
		{
			errorInfo.setCode(StringConstants.C_500.toString());
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage(StringConstants.MESSAGE500.toString());
			errorInfo.setDescription(StringConstants.MESSAGE500.toString());
			expResponse.setErrorInfo(errorInfo);
			logger.error("Service unable to process request" + e.getMessage());
		}
		finally
		{
			 fullReqRes.setExpGenRequest(experianRequest);
			 fullReqRes.setExpGenResponse(expResponse);
			 fullReqRes.setReqRes(reqRes);
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					logger.info("Inside Service insertAllDataToDb thread");
					insertAlltoDB(fullReqRes);
				}
			}).start();
			logger.info("Experian Generate Question :: END");
			NDC.pop();
			NDC.pop();
		}
		
		return expResponse;
	}
	
	
	
	private Long insertAlltoDB(ExperianKickOffFullReqRes fullReqRes) {
		
		Long count=dbConnection.insertAlltoDB(fullReqRes);
		return count;
		
	}
	
}
