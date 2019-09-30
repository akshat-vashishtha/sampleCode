package com.qualtech.cibil.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibil.api.common.dto.ERRORSTATUS;
import com.qualtech.cibil.api.common.dto.ErrorInfo;
import com.qualtech.cibil.api.entity.AccountDetails;
import com.qualtech.cibil.api.entity.AccountManualSummary;
import com.qualtech.cibil.api.entity.AccountSummary;
import com.qualtech.cibil.api.entity.AdditionalMatchDetails;
import com.qualtech.cibil.api.entity.AddressDetails;
import com.qualtech.cibil.api.entity.CibilReqResVpn;
import com.qualtech.cibil.api.entity.DisputeDetails;
import com.qualtech.cibil.api.entity.EmailContact;
import com.qualtech.cibil.api.entity.EmploymentDetail;
import com.qualtech.cibil.api.entity.EnquiryDetails;
import com.qualtech.cibil.api.entity.EnquirySummary;
import com.qualtech.cibil.api.entity.IDDetails;
import com.qualtech.cibil.api.entity.Remark;
import com.qualtech.cibil.api.entity.ScoreDetails;
import com.qualtech.cibil.api.entity.ScoringFactors;
import com.qualtech.cibil.api.entity.TelePhone;
import com.qualtech.cibil.api.interfaces.CibilConvertRequestToTUEFInt;
import com.qualtech.cibil.api.interfaces.CibilRequestSocketInt;
import com.qualtech.cibil.api.interfaces.CibilUtilInt;
import com.qualtech.cibil.api.request.CibilApiRequest;
import com.qualtech.cibil.api.response.ApiResponseHeader;
import com.qualtech.cibil.api.response.CibilAPIResponse;
import com.qualtech.cibil.api.response.CibilResponsePayload;
import com.qualtech.cibil.api.service.CibilApiService;
import com.qualtech.cibil.api.utils.CibilUtil;
import com.qualtech.cibil.api.utils.Commons;
import com.qualtech.cibil.api.utils.ConvertUrltoByteArray;
import com.qualtech.cibil.api.utils.UniqueId;
@Service
public class CibilApiServiceImpl implements CibilApiService 
{
	
	private static Logger logger = Logger.getLogger(CibilApiServiceImpl.class);
	public static List<String> responseSegments;
	public static String  tempRequestJson="";
	@Autowired CibilConvertRequestToTUEFInt cibilconvertrequesttotuefint;
	@Autowired CibilRequestSocketInt  cibilrequestsocketint;
	@Autowired PropertyFile env;
	@Autowired CibilUtilInt cibilutilint;
	private static Map<String,String> apendixAMap = CibilUtil.getApendixAData();
	private static Map<String,String> apendix_G_EM_Map = CibilUtil.getApndxGErrorCodeForEM();
	private static Map<String,String> apendix_G_TL_Map = CibilUtil.getApndxGErrorCodeForAccountTL();
	private static Map<String,String> apendix_I_Map = CibilUtil.getApndxIErrorCodeForTL();
	private static Map<String,String> apendix_H_Map = CibilUtil.getApndxHErrorCode();
	
	public static List<String> getTuefResponseString(String TURFresponse)
	{
		List<String> multipleRequestStr = new ArrayList<String>();
		while(true)
		{
			try
			{
				int l = TURFresponse.length();
				int i = TURFresponse.indexOf("**")+2;
				if(i==l)
				{
					multipleRequestStr.add(TURFresponse);
					break;
				}
				else
				{
					String str = TURFresponse.substring(0,i);
					TURFresponse = TURFresponse.substring(i,TURFresponse.length()); 
					multipleRequestStr.add(str);
				}
			}
			catch(Exception ex)
			{
				break;
			}
			
		}
		return multipleRequestStr;
	}
	
