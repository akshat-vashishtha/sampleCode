package com.qualtech.icici.api.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.common.Header;
import com.qualtech.icici.api.common.ICICIConstants;
import com.qualtech.icici.api.common.MsgInfo;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.request.WelcomeKitRequest;
import com.qualtech.icici.api.response.WelcomeKitResponsePayload;
import com.qualtech.icici.api.utils.HttpCalling;
/*
@author Akshat Vashishtha
@description Helper class for service code implementation
@version initial version 1.0
@since May 2019
*/
public class WelcomeKitServiceHelper {

	public static final Logger logger = Logger.getLogger(WelcomeKitServiceHelper.class);
	@Autowired PropertyFile resProp;
	
	//@description Sets Ibs request header in Welcome response header
	public Header getWelcomeKitHeader(RequestIBs requestIbs) {
		return new Header(requestIbs.getHeader().getAppId(), requestIbs.getHeader().getCorrelationId(),
				requestIbs.getHeader().getMsgVersion(), requestIbs.getHeader().getToken());
	}

	//@description validation checking for Ibs request 
	 public boolean validateIbsRequest(RequestIBs requestIbs) {

		boolean status = false;
		try {
			if (requestIbs != null && requestIbs.getHeader() != null && requestIbs.getPayload() != null
					&& requestIbs.getPayload().getIciciRequest() != null
						&& requestIbs.getPayload().getIciciRequest().getWelcomeKit() != null){
				status = true;
			}
		}catch(Exception exception) {
			logger.error(ICICIConstants.VALIDATE_IBS_EXCEPTION + exception);
		}
		return status;
	}
	
	//@description Generates Welcome Kit response ResponsePayload object based on Welcome Kit Request
	 WelcomeKitResponsePayload getWelcomeKitPayload(WelcomeKitRequest welcomeKitRequest, IciciReqRes rq,PropertyFile resProp) {

		WelcomeKitResponsePayload responsePayload = null;
		Map<String, String> iciciInternalResponse = null;
		try {
				this.resProp=resProp;
				String hardCodedEnv=getHardCoded();
				String policyNumUrl = resProp.getString(ICICIConstants.WELCOME_KIT_URL_PREFIX) +welcomeKitRequest.getPolicyNo()	+resProp.getString(ICICIConstants.WELCOME_KIT_URL_API_KEY);
				String templateIdUrl = resProp.getString(ICICIConstants.WELCOME_KIT_URL_PREFIX) +welcomeKitRequest.getTemplateId()+resProp.getString(ICICIConstants.WELCOME_KIT_URL_API_KEY);
				String agentCodeUrl = resProp.getString(ICICIConstants.WELCOME_KIT_URL_PREFIX) +welcomeKitRequest.getAgentCode()+resProp.getString(ICICIConstants.WELCOME_KIT_URL_API_KEY);
				String finalUrl = resProp.getString("com.qualtech.icici.Request.welcomeKit.url");
				String policyNumber = "";
				String templateId = "";
				String agentCode = "";
				
				if (("Y").equalsIgnoreCase(hardCodedEnv)){
					 policyNumber = resProp.getString("ICICI_WLCMKIT_POL_NO");
					 templateId = resProp.getString("ICICI_WLCMKIT_TEMP_ID");
					 agentCode = resProp.getString("ICICI_WLCMKIT_AGN_CD");
				}else{
					// Hits 3 API's and make final request based on the response data(policyNumber,templateId & agentCode) from those 3 API's 
					policyNumber = HttpCalling.httpGetCall(policyNumUrl).get(ICICIConstants.RESPONSE_DATA);
					templateId = HttpCalling.httpGetCall(templateIdUrl).get(ICICIConstants.RESPONSE_DATA);
					agentCode = HttpCalling.httpGetCall(agentCodeUrl).get(ICICIConstants.RESPONSE_DATA);
				}
				String internalRequest = "policyNo="+policyNumber+"&templateId="+templateId+"&agentCode="+agentCode+"";
				if (("Y").equalsIgnoreCase(hardCodedEnv)){
					iciciInternalResponse = new HashMap<>();
					iciciInternalResponse.put(ICICIConstants.RESPONSE_CODE, "200");
					iciciInternalResponse.put(ICICIConstants.RESPONSE_DATA, resProp.getString("ICICI_WELCOME_KIT"));
				} 
				else{
					iciciInternalResponse = HttpCalling.httpPostCall(internalRequest, finalUrl, "json");
				}
				setInternalReqRes(rq, internalRequest, iciciInternalResponse.get(ICICIConstants.RESPONSE_DATA));
				if( iciciInternalResponse.get(ICICIConstants.RESPONSE_CODE).equals("200")){
					responsePayload = new WelcomeKitResponsePayload();
					Map<String, String> backendResponse = jsonToMapObject(iciciInternalResponse.get(ICICIConstants.RESPONSE_DATA));
					String internalResponse =backendResponse.containsKey("url")?backendResponse.get("url"):"";
					responsePayload.setUrl(internalResponse);
				}else{
					logger.info(ICICIConstants.CUSTOMER_ON_BOARD_BACKEND_FAILS);
				}
			
		}catch(Exception exception) {
			logger.error(ICICIConstants.WELCOME_KIT_PAYLOAD_EXCEPTION + exception);
		}
		return responsePayload;
	}
	
