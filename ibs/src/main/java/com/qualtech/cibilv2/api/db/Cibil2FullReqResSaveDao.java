package com.qualtech.cibilv2.api.db;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.cibil.api.db.DBCibil;


@Service
public class Cibil2FullReqResSaveDao implements Cibil2FullReqResSaveDaoInt{
	
	@Autowired
	DBCibilV2 dbCibilv2;
	
	
	static Logger logger = Logger.getLogger(Cibil2FullReqResSaveDao.class.getName());
	
	public void saveCibilData(Cibil2FullRequestResponse cibil2FullReqRes) 
	{
		try
		{
			logger.info("Cibil2FullReqResSaveDao || saveCibilData in DB :: Start");
			Long EID =dbCibilv2.insertRequestResponseTotal(cibil2FullReqRes);
			logger.info("Cibil2FullReqResSaveDao || insertRequestResponseTotal in DB ::  EID :: "+EID);
			final Long UId= Long.valueOf(EID);
			cibil2FullReqRes.setEID(UId);
		}
		catch(Exception ec)
		{
			logger.error("Exception saving data in Cibil2FullReqResSaveDao || insertRequestResponseTotal || "+ec);
		}
		try
		{
			
			logger.info("Cibil2FullReqResSaveDao save insertResponse in DB individually : Start");
			Long count = dbCibilv2.insertResponse(cibil2FullReqRes.getCibilResponse(), cibil2FullReqRes.getEID());
			logger.info("Cibil2FullReqResSaveDao save insertResponse Successfull Response : Count "+count);
		}
		catch(Exception ec)
		{
			logger.error("Exception saving data in Cibil2FullReqResSaveDao || insertResponse || "+ec);
		}
		
		try
		{
			int count=0;
			logger.info("Cibil2FullReqResSaveDao save insertRequest in DB individually : Start");
			//int count = new DBConnectionCibil2().insertRequest(cibil2FullReqRes.getCibilRequest(), cibil2FullReqRes.getEID());
			logger.info("Cibil2FullReqResSaveDao save insertResponse Successfull Response : Count "+count);
		}
		catch(Exception ec)
		{
			logger.error("Exception saving data in Cibil2FullReqResSaveDao || insertRequest || "+ec);
		}
	}

}
