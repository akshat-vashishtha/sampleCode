package com.qualtech.experiankickoff.api.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.ServiceMaster;
import com.qualtech.experiankickoff.api.common.dto.ExperianRequest;
import com.qualtech.experiankickoff.api.common.dto.MaskExperianRequest;
import com.qualtech.experiankickoff.api.request.AuthExperianRequest;
import com.qualtech.experiankickoff.api.request.ExperianGenQuestionRequest;
import com.qualtech.experiankickoff.api.request.ExperianKickOffFullReqRes;
import com.qualtech.experiankickoff.api.request.ExperianKickoffReqRes;
import com.qualtech.experiankickoff.api.request.Header;
import com.qualtech.experiankickoff.api.response.AuthExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianGenQuestionResponse;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponse;
import com.qualtech.experiankickoff.api.response.ExperianResponse;


@Transactional
@Repository
public class DBConnectionKickOffImpl implements DBConnection{

	static Logger logger = Logger.getLogger(DBConnectionKickOffImpl.class);
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	

	private Session getSession() {
		return sessionFactory.getObject().getCurrentSession();
	}
	
	@Override
	public  AuthValidator validateAuth(Header header)
	{
		AuthValidator validator=new AuthValidator();
		try 
		{
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			String formatted = format.format(cal.getTime());
			
			Criteria criteria=getSession().createCriteria(AuthValidator.class);
            criteria.setProjection(Projections.projectionList()
  	              .add(Projections.property("appid"))
	                  .add(Projections.property("token"))
	                  .add(Projections.property("expirydate")));
               criteria.add(Restrictions.eq("status", "Y"))
                    .add(Restrictions.eq("appid",header.getAppId()))
                    .add(Restrictions.eq("token",header.getToken()))
                    .add(Restrictions.eq("services","KARZA" ))
                    .add(Restrictions.ge("expirydate",formatted));
               
			try
			{
				Object results[] = (Object[]) criteria.uniqueResult();
				if (results != null && results.length > 0)
				{
					validator.setAppid(results[0].toString());
					validator.setToken(results[1].toString());
				}
			} 
			catch (Exception e) 
			{
				logger.error("DBConnectionKickOffImpl || validateAuth() || Exception occurs while calling uniqueResult()"+e);
			}
			return validator;
		} 
		catch (Exception e)
		{
			logger.error("Probelm in checking auth"+e);
			return new AuthValidator();
		} 
		}

	
	public  Long insertExperianRequestResponse(ExperianKickoffReqRes reqRes) {
		Long EID = 0l;

		try {
			//reqRes.setEid(this.getSequenceValue("QCIB_EX2_MAIN_SQC"));
			EID=(Long) getSession().save(reqRes);
			logger.debug("After saving ReqRes ID -:- " + EID + " -:-  ");
			} 
		catch (Exception e)
		{
			EID=0l;
			logger.error("DBConnectionKickOffImpl || insertExperianRequestResponse() || Error while inserting the EXPERIAN RES REQ data:" + e);
		}
		
		return EID;
	}

