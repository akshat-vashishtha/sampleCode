package com.qualtech.multibureau.api.db;

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
import com.qualtech.multibureau.api.common.dto.AllBureauDto;
import com.qualtech.multibureau.api.request.BureauFullReqRes;
import com.qualtech.multibureau.api.request.BureauPayload;
import com.qualtech.multibureau.api.request.BureauReqRes;
import com.qualtech.multibureau.api.request.Header;
import com.qualtech.multibureau.api.response.BureauResponse;

@Transactional
@Repository
public class DBConnectionBureau implements DBBureau {
	static Logger logger = Logger.getLogger(DBConnectionBureau.class);
	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	public Session getSession() {
		return sessionFactory.getObject().getCurrentSession();
	}

	public ServiceMaster getServiceCredential(String serviceRequest) {
		logger.info("Comes to DBConnection in getServiceTagName Method Start: ");
		ServiceMaster master = new ServiceMaster();
		try {

			Criteria criteria = getSession().createCriteria(ServiceMaster.class)
					.add(Restrictions.eq("service_request_uri", serviceRequest));
			logger.info("SQL query to be executed : ");
			try {

				Object results = criteria.uniqueResult();
				if (results != null) {
					master.setService_tag(((ServiceMaster) results).getService_tag());
					master.setPdf_name(((ServiceMaster) results).getPdf_name());
				}
			} catch (Exception e) {
				logger.error("Error in fetching results " + e);
			}

		} catch (Exception e) {
			logger.error("Some exception occured while fetching  Value : " + e);

		}
		logger.info("Getting out from DBConnection in getServiceTagName Method : ");
		return master;
	}

	@Override
	public int insertBureauReqRes(BureauFullReqRes fullReqRes,AllBureauDto dto) {
		Integer EID = null;
		try {
			BureauReqRes rq = fullReqRes.getReqRes();
			logger.info("Bureau Request response save in Bureau : Start");
			EID = insertBureauRequestResponse(rq,dto);
			logger.info("Bureau Request response save in Bureau : End : " + EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in Bureau Request Response " + ec);
		}
		try {
			if (fullReqRes.getBureauRequest() != null && fullReqRes.getBureauRequest().getPayload()!=null
					&& fullReqRes.getBureauRequest().getHeader()!=null) {
					fullReqRes.getBureauRequest().getPayload().setUniqueid(EID);
					fullReqRes.getBureauRequest().getPayload().setCorelationid(fullReqRes.getBureauRequest().getHeader().getCorrelationId());
					logger.info("Bureau save in Bureau individually : Start");
					int count = insertBureauRequest(fullReqRes.getBureauRequest().getPayload());
					logger.info("Bureau Request save in Bureau individually : " + count);
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in bureau Request " + ec);
		}

		try {
			logger.info("Bureau Response save in Bureau individually : Start");
			if (fullReqRes.getBureauResponse() != null ) {
				insertBureauResponse( fullReqRes.getBureauResponse(), EID);
				logger.info("Bureau Response save in Bureau individually : ");
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in bureua Response " + ec);
		}
		return EID;
	}

	public int insertBureauRequestResponse(BureauReqRes rq,AllBureauDto dto) 
	{
		int count = 0;
		try {
			if(dto!=null && dto.getFilePath()!=null && !dto.getFilePath().isEmpty()) 
			{
				int i=1;
				for(String filePath:dto.getFilePath()) 
				{
					BureauReqRes reqRes=(BureauReqRes) rq.clone();
					reqRes.setUniqueId(reqRes.getUniqueId()+"_"+i++);
					reqRes.setPdfPath(filePath);
					count = Integer.parseInt(getSession().save(reqRes).toString());
				}
			}else {
				count = Integer.parseInt(getSession().save(rq).toString());
			}
			logger.info("Bureau Request Reapone saved for id: "+ count);
		} catch (Exception e) {
			logger.error("Error while inserting the BUREAU RES REQ data:" + e);
		}
		return count;
	}

	public int insertBureauRequest(BureauPayload bureauPayload) {
		int count = 0;

		try {
			count = Integer.parseInt(getSession().save(bureauPayload).toString());
			logger.info("BureauRequst saved for id: "+ count);
			
		} catch (Exception e) {
			logger.error("Error while inserting the bureau request data:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in insertBureauRequest Method:- ");
		return count;
	}

	public int insertBureauResponse(BureauResponse res, Integer eID) {
		int count = 0;


		if (res.getPayload() != null && res.getPayload().getResult()!=null) {
			try {

				res.getPayload().getResult().setUniqueid(eID);
				count = Integer.parseInt(getSession().save(res.getPayload().getResult()).toString());
				logger.info("BureauResponse saved for id: "+ count);
			} catch (Exception e) {
				logger.error("Error while inserting the bureau response payload data query:" + e);
			}
			logger.debug("Successfully closed Resources DBConnection in insertBureauResponse Method:- ");
		}
		logger.debug("Successfullysource_system closed Resources DBConnection in insertBureauResponse Method:- ");
		return count;
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
	                    .add(Restrictions.eq("services","MULTIBUREAU" ))
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
				logger.error("Probelm in checking auth"+e);
				return new AuthValidator();
			} 
			}
	

}
