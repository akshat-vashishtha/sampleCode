package com.qualtech.cibilv2.api.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibilv2.api.common.dto.ERRORSTATUS;
import com.qualtech.cibilv2.api.common.dto.ErrorInfo;
import com.qualtech.cibilv2.api.request.Address;
import com.qualtech.cibilv2.api.request.CibilReqRes;
import com.qualtech.cibilv2.api.request.CibilRequest2;
import com.qualtech.cibilv2.api.request.CibilRequestPayload2;
import com.qualtech.cibilv2.api.request.MemberIdTypeDescription;
import com.qualtech.cibilv2.api.request.MemberRelationship;
import com.qualtech.cibilv2.api.request.TelephoneNumberTypeIndicator;
import com.qualtech.cibilv2.api.response.ApiResponseHeader2;
import com.qualtech.cibilv2.api.response.CibilAPIResponse2;
import com.qualtech.cibilv2.api.response.CibilResponsePayload2;
import com.qualtech.cibilv2.api.response.Root;
import com.qualtech.cibilv2.api.service.CibilApiServiceV2;

@Service
@JsonSerialize

public class CibilApiServiceV2Impl implements CibilApiServiceV2 {

	static Logger logger = Logger.getLogger(CibilApiServiceV2Impl.class.getName());
	@Autowired PropertyFile env;
	//static ResourceBundle resBundle = PropertyFile.getInstance().getResourceBundel();
	// public static final Properties PROPERTIES = new Properties();
	// static String url = resBundle.getString("com.qc.cibil.url");
	//	static String url="https://www.cibilhawk.com/MFI/enquiry/comboreport/";
	String cibilThirdPartyReq = null;
	String cibilThirdPartyRes = null;

