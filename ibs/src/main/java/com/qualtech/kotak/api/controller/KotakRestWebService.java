
package com.qualtech.kotak.api.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualtech.kotak.api.db.DBKotak;
import com.qualtech.kotak.api.db.KotakFULLRequestResponse;
import com.qualtech.kotak.api.dto.KotakDto;
import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.request.KotakReqRes;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakReversalResponse;
import com.qualtech.kotak.api.response.MessageInfo;
import com.qualtech.kotak.api.service.KotakService;
import com.qualtech.kotak.api.utils.PDFConverterInt;
import com.qualtech.kotak.api.utils.UniqueId;

@Controller
@RequestMapping("/kotak/api")
public class KotakRestWebService {
	static Logger logger = Logger.getLogger(KotakRestWebService.class.getName());

	@Autowired
	KotakService kotakService;
	@Autowired DBKotak dbKotak;
	@Autowired PDFConverterInt pdfconverter;
	
	
	@RequestMapping(value = "/v1/kotakRequest", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public KotakResponse kotakRequest(@RequestBody KotakRequest kotakRequest) 
	{
		final KotakFULLRequestResponse kotkrqres=new KotakFULLRequestResponse();
		KotakReqRes rq = new KotakReqRes();
		KotakResponse  kotkResponse =null;
		MessageInfo errorInfo=new MessageInfo();
		String uniqueId = UniqueId.getUniqueId();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		try 
		{
			NDC.push("kotakRequest : "+uniqueId);
			logger.info("kotak Request :: STARTS");
			
			NDC.push(kotakRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+kotakRequest.toString());
		}
		catch(Exception ex)
		{
			logger.error("KotakRestWebService || kotakRequest() || Error While Reading Request Data : Invalid Request tried to hit : "+ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(kotakRequest));
			rq.setTag("KOTAK PAYMENT");
			rq.setCorelationid(kotakRequest.getHeader().getCorrelationId());
			rq.setAppid(kotakRequest.getHeader().getAppId());
			rq.setToken(kotakRequest.getHeader().getToken());
			rq.setCreated(cal.getTime());

		} 
		catch (Exception ex)
		{
			logger.error("KotakRestWebService || kotakRequest() || Error While saving final req,res from service : " + ex);
		}
		try 
		{  
			 kotkResponse = kotakService.kotakRequestService(kotakRequest,rq);
			errorInfo = kotkResponse.getMsgInfo();
			String pdfPath=null;
			if(kotkResponse.getMsgInfo().getCode() !=null){
				if(kotkResponse.getMsgInfo().getCode().equals("200"))
				{
					if(kotkResponse.getPayload() != null)
					{
						
						KotakDto experiandto =  pdfconverter.getPdfByteArrayPayment(kotkResponse,kotakRequest);

						if (experiandto != null) 
						{
							pdfPath=experiandto.getFilePath();
							kotkResponse.getPayload().setPdfPath(experiandto.getFilePath());
							kotkResponse.getPayload().setByteArray(experiandto.getByteArray());
						}
					}
				}
			}
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			logger.info("Response is : "+ow.writeValueAsString(kotkResponse));
			//response = KotakResponse.status(200).type("application/json").entity(ow.writeValueAsString(kotkResponse)).build();
			try
			{
				
				rq.setStatus(errorInfo.getStatus().toString());
				rq.setUniqueId(uniqueId);
			    rq.setResponse(om.writeValueAsString(kotkResponse));
				rq.setUpdatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
		
				
				kotkrqres.setUniqueId(uniqueId);
				kotkrqres.setKotkreqres(rq);
				kotkrqres.setKotakPayReq(kotakRequest);
				kotkrqres.setKotakPayRes(kotkResponse);
				
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{   
							saveKotakPaymentRequestResponse(kotkrqres);
						} catch (Exception e) {
							logger.error("KotakRestWebService || kotakRequest() || There is no data saved in database : " + e);
						}
					}
				}).start();

			}
			catch(Exception e)
			{
				logger.error("KotakRestWebService || kotakRequest() || Error while updating Experian req res:"+e);
			}
		} 
		catch (Exception e)
		{
			logger.error("KotakRestWebService || kotakRequest() || Exception again when setting blank response : "+e);
		}
		finally
		{
			logger.info("KOTAK PAYMENT :: END");
			NDC.pop();
			NDC.pop();
		}
		return kotkResponse;
	}


	



	@RequestMapping(value = "/v1/kotakReversalRequest", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public KotakReversalResponse kotakReversalRequest(@RequestBody KotakRequestReversal kotakRequest) 
	{
		
	    final KotakFULLRequestResponse kotkrqres=new KotakFULLRequestResponse();
		MessageInfo errorInfo=new MessageInfo();
		KotakReqRes rq = new KotakReqRes();
		KotakReversalResponse  kotkResponse = null;
		String uniqueId = UniqueId.getUniqueId();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		try 
		{
		     NDC.push("kotakRequest : "+uniqueId);
			logger.info("kotak Request :: STARTS");
		}
		catch(Exception ex)
		{
			logger.error("Error While Reading Request Data : Invalid Request tried to hit : "+ex);
		}

		try 
		{
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(kotakRequest));
			rq.setTag("KOTAK REVERSAL");
			rq.setCorelationid(kotakRequest.getHeader().getCorrelationId());
			rq.setAppid(kotakRequest.getHeader().getAppId());
			rq.setToken(kotakRequest.getHeader().getToken());
			rq.setCreated(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.error("Error While saving final req,res from service : " + ex);
		}
		try 
		{
			
			kotkResponse=kotakService.kotakReversalRequestService(kotakRequest,rq);
			errorInfo = kotkResponse.getMsgInfo();
			String pdfPath=null;
			if(kotkResponse.getMsgInfo().getCode() !=null){
				if(kotkResponse.getMsgInfo().getCode().equals("200"))
				{
					if(kotkResponse.getPayload() != null)
					{
					
						KotakDto experiandto = pdfconverter.getPdfByteArrayRevarsal(kotkResponse,kotakRequest);

						if (experiandto != null) 
						{
							pdfPath=experiandto.getFilePath();
							kotkResponse.getPayload().setPdfPath(experiandto.getFilePath());
							kotkResponse.getPayload().setByteArray(experiandto.getByteArray());
						}
					}
				}
			}
			

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			logger.info("Response is : "+ow.writeValueAsString(kotkResponse));
			//response = Response.status(200).type("application/json").entity(ow.writeValueAsString(kotkResponse)).build();
			try
			{
				
				
				
				rq.setStatus(errorInfo.getStatus().toString());
			    rq.setResponse(om.writeValueAsString(kotkResponse));
				rq.setUpdatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
				
				kotkrqres.setUniqueId(uniqueId);
				kotkrqres.setKotkreqres(rq);
				kotkrqres.setKotakReversalReq(kotakRequest);
				kotkrqres.setKotakReversalRes(kotkResponse);
				
				
		
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveKotakREVRequestResponse(kotkrqres);
						} 
						
						catch (Exception e) 
						{
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();

			}
			catch(Exception e)
			{
				logger.error("Error while updating Kotak req res:"+e);
			}
		} 
		catch (Exception e)
		{
			logger.error("we are in exception again when setting blank response : "+e.getMessage());
		}
		finally
		{
			logger.info("KOTAK REVERSAL :: END");
			NDC.pop();
			NDC.pop();
		}
		return kotkResponse;
	}

	private int saveKotakREVRequestResponse(KotakFULLRequestResponse kotkrqres) {
		logger.info("KOTAK REVERSAL :: saveKotakREVRequestResponse():: START");
	    int count= dbKotak.insertKotakReversalRequestResponse(kotkrqres);
	    logger.info("KOTAK REVERSAL:: saveKotakREVRequestResponse :: END");
	return count;
	}


	private int saveKotakPaymentRequestResponse(KotakFULLRequestResponse kotkrqres) {
		logger.info("KOTAK PAYMENT :: saveKotakPaymentRequestResponse():: START");
		int count= dbKotak.insertKotakPaymentRequestResponse(kotkrqres);
		logger.info("KOTAK PAYMENT :: saveKotakPaymentRequestResponse():: END");
	return count;
	}


}
