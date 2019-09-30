package com.qualtech.multibureau.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.request.AddressBureau;
import com.qualtech.multibureau.api.request.IdTypes;
import com.qualtech.multibureau.api.request.PhoneBureau;

public class ApiMapping {

	static Logger logger = Logger.getLogger(ApiMapping.class.getName());
	public String getFirstApi(BureauRequest req,PropertyFile resProp) {
		
		logger.info("Inside in getFirstApi method");
		
		String requestFirst="";
		try {
			List<AddressBureau> addValue=req.getPayload().getAddress();
			List<PhoneBureau> phnValue=req.getPayload().getPhone();
			
			StringBuilder requestApidata = new StringBuilder();
			Calendar cal1=Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy HH:mm:ss");
			String requesttime = format.format(cal1.getTime());

			 requestApidata.append("	{	");
			 requestApidata.append("	    \"HEADER\": {	");
				requestApidata.append("	            \"CUST-ID\": \""+req.getPayload().getCust_id()+"\",	");
				requestApidata.append("	            \"APPLICATION-ID\": \""+req.getPayload().getApplication_id()+"\",	");
				requestApidata.append("	            \"REQUEST-TYPE\": \""+resProp.getString("com.bureau.request.type")+"\",	");
				requestApidata.append("	            \"REQUEST-TIME\": \""+requesttime+"\"	");
				requestApidata.append("	        }	,");
				
				requestApidata.append("	  \"REQUEST\": {	");
				requestApidata.append("	    \"01\": \""+req.getPayload().getPriority()+"\",	");
				requestApidata.append("	    \"02\": \""+resProp.getString("com.bureau.request.product.type")+"\",");
				requestApidata.append("	    \"03\": \""+req.getPayload().getLoan_Type()+"\",	");
				requestApidata.append("	    \"04\": \""+req.getPayload().getLoan_amount()+"\",");
				requestApidata.append("	    \"05\": \""+resProp.getString("com.bureau.source_system.joint_ind")+"\",	");
				requestApidata.append("	    \"06\": \""+req.getPayload().getInquiry_submitted_by()+"\",");
				requestApidata.append("	    \"07\": \""+resProp.getString("com.bureau.source_system.direct")+"\",	");
				requestApidata.append("	    \"08\": \""+req.getPayload().getSource_system_version()+"\",");
				requestApidata.append("	    \"09\": \""+req.getPayload().getSource_system_vender()+"\",	");
				requestApidata.append("	    \"10\": \""+req.getPayload().getSource_system_instance_id()+"\",");
				requestApidata.append("	    \"11\": \""+resProp.getString("com.bureau.request.bureaau.region")+"\",	");
				requestApidata.append("	    \"12\": \""+req.getPayload().getLoan_purpose_desc()+"\",");
				requestApidata.append("	    \"13\": \""+req.getPayload().getBranch_ifsccode()+"\",	");
				requestApidata.append("	    \"14\": \""+req.getPayload().getKendra()+"\",");
				requestApidata.append("	    \"15\": \""+req.getPayload().getInquiry_stage()+"\",	");
				requestApidata.append("	    \"16\": \""+req.getPayload().getAuthrization_flag()+"\",");
				requestApidata.append("	    \"18\": \""+req.getPayload().getAuthroized_by()+"\",	");
				requestApidata.append("	    \"19\": \""+req.getPayload().getIndividual_corporate_flag()+"\",");
				requestApidata.append("	    \"20\": \""+req.getPayload().getConstitution()+"\",	");
				
				requestApidata.append("	  \"21\": {	");
				requestApidata.append("	    \"01\": \""+req.getPayload().getFirstName()+"\",");
				requestApidata.append("	    \"02\": \""+req.getPayload().getLastName()+"\"	");
				requestApidata.append("	 		 }	,");
				
				requestApidata.append("	    \"22\": \""+req.getPayload().getGender()+"\",	");
				requestApidata.append("	    \"23\": \""+req.getPayload().getMarital_status()+"\",	");
				requestApidata.append("	    \"27\": \""+req.getPayload().getBirth_dt()+"\",	");
				requestApidata.append("	    \"28\": \""+req.getPayload().getAge_as_on_dt()+"\",	");
				
				requestApidata.append("	  \"29\": [	");
				
				int i=0;
				if(addValue!=null)
				{
					for(AddressBureau ad:addValue)
					{
						if(i!=0)
						{
						requestApidata.append("	 		,");
						}
						i=1;
						
						requestApidata.append("	{    \"01\": \""+ad.getAddress_type()+"\",	");
						requestApidata.append("	    \"02\": \""+ad.getAddress_residence_code()+"\",	");
						requestApidata.append("	    \"03\": \""+ad.getAddressInfo()+"\",	");
						requestApidata.append("	    \"04\": \""+ad.getAddress_city()+"\",	");
						requestApidata.append("	    \"06\": \""+ad.getAddress_state()+"\",	");
						requestApidata.append("	    \"05\": \""+ad.getAddress_pin()+"\"	");
						requestApidata.append("	 		 }");
					}
				}	
				else
				{
					logger.info("While getting error of mapping address field");
				}
				requestApidata.append("	 		 ]	,");
				//Id insertion in request
				IdTypes id=req.getPayload().getId();
				if(id!=null){
					requestApidata.append("	  \"30\": {	");
					requestApidata.append("	    \"01\": \""+id.getPan()+"\",	");
					requestApidata.append("	    \"04\": \""+id.getPassport()+"\",	");
					requestApidata.append("	    \"07\": \""+id.getVoter()+"\",	");
					requestApidata.append("	    \"10\": \""+id.getDrivingLicence()+"\",	");
					requestApidata.append("	    \"13\": \""+id.getUid()+"\",	");
					requestApidata.append("	    \"16\": \""+id.getRationcard()+"\"	");
					requestApidata.append("	 		 }	,");
				}
				requestApidata.append("	  \"31\": [	");
				
				int ii=0;
				
				if(phnValue!=null)
				{
					for(PhoneBureau add:phnValue)
					{
						if(ii!=0)
						{
						requestApidata.append("	 		,");
						}
						ii=1;
						
						requestApidata.append("	{    \"01\": \""+add.getPhone_type()+"\",	");
						requestApidata.append("	    \"02\": \""+add.getPhone_number()+"\"	");
						requestApidata.append("	 		 }");
						
						
					}
				}
				else
				{
					logger.info("While getting error of mapping phone field");
				}
				requestApidata.append("	 		],");
				
				
				requestApidata.append("	    \"40\": \""+(req.getPayload().getTanure()!=null?req.getPayload().getTanure():"0")+"\",	");
				requestApidata.append("	    \"51\": \""+req.getPayload().getEmail_id_2()+"\",	");
				requestApidata.append("	    \"52\": \""+req.getPayload().getAlias()+"\",	");
				requestApidata.append("	    \"54\": \""+req.getPayload().getAct_opening_dt()+"\",	");
				requestApidata.append("	    \"55\": \""+req.getPayload().getAccount_number_1()+"\"	");
				
				
				requestApidata.append("	             }	");
				requestApidata.append("	  }	");
				
				requestFirst = requestApidata.toString();
		} catch (Exception e) {
			logger.error("While getting error of getFirstApi api mapping"+e);
		}
		
		
		return requestFirst;
	}
	
	
	
