package com.qualtech.icici.api.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.common.ICICIConstants;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.CustomerOnBoardPayload;
import com.qualtech.icici.api.response.CustomerOnBoardResponse;
import com.qualtech.icici.api.service.CustomerOnBoardService;

/*
@author Akshat Vashishtha
@description Service class which calls ICICI API and reflects Customer On Board information
@version initial version 1.0
@since May 2019
*/
@Service
public class CustomerOnBoardServiceImpl implements CustomerOnBoardService {

	public static final Logger logger = Logger.getLogger(CustomerOnBoardServiceImpl.class);

	@Autowired PropertyFile resProp;
	
	public CustomerOnBoardResponse getCustomerOnBoard(RequestIBs requestIbs, IciciReqRes rq)
	{
		return getCustomerOnBoardResponse(requestIbs,rq);
	}

	private CustomerOnBoardResponse getCustomerOnBoardResponse(RequestIBs requestIbs, IciciReqRes rq) {

		CustomerOnBoardResponse customerOnBoardResponse = new CustomerOnBoardResponse();
		// Helper class which separates service class code for readability
		CustomerOnBoardServiceHelper serviceHelper = new CustomerOnBoardServiceHelper();
		customerOnBoardResponse.setHeader(serviceHelper.getCustomerOnBoard(requestIbs));
		try {

				CustomerOnBoardPayload responsePayload = serviceHelper.getCustomerOnBoardPayload(requestIbs.getPayload().getIciciRequest().getCustomerOnBoard(),rq,resProp);
				customerOnBoardResponse.setPayload(responsePayload);

				if (responsePayload != null) {
					customerOnBoardResponse.setMsgInfo(serviceHelper.successMsgInfo());
				}
				else {
					customerOnBoardResponse.setMsgInfo(serviceHelper.customerOnBoardBackendFails());
					logger.info(ICICIConstants.CUSTOMER_ON_BOARD_BACKEND_FAILS);
				}

		} catch (Exception exception) {
			logger.error(ICICIConstants.GET_CUSTOMER_ON_BOARD_EXCEPTION + exception);
		}
		
		return customerOnBoardResponse;
	}
	
	
}
