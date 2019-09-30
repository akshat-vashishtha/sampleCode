package com.qualtech.hdfc.api.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.hdfc.api.db.DBHdfc;
import com.qualtech.hdfc.api.request.ApiRequest;
import com.qualtech.hdfc.api.request.HdfcReqRes;
import com.qualtech.hdfc.api.request.Header;
import com.qualtech.hdfc.api.request.NomineeDetails;
import com.qualtech.hdfc.api.response.ApiResponse;
import com.qualtech.hdfc.api.response.ApiResponseHdfcPayload;
import com.qualtech.hdfc.api.response.ApiResponseHeader;
import com.qualtech.hdfc.api.response.ERRORSTATUS;
import com.qualtech.hdfc.api.response.Errors;
import com.qualtech.hdfc.api.response.MsgInfo;
import com.qualtech.hdfc.api.utils.ConvertRequestInt;
import com.qualtech.hdfc.api.utils.EncryptionUtil;

@Service
public class HdfcServiceImpl implements HdfcService {


	private static Logger logger = LogManager.getLogger(HdfcServiceImpl.class);

	@Autowired
	private ConvertRequestInt convertRequest;
	@Autowired 
	private PropertyFile resProp;
	@Autowired
	private DBHdfc dbHdfc;

	@Override
	public ApiResponse hdfcRequestServiceQC(ApiRequest hdfcRequest, HdfcReqRes rq) {
		logger.info("In Hdfc || HdfcServiceImpl  start");
		ApiResponseHeader apiResponseHeader=new ApiResponseHeader();
		ApiResponse hdfcResponse = new ApiResponse();
		ApiResponseHdfcPayload hdfcPayLoad = new ApiResponseHdfcPayload();
		ObjectMapper om = new ObjectMapper();
		MsgInfo msgInfo = new MsgInfo();
		if(hdfcRequest!=null && hdfcRequest.getPayload()!=null && hdfcRequest.getHeader()!=null)
		{
			boolean result = checkValidation(hdfcRequest.getHeader());
			logger.info("In Hdfc || HdfcServiceImpl check Validation "+result);
			if(result==true) 
			{
				msgInfo=checkRequestValidation(hdfcRequest);
				if(msgInfo==null)
				{
					msgInfo = new MsgInfo();
					try
					{
						apiResponseHeader.setAppId(hdfcRequest.getHeader().getAppId());
						apiResponseHeader.setCorrelationId(hdfcRequest.getHeader().getCorrelationId());
						apiResponseHeader.setMsgVersion(hdfcRequest.getHeader().getMsgVersion());
						String serviceurl = resProp.getString("com.qualtech.hdfcRequest.thirdparty.url");
						logger.info("Hdfc request json conversion start");
						String requestJson=convertRequest.hdfcRequest(hdfcRequest);
						logger.info("Hdfc request json conversion end");
						rq.setIntReq(requestJson);
						logger.info("Hdfc call for response start");
						hdfcPayLoad=convertRequest.httpCall(requestJson, serviceurl);
						logger.info("Hdfc call for response ends"+hdfcPayLoad);

						if(hdfcPayLoad.getMetaData().getStatus().equals("1"))
						{
							rq.setPrice(resProp.getString("com.qualtech.hdfc.success.price"));
							rq.setStatus("SUCCESS");
							msgInfo.setDescription("Success Response");
							msgInfo.setStatus(ERRORSTATUS.SUCCESS);
							msgInfo.setCode(hdfcPayLoad.getMetaData().getStatus());

						}
						else
						{
							for (Errors errors : hdfcPayLoad.getMetaData().getErrors()) 
							{
								rq.setPrice(resProp.getString("com.qualtech.hdfc.failure.price"));
								rq.setStatus("FAILURE");
								msgInfo.setMessage(errors.getErrorMessage());
								msgInfo.setCode(errors.getErrorCode());
								msgInfo.setStatus(ERRORSTATUS.FAILURE);
								msgInfo.setDescription(errors.getErrorMessage());
							}
						}

						rq.setIntRes(om.writeValueAsString(hdfcPayLoad));

						logger.info("Hdfc Response PDF saving Start");
						String finalpdfPath=hdfcDownloadPdf(hdfcPayLoad);
						String pdfpath=new String(finalpdfPath);
						rq.setPdfPath(pdfpath);
						//					if(finalpdfPath.contains("/"))
						//					{
						//						finalpdfPath=finalpdfPath.replaceAll("/","~~~~");
						//					}
						//					else
						//					{
						//						finalpdfPath=finalpdfPath.replaceAll("\\\\","~~");	
						//					}
						hdfcPayLoad.setPdfPath(finalpdfPath);
					}
					catch (Exception e) {
						logger.error("Exception inside service impl: "+e);
						rq.setPrice(resProp.getString("com.qualtech.hdfc.failure.price"));
						rq.setStatus("FAILURE");
						msgInfo.setMessage("Server Error");
						msgInfo.setCode("600");
						msgInfo.setStatus(ERRORSTATUS.FAILURE);
						//msgInfo.setDescription(errors.getErrorMessage());
					}
				}
				else {
					logger.error("Exception inside service impl: "+"Bad Request ");
					logger.error("Exception inside service impl: "+msgInfo.getDescription());
					rq.setPrice(resProp.getString("com.qualtech.hdfc.failure.price"));
					rq.setStatus("FAILURE");
					/*msgInfo.setMessage("Unauthorized Access");
					msgInfo.setCode("401");
					msgInfo.setStatus(ERRORSTATUS.FAILURE);*/
				}
			}
			else {
				logger.error("Exception inside service impl: "+"Bad Request");
				rq.setPrice(resProp.getString("com.qualtech.hdfc.failure.price"));
				rq.setStatus("FAILURE");
				msgInfo.setMessage("Unauthorized Access");
				msgInfo.setCode("401");
				msgInfo.setStatus(ERRORSTATUS.FAILURE);
			}
		}
		else {
			logger.error("Exception inside service impl: ||Hdfc Request is null ||");
			rq.setPrice(resProp.getString("com.qualtech.hdfc.failure.price"));
			rq.setStatus("FAILURE");
			msgInfo.setMessage("Bad Request");
			msgInfo.setCode("400");
			msgInfo.setStatus(ERRORSTATUS.FAILURE);
		}
		hdfcResponse.setHeader(apiResponseHeader);
		hdfcResponse.setMsgInfo(msgInfo);
		hdfcResponse.setPayload(hdfcPayLoad);
		logger.info("In Hdfc || HdfcServiceImpl  End");
		return hdfcResponse;
	}