	public String getSecondApi(String cusId,String appId,String requestTime,String acknowledgementIdValue,String responseFormat,String requestType,String sourceType) {

		logger.info("Inside secondApi method: Start");
		 String request="";
		try {
			   
			   StringBuilder requestdata = new StringBuilder();
			   
				requestdata.append("	{	");
				requestdata.append("	    \"HEADER\": {	");
				requestdata.append("	            \"CUST-ID\": \""+cusId+"\",	");
				requestdata.append("	            \"APPLICATION-ID\": \""+appId+"\",	");
				requestdata.append("	            \"REQUEST-TYPE\": \""+requestType+"\",	");
				requestdata.append("	            \"REQUEST-RECEIVED-TIME\": \""+requestTime+"\",	");
				requestdata.append("	            \"SOURCE-SYSTEM\": \""+sourceType+"\"	");
				requestdata.append("	        }	,");
				requestdata.append("	          \"ACKNOWLEDGEMENT-ID\": \"" + acknowledgementIdValue + "\",");
				
				requestdata.append("	                \"RESPONSE-FORMAT\": [	");
				requestdata.append("	           					"+responseFormat+"   ]	");
				
				requestdata.append("	                    }	");
				
				request = requestdata.toString();
				
				logger.info("Getting Acknowledgement ID with "+acknowledgementIdValue);
				logger.info("request data  "+request);
		} catch (Exception e) {
			logger.error("While getting error of second api mapping"+e);
		}
		logger.info("Inside secondApi method: END");
		return request;
	}
	
}
