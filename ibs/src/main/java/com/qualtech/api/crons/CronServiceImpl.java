package com.qualtech.api.crons;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.codec.Base64;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.ibs.util.ErrorInfo;
import com.qualtech.api.ibs.util.Header;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.api.ibs.util.ResquestStatus;
import com.qualtech.cibil.api.response.CibilAPIResponse;
import com.qualtech.cibilv2.api.response.CibilAPIResponse2;
import com.qualtech.creditvidya.api.response.EmailSaveResponse;
import com.qualtech.creditvidya.api.response.EmailVerificationResponse;
import com.qualtech.crif.api.response.CriffApiResponse;
import com.qualtech.equifax.api.response.EquifaxApiResponse;
import com.qualtech.experiankickoff.api.response.AuthExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianGenQuestionResponse;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponse;
import com.qualtech.experiankickoff.api.response.ExperianResponse;
import com.qualtech.finbit.api.response.FinbitAPIResponse;
import com.qualtech.hdfc.api.response.ApiResponse;
import com.qualtech.karza.api.response.AadharResponse;
import com.qualtech.karza.api.response.AddressResponse;
import com.qualtech.karza.api.response.BankAccountResponse;
import com.qualtech.karza.api.response.CAMemberShipAuthResponse;
import com.qualtech.karza.api.response.CompSearchResponse;
import com.qualtech.karza.api.response.CompanyLLPCINLookUpResponse;
import com.qualtech.karza.api.response.CompanyLLPIdentificationResponse;
import com.qualtech.karza.api.response.DlResponse;
import com.qualtech.karza.api.response.DlResponse2;
import com.qualtech.karza.api.response.EPFAuthOTPResponse;
import com.qualtech.karza.api.response.EPFAuthPassBookResponse;
import com.qualtech.karza.api.response.ESICIdResponse;
import com.qualtech.karza.api.response.ElectricityResponse;
import com.qualtech.karza.api.response.ElectricityResponse2;
import com.qualtech.karza.api.response.EmailAuthResponse;
import com.qualtech.karza.api.response.EmployerLookupResponse;
import com.qualtech.karza.api.response.FSSAILicenceResponse;
import com.qualtech.karza.api.response.Form16QuatResponse;
import com.qualtech.karza.api.response.Form16Response;
import com.qualtech.karza.api.response.GSTAuthenticationResponse;
import com.qualtech.karza.api.response.GstIdentificationResponse;
import com.qualtech.karza.api.response.HSNResponse;
import com.qualtech.karza.api.response.ICSIMemberShipResponse;
import com.qualtech.karza.api.response.ICWAIFirmAuthResponse;
import com.qualtech.karza.api.response.ICWAIMembershipResponse;
import com.qualtech.karza.api.response.IECResponse;
import com.qualtech.karza.api.response.IFSCResponse;
import com.qualtech.karza.api.response.ITRAuthResponse;
import com.qualtech.karza.api.response.LpgResponse2;
import com.qualtech.karza.api.response.MCASignatureResponse;
import com.qualtech.karza.api.response.NREGAResponse;
import com.qualtech.karza.api.response.NameSimilarityResponse;
import com.qualtech.karza.api.response.PanResponse;
import com.qualtech.karza.api.response.PngResponse;
import com.qualtech.karza.api.response.RCAuthResponse;
import com.qualtech.karza.api.response.RCSearchResponse;
import com.qualtech.karza.api.response.ShopEstablishmentResponse;
import com.qualtech.karza.api.response.TanResponse;
import com.qualtech.karza.api.response.TelephoneResponse2;
import com.qualtech.karza.api.response.UanLookupResponse;
import com.qualtech.karza.api.response.UdyogAadharResponse;
import com.qualtech.karza.api.response.VoterResponse;
import com.qualtech.karza.api.response.WebsiteDomainResponse;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakReversalResponse;
import com.qualtech.multibureau.api.response.BureauResponse;
import com.qualtech.pan.api.request.PanApiRequest;
import com.qualtech.pan.api.response.PanValidationRes;

@Service
public class CronServiceImpl implements CronService {

	static Logger logger = Logger.getLogger(CronService.class.getName());
	@Autowired
	private CronsDBConnection dbConnection;
	@Autowired
	private PropertyFile resProp;
	private CronUtils util = new CronUtils();
	private ObjectMapper om = new ObjectMapper();




	@Override	
	public String ibsService(String request) {

		RequestIBs req = new RequestIBs();
		try {
			req = om.readValue(request, RequestIBs.class);
		} catch (Exception e1) {

			logger.error(" CronServiceImpl || ibsService() || Accessing Request :: "+e1);
		}
		Header header=req.getHeader();
		List<String> servicelist = header.getServicename();
		String uniqueId = header.getTid();
		try {
			if (uniqueId != null && !uniqueId.equals("")) {
				Map<String, String> hm = dbConnection.getSaveInitialRequest(servicelist, uniqueId);
				if (hm.size() > 0) {
					boolean flag = dbConnection.retriveRequest(servicelist, uniqueId, hm,req.getPayload());
					if (flag) {
						StringBuilder sb = new StringBuilder();
						Set<String> set = hm.keySet();
						int i = 0;
						for (String str : set) {
							i++;
							if (i == set.size()) {
								sb.append(str);
							} else {
								sb.append(str + ",");
							}
						}
						return "Request Initiated for " + sb;
					}
				}

			}
		} catch (Exception e) {
			logger.error(" CronServiceImpl || ibsService() || Accessing getSaveInitialRequest() or retriveRequest() :: "+e);
		}
		return "Kindly initiated request with new transaction Id";

	}

