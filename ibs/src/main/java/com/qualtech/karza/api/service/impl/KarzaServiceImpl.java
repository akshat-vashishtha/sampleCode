package com.qualtech.karza.api.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.codec.Base64;
import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.karza.api.common.dto.AadharRequest;
import com.qualtech.karza.api.common.dto.AddressRequest;
import com.qualtech.karza.api.common.dto.BankAccountRequest;
import com.qualtech.karza.api.common.dto.CAMemberShipAuthRequest;
import com.qualtech.karza.api.common.dto.CompSearchRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPCINLookUpRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPIdentificationRequest;
import com.qualtech.karza.api.common.dto.DlRequest;
import com.qualtech.karza.api.common.dto.DlRequest2;
import com.qualtech.karza.api.common.dto.EPFAuthOTPRequest;
import com.qualtech.karza.api.common.dto.EPFAuthPassBookRequest;
import com.qualtech.karza.api.common.dto.ESICIdRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest2;
import com.qualtech.karza.api.common.dto.EmailAuthRequest;
import com.qualtech.karza.api.common.dto.EmolpyerLookupRequest;
import com.qualtech.karza.api.common.dto.FSSAILicenceRequest;
import com.qualtech.karza.api.common.dto.FaceRequest;
import com.qualtech.karza.api.common.dto.Form16QuatRequest;
import com.qualtech.karza.api.common.dto.Form16Request;
import com.qualtech.karza.api.common.dto.GSTAuthenticationRequest;
import com.qualtech.karza.api.common.dto.GstIdentificationRequest;
import com.qualtech.karza.api.common.dto.HSNRequest;
import com.qualtech.karza.api.common.dto.ICSIMemberShipRequest;
import com.qualtech.karza.api.common.dto.ICWAIFirmAuthRequest;
import com.qualtech.karza.api.common.dto.ICWAIMembershipRequest;
import com.qualtech.karza.api.common.dto.IECRequest;
import com.qualtech.karza.api.common.dto.IFSCRequest;
import com.qualtech.karza.api.common.dto.ITRAuthRequest;
import com.qualtech.karza.api.common.dto.LpgIdentificationRequest;
import com.qualtech.karza.api.common.dto.LpgRequest;
import com.qualtech.karza.api.common.dto.LpgRequest2;
import com.qualtech.karza.api.common.dto.MCASignatureRequest;
import com.qualtech.karza.api.common.dto.NREGARequest;
import com.qualtech.karza.api.common.dto.NameSimilarityRequest;
import com.qualtech.karza.api.common.dto.PanRequest;
import com.qualtech.karza.api.common.dto.PassportRequest;
import com.qualtech.karza.api.common.dto.PngRequest;
import com.qualtech.karza.api.common.dto.RCAuthRequest;
import com.qualtech.karza.api.common.dto.RCSearchRequest;
import com.qualtech.karza.api.common.dto.ShopEstablishmentRequest;
import com.qualtech.karza.api.common.dto.TanRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest2;
import com.qualtech.karza.api.common.dto.UanLookupRequest;
import com.qualtech.karza.api.common.dto.UdyogAadharRequest;
import com.qualtech.karza.api.common.dto.VoterRequest;
import com.qualtech.karza.api.common.dto.WebsiteDomainRequest;
import com.qualtech.karza.api.db.DBKarza;
import com.qualtech.karza.api.request.AadharDemographics;
import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.KarzaInfo;
import com.qualtech.karza.api.request.KarzaReqRes;
import com.qualtech.karza.api.request.KycOcrRequest;
import com.qualtech.karza.api.response.AadharResponse;
import com.qualtech.karza.api.response.AadharResponsePayload;
import com.qualtech.karza.api.response.AddressResponse;
import com.qualtech.karza.api.response.AddressResponsePayload;
import com.qualtech.karza.api.response.BankAccountResponse;
import com.qualtech.karza.api.response.BankAccountResponsePayload;
import com.qualtech.karza.api.response.CAMemberShipAuthResponse;
import com.qualtech.karza.api.response.CAMemberShipAuthResponsePayload;
import com.qualtech.karza.api.response.CompSearchResponse;
import com.qualtech.karza.api.response.CompSearchResponsePayload;
import com.qualtech.karza.api.response.CompanyLLPCINLookUpResponse;
import com.qualtech.karza.api.response.CompanyLLPCINLookUpResponsePayload;
import com.qualtech.karza.api.response.CompanyLLPIdentificationResponse;
import com.qualtech.karza.api.response.CompanyLLPIdentificationResponsePayload;
import com.qualtech.karza.api.response.DlResponse;
import com.qualtech.karza.api.response.DlResponse2;
import com.qualtech.karza.api.response.DlResponsePayload;
import com.qualtech.karza.api.response.DlResponsePayload2;
import com.qualtech.karza.api.response.EPFAuthOTPResponse;
import com.qualtech.karza.api.response.EPFAuthOTPResponsePayload;
import com.qualtech.karza.api.response.EPFAuthPassBookResponse;
import com.qualtech.karza.api.response.EPFAuthPassBookResponsePayload;
import com.qualtech.karza.api.response.ESICIdResponse;
import com.qualtech.karza.api.response.ESICIdResponsePayload;
import com.qualtech.karza.api.response.ElectricityResponse;
import com.qualtech.karza.api.response.ElectricityResponse2;
import com.qualtech.karza.api.response.ElectricityResponsePayload;
import com.qualtech.karza.api.response.ElectricityResponsePayload2;
import com.qualtech.karza.api.response.EmailAuthResponse;
import com.qualtech.karza.api.response.EmailAuthResponsePayload;
import com.qualtech.karza.api.response.EmployerLookupResponse;
import com.qualtech.karza.api.response.EmployerLookupResponsePayload;
import com.qualtech.karza.api.response.FSSAILicenceResponse;
import com.qualtech.karza.api.response.FSSAILicenceResponsePayload;
import com.qualtech.karza.api.response.FaceResponse;
import com.qualtech.karza.api.response.Form16QuatResponse;
import com.qualtech.karza.api.response.Form16QuatResponsePayload;
import com.qualtech.karza.api.response.Form16Response;
import com.qualtech.karza.api.response.Form16ResponsePayload;
import com.qualtech.karza.api.response.GSTAuthenticationResponse;
import com.qualtech.karza.api.response.GSTAuthenticationResponsePayload;
import com.qualtech.karza.api.response.GstIdentificationResponse;
import com.qualtech.karza.api.response.GstIdentificationResponsePayload;
import com.qualtech.karza.api.response.HSNResponse;
import com.qualtech.karza.api.response.HSNResponsePayload;
import com.qualtech.karza.api.response.ICSIMemberShipResponse;
import com.qualtech.karza.api.response.ICSIMemberShipResponsePayload;
import com.qualtech.karza.api.response.ICWAIFirmAuthResponse;
import com.qualtech.karza.api.response.ICWAIFirmAuthResponsePayload;
import com.qualtech.karza.api.response.ICWAIMembershipResponse;
import com.qualtech.karza.api.response.ICWAIMembershipResponsePayload;
import com.qualtech.karza.api.response.IECResponse;
import com.qualtech.karza.api.response.IECResponsePayload;
import com.qualtech.karza.api.response.IFSCResponse;
import com.qualtech.karza.api.response.IFSCResponsePayload;
import com.qualtech.karza.api.response.ITRAuthResponse;
import com.qualtech.karza.api.response.ITRAuthResponsePayload;
import com.qualtech.karza.api.response.KycOcrResponse;
import com.qualtech.karza.api.response.LpgIdentificationResponse;
import com.qualtech.karza.api.response.LpgIdentificationResponsePayload;
import com.qualtech.karza.api.response.LpgResponse;
import com.qualtech.karza.api.response.LpgResponse2;
import com.qualtech.karza.api.response.LpgResponsePayload;
import com.qualtech.karza.api.response.LpgResponsePayload2;
import com.qualtech.karza.api.response.MCASignatureResponse;
import com.qualtech.karza.api.response.MCASignatureResponsePayload;
import com.qualtech.karza.api.response.MessageInfo;
import com.qualtech.karza.api.response.NREGAResponse;
import com.qualtech.karza.api.response.NREGAResponsePayload;
import com.qualtech.karza.api.response.NameSimilarityResponse;
import com.qualtech.karza.api.response.NameSimilarityResponsePayload;
import com.qualtech.karza.api.response.PanResponse;
import com.qualtech.karza.api.response.PanResponsePayload;
import com.qualtech.karza.api.response.PassportResponse;
import com.qualtech.karza.api.response.PassportResponsePayload;
import com.qualtech.karza.api.response.PngResponse;
import com.qualtech.karza.api.response.PngResponsePayload;
import com.qualtech.karza.api.response.RCAuthResponse;
import com.qualtech.karza.api.response.RCAuthResponsePayload;
import com.qualtech.karza.api.response.RCSearchResponse;
import com.qualtech.karza.api.response.RCSearchResponsePayload;
import com.qualtech.karza.api.response.ShopEstablishmentResponse;
import com.qualtech.karza.api.response.ShopEstablishmentResponsePayload;
import com.qualtech.karza.api.response.TanResponse;
import com.qualtech.karza.api.response.TanResponsePayload;
import com.qualtech.karza.api.response.TelResponsePayload;
import com.qualtech.karza.api.response.TelResponsePayload2;
import com.qualtech.karza.api.response.TelephoneResponse;
import com.qualtech.karza.api.response.TelephoneResponse2;
import com.qualtech.karza.api.response.UanLookupResponse;
import com.qualtech.karza.api.response.UanLookupResponsePayload;
import com.qualtech.karza.api.response.UdyogAadharResponse;
import com.qualtech.karza.api.response.UdyogAadharResponsePayload;
import com.qualtech.karza.api.response.VoterResponse;
import com.qualtech.karza.api.response.VoterResponsePayload;
import com.qualtech.karza.api.response.WebsiteDomainPayload;
import com.qualtech.karza.api.response.WebsiteDomainResponse;
import com.qualtech.karza.api.service.KarzaService;
import com.qualtech.karza.api.utils.KarzaUtils;
import com.qualtech.karza.api.utils.StringConstants;

@Service
public class KarzaServiceImpl implements KarzaService
{
	static Logger logger = Logger.getLogger(KarzaServiceImpl.class.getName());
	@Autowired PropertyFile resProp;
	@Autowired DBKarza dbKarza;
	private static File file;
	static ResourceBundle res=null;
	
	static
	{
		try 
		{
			res = ResourceBundle.getBundle("hardcode");
		}
		catch(Exception ex)
		{
		}
	}

