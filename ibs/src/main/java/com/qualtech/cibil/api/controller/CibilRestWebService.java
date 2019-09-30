package com.qualtech.cibil.api.controller;

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

import com.qualtech.cibil.api.common.dto.ErrorInfo;
import com.qualtech.cibil.api.db.DBCibil;
import com.qualtech.cibil.api.entity.CibilReqResVpn;
import com.qualtech.cibil.api.request.CibilApiRequest;
import com.qualtech.cibil.api.response.CibilAPIResponse;
import com.qualtech.cibil.api.response.CibilResponsePayload;
import com.qualtech.cibil.api.service.CibilApiService;
import com.qualtech.cibil.api.utils.UniqueId;

@Controller
@RequestMapping("/cibil/api")
public class CibilRestWebService 
{
	static Logger logger = Logger.getLogger(CibilRestWebService.class.getName());
	
    
	@Autowired
	DBCibil dbCibil;

	@Autowired
	CibilApiService cibilapiservicewithbytearray;

	
	@RequestMapping(value ="/v1/cibilRequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody	public CibilAPIResponse cibilRequestProcess(@RequestBody CibilApiRequest cibilapirequest ) 
	{
		ObjectMapper om = new ObjectMapper();
		String raw = "";
		CibilAPIResponse apiResponse=null;
		final CibilReqResVpn rq=new CibilReqResVpn();
		String uniqueId = UniqueId.getUniqueId();
	 try 
		{
			
			NDC.push("cibilRequest : "+cibilapirequest.getHeader().getCorrelationId());
			logger.info(" CibilRestWebService ||  START  :: RequestJson :: "+cibilapirequest);
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(cibilapirequest));
			rq.setTag("CIBIL");
			rq.setCorelationid(cibilapirequest.getHeader().getCorrelationId());
			rq.setAppid(cibilapirequest.getHeader().getAppId());
			rq.setToken(cibilapirequest.getHeader().getToken());
		}
		catch(Exception ex)
		{    
			logger.error("Error While mapping response header : "+ex);
		}
		try 
		{
			logger.info("Service Call || callCibilService() ");
			final CibilAPIResponse  cibilapiresponse= cibilapiservicewithbytearray.callCibilService(cibilapirequest,rq);
			apiResponse=cibilapiresponse;
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			final String responseJsonData = ow.writeValueAsString(cibilapiresponse);
			logger.info(" CibilRestWebService || API_Response || Response JSON :: "+responseJsonData);
			final String reqjson=om.writeValueAsString(cibilapirequest);
			
			if(cibilapiresponse!=null) 
			{
	            ErrorInfo errorInfo =cibilapiresponse.getErrorInfo(); 
	            rq.setStatus(errorInfo.getStatus().toString());
	            rq.setResponse(om.writeValueAsString(cibilapiresponse));
	        }
			
			new Thread(new Runnable() 
			{
				public void run() 
				{
					saveCibilData(cibilapiresponse.getPayload(),cibilapiresponse.getHeader().getCorrelationId(),reqjson,responseJsonData,rq);
				}
			}).start();
		}
		catch (Exception ex) 
		{
			logger.error("CibilRestWebService || cibilRequestProcess() || Exception Occurs " + ex);
		}
		finally 
		{
			logger.info(" CibilRestWebService ||  END  ");
			NDC.pop();
		}
		return apiResponse;
	}
	public int saveCibilData(CibilResponsePayload  cibilresponsepayload,String correalationId, String requestData, String responseData,CibilReqResVpn rq)
	{
		int count = 0;
		try
		{
			 logger.info("CibilRestWebService || saveCibilData() || STARTS");
			 count = dbCibil.insertCibilData(cibilresponsepayload,correalationId,requestData,responseData,rq);
			 logger.info("CibilRestWebService || saveCibilData() || ENDS");
			 return count;
		}
		catch(Exception exception)
		{
			logger.error("Exception in CibilRestWebService || saveCibilData() || calling insertCibilData() "+exception);
		}
		
		return count;
	}
}
