package com.qualtech.icici.api.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.common.ICICIConstants;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.PolicyStatusResponse;
import com.qualtech.icici.api.response.PolicyStatusResponsePayload;
import com.qualtech.icici.api.service.PolicyStatusService;

/*
@author Akshat Vashishtha
@description Service class which calls ICICI API and reflects Policy Status information
@version initial version 1.0
@since May 2019
*/

@Service
public class PolicyStatusServiceImpl implements PolicyStatusService {

	public static final Logger logger = Logger.getLogger(PolicyStatusServiceImpl.class);

	@Autowired PropertyFile resProp;
	
	@Override
	public PolicyStatusResponse getPolicyStatus(RequestIBs requestIbs, IciciReqRes rq) {

		return getPolicyStatusResponse(requestIbs,rq);
	}
	

	private PolicyStatusResponse getPolicyStatusResponse(RequestIBs requestIbs, IciciReqRes rq) {

		PolicyStatusResponse policyStatusResponse = new PolicyStatusResponse();
		// Helper class which separates service class code for readability
		PolicyStatusServiceHelper serviceHelper = new PolicyStatusServiceHelper();
		policyStatusResponse.setHeader(serviceHelper.getPolicyStatusHeader(requestIbs));
		try {
				PolicyStatusResponsePayload responsePayload = serviceHelper.getPolicyStatusPayload(requestIbs.getPayload().getIciciRequest().getPolicyStatus(),rq,resProp);
				policyStatusResponse.setPayload(responsePayload);
				if (responsePayload != null) {
					policyStatusResponse.setMsgInfo(serviceHelper.successMsgInfo());
				} else {
					policyStatusResponse.setMsgInfo(serviceHelper.policyStatusBackendFails());
					logger.info(ICICIConstants.POLICY_STATUS_VALIDATION_FAILS);
				}
		} catch (Exception exception) {
			logger.error(ICICIConstants.GET_POLICY_STATUS_EXCEPTION + exception);
		}
		
		return policyStatusResponse;
	}
}
