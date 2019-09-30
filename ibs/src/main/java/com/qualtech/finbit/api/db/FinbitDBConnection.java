package com.qualtech.finbit.api.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
import com.qualtech.finbit.api.request.AccountDetail;
import com.qualtech.finbit.api.request.FinbitApiRequest;
import com.qualtech.finbit.api.request.FinbitFullReqRes;
import com.qualtech.finbit.api.request.FinbitHeader;
import com.qualtech.finbit.api.request.FinbitReqRes;
import com.qualtech.finbit.api.response.FinbitAPIResponse;
import com.qualtech.finbit.api.response.FinbitResResult;
import com.qualtech.finbit.api.response.FinbitResponsePayload;

@Transactional
@Repository
public class FinbitDBConnection implements FinbitDB{

	static Logger logger = Logger.getLogger(FinbitDBConnection.class);
	@Autowired
	private  LocalSessionFactoryBean sessionFactory;

	public Session getSession() {
		return sessionFactory.getObject().getCurrentSession();
	}
	@Override
	public AuthValidator validateAuth(FinbitHeader header) {
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
			.add(Restrictions.eq("services","FINBIT" ))
			.add(Restrictions.ge("expirydate",formatted));

			try
			{
				Object results[] =(Object[])  criteria.uniqueResult();
				if (results != null)
				{
					validator.setAppid(results[0].toString());//..getAppid());
					validator.setToken(results[1].toString());//.getToken());
				}
			} 
			catch (Exception e) 
			{
			}
			return validator;
		} 
		catch (Exception e)
		{
			logger.error("FinbitDBConnection || validateAuth() || Probelm in checking auth"+e);
			return new AuthValidator();
		} 
	}
	@Override
	public int insertToDB(FinbitFullReqRes fullReqRes) {
		logger.info("Inside insertToDB : Start");
		int EID=0;

		if(fullReqRes.getReqRes()!=null) {
			try {
				if(fullReqRes.getFinbitapiresponse()!=null && fullReqRes.getFinbitapiresponse().getPayload()!=null ) {
					
					fullReqRes.getReqRes().setPdfPath(fullReqRes.getFinbitapiresponse().getPayload().getPdfPath());
				}
				EID=insertFinbitRequestResponse(fullReqRes.getReqRes());
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in insert Experian RequestResponse save "+e);
			}

		}

		if(fullReqRes.getFinbitapirequest()!=null && EID>0) {
			try {
				insertExperianRequest(fullReqRes.getFinbitapirequest(),EID);

			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianSingleAction Request save "+e);
			}
		}


		if(fullReqRes.getFinbitapiresponse()!=null && fullReqRes.getFinbitapiresponse().getPayload()!=null
				&& fullReqRes.getFinbitapiresponse().getPayload().getResult()!=null && EID>0) {
			try {
				insertExperianResponse(fullReqRes.getFinbitapiresponse().getPayload().getResult(),EID);
getSession().getTransaction().commit();
getSession().getTransaction().begin();
			} catch (Exception e) {
				logger.error("DBConnectionKickOffImpl || insertAlltoDB() || Error in ExperianSingleAction Response save "+e);
			}
		}
		logger.info("Inside insertToDB : END");
		return 0;
	}
	private void insertExperianResponse(List<FinbitResResult> results, int EID) {

		logger.info("Inside insertExperianResponse : START");
		int seqID = 0;

		try {
			for(FinbitResResult result:results) {
				result.setEid(EID);
				seqID=Integer.parseInt(getSession().save(result).toString());
				logger.debug("After saving AccountDetail ID -:- " + seqID + " -:-  ");
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error("FinbitDBConnection || insertExperianResponse() || Error while inserting the Finbit Request data:" + e);
		}
		logger.info("Inside insertExperianResponse : END");
	}
	private void insertExperianRequest(FinbitApiRequest finbitapirequest, int EID) {
		logger.info("Inside insertExperianRequest : START");
		int seqID = 0;

		try {
			for(AccountDetail accoutDtl:finbitapirequest.getPayload().getAccountDetail()) {
				accoutDtl.setCorrelationId(finbitapirequest.getHeader().getCorrelationId());
				accoutDtl.setEid(EID);

				seqID=Integer.parseInt(getSession().save(accoutDtl).toString());
				logger.debug("After saving AccountDetail ID -:- " + seqID + " -:-  ");
			}

		} 
		catch (Exception e)
		{
			logger.error("FinbitDBConnection || insertExperianRequest() || Error while inserting the Finbit Request data:" + e);
		}
		logger.info("Inside insertExperianRequest : END");

	}
	private int insertFinbitRequestResponse(FinbitReqRes reqRes) {
		logger.info("Inside insertFinbitRequestResponse : START");
		int EID = 0;

		try {
			
				EID=Integer.parseInt(getSession().save(reqRes).toString());
		
			
			logger.debug("After saving ReqRes ID -:- " + EID + " -:-  ");

		} 
		catch (Exception e)
		{
			EID=0;
			logger.error("FinbitDBConnection || insertFinbitRequestResponse() || Error while inserting the Finbit RES REQ data:" + e);
		}
		logger.info("Inside insertFinbitRequestResponse : END");
		return EID;
	}

}
