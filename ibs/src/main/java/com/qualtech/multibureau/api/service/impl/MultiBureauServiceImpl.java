package com.qualtech.multibureau.api.service.impl;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.db.DBBureau;
import com.qualtech.multibureau.api.request.BureauReqRes;
import com.qualtech.multibureau.api.request.Header;
import com.qualtech.multibureau.api.response.BureauResponse;
import com.qualtech.multibureau.api.response.BureauResponsePayload;
import com.qualtech.multibureau.api.response.BureauResult;
import com.qualtech.multibureau.api.response.MessageInfo;
import com.qualtech.multibureau.api.service.MultiBureauService;
import com.qualtech.multibureau.api.util.ApiMapping;
import com.qualtech.multibureau.api.util.HttpCallMultiBureau;
import com.qualtech.multibureau.api.util.HttpInfo;
import com.qualtech.multibureau.api.util.StringConstants;
import com.qualtech.multibureau.api.util.TestingApi;

@Service
public class MultiBureauServiceImpl implements MultiBureauService 
{

	static Logger logger = Logger.getLogger(MultiBureauServiceImpl.class.getName());
	@Autowired
	PropertyFile resProp;
	@Autowired
	DBBureau dbBureau;
	private HttpCallMultiBureau httpCall = new HttpCallMultiBureau();

