package com.qualtech.finbit.api.controller;

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

import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.db.UniqueId;
import com.qualtech.finbit.api.db.FinbitDB;
import com.qualtech.finbit.api.request.FinbitApiRequest;
import com.qualtech.finbit.api.request.FinbitFullReqRes;
import com.qualtech.finbit.api.request.FinbitReqRes;
import com.qualtech.finbit.api.response.FinbitAPIResponse;
import com.qualtech.finbit.api.service.FinbitApiService;

@Controller
@RequestMapping("/finbit/api")
public class FinbitRestController 
{

	static Logger logger = Logger.getLogger(FinbitRestController.class.getName());
	

	@Autowired
	private FinbitDB dbConnection;
	@Autowired
	private PropertyFile resProp;
	@Autowired
	FinbitApiService finbitapiservicewithbytearray;

	@RequestMapping(value = "/v1/finbitRequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public FinbitAPIResponse finbitRequestProcess(@RequestBody FinbitApiRequest finbitapirequest)
	{
		FinbitAPIResponse apiResponse = null;
		ObjectMapper om=new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		Calendar cal= Calendar.getInstance();
		FinbitReqRes reqRes=new FinbitReqRes();
		try 
		{
			NDC.push("finbitRequest : "+finbitapirequest.getHeader().getCorrelationId());
			logger.info("FinbitRestController || finbitRequestProcess() || Controller STARTS :: RequestJson :: " + om.writeValueAsString(finbitapirequest));
		}
		catch (Exception ex)
		{
			logger.error("Exception in geting correlation id from request header or while conversion of request bean to json string  : " + ex);
		}

		try 
		{
			reqRes.setUniqueId(uniqueId);
			reqRes.setRequest(om.writeValueAsString(finbitapirequest));
			reqRes.setTag("FINBIT");
			reqRes.setCorelationid(finbitapirequest.getHeader().getCorrelationId());
			reqRes.setAppid(finbitapirequest.getHeader().getAppId());
			reqRes.setToken(finbitapirequest.getHeader().getToken());
			reqRes.setCreated(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.error("ExperianKickOffRestWebService || singleAction() ||  Error While setting  req in ReqRes from service : " + ex);
		}
		try 
		{
			logger.info("Service Call callfinbitService()");
			final FinbitAPIResponse finbitapiresponse = finbitapiservicewithbytearray.callFinbitService(finbitapirequest,reqRes);
			logger.info("call callfinbitService ends");
			apiResponse = finbitapiresponse;
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			final String responseJsonData = ow.writeValueAsString(finbitapiresponse);
			logger.info(" FinbitRestController || finbitRequestProcess() || API_Response || Response JSON :: "+responseJsonData);
			try {
				reqRes.setStatus(apiResponse.getErrorInfo().getStatus().toString());
				reqRes.setResponse(om.writeValueAsString(apiResponse));
				reqRes.setUpdated_time(cal.getTime());
				try {
					String price=null;
					if (apiResponse.getErrorInfo().getCode().equals("200"))
					{
						price=resProp.getString("com.qualtech.finbit.success.price");
					}else {
						price=resProp.getString("com.qualtech.finbit.failure.price");
					}
					reqRes.setPrice(price);

				} catch (Exception e) {
					logger.error("Error while updating finbit success/failure price:" + e);
				}
			} catch (Exception e) {
				logger.error("ExperianKickOffRestWebService || singleAction() || Error While setting  res in ReqRes from service : " + e);
			}
			final FinbitFullReqRes fullReqRes=new FinbitFullReqRes();
			fullReqRes.setFinbitapirequest(finbitapirequest);
			fullReqRes.setFinbitapiresponse(apiResponse);
			fullReqRes.setReqRes(reqRes);
			new Thread(new Runnable() {

				@Override public void run() {
					logger.info(" Inside insert to db thread:: START ");
					dbConnection.insertToDB(fullReqRes);
					logger.info(" Inside insert to db thread:: END ");
				} }).start();

		}
		catch (Exception ex) 
		{
			logger.error("Exception in FinbitRestController || finbitRequestProcess() || Exception oocurs : " + ex);
		}
		finally
		{
			logger.info("FinbitRestController || finbitRequestProcess() || Controller ENDS");
			NDC.pop();
		}
		return apiResponse;
	}

}