	public CibilAPIResponse callCibilService(CibilApiRequest apiRequest ,CibilReqResVpn rq) 
	{
		logger.info(" CibilApiServiceImpl || callCibilService() method STARTS ");
		CibilAPIResponse cibilapiresponse=new CibilAPIResponse(); 
		/////////////////Api Response header//////////////////
		ApiResponseHeader header=new ApiResponseHeader();
		header.setAppId(apiRequest.getHeader().getAppId());
		header.setCorrelationId(apiRequest.getHeader().getCorrelationId());
		header.setMsgVersion(apiRequest.getHeader().getMsgVersion());
		cibilapiresponse.setHeader(header);
		/////////////////Api Response header//////////////////
		
		ErrorInfo errorInfo = new ErrorInfo();
		String code="";
		String message="";
		 
		String TUEFStr=cibilconvertrequesttotuefint.convertRequestToTUEF(apiRequest);
		CibilResponsePayload cibilresponsepayload=new CibilResponsePayload();
		cibilresponsepayload.setCibilReq(TUEFStr);
		List<String> multipleResponseStr = null;
		try
		{
			// TODO Auto-generated method stub
			responseSegments=new ArrayList<String>();
			responseSegments.add("ERRR");
			responseSegments.add("TUEF");
			responseSegments.add("PN03N01");
			responseSegments.add("ID03");
			responseSegments.add("PT03");
			responseSegments.add("EC03");
			responseSegments.add("EM03");
			responseSegments.add("PI03");
			responseSegments.add("SC10");
			responseSegments.add("SC07");
			responseSegments.add("PA03A");
			responseSegments.add("TL04");
			responseSegments.add("IQ04");
			responseSegments.add("DR03");
			responseSegments.add("ES07");

			logger.info("The TUEF Request String is : "+TUEFStr);

			String TURFresponse="";
			String hardCodedEnv="N";
			ResourceBundle res=null;
			try 
			{
				res = ResourceBundle.getBundle("hardcode");
				hardCodedEnv = env.getString("com.cibil.vpn.demo.development");	
				logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
			}
			catch(Exception ex)
			{
				hardCodedEnv="N";
			}
			
			if(hardCodedEnv.equalsIgnoreCase("N"))
			{
				Map<String,String>  resMap=cibilrequestsocketint.connectSocket(TUEFStr);
				TURFresponse=resMap.get("TURF");
			}
			else
			{
				TURFresponse = res.getString("CIBIL_VPN_RESPONSE");
			}
			rq.setIntReq(TUEFStr);
			rq.setIntRes(TURFresponse);
			cibilresponsepayload.setCibilRes(TURFresponse);  //here cibil respons set//

			logger.info("The TUEF Response String is : "+TURFresponse);


			if(env.getString("cibil.OutPutFormat").equals("02"))
			{
				cibilresponsepayload.setPrintableReport(TURFresponse);
				cibilapiresponse.setPayload(cibilresponsepayload);
				return cibilapiresponse;
			}
			
			multipleResponseStr = getTuefResponseString(TURFresponse);	
			if(multipleResponseStr!=null && multipleResponseStr.size()>0)
			{
				TURFresponse=multipleResponseStr.get(0);
			}
			try
			{
				if(TURFresponse.contains("ERRR"))
				{
					List<String> responseSegmentsError=new ArrayList<String>();
					responseSegments.add("ERRR");
					responseSegments.add("UR03U01");
					
					String errorSegment = Commons.getResponseFromTag("ERRR", responseSegmentsError, TURFresponse);
					String errorDate = errorSegment.substring(4, 12);
					String errorTime = errorSegment.substring(12, 18);
					
					String errorSegmentUserRef = Commons.getResponseFromTag("UR03U01", responseSegmentsError, TURFresponse);
					String memberrefNo = errorSegmentUserRef.substring(11, 11+25).trim();
					String errorDesc = "We face Error from Cibil -:- Date : "+CibilUtil.getCibilDate(errorDate)+ ", Time : "+getRealTimefromCibilTime(errorTime)+", Member Ref No : "+memberrefNo +", Error String : "+TURFresponse;
					errorInfo.setCode("601");
					errorInfo.setMessage("Somthing went wrong, Please try after sometime.");
					errorInfo.setDescription(errorDesc);
					cibilapiresponse.setErrorInfo(errorInfo);
					cibilapiresponse.setPayload(null);
					cibilapiresponse.setHeader(header);
					return cibilapiresponse;
				}
			}
			catch(Exception ex)
			{
				//Do Nothing as no any Error on Cibil Response
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || TURFresponse contains(\"ERRR\") :: "+ex);
			}
			
			//////////////////getting data from TUEF//////////////////////////////////////////////
			try 
			{

				String tuefTagsData = Commons.getResponseFromTag("TUEF", responseSegments, TURFresponse);
				String returncode = tuefTagsData.substring(67, 68);
				cibilresponsepayload.setReturncode(returncode);
				
				String enquiryControlNumber = tuefTagsData.substring(68, 80);
				cibilresponsepayload.setEnquiryControlNumber(enquiryControlNumber);
				String processedDate = tuefTagsData.substring(80, 88);
				cibilresponsepayload.setProcessedDate(CibilUtil.getCibilDate(processedDate));
				String timeProceesed=tuefTagsData.substring(88,94);
				cibilresponsepayload.setTimeProceesed(getRealTimefromCibilTime(timeProceesed));
			}
			catch (Exception ec) 
			{
				code="600";
				message+="Unable to get TUEF Data, ";
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || while getting main data from response " + ec);
			}

			////////////////// getting data from
			////////////////// TUEF//////////////////////////////////////////////
			/////////////////////////////Naming Segment Start//////////////////////////////////////////
			String NamingSegment = Commons.getResponseFromTag("PN03N01", responseSegments, TURFresponse);
			try 
			{
				String restDatafromTag;
				if (!"".equals(NamingSegment)) 
				{
					restDatafromTag = NamingSegment.substring(7, NamingSegment.length());
					int completelength = 0;
					////Exclusion Code Start
					if ("01".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 2, completelength + 4);
						String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
						cibilresponsepayload.setName1(name.replaceAll("[0-9]", ""));
						completelength = completelength + 4 + Integer.parseInt(value);
					}
					if ("02".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 2, completelength + 4);
						String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
						cibilresponsepayload.setName2(name.replaceAll("[0-9]", ""));
						completelength = completelength + 4 + Integer.parseInt(value);
					}
					if ("03".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 2, completelength + 4);
						String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
						cibilresponsepayload.setName3(name.replaceAll("[0-9]", ""));
						completelength = completelength + 4 + Integer.parseInt(value);
					}
					if ("04".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 2, completelength + 4);
						String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
						cibilresponsepayload.setName4(name.replaceAll("[0-9]", ""));
						completelength = completelength + 4 + Integer.parseInt(value);
					}
					if ("05".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 2, completelength + 4);
						String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
						cibilresponsepayload.setName5(name.replaceAll("[0-9]", ""));
						completelength = completelength + 4 + Integer.parseInt(value);
					}
					if ("0708".equals(CibilUtil.getSubString(completelength, completelength + 4, restDatafromTag))) {
						String value = "08";
						String dateofBirth = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
						cibilresponsepayload.setDateofBirth(CibilUtil.getCibilDate(dateofBirth));
						completelength = completelength + 4 + Integer.parseInt(value);
					}
					if ("0801".equals(CibilUtil.getSubString(completelength, completelength + 4, restDatafromTag))) {
						String value = "01";
						String gender = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
						cibilresponsepayload.setGender(gender);
						completelength = completelength + 4 + Integer.parseInt(value);
					}
				}
			} catch (Exception ec) {
				code="600";
				message+="Unable to get Name Segemnt, ";
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting the Name in response "+ ec);
			}
			
			/////////////////////////// Naming Segment End/////////////////////////////////////////////


			/////////////////////////ID Segments  8/10/2017 Starts/////////////////////////////
			HashMap<String,String> idTypeToValue=new HashMap<String,String>();
			idTypeToValue.put("01", "Income Tax ID Number");
			idTypeToValue.put("02", "Passport Number");
			idTypeToValue.put("03", "Voter ID Number");
			idTypeToValue.put("04", "Driving License Number");
			idTypeToValue.put("05", "Ration Card Number");
			idTypeToValue.put("06", "Universal ID Number (UID)");
			idTypeToValue.put("07", "Additional ID #1");
			idTypeToValue.put("08", "Additional ID #2");
			String IdSegments=Commons.getResponseFromTag("ID03", responseSegments, TURFresponse);
			List<String> idArray=new ArrayList<String>();
			idArray.add("ID03I01");
			idArray.add("ID03I02");
			idArray.add("ID03I03");
			idArray.add("ID03I04");
			idArray.add("ID03I05");
			idArray.add("ID03I06");
			idArray.add("ID03I07");
			idArray.add("ID03I08");
			ArrayList<IDDetails> idDetails=new ArrayList<IDDetails>();
			try
			{
				for(int i=1;i<8;i++)
				{  
					String requiredTag = "ID03I0" + i;
					String singleIdSegment = Commons.getResponseFromTag1(requiredTag, idArray, IdSegments);
					if (!"".equals(singleIdSegment)) 
					{
						IDDetails  iddetails=new IDDetails(); 
						try
						{    
							String idType=singleIdSegment.substring(11, 13);
							String idValue=idTypeToValue.get(idType);
							iddetails.setIdType(idValue);
							String idnumberlength=singleIdSegment.substring(15,17);
							int  idnumberlengthint=Integer.parseInt(idnumberlength);
							String idNumber=singleIdSegment.substring(17,(17+idnumberlengthint));
							iddetails.setIdNumber(idNumber);
							int currentlength=17+idnumberlengthint;
							String issueDate=singleIdSegment.substring(currentlength+4,currentlength+12);
							iddetails.setIssueDate(issueDate);
							currentlength=currentlength+12;
							String expirationdate=singleIdSegment.substring(currentlength+4, currentlength+12);
							iddetails.setExpirationDate(expirationdate);	
						}
						catch(Exception ec)
						{  
							
							logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting ID Segment in Response "+ec);
						
						}
						iddetails.setCibilresponsepayload(cibilresponsepayload);
						idDetails.add(iddetails);
					}
				}
			}
			catch(Exception ec)
			{
				code="600";
				message+="Unable to get ID Segment, ";
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting ID Segment in Response "+ec);
			}
			cibilresponsepayload.setIddetails(idDetails);
			/////////////////////////ID Segments  8/10/2017 Ends/////////////////////////////



			////////////////////////////////Email Contact Segment  Starts   8/10/2017 ///////////////////////////

			String emailIdSegment=Commons.getResponseFromTag("EC03", responseSegments, TURFresponse);
			List<String> emailArray=new ArrayList<String>();
			emailArray.add("EC03C01");
			emailArray.add("EC03C02");
			emailArray.add("EC03C03");
			emailArray.add("EC03C04");
			ArrayList<EmailContact>  emailcontacts=new ArrayList<EmailContact>();
			try
			{
				for(int i=1;i<=4;i++)
				{
					String requiredTag = "EC03C0" + i;
					String singleEmailIdSegment = Commons.getResponseFromTag1(requiredTag, emailArray, emailIdSegment);
					if (!"".equals(singleEmailIdSegment))
					{
						EmailContact  emailcontact=new EmailContact();
						try
						{
							String emailIdLength =singleEmailIdSegment.substring(9, 11);
							int emailIdlength=Integer.parseInt(emailIdLength);
							String emailId=singleEmailIdSegment.substring(11, 11+emailIdlength);
							emailcontact.setEmailId(emailId);
							emailcontact.setCibilresponsepayload(cibilresponsepayload);
						}
						catch(Exception ec)
						{
							logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting Email Contact Segment "+ec);
						}
						emailcontacts.add(emailcontact);
					}
				}
			}
			catch(Exception ec)
			{
				code="600";
				message+="Unable to get Email Segment, ";
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting Email Contact Segment "+ec);
			}
			cibilresponsepayload.setEmailcontact(emailcontacts);
			////////////////////////////////Email Contact Segment  Ends      8/10/2017  ///////////////////////////





			String ptSegment = Commons.getResponseFromTag("PT03", responseSegments, TURFresponse);
			List<String> addressArray = new ArrayList<String>();
			addressArray.add("PT03T01");
			addressArray.add("PT03T02");
			addressArray.add("PT03T03");
			addressArray.add("PT03T04");
			ArrayList<TelePhone> telephones = new ArrayList<TelePhone>();
			try {
				for (int i = 1; i <= 4; i++) {
					try {
						TelePhone tphone = new TelePhone();
						String requiredTag = "PT03T0" + i;
						String singleaddressSegment = Commons.getResponseFromTag1(requiredTag, addressArray, ptSegment);
						if (!"".equals(singleaddressSegment)) {
							int phoneNumberSize = Integer.parseInt(singleaddressSegment.substring(9, 11));
							String telephoneNumber = singleaddressSegment.substring(11, 11 + phoneNumberSize);
							tphone.setTelephoneNumber(telephoneNumber);

							String isExtension = singleaddressSegment.substring(11 + phoneNumberSize,11 + phoneNumberSize+2);
							boolean isExtensionAvailable =false;
							int telephoneExtnsize=0;
							if(isExtension!=null && isExtension.equalsIgnoreCase("02"))
							{
								isExtensionAvailable=true;
								telephoneExtnsize = Integer.parseInt(
										singleaddressSegment.substring(13 + phoneNumberSize, 15 + phoneNumberSize));
								String telephoneExtn = singleaddressSegment.substring(15 + phoneNumberSize,
										15 + phoneNumberSize + telephoneExtnsize);
								tphone.setTelephoneExtn(telephoneExtn);
							}
							String telephonetype="";
							if(isExtensionAvailable)
							{
								telephonetype = singleaddressSegment.substring(
										19 + phoneNumberSize + telephoneExtnsize, 21 + phoneNumberSize + telephoneExtnsize);
							}
							else
							{
								telephonetype = singleaddressSegment.substring(
										15 + phoneNumberSize + telephoneExtnsize, 17 + phoneNumberSize + telephoneExtnsize);
							}
							if(telephonetype!=null && !telephonetype.equalsIgnoreCase(""))
							{
								if(telephonetype.equalsIgnoreCase("00"))
								{
									telephonetype="Not Classified";
								}
								else if(telephonetype.equalsIgnoreCase("01"))
								{
									telephonetype="Mobile Phone";
								}
								else if(telephonetype.equalsIgnoreCase("02"))
								{
									telephonetype="Home Phone";
								}
								if(telephonetype.equalsIgnoreCase("03"))
								{
									telephonetype="Office Phone";
								}
							}
							tphone.setCibilresponsepayload(cibilresponsepayload);
							tphone.setTelephoneType(telephonetype);
							telephones.add(tphone);
						}
					} catch (Exception ec) {
						logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting Telephones in response" + ec);
					}
				}
				
				cibilresponsepayload.setTelephones(telephones);
			} catch (Exception ec) {
				code="600";
				message+="Unable to get Telephone Segment, ";
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting Telephones in response");
			}
			////////////////////////////////////// for cibil transunion score
			////////////////////////////////////// starts///////////////////////////////////////////
			List<ScoreDetails> scoreDetails = new ArrayList<ScoreDetails>();
			//boolean scoreflag = false;
			try 
			{
				String cibiltuscr = Commons.getResponseFromTag("SC10", responseSegments, TURFresponse);
				String restDatafromTag;
				if (!"".equals(cibiltuscr)) 
				{
					ScoreDetails scoreD = new ScoreDetails();
					List<ScoringFactors> scoringFactors= new ArrayList<ScoringFactors>();
					restDatafromTag = cibiltuscr.substring(14, cibiltuscr.length());
					String scoreName="";
					String scorcardNo = restDatafromTag.substring(4, 6);
					String scorecardName = "";
					if ("01".equals(scorcardNo)) {
						scoreName = "CIBILTUSCR";
						scorecardName = "CIBIL TransUnion Score Version 1.0";//Don't update String same isapplied on JSP
					} else if ("02".equals(scorcardNo)) {
						scoreName = "PLSCORE";
						scorecardName = "Personal Loan Score";
					} else if ("04".equals(scorcardNo)) {
						scoreName = "CIBILTUSC2";
						scorecardName = "CIBIL TransUnion Score Version 2.0";//Don't update String same isapplied on JSP
					}
					scoreD.setScore(CibilUtil.getStringOfStringInt(restDatafromTag.substring(28, 33)));
					scoreD.setScoredate(CibilUtil.getCibilDate(restDatafromTag.substring(16, 24)));
					
					int completelength = 33;
					////Exclusion Code Start
					if ("05".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with suit filed status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("06".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with wilful default status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("07".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setName(scoreName);
							factors.setFactor("One or more trades with suit filed (wilful default) status in the past 24 months.");
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("08".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades written off in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("09".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with suit filed and written off status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("10".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with wilful default and written off status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("11".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with suit filed (wilful default) and written off status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					//applicable to Personal Score (PLSCORE) only.
//					if ("12".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
//						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
//						if(value!=null && value.equalsIgnoreCase("01"))
//						{
//							ScoringFactors factors = new ScoringFactors();
//							factors.setCode(value);
//							factors.setFactor("No eligible trade for scoring.");
//							scoringFactors.add(factors);
//						}
//						completelength = completelength + 4 + value.length();
//					}
					if ("13".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with restructured debt in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("14".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with settled debt in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					////Exclusion Code End
					
					
					////Reason Code Start
					if ("25".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null)
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("26".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null)
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("27".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null)
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("28".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null)
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("29".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null)
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					////Reason Code End
					
					scoreD.setCibilresponsepayload(cibilresponsepayload);
					scoreD.setScorecardName(scorecardName);
					scoreD.setScoringFactors(scoringFactors);
					scoreD.setScoringfactorbkp(getCibilScoreFactors(scoreD.getScoringFactors()));
					scoreDetails.add(scoreD);
				}
			}
			catch (Exception ec) 
			{
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || While Calculating Score: "+ec);
			}
			try 
			{
				String plscore = Commons.getResponseFromTag("SC07", responseSegments, TURFresponse);
				String restDatafromTag;
				if (!"".equals(plscore)) 
				{
					ScoreDetails scoreD = new ScoreDetails();
					List<ScoringFactors> scoringFactors= new ArrayList<ScoringFactors>();
					String scoreName="";
					restDatafromTag = plscore.substring(11, plscore.length());
					String scorcardNo = restDatafromTag.substring(4, 6);
					String scorecardName = "";
					if ("01".equals(scorcardNo)) {
						scoreName = "CIBILTUSCR";
						scorecardName = "CIBIL TransUnion Score Version 1.0";
					} else if ("02".equals(scorcardNo)) {
						scoreName = "PLSCORE";
						scorecardName = "Personal Loan Score";
					} else if ("04".equals(scorcardNo)) {
						scoreName = "CIBILTUSC2";
						scorecardName = "CIBIL TransUnion Score Version 2.0";
					}
					scoreD.setScore(CibilUtil.getStringOfStringInt(restDatafromTag.substring(28, 33)));
					scoreD.setScoredate(CibilUtil.getCibilDate(restDatafromTag.substring(16, 24)));
					
					int completelength = 33;
					////Exclusion Code Start
					if ("05".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with suit filed status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("06".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with wilful default status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("07".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setName(scoreName);
							factors.setFactor("One or more trades with suit filed (wilful default) status in the past 24 months.");
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("08".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades written off in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("09".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with suit filed and written off status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("10".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with wilful default and written off status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("11".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with suit filed (wilful default) and written off status in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					//applicable to Personal Score (PLSCORE) only.
					if ("12".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("No eligible trade for scoring.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("13".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with restructured debt in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("14".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor("One or more trades with settled debt in the past 24 months.");
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					////Exclusion Code End
					
					
					////Reason Code Start
					if ("25".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("26".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("27".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("28".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					if ("29".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
						String value = restDatafromTag.substring(completelength + 4, completelength + 6);
						if(value!=null && value.equalsIgnoreCase("01"))
						{
							ScoringFactors factors = new ScoringFactors();
							factors.setCode(value);
							factors.setFactor(CibilUtil.getApendixFdata(value,scoreName));
							factors.setName(scoreName);
							scoringFactors.add(factors);
						}
						completelength = completelength + 4 + value.length();
					}
					////Reason Code End
					scoreD.setCibilresponsepayload(cibilresponsepayload);
					scoreD.setScorecardName(scorecardName);
					scoreD.setScoringFactors(scoringFactors);
					scoreD.setScoringfactorbkp(getCibilScoreFactors(scoreD.getScoringFactors()));
					scoreDetails.add(scoreD);
				}
			}
			catch (Exception ec) 
			{
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || While Calculating PL_Score Scoring Factor : "+ec);
			}
			
			if(scoreDetails!=null && scoreDetails.isEmpty())
			{
				ScoreDetails scoreD = new ScoreDetails();
				scoreD.setScore("NA");
				scoreD.setScoredate("NA");
				scoreD.setScorecardName("NA");
				scoreD.setScoringfactorbkp("NA");
				scoreDetails.add(scoreD);
			}
			cibilresponsepayload.setScoreDetails(scoreDetails);
			
			////////////////////////////////////// for cibil transunion score
			////////////////////////////////////// ends///////////////////////////////////////////
			
			
			//////////Address/////////
			List<AddressDetails> addressResponseDTOs = new ArrayList<AddressDetails>();
			List<String> addressegments = new ArrayList<String>();
			addressegments.add("PA03A01");
			addressegments.add("PA03A02");
			addressegments.add("PA03A03");
			addressegments.add("PA03A04");

			try 
			{
				String address1 = Commons.getResponseFromTag("PA03A01", addressegments, TURFresponse);
				AddressDetails addressDetails= Commons.addressResponseDTO(address1);
				if(addressDetails!=null)
				{
					addressResponseDTOs.add(addressDetails);
				}
				String address2 = Commons.getResponseFromTag("PA03A02", addressegments, TURFresponse);
				if(address2!=null && !address2.equals("")) {
				addressDetails= Commons.addressResponseDTO(address2);
				}
				if(addressDetails!=null)
				{
					addressResponseDTOs.add(addressDetails);
				}
				String address3 = Commons.getResponseFromTag("PA03A03", addressegments, TURFresponse);
				if(address3!=null && !address3.equals("")) {	
				addressDetails= Commons.addressResponseDTO(address3);
				}
				if(addressDetails!=null)
				{
					addressResponseDTOs.add(addressDetails);
				}				
				String address4 = Commons.getResponseFromTag("PA03A04", addressegments, TURFresponse);
				if(address4!=null && !address4.equals("")) {
				addressDetails= Commons.addressResponseDTO(address4);
				}
				if(addressDetails!=null)
				{
					addressResponseDTOs.add(addressDetails);
				}
				addressDetails.setCibilresponsepayload(cibilresponsepayload);
				cibilresponsepayload.setAddresses(addressResponseDTOs);
			}
			catch (Exception ec)
			{
				code="600";
				message+="Unable to get Address Segment, ";
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				logger.error(" Exception in CibilApiServiceImpl || callCibilService() || setting Address in Response "+ec);
			}

			/////////////////////////////// for Account Segment Details
			/////////////////////////////// TL///////////////////////////////////////////////////////
			ArrayList<String> tlSegment = new ArrayList<String>();
			for (int i = 1; i < 1000; i++) {
				String tlSegments = Commons.convertCibilEnquiryListing(i);
				tlSegment.add("TL04T" + tlSegments);
			}
			ArrayList<AccountDetails> accountsSegment = new ArrayList<AccountDetails>();
			List<AccountSummary> accountSummary = new ArrayList<AccountSummary>();
			AccountManualSummary accountManSummary = new AccountManualSummary();
			try 
			{
				for (int i = 1; i < 1000; i++) 
				{
					AccountDetails accountdetails = new AccountDetails();
					String tlSegments = Commons.convertCibilEnquiryListing(i);
					String accountSegment = Commons.getResponseFromTag("TL04T" + tlSegments, tlSegment, TURFresponse);
					if (!"".equals(accountSegment))
					{
						try 
						{
							int beginIndex = accountSegment.indexOf("TL04T" + tlSegments + "02") + 10;
							int completelength = beginIndex + 2;
							int namelength = Integer.parseInt(accountSegment.substring(beginIndex, completelength));
							String reportingMemberName = accountSegment.substring(completelength,
									completelength + namelength);
							completelength = completelength + namelength;
							if(reportingMemberName!=null && reportingMemberName.equalsIgnoreCase("ACCTREVIEW_SUMM"))
							{
								/////////////ACCOUNT : ACCOUNT SEGMENT SUMMARY (TL) � SUMMARY FOR OTHER LIVE/CLOSED ACCOUNTS : END/////////////
								/// for account Number/////
								AccountSummary summary = new AccountSummary();
								try
								{
									if ("03".equals(accountSegment.substring(completelength, completelength + 2))) {
										int accountnumberlength = Integer.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
										String accountNumber = accountSegment.substring(completelength + 4,completelength + 4 + accountnumberlength);
										completelength = completelength + 4 + accountnumberlength;
										summary.setAccountNumber(accountNumber);
									}
									/// Account Group/////
									if ("04".equals(accountSegment.substring(completelength, completelength + 2))) {
										String accounttype = accountSegment.substring(completelength + 4, completelength + 6);
										completelength = completelength + 6;
										if(accounttype!=null)
										{
											if(accounttype.equalsIgnoreCase("98"))
											{
												accounttype="Secured";
											}
											else if(accounttype.equalsIgnoreCase("99"))
											{
												accounttype="Unsecured";
											}
										}
										summary.setAccountGroup(accounttype);
									}
									///// Live/Closed Indicator/////
									if ("05".equals(accountSegment.substring(completelength, completelength + 2))) {
										String liveClosedIndicator = accountSegment.substring(completelength + 4,completelength + 5);
										completelength = completelength + 5;
										if(liveClosedIndicator!=null)
										{
											if(liveClosedIndicator.equalsIgnoreCase("0"))
											{
												liveClosedIndicator="Live Accounts";
											}
											else if(liveClosedIndicator.equalsIgnoreCase("1"))
											{
												liveClosedIndicator="Closed Accounts";
											}
										}
										summary.setLiveClosedIndicator(liveClosedIndicator);
									}
									////Credit Limit / High Credit / Sanctioned Amount
									if ("12".equals(accountSegment.substring(completelength, completelength + 2))) {
										int highSanctionedamountlength = Integer
												.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
										String highsanctionedamount = accountSegment.substring(completelength + 4,
												completelength + 4 + highSanctionedamountlength);
										completelength = completelength + 4 + highSanctionedamountlength;
										accountdetails.setSanctionAmount(highsanctionedamount);
									}
									///Current Balance////
									if ("13".equals(accountSegment.substring(completelength, completelength + 2))) {
										int currentbalancelength = Integer.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
										String currentbalance = accountSegment.substring(completelength + 4,
												completelength + 4 + currentbalancelength);
										completelength = completelength + 4 + currentbalancelength;
										summary.setCurrentBalance(currentbalance);
									}
								}
								
								catch (Exception e)
								{
									logger.error("Exception in CibilApiServiceImpl || callCibilService() ||  Account Summary Part : " + e);
								}
								accountSummary.add(summary);
								summary.setCibilresponsepayload(cibilresponsepayload);
								
								
							}
							else
							{
								/////////////ACCOUNT : ACCOUNT SEGMENT ACTUAL/////////////
								accountdetails.setReportingMemberName(reportingMemberName);
								/// for account Number/////
								if ("03".equals(accountSegment.substring(completelength, completelength + 2))) {
									int accountnumberlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String accountNumber = accountSegment.substring(completelength + 4,
											completelength + 4 + accountnumberlength);
									completelength = completelength + 4 + accountnumberlength;
									accountdetails.setAccountNumber(accountNumber);
								}
								/// for account Number/////
								if ("04".equals(accountSegment.substring(completelength, completelength + 2))) {
									String accounttype = accountSegment.substring(completelength + 4, completelength + 6);
									completelength = completelength + 6;
									accountdetails.setAccountType(apendixAMap.containsKey(Integer.parseInt(accounttype)+"")?apendixAMap.get(Integer.parseInt(accounttype)+""):"");
								
								}
								///// ownership indicator/////
								if ("05".equals(accountSegment.substring(completelength, completelength + 2))) {
									String ownershipindicator = accountSegment.substring(completelength + 4,
											completelength + 5);
									completelength = completelength + 5;
									if(ownershipindicator!=null)
									{
										if(ownershipindicator.equalsIgnoreCase("1"))
										{
											ownershipindicator="Individual";
										}
										else if(ownershipindicator.equalsIgnoreCase("2"))
										{
											ownershipindicator="Authorised User (refers to supplementary credit card holder)";
										}
										else if(ownershipindicator.equalsIgnoreCase("3"))
										{
											ownershipindicator="Guarantor";
										}
										else if(ownershipindicator.equalsIgnoreCase("4"))
										{
											ownershipindicator="Joint";
										}
									}
									accountdetails.setOwnershipIndicator(ownershipindicator);
								}
								///// ownership indicator/////
								if ("08".equals(accountSegment.substring(completelength, completelength + 2))) {
									String dateOpened = accountSegment.substring(completelength + 4, completelength + 12);
									completelength = completelength + 12;
									accountdetails.setDateOpened(CibilUtil.getCibilDate(dateOpened));

								}
								if ("09".equals(accountSegment.substring(completelength, completelength + 2))) {
									String lastpaymentdate = accountSegment.substring(completelength + 4,
											completelength + 12);
									completelength = completelength + 12;
									accountdetails.setDateOfLastPayment(CibilUtil.getCibilDate(lastpaymentdate));
								}
								//// close date///
								if ("10".equals(accountSegment.substring(completelength, completelength + 2))) {
									String closedate = accountSegment.substring(completelength + 4, completelength + 12);
									completelength = completelength + 12;
									accountdetails.setDateClose(CibilUtil.getCibilDate(closedate));
								}
								/// date reported//
								if ("11".equals(accountSegment.substring(completelength, completelength + 2))) {
									String datereported = accountSegment.substring(completelength + 4, completelength + 12);
									completelength = completelength + 12;
									accountdetails.setDateReportedVerified(CibilUtil.getCibilDate(datereported));
								}
								// date reported//
								// High sanctioned amount//
								if ("12".equals(accountSegment.substring(completelength, completelength + 2))) {
									int highSanctionedamountlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String highsanctionedamount = accountSegment.substring(completelength + 4,
											completelength + 4 + highSanctionedamountlength);
									completelength = completelength + 4 + highSanctionedamountlength;
									accountdetails.setSanctionAmount(highsanctionedamount);
								}
								// High sanctioned amount//
								/// complete balance////
								if ("13".equals(accountSegment.substring(completelength, completelength + 2))) {
									int currentbalancelength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String currentbalance = accountSegment.substring(completelength + 4,
											completelength + 4 + currentbalancelength);
									completelength = completelength + 4 + currentbalancelength;
									accountdetails.setCurrntBalance(currentbalance);
								}
								/// complete balance////
								// amountOverdue//
								if ("14".equals(accountSegment.substring(completelength, completelength + 2))) {
									int amountOverduelength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String amountOverdue = accountSegment.substring(completelength + 4,
											completelength + 4 + amountOverduelength);
									completelength = completelength + 4 + amountOverduelength;
									accountdetails.setAmountOverdue(amountOverdue);
								}
								// amountOverdue//
								// payment history
								if ("28".equals(accountSegment.substring(completelength, completelength + 2))) {
									int paymentHistoryLength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String paymentHistory1 = accountSegment.substring(completelength + 4,
											completelength + 4 + paymentHistoryLength);
									completelength = completelength + 4 + paymentHistoryLength;
									accountdetails.setPaymentHistory1(paymentHistory1);
								}
								// payment history

								// payment history2
								if ("29".equals(accountSegment.substring(completelength, completelength + 2))) {
									int paymentHistoryLength2 = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String paymentHistory2 = accountSegment.substring(completelength + 4,
											completelength + 4 + paymentHistoryLength2);
									completelength = completelength + 4 + paymentHistoryLength2;
									accountdetails.setPaymentHistory2(paymentHistory2);
								}
								// payment history2

								/////// payment history satrt
								/////// date///////////////////
								if ("30".equals(accountSegment.substring(completelength, completelength + 2))) {
									String paymenthistorystartdate = accountSegment.substring(completelength + 4,
											completelength + 12);
									completelength = completelength + 12;
									accountdetails.setPaymentHstrStartDate(CibilUtil.getCibilDate(paymenthistorystartdate));
								}
								//////// payment histroy start
								//////// date//////////////////

								/////////// payment history end
								/////////// date///////////////////////
								if ("31".equals(accountSegment.substring(completelength, completelength + 2))) {
									String paymenthistoryEndDate = accountSegment.substring(completelength + 4,
											completelength + 12);
									completelength = completelength + 12;
									accountdetails.setPaymentHistoryEndDate(CibilUtil.getCibilDate(paymenthistoryEndDate));
								}
								/////////// payment history end
								/////////// date///////////////////////
								
								
								//DAYS PAST DUE/ASSET CLASSIFICATION (UP TO 36 MONTHS; LEFT TO RIGHT)
								try 
								{
									String dpdac = accountdetails.getPaymentHistory1().trim().toString()+accountdetails.getPaymentHistory2().trim().toString();
									if(accountdetails.getDateReportedVerified()!=null
											&& accountdetails.getDateReportedVerified().trim().length()==10
											&& dpdac.length()>=3 )
									{
										accountdetails.setDaysPastDueAssetClassificationData(CibilUtil.pastDueAssetClassificationData(accountdetails.getPaymentHstrStartDate(), dpdac, accountdetails, cibilresponsepayload));
									    //cibilresponsepayload.setDaysPastDueAssetClassificationData(CibilUtil.pastDueAssetClassificationData(accountdetails.getPaymentHstrStartDate(), dpdac));
									}
								} 
								catch (Exception e)
								{
									
									logger.error("Exception in CibilApiServiceImpl || callCibilService() || Somthing went wrong while Setting : DAYS PAST DUE/ASSET CLASSIFICATION (UP TO 36 MONTHS) : "+e);
								}
								
								if ("32".equals(accountSegment.substring(completelength, completelength + 2))) {
									String suitfilledstatus = accountSegment.substring(completelength + 4,
											completelength + 6);
									completelength = completelength + 6;
									if(suitfilledstatus!=null)
									{
										if(suitfilledstatus.equalsIgnoreCase("00"))
										{
											suitfilledstatus="No Suit Filed";
										}
										else if(suitfilledstatus.equalsIgnoreCase("01"))
										{
											suitfilledstatus="Suit filed";
										}
										else if(suitfilledstatus.equalsIgnoreCase("02"))
										{
											suitfilledstatus="Wilful default";
										}
										else if(suitfilledstatus.equalsIgnoreCase("03"))
										{
											suitfilledstatus="Suit filed (Wilful default)";
										}
									}
									accountdetails.setSuitfilledDefault(suitfilledstatus);
								}
								/// written off settled status
								if ("33".equals(accountSegment.substring(completelength, completelength + 2))) {
									String writtenoffsettledstatus = accountSegment.substring(completelength + 4,
											completelength + 6);
									completelength = completelength + 6;
									if(writtenoffsettledstatus!=null)
									{
										if(writtenoffsettledstatus.equalsIgnoreCase("00"))
										{
											writtenoffsettledstatus="Restructured Loan";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("01"))
										{
											writtenoffsettledstatus="Restructured Loan (Govt. Mandated)";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("02"))
										{
											writtenoffsettledstatus="Written-off";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("03"))
										{
											writtenoffsettledstatus="Settled";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("04"))
										{
											writtenoffsettledstatus="Post (WO) Settled";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("05"))
										{
											writtenoffsettledstatus="Account Sold";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("06"))
										{
											writtenoffsettledstatus="Written Off and Account Sold";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("07"))
										{
											writtenoffsettledstatus="Account Purchased";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("08"))
										{
											writtenoffsettledstatus="Account Purchased and Written Off";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("09"))
										{
											writtenoffsettledstatus="Account Purchased and Settled";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("10"))
										{
											writtenoffsettledstatus="Account Purchased and Restructured";
										}
										else if(writtenoffsettledstatus.equalsIgnoreCase("11"))
										{
											writtenoffsettledstatus="Restructured due to Natural Calamity";
										}
									}
									accountdetails.setWrittenOffStatus(writtenoffsettledstatus);
								}
								// written off settled status

								/// value of collateral//

								if ("34".equals(accountSegment.substring(completelength, completelength + 2))) {
									int valueOfCollateralLength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String collateralValue = accountSegment.substring(completelength + 4,
											completelength + 4 + valueOfCollateralLength);
									completelength = completelength + 4 + valueOfCollateralLength;
									accountdetails.setValueOfColleteral(collateralValue);
								}
								// value of collateral//

								// type of collateral///
								if ("35".equals(accountSegment.substring(completelength, completelength + 2))) {
									String typeOfCollateral = accountSegment.substring(completelength + 4,
											completelength + 6);
									completelength = completelength + 6;
									if(typeOfCollateral!=null)
									{
										if(typeOfCollateral.equalsIgnoreCase("00"))
										{
											typeOfCollateral="No Collateral";
										}
										else if(typeOfCollateral.equalsIgnoreCase("01"))
										{
											typeOfCollateral="Property";
										}
										else if(typeOfCollateral.equalsIgnoreCase("02"))
										{
											typeOfCollateral="Gold";
										}
										else if(typeOfCollateral.equalsIgnoreCase("03"))
										{
											typeOfCollateral="Shares";
										}
										else if(typeOfCollateral.equalsIgnoreCase("04"))
										{
											typeOfCollateral="Saving Account and Fixed Deposit";
										}
									}
									accountdetails.setTypeOfColleteral(typeOfCollateral);
								}
								// type of collateral//

								// credit limit//
								if ("36".equals(accountSegment.substring(completelength, completelength + 2))) {
									int creditlimitlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String creditlimit = accountSegment.substring(completelength + 4,
											completelength + 4 + creditlimitlength);
									completelength = completelength + 4 + creditlimitlength;
									accountdetails.setCreditLimit(creditlimit);
								}
								// credit limit

								// cash limit//
								if ("37".equals(accountSegment.substring(completelength, completelength + 2))) {
									int cashlimitlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String cashlimit = accountSegment.substring(completelength + 4,
											completelength + 4 + cashlimitlength);
									completelength = completelength + 4 + cashlimitlength;
									accountdetails.setCashLimit(cashlimit);
								}
								// cash limit//

								// rate of interest
								if ("38".equals(accountSegment.substring(completelength, completelength + 2))) {
									int rateofInteresetlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String rateofInterset = accountSegment.substring(completelength + 4,
											completelength + 4 + rateofInteresetlength);
									completelength = completelength + 4 + rateofInteresetlength;
									accountdetails.setRateOfInterest(rateofInterset);
								}
								// rate of interest

								// repaymenttenure//
								if ("39".equals(accountSegment.substring(completelength, completelength + 2))) {
									int repaymenttenurelength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String repaymentTenure = accountSegment.substring(completelength + 4,
											completelength + 4 + repaymenttenurelength);
									completelength = completelength + 4 + repaymenttenurelength;
									accountdetails.setRepaymentTenure(repaymentTenure);
								}
								// repaymenttenure//

								// emi amount
								if ("40".equals(accountSegment.substring(completelength, completelength + 2))) {
									int emiamountlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String emiAmount = accountSegment.substring(completelength + 4,
											completelength + 4 + emiamountlength);
									completelength = completelength + 4 + emiamountlength;
									accountdetails.setEmiAmount(emiAmount);
								}
								// emi amount

								// written off Amount//
								if ("41".equals(accountSegment.substring(completelength, completelength + 2))) {
									int writtenOffAmounttotLength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String writtenOffAmount = accountSegment.substring(completelength + 4,
											completelength + 4 + writtenOffAmounttotLength);
									completelength = completelength + 4 + writtenOffAmounttotLength;
									accountdetails.setWrittenOffAmountTotal(writtenOffAmount);
								}
								// written off Amount//

								// written off principal amount
								if ("42".equals(accountSegment.substring(completelength, completelength + 2))) {
									int writtenOffAmountPrincLength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String writtenOffAmountPrinc = accountSegment.substring(completelength + 4,
											completelength + 4 + writtenOffAmountPrincLength);
									completelength = completelength + 4 + writtenOffAmountPrincLength;
									accountdetails.setWrittenOffAmountPrincipal(writtenOffAmountPrinc);
								}

								// written off principal amount

								// settlement amount//
								if ("43".equals(accountSegment.substring(completelength, completelength + 2))) {
									int settlementAmountlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String settleAmount = accountSegment.substring(completelength + 4,
											completelength + 4 + settlementAmountlength);
									completelength = completelength + 4 + settlementAmountlength;
									accountdetails.setSettlementAmount(settleAmount);
								}

								// settlement amount//

								// paymentfrequency//
								if ("44".equals(accountSegment.substring(completelength, completelength + 2))) {
									String paymentfrequency = accountSegment.substring(completelength + 4,
											completelength + 6);
									completelength = completelength + 6;
									if(paymentfrequency!=null)
									{
										if(paymentfrequency.equalsIgnoreCase("01"))
										{
											paymentfrequency="Weekly";
										}
										else if(paymentfrequency.equalsIgnoreCase("02"))
										{
											paymentfrequency="Fortnightly";
										}
										else if(paymentfrequency.equalsIgnoreCase("03"))
										{
											paymentfrequency="Monthly";
										}
										else if(paymentfrequency.equalsIgnoreCase("04"))
										{
											paymentfrequency="Quarterly";
										}
									}
									accountdetails.setPaymentfrequency(paymentfrequency);
								}
								// paymentfrequency//

								// payment amount
								if ("45".equals(accountSegment.substring(completelength, completelength + 2))) {
									int paymentAmountlength = Integer
											.parseInt(accountSegment.substring(completelength + 2, completelength + 4));
									String paymentAmount = accountSegment.substring(completelength + 4,
											completelength + 4 + paymentAmountlength);
									completelength = completelength + 4 + paymentAmountlength;
									accountdetails.setActualPaymentAmount(paymentAmount);
								}
								// payment amount

								// date off entry for error code
								if ("80".equals(accountSegment.substring(completelength, completelength + 2))) {
									String dateOfEntryCode = accountSegment.substring(completelength + 4,
											completelength + 12);
									completelength = completelength + 12;
									accountdetails.setDateOfEntryForErrorcode(CibilUtil.getCibilDate(dateOfEntryCode));
								}
								/// date of entry for error code

								// error code//
								if ("82".equals(accountSegment.substring(completelength, completelength + 2))) {
									String errorcode = accountSegment.substring(completelength + 4, completelength + 7);
									completelength = completelength + 7;
									if(errorcode!=null)
									{
										errorcode=apendix_G_TL_Map.containsKey(errorcode)?apendix_G_TL_Map.get(errorcode):errorcode;
									}
									accountdetails.setErrorCode(errorcode);
								}
								// error code//

								// date of entry for cibil remarks code//
								if ("83".equals(accountSegment.substring(completelength, completelength + 2))) {
									String dateOfEntryCibilRemarksCode = accountSegment.substring(completelength + 4,
											completelength + 12);
									completelength = completelength + 12;
									accountdetails.setDateOfEntryForCibilRemarkscode(CibilUtil.getCibilDate(dateOfEntryCibilRemarksCode));
								}
								// date of entry for cibil remarks code//

								// cibilremarks code//
								if ("84".equals(accountSegment.substring(completelength, completelength + 2))) {
									String cibil_remarkscode = accountSegment.substring(completelength + 4,
											completelength + 10);
									completelength = completelength + 10;
									accountdetails.setCibilRemarksCode(cibil_remarkscode);
								}
								// cibil remarks code//

								// date of entry for disputeRemarks
								if ("85".equals(accountSegment.substring(completelength, completelength + 2))) {
									String dateOfEntrydisputeRemarks = accountSegment.substring(completelength + 4,
											completelength + 12);
									completelength = completelength + 12;
									accountdetails.setDateOfERemarksCode(CibilUtil.getCibilDate(dateOfEntrydisputeRemarks));
								}
								// date of entry for disputeRemarks

								// error remark code 1
								if ("86".equals(accountSegment.substring(completelength, completelength + 2))) {
									String errorRemarkCode1 = accountSegment.substring(completelength + 4,
											completelength + 10);
									completelength = completelength + 10;
									if(errorRemarkCode1!=null)
									{
										errorRemarkCode1=apendix_H_Map.containsKey(errorRemarkCode1)?apendix_H_Map.get(errorRemarkCode1):errorRemarkCode1;
									}
									accountdetails.setErrordisputecode1(errorRemarkCode1);
								}
								// error remark code 1

								// error remark code 2
								if ("87".equals(accountSegment.substring(completelength, completelength + 2))) {
									String errorRemarkCode2 = accountSegment.substring(completelength + 4,
											completelength + 10);
									completelength = completelength + 10;
									if(errorRemarkCode2!=null)
									{
										errorRemarkCode2=apendix_H_Map.containsKey(errorRemarkCode2)?apendix_H_Map.get(errorRemarkCode2):errorRemarkCode2;
									}
									accountdetails.setErrordisputcode2(errorRemarkCode2);
									
								}
							}
						}
						catch (Exception ec)
						{
							logger.error("Exception in CibilApiServiceImpl || callCibilService() || in Account Details code " + ec);
						
						}
						accountsSegment.add(accountdetails);
					}
                accountdetails.setCibilresponsepayload(cibilresponsepayload);				
				}
				try
				{
					accountManSummary= CibilUtil.getAccountmanualSumaryData(accountsSegment);
				}
				catch(Exception ex)
				{
					logger.error("Exception in CibilApiServiceImpl || callCibilService() || calculating Account manual summary data : "+ex);
				}
                			
	            accountManSummary.setCibilresponsepayload(cibilresponsepayload);
				cibilresponsepayload.setAccountManualSummary(accountManSummary);
				cibilresponsepayload.setAccountDetails(accountsSegment);
				cibilresponsepayload.setAccountSummary(accountSummary);
			}
			catch (Exception ec) 
			{
				code="600";
				message+="Unable to get Account Segment, ";
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || setting Account Details in Response "+ec);
			}
			/////////////////////////////// for Account Segment Details
			/////////////////////////////// TL///////////////////////////////////////////////////////

			/////////////////////////////for Employement Segment///////////////////////////////////
			List<EmploymentDetail>  employmentdetail=new ArrayList<EmploymentDetail>();  

//			String employeeSegment = Commons.getResponseFromTag("EM03E01", responseSegments, TURFresponse);	
			String employeeSegment = Commons.getResponseFromTag("EM03", responseSegments, TURFresponse);
			if(!"".equalsIgnoreCase(employeeSegment))
			{
				int completelength=7;
				EmploymentDetail empdetail=new EmploymentDetail();
				try
				{
					//accounttype
					String  accountType=employeeSegment.substring(completelength+4, completelength+6);
					empdetail.setAccountType(apendixAMap.containsKey(Integer.parseInt(accountType)+"")?apendixAMap.get(Integer.parseInt(accountType)+""):"");
					completelength=completelength+6;
					//accounttype


					//dateReported and certified
					if("02".equals(employeeSegment.substring(completelength,completelength+2))){
						String dateReportedandCertified=employeeSegment.substring(completelength+4,completelength+12);
						empdetail.setDateReportedandCertified(CibilUtil.getCibilDate(dateReportedandCertified));
						completelength=completelength+12;
					}
					//dateReported and certified

					//occupation code
					if("03".equals(employeeSegment.substring(completelength,completelength+2))){
						String occupationCode=employeeSegment.substring(completelength+4,completelength+6);
						if(occupationCode!=null)
						{
							if(occupationCode.equalsIgnoreCase("01"))
							{
								occupationCode="Salaried";
							}
							else if(occupationCode.equalsIgnoreCase("02"))
							{
								occupationCode="Self Employed Professional";
							}
							else if(occupationCode.equalsIgnoreCase("03"))
							{
								occupationCode="Self Employed";
							}
							else if(occupationCode.equalsIgnoreCase("04"))
							{
								occupationCode="Others";
							}
						}
						empdetail.setOccupationCode(occupationCode);
						completelength=completelength+6;

						//occupation code
					}

					//income   details
					if(employeeSegment.length()>=completelength+2 && "04".equals(employeeSegment.substring(completelength,completelength+2))){
						int incomelength=Integer.parseInt(	employeeSegment.substring(completelength+2,completelength+4));
						completelength=completelength+4;
						String income=employeeSegment.substring(completelength,completelength+incomelength);
						completelength=completelength+incomelength;
						empdetail.setIncome(income);
					}
					//income   details

					if("05".equals(employeeSegment.substring(completelength,completelength+2))){
						//Net gross Income indicator

						String grosssIncomeindicator=	employeeSegment.substring(completelength+4, completelength+5);	
						completelength=completelength+5;

						if(grosssIncomeindicator!=null && grosssIncomeindicator.equalsIgnoreCase("G"))
						{
							grosssIncomeindicator="Gross Income";
						}
						else if(grosssIncomeindicator!=null && grosssIncomeindicator.equalsIgnoreCase("N")) 
						{
							grosssIncomeindicator="Net Income";							
						}
						empdetail.setGrosssIncomeindicator(grosssIncomeindicator);
						//Net gross Income indicator
					}

					if("06".equals(employeeSegment.substring(completelength,completelength+2))){
						//Monthly income indicator
						String monthlyIncomeIndicator=employeeSegment.substring(completelength+4, completelength+5);

						if(monthlyIncomeIndicator!=null && monthlyIncomeIndicator.equalsIgnoreCase("M"))
						{
							monthlyIncomeIndicator="Monthly";
						}
						else if(monthlyIncomeIndicator!=null && monthlyIncomeIndicator.equalsIgnoreCase("A")) 
						{
							monthlyIncomeIndicator="Annual";							
						}
						empdetail.setMonthlyIncomeIndicator(monthlyIncomeIndicator);
						completelength=completelength+5;
						//Monthly income indicator
					}

					if("80".equals(employeeSegment.substring(completelength,completelength+2))){
						//Date of Entry for Error Code
						String errordateCode=employeeSegment.substring(completelength+4, completelength+12);
						completelength=completelength+12;
						empdetail.setErrordateCode(CibilUtil.getCibilDate(errordateCode));
						//Date of Entry for Error Code
					}

					if("82".equals(employeeSegment.substring(completelength,completelength+2))){
						//error code
						String errorcode=employeeSegment.substring(completelength+4, completelength+7);
						completelength=completelength+7;
						if(errorcode!=null)
						{
							errorcode=apendix_G_EM_Map.containsKey(errorcode)?apendix_G_EM_Map.get(errorcode):errorcode;
						}
						empdetail.setErrorcode(errorcode);
						//error code
					}

					if("83".equals(employeeSegment.substring(completelength,completelength+2))){
						//date of entry for cibil remarks code//
						String dateOfEntryforremarksCode=employeeSegment.substring(completelength+4,completelength+12);
						completelength=completelength+12;
						empdetail.setDateOfEntryforremarksCode(CibilUtil.getCibilDate(dateOfEntryforremarksCode));
						//date of entry for cibil remarks code//
					}

					if("84".equals(employeeSegment.substring(completelength,completelength+2))){
						//cibil remarks code//
						String cibilremarkscode=employeeSegment.substring(completelength+4,completelength+10);
						if(cibilremarkscode!=null)
						{
							cibilremarkscode=apendix_I_Map.containsKey(cibilremarkscode)?apendix_I_Map.get(cibilremarkscode):cibilremarkscode;
						}
						empdetail.setCibilremarkscode(cibilremarkscode);
						completelength=completelength+10;
						//cibil remarks code//
					}

					if("85".equals(employeeSegment.substring(completelength,completelength+2))){
						//date of entry for dispute code//
						String datedisputeRemarksCode=employeeSegment.substring(completelength+4,completelength+12);
						completelength=completelength+10;
						empdetail.setDatedisputeRemarksCode(CibilUtil.getCibilDate(datedisputeRemarksCode));
						//date of entry for dispute code//
					}

					if("86".equals(employeeSegment.substring(completelength,completelength+2))){
						//cibil dispute remarks code1
						String remarkscode1=employeeSegment.substring(completelength+4,completelength+10);
						completelength=completelength+10;
						if(remarkscode1!=null)
						{
							remarkscode1=apendix_H_Map.containsKey(remarkscode1)?apendix_H_Map.get(remarkscode1):remarkscode1;
						}
						empdetail.setRemarkscode1(remarkscode1);
						//cibil dispute remarks code1
					}

					if("87".equals(employeeSegment.substring(completelength,completelength+2))){
						//cibil dispute remarks code2
						String remarkscode2=employeeSegment.substring(completelength+4,completelength+10);
						if(remarkscode2!=null && remarkscode2.equalsIgnoreCase("000001"))
						{
							remarkscode2="Disputed accepted & under investigation";
						}
						empdetail.setRemarkscode2(remarkscode2);
						completelength=completelength+10;
						//cibil dispute remarks code2
					}
				 
				}
				
				catch(Exception ec)
				{
					logger.error("Exception in CibilApiServiceImpl || callCibilService() || accessing Employement Details "+ec);	
				}
				empdetail.setCibilresponsepayload(cibilresponsepayload);
				employmentdetail.add(empdetail);
			}
			cibilresponsepayload.setEmploymentdetail(employmentdetail);
			/////////////////////////////for Employement Segment///////////////////////////////////
			Set<String> purposeSet = new HashSet<String>(); 
			///////////////////////////////////// Enquiry Segment starts
			///////////////////////////////////// IQ///////////////////////////////////////////////////////
			ArrayList<String> enquirySegments = new ArrayList<String>();
			for (int i = 1; i <= 1000; i++) {
				String enquirySequence = Commons.convertCibilEnquiryListing(i);
				enquirySegments.add("IQ04I" + enquirySequence);
			}

			ArrayList<EnquiryDetails> enquiries = new ArrayList<EnquiryDetails>();

			try 
			{
				for (int i = 1; i <= 1000; i++) 
				{
					EnquiryDetails enquirydto = new EnquiryDetails();
					String enquirySequence = Commons.convertCibilEnquiryListing(i);
					String enQuiry = Commons.getResponseFromTag("IQ04I" + enquirySequence, enquirySegments,
							TURFresponse);
					if (!("".equals(enQuiry))) 
					{
						String enquiryDate = enQuiry.substring(12, 20);
						enquirydto.setDateOfEnquiry(CibilUtil.getCibilDate(enquiryDate));
						int enquiryNamelength = Integer.parseInt(enQuiry.substring(22, 24));
						String enquiryName = enQuiry.substring(24, (24 + enquiryNamelength));
						enquirydto.setEnquiryShortName(enquiryName);
						int completeLength = 24 + enquiryNamelength;
						String enquiryPurpose = enQuiry.substring(completeLength + 4, completeLength + 6);
						String purpose = apendixAMap.containsKey(Integer.parseInt(enquiryPurpose)+"")?apendixAMap.get(Integer.parseInt(enquiryPurpose)+""):"";
						purposeSet.add(purpose);
						enquirydto.setEnquiryPurpose(purpose);
						completeLength = completeLength + 6;
						int enquiryAmountLength = Integer.parseInt(enQuiry.substring(completeLength + 2, completeLength + 4));
						String enquiryAmount = enQuiry.substring(completeLength + 4,(completeLength + 4 + enquiryAmountLength));
						enquirydto.setEnquiryAmount(enquiryAmount);
						enquiries.add(enquirydto);
					}
					enquirydto.setCibilresponsepayload(cibilresponsepayload);
				}
			}
			catch(Exception ec)
			{
				logger.error("Exception in CibilApiServiceImpl || callCibilService() || accessing Enquiry Details "+ec);	
			}
			cibilresponsepayload.setEnquiries(enquiries);
			///////////////////////////////////// Enquiry Segment ends
			///////////////////////////////////// IQ///////////////////////////////////////////////////////

			///////////////////////////////////// Enquiry : Summary : Segment Start
			///////////////////////////////////// IQ///////////////////////////////////////////////////////
			List<EnquirySummary> enquiriesSummary = new ArrayList<EnquirySummary>();
			enquiriesSummary.add(CibilUtil.getEnquirySummary(cibilresponsepayload.getEnquiries()));
			
			for(String enqPurpose : purposeSet)
			{
				enquiriesSummary.add(CibilUtil.getEnquirySummarySpecific(cibilresponsepayload.getEnquiries(),enqPurpose,cibilresponsepayload));
			}
			
			cibilresponsepayload.setEnquiriesSummary(enquiriesSummary);
			///////////////////////////////////// IQ///////////////////////////////////////////////////////
			///////////////////////////////////// Enquiry : Summary : Segment ends

			/////////////////////////////for Consumer Dispute Remarks Segment : Start///////////////////////////////////
			String disputeSegment = Commons.getResponseFromTag("DR03", responseSegments, TURFresponse);	
			if(disputeSegment!=null && !"".equalsIgnoreCase(disputeSegment)	)
			{
				DisputeDetails disputes = new DisputeDetails();
				List<Remark> disputeRemark = new ArrayList<Remark>();
				int completelength=7;//DR03D01
				try
				{
					//Date of Entry
					String dateOfEntryCode = disputeSegment.substring(completelength, completelength+2);
					if(dateOfEntryCode!=null && dateOfEntryCode.equalsIgnoreCase("01"))
					{
						completelength=completelength+4;
						String  entryDate=disputeSegment.substring(completelength, completelength+8);
						completelength=completelength+8;
						disputes.setDisputeDate(entryDate);
					}
					if("02".equals(disputeSegment.substring(completelength,completelength+2))){
						int length=Integer.parseInt(disputeSegment.substring(completelength+2,completelength+4));
						completelength=completelength+4;
						String disputeRemarkkLine=disputeSegment.substring(completelength,completelength+length);
						Remark remark = new Remark();
						remark.setRemark(disputeRemarkkLine);
						completelength=completelength+length;
						disputeRemark.add(remark);
					}
					if("03".equals(disputeSegment.substring(completelength,completelength+2))){
						int length=Integer.parseInt(disputeSegment.substring(completelength+2,completelength+4));
						completelength=completelength+4;
						String disputeRemarkkLine=disputeSegment.substring(completelength,completelength+length);
						Remark remark = new Remark();
						remark.setRemark(disputeRemarkkLine);
						completelength=completelength+length;
						disputeRemark.add(remark);
					}
					if("04".equals(disputeSegment.substring(completelength,completelength+2))){
						int length=Integer.parseInt(disputeSegment.substring(completelength+2,completelength+4));
						completelength=completelength+4;
						String disputeRemarkkLine=disputeSegment.substring(completelength,completelength+length);
						Remark remark = new Remark();
						remark.setRemark(disputeRemarkkLine);
						completelength=completelength+length;
						disputeRemark.add(remark);
					}
					if("05".equals(disputeSegment.substring(completelength,completelength+2))){
						int length=Integer.parseInt(disputeSegment.substring(completelength+2,completelength+4));
						completelength=completelength+4;
						String disputeRemarkkLine=disputeSegment.substring(completelength,completelength+length);
						Remark remark = new Remark();
						remark.setRemark(disputeRemarkkLine);
						completelength=completelength+length;
						disputeRemark.add(remark);
					}
					if("06".equals(disputeSegment.substring(completelength,completelength+2))){
						int length=Integer.parseInt(disputeSegment.substring(completelength+2,completelength+4));
						completelength=completelength+4;
						String disputeRemarkkLine=disputeSegment.substring(completelength,completelength+length);
						Remark remark = new Remark();
						remark.setRemark(disputeRemarkkLine);
						completelength=completelength+length;
						disputeRemark.add(remark);
					}
					if("07".equals(disputeSegment.substring(completelength,completelength+2))){
						int length=Integer.parseInt(disputeSegment.substring(completelength+2,completelength+4));
						completelength=completelength+4;
						String disputeRemarkkLine=disputeSegment.substring(completelength,completelength+length);
						Remark remark = new Remark();
						remark.setRemark(disputeRemarkkLine);
						completelength=completelength+length;
						disputeRemark.add(remark);
					}
				}
				catch(Exception ec)
				{
					logger.error("Exception in CibilApiServiceImpl || callCibilService() || accessing disputes details "+ec);	
				}

				disputes.setDisputeRemark(disputeRemark);
				cibilresponsepayload.setDisputes(disputes);
			}
			/////////////////////////////for Consumer Dispute Remarks Segment : End///////////////////////////////////

			//			DisputeDetails disputes = new DisputeDetails();
			//			disputes.setDisputeDate("17082017");
			//			List<Remark> disputeRemark = new ArrayList<Remark>();
			//			Remark remark = new Remark();
			//			remark.setRemark("Jai HO");
			//			disputeRemark.add(remark);
			//			disputeRemark.add(remark);
			//			disputes.setDisputeRemark(disputeRemark);
			//			cibilresponsepayload.setDisputes(disputes);


			/////////////////////////////for End Segment///////////////////////////////////
			String endSegment = Commons.getResponseFromTag("ES07", responseSegments, TURFresponse);	
			if(endSegment!=null 
					&& !"".equalsIgnoreCase(endSegment)
					&& endSegment.length()==17
					)
			{
				String lenOfTransmission="";
				try
				{
					//length Of Transmission
					lenOfTransmission=endSegment.substring(4,11);
				}
				catch(Exception ec)
				{
					logger.error("Exception in CibilApiServiceImpl || callCibilService() || accessing End Segment "+ec);	
				}
				cibilresponsepayload.setLenOfTransmission(lenOfTransmission);
			}
			/////////////////////////////for END Segment///////////////////////////////////

			
			if(multipleResponseStr!=null && multipleResponseStr.size()>1)
			{
				List<AdditionalMatchDetails> additionalMatchDetails = new ArrayList<AdditionalMatchDetails>();
				for(int z=1; z< multipleResponseStr.size();z++)
				{
					TURFresponse = multipleResponseStr.get(z);
					AdditionalMatchDetails aMDetails = new AdditionalMatchDetails();
					
					
					
					NamingSegment = Commons.getResponseFromTag("PN03N01", responseSegments, TURFresponse);
					try 
					{
						String restDatafromTag;
						if (!"".equals(NamingSegment)) 
						{
							restDatafromTag = NamingSegment.substring(7, NamingSegment.length());
							int completelength = 0;
							////Exclusion Code Start
							if ("01".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
								String value = restDatafromTag.substring(completelength + 2, completelength + 4);
								String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
								aMDetails.setName1(name.replaceAll("[0-9]", ""));
								completelength = completelength + 4 + Integer.parseInt(value);
							}
							if ("02".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
								String value = restDatafromTag.substring(completelength + 2, completelength + 4);
								String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
								aMDetails.setName2(name.replaceAll("[0-9]", ""));
								completelength = completelength + 4 + Integer.parseInt(value);
							}
							if ("03".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
								String value = restDatafromTag.substring(completelength + 2, completelength + 4);
								String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
								aMDetails.setName3(name.replaceAll("[0-9]", ""));
								completelength = completelength + 4 + Integer.parseInt(value);
							}
							if ("04".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
								String value = restDatafromTag.substring(completelength + 2, completelength + 4);
								String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
								aMDetails.setName4(name.replaceAll("[0-9]", ""));
								completelength = completelength + 4 + Integer.parseInt(value);
							}
							if ("05".equals(CibilUtil.getSubString(completelength, completelength + 2, restDatafromTag))) {
								String value = restDatafromTag.substring(completelength + 2, completelength + 4);
								String name = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
								aMDetails.setName5(name.replaceAll("[0-9]", ""));
								completelength = completelength + 4 + Integer.parseInt(value);
							}
							if ("0708".equals(CibilUtil.getSubString(completelength, completelength + 4, restDatafromTag))) {
								String value = "08";
								String dateofBirth = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
								aMDetails.setDateofBirth(CibilUtil.getCibilDate(dateofBirth));
								completelength = completelength + 4 + Integer.parseInt(value);
							}
							if ("0801".equals(CibilUtil.getSubString(completelength, completelength + 4, restDatafromTag))) {
								String value = "01";
								String gender = restDatafromTag.substring(completelength + 4, completelength + 4 + Integer.parseInt(value));
								aMDetails.setGender(gender);
								completelength = completelength + 4 + Integer.parseInt(value);
							}
						}
					} catch (Exception ec) {
						code="600";
						message+="Unable to get Name Segemnt, ";
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						logger.error("Exception in CibilApiServiceImpl || callCibilService() || Additional Match Details --> Name Segment "+ec);
					}
					
					/////////////////////////// Naming Segment End/////////////////////////////////////////////


					/////////////////////////ID Segments  8/10/2017 Starts/////////////////////////////
					IdSegments=Commons.getResponseFromTag("ID03", responseSegments, TURFresponse);
					ArrayList<IDDetails> idDetailsN=new ArrayList<IDDetails>();
					try
					{
						for(int i=1;i<8;i++)
						{  
							String requiredTag = "ID03I0" + i;
							String singleIdSegment = Commons.getResponseFromTag1(requiredTag, idArray, IdSegments);
							
							if (!"".equals(singleIdSegment)) 
							{
								IDDetails  iddetails=new IDDetails(); 	
								try
								{
									
									String idType=singleIdSegment.substring(11,13);
									String idValue=idTypeToValue.get(idType);
									iddetails.setIdType(idValue);
									String idnumberlength=singleIdSegment.substring(15,17);
									int  idnumberlengthint=Integer.parseInt(idnumberlength);
									String idNumber=singleIdSegment.substring(17,(17+idnumberlengthint));
									iddetails.setIdNumber(idNumber);
									int currentlength=17+idnumberlengthint;
									String issueDate=singleIdSegment.substring(currentlength+4,currentlength+12);
									iddetails.setIssueDate(issueDate);
									currentlength=currentlength+12;
									String expirationdate=singleIdSegment.substring(currentlength+4, currentlength+12);
									iddetails.setExpirationDate(expirationdate);	
									
								}
								catch(Exception ec)
								{
									
									logger.error("Exception in CibilApiServiceImpl || callCibilService() || accesing ID Segment "+ec);
								}
								iddetails.setCibilresponsepayload(cibilresponsepayload);
								idDetailsN.add(iddetails);
							}
							
						}
						
					}
					catch(Exception ec)
					{
						code="600";
						message+="Unable to get ID Segment, ";
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						logger.error("Exception in CibilApiServiceImpl || callCibilService() || accesing ID Segment "+ec);
					}
					aMDetails.setIddetails(idDetailsN);
					/////////////////////////ID Segments  8/10/2017 Ends/////////////////////////////



					////////////////////////////////Email Contact Segment  Starts   8/10/2017 ///////////////////////////

					emailIdSegment=Commons.getResponseFromTag("EC03", responseSegments, TURFresponse);
					ArrayList<EmailContact>  emailcontactsN=new ArrayList<EmailContact>();
					try
					{
						for(int i=1;i<=4;i++)
						{
							String requiredTag = "EC03C0" + i;
							String singleEmailIdSegment = Commons.getResponseFromTag1(requiredTag, emailArray, emailIdSegment);
							if (!"".equals(singleEmailIdSegment))
							{
								EmailContact  emailcontact=new EmailContact();
								try
								{
									String emailIdLength =singleEmailIdSegment.substring(9, 11);
									int emailIdlength=Integer.parseInt(emailIdLength);
									String emailId=singleEmailIdSegment.substring(11, 11+emailIdlength);
									emailcontact.setEmailId(emailId);
								}
								catch(Exception ec)
								{
									logger.error("Exception in CibilApiServiceImpl || callCibilService() || accesing Email Segment "+ec);
								}
								emailcontact.setCibilresponsepayload(cibilresponsepayload);
								emailcontactsN.add(emailcontact);
							}
						}
					}
					catch(Exception ec)
					{
						code="600";
						message+="Unable to get Email Segment, ";
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						logger.error("Exception in CibilApiServiceImpl || callCibilService() || accesing Email Segment "+ec);
					}
					aMDetails.setEmailcontact(emailcontactsN);
					////////////////////////////////Email Contact Segment  Ends      8/10/2017  ///////////////////////////


					////////////////////////////////Telephone Contact Segment  Start ///////////////////////////
					ptSegment = Commons.getResponseFromTag("PT03", responseSegments, TURFresponse);
					ArrayList<TelePhone> telephonesN = new ArrayList<TelePhone>();
					try {
						for (int i = 1; i <= 4; i++) {
							try {
								TelePhone tphone = new TelePhone();
								String requiredTag = "PT03T0" + i;
								String singleaddressSegment = Commons.getResponseFromTag1(requiredTag, addressArray, ptSegment);
								if (!"".equals(singleaddressSegment)) {
									int phoneNumberSize = Integer.parseInt(singleaddressSegment.substring(9, 11));
									String telephoneNumber = singleaddressSegment.substring(11, 11 + phoneNumberSize);
									tphone.setTelephoneNumber(telephoneNumber);

									String isExtension = singleaddressSegment.substring(11 + phoneNumberSize,11 + phoneNumberSize+2);
									boolean isExtensionAvailable =false;
									int telephoneExtnsize=0;
									if(isExtension!=null && isExtension.equalsIgnoreCase("02"))
									{
										isExtensionAvailable=true;
										telephoneExtnsize = Integer.parseInt(
												singleaddressSegment.substring(13 + phoneNumberSize, 15 + phoneNumberSize));
										String telephoneExtn = singleaddressSegment.substring(15 + phoneNumberSize,
												15 + phoneNumberSize + telephoneExtnsize);
										tphone.setTelephoneExtn(telephoneExtn);
									}
									String telephonetype="";
									if(isExtensionAvailable)
									{
										telephonetype = singleaddressSegment.substring(
												19 + phoneNumberSize + telephoneExtnsize, 21 + phoneNumberSize + telephoneExtnsize);
									}
									else
									{
										telephonetype = singleaddressSegment.substring(
												13 + phoneNumberSize + telephoneExtnsize, 15 + phoneNumberSize + telephoneExtnsize);
									}
									if(telephonetype!=null && !telephonetype.equalsIgnoreCase(""))
									{
										if(telephonetype.equalsIgnoreCase("00"))
										{
											telephonetype="Not Classified";
										}
										else if(telephonetype.equalsIgnoreCase("01"))
										{
											telephonetype="Mobile Phone";
										}
										else if(telephonetype.equalsIgnoreCase("02"))
										{
											telephonetype="Home Phone";
										}
										if(telephonetype.equalsIgnoreCase("03"))
										{
											telephonetype="Office Phone";
										}
									}
									tphone.setTelephoneType(telephonetype);
									telephonesN.add(tphone);
								}
							} catch (Exception ec) {
								logger.error("Exception in CibilApiServiceImpl || callCibilService() || accessing Telephone Segment "+ec);
							}
						}
						aMDetails.setTelephones(telephonesN);
					} catch (Exception ec) {
						code="600";
						message+="Unable to get Telephone Segment, ";
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting Telephone Segment "+ec);
					}
					////////////////////////////////Telephone Contact Segment  End ///////////////////////////
					
					//////////Address Start/////////
					List<AddressDetails> addressResponseDTOsN = new ArrayList<AddressDetails>();
					try 
					{
						String address1 = Commons.getResponseFromTag("PA03A01", addressegments, TURFresponse);
						AddressDetails addressDetails= Commons.addressResponseDTO(address1);
						if(addressDetails!=null)
						{
							addressResponseDTOsN.add(addressDetails);
						}
						String address2 = Commons.getResponseFromTag("PA03A02", addressegments, TURFresponse);
						addressDetails= Commons.addressResponseDTO(address2);
						if(addressDetails!=null)
						{
							addressResponseDTOsN.add(addressDetails);
						}
						String address3 = Commons.getResponseFromTag("PA03A03", addressegments, TURFresponse);
						addressDetails= Commons.addressResponseDTO(address3);
						if(addressDetails!=null)
						{
							addressResponseDTOsN.add(addressDetails);
						}				
						String address4 = Commons.getResponseFromTag("PA03A04", addressegments, TURFresponse);
						addressDetails= Commons.addressResponseDTO(address4);
						if(addressDetails!=null)
						{
							addressResponseDTOsN.add(addressDetails);
						}
						addressDetails.setCibilresponsepayload(cibilresponsepayload);
						aMDetails.setAddresses(addressResponseDTOsN);
					}
					catch (Exception ec)
					{
						code="600";
						message+="Unable to get Address Segment, ";
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting Address Segment in Response "+ec);
					}
					//////////Address End/////////
									
					/////////////////////////////for End Segment///////////////////////////////////
					endSegment = Commons.getResponseFromTag("ES07", responseSegments, TURFresponse);	
					if(endSegment!=null 
							&& !"".equalsIgnoreCase(endSegment)
							&& endSegment.length()==17
							)
					{
						String lenOfTransmission="";
						try
						{
							//length Of Transmission
							lenOfTransmission=endSegment.substring(4,11);
						}
						catch(Exception ec)
						{
							logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting End Segment "+ec);	
						}
						aMDetails.setLenOfTransmission(lenOfTransmission);
					}
					/////////////////////////////for END Segment///////////////////////////////////

					additionalMatchDetails.add(aMDetails);
				}
				cibilresponsepayload.setAdditionalMatchDetails(additionalMatchDetails);
			}
			
			/////////////////Added 11-August-2017 starts//////////////////////
			String memberreferencenumber=env.getString("cibil.userID.referenceNumber");
			String userID=env.getString("cibil.userID");
			cibilresponsepayload.setMemberId(userID); 
			cibilresponsepayload.setMemberRefNo(memberreferencenumber);
			/////////////////Added 11-August-2017 Ends//////////////////////


			////////////////////////code for byte array starts////////////////////////////////////////////
			//Added By Rohit Kumar on 19-August-2017 Starts//
			ObjectMapper  omapper=new ObjectMapper();
			cibilapiresponse.setPayload(cibilresponsepayload);
			String responseJsontemporarToCreatePdf=omapper.writeValueAsString(cibilapiresponse);
			//Added By  Rohit Kumar on 19-August-2017 Ends //

			/*Changed BY anuj*/
			
			String requestjson=omapper.writeValueAsString(apiRequest);//apiRequest
			/*String requestjson=apiRequest.toString();*/
			/*end*/
			ConvertUrltoByteArray   converturltobytearray=new ConvertUrltoByteArray();
			String url_temp=env.getString("com.qc.liveURL")+"/getCibilConsumerReport";
			tempRequestJson=requestjson;
			String htmlData=converturltobytearray.getingByteArray1(url_temp ,requestjson, "requestJson",responseJsontemporarToCreatePdf, "responseFromService");
			/*String pdfByteArr=*/cibilutilint.convertHtmlToPDF(htmlData,"CibilReport"+UniqueId.getUniqueId(),cibilresponsepayload);
			//cibilresponsepayload.setPdfByteArray(pdfByteArr);

			//////////////////////////////////////////////codefor byte array ends//////////////////////////////////////

		} 
		catch (Exception ec) 
		{
			code="600";
			message+="Unable to get Response data, ";
			errorInfo.setStatus(ERRORSTATUS.FAILURE);
			logger.error("Exception in CibilApiServiceImpl || callCibilService() || Setting Entry Details in Response || Unable to get Response data "+ec);
		}
		if(errorInfo.getStatus()!=null && errorInfo.getStatus().equals(ERRORSTATUS.FAILURE))
		{
			errorInfo.setCode(code);
			errorInfo.setMessage("Failure or Partial Success");
			errorInfo.setDescription(message);
			cibilapiresponse.setErrorInfo(errorInfo);
		}
		else
		{
			errorInfo.setCode("200");
			errorInfo.setStatus(ERRORSTATUS.SUCCESS);
			errorInfo.setDescription("Successfully generated the Response");
			errorInfo.setMessage("Success");
		}
		
	    try 
	    {
			if(cibilresponsepayload.getPdfPath()!=null) 
			{
			    rq.setPdfPath(cibilresponsepayload.getPdfPath());
			    rq.setPrice(/*env.getString("")*/"1");
			}
			else 
			{
			    rq.setPrice(/*env.getString("")*/"0");
			}
		} 
	    catch (Exception e)
	    {
			logger.error("Pdf Path and Price Not set");
		}

		cibilapiresponse.setPayload(cibilresponsepayload);
		cibilapiresponse.setErrorInfo(errorInfo);
		logger.info("CibilApiServiceImpl || callCibilService() || method ENDS ");
		return cibilapiresponse;
	
	}
	private static String getRealTimefromCibilTime(String cibilTime)
	{
		try
		{
			String min = cibilTime.substring(0, 2);
			String sec = cibilTime.substring(2, 4);
			String milis = cibilTime.substring(4, 6);
			return min + ":" + sec + ":" + milis;
		}
		catch (Exception e)
		{
			logger.error("Exception in CibilApiServiceImpl || getRealTimefromCibilTime() || manupulating cibilTime "+e);
			return "";
		}

	}
	public static String getCibilScoreFactors(List<ScoringFactors> scoringFactors)
	{
		String factors ="";
		int i=1;
		for(ScoringFactors scfactors : scoringFactors)
		{
			factors+=(i++)+" : "+scfactors.getFactor()+" || ";
		}
		if(!factors.equals(""))
		{
			factors=factors.substring(0,factors.length()-3);
		}
		return factors;
	}
}
