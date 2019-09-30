package com.qualtech.icici.api.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.common.Header;
import com.qualtech.icici.api.common.ICICIConstants;
import com.qualtech.icici.api.common.MsgInfo;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.request.PolicyStatusRequest;
import com.qualtech.icici.api.response.PolicyStatusResponsePayload;
import com.qualtech.icici.api.response.Result;
import com.qualtech.icici.api.utils.HttpCalling;

/*
	@author Akshat Vashishtha
	@description Helper class for "PolicyStatusServiceImpl" code implementation
	@version initial version 1.0
	@since May 2019
*/
public class PolicyStatusServiceHelper {

	
	public static final Logger logger = Logger.getLogger(PolicyStatusServiceImpl.class);
	@Autowired PropertyFile resProp;
	
	static ResourceBundle res=null;
	static
	{
		try 
		{
			res = ResourceBundle.getBundle("hardcode");
		}
		catch(Exception ex)
		{
			logger.error(" PolicyStatusServiceHelper || static block - ResourceBundle || Exception occurs  :: " + ex);
		}
	}
	
	//@description Sets Ibs request header in policy header response header
	public Header getPolicyStatusHeader(RequestIBs requestIbs) {
		return new Header(requestIbs.getHeader().getAppId(), requestIbs.getHeader().getCorrelationId(),
				requestIbs.getHeader().getMsgVersion(), requestIbs.getHeader().getToken());
	}

	//@description validation checking for Ibs request 
	public  boolean validateIbsRequest(RequestIBs requestIbs) {

		boolean status = false;
		try {
			if (requestIbs != null && requestIbs.getHeader() != null && requestIbs.getPayload() != null
					&& requestIbs.getPayload().getIciciRequest() != null
						&& requestIbs.getPayload().getIciciRequest().getPolicyStatus() != null){
				status = true;
			}
		}catch(Exception exception) {
			logger.error(ICICIConstants.POLICY_STATUS_IBS_EXCEPTION + exception);
		}
		
		return status;
	}
	
