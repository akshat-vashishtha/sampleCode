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
import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;
import com.qualtech.equifax.api.entity.common.PreviousName;
import com.qualtech.equifax.api.entity.pcs.EquifaxHistory48MonthsDetail;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAccountDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAccountSummary;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAddressDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEmailDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEnquiry;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEnquirySummary;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsOthers;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPanDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPersonalDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPhoneDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsRecentActivities;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsScoringElement;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsVoterDetails;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;
@Service
public class ConvertPCSResponseToEntities implements ConvertPCSResponseToEntitiesInt
{
	private static Logger logger = Logger.getLogger(ConvertPCSResponseToEntities.class);
	@Autowired PropertyFile resProp;
	public EquifaxPcsAllDetails convertEquifaxResponseToEntity(
			EquifaxApiResponse equifaxApiResponse,
			EquifaxApiRequest request,
			EquifaxTrackerDTO equifaxTrackerDTO
	)
	{
		//dbEquifaxn dbEquifax = new dbEquifaxn();
		EquifaxPcsAllDetails equifaxPCSDetailslogs=new EquifaxPcsAllDetails();
		equifaxPCSDetailslogs.setCreated_time(new Date());
		equifaxPCSDetailslogs.setUpdated_time(new Date());
		
		
		
		
//		try 
//		{
//			String finalResponseJson=equifaxTrackerDTO.getEquifaxjsonResponse();
//			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Response Json is 2: "+finalResponseJson);
//			JSONObject mainJSOnObject=new JSONObject(finalResponseJson);
//			JSONObject responsetransaction=new JSONObject( new JSONObject(mainJSOnObject.get("payload").toString()).get("transaction").toString());
//		} 
//		catch (JSONException e)
//		{
//			logger.error("We are in Exception while tring to get data from Response DTO");
//		}
//		
		
		
		///////////////score Json///////////////////
		try
		{
			equifaxPCSDetailslogs.setRequest_info_json(request.toString());
			String response_header_info_json=equifaxApiResponse.getHeader().toString();
			equifaxPCSDetailslogs.setResponse_header_info_json(response_header_info_json);
			String response_error_info_json=equifaxApiResponse.getErrorInfo().toString();
			equifaxPCSDetailslogs.setResponse_error_info_json(response_error_info_json); 
			equifaxPCSDetailslogs.setHtmlData(equifaxTrackerDTO.getFinalHtml());
			equifaxPCSDetailslogs.setByteArrayData(equifaxTrackerDTO.getByteArray());
			equifaxPCSDetailslogs.setDisclaimer(equifaxTrackerDTO.getDisclaimer());
			equifaxPCSDetailslogs.setRequestJson(equifaxTrackerDTO.getEquifaxjsonRequest());
			equifaxPCSDetailslogs.setResponseJson(equifaxTrackerDTO.getEquifaxjsonResponse());
			
			//// for getting Miscelleneous Info in Array from Accounts Array//////////////
			JSONObject requiredtransaction=null;
			try
			{
				requiredtransaction=new JSONObject(equifaxTrackerDTO.getResponseTransactionJson()).getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata");
			}
			catch(Exception ec)
			{
				logger.error("There is error in scores from equifax"+ec);
			}
			try
			{
				JSONArray accounts=requiredtransaction.getJSONObject("accountdetails").getJSONArray("account");
				JSONArray history48months=new JSONArray();
				for(int i=0;i<accounts.length();i++)
				{
					JSONArray history48month=accounts.getJSONObject(i).getJSONObject("history48months").getJSONArray("month");
					history48months.put(history48month);
				}
				JSONObject miscelleneousInfo=new JSONObject();
				String disclaimer=requiredtransaction.get("disclaimer").toString();
				miscelleneousInfo.put("disclaimer", disclaimer);
				miscelleneousInfo.put("history48months",history48months.toString());
				equifaxPCSDetailslogs.setMiscellaneous_info_json(miscelleneousInfo.toString());
			}
			catch(Exception ec)
			{
				logger.error("error in getting miscelleneous data"+ec);
			}
			//// for getting Miscelleneous Info in Array from Accounts Array//////////////

		
		////////////////////////getting  array of scoring Elements////////////////////
			try
			{
			List<EquifaxPcsScoringElement> scoringElements=new ArrayList<EquifaxPcsScoringElement>();
				try
				{
					if(requiredtransaction.has("scoringelements")){
						if(requiredtransaction.getJSONObject("scoringelements").has("scoringelement")){
							JSONArray ScoringElements=null;
							try{
								ScoringElements=requiredtransaction.getJSONObject("scoringelements").getJSONArray("scoringelement");
							}catch(Exception eee){
								ScoringElements=new JSONArray().put(requiredtransaction.getJSONObject("scoringelements").getJSONObject("scoringelement"));
							}
								
								for(int i=0;i<ScoringElements.length();i++)
								{
						
									EquifaxPcsScoringElement pcsScoringElement=new EquifaxPcsScoringElement();
									JSONObject ScoringElement=ScoringElements.getJSONObject(i);
									pcsScoringElement.setEquifaxPcsDetailsLogs(equifaxPCSDetailslogs);
									if(ScoringElement.has("code")){
										pcsScoringElement.setCode(ScoringElement.get("code").toString());
									}
									if(ScoringElement.has("description")){
										pcsScoringElement.setDescription(ScoringElement.get("description").toString());
									}
									if(ScoringElement.has("type")){
										pcsScoringElement.setType(ScoringElement.get("type").toString());
									}
									if(ScoringElement.has("seq")){
										pcsScoringElement.setSequence(ScoringElement.get("seq").toString());
									}
									scoringElements.add(pcsScoringElement);
								}
							  }
					}else{
			    		EquifaxPcsScoringElement pcsScoringElement=new EquifaxPcsScoringElement();
						pcsScoringElement.setEquifaxPcsDetailsLogs(equifaxPCSDetailslogs);
						pcsScoringElement.setCode("NA");
						pcsScoringElement.setDescription("NA");
						pcsScoringElement.setType("NA");
						pcsScoringElement.setSequence("NA");
						scoringElements.add(pcsScoringElement);
			    	}
				}
				catch(Exception ec)
				{
					logger.error("There is error while getting array of score elements"+ec);
				}
				
						equifaxPCSDetailslogs.setPcsScoringElements(scoringElements);
				    	
				    	
				    }
		catch(Exception ec)
		{
			logger.error("There is error while getting array of score elements"+ec);
		}
	
			
			/////////////////////// getting Array of Scoring Elements//////////////////////

			///////////////////for saving data of Account Summary//////////////////////////////
			EquifaxPcsAccountSummary account_summary=new EquifaxPcsAccountSummary();
			try
			{
				account_summary.setEquifaxpcDetailslogs(equifaxPCSDetailslogs);
				JSONObject accountSummary=new JSONObject();
				
				if(requiredtransaction.has("accountsummary")){

					accountSummary=requiredtransaction.getJSONObject("accountsummary");
					if(accountSummary.has("noofactiveaccounts")){
						account_summary.setNo_of_active_accounts(accountSummary.get("noofactiveaccounts").toString());
					}else{
						account_summary.setNo_of_active_accounts("NA");
					}
					
					if(accountSummary.has("noofpastdueaccounts")){
						account_summary.setNo_of_past_due_account(accountSummary.get("noofpastdueaccounts").toString());
					}else{
						account_summary.setNo_of_past_due_account("NA");
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

					account_summary.setNo_of_active_accounts("NA");
					account_summary.setNo_of_past_due_account("NA");
					account_summary.setTotal_balance_amount("NA");
					account_summary.setTotal_monthly_payment_amount("NA");
					account_summary.setTotal_past_due("NA");
					account_summary.setAverage_open_balance("NA");
					account_summary.setOldest_account("NA");
					account_summary.setNo_of_zero_balance_account("NA");
					account_summary.setNo_of_accounts("NA");
					account_summary.setTotal_sanction_amount("NA");
					account_summary.setTotal_credit_limit("NA");
					account_summary.setRecent_account("NA");
					account_summary.setSingle_highest_sanction_amount("NA");
					account_summary.setTotal_high_credit("NA");
					account_summary.setAverage_open_balance("NA");
					account_summary.setSingle_highest_balance("NA");
					account_summary.setSingle_highest_credit("NA");
				
				}

			}
			catch(Exception ec)
			{
				logger.error("There is errror while saving data foe account summary"+ec);
			}
			//////////////////for saving data of Account Summary//////////////////////////////
			equifaxPCSDetailslogs.setAccountSummary(account_summary);

			////////////////// for saving data of inquiries/////////////////////////
			List<EquifaxPcsEnquiry> equifaxpcsenquiries=new ArrayList<EquifaxPcsEnquiry>();
			if(requiredtransaction.has("enquiries")){
			JSONArray enquiries=requiredtransaction.getJSONArray("enquiries");
			try
			{
				for(int i=0;i<enquiries.length();i++)
				{
					EquifaxPcsEnquiry enquiryEntity=new EquifaxPcsEnquiry();
					enquiryEntity.setEquifaxPcsDetailsLogs(equifaxPCSDetailslogs);
					JSONObject enquiry=enquiries.getJSONObject(i);
					if(enquiry.has("date")){
						enquiryEntity.setEnquiry_date(enquiry.get("date").toString());
					}else{
						enquiryEntity.setEnquiry_date("NA");
					}
					if(enquiry.has("seq")){
						enquiryEntity.setEnquiry_sequence(enquiry.get("seq").toString());
					}else{
						enquiryEntity.setEnquiry_sequence("NA");
					}
					if(enquiry.has("time")){
						enquiryEntity.setEnquiry_time(enquiry.get("time").toString());
					}else{
						enquiryEntity.setEnquiry_time("NA");
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
					equifaxpcsenquiries.add(enquiryEntity);
				}
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data for enquiry"+ec); 
			}
			}else{
				EquifaxPcsEnquiry enquiryEntity=new EquifaxPcsEnquiry();
				enquiryEntity.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
				enquiryEntity.setEnquiry_date("NA");
				enquiryEntity.setEnquiry_sequence("NA");
				enquiryEntity.setEnquiry_time("NA");
				enquiryEntity.setInstitution("NA");
				enquiryEntity.setRequest_purpose("NA");
				equifaxpcsenquiries.add(enquiryEntity);
			}
			equifaxPCSDetailslogs.setEquifaxPcsEnquiries(equifaxpcsenquiries);
			//////////////////for saving data of inquiries/////////////////////////


			///////////////////for saving recent activities////////////////////////


			EquifaxPcsRecentActivities recentactivity=new EquifaxPcsRecentActivities();
			try
			{
				recentactivity.setEquifaxpcDetailslogs(equifaxPCSDetailslogs);
				if(requiredtransaction.has("recentactivities")){
					JSONObject recentActivity=requiredtransaction.getJSONObject("recentactivities");
					if(recentActivity.has("totalinquiries")){
						recentactivity.setTotal_enquiries(recentActivity.get("totalinquiries").toString());
					}else{
						recentactivity.setTotal_enquiries("NA");
					}
					if(recentActivity.has("accountsupdated")){
						recentactivity.setAccounts_updated(recentActivity.get("accountsupdated").toString());
					}else{
						recentactivity.setAccounts_updated("NA");
					}
					if(recentActivity.has("accountsdeliquent")){
						recentactivity.setAccounts_deliquent(recentActivity.get("accountsdeliquent").toString());
					}else{
						recentactivity.setAccounts_deliquent("NA");
					}
					if(recentActivity.has("accountsopened")){
						recentactivity.setAccounts_opened(recentActivity.get("accountsopened").toString());
					}else{
						recentactivity.setAccounts_opened("NA");
					}
				}else{
					recentactivity.setTotal_enquiries("NA");
					recentactivity.setAccounts_updated("NA");
					recentactivity.setAccounts_deliquent("NA");
					recentactivity.setAccounts_opened("NA");
				}
			}
			catch(Exception ec)
			{
				logger.error(" There is error while saving data for recent activity "+ec);
			}
			equifaxPCSDetailslogs.setRecentActivities(recentactivity);
			//////////////////for saving recent activities/////////////////////////

			//////////////////////for saving data to other key//////////////////////

			EquifaxPcsOthers pcsOtherkey=new EquifaxPcsOthers();
			try
			{
				pcsOtherkey.setEquifaxpcDetailslogs(equifaxPCSDetailslogs);
				JSONObject otherkeyind=requiredtransaction.getJSONObject("otherkeyind");
				pcsOtherkey.setAge_of_oldest_trade(otherkeyind.get("ageofoldesttrade").toString());
				pcsOtherkey.setAll_line_sever_written(otherkeyind.get("alllineseverwritten").toString());
				pcsOtherkey.setNumber_of_open_trades(otherkeyind.get("numberofopentrades").toString());
				pcsOtherkey.setSever_written_in_nine_month(otherkeyind.get("alllineseverwrittenin9months").toString());
				pcsOtherkey.setSever_written_in_six_month(otherkeyind.get("alllineseverwrittenin6months").toString());


			}
			catch(Exception ec)
			{
				logger.error("There is error while saving other key data"+ec); 
			}
			
			EquifaxPcsOthers keyInd=new EquifaxPcsOthers();
    		try{
    			keyInd.setEquifaxpcDetailslogs(equifaxPCSDetailslogs);
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
			    					keyInd.setAge_of_oldest_trade(otherKey.get("ageofoldesttrade").toString());
			    				}else{
			    					keyInd.setAge_of_oldest_trade("NA");
			    				}
			    				if(otherKey.has("alllineseverwritten")){
			    					keyInd.setAll_line_sever_written(otherKey.get("alllineseverwritten").toString());
			    				}else{
			    					keyInd.setAll_line_sever_written("NA");
			    				}
			    				if(otherKey.has("alllineseverwrittenin6months")){
			    					keyInd.setSever_written_in_six_month(otherKey.get("alllineseverwrittenin6months").toString());
			    				}else{
			    					keyInd.setSever_written_in_six_month("NA");
			    				}
			    				if(otherKey.has("alllineseverwrittenin9months")){
			    					keyInd.setSever_written_in_nine_month(otherKey.get("alllineseverwrittenin9months").toString());
			    				}else{
			    					keyInd.setSever_written_in_nine_month("NA");
			    				}
			    				if(otherKey.has("numberofopentrades")){
			    					keyInd.setNumber_of_open_trades(otherKey.get("numberofopentrades").toString());
			    				}else{
			    					keyInd.setNumber_of_open_trades("NA");
			    				}
			    			}
			    			catch(Exception ec)
			    			{
			    				logger.error("error while saving data into otherKeyInd PCS"+ec);
			    			}
			
			    		 }
	    			}else{
		    			keyInd.setAge_of_oldest_trade("NA");
						keyInd.setAll_line_sever_written("NA");
						keyInd.setSever_written_in_six_month("NA");
						keyInd.setSever_written_in_nine_month("NA");
						keyInd.setNumber_of_open_trades("NA");
			    		}
	    		}catch(Exception ee){
	    			keyInd.setAge_of_oldest_trade("NA");
					keyInd.setAll_line_sever_written("NA");
					keyInd.setSever_written_in_six_month("NA");
					keyInd.setSever_written_in_nine_month("NA");
					keyInd.setNumber_of_open_trades("NA");
    			logger.error("error while saving data into otherkeyInd MCR "+ee);
    		}
			
