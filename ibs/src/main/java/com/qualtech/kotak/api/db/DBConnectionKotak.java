package com.qualtech.kotak.api.db;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.request.Header;
import com.qualtech.kotak.api.request.KotakPayRequestHeader;
import com.qualtech.kotak.api.request.KotakPayload;
import com.qualtech.kotak.api.request.KotakPayment;
import com.qualtech.kotak.api.request.KotakReqRes;
import com.qualtech.kotak.api.request.KotakReversal;
import com.qualtech.kotak.api.request.KotakReversalPayload;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakResponseAckHeader;
import com.qualtech.kotak.api.response.KotakResponsePayload;
import com.qualtech.kotak.api.response.KotakReversalResponse;
import com.qualtech.kotak.api.response.KotakReversalResponsePayload;

@Repository
@Transactional
public class DBConnectionKotak  implements DBKotak{
	@Autowired
	private static PropertyFile resourceBundle;
	static Logger logger = Logger.getLogger(DBConnectionKotak.class);
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getObject().getCurrentSession();
	}

	public Transaction getTranstaction() {
		return getSession().getTransaction();
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
				Object results[] =(Object[])  criteria.uniqueResult();
				//AuthValidator results =  (AuthValidator) criteria.uniqueResult();
				if (results != null)
				{
					validator.setAppid(results[0].toString());//..getAppid());
					validator.setToken(results[1].toString());//.getToken());
				}
			} 
			catch (Exception e) 
			{
				logger.error("DBConnectionKotak || validateAuth() || exception calling uniqueResult() "+e);
			}
			return validator;
		} 
		catch (Exception e)
		{
			logger.error("DBConnectionKotak || validateAuth() || Probelm in checking auth"+e);
			return new AuthValidator();
		} 
		}
	
	
	
	///////
	public int insertKotakRequest(KotakReqRes rq) {
		logger.debug("Comes to DBConnection in insertKotakRequest Method: ");
		int count = 0;
		
		try {
			
			
		  long  count1=   (Long) getSession().save(rq);
		   getSession().getTransaction().commit();
		   getSession().getTransaction().begin();
		   count=Integer.parseInt(count1+"");
		  
		  } 
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error("DBConnectionKotak || insertKotakRequest() ||Exception while Saving Object DBConnection in insertKotakRequest Method: " + e);
        }
		logger.debug("Successfully  DBConnection in insertKotakRequest Method:- ");
		return count;
	}
//////////
	public  int updateInternalKotakReqResforPending(Long uniqueId, String pendingStatus) 
	{ 
		logger.debug("Comes to DBConnection in updateInternalKotakReqResforPending Method: ");
		int count = 0;
		
		try
		{
			
			String hql = "UPDATE IB_KOTAK_REQ_RES SET IS_PUSHED = '"+pendingStatus+"' WHERE EID = '"+uniqueId+"'";
			count =getSession().createSQLQuery(hql.toString()).executeUpdate();
		}
		catch (Exception e)
		{

			logger.error("DBConnectionKotak || updateInternalKotakReqResforPending() || Error while updating the QCIB_KOTAK_REQ_RES RES REQ data:" + e);
		}
		logger.debug("Successfully  DBConnection in updateInternalKotakReqResforPending Method:- ");
		return count;
	}

	public int insertKotakPaymentResponse(KotakResponse kotakResponse, Long uniqueId) {
		logger.debug("Comes to DBConnection in insertKotakPaymentResponse Method: ");
		int count = 0;
		
		try{
			if(kotakResponse!=null && !kotakResponse.equals("")){
				KotakResponsePayload kotkpayload = kotakResponse.getPayload();
				if(kotkpayload!=null && !kotkpayload.equals("")){
					KotakResponseAckHeader kotkAckhdr = kotakResponse.getPayload().getAckHeader();
					if(kotkAckhdr!=null && !kotkAckhdr.equals("")){
						try {
							

							kotkpayload.setUniqueid(uniqueId);
							
							long count1=	 (Long) getSession().save(kotkpayload);
							 getSession().getTransaction().commit();
               			     getSession().getTransaction().begin();
							
               			     
               			     String str=""+count1;
						 count=Integer.parseInt(str);
						
						
						} 
						catch (Exception e)
						{  e.printStackTrace();
							logger.info("DBConnectionKotak || insertKotakPaymentResponse() || Error while Saving Object KotakResponseAckHeader in insertKotakPaymentResponse Method: "+e);
							
						}
					}
					
					
				}
			}
			
		}catch(Exception ee){
			logger.error("DBConnectionKotak || insertKotakPaymentResponse() || Some exception occured while fetching records : "+ee);
		}
		logger.debug("Successfully  DBConnection in insertKotakPaymentResponse Method:- ");
		return count;
	
	}