	public Long insertExperianResponse(ExperianResponse expResponse) {
		
		Long EID = 0l;
		try
		{
			if(expResponse!=null){
				
				getSession().saveOrUpdate(expResponse.getPayload().getResult());
				EID=Long.parseLong(expResponse.getPayload().getResult().getEid());
				logger.debug("After saving singleAtion Response ID -:- "+ EID+" -:- ");
				
				}
			}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianResponse() || Exception while saving singleAtion Response : "+e);
		}
		return EID;
	}
	public Long insertExperianMaskResponse(ExperianMaskResponse expResponse) {
		
		Long EID = 0l;
		try
		{
			if(expResponse!=null){
				
				getSession().saveOrUpdate(expResponse.getPayload().getResult());
				EID=Long.parseLong(expResponse.getPayload().getResult().getEid());
				logger.debug("After saving ExperianMaskResponse ID -:- "+ EID+" -:- ");
				
				}
			}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianMaskResponse() || Some exception occured while saving ExperianMaskResponse : "+e);
		}
		return EID;
	}


	private void insertExperianAuthResponse(AuthExperianResponse expAuthResponse) {

		Long EID = 0l;
		try
		{
			if(expAuthResponse!=null){
				
				getSession().saveOrUpdate(expAuthResponse.getPayload().getResult());
				EID=Long.parseLong(expAuthResponse.getPayload().getResult().getEid());
				logger.debug("After saving ExperianAuthResponse ID -:- "+ EID+" -:- ");
				
				}
			}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianAuthResponse() ||Some exception occured while After saving ExperianAuthResponse : "+e);
		}
		
	}
	private void insertExperianGenResponse(ExperianGenQuestionResponse expGenResponse) {
		
		Long EID = 0l;
		try
		{
			if(expGenResponse!=null){
				
				getSession().saveOrUpdate(expGenResponse.getPayload().getResult());
				EID=Long.parseLong(expGenResponse.getPayload().getResult().getEid());
				
				/*if(expGenResponse.getPayload().getResult().getQuestionToCustomer()!=null){
					expGenResponse.getPayload().getResult().getQuestionToCustomer().setEid(EID.toString());
					getSession().saveOrUpdate(expGenResponse.getPayload().getResult().getQuestionToCustomer());
				}*/
				logger.debug("After saving ExperianGenerateQuestion Response ID -:- "+ EID+" -:- ");
				}
			}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianGenResponse() || Some exception occured while saving ExperianGenerateQuestion Response : "+e);
		}
	}
	public Long insertExperianRequest(ExperianRequest experianRequest) {
		Long EID = 0l;

		try
		{
			if(experianRequest!=null)
			{
				getSession().saveOrUpdate(experianRequest.getPayload());
				EID=Long.parseLong(experianRequest.getPayload().getEid());
				logger.debug("After saving ExperianSingleAction Request ID -:- "+EID+" -:-  ");
				
			}
		}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianRequest() || Some exception occured while  saving ExperianSingleAction Request  : "+e);
		}	
		
		return EID;
	
	}
	
	public Long insertExperianMaskRequest(MaskExperianRequest  experianMaskRequest) {
		Long EID = 0l;

		try
		{
			if(experianMaskRequest!=null)
			{
				getSession().saveOrUpdate(experianMaskRequest.getPayload());
				EID=Long.parseLong(experianMaskRequest.getPayload().getEid());
				logger.debug("After saving ExperianMaskRequest ID -:- "+EID+" -:-  ");
				
			}
		}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianMaskRequest() || Some exception occured while  saving ExperianMaskRequest : "+e);
		}	
		
		return EID;
	
	}
	private void insertExperianGenRequest(ExperianGenQuestionRequest expGenRequest) {

		Long EID = 0l;

		try
		{
			if(expGenRequest!=null)
			{
				getSession().saveOrUpdate(expGenRequest.getPayload());
				EID=Long.parseLong(expGenRequest.getPayload().getEid());
				logger.debug("After saving ExperianGenerateQuestion Request ID -:- "+EID+" -:-  ");
				
			}
		}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianGenRequest() || Some exception occured while saving ExperianGenerateQuestion Request  : "+e);
		}	
		
		
	}

	private void insertExperianAuthRequest(AuthExperianRequest expAuthRequest) {

		Long EID = 0l;

		try
		{
			if(expAuthRequest!=null)
			{
				getSession().saveOrUpdate(expAuthRequest.getPayload());
				EID=Long.parseLong(expAuthRequest.getPayload().getEid());
				logger.debug("After saving ExperianAuth Request ID -:- "+EID+" -:-  ");
				
			}
		}
		catch(Exception e)
		{			
			logger.error("DBConnectionKickOffImpl || insertExperianAuthRequest() || Some exception occured while  saving ExperianAuth Request  : "+e);
		}	
		
	}

	public  Long insertAlltoDB(ExperianKickOffFullReqRes fullReqRes) {
		
		logger.info("Inside insertAlltoDbMethod : Start");
		Long EID=0l;
		
		if(fullReqRes.getReqRes()!=null) {
			try {
				
				EID=insertExperianRequestResponse(fullReqRes.getReqRes());
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in insert Experian RequestResponse save "+e);
			}
		
		}
		
		//-----------Single Action
		if(fullReqRes.getExperianRequest()!=null && EID>0) {
			try {
				fullReqRes.getExperianRequest().getPayload().setEid(EID.toString());
				insertExperianRequest(fullReqRes.getExperianRequest());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianSingleAction Request save "+e);
			}
		}
		
		
		if(fullReqRes.getExperianResponse()!=null  && EID>0) {
			try {
				fullReqRes.getExperianResponse().getPayload().getResult().setEid(EID.toString());
				insertExperianResponse(fullReqRes.getExperianResponse());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianSingleAction Response save "+e);
			}
		}
		
		//----------------Mask
		
		if(fullReqRes.getExpMaskRequest()!=null && EID>0) {
			try {
				fullReqRes.getExpMaskRequest().getPayload().setEid(EID.toString());
				insertExperianMaskRequest(fullReqRes.getExpMaskRequest());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianMask Request save "+e);
			}
		}
		
		
		if(fullReqRes.getExpMaskResponse()!=null  && EID>0) {
			try {
				fullReqRes.getExpMaskResponse().getPayload().getResult().setEid(EID.toString());
				insertExperianMaskResponse(fullReqRes.getExpMaskResponse());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianMask Response save "+e);
			}
		}
		
		//------------Auth
		if(fullReqRes.getExpAuthRequest()!=null && EID>0) {
			try {
				fullReqRes.getExpAuthRequest().getPayload().setEid(EID.toString());
				insertExperianAuthRequest(fullReqRes.getExpAuthRequest());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianAuth Request save "+e);
			}
		}
		
		
		if(fullReqRes.getExpAuthResponse()!=null  && EID>0) {
			try {
				fullReqRes.getExpAuthResponse().getPayload().getResult().setEid(EID.toString());
				insertExperianAuthResponse(fullReqRes.getExpAuthResponse());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianGAuth Response save "+e);
			}
		}
		
		//------------Gen Question
		if(fullReqRes.getExpGenRequest()!=null && EID>0) {
			try {
				fullReqRes.getExpGenRequest().getPayload().setEid(EID.toString());
				insertExperianGenRequest(fullReqRes.getExpGenRequest());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianGenrateQuestion Request save "+e);
			}
		}
		
		
		if(fullReqRes.getExpGenResponse()!=null  && EID>0) {
			try {
				fullReqRes.getExpGenResponse().getPayload().getResult().setEid(EID.toString());
				insertExperianGenResponse(fullReqRes.getExpGenResponse());
				
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianGenerateQuestion Response save "+e);
			}
		}
		logger.info("Inside insertAlltoDbMethod  : END ");
		return null;
	}

	
	public  String getServiceTagName(String serviceRequestUri)
	{
		logger.info("Comes to DBConnection in getServiceTagName Method Start: ");
		
		String serviceTagName = null;
		try
		{
				
				Criteria criteria=  getSession().createCriteria(ServiceMaster.class).add(Restrictions.eq("service_request_uri", serviceRequestUri));
				logger.info("SQL query to be executed : " );
				try
				{
				
				Object results=  criteria.uniqueResult();
					if (results != null )
					{
						serviceTagName=((ServiceMaster)results).getService_tag();
					}
				} 
				catch (Exception e) 
				{
					logger.error("Error in fetching results "+e );
				}
			
		}
		catch (Exception e)
		{
			logger.error("DBConnectionKickOffImpl || getServiceTagName() ||Some exception occured while fetching  Value : " + e);

		} 
		logger.info("Getting out from DBConnection in getServiceTagName Method : ");
		return serviceTagName;
	}
	@Override
	public  String getPdfName(String TagName)
	{
		logger.info("Comes to DBConnection in getPdfName Method Start: ");
		
		String pdfName = null;
		try
		{
				Criteria criteria=  getSession().createCriteria(ServiceMaster.class).add(Restrictions.eq("service_tag", TagName));
				logger.info("SQL query to be executed : " );
				try
				{
				
					Object results=  criteria.uniqueResult();
					if (results != null )
					{
						pdfName=((ServiceMaster)results).getPdf_name();
					}
				} 
				catch (Exception e) 
				{
					logger.error("Error in fetching results "+e );
				}
			
		}
		catch (Exception e)
		{
			logger.error("DBConnectionKickOffImpl || getPdfName() || Some exception occured while fetching  Value : " + e);

		} 
		logger.info("Getting out from DBConnection in getServiceTagName Method : ");
		return pdfName;
	}


}
