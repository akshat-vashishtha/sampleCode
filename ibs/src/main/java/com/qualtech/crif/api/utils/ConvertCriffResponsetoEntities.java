package com.qualtech.crif.api.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qualtech.crif.api.db.dao.CriffDetailsDBDao;
import com.qualtech.crif.api.dto.CrifTrackerDTO;
import com.qualtech.crif.api.entity.CriffDetailLogs;
import com.qualtech.crif.api.entity.CriffDrivedAttribute;
import com.qualtech.crif.api.entity.CriffLoanDetails;
import com.qualtech.crif.api.entity.CriffScore;
import com.qualtech.crif.api.entity.CriffScoreStatus;
import com.qualtech.crif.api.entity.GrpResponseSummary;
import com.qualtech.crif.api.entity.IndvGrpDetails;
import com.qualtech.crif.api.entity.IndvIds;
import com.qualtech.crif.api.entity.IndvLoanDetails;
import com.qualtech.crif.api.entity.IndvPrimarySummary;
import com.qualtech.crif.api.entity.IndvRelation;
import com.qualtech.crif.api.entity.IndvResponseAddress;
import com.qualtech.crif.api.entity.IndvResponseDetails;
import com.qualtech.crif.api.entity.IndvResponseNormalSummary;
import com.qualtech.crif.api.entity.IndvSecondarySummary;
import com.qualtech.crif.api.entity.PrimaryAccountSummary;
import com.qualtech.crif.api.entity.SecondaryAccountSummary;
import com.qualtech.crif.api.request.CriffApiRequest;
import com.qualtech.crif.api.response.CriffApiResponse;

