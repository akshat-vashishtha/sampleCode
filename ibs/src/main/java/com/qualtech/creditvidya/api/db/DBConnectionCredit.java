package com.qualtech.creditvidya.api.db;

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
import com.qualtech.creditvidya.api.common.dto.EmailSaveRequest;
import com.qualtech.creditvidya.api.common.dto.EmailVerificationRequest;
import com.qualtech.creditvidya.api.dbInt.DBConnectionCreditInt;
import com.qualtech.creditvidya.api.entity.EmailSaveResponseEntity;
import com.qualtech.creditvidya.api.request.CreditFullReqRes;
import com.qualtech.creditvidya.api.request.CreditReqRes;
import com.qualtech.creditvidya.api.request.Header;
import com.qualtech.creditvidya.api.response.EmailSaveResponse;
import com.qualtech.creditvidya.api.response.EmailVerificationResponse;

@Repository
@Transactional
public class DBConnectionCredit implements DBConnectionCreditInt {
	static Logger logger = Logger.getLogger(DBConnectionCredit.class);

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	private Session getSession() 
	{
		return sessionFactory.getObject().getCurrentSession();
	}

	public int insertEmailSaveResponse(EmailSaveResponse emailSaveResponse, Long EID)
	{
		int count = 0;
		EmailSaveResponseEntity emailSaveResponseEntity = new EmailSaveResponseEntity();

		emailSaveResponseEntity.setSid(EID.toString());
		emailSaveResponseEntity.setUniqueId(emailSaveResponse.getPayload().getUniqueId());
		emailSaveResponseEntity.setCorrelationId(emailSaveResponse.getHeader().getCorrelationId());
		try {
			getSession().save(emailSaveResponseEntity);
			logger.debug(" DBConnectionCredit || insertEmailSaveResponse() || After Executing query ID -:- " + emailSaveResponseEntity.getSid() + " -:-  ");
		} catch (Exception e) {
			logger.error("Exception in DBConnectionCredit || insertEmailSaveResponse() || inserting EMAIL SAVE RESPONSE data :: " + e);
		}
		return count;
	}

	public void insertEmailSaveRequest(EmailSaveRequest emailSaveRequest) {
		if (emailSaveRequest.getPayload() != null) {
			try {
				emailSaveRequest.getPayload().setCorelationid(emailSaveRequest.getHeader().getCorrelationId());
				getSession().save(emailSaveRequest.getPayload());

				logger.debug("DBConnectionCredit || insertEmailSaveRequest() || After Executing query ID -:- " + emailSaveRequest.getPayload().getEid() + " -:- ");

			} catch (Exception e) {
				logger.error("Exception in DBConnectionCredit || insertEmailSaveRequest() || inserting the EMAIL SAVE REQUEST PAYLOAD data:" + e);
			}
		}

	}

	public void insertEmailVerifyResponse(EmailVerificationResponse emailVerificationResponse) {
		if (emailVerificationResponse.getPayload() != null) {
			try {
				emailVerificationResponse.getPayload()
						.setCorelationid(emailVerificationResponse.getHeader().getCorrelationId());

				getSession().save(emailVerificationResponse.getPayload());

			} catch (Exception e) {
				logger.error(" Exception in DBConnectionCredit || insertEmailVerifyResponse() || inserting the  EMAIL vrfy Response PAYLOAD data:" + e);
			}
		}

	}

	public Long insertCreditRequestResponse(CreditReqRes rqres) {
		Long EID = 0l;
		// rqres.setEid(this.getSequenceValue("CREDIT_MAIN_SQC"));
		try {
			EID =(Long) getSession().save(rqres);
			logger.debug("DBConnectionCredit || insertCreditRequestResponse() || After Executing query ID -:- " + rqres.getEid() + " -:- ");

		} catch (Exception e) {
			EID = 0l;
			logger.error("Exception in DBConnectionCredit || insertCreditRequestResponse() || inserting the CREDIT RES REQ data:" + e);
		}
		return EID;
	}

