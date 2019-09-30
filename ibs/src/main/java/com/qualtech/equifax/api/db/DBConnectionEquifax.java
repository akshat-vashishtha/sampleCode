package com.qualtech.equifax.api.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;
import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;
import com.qualtech.equifax.api.entity.EquifaxVidAllDetails;
import com.qualtech.equifax.api.request.EquifaxReqRes;

@Transactional
@Repository
public class DBConnectionEquifax implements DBEquifax{
	static Logger logger = Logger.getLogger(DBConnectionEquifax.class);
	@Autowired
	private  LocalSessionFactoryBean sessionFactory;
	@Autowired PropertyFile env;
	
	
	protected Session getSession()
	{
		return sessionFactory.getObject().getCurrentSession();
	}
	public Long insertEquifaxMCRReqRes(EquifaxReqRes equifaxMcrReqRes) {
		long count = 0l;

		try {
			count = Integer.parseInt(getSession().save(equifaxMcrReqRes).toString());
			logger.info("Total EquifaxMCRReqRes Records Insertion Against ID -:- " + count);
		} catch (Exception e) {
			logger.error("Error while storing EquifaxMCRReqRes all details:" + e);
		}
		return count;

	}
	
	public Long insertEquifaxPCSReqRes(EquifaxReqRes equifaxPcsReqRes) {
		long count = 0l;

		try {
			count = Integer.parseInt(getSession().save(equifaxPcsReqRes).toString());
			logger.info("Total EquifaxPCSReqRes Records Insertion Against ID -:- " + count);
		} catch (Exception e) {
			logger.error("Error while storing EquifaxPCSReqRes all details:" + e);
		}
		return count;

	}
	
	
	
	public Long insertEquiFaxPCS(EquifaxPcsAllDetails  equifaxPcsAllDetails)
	{
		long count=0l;
		try
		{
			count=Integer.parseInt(getSession().save(equifaxPcsAllDetails).toString());
			logger.info("Total EquifaxPcsAllDetails Records Insertion Against ID -:- "+count);
		}
		catch(Exception e)
		{
			logger.error("DBConnectionEquifax || insertEquiFaxPCS() || Error while storing PCS all details:"+e);
		} 
		
			return count;
		}
	///////////going to VID_Request_insert
	public int insertEquiFaxVID(EquifaxVidAllDetails equifaxdetails)
	{
		int count = 0;
		try 
		{
			count=Integer.parseInt(getSession().save(equifaxdetails).toString());
			
			logger.info("Total EquifaxVidAllDetails Records Insertion Against ID -:- "+count);
		} 
		catch (Exception e)
		{
			logger.error("DBConnectionEquifax || insertEquiFaxVID() || Error while storing EquifaxVid all details:"+e);
		}
		return count;
	}
	
	
	///going to MCR_Request Table
	public int insertEquiFaxMCR(EquifaxMcrAllDetaills  equifaxMcrAllDetaills)
	{
		int count = 0;
		try
		{
			
			count=Integer.parseInt(getSession().save(equifaxMcrAllDetaills).toString());
			
			logger.info("Total EquifaxMcrAllDetaills Records Insertion Against ID -:- "+count);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("DBConnectionEquifax || insertEquiFaxMCR() || Error while storing MCR all details:"+e);
		}
		
		return count;	
	}

	////////New Method insertEquiEVD 
	

	public int insertEquiFaxEVDR(EquifaxEvdrAllDetails  equifaxEvdrAllDetails)
	{
		int count = 0;
		try
		{
			count=Integer.parseInt(getSession().save(equifaxEvdrAllDetails).toString());
			
			logger.info("Total EquifaxEvdrAllDetails Records Insertion Against ID -:- "+count);
		}
		catch(Exception e)
		{
			logger.error("DBConnectionEquifax || insertEquiFaxEVDR() || Error while executing query:"+e);
		}
				return count;
	}
			



}


