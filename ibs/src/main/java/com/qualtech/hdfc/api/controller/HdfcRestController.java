package com.qualtech.hdfc.api.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualtech.api.db.ServiceMaster;
import com.qualtech.hdfc.api.db.DBHdfc;
import com.qualtech.hdfc.api.request.ApiRequest;
import com.qualtech.hdfc.api.request.HdfcFullReqRes;
import com.qualtech.hdfc.api.request.HdfcReqRes;
import com.qualtech.hdfc.api.response.ApiResponse;
import com.qualtech.hdfc.api.response.ApiResponseHeader;
import com.qualtech.hdfc.api.service.impl.HdfcService;
import com.qualtech.hdfc.api.utils.UniqueId;

@Controller
@RequestMapping("/hdfc/api")
public class HdfcRestController {

	static Logger logger = Logger.getLogger(HdfcRestController.class.getName());
	@Autowired
	HdfcService hdfcService;
	@Autowired
	DBHdfc dbHdfc;

	@RequestMapping(value = "/v1/requestHdfc", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody 
	public  ApiResponse hdfcRequestProcess(@RequestBody ApiRequest hdfcRequest ) 
	{

		ApiResponse hdfcResponse=null;
		ObjectMapper om = new ObjectMapper();
		final HdfcFullReqRes fullReqRes = new HdfcFullReqRes();
		Calendar cal=Calendar.getInstance();

		final HdfcReqRes rq = new HdfcReqRes();
		ServiceMaster service = null;
		String uniqueId = UniqueId.getUniqueId();
		try {
			NDC.push("hdfcRequest : " + uniqueId);
			NDC.push(hdfcRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(hdfcRequest));
			logger.info("HdfcRestController || hdfcRequestProcess() || Request"+hdfcRequest);
			service = dbHdfc.getServiceCredential("/v1/requestHdfc");
			logger.info("Service credentials get from db");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error("HdfcRestWebService || hdfcRequestProcess() || Error While mapping response header : " + ex);
		}
		try {
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(hdfcRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(hdfcRequest.getHeader().getCorrelationId());
			rq.setAppid(hdfcRequest.getHeader().getAppId());
			rq.setToken(hdfcRequest.getHeader().getToken());
			

		} catch (Exception ex) {
			logger.error("HdfcRestWebService || hdfcRequestProcess() || Error While setting header values : "+ ex);
		}
		try {
			hdfcResponse=hdfcService.hdfcRequestServiceQC(hdfcRequest, rq);

			try {

				rq.setRequest(om.writeValueAsString(hdfcRequest));
				rq.setResponse(om.writeValueAsString(hdfcResponse));
				
				rq.setCreatedTime(cal.getTime());

				fullReqRes.setHdfcRequest(hdfcRequest);
				fullReqRes.setHdfcResponse(hdfcResponse);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {

							savaHdfcRequestResponse(rq,fullReqRes);
						} catch (Exception e) {
							logger.error("HdfcRestWebService || hdfcRequestProcess() || There is no data saved in database : "+ e);
						}
					}
				}).start();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("HdfcRestWebService || hdfcRequestProcess() || Error while inserting hdfc req res:" + e);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error While getting response from service : " + ex);
		} finally {
			NDC.pop();
			NDC.pop();
		}
		return hdfcResponse;
	}

	protected void savaHdfcRequestResponse(HdfcReqRes rq,HdfcFullReqRes fullReqRes) {

		logger.info("Hdfc :: saveHdfcRequestResponse():: START");
		dbHdfc.insertHdfcRequestResponse(rq,fullReqRes);
		logger.info("Hdfc :: saveHdfcRequestResponse():: END");


	}
}