			equifaxPCSDetailslogs.setEquifaxPcsOther(keyInd);
			//////////////////////for saving data to other key//////////////////////


			////////////////////////for saving data into Enquiry Summary///////////////////////////////
			EquifaxPcsEnquirySummary pcsEnquirySummary=new EquifaxPcsEnquirySummary();
			try
			{
				pcsEnquirySummary.setEquifaxpcDetailslogs(equifaxPCSDetailslogs);
				if(requiredtransaction.has("enquirysummary")){
						JSONObject enquirySummary=requiredtransaction.getJSONObject("enquirysummary");
						pcsEnquirySummary.setEquifaxpcDetailslogs(equifaxPCSDetailslogs);
						if(enquirySummary.has("past30days")){
							pcsEnquirySummary.setPast_thirty_days(enquirySummary.get("past30days").toString());
						}else{
							pcsEnquirySummary.setPast_thirty_days("NA");
						}
						
						if(enquirySummary.has("past12months")){
							pcsEnquirySummary.setPast_twelve_month(enquirySummary.get("past12months").toString());;
						}else{
							pcsEnquirySummary.setPast_twelve_month("NA");
						}
						
						if(enquirySummary.has("past24months")){
							pcsEnquirySummary.setPast_twenty_four_month(enquirySummary.get("past24months").toString());
						}else{
							pcsEnquirySummary.setPast_twenty_four_month("NA");
						}
						
						if(enquirySummary.has("purpose")){
							pcsEnquirySummary.setPurpose(enquirySummary.get("purpose").toString());
						}else{
							pcsEnquirySummary.setPurpose("NA");
						}
						
						if(enquirySummary.has("recent")){
							pcsEnquirySummary.setRecent(enquirySummary.get("recent").toString());
						}else{
							pcsEnquirySummary.setRecent("NA");
						}
						
						if(enquirySummary.has("total")){
							pcsEnquirySummary.setTotal(enquirySummary.get("total").toString());
						}else{
							pcsEnquirySummary.setTotal("NA");
						}
				
				}else{
						pcsEnquirySummary.setPast_thirty_days("NA");
						pcsEnquirySummary.setPast_twelve_month("NA");
						pcsEnquirySummary.setPast_twenty_four_month("NA");
						pcsEnquirySummary.setPurpose("NA");
						pcsEnquirySummary.setRecent("NA");
						pcsEnquirySummary.setTotal("NA");
					}
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving enquiry summary"+ec);
			}
			equifaxPCSDetailslogs.setPcsEnquirySummary(pcsEnquirySummary);


