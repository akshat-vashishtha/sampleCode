package com.qualtech.hdfc.api.db;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.ServiceMaster;
import com.qualtech.hdfc.api.request.ApiRequest;
import com.qualtech.hdfc.api.request.HdfcFullReqRes;
import com.qualtech.hdfc.api.request.HdfcReqRes;
import com.qualtech.hdfc.api.request.HdfcRequestPayload;
import com.qualtech.hdfc.api.request.Header;
import com.qualtech.karza.api.db.DBConnectionKarza;


@Service
@Transactional
public class DBConnectionHdfc implements DBHdfc {
	
	static Logger logger = Logger.getLogger(DBConnectionKarza.class);
	
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


	public void insertHdfcRequestResponse(HdfcReqRes rq,HdfcFullReqRes fullReqRes) {
		Integer EID = null;
		logger.info("Hdfc Request Response insert start");
		EID = insertHdfcRequestResponse(rq);
		logger.info("Hdfc Request Response insert end");
		ApiRequest apireq=fullReqRes.getHdfcRequest();
		if(apireq!=null) {
			insertHdfcRequest(apireq,EID);
		}
	}
	private void insertHdfcRequest(ApiRequest apireq, Integer eID) { 
		HdfcRequestPayload payload=apireq.getPayload();
		try {
			
		if(payload!=null) {
			payload.setUniqueid(Long.valueOf(eID));
			/*long id = (long)*/ getSession().saveOrUpdate(payload);
			 getSession().getTransaction().commit();
		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the HDFC REQ  data:" + e);
		}
		
	}


	private Integer insertHdfcRequestResponse(HdfcReqRes rq) {
		int count = 0;
		try {
		
			int id = Integer.parseInt(getSession().save(rq).toString());
			count = Integer.parseInt("" + id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the HDFC REQ RES data:" + e);
		}
		return count;
	}

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
	                    .add(Restrictions.eq("services","HDFC" ))
	                    .add(Restrictions.ge("expirydate",formatted));
				
				try
				{
					
					 List<Object[]> results =criteria.list();
//					System.out.println(results[1]);
					//AuthValidator results =  (AuthValidator) criteria.uniqueResult();
					if (results != null)
					{
						for (Object[] objects : results) {
							
							validator.setAppid(objects[0].toString());//..getAppid());
							validator.setToken(objects[1].toString());//.getToken());
						}
						
						
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
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
