package com.qualtech.creditvidya.api.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualtech.creditvidya.api.common.dto.CreditDto;
import com.qualtech.creditvidya.api.common.dto.EmailSaveRequest;
import com.qualtech.creditvidya.api.common.dto.EmailVerificationRequest;
import com.qualtech.creditvidya.api.dbInt.DBConnectionCreditInt;
import com.qualtech.creditvidya.api.request.CreditFullReqRes;
import com.qualtech.creditvidya.api.request.CreditReqRes;
import com.qualtech.creditvidya.api.response.EmailSaveResponse;
import com.qualtech.creditvidya.api.response.EmailVerificationResponse;
import com.qualtech.creditvidya.api.response.MessageInfo;
import com.qualtech.creditvidya.api.service.CreditVidyaService;
import com.qualtech.creditvidya.api.utils.PDFConverterInter;
import com.qualtech.creditvidya.api.utils.UniqueId;


@Controller
@RequestMapping("/creditvidya/api")
public class CreditVidyaRestWebService {
	static Logger logger = Logger.getLogger(CreditVidyaRestWebService.class.getName());
	@Autowired
	private CreditVidyaService creditVidyaService ;

	@Autowired
	private DBConnectionCreditInt dbconnection;

	@Autowired
	private PDFConverterInter pdf;

	@RequestMapping(value="v1/email",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public EmailSaveResponse emailvalidationProcess(@RequestBody EmailSaveRequest emailSaveRequest) 
	{
		final CreditFullReqRes creditFullReqRes=new CreditFullReqRes();
		MessageInfo msgInfo = null;
		final String UNIQUE_ID = UniqueId.getUniqueId();
		CreditReqRes rqres = new CreditReqRes();
		EmailSaveResponse emailSaveResponse=null;
		ObjectMapper mapper=new ObjectMapper();


		try
		{

			NDC.push("emailSaveRequest : " +emailSaveRequest.getHeader().getCorrelationId());
			logger.info(" CreditVidyaRestWebService || Controller STARTS || RequestJson :: " + mapper.writeValueAsString(emailSaveRequest));

		}
		catch (Exception ex)
		{
			logger.error("Exception in CreditVidyaRestWebService || emailvalidationProcess() || mapping request to JSON :: " + ex);
		}
		try
		{
			creditFullReqRes.setUniqueId(UNIQUE_ID);
			rqres.setUniqueId(UNIQUE_ID);
			rqres.setRequest(mapper.writeValueAsString(emailSaveRequest));
			rqres.setTag("EMAIL SAVE");
			rqres.setCorelationid(emailSaveRequest.getHeader().getCorrelationId());
			rqres.setAppid(emailSaveRequest.getHeader().getAppId());
			rqres.setToken(emailSaveRequest.getHeader().getToken());
		}
		catch (Exception ex) 
		{
			logger.error("Exception in CreditVidyaRestWebService || emailvalidationProcess() || setting CreditReqRes from API_Request : " + ex);
		}
		try
		{

			logger.info("Service Call || emailService() ");
			emailSaveResponse=creditVidyaService.emailService(emailSaveRequest,rqres);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			final String responseJsonData = ow.writeValueAsString(emailSaveResponse);
			logger.info(" CreditVidyaRestWebService || emailvalidationProcess() || API_Response || Response JSON :: "+responseJsonData);
			msgInfo = emailSaveResponse.getMsgInfo();
			try
			{
				Calendar cal=Calendar.getInstance();
				rqres.setStatus(msgInfo.getStatus());
				rqres.setResponse(mapper.writeValueAsString(emailSaveResponse));
				rqres.setUpdatedTime(cal.getTime());

				creditFullReqRes.setReqRes(rqres);
				creditFullReqRes.setEmailSaveRequest(emailSaveRequest);
				creditFullReqRes.setEmailSaveResponse(emailSaveResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						dbconnection.insertAlltoDB(creditFullReqRes);
					}
				}).start();	
			}
			catch(Exception e)
			{
				logger.error("Exception in CreditVidyaRestWebService || emailvalidationProcess() || setting CreditReqRes or creditFullReqRes :: "+e);
			}

		} catch (Exception ex) {
			logger.error("CreditVidyaRestWebService || emailvalidationProcess() || Exception Occurs : " + ex);
		}

