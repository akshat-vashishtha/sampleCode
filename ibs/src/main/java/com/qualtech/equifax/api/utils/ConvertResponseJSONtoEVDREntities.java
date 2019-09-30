package com.qualtech.equifax.api.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.equifax.api.common.dto.EquifaxEVDRTrackerDTO;
import com.qualtech.equifax.api.db.DBEquifax;
import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRAddresInfo;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDREmailDetails;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRNsdlResponse;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRPanDetails;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRPersonalInfo;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRPhoneInfo;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRVoterDetails;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRVoterRequest;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRVoterResponse;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRnsdlRequestEntity;
import com.qualtech.equifax.api.request.EquifaxAPI_EVDRRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;
@Service
public class ConvertResponseJSONtoEVDREntities implements ConvertResponseJSONtoEVDREntitiesInt 
{
	private static Logger logger = Logger.getLogger(ConvertResponseJSONtoEVDREntities.class);
	@Autowired DBEquifax dbEquifax;
	
	public EquifaxEvdrAllDetails equifaxEvdrdetailLogs(String lowerjson,
			EquifaxApiResponse  equifaxAPIResponse,
			EquifaxAPI_EVDRRequest equifaxapiRequest,
			//  EquifaxEVDRDetailLogsDBService  evdrdetailService,
			EquifaxEVDRTrackerDTO equifaxevdrtracker)
	{
		
		JSONObject lowerJsonObject=null;
		try 
		{
			lowerJsonObject = new JSONObject(lowerjson);
		}
		catch (JSONException e)
		{
			logger.info("exception while getting jsonObject in lowerjson ::"+e);
		}
		logger.debug("lowerjson : "+lowerjson);
		EquifaxEvdrAllDetails equifaxevdrDetailLogs=new EquifaxEvdrAllDetails();
		equifaxevdrDetailLogs.setTracker_id("");
		JSONObject enquiryResponse=new JSONObject();

		try
		{
			enquiryResponse=lowerJsonObject.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse");
		}
		catch(Exception ec){logger.error("There is error while getting enquiry response in EVDR");}
		//////////////////////////////////////Addresses///////////////////////////////////////////////
		Set<EquifaxEVDRAddresInfo> addresses=new HashSet<EquifaxEVDRAddresInfo>();
		try
		{

			JSONArray addressesjson= enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONArray("addressinfo");
			for(int i=0;i<addressesjson.length();i++)
			{
				EquifaxEVDRAddresInfo addressInfo=new EquifaxEVDRAddresInfo();                   
				try
				{
					JSONObject addressJSON=addressesjson.getJSONObject(i);
					//EquifaxEVDRAddresInfo addressInfo=new EquifaxEVDRAddresInfo();
					//addressInfo.setAddress_info_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_adrss_seq)));
					addressInfo.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
					addressInfo.setAddress(addressJSON.get("address").toString());
					addressInfo.setPostal(addressJSON.get("postal").toString());
					addressInfo.setReported_date(addressJSON.get("reporteddate").toString());
					addressInfo.setState(addressJSON.get("state").toString());
					addressInfo.setType(addressJSON.get("type").toString());
					addressInfo.setSeq(addressJSON.get("seq").toString());
					addresses.add(addressInfo);
				}
				catch(Exception ec)
				{
					logger.error("There is error while getting single address"+ec);
				}
			}

		}
		catch(Exception ec)
		{
			logger.error("There is error while getting address info"+ec);
		}
		
		equifaxevdrDetailLogs.setAddresses(addresses);
		/////////////////////////////////////Addresses////////////////////////////////////////////////




		equifaxevdrDetailLogs.setCreated_time(new Date());


		/////////////////////////////////////Disclaimer///////////////////////////////////////////////
		try
		{
			String disclaimer=enquiryResponse.getJSONObject("reportdata").get("disclaimer").toString();	  
			equifaxevdrDetailLogs.setDisclaimer(disclaimer);
		}
		catch(Exception ec)
		{
			logger.error("There is error while getting disclaimer"+ec);
		}
		/////////////////////////////////////Disclaimer///////////////////////////////////////////////


		//////////////////////////////////Voter Request///////////////////////////////////////////////
		EquifaxEVDRVoterRequest evdrRequest=new EquifaxEVDRVoterRequest();
		try
		{
			//evdrRequest.setVoter_request_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_voterrq_seq)));
			evdrRequest.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
			JSONObject evdrRequestJSON=  enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONObject("vidvoterresponses").getJSONObject("voterrequest");
			evdrRequest.setSource(evdrRequestJSON.get("source").toString());
			evdrRequest.setVoter_id(evdrRequestJSON.get("voterid").toString());

		}
		catch(Exception ec)
		{
			logger.error("There is error while saving data of disclaimer"+ec);
		}
		equifaxevdrDetailLogs.setEquifaxevdrvoterrequest(evdrRequest);
		//////////////////////////////////Voter Request///////////////////////////////////////////////
		EquifaxEVDRVoterResponse voterResponse=new EquifaxEVDRVoterResponse();
		try
		{
			//voterResponse.setVoter_response_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_voterrs_seq)));
			voterResponse.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
			JSONObject evdrVoterResponseJson= enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONObject("vidvoterresponses").getJSONObject("voterresponse");
			voterResponse.setReturn_code(evdrVoterResponseJson.get("returncode").toString());
			voterResponse.setTitle(evdrVoterResponseJson.get("title").toString());
			voterResponse.setVoter(evdrVoterResponseJson.get("voter").toString());

		}
		catch(Exception ec)
		{
			logger.error("There is error while saving data into voter response"+ec);
		}
		equifaxevdrDetailLogs.setEquifaxevdrvoterresponse(voterResponse);

		/////////////////////////////Identities/////////////////////////////////////////////////////////
	
			
		///////////////////////for saving data for PAN////////////////////////////////////////
		try{
			JSONArray panDetail=null;
			try{
				panDetail=enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONArray("panid");
			}catch(Exception ee){
				panDetail=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONObject("panid"));
			}
			Set<EquifaxEVDRPanDetails> evdrPanDetails=new HashSet<EquifaxEVDRPanDetails>();
			for(int i=0;i<panDetail.length();i++)
			{
				JSONObject pan=panDetail.getJSONObject(i);
				EquifaxEVDRPanDetails evdrPanDetail=new EquifaxEVDRPanDetails();
				try
				{
					//evdrPanDetail.setPandetail_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_evdr_pan_sequence)));//equifaxPcsDetahilsLogsDBservice.getSequenceValueForAccountDetaisl());
					evdrPanDetail.setPanSeq(pan.get("seq").toString());
					evdrPanDetail.setPanNumber(pan.get("idnumber").toString());
					evdrPanDetail.setPanReportedDate(pan.get("reporteddate").toString());
					evdrPanDetail.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for EVDRPanDetails "+ec);
				}
		
				evdrPanDetails.add(evdrPanDetail);
			}
			equifaxevdrDetailLogs.setEvdrPanDetails(evdrPanDetails);
		}catch(Exception ec)
		{
			logger.error("There is error while saving data for EVDRPanDetails "+ec);
		}
		/////////////////////for saving data for PAN////////////////////////////////////////

			
			