//////
	public int insertKotakReversalResponse(KotakReversalResponse kotkResponse, Long uniqueId) {
		logger.debug("Comes to DBConnection in insertKotakReversalResponse Method:");
		int count = 0;
		
		try
		{ 
			
			if(kotkResponse!=null){
				KotakReversalResponsePayload kotkRevPayload =kotkResponse.getPayload();
				if(kotkRevPayload!=null && !kotkRevPayload.equals("")){
					kotkRevPayload.setRequest_unique_id(uniqueId);
					   
					long count1=  (Long) getSession().save(kotkRevPayload);
						 getSession().getTransaction().commit();
          			     getSession().getTransaction().begin();   
					   String str=""+count1;
					   count=Integer.parseInt(str);
			                   
							}
					}
			}
		catch(Exception e)
		{			e.printStackTrace();
			logger.error("DBConnectionKotak || insertKotakReversalResponse() || Some exception occured while fetching records : "+e);
		}		
		logger.debug("Successfully  DBConnection in insertKotakReversalResponse Method:- ");
		return count;
	
	}
///
	public int insertkotakPaymentRequest(KotakRequest kotakRequest, Long UniqueId) {
		logger.debug("Comes to DBConnection in insertkotakPaymentRequest Method:");
		int count = 0;
		
		try
		{     
			if(kotakRequest!=null)
			{
				KotakPayload KotkPayload = kotakRequest.getPayload();
				if(KotkPayload!=null && !KotkPayload.equals(""))
				{
					KotakPayment KotkPayment = KotkPayload.getKotakPayment();
					if(KotkPayment!=null && !KotkPayment.equals(""))
					{
						KotakPayRequestHeader KotkPayHeader = KotkPayment.getPayheader();
						if(KotkPayHeader!=null && !KotkPayHeader.equals(""))
						{
						count = insertkotakPaymentHeaderRequest(KotkPayment, UniqueId);
						}
						
					}
					
				}
				
			}
			
		}
		catch(Exception e)
		{			
			e.printStackTrace();
			logger.error("DBConnectionKotak || insertkotakPaymentRequest() || Some exception occured while fetching records : "+e);
		}		
		logger.debug("Successfully  DBConnection in insertkotakPaymentRequest Method:- ");
		return count;
	
	}
