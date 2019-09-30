package com.qualtech.kotak.api.service.impl;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.kotak.api.db.DBKotak;
import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.request.Header;
import com.qualtech.kotak.api.request.KotakInfo;
import com.qualtech.kotak.api.request.KotakReqRes;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakResponsePayload;
import com.qualtech.kotak.api.response.KotakReversalResponse;
import com.qualtech.kotak.api.response.KotakReversalResponsePayload;
import com.qualtech.kotak.api.response.MessageInfo;
import com.qualtech.kotak.api.service.KotakService;
import com.qualtech.kotak.api.utils.KotakSoapRequestXml;
import com.qualtech.kotak.api.utils.StringConstants;
import com.qualtech.kotak.api.utils.WSClient;

@Service
public class KotakServiceImpl implements KotakService {

	private static Logger logger = Logger.getLogger(KotakServiceImpl.class);
	@Autowired
	PropertyFile resProp;
	private static String wsURL = "https://apigwuat.kotak.com:8443/cms_generic_service";
   
	@Autowired
	DBKotak dbkotak;
	
	@Override
	public KotakResponse kotakRequestService(final KotakRequest kotakRequest, KotakReqRes rq) {
		
		logger.info("KotakServiceImpl || kotakRequestService || START");
		KotakResponse kotakResponse=new KotakResponse();
		KotakSoapRequestXml soapXml=new KotakSoapRequestXml();
		String responseJson= null;
		JSONObject convertedJson;
		KotakInfo info=new KotakInfo();
		MessageInfo msgInfo = new MessageInfo();
		String apiURL =resProp.getString("com.kotak.3rdParty.url");
		try 
		{
			if (kotakRequest != null) 
			{
				if (kotakRequest.getPayload() != null) 
				{
					boolean result = checkValidation(kotakRequest.getHeader());
					if (result == true) 
					{
						String xmlInputPayment=soapXml.getRequestXml(kotakRequest);
						String contentTypePayment=resProp.getString("com.kotak.payment.action");
						String response = null;
						try 
						{
							String hardCodedEnv="N";
							ResourceBundle res=null;
							try 
							{
								res = ResourceBundle.getBundle("hardcode");
								hardCodedEnv = resProp.getString("com.ibs.allapi.demo.development");	
								logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
							}
							catch(Exception ex)
							{
								hardCodedEnv="N";
							}
							if(hardCodedEnv.equalsIgnoreCase("N"))
							{
								info=new WSClient().callSoapService(xmlInputPayment, apiURL,contentTypePayment);
								response = info.getResponse();
							}
							else
							{
								response = res.getString("KOTAK_PAYMENT_RESPONSE");
								info.setResponse(response);
								info.setResponseCode(200);
							}
						}
						catch(Exception e)
						{ 
							logger.info("KotakServiceImpl || kotakRequestService() || Calling via wsclient Exception : "+e);
						}
						try 
						{
							String price=null;
							rq.setIntReq(xmlInputPayment);
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode()==200)
							{
								price=resProp.getString("com.kotak.payment.success.price");
							}
							else 
							{
								price=resProp.getString("com.kotak.payment.failure.price");
							}
							rq.setPrice(price);
						} 
						catch (Exception e)
						{
							logger.error("KotakServiceImpl || kotakRequestService() || Error while updating KOTAK internal req res:" + e);
						}

						if (info.getResponseCode() == 200)
						{
							msgInfo.setCode(""+info.getResponseCode());
							if (!info.getResponse().toString().contains("Invalid Input"))
							{

								if (!info.getResponse().toString().contains("Max retries exceeded"))
								{

									try 
									{
										response=info.getResponse().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://www.w3.org/2003/05/soap-envelope\"><SOAP-ENV:Body>", "")
												.replace("<ns0:Payment xmlns:ns0=\"http://www.kotak.com/schemas/CMS_Generic/Payment_Response.xsd\">", "")
												.replace("</ns0:Payment>", "")
												.replace("</SOAP-ENV:Body></SOAP-ENV:Envelope>", "")
												.replace("ns0:", "");
										convertedJson = org.json.XML.toJSONObject(response);
										responseJson=convertedJson.toString();

										responseJson = responseJson.replaceAll("StatusRem", "statusRem")
												.replaceAll("StatusCd", "statusCd")
												.replaceAll("MessageId", "messageId")
												.replaceAll("AckHeader", "ackHeader")
												.replaceAll("FaultList", "faultList")
												.replaceAll("Fault", "fault")
												.replaceAll("Code", "code")
												.replaceAll("Reason", "reason");
										ObjectMapper om = new ObjectMapper();
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										kotakResponse.setPayload(om.readValue(responseJson, KotakResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (kotakResponse.getPayload() != null) 
										{
											kotakResponse.getPayload().setStatus_code(info.getResponseCode());
											if (kotakResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(kotakResponse.getPayload().getStatus_code());

											}
										}

										kotakResponse.getPayload().setStatus_msg(statusmsg);

										if (kotakResponse.getPayload().getStatus_code() == 200) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(kotakResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + kotakResponse.getPayload().getStatus_code());
										}

										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(kotakResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + kotakResponse.getPayload().getStatus_code());
										}


									}
									catch (Exception ex) 
									{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
										logger.error("KotakServiceImpl || kotakRequestService() || Exception occured : "+ex);
									}
									catch(Throwable t)
									{
										logger.error("KotakServiceImpl || kotakRequestService() || Error Occured : High Priority : Check it : "+t);
									}
								}
								else 
								{
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									msgInfo.setCode(""+104);
								}
							}
							else 
							{
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}

						}
						else 
						{
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} 
					else
					{
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						msgInfo.setCode(""+401);
					}
				}
				else 
				{
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					msgInfo.setCode(""+400);
				}

			} 
			else
			{
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				msgInfo.setCode(""+400);
			}
		} 
		catch (Exception e)
		{
			logger.error("KotakServiceImpl || kotakRequestService() || We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}
		kotakResponse.setMsgInfo(msgInfo);
		kotakResponse.setHeader(kotakRequest.getHeader());
		return kotakResponse;
	}

	@Override
	public KotakReversalResponse kotakReversalRequestService(final KotakRequestReversal kotakRequest,KotakReqRes rq) 
	{
		logger.info("KotakServiceImpl || kotakRequestService || START");
		KotakReversalResponse kotakResponse=new KotakReversalResponse();
		KotakSoapRequestXml soapXml=new KotakSoapRequestXml();
		String responseJson= null;
		JSONObject convertedJson;
		KotakInfo info=new KotakInfo();
		MessageInfo msgInfo = new MessageInfo();
		String apiURL =resProp.getString("com.kotak.3rdParty.url");
		try 
		{
			if (kotakRequest != null)
			{
				if (kotakRequest.getPayload() != null)
				{
					boolean result = checkValidation(kotakRequest.getHeader());
					if (result == true) {

						String xmlInputReversal=soapXml.getReversalrequest(kotakRequest);
						String contentTypeReversal=resProp.getString("com.kotak.reversal.action");
						String response = null;
						try
						{

							String hardCodedEnv="N";
							ResourceBundle res=null;
							try 
							{
								res = ResourceBundle.getBundle("hardcode");
								hardCodedEnv = resProp.getString("com.ibs.allapi.demo.development");	
								logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
							}
							catch(Exception ex)
							{
								hardCodedEnv="N";
							}
							if(hardCodedEnv.equalsIgnoreCase("N"))
							{
								info=new WSClient().callSoapService(xmlInputReversal, apiURL,contentTypeReversal);
								response = info.getResponse();
							}
							else
							{
								response = res.getString("KOTAK_REVERSAL_RESPONSE");
								info.setResponse(response);
								info.setResponseCode(200);
							}
						}
						catch(Exception e)
						{ 
							logger.error("KotakServiceImpl || kotakRequestService || Exception occured while accesing Sys time : "+e);
						}
						try
						{
							String price=null;
						    rq.setIntReq(xmlInputReversal);
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode()==200)
							{
								price=resProp.getString("com.kotak.reversal.success.price");
							}
							else
							{
								price=resProp.getString("com.kotak.reversal.failure.price");
							}
							rq.setPrice(price);
						}
						catch (Exception e) 
						{
							logger.error("KotakServiceImpl || kotakRequestService || Error while updating KOTAK internal req res:" + e);
						}
                        
						if (info.getResponseCode() == 200)
						{    
							msgInfo.setCode(""+info.getResponseCode());
							if (!info.getResponse().toString().contains("Invalid Input"))
							{
								

								if (!info.getResponse().toString().contains("Max retries exceeded"))
								{

									try 
									{
										response=info.getResponse().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "")
												.replace("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://www.w3.org/2003/05/soap-envelope\"><SOAP-ENV:Body>", "")
												.replace("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://www.w3.org/2003/05/soap-envelope\">", "")
												.replace("<SOAP-ENV:Body>", "")
												.replace("<ns0:Reversal xmlns:ns0=\"http://www.kotak.com/schemas/CMS_Generic/Reversal_Response.xsd\">", "")
												.replace("xmlns:ns1=\"http://www.w3.org/2001/XMLSchema-instance\"", "></ns0:UTR>")
												.replace("ns1:nil=\"true\"", "")
												.replace("</ns0:Reversal>", "")
												.replace("</SOAP-ENV:Body>", "")
												.replace("</SOAP-ENV:Envelope>", "")
												.replace("ns0:", "");

										convertedJson = org.json.XML.toJSONObject(response);
										responseJson=convertedJson.toString();

										responseJson = responseJson.replaceAll("Msg_Id", "messageId")
												.replaceAll("Status_Code", "statusCode")
												.replaceAll("Status_Desc", "statusDescription")
												.replaceAll("Rev_Detail", "reversalDetail")
												.replaceAll("Details", "reversalDetails")
												.replaceAll("Header", "reversalHeader")
												.replaceAll("Msg_Src", "msgSource")
												.replaceAll("UTR", "utr")
												.replaceAll("Client_Code", "clientCode")
												.replaceAll("Req_Id", "requestId")
												.replaceAll("Date_Post", "datePost");


										ObjectMapper om=new ObjectMapper();
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										kotakResponse.setPayload(om.readValue(responseJson, KotakReversalResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (kotakResponse.getPayload() != null) 
										{
											kotakResponse.getPayload().setStatus_code(info.getResponseCode());
											if (kotakResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(kotakResponse.getPayload().getStatus_code());

											}
										}

										kotakResponse.getPayload().setStatus_msg(statusmsg);

										if (kotakResponse.getPayload().getStatus_code() == 200) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(kotakResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + kotakResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(kotakResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + kotakResponse.getPayload().getStatus_code());
										}

                                         	
									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.error("Failure from 3rd Party API : " + info.getResponse());
										logger.error("KotakServiceImpl || kotakRequestService || Exception occured : "+ex);
									}
									catch(Throwable t)
									{
										logger.error("KotakServiceImpl || kotakRequestService || Error Occured : High Priority : Check it : "+t);
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.error("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				msgInfo.setCode(""+400);
			}
		} 
		catch (Exception e) 
		{
			logger.error("KotakServiceImpl || kotakRequestService || Exception occurs : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		kotakResponse.setMsgInfo(msgInfo);
		kotakResponse.setHeader(kotakRequest.getHeader());
		return kotakResponse;
	}

	private boolean checkValidation(Header header) {
		if (header != null) {
			AuthValidator auth = dbkotak.validateAuth(header);
			if (auth.getAppid() == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	private String getStatusMsg(int i) {
		String msg = null;
		if (i != 0) {

			if (i == 200) {
				msg = "Valid Authentication";

			} else if (i == 102) {
				msg = "Invalid ID number or combination of inputs";

			} else if (i == 103) {
				msg = "No records found for the given ID or combination of inputs";

			} else if (i == 104) {
				msg = "Max retries exceeded";

			} else if (i == 105) {
				msg = "Missing Consent";

			} else {
				msg = "Error in status message.";
			}

		} else {
			msg = "Error in status message.";
		}
		return msg;
	}


	private MessageInfo getHttpMessage(int i) {
		MessageInfo info=new MessageInfo();

		if (i != 0) {

			if (i == 200) {
				info.setStatus("OK"); 
				info.setCode(""+i);
				info.setMessage("Request Successful");


			} else if (i == 400) {
				info.setStatus("Bad Request"); 
				info.setCode(""+i);
				info.setMessage("Mandatory fields are missing / invalid");
				//info.setInternalCode(""+i);

			} else if (i == 401) {
				info.setStatus("Unauthorized Access"); 
				info.setCode(""+i);
				info.setMessage("API Key is missing or invalid.");
				//info.setInternalCode(""+i);

			} else if (i == 402) {
				info.setStatus("Insufficient Credits"); 
				info.setCode(""+i);
				info.setMessage("Credits to access the APIs expired.");
				//info.setInternalCode(""+i);

			} else if (i == 500) {
				info.setStatus("Internal Server Error"); 
				info.setCode(""+i);
				info.setMessage("Internal processing error of Kotak.");
				//info.setInternalCode(""+i);

			} else if (i == 503) {
				info.setStatus("Source Unavailable"); 
				info.setCode(""+i);
				info.setMessage("The source for authentication is down for maintenance or inaccessible.");
				//info.setInternalCode(""+i);

			} 
			else if (i == 504) {
				info.setStatus("Endpoint Request Timed Out"); 
				info.setCode(""+i);
				info.setMessage("The response latency from the source for authentication is >30sec.");
				//info.setInternalCode(""+i);

			}else{
				info.setStatus(StringConstants.FAILURE.toString());
				info.setMessage(StringConstants.MESSAGE500.toString());
				//info.setInternalCode(StringConstants.C_500.toString());
				info.setCode(""+i);
				//info.setInternalCode(""+i);
			}

		}

		return info;
	}

	
}