public class ConvertCriffResponsetoEntities
{
	private static Logger logger = LogManager.getLogger(ConvertCriffResponsetoEntities.class);
	public CriffDetailLogs processCriffResponse(CriffApiRequest criffApiRequest,CriffApiResponse criffApiResponse,CrifTrackerDTO crifftrackerdto,CriffDetailsDBDao criffDetailDBService)
	{
		String trackerId=criffApiRequest.getHeader().getCorrelationId();
		CriffDetailLogs criffDetailLogs=new CriffDetailLogs();
		criffDetailLogs.setTracker_id(trackerId);
		criffDetailLogs.setCreated_time(new Date());
		criffDetailLogs.setCrif_api_request_xml(crifftrackerdto.getCrifApiReq());
		criffDetailLogs.setCrif_api_response_xml(crifftrackerdto.getCrifApiRes());
		criffDetailLogs.setCrif_final_api_request_xml(crifftrackerdto.getCrifApiIssueXmlReq());
		criffDetailLogs.setCrif_final_api_response_xml(crifftrackerdto.getCrifApiIssueXmlRes());
		criffDetailLogs.setUpdated_time(new Date());
		criffDetailLogs.setCrif_html_response(crifftrackerdto.getHtmlResponse());
		criffDetailLogs.setCriff_byte_arr(crifftrackerdto.getPdfByteArray());
		criffDetailLogs.setRemarks("success");
		criffDetailLogs.setRequest_info_json(criffApiRequest.toString());
		criffDetailLogs.setResponse_error_info_json(criffApiResponse.getErrorInfo().toString());
		String requiredResponse=crifftrackerdto.getApiResponse();
		JSONObject requiredtransaction=null;
		try 
		{
			requiredtransaction = new JSONObject(requiredResponse).getJSONObject("payload").getJSONObject("transaction");
		} catch (Exception e) {
			logger.error("Exception in ConvertCriffResponsetoEntities || processCriffResponse() || accessing JSON -> transaction "+e);
		}
		String personal_info_variation_json="";
		try
		{
			personal_info_variation_json=requiredtransaction.getJSONObject("personalinfovariation").toString();
		}
		catch(Exception ec)
		{
			logger.error("Exception in ConvertCriffResponsetoEntities || processCriffResponse() || accessing JSON -> personalinfovariation "+ec);
		}
		criffDetailLogs.setPersonal_info_variation_json(personal_info_variation_json);
		
		try
		{
		criffDetailLogs.setTracker_id(criffApiRequest.getHeader().getCorrelationId());
		criffDetailLogs.setResponse_header_info_json(criffApiResponse.getHeader().toString());
		}
		catch(Exception ec)
		{
			logger.error("Exception in ConvertCriffResponsetoEntities || processCriffResponse() || criffDetailLogs saving header data "+ec);
		}

		///////////////////////////////for saving criff score status////////////////////////////
		Set<CriffScoreStatus> scorestatuses=new HashSet<CriffScoreStatus>();
		try
		{
		JSONArray statuses=requiredtransaction.getJSONObject("statusdetails").getJSONArray("status");
		for(int i=0;i<statuses.length();i++)
		{
			CriffScoreStatus scorestatus=new CriffScoreStatus();
			JSONObject status=statuses.getJSONObject(i);
			scorestatus.setCrifdetaillogs(criffDetailLogs);
			scorestatus.setErrors(status.get("errors").toString());
			scorestatus.setOptions(status.get("option").toString());
			scorestatus.setOption_satus(status.get("optionstatus").toString());
			scorestatuses.add(scorestatus);
		}
		}
		catch(Exception ec)
		{
			logger.error("Exception in ConvertCriffResponsetoEntities || processCriffResponse() || accessing JSON -> status Array "+ec);
		}
		 criffDetailLogs.setCriffScoreStatus(scorestatuses);
		//////////////////////////////for saving criff score status////////////////////////////
	
		
		//////////////////////////////for saving Criff Score//////////////////////////////////
		try
		{
		CriffScore criffscore=new CriffScore();
	    JSONObject criffscorejson=requiredtransaction.getJSONObject("scores").getJSONObject("score");
		criffscore.setCrifdetaillogs(criffDetailLogs);
		criffscore.setScore_comments(criffscorejson.get("scorecomments").toString());
		criffscore.setScore_type(criffscorejson.get("scoretype").toString());
		criffscore.setScore_value(criffscorejson.get("scorevalue").toString());
		criffDetailLogs.setCriffscore(criffscore);
		}
		catch(Exception ec)
		{
			logger.error(ec);
		}
		//////////////////////////////for saving Criff Score//////////////////////////////////
		
		/////////////////////////////for saving GRP Normal Summary//////////////////////////////
	    GrpResponseSummary grpresponsenormalSummary=new GrpResponseSummary();		
		try
		{
		grpresponsenormalSummary.setCrifdetaillogs(criffDetailLogs);
		JSONObject normalSummary=requiredtransaction.getJSONObject("grpresponses").getJSONObject("summary");
		grpresponsenormalSummary.setNumber_of_active_accounts(normalSummary.get("noofactiveaccounts").toString());
		grpresponsenormalSummary.setNumber_of_closed_accounts(normalSummary.get("noofclosedaccounts").toString());
		grpresponsenormalSummary.setNumber_of_default_account(normalSummary.get("noofdefaultaccounts").toString());
		grpresponsenormalSummary.setNumber_of_other_mfis(normalSummary.get("noofothermfis").toString());
		grpresponsenormalSummary.setNumber_of_own_mfis(normalSummary.get("noofownmfis").toString());
		grpresponsenormalSummary.setStatus(normalSummary.get("status").toString());
		grpresponsenormalSummary.setTotal_responses(normalSummary.get("totalresponses").toString());
		}
		catch(Exception ec)
		{
			logger.error(ec);
		}
		/////////////////////////////for saving GRP  Normal Summary//////////////////////////////
		
		 criffDetailLogs.setGrpResponseNormalSummary(grpresponsenormalSummary);
		
		
		////////////////////////////for saving IDV Normal Summary///////////////////////////////
		
				IndvResponseNormalSummary idvResponsesummary=new IndvResponseNormalSummary();
		try
		{
        JSONObject idvsummary=requiredtransaction.getJSONObject("indvresponses").getJSONObject("summary");
		idvResponsesummary.setCrifdetaillogs(criffDetailLogs);
		idvResponsesummary.setNumber_of_active_accounts(idvsummary.get("noofactiveaccounts").toString());
		idvResponsesummary.setNumber_of_closed_accounts(idvsummary.get("noofclosedaccounts").toString());
		idvResponsesummary.setNumber_of_default_account(idvsummary.get("noofdefaultaccounts").toString());
		idvResponsesummary.setNumber_of_other_mfis(idvsummary.get("noofothermfis").toString());
		idvResponsesummary.setNumber_of_own_mfis(idvsummary.get("noofownmfis").toString());
		
		idvResponsesummary.setStatus(idvsummary.get("status").toString());
		}
		catch(Exception ec)
		{
			logger.error("There is error while getting indvresponses"+ec);
		}
		// There is error while saving data in criff
		criffDetailLogs.setIndvNormalSummary(idvResponsesummary);
		
		
       ////////////////////////////for saving IDV Normal Summary///////////////////////////////
		
		////////////////////////////for saving IDV Primary Summary///////////////////////////////
				
		 	IndvPrimarySummary indvPrimarySummary=new IndvPrimarySummary();
		try
		{
			indvPrimarySummary.setCrifdetaillogs(criffDetailLogs);
		JSONObject prisummary=requiredtransaction.getJSONObject("indvresponses").getJSONObject("primarysummary");
		indvPrimarySummary.setTotal_other_disbursed_amount(prisummary.get("totalotherdisbursedamount").toString());
		indvPrimarySummary.setTotal_other_installment_amount(prisummary.get("totalotherinstallmentamount").toString());
		indvPrimarySummary.setNo_of_other_mfis(prisummary.get("noofothermfis").toString());
		indvPrimarySummary.setNo_of_closed_accounts(prisummary.get("noofclosedaccounts").toString());
		indvPrimarySummary.setTotal_responses(prisummary.get("totalresponses").toString());
		indvPrimarySummary.setNo_of_default_accounts(prisummary.get("noofdefaultaccounts").toString());
		indvPrimarySummary.setTotal_other_current_balance(prisummary.get("totalothercurrentbalance").toString());
		indvPrimarySummary.setMax_worst_delequency(prisummary.get("maxworstdelequency").toString());
		indvPrimarySummary.setNo_of_own_mfis(prisummary.get("noofownmfis").toString());
		indvPrimarySummary.setNo_of_active_accounts(prisummary.get("noofactiveaccounts").toString());
		}
		catch(Exception ec)
		{
		logger.error("There is error while getting indvresponses"+ec);
		}
		// There is error while saving data in criff
		criffDetailLogs.setIndvPrimarySummary(indvPrimarySummary);
		
		
		////////////////////////////for saving IDV primary Summary///////////////////////////////
		
		////////////////////////////for saving IDV Secondry Summary///////////////////////////////
		
				IndvSecondarySummary indvSecondarySummary=new IndvSecondarySummary();
		try
		{
			indvSecondarySummary.setCrifdetaillogs(criffDetailLogs);
		JSONObject prisummary=requiredtransaction.getJSONObject("indvresponses").getJSONObject("secondarysummary");
		indvSecondarySummary.setNo_of_other_mfis(prisummary.get("noofothermfis").toString());
		indvSecondarySummary.setNo_of_closed_accounts(prisummary.get("noofclosedaccounts").toString());
		indvSecondarySummary.setTotal_responses(prisummary.get("totalresponses").toString());
		indvSecondarySummary.setNo_of_default_accounts(prisummary.get("noofdefaultaccounts").toString());
		indvSecondarySummary.setNo_of_own_mfis(prisummary.get("noofownmfis").toString());
		indvSecondarySummary.setNo_of_active_accounts(prisummary.get("noofactiveaccounts").toString());
		
		}
		catch(Exception ec)
		{
		logger.error("There is error while getting indvresponses"+ec);
		}
		//There is error while saving data in criff
		criffDetailLogs.setIndvSecondarySummary(indvSecondarySummary);
		
		
		////////////////////////////for saving IDV Secondry Summary///////////////////////////////
		
		
	  ////////////////////////////////////for saving Loan Details////////////////////////////
		    Set<CriffLoanDetails> criffLoandetailses=new HashSet<CriffLoanDetails>();
		
	    try
	    {
	    JSONArray loanDetails=requiredtransaction.getJSONObject("responses").getJSONArray("response");
		for(int i=0;i<loanDetails.length();i++)
		{
			CriffLoanDetails crifloandetail=new CriffLoanDetails();
			try
			{
			crifloandetail.setCrifdetaillogs(criffDetailLogs);
			JSONObject loandetail=loanDetails.getJSONObject(i).getJSONObject("loandetails");
			crifloandetail.setAccount_number(loandetail.get("acctnumber").toString());
			crifloandetail.setAccount_status(loandetail.get("accountstatus").toString());
			crifloandetail.setAcct_type(loandetail.get("accttype").toString());
			crifloandetail.setCombined_payment_history(loandetail.get("combinedpaymenthistory").toString());
			crifloandetail.setCredit_guaranator(loandetail.get("creditguarantor").toString());
			crifloandetail.setCurrent_balance(loandetail.get("currentbal").toString());
			crifloandetail.setDate_reported(loandetail.get("datereported").toString());
			crifloandetail.setDispurse_amount(loandetail.get("disbursedamt").toString());
			crifloandetail.setDispursed_date(loandetail.get("disburseddate").toString());
			crifloandetail.setInterest_rate(loandetail.get("interestrate").toString());
			crifloandetail.setLinked_accounts(loandetail.get("linkedaccounts").toString());
			crifloandetail.setMatched_type(loandetail.get("matchedtype").toString());
			crifloandetail.setOver_due_amount(loandetail.get("overdueamt").toString());
			crifloandetail.setOwner_ship_ind(loandetail.get("ownershipind").toString());
			crifloandetail.setSecurity_details(loandetail.get("securitydetails").toString());
			crifloandetail.setWrite_off_amount(loandetail.get("writeoffamt").toString());
			}
			catch(Exception ec)
			{
				logger.error(ec);
			}
			criffLoandetailses.add(crifloandetail);
		}
	    }
	    catch(Exception ec)
	    {
	    	logger.error("There is error while getting loan details from JSON"+ec);
	    }
		criffDetailLogs.setCriffLoanDetails(criffLoandetailses);
		
	    ////////////////////////////////////for saving Loan Details////////////////////////////
		
		//////////////////////////////////for saving derived attributes/////////////////////
			CriffDrivedAttribute criffDrivedAttribute=new CriffDrivedAttribute();
			criffDrivedAttribute.setCrifdetaillogs(criffDetailLogs);
		try
		{
		JSONObject derivedattributes=requiredtransaction.getJSONObject("accountssummary").getJSONObject("derivedattributes");
		criffDrivedAttribute.setAverage_account_age_month(derivedattributes.get("averageaccountagemonth").toString());
		criffDrivedAttribute.setAverage_account_age_year(derivedattributes.get("averageaccountageyear").toString());
		
		criffDrivedAttribute.setDel_in_last_six_months(derivedattributes.get("newdelinqaccountinlastsixmonths").toString());
	
		criffDrivedAttribute.setInquiries_in_last_six_months(derivedattributes.get("inquiriesinlastsixmonths").toString());
		criffDrivedAttribute.setLength_of_credit_history_month(derivedattributes.get("lengthofcredithistorymonth").toString());
		criffDrivedAttribute.setLength_of_credit_history_year(derivedattributes.get("lengthofcredithistoryyear").toString());
		criffDrivedAttribute.setNew_accounts_in_last_six_months(derivedattributes.get("newaccountsinlastsixmonths").toString());
		}
		catch(Exception ec)
		{
			logger.error("There is error while getting  derived attribute "+ec);
		}
		criffDetailLogs.setCriffDerivedAttributes(criffDrivedAttribute);
		///////////////////////////////////for saving derived attributes////////////////////

		////////////////////////////////////for saving primary account summary////////////////////////////////////////////////
				
		
		
		PrimaryAccountSummary primaryAccount=new PrimaryAccountSummary();
		
		try
		{
		JSONObject primaryAccountSummary=requiredtransaction.getJSONObject("accountssummary").getJSONObject("primaryaccountssummary");
		
		primaryAccount.setActive_accounts(primaryAccountSummary.get("primaryactivenumberofaccounts").toString());
		primaryAccount.setCrifdetaillogs(criffDetailLogs);
		primaryAccount.setCurrent_balance(primaryAccountSummary.get("primarycurrentbalance").toString());
		primaryAccount.setDisbursed_amount(primaryAccountSummary.get("primarydisbursedamount").toString());
		primaryAccount.setNumber_of_accounts(primaryAccountSummary.get("primarynumberofaccounts").toString());
		primaryAccount.setOverdue_accounts(primaryAccountSummary.get("primaryoverduenumberofaccounts").toString());
		primaryAccount.setSanctioned_amount(primaryAccountSummary.get("primarysanctionedamount").toString());
		primaryAccount.setSecured_accounts(primaryAccountSummary.get("primarysecurednumberofaccounts").toString());
		primaryAccount.setUnsecured_accounts(primaryAccountSummary.get("primaryunsecurednumberofaccounts").toString());
		primaryAccount.setUntagged_accounts(primaryAccountSummary.get("primaryuntaggednumberofaccounts").toString());
		}
		catch(Exception ec)
		{
			logger.error("Therte is error while getting primary account summary"+ec);
		}
		criffDetailLogs.setPrimaryaccountsummary(primaryAccount);
		////////////////////////////////////for saving primary account summary////////////////////////////////////////////////
		
		////////////////////////////////////for saving secondary account summary////////////////////////////////////////////////
		
		SecondaryAccountSummary secondaryAccount=new SecondaryAccountSummary();
		
		try
		{
		JSONObject secondaryAccountSummary=requiredtransaction.getJSONObject("accountssummary").getJSONObject("secondaryaccountssummary");
		
		secondaryAccount.setActive_accounts(secondaryAccountSummary.get("secondaryactivenumberofaccounts").toString());
		secondaryAccount.setCrifdetaillogs(criffDetailLogs);
		secondaryAccount.setCurrent_balance(secondaryAccountSummary.get("secondarycurrentbalance").toString());
		secondaryAccount.setDisbursed_amount(secondaryAccountSummary.get("secondarydisbursedamount").toString());
		secondaryAccount.setNumber_of_accounts(secondaryAccountSummary.get("secondarynumberofaccounts").toString());
		secondaryAccount.setOverdue_accounts(secondaryAccountSummary.get("secondaryoverduenumberofaccounts").toString());
		
		secondaryAccount.setSanctioned_amount(secondaryAccountSummary.get("secondarysanctionedamount").toString());
		secondaryAccount.setSecured_accounts(secondaryAccountSummary.get("secondarysecurednumberofaccounts").toString());
		secondaryAccount.setUnsecured_accounts(secondaryAccountSummary.get("secondaryunsecurednumberofaccounts").toString());
		secondaryAccount.setUntagged_accounts(secondaryAccountSummary.get("secondaryuntaggednumberofaccounts").toString());
		}
		catch(Exception ec)
		{
			logger.error("Therte is error while getting secondary account summary"+ec);
		}
		criffDetailLogs.setSecondaryAccountSummary(secondaryAccount);
		////////////////////////////////////for saving secondary account summary////////////////////////////////////////////////
		

		
		
		////////////////////////////////////for saving indv response Details//////////////////////////////////////////////////
		
		Set<IndvResponseDetails> indvresponsedetailsset=new HashSet<IndvResponseDetails>();
		
		try
		 {

			   JSONArray idvsummary=requiredtransaction.getJSONObject("indvresponses").getJSONObject("indvresponselist").getJSONArray("indvresponse");
			   
			   for(int i=0;i<idvsummary.length();i++)
			   {
				   
				   JSONObject idvresponseDetails=idvsummary.getJSONObject(i);
				   IndvResponseDetails  indvresponsedetails =new IndvResponseDetails();
				   IndvResponseAddress    indvresponseaddress=new IndvResponseAddress(); 
				   Set<IndvIds>  indvids=new HashSet<IndvIds>(); 
				   Set<IndvRelation> indvrelations=new HashSet<IndvRelation>(); 
				   
				   IndvLoanDetails indvloandetails=new IndvLoanDetails(); 
				   IndvGrpDetails indvgrpdetails=new IndvGrpDetails(); 
				   try
				   {
					   indvresponsedetails.setCrifdetaillogs(criffDetailLogs);
					   indvresponseaddress.setIndvresponsedetails(indvresponsedetails);
					   
					   
					   try
					   {
					   String indv_mfiid=idvresponseDetails.get("mfiid").toString();
					   
					   indvresponsedetails.setIndv_mfiid(indv_mfiid);
					   indvresponsedetails.setIndv_dob(idvresponseDetails.get("dob").toString());
					   
					   }
					   catch(Exception ec)
					   {
						 logger.error("There is error while getting mffid");
					   }
							   
					   try
					   {
					   String kendra=idvresponseDetails.get("kendra").toString();
					   indvresponsedetails.setIndv_kendra(kendra);
					   indvresponsedetails.setIndv_cnsmbrid(idvresponseDetails.get("cnsmrmbrid").toString());
					   
					   }
					   catch(Exception ec)
					   {
						 logger.error("Exception ec getting kendra is"+ec);
					   }
					   
					   try
					   {
					   String branch=idvresponseDetails.get("branch").toString();
					   indvresponsedetails.setIndv_branch(branch);
					   
					   
					   indvresponsedetails.setIndv_name(idvresponseDetails.get("name").toString());
					   
					   
					   }
					   catch(Exception ec)
					   {
						   logger.error("There is error at ivd branch "+ec);
					   }
					   
					   try
					   {
					   indvresponsedetails.setIndv_inserted_date(idvresponseDetails.get("insertdate").toString());
					   }
					   catch(Exception ec)
					   {
						logger.error("Exception in indv inserted date"+ec);
					   }
					   
					   try
					   {
					   indvresponsedetails.setIndv_matched_type(idvresponseDetails.get("matchedtype").toString());
					   }
					   catch(Exception ec)
					   {
						   logger.error("There is error in matched type "+ec);
					   }
					   
					   try
					   {
						   indvresponsedetails.setIndv_mfi(idvresponseDetails.get("mfi").toString());
						      
					   }
					   catch(Exception ec)
					   {
						 logger.error("There is exception in mfi"+ec);
					   }
					   
					   try
					   {
						   indvresponseaddress.setAddress(idvresponseDetails.getJSONObject("addresses").get("address").toString());
						   
					   }
					   catch(Exception ec)
					   {
						   
						 logger.error("There is error while getting address");
					   }
					   indvresponsedetails.setIndvResponseAddress(indvresponseaddress);
					   /////////////////////////////////////////////////INDV Group Details///////////////////////////////////////////////////////
					  
					   try
					   {
					   
					   JSONObject jsonGroup=idvresponseDetails.getJSONObject("groupdetails");
					   try
					   {
					   indvgrpdetails.setIndv_grp_id(jsonGroup.get("groupid").toString());
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on Indv Group"+ec);
					   }try
					   {
					   indvgrpdetails.setIndv_toatl_account(jsonGroup.get("totaccounts").toString());
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on  totaccounts"+ec);
					   }try
					   {
					   indvgrpdetails.setIndv_toatl_dp_30(jsonGroup.get("totdpd30").toString());
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on Indv toatl_dp_30"+ec);
					   }try
					   {
					   indvgrpdetails.setIndv_toatl_dp_60(jsonGroup.get("totdpd60").toString());
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on Indv toatl_dp_60"+ec);
					   }try
					   {
					   indvgrpdetails.setIndv_toatl_dp_90(jsonGroup.get("totdpd90").toString());
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on Indv toatl_dp_90"+ec);
					   }try
					   {
					   indvgrpdetails.setIndv_total_balance(jsonGroup.get("totcurrentbal").toString());
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on Indv total_balance"+ec);
					   }try
					   {
					   indvgrpdetails.setIndv_total_disbursed_amount(jsonGroup.get("totdisbursedamt").toString());
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on Indv disbursed_amount"+ec);
					   }
					   
					   }
					   catch(Exception  ec)
					   {
						   logger.error("There is Error on Indv Group"+ec);
					   }
					   indvgrpdetails.setIndvresponsedetails(indvresponsedetails);
					   indvresponsedetails.setIndvgrpdetails(indvgrpdetails);
						/////////////////////////////////////////////////INDV Group Details///////////////////////////////////////////////////////
											   
						/////////////////////////////////////////////////INDV Loan Details//////////////////////////////////////////////////////////
						
						try
						{
						
							JSONObject indvloandetailsjson=idvresponseDetails.getJSONObject("loandetail");
							try
							{
							indvloandetails.setAccount_number(indvloandetailsjson.get("acctnumber").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Account_number"+ec);
							}try
							{
							indvloandetails.setAcct_type(indvloandetailsjson.get("accttype").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Acct_type"+ec);
							}try
							{
							indvloandetails.setCombined_payment_history(indvloandetailsjson.get("combinedpaymenthistory").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Combined_payment_history"+ec);
							}try
							{
							indvloandetails.setCurrent_balance(indvloandetailsjson.get("currentbal").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Current_balance"+ec);
							}try
							{
							indvloandetails.setDispursed_amount(indvloandetailsjson.get("disbursedamt").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Dispursed_amount"+ec);
							}try
							{
							indvloandetails.setDispursed_date(indvloandetailsjson.get("disburseddt").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Dispursed_date"+ec);
							}try
							{
							indvloandetails.setDpd(indvloandetailsjson.get("dpd").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Dpd"+ec);
							}try
							{
							indvloandetails.setFrequency(indvloandetailsjson.get("freq").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Frequency"+ec);
							}try
							{
							indvloandetails.setInfosan(indvloandetailsjson.get("infoason").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Infosan"+ec);
							}try
							{
							indvloandetails.setInq_cnt(indvloandetailsjson.get("inqcnt").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Inq_cnt"+ec);
							}try
							{
							indvloandetails.setInstallment_amount(indvloandetailsjson.get("installmentamt").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Installment_amount"+ec);
							}try
							{
							indvloandetails.setLoancycle_id(indvloandetailsjson.get("loancycleid").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Loancycle_id"+ec);
							}try
							{
							indvloandetails.setOverdue_amount(indvloandetailsjson.get("overdueamt").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Overdue_amount"+ec);
							}try
							{
							indvloandetails.setStatus(indvloandetailsjson.get("status").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Status"+ec);
							}try
							{
								indvloandetails.setWrite_off_amount(indvloandetailsjson.get("writeoffamt").toString());
							}catch(Exception ec)
							{
							logger.error("There is error while getting details of Indv Write_off_amount"+ec);
							}
							
						}catch(Exception ec)
						{
						logger.error("There is error while getting details of Indv Loan"+ec);
						}
						indvloandetails.setIndvresponsedetails(indvresponsedetails);
					 indvresponsedetails.setIndvloandetails(indvloandetails);

		               /////////////////////////////////////////////////INDV Loan Details//////////////////////////////////////////////////////////
					   
					   
					   /////////////////////////////////////////////////////////IDS////////////////////////////////////////////////////////////////
					   
					   
					   try
					   {
						   JSONArray indvidsjson=idvresponseDetails.getJSONObject("ids").getJSONArray("id");
						   for(int j=0;j<indvidsjson.length();j++)
						   {
							   IndvIds  indvid=new IndvIds(); 
							   try
							   {
							   JSONObject indv=indvidsjson.getJSONObject(j);
							   indvid.setId_type(indv.get("type").toString());
							   indvid.setId_value(indv.get("value").toString());
							   }
							   catch(Exception ec)
							   {
								   logger.error("There is error at indvid"+ec);
							   }
							   indvid.setIndvresponsedetails(indvresponsedetails);
							   indvids.add(indvid);
						   }
					   }
					   catch(Exception ec)
					   {
						   logger.error("There is Exception at indv id"+ec);
					   }
					   indvresponsedetails.setIndvids(indvids);
					   
					   ////////////////////////////////////////////////////////////IDS//////////////////////////////////////////////////////////////////
					   
					   /////////////////////////////////////////////////////////////for Relation////////////////////////////////////////////////////////
					     try
						   {
							   JSONArray  indvrelationjarray=idvresponseDetails.getJSONObject("relations").getJSONArray("relation");
							   for(int j=0;j<indvrelationjarray.length();j++)
							   {
								   JSONObject indvrelationsingle=indvrelationjarray.getJSONObject(j);
								   IndvRelation indvrelation=new IndvRelation();
								   indvrelation.setIndvresponsedetails(indvresponsedetails);
								   try
								   {
								   indvrelation.setRelation_name(indvrelationsingle.get("name").toString());
								   indvrelation.setRelation_type(indvrelationsingle.get("type").toString());
								   }
								   catch(Exception ec)
								   {
									   logger.error("There is error while saving realation"+ec);
								   }
								   indvrelations.add(indvrelation);
							   }
						   }
						   catch(Exception ec)
						   {
							   logger.error("There is error while saving realation "+ec);
						   } 
					     indvresponsedetails.setIndvrelations(indvrelations);
					    
				  
				   }
				   catch(Exception ec)
				   {
					   logger.error("There is error while getting INDV address json"+ec);
				   }
				   indvresponsedetailsset.add(indvresponsedetails);
				 }
			       
				 
		}
			 catch(Exception ec )
			 {
				 logger.error("Error in INDV Response Details"+ec);
			 }
		     criffDetailLogs.setIndvResponseDetails(indvresponsedetailsset);
		     criffDetailLogs.setServiceType(crifftrackerdto.getService());
		  ////////////////////////////////////for saving indv response Details//////////////////////////////////////////////////
		return criffDetailLogs;
	}
	
}
