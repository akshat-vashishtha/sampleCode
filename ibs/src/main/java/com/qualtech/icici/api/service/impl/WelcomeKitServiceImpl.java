package com.qualtech.icici.api.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.common.ICICIConstants;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.WelcomeKitResponse;
import com.qualtech.icici.api.response.WelcomeKitResponsePayload;
import com.qualtech.icici.api.service.WelcomeKitService;
/*
@author Akshat Vashishtha
@description Service class which calls ICICI API and reflects Welcome kit information
@version initial version 1.0
@since May 2019
*/
@Service
public class WelcomeKitServiceImpl implements WelcomeKitService {

	public static final Logger logger = Logger.getLogger(WelcomeKitServiceImpl.class);

	@Autowired PropertyFile resProp;
	
	@Override
	public WelcomeKitResponse getWelcomeKit(RequestIBs requestIbs, IciciReqRes rq) {

		return getWelcomeKitResponse(requestIbs,rq);
	}

	private WelcomeKitResponse getWelcomeKitResponse(RequestIBs requestIbs, IciciReqRes rq) {

		logger.info(ICICIConstants.WELCOME_KIT_REQUEST + requestIbs.getPayload().getIciciRequest().getWelcomeKit());
		WelcomeKitResponse welcomeKitResponse = new WelcomeKitResponse();
		// Helper class which separates service class code for readability
		WelcomeKitServiceHelper serviceHelper = new WelcomeKitServiceHelper();
		welcomeKitResponse.setHeader(serviceHelper.getWelcomeKitHeader(requestIbs));
		try {

				WelcomeKitResponsePayload responsePayload = serviceHelper.getWelcomeKitPayload(requestIbs.getPayload().getIciciRequest().getWelcomeKit(),rq,resProp);
				welcomeKitResponse.setPayload(responsePayload);

				if (responsePayload != null) {
					welcomeKitResponse.setMsgInfo(serviceHelper.successMsgInfo());
				} else {
					welcomeKitResponse.setMsgInfo(serviceHelper.welcomeKitBackendFails());
					logger.info(ICICIConstants.WELCOME_KIT_VALIDATION_FAILS);
				}
			
		} catch (Exception exception) {
			logger.error(ICICIConstants.GET_WELCOME_KIT_EXCEPTION + exception);
		}
		
		logger.info(ICICIConstants.WELCOME_KIT_RESPONSE + welcomeKitResponse);
		return welcomeKitResponse;
	}
	
	
}