			///////////////////////for saving data into Enquiry Summary////////////////////////////////


///////////////////////for saving data for Address////////////////////////////////////////
/*List<EquifaxPcsAddressDetails> pcsAddressDetails=new ArrayList<EquifaxPcsAddressDetails>();
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
			EquifaxPcsAddressDetails pcsAddressDetail=new EquifaxPcsAddressDetails();
			try
			{
				pcsAddressDetail.setAddressDetail_id(Long.parseLong(dbEquifax.getSeqNextValue(SQLConstants.qc_pcs_address_sequence)));
				if((address.has("address"))){
					pcsAddressDetail.setAddress(address.get("address").toString());
				}else{
					pcsAddressDetail.setAddress("NA");
				}
				if((address.has("postal"))){
					pcsAddressDetail.setPostal(address.get("postal").toString());
				}else{
					pcsAddressDetail.setPostal("NA");
				}
				if((address.has("seq"))){
					pcsAddressDetail.setSeq(address.get("seq").toString());
				}else{
					pcsAddressDetail.setSeq("NA");
				}
				if((address.has("state"))){
					pcsAddressDetail.setState(address.get("state").toString());
				}else{
					pcsAddressDetail.setState("NA");
				}
				if((address.has("type"))){
					pcsAddressDetail.setType(address.get("type").toString());
				}else{
					pcsAddressDetail.setType("NA");
				}
				if((address.has("reporteddate"))){
					pcsAddressDetail.setReportedDate(address.get("reporteddate").toString());
				}else{
					pcsAddressDetail.setReportedDate("NA");
				}

			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data for pcsAddressDetails"+ec);
			}
			pcsAddressDetails.add(pcsAddressDetail);
		}
		equifaxPCSDetailslogs.setPcsAddressDetails(pcsAddressDetails);
	}catch(Exception ee){
		logger.info("error while parsing address jsonObject "+ee);
		
	}*/
	
	
	try
	{
		if(requiredtransaction.has("idandcontactinfo"))
		{
			JSONObject idandcontactinfo=requiredtransaction.getJSONObject("idandcontactinfo");
			List<EquifaxPcsAddressDetails> pcsAddressDetails=new ArrayList<EquifaxPcsAddressDetails>();
			try
			{
				if(idandcontactinfo.has("addressinfo"))
				{

					JSONArray addressDetail=null;
					try
					{
						addressDetail=idandcontactinfo.getJSONArray("addressinfo");
					}
					catch(Exception ee)
					{
						addressDetail=new JSONArray().put(idandcontactinfo.getJSONObject("addressinfo"));
					}
					for(int i=0;i<addressDetail.length();i++)
					{
						JSONObject address=addressDetail.getJSONObject(i);
						EquifaxPcsAddressDetails pcsAddressDetail=new EquifaxPcsAddressDetails();
						pcsAddressDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
						try
						{
							//
							if((address.has("address"))){
								pcsAddressDetail.setAddress(address.get("address").toString());
							}else{
								pcsAddressDetail.setAddress("NA");
							}
							if((address.has("postal"))){
								pcsAddressDetail.setPostal(address.get("postal").toString());
							}else{
								pcsAddressDetail.setPostal("NA");
							}
							if((address.has("seq"))){
								pcsAddressDetail.setSeq(address.get("seq").toString());
							}else{
								pcsAddressDetail.setSeq("NA");
							}
							if((address.has("state"))){
								pcsAddressDetail.setState(address.get("state").toString());
							}else{
								pcsAddressDetail.setState("NA");
							}
							if((address.has("type"))){
								pcsAddressDetail.setType(address.get("type").toString());
							}else{
								pcsAddressDetail.setType("NA");
							}
							if((address.has("reporteddate"))){
								pcsAddressDetail.setReportedDate(address.get("reporteddate").toString());
							}else{
								pcsAddressDetail.setReportedDate("NA");
							}
						}
						catch(Exception ec)
						{
							logger.error("There is error while saving data for pcsAddressDetails"+ec);
						}
						pcsAddressDetails.add(pcsAddressDetail);
					}
				}
				else
				{
					EquifaxPcsAddressDetails pcsAddressDetail=new EquifaxPcsAddressDetails();
					pcsAddressDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
					pcsAddressDetail.setAddress("NA");
					pcsAddressDetail.setPostal("NA");
					pcsAddressDetail.setSeq("NA");
					pcsAddressDetail.setState("NA");
					pcsAddressDetail.setType("NA");
					pcsAddressDetail.setReportedDate("NA");
					pcsAddressDetails.add(pcsAddressDetail);
				}
			}
			catch(Exception ee)
			{
				logger.info("error while parsing address jsonObject "+ee);

			}
			equifaxPCSDetailslogs.setPcsAddressDetails(pcsAddressDetails);
			}
		}catch(Exception eee){
			logger.info("error while parsing address jsonObject m, tag not found"+eee);
		}
	//////////////////////for saving data for Address///////////////////////////////////////// 
	
	
	///////////////////////for saving data for Phone////////////////////////////////////////
	List<EquifaxPcsPhoneDetails> pcsPhoneDetails=new ArrayList<EquifaxPcsPhoneDetails>();
	try{
		JSONArray phoneDetail=null;
		try{
			phoneDetail=requiredtransaction.getJSONObject("idandcontactinfo").getJSONArray("phoneinfo");
		}catch(Exception ee){
			phoneDetail=new JSONArray().put(requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("phoneinfo"));
		}
		
		for(int i=0;i<phoneDetail.length();i++)
		{
			JSONObject phone=phoneDetail.getJSONObject(i);
			EquifaxPcsPhoneDetails pcsPhoneDetail=new EquifaxPcsPhoneDetails();
			try
			{
				pcsPhoneDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
				if(phone.has("typecode")){
					pcsPhoneDetail.setPhoneTypecode(phone.get("typecode").toString());
				}else{
					pcsPhoneDetail.setPhoneTypecode("NA");
				}
				if(phone.has("seq")){
					pcsPhoneDetail.setPhoneSeq(phone.get("seq").toString());
				}else{
					pcsPhoneDetail.setPhoneSeq("NA");
				}
				if(phone.has("number")){
					pcsPhoneDetail.setPhoneNumber(phone.get("number").toString());
				}else{
					pcsPhoneDetail.setPhoneNumber("NA");
				}
				if(phone.has("reporteddate")){
					pcsPhoneDetail.setPhoneReportedDate(phone.get("reporteddate").toString());
				}else{
					pcsPhoneDetail.setPhoneReportedDate("NA");
				}
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data for pcsPhoneDetail"+ec);
			}
			pcsPhoneDetails.add(pcsPhoneDetail);			
		}
		
	}catch(Exception ec)
	{
		EquifaxPcsPhoneDetails pcsPhoneDetail=new EquifaxPcsPhoneDetails();
		pcsPhoneDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
			pcsPhoneDetail.setPhoneTypecode("NA");
			pcsPhoneDetail.setPhoneSeq("NA");
			pcsPhoneDetail.setPhoneNumber("NA");
			pcsPhoneDetail.setPhoneReportedDate("NA");
			pcsPhoneDetails.add(pcsPhoneDetail);
		logger.error("There is error while saving data for pcsPhoneDetail"+ec);
	}
	equifaxPCSDetailslogs.setPcsPhoneDetails(pcsPhoneDetails);
	/////////////////////for saving data for Phone////////////////////////////////////////
	