	@Override
	public void cronService() 
	{
		logger.info(" CronServiceImpl || cronService() :: Start");
		List<IbRequestMaster> results = dbConnection.loadRequest();
		for (final IbRequestMaster result : results)
		{

			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						NDC.push("IBS_Cron "+result.getService_provider()+" "+result.getCorrelationid());
						logger.info("CronServiceImpl || cronService()  || Inside Service serviceCall thread : Start");
						serviceCall(result);
					}
					catch(Exception ex)
					{

					}
					finally
					{
						logger.info("CronServiceImpl || cronService()  || Inside Service serviceCall thread : End");
						NDC.pop();
					}
				}

			}).start();

		}
	}

	private void serviceCall(IbRequestMaster result)
	{
		String jsonRequest = "";
		String url="";
		CronInfo info = null;
		String processing="PROCESSING";
		try 
		{
			ResourceBundle res=ResourceBundle.getBundle("application");

			try {
				String ip = InetAddress.getLocalHost().getHostAddress().toString();
				if(ip.equals(res.getString("ibs.app.server.ip"))){
					url = resProp.getString("com.qc.liveURL");
				}else {
					url=res.getString("localURL");
				}
			} catch (UnknownHostException e) {
				logger.error("Exception from live url:: " + e);
			}
			util.setResProp(resProp);
			if (result.getService_provider().contains("PERFIOS"))
			{
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				/*if (result.getService_provider().equals("CRIF MFI")) 
				{
					url = url + "/ib/crif/api/v1/crifrequestMFI";
				}
				else if (result.getService_provider().equals("CRIF PCS")) 
				{
					url = url + "/ib/crif/api/v1/crifrequest";
				}
				jsonRequest = util.getCriffPcsRequest(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+" url : " + url);
				//// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);*/

				try 
				{
					perfiosProcess(result, info);
				}
				catch (Exception e) 
				{
					logger.error("Exception in CronServiceImpl || serviceCall () ||  unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("HDFC"))
			{
				result.setRequest_status(processing);
				logger.info(" calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/hdfc/api/v1/requestHdfc";
				jsonRequest = util.getHdfcRequest(result,dbConnection.getIbsNomineeDetail(result.getCorrelationid()));
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try
				{
					hdfcProcess(result,info);
				}
				catch (Exception e)
				{
					logger.error(" unable to process the "+result.getService_provider()+"service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("CRIF"))
			{
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				if (result.getService_provider().equals("CRIF MFI")) 
				{
					url = url + "/ib/crif/api/v1/crifrequestMFI";
				}
				else if (result.getService_provider().equals("CRIF PCS")) 
				{
					url = url + "/ib/crif/api/v1/crifrequest";
				}
				jsonRequest = util.getCriffPcsRequest(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+" url : " + url);
				//// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);

				try 
				{
					crifProcess(result, info);
				}
				catch (Exception e) 
				{
					logger.error("Exception in CronServiceImpl || serviceCall () ||  unable to process the service response :: " + e);
				}

			} 
			else if (result.getService_provider().contains("EQUIFAX")) 
			{
				result.setRequest_status(processing);
				logger.info(" CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				if (result.getService_provider().equals("EQUIFAX PCS")) 
				{
					url = url + "/ib/equifax/api/v1/equifaxrequest";
				} 
				else if (result.getService_provider().equals("EQUIFAX MCR")) 
				{
					url = url + "/ib/equifax/api/v1/equifaxMFIrequest";
				}
				else if (result.getService_provider().equals("EQUIFAX VID")) 
				{
					url = url + "/ib/equifax/api/v1/equifaxrequest/verificationId";
				}
				else if (result.getService_provider().equals("EQUIFAX EVDR")) 
				{
					url = url + "/ib/equifax/api/v1/equifaxrequest/enhanceVerificationId";
				}
				jsonRequest = util.getCriffPcsRequest(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service :: "+result.getService_provider()+" url : " + url);
				//// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try 
				{
					equifaxProcess(result, info);
				}
				catch (Exception e) 
				{
					logger.error(" Exception in CronServiceImpl || serviceCall () || unable to process the service response :: " + e);
				}

			} 
			else if (result.getService_provider().contains("MULTIBUREAU INDV CIR")) 
			{
				result.setRequest_status(processing);
				logger.info(" calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/multibureau/api/v1/requestMultiBureau";
				jsonRequest = util.getMultiBureauRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try
				{
					MultiBureauProcess(result, info,jsonRequest,url);
				}
				catch (Exception e)
				{
					logger.error(" unable to process the "+result.getService_provider()+"service response :: " + e);
				}

			}
			else if (result.getService_provider().equals("CIBIL")) 
			{
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);

				url = url + "/ib/cibil/api/v1/cibilRequest";
				jsonRequest = util.getCibilRequest(result);
				logger.info(" CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try 
				{
					cibilProcess(result, info);
				}
				catch (Exception e)
				{
					logger.error("Exception in CronServiceImpl || serviceCall () ||  unable to process the "+result.getService_provider()+" service response :: " + e);
				}

			}
			else if (result.getService_provider().equals("CIBIL V2")) 
			{
				result.setRequest_status(processing);
				logger.info(" CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				//http://10.1.1.202:8080/integrationbox
				url= url + "/ib/cibil/service/v2/cibilRequest";

				jsonRequest = util.getCibilV2Request(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try
				{
					CibilV2Process(result, info);
				} 
				catch (Exception e) 
				{
					logger.error("Exception in CronServiceImpl || serviceCall () || unable to process the "+result.getService_provider()+" service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("EXPERIAN KICKOFF")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/experianKickOff/api/v1/experianSingleAction";
				jsonRequest = util.getSingleActionRequest(result);
				logger.info(" CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					kickOffSingeActionProcess(result, info);
				} catch (Exception e) {
					logger.error("Exception in CronServiceImpl || serviceCall () || unable to process the "+result.getService_provider()+" service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KARZA PASSPORT V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaPassport";
				jsonRequest = util.getKarzaPassportRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaPassportProcess(result, info);
				} catch (Exception e) {
					logger.error("Exception in CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
				}
			}




			else if (result.getService_provider().contains("KARZA HSN V2")) {
				result.setRequest_status(processing);
				logger.info(" CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaHSN";
				jsonRequest = util.getKarzaHSNRequest(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaHSNProcess(result, info);
				} catch (Exception e) {
					logger.error("Exception in CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
				}
			}



			else if (result.getService_provider().contains("KARZA EPF AUTH PASSBOOK V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaEPFAuthPassBook";
				jsonRequest = util.getKarzaEPFAuthPassV2Request(result);
				logger.info(" CronServiceImpl || serviceCall () || calling IBS Service :: "+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaEPFAuthPassV2Process(result, info);
				} catch (Exception e) {
					logger.error("Exception in CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("KARZA ICWAI FIRM AUTH V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaICWAIFirmAuth";
				jsonRequest = util.getKarzaIWAIFirmAuthRequest(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+"  url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaICWAIFirmAuthProcess(result, info);
				} catch (Exception e) {
					logger.error("Exception in CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("KARZA LPG V3")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v3/requestKarzaLPG";
				jsonRequest = util.getKarzaLPGV3Request(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaLPGV3Process(result, info);
				} catch (Exception e) {
					logger.error("CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("FINBIT")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing:: Correlation ID : " + result.getCorrelationid());
				dbConnection.updateRecord(result);
				url = url + "/ib/finbit/api/v1/finbitRequest";
				jsonRequest = util.getFinbitRequest(result,dbConnection.getIbsAccountDetail(result.getCorrelationid()));
				logger.info(" calling IBS Service :: "+result.getService_provider()+" url : " + url);
				///// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					finbitProcess(result, info,jsonRequest,url);
				} catch (Exception e) {
					logger.error("CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("KARZA GST IDENTIFICATION V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaGstIdentification";
				jsonRequest = util.getKarzaGSTIdentificationV2Request(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service :: url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaGSTIdentificationV2Process(result, info);
				} catch (Exception e) {
					logger.error("CronServiceImpl || serviceCall () || unable to process the service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("KARZA ICSI MEMBERSHIP V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaICSIMemberShip";
				jsonRequest = util.getKarzaICSIRequest(result);
				logger.info("CronServiceImpl || serviceCall () || calling IBS Service :: url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaICSIProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}

			else if (result.getService_provider().contains("KARZA EPF AUTH OTP V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaEPFAuthOTP";
				jsonRequest = util.getKarzaEPFAuthOtpV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaEPFAuthOtpV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KARZA ITR AUTH V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaITRAuth";
				jsonRequest = util.getKarzaITRAuthV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaITRAuthV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA FSSAI LICENCE V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaFSSAILicence";
				jsonRequest = util.getKarzaFSSAILicenceV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaFSSAILicenceV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA RC SEARCH V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestRCSearch";
				jsonRequest = util.getKarzaRCSearchV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaRCSearchV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA RC AUTH V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaRCAuth";
				jsonRequest = util.getKarzaRCAUTHV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaRCAUTHV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KARZA ICWAI MEMBER V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaICWAIMember";
				jsonRequest = util.getKarzaIcwaiMemberV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaIcwaiMemberV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}


			else if (result.getService_provider().contains("KARZA EMPLOYER LOOKUP")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestEmployerLookup";
				jsonRequest = util.getKarzaEmployerLookupRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaEmployerLookupProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KARZA ELEC V3")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v3/requestKarzaElec";
				jsonRequest = util.getKarzaElecV3Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaEleV3Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KARZA IFSC Check")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaIFSC";
				jsonRequest = util.getKarzaIfscCheckRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaIfscCheckProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if(result.getService_provider().contains("TELE V3")) {
                result.setRequest_status(processing);
                logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
                dbConnection.updateRecord(result);
                url = url + "/ib/karza/api/v3/requestKarzaTele";
                jsonRequest = util.getKarzaTeleV3Request(result);
                logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+"  url : " + url);
                // info = util.okHttpCall(jsonRequest, url);
                info = util.httpCall(jsonRequest, url);
                try {
                    karzaTeleV3Process(result, info);
                } catch (Exception e) {
                    logger.error("Exception in CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
                }
           
               
               
            }
            else if (result.getService_provider().contains("KARZA CA MEMBERSHIP AUTH V2")) {
                result.setRequest_status(processing);
                logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
                dbConnection.updateRecord(result);
                url = url + "/ib/karza/api/v2/requestKarzaCAMemberShipAuth";
                jsonRequest = util.getKarzaCaMembershipAuthRequest(result);
                logger.info("CronServiceImpl || serviceCall () || calling IBS Service ::"+result.getService_provider()+"  url : " + url);
                // info = util.okHttpCall(jsonRequest, url);
                info = util.httpCall(jsonRequest, url);
                try {
                    karzaCaMembershipAuthProcess(result, info);
                } catch (Exception e) {
                    logger.error("Exception in CronServiceImpl || serviceCall () || unable to process "+result.getService_provider()+" the service response :: " + e);
                }
            }
            

			else if (result.getService_provider().contains("KARZA PNG V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaPng";
				jsonRequest = util.getKarzaPngV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaPngV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			
			else if (result.getService_provider().contains("KARZA AADHAR V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaAadhar";
				jsonRequest = util.getKarzaAADHARV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaAADHARV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA ESICId V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaESICIDAuth";
				jsonRequest = util.getKarzaESICIdV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaESICIdV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA FORM 16 AUTHENTICATION V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaForm16Auth";
				jsonRequest = util.getKarzaForm16AuthenticationV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaForm16AuthenticationV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("PAN VALIDATION")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				//url = resProp.getString("com.qc.panvalidaion.liveURL");
				url = "http://localhost:8081/integrationboxPan/ib/pan/api/v1/panvalidation";
				jsonRequest = util.getPanValidationRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				info = util.httpCall(jsonRequest, url);
				info=new CronInfo();
				info.setResponse("{\"header\":{\"msgVersion\":\"1.0\",\"appId\":\"PanAppid\",\"correlationId\":\"uniqueIDForEachRequest\",\"token\":\"120fb604-0642-46ae-949c-9a84cd21455e\"},\"msgInfo\":{\"code\":\"200\",\"status\":\"SUCCESS\",\"message\":\"Success\",\"description\":\"Success\"},\"payload\":[{\"pan\":\"CCGPG6163A\",\"panStatus\":\"EXISTING AND VALID\",\"lName\":\"GAUR\",\"fName\":\"Ankita\",\"mName\":\"\",\"title\":\"Kumari\",\"dobIncorporationITD\":\"28/03/2017\",\"fatherLName\":\"\",\"fatherFName\":\"\",\"fatherMName\":\"\",\"lastUpdatedDate\":\"\",\"uniqueId\":\"38f4fb89-db49-44cf-8f16-374e64771a11\"}]}");

				try {

					panValidationProcess(result, info,jsonRequest);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA NREGA V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaNREGA";
				jsonRequest = util.getKarzaNregaV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaNregaV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA FORM 16 QUARTERLY V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaForm16Quarterly";
				jsonRequest = util.getKarzaForm16QuarterlyV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaForm16QuarterlyV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KARZA WEBSITE DOMIAN V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestWebsiteDomain";
				jsonRequest = util.getKarzaWebsiteDomainV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaWebsiteDomainV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}


			else if (result.getService_provider().contains("KARZA UAN LOOKUP V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestUANLookup";
				jsonRequest = util.getKarzaUanLookupV2Request(result);

				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaUanLookupV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}



			else if (result.getService_provider().contains("KARZA BANK ACCOUNT V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestBankAccount";
				jsonRequest = util.getKarzaBankAccountV2Request(result);

				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaBankAccountV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}



			else if (result.getService_provider().contains("KARZA NAME SIMILARITY V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaNameSimilarity";
				jsonRequest = util.getKarzaNameSimilarityV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaNameSimilarityV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}


			else if (result.getService_provider().contains("KARZA ADDRESS MATCHING V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestAddessMatching";
				jsonRequest = util.getKarzaAddressMatchingV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaAddressMatchingV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}


			else if (result.getService_provider().contains("KARZA UDYOG AADHAR V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaUdyogAadhar";
				jsonRequest = util.getKarzaUdyogAadharV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaUdyogAadharV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}


			else if (result.getService_provider().contains("KARZA SHOPESTABLISHMENT V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaShopEstablishment";
				jsonRequest = util.getKarzaShopEstablishmentV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaShopEstablishmentV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}


			/* else if (result.getService_provider().contains("AUTH DELIVERY")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/experianKickOff/api/v1/experianAuthDelivery";
				jsonRequest = util.getAuthDeliveryRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				 // info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {ggg
					kickOffAuthDeliveryProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}*//* else if (result.getService_provider().contains("GENERATE QUESTION")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/experianKickOff/api/v1/experianGenerateQuestion";
				jsonRequest = util.getAuthDeliveryRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				 // info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					kickOffCRQProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}*/

			else if (result.getService_provider().contains("KARZA MCA SIGNATURE V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaMCASignaure";
				jsonRequest = util.getKarzaMCASignatureV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaMCASignatureV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA COMP SEARCH V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaCompSearch";
				jsonRequest = util.getKarzaCompSearchV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaCompSearchV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}

			else if (result.getService_provider().contains("KARZA IEC V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaIEC";
				jsonRequest = util.getKarzaIecV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaIecV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}

			else if (result.getService_provider().contains("KARZA COMPANY LLP CIN LOOKUP V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaCompanyLLPCINLookUp";
				jsonRequest = util.getKarzaLLPCinLookupV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaLLPCinLookupV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}


			else if (result.getService_provider().contains("KARZA COMPANY LLP IDENTIFICATION V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaCompanyLLPIdentification";
				jsonRequest = util.getKarzaLLPIdentificationV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaLLPIdentificationV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}


			else if (result.getService_provider().contains("KARZA GST AUTHENTICATION V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaGSTAuthentication";
				jsonRequest = util.getKarzaGSTAuthenticationV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaGSTAuthenticationV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}






			else if (result.getService_provider().contains("KARZA TAN V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaTan";
				jsonRequest = util.getKarzaTanV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaTanV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}

			else if (result.getService_provider().contains("KARZA VOTER V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaVoter";
				jsonRequest = util.getKarzaVoterV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaVoterV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			} 

			else if (result.getService_provider().contains("KARZA PAN")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaPan";
				jsonRequest = util.getKarzaPanRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaPanProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KARZA EMAIL AUTH V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaEmailAuth";
				jsonRequest = util.getKarzaEMailAUTHRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaEMailAuthProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			
			else if (result.getService_provider().contains("KARZA EPF OTP AUTH V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaEPFAuthOTP";
				jsonRequest = util.getKarzaEPFAuthOTPRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaEPFAuthOTPProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			
			else if (result.getService_provider().contains("KARZA ELEC V2")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v2/requestKarzaElec";
				jsonRequest = util.getKarzaElecV2Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					karzaEleV2Process(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}

			}
			else if (result.getService_provider().contains("KOTAK PAYMENT")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/kotak/api/v1/kotakRequest";
				jsonRequest = util.getKotakPaymentRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					kotakPaymentProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}


			else if(result.getService_provider().contains("KOTAK REVERSAL")) {

				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/kotak/api/v1/kotakReversalRequest";
				jsonRequest = util.getKotakReversalRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try {
					kotakReversalProcess(result, info);
				} catch (Exception e) {
					logger.error(" unable to process the service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("CREDITVIDYA EMAIL SAVE"))
			{
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				String url1=url;
				url = url + "/ib/creditvidya/api/v1/email";
				jsonRequest = util.getCreditVidyaEmailSaveRequest(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try
				{
					creditVidyaEmailSaveProcess(result, info,url1);
				}
				catch (Exception e) 
				{
					logger.error(" unable to process the service response :: " + e);
				}
			}
			else if (result.getService_provider().contains("DL V3")) {
				result.setRequest_status(processing);
				logger.info("CronServiceImpl || serviceCall () || calling updateRecord() to change status processing::"+result.getService_provider()+" record : " + result);
				dbConnection.updateRecord(result);
				url = url + "/ib/karza/api/v3/requestKarzaDL";
				jsonRequest = util.getKarzaDlV3Request(result);
				logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
				// info = util.okHttpCall(jsonRequest, url);
				info = util.httpCall(jsonRequest, url);
				try 
				{
					karzaDlV3Process(result, info);
				} 
				catch (Exception e)
				{
					logger.error(" unable to process the service response :: " + e);
				}

			} 

		} catch (Exception e) {
			logger.error(" have trouble in processing result :: " + e);
		}
	}
	private void kotakReversalProcess(IbRequestMaster result, CronInfo info) {
		KotakReversalResponse  response = null;
		try {
			logger.info(" inside karzaVoterProcess() ::  ");
			response = om.readValue(info.getResponse(), KotakReversalResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getStatus().toString().equals("Success"))
			{
				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}

	}
	private void kotakPaymentProcess(IbRequestMaster result, CronInfo info) {
		KotakResponse  response =null;
		try {
			logger.info(" inside karzaVoterProcess() ::  ");
			response = om.readValue(info.getResponse(), KotakResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getStatus().toString().equals("Success"))
			{
				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}

	}
	private void creditVidyaEmailSaveProcess(IbRequestMaster result, CronInfo info, String url) {
		EmailVerificationResponse  response = null;
		try {
			logger.info(" inside creditVidyaEmailSaveProcess() ::  ");
			EmailSaveResponse  response1 = om.readValue(info.getResponse(), EmailSaveResponse.class);
			try {
				if (response1 != null && response1.getMsgInfo() != null && response1.getMsgInfo().getCode().equals("200")
						&& response1.getMsgInfo().getStatus().toString().equals("Success")) {
					if(response1.getPayload()!=null && response1.getPayload().getUniqueId()!=null){
						logger.info(" IBS internaly call creditvidya email varification  ");
						url = url + "/ib/creditvidya/api/v1/email/verification";
						String jsonRequest = util.getCreditVidyaEmailVerifyRequest(response1);
						logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
						// info = util.okHttpCall(jsonRequest, url);
						info = util.httpCall(jsonRequest, url);
					}
				}
			} catch (Exception e) {
				logger.error(" Error in internaly call  creditvidya email varification :: " + e);
			}
			response = om.readValue(info.getResponse(), EmailVerificationResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getCode().equals("200")
					&& response.getMsgInfo().getStatus().toString().equals("Success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaDlV3Process(IbRequestMaster result, CronInfo info) {
		DlResponse2  response = null;
		try {
			logger.info(" inside karzaDlV3Process() ::  ");
			response = om.readValue(info.getResponse(), DlResponse2.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getCode().equals("101")
					&& response.getMsgInfo().getStatus().toString().equals("Success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaEleV2Process(IbRequestMaster result, CronInfo info) {
		ElectricityResponse  response = null;
		try {
			logger.info(" inside karzaEleV2Process() ::  ");
			response = om.readValue(info.getResponse(), ElectricityResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getCode().equals("101")
					&& response.getMsgInfo().getStatus().toString().equals("Success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaVoterV2Process(IbRequestMaster result, CronInfo info) {
		VoterResponse voterResponse = null;
		try {
			logger.info(" inside karzaVoterV2Process() ::  ");
			voterResponse = om.readValue(info.getResponse(), VoterResponse.class);
			if (voterResponse != null && voterResponse.getMsgInfo() != null	&& voterResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (voterResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(voterResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(voterResponse.getMsgInfo().getCode());
					error.setStatus(voterResponse.getMsgInfo().getStatus().toString());
					error.setMessage(voterResponse.getMsgInfo().getMessage());
					error.setDescription(voterResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(voterResponse.getMsgInfo().getCode());
				error.setStatus(voterResponse.getMsgInfo().getStatus().toString());
				error.setMessage(voterResponse.getMsgInfo().getMessage());
				error.setDescription(voterResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(voterResponse.getMsgInfo().getCode());
				error.setStatus(voterResponse.getMsgInfo().getStatus().toString());
				error.setMessage(voterResponse.getMsgInfo().getMessage());
				error.setDescription(voterResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaPanProcess(IbRequestMaster result, CronInfo info) {
		PanResponse  response = null;
		try {
			logger.info(" inside karzaPanProcess() ::  ");
			response = om.readValue(info.getResponse(), PanResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getCode().equals("101")
					&& response.getMsgInfo().getStatus().toString().equals("Success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	
	
	private void karzaEMailAuthProcess(IbRequestMaster result, CronInfo info) {
		EmailAuthResponse  response = null;
		try {
			logger.info(" inside karzaEMailAuthProcess() ::  ");
			response = om.readValue(info.getResponse(), EmailAuthResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getCode().equals("101")
					&& response.getMsgInfo().getStatus().toString().equals("Success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	
	private void karzaEPFAuthOTPProcess(IbRequestMaster result, CronInfo info) {
		EPFAuthOTPResponse  response = null;
		try {
			logger.info(" inside karzaEPFAuthOTPProcess() ::  ");
			response = om.readValue(info.getResponse(), EPFAuthOTPResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getCode().equals("101")
					&& response.getMsgInfo().getStatus().toString().equals("Success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}



	private void kickOffCRQProcess(IbRequestMaster result, CronInfo info) {
		ExperianGenQuestionResponse response = null;
		try {
			logger.info(" inside kickOffCRQProcess() ::  ");
			response = om.readValue(info.getResponse(), ExperianGenQuestionResponse.class);
			if (response != null && response.getErrorInfo() != null && response.getErrorInfo().getCode().equals("200")
					&& response.getErrorInfo().getStatus().toString().equals("SUCCESS")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaPassportProcess(IbRequestMaster result, CronInfo info) {
		AadharResponse aadharResponse = null;
		try {
			logger.info(" inside Aadhar V2 Process() ::  ");
			aadharResponse = om.readValue(info.getResponse(), AadharResponse
					.class);
			if (aadharResponse != null && aadharResponse.getMsgInfo() != null	&& aadharResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (aadharResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(aadharResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(aadharResponse.getMsgInfo().getCode());
					error.setStatus(aadharResponse.getMsgInfo().getStatus().toString());
					error.setMessage(aadharResponse.getMsgInfo().getMessage());
					error.setDescription(aadharResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(aadharResponse.getMsgInfo().getCode());
				error.setStatus(aadharResponse.getMsgInfo().getStatus().toString());
				error.setMessage(aadharResponse.getMsgInfo().getMessage());
				error.setDescription(aadharResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(aadharResponse.getMsgInfo().getCode());
				error.setStatus(aadharResponse.getMsgInfo().getStatus().toString());
				error.setMessage(aadharResponse.getMsgInfo().getMessage());
				error.setDescription(aadharResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}


	}

	private void karzaHSNProcess(IbRequestMaster result, CronInfo info) {
		HSNResponse hsnResponse = null;
		try {
			logger.info(" inside HSN V2Process() ::  ");
			hsnResponse = om.readValue(info.getResponse(), HSNResponse
					.class);
			if (hsnResponse != null && hsnResponse.getMsgInfo() != null	&& hsnResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (hsnResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(hsnResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(hsnResponse.getMsgInfo().getCode());
					error.setStatus(hsnResponse.getMsgInfo().getStatus().toString());
					error.setMessage(hsnResponse.getMsgInfo().getMessage());
					error.setDescription(hsnResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(hsnResponse.getMsgInfo().getCode());
				error.setStatus(hsnResponse.getMsgInfo().getStatus().toString());
				error.setMessage(hsnResponse.getMsgInfo().getMessage());
				error.setDescription(hsnResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(hsnResponse.getMsgInfo().getCode());
				error.setStatus(hsnResponse.getMsgInfo().getStatus().toString());
				error.setMessage(hsnResponse.getMsgInfo().getMessage());
				error.setDescription(hsnResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}


	}

	private void karzaEPFAuthPassV2Process(IbRequestMaster result, CronInfo info) {
		EPFAuthPassBookResponse epfAuthPassBookResponse =null;
		try {
			logger.info(" inside EPFAuthPass V2Process() ::  ");
			epfAuthPassBookResponse = om.readValue(info.getResponse(), EPFAuthPassBookResponse
					.class);
			if (epfAuthPassBookResponse != null && epfAuthPassBookResponse.getMsgInfo() != null	&& epfAuthPassBookResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (epfAuthPassBookResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(epfAuthPassBookResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(epfAuthPassBookResponse.getMsgInfo().getCode());
					error.setStatus(epfAuthPassBookResponse.getMsgInfo().getStatus().toString());
					error.setMessage(epfAuthPassBookResponse.getMsgInfo().getMessage());
					error.setDescription(epfAuthPassBookResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(epfAuthPassBookResponse.getMsgInfo().getCode());
				error.setStatus(epfAuthPassBookResponse.getMsgInfo().getStatus().toString());
				error.setMessage(epfAuthPassBookResponse.getMsgInfo().getMessage());
				error.setDescription(epfAuthPassBookResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(epfAuthPassBookResponse.getMsgInfo().getCode());
				error.setStatus(epfAuthPassBookResponse.getMsgInfo().getStatus().toString());
				error.setMessage(epfAuthPassBookResponse.getMsgInfo().getMessage());
				error.setDescription(epfAuthPassBookResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}

	}

	private void karzaEPFAuthOtpV2Process(IbRequestMaster result, CronInfo info) {
		EPFAuthOTPResponse epfAuthOTPResponse = null;
		try {
			logger.info(" inside FSSAI Licence V2Process() ::  ");
			epfAuthOTPResponse = om.readValue(info.getResponse(), EPFAuthOTPResponse
					.class);
			if (epfAuthOTPResponse != null && epfAuthOTPResponse.getMsgInfo() != null	&& epfAuthOTPResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (epfAuthOTPResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(epfAuthOTPResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(epfAuthOTPResponse.getMsgInfo().getCode());
					error.setStatus(epfAuthOTPResponse.getMsgInfo().getStatus().toString());
					error.setMessage(epfAuthOTPResponse.getMsgInfo().getMessage());
					error.setDescription(epfAuthOTPResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(epfAuthOTPResponse.getMsgInfo().getCode());
				error.setStatus(epfAuthOTPResponse.getMsgInfo().getStatus().toString());
				error.setMessage(epfAuthOTPResponse.getMsgInfo().getMessage());
				error.setDescription(epfAuthOTPResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(epfAuthOTPResponse.getMsgInfo().getCode());
				error.setStatus(epfAuthOTPResponse.getMsgInfo().getStatus().toString());
				error.setMessage(epfAuthOTPResponse.getMsgInfo().getMessage());
				error.setDescription(epfAuthOTPResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaITRAuthV2Process(IbRequestMaster result, CronInfo info) {
		ITRAuthResponse itrresponse =null;
		try {
			logger.info(" inside ITR Auth V2Process() ::  ");
			itrresponse = om.readValue(info.getResponse(), ITRAuthResponse
					.class);
			if (itrresponse != null && itrresponse.getMsgInfo() != null	&& itrresponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (itrresponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(itrresponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(itrresponse.getMsgInfo().getCode());
					error.setStatus(itrresponse.getMsgInfo().getStatus().toString());
					error.setMessage(itrresponse.getMsgInfo().getMessage());
					error.setDescription(itrresponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(itrresponse.getMsgInfo().getCode());
				error.setStatus(itrresponse.getMsgInfo().getStatus().toString());
				error.setMessage(itrresponse.getMsgInfo().getMessage());
				error.setDescription(itrresponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(itrresponse.getMsgInfo().getCode());
				error.setStatus(itrresponse.getMsgInfo().getStatus().toString());
				error.setMessage(itrresponse.getMsgInfo().getMessage());
				error.setDescription(itrresponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaFSSAILicenceV2Process(IbRequestMaster result, CronInfo info) {
		FSSAILicenceResponse fssaiLicenceResponse = null;
		try {
			logger.info(" inside FSSAI Licence V2Process() ::  ");
			fssaiLicenceResponse = om.readValue(info.getResponse(), FSSAILicenceResponse
					.class);
			if (fssaiLicenceResponse != null && fssaiLicenceResponse.getMsgInfo() != null	&& fssaiLicenceResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (fssaiLicenceResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(fssaiLicenceResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(fssaiLicenceResponse.getMsgInfo().getCode());
					error.setStatus(fssaiLicenceResponse.getMsgInfo().getStatus().toString());
					error.setMessage(fssaiLicenceResponse.getMsgInfo().getMessage());
					error.setDescription(fssaiLicenceResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(fssaiLicenceResponse.getMsgInfo().getCode());
				error.setStatus(fssaiLicenceResponse.getMsgInfo().getStatus().toString());
				error.setMessage(fssaiLicenceResponse.getMsgInfo().getMessage());
				error.setDescription(fssaiLicenceResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(fssaiLicenceResponse.getMsgInfo().getCode());
				error.setStatus(fssaiLicenceResponse.getMsgInfo().getStatus().toString());
				error.setMessage(fssaiLicenceResponse.getMsgInfo().getMessage());
				error.setDescription(fssaiLicenceResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaICWAIFirmAuthProcess(IbRequestMaster result, CronInfo info) {
		ICWAIFirmAuthResponse icwaiFirmAuthResponse = null;
		try {
			logger.info(" inside IWAI Firm Authentication V2Process() ::  ");
			icwaiFirmAuthResponse = om.readValue(info.getResponse(), ICWAIFirmAuthResponse
					.class);
			if (icwaiFirmAuthResponse != null && icwaiFirmAuthResponse.getMsgInfo() != null	&& icwaiFirmAuthResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (icwaiFirmAuthResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(icwaiFirmAuthResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(icwaiFirmAuthResponse.getMsgInfo().getCode());
					error.setStatus(icwaiFirmAuthResponse.getMsgInfo().getStatus().toString());
					error.setMessage(icwaiFirmAuthResponse.getMsgInfo().getMessage());
					error.setDescription(icwaiFirmAuthResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(icwaiFirmAuthResponse.getMsgInfo().getCode());
				error.setStatus(icwaiFirmAuthResponse.getMsgInfo().getStatus().toString());
				error.setMessage(icwaiFirmAuthResponse.getMsgInfo().getMessage());
				error.setDescription(icwaiFirmAuthResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(icwaiFirmAuthResponse.getMsgInfo().getCode());
				error.setStatus(icwaiFirmAuthResponse.getMsgInfo().getStatus().toString());
				error.setMessage(icwaiFirmAuthResponse.getMsgInfo().getMessage());
				error.setDescription(icwaiFirmAuthResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaICSIProcess(IbRequestMaster result, CronInfo info) {
		ICSIMemberShipResponse icsiMemberShipResponse = null;
		try {
			logger.info(" inside ICSI Membership V2Process() ::  ");
			icsiMemberShipResponse = om.readValue(info.getResponse(), ICSIMemberShipResponse
					.class);
			if (icsiMemberShipResponse != null && icsiMemberShipResponse.getMsgInfo() != null	&& icsiMemberShipResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (icsiMemberShipResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(icsiMemberShipResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(icsiMemberShipResponse.getMsgInfo().getCode());
					error.setStatus(icsiMemberShipResponse.getMsgInfo().getStatus().toString());
					error.setMessage(icsiMemberShipResponse.getMsgInfo().getMessage());
					error.setDescription(icsiMemberShipResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(icsiMemberShipResponse.getMsgInfo().getCode());
				error.setStatus(icsiMemberShipResponse.getMsgInfo().getStatus().toString());
				error.setMessage(icsiMemberShipResponse.getMsgInfo().getMessage());
				error.setDescription(icsiMemberShipResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(icsiMemberShipResponse.getMsgInfo().getCode());
				error.setStatus(icsiMemberShipResponse.getMsgInfo().getStatus().toString());
				error.setMessage(icsiMemberShipResponse.getMsgInfo().getMessage());
				error.setDescription(icsiMemberShipResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}

	}

	private void karzaLPGV3Process(IbRequestMaster result, CronInfo info) {
		LpgResponse2 lpgRes = null;
		try {
			logger.info(" inside EPFAuthPass V2Process() ::  ");
			lpgRes = om.readValue(info.getResponse(), LpgResponse2
					.class);
			if (lpgRes != null && lpgRes.getMsgInfo() != null	&& lpgRes.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (lpgRes.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(lpgRes.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(lpgRes.getMsgInfo().getCode());
					error.setStatus(lpgRes.getMsgInfo().getStatus().toString());
					error.setMessage(lpgRes.getMsgInfo().getMessage());
					error.setDescription(lpgRes.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(lpgRes.getMsgInfo().getCode());
				error.setStatus(lpgRes.getMsgInfo().getStatus().toString());
				error.setMessage(lpgRes.getMsgInfo().getMessage());
				error.setDescription(lpgRes.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(lpgRes.getMsgInfo().getCode());
				error.setStatus(lpgRes.getMsgInfo().getStatus().toString());
				error.setMessage(lpgRes.getMsgInfo().getMessage());
				error.setDescription(lpgRes.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}




	}
	private void finbitProcess(IbRequestMaster result, CronInfo info,String jsonRequest,String url) {
		FinbitAPIResponse finbit =null;
		try {
			logger.info(" inside finbitProcess() ::  ");
			finbit = om.readValue(info.getResponse(), FinbitAPIResponse.class);
			if (finbit != null && finbit.getMsgInfo()!=null && finbit.getMsgInfo().getStatus().contains("Success")
					&& finbit.getPayload()!=null && finbit.getPayload().getPdfPath()!=null) 
			{
				result.setRequest_status(ResquestStatus.SUCCESS.toString());

				try {
					result.setPdf_path(finbit.getPayload().getPdfPath());
					result.setFinal_response(info.getResponse());
				} catch (Exception e) {
					logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
				}

			} else {
				result.setRequest_status(ResquestStatus.FAILURE.toString());
				ErrorInfo error=new ErrorInfo();
				error.setCode(finbit.getErrorInfo().getCode());
				error.setStatus(finbit.getErrorInfo().getStatus());
				error.setMessage(finbit.getErrorInfo().getMessage());
				error.setDescription(finbit.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp)) ;
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {

			logger.error(" Error in set response to entity :: " + e);
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			ErrorInfo error=new ErrorInfo();
			error.setCode(finbit.getErrorInfo().getCode());
			error.setStatus(finbit.getErrorInfo().getStatus());
			error.setMessage(finbit.getErrorInfo().getMessage());
			error.setDescription(finbit.getErrorInfo().getDescription());
			result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp)) ;
			dbConnection.updateRecord(result);
		}
	}
	private void karzaGSTIdentificationV2Process(IbRequestMaster result, CronInfo info) {
		GstIdentificationResponse gstIdentificationRequestProcess = null;
		try {
			logger.info(" inside karzaGSTIdentificationV2Process() ::  ");
			gstIdentificationRequestProcess = om.readValue(info.getResponse(), GstIdentificationResponse.class);
			if (gstIdentificationRequestProcess != null && gstIdentificationRequestProcess.getMsgInfo() != null	&& gstIdentificationRequestProcess.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (gstIdentificationRequestProcess.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(gstIdentificationRequestProcess.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(gstIdentificationRequestProcess.getMsgInfo().getCode());
					error.setStatus(gstIdentificationRequestProcess.getMsgInfo().getStatus().toString());
					error.setMessage(gstIdentificationRequestProcess.getMsgInfo().getMessage());
					error.setDescription(gstIdentificationRequestProcess.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(gstIdentificationRequestProcess.getMsgInfo().getCode());
				error.setStatus(gstIdentificationRequestProcess.getMsgInfo().getStatus().toString());
				error.setMessage(gstIdentificationRequestProcess.getMsgInfo().getMessage());
				error.setDescription(gstIdentificationRequestProcess.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(gstIdentificationRequestProcess.getMsgInfo().getCode());
				error.setStatus(gstIdentificationRequestProcess.getMsgInfo().getStatus().toString());
				error.setMessage(gstIdentificationRequestProcess.getMsgInfo().getMessage());
				error.setDescription(gstIdentificationRequestProcess.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaRCSearchV2Process(IbRequestMaster result, CronInfo info) {
		RCSearchResponse rcSearchResponse = null;
		try {
			logger.info(" inside Form RC Search V2Process() ::  ");
			rcSearchResponse = om.readValue(info.getResponse(), RCSearchResponse
					.class);
			if (rcSearchResponse != null && rcSearchResponse.getMsgInfo() != null	&& rcSearchResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (rcSearchResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(rcSearchResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(rcSearchResponse.getMsgInfo().getCode());
					error.setStatus(rcSearchResponse.getMsgInfo().getStatus().toString());
					error.setMessage(rcSearchResponse.getMsgInfo().getMessage());
					error.setDescription(rcSearchResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(rcSearchResponse.getMsgInfo().getCode());
				error.setStatus(rcSearchResponse.getMsgInfo().getStatus().toString());
				error.setMessage(rcSearchResponse.getMsgInfo().getMessage());
				error.setDescription(rcSearchResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(rcSearchResponse.getMsgInfo().getCode());
				error.setStatus(rcSearchResponse.getMsgInfo().getStatus().toString());
				error.setMessage(rcSearchResponse.getMsgInfo().getMessage());
				error.setDescription(rcSearchResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	
	private void karzaRCAUTHV2Process(IbRequestMaster result, CronInfo info) {
		RCAuthResponse rcAuthResponse = null;
		try {
			logger.info(" inside Form RC Auth V2Process() ::  ");
			rcAuthResponse = om.readValue(info.getResponse(), RCAuthResponse
					.class);
			if (rcAuthResponse != null && rcAuthResponse.getMsgInfo() != null	&& rcAuthResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (rcAuthResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(rcAuthResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(rcAuthResponse.getMsgInfo().getCode());
					error.setStatus(rcAuthResponse.getMsgInfo().getStatus().toString());
					error.setMessage(rcAuthResponse.getMsgInfo().getMessage());
					error.setDescription(rcAuthResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(rcAuthResponse.getMsgInfo().getCode());
				error.setStatus(rcAuthResponse.getMsgInfo().getStatus().toString());
				error.setMessage(rcAuthResponse.getMsgInfo().getMessage());
				error.setDescription(rcAuthResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(rcAuthResponse.getMsgInfo().getCode());
				error.setStatus(rcAuthResponse.getMsgInfo().getStatus().toString());
				error.setMessage(rcAuthResponse.getMsgInfo().getMessage());
				error.setDescription(rcAuthResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void kickOffAuthDeliveryProcess(IbRequestMaster result, CronInfo info) {
		try {
			logger.info(" inside kickOffAuthDeliveryProcess() ::  ");
			AuthExperianResponse response = om.readValue(info.getResponse(), AuthExperianResponse.class);
			if (response != null && response.getErrorInfo() != null && response.getErrorInfo().getCode().equals("200")
					&& response.getErrorInfo().getStatus().toString().equals("SUCCESS")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (JsonMappingException je) {
			try {

				ExperianGenQuestionResponse response = om.readValue(info.getResponse(),
						ExperianGenQuestionResponse.class);
				if (response != null && response.getErrorInfo() != null
						&& response.getErrorInfo().getCode().equals("200")
						&& response.getErrorInfo().getStatus().toString().equals("SUCCESS")) {

					if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
						result.setRequest_status(ResquestStatus.SUCCESS.toString());
						try {
							result.setPdf_path(response.getPayload().getPdfPath());
							result.setFinal_response(info.getResponse());
						} catch (Exception e) {
							logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
						}
					} else {
						ErrorInfo error=new ErrorInfo();
						error.setCode(response.getErrorInfo().getCode());
						error.setStatus(response.getErrorInfo().getStatus().toString());
						error.setMessage(response.getErrorInfo().getMessage());
						error.setDescription(response.getErrorInfo().getDescription());
						result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
						result.setRequest_status(ResquestStatus.FAILURE.toString());
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
				logger.info(" calling updateRecord() :: record : " + result);
				dbConnection.updateRecord(result);
			} catch (Exception e) {
				logger.error(" Error in set response to entity :: " + e);
			}
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
		}

	}

	private void kickOffSingeActionProcess(IbRequestMaster result, CronInfo info) {
		try {
			logger.info(" inside kickOffSingeActionProcess() ::  ");
			ExperianResponse response = om.readValue(info.getResponse(), ExperianResponse.class);
			if (response != null && response.getErrorInfo() != null && response.getErrorInfo().getCode().equals("200")
					&& response.getErrorInfo().getStatus().toString().equals("SUCCESS")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else if (response.getPayload() != null && response.getPayload().getPdfPath() == null) {
					try {
						callAuthvalidation(result,info);
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}

			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (JsonMappingException je) {
			try {

				ExperianMaskResponse response = om.readValue(info.getResponse(), ExperianMaskResponse.class);
				if (response != null && response.getErrorInfo() != null
						&& response.getErrorInfo().getCode().equals("200")
						&& response.getErrorInfo().getStatus().toString().equals("SUCCESS")) {

					if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
						result.setRequest_status(ResquestStatus.SUCCESS.toString());
						try {
							result.setPdf_path(response.getPayload().getPdfPath());
							result.setFinal_response(info.getResponse());
						} catch (Exception e) {
							logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
						}
					} else {
						ErrorInfo error=new ErrorInfo();
						error.setCode(response.getErrorInfo().getCode());
						error.setStatus(response.getErrorInfo().getStatus().toString());
						error.setMessage(response.getErrorInfo().getMessage());
						error.setDescription(response.getErrorInfo().getDescription());
						result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
						result.setRequest_status(ResquestStatus.FAILURE.toString());
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
				logger.info(" calling updateRecord() :: record : " + result);
				dbConnection.updateRecord(result);

			} catch (Exception e) {
				logger.error(" Error in set response to entity :: " + e);
			}
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
		}

	}

	private void callAuthvalidation(IbRequestMaster result, CronInfo info) {
		ExperianMaskResponse maskresponse=null;
		ObjectMapper om=new ObjectMapper();
		try {
			maskresponse = om.readValue(info.getResponse(), ExperianMaskResponse.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String url = resProp.getString("com.qc.criff.liveURL");
		//String  url="http://localhost:8080/ibs1";

		url = url + "/ib/experianKickOff/api/v1/experianAuthDelivery";
		String jsonRequest = util.getAuthDeliveryRequest(result,maskresponse);
		logger.info(" calling IBS Service ::"+result.getService_provider()+" url : " + url);
		// info = util.okHttpCall(jsonRequest, url);
		info = util.httpCall(jsonRequest, url);
		try {
			kickOffAuthDeliveryProcess(result, info);
		} catch (Exception e) {
			logger.error(" unable to process the service response :: " + e);
		}

	}
	private void CibilV2Process(IbRequestMaster result, CronInfo info) {
		CibilAPIResponse2 response =null;
		try {
			logger.info(" inside cibilProcess() ::  ");
			response = om.readValue(info.getResponse(), CibilAPIResponse2.class);
			if (response != null && response.getErrorInfo() != null
					&& response.getErrorInfo().getDescription().equals("success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}



	}

	private void cibilProcess(IbRequestMaster result, CronInfo info) {
		CibilAPIResponse response =null;
		try {
			logger.info(" inside cibilProcess() ::  ");
			response = om.readValue(info.getResponse(), CibilAPIResponse.class);
			if (response != null && response.getErrorInfo() != null) //&& response.getErrorInfo().getDescription().equals("Successfully generated the Response")
			{

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error inside create failure pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}

	}
	private void MultiBureauProcess(IbRequestMaster result, CronInfo info,String jsonRequest,String url) {

		BureauResponse bureau =null;
		try {
			logger.info(" inside Multi Bureau Process() ::  ");
			bureau = om.readValue(info.getResponse(), BureauResponse.class);
			if (bureau != null && bureau.getMsgInfo() != null) 
			{

				if (bureau.getPayload() != null) {
					String pdflist =bureau.getPayload().getPdfPath();
					if(pdflist!=null) {

						result.setRequest_status(ResquestStatus.SUCCESS.toString());
						try {
							result.setPdf_path(bureau.getPayload().getPdfPath());
							result.setFinal_response(info.getResponse());
						} catch (Exception e) {
							logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
						}

					}else {
						result.setRequest_status(ResquestStatus.FAILURE.toString());
					}

					/*
					int i=0;
					List<String> pdflist =bureau.getPayload().getPdfPath();
					if(pdflist!=null && !pdflist.isEmpty()) {
						for (String pdfname : pdflist) {
							if(i==0 && pdfname!= null) {
								if(pdfname.contains("failure")) {
									result.setRequest_status(ResquestStatus.FAILURE.toString());
								}else {
									result.setRequest_status(ResquestStatus.SUCCESS.toString());
								}

								try {
									result.setPdf_path(pdfname);
									result.setFinal_response(info.getResponse());
								} catch (Exception e) {
									logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
								}
								i=1;
							}else if(pdfname!= null) {
								IbRequestMaster result1=(IbRequestMaster) result.clone();
								if(pdfname.contains("failure")) {
									result1.setRequest_status(ResquestStatus.FAILURE.toString());
								}else {
									result1.setRequest_status(ResquestStatus.SUCCESS.toString());
								}
								try {
									result1.setPdf_path(pdfname);
									result1.setFinal_response(info.getResponse());
									logger.info(" calling updateRecord() :: record : " + result);
									dbConnection.insertRecord(result1);
								} catch (Exception e) {
									logger.error(" error in save multiple records in multibureau  :: " + e);
								}
							}
						}
					}else {
						result.setRequest_status(ResquestStatus.FAILURE.toString());
					}
					 */} else {
						 result.setRequest_status(ResquestStatus.FAILURE.toString());
					 }
			} else {
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			if(result.getRequest_status().equals(ResquestStatus.FAILURE.toString()))
			{
				ErrorInfo error=new ErrorInfo();
				error.setCode(bureau.getMsgInfo().getCode());
				error.setStatus(bureau.getMsgInfo().getStatus());
				error.setMessage(bureau.getMsgInfo().getMessage());
				//result.setPdf_path(util.pdfFailure(error,url,jsonRequest,info.getResponse(),resProp)) ;
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			ErrorInfo error=new ErrorInfo();
			try {
				error.setCode(bureau.getMsgInfo().getCode());
				error.setStatus(bureau.getMsgInfo().getStatus());
				error.setMessage(bureau.getMsgInfo().getMessage());
			} catch (Exception e2) {
				logger.error(" Have an exception for set error status :: " + e2);
			}

			///result.setPdf_path(util.pdfFailure(error,url,jsonRequest,info.getResponse(),resProp)) ;
			dbConnection.updateRecord(result);
			logger.error(" Error in set response to entity :: " + e);
		}


	}
	private void equifaxProcess(IbRequestMaster result, CronInfo info) {
		EquifaxApiResponse response =null;
		try {
			logger.info(" inside equifaxProcess() ::  ");
			response = om.readValue(info.getResponse(), EquifaxApiResponse.class);
			if (response != null && response.getErrorInfo() != null
					&& response.getErrorInfo().getDescription().equals("Response Generated Successfully")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error inside create failure pdf :: " + e2);
			}

			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}

	}

	private void crifProcess(IbRequestMaster result, CronInfo info) {
		CriffApiResponse response =null;
		try {
			logger.info(" inside crifProcess() ::  ");
			response = om.readValue(info.getResponse(), CriffApiResponse.class);
			if (response != null && response.getErrorInfo() != null
					&& response.getErrorInfo().getDescription().equals("Response Generated Successfully")) {


				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getErrorInfo().getCode());
					error.setStatus(response.getErrorInfo().getStatus().toString());
					error.setMessage(response.getErrorInfo().getMessage());
					error.setDescription(response.getErrorInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}

			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getErrorInfo().getCode());
				error.setStatus(response.getErrorInfo().getStatus().toString());
				error.setMessage(response.getErrorInfo().getMessage());
				error.setDescription(response.getErrorInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error inside create failure pdf :: " + e2);
			}

			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaGSTAuthenticationV2Process(IbRequestMaster result, CronInfo info) {
		GSTAuthenticationResponse gstAuthenticationResponse =null;
		try {
			logger.info(" inside karzaLLPIdentificationV2Process() ::  ");
			gstAuthenticationResponse = om.readValue(info.getResponse(), GSTAuthenticationResponse.class);
			if (gstAuthenticationResponse != null && gstAuthenticationResponse.getMsgInfo() != null	&& gstAuthenticationResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (gstAuthenticationResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(gstAuthenticationResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(gstAuthenticationResponse.getMsgInfo().getCode());
					error.setStatus(gstAuthenticationResponse.getMsgInfo().getStatus().toString());
					error.setMessage(gstAuthenticationResponse.getMsgInfo().getMessage());
					error.setDescription(gstAuthenticationResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(gstAuthenticationResponse.getMsgInfo().getCode());
				error.setStatus(gstAuthenticationResponse.getMsgInfo().getStatus().toString());
				error.setMessage(gstAuthenticationResponse.getMsgInfo().getMessage());
				error.setDescription(gstAuthenticationResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(gstAuthenticationResponse.getMsgInfo().getCode());
				error.setStatus(gstAuthenticationResponse.getMsgInfo().getStatus().toString());
				error.setMessage(gstAuthenticationResponse.getMsgInfo().getMessage());
				error.setDescription(gstAuthenticationResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error inside create failure pdf :: " + e2);
			}

			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void perfiosProcess(IbRequestMaster result, CronInfo info)
	{
		logger.info(" inside perfiosProcess() ::  START");
		try 
		{
			String filePath=resProp.getString("com.perfios.temp.filelocation")+"PERFIOS_"+result.getCorrelationid()+".xls";

			String TURFresponse="";
			String hardCodedEnv="N";
			ResourceBundle res=null;
			JSONObject response=null;
			try 
			{
				res = ResourceBundle.getBundle("hardcode");
				hardCodedEnv = resProp.getString("com.ibs.allapi.demo.development");	
				logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
			}
			catch(Exception ex)
			{
				hardCodedEnv="N";
			}

			if(hardCodedEnv.equalsIgnoreCase("N"))
			{
				response=new JSONObject(res.getString("PERFIOS_IBS_FINAL"));
			}
			else
			{
				response=new JSONObject(res.getString("PERFIOS_IBS_FINAL"));
			}

			if(response!=null && response.has("payload") 
					&& response.getJSONObject("payload").has("response")
					&& response.getJSONObject("payload").getJSONObject("response").has("byteArrays")
					&& response.getJSONObject("payload").getJSONObject("response").getJSONObject("byteArrays").has("byteArray")) 
			{
				String base64ByteArray=response.getJSONObject("payload").getJSONObject("response").getJSONObject("byteArrays").getString("byteArray");
				byte[] byteArray =Base64.decode(base64ByteArray);
				FileOutputStream fout=new FileOutputStream(new File(filePath));
				fout.write(byteArray);
				fout.close();
				result.setPdf_path(filePath);
				result.setRequest_status(ResquestStatus.SUCCESS.toString());
				dbConnection.updateRecord(result);
			}
		} catch (Exception e) {
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
			logger.error(" exception in create excel in perfios :: "+e);
		}
		logger.info(" inside perfiosProcess() ::  END");
	}
	private void karzaLLPIdentificationV2Process(IbRequestMaster result, CronInfo info) {
		CompanyLLPIdentificationResponse companyLLPIdentificationResponse = null;
		try {
			logger.info(" inside karzaLLPIdentificationV2Process() ::  ");
			companyLLPIdentificationResponse = om.readValue(info.getResponse(), CompanyLLPIdentificationResponse.class);
			if (companyLLPIdentificationResponse != null && companyLLPIdentificationResponse.getMsgInfo() != null	&& companyLLPIdentificationResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (companyLLPIdentificationResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(companyLLPIdentificationResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(companyLLPIdentificationResponse.getMsgInfo().getCode());
					error.setStatus(companyLLPIdentificationResponse.getMsgInfo().getStatus().toString());
					error.setMessage(companyLLPIdentificationResponse.getMsgInfo().getMessage());
					error.setDescription(companyLLPIdentificationResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(companyLLPIdentificationResponse.getMsgInfo().getCode());
				error.setStatus(companyLLPIdentificationResponse.getMsgInfo().getStatus().toString());
				error.setMessage(companyLLPIdentificationResponse.getMsgInfo().getMessage());
				error.setDescription(companyLLPIdentificationResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(companyLLPIdentificationResponse.getMsgInfo().getCode());
				error.setStatus(companyLLPIdentificationResponse.getMsgInfo().getStatus().toString());
				error.setMessage(companyLLPIdentificationResponse.getMsgInfo().getMessage());
				error.setDescription(companyLLPIdentificationResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaLLPCinLookupV2Process(IbRequestMaster result, CronInfo info) {
		CompanyLLPCINLookUpResponse companyLLPCINLookUpResponse = null;
		try {
			logger.info(" inside karzaLLPCinLookupV2Process() ::  ");
			companyLLPCINLookUpResponse = om.readValue(info.getResponse(), CompanyLLPCINLookUpResponse.class);
			if (companyLLPCINLookUpResponse != null && companyLLPCINLookUpResponse.getMsgInfo() != null	&& companyLLPCINLookUpResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (companyLLPCINLookUpResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(companyLLPCINLookUpResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(companyLLPCINLookUpResponse.getMsgInfo().getCode());
					error.setStatus(companyLLPCINLookUpResponse.getMsgInfo().getStatus().toString());
					error.setMessage(companyLLPCINLookUpResponse.getMsgInfo().getMessage());
					error.setDescription(companyLLPCINLookUpResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(companyLLPCINLookUpResponse.getMsgInfo().getCode());
				error.setStatus(companyLLPCINLookUpResponse.getMsgInfo().getStatus().toString());
				error.setMessage(companyLLPCINLookUpResponse.getMsgInfo().getMessage());
				error.setDescription(companyLLPCINLookUpResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(companyLLPCINLookUpResponse.getMsgInfo().getCode());
				error.setStatus(companyLLPCINLookUpResponse.getMsgInfo().getStatus().toString());
				error.setMessage(companyLLPCINLookUpResponse.getMsgInfo().getMessage());
				error.setDescription(companyLLPCINLookUpResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaIecV2Process(IbRequestMaster result, CronInfo info) {
		IECResponse iecResponse = null;
		try {
			logger.info(" inside karzaIecV2Process() ::  ");
			iecResponse = om.readValue(info.getResponse(), IECResponse.class);
			if (iecResponse != null && iecResponse.getMsgInfo() != null	&& iecResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (iecResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(iecResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(iecResponse.getMsgInfo().getCode());
					error.setStatus(iecResponse.getMsgInfo().getStatus().toString());
					error.setMessage(iecResponse.getMsgInfo().getMessage());
					error.setDescription(iecResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(iecResponse.getMsgInfo().getCode());
				error.setStatus(iecResponse.getMsgInfo().getStatus().toString());
				error.setMessage(iecResponse.getMsgInfo().getMessage());
				error.setDescription(iecResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(iecResponse.getMsgInfo().getCode());
				error.setStatus(iecResponse.getMsgInfo().getStatus().toString());
				error.setMessage(iecResponse.getMsgInfo().getMessage());
				error.setDescription(iecResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaCompSearchV2Process(IbRequestMaster result, CronInfo info) {
		CompSearchResponse compSearchRessponse = null;
		try {
			logger.info(" inside karzaCompSearchV2Process() ::  ");
			compSearchRessponse = om.readValue(info.getResponse(), CompSearchResponse.class);
			if (compSearchRessponse != null && compSearchRessponse.getMsgInfo() != null	&& compSearchRessponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (compSearchRessponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(compSearchRessponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(compSearchRessponse.getMsgInfo().getCode());
					error.setStatus(compSearchRessponse.getMsgInfo().getStatus().toString());
					error.setMessage(compSearchRessponse.getMsgInfo().getMessage());
					error.setDescription(compSearchRessponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(compSearchRessponse.getMsgInfo().getCode());
				error.setStatus(compSearchRessponse.getMsgInfo().getStatus().toString());
				error.setMessage(compSearchRessponse.getMsgInfo().getMessage());
				error.setDescription(compSearchRessponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(compSearchRessponse.getMsgInfo().getCode());
				error.setStatus(compSearchRessponse.getMsgInfo().getStatus().toString());
				error.setMessage(compSearchRessponse.getMsgInfo().getMessage());
				error.setDescription(compSearchRessponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaMCASignatureV2Process(IbRequestMaster result, CronInfo info) {
		MCASignatureResponse mcaSignatureResponse =null;
		try {
			logger.info(" inside karzaMCASignatureV2Process() ::  ");
			mcaSignatureResponse = om.readValue(info.getResponse(), MCASignatureResponse.class);
			if (mcaSignatureResponse != null && mcaSignatureResponse.getMsgInfo() != null	&& mcaSignatureResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (mcaSignatureResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(mcaSignatureResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(mcaSignatureResponse.getMsgInfo().getCode());
					error.setStatus(mcaSignatureResponse.getMsgInfo().getStatus().toString());
					error.setMessage(mcaSignatureResponse.getMsgInfo().getMessage());
					error.setDescription(mcaSignatureResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(mcaSignatureResponse.getMsgInfo().getCode());
				error.setStatus(mcaSignatureResponse.getMsgInfo().getStatus().toString());
				error.setMessage(mcaSignatureResponse.getMsgInfo().getMessage());
				error.setDescription(mcaSignatureResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(mcaSignatureResponse.getMsgInfo().getCode());
				error.setStatus(mcaSignatureResponse.getMsgInfo().getStatus().toString());
				error.setMessage(mcaSignatureResponse.getMsgInfo().getMessage());
				error.setDescription(mcaSignatureResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaTanV2Process(IbRequestMaster result, CronInfo info) {
		TanResponse tanResponse = null;
		try {
			logger.info(" inside karzaTanV2Process() ::  ");
			tanResponse = om.readValue(info.getResponse(), TanResponse.class);
			if (tanResponse != null && tanResponse.getMsgInfo() != null	&& tanResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (tanResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(tanResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(tanResponse.getMsgInfo().getCode());
					error.setStatus(tanResponse.getMsgInfo().getStatus().toString());
					error.setMessage(tanResponse.getMsgInfo().getMessage());
					error.setDescription(tanResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(tanResponse.getMsgInfo().getCode());
				error.setStatus(tanResponse.getMsgInfo().getStatus().toString());
				error.setMessage(tanResponse.getMsgInfo().getMessage());
				error.setDescription(tanResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(tanResponse.getMsgInfo().getCode());
				error.setStatus(tanResponse.getMsgInfo().getStatus().toString());
				error.setMessage(tanResponse.getMsgInfo().getMessage());
				error.setDescription(tanResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaNregaV2Process(IbRequestMaster result, CronInfo info) {
		NREGAResponse nregaResponse = null;
		try {
			logger.info(" inside UdyogAadharResponse V2Process() ::  ");
			nregaResponse = om.readValue(info.getResponse(), NREGAResponse.class);
			if (nregaResponse != null && nregaResponse.getMsgInfo() != null	&& nregaResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (nregaResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(nregaResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(nregaResponse.getMsgInfo().getCode());
					error.setStatus(nregaResponse.getMsgInfo().getStatus().toString());
					error.setMessage(nregaResponse.getMsgInfo().getMessage());
					error.setDescription(nregaResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(nregaResponse.getMsgInfo().getCode());
				error.setStatus(nregaResponse.getMsgInfo().getStatus().toString());
				error.setMessage(nregaResponse.getMsgInfo().getMessage());
				error.setDescription(nregaResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(nregaResponse.getMsgInfo().getCode());
				error.setStatus(nregaResponse.getMsgInfo().getStatus().toString());
				error.setMessage(nregaResponse.getMsgInfo().getMessage());
				error.setDescription(nregaResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaForm16QuarterlyV2Process(IbRequestMaster result, CronInfo info) {
		Form16QuatResponse form16QuatResponse =null;
		try {
			logger.info(" inside karzaForm16QuarterlyV2Process() ::  ");
			form16QuatResponse = om.readValue(info.getResponse(), Form16QuatResponse
					.class);
			if (form16QuatResponse != null && form16QuatResponse.getMsgInfo() != null	&& form16QuatResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (form16QuatResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(form16QuatResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(form16QuatResponse.getMsgInfo().getCode());
					error.setStatus(form16QuatResponse.getMsgInfo().getStatus().toString());
					error.setMessage(form16QuatResponse.getMsgInfo().getMessage());
					error.setDescription(form16QuatResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(form16QuatResponse.getMsgInfo().getCode());
				error.setStatus(form16QuatResponse.getMsgInfo().getStatus().toString());
				error.setMessage(form16QuatResponse.getMsgInfo().getMessage());
				error.setDescription(form16QuatResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(form16QuatResponse.getMsgInfo().getCode());
				error.setStatus(form16QuatResponse.getMsgInfo().getStatus().toString());
				error.setMessage(form16QuatResponse.getMsgInfo().getMessage());
				error.setDescription(form16QuatResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaUanLookupV2Process(IbRequestMaster result, CronInfo info) {
		UanLookupResponse uanLookupResponse =null;
		try {
			logger.info(" inside Bank Account V2Process() ::  ");
			uanLookupResponse = om.readValue(info.getResponse(), UanLookupResponse
					.class);
			if (uanLookupResponse != null && uanLookupResponse.getMsgInfo() != null	&& uanLookupResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (uanLookupResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(uanLookupResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(uanLookupResponse.getMsgInfo().getCode());
					error.setStatus(uanLookupResponse.getMsgInfo().getStatus().toString());
					error.setMessage(uanLookupResponse.getMsgInfo().getMessage());
					error.setDescription(uanLookupResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(uanLookupResponse.getMsgInfo().getCode());
				error.setStatus(uanLookupResponse.getMsgInfo().getStatus().toString());
				error.setMessage(uanLookupResponse.getMsgInfo().getMessage());
				error.setDescription(uanLookupResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(uanLookupResponse.getMsgInfo().getCode());
				error.setStatus(uanLookupResponse.getMsgInfo().getStatus().toString());
				error.setMessage(uanLookupResponse.getMsgInfo().getMessage());
				error.setDescription(uanLookupResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaEmployerLookupProcess(IbRequestMaster result, CronInfo info) {
		EmployerLookupResponse  response = null;
		try {
			logger.info(" inside karzaEmployerLookupProcess() ::  ");
			response = om.readValue(info.getResponse(), EmployerLookupResponse.class);
			if (response != null && response.getMsgInfo() != null && response.getMsgInfo().getCode().equals("101")
					&& response.getMsgInfo().getStatus().toString().equals("Success")) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaEleV3Process(IbRequestMaster result, CronInfo info) {
		ElectricityResponse2  response = null;
		try {
			logger.info(" inside karzaEleV2Process() ::  ");
			response = om.readValue(info.getResponse(), ElectricityResponse2.class);
			if (response != null) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaIfscCheckProcess(IbRequestMaster result, CronInfo info) {
		IFSCResponse  response = null;
		try {
			logger.info(" inside karzaEleV2Process() ::  ");
			response = om.readValue(info.getResponse(), IFSCResponse.class);
			if (response != null) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaIcwaiMemberV2Process(IbRequestMaster result, CronInfo info) {
		ICWAIMembershipResponse icwaiMembershipResponse = null;
		try {
			logger.info(" inside Form ICWAI Memebership V2Process() ::  ");
			icwaiMembershipResponse = om.readValue(info.getResponse(), ICWAIMembershipResponse
					.class);
			if (icwaiMembershipResponse != null && icwaiMembershipResponse.getMsgInfo() != null	&& icwaiMembershipResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (icwaiMembershipResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(icwaiMembershipResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(icwaiMembershipResponse.getMsgInfo().getCode());
					error.setStatus(icwaiMembershipResponse.getMsgInfo().getStatus().toString());
					error.setMessage(icwaiMembershipResponse.getMsgInfo().getMessage());
					error.setDescription(icwaiMembershipResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(icwaiMembershipResponse.getMsgInfo().getCode());
				error.setStatus(icwaiMembershipResponse.getMsgInfo().getStatus().toString());
				error.setMessage(icwaiMembershipResponse.getMsgInfo().getMessage());
				error.setDescription(icwaiMembershipResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(icwaiMembershipResponse.getMsgInfo().getCode());
				error.setStatus(icwaiMembershipResponse.getMsgInfo().getStatus().toString());
				error.setMessage(icwaiMembershipResponse.getMsgInfo().getMessage());
				error.setDescription(icwaiMembershipResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaPngV2Process(IbRequestMaster result, CronInfo info) {
		PngResponse pngResponse = null;
		try {
			logger.info(" inside karzaPngV2Process() ::  ");
			pngResponse = om.readValue(info.getResponse(), PngResponse
					.class);
			if (pngResponse != null && pngResponse.getMsgInfo() != null	&& pngResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (pngResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(pngResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(pngResponse.getMsgInfo().getCode());
					error.setStatus(pngResponse.getMsgInfo().getStatus().toString());
					error.setMessage(pngResponse.getMsgInfo().getMessage());
					error.setDescription(pngResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(pngResponse.getMsgInfo().getCode());
				error.setStatus(pngResponse.getMsgInfo().getStatus().toString());
				error.setMessage(pngResponse.getMsgInfo().getMessage());
				error.setDescription(pngResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(pngResponse.getMsgInfo().getCode());
				error.setStatus(pngResponse.getMsgInfo().getStatus().toString());
				error.setMessage(pngResponse.getMsgInfo().getMessage());
				error.setDescription(pngResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaAADHARV2Process(IbRequestMaster result, CronInfo info) {
		AadharResponse  response = null;
		try {
			logger.info(" inside karzaEleV2Process() ::  ");
			response = om.readValue(info.getResponse(), AadharResponse.class);
			if (response != null) {

				if (response.getPayload() != null && response.getPayload().getPdfPath() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	
	
	private void karzaESICIdV2Process(IbRequestMaster result, CronInfo info) {
		ESICIdResponse esicIdResponse = null;
		try {
			logger.info(" inside Form 16 Quarterly V2Process() ::  ");
			esicIdResponse = om.readValue(info.getResponse(), ESICIdResponse
					.class);
			if (esicIdResponse != null && esicIdResponse.getMsgInfo() != null	&& esicIdResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (esicIdResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(esicIdResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(esicIdResponse.getMsgInfo().getCode());
					error.setStatus(esicIdResponse.getMsgInfo().getStatus().toString());
					error.setMessage(esicIdResponse.getMsgInfo().getMessage());
					error.setDescription(esicIdResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(esicIdResponse.getMsgInfo().getCode());
				error.setStatus(esicIdResponse.getMsgInfo().getStatus().toString());
				error.setMessage(esicIdResponse.getMsgInfo().getMessage());
				error.setDescription(esicIdResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(esicIdResponse.getMsgInfo().getCode());
				error.setStatus(esicIdResponse.getMsgInfo().getStatus().toString());
				error.setMessage(esicIdResponse.getMsgInfo().getMessage());
				error.setDescription(esicIdResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaForm16AuthenticationV2Process(IbRequestMaster result, CronInfo info) {
		Form16Response form16Response =null;
		try {
			logger.info(" inside Form 16 Quarterly V2Process() ::  ");
			form16Response = om.readValue(info.getResponse(), Form16Response
					.class);
			if (form16Response != null && form16Response.getMsgInfo() != null	&& form16Response.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (form16Response.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(form16Response.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(form16Response.getMsgInfo().getCode());
					error.setStatus(form16Response.getMsgInfo().getStatus().toString());
					error.setMessage(form16Response.getMsgInfo().getMessage());
					error.setDescription(form16Response.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(form16Response.getMsgInfo().getCode());
				error.setStatus(form16Response.getMsgInfo().getStatus().toString());
				error.setMessage(form16Response.getMsgInfo().getMessage());
				error.setDescription(form16Response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(form16Response.getMsgInfo().getCode());
				error.setStatus(form16Response.getMsgInfo().getStatus().toString());
				error.setMessage(form16Response.getMsgInfo().getMessage());
				error.setDescription(form16Response.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaWebsiteDomainV2Process(IbRequestMaster result, CronInfo info) {
		WebsiteDomainResponse websiteDomainResponse =null;
		try {
			logger.info(" inside Website Domain V2Process() ::  ");
			websiteDomainResponse = om.readValue(info.getResponse(), WebsiteDomainResponse
					.class);
			if (websiteDomainResponse != null && websiteDomainResponse.getMsgInfo() != null	&& websiteDomainResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (websiteDomainResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(websiteDomainResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(websiteDomainResponse.getMsgInfo().getCode());
					error.setStatus(websiteDomainResponse.getMsgInfo().getStatus().toString());
					error.setMessage(websiteDomainResponse.getMsgInfo().getMessage());
					error.setDescription(websiteDomainResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} 
			else
			{
				ErrorInfo error=new ErrorInfo();
				error.setCode(websiteDomainResponse.getMsgInfo().getCode());
				error.setStatus(websiteDomainResponse.getMsgInfo().getStatus().toString());
				error.setMessage(websiteDomainResponse.getMsgInfo().getMessage());
				error.setDescription(websiteDomainResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} 
		catch (Exception e) 
		{
			logger.error(" Error in set response to entity :: " + e);
			try 
			{
				ErrorInfo error=new ErrorInfo();
				error.setCode(websiteDomainResponse.getMsgInfo().getCode());
				error.setStatus(websiteDomainResponse.getMsgInfo().getStatus().toString());
				error.setMessage(websiteDomainResponse.getMsgInfo().getMessage());
				error.setDescription(websiteDomainResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void panValidationProcess(IbRequestMaster result, CronInfo info, String jsonRequest) 
	{
		PanValidationRes response = null;
		PanApiRequest panRequest=null;
		try
		{
			logger.info(" inside panValidationProcess() ::  ");
			panRequest=om.readValue(jsonRequest, PanApiRequest.class);
			response = om.readValue(info.getResponse(), PanValidationRes.class);
			if (response != null && response.getMsgInfo() != null
					&& response.getMsgInfo().getDescription().equals("Success")
					&& response.getMsgInfo().getStatus().equals("SUCCESS")) {

				String pdfPath=util.getPanValidationPdf(response,panRequest,"PAN_VALIDATION");
				if (pdfPath != null ) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(pdfPath);
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(response.getMsgInfo().getCode());
					error.setStatus(response.getMsgInfo().getStatus().toString());
					error.setMessage(response.getMsgInfo().getMessage());
					error.setDescription(response.getMsgInfo().getDescription());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}

			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void karzaBankAccountV2Process(IbRequestMaster result, CronInfo info) {
		BankAccountResponse bankAccountResponse = null;
		try {
			logger.info(" inside Bank Account V2Process() ::  ");
			bankAccountResponse = om.readValue(info.getResponse(), BankAccountResponse
					.class);
			if (bankAccountResponse != null && bankAccountResponse.getMsgInfo() != null	&& bankAccountResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (bankAccountResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(bankAccountResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(bankAccountResponse.getMsgInfo().getCode());
					error.setStatus(bankAccountResponse.getMsgInfo().getStatus().toString());
					error.setMessage(bankAccountResponse.getMsgInfo().getMessage());
					error.setDescription(bankAccountResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(bankAccountResponse.getMsgInfo().getCode());
				error.setStatus(bankAccountResponse.getMsgInfo().getStatus().toString());
				error.setMessage(bankAccountResponse.getMsgInfo().getMessage());
				error.setDescription(bankAccountResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(bankAccountResponse.getMsgInfo().getCode());
				error.setStatus(bankAccountResponse.getMsgInfo().getStatus().toString());
				error.setMessage(bankAccountResponse.getMsgInfo().getMessage());
				error.setDescription(bankAccountResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaNameSimilarityV2Process(IbRequestMaster result, CronInfo info) {
		NameSimilarityResponse nameSimilarityResponse =null;
		try {
			logger.info(" inside NameSimilarity V2Process() ::  ");
			nameSimilarityResponse = om.readValue(info.getResponse(), NameSimilarityResponse
					.class);
			if (nameSimilarityResponse != null && nameSimilarityResponse.getMsgInfo() != null	&& nameSimilarityResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (nameSimilarityResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(nameSimilarityResponse.getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(nameSimilarityResponse.getMsgInfo().getCode());
					error.setStatus(nameSimilarityResponse.getMsgInfo().getStatus().toString());
					error.setMessage(nameSimilarityResponse.getMsgInfo().getMessage());
					error.setDescription(nameSimilarityResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(nameSimilarityResponse.getMsgInfo().getCode());
				error.setStatus(nameSimilarityResponse.getMsgInfo().getStatus().toString());
				error.setMessage(nameSimilarityResponse.getMsgInfo().getMessage());
				error.setDescription(nameSimilarityResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(nameSimilarityResponse.getMsgInfo().getCode());
				error.setStatus(nameSimilarityResponse.getMsgInfo().getStatus().toString());
				error.setMessage(nameSimilarityResponse.getMsgInfo().getMessage());
				error.setDescription(nameSimilarityResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaAddressMatchingV2Process(IbRequestMaster result, CronInfo info) {
		AddressResponse addressResponse = null;
		try {
			logger.info(" inside AddressResponse V2Process() ::  ");
			addressResponse = om.readValue(info.getResponse(), AddressResponse
					.class);
			if (addressResponse != null && addressResponse.getMsgInfo() != null	&& addressResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (addressResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(addressResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(addressResponse.getMsgInfo().getCode());
					error.setStatus(addressResponse.getMsgInfo().getStatus().toString());
					error.setMessage(addressResponse.getMsgInfo().getMessage());
					error.setDescription(addressResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(addressResponse.getMsgInfo().getCode());
				error.setStatus(addressResponse.getMsgInfo().getStatus().toString());
				error.setMessage(addressResponse.getMsgInfo().getMessage());
				error.setDescription(addressResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(addressResponse.getMsgInfo().getCode());
				error.setStatus(addressResponse.getMsgInfo().getStatus().toString());
				error.setMessage(addressResponse.getMsgInfo().getMessage());
				error.setDescription(addressResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}


	private void karzaUdyogAadharV2Process(IbRequestMaster result, CronInfo info) {
		UdyogAadharResponse udyogAadharResponse = null;
		try {
			logger.info(" inside UdyogAadharResponse V2Process() ::  ");
			udyogAadharResponse = om.readValue(info.getResponse(), UdyogAadharResponse.class);
			if (udyogAadharResponse != null && udyogAadharResponse.getMsgInfo() != null	&& udyogAadharResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (udyogAadharResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(udyogAadharResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(udyogAadharResponse.getMsgInfo().getCode());
					error.setStatus(udyogAadharResponse.getMsgInfo().getStatus().toString());
					error.setMessage(udyogAadharResponse.getMsgInfo().getMessage());
					error.setDescription(udyogAadharResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(udyogAadharResponse.getMsgInfo().getCode());
				error.setStatus(udyogAadharResponse.getMsgInfo().getStatus().toString());
				error.setMessage(udyogAadharResponse.getMsgInfo().getMessage());
				error.setDescription(udyogAadharResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(udyogAadharResponse.getMsgInfo().getCode());
				error.setStatus(udyogAadharResponse.getMsgInfo().getStatus().toString());
				error.setMessage(udyogAadharResponse.getMsgInfo().getMessage());
				error.setDescription(udyogAadharResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}

	private void karzaShopEstablishmentV2Process(IbRequestMaster result, CronInfo info) {
		ShopEstablishmentResponse shopEstablishmentResponse = null;
		try {
			logger.info(" inside Shop Establishment V2Process() ::  ");
			shopEstablishmentResponse = om.readValue(info.getResponse(), ShopEstablishmentResponse.class);
			if (shopEstablishmentResponse != null && shopEstablishmentResponse.getMsgInfo() != null	&& shopEstablishmentResponse.getMsgInfo().getStatus().toString().equals("Success")
					) {

				if (shopEstablishmentResponse.getPayload() != null) {
					result.setRequest_status(ResquestStatus.SUCCESS.toString());
					try {
						result.setPdf_path(shopEstablishmentResponse.getPayload().getPdfPath());
						result.setFinal_response(info.getResponse());
					} catch (Exception e) {
						logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
					}
				} else {
					ErrorInfo error=new ErrorInfo();
					error.setCode(shopEstablishmentResponse.getMsgInfo().getCode());
					error.setStatus(shopEstablishmentResponse.getMsgInfo().getStatus().toString());
					error.setMessage(shopEstablishmentResponse.getMsgInfo().getMessage());
					error.setDescription(shopEstablishmentResponse.getMsgInfo().getMessage());
					result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
					result.setRequest_status(ResquestStatus.FAILURE.toString());
				}
			} else {
				ErrorInfo error=new ErrorInfo();
				error.setCode(shopEstablishmentResponse.getMsgInfo().getCode());
				error.setStatus(shopEstablishmentResponse.getMsgInfo().getStatus().toString());
				error.setMessage(shopEstablishmentResponse.getMsgInfo().getMessage());
				error.setDescription(shopEstablishmentResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
				result.setRequest_status(ResquestStatus.FAILURE.toString());
			}
			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(shopEstablishmentResponse.getMsgInfo().getCode());
				error.setStatus(shopEstablishmentResponse.getMsgInfo().getStatus().toString());
				error.setMessage(shopEstablishmentResponse.getMsgInfo().getMessage());
				error.setDescription(shopEstablishmentResponse.getMsgInfo().getMessage());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}
	}
	private void hdfcProcess(IbRequestMaster result, CronInfo info) {
		ApiResponse response=null;
		try {
			logger.info(" inside hdfcProcess() ::  ");

			response=om.readValue(info.getResponse(),ApiResponse.class);
			if(response!=null&&response.getPayload()!=null && response.getPayload().getPdfPath()!=null)
			{

				result.setRequest_status(ResquestStatus.SUCCESS.toString());
				try {
					result.setPdf_path(response.getPayload().getPdfPath());
					result.setFinal_response(info.getResponse());

				}
				catch(Exception e) {
					e.printStackTrace();
					logger.error("We are in exception");
				}
			}
			else
			{
				result.setRequest_status(ResquestStatus.FAILURE.toString());
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			}

			logger.info(" calling updateRecord() :: record : " + result);
			dbConnection.updateRecord(result);
		} catch (Exception e) {
			logger.error(" Error in set response to entity :: " + e);
			try {
				ErrorInfo error=new ErrorInfo();
				error.setCode(response.getMsgInfo().getCode());
				error.setStatus(response.getMsgInfo().getStatus().toString());
				error.setMessage(response.getMsgInfo().getMessage());
				error.setDescription(response.getMsgInfo().getDescription());
				result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
			} catch (Exception e2) {
				logger.error(" Error in set error pdf :: " + e2);
			}
			result.setRequest_status(ResquestStatus.FAILURE.toString());
			dbConnection.updateRecord(result);
		}

	}

	
	 private void karzaTeleV3Process(IbRequestMaster result, CronInfo info) {
	       
	        TelephoneResponse2 teleRes = null;
	        try {
	            logger.info(" inside Karza Tele V3 Process() ::  ");
	            teleRes = om.readValue(info.getResponse(), TelephoneResponse2.class);
	            if (teleRes != null && teleRes.getMsgInfo() != null    && teleRes.getMsgInfo().getStatus().toString().equals("Success")
	                    ) {

	                if (teleRes.getPayload() != null) {
	                    result.setRequest_status(ResquestStatus.SUCCESS.toString());
	                    try {
	                        result.setPdf_path(teleRes.getPayload().getPdfPath());
	                        result.setFinal_response(info.getResponse());
	                    } catch (Exception e) {
	                        logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
	                    }
	                } else {
	                    ErrorInfo error=new ErrorInfo();
	                    error.setCode(teleRes.getMsgInfo().getCode());
	                    error.setStatus(teleRes.getMsgInfo().getStatus().toString());
	                    error.setMessage(teleRes.getMsgInfo().getMessage());
	                    error.setDescription(teleRes.getMsgInfo().getMessage());
	                    result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
	                    result.setRequest_status(ResquestStatus.FAILURE.toString());
	                }
	            } else {
	                ErrorInfo error=new ErrorInfo();
	                error.setCode(teleRes.getMsgInfo().getCode());
	                error.setStatus(teleRes.getMsgInfo().getStatus().toString());
	                error.setMessage(teleRes.getMsgInfo().getMessage());
	                error.setDescription(teleRes.getMsgInfo().getMessage());
	                result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
	                result.setRequest_status(ResquestStatus.FAILURE.toString());
	            }
	            logger.info(" calling updateRecord() :: record : " + result);
	            dbConnection.updateRecord(result);
	        } catch (Exception e) {
	            logger.error(" Error in set response to entity :: " + e);
	            try {
	                ErrorInfo error=new ErrorInfo();
	                error.setCode(teleRes.getMsgInfo().getCode());
	                error.setStatus(teleRes.getMsgInfo().getStatus().toString());
	                error.setMessage(teleRes.getMsgInfo().getMessage());
	                error.setDescription(teleRes.getMsgInfo().getMessage());
	                result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
	            } catch (Exception e2) {
	                logger.error(" Error in set error pdf :: " + e2);
	            }
	            result.setRequest_status(ResquestStatus.FAILURE.toString());
	            dbConnection.updateRecord(result);
	        }
	    }
	   
	    private void karzaCaMembershipAuthProcess(IbRequestMaster result, CronInfo info) {

	        CAMemberShipAuthResponse caMemberShipAuthResponse = null;
	        try {
	            logger.info(" inside CA MemberShip Auth Response V2 Process() ::  ");
	            caMemberShipAuthResponse = om.readValue(info.getResponse(), CAMemberShipAuthResponse
	                    .class);
	            if (caMemberShipAuthResponse != null && caMemberShipAuthResponse.getMsgInfo() != null    && caMemberShipAuthResponse.getMsgInfo().getStatus().toString().equals("Success")
	                    ) {

	                if (caMemberShipAuthResponse.getPayload() != null) {
	                    result.setRequest_status(ResquestStatus.SUCCESS.toString());
	                    try {
	                        result.setPdf_path(caMemberShipAuthResponse.getPayload().getPdfPath());
	                        result.setFinal_response(info.getResponse());
	                    } catch (Exception e) {
	                        logger.error(" unable to set "+result.getService_provider()+" pdfPath and response to result :: " + e);
	                    }
	                } else {
	                    ErrorInfo error=new ErrorInfo();
	                    error.setCode(caMemberShipAuthResponse.getMsgInfo().getCode());
	                    error.setStatus(caMemberShipAuthResponse.getMsgInfo().getStatus().toString());
	                    error.setMessage(caMemberShipAuthResponse.getMsgInfo().getMessage());
	                    error.setDescription(caMemberShipAuthResponse.getMsgInfo().getMessage());
	                    result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
	                    result.setRequest_status(ResquestStatus.FAILURE.toString());
	                }
	            } else {
	                ErrorInfo error=new ErrorInfo();
	                error.setCode(caMemberShipAuthResponse.getMsgInfo().getCode());
	                error.setStatus(caMemberShipAuthResponse.getMsgInfo().getStatus().toString());
	                error.setMessage(caMemberShipAuthResponse.getMsgInfo().getMessage());
	                error.setDescription(caMemberShipAuthResponse.getMsgInfo().getMessage());
	                result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
	                result.setRequest_status(ResquestStatus.FAILURE.toString());
	            }
	            logger.info(" calling updateRecord() :: record : " + result);
	            dbConnection.updateRecord(result);
	        } catch (Exception e) {
	            logger.error(" Error in set response to entity :: " + e);
	            try {
	                ErrorInfo error=new ErrorInfo();
	                error.setCode(caMemberShipAuthResponse.getMsgInfo().getCode());
	                error.setStatus(caMemberShipAuthResponse.getMsgInfo().getStatus().toString());
	                error.setMessage(caMemberShipAuthResponse.getMsgInfo().getMessage());
	                error.setDescription(caMemberShipAuthResponse.getMsgInfo().getMessage());
	                result.setPdf_path(util.pdfFailure(error,result.getService_provider(),resProp));
	            } catch (Exception e2) {
	                logger.error(" Error in set error pdf :: " + e2);
	            }
	            result.setRequest_status(ResquestStatus.FAILURE.toString());
	            dbConnection.updateRecord(result);
	        }
	  
	       
	    }
	
}
