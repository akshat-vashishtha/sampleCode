package com.qualtech.karza.api.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.ServiceMaster;
import com.qualtech.karza.api.common.dto.AadharRequest;
import com.qualtech.karza.api.common.dto.AddressRequest;
import com.qualtech.karza.api.common.dto.BankAccountRequest;
import com.qualtech.karza.api.common.dto.CAMemberShipAuthRequest;
import com.qualtech.karza.api.common.dto.CompSearchRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPCINLookUpRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPIdentificationRequest;
import com.qualtech.karza.api.common.dto.DlRequest;
import com.qualtech.karza.api.common.dto.DlRequest2;
import com.qualtech.karza.api.common.dto.EPFAuthOTPRequest;
import com.qualtech.karza.api.common.dto.EPFAuthPassBookRequest;
import com.qualtech.karza.api.common.dto.ESICIdRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest2;
import com.qualtech.karza.api.common.dto.EmailAuthRequest;
import com.qualtech.karza.api.common.dto.EmolpyerLookupRequest;
import com.qualtech.karza.api.common.dto.FSSAILicenceRequest;
import com.qualtech.karza.api.common.dto.Form16QuatRequest;
import com.qualtech.karza.api.common.dto.Form16Request;
import com.qualtech.karza.api.common.dto.GSTAuthenticationRequest;
import com.qualtech.karza.api.common.dto.GstIdentificationRequest;
import com.qualtech.karza.api.common.dto.HSNRequest;
import com.qualtech.karza.api.common.dto.ICSIMemberShipRequest;
import com.qualtech.karza.api.common.dto.ICWAIFirmAuthRequest;
import com.qualtech.karza.api.common.dto.ICWAIMembershipRequest;
import com.qualtech.karza.api.common.dto.IECRequest;
import com.qualtech.karza.api.common.dto.IFSCRequest;
import com.qualtech.karza.api.common.dto.ITRAuthRequest;
import com.qualtech.karza.api.common.dto.LpgIdentificationRequest;
import com.qualtech.karza.api.common.dto.LpgRequest;
import com.qualtech.karza.api.common.dto.LpgRequest2;
import com.qualtech.karza.api.common.dto.MCASignatureRequest;
import com.qualtech.karza.api.common.dto.NREGARequest;
import com.qualtech.karza.api.common.dto.NameSimilarityRequest;
import com.qualtech.karza.api.common.dto.PanRequest;
import com.qualtech.karza.api.common.dto.PassportRequest;
import com.qualtech.karza.api.common.dto.PngRequest;
import com.qualtech.karza.api.common.dto.RCAuthRequest;
import com.qualtech.karza.api.common.dto.RCSearchRequest;
import com.qualtech.karza.api.common.dto.ShopEstablishmentRequest;
import com.qualtech.karza.api.common.dto.TanRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest2;
import com.qualtech.karza.api.common.dto.UanLookupRequest;
import com.qualtech.karza.api.common.dto.UdyogAadharRequest;
import com.qualtech.karza.api.common.dto.VoterRequest;
import com.qualtech.karza.api.common.dto.WebsiteDomainRequest;
import com.qualtech.karza.api.request.AadharPayload;
import com.qualtech.karza.api.request.AddressPayload;
import com.qualtech.karza.api.request.BankAccountPayload;
import com.qualtech.karza.api.request.CAMemberShipAuthPayload;
import com.qualtech.karza.api.request.CompanyLLPIdentificationPayload;
import com.qualtech.karza.api.request.DlPayload;
import com.qualtech.karza.api.request.DlPayload2;
import com.qualtech.karza.api.request.EPFAuthOTPPayload;
import com.qualtech.karza.api.request.EPFAuthPassBookPayload;
import com.qualtech.karza.api.request.ESICIdPayload;
import com.qualtech.karza.api.request.ElectricalPayload;
import com.qualtech.karza.api.request.ElectricalPayload2;
import com.qualtech.karza.api.request.EmailAuthPayload;
import com.qualtech.karza.api.request.EmployerLookupPayload;
import com.qualtech.karza.api.request.FSSAILicencePayload;
import com.qualtech.karza.api.request.Form16Payload;
import com.qualtech.karza.api.request.Form16QuatPayload;
import com.qualtech.karza.api.request.GSTAuthenticationPayload;
import com.qualtech.karza.api.request.GstIdentificationPayload;
import com.qualtech.karza.api.request.HSNPayload;
import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.ICSIMemberShipPayload;
import com.qualtech.karza.api.request.ICWAIFirmAuthPayload;
import com.qualtech.karza.api.request.ICWAIMembershipPayload;
import com.qualtech.karza.api.request.IECPayload;
import com.qualtech.karza.api.request.IFSCPayload;
import com.qualtech.karza.api.request.KarzaFullReqRes;
import com.qualtech.karza.api.request.KarzaReqRes;
import com.qualtech.karza.api.request.KycOcrReqPayload;
import com.qualtech.karza.api.request.KycOcrRequest;
import com.qualtech.karza.api.request.LpgPayload2;
import com.qualtech.karza.api.request.NREGAPayload;
import com.qualtech.karza.api.request.PanPayload;
import com.qualtech.karza.api.request.PassportPayload;
import com.qualtech.karza.api.request.PngPayload;
import com.qualtech.karza.api.request.RCAuthPayload;
import com.qualtech.karza.api.request.RCSearchPayload;
import com.qualtech.karza.api.request.ShopEstablishmentPayload;
import com.qualtech.karza.api.request.TanPayload;
import com.qualtech.karza.api.request.TelephonePayload;
import com.qualtech.karza.api.request.TelephonePayload2;
import com.qualtech.karza.api.request.UanLookupPayload;
import com.qualtech.karza.api.request.UdyogAadharPayload;
import com.qualtech.karza.api.response.*;
@Transactional
@Repository
public class DBConnectionKarza implements DBKarza
{
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

