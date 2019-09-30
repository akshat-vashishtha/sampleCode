package com.qualtech.icici.api.common;

/*
	@author Akshat Vashishtha
	@description Constant class for ICICI service code implementation
	@version initial version 1.0
	@since May 2019
*/

public class ICICIConstants {
	
	private ICICIConstants(){
		
	}
	
	// Success
	public static final String SUCCESS_CODE = "200";
	public static final String SUCCESS_MESSAGE = "Response Generated";
	public static final String SUCCESS_DESCRIPTION = "Service is working";
	public static final String SUCCESS_STATUS = "SUCCESS";
	
	// Bad Request
	public static final String BAD_REQUEST_CODE = "400";
	public static final String BAD_REQUEST_MESSAGE = "Invalid | Bad Request";
	public static final String BAD_REQUEST_DESCRIPTION_IBS = "IBS Request, Validation check FAILS ";
	public static final String BAD_REQUEST_DESCRIPTION_WELCOME_KIT = "ICICI Welcome kit Request, Validation check FAILS ";
	public static final String BAD_REQUEST_DESCRIPTION_CUSTOMER_ON_BOARD = "ICICI Customer on board Request, Validation check FAILS ";
	public static final String BAD_REQUEST_STATUS = "FAILURE";
	
	public static final String BACKEND_FAIL_CODE = "600";
	public static final String BACKEND_FAIL_DESCRIPTION_CUSTOMER_ON_BOARD = "ICICI Customer on board Request, third party backend API FAILS ";
	public static final String BACKEND_FAIL_MESSAGE = "Backend API FAILS";
	public static final String BACKEND_FAIL_STATUS = "FAILURE";
	
	// Internal Error
	public static final String INTERNAL_ERROR_CODE = "500";
	public static final String INTERNAL_ERROR_DESCRIPTION = "INTERNAL ERROR,SOME ISSUE OCCURES AT SERVICE";
	public static final String INTERNAL_ERROR_MESSAGE = "SERVICE FAILS";
	public static final String INTERNAL_ERROR_STATUS = "FAILURE";
	
	// Loggers
	public static final String WELCOME_KIT_CONTROLLER_CONNECTS = " IciciRestWebService || welcomeKit(RequestIBs) ||  Connects ";
	public static final String WELCOME_KIT_VALIDATION_FAILS = "WelcomeKitServiceImpl || getWelcomeKitResponse(RequestIBs) || Bad Request || Welcome kit Request Validation check FAILS ";
	public static final String IBS_VALIDATION_FAILS = "IciciRestWebService || customerOnBoard() || Bad Request || IBS Request Validation check FAILS ";
	public static final String GET_WELCOME_KIT_EXCEPTION = "WelcomeKitServiceImpl || getWelcomeKit(RequestIBs) || Exception :: ";
	
	public static final String VALIDATE_IBS_EXCEPTION = "WelcomeKitServiceHelper || validateIbsRequest(RequestIBs) || Exception :: ";
	public static final String WELCOME_KIT_PAYLOAD_EXCEPTION = "WelcomeKitServiceHelper || getWelcomeKitPayload(WelcomeKitRequest) || Exception :: ";
	public static final String VALIDATE_WELCOME_KIT_EXCEPTION = "WelcomeKitServiceHelper || validateWelcomeKitRequest(WelcomeKitRequest) || Exception :: ";

	public static final String WELCOME_KIT_REQUEST = "WelcomeKitServiceImpl || getWelcomeKitResponse(RequestIBs) ||  Welcome kit Request :: ";
	public static final String WELCOME_KIT_RESPONSE = "WelcomeKitServiceImpl || getWelcomeKitResponse(RequestIBs) ||  Welcome kit Response :: ";
	
	// Customer on Board
	public static final String CUSTOMER_ON_BOARD_CONTROLLER_CONNECTS = " IciciRestWebService || customerOnBoard(RequestIBs) ||  Connects ";
	public static final String CUSTOMER_ON_BOARD_VALIDATION_FAILS = "CustomerOnBoardServiceImpl || getCustomerOnBoardResponse(RequestIBs) || Bad Request || Customer On Board Request Validation check FAILS ";
	public static final String GET_CUSTOMER_ON_BOARD_EXCEPTION = "CustomerOnBoardServiceImpl || getCustomerOnBoardResponse(RequestIBs) || Exception :: ";
	public static final String VALIDATE_IBS_CUSTOMER_ON_BOARD_EXCEPTION = "CustomerOnBoardServiceHelper || validateIbsRequest(RequestIBs) || Exception :: ";
	public static final String CUSTOMER_ON_BOARD_PAYLOAD_EXCEPTION = "CustomerOnBoardServiceHelper || getCustomerOnBoardPayload(CustomerOnBoardRequest) || Exception :: ";
	public static final String VALIDATE_CUSTOMER_ON_BOARD_EXCEPTION = "CustomerOnBoardServiceHelper || validateCustomerOnBoardRequest(CustomerOnBoardRequest) || Exception :: ";
	public static final String CUSTOMER_ON_BOARD_BACKEND_FAILS = " CustomerOnBoardServiceHelper || getCustomerOnBoardPayload(CustomerOnBoardRequest) || Backend API fails || ICICI InternalResponse is null or responseCode is not equals to \"200\" ";
	
	// Policy Status Loggers
		public static final String POLICY_STATUS_CONTROLLER_CONNECTS = " PolicyStatusController || PolicyStatus(RequestIBs) ||  Connects ";
		public static final String POLICY_STATUS_VALIDATION_FAILS = "PolicyStatusServiceImpl || getPolicyStatusResponse(RequestIBs) || Bad Request || Policy Status Request Validation check FAILS ";
		public static final String POLICY_STATUS_IBS_FAILS = "PolicyStatusServiceImpl || getPolicyStatusResponse(RequestIBs) || Bad Request || IBS Request Validation check FAILS ";
		public static final String GET_POLICY_STATUS_EXCEPTION = "PolicyStatusServiceImpl || getPolicyStatus(RequestIBs) || Exception :: ";
		public static final String POLICY_STATUS_PAYLOAD_EXCEPTION = "PolicyStatusServiceHelper || getPolicyStatusPayload(PolicyStatusRequest) || Exception :: ";
		public static final String VALIDATE_POLICY_STATUS_EXCEPTION = "PolicyStatusServiceHelper || validatePolicyStatusRequest(PolicyStatusRequest) || Exception :: ";
		public static final String BAD_REQUEST_DESCRIPTION_POLICY_STATUS = "ICICI Policy Status Request, Validation check FAILS ";
		public static final String POLICY_STATUS_IBS_EXCEPTION = "PolicyStatusServiceHelper || validateIbsRequest(RequestIBs) || Exception :: ";
		public static final String POLICY_STATUS_BACKEND_FAILS = " PolicyStatusServiceHelper || getPolicyStatusPayload(PolicyStatusRequest || Backend API fails || ICICI InternalResponse is null or responseCode is not equals to \"200\" ";

		public static final String RESPONSE_CODE = "responseCode";
		public static final String RESPONSE_DATA = "responseData";
		public static final String REQUEST_JSON = "RequestJson :: ";
		public static final String RESPONSE_JSON = "Response Json :: ";
		
	// Welcome Kit Url
	public static final String WELCOME_KIT_URL_PREFIX = "com.qualtech.icici.Request.welcomeKit.url.prefix";
	public static final String WELCOME_KIT_URL_API_KEY = "com.qualtech.icici.Request.welcomeKit.url.apiKey";

}


























