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
import com.qualtech.icici.api.request.CustomerOnBoardRequest;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.CustomerOnBoardPayload;
import com.qualtech.icici.api.utils.HttpCalling;
/*
@author Akshat Vashishtha
@description Helper class for service code implementation
@version initial version 1.0
@since May 2019
*/
public class CustomerOnBoardServiceHelper {

	public static final Logger logger = Logger.getLogger(CustomerOnBoardServiceHelper.class);
	
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
			logger.error(" CustomerOnBoardServiceHelper || static block - ResourceBundle || Exception occurs  :: " + ex);
		}
	}
	
	//@description Sets Ibs request header in Customer On Board response header
	public Header getCustomerOnBoard(RequestIBs requestIbs) {
		return new Header(requestIbs.getHeader().getAppId(), requestIbs.getHeader().getCorrelationId(),
				requestIbs.getHeader().getMsgVersion(), requestIbs.getHeader().getToken());
	}

	//@description validation checking for Ibs request 
	public boolean validateIbsRequest(RequestIBs requestIbs) {

		boolean status = false;
		try {
			if (requestIbs != null && requestIbs.getHeader() != null && requestIbs.getPayload() != null
					&& requestIbs.getPayload().getIciciRequest() != null
						&& requestIbs.getPayload().getIciciRequest().getCustomerOnBoard() != null){
				status = true;
			}
		}catch(Exception exception) {
			logger.error(ICICIConstants.VALIDATE_IBS_CUSTOMER_ON_BOARD_EXCEPTION + exception);
		}
		
		return status;
	}
	
	//@description Generates Customer On Board response ResponsePayload object based on Customer On Board Request
	 CustomerOnBoardPayload getCustomerOnBoardPayload(CustomerOnBoardRequest customerOnBoardRequest,IciciReqRes rq,PropertyFile resProp) {

		CustomerOnBoardPayload responsePayload = null;
		try {
			this.resProp=resProp;
			Map<String, String> iciciInternalResponse = getIciciInternalData(customerOnBoardRequest,rq);
				
			if(iciciInternalResponse!=null && iciciInternalResponse.get(ICICIConstants.RESPONSE_CODE).equals("200")){
			
				Map<String, String> backendResponse = jsonToMapObject(iciciInternalResponse.get(ICICIConstants.RESPONSE_DATA));
				responsePayload = setBackendResponse(backendResponse);					
				}else{
					logger.info(ICICIConstants.CUSTOMER_ON_BOARD_BACKEND_FAILS);
					
				}
		}catch(Exception exception) {
			logger.error(ICICIConstants.CUSTOMER_ON_BOARD_PAYLOAD_EXCEPTION + exception);
		}
		
		return responsePayload;
	}

	 CustomerOnBoardPayload setBackendResponse(Map<String, String> backendResponse){
		 
		 CustomerOnBoardPayload responsePayload = null;
			
		 try{
			 	responsePayload = new CustomerOnBoardPayload();
				responsePayload.setResponseCode((backendResponse.containsKey(CustomerPayloadConstants.RESPONSE_CODE))?backendResponse.get(CustomerPayloadConstants.RESPONSE_CODE):"");
				responsePayload.setResponseRemarks((backendResponse.containsKey(CustomerPayloadConstants.RESPONSE_REMARKS))?backendResponse.get(CustomerPayloadConstants.RESPONSE_REMARKS):"");
				responsePayload.setTransID((backendResponse.containsKey(CustomerPayloadConstants.TRANS_ID))?backendResponse.get(CustomerPayloadConstants.TRANS_ID):"");
				responsePayload.setUrl((backendResponse.containsKey(CustomerPayloadConstants.URL))?backendResponse.get(CustomerPayloadConstants.URL):"");
				responsePayload.setBreAction((backendResponse.containsKey(CustomerPayloadConstants.BRE_ACTION))?backendResponse.get(CustomerPayloadConstants.BRE_ACTION):"");
				responsePayload.setBreDecision((backendResponse.containsKey(CustomerPayloadConstants.BRE_DECISION))?backendResponse.get(CustomerPayloadConstants.BRE_DECISION):"");
				responsePayload.setModalPremium((backendResponse.containsKey(CustomerPayloadConstants.MODAL_PREMIUM))?backendResponse.get(CustomerPayloadConstants.MODAL_PREMIUM):"");
				responsePayload.setAnnualPremiumWithTax((backendResponse.containsKey(CustomerPayloadConstants.ANNUAL_PREMIUM_TAX))?backendResponse.get(CustomerPayloadConstants.ANNUAL_PREMIUM_TAX):"");
				responsePayload.setIsMedical((backendResponse.containsKey(CustomerPayloadConstants.IS_MEDICAL))?backendResponse.get(CustomerPayloadConstants.IS_MEDICAL):"");
				responsePayload.setShowHncPopUp((backendResponse.containsKey(CustomerPayloadConstants.SHOW_HNC_POP_UP))?backendResponse.get(CustomerPayloadConstants.SHOW_HNC_POP_UP):"");
				responsePayload.setBreRequirments((backendResponse.containsKey(CustomerPayloadConstants.BRE_REQUIRMENTS))?backendResponse.get(CustomerPayloadConstants.BRE_REQUIRMENTS):"");
				responsePayload.setCiCounterOffer((backendResponse.containsKey(CustomerPayloadConstants.CI_COUNTER_OFFER))?backendResponse.get(CustomerPayloadConstants.CI_COUNTER_OFFER):"");
				responsePayload.setBaseCounterOffer((backendResponse.containsKey(CustomerPayloadConstants.BASE_COUNTER_OFFER))?backendResponse.get(CustomerPayloadConstants.BASE_COUNTER_OFFER):"");
				responsePayload.setLifeOption((backendResponse.containsKey(CustomerPayloadConstants.LIFE_OPTION))?backendResponse.get(CustomerPayloadConstants.LIFE_OPTION):"");
				responsePayload.setAdbrCounterOffer((backendResponse.containsKey(CustomerPayloadConstants.ADBR_COUNTER_OFFER))?backendResponse.get(CustomerPayloadConstants.ADBR_COUNTER_OFFER):"");
			 
		 }catch(Exception exception){
			 logger.error(" CustomerOnBoardServiceHelper || setBackendResponse() || Exception Occurs :: " + exception); 
		 }
		 	
		 return responsePayload;
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
	
	//@description Sets success Msg Info details
	 MsgInfo successMsgInfo() {
		return new MsgInfo(ICICIConstants.SUCCESS_CODE, ICICIConstants.SUCCESS_DESCRIPTION,
							ICICIConstants.SUCCESS_MESSAGE, ICICIConstants.SUCCESS_STATUS);
	}
	
	//@description Sets failure Msg Info details for IBS bad request
	public  MsgInfo ibsBadRequestMsg() {
		return new MsgInfo(ICICIConstants.BAD_REQUEST_CODE, ICICIConstants.BAD_REQUEST_DESCRIPTION_IBS,
				ICICIConstants.BAD_REQUEST_MESSAGE, ICICIConstants.BAD_REQUEST_STATUS);
	}
	
	//@description Sets failure Msg Info details for Customer On Board bad request
	 MsgInfo customerOnBoardBackendFails() {
		return new MsgInfo(ICICIConstants.BACKEND_FAIL_CODE, ICICIConstants.BACKEND_FAIL_DESCRIPTION_CUSTOMER_ON_BOARD,
				ICICIConstants.BACKEND_FAIL_MESSAGE, ICICIConstants.BACKEND_FAIL_STATUS);
	}
	 
	//@description Sets failure Msg Info details for Internal exception
		public  MsgInfo internalError() {
			return new MsgInfo(ICICIConstants.INTERNAL_ERROR_CODE, ICICIConstants.INTERNAL_ERROR_DESCRIPTION,
					ICICIConstants.INTERNAL_ERROR_MESSAGE, ICICIConstants.INTERNAL_ERROR_STATUS);
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
	 
	
	Map<String, String> getIciciInternalData(CustomerOnBoardRequest customerOnBoardRequest, IciciReqRes rq) {

		Map<String, String> iciciInternalResponse = null;
		
		try{
			String hardCodedEnv=getHardCoded();
			String url = resProp.getString("com.qualtech.icici.Request.customerOnBoard.url");
			
			String internalRequest = new ObjectMapper().writeValueAsString(customerOnBoardRequest);
			internalRequest = internalRequest.replace("adhb", "ADHB");
			internalRequest = internalRequest.replace("ciBenefit", "CIBenefit");
			internalRequest = internalRequest.replace("lifeCoverOption", "LifeCoverOption"); 
			
			if (("Y").equalsIgnoreCase(hardCodedEnv)){
				iciciInternalResponse = new HashMap<>();
				iciciInternalResponse.put("responseCode", "200");
				iciciInternalResponse.put("responseData", res.getString("ICICI_Customer_On_Board"));
			} 
			else{
				iciciInternalResponse = HttpCalling.httpPostCall(internalRequest, url, "json");
			}
			setInternalReqRes(rq, internalRequest, iciciInternalResponse.get("responseData"));
		}catch(Exception ec){
			logger.error(""+ec);
		}
		
		return iciciInternalResponse;
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
			logger.error("CustomerOnBoardServiceHelper || setInternalReqRes() || Error while updating ICICI internal req res:" + e);
		}
	}
	 
}