	///////////////////////for saving data for PAN////////////////////////////////////////
	List<EquifaxPcsPanDetails> pcsPanDetails=new ArrayList<EquifaxPcsPanDetails>();	
	try{
			JSONArray panDetail=null;
			try{
				panDetail=requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONArray("panid");
			}catch(Exception ee){
				panDetail=new JSONArray().put(	requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONObject("panid"));
			}
			
			
			for(int i=0;i<panDetail.length();i++)
			{
				JSONObject pan=panDetail.getJSONObject(i);
				EquifaxPcsPanDetails pcsPanDetail=new EquifaxPcsPanDetails();
				try
				{
					pcsPanDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
					if(pan.has("seq")){
						pcsPanDetail.setPanSeq(pan.get("seq").toString());
					}else{
						pcsPanDetail.setPanSeq("NA");
					}
					if(pan.has("idnumber")){
						pcsPanDetail.setPanNumber(pan.get("idnumber").toString());
					}else{
						pcsPanDetail.setPanNumber("NA");
					}
					if(pan.has("reporteddate")){
						pcsPanDetail.setPanReportedDate(pan.get("reporteddate").toString());
					}else{
						pcsPanDetail.setPanReportedDate("NA");
					}
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for pcsPanDetails "+ec);
				}

				pcsPanDetails.add(pcsPanDetail);
			}
			
		}catch(Exception ec)
		{
			EquifaxPcsPanDetails pcsPanDetail=new EquifaxPcsPanDetails();
			pcsPanDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
					pcsPanDetail.setPanSeq("NA");
					pcsPanDetail.setPanNumber("NA");
					pcsPanDetail.setPanReportedDate("NA");
					pcsPanDetails.add(pcsPanDetail);
			logger.error("There is error while saving data for pcsPanDetails "+ec);
		}
		equifaxPCSDetailslogs.setPcsPanDetails(pcsPanDetails);
/////////////////////for saving data for PAN////////////////////////////////////////
	
