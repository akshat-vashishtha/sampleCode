package com.qualtech.equifax.api.utils;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.equifax.api.common.dto.EquifaxTrackerDTO;
import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.entity.common.EquifaxOtherKeyInd;
import com.qualtech.equifax.api.entity.common.PreviousName;
import com.qualtech.equifax.api.entity.mcr.EquifaxMCRAccountSummary;
import com.qualtech.equifax.api.entity.mcr.EquifaxMCRIncomeDetails;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAccountDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAcntDtlMfiAddress;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAcntDtlMfiIdentification;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAdditionalMFIDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAddressDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrEnquiry;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrFamilyDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrHistory24MonthsDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrIdentityDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrKeyPersonDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrNomineeDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrPersonalDetail;
import com.qualtech.equifax.api.interfaces.ConvertMCRResponseToEntityInt;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;


@Service
public class ConvertMCRResponseToEntity implements ConvertMCRResponseToEntityInt
{
	
	private static Logger logger = Logger.getLogger(ConvertMCRResponseToEntity.class);
	@Autowired PropertyFile resProp;
	public EquifaxMcrAllDetaills convertEquifaxResponseToEntity(
			EquifaxApiResponse equifaxApiResponse,
			EquifaxApiRequest request,
			EquifaxTrackerDTO equifaxTrackerDTO
	)
	{

		JSONObject requiredtransaction=null;

		try
		{
			requiredtransaction=new JSONObject(equifaxTrackerDTO.getResponseTransactionJson()).getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata");
		}
		catch(Exception ec)
		{
			logger.error("There is error while saving data because not getiing required transaction"+ec);
		}


		EquifaxMcrAllDetaills equifaxMcrDetailLogs=new EquifaxMcrAllDetaills();

		try
		{
			equifaxMcrDetailLogs.setCreated_time(new Date());
			equifaxMcrDetailLogs.setRequest_info_json(request.toString());
			equifaxMcrDetailLogs.setRequest_xml(equifaxTrackerDTO.getEquifaxApiReq());
			equifaxMcrDetailLogs.setResponse_header_info_json(equifaxApiResponse.getHeader().toString());
			equifaxMcrDetailLogs.setUpdated_time(new Date());
			equifaxMcrDetailLogs.setTracker_id("");
			equifaxMcrDetailLogs.setResponse_xml(equifaxTrackerDTO.getEquifaxApiRes());
			equifaxMcrDetailLogs.setHtmlData(equifaxTrackerDTO.getFinalHtml());
			equifaxMcrDetailLogs.setByteArrayData(equifaxTrackerDTO.getByteArray());
			equifaxMcrDetailLogs.setEquifaxIdentityInfo(equifaxTrackerDTO.getEquifaxIdentityInfo());
			equifaxMcrDetailLogs.setDisclaimer(equifaxTrackerDTO.getDisclaimer());
			equifaxMcrDetailLogs.setRequestJson(equifaxTrackerDTO.getEquifaxjsonRequest());
			equifaxMcrDetailLogs.setResponseJson(equifaxTrackerDTO.getEquifaxjsonResponse());
		}
		catch(Exception ec)
		{
			equifaxMcrDetailLogs.setCreated_time(new Date());
			equifaxMcrDetailLogs.setRequest_info_json(request.toString());
			equifaxMcrDetailLogs.setRequest_xml(equifaxTrackerDTO.getEquifaxApiReq());
			equifaxMcrDetailLogs.setResponse_header_info_json(equifaxApiResponse.getHeader().toString());
			equifaxMcrDetailLogs.setUpdated_time(new Date());
			equifaxMcrDetailLogs.setTracker_id("");
			equifaxMcrDetailLogs.setResponse_xml(equifaxTrackerDTO.getEquifaxApiRes().toString());
			equifaxMcrDetailLogs.setResponse_error_info_json(equifaxApiResponse.getErrorInfo().toString());
			equifaxMcrDetailLogs.setByteArrayData(equifaxTrackerDTO.getByteArray());
			equifaxMcrDetailLogs.setRequestJson(equifaxTrackerDTO.getEquifaxjsonRequest());
			equifaxMcrDetailLogs.setResponseJson(equifaxTrackerDTO.getEquifaxjsonResponse());
			logger.error("There is error while saving main column for mcr detail logs"+ec);
		}
//          System.out.println("Request_ID ::"+equifaxMcrDetailLogs.getRequest_unique_id());
		///////////////////////////////////////Id Contact Info/////////////////////////////////////////

		JSONObject idContactInfo=new JSONObject();
		try
		{
			idContactInfo=requiredtransaction.getJSONObject("idandcontactinfo");
			equifaxMcrDetailLogs.setResponse_id_contact_info_json(idContactInfo.toString());
		}
		catch(Exception ec)
		{
			logger.error("There is error while saving id and contact info data"+ec);
		}
		//////////////////////////////////////Id Contact Info//////////////////////////////////////////

		///////////////////////for saving data for Address////////////////////////////////////////
		
	    List<EquifaxMcrAddressDetail> mcrAddressDetails=new ArrayList<EquifaxMcrAddressDetail>();
    	try{
	    if(requiredtransaction.getJSONObject("idandcontactinfo").has("addressinfo")){
    		
    		try{
    			JSONArray addressDetail=null;
    			try{
    			addressDetail=requiredtransaction.getJSONObject("idandcontactinfo").getJSONArray("addressinfo");
    			}catch(Exception ee){
    			addressDetail=new JSONArray().put(requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("addressinfo"));
    			}
				
				for(int i=0;i<addressDetail.length();i++)
				{
				JSONObject address=addressDetail.getJSONObject(i);
				EquifaxMcrAddressDetail mcrAddressDetail=new EquifaxMcrAddressDetail();
				mcrAddressDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
				try
				{
					if(address.has("address")){
						mcrAddressDetail.setAddress(address.get("address").toString());
					}else{
						mcrAddressDetail.setAddress("NA");
					}
					if(address.has("seq")){
						mcrAddressDetail.setSeq(address.get("seq").toString());
					}else{
						mcrAddressDetail.setSeq("NA");
					}
					if(address.has("state")){
						mcrAddressDetail.setState(address.get("state").toString());
					}else{
						mcrAddressDetail.setState("NA");
					}
					if(address.has("type")){
						mcrAddressDetail.setType(address.get("type").toString());
					}else{
						mcrAddressDetail.setType("NA");
					}
					if(address.has("reporteddate")){
					mcrAddressDetail.setReporteddate(address.get("reporteddate").toString());
					}else{
						mcrAddressDetail.setReporteddate("NA");
					}
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for pcsAddressDetails"+ec);
				}
				mcrAddressDetails.add(mcrAddressDetail);
				}
				
				}catch(Exception ee){
				logger.info("error while parsing address jsonObject "+ee);
				
				}
    		}else{
    				EquifaxMcrAddressDetail mcrAddressDetail=new EquifaxMcrAddressDetail();
    				mcrAddressDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
					mcrAddressDetail.setAddress("NA");
					mcrAddressDetail.setSeq("NA");
					mcrAddressDetail.setState("NA");
					mcrAddressDetail.setType("NA");
					mcrAddressDetail.setReporteddate("NA");
					mcrAddressDetails.add(mcrAddressDetail);
    		}
	
		}catch(Exception ee){
			EquifaxMcrAddressDetail mcrAddressDetail=new EquifaxMcrAddressDetail();
			mcrAddressDetail.setAddress("NA");
			mcrAddressDetail.setSeq("NA");
			mcrAddressDetail.setState("NA");
			mcrAddressDetail.setType("NA");
			mcrAddressDetail.setReporteddate("NA");
			mcrAddressDetails.add(mcrAddressDetail);
			mcrAddressDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
			logger.info("error while parsing address jsonObject "+ee);
		}
    	
    	equifaxMcrDetailLogs.setEquifaxMcrAddressDetails(mcrAddressDetails);
		//////////////////////for saving data for Address///////////////////////////////////////// 
		
		///////////////////////for saving data for Identity////////////////////////////////////////
		
    	 List<EquifaxMcrIdentityDetail> idDetails=new ArrayList<EquifaxMcrIdentityDetail>();
		try
				{
					JSONObject idandcontactinfo=requiredtransaction.getJSONObject("idandcontactinfo");
					if(idandcontactinfo.has("identityinfo"))
					{
						List<String> idList = new ArrayList<String>();
						idList.add("PanId");
						idList.add("PassportID");
						idList.add("DriverLicence");
						idList.add("VoterID");
						idList.add("NationalIDCard");
						idList.add("RationCard");
						idList.add("IDCard");
						idList.add("PhotoCreditCard");
						idList.add("IDOther");
						for(int x=0; x<idList.size();x++)
						{
							try
							{
								String id = idList.get(x).toLowerCase();
								
								JSONArray panDetail=null;
								try
								{
									panDetail=idandcontactinfo.getJSONObject("identityinfo").getJSONArray(id);
								}
								catch(Exception ee)
								{
									panDetail=new JSONArray().put(idandcontactinfo.getJSONObject("identityinfo").getJSONObject(id));
								}
								for(int i=0;i<panDetail.length();i++)
								{
									JSONObject pan=panDetail.getJSONObject(i);
									EquifaxMcrIdentityDetail identityDetail=new EquifaxMcrIdentityDetail();
									identityDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
									try
									{
										if(pan.has("seq"))
										{
											identityDetail.setSeq(idList.get(x));
										}
										else
										{
											identityDetail.setSeq("NA");
										}
										if(pan.has("idnumber")){
											identityDetail.setIdnumber(pan.get("idnumber").toString());
										}else{
											identityDetail.setIdnumber("NA");
										}
										if(pan.has("reporteddate")){
											identityDetail.setReporteddate(pan.get("reporteddate").toString());
										}else{
											identityDetail.setReporteddate("NA");
										}

									}
									catch(Exception ec)
									{
										logger.error("There is error while saving data for pcsPanDetails "+ec);
									}
									if(identityDetail.getIdnumber()!=null && !identityDetail.getIdnumber().equalsIgnoreCase("") && !identityDetail.getIdnumber().equalsIgnoreCase("NA"))
									{
										idDetails.add(identityDetail);
									}
								}
							}
							catch(Exception ex)
							{
								//Do Nothing and no want any logger as well
							}
						}
					}
					else
					{
						EquifaxMcrIdentityDetail identityDetail=new EquifaxMcrIdentityDetail();
						identityDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
						identityDetail.setSeq("NA");
						identityDetail.setIdnumber("NA");
						identityDetail.setReporteddate("NA");
						idDetails.add(identityDetail);
					}
				}
				catch(Exception ec)
				{
					EquifaxMcrIdentityDetail identityDetail=new EquifaxMcrIdentityDetail();
					identityDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
					identityDetail.setSeq("NA");
					identityDetail.setIdnumber("NA");
					identityDetail.setReporteddate("NA");
					idDetails.add(identityDetail);
					logger.error("There is error while saving data for pcsPanDetails "+ec);
				}
				equifaxMcrDetailLogs.setEquifaxMcrIdentityDetails(idDetails);
    	
    	
    	
    	
    	
    	
		///////////////////////for saving data for Personal detail////////////////////////////////////////
		
	    List<EquifaxMcrPersonalDetail> personalDetails=new ArrayList<EquifaxMcrPersonalDetail>();
	    try{
	    	JSONObject idandcontactinfo=requiredtransaction.getJSONObject("idandcontactinfo"); 
	    
	    if(idandcontactinfo.has("personalinfo")){
		try{
			
			JSONObject personalDetailValue=idandcontactinfo.getJSONObject("personalinfo");
			
			if(personalDetailValue!=null){
				EquifaxMcrPersonalDetail personalDetail=new EquifaxMcrPersonalDetail();
				personalDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
			try
			{
				if(personalDetailValue.has("maritalstatus")){
					personalDetail.setMaritalstatus(personalDetailValue.get("maritalstatus").toString());
				}else{
					personalDetail.setMaritalstatus("NA");
				}
				
				if(personalDetailValue.has("age")){
					if(personalDetailValue.getJSONObject("age").has("age")){
						personalDetail.setAge(personalDetailValue.getJSONObject("age").get("age").toString());
					}else{
						personalDetail.setAge("NA");
					}
				}
				if(personalDetailValue.has("name")){
					if(personalDetailValue.getJSONObject("name").has("middlename")){
						personalDetail.setMiddle_name(personalDetailValue.getJSONObject("name").get("middlename").toString());
					}else{
						personalDetail.setMiddle_name("NA");
					}
					
					if(personalDetailValue.getJSONObject("name").has("lastname")){
						personalDetail.setLast_name(personalDetailValue.getJSONObject("name").get("lastname").toString());
					}else{
						personalDetail.setLast_name("NA");
					}
					
					if(personalDetailValue.getJSONObject("name").has("firstname")){
						personalDetail.setFirst_name(personalDetailValue.getJSONObject("name").get("firstname").toString());
					}else{
						personalDetail.setFirst_name("NA");
					}
					if(personalDetailValue.getJSONObject("name").has("additionalmiddlename")){
						personalDetail.setAdditionalmiddlename(personalDetailValue.getJSONObject("name").get("additionalmiddlename").toString());
					}else{
						personalDetail.setAdditionalmiddlename("NA");
					}
					if(personalDetailValue.getJSONObject("name").has("suffix")){
						personalDetail.setSuffix(personalDetailValue.getJSONObject("name").get("suffix").toString());
					}else{
						personalDetail.setSuffix("NA");
					}
					
				}
				
				List<PreviousName> previousNamesList =new ArrayList<PreviousName>();
				
				if(personalDetailValue.has("previousname")){
					
					JSONArray prevName=null;
		    		try {
		    			prevName = personalDetailValue.getJSONArray("previousname");
		    		} catch (JSONException e) {
		    			prevName =new JSONArray().put(personalDetailValue.getJSONObject("previousname")); 
		    		}
		    		for(int i=0;i<prevName.length();i++)
		    		{
		    			PreviousName preName=new PreviousName();
		    		
						if(personalDetailValue.getJSONObject("previousname").has("middlename")){
							preName.setMiddle_name(personalDetailValue.getJSONObject("previousname").get("middlename").toString());
						}else{
							preName.setMiddle_name("");
						}
						
						if(personalDetailValue.getJSONObject("previousname").has("lastname")){
							preName.setLast_name(personalDetailValue.getJSONObject("previousname").get("lastname").toString());
						}else{
							preName.setLast_name("");
						}
						
						if(personalDetailValue.getJSONObject("previousname").has("firstname")){
							preName.setFirst_name(personalDetailValue.getJSONObject("previousname").get("firstname").toString());
						}else{
							preName.setFirst_name("");
						}
						if(personalDetailValue.getJSONObject("previousname").has("additionalmiddlename")){
							preName.setAdditionalmiddlename(personalDetailValue.getJSONObject("previousname").get("additionalmiddlename").toString());
						}else{
							preName.setAdditionalmiddlename("");
						}
						if(personalDetailValue.getJSONObject("previousname").has("suffix")){
							preName.setSuffix(personalDetailValue.getJSONObject("previousname").get("suffix").toString());
						}else{
							preName.setSuffix("");
						}
					previousNamesList.add(preName);
		    		}
		    		
				}else{
					PreviousName preName=new PreviousName();
						preName.setMiddle_name("");
						preName.setLast_name("");
						preName.setFirst_name("NA");
						preName.setAdditionalmiddlename("");
						preName.setSuffix("");
					previousNamesList.add(preName);
				}
				personalDetail.setPreviousNamesList(previousNamesList);
				if(personalDetailValue.has("gender")){
					personalDetail.setGender(personalDetailValue.get("gender").toString());
				}else{
				}
				
				if(personalDetailValue.has("dateofbirth")){
					personalDetail.setDate_of_birth(personalDetailValue.get("dateofbirth").toString());
				}else{
				}
				if(personalDetailValue.has("aliasnameinfo")){
					if(personalDetailValue.getJSONObject("aliasnameinfo").has("aliasname")){
						personalDetail.setAliasname(personalDetailValue.getJSONObject("aliasnameinfo").get("aliasname").toString());
					}else{
					}
					
					if(personalDetailValue.getJSONObject("aliasnameinfo").has("reporteddate")){
						personalDetail.setReporteddate(personalDetailValue.getJSONObject("aliasnameinfo").get("reporteddate").toString());
					}else{
					}
				}else{
				}
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data for personalDetail "+ec);
			}
		
			personalDetails.add(personalDetail);
		 }
		}catch(Exception ec)
		{
			logger.error("There is error while saving data for personalDetail "+ec);
		}
	    }else{
	    	EquifaxMcrPersonalDetail personalDetail=new EquifaxMcrPersonalDetail();
	    	personalDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
	    	personalDetail.setMaritalstatus("NA");
			personalDetail.setAge("NA");
			personalDetail.setMiddle_name("NA");
			personalDetail.setLast_name("NA");
			personalDetail.setFirst_name("NA");
			personalDetail.setAdditionalmiddlename("NA");
			personalDetail.setGender("NA");
			personalDetail.setDate_of_birth("NA");
			personalDetail.setAliasname("NA");
			personalDetail.setReporteddate("NA");
			personalDetail.setSuffix("");
			List<PreviousName> previousNamesList =new ArrayList<PreviousName>();
			PreviousName preName=new PreviousName();
			preName.setMiddle_name("NA");
			preName.setLast_name("NA");
			preName.setFirst_name("NA");
			preName.setAdditionalmiddlename("NA");
			preName.setSuffix("NA");
			previousNamesList.add(preName);
			personalDetails.add(personalDetail);
	    }
		}catch(Exception ee){
			EquifaxMcrPersonalDetail personalDetail=new EquifaxMcrPersonalDetail();
			personalDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
	    	personalDetail.setMaritalstatus("NA");
			personalDetail.setAge("NA");
			personalDetail.setMiddle_name("NA");
			personalDetail.setLast_name("NA");
			personalDetail.setFirst_name("NA");
			personalDetail.setAdditionalmiddlename("NA");
			personalDetail.setGender("NA");
			personalDetail.setDate_of_birth("NA");
			personalDetail.setAliasname("NA");
			personalDetail.setReporteddate("NA");
			personalDetail.setSuffix("");
			List<PreviousName> previousNamesList =new ArrayList<PreviousName>();
			PreviousName preName=new PreviousName();
			preName.setMiddle_name("NA");
			preName.setLast_name("NA");
			preName.setFirst_name("NA");
			preName.setAdditionalmiddlename("NA");
			preName.setSuffix("NA");
			previousNamesList.add(preName);
			personalDetails.add(personalDetail);
			logger.error("There is error while saving data for personalDetail "+ee);
    }
	    equifaxMcrDetailLogs.setEquifaxMcrPersonalDetails(personalDetails);
	    //////////////////////for saving data for Personal detail///////////////////////////////////////// 
    	
    	
		
		///////////////////////for saving data for family detail////////////////////////////////////////
	    List<EquifaxMcrFamilyDetail> mcrFamilyDetails=new ArrayList<EquifaxMcrFamilyDetail>();
	    try{
		
		JSONArray familyDetail=null;
		JSONObject idandcontactinfo=requiredtransaction.getJSONObject("idandcontactinfo");
		if(idandcontactinfo.has("familydetails")){
		try{
			familyDetail=idandcontactinfo.getJSONArray("familydetails");
		}catch(Exception ee){
			familyDetail=new JSONArray().put(idandcontactinfo.getJSONObject("familydetails"));
		}
		
		for(int i=0;i<familyDetail.length();i++)
		{
		JSONObject family=familyDetail.getJSONObject(i);
		EquifaxMcrFamilyDetail mcrFamilyDetail=new EquifaxMcrFamilyDetail();
		try
		{
			mcrFamilyDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
			if(family.has("noofdependents")){
				mcrFamilyDetail.setNoofdependents(family.get("noofdependents").toString());
			}else{
				mcrFamilyDetail.setNoofdependents("NA");
			}
			
			if(family.has("additionalnameinfo")){
				if(family.getJSONObject("additionalnameinfo").has("additionalname")){
					mcrFamilyDetail.setAdditionalname(family.getJSONObject("additionalnameinfo").get("additionalname").toString());
				}else{
					mcrFamilyDetail.setAdditionalname("NA");
				}
				if(family.getJSONObject("additionalnameinfo").has("additionalnametype")){
					mcrFamilyDetail.setAdditionalnametype(family.getJSONObject("additionalnameinfo").get("additionalnametype").toString());
				}else{
					mcrFamilyDetail.setAdditionalnametype("NA");
				}
			}else{
				mcrFamilyDetail.setAdditionalname("NA");
				mcrFamilyDetail.setAdditionalnametype("NA");
			}
		}
		catch(Exception ec)
		{
		logger.error("There is error while saving data for pcsAddressDetails"+ec);
		}
		mcrFamilyDetails.add(mcrFamilyDetail);
		}
		}else{
			EquifaxMcrFamilyDetail mcrFamilyDetail=new EquifaxMcrFamilyDetail();
			mcrFamilyDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
			mcrFamilyDetail.setNoofdependents("NA");
			mcrFamilyDetail.setAdditionalname("NA");
			mcrFamilyDetail.setAdditionalnametype("NA");
			mcrFamilyDetails.add(mcrFamilyDetail);
		}
		
		}catch(Exception ee){
			EquifaxMcrFamilyDetail mcrFamilyDetail=new EquifaxMcrFamilyDetail();
			mcrFamilyDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
			mcrFamilyDetail.setNoofdependents("NA");
			mcrFamilyDetail.setAdditionalname("NA");
			mcrFamilyDetail.setAdditionalnametype("NA");
			mcrFamilyDetails.add(mcrFamilyDetail);
		logger.info("error while parsing mcrFamilyDetails jsonObject "+ee);
		
		}
	    equifaxMcrDetailLogs.setEquifaxMcrFamilyDetails(mcrFamilyDetails);
		///////////////////////for saving data for family detail///////////////////////////////////// 
		
		
		///////////////////////////for saving data into Account Summary/////////////////////////////////
		JSONObject accountSummary=new JSONObject();
		EquifaxMCRAccountSummary account_summary=new EquifaxMCRAccountSummary();
		try
		{
			account_summary.setEquifaxmcrDetailslogs(equifaxMcrDetailLogs);
			if(requiredtransaction.has("accountsummary")){
				accountSummary=requiredtransaction.getJSONObject("accountsummary");
				if(accountSummary.has("noofactiveaccounts")){
					account_summary.setNo_of_active_account(accountSummary.get("noofactiveaccounts").toString());
				}else{
					account_summary.setNo_of_active_account("NA");
				}
				
				if(accountSummary.has("noofpastdueaccounts")){
					account_summary.setNo_of_past_due_accounts(accountSummary.get("noofpastdueaccounts").toString());
				}else{
					account_summary.setNo_of_past_due_accounts("NA");
				}
				
				if(accountSummary.has("totalbalanceamount")){
					account_summary.setTotal_balance_amount(accountSummary.get("totalbalanceamount").toString());
				}else{
					account_summary.setTotal_balance_amount("NA");
				}
				
				if(accountSummary.has("totalmonthlypaymentamount")){
					account_summary.setTotal_monthly_payment_amount(accountSummary.get("totalmonthlypaymentamount").toString());
				}else{
					account_summary.setTotal_monthly_payment_amount("NA");
				}
				
				if(accountSummary.has("totalpastdue")){
					account_summary.setTotal_past_due(accountSummary.get("totalpastdue").toString());
				}else{
					account_summary.setTotal_past_due("NA");
				}
				
				if(accountSummary.has("totalwrittenoffamount")){
					account_summary.setTotal_written_off_account(accountSummary.get("totalwrittenoffamount").toString());
				}else{
					account_summary.setTotal_written_off_account("NA");
				}
				
				if(accountSummary.has("averageopenbalance")){
					account_summary.setAverage_open_balance(accountSummary.get("averageopenbalance").toString());
				}else{
					account_summary.setAverage_open_balance("NA");
				}
				
				if(accountSummary.has("oldestaccount")){
					account_summary.setOldest_account(accountSummary.get("oldestaccount").toString());
				}else{
					account_summary.setOldest_account("NA");
				}
				
				if(accountSummary.has("noofzerobalanceaccounts")){
					account_summary.setNo_of_zero_balance_account(accountSummary.get("noofzerobalanceaccounts").toString());
				}else{
					account_summary.setNo_of_zero_balance_account("NA");
				}
				
				if(accountSummary.has("noofaccounts")){
					account_summary.setNo_of_accounts(accountSummary.get("noofaccounts").toString());
				}else{
					account_summary.setNo_of_accounts("NA");
				}
				
				if(accountSummary.has("totalsanctionamount")){
					account_summary.setTotal_sanction_amount(accountSummary.get("totalsanctionamount").toString());
				}else{
					account_summary.setTotal_sanction_amount("NA");
				}
				
				if(accountSummary.has("totalcreditlimit")){
					account_summary.setTotal_credit_limit(accountSummary.get("totalcreditlimit").toString());
				}else{
					account_summary.setTotal_credit_limit("NA");
				}
				
				if(accountSummary.has("recentaccount")){
					account_summary.setRecent_account(accountSummary.get("recentaccount").toString());
				}else{
					account_summary.setRecent_account("NA");
				}
				
				if(accountSummary.has("singlehighestsanctionamount")){
					account_summary.setSingle_highest_sanction_amount(accountSummary.get("singlehighestsanctionamount").toString());
				}else{
					account_summary.setSingle_highest_sanction_amount("NA");
				}
				
				if(accountSummary.has("totalhighcredit")){
					account_summary.setTotal_high_credit(accountSummary.get("totalhighcredit").toString());
				}else{
					account_summary.setTotal_high_credit("NA");
				}
				
				if(accountSummary.has("noofwriteoffs")){
					account_summary.setNoofwriteoffs(accountSummary.get("noofwriteoffs").toString());
				}else{
					account_summary.setNoofwriteoffs("NA");
				}
				if(accountSummary.has("singlehighestbalance")){
					account_summary.setSingle_highest_balance(accountSummary.get("singlehighestbalance").toString());
				}else{
					account_summary.setSingle_highest_balance("NA");
				}
				
				if(accountSummary.has("singlehighestcredit")){
					account_summary.setSingle_highest_credit(accountSummary.get("singlehighestcredit").toString());
				}else{
					account_summary.setSingle_highest_credit("NA");
				}
				
				
			}else{
				account_summary.setNo_of_active_account("NA");
				account_summary.setNo_of_past_due_accounts("NA");
				account_summary.setTotal_balance_amount("NA");
				account_summary.setTotal_monthly_payment_amount("NA");
				account_summary.setTotal_past_due("NA");
				account_summary.setTotal_written_off_account("NA");
				account_summary.setAverage_open_balance("NA");
				account_summary.setOldest_account("NA");
				account_summary.setNo_of_zero_balance_account("NA");
				account_summary.setNo_of_accounts("NA");
				account_summary.setTotal_sanction_amount("NA");
				account_summary.setTotal_credit_limit("NA");
				account_summary.setRecent_account("NA");
				account_summary.setSingle_highest_sanction_amount("NA");
				account_summary.setTotal_high_credit("NA");
				account_summary.setNoofwriteoffs("NA");
				account_summary.setAverage_open_balance("NA");
				account_summary.setSingle_highest_balance("NA");
				account_summary.setSingle_highest_credit("NA");
			}
			equifaxMcrDetailLogs.setQuifaxmcrAccountSummarry(account_summary);
		}
		catch(Exception ec)
		{
			account_summary.setNo_of_active_account("NA");
			account_summary.setNo_of_past_due_accounts("NA");
			account_summary.setTotal_balance_amount("NA");
			account_summary.setTotal_monthly_payment_amount("NA");
			account_summary.setTotal_past_due("NA");
			account_summary.setTotal_written_off_account("NA");
			account_summary.setAverage_open_balance("NA");
			account_summary.setOldest_account("NA");
			account_summary.setNo_of_zero_balance_account("NA");
			account_summary.setNo_of_accounts("NA");
			account_summary.setTotal_sanction_amount("NA");
			account_summary.setTotal_credit_limit("NA");
			account_summary.setRecent_account("NA");
			account_summary.setSingle_highest_sanction_amount("NA");
			account_summary.setTotal_high_credit("NA");
			account_summary.setNoofwriteoffs("NA");
			account_summary.setAverage_open_balance("NA");
			account_summary.setSingle_highest_balance("NA");
			account_summary.setSingle_highest_credit("NA");
		
			logger.error("There is error while saving account summary"+ec);
		}
		///////////////////////////for saving data into account summary//////////////////////////////////


		  ////////////////for getting Enquiries/////////////////////////
		List<EquifaxMcrEnquiry> equifaxMcrenquiries=new ArrayList<EquifaxMcrEnquiry>();
		try
	    {
	    	
	    	if(requiredtransaction.has("enquiries")){
	    		JSONArray enquiries=null;
	    		try{
	    			 enquiries=requiredtransaction.getJSONArray("enquiries");
	    		}catch(Exception eee){
	    			 enquiries=new JSONArray().put(requiredtransaction.getJSONObject("enquiries"));
	    		}
				try
				{
					for(int i=0;i<enquiries.length();i++)
					{
						EquifaxMcrEnquiry enquiryEntity=new EquifaxMcrEnquiry();
						enquiryEntity.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
						JSONObject enquiry=enquiries.getJSONObject(i);
						if(enquiry.has("date")){
							enquiryEntity.setInquiry_date(enquiry.get("date").toString());
						}else{
							enquiryEntity.setInquiry_date("NA");
						}
						if(enquiry.has("seq")){
							enquiryEntity.setSequence(enquiry.get("seq").toString());
						}else{
		    		    	enquiryEntity.setSequence("NA");
						}
						if(enquiry.has("time")){
							enquiryEntity.setInquiry_time(enquiry.get("time").toString());
						}else{
		    		    	enquiryEntity.setInquiry_time("NA");
						}
						if(enquiry.has("institution")){
							enquiryEntity.setInstitution(enquiry.get("institution").toString());
						}else{
		    		    	enquiryEntity.setInstitution("NA");
						}
						if(enquiry.has("requestpurpose")){
							enquiryEntity.setRequest_purpose(enquiry.get("requestpurpose").toString());
						}else{
		    		    	enquiryEntity.setRequest_purpose("NA");
						}
						if(enquiry.has("amount")){
							enquiryEntity.setAmount(enquiry.get("amount").toString());
						}else{
		    		    	enquiryEntity.setAmount("NA");
						}
						equifaxMcrenquiries.add(enquiryEntity);
					}
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for enquiry"+ec); 
				}
				equifaxMcrDetailLogs.setEquifaxMcrenquiries(equifaxMcrenquiries);
	    	}else{
	    		
	    		EquifaxMcrEnquiry enquiryEntity=new EquifaxMcrEnquiry();
	    		enquiryEntity.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
		    	enquiryEntity.setInquiry_date("NA");
		    	enquiryEntity.setSequence("NA");
		    	enquiryEntity.setInquiry_time("NA");
		    	enquiryEntity.setInstitution("NA");
		    	enquiryEntity.setRequest_purpose("NA");
		    	enquiryEntity.setAmount("NA");
		    	equifaxMcrenquiries.add(enquiryEntity);
	    	}
	    }
	    catch(Exception ec)
	    {
    		EquifaxMcrEnquiry enquiryEntity=new EquifaxMcrEnquiry();
    		enquiryEntity.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
	    	enquiryEntity.setInquiry_date("NA");
	    	enquiryEntity.setSequence("NA");
	    	enquiryEntity.setInquiry_time("NA");
	    	enquiryEntity.setInstitution("NA");
	    	enquiryEntity.setRequest_purpose("NA");
	    	enquiryEntity.setAmount("NA");
	    	equifaxMcrenquiries.add(enquiryEntity);
	    	equifaxMcrDetailLogs.setEquifaxMcrenquiries(equifaxMcrenquiries);
	    	logger.error(ec);
	    }
		equifaxMcrDetailLogs.setEquifaxMcrenquiries(equifaxMcrenquiries);

		/////////////////////////////////for saving data into enquiries/////////////////////////////////
		

		////////////////////////////////for saving data into income details////////////////////////////
	    List<EquifaxMCRIncomeDetails> equifaxIncomeDetails=new ArrayList<EquifaxMCRIncomeDetails>();
		try{
    		
			if(requiredtransaction.has("incomedetails")){
	    		JSONArray equifaxIncome=null;
	    		try {
	    			equifaxIncome = requiredtransaction.getJSONArray("incomedetails");
	    		} catch (JSONException e) {
	    			equifaxIncome =new JSONArray().put(requiredtransaction.getJSONObject("incomedetails")); 
	    		}
	    		for(int i=0;i<equifaxIncome.length();i++)
	    		{
	    			try
	    			{
	    				JSONObject incomeDetail=equifaxIncome.getJSONObject(i);
	    				EquifaxMCRIncomeDetails equifaxMcrIncome=new EquifaxMCRIncomeDetails();
	    				equifaxMcrIncome.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
	    				if(incomeDetail.has("monthlyexpense")){
	    					equifaxMcrIncome.setMonthly_expense(incomeDetail.get("monthlyexpense").toString());
	    				}else{
	    					equifaxMcrIncome.setMonthly_expense("NA");
	    				}
	    				if(incomeDetail.has("monthlyincome")){
	    					equifaxMcrIncome.setMonthly_income(incomeDetail.get("monthlyincome").toString());
	    				}else{
		    				equifaxMcrIncome.setMonthly_income("NA");
	    				}
	    				if(incomeDetail.has("occupation")){
	    					equifaxMcrIncome.setOccupation(incomeDetail.get("occupation").toString());
	    				}else{
		    				equifaxMcrIncome.setOccupation("NA");
	    				}
	    				if(incomeDetail.has("reporteddate")){
	    					equifaxMcrIncome.setReported_date(incomeDetail.get("reporteddate").toString());
	    				}else{
		    				equifaxMcrIncome.setReported_date("NA");
	    				}
	    				if(incomeDetail.has("seq")){
	    					equifaxMcrIncome.setSequence(incomeDetail.get("seq").toString());
	    				}else{
		    				equifaxMcrIncome.setSequence("NA");
	    				}
	    				equifaxIncomeDetails.add(equifaxMcrIncome);
	    			}
	    			catch(Exception ec)
	    			{
	    				logger.error("error while saving data into incomeDetails MCR"+ec);
	    			}
	
	    		 }
			}else{
				EquifaxMCRIncomeDetails equifaxMcrIncome=new EquifaxMCRIncomeDetails();
				equifaxMcrIncome.setMonthly_expense("NA");
				equifaxMcrIncome.setMonthly_income("NA");
				equifaxMcrIncome.setOccupation("NA");
				equifaxMcrIncome.setReported_date("NA");
				equifaxMcrIncome.setSequence("NA");
				equifaxIncomeDetails.add(equifaxMcrIncome);
	    		}
		}catch(Exception ee){
			EquifaxMCRIncomeDetails equifaxMcrIncome=new EquifaxMCRIncomeDetails();
			equifaxMcrIncome.setMonthly_expense("NA");
			equifaxMcrIncome.setMonthly_income("NA");
			equifaxMcrIncome.setOccupation("NA");
			equifaxMcrIncome.setReported_date("NA");
			equifaxMcrIncome.setSequence("NA");
			equifaxIncomeDetails.add(equifaxMcrIncome);
		logger.error("error while saving data into incomeDetails MCR "+ee);
	}
	equifaxMcrDetailLogs.setEquifaxMcrIncomeDetails(equifaxIncomeDetails);
		
		////////////////////////////////for saving data into income details////////////////////////////


		///////////////////////////////for saving Account Data/////////////////////////////////////////
	try{
		if(requiredtransaction.has("accountdetails")){
			JSONArray acountsArray=null;
			try {
				acountsArray = requiredtransaction.getJSONObject("accountdetails").getJSONArray("account");
			} catch (JSONException e) {
				logger.info("exception while getting jsonArray of accuntDetails ::"+e.getMessage());
				try {
					acountsArray =new JSONArray().put(requiredtransaction.getJSONObject("accountdetails").getJSONObject("account"));
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
			List<EquifaxMcrAccountDetail> equifaxMcrAccounts=new ArrayList<EquifaxMcrAccountDetail>();

			for(int j=0;j<acountsArray.length();j++)
			{
				try
				{
					EquifaxMcrAccountDetail eqfaxmcrAccount=new EquifaxMcrAccountDetail();
					eqfaxmcrAccount.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
					JSONObject account=acountsArray.getJSONObject(j);
					
					if(account.has("id")){
						eqfaxmcrAccount.setAccount_detail_id(account.get("id").toString());
						}else{
							eqfaxmcrAccount.setAccount_detail_id("NA");
						}
						
						if(account.has("accountnumber")){
						eqfaxmcrAccount.setAccount_number(account.get("accountnumber").toString());
						}else{
							eqfaxmcrAccount.setAccount_number("NA");
						}
						if(account.has("clientname")){
    						eqfaxmcrAccount.setClientName(account.get("clientname").toString());
    						}else{
    							eqfaxmcrAccount.setClientName("NA");
    						}
						if(account.has("accounttype")){
    						eqfaxmcrAccount.setAccount_type(account.get("accounttype").toString());
    						}else{
    							eqfaxmcrAccount.setAccount_type("NA");
    						}
						if(account.has("ownershiptype")){
    						eqfaxmcrAccount.setOwner_ship_type(account.get("ownershiptype").toString());
    						}else{
    							eqfaxmcrAccount.setOwner_ship_type("NA");
    						}
						if(account.has("balance")){
    						eqfaxmcrAccount.setBalance(account.get("balance").toString());
    						}else{
    							eqfaxmcrAccount.setBalance("NA");
    						}
						if(account.has("pastdueamount")){
    						eqfaxmcrAccount.setPast_due_amount(account.get("pastdueamount").toString());
    						}else{
    							eqfaxmcrAccount.setPast_due_amount("NA");
    						}
						if(account.has("lastpayment")){
    						eqfaxmcrAccount.setLast_payment(account.get("lastpayment").toString());
    						}else{
    							eqfaxmcrAccount.setLast_payment("NA");
    						}
						if(account.has("writeoffamount")){
    						eqfaxmcrAccount.setWriteOffAmount(account.get("writeoffamount").toString());
    						}else{
    							eqfaxmcrAccount.setWriteOffAmount("NA");
    						}
						if(account.has("open")){
    						eqfaxmcrAccount.setOpen(account.get("open").toString());
    						}else{
    							eqfaxmcrAccount.setOpen("NA");
    						}
						if(account.has("lastpaymentdate")){
    						eqfaxmcrAccount.setLastPaymentDate(account.get("lastpaymentdate").toString());
    						}else{
    							eqfaxmcrAccount.setLastPaymentDate("NA");
    						}
						if(account.has("highcredit")){
    						eqfaxmcrAccount.setHighCredit(account.get("highcredit").toString());
    						}else{
    							eqfaxmcrAccount.setHighCredit("NA");
    						}
						if(account.has("reason")){
    						eqfaxmcrAccount.setReason(account.get("reason").toString());
    						}else{
    							eqfaxmcrAccount.setReason("NA");
    						}
						if(account.has("datewrittenoff")){
    						eqfaxmcrAccount.setDateWrittenOff(account.get("datewrittenoff").toString());
    						}else{
    							eqfaxmcrAccount.setDateWrittenOff("NA");
    						}
						if(account.has("dateapplied")){
    						eqfaxmcrAccount.setDateApplied(account.get("dateapplied").toString());
    						}else{
    							eqfaxmcrAccount.setDateApplied("NA");
    						}
						if(account.has("interestrate")){
    						eqfaxmcrAccount.setInterestrate(account.get("interestrate").toString());
    						}else{
    							eqfaxmcrAccount.setInterestrate("NA");;
    						}
						if(account.has("termfrequency")){
    						eqfaxmcrAccount.setTerm_frequency(account.get("termfrequency").toString());
    						}else{
    							eqfaxmcrAccount.setTerm_frequency("NA");;
    						}
						if(account.has("creditlimit")){
    						eqfaxmcrAccount.setCreditLimit(account.get("creditlimit").toString());
    						}else{
    							eqfaxmcrAccount.setCreditLimit("NA");;
    						}
						if(account.has("collateraltype")){
    						eqfaxmcrAccount.setCollateral_type(account.get("collateraltype").toString());
    						}else{
    							eqfaxmcrAccount.setCollateral_type("NA");;
    						}
						if(account.has("collateralvalue")){
    						eqfaxmcrAccount.setCollateral_value(account.get("collateralvalue").toString());
    						}else{
    							eqfaxmcrAccount.setCollateral_value("NA");;
    						}
						if(account.has("assetclassification")){
    						eqfaxmcrAccount.setAsset_classification(account.get("assetclassification").toString());
    						}else{
    							eqfaxmcrAccount.setAsset_classification("NA");;
    						}
						if(account.has("suitfiledstatus")){
    						eqfaxmcrAccount.setSuit_filed_status(account.get("suitfiledstatus").toString());
    						}else{
    							eqfaxmcrAccount.setSuit_filed_status("NA");;
    						}
						if(account.has("disputecode")){
    						eqfaxmcrAccount.setDispute_code(account.get("disputecode").toString());
    						}else{
    							eqfaxmcrAccount.setDispute_code("NA");;
    						}
						if(account.has("accountstatus")){
						eqfaxmcrAccount.setAccount_status(account.get("accountstatus").toString());
						}else{
							eqfaxmcrAccount.setAccount_status("NA");
						}if(account.has("appliedamount")){
    						eqfaxmcrAccount.setApplied_amount(account.get("appliedamount").toString());
    						}else{
    							eqfaxmcrAccount.setApplied_amount("NA");
    						}if(account.has("branchidmfi")){
    						eqfaxmcrAccount.setBranch_id_mfi(account.get("branchidmfi").toString());
    						}else{
    							eqfaxmcrAccount.setBranch_id_mfi("NA");
    						}if(account.has("currentbalance")){
    						eqfaxmcrAccount.setCurrent_balance(account.get("currentbalance").toString());
    						}else{
    							eqfaxmcrAccount.setCurrent_balance("NA");
    						}if(account.has("dateclosed")){
    						eqfaxmcrAccount.setDate_closed(account.get("dateclosed").toString());
    						}else{
    							eqfaxmcrAccount.setDate_closed("NA");
    						}if(account.has("dateopened")){
    						eqfaxmcrAccount.setDate_opened(account.get("dateopened").toString());
    						}else{
    							eqfaxmcrAccount.setDate_opened("Na");
    						}if(account.has("datereported")){
    						eqfaxmcrAccount.setDate_reported(account.get("datereported").toString());
    						}else{
    							eqfaxmcrAccount.setDate_reported("NA");
    						}if(account.has("datesanctioned")){
    						eqfaxmcrAccount.setDate_sanctioned(account.get("datesanctioned").toString());
    						}else{
    							eqfaxmcrAccount.setDate_sanctioned("NA");
    						}if(account.has("dayspastdue")){
    						eqfaxmcrAccount.setDays_past_due(account.get("dayspastdue").toString());
    						}else{
    							eqfaxmcrAccount.setDays_past_due("NA");
    						}if(account.has("disbursedamount")){
    						eqfaxmcrAccount.setDisbursed_ammount(account.get("disbursedamount").toString());
    						}else{
    							eqfaxmcrAccount.setDisbursed_ammount("NA");
    						}if(account.has("installmentamount")){
        						eqfaxmcrAccount.setInstallment_amount(account.get("installmentamount").toString());
        						}else{
        							eqfaxmcrAccount.setInstallment_amount("NA");
        						}if(account.has("institution")){
        						eqfaxmcrAccount.setInstitution(account.get("institution").toString());
        						}else{
        							eqfaxmcrAccount.setInstitution("NA");
        						}if(account.has("kendraidmfi")){
        						eqfaxmcrAccount.setKendra_aid_mfi(account.get("kendraidmfi").toString());
        						}else{
        							eqfaxmcrAccount.setKendra_aid_mfi("NA");
        						}if(account.has("loancategory")){
            						eqfaxmcrAccount.setLoan_category(account.get("loancategory").toString());
        						}else{
        							eqfaxmcrAccount.setLoan_category("NA");
        						}if(account.has("loancycleid")){
        						eqfaxmcrAccount.setLoan_cycle_id(account.get("loancycleid").toString());
        						}else{
        							eqfaxmcrAccount.setLoan_cycle_id("NA");
        						}if(account.has("loanpurpose")){
        						eqfaxmcrAccount.setLoan_purpose(account.get("loanpurpose").toString());
        						}else{
        							eqfaxmcrAccount.setLoan_purpose("NA");
        						}if(account.has("noofinstallments")){
        						eqfaxmcrAccount.setNo_of_installments(account.get("noofinstallments").toString());
        						}else{
        							eqfaxmcrAccount.setNo_of_installments("NA");
        						}if(account.has("repaymenttenure")){
            						eqfaxmcrAccount.setRepayment_tenure(account.get("repaymenttenure").toString());
        						}else{
        							eqfaxmcrAccount.setRepayment_tenure("NA");
        						}if(account.has("reporteddate")){
        						eqfaxmcrAccount.setReported_date(account.get("reporteddate").toString());
        						}else{
        							eqfaxmcrAccount.setReported_date("NA");
        						}if(account.has("sanctionamount")){
        						eqfaxmcrAccount.setSanction_amount(account.get("sanctionamount").toString());
        						}else{
        							eqfaxmcrAccount.setSanction_amount("NA");
        						}if(account.has("seq")){
        						eqfaxmcrAccount.setSequence(account.get("seq").toString());
        						}else{
        							eqfaxmcrAccount.setSequence("NA");
        						}
        						if(account.has("additionalmfidetails")){
        							eqfaxmcrAccount.setAdditional_mfi_details(account.get("additionalmfidetails").toString());
        						}else{
        							eqfaxmcrAccount.setAdditional_mfi_details("NA");
        						}
        						if(account.has("nominee")){
        							eqfaxmcrAccount.setNominee(account.get("nominee").toString());
        						}else{
        							eqfaxmcrAccount.setNominee("NA");
        						}
        						if(account.has("history24months")){
        							eqfaxmcrAccount.setHistory_24_months(account.get("history24months").toString());
        						}else{
        							eqfaxmcrAccount.setHistory_24_months("NA");
        						}
        						if(account.has("keyperson")){
        							eqfaxmcrAccount.setKey_person(account.get("keyperson").toString());
        						}else{
        							eqfaxmcrAccount.setKey_person("NA");
        						}
        						
        						equifaxMcrAccounts.add(eqfaxmcrAccount);
				
				}catch(Exception ec)
				{
					logger.error("There is error while saving mcr account"+ec);
				}

			}
			
			equifaxMcrDetailLogs.setEquifaxMcrAccountDetails(equifaxMcrAccounts);
		}
	}catch(Exception ee){
		
	}
		//////////////////////////////for saving Account Data//////////////////////////////////////////
		
		try
		{
			JSONObject inquiryresponseheader=new JSONObject(equifaxTrackerDTO.getResponseTransactionJson()).getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("inquiryresponseheader");
			equifaxMcrDetailLogs.setResponse_date(inquiryresponseheader.get("date").toString());
			equifaxMcrDetailLogs.setResponse_order_no(inquiryresponseheader.get("reportorderno").toString());
			equifaxMcrDetailLogs.setResponse_time(inquiryresponseheader.get("time").toString());
			equifaxMcrDetailLogs.setMemberId(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"));
			equifaxMcrDetailLogs.setMemberReferenceNumber(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber"));
				
		}
		catch(Exception ec)
		{
			logger.error("There is error while saving data from enquiry response header");
		}
		equifaxMcrDetailLogs.setRemarks("success");
	
		

		///////////////////////for saving data for Equifax Mcr History24Months////////////////////////////////////////
		try{
			JSONArray historyDetail=null;
			JSONArray acountsArrayy=null;
			try {
				acountsArrayy = requiredtransaction.getJSONObject("accountdetails").getJSONArray("account");
			} catch (JSONException e) {
				logger.info("exception while getting jsonArray of accuntDetails ::"+e.getMessage());
			}
			List<EquifaxMcrHistory24MonthsDetail> mcr24monthDetails=new ArrayList<EquifaxMcrHistory24MonthsDetail>();
			for(int j=0;j<acountsArrayy.length();j++){
				try{
					historyDetail=new JSONArray().put(acountsArrayy.getJSONObject(j).getJSONObject("history24months").getJSONObject("month"));
				}catch(Exception ee){
					historyDetail=acountsArrayy.getJSONObject(j).getJSONObject("history24months").getJSONArray("month");
				}
				
					JSONObject month24=historyDetail.getJSONObject(0);
					
				
					EquifaxMcrHistory24MonthsDetail mcr24monthDetail=new EquifaxMcrHistory24MonthsDetail();
					try
					{
						mcr24monthDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
						if(month24.has("key")){
							mcr24monthDetail.setKey(month24.get("key").toString());
						}else{
							mcr24monthDetail.setKey("NA");
						}
						if(month24.has("paymentstatus")){
							mcr24monthDetail.setPaymentstatus(month24.get("paymentstatus").toString());
						}else{
							mcr24monthDetail.setPaymentstatus("NA");
						}
						if(month24.has("suitfiledstatus")){
							mcr24monthDetail.setSuitFiledStatus(month24.get("suitfiledstatus").toString());
						}else{
							mcr24monthDetail.setSuitFiledStatus("NA");
						}
						if(month24.has("assetclassificationstatus")){
							mcr24monthDetail.setAssetClassificationStatus(month24.get("assetclassificationstatus").toString());
						}else{
							mcr24monthDetail.setAssetClassificationStatus("NA");
						}
						
					}
					catch(Exception ec)
					{
						logger.error("There is error while saving data for History24Months"+ec);
					}
					
					mcr24monthDetails.add(mcr24monthDetail);
			
		  }equifaxMcrDetailLogs.setHistory24MonthsDetails(mcr24monthDetails);
		}catch(Exception ee){
			logger.info("error while parsing History24Months jsonObject "+ee);

		}
		//////////////////////for saving data for History24Months///////////////////////////////////////// 
		
		
		//////////////////////////////////////////saving data for otherkeyInd///////////////////////////////////////////////////////
		
		  ////////////////////////////////for Other KeyInd details////////////////////////////
		EquifaxOtherKeyInd keyInd=new EquifaxOtherKeyInd();
		
		try{
			keyInd.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
    			if(requiredtransaction.has("otherkeyind>")){
		    		JSONArray equifaxOtherKeyInd=null;
		    		try {
		    			equifaxOtherKeyInd = requiredtransaction.getJSONArray("otherkeyind");
		    		} catch (JSONException e) {
		    			equifaxOtherKeyInd =new JSONArray().put(requiredtransaction.getJSONObject("otherkeyind")); 
		    		}
		    		for(int i=0;i<equifaxOtherKeyInd.length();i++)
		    		{
		    			try
		    			{
		    				JSONObject otherKey=equifaxOtherKeyInd.getJSONObject(i);
		    			
		    				if(otherKey.has("ageofoldesttrade")){
		    					keyInd.setAgeOfOldestTrade(otherKey.get("ageofoldesttrade").toString());
		    				}else{
		    					keyInd.setAgeOfOldestTrade("NA");
		    				}
		    				if(otherKey.has("alllineseverwritten")){
		    					keyInd.setAllLinesEVERWritten(otherKey.get("alllineseverwritten").toString());
		    				}else{
		    					keyInd.setAllLinesEVERWritten("NA");
		    				}
		    				if(otherKey.has("alllineseverwrittenin6months")){
		    					keyInd.setAllLinesEVERWrittenIn6Months(otherKey.get("alllineseverwrittenin6months").toString());
		    				}else{
		    					keyInd.setAllLinesEVERWrittenIn6Months("NA");
		    				}
		    				if(otherKey.has("alllineseverwrittenin9months")){
		    					keyInd.setAllLinesEVERWrittenIn9Months(otherKey.get("alllineseverwrittenin9months").toString());
		    				}else{
		    					keyInd.setAllLinesEVERWrittenIn9Months("NA");
		    				}
		    				if(otherKey.has("numberofopentrades")){
		    					keyInd.setNumberOfOpenTrades(otherKey.get("numberofopentrades").toString());
		    				}else{
		    					keyInd.setNumberOfOpenTrades("NA");
		    				}
		    			}
		    			catch(Exception ec)
		    			{
		    				logger.error("error while saving data into otherKeyInd MCR"+ec);
		    			}
		
		    		 }
    			}else{
    					keyInd.setAgeOfOldestTrade("NA");
    					keyInd.setAllLinesEVERWritten("NA");
    					keyInd.setAllLinesEVERWrittenIn6Months("NA");
    					keyInd.setAllLinesEVERWrittenIn9Months("NA");
    					keyInd.setNumberOfOpenTrades("NA");
    					keyInd.setAgeOfOldestTrade("NA");
		    		}
    		}catch(Exception ee){
    			keyInd.setAgeOfOldestTrade("NA");
				keyInd.setAllLinesEVERWritten("NA");
				keyInd.setAllLinesEVERWrittenIn6Months("NA");
				keyInd.setAllLinesEVERWrittenIn9Months("NA");
				keyInd.setNumberOfOpenTrades("NA");
				keyInd.setAgeOfOldestTrade("NA");
			logger.error("error while saving data into otherkeyInd MCR "+ee);
		}
		equifaxMcrDetailLogs.setEquifaxMcrOtherKeyInd(keyInd);
		
		///////////////////////////////////////////saving data for otherkeyind/////////////////////////////////////////////////////
		
		///////////////////////for saving data for Equifax Mcr additional MFI details////////////////////////////////////////
		try{
			JSONArray additionalMfiDetail=null;
			JSONArray acountsArrayy=null;
			try {
				acountsArrayy = requiredtransaction.getJSONObject("accountdetails").getJSONArray("account");
			} catch (JSONException e) {
				logger.info("exception while getting jsonArray of accuntDetails ::"+e.getMessage());
				acountsArrayy =new JSONArray().put(requiredtransaction.getJSONObject("accountdetails").getJSONObject("account"));
			}
			List<EquifaxMcrAdditionalMFIDetail> additionalMfiDetails=new ArrayList<EquifaxMcrAdditionalMFIDetail>();
			List<EquifaxMcrAcntDtlMfiAddress> additionalAddr=new ArrayList<EquifaxMcrAcntDtlMfiAddress>();
			List<EquifaxMcrAcntDtlMfiIdentification> additionalIdentity=new ArrayList<EquifaxMcrAcntDtlMfiIdentification>();
			for(int j=0;j<acountsArrayy.length();j++){
				
				try{
					additionalMfiDetail=new JSONArray().put(acountsArrayy.getJSONObject(j).getJSONObject("additionalmfidetails"));
				}catch(Exception ee){
					additionalMfiDetail=acountsArrayy.getJSONObject(j).getJSONArray("additionalmfidetails");
				}
				
					JSONObject aDetial=additionalMfiDetail.getJSONObject(0);
					
					EquifaxMcrAdditionalMFIDetail mcrAdditionalMfiDetail=new EquifaxMcrAdditionalMFIDetail();
					EquifaxMcrAcntDtlMfiAddress address =new EquifaxMcrAcntDtlMfiAddress();
					EquifaxMcrAcntDtlMfiIdentification identification = new EquifaxMcrAcntDtlMfiIdentification();
					try
					{
						mcrAdditionalMfiDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
						//mcrAdditionalMfiDetail.setUnique_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.QC_MCR_ADDIONALMFI_SEQUENCE)));
								try
								{
									if(aDetial.has("id")){
										mcrAdditionalMfiDetail.setId(aDetial.get("id").toString());
									}else{
										mcrAdditionalMfiDetail.setId("NA");
									}
									if(aDetial.has("memberid")){
										mcrAdditionalMfiDetail.setMemberid(aDetial.get("memberid").toString());
									}else{
										mcrAdditionalMfiDetail.setMemberid("NA");
									}
									if(aDetial.has("mficlientfullname")){
										mcrAdditionalMfiDetail.setMficlientfullname(aDetial.get("mficlientfullname").toString());
									}else{
										mcrAdditionalMfiDetail.setMficlientfullname("NA");
									}
									if(aDetial.has("mfidob")){
										mcrAdditionalMfiDetail.setMfidob(aDetial.get("mfidob").toString());
									}else{
										mcrAdditionalMfiDetail.setMfidob("NA");
									}
									if(aDetial.has("mfigender")){
										mcrAdditionalMfiDetail.setMfigender(aDetial.get("mfigender").toString());
									}else{
										mcrAdditionalMfiDetail.setMfigender("NA");
									}
									address.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
									if(aDetial.has("mfiaddress")){
										if(aDetial.getJSONObject("mfiaddress").has("additionaladdressdetails")){
											JSONObject mfiaddr=aDetial.getJSONObject("mfiaddress").getJSONObject("additionaladdressdetails");
											if(mfiaddr.has("mfiaddressline")){
												address.setMfiaddressline(mfiaddr.get("mfiaddressline").toString());
											}else{
												address.setMfiaddressline("NA");
											}
											if(mfiaddr.has("seq")){
												address.setSeq(mfiaddr.get("seq").toString());
											}else{
												address.setSeq("NA");
											}
											if(mfiaddr.has("mfistate")){
												address.setMfistate(mfiaddr.get("mfistate").toString());
											}else{
												address.setMfistate("NA");
											}
											if(mfiaddr.has("mfipostalpin")){
												address.setMfipostalpin(mfiaddr.get("mfipostalpin").toString());
											}else{
												address.setMfipostalpin("NA");
											}
											
										}else{
											address.setMfiaddressline("NA");
											address.setSeq("NA");
											address.setMfistate("NA");
											address.setMfipostalpin("NA");
										}
									}else{
										address.setMfiaddressline("NA");
										address.setSeq("NA");
										address.setMfistate("NA");
										address.setMfipostalpin("NA");
									}//
									mcrAdditionalMfiDetail.setMcrAcntDtlMfiAddress(address);
									identification.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
									if(aDetial.has("mfiidentification")){
										if(aDetial.getJSONObject("mfiidentification").has("mfiotherid")){
											identification.setMfiOtherId(aDetial.getJSONObject("mfiidentification").get("mfiotherid").toString());
										}else{
											identification.setMfiOtherId("NA");
										}
									}else{
										identification.setMfiOtherId("NA");
									}
									mcrAdditionalMfiDetail.setMcrAcntDtlMfiIdentification(identification);
									
								}
								catch(Exception ec)
								{
									logger.error("There is error while saving data for mcrAdditionalMfiDetail"+ec);
								}
								additionalMfiDetails.add(mcrAdditionalMfiDetail);
					}
					catch(Exception ec)
					{
						logger.error("There is error while saving data for History24Months"+ec);
					}
					additionalMfiDetails.add(mcrAdditionalMfiDetail);
					additionalAddr.add(address);
					additionalIdentity.add(identification);
			
		  }
			equifaxMcrDetailLogs.setEquifaxMcrAdditionalMFIDetails(additionalMfiDetails);
			equifaxMcrDetailLogs.setEquifaxMcrAcntDtlMfiAddresses(additionalAddr);
			equifaxMcrDetailLogs.setEquifaxMcrAcntDtlMfiIdentification(additionalIdentity);
		}catch(Exception ee){
			logger.info("error while parsing History24Months jsonObject "+ee);

		}
		//////////////////////for saving data for additionalMfiDetails///////////////////////////////////////// 
		
		///////////////////////for saving data for Equifax Mcr Nominee////////////////////////////////////////
		List<EquifaxMcrNomineeDetail> nomineeDetails=new ArrayList<EquifaxMcrNomineeDetail>();
		try{
		JSONArray nomineeDetail=null;
		JSONArray acountsArrayy=null;

		try {
			acountsArrayy = requiredtransaction.getJSONObject("accountdetails").getJSONArray("account");
		} catch (JSONException e) {
			logger.info("exception while getting jsonArray of accuntDetails ::"+e.getMessage());
		}
		for(int j=0;j<acountsArrayy.length();j++){
			try{
				nomineeDetail=new JSONArray().put(acountsArrayy.getJSONObject(j).getJSONObject("nominee"));
			}catch(Exception ee){
				nomineeDetail=acountsArrayy.getJSONObject(j).getJSONArray("nominee");
			}
			
				JSONObject nomDtl=nomineeDetail.getJSONObject(0);
				EquifaxMcrNomineeDetail nomDetail=new EquifaxMcrNomineeDetail();
				try
				{
					nomDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
					if(nomDtl.has("relationtype")){
					nomDetail.setRelationtype(nomDtl.get("relationtype").toString());
					}else{
						nomDetail.setRelationtype("NA");
					}
					if(nomDtl.has("name")){
						nomDetail.setName(nomDtl.get("name").toString());
					}else{
						nomDetail.setName("NA");
					}
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for Nominee"+ec);
				}
				nomineeDetails.add(nomDetail);
		
		}
		}catch(Exception ee){
			EquifaxMcrNomineeDetail nomDetail=new EquifaxMcrNomineeDetail();
			nomDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
			nomDetail.setRelationtype("NA");
			nomDetail.setName("NA");
		logger.info("error while parsing nomineeDetails jsonObject "+ee);
		}
		equifaxMcrDetailLogs.setEquifaxMcrNomineeDetails(nomineeDetails);
		//////////////////////for saving data for Nominee/////////////////////////////////////////
		
		////////////////////for saving data for Key Person/////////////////////////////////////////
		
		try{
		JSONArray keyPersonDetail=null;
		JSONArray acountsArrayy=null;
		try {
			acountsArrayy = requiredtransaction.getJSONObject("accountdetails").getJSONArray("account");
		} catch (JSONException e) {
			logger.info("exception while getting jsonArray of accuntDetails ::"+e.getMessage());
		}
		List<EquifaxMcrKeyPersonDetail> keyPersonDetails=new ArrayList<EquifaxMcrKeyPersonDetail>();
		for(int j=0;j<acountsArrayy.length();j++){
			try{
				keyPersonDetail=new JSONArray().put(acountsArrayy.getJSONObject(j).getJSONObject("keyperson"));
			}catch(Exception ee){
				keyPersonDetail=acountsArrayy.getJSONObject(j).getJSONArray("keyperson");
			}
			
				JSONObject kpDtl=keyPersonDetail.getJSONObject(0);
				EquifaxMcrKeyPersonDetail kpDetail=new EquifaxMcrKeyPersonDetail();
				try
				{
					kpDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
					if(kpDtl.has("relationtype")){
						kpDetail.setRelationtype(kpDtl.get("relationtype").toString());
					}else{
						kpDetail.setRelationtype("NA");
					}
					if(kpDtl.has("name")){
						kpDetail.setName(kpDtl.get("name").toString());
					}else{
						kpDetail.setName("NA");
					}
					
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for keyPersonDetails"+ec);
				}
				keyPersonDetails.add(kpDetail);
		
			}equifaxMcrDetailLogs.setEquifaxMcrKeyPersonDetails(keyPersonDetails);
		}catch(Exception ee){
			EquifaxMcrKeyPersonDetail kpDetail=new EquifaxMcrKeyPersonDetail();
			kpDetail.setEquifaxMcrDetailsLogs(equifaxMcrDetailLogs);
			kpDetail.setRelationtype("NA");
			kpDetail.setName("NA");
		logger.info("error while parsing keyPersonDetails jsonObject "+ee);
		
		}
		//////////////////////for saving data for Key Person/////////////////////////////////////////
		
		
		return equifaxMcrDetailLogs;
	}

}