	public CibilAPIResponse2 cibilRequestService(CibilRequest2 cibilapirequest, CibilReqRes cibilReqRes)
	{
		CibilAPIResponse2 cibilResponse = new CibilAPIResponse2();
		ApiResponseHeader2 apiResponseHeader2 = new ApiResponseHeader2();
		CibilResponsePayload2 cibilResponsePayload2 = new CibilResponsePayload2();
		String xmlResponse = null;
		ErrorInfo errorInfo = new ErrorInfo();
		try 
		{
			if (cibilapirequest != null && cibilapirequest.getPayload() != null
					&& cibilapirequest.getHeader() != null)
			{

				apiResponseHeader2.setAppId(cibilapirequest.getHeader().getAppId());
				apiResponseHeader2.setCorrelationId(cibilapirequest.getHeader().getCorrelationId());
				apiResponseHeader2.setMsgVersion(cibilapirequest.getHeader().getMsgVersion());
				cibilResponse.setHeader(apiResponseHeader2);
				cibilapirequest.getPayload().setMemberReferenceNumber(cibilapirequest.getHeader().getCorrelationId());
				String jsonToString = convertJavaPojoToStringRequest(cibilapirequest);
				if (jsonToString != null && !jsonToString.equals("") && !jsonToString.contains("is required"))
				{
					try 
					{
						logger.info(" Cibil internal \"Request\" :: " + jsonToString);

						String hardCodedEnv="N";
						ResourceBundle res=null;
						try 
						{
							res = ResourceBundle.getBundle("hardcode");
							hardCodedEnv = env.getString("com.cibil.mfi.demo.development");	
							logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
						}
						catch(Exception ex)
						{
							hardCodedEnv="N";
						}

						if("Y".equals(hardCodedEnv))
						{
							xmlResponse = res.getString("CIBIL_MFI_RESPONSE");
						}
						else
						{
							xmlResponse = httpCall(env.getString("com.qc.cibil.url"), jsonToString);
						}

						cibilReqRes.setIntReq(jsonToString);
						cibilReqRes.setIntRes(xmlResponse);

						logger.info(" Cibil internal \"Response in xml\" :: " + xmlResponse);
					}
					catch (Exception exception) 
					{
						errorInfo.setCode("");
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setDescription("Failure from third party");
						errorInfo.setMessage("");
						logger.error("Failure from third party : " + exception);
					}

					try
					{
						if (xmlResponse.contains("Invalid Length Record Structure")) 
						{
							errorInfo.setCode("400");
							errorInfo.setStatus(ERRORSTATUS.FAILURE);
							errorInfo.setDescription("Invalid Length Record Structure");
							errorInfo.setMessage("Bad Request");
							logger.info("Invalid and bad request");
						}
						else if (xmlResponse != null && !xmlResponse.equals(""))
						{
							JSONObject JSONObj = XML.toJSONObject(xmlResponse);
							logger.info("Cibil internal xmlResponse succesfully converted in JSON format");
							logger.info(" Cibil internal \"Response in JSON\" :: " + JSONObj);

							ObjectMapper mapper = new ObjectMapper();
							mapper.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
							if (JSONObj.has("root") && JSONObj.getJSONObject("root").has("reportType"))
							{
								JSONObject root = JSONObj.getJSONObject("root");
								JSONObject reportType = root.getJSONObject("reportType");
								JSONObject informationData = null;

								if (reportType.has("informationData")) 
								{
									try 
									{
										informationData = reportType.getJSONObject("informationData");
										JSONArray fieldArray = null;
										try 
										{
											fieldArray = informationData.getJSONArray("field");
										}
										catch (Exception e) 
										{
											fieldArray = new JSONArray(
													"[" + informationData.getJSONObject("field").toString() + "]");
										}
										informationData.remove("field");
										informationData.put("field", fieldArray);
									}
									catch (Exception e) 
									{
										logger.error("Exception while Handling in JSON format reportType :: " + e);
									}
								}

								reportType.remove("informationData");
								reportType.put("informationData", informationData);
								root.remove("reportType");
								root.put("reportType", reportType);

								try 
								{
									if (root.has("alerts") && root.get("alerts") != null
											&& root.get("alerts").toString().equals(""))
									{
										root.remove("alerts");
										JSONObject alerts = new JSONObject();
										root.put("alerts", alerts);

									}
								} 
								catch (Exception e)
								{
									logger.error("Exception while Handling in JSON format alerts :: " + e);
								}

								try
								{
									if (root.has("creditReportSummary")
											&& root.get("creditReportSummary").toString().equals(""))
									{
										root.remove("creditReportSummary");
										JSONObject creditReportSummary = new JSONObject();

										root.put("creditReportSummary", creditReportSummary);
									}
								} 
								catch (Exception e)
								{
									logger.error("Exception while Handling in JSON format creditReportSummary :: " + e);
								}

								try 
								{
									if (root.has("consumerInformation")
											&& root.get("consumerInformation").toString().equals(""))
									{
										root.remove("consumerInformation");
										JSONObject consumerInformation = new JSONObject();

										root.put("consumerInformation", consumerInformation);
									}
								}
								catch (Exception e)
								{
									logger.error("Exception while Handling in JSON format consumerInformation :: " + e);
								}

								try
								{
									if (root.has("score")
											&& root.get("score").toString().equals("")) 
									{
										root.remove("score");
										JSONObject score = new JSONObject();

										root.put("score", score);
									}
								} 
								catch (Exception e)
								{
									logger.error("Exception while Handling in JSON format score :: " + e);
								}

								try
								{
									if (root.has("plScore")
											&& root.get("plScore").toString().equals("")) 
									{
										root.remove("plScore");
										JSONObject plScore = new JSONObject();

										root.put("plScore", plScore);
									}
								} 
								catch (Exception e)
								{
									logger.error("Exception while Handling in JSON format plScore :: " + e);
								}
								try
								{
									if (root.has("errorResponse")
											&& root.getJSONObject("errorResponse").has("informationData"))
									{
										if (root.getJSONObject("errorResponse").get("informationData").toString().equals(""))
										{
											root.remove("errorResponse");
											JSONObject errorResponse = null;
											root.put("errorResponse", errorResponse);
										}
										root.remove("errorResponse");
										JSONObject errorResponse = new JSONObject();

										root.put("errorResponse", errorResponse);
									}
								} catch (Exception e) {
									logger.error("Exception while Handling in JSON format errorResponse :: " + e);
								}
								try
								{
									Root rootObject = mapper.readValue(root.toString(), Root.class);

									cibilResponsePayload2.setRoot(rootObject);
									cibilResponse.setPayload(cibilResponsePayload2);
								}catch(Exception e)
								{
									e.printStackTrace();
									logger.error("Exception while root mapping :: " + e);
								}


								errorInfo.setCode("200");
								errorInfo.setStatus(ERRORSTATUS.SUCCESS);
								errorInfo.setDescription("success");
								errorInfo.setMessage("responce generated successfully");
								logger.info("responce generated successfully");
							}
							else if(JSONObj.has("root") && JSONObj.getJSONObject("root").has("error"))
							{
								String error = JSONObj.getJSONObject("root").getString("error");
								errorInfo.setCode("400");
								errorInfo.setStatus(ERRORSTATUS.FAILURE);
								errorInfo.setDescription(error);
								errorInfo.setMessage("error in generating response from third party");
								logger.info("error in generating response from third party");
							}
						} else {
							errorInfo.setCode("600");
							errorInfo.setStatus(ERRORSTATUS.FAILURE);
							errorInfo.setDescription("Unable to get Response data from third party api");
							errorInfo.setMessage("Unable to get Response data");
							logger.info("Unable to get Response data from third party api");
						}
					} catch (Exception e) {

						errorInfo.setCode("600");
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setDescription("Unable to get Response data");
						errorInfo.setMessage("Unable to get Response data");
						logger.info("Unable to get Response data : " + e);
					}

				} else {

					errorInfo.setCode("400");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setDescription(jsonToString);
					errorInfo.setMessage("Bad Request");
					logger.info("Invalid and bad request");

				}
			} else {
				errorInfo.setCode("400");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setDescription("Bad Request");
				errorInfo.setMessage("Bad Request");
				logger.info("Invalid and bad request status code 400");
			}
		} catch (Exception e) {
			errorInfo.setCode("500");
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			errorInfo.setDescription("Internal server error");
			errorInfo.setMessage("Internal server error");
			logger.info("Internal server error status code 500");
		}

		cibilResponse.setErrorInfo(errorInfo);
		return cibilResponse;
	}

