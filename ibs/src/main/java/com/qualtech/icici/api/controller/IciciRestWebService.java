package com.qualtech.icici.api.controller;

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

import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.common.ICICIConstants;
import com.qualtech.icici.api.db.DBIcici;
import com.qualtech.icici.api.request.CustomerOnBoardRequest;
import com.qualtech.icici.api.request.IciciFullReqRes;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.request.PolicyStatusRequest;
import com.qualtech.icici.api.request.PremCalc;
import com.qualtech.icici.api.request.WelcomeKitRequest;
import com.qualtech.icici.api.response.CustomerOnBoardResponse;
import com.qualtech.icici.api.response.MsgInfo;
import com.qualtech.icici.api.response.PolicyStatusResponse;
import com.qualtech.icici.api.response.PremCalResponse;
import com.qualtech.icici.api.response.WelcomeKitResponse;
import com.qualtech.icici.api.service.CustomerOnBoardService;
import com.qualtech.icici.api.service.ICICIService;
import com.qualtech.icici.api.service.PolicyStatusService;
import com.qualtech.icici.api.service.WelcomeKitService;
import com.qualtech.icici.api.service.impl.CustomerOnBoardServiceHelper;
import com.qualtech.icici.api.service.impl.PolicyStatusServiceHelper;
import com.qualtech.icici.api.service.impl.WelcomeKitServiceHelper;
import com.qualtech.icici.api.utils.UniqueId;

@Controller
@RequestMapping("/icici/api")
public class IciciRestWebService {

	static Logger logger = Logger.getLogger(IciciRestWebService.class.getName());

	@Autowired ICICIService iciciService;
	@Autowired DBIcici dbIcici;
	@Autowired CustomerOnBoardService customerOnBoardService;
	@Autowired WelcomeKitService welcomeKitService;
	@Autowired PolicyStatusService policyStatusService;