		///////////////////////for saving data for Voter////////////////////////////////////////
		try{
			JSONArray voterDetail=null;
			try{
				voterDetail=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONObject("voterid"));
			}catch(Exception ee){
				voterDetail=enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONArray("voterid");
			}
			Set<EquifaxEVDRVoterDetails> evdrVoterDetails=new HashSet<EquifaxEVDRVoterDetails>();
			EquifaxEVDRVoterDetails evdrVoterDetail=new EquifaxEVDRVoterDetails();
			for(int i=0;i<voterDetail.length();i++)
			{
				JSONObject voter=voterDetail.getJSONObject(i);
			try
			{
				//evdrVoterDetail.setVoterdetail_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_evdr_voter_sequence)));//equifaxPcsDetahilsLogsDBservice.getSequenceValueForAccountDetaisl());
				evdrVoterDetail.setVoterIdSeq(voter.get("seq").toString());
				evdrVoterDetail.setVoterIdNum(voter.get("idnumber").toString());
				evdrVoterDetail.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
				evdrVoterDetail.setVoterIdreRorteddate(voter.get("reporteddate").toString());
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data for voter detail"+ec);
			}

			evdrVoterDetails.add(evdrVoterDetail);
			}
			equifaxevdrDetailLogs.setEvdrVoterDetails(evdrVoterDetails);
		}catch(Exception ec)
		{
			logger.error("There is error while saving data for voter detail"+ec);
		}

		//////////////////////for saving data for voter///////////////////////////////////////// 

		

		///////////////////////for saving data for Phone////////////////////////////////////////
		try{
			JSONArray phoneDetail=null;
			try{
				phoneDetail=enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONArray("phoneinfo");
			}catch(Exception ee){
				phoneDetail=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONArray("phoneinfo"));
			}
			Set<EquifaxEVDRPhoneInfo> evdrPhoneDetails=new HashSet<EquifaxEVDRPhoneInfo>();
			for(int i=0;i<phoneDetail.length();i++)
			{
				JSONObject phone=phoneDetail.getJSONObject(i);
				EquifaxEVDRPhoneInfo evdrPhoneDetail=new EquifaxEVDRPhoneInfo();
				try
				{
					//evdrPhoneDetail.setPhone_info_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_phn_seq)));//equifaxPcsDetahilsLogsDBservice.getSequenceValueForAccountDetaisl());
					evdrPhoneDetail.setType_code(phone.get("typecode").toString());
					evdrPhoneDetail.setPhoneSeq(phone.get("seq").toString());
					evdrPhoneDetail.setNumber(phone.get("number").toString());
					evdrPhoneDetail.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
					evdrPhoneDetail.setReported_date(phone.get("reporteddate").toString());
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for evdrPhoneDetail"+ec);
				}

				evdrPhoneDetails.add(evdrPhoneDetail);
			}
			equifaxevdrDetailLogs.setEvdrPhoneDetails(evdrPhoneDetails);
		}catch(Exception ec)
		{
			logger.error("There is error while saving data for evdrPhoneDetail"+ec);
		}
		
		/////////////////////for saving data for Phone////////////////////////////////////////
		
		///////////////////////for saving data for Email////////////////////////////////////////
		try{
		JSONArray emailDetail=null;
		try{
			emailDetail=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("emailaddressinfo"));
		}catch(Exception ee){
			 emailDetail=enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONArray("emailaddressinfo");
		}
		
		Set<EquifaxEVDREmailDetails> evdrEmailDetails=new HashSet<EquifaxEVDREmailDetails>();
		for(int i=0;i<emailDetail.length();i++)
		{
			JSONObject email=emailDetail.getJSONObject(i);
			EquifaxEVDREmailDetails evdrEmailDetail=new EquifaxEVDREmailDetails();
			try
			{
				//evdrEmailDetail.setEmailDetails_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_evdr_email_sequence)));
				evdrEmailDetail.setEmailaddress(email.get("emailaddress").toString());
				evdrEmailDetail.setSeq(email.get("seq").toString());
				evdrEmailDetail.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
				evdrEmailDetail.setReporteddate(email.get("reporteddate").toString());
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data for evdrEmailDetails"+ec);
			}
		
			evdrEmailDetails.add(evdrEmailDetail);
		}
		equifaxevdrDetailLogs.setEvdrEmailDetails(evdrEmailDetails);
		}catch(Exception ec)
		{
		logger.error("There is error while saving data for evdrEmailDetails"+ec);
		}
		
		/////////////////////for saving data for Email////////////////////////////////////////
		
		/////////////////////////////for Personal Info Identity/////////////////////////////////////////

		EquifaxEVDRPersonalInfo nsdlpersonalInfo=new EquifaxEVDRPersonalInfo();
		try
		{
			nsdlpersonalInfo.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
			//nsdlpersonalInfo.setPersonal_info_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_pinfo_seq)));//evdrdetailService.getSequenceValueForEVDRprsnlInfoSequence());
			JSONObject personalInfo= enquiryResponse.getJSONObject("reportdata").getJSONObject("idandcontactinfo").getJSONObject("personalinfo");
			nsdlpersonalInfo.setAge(personalInfo.getJSONObject("age").get("age").toString());
			nsdlpersonalInfo.setDate_of_birth(personalInfo.get("dateofbirth").toString());
			nsdlpersonalInfo.setFirst_name(personalInfo.getJSONObject("name").get("firstname").toString());
			nsdlpersonalInfo.setGender(personalInfo.get("gender").toString());
			nsdlpersonalInfo.setLast_name(personalInfo.getJSONObject("name").get("lastname").toString());
			nsdlpersonalInfo.setMiddle_name(personalInfo.getJSONObject("name").get("middlename").toString());
			nsdlpersonalInfo.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
			nsdlpersonalInfo.setOccupation(personalInfo.get("occupation").toString());
			nsdlpersonalInfo.setTotalincome(personalInfo.get("totalincome").toString());
		}
		catch(Exception ec)
		{
			logger.error("There is error while saving personal info data"+ec);
		}
		equifaxevdrDetailLogs.setNsdlPersonalInfoEntity(nsdlpersonalInfo);
		////////////////////////////for Personal Info Identity/////////////////////////////////////////



		
		////////////////////////for NSDL Request ////////////////////////////////
		try{
			JSONArray nsdlRequestCheck=null;
			JSONArray nsdlRequest=null;
			try{
				nsdlRequest=enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONArray("vidnsdlresponses");
			}catch(Exception ee){
				nsdlRequestCheck=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONObject("vidnsdlresponses"));
				nsdlRequest= new JSONArray(nsdlRequestCheck.toString());
			}
			Set<EquifaxEVDRnsdlRequestEntity> evdrNsdlRequestDetails=new HashSet<EquifaxEVDRnsdlRequestEntity>();
			for(int i=0; i < nsdlRequest.length(); i++) {
				JSONObject	nsdlReq = nsdlRequest.getJSONObject(i).getJSONObject("nsdlrequest");
				EquifaxEVDRnsdlRequestEntity nsdlRequestEntity=new EquifaxEVDRnsdlRequestEntity();
				try
				{
					//nsdlRequestEntity.setNsdl_request_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_nsdlreq_seq)));//evdrdetailService.getSequenceValueForEVDRnsdlreqSequence());   
					nsdlRequestEntity.setPan_number(nsdlReq.get("pannumber").toString());
					nsdlRequestEntity.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
					nsdlRequestEntity.setSource(nsdlReq.get("source").toString());
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for EVDRPanDetails "+ec);
				}
		
				evdrNsdlRequestDetails.add(nsdlRequestEntity);
			}
			equifaxevdrDetailLogs.setNsdlRequestEntity(evdrNsdlRequestDetails);
		}catch(Exception ec)
		{
			logger.error("There is error while saving data for EVDRPanDetails "+ec);
		}
		/////////////////////for NSDL Request////////////////////////////////////
		
		
		
		////////////////////////for NSDL Response ////////////////////////////////
		try{
			JSONArray nsdlResponseCheck=null;
			JSONArray nsdlResponse=null;
			try{
				nsdlResponse=enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONArray("vidnsdlresponses");
			}catch(Exception ee){
				nsdlResponseCheck=new JSONArray().put(enquiryResponse.getJSONObject("reportdata").getJSONObject("verifyidresponse").getJSONObject("vidnsdlresponses"));//.getJSONObject("nsdlresponse"));
				nsdlResponse= new JSONArray(nsdlResponseCheck.toString());
			}
			Set<EquifaxEVDRNsdlResponse> evdrNsdlResponseDetails=new HashSet<EquifaxEVDRNsdlResponse>();
			for(int i=0; i < nsdlResponse.length(); i++) {
				JSONObject	nsdlRes = nsdlResponse.getJSONObject(i).getJSONObject("nsdlresponse");
				EquifaxEVDRNsdlResponse nsdl=new EquifaxEVDRNsdlResponse();
				try
				{
					//nsdl.setNsdl_response_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_nsdlrps_seq)));//equifaxPcsDetahilsLogsDBservice.getSequenceValueForAccountDetaisl());
					nsdl.setFirst_name(nsdlRes.get("firstname").toString());
					nsdl.setLast_name(nsdlRes.get("lastname").toString());
					nsdl.setLast_updated_date(nsdlRes.get("lastupdateddate").toString());
					nsdl.setPan(nsdlRes.get("pan").toString());
					nsdl.setPercentage_match(nsdlRes.get("percentmatch").toString());
					nsdl.setReturn_code(nsdlRes.get("returncode").toString());
					nsdl.setReturn_code_desc(nsdlRes.get("returncodedesc").toString());
					nsdl.setTitle("NA");
					nsdl.setEquifaxevdrdetails_logs(equifaxevdrDetailLogs);
					nsdl.setPanStatus(nsdlRes.get("panstatus").toString());
					nsdl.setNsdlResId(nsdlRes.get("nsdlrespid").toString());
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for EVDRPanDetails "+ec);
				}
		
				evdrNsdlResponseDetails.add(nsdl);
			}
			equifaxevdrDetailLogs.setNsdlResponseEntity(evdrNsdlResponseDetails);
		}catch(Exception ec)
		{
			logger.error("There is error while saving data for EVDRPanDetails "+ec);
		}
		/////////////////////for NSDL Response////////////////////////////////////
		
		

		equifaxevdrDetailLogs.setRemarks("Success");
		equifaxevdrDetailLogs.setRequest_info_json(equifaxapiRequest.toString());
		//equifaxevdrDetailLogs.setRequest_unique_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_equifax_evdr_sequence)));//evdrdetailService.getSequenceValue());
		equifaxevdrDetailLogs.setRequest_xml(equifaxevdrtracker.getRequestXML());

		try
		{
			JSONObject inquiryresponseheader=	lowerJsonObject.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("inquiryresponseheader"); 

			equifaxevdrDetailLogs.setResponse_date(inquiryresponseheader.get("date").toString());
			equifaxevdrDetailLogs.setResponse_order_no(inquiryresponseheader.get("reportorderno").toString());
			equifaxevdrDetailLogs.setResponse_time(inquiryresponseheader.get("time").toString());
			equifaxevdrDetailLogs.setRequest_json(equifaxevdrtracker.getRequest_json());
			equifaxevdrDetailLogs.setResponse_json(equifaxevdrtracker.getResponse_json());
			equifaxevdrDetailLogs.setHtmlData(equifaxevdrtracker.getHtmlData());
			equifaxevdrDetailLogs.setPdf_byte_Array(equifaxevdrtracker.getPdf_byte_Array());
			
		}
		catch(Exception ec)
		{
			logger.error("There is error while saving data from enquiry response header");
		}
		equifaxevdrDetailLogs.setResponse_error_info_json(equifaxAPIResponse.getErrorInfo().toString());
		equifaxevdrDetailLogs.setResponse_xml(equifaxevdrtracker.getResponseXML());
		equifaxevdrDetailLogs.setUpdated_time(new Date());
		return equifaxevdrDetailLogs;
	}

}