	@Override
	public BureauResponse bureauService(final BureauRequest req, BureauReqRes reqRes) 
	{

		logger.info("Inside in bureau Service method:");

		BureauResponse bResponse = new BureauResponse();
		BureauResponsePayload payload = new BureauResponsePayload();
		MessageInfo msgInfo = new MessageInfo();
		
				//String request_type="Issue";
				//String source_system="DIRECT";
				
				
//		String firstCallWait="7000";/// from db
//		String numberOfRetry="5";/// from db
//		String retryWaitTime="5000";/// from db

		ApiMapping mappping = new ApiMapping();
		try {
			String instituationID = resProp.getString("com.bureau.institution.id");
			String aggregatorID = resProp.getString("com.bureau.aggregator.id");
			String memberID = resProp.getString("com.bureau.member.id");
			String pswd = resProp.getString("com.bureau.password");
			String caseno=resProp.getString("com.bureau.case.no");
			String urlSave = resProp.getString("com.bureau.url");
			String hardCodedEnv = resProp.getString("com.bureau.demo.development");
			HttpInfo info = new HttpInfo();
					String firstCallWait=resProp.getString("com.bureau.delay.time.firstcallwait");
					String numberOfRetry=resProp.getString("com.bureau.delay.time.maxretry");
					String retryWaitTime=resProp.getString("com.bureau.delay.time.retrywait");
					
			 		String request_type=resProp.getString("com.bureau.request.type.Issue");
				    String source_system=resProp.getString("com.bureau.source_system.direct");

				if (req != null && req.getPayload() != null && req.getPayload().getApplication_id() != null
						&& !req.getPayload().getApplication_id().equals("") && req.getPayload().getLoan_Type() != null
						&& !req.getPayload().getLoan_Type().equals("") && req.getPayload().getFirstName() != null
						&& !req.getPayload().getFirstName().equals("") && req.getPayload().getBirth_dt() != null
						&& !req.getPayload().getBirth_dt().equals("")) 
				{
					boolean result = checkValidation(req.getHeader());
					if (result) {
 						ObjectMapper om = new ObjectMapper();

						logger.info("while calling getFirstApi method");
						String valueofFirst = mappping.getFirstApi(req,resProp);
						logger.info("End to getFirstApi method" + valueofFirst);

						JSONObject json=null;
						if (hardCodedEnv.equalsIgnoreCase("N")) 
						{
							String res = "{\"request_id\": \"f76b5271-1792-11e8-bed1-c912f8982ea1\", \"result\": {\"dl\": {\"issue_date\": \"15-07-2005\", \"father/husband\": \"VIJAI RAJ  SAMDARIA\", \"name\": \"GAURAV  SAMDARIA\", \"img\": \"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\", \"blood_group\": \"U\", \"dob\": \"12-06-1987\", \"validity\": {\"non-transport\": \"15-07-2005 to 14-07-2025\", \"transport\": \"\"}, \"cov_details\": [{\"issue_date\": \"15-07-2005 RTO,MUMBAI CENTRAL\", \"cov\": \"LMV\"}], \"address\": \"1302 13 TH FLOOR, ORCHID 18, B.G.KHER ROAD, WORLI NAKA MUMBAI. 400018\"}, \"name\": {\"score\": 0.09898989898989899, \"match\": false}}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);

						}
						else 
						{

							info = httpCall.httpCallMB(valueofFirst, urlSave, instituationID, aggregatorID, memberID,
									pswd);
						}
						try {
							reqRes.setIntReq(valueofFirst);
							reqRes.setIntRes(info.getResponse());
						} catch (Exception e) {
							logger.error("Found exception to set first api internal request response : "+e);
						}
						if (info.getResponseCode() == 200) 
						{
							String fromFirstApi = info.getResponse();
							if(fromFirstApi!=null && !fromFirstApi.isEmpty())
							{
								json = new JSONObject(fromFirstApi);
							}
							String status = "", cusId = "", appId = "", requestTime = "", acknowledgementIdValue = "",fromSecondApi="";
							
							if (json!=null && json.has("ACKNOWLEDGEMENT-ID") && json.has("STATUS")
									&& json.has("HEADER")) 
							{
								status = json.get("STATUS") + "";
								acknowledgementIdValue = json.get("ACKNOWLEDGEMENT-ID") + "";
								JSONObject headerValue = json.getJSONObject("HEADER");
								if(headerValue!=null && headerValue.has("REQUEST-RECEIVED-TIME")
										&& headerValue.has("APPLICATION-ID")
										&& headerValue.has("CUST-ID")) {
									requestTime = headerValue.getString("REQUEST-RECEIVED-TIME");
									appId = headerValue.getString("APPLICATION-ID");
									cusId = headerValue.getString("CUST-ID");
								}
								
								
							}
							if (caseno!=null && status.equalsIgnoreCase("SUCCESS")) 
							{
								logger.info("while calling getSecondApi method");
								String valueofSecond="";
								String allValidRes="\"01\",\"02\",\"03\",\"04\"";
								logger.info("caseno value = "+caseno);
								int num=Integer.parseInt(caseno);
								switch (num) 
								{
								case 01:
									//for HTML
									valueofSecond = mappping.getSecondApi(cusId, appId, requestTime,
											acknowledgementIdValue,"\"01\"",request_type,source_system);
									break;
								case 02:
									//for PDF
									valueofSecond = mappping.getSecondApi(cusId, appId, requestTime,
											acknowledgementIdValue,"\"02\"",request_type,source_system);
									break;
								case 03:
									//for BUREAU-STRING
									valueofSecond = mappping.getSecondApi(cusId, appId, requestTime,
											acknowledgementIdValue,"\"03\"",request_type,source_system);
									break;	
								case 04:
									//for RESPONSE-JSON-OBJECT
									valueofSecond = mappping.getSecondApi(cusId, appId, requestTime,
											acknowledgementIdValue,"\"04\"",request_type,source_system);
									break;	
								case 05:	
									//for PDF-ONLY
									valueofSecond = mappping.getSecondApi(cusId, appId, requestTime,
											acknowledgementIdValue,"\"05\"",request_type,source_system);
									break;	
								case 06:	
									//for HTML-ONLY
									valueofSecond = mappping.getSecondApi(cusId, appId, requestTime,
											acknowledgementIdValue,"\"06\"",request_type,source_system);
									break;		
								default:
									//for All
									valueofSecond = mappping.getSecondApi(cusId, appId, requestTime,
											acknowledgementIdValue,allValidRes,request_type,source_system);
									break;
								}

								logger.info("End to getSecondApi method" + valueofSecond);
								logger.info("numberOfRetry value = " +numberOfRetry);
								int maxretry = Integer.parseInt(numberOfRetry);
								for(int callCounter=1;callCounter<=maxretry;callCounter++)
								{
									try 
									{
										if(callCounter==1)
										{
											logger.info("Call No : "+callCounter+" : End : wait time : "+firstCallWait+" Sec");
											Thread.sleep(Integer.parseInt(firstCallWait));
											logger.info("Call No : "+callCounter+" : End : wait time : "+firstCallWait+" Sec");
										}
										else
										{
											logger.info("Call No : "+callCounter+" : End : wait time : "+retryWaitTime+" Sec");
											Thread.sleep(Integer.parseInt(retryWaitTime));
											logger.info("Call No : "+callCounter+" : End : wait time : "+retryWaitTime+" Sec");
										}
									}
									catch(InterruptedException ex) 
									{   
										logger.info("Call No : "+callCounter+" : Intrupted : "+ex);
										Thread.currentThread().interrupt();
									}

									info = httpCall.httpCallMB(valueofSecond, urlSave, instituationID, aggregatorID,memberID, pswd);
									try {
										reqRes.setIntReq(reqRes.getIntReq()+"\n|||||\n"+valueofSecond);
										reqRes.setIntRes(reqRes.getIntRes()+"\n|||||\n"+info.getResponse());
									} catch (Exception e) {
										logger.error("Found exception to set second api internal request response : "+e);
									}
									if(info.getResponseCode()==200 && info.getResponse()!=null) {
										fromSecondApi = info.getResponse();
										if(fromSecondApi!=null && !fromSecondApi.isEmpty())
										{
											json = new JSONObject(fromSecondApi);
										}
										status="";
										if(json!=null && json.has("STATUS")) {
											status = json.get("STATUS") + "";
										}
										
										msgInfo.setCode("" + info.getResponseCode());

										if (!status.equalsIgnoreCase("ERRORS")) 
										{

											if (!status.equalsIgnoreCase("NO-HIT")) 
											{

												try 
												{
													if(status.equalsIgnoreCase("IN-PROCESS"))
													{
														if(callCounter!=maxretry)
														{
															continue;
														}
													}
													JSONObject jsonExtract=new JSONObject(info.getResponse());
													if(jsonExtract!=null) {
														jsonExtract = TestingApi.keysToLowerCase(jsonExtract);
														BureauResult bureauResult = om.readValue(jsonExtract.toString(),BureauResult.class);
														payload.setResult(bureauResult);
														bResponse.setPayload(payload);
														if (bResponse.getPayload() != null && bResponse.getPayload().getResult()!=null
																&& bResponse.getPayload().getResult().getAcknowledgement_id() != null
																&& bResponse.getPayload().getResult().getStatus() != null) 
														{
																if (bResponse.getPayload().getResult().getStatus().equalsIgnoreCase("COMPLETED")) 
																{
																	msgInfo.setStatus(StringConstants.SUCCESS.toString());
																	msgInfo.setMessage(bResponse.getPayload().getResult().getStatus());
																}
																else if (bResponse.getPayload().getResult().getStatus().equalsIgnoreCase("IN-PROCESS"))
																{
																	msgInfo.setStatus(StringConstants.SUCCESS.toString());
																	msgInfo.setMessage(bResponse.getPayload().getResult().getStatus());
																}
																else
																{
																	msgInfo.setStatus(StringConstants.SUCCESS.toString());
																	msgInfo.setMessage(bResponse.getPayload().getResult().getStatus());
																}
														}
													}
													break;
												} 
												catch (Exception ex) 
												{
													msgInfo.setStatus(StringConstants.FAILURE.toString());
													msgInfo.setMessage("" + StringConstants.MESSAGE600);
													msgInfo.setCode(StringConstants.C_600.toString());
													logger.info("Failure from 3rd Party API : " + info.getResponse());
												}
												break;
											}
											else 
											{
												msgInfo.setStatus(StringConstants.FAILURE.toString());
												msgInfo.setMessage("NO-HIT from API");
												msgInfo.setCode("" + 104);
												break;
											}
										} 
										else 
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage("Invalid Input from issue request");
											msgInfo.setCode("" + 101);
											break;
										}
									}else{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}
								}
							}
							else 
							{
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("User Authentication failed. Check username/ password/ Institution Id / Aggregator Id / IP Address of user and retry.");
								msgInfo.setCode("" + 101);
							}
						}
						else 
						{
							msgInfo.setCode(StringConstants.C_500.toString());
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} 
					else 
					{
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						msgInfo.setCode(StringConstants.C_401.toString());
					}

				}
				else 
				{
					msgInfo.setStatus(StringConstants.FAILURE.toString());
					msgInfo.setMessage(StringConstants.MESSAGE400.toString());
					msgInfo.setCode(StringConstants.C_400.toString());
				}

		}
		catch (Exception e) 
		{
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode("" + 600);
		}
		logger.info("End to bureau Service method:");
		bResponse.setMsgInfo(msgInfo);
		bResponse.setHeader(req.getHeader());
		try {
			String price=null;
			if (bResponse.getMsgInfo().getCode().equals("200"))
			{
				price=resProp.getString("com.bureau.success.price");
			}else {
				price=resProp.getString("com.bureau.failure.price");
			}
			reqRes.setPrice(price);

		} catch (Exception e) {
			logger.error("Error while updating bureau success/failure price:" + e);
		}
		logger.info("End to bureau Service method:");
		return bResponse;
	}