	public String hdfcDownloadPdf(ApiResponseHdfcPayload payload)
	{
		//String pdfpath="E:\\Deepak\\pdfs\\ ";

		String finalpdfPath=null;
		if(payload!=null && payload.getMetaData()!=null)
		{
			String downLoadurl=payload.getMetaData().getDownloadPDFUrl();
			if(downLoadurl!=null || downLoadurl!="")
			{
				String encryptedUrl=EncryptionUtil.decryptString(downLoadurl);
				if(encryptedUrl!=null)
				{

					try
					{
						Thread.sleep(Integer.parseInt(resProp.getString("com.qualtech.hdfc.delayTime")));
						URL url = new URL(encryptedUrl);
						InputStream in = url.openStream();
						finalpdfPath=resProp.getString("com.qualtech.hdfc.pdfPath")+File.separator+payload.getMetaData().getPrn()+".pdf";
						FileOutputStream fos = new FileOutputStream(new File(finalpdfPath));
						int length = -1;
						byte[] buffer = new byte[50000];// buffer for portion of data from connection
						while ((length = in.read(buffer)) > -1) {
							fos.write(buffer, 0, length);
						}
						fos.close();
						in.close();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						logger.error("Error While saving the PDF : " + ex);
					}

				}
			}
			//  failure pdf-----------------------------
		}
		return finalpdfPath;
	}