	@RequestMapping(value = "/v1/premiumCalculation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public PremCalResponse premiumCalculationProcess(@RequestBody RequestIBs icicIbsRequest) {

		PremCalc premCalcRequest = icicIbsRequest.getPayload().getIciciRequest().getPremiumCalculation();
		PremCalResponse premCalResponse = null;
		ObjectMapper om = new ObjectMapper();
		IciciReqRes rq = new IciciReqRes();
		Calendar cal = Calendar.getInstance();
		MsgInfo msgInfo = null;
		String uniqueId = UniqueId.getUniqueId();
		final IciciFullReqRes fullReqRes = new IciciFullReqRes();

		try {
			NDC.push("Premium Calculation Process : " + uniqueId);
			NDC.push(icicIbsRequest.getHeader().getCorrelationId());
			logger.info(ICICIConstants.REQUEST_JSON + om.writeValueAsString(premCalcRequest));

			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(premCalcRequest));
			rq.setTag("Premium Calculation");
			rq.setCorelationid(icicIbsRequest.getHeader().getCorrelationId());
			rq.setAppid(icicIbsRequest.getHeader().getAppId());
			rq.setToken(icicIbsRequest.getHeader().getToken());
			rq.setCreated(cal.getTime());

			premCalResponse = iciciService.premiumCalculationService(icicIbsRequest, rq);
			msgInfo = premCalResponse.getMsgInfo();

			rq.setStatus(msgInfo.getStatus());
			rq.setUniqueId(uniqueId);
			rq.setResponse(om.writeValueAsString(premCalResponse));
			rq.setUpdated_time(cal.getTime());
			fullReqRes.setReqRes(rq);
			fullReqRes.setPremCalcRequest(icicIbsRequest);
			fullReqRes.setPremCalResponse(premCalResponse);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						savePremCalcReqRes(fullReqRes);
					} catch (Exception e) {
						logger.error("IciciRestWebService || premiumCalculationProcess() || There is no data saved in database : " + e);
					}
				}
			}).start();
		} catch (Exception ex) {
			logger.error("IciciRestWebService || premiumCalculationProcess() || Error While getting response from service : " + ex);
		}
		finally {
			NDC.pop();
			NDC.pop();
		}
		return premCalResponse;
	}
	protected int savePremCalcReqRes(IciciFullReqRes fullReqRes) {
		logger.info("IciciRestWebService || saveDlReqRes() || STARTS");
		int count = dbIcici.insertPremCalReqRes(fullReqRes);
		logger.info("IciciRestWebService || saveDlReqRes() || ENDS");
		return count;
	}

	/*
			@author Akshat Vashishtha
			@description Controller which reflects CustomerOnBoardService  details to the caller of (/icici/api/v1/customeronboard)
			@version initial version 1.0
			@since May 2019
	 */

	@RequestMapping(value = "/v1/customeronboard", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CustomerOnBoardResponse customerOnBoard(@RequestBody RequestIBs requestIbs) {
		logger.info(ICICIConstants.CUSTOMER_ON_BOARD_CONTROLLER_CONNECTS);
		
		CustomerOnBoardResponse customerOnBoardResponse = new CustomerOnBoardResponse();
		ObjectMapper om = new ObjectMapper();
		IciciReqRes rq = new IciciReqRes();
		Calendar cal = Calendar.getInstance();
		com.qualtech.icici.api.common.MsgInfo msgInfo = null;
		String uniqueId = UniqueId.getUniqueId();
		final IciciFullReqRes fullReqRes = new IciciFullReqRes();
		CustomerOnBoardServiceHelper serviceHelper = new CustomerOnBoardServiceHelper();
		try{
			
				if (serviceHelper.validateIbsRequest(requestIbs)) {
					CustomerOnBoardRequest customerOnBoardRequest = requestIbs.getPayload().getIciciRequest().getCustomerOnBoard();
					NDC.push("Customer On Board Request : " + uniqueId);
					NDC.push(requestIbs.getHeader().getCorrelationId());
					logger.info(ICICIConstants.REQUEST_JSON+ om.writeValueAsString(customerOnBoardRequest));
					
					rq.setUniqueId(uniqueId);
					rq.setRequest(om.writeValueAsString(customerOnBoardRequest));
					rq.setTag("Customer On Board Request");
					rq.setCorelationid(requestIbs.getHeader().getCorrelationId());
					rq.setAppid(requestIbs.getHeader().getAppId());
					rq.setToken(requestIbs.getHeader().getToken());
					rq.setCreated(cal.getTime());
					customerOnBoardResponse = customerOnBoardService.getCustomerOnBoard(requestIbs,rq);
					msgInfo = customerOnBoardResponse.getMsgInfo();
					
					rq.setStatus(msgInfo.getStatus());
					rq.setResponse(om.writeValueAsString(customerOnBoardResponse));
					rq.setUpdated_time(cal.getTime());
					fullReqRes.setReqRes(rq);
					fullReqRes.setCustomerOnBoardRequest(customerOnBoardRequest);
					fullReqRes.setCustomerOnBoardResponse(customerOnBoardResponse);
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								saveCustomerOnBoardRequestResponse(fullReqRes);
							} catch (Exception e) {
								logger.error("IciciRestWebService || customerOnBoard() || Unable to save in database : " + e);
							}
						}
					}).start();
					
				}else {
					customerOnBoardResponse.setMsgInfo(serviceHelper.ibsBadRequestMsg());
					logger.info(ICICIConstants.IBS_VALIDATION_FAILS);
				}
			
			logger.info(ICICIConstants.RESPONSE_JSON + om.writeValueAsString(customerOnBoardResponse));
		}catch(Exception exception){
			logger.error("IciciRestWebService || customerOnBoard() || Exception Occurs : " + exception);
			if(null == msgInfo){
				msgInfo = serviceHelper.internalError();
			}
			customerOnBoardResponse = new CustomerOnBoardResponse(serviceHelper.getCustomerOnBoard(requestIbs), msgInfo, null);
		}finally {
			
			NDC.pop();
			NDC.pop();
		}
		
		return customerOnBoardResponse;
	}

	protected int saveCustomerOnBoardRequestResponse(IciciFullReqRes fullReqRes) {
		logger.info("IciciRestWebService || saveCustomerOnBoardRequestResponse() || STARTS");
		int count = dbIcici.insertCustomerOnBoardReqRes(fullReqRes);
		logger.info("IciciRestWebService || saveCustomerOnBoardRequestResponse() || ENDS");
		return count;
	}
	
	/*
			@author Akshat Vashishtha
			@description Controller which reflects Welcome kit service details to the caller of (/icici/api/v1/welcomekit)
			@version initial version 1.0
			@since May 2019
	 */

	@RequestMapping(value = "/v1/welcomekit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public WelcomeKitResponse welcomeKit(@RequestBody RequestIBs requestIbs) {
		logger.info(ICICIConstants.WELCOME_KIT_CONTROLLER_CONNECTS);
		
		WelcomeKitResponse welcomeKitResponse = new WelcomeKitResponse();
		ObjectMapper om = new ObjectMapper();
		IciciReqRes rq = new IciciReqRes();
		Calendar cal = Calendar.getInstance();
		com.qualtech.icici.api.common.MsgInfo msgInfo = null;
		String uniqueId = UniqueId.getUniqueId();
		final IciciFullReqRes fullReqRes = new IciciFullReqRes();
		WelcomeKitServiceHelper serviceHelper = new WelcomeKitServiceHelper();
		
		try{
				if (serviceHelper.validateIbsRequest(requestIbs)){
					
					WelcomeKitRequest welcomeKitRequest = requestIbs.getPayload().getIciciRequest().getWelcomeKit();
					NDC.push("Welcome Kit Request : " + uniqueId);
					NDC.push(requestIbs.getHeader().getCorrelationId());
					logger.info(ICICIConstants.REQUEST_JSON+ om.writeValueAsString(welcomeKitRequest));
					
					rq.setUniqueId(uniqueId);
					rq.setRequest(om.writeValueAsString(welcomeKitRequest));
					rq.setTag("Welcome Kit Request");
					rq.setCorelationid(requestIbs.getHeader().getCorrelationId());
					rq.setAppid(requestIbs.getHeader().getAppId());
					rq.setToken(requestIbs.getHeader().getToken());
					rq.setCreated(cal.getTime());
					welcomeKitResponse= welcomeKitService.getWelcomeKit(requestIbs,rq);
					msgInfo = welcomeKitResponse.getMsgInfo();
					rq.setStatus(msgInfo.getStatus());
					rq.setUniqueId(uniqueId);
					rq.setResponse(om.writeValueAsString(welcomeKitResponse));
					rq.setUpdated_time(cal.getTime());
					fullReqRes.setReqRes(rq);
					fullReqRes.setWelcomeKitRequest(welcomeKitRequest);
					fullReqRes.setWelcomeKitResponse(welcomeKitResponse);
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								saveWelcomeKitRequestResponse(fullReqRes);
							} catch (Exception e) {
								logger.error("IciciRestWebService || welcomeKit() || Unable to save in database : " + e);
							}
						}
					}).start();

				}else {
					welcomeKitResponse.setMsgInfo(serviceHelper.ibsBadRequestMsg());
					logger.info("IciciRestWebService || welcomeKit() || Bad Request || IBS Request Validation check FAILS ");
				}
		logger.info(ICICIConstants.RESPONSE_JSON + om.writeValueAsString(welcomeKitResponse));

		}catch(Exception exception){
			logger.error("IciciRestWebService || welcomeKit() || Exception Occurs : " + exception);
			if(null == msgInfo){
				msgInfo = serviceHelper.internalError();
			}
			welcomeKitResponse = new WelcomeKitResponse(serviceHelper.getWelcomeKitHeader(requestIbs), msgInfo, null);
		}finally {
			
			NDC.pop();
			NDC.pop();
		}
		return welcomeKitResponse;
	}

	protected int saveWelcomeKitRequestResponse(IciciFullReqRes fullReqRes) {
		logger.info("IciciRestWebService || saveWelcomeKitRequestResponse() || STARTS");
		int count = dbIcici.insertWelcomeKitReqRes(fullReqRes);
		logger.info("IciciRestWebService || saveWelcomeKitRequestResponse() || ENDS");
		return count;
	}
	
	@RequestMapping(value = "/v1/policystatus", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public PolicyStatusResponse policyStatus(@RequestBody RequestIBs requestIbs) {
		logger.info(ICICIConstants.POLICY_STATUS_CONTROLLER_CONNECTS);
		
		PolicyStatusResponse policyStatusResponse = new PolicyStatusResponse();
		ObjectMapper om = new ObjectMapper();
		IciciReqRes rq = new IciciReqRes();
		Calendar cal = Calendar.getInstance();
		com.qualtech.icici.api.common.MsgInfo msgInfo = null;
		String uniqueId = UniqueId.getUniqueId();
		final IciciFullReqRes fullReqRes = new IciciFullReqRes();
		PolicyStatusServiceHelper serviceHelper = new PolicyStatusServiceHelper();
		
		try{
			
			if (serviceHelper.validateIbsRequest(requestIbs)){
				PolicyStatusRequest policyStatusRequest = requestIbs.getPayload().getIciciRequest().getPolicyStatus();
				NDC.push("Policy Status Request : " + uniqueId);
				NDC.push(requestIbs.getHeader().getCorrelationId());
				logger.info(ICICIConstants.REQUEST_JSON + om.writeValueAsString(policyStatusRequest));
				
				rq.setUniqueId(uniqueId);
				rq.setRequest(om.writeValueAsString(policyStatusRequest));
				rq.setTag("Policy Status Request");
				rq.setCorelationid(requestIbs.getHeader().getCorrelationId());
				rq.setAppid(requestIbs.getHeader().getAppId());
				rq.setToken(requestIbs.getHeader().getToken());
				rq.setCreated(cal.getTime());
				policyStatusResponse = policyStatusService.getPolicyStatus(requestIbs,rq);
				msgInfo = policyStatusResponse.getMsgInfo();
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(policyStatusResponse));
				rq.setUpdated_time(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setPolicyStatusRequest(policyStatusRequest);
				fullReqRes.setPolicyStatusResponse(policyStatusResponse);
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							savePolicyStatusResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("IciciRestWebService || policyStatus() || Unable to save in database : " + e);
						}
					}
				}).start();

			}else {
				policyStatusResponse.setMsgInfo(serviceHelper.ibsBadRequestMsg());
				logger.info("IciciRestWebService || policyStatus() || Bad Request || IBS Request Validation check FAILS ");
			}
		logger.info(ICICIConstants.RESPONSE_JSON + om.writeValueAsString(policyStatusResponse));
		}catch(Exception exception){
			logger.error("IciciRestWebService || policyStatus() || Exception Occurs : " + exception);
			if(null == msgInfo){
				msgInfo = serviceHelper.internalError();
			}
			policyStatusResponse = new PolicyStatusResponse(serviceHelper.getPolicyStatusHeader(requestIbs), msgInfo, null);
		}finally {
			
			NDC.pop();
			NDC.pop();
		}
		
		return policyStatusResponse;
	}

	protected int savePolicyStatusResponse(IciciFullReqRes fullReqRes) {
		logger.info("IciciRestWebService || saveWelcomeKitRequestResponse() || STARTS");
		int count = dbIcici.insertPolicyStatusReqRes(fullReqRes);
		logger.info("IciciRestWebService || saveWelcomeKitRequestResponse() || ENDS");
		return count;
	}
	
}