	@Override
	public KycOcrResponse ocrRequestServiceQC(KycOcrRequest ocrRequest, KarzaReqRes rq) 
	{
		KycOcrResponse ocrResponse = new KycOcrResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key ="td89e4TCG76nd7gz9llh"; //resProp.getString("com.karza.key40");
		String urlSave ="http://ocr.karza.in:8888/kyc";// resProp.getString("com.karza.pan.url");
		KarzaInfo info = new KarzaInfo();
		try
		{
			if (ocrRequest != null) 
			{
				if (ocrRequest.getPayload() != null && ocrRequest.getPayload().getFile() != null  && requestValidation(ocrRequest.getPayload().getFile()))
				{
					if (checkValidation(ocrRequest.getHeader())) 
					{
						//	info = kycOcrhttpCall(urlSave,key);
						info.setResponseCode(200);
						try 
						{
							FileInputStream fin = new FileInputStream(file);
							byte fileContent[] = new byte[(int)file.length()];
							fin.read(fileContent);
							fin.close();
							rq.setIntReq(Base64.encodeBytes(fileContent));
							ocrRequest.getPayload().setFile(Base64.encodeBytes(fileContent));
							rq.setIntRes(info.getResponse());
						}
						catch (Exception e) 
						{
							logger.error("Error while setting karza internal req res:" + e);
						}

						if (info.getResponseCode() == 200)
						{
							msgInfo.setCode(""+info.getResponseCode());
							try 
							{
								ocrResponse.setPayload(KarzaUtils.kycOcrJsonToPayload(info.getResponse()));
								String statusmsg = "No status code received from third party";
								if (ocrResponse.getPayload() != null) 
								{

									if (ocrResponse.getPayload().getStatus_code() != 0)
									{
										statusmsg = getStatusMsg(ocrResponse.getPayload().getStatus_code());

									}
								}
								String price=null;
								if (ocrResponse.getPayload().getStatus_code() == 101)
								{
									//price=resProp.getString("com.karza.pan.success.price");
								}
								else
								{
									//price=resProp.getString("com.karza.pan.failure.price");
								}
								rq.setPrice(price);
								ocrResponse.getPayload().setStatus_msg(statusmsg);

								if (ocrResponse.getPayload().getStatus_code() == 101) 
								{
									msgInfo.setStatus(StringConstants.SUCCESS.toString());
									msgInfo.setMessage(statusmsg);
									msgInfo.setCode("" + ocrResponse.getPayload().getStatus_code());
								}
								else if (ocrResponse.getPayload().getStatus_code() == 102)
								{
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage(statusmsg);
									msgInfo.setCode("" + ocrResponse.getPayload().getStatus_code());
								}
								else
								{
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage(statusmsg);
									msgInfo.setCode("" + ocrResponse.getPayload().getStatus_code());
								}

							} catch (Exception ex) {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("" + StringConstants.MESSAGE600);
								msgInfo.setCode(StringConstants.C_600.toString());
								logger.info("Failure from 3rd Party API : " + info.getResponse());
							}
							// for invalid input

						}else {
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
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
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}finally {
			if(file.exists()){
				file.delete();
			}
		}
		ocrResponse.setMsgInfo(msgInfo);
		ocrResponse.setHeader(ocrRequest.getHeader());
		return ocrResponse;
	}


	@Override
	public CompanyLLPIdentificationResponse companyLLPIdentificationRequestService(CompanyLLPIdentificationRequest companyLLPIdentificationRequest, KarzaReqRes reqRes)
	{
		CompanyLLPIdentificationResponse companyLLPIdentificationResponse=new CompanyLLPIdentificationResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.llpIdentification.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.LLPIden.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try 
		{
			if (companyLLPIdentificationRequest != null) 
			{
				if (companyLLPIdentificationRequest.getPayload() != null 
						&& companyLLPIdentificationRequest.getPayload().getCin() != null && !companyLLPIdentificationRequest.getPayload().getCin().equals("")
						&& !companyLLPIdentificationRequest.getPayload().getConsent().equals("") && companyLLPIdentificationRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(companyLLPIdentificationRequest.getHeader());
					if (result == true)
					{
						String responseCompanyLLPIdentificationCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();
						if(hardCodedEnv.equalsIgnoreCase("Y"))
						{
							String	res="{\"request_id\": \"63285632-1878-11e8-b8c6-8198354346ec\", \"result\": {\"Whether_Listed_or_not\": \"Unlisted\", \"Company_Status\": \"Active\", \"ROC_Code\": \"RoC-Mumbai\", \"Company_SubCategory\": \"Non-govt company\", \"Email_Id\": \"omkar_shirhatti@yahoo.com\", \"Suspended_at_stock_exchange\": \" -\", \"alternative_address\": \"-\", \"Date_of_Balance_Sheet\": \"31/03/2017\", \"cin\": \"U74120MH2015PTC265316\", \"Authorised_Capital(Rs)\": \"9500000.0\", \"Company_Category\": \"Company limited by Shares\", \"Date_of_last_AGM\": \"30/09/2017\", \"Class_of_Company\": \"Private\", \"Company_Name\": \"KARZA TECHNOLOGIES PRIVATE LIMITED\", \"Paid_up_Capital(Rs)\": \"7571410.0\", \"Registered_Address\": \"1302, ORCHID, CAMPCOLA COMPOUND, 18, B. G. KHER ROAD, WORLI MUMBAI Mumbai City MH 400018 IN \", \"Number_of_Members\": \"0\", \"Registration_Number\": \" 265316\", \"Date_of_Incorporation\": \"08/06/2015\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(companyLLPIdentificationRequest.getPayload()), urlSave,key);
						}
						try 
						{
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(companyLLPIdentificationRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.company_llp_identification.success.price");
							}
							else 
							{
								price=resProp.getString("com.karza.company_llp_identification.failure.price");
							}
							reqRes.setPrice(price);
						}
						catch (Exception e) 
						{
							logger.error("Error while updating karza internal req res:" + e);
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
										responseCompanyLLPIdentificationCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("Whether_Listed_or_not", "whetherListedOrNot")
												.replaceAll("Company_Status", "companyStatus")
												.replaceAll("ROC_Code", "rocCode")
												.replaceAll("Company_SubCategory", "companySubCategory")
												.replaceAll("Email_Id", "emailId")
												.replaceAll("Suspended_at_stock_exchange", "suspendedAtStockExchange")
												.replaceAll("alternative_address", "alternativeAddress")
												.replaceAll("Date_of_Balance_Sheet", "dateOfBalanceSheet")
												.replaceAll("Authorised_Capital(Rs)", "authorisedCapital")
												.replaceAll("Company_Category", "companyCategory")
												.replaceAll("Date_of_last_AGM", "dateOfLastAGM")
												.replaceAll("Company_Name", "companyName")
												.replaceAll("Registered_Address", "registeredAddress")
												.replaceAll("Number_of_Members", "numberOfMembers")
												.replaceAll("Class_of_Company", "classOfCompany")
												.replaceAll("Registration_Number", "registrationNumber")
												.replaceAll("Date_of_Incorporation", "dateofIncorporation")
												.replaceAll("Paid_up_Capital(Rs)", "paidUpCapital");
										responseCompanyLLPIdentificationCall=responseCompanyLLPIdentificationCall
												.replace("Authorised_Capital(Rs)", "authorisedCapital")
												.replace("Paid_up_Capital(Rs)", "paidUpCapital");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										companyLLPIdentificationResponse.setPayload(om.readValue(responseCompanyLLPIdentificationCall, CompanyLLPIdentificationResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (companyLLPIdentificationResponse.getPayload() != null) 
										{
											if (companyLLPIdentificationResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(companyLLPIdentificationResponse.getPayload().getStatus_code());
											}
										}
										companyLLPIdentificationResponse.getPayload().setStatus_msg(statusmsg);
										if (companyLLPIdentificationResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(companyLLPIdentificationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + companyLLPIdentificationResponse.getPayload().getStatus_code());
										}
										else if (companyLLPIdentificationResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(companyLLPIdentificationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + companyLLPIdentificationResponse.getPayload().getStatus_code());
										}
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(companyLLPIdentificationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + companyLLPIdentificationResponse.getPayload().getStatus_code());
										}
									}
									catch (Exception ex) 
									{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								}
								else 
								{
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
							// for invalid input
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
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}
		companyLLPIdentificationResponse.setMsgInfo(msgInfo);
		companyLLPIdentificationResponse.setHeader(companyLLPIdentificationRequest.getHeader());
		return companyLLPIdentificationResponse;
	}


	@Override
	public DlResponse dlRequestService(final DlRequest dlRequest,KarzaReqRes rq) 
	{
		DlResponse dlResponse = new DlResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key");
		String urlSave = resProp.getString("com.karza.dl.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.dlv2.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try 
		{
			if (dlRequest != null) 
			{
				if (dlRequest.getPayload() != null && dlRequest.getPayload().getDl_no() != null
						&& dlRequest.getPayload().getDob() != null 
						&& !dlRequest.getPayload().getDob().equals("")
						&& !dlRequest.getPayload().getDl_no().equals("") 
						&& !dlRequest.getPayload().getName().equals("")
						&& dlRequest.getPayload().getName() != null) {
					boolean result = checkValidation(dlRequest.getHeader());
					if (result == true) 
					{
						String responseDlCall = null;
						ObjectMapper om = new ObjectMapper();
						if (hardCodedEnv.equalsIgnoreCase("Y"))
						{
							String res = "{\"request_id\": \"f76b5271-1792-11e8-bed1-c912f8982ea1\", \"result\": {\"dl\": {\"issue_date\": \"15-07-2005\", \"father/husband\": \"VIJAI RAJ  SAMDARIA\", \"name\": \"GAURAV  SAMDARIA\", \"img\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCACgAHgDASIAAhEBAxEB/8QAHAAAAAcBAQAAAAAAAAAAAAAAAQIDBAUGBwAI/8QANxAAAQMDAwIEBQIFAwUAAAAAAQACAwQFERIhMQZBEyJRYQcUMnGBkaEVUrHB0SMzQmJyguHw/8QAGgEAAgMBAQAAAAAAAAAAAAAAAwQBAgUABv/EACURAAMAAgICAgICAwAAAAAAAAABAgMREiEEMRNBIlEUMlJhof/aAAwDAQACEQMRAD8ApDQhARQ5HbumULggI7fRA1CpRAbCMDsit4KMFO0ckwQULd0i94aTlAKlreVHJHaY5Rmps2pjP/LdG8doOxH6ruSO0xyhBwEQE4B5RvsrFQwKUaUk3hHacqCGhVrsALkA4XKxBCAJRg4SbUozjKoEYcBCBnugBRXnTz+FDIQq0YGTwiy5DCQfKF0cnlbluQDkoHML8u3x6Z4S9230g0oYSOcSckhIkOycnZPjD4jyIwSewKbzRODi0oey+hAbjlFAJ3ydko+MgDCPHHqa7HI7KU2iGhxR1Za3S4Zx7p3T1QedLm6T91FnyHjK7xy14PZFVtFHJPA7I8abUswliBGx9E5jR5rYFpoUBXIAuUlSHwjNO2EUIQoCCjSgnDWtDyd/Rcz0TOpJdKW9kPI+iZQpBO4yaTgg9lY6Smb8i+aXyhvYjndJdA2R13rd2+Rp+ojlaFXdLfOVENtoA0zuOuXJ2YwevosvJ5KVOR6MG12U2ktmuN5jYQQ3UcjfCUqbC1tLDK4byAnj0K1O29HSClmma7IDcMZ6n7qr1EZfBHSztLapj3N375KAs++wvwpLSKC21tfVOix9Ptyk7jZvl6lwYQAQMjHGytN5pJqOrrXRxPc6BscpAHY7Jhe2SyvikiGvU0FxA9URZ1v2Q8L16KhU0zQ3DXg49kxfEfTZTNxoZKerkaQcEAg49kwZNqiMb2jbgpyK5LYvUufYWieYntzwVLRkOGVCh48QDGwUtSnMDT65/qmcT7AZF0OW8LkDTsuRwJE53XZykte/CM12Twqlxdh3TGqcROY28kt/OTjCesOBn9glv4ZV09wtNbPb6k01RONGWHEmOwS/kVxh96C4odUjVrZPbug7NSxPa2ouErNehu3O/m5xymsXxPmoZJfDttLGJTmQtcMnbjKdXm4xNo3VtdaKd1TIcBgOt2AMNGfsBthV+4XGst76f+IWWjp46qMyQ5hznfGOFmRxme1tjNqm97L7ZPi1RVc0FPJSGHcN8jg4fsrg+itFfM2r0jVyDjCyN1trGV9NFNZ4WvkDXt8Ful2DvxuOPZaTaqcfJzviqP8ATjOluW9++/scoOSlT9dB8UtL32LXKp6et9wqZayRpdLA1j2ac5A3Vaqur+knufDDA0AADOjCieqBb6xzzVmqkBBBfF5QBxsd8quVdp6PfTtjxdKOpZgl8kgcC09zwunHjfXH/pF1c9qiduVBar6dVtqItfJZndZTfbe+23R9O84JPJHZXR3SZja2r6YuYkmY4eV5wSEHXlO6s6fhucsAbWUzvCm34OcI2JqK0iMi5TtmezRBjc6sn7KVpdoGDthRzW+I4N5wMlScYwwD2Wph9mfk9CgOy5ADsuTIEhG8pRo4STXbpRruAqPYRC7R5dgdRIH6rfOkaed11o7bUSCeipIxURa25LXaQDj7nKwu1RGorqaAY1STNaM/cL0bSRMo+oGvaN3QMiyONuVl+c92pHfG6jZJXbpqCtJe2Jg3yNIwmD+mGzCNtTLO4xcA4IVuppA/G6Vla3OUio6DK9Mq/wDAaeD/AFC6XxXbBwd5gD2B7KwRxRUNvZFGwBgbwe57kosMeuZrjjQOB6pzXtHhYVphLbRzp00mUF9j+bbJTGTLdZcwEY2Jzj91E3zomKvukddWQNnexrWGMO0tc1vAP6K6RR5qHMeMYOxynppB/MUBXSfQdqfszG39MVMN5kqPCYyJ7wRBHnSwDjCrvxEsFxpbPc62jcIqad+Zadxz3+oei2iWmYzJzv7gKlfEWWOHpi54xtFsPfKvju5pPZW5mlow22W2sktxqY6aUwjGuXT5QE4HtwtO6ioYqDpFlSS9rvkIz4ecNDiBkY/JWXs2aBvsPVbHhZHkl1/sz/JxrHSlBgVyAb5XJ4UIMFKMKSaUozhQXRIWmq+SuVHVFupsMzXkeoC3un6ltt5rIX2ucSOa3W9mnBb7Lz1G79VcfhfUiDqIs8rTMzGfskvKxKly+w+G2vxPQNBWAtH+VKxyCRuXHDVTKKbc4OAPdJV11uLS5tso/mdP1anaWj9lly/2NpN+hTqy1XKWvjrLTc5I2RbmA/Sf3Ua69dS1MfhRvhiLdi47p6KvqCWm8UfISEc08eSW/num0t1u/wBP8Hccbuw3C662vxYxjw0u2iW6XfcXtcbs+N7m/wDJvdWJsw0qkUXUtK+YxPY+lqhzG8bfqpuKu8SIvG4+6X1rtsmu+h5cKkNa7/KptxDLlXNgnYXwfW9v8wCf3Ot1agCqZP1ZT2e7SCSkNSTHp+vTjv6FXnFeROZBvJOPumPPi7XRvsVsggBaJHgOB2yBn/78LLu5HopbqvqCfqG4MnmjEMUTNEUQOdI9c91EA4HC3fGx/FjUv2ZmbJ8lugWnsuRQd8rkxsCQjUqzgIgacpRrduVzLJB2KRs1UaG501Xw2OQZ9wo9oRyD4Z37bKl6cvYSE0zdaCvEgaWnIeM8qche9kJEZzlZzQzGjo6JxJ0vja4H8K62K7ROi87hssLJGn0PS/2NLlc623TOMFMHg+5CSpr5XXFzopo3wj1a8kf0Vxp6+3zx4cxjn98pGQ0DA4t8NrvQKlKtDEZeP2RNLQw+GS9ofJ/M4ZKB9U2nhLN05qa2Cmic9zgARsqbcrs2SSRzdmnjdUnHT9k1kTF7zdGQQSPJHHqsrq6h09TLM76nnnPZT/U9S91AHO2EjtI35CqpO2y1vExqZ2ZuetvQsHD0Rg7bhN9WEow5CdkWYrqXIAMLlYgYtj25RxHtsnDIydmgudngDKdxW5xiMk72wsHOecfZVbGUkRzWbHdSFHRSfJVNZKxwiiYSAR9Sn7baKaCFryC+R2+pw7dtvthOLw0utNTG04boxgBUe2mcqW1onqSjNb0rbi4YeYcg49yq1M+vtsz2hx0j2Wj9PwN/glBFpyGwt/om9zs/jl2GhzfRY3yab2PqNlEjvdTj/ccD90pHfKhgyZC4+6kK3ph/iOdCRH7ELqTplxyZ36/+lrVV5UGWHZG1F3qq1+nLnDs0J1R0EzzrqfIzsOcqyW+wuiYDBTiNvq45KkIrdFTgvld4kg9RsPwhvNvpHfCkZX8RGyMfbY2tLY8k4UI6N3hB4BxhXTro+PVQMLc4Ooj24UNHG3RjGGHsQtfxm+Bl50uRXWgkpaMc7p/U24sidJCQQDuPRMdBBITaFn0GDvZcg0lcpK7RsMvwuD4TBSXoMkcQPLT4DRz/ADIerbfZrBYaa3R00NRK1wzUP+tzvU/4Ue/ru81FLVxuZDFlhGqKN2po9QqbQ6qqnc+aeSVof5dbifui6xx2uxXHHkZGub0iXdLrcf7Iso8Snmj7uZj90EDPIMozhg5H2QX2maM6TNDsPmtNI8DbQGn8bf2UoWBzdh+VHfD+P5q2mBzgMElufuVY/kSCRledv+zNWWuiFc0jI/qEMbM76Wj3AUw2gHc7/ZA+j05A/ogOQyv6ImUua3fOFE18+hjyfpAySp6ejLnHJOD6Kldb1HgAUMP1v3ec9kbBjd1opkyKJ7ZSrhUOrKyScgjJwN84HCSAccNIy3sl/BPojNiJOGjdbszpaRl0+Q5tljr7yyentdOZ5GNy4ag39ykKrpDqCkeGT2eqcDsPDaH/ALgqz9L9UN6bpaqF1vFRJUEO1h2k7fqrpauvbTUUjpKySSgmB8zDlw57EBMRLf2ZufJcPqejH6rpK9Qw+I+1VjGe8a5ba3rXp9oLheWkZ9HH+y5X+N/sW/lv/En+nekbVYoJ2UsRlMv1Onw8kemccLP/AIvWuktZtD7bRxU8L3yeKYo8DOBhKXL4p1dPcz8vRwvoQcBjiQ93vq/9KH6l+IN4ubTHS+FRU5Hma1oe4/8Akf7BBjHxe2adW6X4orgLiSdLjn2KSka7clrsfZQ5iqu9wqfw5DJE57Ga5pnPYch5durN67QRG1/Cm1TS281lQ6RkOS2JhGMjO5/XKt87GCZwieJADgkHOD6FecrrcrtPSRPbc63UwgANkLRj7BSfw66rrLNLJDVPc+GcnUHOOxzzkrOy+PKnftjOPJTrv0bs0DJBxn7oTC6V2I26s+nZVanvY8BkuvU9zsYHYZVR+IfVVwfcaaltVVJSmDcywu+vIzv+qUxYfkevQbJbhGkXtz7VQS1TqWeoeBhrImF2/v6LG62SSuqpqidwMsh3aTuPZOaX4i9UUsYiFdHUOG2ZYQdk5qOu33CMi82KkqqnH+/C4xOHpnY52WjhwTi/r2J1dU+yCdA8uxsAnFst8lVWwUdOMzTPDRj3KY1VaHPbLFTuibnzNMmSf2V3+GV56foauprLhXRwVOAyKOQHLQeTnCJfJLpHLS9ktN8JdUpkhvLmAgAtNPnt/wByi778NblQeG+3SfxEEYd5fDLfxk5Wr0t5ttVEZIK+lkZzkSBOKWpgq4/FpZY5mZwTGQ5Rxqe9leUvowOXpK9RvPiWerOOSyPOVy9CcbAlcp5X+yvxz+j/2Q==\", \"blood_group\": \"B+\", \"dob\": \"12-06-1987\", \"validity\": {\"non-transport\": \"15-07-2005 to 14-07-2025\", \"transport\": \"\"}, \"cov_details\": [{\"issue_date\": \"15-07-2005 RTO,MUMBAI CENTRAL\", \"cov\": \"LMV\"}], \"address\": \"1302 13 TH FLOOR, ORCHID 18, B.G.KHER ROAD, WORLI NAKA MUMBAI. 400018\"}, \"name\": {\"score\": 0.09898989898989899, \"match\": false}}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						} 
						else
						{
							info = httpCall(om.writeValueAsString(dlRequest.getPayload()), urlSave, key);
						}
						try
						{
							String price=null;
							rq.setIntReq(om.writeValueAsString(dlRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.dl.success.price");
							}else {
								price=resProp.getString("com.karza.dl.failure.price");
							}
							rq.setPrice(price);
						} 
						catch (Exception e)
						{
							logger.error("Error while updating karza internal req res:" + e);
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
										responseDlCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("father/husband", "father_husband").replaceAll("LMV", "lmv")
												.replaceAll("non-transport", "non_transport");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										dlResponse.setPayload(om.readValue(responseDlCall, DlResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (dlResponse.getPayload() != null) 
										{
											if (dlResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(dlResponse.getPayload().getStatus_code());

											}
										}
										dlResponse.getPayload().setStatus_msg(statusmsg);
										if (dlResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(dlResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + dlResponse.getPayload().getStatus_code());
										}
										else if (dlResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(dlResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + dlResponse.getPayload().getStatus_code());
										}
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(dlResponse.getPayload().getStatus_msg());
											msgInfo.setCode(""+dlResponse.getPayload().getStatus_code());
										}
									}
									catch (Exception ex)
									{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
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
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
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
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}
		dlResponse.setMsgInfo(msgInfo);
		dlResponse.setHeader(dlRequest.getHeader());
		return dlResponse;
	}



	@Override
	public TelephoneResponse2 teleRequestServiceQC(final TelephoneRequest2 teleRequest,KarzaReqRes rq)
	{ 
		TelephoneResponse2 teleResponse = new TelephoneResponse2();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave = resProp.getString("com.karza.tele.v3.url");
		//String urlSave = "https://testapi.karza.in/v2/tele";
		String key = resProp.getString("com.karza.key40");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.telev3.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try 
		{
			if (teleRequest != null) 
			{
				if (teleRequest.getPayload() != null
						&& teleRequest.getPayload().getTel_no() != null	&& !teleRequest.getPayload().getTel_no().equals("")
						&& !teleRequest.getPayload().getConsent().equals("") && teleRequest.getPayload().getConsent() != null) 
				{
					boolean result = checkValidation(teleRequest.getHeader());
					if (result == true)
					{
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();
						if(hardCodedEnv.equalsIgnoreCase("Y"))
						{
							String	res="{\"request_id\": \"51db5196-2217-11e8-91bb-5d8d9682a0b5\", \"result\": {\"category\": \"OTHERS\", \"TelephoneNo\": \"0251-2880343\", \"name\": \"OMKAR MILIND SHIRHATTI\", \"Installation_Type\": \"LANDLINE\", \"address\": \"PUSHPA BLDG 125, AJANTHA SOC,NANDIVALI RD,X, 999999 Maharashtra\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(teleRequest.getPayload()), urlSave,key);
						}
						try 
						{
							String price=null;
							rq.setIntReq(ow.writeValueAsString(teleRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.tele.success.price");
							}else {
								price=resProp.getString("com.karza.tele.failure.price");
							}
							rq.setPrice(price);

						} 
						catch (Exception e) 
						{
							logger.error("Error while updating karza internal req res:" + e);
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
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										teleResponse.setPayload(om.readValue(
												info.getResponse().replaceAll("TelephoneNo", "telephoneNo")
												.replaceAll("status-code", "status_code")
												.replaceAll("Installation_Type", "installation_Type")
												.replaceAll("category", "installation_Type"),
												TelResponsePayload2.class));
										String statusmsg = "No status code received from third party";
										if (teleResponse.getPayload() != null) 
										{
											if (teleResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(teleResponse.getPayload().getStatus_code());
											}
										}
										teleResponse.getPayload().setStatus_msg(statusmsg);
										if (teleResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(teleResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + teleResponse.getPayload().getStatus_code());
										} 
										else if (teleResponse.getPayload().getStatus_code() == 102) 
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(teleResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + teleResponse.getPayload().getStatus_code());
										} 
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(teleResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + teleResponse.getPayload().getStatus_code());
										}
									} 
									catch (Exception ex)
									{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
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
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+400);
							}

						}

						else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;

							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							// ErrorResponse errorResponse =
							// om.readValue(info.getResponse(),ErrorResponse.class);
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}
			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		teleResponse.setMsgInfo(msgInfo);
		teleResponse.setHeader(teleRequest.getHeader());
		return teleResponse;
	}
	@Override
	public ElectricityResponse2 electricityRequestServiceQC(final ElectricalRequest2 elecRequest,KarzaReqRes rq) {
		ElectricityResponse2 elecResponse = new ElectricityResponse2();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave ="https://testapi.karza.in/v2/elec";
		//			String urlSave = resProp.getString("com.karza.elec.url");
		String key = resProp.getString("com.karza.key40");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.elecv3.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}

		try {
			if (elecRequest != null) {

				if (elecRequest.getPayload() != null 
						&& elecRequest.getPayload().getConsumer_id() != null && !elecRequest.getPayload().getConsumer_id().equals("")
						&& elecRequest.getPayload().getService_provider() != null && !elecRequest.getPayload().getService_provider().equals("")
						&& !elecRequest.getPayload().getConsent().equals("") && elecRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(elecRequest.getHeader());
					if (result == true) {
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){
							//for Bank Account
							String	res="{\"request_id\": \"74c48094-2216-11e8-880b-790d22aa9b61\", \"result\": {\"bill_no\": \"\", \"bill_due_date\": \"15-03-2018\", \"consumer_number\": \"555295175\", \"bill_amount\": \"420.0\", \"bill_issue_date\": \"\", \"mobile_number\": \"9819574650\", \"amount_payable\": \"\", \"total_amount\": \"\", \"address\": \"R:2306;F:23;W:;P:1090/1092;;STANDARD PRABHA CHS.LTD.;STANDARD MILL COM, POUND;NEW PRABHADEVI ROAD;;PRABHADEVI;Pin:400025\", \"consumer_name\": \"DEPUTY ENGG HOUSING ELECT.SUB-DN II\", \"email_address\": \"\", \"bill_date\": \"20-02-2018\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(elecRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(elecRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.elec.success.price");
							}else {
								price=resProp.getString("com.karza.elec.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
						}

						if (info.getResponseCode() == 200) {

							msgInfo.setCode(""+info.getResponseCode());

							if (!info.getResponse().toString().contains("Invalid Input")) {
								if (!info.getResponse().toString().contains("Max retries exceeded")) {
									try {

										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										elecResponse.setPayload(om.readValue(
												info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("bill_no", "bill_no"),
												ElectricityResponsePayload2.class));

										String statusmsg = "No status code received from third party";
										if (elecResponse.getPayload() != null) {

											if (elecResponse.getPayload().getStatus_code() != 0) {
												statusmsg = getStatusMsg(elecResponse.getPayload().getStatus_code());

											}
										}

										elecResponse.getPayload().setStatus_msg(statusmsg);

										if (elecResponse.getPayload().getStatus_code() == 101) {
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(elecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + elecResponse.getPayload().getStatus_code());
										} else if (elecResponse.getPayload().getStatus_code() == 102) {
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(elecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + elecResponse.getPayload().getStatus_code());
										} else {

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(elecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + elecResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {


										// ErrorResponse errorResponse =
										// om.readValue(info.getResponse(),ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);
								}

							} else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}

						}

						else {
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(info.getResponse(),ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}
			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		elecResponse.setMsgInfo(msgInfo);
		elecResponse.setHeader(elecRequest.getHeader());
		return elecResponse;
	}

	@Override
	public RCAuthResponse rcAuthRequestService(RCAuthRequest rcAuthRequest, KarzaReqRes rq){
		RCAuthResponse rcAuthResponse=new RCAuthResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.rcAuth.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.rcauth.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (rcAuthRequest != null) {

				if (rcAuthRequest.getPayload() != null 
						&& !rcAuthRequest.getPayload().getConsent().equals("") && rcAuthRequest.getPayload().getConsent() != null
						&& !rcAuthRequest.getPayload().getReg_no().equals("") && rcAuthRequest.getPayload().getReg_no() != null) {

					boolean result = checkValidation(rcAuthRequest.getHeader());
					if (result == true) {
						String rcAuthResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"b06b7ea5-1af3-11e8-b140-d99764438e21\", \"result\": {\"rc_eng_no\": \"MNH517641\", \"rc_vh_class_desc\": \"Goods Carrier\", \"rc_present_address\": \"G-3 KANTI EMPIRE NEAR VARTAK, COLLEGE, VASAI-W, TAL-VASAI, DIST-PALGHAR, -401201\", \"rc_registered_at\": \"VASAI, Maharashtra\", \"rc_regn_dt\": \"20-Mar-2008\", \"rc_insurance_comp\": \"UNIVERSAL SOMPO GENERAL INSURANCE CO.LTD\", \"rc_color\": \"NA\", \"rc_manu_month_yr\": \"3/2008\", \"rc_sleeper_cap\": \"\", \"rc_maker_desc\": \"ASHOK LEYLAND LTD\", \"rc_status_as_on\": \"26-Feb-2018\", \"rc_insurance_upto\": \"04-Jul-2017\", \"rc_cubic_cap\": \"5883.00\", \"rc_owner_sr\": \"2\", \"rc_permanent_address\": \"G-3 KANTI EMPIRE NEAR VARTAK, COLLEGE, VASAI-W, TAL-VASAI, DIST-PALGHAR, -401201\", \"rc_financer\": \"HDB FINANCIAL SERVICE LTD\", \"rc_owner_name\": \"SKYWAYS RMC PLANTS PVT LTD\", \"rc_f_name\": \"DIRECTOR\", \"rc_unld_wt\": \"8960\", \"rc_chasi_no\": \"MNR247476\", \"rc_maker_model\": \"ASHOK LEYLAND LTD, TAURUS 2516C/3-150 WB\", \"rc_gvw\": \"25000\", \"rc_vch_catg\": \"HGV\", \"rc_tax_upto\": \"28-Feb-2018\", \"rc_stand_cap\": \"\", \"rc_fit_upto\": \"26-Mar-2016\", \"stautsMessage\": \"OK\", \"rc_insurance_policy_no\": \"201830210459\", \"rc_body_type_desc\": \"TRUCK (CLOSED BODY)\", \"rc_wheelbase\": \"3810\", \"rc_norms_desc\": \"Not Available\", \"rc_regn_no\": \"MH04DK6617\", \"rc_fuel_desc\": \"DIESEL\", \"rc_no_cyl\": \"6\", \"rc_seat_cap\": \"2\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(rcAuthRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(rcAuthRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.rc_authentication.success.price");
							}else {
								price=resProp.getString("com.karza.rc_authentication.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										rcAuthResponseCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										rcAuthResponse.setPayload(om.readValue(rcAuthResponseCall, RCAuthResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (rcAuthResponse.getPayload() != null) 
										{

											if (rcAuthResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(rcAuthResponse.getPayload().getStatus_code());

											}
										}

										rcAuthResponse.getPayload().setStatus_msg(statusmsg);

										if (rcAuthResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(rcAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + rcAuthResponse.getPayload().getStatus_code());
										}
										else if (rcAuthResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(rcAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + rcAuthResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(rcAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + rcAuthResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		rcAuthResponse.setMsgInfo(msgInfo);
		rcAuthResponse.setHeader(rcAuthRequest.getHeader());
		return rcAuthResponse;
	}


	@Override
	public FSSAILicenceResponse fssaiLicenceRequestService(final FSSAILicenceRequest fssaiLicenceRequest, KarzaReqRes rq) {
		FSSAILicenceResponse fssaiLicenceResponse = new FSSAILicenceResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.fssai.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Karza.FSSAILic.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (fssaiLicenceRequest != null) {

				if (fssaiLicenceRequest.getPayload() != null 
						&& fssaiLicenceRequest.getPayload().getReg_no() != null && !fssaiLicenceRequest.getPayload().getReg_no().equals("")
						&& !fssaiLicenceRequest.getPayload().getConsent().equals("") && fssaiLicenceRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(fssaiLicenceRequest.getHeader());
					if (result == true) {
						String responseFssaiLicenceCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"7187a662-1930-11e8-a817-67e743db6346\", \"result\": {\"Status\": \"Active\", \"LicType\": \"Central License\", \"LicNO\": \"10013022002245\", \"FirmName\": \"PARLE PRODUCTS PVT. LTD.\", \"Address\": \"PARLE PRODUCTS PVT LTD NORTH LEVEL CROSSING VILE PARLE  E   District - Mumbai  Suburban    STATE - Maharashtra\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(fssaiLicenceRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(fssaiLicenceRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.fassai_licence.success.price");
							}else {
								price=resProp.getString("com.karza.fassai_licence.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseFssaiLicenceCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("Status", "status")
												.replaceAll("LicType", "licType")
												.replaceAll("LicNO", "licNo")
												.replaceAll("FirmName", "firmName")
												.replaceAll("Address", "address");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										fssaiLicenceResponse.setPayload(om.readValue(responseFssaiLicenceCall, FSSAILicenceResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (fssaiLicenceResponse.getPayload() != null) 
										{

											if (fssaiLicenceResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(fssaiLicenceResponse.getPayload().getStatus_code());

											}
										}

										fssaiLicenceResponse.getPayload().setStatus_msg(statusmsg);

										if (fssaiLicenceResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(fssaiLicenceResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + fssaiLicenceResponse.getPayload().getStatus_code());
										}
										else if (fssaiLicenceResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(fssaiLicenceResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + fssaiLicenceResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(fssaiLicenceResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + fssaiLicenceResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		fssaiLicenceResponse.setMsgInfo(msgInfo);
		fssaiLicenceResponse.setHeader(fssaiLicenceRequest.getHeader());
		return fssaiLicenceResponse;
	}

	@Override
	public ElectricityResponse electricityRequestService(final ElectricalRequest elecRequest, KarzaReqRes rq) {

		ElectricityResponse elecResponse = new ElectricityResponse();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave = resProp.getString("com.karza.elec.url");
		String key = resProp.getString("com.karza.key");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.elecv2.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}

		try {
			if (elecRequest != null) {

				if (elecRequest.getPayload() != null && elecRequest.getPayload().getConsumer_id() != null
						&& !elecRequest.getPayload().getConsumer_id().equals("")
						&& elecRequest.getPayload().getService_provider() != null
						&& !elecRequest.getPayload().getService_provider().equals("")
						&& !elecRequest.getPayload().getName().equals("") && elecRequest.getPayload().getName() != null
						&& !elecRequest.getPayload().getConsent().equals("")
						&& elecRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(elecRequest.getHeader());
					if (result == true) {
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"351ba232-2100-11e8-bdff-571e20df778e\", \"result\": {\"elec\": {\"bill_no\": \"\", \"bill_due_date\": \"15-03-2018\", \"consumer_number\": \"555295175\", \"bill_amount\": \"420.0\", \"bill_issue_date\": \"\", \"address\": \"R:2306;F:23;W:;P:1090/1092;;STANDARD PRABHA CHS.LTD.;STANDARD MILL COM, POUND;NEW PRABHADEVI ROAD;;PRABHADEVI;Pin:400025\", \"amount_payable\": \"\", \"bill_date\": \"20-02-2018\", \"mobile_number\": \"9819574650\", \"consumer_name\": \"DEPUTY ENGG HOUSING ELECT.SUB-DN II\", \"email_address\": \"\", \"total_amount\": \"\"}, \"name\": {\"score\": 0.060018664909969245, \"match\": false}}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(elecRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(elecRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.elec.success.price");
							}else {
								price=resProp.getString("com.karza.elec.failure.price");
							}
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
						}

						if (info.getResponseCode() == 200) {

							msgInfo.setCode(""+info.getResponseCode());

							if (!info.getResponse().toString().contains("Invalid Input")) {
								if (!info.getResponse().toString().contains("Max retries exceeded")) {
									try {
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										elecResponse.setPayload(om.readValue(
												info.getResponse().replaceAll("status-code", "status_code"),
												ElectricityResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (elecResponse.getPayload() != null) {

											if (elecResponse.getPayload().getStatus_code() != 0) {
												statusmsg = getStatusMsg(elecResponse.getPayload().getStatus_code());

											}
										}

										elecResponse.getPayload().setStatus_msg(statusmsg);

										if (elecResponse.getPayload().getStatus_code() == 101) {
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(elecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + elecResponse.getPayload().getStatus_code());
										} else if (elecResponse.getPayload().getStatus_code() == 102) {
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(elecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + elecResponse.getPayload().getStatus_code());
										} else {

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(elecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + elecResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {


										// ErrorResponse errorResponse =
										// om.readValue(info.getResponse(),ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);
								}

							} else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}

						}

						else {
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(info.getResponse(),ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}
			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		elecResponse.setMsgInfo(msgInfo);
		elecResponse.setHeader(elecRequest.getHeader());
		return elecResponse;
	}


	@Override
	public TelephoneResponse teleRequestService(final TelephoneRequest teleRequest,KarzaReqRes rq) {
		TelephoneResponse teleResponse = new TelephoneResponse();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave = resProp.getString("com.karza.tele.url");
		String key = resProp.getString("com.karza.key");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.telev2.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (teleRequest != null) {
				if (teleRequest.getPayload() != null && teleRequest.getPayload().getTel_no() != null
						&& !teleRequest.getPayload().getTel_no().equals("")
						&& !teleRequest.getPayload().getName().equals("") && teleRequest.getPayload().getName() != null
						&& !teleRequest.getPayload().getConsent().equals("")
						&& teleRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(teleRequest.getHeader());

					if (result == true) {
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){
							//for Bank Account
							String	res="{\"request_id\": \"4e3c2099-2100-11e8-befc-294fb890ad2c\", \"result\": {\"tele\": {\"category\": \"OTHERS\", \"TelephoneNo\": \"0251-2880343\", \"name\": \"OMKAR MILIND SHIRHATTI\", \"Installation_Type\": \"LANDLINE\", \"address\": \"PUSHPA BLDG 125, AJANTHA SOC,NANDIVALI RD,X, 999999 Maharashtra\"}, \"name\": {\"score\": 0.13825757575757575, \"match\": false}}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(teleRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(teleRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.tele.success.price");
							}else {
								price=resProp.getString("com.karza.tele.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
						}

						if (info.getResponseCode() == 200) {
							msgInfo.setCode(""+info.getResponseCode());
							if (!info.getResponse().toString().contains("Invalid Input")) {
								if (!info.getResponse().toString().contains("Max retries exceeded")) {

									try {
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										teleResponse.setPayload(om.readValue(
												info.getResponse().replaceAll("TelephoneNo", "telephoneNo")
												.replaceAll("status-code", "status_code")
												.replaceAll("Installation_Type", "installation_Type"),
												TelResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (teleResponse.getPayload() != null) {

											if (teleResponse.getPayload().getStatus_code() != 0) {
												statusmsg = getStatusMsg(teleResponse.getPayload().getStatus_code());

											}
										}

										teleResponse.getPayload().setStatus_msg(statusmsg);

										if (teleResponse.getPayload().getStatus_code() == 101) {
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(teleResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + teleResponse.getPayload().getStatus_code());
										} else if (teleResponse.getPayload().getStatus_code() == 102) {
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(teleResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + teleResponse.getPayload().getStatus_code());
										} else {

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(teleResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + teleResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										//msgInfo.setCode(StringConstants.C_600.toString());
										// ErrorResponse errorResponse =
										// om.readValue(info.getResponse(),ErrorResponse.class);
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);
								}

							} else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+400);
							}

						}

						else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;

							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							// ErrorResponse errorResponse =
							// om.readValue(info.getResponse(),ErrorResponse.class);
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}
			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		teleResponse.setMsgInfo(msgInfo);
		teleResponse.setHeader(teleRequest.getHeader());
		return teleResponse;
	}
	@Override
	public ESICIdResponse esicIdRequestService(final ESICIdRequest esicIdRequest, KarzaReqRes rq) {
		ESICIdResponse esicIdResponse = new ESICIdResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.esic.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.esici.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (esicIdRequest != null) {

				if (esicIdRequest.getPayload() != null 
						&& esicIdRequest.getPayload().getEsic_id() != null && !esicIdRequest.getPayload().getEsic_id().equals("")
						&& !esicIdRequest.getPayload().getConsent().equals("") && esicIdRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(esicIdRequest.getHeader());
					if (result == true) {
						String responseEsicIdCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"5466b4bb-1ae5-11e8-800d-0fbc999d9eb5\", \"result\": {\"UHID\": \"PB01.0001470420\", \"DOB\": \"09/05/1981\", \"AdhaarStatus\": \"Verified\", \"Relation_with_IP\": \"Spouse\", \"CurrentDateOfAppointment\": \"01/02/2011\", \"Nominee_Address\": \"COL NO 7  \", \"PresentAddress\": \"B34/5905 LUXMI NAGAR ST NO 8 JASSIAN ROAD HAIBOWAL, NEAR SATYA NARAYAN MANDIR Ludhiana Punjab\", \"DisabilityType\": \"-- N.A --\", \"AdhaarNO\": \"34********87\", \"PhoneNO\": \"-\", \"MaritialStatus\": \"Married\", \"Name\": \"RAJINDER KUMAR\", \"Gender\": \"M\", \"Contribution_Details\": [{\"Employee_Conrtibution\": \"101.00\", \"Wage_Period\": \"October-2017\", \"Total_Monthly_Wages\": \"5752.00\", \"Number_of_Days_wages_paid\": \"21\"}, {\"Employee_Conrtibution\": \"101.00\", \"Wage_Period\": \"November-2017\", \"Total_Monthly_Wages\": \"5747.00\", \"Number_of_Days_wages_paid\": \"21\"}, {\"Employee_Conrtibution\": \"0.00\", \"Wage_Period\": \"December-2017\", \"Total_Monthly_Wages\": \"0.00\", \"Number_of_Days_wages_paid\": \"0\"}, {\"Employee_Conrtibution\": \"103.00\", \"Wage_Period\": \"January-2018\", \"Total_Monthly_Wages\": \"5833.00\", \"Number_of_Days_wages_paid\": \"22\"}, {\"Employee_Conrtibution\": \"305.00\", \"Wage_Period\": \"Totals\", \"Total_Monthly_Wages\": \"17332.00\", \"Number_of_Days_wages_paid\": \"64\"}], \"Nominee_AdhaarNO\": \"65********40\", \"Father_or_Husband\": \"GURBACHAN SINGH\", \"RegistrationDate\": \"21/02/2011\", \"PermanentAddress\": \"B34/5905 LUXMI NAGAR ST NO 8 JASSIAN ROAD HAIBOWAL,NEAR SATYA NARAYAN MANDIR Ludhiana Punjab\", \"Nominee_Name\": \"POOJA\", \"InsuranceNO\": \"2612316891\", \"FirstDateOfAppointment\": \"\", \"DispensaryName\": \"D 1 ESI Hospital campus, Ludhiana, PB (ESIS Disp.)\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(esicIdRequest.getPayload()), urlSave,key);
						}


						//							 String response="{\"header\" : {\"msgVersion\" : \"1.0\",\"appId\" : \"KARZA\",\"correlationId\" : \"12345\",\"token\" : \"f4b10aac-e9c2-4bf5-8c8e-31bee316dd8d\"},\"msgInfo\" : {\"code\" : \"101\",\"status\" : \"Success\",\"message\" : \"Valid Authentication\"},\"payload\" : {\"result\" : {\"uhId\" : \"PB01.0001470420\",\"dob\" : \"09/05/1981\",\"adhaarStatus\" : \"Verified\",\"relationWithIP\" : \"Spouse\",\"currentDateOfAppointment\" : \"01/02/2011\",\"nomineeAddress\" : \"COL NO 7  \",\"presentAddress\" : \"B34/5905 LUXMI NAGAR ST NO 8 JASSIAN ROAD HAIBOWAL, NEAR SATYA NARAYAN MANDIR Ludhiana Punjab\",\"disabilityType\" : \"-- N.A --\",\"adhaarNo\" : \"34********87\",\"phoneNo\" : \"-\",\"maritialStatus\" : \"Married\",\"name\" : \"RAJINDER KUMAR\",\"gender\" : \"M\",\"contributionDetails\" : [ {\"employeeConrtibution\" : \"101.00\",\"wagePeriod\" : \"October-2017\",\"totalMonthlyWages\" : \"5752.00\",\"numberOfDaysWagesPaid\" : \"21\"}, {\"employeeConrtibution\" : \"101.00\",\"wagePeriod\" : \"November-2017\",\"totalMonthlyWages\" : \"5747.00\",\"numberOfDaysWagesPaid\" : \"21\"}, {\"employeeConrtibution\" : \"0.00\",\"wagePeriod\" : \"December-2017\",\"totalMonthlyWages\" : \"0.00\",\"numberOfDaysWagesPaid\" : \"0\"}, {\"employeeConrtibution\" : \"103.00\",\"wagePeriod\" : \"January-2018\",\"totalMonthlyWages\" : \"5833.00\",\"numberOfDaysWagesPaid\" : \"22\"}, {\"employeeConrtibution\" : \"305.00\",\"wagePeriod\" : \"Totals\",\"totalMonthlyWages\" : \"17332.00\",\"numberOfDaysWagesPaid\" : \"64\"} ],\"nomineeAdhaarNo\" : \"65********40\",\"fatherOrHusband\" : \"GURBACHAN SINGH\",\"registrationDate\" : \"21/02/2011\",\"permanentAddress\" : \"B34/5905 LUXMI NAGAR ST NO 8 JASSIAN ROAD HAIBOWAL,NEAR SATYA NARAYAN MANDIR Ludhiana Punjab\",\"nomineeName\" : \"POOJA\",\"insuranceNo\" : \"2612316891\",\"firstDateOfAppointment\" : \"\",\"dispensaryName\" : \"D 1 ESI Hospital campus, Ludhiana, PB (ESIS Disp.)\"},\"byteArray\" : null,\"pdfPath\" : null,\"status_code\" : 101,\"request_id\" : \"095c30b5-1968-11e8-bc85-c51053d04a93\",\"status_msg\" : \"Valid Authentication\"}}";
						//							 esicIdResponse=om.readValue(response,ESICIdResponse.class );
						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(esicIdRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.esic_id_auth.success.price");
							}else {
								price=resProp.getString("com.karza.esic_id_auth.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseEsicIdCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("UHID", "uhId")
												.replaceAll("DOB", "dob")
												.replaceAll("AdhaarStatus", "adhaarStatus")
												.replaceAll("Relation_with_IP", "relationWithIP")
												.replaceAll("CurrentDateOfAppointment", "currentDateOfAppointment")
												.replaceAll("Nominee_Address", "nomineeAddress")
												.replaceAll("PresentAddress", "presentAddress")
												.replaceAll("DisabilityType", "disabilityType")
												.replaceAll("AdhaarNO", "adhaarNo")
												.replaceAll("PhoneNO", "phoneNo")
												.replaceAll("MaritialStatus", "maritialStatus")
												.replaceAll("Name", "name")
												.replaceAll("Gender", "gender")
												.replaceAll("Contribution_Details", "contributionDetails")
												.replaceAll("Employee_Conrtibution", "employeeConrtibution")
												.replaceAll("Wage_Period", "wagePeriod")
												.replaceAll("Total_Monthly_Wages", "totalMonthlyWages")
												.replaceAll("Number_of_Days_wages_paid", "numberOfDaysWagesPaid")
												.replaceAll("Nominee_AdhaarNO", "nomineeAdhaarNo")
												.replaceAll("Father_or_Husband", "fatherOrHusband")
												.replaceAll("RegistrationDate", "registrationDate")
												.replaceAll("PermanentAddress", "permanentAddress")
												.replaceAll("Nominee_Name", "nomineeName")
												.replaceAll("InsuranceNO", "insuranceNo")
												.replaceAll("FirstDateOfAppointment", "firstDateOfAppointment")
												.replaceAll("DispensaryName", "dispensaryName")
												.replaceAll("Dispensaryname", "dispensaryName")
												.replaceAll("Nominee_AdhaarNO", "nomineeAdhaarNo")
												.replaceAll("Nominee_adhaarNo", "nomineeAdhaarNo")
												.replaceAll("Nominee_name", "nomineeName")
												.replaceAll("Nominee_Name", "nomineeName");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										esicIdResponse.setPayload(om.readValue(responseEsicIdCall, ESICIdResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (esicIdResponse.getPayload() != null) 
										{

											if (esicIdResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(esicIdResponse.getPayload().getStatus_code());

											}
										}

										esicIdResponse.getPayload().setStatus_msg(statusmsg);

										if (esicIdResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(esicIdResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + esicIdResponse.getPayload().getStatus_code());
										}
										else if (esicIdResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(esicIdResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + esicIdResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(esicIdResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + esicIdResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		esicIdResponse.setMsgInfo(msgInfo);
		esicIdResponse.setHeader(esicIdRequest.getHeader());
		return esicIdResponse;
	}
	@Override
	public PngResponse pngRequestService(final PngRequest pngRequest,KarzaReqRes rq) {
		PngResponse pngResponse = new PngResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.png.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Karza.Png.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try 
		{
			if (pngRequest != null) 
			{

				if (pngRequest.getPayload() != null 
						&& pngRequest.getPayload().getConsumer_id() != null && !pngRequest.getPayload().getConsumer_id().equals("")
						//&& pngRequest.getPayload().getBp_no() != null && !pngRequest.getPayload().getBp_no().equals("")
						&& pngRequest.getPayload().getService_provider() != null && !pngRequest.getPayload().getService_provider().equals("")
						&& !pngRequest.getPayload().getConsent().equals("") && pngRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(pngRequest.getHeader());
					if (result == true) {
						String responsePngCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"17ce687d-193e-11e8-9912-5b364069cca4\", \"result\": {\"Bill_No\": \"R201711160375\", \"Due_Date\": \"07/03/2018\", \"Bill_Amount\": \"1251.0000\", \"mobile\": \"9925111620\", \"Customer_Address\": \"\", \"Bill_Date\": \"20180220\", \"Email\": \"dishantm.gajjar@adani.com\", \"Customer_Name\": \"Dishant Gajjar\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(pngRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(pngRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.png.success.price");
							}else {
								price=resProp.getString("com.karza.png.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responsePngCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("Bill_No", "billNo")
												.replaceAll("Due_Date", "dueDate")
												.replaceAll("Bill_Amount", "billAmount")
												.replaceAll("mobile", "mobile")
												.replaceAll("Customer_Address", "customerAddress")
												.replaceAll("Bill_Date", "billDate")
												.replaceAll("ValidUpDt", "validUpDt")
												.replaceAll("Email", "email")
												.replaceAll("Customer_Name", "customerName");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										pngResponse.setPayload(om.readValue(responsePngCall, PngResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (pngResponse.getPayload() != null) 
										{

											if (pngResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(pngResponse.getPayload().getStatus_code());

											}
										}

										pngResponse.getPayload().setStatus_msg(statusmsg);

										if (pngResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(pngResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + pngResponse.getPayload().getStatus_code());
										}
										else if (pngResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(pngResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + pngResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(pngResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + pngResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		pngResponse.setMsgInfo(msgInfo);
		pngResponse.setHeader(pngRequest.getHeader());
		return pngResponse;
	}
	@Override
	public Form16QuatResponse form16QuatRequestService(final Form16QuatRequest form16QuatRequest,KarzaReqRes rq) {
		Form16QuatResponse form16QuatResponse = new Form16QuatResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.form16Quaterly.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.form16quat.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (form16QuatRequest != null) {

				if (form16QuatRequest.getPayload() != null 
						&& form16QuatRequest.getPayload().getTan() != null && !form16QuatRequest.getPayload().getTan().equals("")
						&& form16QuatRequest.getPayload().getPan() != null && !form16QuatRequest.getPayload().getPan().equals("")
						&& form16QuatRequest.getPayload().getFiscal_year() != null && !form16QuatRequest.getPayload().getFiscal_year().equals("")
						&& !form16QuatRequest.getPayload().getConsent().equals("") && form16QuatRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(form16QuatRequest.getHeader());
					if (result == true) {
						String responseForm16QuatCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"dde18657-1ace-11e8-8bf5-8fd57ab09b0b\", \"result\": {\"quarterly_records_count_for_next_fiscal\": {\"Q-1\": \"2\", \"Q-3\": \"3\", \"Q-2\": \"3\", \"Q-4\": \"4\"}}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(form16QuatRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(form16QuatRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.form_16_quarterly.success.price");
							}else {
								price=resProp.getString("com.karza.form_16_quarterly.failure.price");
							}
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseForm16QuatCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("quarterly_records_count_for_next_fiscal", "quarterly_records_count_for_next_fiscal")
												.replaceAll("Q-1", "q1")
												.replaceAll("Q-2", "q2")
												.replaceAll("Q-3", "q3")
												.replaceAll("Q-4", "q4");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										form16QuatResponse.setPayload(om.readValue(responseForm16QuatCall, Form16QuatResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (form16QuatResponse.getPayload() != null) 
										{

											if (form16QuatResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(form16QuatResponse.getPayload().getStatus_code());

											}
										}

										form16QuatResponse.getPayload().setStatus_msg(statusmsg);

										if (form16QuatResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(form16QuatResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + form16QuatResponse.getPayload().getStatus_code());
										}
										else if (form16QuatResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(form16QuatResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + form16QuatResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(form16QuatResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + form16QuatResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		form16QuatResponse.setMsgInfo(msgInfo);
		form16QuatResponse.setHeader(form16QuatRequest.getHeader());
		return form16QuatResponse;
	}

	@Override
	public Form16Response form16RequestService(final Form16Request form16Request, KarzaReqRes rq) {
		Form16Response form16Response = new Form16Response();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.form16.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Karza.Form16Auth.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (form16Request != null) {

				if (form16Request.getPayload() != null 
						&& form16Request.getPayload().getTan() != null && !form16Request.getPayload().getTan().equals("")
						&& form16Request.getPayload().getPan() != null && !form16Request.getPayload().getPan().equals("")
						&& form16Request.getPayload().getCert_no() != null && !form16Request.getPayload().getCert_no().equals("")
						&& form16Request.getPayload().getAmount() != null && !form16Request.getPayload().getAmount().equals("")
						&& form16Request.getPayload().getFiscal_year() != null && !form16Request.getPayload().getFiscal_year().equals("")
						&& !form16Request.getPayload().getConsent().equals("") && form16Request.getPayload().getConsent() != null) {
					boolean result = checkValidation(form16Request.getHeader());
					if (result == true) {
						String responseForm16Call = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"efe30ad0-1942-11e8-acc4-ed819f540218\", \"result\": {\"status\": \"Valid\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(form16Request.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(form16Request.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.form 16 authentication.success.price");
							}else {
								price=resProp.getString("com.karza.form 16 authentication.failure.price");
							}
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseForm16Call = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										form16Response.setPayload(om.readValue(responseForm16Call, Form16ResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (form16Response.getPayload() != null) 
										{

											if (form16Response.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(form16Response.getPayload().getStatus_code());

											}
										}

										form16Response.getPayload().setStatus_msg(statusmsg);

										if (form16Response.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(form16Response.getPayload().getStatus_msg());
											msgInfo.setCode("" + form16Response.getPayload().getStatus_code());
										}
										else if (form16Response.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(form16Response.getPayload().getStatus_msg());
											msgInfo.setCode("" + form16Response.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(form16Response.getPayload().getStatus_msg());
											msgInfo.setCode("" + form16Response.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		form16Response.setMsgInfo(msgInfo);
		form16Response.setHeader(form16Request.getHeader());
		return form16Response;
	}

	@Override
	public IECResponse iecRequestService(IECRequest iecRequest, KarzaReqRes rq){
		IECResponse iecResponse=new IECResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.iec.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Karza.IEC.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (iecRequest != null) {

				if (iecRequest.getPayload() != null 
						&& iecRequest.getPayload().getIec() != null && !iecRequest.getPayload().getIec().equals("")
						&& !iecRequest.getPayload().getConsent().equals("") && iecRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(iecRequest.getHeader());
					if (result == true) {
						String responseIECCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){
							//for Bank Account
							String	res="{\"request_id\": \"087e9cdb-186c-11e8-8738-cf521a38bb1e\", \"result\": {\"Name\": \"PRIME EXPORTS\", \"del_status\": \"false\", \"IE Code\": \"0393018351\", \"Address\": \"C 208,SYNTHOFINE INDL.ESTATE OFFAAREY ROAD, GOREGAON EAST MUMBAIMAHARASHTRA\", \"No_of_branches\": \"2\", \"IEC Status\": \"AMENDMENT\", \"PAN\": \"AAAFP6258MFT001\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(iecRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(iecRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.iec.success.price");
							}else {
								price=resProp.getString("com.karza.iec.failure.price");
							}
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseIECCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("Name", "name")
												.replaceAll("IE Code", "ieCode")
												.replaceAll("Address", "address")
												.replaceAll("No_of_branches", "noOfBranches")
												.replaceAll("IEC Status", "iecStatus")
												.replaceAll("PAN", "pan");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										iecResponse.setPayload(om.readValue(responseIECCall, IECResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (iecResponse.getPayload() != null) 
										{

											if (iecResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(iecResponse.getPayload().getStatus_code());

											}
										}

										iecResponse.getPayload().setStatus_msg(statusmsg);

										if (iecResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(iecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + iecResponse.getPayload().getStatus_code());
										}
										else if (iecResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(iecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + iecResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(iecResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + iecResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		iecResponse.setMsgInfo(msgInfo);
		iecResponse.setHeader(iecRequest.getHeader());
		return iecResponse;
	}



	@Override
	public DlResponse2 dlRequestServiceQC(final DlRequest2 dlRequest, KarzaReqRes rq) {
		DlResponse2 dlResponse = new DlResponse2();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.dl.v3.url");
		//String urlSave = "https://testapi.karza.in/v2/dl";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Karza.dlv3.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (dlRequest != null) {

				if (dlRequest.getPayload() != null
						&& dlRequest.getPayload().getDl_no() != null && !dlRequest.getPayload().getDl_no().equals("")
						&& dlRequest.getPayload().getDob() != null && !dlRequest.getPayload().getDob().equals("")
						&& !dlRequest.getPayload().getConsent().equals("")	&& dlRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(dlRequest.getHeader());
					if (result == true) {
						String responseDlCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"087fad28-2216-11e8-88f5-73cd05afabd0\", \"result\": {\"name\": \"GAURAV  SAMDARIA\", \"dob\": \"12-06-1987\", \"issue_date\": \"15-07-2005\", \"blood_group\": \"\", \"father/husband\": \"VIJAI RAJ  SAMDARIA\", \"address\": \"1302 13 TH FLOOR, ORCHID 18, B.G.KHER ROAD, WORLI NAKA MUMBAI. 400018\", \"validity\": {\"non-transport\": \"15-07-2005 to 14-07-2025\", \"transport\": \"\"}, \"img\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCACgAHgDASIAAhEBAxEB/8QAHAAAAAcBAQAAAAAAAAAAAAAAAQIDBAUGBwAI/8QANxAAAQMDAwIEBQIFAwUAAAAAAQACAwQFERIhMQZBEyJRYQcUMnGBkaEVUrHB0SMzQmJyguHw/8QAGgEAAgMBAQAAAAAAAAAAAAAAAwQBAgUABv/EACURAAMAAgICAgICAwAAAAAAAAABAgMREiEEMRNBIlEUMlJhof/aAAwDAQACEQMRAD8ApDQhARQ5HbumULggI7fRA1CpRAbCMDsit4KMFO0ckwQULd0i94aTlAKlreVHJHaY5Rmps2pjP/LdG8doOxH6ruSO0xyhBwEQE4B5RvsrFQwKUaUk3hHacqCGhVrsALkA4XKxBCAJRg4SbUozjKoEYcBCBnugBRXnTz+FDIQq0YGTwiy5DCQfKF0cnlbluQDkoHML8u3x6Z4S9230g0oYSOcSckhIkOycnZPjD4jyIwSewKbzRODi0oey+hAbjlFAJ3ydko+MgDCPHHqa7HI7KU2iGhxR1Za3S4Zx7p3T1QedLm6T91FnyHjK7xy14PZFVtFHJPA7I8abUswliBGx9E5jR5rYFpoUBXIAuUlSHwjNO2EUIQoCCjSgnDWtDyd/Rcz0TOpJdKW9kPI+iZQpBO4yaTgg9lY6Smb8i+aXyhvYjndJdA2R13rd2+Rp+ojlaFXdLfOVENtoA0zuOuXJ2YwevosvJ5KVOR6MG12U2ktmuN5jYQQ3UcjfCUqbC1tLDK4byAnj0K1O29HSClmma7IDcMZ6n7qr1EZfBHSztLapj3N375KAs++wvwpLSKC21tfVOix9Ptyk7jZvl6lwYQAQMjHGytN5pJqOrrXRxPc6BscpAHY7Jhe2SyvikiGvU0FxA9URZ1v2Q8L16KhU0zQ3DXg49kxfEfTZTNxoZKerkaQcEAg49kwZNqiMb2jbgpyK5LYvUufYWieYntzwVLRkOGVCh48QDGwUtSnMDT65/qmcT7AZF0OW8LkDTsuRwJE53XZykte/CM12Twqlxdh3TGqcROY28kt/OTjCesOBn9glv4ZV09wtNbPb6k01RONGWHEmOwS/kVxh96C4odUjVrZPbug7NSxPa2ouErNehu3O/m5xymsXxPmoZJfDttLGJTmQtcMnbjKdXm4xNo3VtdaKd1TIcBgOt2AMNGfsBthV+4XGst76f+IWWjp46qMyQ5hznfGOFmRxme1tjNqm97L7ZPi1RVc0FPJSGHcN8jg4fsrg+itFfM2r0jVyDjCyN1trGV9NFNZ4WvkDXt8Ful2DvxuOPZaTaqcfJzviqP8ATjOluW9++/scoOSlT9dB8UtL32LXKp6et9wqZayRpdLA1j2ac5A3Vaqur+knufDDA0AADOjCieqBb6xzzVmqkBBBfF5QBxsd8quVdp6PfTtjxdKOpZgl8kgcC09zwunHjfXH/pF1c9qiduVBar6dVtqItfJZndZTfbe+23R9O84JPJHZXR3SZja2r6YuYkmY4eV5wSEHXlO6s6fhucsAbWUzvCm34OcI2JqK0iMi5TtmezRBjc6sn7KVpdoGDthRzW+I4N5wMlScYwwD2Wph9mfk9CgOy5ADsuTIEhG8pRo4STXbpRruAqPYRC7R5dgdRIH6rfOkaed11o7bUSCeipIxURa25LXaQDj7nKwu1RGorqaAY1STNaM/cL0bSRMo+oGvaN3QMiyONuVl+c92pHfG6jZJXbpqCtJe2Jg3yNIwmD+mGzCNtTLO4xcA4IVuppA/G6Vla3OUio6DK9Mq/wDAaeD/AFC6XxXbBwd5gD2B7KwRxRUNvZFGwBgbwe57kosMeuZrjjQOB6pzXtHhYVphLbRzp00mUF9j+bbJTGTLdZcwEY2Jzj91E3zomKvukddWQNnexrWGMO0tc1vAP6K6RR5qHMeMYOxynppB/MUBXSfQdqfszG39MVMN5kqPCYyJ7wRBHnSwDjCrvxEsFxpbPc62jcIqad+Zadxz3+oei2iWmYzJzv7gKlfEWWOHpi54xtFsPfKvju5pPZW5mlow22W2sktxqY6aUwjGuXT5QE4HtwtO6ioYqDpFlSS9rvkIz4ecNDiBkY/JWXs2aBvsPVbHhZHkl1/sz/JxrHSlBgVyAb5XJ4UIMFKMKSaUozhQXRIWmq+SuVHVFupsMzXkeoC3un6ltt5rIX2ucSOa3W9mnBb7Lz1G79VcfhfUiDqIs8rTMzGfskvKxKly+w+G2vxPQNBWAtH+VKxyCRuXHDVTKKbc4OAPdJV11uLS5tso/mdP1anaWj9lly/2NpN+hTqy1XKWvjrLTc5I2RbmA/Sf3Ua69dS1MfhRvhiLdi47p6KvqCWm8UfISEc08eSW/num0t1u/wBP8Hccbuw3C662vxYxjw0u2iW6XfcXtcbs+N7m/wDJvdWJsw0qkUXUtK+YxPY+lqhzG8bfqpuKu8SIvG4+6X1rtsmu+h5cKkNa7/KptxDLlXNgnYXwfW9v8wCf3Ot1agCqZP1ZT2e7SCSkNSTHp+vTjv6FXnFeROZBvJOPumPPi7XRvsVsggBaJHgOB2yBn/78LLu5HopbqvqCfqG4MnmjEMUTNEUQOdI9c91EA4HC3fGx/FjUv2ZmbJ8lugWnsuRQd8rkxsCQjUqzgIgacpRrduVzLJB2KRs1UaG501Xw2OQZ9wo9oRyD4Z37bKl6cvYSE0zdaCvEgaWnIeM8qche9kJEZzlZzQzGjo6JxJ0vja4H8K62K7ROi87hssLJGn0PS/2NLlc623TOMFMHg+5CSpr5XXFzopo3wj1a8kf0Vxp6+3zx4cxjn98pGQ0DA4t8NrvQKlKtDEZeP2RNLQw+GS9ofJ/M4ZKB9U2nhLN05qa2Cmic9zgARsqbcrs2SSRzdmnjdUnHT9k1kTF7zdGQQSPJHHqsrq6h09TLM76nnnPZT/U9S91AHO2EjtI35CqpO2y1vExqZ2ZuetvQsHD0Rg7bhN9WEow5CdkWYrqXIAMLlYgYtj25RxHtsnDIydmgudngDKdxW5xiMk72wsHOecfZVbGUkRzWbHdSFHRSfJVNZKxwiiYSAR9Sn7baKaCFryC+R2+pw7dtvthOLw0utNTG04boxgBUe2mcqW1onqSjNb0rbi4YeYcg49yq1M+vtsz2hx0j2Wj9PwN/glBFpyGwt/om9zs/jl2GhzfRY3yab2PqNlEjvdTj/ccD90pHfKhgyZC4+6kK3ph/iOdCRH7ELqTplxyZ36/+lrVV5UGWHZG1F3qq1+nLnDs0J1R0EzzrqfIzsOcqyW+wuiYDBTiNvq45KkIrdFTgvld4kg9RsPwhvNvpHfCkZX8RGyMfbY2tLY8k4UI6N3hB4BxhXTro+PVQMLc4Ooj24UNHG3RjGGHsQtfxm+Bl50uRXWgkpaMc7p/U24sidJCQQDuPRMdBBITaFn0GDvZcg0lcpK7RsMvwuD4TBSXoMkcQPLT4DRz/ADIerbfZrBYaa3R00NRK1wzUP+tzvU/4Ue/ru81FLVxuZDFlhGqKN2po9QqbQ6qqnc+aeSVof5dbifui6xx2uxXHHkZGub0iXdLrcf7Iso8Snmj7uZj90EDPIMozhg5H2QX2maM6TNDsPmtNI8DbQGn8bf2UoWBzdh+VHfD+P5q2mBzgMElufuVY/kSCRledv+zNWWuiFc0jI/qEMbM76Wj3AUw2gHc7/ZA+j05A/ogOQyv6ImUua3fOFE18+hjyfpAySp6ejLnHJOD6Kldb1HgAUMP1v3ec9kbBjd1opkyKJ7ZSrhUOrKyScgjJwN84HCSAccNIy3sl/BPojNiJOGjdbszpaRl0+Q5tljr7yyentdOZ5GNy4ag39ykKrpDqCkeGT2eqcDsPDaH/ALgqz9L9UN6bpaqF1vFRJUEO1h2k7fqrpauvbTUUjpKySSgmB8zDlw57EBMRLf2ZufJcPqejH6rpK9Qw+I+1VjGe8a5ba3rXp9oLheWkZ9HH+y5X+N/sW/lv/En+nekbVYoJ2UsRlMv1Onw8kemccLP/AIvWuktZtD7bRxU8L3yeKYo8DOBhKXL4p1dPcz8vRwvoQcBjiQ93vq/9KH6l+IN4ubTHS+FRU5Hma1oe4/8Akf7BBjHxe2adW6X4orgLiSdLjn2KSka7clrsfZQ5iqu9wqfw5DJE57Ga5pnPYch5durN67QRG1/Cm1TS281lQ6RkOS2JhGMjO5/XKt87GCZwieJADgkHOD6FecrrcrtPSRPbc63UwgANkLRj7BSfw66rrLNLJDVPc+GcnUHOOxzzkrOy+PKnftjOPJTrv0bs0DJBxn7oTC6V2I26s+nZVanvY8BkuvU9zsYHYZVR+IfVVwfcaaltVVJSmDcywu+vIzv+qUxYfkevQbJbhGkXtz7VQS1TqWeoeBhrImF2/v6LG62SSuqpqidwMsh3aTuPZOaX4i9UUsYiFdHUOG2ZYQdk5qOu33CMi82KkqqnH+/C4xOHpnY52WjhwTi/r2J1dU+yCdA8uxsAnFst8lVWwUdOMzTPDRj3KY1VaHPbLFTuibnzNMmSf2V3+GV56foauprLhXRwVOAyKOQHLQeTnCJfJLpHLS9ktN8JdUpkhvLmAgAtNPnt/wByi778NblQeG+3SfxEEYd5fDLfxk5Wr0t5ttVEZIK+lkZzkSBOKWpgq4/FpZY5mZwTGQ5Rxqe9leUvowOXpK9RvPiWerOOSyPOVy9CcbAlcp5X+yvxz+j/2Q==\", \"cov_details\": [{\"issue_date\": \"RTO,MUMBAI CENTRAL\", \"cov\": \"LMV\"}]}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(dlRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(dlRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.dl.success.price");
							}else {
								price=resProp.getString("com.karza.dl.failure.price");
							}
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while setting karza internal req res:" + e);
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
										responseDlCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("father/husband", "father_husband").replaceAll("LMV", "lmv")
												.replaceAll("non-transport", "non_transport");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										dlResponse.setPayload(om.readValue(responseDlCall, DlResponsePayload2.class));

										String statusmsg = "No status code received from third party";
										if (dlResponse.getPayload() != null) 
										{

											if (dlResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(dlResponse.getPayload().getStatus_code());

											}
										}

										dlResponse.getPayload().setStatus_msg(statusmsg);

										if (dlResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(dlResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + dlResponse.getPayload().getStatus_code());
										}
										else if (dlResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(dlResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + dlResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(dlResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + dlResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		dlResponse.setMsgInfo(msgInfo);
		dlResponse.setHeader(dlRequest.getHeader());
		return dlResponse;
	}
	@Override
	public TanResponse tanRequestService(final TanRequest tanRequest,  KarzaReqRes rq) {
		TanResponse tanResponse = new TanResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.tan.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Karza.tan.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (tanRequest != null) {

				if (tanRequest.getPayload() != null 
						&& tanRequest.getPayload().getTan() != null && !tanRequest.getPayload().getTan().equals("")
						&& !tanRequest.getPayload().getConsent().equals("") && tanRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(tanRequest.getHeader());
					if (result == true) {
						String responseTanCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"8408d7f8-1920-11e8-b6e1-55bf7db13bba\", \"result\": {\"name\": \"DECCAN CHRONICLE SECUNDERABAD PRIVATE LIMITED\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(tanRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(tanRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.tan.success.price");
							}else {
								price=resProp.getString("com.karza.tan.failure.price");
							}
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseTanCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										tanResponse.setPayload(om.readValue(responseTanCall, TanResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (tanResponse.getPayload() != null) 
										{

											if (tanResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(tanResponse.getPayload().getStatus_code());

											}
										}

										tanResponse.getPayload().setStatus_msg(statusmsg);

										if (tanResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(tanResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + tanResponse.getPayload().getStatus_code());
										}
										else if (tanResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(tanResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + tanResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(tanResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + tanResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		tanResponse.setMsgInfo(msgInfo);
		tanResponse.setHeader(tanRequest.getHeader());
		return tanResponse;
	}


	@Override
	public NREGAResponse nregaRequestService(final NREGARequest nregaRequest, KarzaReqRes rq) {
		NREGAResponse nregaResponse = new NREGAResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.nrega.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.nrega.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (nregaRequest != null) {

				if (nregaRequest.getPayload() != null 
						&& nregaRequest.getPayload().getJobcardid() != null && !nregaRequest.getPayload().getJobcardid().equals("")
						&& !nregaRequest.getPayload().getConsent().equals("") && nregaRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(nregaRequest.getHeader());
					if (result == true) {
						String responseNregaCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{ \"status-code\": 101, \"request_id\": \"73cdbde2-80f7-11e7-8f0c-e7e769f70bd1\", \"result\": { \"familyId1\": \"4\", \"address\": \"4\", \"nameOfFatherOrHusband\": \"\", \"voterId\": \"\", \"village\": \"KONDARAJANAHALLI\", \"bplFamilyId\": \"\", \"incomeDetail\": [ { \"Income for 2017-2018\": \"5664\" }, { \"Income for 2010-2011\": \"7500\" }, { \"Income for 2013-2014\": \"4872\" }, { \"Income for 2016-2017\": \"6272\" }, { \"Income for 2008-2009\": \"3772\" }, { \"Income for 2009-2010\": \"8804\" }, { \"Income for 2014-2015\": \"20055\" } ], \"familyId\": \"4\", \"category\": \"SC\", \"applicantDetail\": [ { \"name\": \"ಕಳಾವತಮ್ಮ\", \"gender\": \"Female\", \"age\": \"20\", \"bankorpostoffice\": \"Canara Bank\", \"aadhaarNo\": \"\", \"accountNo\": \"\" }, { \"name\": \"ವೆಂಕಟರಾಯಪ್ಪ\", \"gender\": \"Male\", \"age\": \"45\", \"bankorpostoffice\": \"Canara Bank\", \"aadhaarNo\": \"\", \"accountNo\": \"\" }, { \"name\": \"ಶಂಕರಪ್ಪ\", \"gender\": \"Male\", \"age\": \"25\", \"bankorpostoffice\": \"Canara Bank\", \"aadhaarNo\": \"\", \"accountNo\": \"\" }, { \"name\": \"ಯಲ್ಲಪ್ಪ ವಿ\", \"gender\": \"Male\", \"age\": \"22\", \"bankorpostoffice\": \"Canara Bank\", \"aadhaarNo\": \"\", \"accountNo\": \"\" }, { \"name\": \"ನಾಗಮ್ಮ\", \"gender\": \"Female\", \"age\": \"38\", \"bankorpostoffice\": \"Canara Bank\", \"aadhaarNo\": \"\", \"accountNo\": \"\" } ], \"district\": \"CHIKKABALLAPURA(KARNATAKA)\", \"nameOfHead\": \"ಕಳಾವತಮ್ಮ\", \"photoImageUrl\": \"http://mnregaweb2.nic.in/\", \"bplFamily\": \"\", \"jobcardno\": \"KN-28-006-015-014/4\", \"dateOfRegistration\": \"6/21/2008\", \"panchayat\": \"ಗಂಜಿಗುಂಟೆ\", \"block\": \"ಸಿಡ್ಲಗಟ್ಟಾ\" } }";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(nregaRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.nrega.success.price");
							}else {
								price=resProp.getString("com.karza.nrega.failure.price");
							}
							rq.setIntReq(ow.writeValueAsString(nregaRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseNregaCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("Income for 2017-2018","income_for_2017_2018")
												.replaceAll("Income for 2010-2011","income_for_2010_2011")
												.replaceAll("Income for 2013-2014","income_for_2013_2014")
												.replaceAll("Income for 2016-2017","income_for_2016_2017")
												.replaceAll("Income for 2008-2009","income_for_2008_2009")
												.replaceAll("Income for 2009-2010","income_for_2009_2010")
												.replaceAll("Income for 2014-2015","income_for_2014_2015");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										nregaResponse.setPayload(om.readValue(responseNregaCall, NREGAResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (nregaResponse.getPayload() != null) 
										{

											if (nregaResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(nregaResponse.getPayload().getStatus_code());

											}
										}

										nregaResponse.getPayload().setStatus_msg(statusmsg);

										if (nregaResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(nregaResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + nregaResponse.getPayload().getStatus_code());
										}
										else if (nregaResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(nregaResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + nregaResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(nregaResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + nregaResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		nregaResponse.setMsgInfo(msgInfo);
		nregaResponse.setHeader(nregaRequest.getHeader());
		return nregaResponse;
	}


	@Override
	public ShopEstablishmentResponse shopEstablishmentRequestService(ShopEstablishmentRequest shopEstablishmentRequest,KarzaReqRes rq){
		ShopEstablishmentResponse shopEstablishmentResponse=new ShopEstablishmentResponse();
		MessageInfo msgInfo = new MessageInfo();

		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.shop.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.shopest.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try 
		{
			if (shopEstablishmentRequest != null)
			{

				if (shopEstablishmentRequest.getPayload() != null 
						&& shopEstablishmentRequest.getPayload().getReg_no() != null && !shopEstablishmentRequest.getPayload().getReg_no().equals("")
						&& shopEstablishmentRequest.getPayload().getArea_code() != null && !shopEstablishmentRequest.getPayload().getArea_code().equals("")
						&& !shopEstablishmentRequest.getPayload().getConsent().equals("") && shopEstablishmentRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(shopEstablishmentRequest.getHeader());
					if (result == true) {
						String shopEstablishmentResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y") && res!=null)
						{
							info.setResponse(res.getString("KARZA_Shop_Establishment_Report"));
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(shopEstablishmentRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;

							rq.setIntReq(ow.writeValueAsString(shopEstablishmentRequest));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.shop_and_stablishment.success.price");
							}else {
								price=resProp.getString("com.karza.shop_and_stablishment.failure.price");
							}
							rq.setPrice(price);
						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										shopEstablishmentResponseCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("father's_name_of_occupier", "fathers_name_of_occupier");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										shopEstablishmentResponse.setPayload(om.readValue(shopEstablishmentResponseCall, ShopEstablishmentResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (shopEstablishmentResponse.getPayload() != null) 
										{

											if (shopEstablishmentResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(shopEstablishmentResponse.getPayload().getStatus_code());

											}
										}

										shopEstablishmentResponse.getPayload().setStatus_msg(statusmsg);

										if (shopEstablishmentResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(shopEstablishmentResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + shopEstablishmentResponse.getPayload().getStatus_code());
										}
										else if (shopEstablishmentResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(shopEstablishmentResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + shopEstablishmentResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(shopEstablishmentResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + shopEstablishmentResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		shopEstablishmentResponse.setMsgInfo(msgInfo);
		shopEstablishmentResponse.setHeader(shopEstablishmentRequest.getHeader());
		return shopEstablishmentResponse;
	}


	@Override
	public PanResponse panRequestService(final PanRequest panRequest,KarzaReqRes rq) {
		PanResponse panResponse = new PanResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.pan.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.pan.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (panRequest != null) {

				if (panRequest.getPayload() != null 
						&& panRequest.getPayload().getPan() != null && !panRequest.getPayload().getPan().equals("")
						&& !panRequest.getPayload().getConsent().equals("") && panRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(panRequest.getHeader());
					if (result == true) {
						String responsePanCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){
							//for Bank Account
							String	res="{\"result\": {\"name\": \"OMKAR MILIND SHIRHATTI\"}, \"request_id\": \"b24be2d8-3998-11e8-99d6-09c4fdd5ec70\", \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(panRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(panRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.pan.success.price");
							}else {
								price=resProp.getString("com.karza.pan.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responsePanCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										panResponse.setPayload(om.readValue(responsePanCall, PanResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (panResponse.getPayload() != null) 
										{

											if (panResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(panResponse.getPayload().getStatus_code());

											}
										}

										panResponse.getPayload().setStatus_msg(statusmsg);

										if (panResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(panResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + panResponse.getPayload().getStatus_code());
										}
										else if (panResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(panResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + panResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(panResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + panResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							msgInfo.setCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		panResponse.setMsgInfo(msgInfo);
		panResponse.setHeader(panRequest.getHeader());
		return panResponse;
	}

	private boolean checkValidation(Header header) {
		if (header != null) {
			AuthValidator auth =dbKarza.validateAuth(header);
			if (auth.getAppid() == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public  KarzaInfo makeRestCall(HttpsURLConnection connection, String req,String key) throws Exception {
		KarzaInfo info = new KarzaInfo();
		String output = new String();
		StringBuffer result = new StringBuffer();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setConnectTimeout(Integer.parseInt(resProp.getString("com.qc.timeout.karza")));
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("x-karza-key", key);
		connection.connect();
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(req.getBytes());
		outputStream.flush();
		info.setResponseCode(connection.getResponseCode());
		logger.info("Response code from Server :: "+connection.getResponseCode());
		if (info.getResponseCode() == 200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((output = br.readLine()) != null) {
				result.append(output);
			}
		} else {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				br.close();
			} catch (Exception ex) {
				logger.info("We are not getting Error stream : " + ex);
			}
		}
		connection.disconnect();
		logger.info("Service Response from Third Party :: "+result.toString());
		info.setResponse(result.toString());
		return info;
	}

	public  KarzaInfo httpCall(String req, String url,String key) {
		KarzaInfo karzaInfo = new KarzaInfo();
		HttpsUrlConnectionMessageSender msgSender = new HttpsUrlConnectionMessageSender();
		try {
			HttpsURLConnection conn1 = msgSender.createConnection1(new URI(url));
			karzaInfo=makeRestCall(conn1,req,key);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return karzaInfo;
	}

	private String replaceAttributes(String parsedLpgResponse) {
		return parsedLpgResponse.replaceAll("DistributorName", "distributorName")
				.replaceAll("SubsidizedRefillConsumed", "subsidizedRefillConsumed")
				.replaceAll("ConsumerEmail", "consumerEmail").replaceAll("GivenUpSubsidy", "givenUpSubsidy")
				.replaceAll("BankName", "bankName").replaceAll("IFSCCode", "ifscCode").replaceAll("city/town", "city")
				.replaceAll("AadhaarNo", "aadhaarNo").replaceAll("ConsumerNo", "consumerNo")
				.replaceAll("ConsumerContact", "consumerContact")
				.replaceAll("ApproximateSubsidyAvailed", "approximateSubsidyAvailed")
				.replaceAll("ConsumerName", "consumerName").replaceAll("DistributorAddress", "distributorAddress")
				.replaceAll("DistributorCode", "distributorCode").replaceAll("BankAccountNo", "bankAccountNo")
				.replaceAll("LastBookingDate", "lastBookingDate").replaceAll("ConsumerAddress", "consumerAddress")
				.replaceAll("TotalRefillConsumed", "totalRefillConsumed").replaceAll("status-code", "status_code")
				.replaceAll("status-lpgid", "status_code");
	}





	private String getStatusMsg(int i) {
		String msg = null;
		if (i != 0) {

			if (i == 101) {
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
				info.setMessage("Internal processing error of Karza.");
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



	@Override
	public AadharResponse aadharRequestService(final AadharRequest aadharRequest, KarzaReqRes reqRes ) {
		AadharResponse aadharResponse= new AadharResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.aadhar.url");
		//String urlSave = "https://testapi.karza.in/v2/aadhar";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.aadhar.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (aadharRequest != null) {

				if (aadharRequest.getPayload() != null 
						&& aadharRequest.getPayload().getAadhaarId() != null && !aadharRequest.getPayload().getAadhaarId().equals("")
						&& !aadharRequest.getPayload().getConsent().equals("") && aadharRequest.getPayload().getConsent() != null) {
					AadharDemographics demographics=aadharRequest.getPayload().getDemographics();


					if(demographics!=null){
						//AadharDob aadharDob=demographics.getDob();
						//AadharName aadharName=demographics.getName();

						boolean result = checkValidation(aadharRequest.getHeader());
						if (result == true) {
							String responseAadharCall = null;
							ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
							ObjectMapper om = new ObjectMapper();

							String aadharRequestPayload=ow.writeValueAsString(aadharRequest.getPayload());
							aadharRequestPayload=aadharRequestPayload.replaceAll("aadhaarId", "aadhaar-id")
									.replaceAll("matchingStrategy", "matching-strategy")
									.replaceAll("nameValue", "name-value")
									.replaceAll("dobValue", "dob-value")
									.replaceAll("addressFormat", "address-format")
									.replaceAll("addressFreetext", "address-freetext")
									.replaceAll("matchingStrategy", "matching-strategy")
									.replaceAll("addressValue", "address-value");

							if(hardCodedEnv.equalsIgnoreCase("Y")){

								String	res="{\"request_id\": \"c77c54c7-1795-11e8-a817-67e743db6346\", \"result\": {\"info\": \"03{bd4b5a280552d61f7dcada08ee23dcb149d9bede8b57d738806353576e80d019,c2e595e082d9157477d6b35c428f7a3e8113fea6599ab353ca6069370cea4011,01b800800b800010,2.0,20180222060110,0,0,0,0,2.0,cc8cbd56e1d85eff5db05b7cc8a6dd9124fe24321db232de882529875de687c1,acd80c57680d4a038504bdf1b351d990e9739fc931cafb743bc41806961ae142,06ab532fae6bcb5ba66d8c9dae2daacab60ed1b326e63e39c488d9e37160ffe4,23,E,100,NA,NA,NA,NA,E,100,NA,,NA,NA,NA,NA,NA,NA}\", \"aadhaar-reference-code\": \"bfb631c2554f4df28699ba790ea75e43\", \"aadhaar-status-code\": \"200\", \"success\": false, \"pid-timestamp\": \"20180222060110\"}, \"status-code\": \"101\"}";
								info.setResponse(res);
								info.setResponseCode(200);
							}else{
								info = httpCall(aadharRequestPayload, urlSave,key);
							}



							String 	response="{\"header\" : {\"msgVersion\" : \"1.0\",\"appId\" : \"KARZA\",\"correlationId\" : \"12345\",\"token\" : \"f4b10aac-e9c2-4bf5-8c8e-31bee316dd8d\"},\"msgInfo\" : {\"code\" : \"101\",\"status\" : \"Success\",\"message\" : \"Valid Authentication\"},\"payload\" : {\"result\" : {\"success\" : \"false\",\"aadhaar_reference_code\" : \"662c978606bf4789b0fd654623095f00\",\"aadhaar_status_code\" : \"200\",\"info\" : \"03{bd4b5a280552d61f7dcada08ee23dcb149d9bede8b57d738806353576e80d019,c2e595e082d9157477d6b35c428f7a3e8113fea6599ab353ca6069370cea4011,01b800800b800010,2.0,20180226092418,0,0,0,0,2.0,cc8cbd56e1d85eff5db05b7cc8a6dd9124fe24321db232de882529875de687c1,acd80c57680d4a038504bdf1b351d990e9739fc931cafb743bc41806961ae142,06ab532fae6bcb5ba66d8c9dae2daacab60ed1b326e63e39c488d9e37160ffe4,23,E,100,NA,NA,NA,NA,E,100,NA,,NA,NA,NA,NA,NA,NA}\",\"pid_timestamp\" : \"20180226092418\"},\"byteArray\" : \"JVBERi0xLjQKJeLjz9MKMSAwIG9iago8PC9UeXBlL1hPYmplY3QvU3VidHlwZS9JbWFnZS9XaWR0\\naCAxODUvSGVpZ2h0IDQ3L0xlbmd0aCAyOTE3L0NvbG9yU3BhY2VbL0NhbFJHQjw8L0dhbW1hWzIu\\nMiAyLjIgMi4yXS9NYXRyaXhbMC40MTIzOSAwLjIxMjY0IDAuMDE5MzMgMC4zNTc1OCAwLjcxNTE3\\nIDAuMTE5MTkgMC4xODA0NSAwLjA3MjE4IDAuOTUwNF0vV2hpdGVQb2ludFswLjk1MDQzIDEgMS4w\\nOV0+Pl0vRGVjb2RlUGFybXM8PC9CaXRzUGVyQ29tcG9uZW50IDgvUHJlZGljdG9yIDE1L0NvbHVt\\nbnMgMTg1L0NvbG9ycyAzPj4vSW50ZW50L1BlcmNlcHR1YWwvQml0c1BlckNvbXBvbmVudCA4L0Zp\\nbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnhe7dpbjFZXFQfwJk2MGo2psX3wwZhGkyby0KRNTBOj\\nL00aY2LSxAcepKZGoaYJxEaixkhDVbAESgst0iulabRYqZBaBUqocociFsYpNxGkYMFaepW21BZ/\\nnXXY2T3nfGfOzDAQh/PPyuSbdfbae6+1/nvvtc/3XXCqQ4d26LjSoS06rnRoi44rHdqi40qHtui4\\n0qEtOq50aIuOKx3aouNKh7YYPleOnzi58dDLIX1HXzv6+lvFg7GOY6+8vqpvP3nq2QOvvVl4/dbb\\n76zb/Q/KFTv2HXzh5VCec/z3nXc37Tscs/rbseOFdrgYGleeee7FpU/vMgOfl/Q9f+ltaz83b/24\\n+euv/MXGaxZvm7i8f9H2wwdfeiMaj1WIwIVfn04unjSr77ljoXzx9ROf+e4dlBeMnzb7dxtDec6B\\nypf/cGHM6gePrC60w0VbrmDJdQ/+6aKbHvrKXSvffY8qp5b2H73sjnVYcsWAXH7XBqShueqezTev\\n2TeGGfPEX/Z+7FszyaVTbu8//K9Q4oqsUH7gulvmrdwcynOOEyff/sL0B8zqw9f/bPrSPxba4WJw\\nrthF5jzZd8nUhz80eREZf9+a0OOKTQVXSoI0GPPF+7fYeKLlGEPHlXoIAXKgiB0FXT4y5cFBuRIS\\njLHBOMij/ZhBx5UaHP/Pm04cRMGSkPZcIQ4mdJm6cs8Yo0vHlTIcPQqUnChD5QoJusxefyBMxgY6\\nrpRx77rdiHJxRpRhcIVEzbti99GwasaRl15181y2bTdxI/Vv8aCCPf/898LVT5PFa5955cSbhbYO\\n+rlz1RYt3V8KVQWuwZrpKpptP/h81O+1OONcccc2unH17IprOy8eNMIVTJQiAr1iVeWKLYB3xkqG\\nfI/Gg6KeK/tfePWz034dNcoIuUJwxY36+ImTYVgLt/8bH3zisu/Nv+jbP+fYB7/xEx7694b7H0/5\\nyPHLjTvj4nrJDbOa3xzowY2RfH7avYUqgxDf9PDKcd9fYDiDhujz6hmLe3HrDHJlw95DExY85rId\\no3P8E5Nu1Y/7bcM6QZEv3/rwJ2+cE4EizMWKI6VXO4krH/3mjFmPbzBzfvEurMKQ74Zrw5h6rkxe\\nsilOH/tKLlWuxIV5UHESLdx6KAyrQPBPTb4tpo4r/qYPlIJy31N/LpqexqNb+qOBQDe/+xJBnQiW\\nMBWq0xA7IZbd0rgfn/geX5lcf/ey9LYtYVCuWLKhbIB9a+bydZjRy2sTM1DR+jRUflMe+oOJmZ6W\\nVSvRwKSidcaViyfNwgksKRnyNAw9Xf3XvxdmPVDDFcvUjnLhd+5Hl5JQpvcr8S4OCdqIlm7RtVsL\\nHnDAjAXOB/4IEwnHKDnmw9zfbyoMBjByrqzq268HenGMccXrih/drUNKj+glfvz834h4YTOAZq4Y\\nCwnsCiUpdWIp61yqgpeWitF5bWGYCTEHj2yf8eYTfMBdW2nERAMTMOFEAn8jjNZeWCWueMTEI58Z\\n8rFkSGloBUCMVYsarjiAFCuLN+2tCv3qXUei2cGX3kAXu0tL0bj6PcDW/UfCjYia88J0I7g+SHPo\\nOemvw7UwGzFX9C8xlBE+OTCcfFu4loqNIZ6am4zavQuzATRzhYmge/TpKXND8IAmX+4YIEMxtKc/\\n/e1aNYQeHAQeaexRDO2sSSRztNFEXnntX0WbCatvTJ4LemOITM61uHvmXAlD/LA4+aiBEYXUYqC3\\nMOJpw2HUs7Y9C8D9r92+JKKGClZD8SADTnBSA83ywI2QK7GsI3wlKgSQwMrTv1HkOx9iUK6EO0n0\\nYJRU/ajE9UxDr4d8KWMwH+l1Ys4+06RH6Bvjymj6biEHR1jJfSr2c64YUcVWre2cEj9+dI2nMWjD\\ndelcckWgI7J40PBthWUXfBJE14RQjoQrQinckUJkDWUVDimDEuZ5CTIoV4IfSSj1YMLRknnaVOwi\\noQReyKVHeoiFkS9xC0knEYQVO/YV2gqMYibFPxlXwhGldPHg/bBovzrnV6IRfKqWaIEyV5QUU1fu\\nmbi8v1YmLN05e/2BqFdcg9Uu1y58sqVorGROGwNIgMmJrF3XdlpoK7CkZEUzEUx7wEi4YuMNjlI2\\nxB0kTBvjWqyFajCuaKz8VCfqOQmTtENYxOajpSymWsRyt9kEUTyVuTzloM+SF22QuMJQn4W2DuKp\\nmSCbgAOx0L4fZa6oQq66Z7Nq1EW3KkpUjAmuqF2Uum5GLUVp7B6evzyw3YX/X7rlgZxDJQhoypl7\\ndShHwhWrmWHYVvfkHHa7NMPwGpq54mir3tpyoB1HiOIsNEEUAwVRrp37SPUVi/0vIoA0haoFElcY\\n4mihrYNDTeUkpEbJj8UcNVxxYbn8rg2ldyQhLsmTn3g2Wip1MSB/+9Is7lZXzlyWRyFWWGQxarFa\\n4IrwRaQSV5SKFkHku5krgsswRglN4opkN3MlzXBQrvArcaX5/UqJKzbUnCg4UftqceRccZQX2jpw\\nRFk2NK64qlyzeNvZ4cqdq7aYmZSXiscS0mnK4cQV/qRzpOHFgATbkyJYbgqhzG1XNJ5B7blikrhY\\nmmQt7HPaMNfeak41SgNRYIRnkCGa3/EPZ1+xvhUljpsSS0LOLFeci5EznjT8PqiWK/b8qE8pW9an\\n6WiQj1RbNNhCe65AHFjGEvRcX4ILkWYcV6XZFPUTRMnvL2DmecmSalvtTaDQVqDz3Gp0uQJq214v\\n788sV/DSoudGlLe9todaroBE2vCFz6PSm7pAbO+RV1lJ1SUECcK2gaZD4srW/UfEOvq0+vPhcrjd\\nxO1Xy6glWZVeEDsldUKZyji9BbGIRVLLRUEw2wkLHkucG3WuLD39g7eqnFmugNUTUfMXXXhbexhV\\n6xUQvvx9muPfjVrEcUsVYhfJ36eVyMRWxJOtrLhPCjHbJJoNiStgevHaxlQdLgpwA0VvyRxMJpoR\\n/UhnIhZ+S6pZSZs2Ep/okqzMh2tukWKlZy5z3NC6YhhWpXdxo8UVJUuv8vaMcwXUXPJhhRETtXos\\nShtJLpSiUOIKpLTFe3psc7hIkva6ilWexy5H9R0/W5FNgh8xrjYtuWL/N3nuBAmIpJoPMVzRKA76\\nBY9pZmgtY9q2WLZKN5MJrhhXgoO14AOrCwd+56vniJWe2eqBVejZWidhNepcgZvX7KvdWkaDK3LA\\njfAHXWK6JeG/mGpQ4gq4EAkZQjAk+iE+6Ep7tvabtDRLkHW5ZBsmYZuEbZwRPrTkCjhi7IKxGyV3\\n/GuPKVoMwGZgM4sZahbDkfCCnsvV7yA5wh3NNIiWMZP4YBRTyr8ePxtcibcs1a1lNLgSUKxYWBLD\\nYcK3XCJnPlS5ArZi14TYS8KcaK/D/FuYWtj8XUzitEq2Jblg/DR1T+KKZNBY32ZV+9rKtmFlS5J8\\nM8dFjUtcAUtfXYKFMpQPZ5NQ56p+inYVmEAeK2LyXBCE0isAjHTUGn3Q3/FzxDSiZa/CsZ4rsGj7\\nYVvLFWeLKwEzVvDPXL7OIshFqiSmF1cCGLNixz5r0VVcYnSVlwjNwBhnhASzrYpCQVJTb0oKmnkr\\nNxur4Zs2a1oZwTB66PViGrGUSobmJse1t8SLZ71hMhzkps7ZmnyqeHLoXDxN1QR67RYBjnAnWvZ6\\nf9GTK1iPFqWTaLS50gvpTVQDVzqMNnpyBY6fODlh6c6cLueEK1irvO24cs7RxBVAl4nL+9Nh1HHl\\nfMYgXAFn3sKthxAFY0jHlfMWg3Ml0Hf0NSwZN399+p45caX0m9xe0nHl/x1tuRLAmGW7jkmez/Gb\\nhPzXuM2icek3CS1hY7t6xuK4zqXv/zqcfQyNKzncslbvOjIkcYeU+MK+NeJ+6NJPar8H6XB2MHyu\\ndDjf0HGlQ1t0XOnQFh1XOrRFx5UObdFxpUM7nDr1P5ZaxDAKZW5kc3RyZWFtCmVuZG9iago1IDAg\\nb2JqCjw8L0xlbmd0aCA2NjAvRmlsdGVyL0ZsYXRlRGVjb2RlPj5zdHJlYW0KeJyVVclu2zAUvOsr\\n3jE9hOG+HNO4LdqiRRMLBXpUZMpxYdmJFhT9m35qn7ZEsmnFgWGIAGfeDMl55FP0Po6EBks1xKuI\\nwiVXRLJ+LClxph9bQWw/ZFIQ1mI+xNFt9NT+OXzBuU/NGBil0Py6WlI5MJYS/KQ5XG3yNYXFHm4b\\nadbiJpg4jzo+1r98HqHSoc6IzpklBito3le4+siAcYiziBKjuJMtsFhHF9fJ6iEp4LquHvyu2qRJ\\ntdnv4M4/7ovqXfwba68bsXFxp4gVYCRvVz0ufvGvobTeEP2shDrCgra4fIdIhxPADdEaCh9lh1Cm\\nTQM4F86dIeejJU7xGfh4Sylh8GfG+bJHzBseULM+B9C8veXIkjLNIbxmaR7VW5oHDZZOoabB461x\\nwxzRossGH7Lx9dcN5jytc8wZlHWeJ8XfPi6jClYSjWpOEiaHcNmWP3CPOcw5gg07R4LvdX7vi2Ou\\nwKU4HuQuq6QqISnh0RdQ7usi9cd8qQWRYcMNvy7hmy/LZB2gIlOhstbNlk2YwaY8LsCpIhjsUAVO\\njVJCCs4cDyxa4pnqIHFZpykaDixUaWJMkPMz2W5WYbdPmCInGbNw+G2akYLCo9OY9ObCmjQiHW4o\\nptqpHkiJcMrqCXJewHBCxTkCHfDtAlISbMgzBDrg2wUYbjw/R6ADBgROPxQKny4Mgmiv9Gm/LnyV\\nbLZ+NXoMDhKBaaCWCNURxWsJYhgdpiacIUFZsi1DzWVA4Suo3VShb487n/nC71IPN/tVgN3rjSsM\\nelrz1Bmrqb7PpLHunmYrraTmgjqVURq2gptFRdBK3+rzPkb0lzY9IYX3KGZlIvXj8wLiTe7LKskf\\nT6uMmC8qzFLONXVcMttT/wNAnvQ4CmVuZHN0cmVhbQplbmRvYmoKNyAwIG9iago8PC9UeXBlL1Bh\\nZ2UvTWVkaWFCb3hbMCAwIDU5NSA4NDJdL1Jlc291cmNlczw8L0ZvbnQ8PC9GMSAyIDAgUi9GMiAz\\nIDAgUi9GMyA0IDAgUj4+L1hPYmplY3Q8PC9pbWcwIDEgMCBSPj4+Pi9Db250ZW50cyA1IDAgUi9Q\\nYXJlbnQgNiAwIFI+PgplbmRvYmoKMiAwIG9iago8PC9UeXBlL0ZvbnQvU3VidHlwZS9UeXBlMS9C\\nYXNlRm9udC9IZWx2ZXRpY2EvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjMgMCBv\\nYmoKPDwvVHlwZS9Gb250L1N1YnR5cGUvVHlwZTEvQmFzZUZvbnQvSGVsdmV0aWNhLUJvbGQvRW5j\\nb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjQgMCBvYmoKPDwvVHlwZS9Gb250L1N1YnR5\\ncGUvVHlwZTEvQmFzZUZvbnQvSGVsdmV0aWNhLU9ibGlxdWUvRW5jb2RpbmcvV2luQW5zaUVuY29k\\naW5nPj4KZW5kb2JqCjYgMCBvYmoKPDwvVHlwZS9QYWdlcy9Db3VudCAxL0tpZHNbNyAwIFJdPj4K\\nZW5kb2JqCjggMCBvYmoKPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDYgMCBSPj4KZW5kb2JqCjkgMCBv\\nYmoKPDwvUHJvZHVjZXIoaVRleHSuIDUuNS4xMSCpMjAwMC0yMDE3IGlUZXh0IEdyb3VwIE5WIFwo\\nQUdQTC12ZXJzaW9uXCkpL0NyZWF0aW9uRGF0ZShEOjIwMTgwMjI2MTQ1NDUyKzA1JzMwJykvTW9k\\nRGF0ZShEOjIwMTgwMjI2MTQ1NDUyKzA1JzMwJyk+PgplbmRvYmoKeHJlZgowIDEwCjAwMDAwMDAw\\nMDAgNjU1MzUgZiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDA0MTgyIDAwMDAwIG4gCjAwMDAw\\nMDQyNzAgMDAwMDAgbiAKMDAwMDAwNDM2MyAwMDAwMCBuIAowMDAwMDAzMzAyIDAwMDAwIG4gCjAw\\nMDAwMDQ0NTkgMDAwMDAgbiAKMDAwMDAwNDAyOSAwMDAwMCBuIAowMDAwMDA0NTEwIDAwMDAwIG4g\\nCjAwMDAwMDQ1NTUgMDAwMDAgbiAKdHJhaWxlcgo8PC9TaXplIDEwL1Jvb3QgOCAwIFIvSW5mbyA5\\nIDAgUi9JRCBbPDQ4ZmE1NDBmYTUzNTk0ODNmOTczYmZlZWFkMjYwYzI5Pjw0OGZhNTQwZmE1MzU5\\nNDgzZjk3M2JmZWVhZDI2MGMyOT5dPj4KJWlUZXh0LTUuNS4xMQpzdGFydHhyZWYKNDcxMwolJUVP\\nRgo=\",\"pdfPath\" : \"E:\\\\\\\\opt\\\\\\\\Logger\\\\\\\\KarzaPdf\\\\\\\\2-2018\",\"status_code\" : 101,\"request_id\" : \"d1d830e0-1ad6-11e8-a44b-1fb387177b02\",\"status_msg\" : \"Valid Authentication\"}}";
							aadharResponse=om.readValue(response, AadharResponse.class);
							try {
								String price=null;
								reqRes.setIntReq(ow.writeValueAsString(aadharRequest.getPayload()));
								reqRes.setIntRes(info.getResponse());
								if (info.getResponseCode() == 200)
								{
									price=resProp.getString("com.karza.aadhar.success.price");
								}else {
									price=resProp.getString("com.karza.aadhar.failure.price");
								}
								reqRes.setPrice(price);

							} catch (Exception e) {
								logger.error("Error while updating karza internal req res:" + e);
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
											responseAadharCall = info.getResponse().replaceAll("status-code", "status_code")
													.replaceAll("aadhaar-reference-code", "aadhaar_reference_code")
													.replaceAll("aadhaar-status-code", "aadhaar_status_code")
													.replaceAll("aadhaar-status_code", "aadhaar_status_code")
													.replaceAll("pid-timestamp", "pid_timestamp");
											om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
											aadharResponse.setPayload(om.readValue(responseAadharCall, AadharResponsePayload.class));

											String statusmsg = "No status code received from third party";
											if (aadharResponse.getPayload() != null) 
											{

												if (aadharResponse.getPayload().getStatus_code() != 0)
												{
													statusmsg = getStatusMsg(aadharResponse.getPayload().getStatus_code());

												}
											}

											aadharResponse.getPayload().setStatus_msg(statusmsg);

											if (aadharResponse.getPayload().getStatus_code() == 101) 
											{
												msgInfo.setStatus(StringConstants.SUCCESS.toString());
												msgInfo.setMessage(aadharResponse.getPayload().getStatus_msg());
												msgInfo.setCode("" + aadharResponse.getPayload().getStatus_code());
											}
											else if (aadharResponse.getPayload().getStatus_code() == 102)
											{
												msgInfo.setStatus(StringConstants.FAILURE.toString());
												msgInfo.setMessage(aadharResponse.getPayload().getStatus_msg());
												msgInfo.setCode("" + aadharResponse.getPayload().getStatus_code());
											}
											else
											{

												msgInfo.setStatus(StringConstants.FAILURE.toString());
												msgInfo.setMessage(aadharResponse.getPayload().getStatus_msg());
												msgInfo.setCode("" + aadharResponse.getPayload().getStatus_code());
											}

										} catch (Exception ex) {
											// ErrorResponse errorResponse =
											// om.readValue(responseDlCall,ErrorResponse.class);
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage("" + StringConstants.MESSAGE600);
											//msgInfo.setInternalCode(StringConstants.C_600.toString());
											msgInfo.setCode(StringConstants.C_600.toString());
											logger.info("Failure from 3rd Party API : " + info.getResponse());
										}

									} else {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("Max retries exceeded");
										//msgInfo.setInternalCode("" + 104);
										msgInfo.setCode(""+104);

									}

								}

								else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Invalid Input");
									//msgInfo.setInternalCode("" + 101);
									msgInfo.setCode(""+101);
								}
								// for invalid input

							}else {

								MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
								msgInfo=httpMsg;
								// ErrorResponse errorResponse =
								// om.readValue(responseDlCall,ErrorResponse.class);
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("" + StringConstants.MESSAGE500);
								//msgInfo.setInternalCode(StringConstants.C_500.toString());
								logger.info("Failure from 3rd Party API : " + info.getResponse());
							}
						} else {
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage(StringConstants.MESSAGE401.toString());
							//msgInfo.setInternalCode(StringConstants.C_401.toString());
							msgInfo.setCode(""+401);
						}


					}else{
						msgInfo.setStatus("Failure");
						msgInfo.setMessage("Bad Request");
						//msgInfo.setInternalCode(""+400);
						msgInfo.setCode(""+400);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		aadharResponse.setMsgInfo(msgInfo);
		aadharResponse.setHeader(aadharRequest.getHeader());
		return aadharResponse;
	}

	@Override
	public PassportResponse passportRequestService(final PassportRequest passportRequest, KarzaReqRes reqRes) {
		PassportResponse passportResponse=new PassportResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.passport.url");
		//		String urlSave = "https://testapi.karza.in/v2/passport";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.passport.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (passportRequest != null) {

				if (passportRequest.getPayload() != null 
						&& passportRequest.getPayload().getPassportNumber() != null && !passportRequest.getPayload().getPassportNumber().equals("")
						&& !passportRequest.getPayload().getConsent().equals("") && passportRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(passportRequest.getHeader());
					if (result == true) {
						String responsePassportCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						String passportRequestPayload=ow.writeValueAsString(passportRequest.getPayload());
						passportRequestPayload=passportRequestPayload.replaceAll("lastName", "last_name")
								.replaceAll("passportNumber", "passport_no");

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"dfef6a8f-17e1-11e8-b2f2-bf86f72fd239\", \"result\": {\"string1\": \"P<INDSHIRHATTI<<OMKAR<MILIND<<<<<<<<<<<<<<<<\", \"string2\": \"G3850330<8IND8908179M1807103<<<<<<<<<<<<<<<2\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(passportRequestPayload, urlSave,key);
						}



						try {
							String price=null;


							reqRes.setIntReq(ow.writeValueAsString(passportRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.passport.success.price");
							}else {
								price=resProp.getString("com.karza.passport.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responsePassportCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										passportResponse.setPayload(om.readValue(responsePassportCall, PassportResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (passportResponse.getPayload() != null) 
										{

											if (passportResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(passportResponse.getPayload().getStatus_code());

											}
										}

										passportResponse.getPayload().setStatus_msg(statusmsg);

										if (passportResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(passportResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + passportResponse.getPayload().getStatus_code());
										}
										else if (passportResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(passportResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + passportResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(passportResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + passportResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		passportResponse.setMsgInfo(msgInfo);
		passportResponse.setHeader(passportRequest.getHeader());
		return passportResponse;
	}



	@Override
	public GSTAuthenticationResponse gstAuthenticationRequestService(GSTAuthenticationRequest gstAuthenticationRequest,KarzaReqRes reqRes){
		GSTAuthenticationResponse gstAuthenticationResponse=new GSTAuthenticationResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.gstdetailed.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.gstauth.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (gstAuthenticationRequest != null) {

				if (gstAuthenticationRequest.getPayload() != null 
						&& gstAuthenticationRequest.getPayload().getGstin() != null && !gstAuthenticationRequest.getPayload().getGstin().equals("")
						&& !gstAuthenticationRequest.getPayload().getConsent().equals("") && gstAuthenticationRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(gstAuthenticationRequest.getHeader());
					if (result == true) {
						String gstAuthenticationResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y") && res!=null)
						{
							info.setResponse(res.getString("KARZA_Gst_Authentication_Report"));
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(gstAuthenticationRequest.getPayload()), urlSave,key);
						}
						try
						{
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(gstAuthenticationRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.gst_authentication.success.price");
							}else {
								price=resProp.getString("com.karza.gst_authentication.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										gstAuthenticationResponseCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										gstAuthenticationResponse.setPayload(om.readValue(gstAuthenticationResponseCall, GSTAuthenticationResponsePayload.class));
										String statusmsg = "No status code received from third party";
										if (gstAuthenticationResponse.getPayload() != null) 
										{

											if (gstAuthenticationResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(gstAuthenticationResponse.getPayload().getStatus_code());

											}
										}

										gstAuthenticationResponse.getPayload().setStatus_msg(statusmsg);

										if (gstAuthenticationResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(gstAuthenticationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + gstAuthenticationResponse.getPayload().getStatus_code());
										}
										else if (gstAuthenticationResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(gstAuthenticationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + gstAuthenticationResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(gstAuthenticationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + gstAuthenticationResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}






				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		gstAuthenticationResponse.setMsgInfo(msgInfo);
		gstAuthenticationResponse.setHeader(gstAuthenticationRequest.getHeader());
		return gstAuthenticationResponse;
	}

	@Override
	public CAMemberShipAuthResponse caMemberShipAuthService(CAMemberShipAuthRequest caMemberShipAuthRequest,KarzaReqRes reqRes){
		CAMemberShipAuthResponse caMemberShipAuthResponse=new CAMemberShipAuthResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.icai.url");
		//		String urlSave = "https://testapi.karza.in/v2/icai";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.cambrshpauth.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (caMemberShipAuthRequest != null) {

				if (caMemberShipAuthRequest.getPayload() != null 
						&& caMemberShipAuthRequest.getPayload().getMembership_no() != null && !caMemberShipAuthRequest.getPayload().getMembership_no().equals("")
						&& !caMemberShipAuthRequest.getPayload().getConsent().equals("") && caMemberShipAuthRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(caMemberShipAuthRequest.getHeader());
					if (result == true) {
						String caMemberShipAuthResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"1641d833-1795-11e8-a817-67e743db6346\", \"result\": {\"AssociateYear\": \"A2011\", \"COPStatus\": \"NOT HOLDING COP\", \"name\": \"SHIRHATTI OMKAR MILIND SUSHAMA\", \"gender\": \"M\", \"FellowYear\": \"\", \"Qualification\": \"BCOM\", \"address\": \"PUSHPA PLOT NO 125 AJANTHA CHS NANDIVALI ROAD DOMBIVALI EAST DOMBIVALI 421201\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(caMemberShipAuthRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(caMemberShipAuthRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.ca_membership_auth.success.price");
							}else {
								price=resProp.getString("com.karza.ca_membership_auth.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Error while updating karza internal req res:" + e);
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
										caMemberShipAuthResponseCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("COPStatus", "copStatus")
												.replaceAll("AssociateYear", "associateYear")
												.replaceAll("FellowYear", "fellowYear")
												.replaceAll("Qualification", "qualification");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										caMemberShipAuthResponse.setPayload(om.readValue(caMemberShipAuthResponseCall, CAMemberShipAuthResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (caMemberShipAuthResponse.getPayload() != null) 
										{

											if (caMemberShipAuthResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(caMemberShipAuthResponse.getPayload().getStatus_code());

											}
										}

										caMemberShipAuthResponse.getPayload().setStatus_msg(statusmsg);

										if (caMemberShipAuthResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(caMemberShipAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + caMemberShipAuthResponse.getPayload().getStatus_code());
										}
										else if (caMemberShipAuthResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(caMemberShipAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + caMemberShipAuthResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(caMemberShipAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + caMemberShipAuthResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}






				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		caMemberShipAuthResponse.setMsgInfo(msgInfo);
		caMemberShipAuthResponse.setHeader(caMemberShipAuthRequest.getHeader());
		return caMemberShipAuthResponse;
	}


	@Override
	public ICSIMemberShipResponse icsiMemberShipRequestService(ICSIMemberShipRequest icsiMemberShipRequest, KarzaReqRes reqRes){
		ICSIMemberShipResponse icsiMemberShipResponse=new ICSIMemberShipResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.icsi.url");
		//		String urlSave = "https://testapi.karza.in/v2/icsi";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.icsi.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (icsiMemberShipRequest != null) {

				if (icsiMemberShipRequest.getPayload() != null 
						&& !icsiMemberShipRequest.getPayload().getConsent().equals("") && icsiMemberShipRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(icsiMemberShipRequest.getHeader());
					if (result == true) {
						String icsiMemberShipResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"bded91b0-1aeb-11e8-ae83-89f2f239e477\", \"result\": [{\"City\": \"ROPAR\", \"Designation\": \"CHIEF MANAGER(FIN) & CO SECY\", \"BenevolentMember\": \"No\", \"Phone\": \"0172-670\", \"MembershipNumber\": \"A4557\", \"Mob\": \"7526068165\", \"CP_Number\": \"0\", \"address\": \"PHASE IV S.A.S.NAGAR DIST ROPAR\", \"Organization\": \"PUNJAB TRACTORS LTD.\", \"Email\": \"kaushal.mahesh@swarajenterprise.com\"}], \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(icsiMemberShipRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(icsiMemberShipRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.icsi_mambership.success.price");
							}else {
								price=resProp.getString("com.karza.icsi_mambership.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										icsiMemberShipResponseCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("City", "city")
												.replaceAll("Designation", "designation")
												.replaceAll("BenevolentMember", "benevolentMember")
												.replaceAll("Phone", "phone")
												.replaceAll("MembershipNumber", "membershipNumber")
												.replaceAll("Mob", "mob")
												.replaceAll("CP_Number", "cpNumber")
												.replaceAll("Email", "email")
												.replaceAll("Organization", "organization");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										icsiMemberShipResponse.setPayload(om.readValue(icsiMemberShipResponseCall, ICSIMemberShipResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (icsiMemberShipResponse.getPayload() != null) 
										{

											if (icsiMemberShipResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(icsiMemberShipResponse.getPayload().getStatus_code());

											}
										}

										icsiMemberShipResponse.getPayload().setStatus_msg(statusmsg);

										if (icsiMemberShipResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(icsiMemberShipResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icsiMemberShipResponse.getPayload().getStatus_code());
										}
										else if (icsiMemberShipResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(icsiMemberShipResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icsiMemberShipResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(icsiMemberShipResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icsiMemberShipResponse.getPayload().getStatus_code());
										}
									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		icsiMemberShipResponse.setMsgInfo(msgInfo);
		icsiMemberShipResponse.setHeader(icsiMemberShipRequest.getHeader());
		return icsiMemberShipResponse;
	}


	@Override
	public ICWAIFirmAuthResponse icwaiFirmAuthRequestService(ICWAIFirmAuthRequest icwaiFirmAuthRequest,KarzaReqRes reqRes){
		ICWAIFirmAuthResponse icwaiFirmAuthResponse=new ICWAIFirmAuthResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.icwaif.url");
		//		String urlSave = "https://testapi.karza.in/v2/icwaif";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.icwaifirmauth.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (icwaiFirmAuthRequest != null) {

				if (icwaiFirmAuthRequest.getPayload() != null 
						&& icwaiFirmAuthRequest.getPayload().getReg_no() != null && !icwaiFirmAuthRequest.getPayload().getReg_no().equals("")
						&& !icwaiFirmAuthRequest.getPayload().getConsent().equals("") && icwaiFirmAuthRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(icwaiFirmAuthRequest.getHeader());
					if (result == true) {
						String icwaiFirmAuthResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"64d11cf1-1aec-11e8-8250-b9bddd9f8fc2\", \"result\": {\"approvalDate\": \"21/06/1989\", \"FirmType\": \"Partnership\", \"FirmName\": \"N I Mehta & Co.\", \"Pin\": \"400021\", \"City\": \"MUMBAI\", \"reConDate\": \"28/03/2016\", \"Region\": \"WESTERN\", \"Address\": \"Cost Accountants,1101 Dalamal Tower, 'B' Wing,Free Press Journal Marg,Nariman Point\", \"MemberDetails\": [{\"City\": \"MUMBAI\", \"MemberNo\": \"7759\", \"Pin\": \"400021\", \"MemberName\": \"Dushyant Chandrakant Dave\", \"State\": \"MAHARASHTRA\", \"Address\": \"1101 Dalamal Tower,Nariman Point,\"}, {\"City\": \"MUMBAI\", \"MemberNo\": \"2380\", \"Pin\": \"400071\", \"MemberName\": \"P. N. Ramachandran\", \"State\": \"MAHARASHTRA\", \"Address\": \"No.-3, Satyam II,B S D G S Chs. Ltd.,Opp. Vijaya Bank, R.C. Marg, Chembur,\"}, {\"City\": \"MUMBAI\", \"MemberNo\": \"14530\", \"Pin\": \"400016\", \"MemberName\": \"Richard  Correa\", \"State\": \"MAHARASHTRA\", \"Address\": \"Divine House, Ground Floor,Room No. 6, Station Road,Mahim (W),\"}], \"deedDt\": \"28/03/2016\", \"State\": \"MAHARASHTRA\", \"Contact\": \"02243456200, 9820028560, 9820747984\", \"mobile\": \"9820028560\", \"ldt\": \"28/03/2016\", \"Dist\": \"\", \"email\": \"dushyant.dave@nimehta.com\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(icwaiFirmAuthRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(icwaiFirmAuthRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.icwai_firm_authentication.success.price");
							}else {
								price=resProp.getString("com.karza.icwai_firm_authentication.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										icwaiFirmAuthResponseCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("FirmType", "firmType")
												.replaceAll("FirmName", "firmName")
												.replaceAll("Pin", "pin")
												.replaceAll("City", "city")
												.replaceAll("Region", "region")
												.replaceAll("Address", "address")
												.replaceAll("MemberDetails", "memberDetails")
												.replaceAll("MemberNo", "memberNo")
												.replaceAll("MemberName", "memberName")
												.replaceAll("State", "state")
												.replaceAll("Contact", "contact")
												.replaceAll("Dist", "dist");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										icwaiFirmAuthResponse.setPayload(om.readValue(icwaiFirmAuthResponseCall, ICWAIFirmAuthResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (icwaiFirmAuthResponse.getPayload() != null) 
										{

											if (icwaiFirmAuthResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(icwaiFirmAuthResponse.getPayload().getStatus_code());

											}
										}

										icwaiFirmAuthResponse.getPayload().setStatus_msg(statusmsg);

										if (icwaiFirmAuthResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(icwaiFirmAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icwaiFirmAuthResponse.getPayload().getStatus_code());
										}
										else if (icwaiFirmAuthResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(icwaiFirmAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icwaiFirmAuthResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(icwaiFirmAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icwaiFirmAuthResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}






				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		icwaiFirmAuthResponse.setMsgInfo(msgInfo);
		icwaiFirmAuthResponse.setHeader(icwaiFirmAuthRequest.getHeader());
		return icwaiFirmAuthResponse;
	}


	@Override
	public EPFAuthOTPResponse epfAuthOTPRequestService(EPFAuthOTPRequest epfAuthOTPRequest,KarzaReqRes reqRes){
		EPFAuthOTPResponse epfAuthOTPResponse=new EPFAuthOTPResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.epfOtp.url");
		//		String urlSave = "https://testapi.karza.in/v2/epf-get-otp";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.epfauthotp.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (epfAuthOTPRequest != null) {

				if (epfAuthOTPRequest.getPayload() != null 
						&& epfAuthOTPRequest.getPayload().getMobile_no() != null && !epfAuthOTPRequest.getPayload().getMobile_no().equals("")
						&& !epfAuthOTPRequest.getPayload().getConsent().equals("") && epfAuthOTPRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(epfAuthOTPRequest.getHeader());
					if (result == true) {
						String epfAuthOTPResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"status-code\": \"101\", \"result\": {\"message\": \"An OTP has been sent to your mobile number 91XXXXXX71\"}, \"request_id\": \"05e81446-1af7-11e8-a46d-8b9f7c60dbbb\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(epfAuthOTPRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(epfAuthOTPRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.epf_otp_authentication.success.price");
							}else {
								price=resProp.getString("com.karza.epf_otp_authentication.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										epfAuthOTPResponseCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										epfAuthOTPResponse.setPayload(om.readValue(epfAuthOTPResponseCall, EPFAuthOTPResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (epfAuthOTPResponse.getPayload() != null) 
										{

											if (epfAuthOTPResponse.getPayload().getStatus_code() == 0)
											{
												if(epfAuthOTPResponse.getPayload().getResult().getMessage()!=null 
														&& !epfAuthOTPResponse.getPayload().getResult().getMessage().equals("")){
													statusmsg = "Valid Authentication";
												}


											}
										}

										epfAuthOTPResponse.getPayload().setStatus_msg(statusmsg);

										if (epfAuthOTPResponse.getPayload().getStatus_code() == 101 || epfAuthOTPResponse.getPayload().getStatus_code()==0) 
										{
											if(epfAuthOTPResponse.getPayload().getResult().getMessage()!=null 
													&& !epfAuthOTPResponse.getPayload().getResult().getMessage().equals("")){
												msgInfo.setStatus(StringConstants.SUCCESS.toString());
											}else{
												msgInfo.setStatus(StringConstants.FAILURE.toString());
											}

											msgInfo.setMessage(epfAuthOTPResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + epfAuthOTPResponse.getPayload().getStatus_code());
										}
										else if (epfAuthOTPResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(epfAuthOTPResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + epfAuthOTPResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(epfAuthOTPResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + epfAuthOTPResponse.getPayload().getStatus_code());
										}
									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		epfAuthOTPResponse.setMsgInfo(msgInfo);
		epfAuthOTPResponse.setHeader(epfAuthOTPRequest.getHeader());
		return epfAuthOTPResponse;
	}


	@Override
	public EPFAuthPassBookResponse epfAuthPassBookRequestService(EPFAuthPassBookRequest epfAuthPassBookRequest,KarzaReqRes reqRes){
		EPFAuthPassBookResponse epfAuthPassBookResponse=new EPFAuthPassBookResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.epfPassbook.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.epfpassbok.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (epfAuthPassBookRequest != null) {

				if (epfAuthPassBookRequest.getPayload() != null 
						&& epfAuthPassBookRequest.getPayload().getRequest_id() != null && !epfAuthPassBookRequest.getPayload().getRequest_id().equals("")
						&& !epfAuthPassBookRequest.getPayload().getOtp().equals("") && epfAuthPassBookRequest.getPayload().getOtp() != null) {

					boolean result = checkValidation(epfAuthPassBookRequest.getHeader());
					if (result == true) {
						String epfAuthPassBookResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"status\": \"101\", \"result\": {\"est_details\": [{\"est_name\": \"QUALTECH CONSULTANTS PVT LTD\", \"doe_epf\": \"05-03-2018\", \"office\": \"437\", \"doj_epf\": \"01-01-2015\", \"doe_eps\": \"05-03-2018\", \"member_id\": \"MRNOI00602400000000114\", \"passbook\": [{\"approved_on\": \"20-02-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"02-01-2015\", \"cr_pen_bal\": \"210\", \"cr_ee_share\": \"302\", \"cr_er_share\": \"92\", \"particular\": \"Cont. For 022015\", \"month_year\": \"22015\"}, {\"approved_on\": \"26-03-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"03-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 032015\", \"month_year\": \"32015\"}, {\"approved_on\": \"31-03-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"31-03-2015\", \"cr_pen_bal\": \"0\", \"cr_ee_share\": \"2\", \"cr_er_share\": \"1\", \"particular\": \"Int. Updated upto 31/03/2015\", \"month_year\": \"000000\"}, {\"approved_on\": \"05-05-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"04-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 042015\", \"month_year\": \"42015\"}, {\"approved_on\": \"28-05-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"05-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 052015\", \"month_year\": \"52015\"}, {\"approved_on\": \"24-06-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"06-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 062015\", \"month_year\": \"62015\"}, {\"approved_on\": \"24-07-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"07-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 072015\", \"month_year\": \"72015\"}, {\"approved_on\": \"17-08-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"08-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 082015\", \"month_year\": \"82015\"}, {\"approved_on\": \"17-09-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"09-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 092015\", \"month_year\": \"92015\"}, {\"approved_on\": \"27-10-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"10-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 102015\", \"month_year\": \"102015\"}, {\"approved_on\": \"19-11-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"11-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 112015\", \"month_year\": \"112015\"}, {\"approved_on\": \"22-12-2015\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"12-01-2015\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 122015\", \"month_year\": \"122015\"}, {\"approved_on\": \"07-03-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"01-01-2016\", \"cr_pen_bal\": \"500\", \"cr_ee_share\": \"720\", \"cr_er_share\": \"220\", \"particular\": \"Cont. For 012016\", \"month_year\": \"12016\"}, {\"approved_on\": \"11-03-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"02-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 022016\", \"month_year\": \"22016\"}, {\"approved_on\": \"28-05-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"31-03-2016\", \"cr_pen_bal\": \"0\", \"cr_ee_share\": \"443\", \"cr_er_share\": \"135\", \"particular\": \"Int. Updated upto 31/03/2016\", \"month_year\": \"000000\"}, {\"approved_on\": \"28-06-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"04-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"OB Adjustment-  ( AUTO APPENDIX BACK PERIOD )- IPR(Back Period) (2015-2016)-Contribution only\", \"month_year\": \"000000\"}, {\"approved_on\": \"23-09-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"04-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 042016\", \"month_year\": \"42016\"}, {\"approved_on\": \"19-05-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"05-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 052016\", \"month_year\": \"52016\"}, {\"approved_on\": \"28-09-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"06-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 062016\", \"month_year\": \"62016\"}, {\"approved_on\": \"17-09-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"07-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 072016\", \"month_year\": \"72016\"}, {\"approved_on\": \"26-08-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"08-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 082016\", \"month_year\": \"82016\"}, {\"approved_on\": \"23-09-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"09-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 092016\", \"month_year\": \"92016\"}, {\"approved_on\": \"10-11-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"10-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 102016\", \"month_year\": \"102016\"}, {\"approved_on\": \"21-11-2016\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"11-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 112016\", \"month_year\": \"112016\"}, {\"approved_on\": \"21-04-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"12-01-2016\", \"cr_pen_bal\": \"937\", \"cr_ee_share\": \"1350\", \"cr_er_share\": \"413\", \"particular\": \"Cont. For 122016\", \"month_year\": \"122016\"}, {\"approved_on\": \"13-04-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"01-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 012017\", \"month_year\": \"12017\"}, {\"approved_on\": \"04-04-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"02-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 022017\", \"month_year\": \"22017\"}, {\"approved_on\": \"08-04-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"03-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 032017\", \"month_year\": \"32017\"}, {\"approved_on\": \"17-06-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"31-03-2017\", \"cr_pen_bal\": \"0\", \"cr_ee_share\": \"1635\", \"cr_er_share\": \"500\", \"particular\": \"Int. Updated upto 31/03/2017\", \"month_year\": \"000000\"}, {\"approved_on\": \"22-04-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\",\"tr_date_my\": \"04-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 042017\", \"month_year\": \"42017\"}, {\"approved_on\": \"07-06-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"05-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 052017\", \"month_year\": \"52017\"}, {\"approved_on\": \"03-07-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"06-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 062017\", \"month_year\": \"62017\"}, {\"approved_on\": \"30-07-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"07-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 072017\", \"month_year\": \"72017\"}, {\"approved_on\": \"20-08-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"08-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 082017\", \"month_year\": \"82017\"}, {\"approved_on\": \"21-09-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"09-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 092017\", \"month_year\": \"92017\"}, {\"approved_on\": \"20-10-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"10-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 102017\", \"month_year\": \"102017\"}, {\"approved_on\": \"17-11-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"11-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 112017\", \"month_year\": \"112017\"}, {\"approved_on\": \"15-12-2017\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"12-01-2017\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 122017\", \"month_year\": \"122017\"}, {\"approved_on\": \"16-01-2018\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"01-01-2018\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 012018\", \"month_year\": \"12018\"}, {\"approved_on\": \"17-02-2018\", \"db_cr_flag\": \"C\", \"tr_approved\": \"05-03-2018\", \"tr_date_my\": \"02-01-2018\", \"cr_pen_bal\": \"1250\", \"cr_ee_share\": \"1800\", \"cr_er_share\": \"550\", \"particular\": \"Cont. For 022018\", \"month_year\": \"22018\"}]}], \"employee_details\": {\"dob\": \"24-09-1998\", \"father_name\": \"UMRAO SINGH\", \"member_name\": \"Pawan Singh\"}}, \"request_id\": \"0ab560fd-211c-11e8-a384-45dee8ec3342\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(epfAuthPassBookRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;

							reqRes.setIntReq(ow.writeValueAsString(epfAuthPassBookRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.epf_otp_passbook.success.price");
							}else {
								price=resProp.getString("com.karza.epf_otp_passbook.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										epfAuthPassBookResponseCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("status", "status_code")
												.replaceAll("status_code_code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										epfAuthPassBookResponse.setPayload(om.readValue(epfAuthPassBookResponseCall, EPFAuthPassBookResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (epfAuthPassBookResponse.getPayload() != null) 
										{

											if (epfAuthPassBookResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(epfAuthPassBookResponse.getPayload().getStatus_code());

											}
										}

										epfAuthPassBookResponse.getPayload().setStatus_msg(statusmsg);

										if (epfAuthPassBookResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(epfAuthPassBookResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + epfAuthPassBookResponse.getPayload().getStatus_code());
										}
										else if (epfAuthPassBookResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(epfAuthPassBookResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + epfAuthPassBookResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(epfAuthPassBookResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + epfAuthPassBookResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		epfAuthPassBookResponse.setMsgInfo(msgInfo);
		epfAuthPassBookResponse.setHeader(epfAuthPassBookRequest.getHeader());
		return epfAuthPassBookResponse;
	}



	@Override
	public ITRAuthResponse itrAuthRequestRequestService(ITRAuthRequest itrAuthRequest,KarzaReqRes reqRes){
		ITRAuthResponse itrAuthResponse=new ITRAuthResponse();
		MessageInfo msgInfo = new MessageInfo();

		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.itr.url");
		//		String urlSave = "https://testapi.karza.in/v2/itr";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.Karza.itrauth.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (itrAuthRequest != null) {

				if (itrAuthRequest.getPayload() != null 
						&& itrAuthRequest.getPayload().getConsent() != null && !itrAuthRequest.getPayload().getConsent().equals("")
						&& itrAuthRequest.getPayload().getAck() != null && !itrAuthRequest.getPayload().getAck().equals("")
						&& !itrAuthRequest.getPayload().getPan().equals("") && itrAuthRequest.getPayload().getPan() != null) {

					boolean result = checkValidation(itrAuthRequest.getHeader());
					if (result == true) {
						String itrAuthResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"9bfcc29a-187a-11e8-9f67-85e0d6045ef7\", \"result\": {\"status\": \"Return Processed and Refund Paid\", \"validity\": \"Valid\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(itrAuthRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;

							reqRes.setIntReq(ow.writeValueAsString(itrAuthRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.itr_authentication.success.price");
							}else {
								price=resProp.getString("com.karza.itr_authentication.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										itrAuthResponseCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										itrAuthResponse.setPayload(om.readValue(itrAuthResponseCall, ITRAuthResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (itrAuthResponse.getPayload() != null) 
										{

											if (itrAuthResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(itrAuthResponse.getPayload().getStatus_code());

											}
										}

										itrAuthResponse.getPayload().setStatus_msg(statusmsg);

										if (itrAuthResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(itrAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + itrAuthResponse.getPayload().getStatus_code());
										}
										else if (itrAuthResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(itrAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + itrAuthResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(itrAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + itrAuthResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}






				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		itrAuthResponse.setMsgInfo(msgInfo);
		itrAuthResponse.setHeader(itrAuthRequest.getHeader());
		return itrAuthResponse;
	}


	@Override
	public EmailAuthResponse emailAuthRequestService(EmailAuthRequest emailAuthRequest, KarzaReqRes reqRes ){
		EmailAuthResponse emailAuthResponse=new EmailAuthResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.emailAuth.url");
		//		String urlSave = "https://testapi.karza.in/v2/email";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.emailauth.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (emailAuthRequest != null) {

				if (emailAuthRequest.getPayload() != null 
						&& !emailAuthRequest.getPayload().getEmail().equals("") && emailAuthRequest.getPayload().getEmail() != null) {

					boolean result = checkValidation(emailAuthRequest.getHeader());
					if (result == true) {
						String emailAuthResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"status\": \"101\", \"result\": {\"meta\": {\"params\": {\"email\": \"sajal7patel@gmail.com\"}}, \"data\": {\"disposable\": false, \"webmail\": true, \"result\": true, \"accept_all\": false, \"smtp_check\": true, \"regexp\": true, \"mx_records\": true, \"email\": \"sajal7patel@gmail.com\"}}, \"request_id\": \"6cbd4c70-25df-11e8-962b-391bba0b62e5\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(emailAuthRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(emailAuthRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.email_authentication.success.price");
							}else {
								price=resProp.getString("com.karza.email_authentication.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										emailAuthResponseCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										emailAuthResponse.setPayload(om.readValue(emailAuthResponseCall, EmailAuthResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (emailAuthResponse.getPayload() != null) 
										{

											if (emailAuthResponse.getPayload().getStatus() != 0)
											{
												statusmsg = getStatusMsg(emailAuthResponse.getPayload().getStatus());

											}
										}

										emailAuthResponse.getPayload().setStatus_msg(statusmsg);

										if (emailAuthResponse.getPayload().getStatus_code() == 101 || emailAuthResponse.getPayload().getStatus() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(emailAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + emailAuthResponse.getPayload().getStatus());
										}
										else if (emailAuthResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(emailAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + emailAuthResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(emailAuthResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + emailAuthResponse.getPayload().getStatus_code());
										}
									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		emailAuthResponse.setMsgInfo(msgInfo);
		emailAuthResponse.setHeader(emailAuthRequest.getHeader());
		return emailAuthResponse;
	}

	@Override
	public IFSCResponse ifscRequestService(IFSCRequest ifscRequest, KarzaReqRes reqRes){
		IFSCResponse ifscResponse=new IFSCResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.ifsc.url");
		//		String urlSave = "https://testapi.karza.in/v2/ifsc";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.ifsc.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (ifscRequest != null) {

				if (ifscRequest.getPayload() != null 
						&& !ifscRequest.getPayload().getIfsc().equals("") && ifscRequest.getPayload().getIfsc() != null) {

					boolean result = checkValidation(ifscRequest.getHeader());
					if (result == true) {
						String ifscResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"845aa5c1-1963-11e8-8250-b9bddd9f8fc2\", \"result\": {\"city\": \"RUDRAPRAYAG\", \"district\": \"CHAMOLI\", \"ifsc\": \"SBIN0004532\", \"micr\": \"246002073\", \"state\": \"UTTARAKHAND\", \"contact\": \"0\", \"branch\": \"POKHARI\", \"address\": \"DIST CHAMOLI,UTTARANCHAL 246473\", \"bank\": \"STATE BANK OF INDIA\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(ifscRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;


							reqRes.setIntReq(ow.writeValueAsString(ifscRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.ifsc_check.success.price");
							}else {
								price=resProp.getString("com.karza.ifsc_check.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										ifscResponseCall = info.getResponse().replaceAll("status-code", "status_code");

										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										ifscResponse.setPayload(om.readValue(ifscResponseCall, IFSCResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (ifscResponse.getPayload() != null) 
										{

											if (ifscResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(ifscResponse.getPayload().getStatus_code());

											}
										}

										ifscResponse.getPayload().setStatus_msg(statusmsg);

										if (ifscResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(ifscResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + ifscResponse.getPayload().getStatus_code());
										}
										else if (ifscResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(ifscResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + ifscResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(ifscResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + ifscResponse.getPayload().getStatus_code());
										}
									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}






				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		ifscResponse.setMsgInfo(msgInfo);
		ifscResponse.setHeader(ifscRequest.getHeader());
		return ifscResponse;
	}


	@Override
	public HSNResponse hsnRequestService(HSNRequest hsnRequest, KarzaReqRes reqRes){
		HSNResponse hsnResponse=new HSNResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.hsn.url");
		//		String urlSave = "https://testapi.karza.in/v2/dgft";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.hsn.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (hsnRequest != null) {

				if (hsnRequest.getPayload() != null 
						&& !hsnRequest.getPayload().getConsent().equals("") && hsnRequest.getPayload().getConsent() != null
						&& !hsnRequest.getPayload().getHsCode().equals("") && hsnRequest.getPayload().getHsCode() != null) {

					boolean result = checkValidation(hsnRequest.getHeader());
					if (result == true) {
						String hsnResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"d647ab5a-1888-11e8-b6e1-55bf7db13bba\", \"result\": {\"policyLink\": \"http://dgftcom.nic.in/licasp/itchs2012/01foot.pdf\", \"chapterNotes\": \"http://dgftcom.nic.in/licasp/itchs2012/01Head.pdf\", \"headingDesc\": \"OTHER LIVE ANIMALS\", \"sectionDesc\": \"LIVE ANIMALS, ANIMAL PRODUCTS\", \"itemDesc2\": \"Primates\", \"itemDesc1\": \"\", \"chapterNo\": \"01\", \"policy\": \"Restricted\", \"chapterDesc\": \"Live animals\", \"policyConditions\": \"Subject to Policy Condition 6 of this Chapter.\", \"sectionNo\": \"I\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(hsnRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(hsnRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.hsn_check.success.price");
							}else {
								price=resProp.getString("com.karza.hsn_check.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										hsnResponseCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										hsnResponse.setPayload(om.readValue(hsnResponseCall, HSNResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (hsnResponse.getPayload() != null) 
										{

											if (hsnResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(hsnResponse.getPayload().getStatus_code());

											}
										}

										hsnResponse.getPayload().setStatus_msg(statusmsg);

										if (hsnResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(hsnResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + hsnResponse.getPayload().getStatus_code());
										}
										else if (hsnResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(hsnResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + hsnResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(hsnResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + hsnResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}		

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		hsnResponse.setMsgInfo(msgInfo);
		hsnResponse.setHeader(hsnRequest.getHeader());
		return hsnResponse;
	}


	@Override
	public UdyogAadharResponse udyogAadharRequestService(final UdyogAadharRequest udyogAadharRequest, KarzaReqRes reqRes) {
		UdyogAadharResponse udyogAadharResponse = new UdyogAadharResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.udyogAadharNum.url");
		//		String urlSave,key = "https://testapi.karza.in/v2/uam";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.udyogaadhar.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (udyogAadharRequest != null) {

				if (udyogAadharRequest.getPayload() != null 
						&& udyogAadharRequest.getPayload().getAadhar() != null && !udyogAadharRequest.getPayload().getAadhar().equals("")
						&& !udyogAadharRequest.getPayload().getUan().equals("") && udyogAadharRequest.getPayload().getUan() != null
						&& !udyogAadharRequest.getPayload().getConsent().equals("") && udyogAadharRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(udyogAadharRequest.getHeader());
					if (result == true) {
						String responseUdyogAadharCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"3668bc7e-1928-11e8-a46d-8b9f7c60dbbb\", \"result\": {\"pin\": \"360002\", \"DateOFCommencement\": \"21/11/1995\", \"aadhar\": \"690907314709\", \"district\": \"RAJKOT\", \"DistrictIndustryCentre\": \"RAJKOT\", \"NameofEnterPrise\": \"DIVINE PUMPS\", \"NumberofEmp\": \"7\", \"state\": \"GUJARAT\", \"OwnerName\": \"NITINBHAI DAYABHAI KHUNT\", \"MajorActivity\": \"MANUFACTURING\", \"email\": \"divinepumps@gmail.com\", \"pan\": \"\", \"ifsc\": \"ICIC0000728\", \"mobile\": \"9825075435\", \"address\": \"DHEBAR ROAD SOUTH  ATIKA  OPP:-STREET OF OCTROI NAKA B/H. ORIENT TRANSPORT\", \"social_category\": \"GENERAL\", \"AccountNumber\": \"072805000544\", \"EntType\": \"Micro\", \"gender\": \"\", \"type_of_org\": \"Proprietary\", \"Investment\": \"7(Rs. In Lakhs) \", \"NIC_Digit_Code\": \"27900 - Manufacture of other electrical equipment\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(udyogAadharRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;


							reqRes.setIntReq(ow.writeValueAsString(udyogAadharRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.udyog_aadhar.success.price");
							}else {
								price=resProp.getString("com.karza.udyog_aadhar.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseUdyogAadharCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("pin", "pin")
												.replaceAll("DateOFCommencement", "dateOFCommencement")
												.replaceAll("aadhar", "aadhar")
												.replaceAll("district", "district")
												.replaceAll("DistrictIndustryCentre", "districtIndustryCentre")
												.replaceAll("NameofEnterPrise", "nameOfEnterPrise")
												.replaceAll("NumberofEmp", "numberOfEmp")
												.replaceAll("state", "state")
												.replaceAll("OwnerName", "ownerName")
												.replaceAll("MajorActivity", "majorActivity")
												.replaceAll("email", "email")
												.replaceAll("pan", "pan")
												.replaceAll("ifsc", "ifsc")
												.replaceAll("mobile", "mobile")
												.replaceAll("address", "address")
												.replaceAll("social_category", "socialCategory")
												.replaceAll("AccountNumber", "accountNumber")
												.replaceAll("EntType", "entType")
												.replaceAll("gender", "gender")
												.replaceAll("type_of_org", "typeOfOrg")
												.replaceAll("Investment", "investment")
												.replaceAll("NIC_Digit_Code", "nicDigitCode");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										udyogAadharResponse.setPayload(om.readValue(responseUdyogAadharCall, UdyogAadharResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (udyogAadharResponse.getPayload() != null) 
										{

											if (udyogAadharResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(udyogAadharResponse.getPayload().getStatus_code());

											}
										}

										udyogAadharResponse.getPayload().setStatus_msg(statusmsg);

										if (udyogAadharResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(udyogAadharResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + udyogAadharResponse.getPayload().getStatus_code());
										}
										else if (udyogAadharResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(udyogAadharResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + udyogAadharResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(udyogAadharResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + udyogAadharResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		udyogAadharResponse.setMsgInfo(msgInfo);
		udyogAadharResponse.setHeader(udyogAadharRequest.getHeader());
		return udyogAadharResponse;
	}

	@Override
	public GstIdentificationResponse gstIdentificationRequestService(final GstIdentificationRequest gstIdentificationRequest,KarzaReqRes reqRes) {
		GstIdentificationResponse gstIdentificationResponse = new GstIdentificationResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.gstIdentification.url");
		//		String urlSave,key = "https://testapi.karza.in/v2/gst";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.gstIden.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (gstIdentificationRequest != null) {

				if (gstIdentificationRequest.getPayload() != null 
						&& !gstIdentificationRequest.getPayload().getConsent().equals("") && gstIdentificationRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(gstIdentificationRequest.getHeader());
					if (result == true) {
						String responseGstIdentificationCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();


						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"cb8c0af1-192f-11e8-a309-8d175d990663\", \"result\": [{\"emailId\": \"RIL.MHGST@ril.com\", \"applicationStatus\": \"APR\", \"mobNum\": \"9867147765\", \"pan\": \"AAACR5055K\", \"gstinRefId\": \"T270000013157\", \"regType\": \"V\", \"gstinId\": \"27AAACR5055K1Z7\", \"registrationName\": \"RELIANCE INDUSTRIES LIMITED\", \"tinNumber\": \"27110386481V\"}], \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(gstIdentificationRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(gstIdentificationRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.gst.success.price");
							}else {
								price=resProp.getString("com.karza.gst.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseGstIdentificationCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										gstIdentificationResponse.setPayload(om.readValue(responseGstIdentificationCall, GstIdentificationResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (gstIdentificationResponse.getPayload() != null) 
										{

											if (gstIdentificationResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(gstIdentificationResponse.getPayload().getStatus_code());

											}
										}

										gstIdentificationResponse.getPayload().setStatus_msg(statusmsg);

										if (gstIdentificationResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(gstIdentificationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + gstIdentificationResponse.getPayload().getStatus_code());
										}
										else if (gstIdentificationResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(gstIdentificationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + gstIdentificationResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(gstIdentificationResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + gstIdentificationResponse.getPayload().getStatus_code());
										}
									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		gstIdentificationResponse.setMsgInfo(msgInfo);
		gstIdentificationResponse.setHeader(gstIdentificationRequest.getHeader());
		return gstIdentificationResponse;
	}


	@Override
	public LpgResponse lpgRequestService(final LpgRequest lpgRequest,KarzaReqRes reqRes) {
		LpgResponse lpgResponse = new LpgResponse();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave = resProp.getString("com.karza.lpg.url");
		String key = resProp.getString("com.karza.key");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.lpgv2.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (lpgRequest != null) {
				if (lpgRequest.getPayload() != null && lpgRequest.getPayload().getLpg_id() != null
						&& !lpgRequest.getPayload().getLpg_id().equals("")
						//&& !lpgRequest.getPayload().getName().equals("") && lpgRequest.getPayload().getName() != null
						&& !lpgRequest.getPayload().getConsent().equals("")
						&& lpgRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(lpgRequest.getHeader());
					if (result == true) {
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"434e46f5-2100-11e8-a979-4b603c20877e\", \"result\": {\"lpg\": {\"status\": \"Active\", \"SubsidizedRefillConsumed\": \"11\", \"ConsumerAddress\": \"PLNO 125PUSHPA AJANTA NR NANDIVALI TELEPHONE EX THANA MAHARASHTRA Maharas 421201\", \"pin\": \"\", \"BankName\": \"Not Applicable\", \"DistributorName\": \"SHIVSHAKTI BHARATGAS AGENCY\", \"IFSCCode\": \"Not Applicable\", \"city/town\": \"\", \"AadhaarNo\": \"**** **** 7460\", \"ConsumerContact\": \"******9349\", \"ApproximateSubsidyAvailed\": \"0.0\", \"ConsumerEmail\": \"Not Available\", \"ConsumerNo\": \"50431060\", \"ConsumerName\": \"MRS SUSHAMA M SHIRHATTI\", \"GivenUpSubsidy\": \"No\", \"BankAccountNo\": \"Not Applicable\", \"TotalRefillConsumed\": \"\", \"LastBookingDate\": \"24-Feb-2018\", \"DistributorCode\": \"186834\", \"DistributorAddress\": \"SHOP NO 1 MANUSMRITI BLDG GR FLOOR TUKARAM NAGAR, 421201\"}, \"name\": {\"score\": 0.20263063839452727, \"match\": false}}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(lpgRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(lpgRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.lpg.success.price");
							}else {
								price=resProp.getString("com.karza.lpg.failure.price");
							}
							reqRes.setPrice(price);


						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
						}

						if (info.getResponseCode() == 200) {
							msgInfo.setCode(""+info.getResponseCode());
							if (!info.getResponse().toString().contains("Invalid Input")) {
								if (!info.getResponse().toString().contains("Max retries exceeded")) {

									try {
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										lpgResponse.setPayload(om.readValue(replaceAttributes(info.getResponse()),
												LpgResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (lpgResponse.getPayload() != null) {

											if (lpgResponse.getPayload().getStatus_code() != 0) {
												statusmsg = getStatusMsg(lpgResponse.getPayload().getStatus_code());

											}
										}

										lpgResponse.getPayload().setStatus_msg(statusmsg);

										if (lpgResponse.getPayload().getStatus_code() == 101) {
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										} else if (lpgResponse.getPayload().getStatus_code() == 102) {
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										} else {

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										}
									} catch (Exception ex) {
										MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
										msgInfo=httpMsg;

										// ErrorResponse errorResponse =
										// om.readValue(info.getResponse(),ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);
								}

							} else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}

						}

						else {
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(info.getResponse(),ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}
			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}
		lpgResponse.setMsgInfo(msgInfo);
		lpgResponse.setHeader(lpgRequest.getHeader());
		return lpgResponse;
	}




	public VoterResponse voterRequestService(final VoterRequest voterRequest, KarzaReqRes reqRes) {
		VoterResponse voterResponse = new VoterResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.voter.url");
		//			String urlSave,key = "https://testapi.karza.in/v2/voter";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.voter.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (voterRequest != null) {

				if (voterRequest.getPayload() != null 
						&& voterRequest.getPayload().getEpic_no() != null && !voterRequest.getPayload().getEpic_no().equals("")
						&& !voterRequest.getPayload().getConsent().equals("") && voterRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(voterRequest.getHeader());
					if (result == true) {
						String responseVoterCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"9a74d38b-18a8-11e8-b22e-4370ee4c500c\", \"result\": {\"ps_lat_long\": \"19.13799000,72.86913000\", \"rln_name_v1\": \"\\u092a\\u094b\\u0915\\u094b\\u091f\\u0947 \\u0928\\u091f\\u0930\\u093e\\u091c\\u0928\", \"rln_name_v2\": \"\", \"rln_name_v3\": \"\", \"part_no\": \"141\", \"rln_type\": \"F\", \"section_no\": \"1\", \"id\": \"SIQ3830379\", \"name_v1\": \"\\u092a\\u094d\\u0930\\u0926\\u093f\\u092a \\u092a\\u094b\\u0915\\u094b\\u091f\\u0947 \\u0928\\u091f\\u0930\\u093e\\u091c\\u0928\", \"rln_name\": \"Pookote Natarajan\", \"district\": \"Mumbai Suburban\", \"last_update\": \"\", \"state\": \"Maharashtra\", \"ac_no\": \"158\", \"slno_inpart\": \"143\", \"ps_name\": \"\", \"pc_name\": \"Mumbai North-West\", \"house_no\": \"3-B-163\", \"name\": \"Pradeep Pookote Natarajan\", \"part_name\": \"Mahakali Gunfa Road West Andheri [purv]mumbai 400 093\", \"dob\": \"09-08-1972\", \"gender\": \"M\", \"age\": 45, \"ac_name\": \"Jogeshwari East\", \"epic_no\": \"SIQ3830379\", \"st_code\": \"S13\", \"name_v3\": \"\", \"name_v2\": \"\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(voterRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(voterRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.voter.success.price");
							}else {
								price=resProp.getString("com.karza.voter.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseVoterCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										voterResponse.setPayload(om.readValue(responseVoterCall, VoterResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (voterResponse.getPayload() != null) 
										{

											if (voterResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(voterResponse.getPayload().getStatus_code());

											}
										}

										voterResponse.getPayload().setStatus_msg(statusmsg);

										if (voterResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(voterResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + voterResponse.getPayload().getStatus_code());
										}
										else if (voterResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(voterResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + voterResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(voterResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + voterResponse.getPayload().getStatus_code());
										}


									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		voterResponse.setMsgInfo(msgInfo);
		voterResponse.setHeader(voterRequest.getHeader());
		return voterResponse;
	}



	public NameSimilarityResponse nameSimilarityRequestService(final NameSimilarityRequest nameSimilarityRequest,  KarzaReqRes reqRes) {
		NameSimilarityResponse nameSimilarityResponse = new NameSimilarityResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.name.url");
		//			String urlSave = "https://testapi.karza.in/v2/name";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.namesimilarity.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (nameSimilarityRequest != null) {

				if (nameSimilarityRequest.getPayload() != null 
						&& nameSimilarityRequest.getPayload().getName1() != null && !nameSimilarityRequest.getPayload().getName1().equals("")
						&& nameSimilarityRequest.getPayload().getName2() != null && !nameSimilarityRequest.getPayload().getName2().equals("")
						&& nameSimilarityRequest.getPayload().getType() != null && !nameSimilarityRequest.getPayload().getType().equals("")){
					boolean result = checkValidation(nameSimilarityRequest.getHeader());
					if (result == true) {
						String responseNameSimilarityCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();


						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"a2b354cf-1b08-11e8-9d6f-2bee12c004ab\", \"result\": 0.71397849462365581, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(nameSimilarityRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(nameSimilarityRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.name_similarity.success.price");
							}else {
								price=resProp.getString("com.karza.name_similarity.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseNameSimilarityCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										nameSimilarityResponse.setPayload(om.readValue(responseNameSimilarityCall, NameSimilarityResponsePayload.class));
										String statusmsg = "No status code received from third party";
										if (nameSimilarityResponse.getPayload() != null) 
										{

											if (nameSimilarityResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(nameSimilarityResponse.getPayload().getStatus_code());

											}
										}

										nameSimilarityResponse.getPayload().setStatus_msg(statusmsg);

										if (nameSimilarityResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(nameSimilarityResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + nameSimilarityResponse.getPayload().getStatus_code());
										}
										else if (nameSimilarityResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(nameSimilarityResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + nameSimilarityResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(nameSimilarityResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + nameSimilarityResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		nameSimilarityResponse.setMsgInfo(msgInfo);
		nameSimilarityResponse.setHeader(nameSimilarityRequest.getHeader());
		return nameSimilarityResponse;
	}




	public CompSearchResponse compSearchRequestService(final CompSearchRequest compSearchRequest,  KarzaReqRes reqRes) {
		CompSearchResponse compSearchResponse = new CompSearchResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.compsearch.url");
		//			String urlSave,key = "https://testapi.karza.in/v2/compsearch";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.compserch.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (compSearchRequest != null) {

				if (compSearchRequest.getPayload() != null 
						&& compSearchRequest.getPayload().getCompany_name() != null && !compSearchRequest.getPayload().getCompany_name().equals("")
						&& !compSearchRequest.getPayload().getConsent().equals("") && compSearchRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(compSearchRequest.getHeader());
					if (result == true) {
						String responseCompSearchCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"de15e7b5-1922-11e8-8a14-41f0eb3ec968\", \"result\": {\"result\": [{\"score\": 1.0, \"cin\": \"U74120MH2015PTC265316\", \"comapany_name\": \"KARZA TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.933598224876116, \"cin\": \"U72200OR2007PTC009689\", \"comapany_name\": \"KARAK TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.8652317668181803, \"cin\": \"U32200MH2005PTC152807\", \"comapany_name\": \"KARSH TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.6385158377347979, \"cin\": \"U72900UP2017PTC097966\", \"comapany_name\": \"KARZAM TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.4892220883030779, \"cin\": \"U72900PB2008PTC031888\", \"comapany_name\": \"KARMA TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.4892220883030779, \"cin\": \"U72200WB2011PTC164494\", \"comapany_name\": \"KARAN TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.47861795153364667, \"cin\": \"U31200TZ2015PTC021657\", \"comapany_name\": \"KARSAN TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.47861795153364667, \"cin\": \"U72900DL2015PTC287394\", \"comapany_name\": \"EKARSA TECHNOLOGIES PRIVATE LIMITED\"}, {\"score\": 0.47861795153364667, \"cin\": \"U72900DL1999PTC100529\", \"comapany_name\": \"KARUNA TECHNOLOGIES PVT LTD\"}, {\"score\": 0.4686544507230888, \"cin\": \"U51909DL2004PLC130555\", \"comapany_name\": \"KASA TECHNOLOGIES LIMITED\"}]}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(compSearchRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							//reqRes.setUniqueId(uId);
							reqRes.setIntReq(ow.writeValueAsString(compSearchRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.company_search.success.price");
							}else {
								price=resProp.getString("com.karza.company_search.failure.price");
							}
							reqRes.setPrice(price);
							//int count = DBConnectionKarza.updateInternalKarzaReqRes(reqRes);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseCompSearchCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										compSearchResponse.setPayload(om.readValue(responseCompSearchCall, CompSearchResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (compSearchResponse.getPayload() != null) 
										{

											if (compSearchResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(compSearchResponse.getPayload().getStatus_code());

											}
										}

										compSearchResponse.getPayload().setStatus_msg(statusmsg);

										if (compSearchResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(compSearchResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + compSearchResponse.getPayload().getStatus_code());
										}
										else if (compSearchResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(compSearchResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + compSearchResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(compSearchResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + compSearchResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//	msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		compSearchResponse.setMsgInfo(msgInfo);
		compSearchResponse.setHeader(compSearchRequest.getHeader());
		return compSearchResponse;
	}





	public CompanyLLPCINLookUpResponse companyLLPCINLookUpRequestService(CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest, KarzaReqRes reqRes){
		CompanyLLPCINLookUpResponse companyLLPCINLookUpResponse=new CompanyLLPCINLookUpResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.cinlookup.url");
		//			String urlSave = "https://testapi.karza.in/v2/cinlookup";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.cmplookup.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (companyLLPCINLookUpRequest != null) {

				if (companyLLPCINLookUpRequest.getPayload() != null 
						&& companyLLPCINLookUpRequest.getPayload().getCompany_name() != null && !companyLLPCINLookUpRequest.getPayload().getCompany_name().equals("")
						&& !companyLLPCINLookUpRequest.getPayload().getConsent().equals("") && companyLLPCINLookUpRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(companyLLPCINLookUpRequest.getHeader());
					if (result == true) {
						String responseCompanyLLPCINLookUpResponseCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();


						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"5fe3ca88-1632-11e8-bf98-2da0d06b4b64\", \"result\": [{\"companyName\": \"RELIANCE CELLULOSE PRODUCTS LIMITED\", \"companyID\": \"U15429AP1978PLC002337\"}, {\"companyName\": \"RELIANCE DISTRIBUTORS AND MARKETING AGENCIES PRIVATE LIMITED\", \"companyID\": \"U52520TG1979PTC002575\"}, {\"companyName\": \"RELIANCE CORUGERATION PVT LTD\", \"companyID\": \"U21022AP1990PTC011263\"}, {\"companyName\": \"RELIANCE COIR LIMITED\", \"companyID\": \"U52520TG1995PLC021000\"}, {\"companyName\": \"RELIANCE CYBERTECH (VIZAG) PRIVATE LIMITED\", \"companyID\": \"U72200AP1998PTC028996\"}, {\"companyName\": \"RELIANCE BATTERIES PRIVATE LIMITED\", \"companyID\": \"U31402TG2002PTC038339\"}, {\"companyName\": \"RELIANCE ASSAM PETROCHEMICALS LTD\", \"companyID\": \"U24111AS1994PLC004277\"}, {\"companyName\": \"RELIANCE CLINICAL LABORATORY PVT LTD\", \"companyID\": \"U85110AS1996PTC004816\"}, {\"companyName\": \"RELIANCE CAPITAL LTD\", \"companyID\": \"L65910MH1986PLC165645\"}, {\"companyName\": \"RELIANCE ELASTOMERS PVT LTD\", \"companyID\": \"U24130GJ1991PTC016096\"}], \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(companyLLPCINLookUpRequest.getPayload()), urlSave,key);
						}



						try {
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(companyLLPCINLookUpRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.company_llp_lookup.success.price");
							}else {
								price=resProp.getString("com.karza.company_llp_lookup.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseCompanyLLPCINLookUpResponseCall = info.getResponse().replaceAll("status-code", "status_code");

										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										companyLLPCINLookUpResponse.setPayload(om.readValue(responseCompanyLLPCINLookUpResponseCall, CompanyLLPCINLookUpResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (companyLLPCINLookUpResponse.getPayload() != null) 
										{

											if (companyLLPCINLookUpResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(companyLLPCINLookUpResponse.getPayload().getStatus_code());

											}
										}

										companyLLPCINLookUpResponse.getPayload().setStatus_msg(statusmsg);

										if (companyLLPCINLookUpResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(companyLLPCINLookUpResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + companyLLPCINLookUpResponse.getPayload().getStatus_code());
										}
										else if (companyLLPCINLookUpResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(companyLLPCINLookUpResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + companyLLPCINLookUpResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(companyLLPCINLookUpResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + companyLLPCINLookUpResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}






				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		companyLLPCINLookUpResponse.setMsgInfo(msgInfo);
		companyLLPCINLookUpResponse.setHeader(companyLLPCINLookUpRequest.getHeader());
		return companyLLPCINLookUpResponse;
	}




	public AddressResponse addressRequestService(AddressRequest addressRequest, KarzaReqRes reqRes) {

		AddressResponse addressResponse = new AddressResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.address.url");
		//				String urlSave = "https://testapi.karza.in/v2/address";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.addessmatching.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (addressRequest != null) {

				if (addressRequest.getPayload() != null 
						&& addressRequest.getPayload().getAddress1() != null && !addressRequest.getPayload().getAddress1().equals("")
						&& addressRequest.getPayload().getAddress2() != null && !addressRequest.getPayload().getAddress2().equals("")){
					boolean result = checkValidation(addressRequest.getHeader());
					if (result == true) {
						String responseAddressCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"2190a3e5-1952-11e8-9b2d-5ba62212253e\", \"result\": {\"address1\": {\"Building\": \"\", \"Pin\": 411015, \"District\": \"PUNE\", \"Floor\": \"\", \"House\": \"52\", \"locality\": \"\", \"State\": \"MAHARASHTRA\", \"Complex\": \"\", \"Untagged\": \"ROAD 3 NEAR DATA MANDIR\", \"C/O\": \"\", \"Street\": \"\"}, \"address2\": {\"Building\": \"\", \"Pin\": 411015, \"District\": \"PUNE\", \"Floor\": \"\", \"House\": \"52\", \"locality\": \"\", \"State\": \"MAHARASHTRA\", \"Complex\": \"\", \"Untagged\": \"ROAD 3\", \"C/O\": \"\", \"Street\": \"\"}, \"score\": 1.0, \"match\": \"True\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(addressRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;

							reqRes.setIntReq(ow.writeValueAsString(addressRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.address_matching.success.price");
							}else {
								price=resProp.getString("com.karza.address_matching.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseAddressCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("Building", "building")
												.replaceAll("Pin", "pin")
												.replaceAll("District", "district")
												.replaceAll("Floor", "floor")
												.replaceAll("House", "house")
												.replaceAll("locality", "locality")
												.replaceAll("State", "state")
												.replaceAll("Complex", "complex")
												.replaceAll("Untagged", "untagged")
												.replaceAll("C/O", "co")
												.replaceAll("Street", "street");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										addressResponse.setPayload(om.readValue(responseAddressCall, AddressResponsePayload.class));
										String statusmsg = "No status code received from third party";
										if (addressResponse.getPayload() != null) 
										{

											if (addressResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(addressResponse.getPayload().getStatus_code());

											}
										}

										addressResponse.getPayload().setStatus_msg(statusmsg);

										if (addressResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(addressResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + addressResponse.getPayload().getStatus_code());
										}
										else if (addressResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(addressResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + addressResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(addressResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + addressResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		addressResponse.setMsgInfo(msgInfo);
		addressResponse.setHeader(addressRequest.getHeader());
		return addressResponse;
	}





	public MCASignatureResponse mcaSignaureRequestService(final MCASignatureRequest mcaSignatureRequest, KarzaReqRes reqRes) {
		MCASignatureResponse mcaSignatureResponse=new MCASignatureResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.mca.singanature.url");
		//			String urlSave = "https://testapi.karza.in/v2/mca-signatories";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.mcasignature.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (mcaSignatureRequest != null) {

				if (mcaSignatureRequest.getPayload() != null 
						&& mcaSignatureRequest.getPayload().getCin() != null && !mcaSignatureRequest.getPayload().getCin().equals("")
						&& !mcaSignatureRequest.getPayload().getConsent().equals("") && mcaSignatureRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(mcaSignatureRequest.getHeader());
					if (result == true) {
						String responseMcaSignaureCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"fc12eaec-1ade-11e8-b307-f1e8511f603e\", \"result\": [{\"date_of_appointment\": \"28/04/2010\", \"designation\": \"Designated Partner\", \"dsc_expiry_date\": \"05/10/2019\", \"address\": \"NIRMAL ANAND CO OP HSG SOC.  FLAT NO. 5  A-WING  2ND FLOOR J.P.ROAD  ANDHERI WEST MUMBAI 400058\", \"DIN/DPIN/PAN\": \"05005591\", \"full_name\": \"GADA JITENDRA RAGHAVJI\", \"wheather_dsc_registered\": \"Yes\"}, {\"date_of_appointment\": \"28/04/2010\", \"designation\": \"Designated Partner\", \"dsc_expiry_date\": \"05/10/2019\", \"address\": \"VEERA DESAI ROAD  ADARSH NAGAR 28/445 ANDHERI WEST MUMBAI 400055\", \"DIN/DPIN/PAN\": \"05005933\", \"full_name\": \"GADA RAMESHCHANDRA RAGHAVJI\", \"wheather_dsc_registered\": \"Yes\"}], \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(mcaSignatureRequest.getPayload()), urlSave,key);
						}
						// mcaSignatureResponse=om.readValue(res, MCASignatureResponse.class);
						try {
							String price=null;

							reqRes.setIntReq(ow.writeValueAsString(mcaSignatureRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{

								price=resProp.getString("com.karza.mca_signature.success.price");
							}else {
								price=resProp.getString("com.karza.mca_signature.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseMcaSignaureCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("DIN/DPIN/PAN", "din_dpin_pan");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										mcaSignatureResponse.setPayload(om.readValue(responseMcaSignaureCall, MCASignatureResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (mcaSignatureResponse.getPayload() != null) 
										{

											if (mcaSignatureResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(mcaSignatureResponse.getPayload().getStatus_code());

											}
										}

										mcaSignatureResponse.getPayload().setStatus_msg(statusmsg);

										if (mcaSignatureResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(mcaSignatureResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + mcaSignatureResponse.getPayload().getStatus_code());
										}
										else if (mcaSignatureResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(mcaSignatureResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + mcaSignatureResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(mcaSignatureResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + mcaSignatureResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}






				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		mcaSignatureResponse.setMsgInfo(msgInfo);
		mcaSignatureResponse.setHeader(mcaSignatureRequest.getHeader());
		return mcaSignatureResponse;
	}


	@Override
	public ICWAIMembershipResponse icwaiMembershipRequestService( ICWAIMembershipRequest icwaiMembershipRequest, KarzaReqRes reqRes) {
		ICWAIMembershipResponse icwaiMembershipResponse = new ICWAIMembershipResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.icwaim.url");
		//			String urlSave = "https://testapi.karza.in/v2/icwaim";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.icwaimember.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (icwaiMembershipRequest != null) {

				if (icwaiMembershipRequest.getPayload() != null 
						&& icwaiMembershipRequest.getPayload().getMembership_no() != null && !icwaiMembershipRequest.getPayload().getMembership_no().equals("")
						&& !icwaiMembershipRequest.getPayload().getConsent().equals("") && icwaiMembershipRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(icwaiMembershipRequest.getHeader());
					if (result == true) {
						String responseICWAIMembershipCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();


						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{ \"status-code\": 101, \"request_id\": \"73cdbde2-80f7-11e7-8f0c-e7e769f70bd1\", \"result\": { \"MemshipDt\": \"18/12/1994\", \"Chapter\": \"W.I.R.C. OF ICWAI\", \"Retired\": \"N\", \"dob\": \"21/10/1957\", \"Mname\": \"\", \"ProtFirmName\": \"Richard Correa & Co.\", \"ValidUpDt\": \"31/03/2017\", \"MemCategory\": \"F\", \"Fname\": \"Richard\", \"gender\": \"M\", \"SrName\": \"Correa\", \"EffectiveDt\": \"01/06/1998\", \"MemRegion\": \"WESTERN\", \"CrtEmployer\": \"\", \"FirmEftDt\": \"\", \"CancellationDt\": \"\" } }";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(icwaiMembershipRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;

							reqRes.setIntReq(ow.writeValueAsString(icwaiMembershipRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.icwai_membership.success.price");
							}else {
								price=resProp.getString("com.karza.icwai_membership.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
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
										responseICWAIMembershipCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("MemshipDt", "memberDt")
												.replaceAll("Chapter", "chapter")
												.replaceAll("Retired", "retired")
												.replaceAll("dob", "dob")
												.replaceAll("Mname", "mName")
												.replaceAll("ProtFirmName", "protFirmName")
												.replaceAll("ValidUpDt", "validUpDt")
												.replaceAll("MemCategory", "menCategory")
												.replaceAll("Fname", "fName")
												.replaceAll("gender", "gender")
												.replaceAll("SrName", "srName")
												.replaceAll("EffectiveDt", "effectiveDt")
												.replaceAll("MemRegion", "memRegion")
												.replaceAll("CrtEmployer", "crtEmployer")
												.replaceAll("FirmEftDt", "firmEftDt")
												.replaceAll("CancellationDt", "cancellationDt");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										icwaiMembershipResponse.setPayload(om.readValue(responseICWAIMembershipCall, ICWAIMembershipResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (icwaiMembershipResponse.getPayload() != null) 
										{

											if (icwaiMembershipResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(icwaiMembershipResponse.getPayload().getStatus_code());

											}
										}

										icwaiMembershipResponse.getPayload().setStatus_msg(statusmsg);

										if (icwaiMembershipResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(icwaiMembershipResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icwaiMembershipResponse.getPayload().getStatus_code());
										}
										else if (icwaiMembershipResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(icwaiMembershipResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icwaiMembershipResponse.getPayload().getStatus_code());
										}
										else
										{

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(icwaiMembershipResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + icwaiMembershipResponse.getPayload().getStatus_code());
										}



									} catch (Exception ex) {
										// ErrorResponse errorResponse =
										// om.readValue(responseDlCall,ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);

								}

							}

							else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							// ErrorResponse errorResponse =
							// om.readValue(responseDlCall,ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							//msgInfo.setInternalCode(StringConstants.C_500.toString());
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}

				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}

			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}

		icwaiMembershipResponse.setMsgInfo(msgInfo);
		icwaiMembershipResponse.setHeader(icwaiMembershipRequest.getHeader());
		return icwaiMembershipResponse;
	}





//here clean
	@Override
	public RCSearchResponse rcSearchRequestService(final RCSearchRequest rcSearchRequest,KarzaReqRes reqRes) 
	{
		RCSearchResponse rcSearchResponse = new RCSearchResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.rcsearch.url");
		Boolean checkflag=false;
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.rcsearch.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try 
		{
			if (rcSearchRequest != null)
			{
				if(rcSearchRequest.getPayload() != null )
				{
					if(rcSearchRequest.getPayload().getEngine_no() != null && !rcSearchRequest.getPayload().getEngine_no().equals(""))
					{
						checkflag=true;
					}
					else if (rcSearchRequest.getPayload().getChassis_no() != null && !rcSearchRequest.getPayload().getChassis_no().equals("")) 
					{
						checkflag=true;
					}
				}

				if (rcSearchRequest.getPayload() != null 
						&& rcSearchRequest.getPayload().getConsent() != null && !rcSearchRequest.getPayload().getConsent().equals("")
						&& checkflag==true)
				{
					boolean result = checkValidation(rcSearchRequest.getHeader());
					if (result == true) 
					{
						String responseRcSearchCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y"))
						{
							String	res="{\"request_id\": \"4d72215d-1960-11e8-a9a2-f35c86bb4eae\", \"result\": {\"rc_manu_month_yr\": \"2016\", \"rc_maker_model\": \"PULSAR 150\", \"rc_eng_no\": \"DHZRGK00796\", \"rc_owner_name\": \"RADHE LAL  SHARMA\", \"rc_vh_class_desc\": \"MOTOR CYCLE\", \"rc_maker_desc\": \"BAJAJ AUTO LTD\", \"rc_chasi_no\": \"MD2A11CZ0GRK00720\", \"rc_regn_no\": \"MP07MY2953\", \"rc_regn_dt\": \"23-01-2016\", \"rc_color\": \"S. BLUE\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(rcSearchRequest.getPayload()), urlSave,key);
						}
						try
						{
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(rcSearchRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.vehicle_rc_search.success.price");
							}
							else 
							{
								price=resProp.getString("com.karza.vehicle_rc_search.failure.price");
							}
							reqRes.setPrice(price);
						}
						catch (Exception e) 
						{
							logger.error("Error while updating karza internal req res:" + e);
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
										responseRcSearchCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("rc_regn_no", "rcRegnNo")
												.replaceAll("rc_owner_name", "rcOwnerName")
												.replaceAll("rc_eng_no", "rcEngNo")
												.replaceAll("rc_chasi_no", "rcChasiNo")
												.replaceAll("rc_maker_desc", "rcMakerDesc")
												.replaceAll("rc_maker_model", "rcMakerModel")
												.replaceAll("rc_regn_dt", "rcRegnDt")
												.replaceAll("rc_vh_class_desc", "rcVhClassDesc")
												.replaceAll("rc_color", "rcColor")
												.replaceAll("rc_manu_month_yr", "rcManuMonthYr");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										rcSearchResponse.setPayload(om.readValue(responseRcSearchCall, RCSearchResponsePayload.class));
										String statusmsg = "No status code received from third party";
										if (rcSearchResponse.getPayload() != null) 
										{
											if (rcSearchResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(rcSearchResponse.getPayload().getStatus_code());
											}
										}
										rcSearchResponse.getPayload().setStatus_msg(statusmsg);
										if (rcSearchResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(rcSearchResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + rcSearchResponse.getPayload().getStatus_code());
										}
										else if (rcSearchResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(rcSearchResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + rcSearchResponse.getPayload().getStatus_code());
										}
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(rcSearchResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + rcSearchResponse.getPayload().getStatus_code());
										}
									} 
									catch (Exception ex) 
									{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
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
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
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
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}

		rcSearchResponse.setMsgInfo(msgInfo);
		rcSearchResponse.setHeader(rcSearchRequest.getHeader());
		return rcSearchResponse;
	}

	@Override
	public BankAccountResponse bankAccountRequestService(final BankAccountRequest bankAccountRequest, KarzaReqRes rq) 
	{
		BankAccountResponse bankAccountResponse = new BankAccountResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.bankAccount.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.bankaccount.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try 
		{
			if (bankAccountRequest != null) 
			{

				if (bankAccountRequest.getPayload() != null 
						&& bankAccountRequest.getPayload().getIfsc() != null && !bankAccountRequest.getPayload().getIfsc().equals("")
						&& bankAccountRequest.getPayload().getAccountNumber() != null && !bankAccountRequest.getPayload().getAccountNumber().equals("")
						&& bankAccountRequest.getPayload().getConsent() != null && !bankAccountRequest.getPayload().getConsent().equals(""))
				{
					boolean result = checkValidation(bankAccountRequest.getHeader());
					if (result == true) 
					{
						String responseBankAccountCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y"))
						{
							//for Bank Account
							String	res="{\"request_id\": \"0c58333d-21c9-11e8-8b1d-9710c23265c7\", \"result\": {\"bankTxnStatus\": \"success\", \"accountNumber\": \"46738920294509\", \"ifsc\": \"HDFC0000975\", \"accountName\": \"DEEPAK GILL\", \"bankResponse\": \"Transaction Successful\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(bankAccountRequest.getPayload()), urlSave,key);
						}
						try 
						{
							String price=null;
							rq.setIntReq(ow.writeValueAsString(bankAccountRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.bank_account.success.price");
							}else {
								price=resProp.getString("com.karza.bank_account.failure.price");
							}
							rq.setPrice(price);
						}
						catch (Exception e) 
						{
							logger.error("Error while setting karza internal req res:" + e);
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
										responseBankAccountCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										bankAccountResponse.setPayload(om.readValue(responseBankAccountCall, BankAccountResponsePayload.class));
										String statusmsg = "No status code received from third party";
										if (bankAccountResponse.getPayload() != null) 
										{
											if (bankAccountResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(bankAccountResponse.getPayload().getStatus_code());
											}
										}
										bankAccountResponse.getPayload().setStatus_msg(statusmsg);

										if (bankAccountResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(bankAccountResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + bankAccountResponse.getPayload().getStatus_code());
										}
										else if (bankAccountResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(bankAccountResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + bankAccountResponse.getPayload().getStatus_code());
										}
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(bankAccountResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + bankAccountResponse.getPayload().getStatus_code());
										}
									} 
									catch (Exception ex)
									{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
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
							// for invalid input
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
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}

		bankAccountResponse.setMsgInfo(msgInfo);
		bankAccountResponse.setHeader(bankAccountRequest.getHeader());
		return bankAccountResponse;
	}


	@Override
	public LpgIdentificationResponse lpgIdentificationRequestService(final LpgIdentificationRequest lpgRequest, KarzaReqRes reqRes) 
	{
		LpgIdentificationResponse lpgResponse = new LpgIdentificationResponse();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave = "https://testapi.karza.in/v2/lpgid";
		String key = resProp.getString("com.karza.key40");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.lpgIden.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (lpgRequest != null) 
			{
				if (lpgRequest.getPayload() != null
						&& lpgRequest.getPayload().getInput() != null && !lpgRequest.getPayload().getInput().equals("") 
						&& !lpgRequest.getPayload().getConsent().equals("") && lpgRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(lpgRequest.getHeader());
					if (result == true) 
					{
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();

						if(hardCodedEnv.equalsIgnoreCase("Y"))
						{

							String	res="{\"request_id\": \"c797f6ab-2216-11e8-8e08-5153fb4e56a8\", \"result\": {\"lpg_id\": \"10000000050431060\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(lpgRequest.getPayload()), urlSave,key);
						}
						//lpgResponse=om.readValue(res, LpgIdentificationResponse.class);
						try 
						{
							String price=null;
							reqRes.setIntReq(ow.writeValueAsString(lpgRequest.getPayload()));
							reqRes.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.lpg.success.price");
							}else {
								price=resProp.getString("com.karza.lpg.failure.price");
							}
							reqRes.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while updating karza internal req res:" + e);
						}

						if (info.getResponseCode() == 200) {
							msgInfo.setCode(""+info.getResponseCode());
							if (!info.getResponse().toString().contains("Invalid Input")) {
								if (!info.getResponse().toString().contains("Max retries exceeded")) {

									try {
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										lpgResponse.setPayload(om.readValue(replaceAttributes(info.getResponse()),
												LpgIdentificationResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (lpgResponse.getPayload() != null) {

											if (lpgResponse.getPayload().getStatus_code() != 0) {
												statusmsg = getStatusMsg(lpgResponse.getPayload().getStatus_code());

											}
										}

										lpgResponse.getPayload().setStatus_msg(statusmsg);

										if (lpgResponse.getPayload().getStatus_code() == 101) {
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										} else if (lpgResponse.getPayload().getStatus_code() == 102) {
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										} else {

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										/*MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
										msgInfo=httpMsg;
										 */
										// ErrorResponse errorResponse =
										// om.readValue(info.getResponse(),ErrorResponse.class);
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										//msgInfo.setInternalCode(StringConstants.C_600.toString());
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									//msgInfo.setInternalCode("" + 104);
									msgInfo.setCode(""+104);
								}

							} else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								//msgInfo.setInternalCode("" + 101);
								msgInfo.setCode(""+101);
							}

						}

						else {
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							/*// ErrorResponse errorResponse =
							// om.readValue(info.getResponse(),ErrorResponse.class);
							msgInfo.setStatus(StringConstants.FAILURE.toString());
							msgInfo.setMessage("" + StringConstants.MESSAGE500);
							msgInfo.setInternalCode(StringConstants.C_500.toString());*/
							logger.info("Failure from 3rd Party API : " + info.getResponse());
						}
					} else {
						msgInfo.setStatus(StringConstants.FAILURE.toString());
						msgInfo.setMessage(StringConstants.MESSAGE401.toString());
						//msgInfo.setInternalCode(StringConstants.C_401.toString());
						msgInfo.setCode(""+401);
					}
				} else {
					msgInfo.setStatus("Failure");
					msgInfo.setMessage("Bad Request");
					//msgInfo.setInternalCode(""+400);
					msgInfo.setCode(""+400);
				}
			} else {
				msgInfo.setStatus("Failure");
				msgInfo.setMessage("Bad Request");
				//msgInfo.setInternalCode(""+400);
				msgInfo.setCode(""+400);
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			//msgInfo.setInternalCode(StringConstants.C_600.toString());
			msgInfo.setCode(""+600);
		}
		lpgResponse.setMsgInfo(msgInfo);
		lpgResponse.setHeader(lpgRequest.getHeader());
		return lpgResponse;
	}

	@Override
	public LpgResponse2 lpgRequestServiceQC(final LpgRequest2 lpgRequest, KarzaReqRes rq)
	{
		LpgResponse2 lpgResponse = new LpgResponse2();
		MessageInfo msgInfo = new MessageInfo();
		String urlSave = resProp.getString("com.karza.lpg.v3..url");
		//String urlSave = "https://testapi.karza.in/v2/lpg";
		String key = resProp.getString("com.karza.key40");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.lpgv3.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (lpgRequest != null) {
				if (lpgRequest.getPayload() != null
						&& lpgRequest.getPayload().getLpg_id() != null && !lpgRequest.getPayload().getLpg_id().equals("") 
						&& !lpgRequest.getPayload().getConsent().equals("") && lpgRequest.getPayload().getConsent() != null) {
					boolean result = checkValidation(lpgRequest.getHeader());
					if (result == true) {
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();
						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"request_id\": \"f1865ef6-2216-11e8-a934-a77e221dfe7d\", \"result\": {\"status\": \"Active\", \"DistributorName\": \"SHIVSHAKTI BHARATGAS AGENCY\", \"SubsidizedRefillConsumed\": \"11\", \"pin\": \"\", \"ConsumerEmail\": \"Not Available\", \"GivenUpSubsidy\": \"No\", \"BankName\": \"Not Applicable\", \"IFSCCode\": \"Not Applicable\", \"city/town\": \"\", \"AadhaarNo\": \"**** **** 7460\", \"ConsumerContact\": \"******9349\", \"ApproximateSubsidyAvailed\": \"0.0\", \"ConsumerName\": \"MRS SUSHAMA M SHIRHATTI\", \"ConsumerNo\": \"50431060\", \"DistributorAddress\": \"SHOP NO 1 MANUSMRITI BLDG GR FLOOR TUKARAM NAGAR, 421201\", \"DistributorCode\": \"186834\", \"BankAccountNo\": \"Not Applicable\", \"LastBookingDate\": \"24-Feb-2018\", \"ConsumerAddress\": \"PLNO 125PUSHPA AJANTA NR NANDIVALI TELEPHONE EX THANA MAHARASHTRA Maharas 421201\", \"TotalRefillConsumed\": \"\"}, \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(lpgRequest.getPayload()), urlSave,key);
						}


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(lpgRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.lpg.success.price");
							}else {
								price=resProp.getString("com.karza.lpg.failure.price");
							}
							rq.setPrice(price);


						} catch (Exception e) {
							logger.error("Error while setting karza internal req res:" + e);
						}

						if (info.getResponseCode() == 200) {
							msgInfo.setCode(""+info.getResponseCode());
							if (!info.getResponse().toString().contains("Invalid Input")) {
								if (!info.getResponse().toString().contains("Max retries exceeded")) {

									try {
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										lpgResponse.setPayload(om.readValue(replaceAttributes(info.getResponse()),
												LpgResponsePayload2.class));

										String statusmsg = "No status code received from third party";
										if (lpgResponse.getPayload() != null) {

											if (lpgResponse.getPayload().getStatus_code() != 0) {
												statusmsg = getStatusMsg(lpgResponse.getPayload().getStatus_code());
											}
										}
										lpgResponse.getPayload().setStatus_msg(statusmsg);

										if (lpgResponse.getPayload().getStatus_code() == 101) {
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										} else if (lpgResponse.getPayload().getStatus_code() == 102) {
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										} else {

											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(lpgResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + lpgResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
									}

								} else {
									msgInfo.setStatus(StringConstants.FAILURE.toString());
									msgInfo.setMessage("Max retries exceeded");
									msgInfo.setCode(""+104);
								}

							} else {
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setMessage("Invalid Input");
								msgInfo.setCode(""+101);
							}

						}

						else {
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
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
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}
		lpgResponse.setMsgInfo(msgInfo);
		lpgResponse.setHeader(lpgRequest.getHeader());
		return lpgResponse;
	}

	@Override
	public EmployerLookupResponse employerLookupRequestService(EmolpyerLookupRequest employerLookupRequest,	KarzaReqRes rq)
	{
		EmployerLookupResponse employerLookupResponse = new EmployerLookupResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.employer_lookup.url");
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.emolpyerlookup.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try
		{
			if (employerLookupRequest != null)
			{
				if (employerLookupRequest.getPayload() != null 
						&& employerLookupRequest.getPayload().getUan()!= null && !employerLookupRequest.getPayload().getUan().equals("")
						&& !employerLookupRequest.getPayload().getConsent().equals("") && employerLookupRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(employerLookupRequest.getHeader());
					if (result == true) {
						String responseEmployerLookupCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();
						if(hardCodedEnv.equalsIgnoreCase("Y") && res!=null)
						{
							info.setResponse(res.getString("KARZA_Employer_Lookup_Report"));
							info.setResponseCode(200);
						}
						else
						{
							info = httpCall(ow.writeValueAsString(employerLookupRequest.getPayload()), urlSave,key);
						} 
						try 
						{
							String price=null;
							rq.setIntReq(ow.writeValueAsString(employerLookupRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.employer_lookup.success.price");
							}else {
								price=resProp.getString("com.karza.employer_lookup.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while setting karza internal req res:" + e);
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
										responseEmployerLookupCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										employerLookupResponse.setPayload(om.readValue(responseEmployerLookupCall, EmployerLookupResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (employerLookupResponse.getPayload() != null) 
										{
											if (employerLookupResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(employerLookupResponse.getPayload().getStatus_code());
											}
										}
										employerLookupResponse.getPayload().setStatus_msg(statusmsg);

										if (employerLookupResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(employerLookupResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + employerLookupResponse.getPayload().getStatus_code());
										}
										else if (employerLookupResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(employerLookupResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + employerLookupResponse.getPayload().getStatus_code());
										}
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(employerLookupResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + employerLookupResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
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
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
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
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}

		employerLookupResponse.setMsgInfo(msgInfo);
		employerLookupResponse.setHeader(employerLookupRequest.getHeader());
		return employerLookupResponse;

	}


	@Override
	public UanLookupResponse uanLookupRequestService(UanLookupRequest uanLookupRequest, KarzaReqRes rq) {

		UanLookupResponse uanLookupResponse = new UanLookupResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.epf_uan_lookup.url");
		//		String urlSave = "https://testapi.karza.in/v2/uan-lookup";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.uanlookup.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (uanLookupRequest != null) {

				if (uanLookupRequest.getPayload() != null 
						&& uanLookupRequest.getPayload().getMobile() != null && !uanLookupRequest.getPayload().getMobile().equals("")
						&& !uanLookupRequest.getPayload().getConsent().equals("") && uanLookupRequest.getPayload().getConsent() != null) {

					boolean result = checkValidation(uanLookupRequest.getHeader());
					if (result == true) {
						String responseUanLookupCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();
						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{\"result\": {\"uan\": [\"100063636287\"]}, \"request_id\": \"1ca2c554-335b-11e8-afa4-6d68fd4f9bc8\", \"status-code\": \"101\"}";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(uanLookupRequest.getPayload()), urlSave,key);
						} 


						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(uanLookupRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.epf_uan_lookup.success.price");
							}else {
								price=resProp.getString("com.karza.epf_uan_lookup.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while setting karza internal req res:" + e);
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
										responseUanLookupCall = info.getResponse().replaceAll("status-code", "status_code");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										uanLookupResponse.setPayload(om.readValue(responseUanLookupCall, UanLookupResponsePayload.class));

										String statusmsg = "No status code received from third party";
										if (uanLookupResponse.getPayload() != null) 
										{

											if (uanLookupResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(uanLookupResponse.getPayload().getStatus_code());
											}
										}
										uanLookupResponse.getPayload().setStatus_msg(statusmsg);

										if (uanLookupResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(uanLookupResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + uanLookupResponse.getPayload().getStatus_code());
										}
										else if (uanLookupResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(uanLookupResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + uanLookupResponse.getPayload().getStatus_code());
										}
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(uanLookupResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + uanLookupResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
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
							// for invalid input

						}else {
							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
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
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}
		uanLookupResponse.setMsgInfo(msgInfo);
		uanLookupResponse.setHeader(uanLookupRequest.getHeader());
		return uanLookupResponse;
	}

	@Override
	public WebsiteDomainResponse websiteDomaminRequestService(WebsiteDomainRequest websiteDomainRequest, KarzaReqRes rq)
	{
		WebsiteDomainResponse websiteDomainResponse = new WebsiteDomainResponse();
		MessageInfo msgInfo = new MessageInfo();
		String key = resProp.getString("com.karza.key40");
		String urlSave = resProp.getString("com.karza.webdomain.url");
		//		String urlSave = "https://testapi.karza.in/v2/whois";
		KarzaInfo info = new KarzaInfo();
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.karza.websitedomain.demo.development");	
			logger.info("KARZA Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		try {
			if (websiteDomainRequest != null) {

				if (websiteDomainRequest.getPayload() != null 
						&& websiteDomainRequest.getPayload().getDomain()!= null && !websiteDomainRequest.getPayload().getDomain().equals(""))
					//						&& !websiteDomainRequest.getPayload().getConsent().equals("") && websiteDomainRequest.getPayload().getConsent() != null)
				{
					boolean result = checkValidation(websiteDomainRequest.getHeader());
					if (result == true) {
						String responseWebsiteDomainCall = null;
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						ObjectMapper om = new ObjectMapper();
						if(hardCodedEnv.equalsIgnoreCase("Y")){

							String	res="{ \"status-code\": \"101\", \"request_id\": \"8e8d7649-2388-11e8-bd76-17409a1bf823\", \"result\": { \"domain\": { \"name\": \"TOPWORTHGROUP.COM\" }, \"update_date\": \"2018-03-09 10:56:49\", \"expiration_date\": \"2018-07-19 07:54:56\", \"creation_date\": \"2007-07-19 07:54:56\", \"admin\": { \"city\": \"Burlington\", \"fax\": \"\", \"name\": \"Domain Admin\", \"country\": \"US\", \"stateprovince\": \"MA\", \"phone\": \"+1.8022274003\", \"street\": \"10 Corporate Drive\", \"organization\": \"Privacy Protect, LLC (PrivacyProtect.org)\", \"postal\": \"01803\", \"email\": \"contact@privacyprotect.org\" }, \"tech\": { \"city\": \"Burlington\", \"fax\": \"\", \"name\": \"Domain Admin\", \"country\": \"US\", \"stateprovince\": \"MA\", \"phone\": \"+1.8022274003\", \"street\": \"10 Corporate Drive\", \"organization\": \"Privacy Protect, LLC (PrivacyProtect.org)\", \"postal\": \"01803\", \"email\": \"contact@privacyprotect.org\" }, \"registry\": { \"phone\": \"\", \"expiry\": \"\", \"id\": \"Not Available From Registry, 1095886661_DOMAIN_COM-VRSN, Not Available From Registry, Not Available From Registry\", \"email\": \"\" }, \"registrar\": { \"phone\": \"+1.2013775952\", \"email\": \"abuse-contact@publicdomainregistry.com\", \"id\": \"303\" }, \"nameserver\": { \"name\": \"H1.THINKTECHNOLOGYSERVICES.COM, H2.THINKTECHNOLOGYSERVICES.COM, H3.THINKTECHNOLOGYSERVICES.COM, H4.THINKTECHNOLOGYSERVICES.COM\" }, \"registrant\": { \"city\": \"Burlington\", \"fax\": \"\", \"name\": \"Domain Admin\", \"country\": \"US\", \"stateprovince\": \"MA\", \"phone\": \"+1.8022274003\", \"street\": \"10 Corporate Drive\", \"organization\": \"Privacy Protect, LLC (PrivacyProtect.org)\", \"postal\": \"01803\", \"email\": \"contact@privacyprotect.org\" } } }";
							info.setResponse(res);
							info.setResponseCode(200);
						}else{
							info = httpCall(ow.writeValueAsString(websiteDomainRequest.getPayload()), urlSave,key);
						}




						try {
							String price=null;
							rq.setIntReq(ow.writeValueAsString(websiteDomainRequest.getPayload()));
							rq.setIntRes(info.getResponse());
							if (info.getResponseCode() == 200)
							{
								price=resProp.getString("com.karza.webdomain.success.price");
							}else {
								price=resProp.getString("com.karza.webdomain.failure.price");
							}
							rq.setPrice(price);

						} catch (Exception e) {
							logger.error("Error while setting karza internal req res:" + e);
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
										responseWebsiteDomainCall = info.getResponse().replaceAll("status-code", "status_code")
												.replaceAll("update_date", "updateDate")
												.replaceAll("expiration_date", "expirationDate")
												.replaceAll("creation_date", "creationDate")
												.replaceAll("stateprovince", "stateProvince");
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										websiteDomainResponse.setPayload(om.readValue(responseWebsiteDomainCall, WebsiteDomainPayload.class));

										String statusmsg = "No status code received from third party";
										if (websiteDomainResponse.getPayload() != null) 
										{

											if (websiteDomainResponse.getPayload().getStatus_code() != 0)
											{
												statusmsg = getStatusMsg(websiteDomainResponse.getPayload().getStatus_code());
											}
										}

										websiteDomainResponse.getPayload().setStatus_msg(statusmsg);

										if (websiteDomainResponse.getPayload().getStatus_code() == 101) 
										{
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(websiteDomainResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + websiteDomainResponse.getPayload().getStatus_code());
										}
										else if (websiteDomainResponse.getPayload().getStatus_code() == 102)
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(websiteDomainResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + websiteDomainResponse.getPayload().getStatus_code());
										}
										else
										{
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setMessage(websiteDomainResponse.getPayload().getStatus_msg());
											msgInfo.setCode("" + websiteDomainResponse.getPayload().getStatus_code());
										}

									} catch (Exception ex) {
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_600.toString());
										logger.info("Failure from 3rd Party API : " + info.getResponse());
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
							// for invalid input

						}else {

							MessageInfo httpMsg=getHttpMessage(info.getResponseCode());
							msgInfo=httpMsg;
							logger.info("Failure from 3rd Party API : " + info.getResponse());
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
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setCode(""+600);
		}

		websiteDomainResponse.setMsgInfo(msgInfo);
		websiteDomainResponse.setHeader(websiteDomainRequest.getHeader());
		return websiteDomainResponse;
	}


	@Override
	public FaceResponse faceRequestService(FaceRequest faceRequest,
			KarzaReqRes rq) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	private KarzaInfo kycOcrhttpCall(String urlSave, String key) {
		 KarzaInfo info = new KarzaInfo();
		 try {
				//File fileName = new File("/home/qualtech/Pictures/PAN.jpg");

				OkHttpClient client = new OkHttpClient();
				RequestBody requestBody = new MultipartBuilder()
					    .type(MultipartBuilder.FORM).addFormDataPart("file", file.getName(),
				RequestBody.create(MediaType.parse(URLConnection.guessContentTypeFromName(file.getName())), file)).build();
				Request request = new Request.Builder()
				 .url(urlSave"http://ocr.karza.in:8888/kyc")https://testapi.karza.in/v2/ocr/kyc
				 .post(requestBody)
				 .addHeader("x-karza-key", key"td89e4TCG76nd7gz9llh")
				 .build();
				Response response = client.newCall(request).execute();

				info.setResponseCode(response.code());
				info.setResponse(response.body().string());

		 } catch (Exception e) {

			}

		return info;
	}

	 */private boolean requestValidation(String fileData) {

		 try {
			 String mimeType ="";
			 byte[] response =null;
			 File newFile=new File(fileData);
			 if(isValidUrl(fileData)){
				 URL url=new URL(fileData);
				 URLConnection conn = url.openConnection();
				 conn.setRequestProperty("User-Agent", "Mozilla/5.0");

				 mimeType = conn.getContentType();
				 InputStream in = conn.getInputStream();//new BufferedInputStream(url.openStream());
				 ByteArrayOutputStream out = new ByteArrayOutputStream();
				 byte[] buf = new byte[1024];
				 int n = 0;
				 while (-1!=(n=in.read(buf)))
				 {
					 out.write(buf, 0, n);
				 }
				 out.close();
				 in.close();
				 response = out.toByteArray();


			 }else if(newFile.exists()){
				 mimeType= URLConnection.guessContentTypeFromName(newFile.getName());
				 if(mimeType!=null && (mimeType.equals("image/png") || mimeType.equals("image/jpg") || mimeType.equals("image/jpeg") || mimeType.equals("application/pdf"))){

					 file = new File("/home/qualtech/Pictures/file/"+Calendar.getInstance().getTime()+"."+mimeType.split("/")[1]);
					 FileUtils.copyFile(newFile, file);
					 return true;
				 }else{
					 return false;
				 }
			 }else {
				 InputStream in = new BufferedInputStream(new ByteArrayInputStream(Base64.decode(fileData)));
				 mimeType = URLConnection.guessContentTypeFromStream(in);
				 ByteArrayOutputStream out = new ByteArrayOutputStream();
				 byte[] buf = new byte[1024];
				 int n = 0;
				 while (-1!=(n=in.read(buf)))
				 {
					 out.write(buf, 0, n);
				 }
				 out.close();
				 in.close();
				 response = out.toByteArray();
			 }

			 if(mimeType!=null && (mimeType.equals("image/png") || mimeType.equals("image/jpg") || mimeType.equals("image/jpeg") || mimeType.equals("application/pdf"))){
				 FileOutputStream fos = null;
				 try {
					 file = new File("/home/qualtech/Pictures/file/"+Calendar.getInstance().getTime()+"."+mimeType.split("/")[1]);
					 fos = new FileOutputStream(file);
					 fos.write(response);
				 }
				 catch (Exception e)
				 {
				 }
				 finally 
				 {
					 if(fos!=null)
					 {
						 fos.close();
					 }
				 }
			 }
			 else
			 {
				 return false;
			 }
			 return true;
		 } 
		 catch (Exception e) 
		 {
			 return false;
		 }

	 }
	 public static boolean isValidUrl(String url)
	 {
		 try 
		 {
			 new URL(url).toURI();
			 return true;
		 }
		 catch (Exception e)
		 {
			 return false;
		 }
	 }
}