	//@description Sets success Msg Info details
	 MsgInfo successMsgInfo() {
		return new MsgInfo(ICICIConstants.SUCCESS_CODE, ICICIConstants.SUCCESS_DESCRIPTION,
							ICICIConstants.SUCCESS_MESSAGE, ICICIConstants.SUCCESS_STATUS);
	}
	
	//@description Sets failure Msg Info details for IBS bad request
	 public MsgInfo ibsBadRequestMsg() {
		return new MsgInfo(ICICIConstants.BAD_REQUEST_CODE, ICICIConstants.BAD_REQUEST_DESCRIPTION_IBS,
				ICICIConstants.BAD_REQUEST_MESSAGE, ICICIConstants.BAD_REQUEST_STATUS);
	}
	
	//@description Sets failure Msg Info details for welcome Kit Back end Fails
	 MsgInfo welcomeKitBackendFails() {
		return new MsgInfo(ICICIConstants.BACKEND_FAIL_CODE, ICICIConstants.BACKEND_FAIL_DESCRIPTION_CUSTOMER_ON_BOARD,
				ICICIConstants.BACKEND_FAIL_MESSAGE, ICICIConstants.BACKEND_FAIL_STATUS);
	}
	 
	//@description Sets failure Msg Info details for Internal exception
	 public  MsgInfo internalError() {
				return new MsgInfo(ICICIConstants.INTERNAL_ERROR_CODE, ICICIConstants.INTERNAL_ERROR_DESCRIPTION,
						ICICIConstants.INTERNAL_ERROR_MESSAGE, ICICIConstants.INTERNAL_ERROR_STATUS);
	}
		
	 
	 //@description Provide hard code response data from DB instead of third party call response data for development environment purpose
	 String getHardCoded() {
			String hardCodedEnv = "N";
			try {
				hardCodedEnv = resProp.getString("com.icici.demo.development");
				logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration | present value is "+ hardCodedEnv);
			} catch (Exception ex) {
				hardCodedEnv = "N";
			}
			return hardCodedEnv;
		}
	 
	 //@description Calculates pricing for every hit to third party 
	 public void setInternalReqRes(IciciReqRes rq, String internalRequest, String internalResponse) {
			try 
			{
				String price=null;
				rq.setInternal_req(internalRequest);
				rq.setInternal_res(internalResponse);
				
				if (internalRequest!=null && !("").equals(internalRequest))
				{
					price=resProp.getString("com.qualtech.icici.success.price");
				}else {
					price=resProp.getString("com.qualtech.icici.failure.price");
				}
				rq.setPrice(price);
			} 
			catch (Exception e)
			{
				logger.error("WelcomeKitServiceHelper || setInternalReqRes() || Error while updating ICICI internal req res:" + e);
			}
		}
	 
	 Map<String, String> jsonToMapObject(String jsonRequest){

	        Map<String, String> convertedMapObject = new HashMap<>();
	        try {
				JSONObject jsonObject = new JSONObject(jsonRequest);
				Iterator<?> keys = jsonObject.keys();

				while( keys.hasNext() ){
				    String key = (String)keys.next();
				    String value = jsonObject.getString(key); 
				    convertedMapObject.put(key, value);

				}
			} catch (Exception exception) {
				convertedMapObject = null;
			}
	    return convertedMapObject;
	 }
	
}