///
	public int insertkotakPaymentHeaderRequest(KotakPayment KotkPayment, Long UniqueId) {
		logger.debug("Comes to DBConnection in insertkotakPaymentHeaderRequest Method:");
		int count = 0;

		try {
			if (KotkPayment != null) {
				KotakPayRequestHeader KotkPayHeader = KotkPayment.getPayheader();
				if (KotkPayHeader != null && !KotkPayHeader.equals("")) {
					KotkPayment.setRequest_unique_id(UniqueId);
					KotkPayment.getInstrumentList().setEnrichmentSetCopy(KotkPayment.getInstrumentList().getInstrument().getEnrichmentSet());
					KotkPayment.getInstrumentList().getEnrichmentSetCopy().setEnchSet(KotkPayment.getInstrumentList());
					long count1 = (Long) getSession().save(KotkPayment);
					boolean commitStatus = getTranstaction().wasCommitted();
					if (commitStatus == false) {
						getTranstaction().commit();
					}

					getTranstaction().begin();
					String str = "" + count1;
					count = Integer.parseInt(str);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DBConnectionKotak || insertkotakPaymentHeaderRequest() || Some exception occured while fetching records : " + e);
		}
		logger.debug("Successfully  DBConnection in insertkotakPaymentHeaderRequest Method:- ");
		return count;
	}
	
	
	//////////
public int insertkotakRevrslHeaderRequest(KotakReversalPayload kotakReversalPayload, Long UniqueId) {
		logger.debug("Comes to DBConnection in insertkotakRevrslHeaderRequest Method:");
		int count = 0;
		
		try
		{
			
			if(kotakReversalPayload!=null)
			{
				KotakReversal kotakReversal = kotakReversalPayload.getReversal();
				if(kotakReversal!=null && !kotakReversal.equals(""))
				{
					kotakReversal.setRequest_unique_id(UniqueId);
				
					
				long count1=(Long) getSession().save(kotakReversal);
				 getSession().getTransaction().commit();
				 getSession().getTransaction().begin();
				  String str=""+count1;
			     count=Integer.parseInt(str);
			}
			}
			
		}
		catch(Exception e)
		{		
			logger.error("DBConnectionKotak || insertkotakRevrslHeaderRequest() || Some exception occured while fetching records : "+e);
		}		
		logger.debug("Successfully  DBConnection in insertkotakRevrslHeaderRequest Method:- ");
		return count;
	}

	////
	public String getfailure(KotakReqRes  kotakreqres,long requestId)
	{
		logger.info("getfailure() method : Start");
	
		String response="";
		try
		{
			String str=""+requestId;
			int id=Integer.parseInt(str);
			
			Criteria criteria=getSession().createCriteria(KotakReqRes.class)
					.add(Restrictions.eq("eid", requestId));
			
			KotakReqRes	kotakres= (KotakReqRes) criteria.uniqueResult();
			
			response=kotakres.getResponse();
		}
		catch (Exception e) 
		{ e.printStackTrace();
			logger.error("DBConnectionKotak || getfailure() : Exception : " + e);
		}
		catch(Throwable t)
		{
			logger.error("DBConnectionKotak || getfailure() : Throwable : " + t);
		}
		logger.info("getfailure() method : END");
		return response;
	}

	@Override
	public int insertKotakReversalRequestResponse(KotakFULLRequestResponse kotkrqres) {
		logger.info("insertKotakReversalRequestResponse() method : Start");
		
		int count =0;
		
	  try{
		if(kotkrqres!=null){
			//Insertion Operation for KotakReqRes
			KotakReqRes rq=kotkrqres.getKotkreqres();
			if(rq!=null && !rq.equals(""))
			{
			count = insertKotakRequest(rq);
			logger.info("REQUEST ID :- "+count);
			}
			
			//Insertion Operation for KotakRequestReversal(REQUEST)
			KotakRequestReversal kotakreq=kotkrqres.getKotakReversalReq();
			final Integer UID=count;
		    final Long RequestUId= Long.valueOf(UID);
			if( kotakreq!=null && ! kotakreq.equals(""))
			{
				KotakReversalPayload kotakReversalPayload=kotakreq.getPayload();
				if(kotakReversalPayload!=null)
					insertkotakRevrslHeaderRequest(kotakReversalPayload,RequestUId );
			      
			
			}
			
			//Insertion Operation for KotakRequestReversal(RESponse)
			KotakReversalResponse kotkResponse=kotkrqres.getKotakReversalRes();
			if( kotkResponse!=null && ! kotkResponse.equals(""))
			{
				count=insertKotakReversalResponse(kotkResponse, RequestUId);
				int count2=updateInternalKotakReqResforPending(RequestUId,"NOT REQUIRED");
				
			   }
		}
		
	   }catch(Exception e)
	      {
		   
		   logger.error("DBConnectionKotak || insertKotakReversalRequestResponse() : Exception : " + e);
		
	            }
	      logger.info("DBConnectionKotak || insertKotakReversalRequestResponse() method : End");
		return count;
	}

	@Override
	public int insertKotakPaymentRequestResponse(KotakFULLRequestResponse kotkrqres) {
		logger.info("insertKotakPaymentRequestResponse() method : Start");
		int count =0;
		
		  try{
			if(kotkrqres!=null){
				//Insertion Operation for KotakReqRes
				KotakReqRes rq=kotkrqres.getKotkreqres();
				if(rq!=null && !rq.equals(""))
				{
				count = insertKotakRequest(rq);
				}
				
				//Insertion Operation for KotakPaymentRequest(REQUEST)
				KotakRequest kotakRequest =kotkrqres.getKotakPayReq();
				final Integer UID=count;
			    final Long RequestUId= Long.valueOf(UID);
				if( kotakRequest!=null && ! kotakRequest.equals(""))
				{
			     int count1=	insertkotakPaymentRequest(kotakRequest, RequestUId);
				 }
				
				//Insertion Operation for KotakPaymentRequest(RESponse)
				KotakResponse kotakResponse=kotkrqres.getKotakPayRes();
				if( kotakResponse!=null && ! kotakResponse.equals(""))
				{
					 insertKotakPaymentResponse(kotakResponse, RequestUId);
					 KotakReqRes Kotekreqres=kotkrqres.getKotkreqres();
					String response=getfailure(Kotekreqres,RequestUId);
					if(response.contains("Response failure from third party API") || response.contains("Internal Server Error")){
					  int count2=updateInternalKotakReqResforPending(RequestUId,"FAILURE");
					}
				   }
			}
			
		   }catch(Exception e)
		      { 
			   logger.error("DBConnectionKotak || insertKotakPaymentRequestResponse() : Exception : " + e);
			
		        }
		  logger.info("insertKotakPaymentRequestResponse() method : END");
		return count;
	}

	@Override
	public int insertkotakReversalRequest(KotakReversalPayload KotkRevPayload, Long UniqueId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