	@Override
	public AuthValidator validateAuth(Header header) {
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
                    .add(Restrictions.eq("services","CREDIT" ))
                    .add(Restrictions.ge("expirydate",formatted));
			
			try
			{
				Object results[] =(Object[])  criteria.uniqueResult();
//				System.out.println(results[1]);
				//AuthValidator results =  (AuthValidator) criteria.uniqueResult();
				if (results != null)
				{
					validator.setAppid(results[0].toString());//..getAppid());
					validator.setToken(results[1].toString());//.getToken());
				}
			} 
			catch (Exception e) 
			{
				logger.error("Exception in DBConnectionCredit || validateAuth() || criteria.uniqueResult() " + e);
			}
			return validator;
		} 
		catch (Exception e)
		{
			logger.error("DBConnectionCredit || validateAuth() || Exception occurs :: "+e);
			return new AuthValidator();
		} 
	}


	public int insertEmailVerificationRequest(EmailVerificationRequest emailVerificationRequest) {

		int count = 0;
		try {
			emailVerificationRequest.getPayload()
					.setCorelationid(emailVerificationRequest.getHeader().getCorrelationId());

			getSession().save(emailVerificationRequest.getPayload());
			logger.debug(" DBConnectionCredit || insertEmailVerificationRequest() || After Executing query ID -:- " + emailVerificationRequest.getPayload().getSid() + " -:-  ");

		} catch (Exception e) {
			logger.error("Exception in  DBConnectionCredit || insertEmailVerificationRequest() || Inserting EMAIL VRFY REQ data :: " + e);
		}

		return count;
	}

	@Override
	public void insertAlltoDB(CreditFullReqRes creditFullReqRes) 
	{
		Long EID = 0l;
		
		if (creditFullReqRes.getReqRes() != null) 
		{
			try 
			{
				EID = insertCreditRequestResponse(creditFullReqRes.getReqRes());
			}
			catch (Exception e) 
			{
				logger.error("Exception in DBConnectionCredit || insertAlltoDB() || calling insertCreditRequestResponse() :: "+e);
			}
		}

		if (creditFullReqRes.getEmailVerificationResponse() != null && EID > 0) {
			try 
			{
				creditFullReqRes.getEmailVerificationResponse().getPayload().setEid(EID);
				insertEmailVerifyResponse(creditFullReqRes.getEmailVerificationResponse());

			} catch (Exception e) 
			{
				logger.error("Exception in DBConnectionCredit || insertAlltoDB() || calling insertEmailVerifyResponse() :: "+e);
			}
		}

		if (creditFullReqRes.getEmailSaveResponse() != null && EID > 0) {
			try {
				insertEmailSaveResponse(creditFullReqRes.getEmailSaveResponse(), EID);
			} catch (Exception e) {
				logger.error("Exception in DBConnectionCredit || insertAlltoDB() || calling insertEmailSaveResponse() :: "+e);
			}
		}

		if (creditFullReqRes.getEmailSaveRequest() != null && EID > 0) {
			try {
				creditFullReqRes.getEmailSaveRequest().getPayload().setEid(EID.toString());
				insertEmailSaveRequest(creditFullReqRes.getEmailSaveRequest());
			} catch (Exception e) {
				logger.error("Exception in DBConnectionCredit || insertAlltoDB() || calling insertEmailSaveRequest() :: "+e);
			}
		}
		if (creditFullReqRes.getEmailVerificationRequest() != null && EID > 0) {
			try {
				creditFullReqRes.getEmailVerificationRequest().getPayload().setSid(EID.toString());
				insertEmailVerificationRequest(creditFullReqRes.getEmailVerificationRequest());
			} catch (Exception e) {
				logger.error("Exception in DBConnectionCredit || insertAlltoDB() || calling insertEmailVerificationRequest() :: "+e);
			}
		}

	}

}
