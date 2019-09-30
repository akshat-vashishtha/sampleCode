package com.qualtech.equifax.api.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.entity.common.EquifaxOtherKeyInd;
import com.qualtech.equifax.api.entity.common.EquifaxPcsInquiryRequestInfo;
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
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrPhoneDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAddressDetails;

public class ConvertRequestToHTMLForMFI 
{
	private static Logger logger = Logger.getLogger(ConvertRequestToHTMLForMFI.class);
//	@Autowired
	//PropertyFile resProp;
	//public static ResourceBundle resProp = PropertyFile.getInstance().getResourceBundel();
	EquifaxMcrAllDetaills equifaxMcrDetailLogs=new EquifaxMcrAllDetaills(); 
	public void insertCorrectDatainRequest(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			/*  Inquiry purpose   */
			Map<String,String> enquiryPurpose=new HashMap<String,String>();
			enquiryPurpose.put("00", "Other");
			enquiryPurpose.put("01", "Auto Loan");
			enquiryPurpose.put("02","Housing Loan");
			enquiryPurpose.put("03","Property Loan");
			enquiryPurpose.put("04","Loan against Shares/Securities");
			enquiryPurpose.put("05","Personal Loan");
			enquiryPurpose.put("07","Gold Loan");
			enquiryPurpose.put("08","Education Loan");
			enquiryPurpose.put("09","Loan to Professional");
			enquiryPurpose.put("10","Credit Card");
			enquiryPurpose.put("11","Lease");
			enquiryPurpose.put("12","Overdraft");
			enquiryPurpose.put("13","Two-wheeler Loan");
			enquiryPurpose.put("14","Non-Funded Credit Facility");
			enquiryPurpose.put("15","Loan Against Bank Deposits");
			enquiryPurpose.put("16","Fleet Card");
			enquiryPurpose.put("17","Commercial Vehicle Loan");
			enquiryPurpose.put("18","Telco - Wireless");
			enquiryPurpose.put("19","Telco - Broadband");
			enquiryPurpose.put("20","Telco - Landline");
			enquiryPurpose.put("31","Secured Credit Card");
			enquiryPurpose.put("32","Used Car Loan");
			enquiryPurpose.put("33","Construction Equipment Loan");
			enquiryPurpose.put("34","Tractor Loan");
			enquiryPurpose.put("35","Corporate Credit Card");
			enquiryPurpose.put("3A","Auto Lease");
			enquiryPurpose.put("51","Business Loan");
			enquiryPurpose.put("52","Business Loan-Priority Sector-Small Business");
			enquiryPurpose.put("53","Business Loan - Priority Sector- Agriculture");
			enquiryPurpose.put("54","Business Loan - Priority Sector- Others");
			enquiryPurpose.put("55","Business Non-Funded Credit Facility");
			enquiryPurpose.put("56","Business Non-Funded Credit Facility - Priority Sector - Small Business");
			enquiryPurpose.put("57","Business Non-Funded Credit Facility - Priority");

			request.setAttribute("enquiryPurpose", enquiryPurpose);
			/*  Inquiry purpose   */

			String requestJson=request.getParameter("requestJson");
			if(requestJson==null)
			{
				try
				{
					InputStream ins=request.getInputStream();
					InputStreamReader insreader=new InputStreamReader(ins);
					BufferedReader breader=new BufferedReader(insreader);
					@SuppressWarnings("rawtypes")
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					//            List uriResult=  URLEncodedUtils.parse(breader.readLine(), Charset.defaultCharset());
					String completeRequest=uriResult.get(0).toString();
					StringTokenizer stk=new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					requestJson=stk.nextToken();
				}
				catch(Exception ec)
				{
					logger.error("Error in Exception "+ec);
				}
			}

			JSONObject requestJsonData=new JSONObject(requestJson);
			JSONArray transaction=new JSONArray(new JSONObject(requestJsonData.get("payload").toString()).get("transaction").toString());
			JSONObject GeneralUserInfo=new JSONObject(transaction.get(0).toString());
			request.setAttribute("GeneralUserInfo", GeneralUserInfo);
			String finalResponseJson=request.getAttribute("responseFromService").toString();
			JSONObject mainJSOnObject=new JSONObject(finalResponseJson);
			JSONObject responsetransaction=new JSONObject( new JSONObject(mainJSOnObject.get("payload").toString()).get("transaction").toString());
            request.setAttribute("CustomerId",request.getParameter("CustomerId"));
			//request.setAttribute("CustomerId", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"));
			//request.setAttribute("MemberNumber", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber"));
			request.setAttribute("CustomerId",request.getParameter("CustomerId"));
			try
			{
				if(GeneralUserInfo.has("consumerId")){
					String consumerId=GeneralUserInfo.get("consumerId").toString();
					request.setAttribute("consumerId", consumerId);
				}else{
					request.setAttribute("consumerId", "NA");
				}

			}
			catch(Exception ec)
			{
				logger.error(ec);
			}
			



			///////////////////////Account Summary////////////////////////////
			try
			{
				if(responsetransaction.has("accountsummary")){
					JSONObject accountsummary=responsetransaction.getJSONObject("accountsummary");
					request.setAttribute("accountsummarymfi", accountsummary);
				}
			}
			catch(Exception ec)
			{
				ec.printStackTrace();
			}

			///////////////////////////for saving data into Account Summary/////////////////////////////////
			JSONObject accountSummary=new JSONObject();
			EquifaxMCRAccountSummary account_summary=new EquifaxMCRAccountSummary();
			try
			{
				if(responsetransaction.has("accountsummary")){
					accountSummary=responsetransaction.getJSONObject("accountsummary");
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
				request.setAttribute("accountsummary", equifaxMcrDetailLogs.getQuifaxmcrAccountSummarry());
			}
			catch(Exception ec)
			{
				logger.error("There is error while saving account summary"+ec);
			}
			///////////////////////////for saving data into account summary//////////////////////////////////


			//////////////////////Account Summary//////////////////////////////

			/////////////////////////////////Enquiries///////////////////////////////
			/*try
    	    { 
    	    if(responsetransaction.has("enquiries")){	
    	    	JSONArray enquiries=responsetransaction.getJSONArray("enquiries");
    	    	request.setAttribute("enquiries", enquiries);
    	    }
    	    }
    	    catch(Exception ec)
    	    {
    	    	ec.printStackTrace();
    	    }*/

			////////////////for getting Enquiries/////////////////////////
			try
			{
				List<EquifaxMcrEnquiry> equifaxMcrenquiries=new ArrayList<EquifaxMcrEnquiry>();
				if(responsetransaction.has("enquiries")){
					JSONArray enquiries=null;
					try{
						enquiries=responsetransaction.getJSONArray("enquiries");
					}catch(Exception eee){
						enquiries=new JSONArray().put(responsetransaction.getJSONObject("enquiries"));
					}
					try
					{
						for(int i=0;i<enquiries.length();i++)
						{
							EquifaxMcrEnquiry enquiryEntity=new EquifaxMcrEnquiry();
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
					request.setAttribute("enquiries", equifaxMcrDetailLogs.getEquifaxMcrenquiries());
				}else{

					EquifaxMcrEnquiry enquiryEntity=new EquifaxMcrEnquiry();
					enquiryEntity.setInquiry_date("NA");
					enquiryEntity.setSequence("NA");
					enquiryEntity.setInquiry_time("NA");
					enquiryEntity.setInstitution("NA");
					enquiryEntity.setRequest_purpose("NA");
					enquiryEntity.setAmount("NA");
					equifaxMcrenquiries.add(enquiryEntity);
				}
				equifaxMcrDetailLogs.setEquifaxMcrenquiries(equifaxMcrenquiries);
				request.setAttribute("enquiries", equifaxMcrDetailLogs.getEquifaxMcrenquiries());
			}
			catch(Exception ec)
			{
				List<EquifaxMcrEnquiry> equifaxMcrenquiries=new ArrayList<EquifaxMcrEnquiry>();
				EquifaxMcrEnquiry enquiryEntity=new EquifaxMcrEnquiry();
				enquiryEntity.setInquiry_date("NA");
				enquiryEntity.setSequence("NA");
				enquiryEntity.setInquiry_time("NA");
				enquiryEntity.setInstitution("NA");
				enquiryEntity.setRequest_purpose("NA");
				enquiryEntity.setAmount("NA");
				equifaxMcrenquiries.add(enquiryEntity);
				equifaxMcrDetailLogs.setEquifaxMcrenquiries(equifaxMcrenquiries);
				request.setAttribute("enquiries", equifaxMcrDetailLogs.getEquifaxMcrenquiries());
				logger.error(ec);
			}

			/////////////////////////////////Enquiries///////////////////////////////


			////////////////////////////////for saving data into income details////////////////////////////
			List<EquifaxMCRIncomeDetails> equifaxIncomeDetails=new ArrayList<EquifaxMCRIncomeDetails>();
			try{

				if(responsetransaction.has("incomedetails")){
					JSONArray equifaxIncome=null;
					try {
						equifaxIncome = responsetransaction.getJSONArray("incomedetails");
					} catch (JSONException e) {
						equifaxIncome =new JSONArray().put(responsetransaction.getJSONObject("incomedetails")); 
					}
					for(int i=0;i<equifaxIncome.length();i++)
					{
						try
						{
							JSONObject incomeDetail=equifaxIncome.getJSONObject(i);
							EquifaxMCRIncomeDetails equifaxMcrIncome=new EquifaxMCRIncomeDetails();
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
				logger.error("error while saving data into incomeDetails MCR "+ee);
			}
			equifaxMcrDetailLogs.setEquifaxMcrIncomeDetails(equifaxIncomeDetails);
			request.setAttribute("incomedetails", equifaxMcrDetailLogs.getEquifaxMcrIncomeDetails());
			////////////////////////////////for saving data into income details////////////////////////////




			////////////////////////////////for Other KeyInd details////////////////////////////
			EquifaxOtherKeyInd keyInd=new EquifaxOtherKeyInd();
			try{

				if(responsetransaction.has("otherkeyind>")){
					JSONArray equifaxOtherKeyInd=null;
					try {
						equifaxOtherKeyInd = responsetransaction.getJSONArray("otherkeyind");
					} catch (JSONException e) {
						equifaxOtherKeyInd =new JSONArray().put(responsetransaction.getJSONObject("otherkeyind")); 
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
							if(otherKey.has("monthlyincome")){
								keyInd.setOtherKeyId(12);
							}else{
								keyInd.setAgeOfOldestTrade("NA");
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
			request.setAttribute("otherkeyInd", equifaxMcrDetailLogs.getEquifaxMcrOtherKeyInd());
			////////////////////////////////for saving data into otherkeyInd details////////////////////////////



			////////////////////////////////////for Account Details////////////////////

			List<EquifaxMcrAccountDetail> equifaxMcrAccounts=new ArrayList<EquifaxMcrAccountDetail>();

			List<EquifaxMcrAcntDtlMfiAddress> additionalAddr=new ArrayList<EquifaxMcrAcntDtlMfiAddress>();
			List<EquifaxMcrAcntDtlMfiIdentification> additionalIdentity=new ArrayList<EquifaxMcrAcntDtlMfiIdentification>();
			List<EquifaxMcrNomineeDetail> nomineeDetails=new ArrayList<EquifaxMcrNomineeDetail>();
			List<EquifaxMcrKeyPersonDetail> keyPersonDetails=new ArrayList<EquifaxMcrKeyPersonDetail>();
			if(responsetransaction.has("accountdetails")){
				if(responsetransaction.getJSONObject("accountdetails").has("account")){
					JSONArray acountsArray=null;
					try {
						acountsArray = responsetransaction.getJSONObject("accountdetails").getJSONArray("account");
					} catch (JSONException e) {
						logger.info("exception while getting jsonArray of accuntDetails ::"+e.getMessage());
						try {
							acountsArray =new JSONArray().put(responsetransaction.getJSONObject("accountdetails").getJSONObject("account"));
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
					}
					for(int j=0;j<acountsArray.length();j++)
					{
						try
						{
							EquifaxMcrAccountDetail eqfaxmcrAccount=new EquifaxMcrAccountDetail();
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
							////////////////////////////////////////////////////////


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


							//////////////////////////////////////////////////

							if(account.has("accountstatus")){
								eqfaxmcrAccount.setAccount_status(account.get("accountstatus").toString());
							}else{
								eqfaxmcrAccount.setAccount_status("NA");
							}
							if(account.has("additionalmfidetails")){
								List<EquifaxMcrAdditionalMFIDetail> additionalMfiDetails=new ArrayList<EquifaxMcrAdditionalMFIDetail>();
								JSONArray additionalMfiDetail=null;
								try{
									additionalMfiDetail=new JSONArray().put(account.getJSONObject("additionalmfidetails"));
								}catch(Exception ee){
									additionalMfiDetail=account.getJSONArray("additionalmfidetails");
								}

								JSONObject aDetial=additionalMfiDetail.getJSONObject(0);

								EquifaxMcrAdditionalMFIDetail mcrAdditionalMfiDetail=new EquifaxMcrAdditionalMFIDetail();
								EquifaxMcrAcntDtlMfiAddress address =new EquifaxMcrAcntDtlMfiAddress();
								EquifaxMcrAcntDtlMfiIdentification identification = new EquifaxMcrAcntDtlMfiIdentification();
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
									}
									mcrAdditionalMfiDetail.setMcrAcntDtlMfiAddress(address);
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
									logger.error("There is error while saving data for History24Months"+ec);
								}
								additionalMfiDetails.add(mcrAdditionalMfiDetail);
								eqfaxmcrAccount.setEquifaxMcrAdditionalMFIDetails(additionalMfiDetails);

							}else{
								List<EquifaxMcrAdditionalMFIDetail> additionalMfiDetails=new ArrayList<EquifaxMcrAdditionalMFIDetail>();
								EquifaxMcrAdditionalMFIDetail mcrAdditionalMfiDetail=new EquifaxMcrAdditionalMFIDetail();
								EquifaxMcrAcntDtlMfiAddress address =new EquifaxMcrAcntDtlMfiAddress();
								EquifaxMcrAcntDtlMfiIdentification identification = new EquifaxMcrAcntDtlMfiIdentification();
								mcrAdditionalMfiDetail.setId("NA");
								mcrAdditionalMfiDetail.setMemberid("NA");
								mcrAdditionalMfiDetail.setMficlientfullname("NA");
								mcrAdditionalMfiDetail.setMfidob("NA");
								mcrAdditionalMfiDetail.setMfigender("NA");
								address.setMfiaddressline("NA");
								address.setSeq("NA");
								address.setMfistate("NA");
								address.setMfipostalpin("NA");
								mcrAdditionalMfiDetail.setMcrAcntDtlMfiAddress(address);
								identification.setMfiOtherId("NA");
								mcrAdditionalMfiDetail.setMcrAcntDtlMfiIdentification(identification);
								additionalMfiDetails.add(mcrAdditionalMfiDetail);
								eqfaxmcrAccount.setEquifaxMcrAdditionalMFIDetails(additionalMfiDetails);
							}

							if(account.has("appliedamount")){
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
							}if(account.has("history24months"))
							{
								List<EquifaxMcrHistory24MonthsDetail> mcr24monthDetails=new ArrayList<EquifaxMcrHistory24MonthsDetail>();
								if(account.has("history24months"))
								{
									JSONArray historyDetail=null;
									try
									{
										historyDetail=new JSONArray().put(account.getJSONObject("history24months").getJSONObject("month"));
									}
									catch(Exception ee)
									{
										historyDetail=account.getJSONObject("history24months").getJSONArray("month");
									}
									for(int k=0;k<historyDetail.length();k++)
									{
										JSONObject month24=historyDetail.getJSONObject(k);
										EquifaxMcrHistory24MonthsDetail mcr24monthDetail=new EquifaxMcrHistory24MonthsDetail();
										try
										{
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
										eqfaxmcrAccount.setHistory24MonthsDetails(mcr24monthDetails);
									}
								}
								else
								{
									EquifaxMcrHistory24MonthsDetail mcr24monthDetail=new EquifaxMcrHistory24MonthsDetail();
									mcr24monthDetail.setKey("NA");
									mcr24monthDetail.setPaymentstatus("NA");
									mcr24monthDetail.setAssetClassificationStatus("NA");
									mcr24monthDetail.setSuitFiledStatus("NA");
									mcr24monthDetails.add(mcr24monthDetail);
									eqfaxmcrAccount.setHistory24MonthsDetails(mcr24monthDetails);
								}


								eqfaxmcrAccount.setHistory_24_months(account.get("history24months").toString());
							}else{
								eqfaxmcrAccount.setHistory_24_months("NA");
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
							}if(account.has("keyperson")){
								JSONArray keyPersonDetail=null;

								try{
									keyPersonDetail=new JSONArray().put(account.getJSONObject("keyperson"));
								}catch(Exception ee){
									keyPersonDetail=account.getJSONArray("keyperson");
								}

								JSONObject kpDtl=keyPersonDetail.getJSONObject(0);
								EquifaxMcrKeyPersonDetail kpDetail=new EquifaxMcrKeyPersonDetail();
								try
								{
									kpDetail.setRelationtype(kpDtl.get("relationtype").toString());
									kpDetail.setName(kpDtl.get("name").toString());
								}
								catch(Exception ec)
								{
									logger.error("There is error while saving data for keyPersonDetails"+ec);
								}
								keyPersonDetails.add(kpDetail);
								eqfaxmcrAccount.setEquifaxMcrKeyPersonDetails(keyPersonDetails);
								eqfaxmcrAccount.setKey_person(account.get("keyperson").toString());
							}else{
								EquifaxMcrKeyPersonDetail kpDetail=new EquifaxMcrKeyPersonDetail();
								kpDetail.setRelationtype("NA");
								kpDetail.setName("NA");
								keyPersonDetails.add(kpDetail);
								eqfaxmcrAccount.setKey_person("NA");

								eqfaxmcrAccount.setEquifaxMcrKeyPersonDetails(keyPersonDetails);
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
							}if(account.has("nominee")){
								JSONArray nomineeDetail=null;

								try{
									nomineeDetail=new JSONArray().put(account.getJSONObject("nominee"));
								}catch(Exception ee){
									nomineeDetail=account.getJSONArray("nominee");
								}

								JSONObject nomDtl=nomineeDetail.getJSONObject(0);
								EquifaxMcrNomineeDetail nomDetail=new EquifaxMcrNomineeDetail();
								try
								{
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
								eqfaxmcrAccount.setEquifaxMcrNomineeDetails(nomineeDetails);
								eqfaxmcrAccount.setNominee(account.get("nominee").toString());
							}else{
								EquifaxMcrNomineeDetail nomDetail=new EquifaxMcrNomineeDetail();
								nomDetail.setRelationtype("NA");
								nomDetail.setName("NA");
								nomineeDetails.add(nomDetail);
								eqfaxmcrAccount.setEquifaxMcrNomineeDetails(nomineeDetails);
								eqfaxmcrAccount.setNominee("NA");
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
							equifaxMcrAccounts.add(eqfaxmcrAccount);
						}catch(Exception ec)
						{
							logger.error("There is error while saving mcr account"+ec);
						}

					}
				}
				else
				{
					List<EquifaxMcrHistory24MonthsDetail> mcr24monthDetails=new ArrayList<EquifaxMcrHistory24MonthsDetail>();
					List<EquifaxMcrAdditionalMFIDetail> additionalMfiDetails=new ArrayList<EquifaxMcrAdditionalMFIDetail>();
					EquifaxMcrAccountDetail eqfaxmcrAccount=new EquifaxMcrAccountDetail();
					eqfaxmcrAccount.setAccount_detail_id("NA");
					eqfaxmcrAccount.setAccount_number("NA");
					eqfaxmcrAccount.setAccount_status("NA");
					eqfaxmcrAccount.setAdditional_mfi_details("NA");
					eqfaxmcrAccount.setApplied_amount("NA");
					eqfaxmcrAccount.setBranch_id_mfi("NA");
					eqfaxmcrAccount.setCurrent_balance("NA");
					eqfaxmcrAccount.setDate_closed("NA");
					eqfaxmcrAccount.setDate_opened("NA");
					eqfaxmcrAccount.setDate_reported("NA");
					eqfaxmcrAccount.setDate_sanctioned("NA");
					eqfaxmcrAccount.setDays_past_due("NA");
					eqfaxmcrAccount.setDisbursed_ammount("NA");
					eqfaxmcrAccount.setHistory_24_months("NA");
					eqfaxmcrAccount.setInstallment_amount("NA");
					eqfaxmcrAccount.setInstitution("NA");
					eqfaxmcrAccount.setKendra_aid_mfi("NA");
					eqfaxmcrAccount.setKey_person("NA");
					eqfaxmcrAccount.setLoan_category("NA");
					eqfaxmcrAccount.setLoan_cycle_id("NA");
					eqfaxmcrAccount.setLoan_purpose("NA");
					eqfaxmcrAccount.setNo_of_installments("NA");
					eqfaxmcrAccount.setNominee("NA");
					eqfaxmcrAccount.setRepayment_tenure("NA");
					eqfaxmcrAccount.setReported_date("NA");
					eqfaxmcrAccount.setSanction_amount("NA");
					eqfaxmcrAccount.setSequence("NA");
					EquifaxMcrAdditionalMFIDetail mcrAdditionalMfiDetail=new EquifaxMcrAdditionalMFIDetail();
					EquifaxMcrAcntDtlMfiAddress address =new EquifaxMcrAcntDtlMfiAddress();
					EquifaxMcrAcntDtlMfiIdentification identification = new EquifaxMcrAcntDtlMfiIdentification();
					mcrAdditionalMfiDetail.setId("NA");
					mcrAdditionalMfiDetail.setMemberid("NA");
					mcrAdditionalMfiDetail.setMficlientfullname("NA");
					mcrAdditionalMfiDetail.setMfidob("NA");
					mcrAdditionalMfiDetail.setMfigender("NA");
					address.setMfiaddressline("NA");
					address.setSeq("NA");
					address.setMfistate("NA");
					address.setMfipostalpin("NA");
					mcrAdditionalMfiDetail.setMcrAcntDtlMfiAddress(address);
					identification.setMfiOtherId("NA");
					mcrAdditionalMfiDetail.setMcrAcntDtlMfiIdentification(identification);
					additionalMfiDetails.add(mcrAdditionalMfiDetail);
					additionalAddr.add(address);
					additionalIdentity.add(identification);
					EquifaxMcrNomineeDetail nomDetail=new EquifaxMcrNomineeDetail();
					nomDetail.setRelationtype("NA");
					nomDetail.setName("NA");
					nomineeDetails.add(nomDetail);
					EquifaxMcrKeyPersonDetail kpDetail=new EquifaxMcrKeyPersonDetail();
					kpDetail.setRelationtype("NA");
					kpDetail.setName("NA");
					keyPersonDetails.add(kpDetail);
					eqfaxmcrAccount.setEquifaxMcrKeyPersonDetails(keyPersonDetails);
					eqfaxmcrAccount.setEquifaxMcrNomineeDetails(nomineeDetails);
					eqfaxmcrAccount.setEquifaxMcrAdditionalMFIDetails(additionalMfiDetails);
					EquifaxMcrHistory24MonthsDetail mcr24monthDetail=new EquifaxMcrHistory24MonthsDetail();
					mcr24monthDetail.setKey("NA");
					mcr24monthDetail.setPaymentstatus("NA");
					mcr24monthDetail.setAssetClassificationStatus("NA");
					mcr24monthDetail.setSuitFiledStatus("NA");
					mcr24monthDetails.add(mcr24monthDetail);
					eqfaxmcrAccount.setHistory24MonthsDetails(mcr24monthDetails);
					eqfaxmcrAccount.setClientName("NA");
					eqfaxmcrAccount.setAccount_type("NA");
					eqfaxmcrAccount.setOwner_ship_type("NA");
					eqfaxmcrAccount.setBalance("NA");
					eqfaxmcrAccount.setPast_due_amount("NA");
					eqfaxmcrAccount.setLast_payment("NA");
					eqfaxmcrAccount.setWriteOffAmount("NA");
					eqfaxmcrAccount.setOpen("NA");
					eqfaxmcrAccount.setLastPaymentDate("NA");
					eqfaxmcrAccount.setHighCredit("NA");
					eqfaxmcrAccount.setReason("NA");
					eqfaxmcrAccount.setDateWrittenOff("NA");
					eqfaxmcrAccount.setDateApplied("NA");
					eqfaxmcrAccount.setInterestrate("NA");;
					eqfaxmcrAccount.setCollateral_type("NA");
					eqfaxmcrAccount.setCollateral_value("NA");
					eqfaxmcrAccount.setSuit_filed_status("NA");
					eqfaxmcrAccount.setTerm_frequency("NA");
					eqfaxmcrAccount.setAsset_classification("NA");
					equifaxMcrAccounts.add(eqfaxmcrAccount);

				}
			}
			else
			{
				List<EquifaxMcrHistory24MonthsDetail> mcr24monthDetails=new ArrayList<EquifaxMcrHistory24MonthsDetail>();
				List<EquifaxMcrAdditionalMFIDetail> additionalMfiDetails=new ArrayList<EquifaxMcrAdditionalMFIDetail>();
				EquifaxMcrAccountDetail eqfaxmcrAccount=new EquifaxMcrAccountDetail();
				eqfaxmcrAccount.setAccount_detail_id("NA");
				eqfaxmcrAccount.setAccount_number("NA");
				eqfaxmcrAccount.setAccount_status("NA");
				eqfaxmcrAccount.setAdditional_mfi_details("NA");
				eqfaxmcrAccount.setApplied_amount("NA");
				eqfaxmcrAccount.setBranch_id_mfi("NA");
				eqfaxmcrAccount.setCurrent_balance("NA");
				eqfaxmcrAccount.setDate_closed("NA");
				eqfaxmcrAccount.setDate_opened("NA");
				eqfaxmcrAccount.setDate_reported("NA");
				eqfaxmcrAccount.setDate_sanctioned("NA");
				eqfaxmcrAccount.setDays_past_due("NA");
				eqfaxmcrAccount.setDisbursed_ammount("NA");
				eqfaxmcrAccount.setHistory_24_months("NA");
				eqfaxmcrAccount.setInstallment_amount("NA");
				eqfaxmcrAccount.setInstitution("NA");
				eqfaxmcrAccount.setKendra_aid_mfi("NA");
				eqfaxmcrAccount.setKey_person("NA");
				eqfaxmcrAccount.setLoan_category("NA");
				eqfaxmcrAccount.setLoan_cycle_id("NA");
				eqfaxmcrAccount.setLoan_purpose("NA");
				eqfaxmcrAccount.setNo_of_installments("NA");
				eqfaxmcrAccount.setNominee("NA");
				eqfaxmcrAccount.setRepayment_tenure("NA");
				eqfaxmcrAccount.setReported_date("NA");
				eqfaxmcrAccount.setSanction_amount("NA");
				eqfaxmcrAccount.setSequence("NA");
				EquifaxMcrAdditionalMFIDetail mcrAdditionalMfiDetail=new EquifaxMcrAdditionalMFIDetail();
				EquifaxMcrAcntDtlMfiAddress address =new EquifaxMcrAcntDtlMfiAddress();
				EquifaxMcrAcntDtlMfiIdentification identification = new EquifaxMcrAcntDtlMfiIdentification();
				mcrAdditionalMfiDetail.setId("NA");
				mcrAdditionalMfiDetail.setMemberid("NA");
				mcrAdditionalMfiDetail.setMficlientfullname("NA");
				mcrAdditionalMfiDetail.setMfidob("NA");
				mcrAdditionalMfiDetail.setMfigender("NA");
				address.setMfiaddressline("NA");
				address.setSeq("NA");
				address.setMfistate("NA");
				address.setMfipostalpin("NA");
				mcrAdditionalMfiDetail.setMcrAcntDtlMfiAddress(address);
				identification.setMfiOtherId("NA");
				mcrAdditionalMfiDetail.setMcrAcntDtlMfiIdentification(identification);
				additionalMfiDetails.add(mcrAdditionalMfiDetail);
				additionalAddr.add(address);
				additionalIdentity.add(identification);
				EquifaxMcrNomineeDetail nomDetail=new EquifaxMcrNomineeDetail();
				nomDetail.setRelationtype("NA");
				nomDetail.setName("NA");
				nomineeDetails.add(nomDetail);
				EquifaxMcrKeyPersonDetail kpDetail=new EquifaxMcrKeyPersonDetail();
				kpDetail.setRelationtype("NA");
				kpDetail.setName("NA");
				keyPersonDetails.add(kpDetail);
				eqfaxmcrAccount.setEquifaxMcrKeyPersonDetails(keyPersonDetails);
				eqfaxmcrAccount.setEquifaxMcrNomineeDetails(nomineeDetails);
				eqfaxmcrAccount.setEquifaxMcrAdditionalMFIDetails(additionalMfiDetails);
				EquifaxMcrHistory24MonthsDetail mcr24monthDetail=new EquifaxMcrHistory24MonthsDetail();
				mcr24monthDetail.setKey("NA");
				mcr24monthDetail.setPaymentstatus("NA");
				mcr24monthDetail.setAssetClassificationStatus("NA");
				mcr24monthDetail.setSuitFiledStatus("NA");
				mcr24monthDetails.add(mcr24monthDetail);
				eqfaxmcrAccount.setHistory24MonthsDetails(mcr24monthDetails);
				eqfaxmcrAccount.setClientName("NA");
				eqfaxmcrAccount.setAccount_type("NA");
				eqfaxmcrAccount.setOwner_ship_type("NA");
				eqfaxmcrAccount.setBalance("NA");
				eqfaxmcrAccount.setPast_due_amount("NA");
				eqfaxmcrAccount.setLast_payment("NA");
				eqfaxmcrAccount.setWriteOffAmount("NA");
				eqfaxmcrAccount.setOpen("NA");
				eqfaxmcrAccount.setLastPaymentDate("NA");
				eqfaxmcrAccount.setHighCredit("NA");
				eqfaxmcrAccount.setReason("NA");
				eqfaxmcrAccount.setDateWrittenOff("NA");
				eqfaxmcrAccount.setDateApplied("NA");
				eqfaxmcrAccount.setInterestrate("NA");;
				eqfaxmcrAccount.setCollateral_type("NA");
				eqfaxmcrAccount.setCollateral_value("NA");
				eqfaxmcrAccount.setSuit_filed_status("NA");
				eqfaxmcrAccount.setTerm_frequency("NA");
				eqfaxmcrAccount.setAsset_classification("NA");
				equifaxMcrAccounts.add(eqfaxmcrAccount);

			}
			equifaxMcrDetailLogs.setEquifaxMcrAccountDetails(equifaxMcrAccounts);
			request.setAttribute("account", equifaxMcrDetailLogs.getEquifaxMcrAccountDetails());

			////////////////////////////////////for Account Details////////////////////

			////////////////////////////////////////for Complete Contact Information//////////////////////////////////

			if(responsetransaction.has("idandcontactinfo"))
			{
				JSONObject idandcontactinfo=responsetransaction.getJSONObject("idandcontactinfo");
				/////////////////////for saving data for Personal detail////////////////////////////////////////
				List<EquifaxMcrPersonalDetail> personalDetails=new ArrayList<EquifaxMcrPersonalDetail>();
				if(idandcontactinfo.has("personalinfo"))
				{
					try
					{
						JSONObject personalDetailValue=idandcontactinfo.getJSONObject("personalinfo");

						if(personalDetailValue!=null)
						{
							EquifaxMcrPersonalDetail personalDetail=new EquifaxMcrPersonalDetail();
							try
							{
								if(personalDetailValue.has("maritalstatus"))
								{
									personalDetail.setMaritalstatus(personalDetailValue.get("maritalstatus").toString());
									request.setAttribute("maritalstatus", personalDetail.getMaritalstatus());
								}else{
									request.setAttribute("maritalstatus", "NA");
								}

								if(personalDetailValue.has("age"))
								{
									if(personalDetailValue.getJSONObject("age").has("age")){
										personalDetail.setAge(personalDetailValue.getJSONObject("age").get("age").toString());
										request.setAttribute("age", personalDetail.getAge());
									}else{
										request.setAttribute("age", "NA");
									}
								}
								if(personalDetailValue.has("name"))
								{
									if(personalDetailValue.getJSONObject("name").has("middlename"))
									{
										personalDetail.setMiddle_name(personalDetailValue.getJSONObject("name").get("middlename").toString());
										request.setAttribute("middlename", personalDetail.getMiddle_name());
									}
									else
									{
										request.setAttribute("middlename","NA");
									}

									if(personalDetailValue.getJSONObject("name").has("lastname"))
									{
										personalDetail.setLast_name(personalDetailValue.getJSONObject("name").get("lastname").toString());
										request.setAttribute("lastname", personalDetail.getLast_name());
									}
									else
									{
										request.setAttribute("lastname", "NA");
									}

									if(personalDetailValue.getJSONObject("name").has("firstname"))
									{
										personalDetail.setFirst_name(personalDetailValue.getJSONObject("name").get("firstname").toString());
										request.setAttribute("firstname", personalDetail.getFirst_name());
									}
									else
									{
										request.setAttribute("firstname", "NA");
									}
									if(personalDetailValue.getJSONObject("name").has("additionalmiddlename"))
									{
										personalDetail.setAdditionalmiddlename(personalDetailValue.getJSONObject("name").get("additionalmiddlename").toString());
										request.setAttribute("additionalmiddlename", personalDetail.getAdditionalmiddlename());
									}
									else
									{
										request.setAttribute("additionalmiddlename", "NA");
									}
									if(personalDetailValue.getJSONObject("name").has("suffix"))
									{
										personalDetail.setSuffix(personalDetailValue.getJSONObject("name").get("suffix").toString());
										request.setAttribute("suffix", personalDetail.getSuffix());
									}
									else
									{
										request.setAttribute("suffix", "");
									}
									String fullName="";
									if(personalDetailValue.getJSONObject("name").has("fullname"))
									{
										fullName=personalDetailValue.getJSONObject("name").get("fullname").toString().trim();
									}
									String fName=" ";
									String mName=" ";
									String lName=" ";
									if(personalDetail.getFirst_name()!=null 
											&& !personalDetail.getFirst_name().equals("")
											&& !personalDetail.getFirst_name().equalsIgnoreCase("null")
											&& !personalDetail.getFirst_name().equalsIgnoreCase("NA")
											)
									{
										fName=personalDetail.getFirst_name().trim()+" ";
									}
									if(personalDetail.getMiddle_name()!=null
										&& !personalDetail.getMiddle_name().equals("")
										&& !personalDetail.getMiddle_name().equalsIgnoreCase("null")
										&& !personalDetail.getMiddle_name().equalsIgnoreCase("NA")
										)
									{
										mName=personalDetail.getMiddle_name().trim()+" ";
									}
									if(personalDetail.getLast_name()!=null
											&& !personalDetail.getLast_name().equals("")
											&& !personalDetail.getLast_name().equalsIgnoreCase("null")
											&& !personalDetail.getLast_name().equalsIgnoreCase("NA")
											)
									{
										lName=personalDetail.getLast_name().trim();
									}
									
									if(fullName.isEmpty())
									{
										fullName=fName+mName+lName;
									}
									request.setAttribute("fullName", fullName);
								}
								else
								{
									request.setAttribute("fullName", "NA");
									request.setAttribute("middlename","");
									request.setAttribute("lastname", "");
									request.setAttribute("firstname", "");
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
									request.setAttribute("gender", personalDetail.getGender());
								}else{
									request.setAttribute("gender", "NA");
								}

								if(personalDetailValue.has("dateofbirth")){
									personalDetail.setDate_of_birth(personalDetailValue.get("dateofbirth").toString());
									request.setAttribute("dateofbirth", personalDetail.getDate_of_birth());
								}else{
									request.setAttribute("dateofbirth", "NA");
								}
								if(personalDetailValue.has("aliasnameinfo")){
									if(personalDetailValue.getJSONObject("aliasnameinfo").has("aliasname")){
										personalDetail.setAliasname(personalDetailValue.getJSONObject("aliasnameinfo").get("aliasname").toString());
										request.setAttribute("aliasname", personalDetail.getAliasname());
									}else{
										request.setAttribute("aliasname", "NA");
									}

									if(personalDetailValue.getJSONObject("aliasnameinfo").has("reporteddate")){
										personalDetail.setReporteddate(personalDetailValue.getJSONObject("aliasnameinfo").get("reporteddate").toString());
										request.setAttribute("reporteddate", personalDetail.getReporteddate());
									}else{
										request.setAttribute("reporteddate", "NA");
									}
								}else{
									request.setAttribute("reporteddate", "NA");
									request.setAttribute("aliasname", "NA");
									request.setAttribute("maritalstatus", "NA");
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
					request.setAttribute("dateofbirth", "NA");
					request.setAttribute("fullName", "");
				}
				equifaxMcrDetailLogs.setEquifaxMcrPersonalDetails(personalDetails);
				request.setAttribute("personalDetails", equifaxMcrDetailLogs.getEquifaxMcrPersonalDetails());
				//////////////////////for saving data for Personal detail///////////////////////////////////////// 

				//	/////////////////////for saving data for Address////////////////////////////////////////
				List<EquifaxMcrAddressDetail> mcrAddressDetails=new ArrayList<EquifaxMcrAddressDetail>();
				if(idandcontactinfo.has("addressinfo")){
					try{
						JSONArray addressDetail=null;
						try{
							addressDetail=idandcontactinfo.getJSONArray("addressinfo");
						}catch(Exception ee){
							addressDetail=new JSONArray().put(idandcontactinfo.getJSONObject("addressinfo"));
						}

						for(int i=0;i<addressDetail.length();i++)
						{
							JSONObject address=addressDetail.getJSONObject(i);
							EquifaxMcrAddressDetail mcrAddressDetail=new EquifaxMcrAddressDetail();
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
					mcrAddressDetail.setAddress("NA");
					mcrAddressDetail.setSeq("NA");
					mcrAddressDetail.setState("NA");
					mcrAddressDetail.setType("NA");
					mcrAddressDetail.setReporteddate("NA");
					mcrAddressDetails.add(mcrAddressDetail);
				}
				equifaxMcrDetailLogs.setEquifaxMcrAddressDetails(mcrAddressDetails);
				request.setAttribute("addressDetails", equifaxMcrDetailLogs.getEquifaxMcrAddressDetails());
				//////////////////////for saving data for Address///////////////////////////////////////// 

				///////////////////////for saving data for family detail////////////////////////////////////////
				List<EquifaxMcrFamilyDetail> mcrFamilyDetails=new ArrayList<EquifaxMcrFamilyDetail>();
				if(idandcontactinfo.has("familydetails")){
					try{
						JSONArray familyDetail=null;
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
								logger.error("There is error while saving data for mcrFamilyDetail"+ec);
							}
							mcrFamilyDetails.add(mcrFamilyDetail);
						}
					}catch(Exception ee){
						logger.info("error while parsing mcrFamilyDetails jsonObject "+ee);
					}
				}else{
					EquifaxMcrFamilyDetail mcrFamilyDetail=new EquifaxMcrFamilyDetail();
					mcrFamilyDetail.setNoofdependents("NA");
					mcrFamilyDetail.setAdditionalname("NA");
					mcrFamilyDetail.setAdditionalnametype("NA");
					mcrFamilyDetails.add(mcrFamilyDetail);
				}
				equifaxMcrDetailLogs.setEquifaxMcrFamilyDetails(mcrFamilyDetails);
				request.setAttribute("familydetails", equifaxMcrDetailLogs.getEquifaxMcrFamilyDetails());
				/////////////////////for saving data for family detail/////////////////////////////////////





				List<EquifaxMcrIdentityDetail> idDetails=new ArrayList<EquifaxMcrIdentityDetail>();
				try
				{
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
						identityDetail.setSeq("NA");
						identityDetail.setIdnumber("NA");
						identityDetail.setReporteddate("NA");
						idDetails.add(identityDetail);
					}
				}
				catch(Exception ec)
				{
					logger.error("There is error while saving data for pcsPanDetails "+ec);
				}
				equifaxMcrDetailLogs.setEquifaxMcrIdentityDetails(idDetails);
				request.setAttribute("identityinfo", idDetails);
				/////////////////////for saving data for PAN////////////////////////////////////////


				//	    		///////////////////////for saving data for Identity////////////////////////////////////////
				//	    	    List<EquifaxMcrIdentityDetail> mcrIdentityDetails=new ArrayList<EquifaxMcrIdentityDetail>();
				//	    	    if(idandcontactinfo.has("identityinfo"))
				//	    	    {
				//    	    		try
				//    	    		{
				//    	    		JSONArray identityDetail=null;
				//    	    		try{
				//    	    			identityDetail=idandcontactinfo.getJSONObject("identityinfo").getJSONArray("idother");
				//    	    		}catch(Exception ee){
				//    	    			identityDetail=new JSONArray().put(idandcontactinfo.getJSONObject("identityinfo").getJSONObject("idother"));
				//    	    		}
				//    	    		
				//    	    		for(int i=0;i<identityDetail.length();i++)
				//    	    		{
				//    	    		JSONObject identity=identityDetail.getJSONObject(i);
				//    	    		EquifaxMcrIdentityDetail mcrIdentityDetail=new EquifaxMcrIdentityDetail();
				//    	    		try
				//    	    		{
				//    	    			if(identity.has("seq")){
				//    	    				mcrIdentityDetail.setSeq(identity.get("seq").toString());
				//    	    			}else{
				//    	    				mcrIdentityDetail.setSeq("NA");
				//    	    			}
				//    	    			if(identity.has("idnumber")){
				//    	    				mcrIdentityDetail.setIdnumber(identity.get("idnumber").toString());
				//    	    			}else{
				//    	    				mcrIdentityDetail.setIdnumber("NA");
				//    	    			}
				//    	    			if(identity.has("reporteddate")){
				//    	    				mcrIdentityDetail.setReporteddate(identity.get("reporteddate").toString());
				//    	    			}else{
				//    	    				mcrIdentityDetail.setReporteddate("NA");
				//    	    			}
				//    	    			
				//    	    		}
				//    	    		catch(Exception ec)
				//    	    		{
				//    	    		logger.error("There is error while saving data for pcsAddressDetails"+ec);
				//    	    		}
				//    	    		mcrIdentityDetails.add(mcrIdentityDetail);
				//    	    		}
				//    	    	
				//    	    		}catch(Exception ee){
				//    	    		logger.info("error while parsing address jsonObject "+ee);
				//    	    		
				//    	    		}
				//    	    	}else{
				//    	    		EquifaxMcrIdentityDetail mcrIdentityDetail=new EquifaxMcrIdentityDetail();
				//    	    			mcrIdentityDetail.setSeq("NA");
				//    	    			mcrIdentityDetail.setIdnumber("NA");
				//    	    			mcrIdentityDetail.setReporteddate("NA");
				//    	    			mcrIdentityDetails.add(mcrIdentityDetail);
				//    	    	}
				//	    		equifaxMcrDetailLogs.setEquifaxMcrIdentityDetails(mcrIdentityDetails);
				//	    		request.setAttribute("identityinfo", equifaxMcrDetailLogs.getEquifaxMcrIdentityDetails());
				//	    		//////////////////////for saving data for Identity///////////////////////////////////////// 




				///////////////////////for saving data for Phone////////////////////////////////////////
				List<EquifaxMcrPhoneDetails> mcrPhoneDetails=new ArrayList<EquifaxMcrPhoneDetails>();
				try{
					if(idandcontactinfo.has("phoneinfo")){
						JSONArray phoneDetail=null;
						try{
							phoneDetail=idandcontactinfo.getJSONArray("phoneinfo");
						}catch(Exception ee){
							phoneDetail=new JSONArray().put(idandcontactinfo.getJSONObject("phoneinfo"));
						}
						for(int i=0;i<phoneDetail.length();i++)
						{
							JSONObject phone=phoneDetail.getJSONObject(i);
							EquifaxMcrPhoneDetails mcrPhoneDetail=new EquifaxMcrPhoneDetails();
							try
							{
								if(phone.has("typecode")){
									mcrPhoneDetail.setPhoneTypecode(phone.get("typecode").toString());
								}
								if(phone.has("seq")){
									mcrPhoneDetail.setPhoneSeq(phone.get("seq").toString());
								}
								if(phone.has("number")){
									mcrPhoneDetail.setPhoneNumber(phone.get("number").toString());
								}
								if(phone.has("reporteddate")){
									mcrPhoneDetail.setPhoneReportedDate(phone.get("reporteddate").toString());
								}else{

								}

							}
							catch(Exception ec)
							{
								logger.error("There is error while saving data for pcsPhoneDetail"+ec);
							}

							mcrPhoneDetails.add(mcrPhoneDetail);
						}
						equifaxMcrDetailLogs.setEquifaxMcrPhoneDetails(mcrPhoneDetails);
						request.setAttribute("phoneDetails", equifaxMcrDetailLogs.getEquifaxMcrPhoneDetails());
					}else{

						EquifaxMcrPhoneDetails mcrPhoneDetail=new EquifaxMcrPhoneDetails();
						mcrPhoneDetail.setPhoneTypecode("NA");
						mcrPhoneDetail.setPhoneSeq("NA");
						mcrPhoneDetail.setPhoneNumber("NA");
						mcrPhoneDetail.setPhoneReportedDate("NA");
						mcrPhoneDetails.add(mcrPhoneDetail);
						equifaxMcrDetailLogs.setEquifaxMcrPhoneDetails(mcrPhoneDetails);
						request.setAttribute("phoneDetails", equifaxMcrDetailLogs.getEquifaxMcrPhoneDetails());
					}
				}catch(Exception ec)
				{
					logger.error("There is error while saving data for mcrPhoneDetail"+ec);
				}

				/////////////////////for saving data for Phone////////////////////////////////////////
			}
			///////////////////////////////////////for Complete Contact Information //////////////////////////////////


			////////////////////////////////////For Desclaimer///////////////////////////
			if(responsetransaction.has("disclaimer")){
				String disclaimer=responsetransaction.getString("disclaimer");
				request.setAttribute("disclaimer", disclaimer);
			}else{
				request.setAttribute("disclaimer", "NA");
			}

			///////////////////////////////////For Desclaimer/////////////////////////
			/*	if(responsetransaction.has("score")){
    			request.setAttribute("",responsetransaction.get("score"));
    		}*/

			request.setAttribute("responsetransaction",responsetransaction);



			////////////////////////////////////////////inquiryrequestinfo ////////////////////////////////////////////
			if(responsetransaction.has("inquiryrequestinfo"))
			{
				JSONObject inquiryrequestinfo= responsetransaction.getJSONObject("inquiryrequestinfo");
				request.setAttribute("inquiryrequestinfo", inquiryrequestinfo);
			}
			else
			{
				request.setAttribute("inquiryrequestinfo", null);
			}

			EquifaxPcsInquiryRequestInfo inquiryrequestinfoBean=new EquifaxPcsInquiryRequestInfo();
			try
			{
				if(responsetransaction.has("inquiryrequestinfo"))
				{
					JSONObject inquiryrequestinfo=responsetransaction.getJSONObject("inquiryrequestinfo");

					if(inquiryrequestinfo.has("inquirypurpose"))
					{
						try 
						{
							inquiryrequestinfoBean.setRequestPurpose(EquifaxUtill.getEquifaxAppendixA().get(inquiryrequestinfo.get("inquirypurpose").toString()));
							inquiryrequestinfoBean.setInquirypurpose(inquiryrequestinfoBean.getRequestPurpose());
						} 
						catch (Exception e) 
						{
							logger.error("Developer : Check this 100% chance is wrong input");
						}
					}
					else
					{
						inquiryrequestinfoBean.setRequestPurpose("NA");
						inquiryrequestinfoBean.setInquirypurpose("NA");
					}
					if(inquiryrequestinfo.has("firstname")){
						inquiryrequestinfoBean.setfName(inquiryrequestinfo.get("firstname").toString());
						String name = ""+request.getAttribute("fullName");
						if(name.equals("") || name .equals("NA"))
						{
							request.setAttribute("fullName", inquiryrequestinfoBean.getfName());
						}
					}else{
						inquiryrequestinfoBean.setfName("NA");
					}
					if(inquiryrequestinfo.has("lastname")){
						inquiryrequestinfoBean.setFamilyName(inquiryrequestinfo.get("lastname").toString());
					}else{
						inquiryrequestinfoBean.setFamilyName("NA");
					}
					if(inquiryrequestinfo.has("dob")){
						inquiryrequestinfoBean.setDob(inquiryrequestinfo.get("dob").toString());
					}else{
						inquiryrequestinfoBean.setDob("NA");
					}
					if(inquiryrequestinfo.has("gender")){
						inquiryrequestinfoBean.setGender(inquiryrequestinfo.get("gender").toString());
					}else{
						inquiryrequestinfoBean.setGender("NA");
					}
					if(inquiryrequestinfo.has("transactionamount")){
						inquiryrequestinfoBean.setTransactionAmount(inquiryrequestinfo.get("transactionamount").toString());
					}else{
						inquiryrequestinfoBean.setTransactionAmount("0");
					}



					if(inquiryrequestinfo.has("panid")){
						inquiryrequestinfoBean.setPan(inquiryrequestinfo.get("panid").toString());
					}else{
						inquiryrequestinfoBean.setPan("NA");
					}
					if(inquiryrequestinfo.has("voterid")){
						inquiryrequestinfoBean.setVoterID(inquiryrequestinfo.get("voterid").toString());
					}else{
						inquiryrequestinfoBean.setVoterID("NA");
					}
					if(inquiryrequestinfo.has("passportid")){
						inquiryrequestinfoBean.setPassportId(inquiryrequestinfo.get("passportid").toString());
					}else{
						inquiryrequestinfoBean.setPassportId("NA");
					}
					if(inquiryrequestinfo.has("nationalidcard")){
						inquiryrequestinfoBean.setUid(inquiryrequestinfo.get("nationalidcard").toString());
					}else{
						inquiryrequestinfoBean.setUid("NA");
					}
					if(inquiryrequestinfo.has("driverlicense")){
						inquiryrequestinfoBean.setDriverLicense(inquiryrequestinfo.get("driverlicense").toString());
					}else{
						inquiryrequestinfoBean.setDriverLicense("NA");
					}


					if(inquiryrequestinfo.has("inquiryphones"))
					{
						JSONObject inquiryphones=inquiryrequestinfo.getJSONObject("inquiryphones");
						JSONArray inquiryphone=null;
						try
						{
							inquiryphone=inquiryphones.getJSONArray("inquiryphone");
						}
						catch(Exception ee){
							inquiryphone=new JSONArray().put(inquiryphones.getJSONObject("inquiryphone"));
						}
						if(inquiryphone!=null && inquiryphone.length()>0)
						{

							for(int i=0;i<inquiryphone.length();i++)
							{
								JSONObject phone = inquiryphone.getJSONObject(i);
								String typecode = ""+phone.getString("phonetype");
								if(typecode.equalsIgnoreCase("H") || typecode.equalsIgnoreCase("Home"))
								{
									inquiryrequestinfoBean.sethPhone(phone.get("number").toString());
								}
								else if(typecode.equalsIgnoreCase("M") || typecode.equalsIgnoreCase("Mobile"))
								{
									inquiryrequestinfoBean.setmPhone(phone.get("number").toString());
								}
								else if(!typecode.equalsIgnoreCase(""))
								{
									inquiryrequestinfoBean.setoPhone(phone.get("number").toString());
								}
							}
						}
					}
					List<EquifaxPcsAddressDetails> address = new ArrayList<EquifaxPcsAddressDetails>();
					if(inquiryrequestinfo.has("inquiryaddresses"))
					{
						JSONObject inquiryaddresses=inquiryrequestinfo.getJSONObject("inquiryaddresses");
						JSONArray inquiryaddress=null;
						try
						{
							inquiryaddress=inquiryaddresses.getJSONArray("inquiryaddress");
						}
						catch(Exception ee){
							inquiryaddress=new JSONArray().put(inquiryaddresses.getJSONObject("inquiryaddress"));
						}
						if(inquiryaddress!=null && inquiryaddress.length()>0)
						{

							for(int i=0;i<inquiryaddress.length();i++)
							{
								EquifaxPcsAddressDetails addressDetails = new EquifaxPcsAddressDetails();
								JSONObject phone = inquiryaddress.getJSONObject(i);
								addressDetails.setState(phone.get("state").toString());
								addressDetails.setAddress(phone.get("addressline").toString());
								addressDetails.setPostal(phone.get("postal").toString());
								address.add(addressDetails);
							}
						}
					}
					inquiryrequestinfoBean.setAddress(address);
				}
				else
				{
					inquiryrequestinfoBean.setDob("NA");
					inquiryrequestinfoBean.setDriverLicense("NA");
					inquiryrequestinfoBean.setFamilyName("NA");
					inquiryrequestinfoBean.setfName("NA");
					inquiryrequestinfoBean.setGender("NA");
					inquiryrequestinfoBean.sethPhone("NA");
					inquiryrequestinfoBean.setInquirypurpose("NA");
					inquiryrequestinfoBean.setmPhone("NA");
					inquiryrequestinfoBean.setPan("NA");
					inquiryrequestinfoBean.setPassportId("NA");
					inquiryrequestinfoBean.setRequestPurpose("NA");
					inquiryrequestinfoBean.setUid("NA");
					inquiryrequestinfoBean.setVoterID("NA");
					inquiryrequestinfoBean.setTransactionAmount("0");
					inquiryrequestinfoBean.setInquiryAccount1("NA");
					inquiryrequestinfoBean.setInquiryAccount2("NA");
					inquiryrequestinfoBean.setInquiryAccount3("NA");
					inquiryrequestinfoBean.setInquiryAccount4("NA");
				}
			}
			catch(Exception ec)
			{
				logger.error(" There is error while saving data for recent activity "+ec);
			}
			//equifaxPCSDetailslogs.setRecentActivities(inquiryrequestinfoBean);
			request.setAttribute("inquiryrequestinfoBean", inquiryrequestinfoBean);
			////////////////////////////////////////////inquiryrequestinfo ////////////////////////////////////////////



		}
		catch(Exception ec)
		{
			ec.printStackTrace();
		}
	}

}