	public String convertJavaPojoToStringRequest(CibilRequest2 cibilapirequest) {
		String header = env.getString("com.qc.cibil.headerTag");
		cibilapirequest.getPayload().setHeaderTag(header);
		String version = env.getString("com.qc.cibil.version");
		cibilapirequest.getPayload().setVersion(version);
		String userId = env.getString("com.qc.cibil.UserId");
		cibilapirequest.getPayload().setUserId(userId);
		String password = env.getString("com.qc.cibil.password");
		cibilapirequest.getPayload().setPassword(password);
		String pipeLine = "|";
		StringBuilder jsonToString = new StringBuilder();
		try {
			CibilRequestPayload2 payload = cibilapirequest.getPayload();

			if (payload != null) {

				if (header != null && !header.equals("")) {
					jsonToString.append(header);
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Header Tag is required");
					logger.info("Header Tag is required");
					return jsonToString.toString();
				}

				if (version != null && !version.equals("")) {
					jsonToString.append(version);
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Version is required");
					logger.info("Version is required");
					return jsonToString.toString();
				}

				if (userId != null && !userId.equals("")) {
					jsonToString.append(userId);
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("UserId is required");
					logger.info("UserId is required");
					return jsonToString.toString();
				}

				if (password != null && !password.equals("")) {
					jsonToString.append(password);
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("password is required");
					logger.info("password is required");
					return jsonToString.toString();
				}

				if (payload.getMemberReferenceNumber() != null && !payload.getMemberReferenceNumber().equals("")) {
					jsonToString.append(payload.getMemberReferenceNumber());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				if (payload.getEnquiryAmount() != null && !payload.getEnquiryAmount().equals("")) {
					jsonToString.append(payload.getEnquiryAmount());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Enquiry Amount is required");
					logger.info("Enquiry amount is required");
					return jsonToString.toString();
				}
				if (payload.getLoanPurpose() != null && !payload.getLoanPurpose().equals("")) {
					jsonToString.append(payload.getLoanPurpose());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Loan Purpose is required");
					logger.info("Loan purpose is required");
					return jsonToString.toString();
				}
				if (payload.getReportType() != null && !payload.getReportType().equals("")) {
					jsonToString.append(payload.getReportType());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Report type is required");
					logger.info("Report type is required");
					return jsonToString.toString();
				}
				if (payload.getFutureUse() != null && !payload.getFutureUse().equals("")) {
					jsonToString.append(payload.getFutureUse());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Future Use is required");
					logger.info("Future Use is required");
					return jsonToString.toString();
				}

				if (payload.getfName() != null && !payload.getfName().equals("")) {
					jsonToString.append((payload.getfName() != null ? payload.getfName() + pipeLine : pipeLine)
							+ (payload.getmName() != null ? payload.getmName() + pipeLine : pipeLine)
							+ (payload.getlName() != null ? payload.getlName() + pipeLine : pipeLine));
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Atleast First name is required");
					logger.info("Atleast 1 applicant name is required");
					return jsonToString.toString();
				}
				if (payload.getAlternateName() != null) {
					jsonToString.append(payload.getAlternateName());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);

				}

				String voterId = payload.getVoterId();
				String uid = payload.getUid();
				String pan = payload.getPan();

				// Atleast one Id is required
				if ((voterId != null && !voterId.equals("")) || (uid != null && !uid.equals(""))
						|| (pan != null && !pan.equals(""))) {
					if (payload.getVoterId() != null && !payload.getVoterId().equals("")) {
						jsonToString.append(payload.getVoterId());
						jsonToString.append(pipeLine);
					} else {
						jsonToString.append(pipeLine);

					}
					if (payload.getUid() != null && !payload.getUid().equals("")) {
						jsonToString.append(payload.getUid());
						jsonToString.append(pipeLine);
					} else {
						jsonToString.append(pipeLine);

					}
					if (payload.getPan() != null && !payload.getPan().equals("")) {
						jsonToString.append(payload.getPan());
						jsonToString.append(pipeLine);
					} else {
						jsonToString.append(pipeLine);

					}
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Atleast one(Pan, Uid or Voter) ID name is required");
					logger.info("Atleast one(Pan, Uid or Voter) ID name must is required");
					return jsonToString.toString();
				}

				if (payload.getRationCard() != null && !payload.getRationCard().equals("")) {
					jsonToString.append(payload.getRationCard());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				if (payload.getMemberIdTypeDescription() != null && !payload.getMemberIdTypeDescription().isEmpty()) {
					int loopCounter = 0;
					boolean flag = true;
					while (loopCounter < 3) {
						if (flag) {
							for (MemberIdTypeDescription memberIdTypeDescription : payload
									.getMemberIdTypeDescription()) {

								if (memberIdTypeDescription != null && !memberIdTypeDescription.equals("")) {
									jsonToString.append(memberIdTypeDescription.getTypeDescription());
									jsonToString.append(pipeLine);
									jsonToString.append(memberIdTypeDescription.getMemberId());
									jsonToString.append(pipeLine);
								} else {
									jsonToString.append(pipeLine);
									jsonToString.append(pipeLine);
								}
								loopCounter++;
								flag = false;
							}
						} else {
							jsonToString.append(pipeLine);
							jsonToString.append(pipeLine);
							loopCounter++;
						}
					}
				} else {
					// Changes
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);

				}

				// change in telephone code
				if (payload.getTelephoneNumberTypeIndicator() != null
						&& !payload.getTelephoneNumberTypeIndicator().isEmpty()) {
					// new change
					if (payload.getTelephoneNumberTypeIndicator().size() > 1) {
						int loopCounter = 0;
						for (TelephoneNumberTypeIndicator telephoneNumberTypeIndicator : payload
								.getTelephoneNumberTypeIndicator()) {

							if (loopCounter > 2) {
								break;
							}

							if (telephoneNumberTypeIndicator != null) {
								jsonToString.append(telephoneNumberTypeIndicator.getTypeIndicator());
								jsonToString.append(pipeLine);
								jsonToString.append(telephoneNumberTypeIndicator.getTelephoneNumber());
								jsonToString.append(pipeLine);
							} else {
								jsonToString.append(pipeLine);
							}
							loopCounter++;
						}

					}

					if (payload.getTelephoneNumberTypeIndicator().size() < 2) {
						for (TelephoneNumberTypeIndicator telephoneNumberTypeIndicator : payload
								.getTelephoneNumberTypeIndicator()) {

							if (telephoneNumberTypeIndicator != null) {
								jsonToString.append(telephoneNumberTypeIndicator.getTypeIndicator());
								jsonToString.append(pipeLine);
								jsonToString.append(telephoneNumberTypeIndicator.getTelephoneNumber());
								jsonToString.append(pipeLine);
							} else {
								jsonToString.append(pipeLine);
							}
						}
						jsonToString.append(pipeLine);
						jsonToString.append(pipeLine);
					}

				} else {
					// Changes Done
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
				}

				if (payload.getMemberBirthDate() != null && !payload.getMemberBirthDate().equals("")) {
					jsonToString.append(payload.getMemberBirthDate());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Member Birth Date is required");
					logger.info("Member Birth Date is required");
					return jsonToString.toString();
				}
				if (payload.getMemberGenderType() != null && !payload.getMemberGenderType().equals("")) {
					jsonToString.append(payload.getMemberGenderType());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Gender type is required");
					logger.info("Gender type is required");
					return jsonToString.toString();
				}

				// Change in address Code
				if (payload.getAddress() != null && !payload.getAddress().isEmpty()) {
					int loopCounter = 0;
					boolean flag = true;
					while (loopCounter < 2) {

						if (flag) {

							flag = false;
							for (Address address : payload.getAddress()) {
								if (address.getAddressType() != null && (address.getAddressType().equalsIgnoreCase("P") || address.getAddressType().equalsIgnoreCase("C"))) {
									jsonToString.append(address.getAddressType());
									jsonToString.append(pipeLine);
								} else {
									jsonToString = null;
									jsonToString = new StringBuilder();
									jsonToString.append("Address Type either 'C' or 'P' is required");
									logger.info("Address type is required");
									return jsonToString.toString();
								}

								if (address.getAddressLine() != null && !address.getAddressLine().equals("")) {
									jsonToString.append(address.getAddressLine());
									jsonToString.append(pipeLine);
								} else {
									jsonToString = null;
									jsonToString = new StringBuilder();
									jsonToString.append("Address Line is required");
									logger.info("Address Line is required");
									return jsonToString.toString();
								}

								if (address.getCityTown() != null && !address.getCityTown().equals("")) {
									jsonToString.append(address.getCityTown());
									jsonToString.append(pipeLine);
								} else {
									jsonToString.append(pipeLine);
								}

								if (address.getState() != null && !address.getState().equals("")) {
									jsonToString.append(address.getState());
									jsonToString.append(pipeLine);
								} else {
									jsonToString = null;
									jsonToString = new StringBuilder();
									jsonToString.append("State is required");
									logger.info("State is required");
									return jsonToString.toString();
								}

								if (address.getPinCode() != null && !address.getPinCode().equals("")) {
									jsonToString.append(address.getPinCode());
									jsonToString.append(pipeLine);
								} else {
									jsonToString = null;
									jsonToString = new StringBuilder();
									jsonToString.append("Pincode is required");
									logger.info("Pincode is required");
									return jsonToString.toString();
								}
								loopCounter++;
							}
						} else {
							jsonToString.append(pipeLine);
							jsonToString.append(pipeLine);
							jsonToString.append(pipeLine);
							jsonToString.append(pipeLine);
							jsonToString.append(pipeLine);
							loopCounter++;
						}

					}
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Address is required");
					logger.info("Address is required");
					return jsonToString.toString();
				}
				if (payload.getKeyPersonName() != null && !payload.getKeyPersonName().equals("")) {
					jsonToString.append(payload.getKeyPersonName());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Key person name is required");
					logger.info("Key person name is required");
					return jsonToString.toString();
				}
				if (payload.getKeyPersonsRelationship() != null && !payload.getKeyPersonsRelationship().equals("")) {
					jsonToString.append(payload.getKeyPersonsRelationship());
					jsonToString.append(pipeLine);
				} else {
					jsonToString = null;
					jsonToString = new StringBuilder();
					jsonToString.append("Key person relationship is required");
					logger.info("Key person relationship is required");
					return jsonToString.toString();
				}
				if (payload.getMemberRelationship() != null && !payload.getMemberRelationship().isEmpty()) {
					int loopCounter = 0;
					boolean flag = true;
					while (loopCounter < 4) {
						if (flag) {
							for (MemberRelationship memberRelationship : payload.getMemberRelationship()) {
								if (memberRelationship.getRelationshipName() != null
										&& !memberRelationship.getRelationshipName().equals("")) {
									jsonToString.append(memberRelationship.getRelationshipName());
									jsonToString.append(pipeLine);
								} else {
									jsonToString.append(pipeLine);
								}
								if (memberRelationship.getRelationshipType() != null
										&& !memberRelationship.getRelationshipType().equals("")) {
									jsonToString.append(memberRelationship.getRelationshipType());
									jsonToString.append(pipeLine);
								} else {
									jsonToString.append(pipeLine);
								}
								loopCounter++;

							}
							flag = false;
						} else {
							jsonToString.append(pipeLine);
							jsonToString.append(pipeLine);
							loopCounter++;
						}
					}

				} else {
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
					jsonToString.append(pipeLine);
				}

				if (payload.getNomineeName() != null && !payload.getNomineeName().equals("")) {
					jsonToString.append(payload.getNomineeName());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				if (payload.getNomineeRelationship() != null && !payload.getNomineeRelationship().equals("")) {
					jsonToString.append(payload.getNomineeRelationship());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				if (payload.getAccount1() != null && !payload.getAccount1().equals("")) {
					jsonToString.append(payload.getAccount1());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				if (payload.getGstStateCode() != null && !payload.getGstStateCode().equals("")) {
					jsonToString.append(payload.getGstStateCode());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				if (payload.getBranchReferenceNo() != null && !payload.getBranchReferenceNo().equals("")) {
					jsonToString.append(payload.getBranchReferenceNo());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				if (payload.getCentreReferenceNo() != null && !payload.getCentreReferenceNo().equals("")) {
					jsonToString.append(payload.getCentreReferenceNo());
					jsonToString.append(pipeLine);
				} else {
					jsonToString.append(pipeLine);
				}
				/*
				 * if (payload.getEndSegmentTag() != null &&
				 * !payload.getEndSegmentTag().equals("")) {
				 * jsonToString.append(payload.getEndSegmentTag()); } else { jsonToString =
				 * null; jsonToString = new StringBuilder();
				 * jsonToString.append("End  Segment Tag is required");
				 * logger.info("End  Segment Tag is required"); }
				 */
				jsonToString.append("ES");

			} else {
				jsonToString = null;
			}

		} catch (Exception ee) {
			logger.info("Exception while converting java object to String : " + ee);
		}
		if (jsonToString != null) {
			return jsonToString.toString();
		} else {
			return null;
		}

	}

	public String httpCall(String url, String input) {
		//input="MFEF|1|BP03089999_AyeFinUser|Sept@2018||6647|40|3|NB7369|Ruben|Damion|Pitts|Ruben|VTR0400108||PANPN1010C|9201740108|ELECTRICITY BILL|EXBL0108|JOB CARD|JOBC010|OTHER ID|OTID0108|P03|8500176108|||21121969|M||||||C|Pitts Nivasthan||MH|400108|Damion|K01|Damion|K01|Selene|K02|||||Damion|K01|||||ES";
		String xmlResponse = "";
		HttpURLConnection urlConn = null;
		String strMFIEF = input;
		String isProxyRequired = env.getString("com.qc.cibil.isProxyRequired");
		if ("true".equalsIgnoreCase(isProxyRequired)) {
			String isProxyHttps = env.getString("com.qc.cibil.isProxyHttps");
			if ("true".equalsIgnoreCase(isProxyHttps)) {
				System.setProperty("https.proxyHost", env.getString("com.qc.cibil.https.proxyHost"));
				System.setProperty("https.proxyPort", env.getString("com.qc.cibil.https.proxyHost"));
			}
			System.setProperty("https.protocols", env.getString("com.qc.cibil.https.protocols"));
		}
		String isTrustStoreRequired = env.getString("com.qc.cibil.isTrustStoreRequired");
		if ("true".equalsIgnoreCase(isTrustStoreRequired)) {
			System.setProperty("javax.net.ssl.trustStore", env.getString("com.qc.cibil.trustStore"));
		}
		try {

			URL iurl = new URL(url.trim() + "?recordStr=" + URLEncoder.encode(strMFIEF, "UTF-8"));
			logger.info(" Cibil Url :: " + iurl);
			urlConn = (HttpURLConnection) iurl.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("POST");
			urlConn.setRequestProperty("Content-Type", "text/xml");
			urlConn.connect();
			logger.info("After getting connection...");
			logger.info("Response code after output:::" + urlConn.getResponseCode());
			InputStream is = urlConn.getInputStream();

			// System.out.println(is);
			BufferedReader reader = new BufferedReader(new InputStreamReader((is)));

			xmlResponse = reader.readLine();
			logger.info("Xml response from Cibil API :: " + xmlResponse);

		} catch (Exception e) {
			logger.error("Exception inside comboreport() :: " + e);
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}

		return xmlResponse;
	}

	public Map<String, String> requiredParam() {
		String filePath = env.getString("com.qc.cibil.pdf.savePath");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());

		Map<String, String> params = new HashMap<String, String>();
		params.put("path", filePath);
		params.put("internalReq", cibilThirdPartyReq);
		params.put("internalRes", cibilThirdPartyRes);
		params.put("time", time);

		return params;

	}

}