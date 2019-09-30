package com.qualtech.cibil.api.db;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.cibil.api.entity.CibilReqResVpn;
import com.qualtech.cibil.api.entity.ScoringFactors;
import com.qualtech.cibil.api.response.CibilResponsePayload;

@Transactional
@Repository
public class DBConnectionn implements DBCibil
	{	
	@Autowired
	private  LocalSessionFactoryBean sessionFactory;
	static Logger logger = Logger.getLogger(DBConnectionn.class);


	public Session getSession() {
		return sessionFactory.getObject().getCurrentSession();
	}
	
	public static String getCibilScoreFactors(List<ScoringFactors> scoringFactors)
	{
		String factors ="";
		int i=1;
		for(ScoringFactors scfactors : scoringFactors)
		{
			factors+=(i++)+" : "+scfactors.getFactor()+" || ";
		}
		if(!factors.equals(""))
		{
			factors=factors.substring(0,factors.length()-3);
		}
		return factors;
	}
	public int insertCibilData(CibilResponsePayload  cibilresponseentity,String correalationId, String requestData, String responseData,CibilReqResVpn rq)
	{
		logger.info("DBConnectionn || insertCibilData() || STARTS");
		
		int count = 0;
		try {
			count=(Integer) getSession().save(rq);
		} catch (Exception e) {
			logger.error("DBConnectionn || insertCibilData() || CIBILREQRES || Exception : "+e);
		}
		
		try
		{
			
			cibilresponseentity.setCorelationid(correalationId);
			cibilresponseentity.setCibilReq(requestData);
			cibilresponseentity.setUniqueid(count);
			cibilresponseentity.setCibilRes(responseData);
			cibilresponseentity.setPrintableReport(cibilresponseentity.getPrintableReport()==null?"":cibilresponseentity.getPrintableReport());
			count=Integer.parseInt(getSession().save(cibilresponseentity).toString());
			
		    logger.debug("DBConnectionn || insertCibilData() || save(cibilresponseentity) :: "+count+" -:-  ");
	
		}
		catch(Exception e)
		{
			
			logger.error("DBConnectionn || insertCibilData() || fetching records || Exception : "+e);
		}
		logger.info("DBConnectionn || insertCibilData() || ENDS");
		return count;
	}


}