	private boolean checkValidation(Header header) {
		if (header != null) {
			AuthValidator auth =dbHdfc.validateAuth(header);
			if (auth.getAppid() == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}


	private MsgInfo checkRequestValidation(ApiRequest hdfcRequest) 
	{

		boolean flag = false;
		MsgInfo msgInfo = new MsgInfo();

		if (hdfcRequest.getPayload() != null && hdfcRequest.getPayload().getMetaData() != null) 
		{

			if (hdfcRequest.getPayload().getMetaData().getPartnerId() != null
					&& !hdfcRequest.getPayload().getMetaData().getPartnerId().equals("")) 
			{
				if (hdfcRequest.getPayload().getMetaData().getPartnerData() != null
						&& hdfcRequest.getPayload().getMetaData().getPartnerData().getSumAssuredType() != null
						&& !hdfcRequest.getPayload().getMetaData().getPartnerData().getSumAssuredType().equals("")) 
				{
					if (hdfcRequest.getPayload().getMetaData().getPartnerData().getFundingOption() != null
							&& !hdfcRequest.getPayload().getMetaData().getPartnerData().getFundingOption().equals("")) 
					{
						if (hdfcRequest.getPayload().getMetaData().getPlan() != null
								&& !hdfcRequest.getPayload().getMetaData().getPlan().equals("")
								&& hdfcRequest.getPayload().getMetaData().getPlan().matches("[-a-zA-Z0-9_]+")) 
						{
							if (hdfcRequest.getPayload().getCustomerDetails() != null
									&& hdfcRequest.getPayload().getCustomerDetails().getSumAssured() != null
									&& !hdfcRequest.getPayload().getCustomerDetails().getSumAssured().equals("")) 
							{
								if (hdfcRequest.getPayload().getCustomerDetails().getLoanType() != null
										&& !hdfcRequest.getPayload().getCustomerDetails().getLoanType().equals("")) 
								{
									if (hdfcRequest.getPayload().getCustomerDetails().getLoanAmount() != null
											&& !hdfcRequest.getPayload().getCustomerDetails().getLoanAmount().equals("")) 
									{
										if (hdfcRequest.getPayload().getCustomerDetails().getBasicPremium() != null
												&& !hdfcRequest.getPayload().getCustomerDetails().getBasicPremium().equals("")) 
										{
											if (hdfcRequest.getPayload().getCustomerDetails().getPolicyTerm() != null
													&& !hdfcRequest.getPayload().getCustomerDetails().getPolicyTerm().equals("")) 
											{
												if (hdfcRequest.getPayload().getCustomerDetails().getPremiumPayable() != null
														&& !hdfcRequest.getPayload().getCustomerDetails().getPremiumPayable().equals("")) 
												{
													for (NomineeDetails nomineeDetail : hdfcRequest.getPayload()
															.getCustomerDetails().getPersonalDetails()
															.getNomineeDetails()) 
													{

														if (nomineeDetail.getFirstName() != null
																&& !nomineeDetail.getFirstName().equals("")
																&& nomineeDetail.getFirstName().matches("[a-zA-Z]+")) 
														{
															if (nomineeDetail.getLastName() != null 
																	&& !nomineeDetail.getLastName().equals("")
																	&& nomineeDetail.getLastName().matches("[a-zA-Z]+")) 
															{
																if (nomineeDetail.getDob() != null && !nomineeDetail.getDob().equals("")) 
																{
																	if (nomineeDetail.getRelationshipToCustomer() != null
																			&& !nomineeDetail.getRelationshipToCustomer().equals("")) 
																	{

																		if (nomineeDetail.getMobile() != null
																				&& nomineeDetail.getMobile().length() == 10
																				&& nomineeDetail.getMobile().matches("[0-9]+")) 
																		{

																		} else {
																			flag = true;
																			msgInfo.setDescription(
																					"Mobile is an Mandatory field in nomineeDetails and it should be 10 digit stating with 9,8,7,6");
																		}

																	} else {
																		flag = true;
																		msgInfo.setDescription(
																				"Relationship to customer is Mandatory field for nominee details");
																	}
																} else {
																	flag = true;
																	msgInfo.setDescription(
																			"Date of birth is Mandatory field for Nominee in MM/DD/YYYY");
																}
															} else {
																flag = true;
																msgInfo.setDescription("Last Name of nominee is  Mandatory field ");
															}
														} else {
															flag = true;
															msgInfo.setDescription("First Name of Nominee is  Mandatory field ");
														}

													}

												} else {
													flag = true;
													msgInfo.setDescription("Premium Payable is  Mandatory field ");
												}

											} else {
												flag = true;
												msgInfo.setDescription("Policy Term is  Mandatory field ");
											}
										} else {
											flag = true;
											msgInfo.setDescription("Basic Premium is Mandatory field ");
										}
									} else {
										flag = true;
										msgInfo.setDescription("Loan Amount is Mandatory field ");
									}

								} else {
									flag = true;
									msgInfo.setDescription("LoanType is  Mandatory field ");
								}

							} else {
								flag = true;
								msgInfo.setDescription("Field SumAssured is  Mandatory field in Customerdetails");
							}

						} else {
							flag = true;
							msgInfo.setDescription("Plan is  Mandatory field in Alphanumeric");
						}
					} else {
						flag = true;
						msgInfo.setDescription("Funding option is  Mandatory field ");
					}

				} else {
					flag = true;
					msgInfo.setDescription("SumAssuredType is  Mandatory field ");
				}

			} else {
				flag = true;
				msgInfo.setDescription("PartnerId is  Mandatory field ");
			}

		}
		if (flag) {
			msgInfo.setCode("400");
			msgInfo.setMessage("Bad Request ");
			msgInfo.setStatus(ERRORSTATUS.FAILURE);
			return msgInfo;
		}

		return null;

	}

}
