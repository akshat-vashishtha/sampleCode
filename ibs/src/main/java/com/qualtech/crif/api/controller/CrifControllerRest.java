package com.qualtech.crif.api.controller;

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

import com.qualtech.crif.api.dto.ERRORSTATUS;
import com.qualtech.crif.api.dto.ErrorInfo;
import com.qualtech.crif.api.request.CriffApiRequest;
import com.qualtech.crif.api.response.CriffApiResponse;
import com.qualtech.crif.api.service.CriffMVCService;
import com.qualtech.crif.api.service.CriffService;

@Controller
@RequestMapping("/crif/api")
public class CrifControllerRest {
	Logger logger = Logger.getLogger(CrifControllerRest.class.getName());

	@Autowired
	CriffMVCService criffmvcservice;
	@Autowired
	CriffService criffService;

	@RequestMapping(value = "/v1/crifrequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CriffApiResponse criffRequestAction(@RequestBody CriffApiRequest criffApiRequest) {

		CriffApiResponse criffApiResponse = new CriffApiResponse();
		ErrorInfo errorInfo = new ErrorInfo();
		try {
			NDC.push("CriffRequest : " + criffApiRequest.getHeader().getCorrelationId());
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			logger.info(" CrifControllerRest || Controller STARTS || RequestJson :: : "
					+ ow.writeValueAsString(criffApiRequest));
		} catch (Exception e) {
			logger.error("Exception in CrifControllerRest || criffRequestAction() || mapping request to JSON :: " + e);
		}
		try {
			logger.info("Service Call || criffService() ");
			criffApiResponse = criffService.criffService(criffApiRequest, "CONSUMER");
		} catch (Exception e) {
			errorInfo.setCode("500");
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage("Service unable to process request");
			errorInfo.setDescription("Service unable to process request");
			criffApiResponse.setErrorInfo(errorInfo);
			logger.error("CrifControllerRest || criffRequestAction() || Exception in SERVICE Call :: " + e);
		}
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			logger.info(" CrifControllerRest || criffRequestAction() || Controller ENDS || API_RESPONSE ::"
					+ ow.writeValueAsString(criffApiResponse));
		} catch (Exception e) {
			logger.error("Exception in CrifControllerRest || criffRequestAction() || mapping response to JSON :: " + e);
		} finally {
			logger.info(" CrifControllerRest || Controller ENDS");
			NDC.pop();
		}
		return criffApiResponse;
	}

	@RequestMapping(value = "/v1/crifrequestMFI", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	@ResponseBody
	public CriffApiResponse criffRequestActionforMFI(@RequestBody CriffApiRequest criffApiRequest) {

		CriffApiResponse criffApiResponse = new CriffApiResponse();
		ErrorInfo errorInfo = new ErrorInfo();
		try {
			NDC.push("CriffRequest : " + System.currentTimeMillis());
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			logger.info(" CrifControllerRest ||  Controller STARTS || Request in Json format : "
					+ ow.writeValueAsString(criffApiRequest));
		} catch (Exception e) {
			logger.error(
					"Exception in CrifControllerRest || criffRequestActionforMFI() || mapping request to JSON :: " + e);
		}
		try {
			logger.info("Service Call || criffService() ");
			criffApiResponse = criffService.criffService(criffApiRequest, "MFI");
		} catch (Exception e) {
			errorInfo.setCode("500");
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setMessage("Service unable to process request");
			errorInfo.setDescription("Service unable to process request");
			criffApiResponse.setErrorInfo(errorInfo);

			logger.error("CrifControllerRest || criffRequestActionforMFI() || Exception in SERVICE Call :: " + e);
		}
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			logger.info(" CrifControllerRest || criffRequestActionforMFI() || Controller ENDS || API_RESPONSE ::"
					+ ow.writeValueAsString(criffApiResponse));
		} catch (Exception e) {
			logger.error("Exception in CrifControllerRest || criffRequestActionforMFI() || mapping response to JSON :: "
					+ e);
		} finally {
			NDC.pop();
		}
		return criffApiResponse;
	}

}