	///////////////////////for saving data for Voter////////////////////////////////////////
	List<EquifaxPcsVoterDetails> pcsVoterDetails=new ArrayList<EquifaxPcsVoterDetails>();
	try{
		JSONArray voterDetail=null;
		try{ 
			voterDetail=new JSONArray().put(requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONObject("voterid"));
		}catch(Exception ee){
			voterDetail=requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("identityinfo").getJSONArray("voterid");
		}
		
		
		for(int i=0;i<voterDetail.length();i++)
		{
			JSONObject vote=voterDetail.getJSONObject(i);
		EquifaxPcsVoterDetails pcsVoterDetail=new EquifaxPcsVoterDetails();
		try
		{
			pcsVoterDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
			if(vote.has("seq")){
				pcsVoterDetail.setVoterIdSeq(vote.get("seq").toString());
			}else{
				pcsVoterDetail.setVoterIdSeq("NA");
			}
			if(vote.has("idnumber")){
				pcsVoterDetail.setVoterIdNum(vote.get("idnumber").toString());
			}else{
				pcsVoterDetail.setVoterIdNum("NA");
			}
			if(vote.has("reporteddate")){
				pcsVoterDetail.setVoterIdreRorteddate(vote.get("reporteddate").toString());
			}else{
				pcsVoterDetail.setVoterIdreRorteddate("NA");
			}
		}
		catch(Exception ec)
		{
			logger.error("There is error while saving data for voter detail"+ec);
		}

		pcsVoterDetails.add(pcsVoterDetail);
		}
		equifaxPCSDetailslogs.setPcsVoterDetails(pcsVoterDetails);
	}catch(Exception ec)
	{
		EquifaxPcsVoterDetails pcsVoterDetail=new EquifaxPcsVoterDetails();
		pcsVoterDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
				pcsVoterDetail.setVoterIdSeq("NA");
				pcsVoterDetail.setVoterIdNum("NA");
				pcsVoterDetail.setVoterIdreRorteddate("NA");
				pcsVoterDetails.add(pcsVoterDetail);
		logger.error("There is error while saving data for voter detail"+ec);
	}
	equifaxPCSDetailslogs.setPcsVoterDetails(pcsVoterDetails);
	//////////////////////for saving data for voter///////////////////////////////////////// 
	
