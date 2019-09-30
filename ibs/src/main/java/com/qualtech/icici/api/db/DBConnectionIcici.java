package com.qualtech.icici.api.db;


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
import com.qualtech.api.ibs.util.Header;
import com.qualtech.icici.api.request.CustomerOnBoardRequest;
import com.qualtech.icici.api.request.IciciFullReqRes;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.request.PolicyStatusRequest;
import com.qualtech.icici.api.request.PremCalc;
import com.qualtech.icici.api.request.WelcomeKitRequest;
import com.qualtech.icici.api.response.CustomerOnBoardResponse;
import com.qualtech.icici.api.response.PolicyStatusResponse;
import com.qualtech.icici.api.response.PremCalResponse;
import com.qualtech.icici.api.response.WelcomeKitResponse;


@Transactional
@Repository
public class DBConnectionIcici implements DBIcici
{
	static Logger logger = Logger.getLogger(DBConnectionIcici.class);
	@Autowired
	private  LocalSessionFactoryBean sessionFactory;

	public Session getSession() {
		return sessionFactory.getObject().getCurrentSession();
	}
	public  ServiceMaster getServiceCredential(String serviceRequest)
	{
		logger.info("Comes to DBConnection in getServiceTagName Method Start: ");
		ServiceMaster master=new ServiceMaster();
		try
		{
				Criteria criteria=  getSession().createCriteria(ServiceMaster.class).add(Restrictions.eq("service_request_uri", serviceRequest));
				logger.info("SQL query to be executed : " );
				try
				{
					
					
				    Object results=  criteria.uniqueResult();
					if (results != null )
					{
						master.setService_tag(((ServiceMaster)results).getService_tag());
						master.setPdf_name(((ServiceMaster)results).getPdf_name());
					}
				} 
				catch (Exception e) 
				{
					logger.error("Error in fetching results "+e );
				}
			
		}
		catch (Exception e)
		{
			logger.error("Some exception occured while fetching  Value : " + e);

		} 
		logger.info("Getting out from DBConnection in getServiceTagName Method : ");
		return master;
	}

