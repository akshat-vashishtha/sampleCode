package com.qualtech.equifax.api.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.qualtech.equifax.api.entity.common.EquifaxIdentityInfo;
import com.qualtech.equifax.api.entity.common.EquifaxPersonalInfo;
import com.qualtech.equifax.api.entity.mcr.EquifaxMCRIdAndContactDetails;

public class EquifaxIdAndContactSetter {

	Logger logger = Logger.getLogger(EquifaxIdAndContactSetter.class.getName());
	public EquifaxMCRIdAndContactDetails setMCR_IDAndContactDetails(JSONObject jsonObj){
		EquifaxMCRIdAndContactDetails idAndContactDetails=new EquifaxMCRIdAndContactDetails();
		try{
			
			if(jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata").getJSONObject("idandcontactinfo")!=null){
				
				JSONObject mixDetail=jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata").getJSONObject("idandcontactinfo");
				if(mixDetail!=null){
					try{
						idAndContactDetails.setAddress(mixDetail.getJSONObject("addressinfo").get("address").toString());
					}catch(Exception ex){
						logger.info("error while parse jsonObject:: "+ex);}
					try{
						idAndContactDetails.setSeq(mixDetail.getJSONObject("addressinfo").get("seq").toString());
					}catch(Exception ex){
						logger.info("error while parse jsonObject:: "+ex);}
					try{
						idAndContactDetails.setState(mixDetail.getJSONObject("addressinfo").get("state").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						idAndContactDetails.setType(mixDetail.getJSONObject("addressinfo").get("type").toString());
					}catch(Exception ex){
						logger.info("error while parse jsonObject:: "+ex);}
					try{
						idAndContactDetails.setReporteddate(mixDetail.getJSONObject("addressinfo").get("reporteddate").toString());
					}catch(Exception ex){
						logger.info("error while parse jsonObject:: "+ex);}
					
					
				}
				
			}
			
		}catch(Exception ee){
			logger.info("error in setMCR_IDAndContactDetails method while parse jsonObject:: "+ee);
		}
	
		
		return idAndContactDetails;
	}
	
	public EquifaxPersonalInfo equifaxMCRPersonalInfo(JSONObject jsonObj){
		EquifaxPersonalInfo equifaxPersonalInfo=new EquifaxPersonalInfo();
		try{
			
			if(jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata").getJSONObject("idandcontactinfo")!=null){
				
				JSONObject mixDetail=jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata").getJSONObject("idandcontactinfo");
				if(mixDetail!=null){
					
					try{
						equifaxPersonalInfo.setMaritalstatus(mixDetail.getJSONObject("personalinfo").get("maritalstatus").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setFirstname(mixDetail.getJSONObject("personalinfo").getJSONObject("name").get("firstname").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setMiddlename(mixDetail.getJSONObject("personalinfo").getJSONObject("name").get("middlename").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setLastname(mixDetail.getJSONObject("personalinfo").getJSONObject("name").get("lastname").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setAdditionalmiddlename(mixDetail.getJSONObject("personalinfo").getJSONObject("name").get("additionalmiddlename").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setAge(mixDetail.getJSONObject("personalinfo").getJSONObject("age").get("age").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setGender(mixDetail.getJSONObject("personalinfo").get("gender").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setDateofbirth(mixDetail.getJSONObject("personalinfo").get("dateofbirth").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setAliasname(mixDetail.getJSONObject("personalinfo").getJSONObject("aliasnameinfo").get("aliasname").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setReporteddate(mixDetail.getJSONObject("personalinfo").getJSONObject("aliasnameinfo").get("reporteddate").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setFamily_noOfDependents(mixDetail.getJSONObject("familydetails").get("noofdependents").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setFamily_additionalnametype(mixDetail.getJSONObject("familydetails").getJSONObject("additionalnameinfo").get("additionalnametype").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						equifaxPersonalInfo.setFamily_additionalname(mixDetail.getJSONObject("familydetails").getJSONObject("additionalnameinfo").get("additionalname").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);
				}
					
				}
				
			}
			
		}catch(Exception ee){
			logger.info("error in equifaxMCRPersonalInfo method while parse jsonObject:: "+ee);
		}
		
		return equifaxPersonalInfo;
	}
	
	public EquifaxIdentityInfo equifaxMcrIdentityInfo(JSONObject jsonObj){
		EquifaxIdentityInfo identityInfo=new EquifaxIdentityInfo();
		try{
			
			if(jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata").getJSONObject("idandcontactinfo")!=null){
				
				JSONObject mixDetail=jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata").getJSONObject("idandcontactinfo");
				if(mixDetail!=null){
					
					try{
						identityInfo.setSeq(mixDetail.getJSONObject("identityinfo").getJSONObject("idother").get("seq").toString());
					}catch(Exception ex){
						logger.info("error while parse jsonObject:: "+ex);}
					try{
						identityInfo.setIdnumber(mixDetail.getJSONObject("identityinfo").getJSONObject("idother").get("idnumber").toString());
					}catch(Exception ex){logger.info("error while parse jsonObject:: "+ex);}
					try{
						identityInfo.setReporteddate(mixDetail.getJSONObject("identityinfo").getJSONObject("idother").get("reporteddate").toString());
					}catch(Exception ex){
						logger.info("error while parse jsonObject:: "+ex);}
					
					
				}
				
			}
			
		}catch(Exception ee){
			logger.info("error in equifaxMcrIdentityInfo method while parse jsonObject:: "+ee);
		}
		return identityInfo;
	}
	
	public String getDisclaimer(JSONObject jsonObj){
		String disclaimer="";
		try{
			if(jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata")!=null){
				
				JSONObject mixDetail=jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata");
				if(mixDetail!=null){
					 disclaimer=(String) mixDetail.get("disclaimer");
				}
			}
			
		}catch(Exception ee){
			logger.info("error in getDisclaimer method while parse jsonObject:: "+ee);
		}
		return disclaimer;
	} 
}