	//@description Generates policy status response ResponsePayload object based on policy status Request
	 PolicyStatusResponsePayload getPolicyStatusPayload(PolicyStatusRequest policyStatusRequest,IciciReqRes rq,PropertyFile resProp) {

		PolicyStatusResponsePayload responsePayload = null;
		try {
			this.resProp=resProp;
			Map<String, String> iciciInternalResponse = getIciciInternalData(policyStatusRequest,rq);
			
			if(iciciInternalResponse!=null && iciciInternalResponse.get(ICICIConstants.RESPONSE_CODE).equals("200")){
				Map<String, String> backendResponse = jsonToMapObject(iciciInternalResponse.get(ICICIConstants.RESPONSE_DATA));
				responsePayload = new PolicyStatusResponsePayload();
				responsePayload.setResult(getResult(backendResponse));
			}else{
				logger.info(ICICIConstants.POLICY_STATUS_BACKEND_FAILS);
			}
		}catch(Exception exception) {
			logger.error(ICICIConstants.POLICY_STATUS_PAYLOAD_EXCEPTION + exception);
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
	
	//@description Sets failure Msg Info details for Internal exception
			public  MsgInfo internalError() {
				return new MsgInfo(ICICIConstants.INTERNAL_ERROR_CODE, ICICIConstants.INTERNAL_ERROR_DESCRIPTION,
						ICICIConstants.INTERNAL_ERROR_MESSAGE, ICICIConstants.INTERNAL_ERROR_STATUS);
			}
	
	//@description Sets failure Msg Info details for Customer On Board bad request
		 MsgInfo policyStatusBackendFails() {
			return new MsgInfo(ICICIConstants.BACKEND_FAIL_CODE, ICICIConstants.BACKEND_FAIL_DESCRIPTION_CUSTOMER_ON_BOARD,
					ICICIConstants.BACKEND_FAIL_MESSAGE, ICICIConstants.BACKEND_FAIL_STATUS);
		}
 
	 Result getResult(Map<String, String> backendResponse)
	 {
		 Result result = null;
			
		 try{
			 if(backendResponse != null){
				 result = new Result();
				 result.setStatus(backendResponse.containsKey(PolicyPayloadConstants.STATUS)?backendResponse.get(PolicyPayloadConstants.STATUS):"");
				 result.setIssuanceDate(backendResponse.containsKey(PolicyPayloadConstants.ISSUANCE_DATE)?backendResponse.get(PolicyPayloadConstants.ISSUANCE_DATE):"");
				 result.setRiskCommDate(backendResponse.containsKey(PolicyPayloadConstants.RISK_COMM_DATE)?backendResponse.get(PolicyPayloadConstants.RISK_COMM_DATE):"");
				 result.setPolicyStatus(backendResponse.containsKey(PolicyPayloadConstants.POLICY_STATUS)?backendResponse.get(PolicyPayloadConstants.POLICY_STATUS):"");
				 result.setPolicyNo(backendResponse.containsKey(PolicyPayloadConstants.POLICY_NO)?backendResponse.get(PolicyPayloadConstants.POLICY_NO):"");
				 result.setPremiumAmt(backendResponse.containsKey(PolicyPayloadConstants.PREMIUM_AMT)?backendResponse.get(PolicyPayloadConstants.PREMIUM_AMT):"");
				 result.setTimestamp(backendResponse.containsKey(PolicyPayloadConstants.TIME_STAMP)?backendResponse.get(PolicyPayloadConstants.TIME_STAMP):"");
				 result.setMessage(backendResponse.containsKey(PolicyPayloadConstants.MESSAGE)?backendResponse.get(PolicyPayloadConstants.MESSAGE):"");
				 result.setCode(backendResponse.containsKey(PolicyPayloadConstants.CODE)?backendResponse.get(PolicyPayloadConstants.CODE):"");
				 result.setDetails(backendResponse.containsKey(PolicyPayloadConstants.DETAILS)?backendResponse.get(PolicyPayloadConstants.DETAILS):"");
			 }
		 }catch(Exception exception){
			 logger.error("PolicyStatusServiceHelper || getResult() || Exception occurs :: "+ exception);
		 }
		return result;
	 }
	 
	//@description JSON conversion into Map object
		 public Map<String, String> jsonToMapObject(String jsonRequest) {

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
	 
	 Map<String, String> getIciciInternalData(PolicyStatusRequest policyStatusRequest, IciciReqRes rq) throws IOException{

			Map<String, String> iciciInternalResponse = null;
			try{
				String hardCodedEnv=getHardCoded();
				String url = resProp.getString("com.qualtech.icici.Request.policyStatus.url");
				String internalRequest = new ObjectMapper().writeValueAsString(policyStatusRequest);
				String updated = new ObjectMapper().writeValueAsString(policyStatusRequest.getParamListMap());
				String previous = updated;
				updated = updated.replace("\"", "\\\"");
				updated = updated.replace("{", "\"{");
				updated = updated.replace("}", "}\"");
				// To convert internal json request object into string for third party API request
				internalRequest = internalRequest.replace(previous, updated);
				
				if (("Y").equalsIgnoreCase(hardCodedEnv)){
					iciciInternalResponse = new HashMap<>();
					iciciInternalResponse.put(ICICIConstants.RESPONSE_CODE, "200");
					iciciInternalResponse.put(ICICIConstants.RESPONSE_DATA, res.getString("ICICI_POLICY_STATUS"));
				} 
				else{
					iciciInternalResponse = HttpCalling.httpPostCall(internalRequest, url, "json");
				}
				setInternalReqRes(rq, internalRequest, iciciInternalResponse.get(ICICIConstants.RESPONSE_DATA ));
			}catch(Exception exception){
				logger.error("PolicyStatusServiceHelper || getIciciInternalData() || Exception occurs :: "+ exception);
			}
			return iciciInternalResponse;
		}
	 
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
				logger.error("PolicyStatusServiceHelper || setInternalReqRes() || Error while updating ICICI internal req res:" + e);
			}
		}
	 
}