		finally {
			logger.info(" CreditVidyaRestWebService || Controller ENDS");
			NDC.pop();
		}

		logger.info("CreditVidyaRestWebService || emailvalidationProcess() || Controller ENDS");
		return emailSaveResponse;
	}


	@RequestMapping(value="/v1/email/verification",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public EmailVerificationResponse emailVerificationProcess(@RequestBody EmailVerificationRequest emailVerificationRequest)
	{
		final CreditFullReqRes creditFullReqRes=new CreditFullReqRes();
		MessageInfo msgInfo = null;
		EmailVerificationResponse emailVerificationResponse=null;
		ObjectMapper om=new ObjectMapper();
		String uniqueRequestID = UniqueId.getUniqueId();
		CreditReqRes rqres = new CreditReqRes();
		Calendar cal=Calendar.getInstance();
		CreditDto dto=null;
		logger.info(" CreditVidyaRestWebService || emailVerificationProcess() || Controller STARTS || API_Request :: ");
		try
		{
			NDC.push("emailVerificationRequest : " + UniqueId.getUniqueId());
			NDC.push(emailVerificationRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(emailVerificationRequest));
		}
		catch (Exception ex) 
		{
			logger.info("Error While mapping response header : " + ex);
		}
		try 
		{
			rqres.setUniqueId(uniqueRequestID);
			rqres.setRequest(om.writeValueAsString(emailVerificationRequest));
			rqres.setTag("EMAIL VERIFICATION");
			rqres.setCorelationid(emailVerificationRequest.getHeader().getCorrelationId());
			rqres.setAppid(emailVerificationRequest.getHeader().getAppId());
			rqres.setToken(emailVerificationRequest.getHeader().getToken());
			rqres.setCreatedTime(cal.getTime());
		}
		catch (Exception ex)
		{
			logger.error("Exception in CreditVidyaRestWebService || emailVerificationProcess() || setting CreditReqRes from API_Request : " + ex);
		}
		try 
		{
			logger.info("Service Call || emailVerificationService() ");
			emailVerificationResponse = creditVidyaService.emailVerificationService(emailVerificationRequest,rqres);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			final String responseJsonData = ow.writeValueAsString(emailVerificationResponse);

			logger.info(" CreditVidyaRestWebService || emailVerificationService() || API_Response || Response JSON :: "+responseJsonData);

			msgInfo = emailVerificationResponse.getMsgInfo();

			try 
			{
				dto=pdf.convertToPdf(emailVerificationResponse.getPayload().getData().getOutput().getContent(),
						emailVerificationResponse.getHeader().getCorrelationId());
			}
			catch (Exception e) {
				logger.error("Exception in CreditVidyaRestWebService || emailVerificationProcess() || Base64 to PDF conversion :: " + e);
			}

			try{
				rqres.setStatus(msgInfo.getStatus());
				rqres.setResponse(om.writeValueAsString(emailVerificationResponse));
				if(dto != null) 
				{

					rqres.setPdfPath(dto.getFilePath());
					emailVerificationResponse.getPayload().setPdfPath(dto.getFilePath());
					emailVerificationResponse.getPayload().setByteArray(dto.getByteArray());
				}
				rqres.setUpdatedTime(cal.getTime());

				creditFullReqRes.setReqRes(rqres);
				creditFullReqRes.setEmailVerificationRequest(emailVerificationRequest);
				creditFullReqRes.setEmailVerificationResponse(emailVerificationResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						dbconnection.insertAlltoDB(creditFullReqRes);
					}
				}).start();	

			}
			catch(Exception e)
			{
				logger.error("Exception in CreditVidyaRestWebService || emailVerificationProcess() || setting CreditReqRes or creditFullReqRes :: "+e);

			}
		} catch (Exception ex) {
			logger.error("CreditVidyaRestWebService || emailVerificationProcess() || Exception Occurs : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		logger.info(" CreditVidyaRestWebService || emailVerificationProcess() || Controller ENDS ");
		return emailVerificationResponse;
	}
}