	private boolean checkValidation(Header header) 
	{
		if (header != null) {
			AuthValidator auth = dbBureau.validateAuth(header);
			if (auth.getAppid() == null) 
			{
				return false;
			} else {
				return true;
			}
		} else 
		{
			return true;
		}
	}

	private String getStatusMsg(int i) 
	{
		String msg = null;
		if (i != 0) {

			if (i == 101) 
			{
				msg = "Valid Authentication";

			} else if (i == 102) 
			{
				msg = "Invalid ID number or combination of inputs";

			} else if (i == 103) 
			{
				msg = "No records found for the given ID or combination of inputs";

			} else if (i == 104) 
			{
				msg = "Max retries exceeded";

			} else if (i == 105) 
			{
				msg = "Missing Consent";

			} else 
			{
				msg = "Error in status message.";
			}

		} else 
		{
			msg = "Error in status message.";
		}
		return msg;
	}

	private MessageInfo getHttpMessage(int i) 
	{
		MessageInfo info = new MessageInfo();

		if (i != 0) 
		{

			if (i == 200) 
			{
				info.setStatus("OK");
				info.setCode("" + i);
				info.setMessage("Request Successful");

			} else if (i == 400) 
			{
				info.setStatus("Bad Request");
				info.setCode("" + i);
				info.setMessage("Mandatory fields are missing / invalid");
				// info.setInternalCode(""+i);

			} else if (i == 401) 
			{
				info.setStatus("Unauthorized Access");
				info.setCode("" + i);
				info.setMessage("API Key is missing or invalid.");
				// info.setInternalCode(""+i);

			} else if (i == 402) 
			{
				info.setStatus("Insufficient Credits");
				info.setCode("" + i);
				info.setMessage("Credits to access the APIs expired.");
				// info.setInternalCode(""+i);

			} else if (i == 500) 
			{
				info.setStatus("Internal Server Error");
				info.setCode("" + i);
				info.setMessage("Internal processing error of Karza.");
				// info.setInternalCode(""+i);

			} else if (i == 503) 
			{
				info.setStatus("Source Unavailable");
				info.setCode("" + i);
				info.setMessage("The source for authentication is down for maintenance or inaccessible.");
				// info.setInternalCode(""+i);

			} else if (i == 504) 
			{
				info.setStatus("Endpoint Request Timed Out");
				info.setCode("" + i);
				info.setMessage("The response latency from the source for authentication is >30sec.");
				// info.setInternalCode(""+i);

			} else 
			{
				info.setStatus(StringConstants.FAILURE.toString());
				info.setMessage(StringConstants.MESSAGE500.toString());
				// info.setInternalCode(StringConstants.C_500.toString());
				info.setCode("" + i);
				// info.setInternalCode(""+i);
			}

		}

		return info;
	}
}
