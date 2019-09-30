package com.qualtech.cibilv2.api.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.cibilv2.api.response.CibilAPIResponse2;

@Transactional
@Repository
public class DBConnectionCibil2 implements DBCibilV2 {

	@Autowired
	private  LocalSessionFactoryBean sessionFactory;

	static Logger logger = Logger.getLogger(DBConnectionCibil2.class);


	public Session getSession() {
		return sessionFactory.getObject().getCurrentSession();
	}

	public Long insertRequestResponseTotal(Cibil2FullRequestResponse cibil2FullReqRes) 
	{
		Long count=0l;

		try
		{
			if(cibil2FullReqRes!=null&& cibil2FullReqRes.getCibilReqRes()!=null)
			{

				count=(Long) getSession().save(cibil2FullReqRes.getCibilReqRes());
				logger.debug("DBConnectionn || insertCibilData() || save cibil reqres :: "+count+" -:-  ");

			}
		}
		catch (Exception e) {
			logger.error("Error while inserting the IB_Cibilv2_Req_Res data:" + e);
		}


		return count;
	}

	public Long insertResponse(CibilAPIResponse2 res, Long UNIQUE_ID) {
		Long count = 0l;
		try {
			res.getPayload().getRoot().setUniqueid(UNIQUE_ID);
			getSession().save(res.getPayload().getRoot());

		} catch (Exception e) {
			logger.error("Error while inserting the Response data:" + e);
		}


		return count;
	}

}