	@Override
    public  AuthValidator validateAuth(Header header)
	{
		logger.info("DBConnectionIcici || validateAuth() || STARTS");
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
                    .add(Restrictions.eq("services","ICICI" ))
                    .add(Restrictions.ge("expirydate",formatted));
			
			try
			{
				Object results[] =(Object[])  criteria.uniqueResult();
				if (results != null)
				{
					validator.setAppid(results[0].toString());
					validator.setToken(results[1].toString());
				}
			} 
			catch (Exception e) 
			{
				logger.error("DBConnectionIcici || validateAuth() || in exception :: " + e);
			}
			logger.info("DBConnectionIcici || validateAuth() || ENDS");
			return validator;
		} 
		catch (Exception e)
		{
			logger.error("DBConnectionIcici || validateAuth() || ENDS with error :: " +e);
			return new AuthValidator();
		} 

		}

	public  int insertIciciRequestResponse(IciciReqRes iciciReqRes) {
		logger.info("DBConnectionIcici || insertIciciRequestResponse() || STARTS");
		int eId = 0;

		try {
			eId= Integer.parseInt(getSession().save(iciciReqRes).toString());
			logger.info("DBConnectionIcici || insertIciciRequestResponse() || After saving ReqRes ID :: " +eId);
			} 
		catch (Exception e)
		{
			eId=0;
			logger.error("DBConnectionIcici || insertIciciRequestResponse() || Error while inserting the Policy Status RES REQ data :: " + e);
		}
		logger.info("DBConnectionIcici || insertIciciRequestResponse() || ENDS");
		return eId;
	}
	
	public int insertPremCalcRequest(PremCalc premCalcReq) {
		logger.info("DBConnectionIcici || insertPolicyStatusRequest() || STARTS");
		int eId = 0;

		try
		{
			if(premCalcReq!=null)
			{
				getSession().saveOrUpdate(premCalcReq);
				getSession().getTransaction().commit();
				getSession().getTransaction().begin();
				eId= (premCalcReq.getEid());
				logger.info("DBConnectionIcici || insertPolicyStatusRequest() || After saving Req ID :: " +eId);
				
			}
		}
		catch(Exception e)
		{	
			logger.error("DBConnectionIcici || insertPolicyStatusRequest() || Error while inserting the Policy Status REQ data :: " + e);
		}	
		logger.info("DBConnectionIcici || insertPolicyStatusRequest() || ENDS");
		return eId;
	
	}
	
		
	public int insertPolicyStatusResponse(PremCalResponse premCalResponse) {
		logger.info("DBConnectionIcici || insertPolicyStatusResponse() || STARTS");
		int eId = 0;
		try {
			if (premCalResponse != null) {

				getSession().saveOrUpdate(premCalResponse.getPayload().getEbiResponse());
				
				eId = premCalResponse.getPayload().getEbiResponse().getEid();
				logger.info("DBConnectionIcici || insertPolicyStatusResponse() || After saving Res ID :: " +eId);
			}
		} catch (Exception e) {
			logger.error("DBConnectionIcici || insertPolicyStatusResponse() || Error while inserting the Policy Status REQ data :: "+ e);
		}
		logger.info("DBConnectionIcici || insertPolicyStatusResponse() || ENDS");
		return eId;
	}
	
	@Override
	public int insertPremCalReqRes(IciciFullReqRes fullReqRes) {
		logger.info("DBConnectionIcici || insertPolicyStatusReqRes() || STARTS");
		int eId = 0;
			if(fullReqRes.getReqRes()!=null) {
				try {
					
					eId=insertIciciRequestResponse(fullReqRes.getReqRes());
				} catch (Exception e) {
					logger.error(
							"DBConnectionIcici || insertPolicyStatusReqRes() || Error while inserting the Policy Status REQ RES data :: "
									+ e);
				}
			
			}
			
			if(fullReqRes.getPremCalcRequest()!=null && eId>0) {
				try {
					fullReqRes.getPremCalcRequest().getPayload().getIciciRequest().getPremiumCalculation().setEid(eId);
					PremCalc premCalcReq = fullReqRes.getPremCalcRequest().getPayload().getIciciRequest().getPremiumCalculation();
					insertPremCalcRequest(premCalcReq);
				} catch (Exception e) {
					logger.error(
							"DBConnectionIcici || insertPolicyStatusReqRes() || Error while inserting the Policy Status REQ data :: "
									+ e);
				}
			}
			
			if(fullReqRes.getPremCalResponse()!=null  && eId>0) {
				try {
					fullReqRes.getPremCalResponse().getPayload().getEbiResponse().setEid(eId);
					insertPolicyStatusResponse(fullReqRes.getPremCalResponse());
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertPolicyStatusReqRes() || Error while inserting the Policy Status RES data :: "+ e);
				}
			}
			logger.info("DBConnectionIcici || insertPolicyStatusReqRes() || ENDS");
			return eId;
	}
	@Override
	public int insertCustomerOnBoardReqRes(IciciFullReqRes fullReqRes) {

		logger.info("DBConnectionIcici || insertCustomerOnBoardReqRes() || STARTS");
		int eId = 0;
			if(fullReqRes.getReqRes()!=null) {
				try {
					eId=insertIciciRequestResponse(fullReqRes.getReqRes());
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertCustomerOnBoardReqRes() || Error while inserting Customer On Board Request & response data :: "+ e);
				}
			}
			
	// Comment due to CustomerOnBoardRequest not created by hibernate 
			/*if(fullReqRes.getCustomerOnBoardRequest()!=null && eId>0) {
				try {
					fullReqRes.getCustomerOnBoardRequest().setEid(eId);
					CustomerOnBoardRequest customerOnBoardRequest =fullReqRes.getCustomerOnBoardRequest();
					insertCustomerOnBoardRequest(customerOnBoardRequest);
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertCustomerOnBoardReqRes() || Error while inserting Customer On Board Request data :: "+ e);
				}
			}*/
			
			if(fullReqRes.getCustomerOnBoardResponse()!=null  && eId>0) {
				try {
					fullReqRes.getCustomerOnBoardResponse().getPayload().setEid(eId);
					CustomerOnBoardResponse customerOnBoardResponse = fullReqRes.getCustomerOnBoardResponse();
					insertCustomerOnBoardResponse(customerOnBoardResponse);
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertPolicyStatusReqRes() || Error while inserting Customer On Board response data :: "+ e);
				}
			}
			logger.info("DBConnectionIcici || insertCustomerOnBoardReqRes() || ENDS");
			return eId;
	}
	
	public int insertCustomerOnBoardRequest(CustomerOnBoardRequest customerOnBoardRequest) {
		logger.info("DBConnectionIcici || insertCustomerOnBoardRequest() || STARTS");
		int eId = 0;

		try
		{
			if(customerOnBoardRequest!=null)
			{
				getSession().saveOrUpdate(customerOnBoardRequest);
				getSession().getTransaction().commit();
				getSession().getTransaction().begin();
				eId= (customerOnBoardRequest.getEid());
				logger.info("DBConnectionIcici || insertCustomerOnBoardRequest() || After saving Req ID :: " +eId);
			}
		}
		catch(Exception e)
		{	
			logger.error("DBConnectionIcici || insertCustomerOnBoardRequest() || Error while inserting Customer On Board Request data :: " + e);
		}	
		logger.info("DBConnectionIcici || insertCustomerOnBoardRequest() || ENDS");
		return eId;
	
	}
	
	public int insertCustomerOnBoardResponse(CustomerOnBoardResponse customerOnBoardResponse ) {
		logger.info("DBConnectionIcici || insertCustomerOnBoardResponse() || STARTS");
		int eId = 0;
		try {
			if (customerOnBoardResponse != null) {

				getSession().saveOrUpdate(customerOnBoardResponse.getPayload());
				
				eId = customerOnBoardResponse.getPayload().getEid();
				logger.info("DBConnectionIcici || insertCustomerOnBoardResponse() || After saving Res ID :: " +eId);
			}
		} catch (Exception e) {
			logger.error("DBConnectionIcici || insertCustomerOnBoardResponse() || Error while inserting Customer On Board Response data :: "+ e);
		}
		logger.info("DBConnectionIcici || insertCustomerOnBoardResponse() || ENDS");
		return eId;
	}

	
	@Override
	public int insertWelcomeKitReqRes(IciciFullReqRes fullReqRes) {
		logger.info("DBConnectionIcici || insertWelcomeKitReqRes() || STARTS");
		int eId = 0;
			if(fullReqRes.getReqRes()!=null) {
				try {
					eId=insertIciciRequestResponse(fullReqRes.getReqRes());
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertWelcomeKitReqRes() || Error while inserting Welcome Kit Request & response data :: "+ e);
				}
			}
			
			if(fullReqRes.getWelcomeKitRequest()!=null && eId>0) {
				try {
					fullReqRes.getWelcomeKitRequest().setEid(eId);
					WelcomeKitRequest welcomeKitRequest =fullReqRes.getWelcomeKitRequest();
					insertWelcomeKitRequest(welcomeKitRequest);
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertWelcomeKitReqRes() || Error while inserting Welcome Kit Request data :: "+ e);
				}
			}
			
			if(fullReqRes.getWelcomeKitResponse()!=null  && eId>0) {
				try {
					fullReqRes.getWelcomeKitResponse().getPayload().setEid(eId);
					WelcomeKitResponse welcomeKitResponse = fullReqRes.getWelcomeKitResponse();
					insertWelcomeKitResponse(welcomeKitResponse);
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertWelcomeKitReqRes() || Error while inserting Welcome Kit response data :: "+ e);
				}
			}
			logger.info("DBConnectionIcici || insertWelcomeKitReqRes() || ENDS");
			return eId;
	}
	
	public int insertWelcomeKitRequest(WelcomeKitRequest welcomeKitRequest) {
		logger.info("DBConnectionIcici || insertWelcomeKitRequest() || STARTS");
		int eId = 0;

		try
		{
			if(welcomeKitRequest!=null)
			{
				getSession().saveOrUpdate(welcomeKitRequest);
				getSession().getTransaction().commit();
				getSession().getTransaction().begin();
				eId= (welcomeKitRequest.getEid());
				logger.info("DBConnectionIcici || insertWelcomeKitRequest() || After saving Req ID :: " +eId);
			}
		}
		catch(Exception e)
		{	
			logger.error("DBConnectionIcici || insertWelcomeKitRequest() || Error while inserting Welcome Kit  Request data :: " + e);
		}	
		logger.info("DBConnectionIcici || insertWelcomeKitRequest() || ENDS");
		return eId;
	
	}
	
	public int insertWelcomeKitResponse(WelcomeKitResponse welcomeKitResponse) {
		logger.info("DBConnectionIcici || insertWelcomeKitResponse() || STARTS");
		int eId = 0;
		try {
			if (welcomeKitResponse != null) {
				getSession().saveOrUpdate(welcomeKitResponse.getPayload());
				eId = welcomeKitResponse.getPayload().getEid();
				logger.info("DBConnectionIcici || insertWelcomeKitResponse() || After saving Res ID :: " +eId);
			}
		} catch (Exception e) {
			logger.error("DBConnectionIcici || insertWelcomeKitResponse() || Error while inserting Welcome Kit Response data :: "+ e);
		}
		logger.info("DBConnectionIcici || insertWelcomeKitResponse() || ENDS");
		return eId;
	}
	
	@Override
	public int insertPolicyStatusReqRes(IciciFullReqRes fullReqRes) {
		logger.info("DBConnectionIcici || insertPolicyStatusReqRes() || STARTS");
		int eId = 0;
			if(fullReqRes.getReqRes()!=null) {
				try {
					eId=insertIciciRequestResponse(fullReqRes.getReqRes());
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertPolicyStatusReqRes() || Error while inserting Policy Status Request & response data :: "+ e);
				}
			}
			
			if(fullReqRes.getPolicyStatusRequest()!=null && eId>0) {
				try {
					fullReqRes.getPolicyStatusRequest().setEid(eId);
					PolicyStatusRequest policyStatusRequest =fullReqRes.getPolicyStatusRequest();
					insertPolicyStatusRequest(policyStatusRequest);
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertPolicyStatusReqRes() || Error while inserting Policy Status Request data :: "+ e);
				}
			}
			
			if(fullReqRes.getPolicyStatusResponse()!=null  && eId>0) {
				try {
					fullReqRes.getPolicyStatusResponse().getPayload().setEid(eId);
					PolicyStatusResponse policyStatusResponse = fullReqRes.getPolicyStatusResponse();
					insertPolicyStatusResponse(policyStatusResponse);
				} catch (Exception e) {
					logger.error("DBConnectionIcici || insertPolicyStatusReqRes() || Error while inserting Policy Status response data :: "+ e);
				}
			}
			logger.info("DBConnectionIcici || insertPolicyStatusReqRes() || ENDS");
			return eId;
	}
	
	public int insertPolicyStatusRequest(PolicyStatusRequest policyStatusRequest) {
		logger.info("DBConnectionIcici || insertWelcomeKitRequest() || STARTS");
		int eId = 0;

		try
		{
			if(policyStatusRequest!=null)
			{
				getSession().saveOrUpdate(policyStatusRequest);
				getSession().getTransaction().commit();
				getSession().getTransaction().begin();
				eId= (policyStatusRequest.getEid());
				logger.info("DBConnectionIcici || insertPolicyStatusRequest() || After saving Req ID :: " +eId);
			}
		}
		catch(Exception e)
		{	
			logger.error("DBConnectionIcici || insertPolicyStatusRequest() || Error while inserting Policy Status Request data :: " + e);
		}	
		logger.info("DBConnectionIcici || insertPolicyStatusRequest() || ENDS");
		return eId;
	
	}
	
	public int insertPolicyStatusResponse(PolicyStatusResponse policyStatusResponse) {
		logger.info("DBConnectionIcici || insertPolicyStatusResponse() || STARTS");
		int eId = 0;
		try {
			if (policyStatusResponse != null) {
				getSession().saveOrUpdate(policyStatusResponse.getPayload());
				eId = policyStatusResponse.getPayload().getEid();
				logger.info("DBConnectionIcici || insertPolicyStatusResponse() || After saving Res ID :: " +eId);
			}
		} catch (Exception e) {
			logger.error("DBConnectionIcici || insertPolicyStatusResponse() || Error while inserting Policy Status Response data :: "+ e);
		}
		logger.info("DBConnectionIcici || insertPolicyStatusResponse() || ENDS");
		return eId;
	}
	
	
	
}