	public int insertDlResponse(DlResponse res, Integer eID) {
		int count = 0;

		DlResponsePayload responsePayload = res.getPayload();
		if (responsePayload != null && !responsePayload.equals("")) {
			DlResult dlResult = responsePayload.getResult();
			if (dlResult != null && !dlResult.equals("")) {
				DlDrivingLicense dldrivinglicense = dlResult.getDl();
				if (dldrivinglicense != null && !dldrivinglicense.equals("")) {
					try {

						
						
						dldrivinglicense.setUniqueid("" + eID);
						dldrivinglicense.setByte_array(responsePayload.getByteArray());
						dldrivinglicense.setCorelationid(res.getHeader().getCorrelationId());
						String EID = (String) getSession().save(dldrivinglicense);
						
						
						
						count = Integer.parseInt(EID);
					} catch (Exception e) {
						logger.error("Error while inserting the dl response payload data query:"
								+ e);
					}
					logger.debug("Successfully closed Resources DBConnection in insertDlResponse v2 Method:- ");
				}
			}
		}
		logger.debug("Successfully closed Resources DBConnection in insertDlResponse  v2 Method:- ");
		return count;
	}
	
	
	
	
	public int insertDlRequest(DlPayload dlPayload) 
	{
		int count = 0;
		
		try 
		{
			String EID = (String) getSession().save(dlPayload);
			
			
			count = Integer.parseInt(EID);
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the dl request data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertDlRequest Method:- ");
		return count;
	}

	public int insertElectricalResponse(ElectricityResult electricityResult)
	{
		int count = 0;
		try 
		{
			
			
			String id=(String) getSession().save(electricityResult);
			
			count=Integer.parseInt(id);
			
		} 
		catch (Exception e) 
		{
			logger.error("Error while inserting the electrical response payload data query:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertElectricalResponse Method:- ");
		return count;

	}

	public int insertElectricalRequest(ElectricalPayload electricalPayload) {
		int count = 0;
		try {
			
			
			String id=(String) getSession().save(electricalPayload);
			
			
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the ELECTRICAL request data:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertElectricalRequest Method:- ");
		return count;
	}

	public int insertTeleResponse(TelResult telResult) {
		int count = 0;
		

		try {
			String id=(String) getSession().save(telResult);
			count=Integer.parseInt(id);
			
		} catch (Exception e) {
			logger.error("Error while inserting the telephone response data:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertTeleResponse Method:- ");
			
		return count;
	}

	
	public int insertTeleRequest(TelephonePayload telephonePayload) {
		int count = 0;
		
		try {
			String id= (String) getSession().save(telephonePayload);
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the TELE request data:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertTeleRequest Method:- ");
			
		return count;
	}

	

	
	public int insertKarzaRequestResponse(KarzaReqRes rq) {
		int count = 0;
		try {
		
			int id = Integer.parseInt(getSession().save(rq).toString());
			
			
			
			
			count = Integer.parseInt("" + id);
//			System.out.println("Request Id/EID "+id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the KARZA RES REQ data:" + e);
		}
		return count;
	}

	/*@Override
	public  AuthValidator validateAuth(Header header)
	{
		
		try 
		{
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			String formatted = format.format(cal.getTime());
			StringBuilder sbCover = new StringBuilder();
			sbCover.append("SELECT APPID,TOKEN,EXPIRYDATE FROM QCIB_AUTH_VALIDATION WHERE 1=1 AND STATUS='Y' ");
			sbCover.append(" AND APPID = '"+header.getAppId()+"'");
			sbCover.append(" AND TOKEN = '"+header.getToken()+"'");
			sbCover.append(" AND SERVICES = 'KARZA'");
			sbCover.append(" AND EXPIRYDATE >= '"+formatted+"'");
			
			Query query = getSession().createSQLQuery(sbCover.toString());
			AuthValidator validator=new AuthValidator();
			try
			{
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.list();
			
				List<Map<String, String>> result = this.getCustomClass(results);
				if (result != null && result.size() > 0)
				{

					Iterator it = results.iterator();
					while(it.hasNext())
					{
					     Object[] line =  (Object[]) it.next();
					     validator.setAppid(line[0].toString());
						validator.setToken(line[1].toString());
						break;
					}
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
		 }*/
	
	

	
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
//					System.out.println(results[1]);
					//AuthValidator results =  (AuthValidator) criteria.uniqueResult();
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
	


	public static List<Map<String , String>> getCustomClass(List<Object[]> objectList) 
	{
		List<Map<String , String>> pojoList = new ArrayList<Map<String , String>>();
		if(objectList != null && objectList.size()>0)
		{
			for( Object[] obj : objectList)
			{
				int key = 0;	
				Map<String , String> hashMap = new HashMap<String , String>();
				for(Object oo : obj)
				{
					hashMap.put("key"+key, (oo == null || oo == "")?"":oo.toString());
					key++;
				}
				pojoList.add(hashMap);	
			}
		}
		
		return pojoList;
	}
	

	public int insertDlResponse(DlResponse2 res, Integer eID) {

		int count = 0;
		try {
			DlResult2 dlResult2 = res.getPayload().getResult();
			dlResult2.setUniqueid("" + eID);
			dlResult2.setByte_array(res.getPayload().getByteArray());
			dlResult2.setCorelationid(res.getHeader().getCorrelationId());
			String EID = (String) getSession().save(dlResult2);
			
			
			
			count = Integer.parseInt(EID);

		} catch (Exception e) {
			logger.error("Error while inserting the dl response payload data query:"
					+ e);
		}
		logger.debug("Successfully closed Resources DBConnection in insertDlResponse Method:- ");

		/*CoverDetails[] cov_details = res.getPayload().getResult()
				.getCov_details();
		if (cov_details.length != 0) {
		for(CoverDetails coverdtl :cov_details){
			//coverdtl.setUniqueId(""+eID);
			String EID = (String) getSession().save(coverdtl);
		}

		}*/
		/*DlValidity dlvalidity = res.getPayload().getResult().getValidity();
		if (dlvalidity != null) {
			try {
				//dlvalidity.setUniqueId("" + eID);
				String eid = (String) getSession().save(dlvalidity);
				count = Integer.parseInt(eid);
			} catch (Exception e) {
				logger.error("Error while inserting the validity detail query:"
						+ e);
			}
			logger.debug("Successfully closed Resources DBConnection in insertDlResponse Method:- ");

		}*/
		return count;

	}

	
	public int insertElectricalResponse2(ElectricityResult2 electricityResult2) {

		int count = 0;
		try {
			
			
			String id=(String) getSession().save(electricityResult2);
			
			count=Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the electrical response payload data query:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertElectricalResponse2 Method:- ");
			
		return count;

	}

	public int insertTeleResponse2(TelResult2 telResult2) {

		int count = 0;

		try {
			
			
			String id=(String) getSession().save(telResult2);
			
			
			count=Integer.parseInt(id);
			
		} catch (Exception e) {
			logger.error("Error while inserting the telephone response data:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
			
		return count;

	}

	
	public int insertNregaResponse(NREGAResponse nregaRes, Integer eID) {
		int count = 0;
		try {
			NREGAResponsePayload nregaResponsePayload = nregaRes.getPayload();
			if (nregaResponsePayload != null && !nregaResponsePayload.equals("")) {
				NREGAResult nregaResult = nregaResponsePayload.getResult();
				if (nregaResult != null && !nregaResult.equals("")) {
					nregaResult.setUniqueId("" + eID);
					nregaResult.setCorrelation_Id(nregaRes.getHeader().getCorrelationId());

					getSession().save(nregaResult);

					/*List<NREGAIncomeDetail> nregaincomedetail = nregaRes.getPayload().getResult().getIncomeDetail();
					if (nregaincomedetail.size() != 0) {
						for (NREGAIncomeDetail nregaincome : nregaincomedetail) {

							try {
								nregaincome.setUniqueId("" + eID);
								getSession().save(nregaincome);

							} catch (Exception e) {
								logger.error("Error while executing query:" + e);
							}
						}
					}// ifloop
*/					/*List<NREGAApplicantDetail> applicantDetail = nregaRes.getPayload().getResult().getApplicantDetail();
					if (applicantDetail.size() != 0) {
						for (NREGAApplicantDetail nregaAplicantdetail : applicantDetail) {
							try {
								nregaAplicantdetail.setUniqueId("" + eID);
								getSession().save(nregaAplicantdetail);
							} catch (Exception e) {
								logger.error("Error while executing query:" + e);
							}
						}
					}*/
				}
			}
		} catch (Exception e) {
			logger.error("Error while inserting the NREGA response payload data query:"+ e);
		}
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return count;
	}

	public int insertTanResponse(TanResult tanResult) {
		int count = 0;

		try {

			String eid = (String) getSession().save(tanResult);
			
			
			count = Integer.parseInt(eid);
		} catch (Exception e) {
			logger.error("Error while inserting the TAN response payload data query:"+ e);
		}
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");

		return count;
	}
	

	public int insertPanResponse(PanResult panResult) {
		int count = 0;
		try {
			
			
			long count1=   (Long) getSession().save(panResult);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the PAN response payload data query:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertPanResponse Method:- ");
		return count;
	}


	public int insertFSSAILicenceResponse(FSSAILicenceResult fssaiLicenceResult) {
		int count = 0;
		try {
			
			
			String id=(String) getSession().save(fssaiLicenceResult);
			
			
			count=Integer.parseInt(id);
			
		} catch (Exception e) {
			logger.error("Error while inserting the FSSAI response payload data query:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertFSSAILicenceResponse Method:- ");
		return count;
	}

	public int insertPngResponse(PngResult pngResult) {
		int count = 0;
		
		try {
			String id=(String) getSession().save(pngResult);
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the PNG response payload data query:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertPngResponse Method:- ");
			
		return count;
	}

	public int insertPassportResponse(PassportResponse passportRes) {
		int count = 0;
		
		try {
			
			//passportRes.getPayload().getResult().setUniqueid(this.getSeqNextValue("QCIB_K_PASSPORT_RES_SQC"));
			passportRes.getPayload().getResult().setCorrelationid(passportRes.getHeader().getCorrelationId());
			
			String count1= (String) getSession().save(passportRes.getPayload().getResult());
		
			 count=Integer.parseInt(count1);
			
			} catch (Exception e) {
			logger.error("Error while inserting the PASSPORT response payload data query:" + e);
		} 
		return count;
	}



	public int insertIECRequestResponse(IECResult iecResult) {

		int count = 0;
		
		try {
		String count1=	(String) getSession().save(iecResult);
		
		
		count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the IECResponse response payload data query:" + e);
		}
		    logger.debug("Successfully closed Resources DBConnection in insertIECRequestResponse Method:- ");
			
		return count;
	}

	
	private String getStringByArray(String[] mbr) {

		String str = "";
		int i = 0;
		for (String s : mbr) {
			if (i != 0)
				s += "," + s;

			str += s.trim();
			i = 1;
		}
		return str;
	}

	public int insertRCAuthResponse(RCAuthResult rcAuthResult) {

		int count = 0;
		try {
			
			
			
			String id=(String) getSession().save(rcAuthResult);
			
			
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the form16QuatRes response payload data query:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertRCAuthResponse Method:- ");
		return count;

	}

	public int insertForm16QuatResponse(Form16QuatResultRecords form16QuatResultRecords) {
		int count = 0;
		
		try {
			String id=(String) getSession().save(form16QuatResultRecords);
			count=Integer.parseInt(id);
			
		} catch (Exception e) {
			logger.error("Error while inserting the form16QuatRes response payload data query:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertForm16QuatResponse Method:- ");
			

		return count;
	}

	public int insertESIC_IDResponse(ESICIdResult esicIdResult) {
		int count = 0;
		
		try 
		{
				String id=(String) getSession().save(esicIdResult);
				
				
					count=Integer.parseInt(id);
					
					/*if(count>0){
						
						try 
						{
							List<ESICIdResultContributionDetails> esicIdResultContributionDetails=esicIdResult.getContributionDetails();
							if(esicIdResultContributionDetails.size()!=0){
							for(ESICIdResultContributionDetails contribution:esicIdResultContributionDetails){
								try 
								{
									contribution.setUniqueId(""+count);
									contribution.setCorrelation_Id(esicIdResult.getCorrelation_Id());
								String id2=	(String) getSession().save(contribution);
								} 
								catch (Exception e)
								{
									logger.error("Error while executing QCIB_K_ESICID_CONT_DET_KYC_RES query:" + e);
								}
						  }
							}
						}
						
						catch (Exception e)
						{
							logger.error("Error while inserting the  QCIB_K_ESICID_CONT_DET_KYC_RES query:" + e);
						}
					}*/
					}
				 
				catch (Exception e)
				{
					logger.error("Error while executing QCIB_K_ESICI_KYC_RES query:" + e);
				}
			
		  
		
			logger.debug("Successfully closed Resources DBConnection in insertESIC_IDResponse Method:- ");
			
		return count;
	}


	
	public int insertDlRequest2(DlPayload2 dlPayload) {
		int count = 0;
		try {
			String Eid=(String) getSession().save(dlPayload);
			
			
			count=Integer.parseInt(Eid);
		} catch (Exception e) {
			logger.error("Error while inserting the DL request data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return count;
	}
	



	public int insertShopEstablishmentResponse(ShopEstablishmentResult establishmentResult) {
		int count = 0;
		try{
		String 	id=(String) getSession().save(establishmentResult);
		
		
		count=Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the  SHOPESTABLISHMENT response payload data query:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in insertShopEstablishmentResponse Method:- ");
		return count;
	}

	public static String NullCheck(String input) {
		return input == null ? "" : input;
	}

	public int insertForm16Response(Form16Result form16Result) {

		int count = 0;
		try {
			String id=(String) getSession().save(form16Result);
			
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the Form16 request data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertForm16Response Method:- ");
		return count;
	}

	public int insertPanRequest(PanPayload panPayload) {
		int count = 0;
		try 
		{
			count=   Integer.parseInt(getSession().save(panPayload).toString());
			
		} 
		catch (Exception e)
		{  e.printStackTrace();
			logger.error("Error while inserting the pan request data:" + e);
		}
		
			logger.debug("Successfully closed Resources DBConnection in insertPanRequest Method:- ");
			
		return count;
	
	}

	
	public int insertNregaRequest(NREGAPayload nregaPayload) {
		int count = 0;
		try {
			String eid = (String) getSession().save(nregaPayload);
			count = Integer.parseInt(eid);
		} catch (Exception e) {
			logger.error("Error while inserting the dl request data:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in insertNregaRequest Method:- ");

		return count;
	}

	public int insertESICIDAuthRequest(ESICIdPayload esicIdPayload) {
		int count = 0;
		try 
		{
			String id=(String) getSession().save(esicIdPayload);
			count=Integer.parseInt(id);
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the ESICID request data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertESICIDAuthRequest Method:- ");
		return count;
	}




	public int insertTanRequest(TanPayload tanPayload) {
     
		int count = 0;
		
		try {
			String count1=(String) getSession().save(tanPayload);
			
			
			count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the TAN response payload data query:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertTanRequest Method:- ");
		return count;
	}

	
	
	

	public int insertRcAuthRequest(RCAuthPayload rcAuthPayload) {

		int count = 0;
	
		try 
		{
			
			
			String id=(String) getSession().save(rcAuthPayload);
			
			
			count=Integer.parseInt(id);
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the rc_auth request data:" + e);
		}
		
			logger.debug("Successfully closed Resources DBConnection in insertRcAuthRequest Method:- ");
			
		return count;
	
	}


	public int insertShopEstablishmentRequest(ShopEstablishmentPayload establishmentPayload) {

		int count = 0;
		
		try 
		{
		String 	count1=(String) getSession().save(establishmentPayload);
		
		
		count=Integer.parseInt(count1);
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error("Error while inserting the shop_est request data:" + e);
		}
		
			
		logger.debug("Successfully closed Resources DBConnection in insertShopEstablishmentRequest Method:- ");
		return count;
	
	}

	

	public int insertPngRequest(PngPayload pngPayload) {
		int count = 0;
		try {
			String id=(String) getSession().save(pngPayload);
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the PNG request data:" + e);
		}
		   logger.debug("Successfully closed Resources DBConnection in insertPngRequest Method:- ");
			return count;
	
	}

	public int insertForm16authRequest(Form16Payload form16Payload) {
		int count = 0;

		try {
			String count1=(String) getSession().save(form16Payload);
			count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the FORM16AUTH request data:" + e);
		}
		    logger.debug("Successfully closed Resources DBConnection in insertForm16authRequest Method:- ");
		return count;
	
	}

	public int insertForm16QuatRequest(Form16QuatPayload form16QuatPayload) {
		int count = 0;
		try {
			String count1=(String) getSession().save(form16QuatPayload);
		    count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the FORM16QUATrequest data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertForm16QuatRequest Method:- ");
		return count;
	}

	

	public int insertFSSAILicenceRequest(FSSAILicencePayload fssaiLicencePayload) {
		int count = 0;
		try {
			
			
			String id=(String) getSession().save(fssaiLicencePayload);
			
			
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the FSSAILICENCE response payload data query:" + e);
		} 
			logger.debug("Successfully closed Resources DBConnection in insertFSSAILicenceRequest Method:- ");
			return count;
	}






	public int insertIECRequest(IECPayload iecPayload) {
		int count = 0;
	
		try {
		String count1=	(String) getSession().save(iecPayload);
		
		
		count=Integer.parseInt(count1);	
		} catch (Exception e) {
			logger.error("Error while inserting the IEC request data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertIECRequest Method:- ");
		return count;
	}

	

	public int insertElectricalRequest2(ElectricalPayload2 electricalPayload2) {
		int count = 0;
		try {
			
			
			String id=(String) getSession().save(electricalPayload2);
			
			
			count=Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the DL request data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertElectricalRequest2 Method:- ");
		return count;
	}

	public int insertTelephoneRequest2(TelephonePayload2 telephonePayload2) {
		int count = 0;
		try {
			
			
			String id=(String) getSession().save(telephonePayload2);
			
			
			count=Integer.parseInt(id);
		} catch (Exception e) {
			logger.error("Error while inserting the TELEPHONE request data:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertTelephoneRequest2 Method:- ");
			
		return count;
	}


	public int insertPassportRequest(PassportRequest passportRequest) {
		int count = 0;
	

		try {
			
			PassportPayload passportPayload =passportRequest.getPayload();
			if(passportPayload!=null){
				
			//passportPayload.setUniqueid(this.getSeqNextValue("QCIB_K_PASSPORT_REQ_SQC"));
			passportPayload.setCorelationid(passportRequest.getHeader().getCorrelationId());
				String count1= (String) getSession().save(passportPayload);
				
				 count=Integer.parseInt(count1);
			}
		} catch (Exception e) {
			logger.error("Error while inserting the pss request data:" + e);
		}
				logger.debug("Successfully closed Resources DBConnection in insertPassportRequest Method:- ");
		return count;
	}


	@Override
	public int insertNregaRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("NREGA Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("NREGA Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in NREGA Request Response "+ ec);
		}
		try {
			NREGARequest nregaRequest = fullReqRes.getNregaRequest();
			if (nregaRequest != null && !nregaRequest.equals("")) {
				NREGAPayload nregaPayload = nregaRequest.getPayload();
				if (nregaPayload != null && !nregaPayload.equals("")) {
					nregaPayload.setUniqueId("" + EID);
					nregaPayload.setCorelationid(nregaRequest.getHeader().getCorrelationId());
					logger.info("NREGA save in DB individually : Start");
					int count = insertNregaRequest(nregaPayload);
					logger.info("NREGA Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in NREGA Request "
					+ ec);
		}
		try {
			logger.info("NREGA Response save in DB individually : Start");
			NREGAResponse nregaResponse = fullReqRes.getNregaResponse();
			if (nregaResponse != null && !nregaResponse.equals("")) {
				int count = insertNregaResponse(nregaResponse,EID);
				logger.info("NREGA Response save in DB individually : " + count);
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in NREGA Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertTanRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("TAN Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("TAN Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in TAN Request Response "+ ec);
		}
		try {
			TanRequest tanRequest = fullReqRes.getTanRequest();
			if (tanRequest != null && !tanRequest.equals("")) {
				TanPayload tanPayload = tanRequest.getPayload();
				if (tanPayload != null && !tanPayload.equals("")) {
					tanPayload.setUniqueId("" + EID);
					tanPayload.setCorrelation_Id(tanRequest.getHeader().getCorrelationId());
					logger.info("TAN save in DB individually : Start");
					int count = insertTanRequest(tanPayload);
					logger.info("TAN Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in TAN Request "+ ec);
		}
		try {
			logger.info("TAN Response save in DB individually : Start");
			TanResponse  tanResponse = fullReqRes.getTanResponse();
			if (tanResponse != null && !tanResponse.equals("")) {
				TanResult tanResult=tanResponse.getPayload().getResult();
				tanResult.setUniqueId(""+EID);
				tanResult.setCorrelation_Id(tanResponse.getHeader().getCorrelationId());
				tanResult.setByte_Array(tanResponse.getPayload().getByteArray());
				int count = insertTanResponse(tanResult);
				logger.info("TAN Response save in DB individually : " + count);
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in TAN Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertDlRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("Dl2 Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("Dl2 Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in Dl2 Request Response "+ ec);
		}
		try {
			DlRequest2  dlRequest = fullReqRes.getDlRequest();
			if (dlRequest != null && !dlRequest.equals("")) {
				DlPayload2 dlPayload = dlRequest.getPayload();
				if (dlPayload != null && !dlPayload.equals("")) {
					dlPayload.setUniqueId("" + EID);
					dlPayload.setCorelationId(dlRequest.getHeader().getCorrelationId());
					logger.info("Dl2 save in DB individually : Start");
					int count = insertDlRequest2(dlPayload);
					logger.info("Dl2 Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in Dl2 Request "+ ec);
		}
		try {
			logger.info("Dl2 Response save in DB individually : Start");
			DlResponse2  dlResponse = fullReqRes.getDlRes();
			if (dlResponse != null && !dlResponse.equals("")) {
				int count =  insertDlResponse(dlResponse,EID) ;
				logger.info("Dl2 Response save in DB individually : " + count);
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in Dl2 Response "+ ec);
		}
		return EID;
	}
	@Override
	public int insertIECRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("IEC Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("IEC Request response save in DB : End : " + EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in IEC Request Response "+ ec);
		}
		try {
			IECRequest iecRequest  = fullReqRes.getIecRequest();
			if (iecRequest != null && !iecRequest.equals("")) {
				IECPayload iecPayload = iecRequest.getPayload();
				if (iecPayload != null && !iecPayload.equals("")) {
					iecPayload.setUniqueId("" + EID);
					iecPayload.setCorelationId(iecRequest.getHeader().getCorrelationId());
					logger.info("IEC save in DB individually : Start");
					int count =insertIECRequest(iecPayload);
					logger.info("IEC Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in IEC Request "+ ec);
		}
		try {
			logger.info("IEC Response save in DB individually : Start");
			IECResponse iecResponse   = fullReqRes.getIecResponse();
			if (iecResponse != null && !iecResponse.equals("")) {
				IECResponsePayload iecResponsePayload= iecResponse.getPayload();
				if (iecResponsePayload != null && !iecResponsePayload.equals("")) {
					IECResult iecResult=iecResponsePayload.getResult();
					if(iecResult != null && !iecResult.equals(""))
					iecResult.setUniqueId("" + EID);
					iecResult.setCorrelation_Id(iecResponse.getHeader().getCorrelationId());
					logger.info("IEC save in DB individually : Start");
					int count =insertIECRequestResponse(iecResult);
					logger.info("IEC Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in IEC Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertForm16RequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("Form16 Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("Form16 Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in Form16 Request Response "+ ec);
		}
		try {
			Form16Request form16Request = fullReqRes.getForm16Request();
			if (form16Request != null && !form16Request.equals("")) {
				Form16Payload form16Payload= form16Request.getPayload();
				if (form16Payload != null && !form16Payload.equals("")) {
					form16Payload.setUniqueId("" + EID);
					form16Payload.setCorelationId(form16Request.getHeader().getCorrelationId());
					logger.info("Form16 save in DB individually : Start");
					int count =insertForm16authRequest(form16Payload);
					logger.info("Form16 Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in Form16 Request "+ ec);
		}
		try {
			logger.info("Form16 Response save in DB individually : Start");
			Form16Response form16Response = fullReqRes.getForm16Response();
			if (form16Response != null && !form16Response.equals("")) {
				Form16ResponsePayload form16ResponsePayload= form16Response.getPayload();
				if (form16ResponsePayload != null && !form16ResponsePayload.equals("")) {
					Form16Result form16Result=form16ResponsePayload.getResult();
					if(form16Result != null && !form16Result.equals(""))
					form16Result.setUniqueId("" + EID);
					form16Result.setCorelationId(form16Response.getHeader().getCorrelationId());
					logger.info("Form16 save in DB individually : Start");
					int count =insertForm16Response(form16Result);
					logger.info("Form16 Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in Form16 Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertForm16QuatRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("form16Quat Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("form16Quat Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in form16Quat Request Response "+ ec);
		}
		try {
			Form16QuatRequest form16QuatRequest= fullReqRes.getForm16QuatRequest();
			if (form16QuatRequest != null && !form16QuatRequest.equals("")) {
				Form16QuatPayload form16QuatPayload = form16QuatRequest.getPayload();
				if (form16QuatPayload != null && !form16QuatPayload.equals("")) {
					form16QuatPayload.setUniqueId("" + EID);
					form16QuatPayload.setCorelationId(form16QuatRequest.getHeader().getCorrelationId());
					logger.info("form16Quat save in DB individually : Start");
					int count =insertForm16QuatRequest(form16QuatPayload);
					logger.info("form16Quat Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in form16Quat Request "+ ec);
		}
		try {
			logger.info("form16Quat Response save in DB individually : Start");
			Form16QuatResponse form16QuatResponse= fullReqRes.getForm16QuatResponse();
			if (form16QuatResponse != null && !form16QuatResponse.equals("")) {
				Form16QuatResponsePayload form16QuatResponsePayload= form16QuatResponse.getPayload();
				if (form16QuatResponsePayload != null && !form16QuatResponsePayload.equals("")) {
					Form16QuatResult form16QuatResult =form16QuatResponsePayload.getResult();
					if(form16QuatResult != null && !form16QuatResult.equals("")){
					Form16QuatResultRecords form16QuatResultRecords=form16QuatResult.getQuarterly_records_count_for_next_fiscal();
					if(form16QuatResultRecords!=null && !form16QuatResultRecords.equals("")){
						
						form16QuatResultRecords.setUniqueId("" + EID);
						form16QuatResultRecords.setCorrelation_Id(form16QuatResponse.getHeader().getCorrelationId());
						logger.info("form16Quat save in DB individually : Start");
						int count =insertForm16QuatResponse(form16QuatResultRecords);
						logger.info("form16Quat Request save in DB individually : "+ count);	
						
					}
					}
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in form16Quat Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertPngRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("PNG Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("PNG Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in PNG Request Response "+ ec);
		}
		try {
			PngRequest pngRequest= fullReqRes.getPngRequest();
			if (pngRequest != null && !pngRequest.equals("")) {
				PngPayload pngPayload= pngRequest.getPayload();
				if (pngPayload != null && !pngPayload.equals("")) {
					pngPayload.setUniqueId("" + EID);
					pngPayload.setCorelationId(pngRequest.getHeader().getCorrelationId());
					logger.info("PNG save in DB individually : Start");
					int count = insertPngRequest(pngPayload);
					logger.info("PNG Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in PNG Request "+ ec);
		}
		try {
			logger.info("PNG Response save in DB individually : Start");
			PngResponse pngResponse = fullReqRes.getPngResponse();
			if (pngResponse != null && !pngResponse.equals("")) {
				PngResponsePayload pngResponsePayload= pngResponse.getPayload();
				if ( pngResponsePayload!= null && !pngResponsePayload.equals("")) {
					PngResult pngResult=pngResponsePayload.getResult();
					if(pngResult != null && !pngResult.equals(""))
					pngResult.setUniqueId("" + EID);
					pngResult.setCorrelation_Id(pngResponse.getHeader().getCorrelationId());
					logger.info("PNG save in DB individually : Start");
					int count =insertPngResponse(pngResult);
					logger.info("PNG Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in PNG Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertEsicIdRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("ESICID Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("ESICID Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in ESICID Request Response "+ ec);
		}
		try {
			ESICIdRequest esicIdRequest = fullReqRes.getEsicIdRequest();
			if (esicIdRequest != null && !esicIdRequest.equals("")) {
				ESICIdPayload esicIdPayload= esicIdRequest.getPayload();
				if (esicIdPayload != null && !esicIdPayload.equals("")) {
					esicIdPayload.setUniqueId("" + EID);
					esicIdPayload.setCorelationId(esicIdRequest.getHeader().getCorrelationId());
					logger.info("ESICID save in DB individually : Start");
					int count = insertESICIDAuthRequest(esicIdPayload);
					logger.info("ESICID Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ESICID Request "+ ec);
		}
		try {
			logger.info("ESICID Response save in DB individually : Start");
			ESICIdResponse esicIdResponse = fullReqRes.getEsicIdResponse();
			if (esicIdResponse != null && !esicIdResponse.equals("")) {
				ESICIdResponsePayload esicIdResponsePayload= esicIdResponse.getPayload();
				if ( esicIdResponsePayload!= null && !esicIdResponsePayload.equals("")) {
					ESICIdResult esicIdResult=esicIdResponsePayload.getResult();
					if(esicIdResult != null && !esicIdResult.equals(""))
					esicIdResult.setUnique_Id("" + EID);
					esicIdResult.setCorrelation_Id(esicIdResponse.getHeader().getCorrelationId());
					logger.info("ESICID save in DB individually : Start");
					int count =insertESIC_IDResponse(esicIdResult);
					logger.info("ESICID Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ESICID Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertTeleRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("TELE Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("TELE Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in TELE Request Response "+ ec);
		}
		try { 
			TelephoneRequest telephoneRequest = fullReqRes.getTeleRequest();
			if (telephoneRequest != null && !telephoneRequest.equals("")) {
				TelephonePayload telephonePayload = telephoneRequest.getPayload();
				if (telephonePayload != null && !telephonePayload.equals("")) {
					telephonePayload.setUniqueId("" + EID);
					telephonePayload.setCorelationId(telephoneRequest.getHeader().getCorrelationId());
					logger.info("TELE save in DB individually : Start");
					int count = insertTeleRequest(telephonePayload);
					logger.info("TELE Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in TELE Request "+ ec);
		}
		try {
			logger.info("TELE Response save in DB individually : Start");
			TelephoneResponse telephoneResponse= fullReqRes.getTeleRes();
			if (telephoneResponse != null && !telephoneResponse.equals("")) {
				TelResponsePayload telResponsePayload = telephoneResponse.getPayload();
				if ( telResponsePayload!= null && !telResponsePayload.equals("")) {
					TelResult telResult=telResponsePayload.getResult();
					if(telResult != null && !telResult.equals(""))
						telResult.setUniqueId("" + EID);
					    telResult.setCorelationId(telephoneResponse.getHeader().getCorrelationId());
					    telResult.setByte_Array(telResponsePayload.getByteArray());
					logger.info("TELE save in DB individually : Start");
					int count =insertTeleResponse(telResult);
					logger.info("TELE Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in TELE Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertElectricalRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("ELECTRICAL Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("ELECTRICAL Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in ELECTRICAL Request Response "+ ec);
		}
		try { 
			ElectricalRequest electricalRequest = fullReqRes.getElecRequest();
			if (electricalRequest != null && !electricalRequest.equals("")) {
				ElectricalPayload electricalPayload= electricalRequest.getPayload();
				if (electricalPayload != null && !electricalPayload.equals("")) {
					electricalPayload.setUniqueId("" + EID);
					electricalPayload.setCorelationId(electricalRequest.getHeader().getCorrelationId());
					logger.info("ELECTRICAL save in DB individually : Start");
					int count = insertElectricalRequest(electricalPayload);
					logger.info("ELECTRICAL Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ELECTRICAL Request "+ ec);
		}
		try {
			logger.info("ELECTRICAL Response save in DB individually : Start");
			ElectricityResponse electricityResponse= fullReqRes.getElecRes();
			if (electricityResponse != null && !electricityResponse.equals("")) {
				ElectricityResponsePayload electricityResponsePayload= electricityResponse.getPayload();
				if ( electricityResponsePayload!= null && !electricityResponsePayload.equals("")) {
					ElectricityResult electricityResult=electricityResponsePayload.getResult();
					if(electricityResult != null && !electricityResult.equals(""))
					electricityResult.setUniqueId("" + EID);
					electricityResult.setCorelationId(electricityResponse.getHeader().getCorrelationId());
					electricityResult.setByte_Array(electricityResponsePayload.getByteArray());
					logger.info("ELECTRICAL save in DB individually : Start");
					int count =insertElectricalResponse(electricityResult);
					logger.info("ELECTRICAL Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ELECTRICAL Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertFSSAILicenceRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("FSSAI Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("FSSAI Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in FSSAI Request Response "+ ec);
		}
		try { 
			FSSAILicenceRequest fssaiLicenceRequest=fullReqRes.getFssaiLicenceRequest();
			if (fssaiLicenceRequest != null && !fssaiLicenceRequest.equals("")) {
				FSSAILicencePayload fssaiLicencePayload= fssaiLicenceRequest.getPayload();
				if (fssaiLicencePayload != null && !fssaiLicencePayload.equals("")) {
					fssaiLicencePayload.setUniqueId("" + EID);
					fssaiLicencePayload.setCorrelation_Id(fssaiLicenceRequest.getHeader().getCorrelationId());
					logger.info("FSSAI save in DB individually : Start");
					int count = insertFSSAILicenceRequest(fssaiLicencePayload);
					logger.info("FSSAI Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in FSSAI Request "+ ec);
		}
		try {
			logger.info("FSSAI Response save in DB individually : Start");
			FSSAILicenceResponse fssaiLicenceResponse= fullReqRes.getFssaiLicenceResponse();
			if (fssaiLicenceResponse != null && !fssaiLicenceResponse.equals("")) {
				FSSAILicenceResponsePayload fssaiLicenceResponsePayload= fssaiLicenceResponse.getPayload();
				if ( fssaiLicenceResponsePayload!= null && !fssaiLicenceResponsePayload.equals("")) {
					FSSAILicenceResult fssaiLicenceResult=fssaiLicenceResponsePayload.getResult();
					if(fssaiLicenceResult != null && !fssaiLicenceResult.equals(""))
					fssaiLicenceResult.setUniqueId("" + EID);
					fssaiLicenceResult.setCorrelation_Id(fssaiLicenceResponse.getHeader().getCorrelationId());
					logger.info("FSSAI save in DB individually : Start");
					int count =insertFSSAILicenceResponse(fssaiLicenceResult);
					logger.info("FSSAI Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in FSSAI Response "
					+ ec);
		}
		return EID;
	}
	@Override
	public int insertRCAuthRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("RC_AUTH Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("RC_AUTH Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in RC_AUTH Request Response "+ ec);
		}
		try { 
			RCAuthRequest rcAuthRequest =fullReqRes.getRcAuthRequest();
			if (rcAuthRequest != null && !rcAuthRequest.equals("")) {
				RCAuthPayload rcAuthPayload= rcAuthRequest.getPayload();
				if (rcAuthPayload != null && !rcAuthPayload.equals("")) {
					rcAuthPayload.setUniqueId("" + EID);
					rcAuthPayload.setCorelationId(rcAuthRequest.getHeader().getCorrelationId());
					logger.info("RC_AUTH save in DB individually : Start");
					int count = insertRcAuthRequest(rcAuthPayload);
					logger.info("RC_AUTH Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in RC_AUTH Request "+ ec);
		}
		try {
			logger.info("RC_AUTH Response save in DB individually : Start");
			RCAuthResponse rcAuthResponse= fullReqRes.getRcAuthResponse();
			if (rcAuthResponse != null && !rcAuthResponse.equals("")) {
				RCAuthResponsePayload rcAuthResponsePayload= rcAuthResponse.getPayload();
				if ( rcAuthResponsePayload!= null && !rcAuthResponsePayload.equals("")) {
					RCAuthResult rcAuthResult=rcAuthResponsePayload.getResult();
					if(rcAuthResult != null && !rcAuthResult.equals(""))
					rcAuthResult.setUniqueId("" + EID);
					rcAuthResult.setCorrelation_Id(rcAuthResponse.getHeader().getCorrelationId());
					logger.info("RC_AUTH save in DB individually : Start");
					int count =insertRCAuthResponse(rcAuthResult);
					logger.info("RC_AUTH Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in RC_AUTH Response "
					+ ec);
		}
		return EID;
	}

	@Override
	public int insertShopEstablishmentReqRes(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("ShopEstablishment Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("ShopEstablishment Request response save in DB : End : "+ EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in ShopEstablishment Request Response "+ ec);
		}

		try {
			ShopEstablishmentRequest establishmentRequest = fullReqRes.getShopEstablishmentRequest();
			if (establishmentRequest != null && !establishmentRequest.equals("")) {
				ShopEstablishmentPayload establishmentPayload = establishmentRequest.getPayload();
				if (establishmentPayload != null && !establishmentPayload.equals("")) {
					establishmentPayload.setUniqueid("" + EID);
					establishmentPayload.setCorelationId(establishmentRequest.getHeader().getCorrelationId());
					logger.info("ShopEstablishment save in DB individually : Start");
					int count = insertShopEstablishmentRequest(establishmentPayload);
					logger.info("ShopEstablishment Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ShopEstablishment Request "+ ec);
		}
		try {
			logger.info("ShopEstablishment Response save in DB individually : Start");
			ShopEstablishmentResponse establishmentResponse = fullReqRes.getShopEstablishmentResponse();
			if (establishmentResponse != null && !establishmentResponse.equals("")) {
				ShopEstablishmentResponsePayload shopEstablishmentResponsePayload = establishmentResponse.getPayload();
				if (shopEstablishmentResponsePayload != null&& !shopEstablishmentResponsePayload.equals("")) {
					ShopEstablishmentResult shopEstablishmentResult = shopEstablishmentResponsePayload.getResult();
					if (shopEstablishmentResult != null&& !shopEstablishmentResult.equals(""))
						shopEstablishmentResult.setUniqueid("" + EID);
					    shopEstablishmentResult.setCorrelation_id(establishmentResponse.getHeader().getCorrelationId());
					logger.info("ShopEstablishment save in DB individually : Start");
					int count = insertShopEstablishmentResponse(shopEstablishmentResult);
					logger.info("ShopEstablishment Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ShopEstablishment Response "+ ec);
		}
		return EID;
	}



	@Override
	public int insertElectrical2RequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("ELECTRICAL2 Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("ELECTRICAL2 Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in ELECTRICAL2 Request Response "+ ec);
		}
		try { 
			ElectricalRequest2 electricalRequest2 = fullReqRes.getElecRequest2();
			if (electricalRequest2 != null && !electricalRequest2.equals("")) {
				ElectricalPayload2 electricalPayload2= electricalRequest2.getPayload();
				if (electricalPayload2 != null && !electricalPayload2.equals("")) {
					electricalPayload2.setUniqueId("" + EID);
					electricalPayload2.setCorelationId(electricalRequest2.getHeader().getCorrelationId());
					logger.info("ELECTRICAL2 save in DB individually : Start");
					int count =insertElectricalRequest2(electricalPayload2);
					logger.info("ELECTRICAL2 Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ELECTRICAL2 Request "+ ec);
		}
		try {
			logger.info("ELECTRICAL2 Response save in DB individually : Start");
			ElectricityResponse2 electricityResponse2= fullReqRes.getElecRes2();
			if (electricityResponse2 != null && !electricityResponse2.equals("")) {
				ElectricityResponsePayload2 electricityResponsePayload2= electricityResponse2.getPayload();
				if ( electricityResponsePayload2!= null && !electricityResponsePayload2.equals("")) {
					ElectricityResult2 electricityResult2=electricityResponsePayload2.getResult();
					if(electricityResult2 != null && !electricityResult2.equals(""))
					electricityResult2.setUniqueId("" + EID);
					electricityResult2.setCorelationId(electricityResponse2.getHeader().getCorrelationId());
					electricityResult2.setByte_Array(electricityResponsePayload2.getByteArray());
					logger.info("ELECTRICAL2 save in DB individually : Start");
					int count =insertElectricalResponse2(electricityResult2);
					logger.info("ELECTRICAL2 Request save in DB individually : "+ count);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in ELECTRICAL2 Response "
					+ ec);
		}
		return EID;
	}



	@Override
	public int insertPanRequestResponse(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("PAN Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("PAN Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in PAN Request Response "+ ec);
		}
		try {
			PanRequest panRequest = fullReqRes.getPanRequest();
			if (panRequest != null && !panRequest.equals("")) {
				PanPayload panPayload = panRequest.getPayload();
				if (panPayload != null && !panPayload.equals("")) {
					panPayload.setUniqueid(EID);
					panPayload.setCorelationid(panRequest.getHeader().getCorrelationId());
					logger.info("PAN save in DB individually : Start");
					int count = insertPanRequest(panPayload);
					logger.info("PAN Request save in DB individually : "+ EID);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in PAN Request "+ ec);
		}
		try {
			logger.info("PAN Response save in DB individually : Start");
			PanResponse  panResponse = fullReqRes.getPanRes();
			if (panResponse != null && !panResponse.equals("")) {
				PanResponsePayload panResponsePayload=panResponse.getPayload();
				if(panResponsePayload!=null && !panResponse.equals("")){
					PanResult panResult=panResponsePayload.getResult();
					panResult.setUniqueid(Long.valueOf(EID));
					panResult.setCorrelation_id(panResponse.getHeader().getCorrelationId());
				    insertPanResponse(panResult);
				logger.info("PAN Response save in DB individually : " + EID);
			}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in PAN Response "
					+ ec);
		}
		return EID;
	}



	@Override
	public int insertTeleRequestResponse2(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("TELE2 Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("TELE2 Request response save in DB : End : " + EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in TELE2 Request Response "+ ec);
		}
		try { 
			TelephoneRequest2 telephoneRequest2 = fullReqRes.getTeleRequest2();
			if (telephoneRequest2 != null && !telephoneRequest2.equals("")) {
				TelephonePayload2 telephonePayload2 = telephoneRequest2.getPayload();
				if (telephonePayload2 != null && !telephonePayload2.equals("")) {
					telephonePayload2.setUniqueId("" + EID);
					telephonePayload2.setCorelationId(telephoneRequest2.getHeader().getCorrelationId());
					logger.info("TELE2 save in DB individually : Start");
					int count = insertTelephoneRequest2(telephonePayload2);
					logger.info("TELE2 Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in TELE2 Request "+ ec);
		}
		try {
			logger.info("TELE2 Response save in DB individually : Start");
			TelephoneResponse2 telephoneResponse2= fullReqRes.getTeleRes2();
			if (telephoneResponse2 != null && !telephoneResponse2.equals("")) {
				TelResponsePayload2 telResponsePayload2 = telephoneResponse2.getPayload();
				if ( telResponsePayload2!= null && !telResponsePayload2.equals("")) {
					TelResult2 telResult2=telResponsePayload2.getResult();
					if(telResult2 != null && !telResult2.equals(""))
						telResult2.setUniqueId("" + EID);
					    telResult2.setCorelationId(telephoneResponse2.getHeader().getCorrelationId());
					    telResult2.setByte_Array(telResponsePayload2.getByteArray());
					logger.info("TELE2 save in DB individually : Start");
					int count =insertTeleResponse2(telResult2);
					logger.info("TELE2 Request save in DB individually : " /*count*/);
				}
				}
		} catch (Exception ec) {
			logger.error("There is error while saving data in TELE2 Response "
					+ ec);
		}
		return EID;
	}



	@Override
	public int insertDlReqRes(KarzaFullReqRes fullReqRes) {
		Integer EID = null;
		try {
			KarzaReqRes rq = fullReqRes.getReqRes();
			logger.info("Dl Request response save in DB : Start");
			EID = insertKarzaRequestResponse(rq);
			logger.info("Dl Request response save in DB : End : " + EID);
			final Long UId = Long.valueOf(EID);
		} catch (Exception ec) {
			logger.error("There is error while saving data in Dl Request Response "+ ec);
		}
		try {
			DlRequest  dlRequest = fullReqRes.getDlRequest1();
			if (dlRequest != null && !dlRequest.equals("")) {
				DlPayload dlPayload = dlRequest.getPayload();
				if (dlPayload != null && !dlPayload.equals("")) {
					dlPayload.setUniqueid("" + EID);
					dlPayload.setCorelationid(dlRequest.getHeader().getCorrelationId());
					logger.info("Dl save in DB individually : Start");
					int count = insertDlRequest(dlPayload);
					logger.info("Dl Request save in DB individually : "+ count);
				}
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in Dl2 Request "+ ec);
		}
		try {
			logger.info("Dl Response save in DB individually : Start");
			DlResponse  dlResponse = fullReqRes.getDlRes1();
			if (dlResponse != null && !dlResponse.equals("")) {
				int count =  insertDlResponse(dlResponse,EID) ;
				logger.info("Dl Response save in DB individually : ");
			}
		} catch (Exception ec) {
			logger.error("There is error while saving data in Dl Response "+ ec);
		}
		return EID;
	}



/*	public String getSeqNextValue(String seqName) 
	{try
	{
		Query query = getSession().createSQLQuery("select "+seqName+".NEXTVAL from DUAL");
		return query.uniqueResult().toString();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.error("We are exception while tring to get next sequence : "+e);
	}
	return null;
	}
*/

	public static java.sql.Date stringToSqlDateFormat(String strDate) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = null;
		try {
			date = sdf1.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlStartDate = (java.sql.Date) new java.sql.Date(date.getTime());
		return sqlStartDate;
	}


	public int insertUdyogAadharResponse(UdyogAadharResult udyogAadharResult) {
		int count = 0;
		try {
			String count1=(String) getSession().save(udyogAadharResult);
			
			
		    count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the UDHYOGAADHAR response payload data query:" + e);
		} 
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return count;
	}

	public int insertPanResponse(PanResponse panres) {
		int count = 0;
		
		try {
			
			//panres.getPayload().getResult().setUniqueid(this.getSeqNextValue("QCIB_K_PAN_RES_SQC"));
			panres.getPayload().getResult().setCorrelation_id(panres.getHeader().getCorrelationId());
			String count1= (String) getSession().save(panres.getPayload().getResult());
			 count=Integer.parseInt(count1);
			
		} catch (Exception e) {
			logger.error("Error while inserting the PAN response payload data query:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertPanResponse Method:- ");
			

		return count;
	}


	public int insertPassportResponse(PassportResult passportResult) {
		int count = 0;

		try {
			   
	          String count1= (String) getSession().save(passportResult);
	          
	          
				
			 count=Integer.parseInt(count1);
			
			} catch (Exception e) {
			logger.error("Error while inserting the PASSPORT response payload data query:" + e);
		}
			logger.debug("Successfully closed Resources DBConnection in insertPassportResponse Method:- ");
			
		return count;
	}

	public int insertAadharResponse(AadharResult aadharResult) {
		int count = 0;
		try {
			
			Transaction tx=getSession().getTransaction();
			String count1 = (String) getSession().save(aadharResult);
			tx.commit();
			tx.begin();
			count = Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the AADHAR response payload data query:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in insertAadharResponse Method:- ");
		return count;
	}

	public int insertGSTAuthenticationResponse(GSTAuthenticationResult gstAuthenticationResult) {

		int count = 0;
		
		try {
			
			
			String count1= (String) getSession().save(gstAuthenticationResult);
			
			
			count=Integer.parseInt(count1);
          } catch (Exception e) {
			
			logger.error("Error while inserting the GSTAuthenticationResult response payload data query:" + e);
		}
		try {
			GSTAuthenticationPRADR gstAuthenticationPRADR=gstAuthenticationResult.getPradr();
			
			gstAuthenticationPRADR.setUniqueid_of_res(count);
			 getSession().save(gstAuthenticationPRADR);
			
			} catch (Exception e) {
			
			logger.error("Error while inserting the gstAuthenticationPRADR response payload data query:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return count;

	}

	

	public int insertGstIdentificationResponse(GstIdentificationResult gstIdentificationResult) {

		int count = 0;
		try {
			
			String count1=(String) getSession().save(gstIdentificationResult);
			
			
			count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the ADDRESS response payload data query:" + e);
		} 
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return count;
	}

	public int insertEmailAuthResponse(EmailAuthResult emailAuthResult) {
		int count = 0;
	
		try {
	
			count=Integer.parseInt(getSession().save(emailAuthResult).toString());
			
			
			
			} catch (Exception e) {
				e.printStackTrace();
			logger.error("Error while inserting the EmailAuth RES REQ data:" + e);
		} 
		return count;
	}

	/*public int insertEmailAuthResponse(EmailAuthResult emailAuthResult) {
		int countFirst = 0;
		int countSecond = 0;
	
		try {
			EmailAuthParam authParam=emailAuthResult.getMeta().getParams();
			String count1 =(String) getSession().save(authParam);
			countFirst=Integer.parseInt(count1);
			
			if(countFirst>0)
			{   
				EmailAuthData  emailauthdata=emailAuthResult.getData();
				emailauthdata.setEid(count1);
			    emailauthdata.setSequenceid(this.getSeqNextValue("QCIB_K_EMAILAUTH_DATA_SQC"));
				
				String count2 =(String) getSession().save(emailauthdata);
				
			    countSecond=Integer.parseInt(count2);
			}
			} catch (Exception e) {
			logger.error("Error while inserting the EmailAuth RES REQ data:" + e);
		} 
		return countSecond;
	}*/

	public int insertITRAuthResponse(ITRAuthResponse itrAuthResponse) {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try 		     
		{    ITRAuthResult itrauthresult= itrAuthResponse.getPayload().getResult();
		       // itrauthresult.setEid(this.getSeqNextValue("IB_K_ITRAUTH_SQC"));
		        itrauthresult.setCorelationid(itrAuthResponse.getHeader().getCorrelationId());
		        String count1=(String) getSession().save(itrauthresult);
		        
		        
		        count=Integer.parseInt(count1);
			 
			} catch (Exception e) {
			logger.error("Error while inserting the ITRAUTH RES REQ data:" + e);
		} 
		return count;
	}


	public int insertCAMemberShipAuthResponse(CAMemberShipAuthResult caMemberShipAuthResult) {

		int count = 0;
		
		try {
			String count1=(String) getSession().save(caMemberShipAuthResult);
			
			
			count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the  CAMEMBERSHIPAUTH response payload data query:" + e);
		} 		return count;

	}

	

	public int insertLLPIdentificationResponse(CompanyLLPIdentificationResult companyLLPIdentificationResult) {
		int count = 0;
;
		try {
			String count1=(String) getSession().save(companyLLPIdentificationResult);
			
			
			count=Integer.parseInt(count1);
			
			
		} catch (Exception e) {
			logger.error("Error while inserting the LLPIDENTIFICATION response payload data query:" + e);
		} 		return count;
	}

	public int insertEPFAuthOTPResponse(EPFAuthOTPResult epfAuthOTPResult) {
		int count = 0;
			try {
			    String count1=(String) getSession().save(epfAuthOTPResult);
			    
			    
				count=Integer.parseInt(count1);
				
			} catch (Exception e) {
			logger.error("Error while inserting the EPFAUTHOTP response payload data query:" + e);
		} 		return count;
	}

	public int insertICSIMemberShipResponse(ICSIMemberShipResult icsiMemberShipResult2) {

		int count = 0;
		
		try {
			
			String count1=(String) getSession().save(icsiMemberShipResult2);
			
			
			count=Integer.parseInt(count1);
			} catch (Exception e) {
			logger.error("Error while inserting the ICSIMEMBERSHIP response payload data query:" + e);
		} 
		return count;
	}

	

	public int insertICWAIFirmAuthResponse(ICWAIFirmAuthResult icwaiFirmAuthResult) {

		int count = 0;
		
		try {
			/*for(ICWAIFirmAuthMemberDetails icwaiFirmAuthMemberDetails:icwaiFirmAuthResult.getMemberDetails())
			{
			   icwaiFirmAuthMemberDetails.setSequenceid(this.getSeqNextValue("IB_K_ICWAIFirm_KYC_SQC"));
			   }*/
		
			String count1=(String) getSession().save(icwaiFirmAuthResult);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		logger.error("Error while inserting the ICWAIFIRMAUTH response payload data query:"
				+ e);
	}

		/*try {
		String count1=(String) getSession().save(icwaiFirmAuthResult);
		count=Integer.parseInt(count1);
		
		for(ICWAIFirmAuthMemberDetails icwaiFirmAuthMemberDetails:icwaiFirmAuthResult.getMemberDetails())
		{
		   icwaiFirmAuthMemberDetails.setSequenceid(this.getSeqNextValue("QCIB_K_ICWAIFirm_KYC_SQC"));
			icwaiFirmAuthMemberDetails.setUniqueid(count1);
		
		
			getSession().save(icwaiFirmAuthMemberDetails);
		}
		
			} catch (Exception e) {
			logger.error("Error while inserting the ICWAIFIRMAUTH response payload data query:"
					+ e);
		}
*/		
		return count;

	}

	public int insertHSNRequestProcess(HSNResult hsnResult) {

		int count = 0;

		try {
			String count1=(String) getSession().save(hsnResult);
			
			
			count=Integer.parseInt(count1);
			
		} catch (Exception e) {
			logger.error("Error while inserting the HSN request data:" + e);
		}
		return count;
	}

	public int insertIFSCRequestProcess(IFSCResult ifscResult) {
		int count = 0;

		try {
			
			
			String count1=(String) getSession().save(ifscResult);
			
            
			count=Integer.parseInt(count1);
			
			} catch (Exception e) {
			logger.error("Error while inserting the LPG request data:" + e);
		}
		return count;

	}


	

	public int insertPanRequest(PanRequest panRequest) {

		int count = 0;
		
		try 
		{
			
			
		//panRequest.getPayload().setUniqueid(this.getSeqNextValue("QCIB_K_PAN_REQ_SQC"));
			
			panRequest.getPayload().setCorelationid(panRequest.getHeader().getCorrelationId());
			
			String count1= (String) getSession().save(panRequest.getPayload());
			
			 count=Integer.parseInt(count1);
				} 
		catch (Exception e)
		{  
			logger.error("Error while inserting the pan request data:" + e);
		}
		
				logger.debug("Successfully closed Resources DBConnection in insertPanRequest Method:- ");
			
		return count;
	
	}

	public int insertEPFAuthPassBookRequest(EPFAuthPassBookPayload epfAuthPassBookPayload) {


		int count = 0;
		try 
		{
			String count1=(String) getSession().save(epfAuthPassBookPayload);
			
			
			count=Integer.parseInt(count1);
			
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the dl request data:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return count;
	
	
	}

	
	
	public int insertEPFAuthPassBookResponse(EPFAuthPassBookResponse epfAuthPassBookResponse,int SID) 
	{
		int count = 0;
		
		EPFAuthPassBookResult epfAuthPassBookResult= epfAuthPassBookResponse.getPayload().getResult();
		epfAuthPassBookResult.setUniqueid(""+SID);
//		System.out.println(epfAuthPassBookResponse.getHeader().getCorrelationId());
		if(epfAuthPassBookResult.getEmployee_details()!=null)
		epfAuthPassBookResult.getEmployee_details().setCorelationid(epfAuthPassBookResponse.getHeader().getCorrelationId());
		getSession().save(epfAuthPassBookResult);
		
		
		
		
		/*try {
	       
			EPFAuthPassBookEmpDetails epfAuthPassBookEmpDetails=epfAuthPassBookResponse.getPayload().getResult().getEmployee_details();   
			 	epfAuthPassBookEmpDetails.setUniqueid(""+count2);
				epfAuthPassBookEmpDetails.setCorelationid(epfAuthPassBookResponse.getHeader().getCorrelationId());
			    	
			String count3=(String) getSession().save(epfAuthPassBookEmpDetails);
			count=Integer.parseInt(count3);
			
			if (epfAuthPassBookResponse.getPayload().getResult().getEst_details().size() != 0) {
				for (EPFAuthPassBookESTDetails epfAuthPassBookESTDetails : epfAuthPassBookResponse.getPayload()
						.getResult().getEst_details())
				{
					try {
						epfAuthPassBookESTDetails.setUniqueid(""+count2);
                        epfAuthPassBookESTDetails.setSequenceid(this.getSeqNextValue("QCIB_K_EPF_AUTH_PASS_REQ_SQC"));
                    	getSession().save(epfAuthPassBookESTDetails);
					}
					catch(Exception e)
					{
						
					}
				
				
			
				if (epfAuthPassBookESTDetails.getPassbook().size() != 0) {
					
					
					for (EPFAuthPassBook epfauthpassbook : epfAuthPassBookESTDetails.getPassbook()) {
						 
							try {
							epfauthpassbook.setUniqueid(""+count2);
							epfauthpassbook.setSequenceid(this.getSeqNextValue("QCIB_K_PASSBOOKESTDETAIL_SQC"));
							getSession().save(epfauthpassbook);
						
							}
							catch(Exception e)
							{
								
							}
						
						}
						
					
					
				
			
			} 
				}
			}
		}						
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting theEPF Passbook Detail data:" + e);

		}
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");*/
		
		return count;

	}
	


	public int insertudyogAadharRequest(UdyogAadharPayload udyogAadharPayload) 
	{
		int count = 0;
		try {
			
             String count1=(String) getSession().save(udyogAadharPayload);
             
             
			 count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the UdyogAadharRequest response payload data query:" + e);
		} 
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		
		return 0;
	}

	public int insertgstIdentificationRequest(GstIdentificationPayload gstIdentificationPayload) 
	{
		int count = 0;
		try {
			
			String count1=(String) getSession().save(gstIdentificationPayload);
			
			
			count=Integer.parseInt(count1);
			
		} catch (Exception e) {
			logger.error("Error while inserting the GstIdentificationRequest response payload data query:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return 0;
	}

	public int insertifscRequest(IFSCPayload ifscPayload)
	{
		int count = 0;
        try {
        	 
             String count1=(String) getSession().save(ifscPayload);
             
             
    	     count=Integer.parseInt(count1);
          } catch (Exception e) {
			logger.error("Error while inserting the IFSCRequest response payload data query:" + e);
		} 
		return 0;
	}

	public int insertitrAuthRequest(ITRAuthRequest itrAuthRequest)
	{
		int count = 0;
		
		
		try {
			//itrAuthRequest.getPayload().setUniqueid(this.getSeqNextValue("IB_K_ITRAUTh_REQ_SQC"));
			itrAuthRequest.getPayload().setCorrelationid(itrAuthRequest.getHeader().getCorrelationId());
			
			String count1=(String) getSession().save(itrAuthRequest.getPayload());
			
			
			
			String str=""+count1;
			count=Integer.parseInt(str);
			} catch (Exception e) {
			logger.error("Error while inserting the itrAuthRequest response payload data query:" + e);
		}
		return 0;
	}



	

	public int insertGSTAuthenticationRequest(GSTAuthenticationPayload gstAuthenticationPayload) {

		int count = 0;
		try 
		{
			String count1=(String) getSession().save(gstAuthenticationPayload);
			
			
			count=Integer.parseInt(count1);
			
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the gst_auth request data:" + e);
		}
		
		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
		return count;
	
	}


	public int insertCAMemberShipAuthRequest(CAMemberShipAuthPayload caMemberShipAuthPayload) {

		int count = 0;
		try 
		{
			String count1=(String) getSession().save(caMemberShipAuthPayload);
			
			
		    count=Integer.parseInt(count1);
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the ca_mem request data:" + e);
		}
		return count;
	
	}

	public int insertICSIMemberShipRequest(ICSIMemberShipPayload icsiMemberShipPayload) {

		int count = 0;
		try 
		{
		  String count1= (String) getSession().save(icsiMemberShipPayload);
		  
		  
			count=Integer.parseInt(count1);
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the icsi_mem request data:" + e);
		}
		
		return count;
	
	}

	public int insertICWAIFirmAuthRequest(ICWAIFirmAuthPayload icwaiFirmAuthPayload) {

		int count = 0;
		try 
		{
			String count1=(String) getSession().save(icwaiFirmAuthPayload);
			
			
	        	count=Integer.parseInt(count1);	
			
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the icwai_firm_auth request data:" + e);
		}
		return count;
	
	}

	
	public int insertEPFAuthOTPRequest(EPFAuthOTPPayload epfAuthOTPPayload) {

		int count = 0;
		try 
		{
              String count1=(String) getSession().save(epfAuthOTPPayload);
              
              
			  count=Integer.parseInt(count1);
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting the epf_auth_otp_request request data:" + e);
		}
		return count;
	
	}



	public int insertHSNRequest(HSNPayload hsnPayload) {
		int count = 0;
		try {
			  String count1=(String) getSession().save(hsnPayload);
			  
			  
			  count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the HSN request data:" + e);
		}
		return count;
	}

	

	public int insertAadharRequest(AadharPayload aadharPayload) {
		int count = 0;
		try {
			Transaction tx = getSession().getTransaction();
			String count1 = (String) getSession().save(aadharPayload);
			tx.commit();
			
			count = Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the AADHAR request data:" + e);
		}
		logger.debug("Successfully closed Resources DBConnection in insertAadharRequest Method:- ");
		return count;
	}

	public int insertCompanyLLPIdentificationRequest(CompanyLLPIdentificationPayload companyLLPIdentificationPayload) {
		int count = 0;
		try {
			
			String count1=(String) getSession().save(companyLLPIdentificationPayload);
			
			
			count=Integer.parseInt(count1);
			} catch (Exception e) {
			logger.error("Error while inserting the COMPANY LLP IDENTIFICATION request data:" + e);
		}
       return count;
	}

	public int insertEmailAuthRequest(EmailAuthPayload emailAuthPayload) {
		int count = 0;
		try {
			
		String count1=(String) getSession().save(emailAuthPayload);
		
		
		
		count=Integer.parseInt(count1);
		} catch (Exception e) {
			logger.error("Error while inserting the EMAIL AUTHENTICATION request data:" + e);
		}
		return count;	
		}

	public int insertPassportRequest(PassportPayload passportPayload) {
		int count = 0;
	

		try {
			
				String count1= (String) getSession().save(passportPayload);
				
				
				
				 count=Integer.parseInt(count1);
			} catch (Exception e) {
			logger.error("Error while inserting the HSN request data:" + e);
		}
		return count;
	}


	
		public int insertLpgResponse(LpgResult2 lpgRes){
			int count = 0;
			try {
				count= (Integer) getSession().save(lpgRes);
					}
			catch (Exception e) {
						logger.error("Error while executing query:" + e);
					}
					logger.debug("Successfully closed Resources DBConnection in insertLpgResponse Method:- ");
		return count;
	}

		@Override
		public void inserAllinDBPassport(KarzaFullReqRes karzaFullReqRes) {
			int count=0;
			KarzaReqRes rq=karzaFullReqRes.getReqRes();
			if(rq!=null) {
				try {
					logger.info("PASSPORT Request response save in DB : Start");
					count=insertKarzaRequestResponse(rq);
					logger.info("PASSPORT Request response save in DB : End : " +count);
				} catch (Exception e) {
					logger.error("There is error while saving data in PASSPORT Request Response "+e);
				}
			}
			PassportRequest passportRequest=karzaFullReqRes.getPassportRequest();
			if(passportRequest!=null) {
				try {
					logger.info("PASSPORT Request save in DB individually : Start");
					PassportPayload passportPayload=passportRequest.getPayload(); 
					passportPayload.setUniqueid(""+count);
					passportPayload.setCorelationid(passportRequest.getHeader().getCorrelationId());
					insertPassportRequest(passportPayload);
					logger.info("PASSPORT Request save in DB individually : "+ count);
				} catch (Exception e) {
					logger.error("There is error while saving data in PASSPORT Request "+ e);
					
				}
			}
			PassportResponse passportResponse=karzaFullReqRes.getPassportResponse();
			if(passportResponse!=null) {
				try {
					logger.info("PASSPORT Response save in DB individually : Start");
					PassportResult passportResult=passportResponse.getPayload().getResult();
					passportResult.setUniqueid(""+count);
					passportResult.setCorrelationid(passportResponse.getHeader().getCorrelationId());
					insertPassportResponse(passportResult);
					logger.info("PASSPORT Response save in DB individually : " + count);
				} catch (Exception e) {
					logger.error("There is error while saving data in PASSPORT Response "+e);
				}
			}
	}

        @Override
		public void inserAllinDBPITRAuth(KarzaFullReqRes karzaFullReqRes) {
			int count=0;
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("ITRAuth Request response save in DB : Start");
					count=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					logger.info("ITRAuth Request response save in DB : End : " +count);
				} catch (Exception e) {
					logger.error("There is error while saving data in ITRAuth Request Response "+e);
				}
			}
			
			if(karzaFullReqRes.getItrAuthRequest()!=null) {
				try {
					logger.info("ITRAuth Request save in DB individually : Start");
					insertitrAuthRequest(karzaFullReqRes.getItrAuthRequest());
					logger.info("ITRAuth Request save in DB individually : "+ count);
				} catch (Exception e) {
					logger.error("There is error while saving data in ITRAuth Request "+ e);
				}
			}
			if(karzaFullReqRes.getItrAuthResponse()!=null) {
				try {
					logger.info("ITRAuth Response save in DB individually : Start");
					insertITRAuthResponse(karzaFullReqRes.getItrAuthResponse());
					logger.info("ITRAuth Request response save in DB : End : " +count);
				} catch (Exception e) {
					logger.error("There is error while saving data in ITRAuth Response "+e);
				}
			}
			
			
		}
        
        @Override
      		public void insertAllinIFSC(KarzaFullReqRes karzaFullReqRes) {
      			int count=0;
      			KarzaReqRes rq=karzaFullReqRes.getReqRes();
      			if(rq!=null) {
      				try {
      					logger.info("IFSC Request response save in DB : Start");
      				    count=insertKarzaRequestResponse(rq);
      				  logger.info("IFSC Request response save in DB : End : " +count);
      				} catch (Exception e) {
      					logger.error("There is error while saving data in IFSC Request Response "+e);
      				}
      			}
      			IFSCRequest ifscRequest=karzaFullReqRes.getIfscRequest();
      			if(ifscRequest!=null) {
      				try {
      					logger.info("IFSC Request save in DB individually : Start");
      					IFSCPayload ifscPayload=ifscRequest.getPayload();
      					ifscPayload.setUniqueid(""+count);
      					ifscPayload.setCorrelationid(ifscRequest.getHeader().getCorrelationId());
      					insertifscRequest(ifscPayload);
      					logger.info("IFSC Request save in DB individually : "+ count);
      				} catch (Exception e) {
      					logger.error("There is error while saving data in IFSC Request "+ e);
      				}
      			}
      			IFSCResponse ifscResponse=karzaFullReqRes.getIfscResponse();
      			if(ifscResponse!=null) {
      				try {
      					logger.info("IFSC Response save in DB individually : Start");
      					IFSCResult ifscResult=ifscResponse.getPayload().getResult();
      					ifscResult.setUniqueid(""+count);
      					ifscResult.setCorelationid(ifscResponse.getHeader().getCorrelationId());
      					insertIFSCRequestProcess(ifscResult);
      					logger.info("IFSC Request response save in DB : End : " +count);
      				} catch (Exception e) {
      					logger.error("There is error while saving data in IFSC Response "+e);
      				}
      			}
      			
      			
      		}
        
        @Override
  		public void insertHSNRequestProcess(KarzaFullReqRes karzaFullReqRes) {
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("HSN Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("HSN Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in HSN Request Response "+e);
  				}
  			}
  			HSNRequest hsnRequest=karzaFullReqRes.getHsnRequest();
  			if(hsnRequest!=null) {
  				try {
  					logger.info("HSN Request save in DB individually : Start");
  					HSNPayload hsnPayload=hsnRequest.getPayload();
  					hsnPayload.setUniqueid(""+count);
  					hsnPayload.setCorelationid(hsnRequest.getHeader().getCorrelationId());
  					insertHSNRequest(hsnPayload);
  					logger.info("HSN Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in HSN Request "+ e);
  				}
  			}
  			HSNResponse hsnResponse=karzaFullReqRes.getHsnResponse();
  			if(hsnResponse!=null) {
  				try {
  					logger.info("HSN Response save in DB individually : Start");
  					HSNResult hsnResult=hsnResponse.getPayload().getResult();
  					hsnResult.setUniqueid(""+count);
  					hsnResult.setCorelationid(hsnResponse.getHeader().getCorrelationId());
  					insertHSNRequestProcess(hsnResult);
  					logger.info("HSN Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in HSN Response "+e);
  				}
  			}
  			
  			
  		}
		
        
		@Override
  		public void insertEmailAuthResponse(KarzaFullReqRes karzaFullReqRes) {
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("EmailAuth Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("EmailAuth Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EmailAuth Request Response "+e);
  				}
  			}
  			EmailAuthRequest emailAuthRequest=karzaFullReqRes.getEmailAuthRequest();
  			if(emailAuthRequest!=null) {
  				try {
  					logger.info("EmailAuth Request save in DB individually : Start");
  					EmailAuthPayload emailAuthPayload=emailAuthRequest.getPayload();
  					emailAuthPayload.setUniqueid(""+count);
  					emailAuthPayload.setCorelationid(emailAuthRequest.getHeader().getCorrelationId());
  					insertEmailAuthRequest(emailAuthPayload);
  					logger.info("EmailAuth Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EmailAuth Request "+ e);
  				}
  			}
  			EmailAuthResponse emailAuthResponse=karzaFullReqRes.getEmailAuthResponse();
  			if(emailAuthResponse!=null) {
  				try {
  					logger.info("EmailAuth Response save in DB individually : Start");
  					
  					EmailAuthResult emailAuthResult=emailAuthResponse.getPayload().getResult();
  					emailAuthResult.setEid(count);
  					emailAuthResult.setCorelationid(emailAuthResponse.getHeader().getCorrelationId());
  					insertEmailAuthResponse(emailAuthResult);
  					logger.info("EmailAuth Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EmailAuth Response "+e);
  				}
  			}
  			
  			
  		}
		
		@Override
  		public void insertAadharReqResponse(KarzaFullReqRes karzaFullReqRes) {
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("Aadhar Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("Aadhar Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in Aadhar Request Response "+e);
  				}
  			}
  			AadharRequest aadharRequest=karzaFullReqRes.getAadharRequest();
  			if(aadharRequest!=null) {
  				try {
  					logger.info("Aadhar Request save in DB individually : Start");
  					AadharPayload aadharPayload=aadharRequest.getPayload();
  					
  					if(aadharPayload!=null){
  					aadharPayload.setUniqueId(""+count);
  					aadharPayload.setCorelationId(aadharRequest.getHeader().getCorrelationId());
  					insertAadharRequest(aadharPayload);
  					logger.info("Aadhar Request save in DB individually : "+ count);
  					}
  					} catch (Exception e) {
  					logger.error("There is error while saving data in Aadhar Request "+ e);
  				}
  			}
  			try {
  		     AadharResponse aadharResponse=karzaFullReqRes.getAadharResponse();
  			   if(aadharResponse!=null) {
  				 AadharResponsePayload aadharResponsePayload=aadharResponse.getPayload();
  				 if(aadharResponsePayload!=null){
  					AadharResult aadharResult=aadharResponsePayload.getResult();
  					 if(aadharResult!=null){
  					logger.info("Aadhar Response save in DB individually : Start");
  					aadharResult.setUniqueId(""+count);
  					aadharResult.setCorrelation_id(aadharResponse.getHeader().getCorrelationId());
  					insertAadharResponse(aadharResult);
  					logger.info("Aadhar Request response save in DB : End : " +count);
  					 }
  			   }
  			   } 
  			} catch (Exception e) {
  					logger.error("There is error while saving data in Aadhar Response "+e);
  				}
  			
  			
 			
  		}
		

        @Override
  		public void insertAllinUdyogAadharResponse(KarzaFullReqRes karzaFullReqRes) {
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("UdyogAadhar Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("UdyogAadhar Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in UdyogAadhar Request Response "+e);
  				}
  			}
  			UdyogAadharRequest udyogAadharRequest=karzaFullReqRes.getUdyogAadharRequest();
  			if(udyogAadharRequest!=null) {
  				try {
  					logger.info("UdyogAadhar Request save in DB individually : Start");
  					UdyogAadharPayload udyogAadharPayload=udyogAadharRequest.getPayload();
  					udyogAadharPayload.setUniqueid(""+count);
  					udyogAadharPayload.setCorrelationid(udyogAadharRequest.getHeader().getCorrelationId());
  					insertudyogAadharRequest(udyogAadharPayload);
  					logger.info("UdyogAadhar Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in UdyogAadhar Request "+ e);
  				}
  			}
  	         UdyogAadharResponse udyogAadharResponse=karzaFullReqRes.getUdyogAadharResponse();
  			if(udyogAadharResponse!=null) {
  				try {
  					logger.info("UdyogAadhar Response save in DB individually : Start");
  					UdyogAadharResult udyogAadharResult=udyogAadharResponse.getPayload().getResult();
  					udyogAadharResult.setUniqueid(""+count);
  					udyogAadharResult.setCorrelationid(udyogAadharResponse.getHeader().getCorrelationId());
  					insertUdyogAadharResponse(udyogAadharResult);
  					logger.info("UdyogAadhar Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in UdyogAadhar Request Response "+e);
  				}
  			}
  			
  			
  		}
        @Override
  		public void insertAlinlGSTAuthenticationResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("GSTAuthentication Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("GSTAuthentication Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in GSTAuthentication Request Response "+e);
  				}
  			}
  			GSTAuthenticationRequest authenticationRequest=karzaFullReqRes.getGstAuthenticationRequest();
  			if(authenticationRequest!=null) {
  				try {
  					logger.info("GSTAuthentication Request save in DB individually : Start");
  					GSTAuthenticationPayload gstAuthenticationPayload=authenticationRequest.getPayload();
  					if(gstAuthenticationPayload!=null){
  					gstAuthenticationPayload.setUniqueid(""+count);
  					gstAuthenticationPayload.setCorelationid(authenticationRequest.getHeader().getCorrelationId());
  					insertGSTAuthenticationRequest(gstAuthenticationPayload);
  					logger.info("GSTAuthentication Request save in DB individually : "+ count);
  					}
  				} catch (Exception e) {
  					logger.error("There is error while saving data in GSTAuthentication Request "+ e);
  				}
  			}
  		     GSTAuthenticationResponse gstAuthenticationResponse=karzaFullReqRes.getGstAuthenticationResponse();
  	          if(gstAuthenticationResponse!=null) {
  				try {
  					logger.info("GSTAuthentication Response save in DB individually : Start");
  					GSTAuthenticationResult gstAuthenticationResult=gstAuthenticationResponse.getPayload().getResult();
  					gstAuthenticationResult.setUniqueid(""+count);
  					gstAuthenticationResult.setCorrelationid(gstAuthenticationResponse.getHeader().getCorrelationId());
  					insertGSTAuthenticationResponse(gstAuthenticationResult);
  					logger.info("GSTAuthentication Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in GSTAuthentication Request Response "+e);

  				}
  			}
  			
  			
  		}
        
        @Override
  		public void insertAllinLLPIdentificationResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("LLPIdentification Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("LLPIdentification Request response save in DB : End : " +count);
  				   
  				} catch (Exception e) {
  					logger.error("There is error while saving data in LLPIdentification Request Response "+e);
  				}
  			}
  			CompanyLLPIdentificationRequest companyLLPIdentificationRequest=karzaFullReqRes.getCompanyLLPIdentificationRequest();
  			if(companyLLPIdentificationRequest!=null) {
  				try {
  					logger.info("LLPIdentification Request save in DB individually : Start");
  					CompanyLLPIdentificationPayload companyLLPIdentificationPayload=companyLLPIdentificationRequest.getPayload();
  					companyLLPIdentificationPayload.setUniqueid(""+count);
  					companyLLPIdentificationPayload.setCorelationid(companyLLPIdentificationRequest.getHeader().getCorrelationId());
  					insertCompanyLLPIdentificationRequest(companyLLPIdentificationPayload);
  					logger.info("LLPIdentification Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in LLPIdentification Request "+ e);
  				}
  			}
  			 CompanyLLPIdentificationResponse companyLLPIdentificationResponse=karzaFullReqRes.getCompanyLLPIdentificationResponse();
  	          if(companyLLPIdentificationResponse!=null) {
  				try {
  					logger.info("LLPIdentification Response save in DB individually : Start");
  					CompanyLLPIdentificationResult companyLLPIdentificationResult=companyLLPIdentificationResponse.getPayload().getResult();
  					companyLLPIdentificationResult.setUniqueid(""+count);
  					companyLLPIdentificationResult.setCorrelationid(companyLLPIdentificationResponse.getHeader().getCorrelationId());
  					insertLLPIdentificationResponse(companyLLPIdentificationResult);
  					logger.info("LLPIdentification Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in LLPIdentification Request Response "+e);
  				}
  			}
  		}
        @Override
  		public void insertAllinCAMemberShipAuthResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("CAMemberShipAuth Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("CAMemberShipAuth Request response save in DB : End : " +count);
  				   
  				} catch (Exception e) {
  					logger.error("There is error while saving data in CAMemberShipAuth Request Response "+e);
  				}
  			}
  			CAMemberShipAuthRequest caMemberShipAuthRequest=karzaFullReqRes.getCaMemberShipAuthRequest();
  				if(caMemberShipAuthRequest!=null) {
  				try {
  					logger.info("CAMemberShipAuth Request save in DB individually : Start");
  					CAMemberShipAuthPayload caMemberShipAuthPayload=caMemberShipAuthRequest.getPayload();
  					caMemberShipAuthPayload.setUniqueid(""+count);
  					caMemberShipAuthPayload.setCorelationid(caMemberShipAuthRequest.getHeader().getCorrelationId());
  					insertCAMemberShipAuthRequest(caMemberShipAuthPayload);
  					logger.info("CAMemberShipAuth Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in CAMemberShipAuth Request "+ e);
  				}
  			}
  				CAMemberShipAuthResponse caMemberShipAuthResponse=karzaFullReqRes.getCaMemberShipAuthResponse();
  	          if(caMemberShipAuthResponse!=null) {
  				try {
  					logger.info("CAMemberShipAuth Response save in DB individually : Start");
  					CAMemberShipAuthResult caMemberShipAuthResult=caMemberShipAuthResponse.getPayload().getResult();
  					caMemberShipAuthResult.setUniqueid(""+count);
  					caMemberShipAuthResult.setCorrelationid(caMemberShipAuthResponse.getHeader().getCorrelationId());
  					insertCAMemberShipAuthResponse(caMemberShipAuthResult);
  					logger.info("CAMemberShipAuth Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in CAMemberShipAuth Request Response "+e);
  				}
  			}
  		}
        @Override
  		public void insertAllinICSIMemberShipResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("ICSIMemberShip Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("ICSIMemberShip Request response save in DB : End : " +count);
  				    
  				} catch (Exception e) {
  					logger.error("There is error while saving data in ICSIMemberShip Request Response "+e);
  				}
  			}
  			ICSIMemberShipRequest icsiMemberShipRequest=karzaFullReqRes.getIcsiMemberShipRequest();
  				if(icsiMemberShipRequest!=null) {
  				try {
  					logger.info("ICSIMemberShip Request save in DB individually : Start");
  					ICSIMemberShipPayload icsiMemberShipPayload=icsiMemberShipRequest.getPayload();
  					icsiMemberShipPayload.setUniqueid(""+count);
  					icsiMemberShipPayload.setCorelationid(icsiMemberShipRequest.getHeader().getCorrelationId());
  					insertICSIMemberShipRequest(icsiMemberShipPayload);
  					logger.info("ICSIMemberShip Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in ICSIMemberShip Request "+ e);
  				}
  			}   
  				ICSIMemberShipResponse icsiMemberShipResponse=karzaFullReqRes.getIcsiMemberShipResponse();
  	          if(icsiMemberShipResponse!=null) {
  				try {
  					for(ICSIMemberShipResult icsiMemberShipResult:icsiMemberShipResponse.getPayload().getResult())
  					{
  	  			    logger.info("ICSIMemberShip Response save in DB individually : Start");
  					icsiMemberShipResult.setUniqueid(""+count);	
  					icsiMemberShipResult.setCorrelationid(icsiMemberShipResponse.getHeader().getCorrelationId());
  				    //icsiMemberShipResult.setSequence_id(this.getSeqNextValue("IB_K_ICSIMEMBER_FOR_RES_SQC"));
  				 	insertICSIMemberShipResponse(icsiMemberShipResult);
  					logger.info("ICSIMemberShip Request response save in DB : End : " +count);
  					}
  				} catch (Exception e) {
  					logger.error("There is error while saving data in ICSIMemberShip Request Response "+e);
  				}
  			}
  		}
        @Override
  		public void inserAllintICWAIFirmAuthResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("ICWAIFirmAuth Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("ICWAIFirmAuth Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in ICWAIFirmAuth Request Response "+e);
  				}
  			}
  			ICWAIFirmAuthRequest icwaiFirmAuthRequest=karzaFullReqRes.getIcwaiFirmAuthRequest();
  				if(icwaiFirmAuthRequest!=null) {
  				try {
  					logger.info("ICWAIFirmAuth Request save in DB individually : Start");
  					ICWAIFirmAuthPayload icwaiFirmAuthPayload=icwaiFirmAuthRequest.getPayload();
  					icwaiFirmAuthPayload.setUniqueid(""+count);
  					icwaiFirmAuthPayload.setCorelationid(icwaiFirmAuthRequest.getHeader().getCorrelationId());
  					insertICWAIFirmAuthRequest(icwaiFirmAuthPayload);
  					logger.info("ICWAIFirmAuth Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in ICWAIFirmAuth Request "+ e);
  				}
  			}   
  				ICWAIFirmAuthResponse icwaiFirmAuthResponse= karzaFullReqRes.getIcwaiFirmAuthResponse();
  	          if(icwaiFirmAuthResponse!=null) {
  				try {
  	  			    logger.info("ICWAIFirmAuth Response save in DB individually : Start");
  					ICWAIFirmAuthResult icwaiFirmAuthResult=icwaiFirmAuthResponse.getPayload().getResult();
  					icwaiFirmAuthResult.setUniqueid(""+count);	
  					icwaiFirmAuthResult.setCorrelationid(icwaiFirmAuthResponse.getHeader().getCorrelationId());
  					insertICWAIFirmAuthResponse(icwaiFirmAuthResult);
  					logger.info("ICWAIFirmAuth Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in ICWAIFirmAuth Request Response "+e);
  				}
  			} 						
  		}
        @Override
  		public void insertAllinEPFAuthOTPResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("EPFAuthOTP Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("EPFAuthOTP Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EPFAuthOTP Request Response "+e);
  				}
  			}
  			EPFAuthOTPRequest epfAuthOTPRequest=karzaFullReqRes.getEpfAuthOTPRequest();
  				if(epfAuthOTPRequest!=null) {
  				try {
  					logger.info("EPFAuthOTP Request save in DB individually : Start");
  					EPFAuthOTPPayload epfAuthOTPPayload=epfAuthOTPRequest.getPayload();
  					epfAuthOTPPayload.setUniqueid(""+count);
  					epfAuthOTPPayload.setCorelationid(epfAuthOTPRequest.getHeader().getCorrelationId());
  					insertEPFAuthOTPRequest(epfAuthOTPPayload);
  					logger.info("EPFAuthOTP Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EPFAuthOTP Request "+ e);
  				}
  			}   
  				EPFAuthOTPResponse epfAuthOTPResponse=karzaFullReqRes.getEpfAuthOTPResponse();
  	          if(epfAuthOTPResponse!=null) {
  				try {
  	  			    logger.info("EPFAuthOTP Response save in DB individually : Start");
  					EPFAuthOTPResult epfAuthOTPResult=epfAuthOTPResponse.getPayload().getResult();
  					epfAuthOTPResult.setUniqueid(""+count);	
  					epfAuthOTPResult.setCorrelationid(epfAuthOTPResponse.getHeader().getCorrelationId());
  					insertEPFAuthOTPResponse(epfAuthOTPResult);
  					logger.info("EPFAuthOTP Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EPFAuthOTP Request Response "+e);
  				}
  			}
  			}
        @Override
  		public void insertAllinGstIdentificationResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("GstIdentification Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("GstIdentification Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in GstIdentification Request Response "+e);
  				}
  			}
  			  GstIdentificationRequest gstIdentificationRequest=karzaFullReqRes.getGstIdentificationRequest();
  				if(gstIdentificationRequest!=null) {
  				try {
  					logger.info("GstIdentification Request save in DB individually : Start");
  					GstIdentificationPayload gstIdentificationPayload=gstIdentificationRequest.getPayload();
  					gstIdentificationPayload.setUniqueid(""+count);
  					gstIdentificationPayload.setCorrelationid(gstIdentificationRequest.getHeader().getCorrelationId());
  					insertgstIdentificationRequest(gstIdentificationPayload);	
  					logger.info("GstIdentification Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in GstIdentification Request "+ e);
  				}
  			}   
  				GstIdentificationResponse gstIdentificationResponse=karzaFullReqRes.getGstIdentificationResponse();
  	          if(gstIdentificationResponse!=null) {
  				try {
  					for(GstIdentificationResult gstIdentificationResult:gstIdentificationResponse.getPayload().getResult())
  					{
  	  	  			logger.info("GstIdentification Response save in DB individually : Start");
  					gstIdentificationResult.setUniqueid(""+count);	
  					//gstIdentificationResult.setSequenceid(this.getSeqNextValue("QCIB_K_GST_IDENTI_FOR_RES_SQC"));
  					gstIdentificationResult.setCorrelation_id(gstIdentificationResponse.getHeader().getCorrelationId());
  					insertGstIdentificationResponse(gstIdentificationResult);
  					logger.info("GstIdentification Request response save in DB : End : " +count);
  					}
  				
  				} catch (Exception e) {
  					logger.error("There is error while saving data in GstIdentification Request Response "+e);
  				}
  			}
  		}
     
        @Override
  		public void insertAllinEPFAuthPassbookResponse(KarzaFullReqRes karzaFullReqRes){
  			int count=0;
  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
  			if(rq!=null) {
  				try {
  					logger.info("EPFAuthPassbook Request response save in DB : Start");
  				    count=insertKarzaRequestResponse(rq);
  				    logger.info("EPFAuthPassbook Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EPFAuthPassbook Request Response "+e);
  				}
  			}
  			  EPFAuthPassBookRequest epfAuthPassBookRequest=karzaFullReqRes.getEpfAuthPassBookRequest();
  				if(epfAuthPassBookRequest!=null) {
  				try {
  					logger.info("EPFAuthPassbook Request save in DB individually : Start");
  					EPFAuthPassBookPayload epfAuthPassBookPayload=epfAuthPassBookRequest.getPayload();
  					epfAuthPassBookPayload.setUniqueid(""+count);
  					epfAuthPassBookPayload.setCorelationid(epfAuthPassBookRequest.getHeader().getCorrelationId());
  					insertEPFAuthPassBookRequest(epfAuthPassBookPayload);	
  					logger.info("EPFAuthPassbook Request save in DB individually : "+ count);
  				} catch (Exception e) {
  					logger.error("There is error while saving data in EPFAuthPassbook Request "+ e);
  				}
  			}   
  				EPFAuthPassBookResponse epfAuthPassBookResponse=karzaFullReqRes.getEpfAuthPassBookResponse();
  	          if(epfAuthPassBookResponse!=null) {
  				try {
  	  	  			logger.info("EPFAuthPassbook Response save in DB individually : Start");
  					insertEPFAuthPassBookResponse(epfAuthPassBookResponse,count);
  					logger.info("EPFAuthPassbook Request response save in DB : End : " +count);
  				} catch (Exception e) {
  					e.printStackTrace();
  					logger.error("There is error while saving data in EPFAuthPassbook Request Response "+e);
  					
  				}
  			}
  		}


    	public int insertLpgRequestResponse(KarzaFullReqRes karzaFullReqRes) {
			Integer EID=0;
    		Long id=0l;
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("LPG Request response save in DB : Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					logger.info("LPG Request response save in DB : End : ");
					 id = Long.valueOf(EID);
				} catch (Exception e) {
					logger.error("Error while inserting the KARZA RES REQ data:" + e);
				}
			}
			if(karzaFullReqRes.getLpgReq()!=null && id>0) {
				try {
					String corelationid=karzaFullReqRes.getLpgReq().getHeader().getCorrelationId();
					karzaFullReqRes.getLpgReq().getPayload().setUniqueid(id);
					karzaFullReqRes.getLpgReq().getPayload().setCorelationid(corelationid);
					logger.info("LPG save in DB individually : Start");
					insertlpgRequest(karzaFullReqRes.getLpgReq());
					logger.info("LPG Request save in DB individually : ");
				} catch (Exception e) {
					logger.error("Error while inserting the LPG Request data:" + e);
				}
			}
			if(karzaFullReqRes.getLpgResponse()!=null && id>0){
				try {
					logger.info("LPG Response save in DB individually : Start");
					String corelationid=karzaFullReqRes.getLpgResponse().getHeader().getCorrelationId();
					karzaFullReqRes.getLpgResponse().getPayload().getResult().getLpg().setCorelationid(corelationid);
					karzaFullReqRes.getLpgResponse().getPayload().getResult().getLpg().setUniqueid(id);
					insertlpgRes(karzaFullReqRes.getLpgResponse(),id);
					logger.info("LPG Response save in DB individually : ");
					} 
				catch (Exception e) {
					logger.error("Error while inserting the LPG Response data:" + e);
				}
				}
			return 0;
		}
		private void insertlpgRes(LpgResponse lpgResponse, Long id) {
			Long count = 0l;
				try {
					if(lpgResponse!=null && lpgResponse.getPayload().getResult().getLpg()!=null){
					 count= Long.parseLong(getSession().save(lpgResponse.getPayload().getResult().getLpg()).toString());
					}
					}
				 catch (Exception e) {
					logger.error("Error while inserting the LPG Response data:" + e);
				}
		}
		private void insertlpgRequest(LpgRequest lpgReq) {
			Long count = 0l;
			try {
				if(lpgReq!=null && lpgReq.getPayload()!=null){
				 count= Long.parseLong(getSession().save(lpgReq.getPayload()).toString());
				 
				 
				}
				}
			 catch (Exception e) {
				logger.error("Error while inserting the LPG Request data:" + e);
			 	}
		}
		@Override
		public int insertVoterRequestResponse(KarzaFullReqRes karzaFullReqRes) {
            Integer EID=null;
			Long id=0l;
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("VOTER Request response save in DB : Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					logger.info("VOTER Request response save in DB : END");
					id=Long.valueOf(EID);
				} catch (Exception e) {
					logger.error("Error while inserting the KARZA RES REQ data:" + e);
				}
			}
			
			if(karzaFullReqRes.getVoterRequest()!=null && id>0) {
				try {
					String corelationid=karzaFullReqRes.getVoterRequest().getHeader().getCorrelationId();
					karzaFullReqRes.getVoterRequest().getPayload().setUniqueid(id);
					karzaFullReqRes.getVoterRequest().getPayload().setCorelationid(corelationid);
					logger.info("VOTER Request save in DB individually : Start");
					insertvoterRequest(karzaFullReqRes.getVoterRequest());
					logger.info("VOTER Request save in DB individually : End");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the Voter Request data:" + e);
				}
			}
			
			if(karzaFullReqRes.getVoterResponse()!=null && id>0){
				try {
					String corelationid=karzaFullReqRes.getVoterResponse().getHeader().getCorrelationId();
					karzaFullReqRes.getVoterResponse().getPayload().getResult().setCorelationid(corelationid);
					karzaFullReqRes.getVoterResponse().getPayload().getResult().setUniqueid(id);
					logger.info("VOTER Response save in DB individually : Start");
					insertvoterResponse(karzaFullReqRes.getVoterResponse(),id);
					logger.info("VOTER Response save in DB individually : End");
					} 
				
				catch (Exception e) {
					
					logger.error("Error while inserting the Voter Response data:" + e);
				}
				}
			return 0;
		}

	private void insertvoterResponse(VoterResponse voterResponse, Long id) {
		Long count = 0l;
			try {
				if(voterResponse!=null && voterResponse.getPayload()!=null){
				 count= Long.parseLong(getSession().save(voterResponse.getPayload().getResult()).toString());
				 
				 
				}
				}
			 catch (Exception e) {
				 e.printStackTrace();
				logger.error("Error while inserting the Voter Response data:" + e);
			}
	}
	private void insertvoterRequest(VoterRequest voterRequest) {
		Long count = 0l;
			try {
				if(voterRequest!=null && voterRequest.getPayload()!=null){
				 count= Long.parseLong(getSession().save(voterRequest.getPayload()).toString());
				 
				 
				}
				}
			 catch (Exception e) {
				logger.error("Error while inserting the Voter Request data:" + e);
			 	}
		}
		@Override
		public int insertLpgIdentiRequestResponse(KarzaFullReqRes karzaFullReqRes) {
			Integer EID=null;
			Long id=0l;
			
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("LpgIdenti Request response save in DB : Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					logger.info("LpgIdenti Request response save in DB : End");
					id=Long.valueOf(EID);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the KARZA RES REQ data:" + e);
				}
			}
			if(karzaFullReqRes.getLpgRequest()!=null && id>0) {
				try {
					String corelationid=karzaFullReqRes.getLpgRequest().getHeader().getCorrelationId();
					karzaFullReqRes.getLpgRequest().getPayload().setUniqueid(id);
					karzaFullReqRes.getLpgRequest().getPayload().setCorelationid(corelationid);
					logger.info("LPGIdenti Request save in DB individually : Start");
					insertLpgIdentificationsRequest(karzaFullReqRes.getLpgRequest());
					logger.info("LPGIdenti Request save in DB individually : End");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the LPG Identification Request data:" + e);
				}
			}
			
			if(karzaFullReqRes.getLpgRes()!=null && id>0){
				try {
					logger.info("LPGIdenti Response save in DB individually : Start");
					insertLpgIdentificationsResponse(karzaFullReqRes.getLpgRes(),id);
					logger.info("LPGIdenti Response save in DB individually : End");
					} 
				catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the LPG Identification Response data:" + e);
				}
			}	
			return 0;
		}
		private void insertLpgIdentificationsResponse(LpgIdentificationResponse lpgRes, Long id) {
			Long count = 0l;
				try {
					LpgIdentificationResponsePayload lpgIdentificationResponsePayload=lpgRes.getPayload();
					if(lpgIdentificationResponsePayload!=null){
					LpgIdentificationResult lpgIdentificationResult=lpgIdentificationResponsePayload.getResult();
					if(lpgIdentificationResult!=null){
						lpgIdentificationResult.setUniqueid(id);
						lpgIdentificationResult.setCorelationid(lpgRes.getHeader().getCorrelationId());
						count=(Long) getSession().save(lpgIdentificationResult);
						
					}
					}
				}
				 catch (Exception e) {
					logger.error("Error while inserting the LPG Identification Response data:" + e);
				}
		}
		private void insertLpgIdentificationsRequest(LpgIdentificationRequest lpgRequest) {
			Long count = 0l;
				try {
					 count= Long.parseLong(getSession().save(lpgRequest.getPayload()).toString());
					 
					 
					 
					}
				 catch (Exception e) {
					 e.printStackTrace();
					logger.error("Error while inserting the Lpg Identification Request data:" + e);
				}
		}
		@Override
		public int insertnameSimilarityRequestResponse(KarzaFullReqRes karzaFullReqRes) {
			Long id=0l;
			Integer EID=null;
			
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("Name Similarity Request response save in DB : Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					logger.info("Name Similarity Request response save in DB : End");
					id=Long.valueOf(EID);
				} catch (Exception e) {
					logger.error("Error while inserting the KARZA RES REQ data:" + e);
				}
			}
			
			if(karzaFullReqRes.getNameSimilarityRequest()!=null && id>0) {
				try {
					String corelationid=karzaFullReqRes.getNameSimilarityRequest().getHeader().getCorrelationId();
					karzaFullReqRes.getNameSimilarityRequest().getPayload().setUniqueid(id);
					karzaFullReqRes.getNameSimilarityRequest().getPayload().setCorelationid(corelationid);
					logger.info("Name Similarity Request save in DB individually : Start");
					insertnameSimilarityRequest(karzaFullReqRes.getNameSimilarityRequest());
					logger.info("Name Similarity Request save in DB individually : End");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the Name Similarity Request data:" + e);
				}
			}
			
			if(karzaFullReqRes.getNameSimilarityResponse()!=null && id>0){
			try {
				String corelationid=karzaFullReqRes.getNameSimilarityResponse().getHeader().getCorrelationId();
				karzaFullReqRes.getNameSimilarityResponse().getPayload().setCorelationid(corelationid);
				karzaFullReqRes.getNameSimilarityResponse().getPayload().setEid(id);
				logger.info("Name Similarity Response save in DB individually : Start");
				insertNameSimilarityResponse(karzaFullReqRes.getNameSimilarityResponse(),id);
				logger.info("Name Similarity Response save in DB individually : End");
				} 
			catch (Exception e) {
				logger.error("Error while inserting the Name Similarity Response data:" + e);
			}
			}
			return 0;
		}
		private void insertNameSimilarityResponse(NameSimilarityResponse nameSimilarityResponse, Long id) {
			Long count = 0l;
				try {
					if(nameSimilarityResponse!=null && nameSimilarityResponse.getPayload()!=null){
					 count= Long.parseLong(getSession().save(nameSimilarityResponse.getPayload()).toString());
					 
					 
					}
					}
				 catch (Exception e) {
					 e.printStackTrace();
					logger.error("Error while inserting the Name Similarity Response data:" + e);
				}
			}
		private void insertnameSimilarityRequest(NameSimilarityRequest nameSimilarityRequest) {
			Long count = 0l;
				try {
					if(nameSimilarityRequest!=null && nameSimilarityRequest.getPayload()!=null){
					 count= Long.parseLong(getSession().save(nameSimilarityRequest.getPayload()).toString());
					 
					 
					}
					}
				 catch (Exception e) {
					logger.error("Error while inserting the Name Similarity Request data:" + e);
				 	}
				}
		public int insertcompSearchRequestResponse(KarzaFullReqRes karzaFullReqRes) {
			Long id=0l;
			Integer EID=null;
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("Com Search Request response save in DB : Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					logger.info("Com Search Request response save in DB : Start");
					id=Long.valueOf(EID);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the KARZA RES REQ data:" + e);
				}
			}
			
			if(karzaFullReqRes.getCompSearchRequest()!=null && id>0) {
				try {
					String corelationid=karzaFullReqRes.getCompSearchRequest().getHeader().getCorrelationId();
					karzaFullReqRes.getCompSearchRequest().getPayload().setUniqueid(id);
					karzaFullReqRes.getCompSearchRequest().getPayload().setCorelationid(corelationid);
					logger.info("Com Search Request save in DB individually: Start");
					insertCompSearchRequest(karzaFullReqRes.getCompSearchRequest());
					logger.info("Com Search Request save in DB individually: End");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the LLPCIN Look Up Request data:" + e);
				}
			}
			
			if(karzaFullReqRes.getCompSearchRessponse()!=null && id>0) {
				try {
					logger.info("Com Search Response save in DB individually: Start");
					insertCompSearchResponse(karzaFullReqRes.getCompSearchRessponse(),id);
					logger.info("Com Search Response save in DB individually: End");
					} 
				catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			return 0;
		}
		private void insertCompSearchResponse(CompSearchResponse compSearchRessponse, Long id) {
			List<CompSearchResultData> compSearchResultDatas = compSearchRessponse.getPayload().getResult().getResult();
			try {
				
				for (CompSearchResultData companySearchResult :compSearchResultDatas) {
			
					companySearchResult.setEid(id);
					companySearchResult.setCorelationid(compSearchRessponse.getHeader().getCorrelationId());
					Long count1= (Long) getSession().save(companySearchResult);
					
					
				}
			}
			catch(Exception ee){
				ee.printStackTrace();
				logger.error("Error while inserting the LLPCIN Look Up Response data:" + ee);
			}
		}
	private void insertCompSearchRequest(CompSearchRequest compSearchRequest) {
		Long count = 0l;
		try {
			count = Long.parseLong(getSession().save(compSearchRequest.getPayload()).toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while inserting the Comp Search Request data:" + e);
		}
	}
		
		@Override
		public int insertLLPCINLookUpRequestResponse(KarzaFullReqRes karzaFullReqRes) {
			Long id=0l;
			Integer EID=null;
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("LLPCIN Lookup Request Response save in DB : Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					id=Long.valueOf(EID);
					logger.info("LLPCIN Lookup Request Response save in DB : End");
				} catch (Exception e) {
					logger.error("Error while inserting the KARZA RES REQ data:" + e);
				}
			}
			if(karzaFullReqRes.getCompanyLLPCINLookUpRequest()!=null && id>0) {
				try {
					String corelationid=karzaFullReqRes.getReqRes().getCorelationid();
					karzaFullReqRes.getCompanyLLPCINLookUpRequest().getPayload().setUniqueid(id);
					karzaFullReqRes.getCompanyLLPCINLookUpRequest().getPayload().setCorelationid(corelationid);
					logger.info("LLPCIN Lookup Request save in DB individually : Start");
					insertLLPCINLookUpRequest(karzaFullReqRes.getCompanyLLPCINLookUpRequest());
					logger.info("LLPCIN Lookup Request save in DB  individually: End");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the LLPCIN Look Up Request data:" + e);
				}
			}
			if(karzaFullReqRes.getCompanyLLPCINLookUpResponse()!=null && id>0) {
				try {
					logger.info("LLPCIN Lookup Response save in DB  individually: Start");
					insertLLPCINLookUpResponse(karzaFullReqRes.getCompanyLLPCINLookUpResponse(),id);
					logger.info("LLPCIN Lookup Response save in DB  individually: End");
					} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			return 0;
		}
		private void insertLLPCINLookUpResponse(CompanyLLPCINLookUpResponse companyLLPCINLookUpResponse, Long id) {
			
			List<CompanyLLPCINLookUpResult> companyLLPCINLookUpResults =  companyLLPCINLookUpResponse.getPayload().getResult();
			if(companyLLPCINLookUpResults.size()!=0){
			try {
				for (CompanyLLPCINLookUpResult companyLLPCINLookUpResult :companyLLPCINLookUpResults) {
					companyLLPCINLookUpResult.setEid(id);
					companyLLPCINLookUpResult.setCorelationid(companyLLPCINLookUpResponse.getHeader().getCorrelationId());
					Long count1= (Long) getSession().save(companyLLPCINLookUpResult);
					
					
				}
			}
			catch(Exception ee){
				logger.error("Error while inserting the LLPCIN Look Up Response data:" + ee);
			}
		}
		}
		private void insertLLPCINLookUpRequest(CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest) {
			Long count = 0l;
			try {
				 count= Long.parseLong(getSession().save(companyLLPCINLookUpRequest.getPayload()).toString());
				 
				 
				}
			 catch (Exception e) {
				 e.printStackTrace();
				logger.error("Error while inserting the LLPCIN Look Up Request data:" + e);
			} 
		}
		
		public void insertAddressRequestResponse(KarzaFullReqRes karzaFullReqRes) {
			
			Long id=0l;
			Integer EID=null;
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("Address Request Response save in DB :Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					id=Long.valueOf(EID);
					logger.info("Address Request Response save in DB :End" + id);
				} catch (Exception e) {
					logger.error("There is error while saving data in Address Request Response "+ e);
				}
			}

			try {
				AddressRequest addressRequest = karzaFullReqRes.getAddressRequest();
				if(addressRequest != null && !addressRequest.equals(""))
				{
					AddressPayload addressPayload= addressRequest.getPayload();
					if(addressPayload != null && !addressPayload.equals(""))
					{
						logger.info("Address Request save in DB individually:Start" + id);
						addressPayload.setUniqueid(id);
						addressPayload.setCorelationid(addressRequest.getHeader().getCorrelationId());
						insertAddressRequest(addressPayload);
						logger.info("Address Request save in DB individually : End"+ id);
					}
				}
			} catch (Exception e) {
				logger.error("There is error while saving data in Address Request "+ e);
			}

			try {
				AddressResponse addressResponse = karzaFullReqRes.getAddressResponse();
				if(addressResponse != null && !addressResponse.equals(""))
				{
					AddressResponsePayload addressResponsePayload = addressResponse.getPayload();
					if(addressResponsePayload != null && !addressResponsePayload.equals(""))
					{
						AddressResult addressResult = addressResponsePayload.getResult();
						if(addressResult != null && !addressResult.equals(""))
						{
							logger.info("Address Response save in DB individually:Start"+ id);
							addressResult.setCorelationid(addressResponse.getHeader().getCorrelationId());
							insertAddressResponse(addressResult,id);
							logger.info("Address Response save in DB individually:End"+ id);
						}
					}
				}
			} catch (Exception e) {
				logger.error("There is error while saving data in Address Response "+ e);
			}
				
		}
		
		private int insertAddressResponse(AddressResult addressResult, Long id) {
			int count = 0;
			try {
				addressResult.setUniqueid(id);
				long eid= (Long) getSession().save(addressResult);
				
				
				
				count=Integer.parseInt(""+eid);
				}
				catch(Exception e)
				{
					logger.error("Error while inserting the Address response data query:" + e);
				
				}
			return count;
		}
		private int insertAddressRequest(AddressPayload addressPayload) {
			int count = 0;
			try {
				 Long id= (Long) getSession().save(addressPayload);
				 
				 
				 count=Integer.parseInt(""+id);	
			}
			 catch (Exception e) {
				 e.printStackTrace();
				logger.error("Error while inserting the KARZA RES REQ data:" + e);
			} 
			return count;
		}

		public int insertMCASignatureRequestResponse(KarzaFullReqRes karzaFullReqRes){
			Long id=0l;
			Integer EID=null;
			if(karzaFullReqRes.getReqRes()!=null) {
				try {
					logger.info("MCA Signature Request Response save in DB :Start");
					EID=insertKarzaRequestResponse(karzaFullReqRes.getReqRes());
					logger.info("MCA Signature Request Response save in DB :End");
					id=Long.valueOf(EID);
				} catch (Exception e) {
				}
			}
			if(karzaFullReqRes.getMcaSignatureRequest()!=null && id>0) {
				try {
					String corelationid=karzaFullReqRes.getReqRes().getCorelationid();
					karzaFullReqRes.getMcaSignatureRequest().getPayload().setUniqueid(id);
					karzaFullReqRes.getMcaSignatureRequest().getPayload().setCorelationid(corelationid);
					logger.info("MCA Signature Request save in DB individually :Start");
					insertMcaSignatureRequest(karzaFullReqRes.getMcaSignatureRequest());
					logger.info("MCA Signature Request save in DB individually:End");
				} catch (Exception e) {
				}
			}
			if(karzaFullReqRes.getMcaSignatureResponse()!=null && id>0) {
				try {
					logger.info("MCA Signature Response save in DB individually:Start");
					insertMcaSignatureResponse(karzaFullReqRes.getMcaSignatureResponse(),id);
					logger.info("MCA Signature Response save in DB individually:End");
				} catch (Exception e) {
				}
			}
			return 0;
		}
		private void insertMcaSignatureResponse(MCASignatureResponse mcaSignatureResponse,Long id) {
			int count = 0;
			
			List<MCASignatureResult> mcaSignatureResults =  mcaSignatureResponse.getPayload().getResult();
			try {
				for (MCASignatureResult mcaSignatureResult :mcaSignatureResults) {
					mcaSignatureResult.setUniqueid(id.toString());
					Long count1= Long.parseLong(getSession().save(mcaSignatureResult).toString());
					
					
				}
			}
				catch(Exception e)
				{
					e.printStackTrace();
				logger.error("Error while executing query:" + e);
				
				}
			}
		private void insertMcaSignatureRequest(MCASignatureRequest mcaSignatureRequest) {
			Long count = 0l;
			try {
				 count= Long.parseLong( getSession().save(mcaSignatureRequest.getPayload()).toString());
				 
				 
				
				}
			 catch (Exception e) {
				 e.printStackTrace();
				logger.error("Error while inserting the KARZA RES REQ data:" + e);
			} 
		}



        @Override
      		public void insertAllinICWAIMembershipResponse(KarzaFullReqRes karzaFullReqRes){
      			int count=0;
      			KarzaReqRes rq=karzaFullReqRes.getReqRes();
      			if(rq!=null) {
      				try {
      					logger.info("CWAIMembership Request response save in DB : Start");
      				    count=insertKarzaRequestResponse(rq);
      				    logger.info("CWAIMembership Request response save in DB : End : " +count);
      				} catch (Exception e) {
      					logger.error("There is error while saving data in CWAIMembership Request Response "+e);
      				}
      			}
                    ICWAIMembershipRequest icwaiMembershipRequest=karzaFullReqRes.getIcwaiMembershipRequest();
      			if(icwaiMembershipRequest!=null) {
      				try {
      					logger.info("CWAIMembership Request save in DB individually : Start");
      					ICWAIMembershipPayload icwaiMembershipPayload=icwaiMembershipRequest.getPayload();
      					icwaiMembershipPayload.setUniqueid(""+count);
      					icwaiMembershipPayload.setCorrelationid(icwaiMembershipRequest.getHeader().getCorrelationId());
      					insertICWAIMembershipRequest(icwaiMembershipPayload);
      					logger.info("CWAIMembership Request save in DB individually : "+ count);
      				} catch (Exception e) {
      					logger.error("There is error while saving data in CWAIMembership Request "+ e);
      				}
      			}   
      				ICWAIMembershipResponse icwaiMembershipResponse=karzaFullReqRes.getIcwaiMembershipResponse();
      	          if(icwaiMembershipResponse!=null) {
      	        	ICWAIMembershipResponsePayload icwaiMembershipResponsePayload=icwaiMembershipResponse.getPayload();
      	        	if(icwaiMembershipResponsePayload!=null){
      	        		 ICWAIMembershipResult icwaiMembershipResult=icwaiMembershipResponsePayload.getResult();
      	        		 if(icwaiMembershipResult!=null){
      				try {
      	  	  			logger.info("CWAIMembership Response save in DB individually : Start");
      	  	  		    icwaiMembershipResult.setUniqueid(""+count);
      	  	  		    icwaiMembershipResult.setCorrelationid(icwaiMembershipResponse.getHeader().getCorrelationId());
      	  	  		    insertICWAIMembershipResponse(icwaiMembershipResult);
      					logger.info("CWAIMembership Request response save in DB : End : " +count);
      				} catch (Exception e) {
      					logger.error("There is error while saving data in CWAIMembership Request Response "+e);
      					
      				}
      				}
      			}
      	        	}
      		}

        public int insertICWAIMembershipRequest(ICWAIMembershipPayload icwaiMembershipPayload) {
    		int count = 0;

    		try {
    			   String count1=(String) getSession().save(icwaiMembershipPayload);
    			   
    			   
    			   count=Integer.parseInt(count1);
    		} catch (Exception e) {
    			logger.error("Error while inserting the ICWAIMEMBERSHIP response payload data query:" + e);
    		} 
    		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
    		return count;
    	}

        public int insertICWAIMembershipResponse(ICWAIMembershipResult icwaiMembershipResult) {
    		int count = 0;
    		try {
    				
    			String count1=(String) getSession().save(icwaiMembershipResult);
    			
    			
    			count=Integer.parseInt(count1);
    		} catch (Exception e) {
    			logger.error("Error while inserting the ICWAI response payload data query:" + e);
    		} 
    		logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
    		return count;
    	}

		


		  @Override
	  		public void insertAllinRCRequestProcess(KarzaFullReqRes karzaFullReqRes){
	  			int count=0;
	  			KarzaReqRes rq=karzaFullReqRes.getReqRes();
	  			if(rq!=null) {
	  				try {
	  					logger.info("RC_SEARCH Request response save in DB : Start");
	  				    count=insertKarzaRequestResponse(rq);
	  				    logger.info("RC_SEARCH Request response save in DB : End : " +count);
	  				} catch (Exception e) {
	  					logger.error("There is error while saving data in RC_SEARCH Request Response "+e);
	  				}
	  			}
	                RCSearchRequest rcSearchRequest=karzaFullReqRes.getRcSearchRequest();
	  			if(rcSearchRequest!=null) {
	  				try {
	  					logger.info("RC_SEARCH Request save in DB individually : Start");
	                    RCSearchPayload rcSearchPayload=rcSearchRequest.getPayload();
	                    if(rcSearchPayload!=null){
	                    rcSearchPayload.setUniqueid(""+count);
	                    rcSearchPayload.setCorelationid(rcSearchRequest.getHeader().getCorrelationId());
	                    insertRCSearchRequest(rcSearchPayload);
	                    logger.info("RC_SEARCH Request save in DB individually : "+ count);
	                    }
	                    } catch (Exception e) {
	  					logger.error("There is error while saving data in RC_SEARCH Request "+ e);
	  				}
	  			}   
	               RCSearchResponse rcSearchResponse=karzaFullReqRes.getRcSearchResponse();
	  			if(rcSearchResponse!=null) {
	  				RCSearchResponsePayload  rcSearchResponsePayload=rcSearchResponse.getPayload();
	  				if(rcSearchResponsePayload!=null){
	  					RCSearchResult rcSearchResult=rcSearchResponsePayload.getResult();
	  				try {
	  	  	  			logger.info("RC_SEARCH Response save in DB individually : Start");
	                    rcSearchResult.setUniqueid(""+count);
	                    rcSearchResult.setCorelationid(rcSearchResponse.getHeader().getCorrelationId());
	  	  	  		    insertRCRequestProcess(rcSearchResult);
	  					logger.info("RC_SEARCH Request response save in DB : End : " +count);
	  				} catch (Exception e) {
	  					logger.error("There is error while saving data in RC_SEARCH Request Response "+e);
	  					
	  				}
	  			}
	  				}
	  		}
		  public int insertRCSearchRequest(RCSearchPayload rcSearchPayload) {
				int count = 0;
					try 
					{
						String count1=(String) getSession().save(rcSearchPayload);
						
						
					    count=Integer.parseInt(count1);
					} 
					catch (Exception e)
					{
						logger.error("Error while inserting the RC request data:" + e);
					}
					logger.debug("Successfully closed Resources DBConnection in insertRCSearchRequest Method:- ");
					return count;
				
				}


			public int insertRCRequestProcess(RCSearchResult rcSearchResult) {

				int count = 0;
				try {
					String count1=(String) getSession().save(rcSearchResult);
					
					
					count=Integer.parseInt(count1);
					
				} catch (Exception e) {
					logger.error("Error while inserting the RC request data:" + e);
				}
				logger.debug("Successfully closed Resources DBConnection in insertRCRequestProcess Method:- ");
		   		return count;
			}




			@Override
			public int insertBankAccountRequestResponse(KarzaFullReqRes fullReqRes) {
				Integer EID = null;
				try {
					KarzaReqRes rq = fullReqRes.getReqRes();
					logger.info("BANKACC Request response save in DB : Start");
					EID = insertKarzaRequestResponse(rq);
					logger.info("BANKACC Request response save in DB : End : " + EID);
					final Long UId = Long.valueOf(EID);
				} catch (Exception ec) {
					logger.error("There is error while saving data in BANKACC Request Response "+ ec);
				}
				try {
					BankAccountRequest bankAccountRequest = fullReqRes.getBankAccountRequest();
					if (bankAccountRequest != null && !bankAccountRequest.equals("")) {
						BankAccountPayload bankAccountPayload= bankAccountRequest.getPayload();
						if (bankAccountPayload != null && !bankAccountPayload.equals("")) {
							bankAccountPayload.setUniqueid("" + EID);
							bankAccountPayload.setCorelationid(bankAccountRequest.getHeader().getCorrelationId());
							logger.info("BANKACC save in DB individually : Start");
							int count = insertBankAccountRequest(bankAccountPayload);
							logger.info("BANKACC Request save in DB individually : "+ count);
						}
					}
				} catch (Exception ec) {
					logger.error("There is error while saving data in BANKACC Request "+ ec);
				}
				try {
					logger.info("BANKACC Response save in DB individually : Start");
					BankAccountResponse bankAccountResponse = fullReqRes.getBankAccountResponse();
					if (bankAccountResponse != null && !bankAccountResponse.equals("")) {
						BankAccountResponsePayload bankAccountResponsePayload= bankAccountResponse.getPayload();
						if ( bankAccountResponsePayload!= null && !bankAccountResponsePayload.equals("")) {
							BankAccountResult bankAccountResult =bankAccountResponsePayload.getResult();
							if(bankAccountResult != null && !bankAccountResult.equals(""))
								bankAccountResult.setUniqueid("" + EID);
							bankAccountResult.setCorelationid(bankAccountResponse.getHeader().getCorrelationId());
							logger.info("BANKACC save in DB individually : Start");
							int count =insertBankAccountResponse(bankAccountResult);
							logger.info("BANKACC Request save in DB individually : "+ count);
						}
						}
				} catch (Exception ec) {
					logger.error("There is error while saving data in BANKACC Response "
							+ ec);
				}
				return EID;
			}
			public int insertBankAccountRequest(BankAccountPayload bankAccountPayload) 
			{
				int count = 0;
				
				try 
				{
					String id=(String) getSession().save(bankAccountPayload);
					
					
					count=Integer.parseInt(id);
				} 
				catch (Exception e)
				{
					logger.error("Error while inserting the QCIB_K_BankAccount_REQ data:" + e);
				}
				
						logger.debug("Successfully closed Resources DBConnection in insertBankAccountRequest Method:- ");
					
				return count;
			}

			public int insertBankAccountResponse(BankAccountResult bankAccountResult)
			{
				int count = 0;
				
				try {
					String id=(String) getSession().save(bankAccountResult);
					
					
					count=Integer.parseInt(id);
				} catch (Exception e) {
					logger.error("Error while inserting the QCIB_K_BANKACCOUNT_RES data query:" + e);
				}
						logger.debug("Successfully closed Resources DBConnection in insertBankAccountResponse Method:- ");
					
				return count;
				}




			@Override
			public void insertLpg2RequestResponse(KarzaFullReqRes fullReqRes) {
				Integer EID = null;
				try {
					KarzaReqRes rq = fullReqRes.getReqRes();
					logger.info("LPG2 Request response save in DB : Start");
					EID = insertKarzaRequestResponse(rq);
					logger.info("LPG2 Request response save in DB : End : " + EID);
					final Long UId = Long.valueOf(EID);
				} catch (Exception ec) {
					logger.error("There is error while saving data in LPG2 Request Response "+ ec);
				}
				try {
					LpgRequest2 lpgRequest2= fullReqRes.getLpgRequest2();
					if (lpgRequest2 != null && !lpgRequest2.equals("")) {
						LpgPayload2 lpgPayload2= lpgRequest2.getPayload();
						if (lpgPayload2 != null && !lpgPayload2.equals("")) {
							lpgPayload2.setUniqueid("" + EID);
							lpgPayload2.setCorelationid(lpgRequest2.getHeader().getCorrelationId());
							logger.info("LPG2 save in DB individually : Start");
							int count =  insertLpgRequest2(lpgPayload2);
							logger.info("LPG2 Request save in DB individually : "+ count);
						}
					}
				} catch (Exception ec) {
					logger.error("There is error while saving data in LPG2 Request "+ ec);
				}
				try {
					logger.info("LPG2 Response save in DB individually : Start");
					LpgResponse2 lpgResponse2 = fullReqRes.getLpgRes2();
					if (lpgResponse2 != null && !lpgResponse2.equals("")) {
						LpgResponsePayload2 lpgResponsePayload2= lpgResponse2.getPayload();
						if ( lpgResponsePayload2!= null && !lpgResponsePayload2.equals("")) {
							LpgResult2 lpgResult2 =lpgResponsePayload2.getResult();
							if(lpgResult2 != null && !lpgResult2.equals(""))
								lpgResult2.setUniqueid("" + EID);
							logger.info("LPG2 save in DB individually : Start");
							int count = insertLpgResponse2(lpgResult2);
							logger.info("LPG2 Request save in DB individually : "+ count);
						}
						}
				} catch (Exception ec) {
					logger.error("There is error while saving data in LPG2 Response "+ ec);
				}
			
			}
			

			public int insertLpgRequest2(LpgPayload2 lpgPayload2) {
				int count = 0;
			
				try {
					String id=(String) getSession().save(lpgPayload2);
					
					
					
				} catch (Exception e) {
					logger.error("Error while inserting the LPG2 request data:" + e);
				}

						logger.debug("Successfully closed Resources DBConnection in insertLpgRequest2 Method:- ");
					
				return count;
			}
			public int insertLpgResponse2(LpgResult2 lpgResult2) {

				int count = 0;
				Connection con = null;
				PreparedStatement pstmt = null;

				try {
					String id=(String) getSession().save(lpgResult2);
					
					
					count=Integer.parseInt(id);
				} catch (Exception e) {
					logger.error("Error while inserting the QCIB_K_LPG_KYC_RES response data:" + e);
				} 
						logger.debug("Successfully closed Resources DBConnection in insertLpgResponse2 Method:- ");
					
				return count;

			}


			public String getPdfName(String serviceTag)
			{
				logger.info("Comes to DBConnection in getPdfName Method Start: ");
				String pdfName = null;
				try
				{
						Criteria criteria=  getSession().createCriteria(ServiceMaster.class).add(Restrictions.eq("service_tag", serviceTag));
						logger.info("SQL query to be executed : " );
						try
						{
						
							Object results=  criteria.uniqueResult();
							if (results != null )
							{
								pdfName=((ServiceMaster)results).getPdf_name();
							}
						} 
						catch (Exception e) 
						{
							logger.error("Error in fetching results "+e );
						}
					
				}
				catch (Exception e)
				{
					logger.error("DBConnectionKarza || getPdfName() || Some exception occured while fetching  Value : " + e);

				} 
				
				logger.info("Getting out from DBConnection in getPdfName Method : ");
				return pdfName;
			}




			@Override
			public void insertEmolpyerLookupRequestResponse(KarzaFullReqRes fullReqRes) {
				Integer EID = null;
				try {
					KarzaReqRes rq = fullReqRes.getReqRes();
					logger.info("EMOLPYERLOOKUP Request response save in DB : Start");
					EID = insertKarzaRequestResponse(rq);
					logger.info("EMOLPYERLOOKUP Request response save in DB : End : " + EID);
					final Long UId = Long.valueOf(EID);
				} catch (Exception ec) {
					logger.error("There is error while saving data in EMOLPYERLOOKUP Request Response "+ ec);
				}
				try {
					EmolpyerLookupRequest emolpyerLookupRequest = fullReqRes.getEmployerLookupRequest();
					if (emolpyerLookupRequest != null && !emolpyerLookupRequest.equals("")) {
						EmployerLookupPayload employerLookupPayload= emolpyerLookupRequest.getPayload();
						if (employerLookupPayload != null && !employerLookupPayload.equals("")) {
							employerLookupPayload.setUniqueid("" + EID);
							employerLookupPayload.setCorelationid(emolpyerLookupRequest.getHeader().getCorrelationId());
							logger.info("EMOLPYERLOOKUP save in DB individually : Start");
							int count =   insertEmolpyerLookupRequest(employerLookupPayload);
							logger.info("EMOLPYERLOOKUP Request save in DB individually : "+ count);
						}
					}
				} catch (Exception ec) {
					logger.error("There is error while saving data in EMOLPYERLOOKUP Request "+ ec);
				}
				try {
					logger.info("EMOLPYERLOOKUP Response save in DB individually : Start");
					EmployerLookupResponse employerLookupResponse = fullReqRes.getEmployerLookupResponse();
					
							logger.info("EMOLPYERLOOKUP save in DB individually : Start");
							int count = insertEmployerLookupResponse(employerLookupResponse,EID);
							logger.info("EMOLPYERLOOKUP Request save in DB individually : "+ count);
						
						
				} catch (Exception ec) {
					logger.error("There is error while saving data in EMOLPYERLOOKUP Response "+ ec);
				}
					
			}
		
			
			public int insertEmployerLookupResponse(EmployerLookupResponse employerLookupResponse,int EMPLOYER_UNIQUE_ID) 
			{
				int count = 0;
				
				try {
					if(employerLookupResponse!=null && !employerLookupResponse.equals("")){
						EmployerLookupResponsePayload employerLookupResponsePayload = employerLookupResponse.getPayload();
						if(employerLookupResponsePayload!=null && !employerLookupResponsePayload.equals("")){
							List<EmployerLookupResult> employerLookupResultlist= employerLookupResponsePayload.getResult();
							if(employerLookupResultlist!=null && employerLookupResultlist.size()>0){
								for(EmployerLookupResult employerLookupResult : employerLookupResultlist ){
									
									employerLookupResult.setUNIQUEID(""+EMPLOYER_UNIQUE_ID);
									employerLookupResult.setCORELATIONID(employerLookupResponse.getHeader().getCorrelationId());
								count=	(Integer) getSession().save(employerLookupResult);
								
								
									
								}
							}
						}
					}
					} catch (Exception e) {

					logger.error("Error while inserting the QCIB_K_EMPLOYERLOOKUP_RES response payload data query:" + e);
				} 
						logger.debug("Successfully closed Resources DBConnection in insertEmployerLookupResponse Method:- ");
					
				return count;
			}
			
			public int insertEmolpyerLookupRequest(EmployerLookupPayload employerLookupPayload)
			{
				int count = 0;
				

				try {
					String id=(String) getSession().save(employerLookupPayload);
					
					
					count=Integer.parseInt(id);
					
					
				} catch (Exception e) {
					logger.error("Error while inserting the EMPLOYER LOOKUP request data:" + e);
				}

						logger.debug("Successfully closed Resources DBConnection in insertEmolpyerLookupRequest Method:- ");
					
				return count;
			
			}




			@Override
			public void insertUanLookupRequestResponse(KarzaFullReqRes fullReqRes) {
				Integer EID = null;
				try {
					KarzaReqRes rq = fullReqRes.getReqRes();
					logger.info("UANLOOKUP Request response save in DB : Start");
					EID = insertKarzaRequestResponse(rq);
					logger.info("UANLOOKUP Request response save in DB : End : " + EID);
					final Long UId = Long.valueOf(EID);
				} catch (Exception ec) {
					logger.error("There is error while saving data in UANLOOKUP Request Response "+ ec);
				}
				try {
					UanLookupRequest uanLookupRequest= fullReqRes.getUanLookupRequest();
					if (uanLookupRequest != null && !uanLookupRequest.equals("")) {
						UanLookupPayload uanLookupPayload = uanLookupRequest.getPayload();
						if (uanLookupPayload != null && !uanLookupPayload.equals("")) {
							uanLookupPayload.setUniqueid("" + EID);
							uanLookupPayload.setCorelationid(uanLookupRequest.getHeader().getCorrelationId());
							logger.info("UANLOOKUP save in DB individually : Start");
							int count =   insertUanLookupRequest(uanLookupPayload);
							logger.info("UANLOOKUP Request save in DB individually : "+ count);
						}
					}
				} catch (Exception ec) {
					logger.error("There is error while saving data in UANLOOKUP Request "+ ec);
				}
				try {
					logger.info("UANLOOKUP Response save in DB individually : Start");
					UanLookupResponse uanLookupResponse = fullReqRes.getUanLookupResponse();
					if (uanLookupResponse != null && !uanLookupResponse.equals("")) {
						UanLookupResponsePayload uanLookupResponsePayload = uanLookupResponse.getPayload();
						if ( uanLookupResponsePayload!= null && !uanLookupResponsePayload.equals("")) {
							UanLookupResult uanLookupResult =uanLookupResponsePayload.getResult();
							if(uanLookupResult != null && !uanLookupResult.equals(""))
								uanLookupResult.setUniqueid("" + EID);
							uanLookupResult.setCorelationid(uanLookupResponse.getHeader().getCorrelationId());
							logger.info("UANLOOKUP save in DB individually : Start");
							int count = insertUanLookupResponse(uanLookupResult);
							logger.info("UANLOOKUP Request save in DB individually : "+ count);
						}
						}
				} catch (Exception ec) {
					logger.error("There is error while saving data in LPG2 Response "+ ec);
				}
			}

			public int insertUanLookupRequest(UanLookupPayload uanLookupPayload)
			{
				int count = 0;
				try {
					String id=(String) getSession().save(uanLookupPayload);
					
					
					
					count=Integer.parseInt(id);
					
				} catch (Exception e) {
					logger.error("Error while inserting the UAN Lookup request data:" + e);
				}
						logger.debug("Successfully closed Resources DBConnection in insertUanLookupRequest Method:- ");
					
				return count;
			}

			public int insertUanLookupResponse(UanLookupResult uanLookupResult) 
			{
				int count = 0;
				

				try {
					
					String id=(String) getSession().save(uanLookupResult);
					
					
					count =Integer.parseInt(id);
				} catch (Exception e) {

					logger.error("Error while inserting the QCIB_K_UANLOOKUP_RES response payload data query:" + e);
				} 
						logger.debug("Successfully closed Resources DBConnection in insertUanLookupResponse Method:- ");
					
				return count;
			
			
			}
			
			
			public void insertWebsiteDomainRequestResponse(KarzaFullReqRes fullReqRes) {

				Integer EID = null;
				long UId=0l;
				try {
					KarzaReqRes rq = fullReqRes.getReqRes();
					logger.info("Website Domain Request response save in DB : Start");
					EID = insertKarzaRequestResponse(rq);
					logger.info("Website Domain Request response save in DB : End : " + EID);
					 UId = Long.valueOf(EID);
				} catch (Exception ec) {
					logger.error("There is error while saving data in Website Domain Request Response "+ ec);
				}
				
				try {
					WebsiteDomainRequest websiteDomainRequest= fullReqRes.getWebsiteDomainRequest();
					if (websiteDomainRequest != null && !websiteDomainRequest.equals("")) {
						com.qualtech.karza.api.request.WebsiteDomainPayload  websiteDomainPayload=websiteDomainRequest.getPayload();
						if (websiteDomainPayload != null && !websiteDomainPayload.equals("")) {
							logger.info("Website Domain Request save in DB individually : Start");
							websiteDomainPayload.setUniqueid(UId);
							websiteDomainPayload.setCorelationid(websiteDomainRequest.getHeader().getCorrelationId());
							int count =  insertWebsiteDomainRequest(websiteDomainPayload);
							logger.info("Website Domain Request save in DB individually : End"+ count);
						}
					}
				} catch (Exception ec) {
					ec.printStackTrace();
					logger.error("There is error while saving data in Website Domain Request "+ ec);
				}
				
				try {
					logger.info("Website Domain Response save in DB individually : Start");
					WebsiteDomainResponse websiteDomainResponse = fullReqRes.getWebsiteDomainResponse();
					if (websiteDomainResponse != null && !websiteDomainResponse.equals("")) {
						com.qualtech.karza.api.response.WebsiteDomainPayload websiteDomainPayload=websiteDomainResponse.getPayload();
						if(websiteDomainPayload!=null && !websiteDomainPayload.equals("")){
							WebsiteDomainResult websiteDomainResult=websiteDomainPayload.getResult();
							if(websiteDomainResult!=null && !websiteDomainResult.equals("")){
								websiteDomainResult.setCorelationid(websiteDomainResponse.getHeader().getCorrelationId());
							insertWebsiteDomainResponse(websiteDomainResult,UId);
						}}}
				} catch (Exception ec) {
					logger.error("There is error while saving data in Website Domain Response "+ ec);
				}
			}

			private int insertWebsiteDomainRequest(com.qualtech.karza.api.request.WebsiteDomainPayload websiteDomainPayload) {
				int count = 0;
			
				try {
					long id= (Long) getSession().save(websiteDomainPayload);
					
					
					
					count=Integer.parseInt(""+id);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while inserting the Website Domain request data:" + e);
				}

						logger.debug("Successfully closed Resources DBConnection in insertWebsiteDomainRequest Method:- ");
					
				return count;
			}

			public int insertWebsiteDomainResponse(WebsiteDomainResult websiteDomainResult, Long KARZA_UNIQUE_ID) 
			{
				int count = 0;

				try 
				{      
					websiteDomainResult.setUniqueid(KARZA_UNIQUE_ID);
					long id= (Long) getSession().save(websiteDomainResult);
					
					
					count=Integer.parseInt(""+id);
				}
				catch (Exception e)
				{
					logger.error("Error while inserting the Website Domain response payload data query:" + e);
				}
				
				/*WebsiteDomain websiteDomain =websiteDomainResult.getDomain();
				if ( websiteDomain!= null)
				{
					try
					{     
						websiteDomain.setUniqueid(KARZA_UNIQUE_ID);
						long id=(Long) getSession().save(websiteDomain);
					}
					
					catch (Exception e) 
					{
						logger.error("Error while inserting the Website Domain Domain detail query:" + e);
					}
					
				}*/
				
				
			/*	WebsiteDomainAdmin websiteDomainAdmin=websiteDomainResult.getAdmin();
				if ( websiteDomainAdmin!= null)
				{
					try
					{     
						websiteDomainAdmin.setUniqueid(KARZA_UNIQUE_ID);
						long id=(Long) getSession().save(websiteDomainAdmin);
					}
					
					catch (Exception e) 
					{
						logger.error("Error while inserting the Website Domain Admin detail query:" + e);
					}
					
				}*/			
		/*			WebsiteDomainTech websiteDomainTech=websiteDomainResult.getTech();		

				if (websiteDomainTech!=null)
				{
					try
					{
						websiteDomainTech.setUniqueid(KARZA_UNIQUE_ID);
						long id= (Long) getSession().save(websiteDomainTech);
					}
					
					catch (Exception e) 
					{
						logger.error("Error while inserting the Website Domain Tech detail query:" + e);
					}
				}*/
		
				
				/*WebsiteDomainRegistry websiteDomainRegistry= websiteDomainResult.getRegistry();
				if (websiteDomainRegistry != null)
				{
					try
					{
						websiteDomainRegistry.setUniqueid(KARZA_UNIQUE_ID);
						long id= (Long) getSession().save(websiteDomainRegistry);
					}
					catch (Exception e) {
								logger.error("Error while executing QCIB_K_WEBDOMAIN_REGISTRY_RES query:" + e);
							}
				}*/

			
				
				/*WebsiteDomainRegistrar websiteDomainRegistrar= websiteDomainResult.getRegistrar();
				if (websiteDomainRegistrar != null)
				{
					try
					{	
						websiteDomainRegistrar.setUniqueid(KARZA_UNIQUE_ID);
						long id= (Long) getSession().save(websiteDomainRegistrar);
					}
					catch (Exception e) 
					{
						logger.error("Error while inserting the Website Domain Registrar detail query:" + e);
					}
					
				}*/
				
				
				
				/*WebsiteDomainNameServer websiteDomainNameServer=websiteDomainResult.getNameserver();
				if (websiteDomainNameServer != null)
				{
					try
					{
						websiteDomainNameServer.setUniqueid(KARZA_UNIQUE_ID);
						long id= (Long) getSession().save(websiteDomainNameServer);
					}
					catch (Exception e) 
					{
						logger.error("Error while inserting the Website Domain NameServer detail query:" + e);
					}
				}*/	

					
				/*	WebsiteDomainRegistrant websiteDomainRegistrant=websiteDomainResult.getRegistrant();
				
				if (websiteDomainRegistrant != null)
				{
					try
					{
						websiteDomainRegistrant.setUniqueid(KARZA_UNIQUE_ID);
						long id=(Long) getSession().save(websiteDomainRegistrant);
					}
					catch (Exception e) 
					{
						logger.error("Error while inserting the Website Domain Registrant detail query:" + e);
					}
					
				}*/
				
				return count;
			
			}
			
			@Override
			public void insertKycOcrRequestResponse(KarzaFullReqRes fullReqRes) {
				
				Integer EID = null;
				long UId=0l;
				try {
					KarzaReqRes rq = fullReqRes.getReqRes();
					logger.info("Kyc Ocr Request response save in DB : Start");
					EID = insertKarzaRequestResponse(rq);
					logger.info("Kyc Ocr Request response save in DB : End : " + EID);
					 UId = Long.valueOf(EID);
				} catch (Exception ec) {
					ec.printStackTrace();
					logger.error("There is error while saving data in KycOcr Request Response "+ ec);
				}
				
				try {
					KycOcrRequest ocrRequest= fullReqRes.getOcrRequest();
					if (ocrRequest != null && !ocrRequest.equals("")) {

						KycOcrReqPayload  payload=ocrRequest.getPayload();
						if (payload != null && !payload.equals("")) {
							logger.info("Kyc Ocr Request save in DB individually : Start");
							payload.setUniqueid(UId);
							payload.setCorelationid(ocrRequest.getHeader().getCorrelationId());
							int count =  insertKycOcrRequest(payload);
							logger.info("Kyc Ocr Request save in DB individually : End"+ count);
						}
					}
				} catch (Exception ec) {
					ec.printStackTrace();
					logger.error("There is error while saving data in KycOcr Request "+ ec);
				}
				
				try {
					logger.info("KycOcr Response save in DB individually : Start");
					KycOcrResponse kycOcrResponse=fullReqRes.getOcrResponse();
					if (kycOcrResponse != null && !kycOcrResponse.equals("")) {
						com.qualtech.karza.api.response.KycOcrResponsePayload kycOcrResponsePayload=kycOcrResponse.getPayload();
						if(kycOcrResponsePayload!=null && !kycOcrResponsePayload.equals("")){
							KycOcrResResult kycOcrResResult=kycOcrResponsePayload.getResult();
							if(kycOcrResResult!=null && !kycOcrResResult.equals("")){
							insertKycOcrResponse(kycOcrResResult,UId);
						}}}
				} catch (Exception ec) {
					logger.error("There is error while saving data in LPG2 Response "+ ec);
				}
			}

			private void insertKycOcrResponse(KycOcrResResult kycOcrResResult, long uId) {
				
				long count = 0;
				try {
					if(kycOcrResResult!=null && kycOcrResResult.getFront()!=null 
							&& kycOcrResResult.getFront_top()!=null && kycOcrResResult.getBack()!=null){
						
						if(kycOcrResResult.getFront()!=null  ){
							try {
							kycOcrResResult.setUniqueid(uId);
							count=(Long)sessionFactory.getObject().getCurrentSession().save(kycOcrResResult);
							sessionFactory.getObject().getCurrentSession().getTransaction().commit();
							}
							catch(Exception e) {
								logger.error("Error while inserting the KycOcr Front response payload data query:" + e);
							}
						}
				
					}
							
				}
			 catch (Exception e) {

				logger.error("Error while inserting the QCIB_K_KYC_OCR_RES response payload data query:" + e);
			} 
}
			
			
			private int insertKycOcrRequest(KycOcrReqPayload payload) {
				int count = 0;
				try {
					long id= (Long) sessionFactory.getObject().getCurrentSession().save(payload);
					sessionFactory.getObject().getCurrentSession().getTransaction().commit();
					sessionFactory.getObject().getCurrentSession().getTransaction().begin();
					count=Integer.parseInt(""+id);
				} catch (Exception e) {
					logger.error("Error while inserting the KycOcr Back response payload data query:" + e);
				}
				return count;
			}

}