	///////////////////////for saving data for Personal detail////////////////////////////////////////
	try{
		JSONObject personalDetailValue=requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("personalinfo");
		List<EquifaxPcsPersonalDetails> personalDetails=new ArrayList<EquifaxPcsPersonalDetails>();
		if(personalDetailValue!=null){
			
		
			EquifaxPcsPersonalDetails personalDetail=new EquifaxPcsPersonalDetails();
			
			try
			{
				personalDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
				if(personalDetailValue.has("totalincome")){
					personalDetail.setTotalincome(personalDetailValue.get("totalincome").toString());
				}else{
					personalDetail.setTotalincome("NA");
				}
				if(personalDetailValue.has("occupation")){
					personalDetail.setOccupation(personalDetailValue.get("occupation").toString());
				}else{
					personalDetail.setOccupation("NA");
				}
				if(personalDetailValue.has("age")){
					if(personalDetailValue.getJSONObject("age").has("age"))
					{
						personalDetail.setAge(personalDetailValue.getJSONObject("age").get("age").toString());
					}else{
						personalDetail.setAge("NA");
					}
				}
				if(personalDetailValue.has("name"))
				{
					if(personalDetailValue.getJSONObject("name").has("middlename"))
					{
						personalDetail.setMiddleName(personalDetailValue.getJSONObject("name").get("middlename").toString());
					}
					else
					{
						personalDetail.setMiddleName("");
					}
					if(personalDetailValue.getJSONObject("name").has("lastname"))
					{
						personalDetail.setLastName(personalDetailValue.getJSONObject("name").get("lastname").toString());
					}
					else
					{
						personalDetail.setLastName("");
					}
					if(personalDetailValue.getJSONObject("name").has("firstname"))
					{
						personalDetail.setFirstName(personalDetailValue.getJSONObject("name").get("firstname").toString());
					}
					else
					{
						personalDetail.setFirstName("");
					}
					if(personalDetailValue.getJSONObject("name").has("suffix"))
					{
						personalDetail.setSuffix(personalDetailValue.getJSONObject("name").get("suffix").toString());
					}
					else
					{
						personalDetail.setSuffix("NA");
					}
					if(personalDetailValue.getJSONObject("name").has("additionalmiddlename"))
					{
						personalDetail.setAdditionalmiddlename(personalDetailValue.getJSONObject("name").get("additionalmiddlename").toString());
					}
					else
					{
						personalDetail.setAdditionalmiddlename("NA");
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
		    		personalDetail.setPreviousNames(previousNamesList);
				}else{
					PreviousName preName=new PreviousName();
						preName.setMiddle_name("");
						preName.setLast_name("");
						preName.setFirst_name("NA");
						preName.setAdditionalmiddlename("");
						preName.setSuffix("");
					previousNamesList.add(preName);
					personalDetail.setPreviousNames(previousNamesList);
				}
				if(personalDetailValue.has("gender"))
				{
					personalDetail.setGender(personalDetailValue.get("gender").toString());
				}
				else
				{
					personalDetail.setGender("NA");
				}
				if(personalDetailValue.has("dateofbirth")){
					personalDetail.setDateofbirth(personalDetailValue.get("dateofbirth").toString());
				}
				else
				{
					personalDetail.setDateofbirth("NA");
				}
				if(personalDetailValue.has("maritalstatus"))
				{
					personalDetail.setMaritalStatus(personalDetailValue.get("maritalstatus").toString());
				}
				else
				{
					personalDetail.setMaritalStatus("NA");
				}
				if(personalDetailValue.has("aliasnameinfo"))
				{
					personalDetail.setAliasNameInfo(personalDetailValue.get("aliasnameinfo").toString());
				}
				else
				{
					personalDetail.setAliasNameInfo("NA");
				}
				
			
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data for personalDetail "+ec);
			}
		
			personalDetails.add(personalDetail);
			}
		equifaxPCSDetailslogs.setPcsPersonalDetails(personalDetails);
	}catch(Exception ec)
	{
		logger.error("There is error while saving data for personalDetail "+ec);
	}

	//////////////////////for saving data for Personal detail///////////////////////////////////////// 
	
	///////////////////////for saving data for Email detail////////////////////////////////////////
	
		try{
			JSONArray emailDetail=null;
			try{
				emailDetail=new JSONArray().put(requiredtransaction.getJSONObject("idandcontactinfo").getJSONObject("emailaddressinfo"));
			}catch(Exception ee){
				 emailDetail=requiredtransaction.getJSONObject("idandcontactinfo").getJSONArray("emailaddressinfo");
			}
			
			List<EquifaxPcsEmailDetails> emailsDetails=new ArrayList<EquifaxPcsEmailDetails>();
			for(int i=0;i<emailDetail.length();i++)
			{
				JSONObject email=emailDetail.getJSONObject(i);
				if(email!=null){
					EquifaxPcsEmailDetails emaillDetail=new EquifaxPcsEmailDetails();
					try
					{
						emaillDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
						if(email.has("emailaddress")){
							emaillDetail.setEmailaddress(email.get("emailaddress").toString());
						}else{
							emaillDetail.setEmailaddress("NA");
						}
						if(email.has("seq")){
							emaillDetail.setSeq(email.get("seq").toString());
						}else{
							emaillDetail.setSeq("NA");
						}
						if(email.has("reporteddate")){
							emaillDetail.setReporteddate(email.get("reporteddate").toString());
						}else{
							emaillDetail.setReporteddate("NA");
						}
					}
					catch(Exception ec)
					{
						logger.error("There is error while saving data for Email Detail "+ec);
					}
		
					emailsDetails.add(emaillDetail);
				}
			}
			equifaxPCSDetailslogs.setPcsEmailDetails(emailsDetails);
		}catch(Exception ec)
		{
			logger.error("There is error while saving data for Email Detail "+ec);
		}

	//////////////////////for saving data for Email detail///////////////////////////////////////// 

			///////////////////////for saving data for Accounts////////////////////////////////////////
			JSONArray accounts=requiredtransaction.getJSONObject("accountdetails").getJSONArray("account");
			List<EquifaxPcsAccountDetails> pcsAccountsDetail=new ArrayList<EquifaxPcsAccountDetails>();
			for(int i=0;i<accounts.length();i++)
			{
				JSONObject account=accounts.getJSONObject(i);
				EquifaxPcsAccountDetails pcsAccountDetail=new EquifaxPcsAccountDetails();
				try
				{

					pcsAccountDetail.setEquifaxPcsAllDetails(equifaxPCSDetailslogs);
					if(account.has("accountnumber")){
						pcsAccountDetail.setAccount_number(account.get("accountnumber").toString());
					}else{
						pcsAccountDetail.setAccount_number("NA");
					}
					if(account.has("accountstatus")){
						pcsAccountDetail.setAccount_status(account.get("accountstatus").toString());
					}else{
						pcsAccountDetail.setAccount_status("NA");
					}
					
					if(account.has("accounttype")){
						pcsAccountDetail.setAccount_type(account.get("accounttype").toString());
					}else{
						pcsAccountDetail.setAccount_type("NA");
					}
					
					if(account.has("assetclassification")){
						pcsAccountDetail.setAsset_classification(account.get("assetclassification").toString());
					}else{
						pcsAccountDetail.setAsset_classification("NA");
					}
					
					if(account.has("balance")){
						pcsAccountDetail.setBalance(account.get("balance").toString());
					}else{
						pcsAccountDetail.setBalance("NA");
					}
					
					if(account.has("collateraltype")){
						pcsAccountDetail.setCollateral_type(account.get("collateraltype").toString());
					}else{
						pcsAccountDetail.setCollateral_type("NA");
					}
					
					if(account.has("disputecode")){
						pcsAccountDetail.setDispute_code(account.get("disputecode").toString());
					}else{
						pcsAccountDetail.setDispute_code("NA");
					}
					
					if(account.has("history48months")){
						pcsAccountDetail.setHistory_fourty_eight_months(account.get("history48months").toString());
					}else{
						pcsAccountDetail.setHistory_fourty_eight_months("NA");
					}
					
					if(account.has("installmentamount")){
						pcsAccountDetail.setInstallment_amount(account.get("installmentamount").toString());
					}else{
						pcsAccountDetail.setInstallment_amount("NA");
					}
					
					if(account.has("institution")){
						pcsAccountDetail.setInstitution(account.get("institution").toString());
					}else{
						pcsAccountDetail.setInstitution(account.get("institution").toString());
					}
					
					if(account.has("interestrate")){
						pcsAccountDetail.setInterestrate(account.get("interestrate").toString());
					}else{
						pcsAccountDetail.setInterestrate("NA");
					}
					
					if(account.has("lastpayment")){
						pcsAccountDetail.setLast_payment(account.get("lastpayment").toString());
					}else{
						pcsAccountDetail.setLast_payment("NA");
					}
					
					if(account.has("open")){
						pcsAccountDetail.setOpen(account.get("open").toString());
					}else{
						pcsAccountDetail.setOpen("NA");
					}
					
					if(account.has("ownershiptype")){
						pcsAccountDetail.setOwner_ship_type(account.get("ownershiptype").toString());
					}else{
						pcsAccountDetail.setOwner_ship_type("NA");
					}
					
					if(account.has("reason")){
						pcsAccountDetail.setReason(account.get("reason").toString());
					}else{
						pcsAccountDetail.setReason("NA");
					}
					
					if(account.has("reporteddate")){
						pcsAccountDetail.setReported_date(account.get("reporteddate").toString());
					}else{
						pcsAccountDetail.setReported_date("NA");
					}
					
					if(account.has("sanctionamount")){
						pcsAccountDetail.setSanction_amount(account.get("sanctionamount").toString());
					}else{
						pcsAccountDetail.setSanction_amount("NA");
					}
					
					if(account.has("suitfiledstatus")){
						pcsAccountDetail.setSuit_filed_status(account.get("suitfiledstatus").toString());
					}else{
						pcsAccountDetail.setSuit_filed_status("NA");
					}
					
					if(account.has("termfrequency")){
						pcsAccountDetail.setTerm_frequency(account.get("termfrequency").toString());
					}else{
						pcsAccountDetail.setTerm_frequency("NA");
					}
					
					if(account.has("lastpaymentdate")){
						pcsAccountDetail.setLastPaymentDate(account.get("lastpaymentdate").toString());
						}else{
							pcsAccountDetail.setLastPaymentDate("NA");
						}
					if(account.has("highcredit")){
						pcsAccountDetail.setHighCredit(account.get("highcredit").toString());
						}else{
							pcsAccountDetail.setHighCredit("NA");
						}
					if(account.has("pastdueamount")){
						pcsAccountDetail.setPastDueAmount(account.get("pastdueamount").toString());
						}else{
							pcsAccountDetail.setPastDueAmount("NA");
						}
					if(account.has("creditlimit")){
						pcsAccountDetail.setCreditLimit(account.get("creditlimit").toString());
						}else{
							pcsAccountDetail.setCreditLimit("NA");;
						}
					if(account.has("otherlastpayment")){
						pcsAccountDetail.setOtherLastPayment(account.get("otherlastpayment").toString());
						}else{
							pcsAccountDetail.setOtherLastPayment("NA");;
						}
					if(account.has("individualwriteoffamount")){
						pcsAccountDetail.setIndividualwriteOffAmount(account.get("individualwriteoffamount").toString());
						}else{
							pcsAccountDetail.setIndividualwriteOffAmount("NA");;
						}
					if(account.has("dateopened")){
						pcsAccountDetail.setDateopened(account.get("dateopened").toString());
						}else{
							pcsAccountDetail.setDateopened("NA");;
						}
					if(account.has("dateclosed")){
						pcsAccountDetail.setDateClosed(account.get("dateclosed").toString());
						}else{
							pcsAccountDetail.setDateClosed("NA");;
						}
					
					if(account.has("history48months"))
					{
						List<EquifaxHistory48MonthsDetail> _48monthDetails=new ArrayList<EquifaxHistory48MonthsDetail>();
						
						if(account.has("history48months"))
						{
							JSONArray historyDetail=null;
							try
							{
								historyDetail=new JSONArray().put(account.getJSONObject("history48months").getJSONObject("month"));
							}
							catch(Exception ee)
							{
								historyDetail=account.getJSONObject("history48months").getJSONArray("month");
							}
							for(int k=0;k<historyDetail.length();k++)
							{
								JSONObject month48=historyDetail.getJSONObject(k);
								EquifaxHistory48MonthsDetail _48monthDetail=new EquifaxHistory48MonthsDetail();
								try
								{
									if(month48.has("key")){
										_48monthDetail.setKey(month48.get("key").toString());
									}else{
										_48monthDetail.setKey("NA");
									}
									if(month48.has("paymentstatus")){
										_48monthDetail.setPaymentstatus(month48.get("paymentstatus").toString());
									}else{
										_48monthDetail.setPaymentstatus("NA");
									}
									if(month48.has("suitfiledstatus")){
										_48monthDetail.setSuitFiledStatus(month48.get("suitfiledstatus").toString());
									}else{
										_48monthDetail.setSuitFiledStatus("NA");
									}
									if(month48.has("assetclassificationstatus")){
										_48monthDetail.setAssetClassificationStatus(month48.get("assetclassificationstatus").toString());
									}else{
										_48monthDetail.setAssetClassificationStatus("NA");
									}
									
								}
								catch(Exception ec)
								{
									logger.error("There is error while saving data for History48Months"+ec);
								}
								_48monthDetails.add(_48monthDetail);
								
							}
						}
						else
						{
							EquifaxHistory48MonthsDetail _48monthDetail=new EquifaxHistory48MonthsDetail();
							_48monthDetail.setKey("NA");
							_48monthDetail.setPaymentstatus("NA");
							_48monthDetail.setAssetClassificationStatus("NA");
							_48monthDetail.setSuitFiledStatus("NA");
							_48monthDetails.add(_48monthDetail);
						}
						pcsAccountDetail.setHistory48MonthsDetail(_48monthDetails);
					}else{
						pcsAccountDetail.setHistory48MonthsDetail(null);
					}
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for Account"+ec);
				}

				pcsAccountsDetail.add(pcsAccountDetail);
			}
			equifaxPCSDetailslogs.setPcsAccountDetails(pcsAccountsDetail);
			try
			{
				JSONObject inquiryresponseheader=new JSONObject(equifaxTrackerDTO.getResponseTransactionJson()).getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("inquiryresponseheader");
				if(inquiryresponseheader.has("date")){
					equifaxPCSDetailslogs.setResponse_date(inquiryresponseheader.get("date").toString());
				}
				if(inquiryresponseheader.has("reportorderno")){
					equifaxPCSDetailslogs.setResponse_order_no(inquiryresponseheader.get("reportorderno").toString());
				}
				if(inquiryresponseheader.has("time")){
					equifaxPCSDetailslogs.setResponse_time(inquiryresponseheader.get("time").toString());
				}
				equifaxPCSDetailslogs.setMemberId(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"));
				equifaxPCSDetailslogs.setMemberReferenceNumber(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber"));
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving data from enquiry response header");
			}

			//////////////////////for saving data for Accounts///////////////////////////////////////// 
			equifaxPCSDetailslogs.setRemarks("success");

		}
		catch(Exception ec)
		{
			equifaxPCSDetailslogs.setByteArrayData(equifaxTrackerDTO.getByteArray());
			equifaxPCSDetailslogs.setRequestJson(equifaxTrackerDTO.getEquifaxjsonRequest());
			equifaxPCSDetailslogs.setResponseJson(equifaxTrackerDTO.getEquifaxjsonResponse());
			equifaxPCSDetailslogs.setRequest_info_json(request.toString());
			String response_header_info_json=equifaxApiResponse.getHeader().toString();
			equifaxPCSDetailslogs.setResponse_header_info_json(response_header_info_json);
			String response_error_info_json=equifaxApiResponse.getErrorInfo().toString();
			equifaxPCSDetailslogs.setResponse_error_info_json(response_error_info_json); 
			try
			{
				JSONObject inquiryresponseheader=new JSONObject(equifaxTrackerDTO.getResponseTransactionJson()).getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("inquiryresponseheader");
				if(inquiryresponseheader.has("date")){
					equifaxPCSDetailslogs.setResponse_date(inquiryresponseheader.get("date").toString());
				}
				if(inquiryresponseheader.has("reportorderno")){
					equifaxPCSDetailslogs.setResponse_order_no(inquiryresponseheader.get("reportorderno").toString());
				}
				if(inquiryresponseheader.has("time")){
					equifaxPCSDetailslogs.setResponse_time(inquiryresponseheader.get("time").toString());
				}
				equifaxPCSDetailslogs.setMemberId(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"));
				equifaxPCSDetailslogs.setMemberReferenceNumber(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber"));
			}
			catch(Exception ecx)
			{
				logger.error("There is error while saving data from enquiry response header : "+ecx);
			}
			logger.error("There is error while saving equifax data"+ec);
		}
		return equifaxPCSDetailslogs;
	}
}
