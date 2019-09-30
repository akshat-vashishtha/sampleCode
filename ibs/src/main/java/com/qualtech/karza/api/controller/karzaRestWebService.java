package com.qualtech.karza.api.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.qualtech.karza.api.common.dto.KarzaDto;
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
import com.qualtech.karza.api.db.DBKarza;
import com.qualtech.karza.api.request.KarzaFullReqRes;
import com.qualtech.karza.api.request.KarzaReqRes;
import com.qualtech.karza.api.request.KycOcrRequest;
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
import com.qualtech.karza.api.response.KycOcrResponse;
import com.qualtech.karza.api.response.LpgIdentificationResponse;
import com.qualtech.karza.api.response.LpgResponse;
import com.qualtech.karza.api.response.LpgResponse2;
import com.qualtech.karza.api.response.MCASignatureResponse;
import com.qualtech.karza.api.response.MessageInfo;
import com.qualtech.karza.api.response.NREGAResponse;
import com.qualtech.karza.api.response.NameSimilarityResponse;
import com.qualtech.karza.api.response.PanResponse;
import com.qualtech.karza.api.response.PassportResponse;
import com.qualtech.karza.api.response.PngResponse;
import com.qualtech.karza.api.response.RCAuthResponse;
import com.qualtech.karza.api.response.RCSearchResponse;
import com.qualtech.karza.api.response.ShopEstablishmentResponse;
import com.qualtech.karza.api.response.TanResponse;
import com.qualtech.karza.api.response.TelephoneResponse;
import com.qualtech.karza.api.response.TelephoneResponse2;
import com.qualtech.karza.api.response.UanLookupResponse;
import com.qualtech.karza.api.response.UdyogAadharResponse;
import com.qualtech.karza.api.response.VoterResponse;
import com.qualtech.karza.api.response.WebsiteDomainResponse;
import com.qualtech.karza.api.service.KarzaService;
import com.qualtech.karza.api.utils.PDFConvert;
import com.qualtech.karza.api.utils.UniqueId;


@Controller
@RequestMapping("/karza/api")
public class karzaRestWebService {
	
	static Logger logger = Logger.getLogger(karzaRestWebService.class.getName());
	@Autowired KarzaService karzaService;
	@Autowired DBKarza dbKarza;
	@Autowired PDFConvert pdfConvert;
	
	
	@RequestMapping(value ="/v2/requestKarzaKycOcr", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public KycOcrResponse kycOCRRequestProcess(@RequestBody KycOcrRequest ocrRequest) 
	{
		
		KycOcrResponse ocrResponse = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		MessageInfo msgInfo = null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("ocrRequest : " + uniqueId);
			NDC.push(ocrRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(ocrRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaKycOcr");
		} 

		catch (Exception ex) 
		{
			ex.printStackTrace();
			logger.error("karzaRestWebService || kycOCRRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(ocrRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(ocrRequest.getHeader().getCorrelationId());
			rq.setAppid(ocrRequest.getHeader().getAppId());
			rq.setToken(ocrRequest.getHeader().getToken());
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.info("karzaRestWebService || kycOCRRequestProcess() || Error While saving final req,res from service : " + ex);
		}
		try 
		{
			ocrResponse = karzaService.ocrRequestServiceQC(ocrRequest, rq);
			msgInfo = ocrResponse.getMsgInfo();
			try
			{
				ocrResponse.getPayload().setByteArray("");
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(ocrResponse));
				rq.setPdfPath(pdfPath);
				fullReqRes.setOcrRequest(ocrRequest);
				fullReqRes.setOcrResponse(ocrResponse);
				fullReqRes.setReqRes(rq);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveKycOcrRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("karzaRestWebService || kycOCRRequestProcess() || There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error("karzaRestWebService || kycOCRRequestProcess() || Error while inserting karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.error("Error While getting response from service : " + ex);
		}
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return ocrResponse;
	}
	
	
	@RequestMapping(value ="/v2/requestKarzaDL", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public DlResponse dlRequestProcess(@RequestBody DlRequest dlRequest) 
	{
		
		DlResponse dlRes = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		MessageInfo msgInfo = null;
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			
			NDC.push("dlRequest : " + uniqueId);
			NDC.push(dlRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(dlRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaDL");
		} 
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || dlRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(dlRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(dlRequest.getHeader().getCorrelationId());
			rq.setAppid(dlRequest.getHeader().getAppId());
			rq.setToken(dlRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || dlRequestProcess() || Error While setting fianl req,res from service : " + ex);
		}
		try 
		{
			
			 dlRes = karzaService.dlRequestService(dlRequest,rq);
			msgInfo = dlRes.getMsgInfo();
			if(dlRes.getMsgInfo().getCode() !=null){
			if(dlRes.getMsgInfo().getCode().equals("101"))
			{
			if(dlRes.getPayload() != null)
			{
				
				KarzaDto kDto =pdfConvert.getPdfByteArrayDl(dlRes,dlRequest,service.getPdf_name());

				if (kDto != null) 
				{
					pdfPath=kDto.getFilePath();
					dlRes.getPayload().setPdfPath(kDto.getFilePath());
					dlRes.getPayload().setByteArray(kDto.getByteArray());
				}
			}
			}}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(dlRes));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setDlRequest1(dlRequest);
				fullReqRes.setDlRes1(dlRes);

				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveDlReqRes(fullReqRes);
						} catch (Exception e) {
							logger.error("karzaRestWebService || dlRequestProcess() || There is no data saved in database : " + e);
						}
					}
				}).start();

			}
			catch(Exception e)
			{
				logger.error("karzaRestWebService || dlRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("karzaRestWebService || dlRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return dlRes;
	}
	
	@RequestMapping(value ="/v3/requestKarzaTele", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public TelephoneResponse2 teleProcessQC(@RequestBody TelephoneRequest2 teleRequest )
	{
		TelephoneResponse2 teleRes = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueRequestID = UniqueId.getUniqueId();
		String pdfPath=null;

		try
		{
			NDC.push("teleRequest : " + UniqueId.getUniqueId());
			NDC.push(teleRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +om.writeValueAsString(teleRequest));
			service=dbKarza.getServiceCredential("/v3/requestKarzaTele");
		}
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || teleProcessQC() || Error While mapping response header : " + ex);
		}
		try
		{
			rq.setUniqueId(uniqueRequestID);
			rq.setRequest(om.writeValueAsString(teleRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(teleRequest.getHeader().getCorrelationId());
			rq.setAppid(teleRequest.getHeader().getAppId());
			rq.setToken(teleRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		}
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || teleProcessQC() || Error While setting fianl req,res from service : " + ex);
		}
		try
		{
			teleRes = karzaService.teleRequestServiceQC(teleRequest,rq);
			msgInfo = teleRes.getMsgInfo();
			
			try{
				if(teleRes.getMsgInfo().getCode()!=null)
				{
					if(teleRes.getMsgInfo().getCode().equals("101"))
					{
					
					if(teleRes.getPayload() != null)
						{
						KarzaDto dto = pdfConvert.getPdfByteArrayTeleQC(teleRes,teleRequest,service.getPdf_name());
						if (dto != null)
							{
							pdfPath=dto.getFilePath();
							teleRes.getPayload().setPdfPath(dto.getFilePath());
							teleRes.getPayload().setByteArray(dto.getByteArray());
							}
						}
					}
				}
			   }catch(Exception ee){
				   logger.error("karzaRestWebService || teleProcessQC || error:: "+ee);
			}
			
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setResponse(om.writeValueAsString(teleRes));
				rq.setPdfPath(pdfPath);
				rq.setUpdated_Time(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setTeleRequest2(teleRequest);
				fullReqRes.setTeleRes2(teleRes);
				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						try
						{
							 saveTeleRequestResponse2(fullReqRes);
						}
						catch (Exception e)
						{
							logger.error("karzaRestWebService || teleProcessQC() || There is no data saved in database : " + e);
						}
					}

				}).start();
			}
			catch(Exception e)
			{
				logger.error("karzaRestWebService || teleProcessQC() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || teleProcessQC() || Error While getting response from service : " + ex);
		} 
		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return teleRes;
	}
	
	@RequestMapping(value ="/v2/requestKarzaPan", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public PanResponse panRequestProcess(@RequestBody PanRequest panRequest ) 
	{	
		PanResponse PanRes=null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("panRequest : " + uniqueId);
			NDC.push(panRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +om.writeValueAsString(panRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaPan");
		} 
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || panRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(panRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(panRequest.getHeader().getCorrelationId());
			rq.setAppid(panRequest.getHeader().getAppId());
			rq.setToken(panRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || panRequestProcess() || Error While setting fianl req,res from service : " + ex);
		}
		try 
		{
			
			 PanRes = karzaService.panRequestService(panRequest,rq);
			msgInfo = PanRes.getMsgInfo();
			
			try{
				if(msgInfo.getCode() !=null){
					if(msgInfo.getCode().equals("101"))
						{
						if(PanRes.getPayload() != null)
						{
						
							KarzaDto kDto = pdfConvert.getPdfByteArrayPan(PanRes,panRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								PanRes.getPayload().setPdfPath(kDto.getFilePath());
								PanRes.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}
			catch(Exception ee)
			{
				logger.info("KarzaRestWebService || panRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(PanRes));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
	            fullReqRes.setPanRequest(panRequest);
	            fullReqRes.setPanRes(PanRes);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							
							savePanRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("karzaRestWebService || panRequestProcess() || There is no data saved in database : " + e);
						}
					}

					
				}).start();

			}
			catch(Exception e)
			{ 
				logger.error("karzaRestWebService || panRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("karzaRestWebService || panRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return PanRes;
	}

	@RequestMapping(value = "/v3/requestKarzaElec", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public ElectricityResponse2 electricityProcessQC(@RequestBody ElectricalRequest2 elecRequest)
	{
		ElectricityResponse2 elecRes = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueRequestID = UniqueId.getUniqueId();
		String pdfPath=null;
		try 
		{
			NDC.push("elecRequest : " + UniqueId.getUniqueId());
			NDC.push(elecRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(elecRequest));
			service=dbKarza.getServiceCredential("/v3/requestKarzaElec");
		} 
		catch (Exception ex)
		{
			logger.error("karzaRestWebService || electricityProcessQC() || Error While mapping response header : " + ex);
		}
		try
		{
			rq.setUniqueId(uniqueRequestID);
			rq.setRequest(om.writeValueAsString(elecRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(elecRequest.getHeader().getCorrelationId());
			rq.setAppid(elecRequest.getHeader().getAppId());
			rq.setToken(elecRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		}
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || electricityProcessQC() || Error While setting fianl req,res from service : " + ex);
		}
		try 
		{
			 elecRes = karzaService.electricityRequestServiceQC(elecRequest,rq);
			msgInfo = elecRes.getMsgInfo();
			
			try{
				if(elecRes.getMsgInfo().getCode()!=null)
				{
					if(elecRes.getMsgInfo().getCode().equals("101"))
					{
						if(elecRes.getPayload() != null)
						{
							KarzaDto dto = pdfConvert.getPdfByteArrayElecQC(elecRes,elecRequest,service.getPdf_name());

							if (dto != null)
							{
								pdfPath=dto.getFilePath();
								elecRes.getPayload().setPdfPath(dto.getFilePath());
								elecRes.getPayload().setByteArray(dto.getByteArray());
							}
						}
					}
				}
			   }catch(Exception ee){
				   logger.error("karzaRestWebService || electricityProcessQC || error:: "+ee);
			}
			
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueRequestID);
				rq.setResponse(om.writeValueAsString(elecRes));
				rq.setPdfPath(pdfPath);
				rq.setUpdated_Time(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setElecRequest2(elecRequest);
				fullReqRes.setElecRes2(elecRes);
				new Thread(new Runnable() 
				{
					@Override
					public void run() {
						try {
							saveElectrical2RequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("karzaRestWebService || electricityProcessQC() || There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("karzaRestWebService || electricityProcessQC() || Error while updating karza req res:"+e);
			}
		}
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || electricityProcessQC() || Error While getting response from service : " + ex);
		} 
		finally
		{
			NDC.pop();
			NDC.pop();
		}

		return elecRes;
	}
	
	@RequestMapping(value = "/v2/requestKarzaRCAuth", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public RCAuthResponse RCAuthRequestProcess(@RequestBody RCAuthRequest rcAuthRequest) 
	{
		RCAuthResponse rcAuthResponse = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		Calendar cal = Calendar.getInstance();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {
			NDC.push("RCAuthRequest : " + uniqueId);
			NDC.push(rcAuthRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+ om.writeValueAsString(rcAuthRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaRCAuth");
		} catch (Exception ex) {
			logger.error("karzaRestWebService || RCAuthRequestProcess() || Error While mapping response header : " + ex);
		}
		try {
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(rcAuthRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(rcAuthRequest.getHeader().getCorrelationId());
			rq.setAppid(rcAuthRequest.getHeader().getAppId());
			rq.setToken(rcAuthRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} catch (Exception ex) {
			logger.info("karzaRestWebService || RCAuthRequestProcess() || Error While set  req,res from service : " + ex);
		}
		try {
			rcAuthResponse = karzaService.rcAuthRequestService(rcAuthRequest, rq);
			msgInfo = rcAuthResponse.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (rcAuthResponse.getPayload() != null) {
							KarzaDto kDto = pdfConvert.getPdfByteArrayRCAuth(rcAuthResponse, rcAuthRequest,service.getPdf_name());
							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								rcAuthResponse.getPayload().setPdfPath(kDto.getFilePath());
								rcAuthResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || RCAuthRequestProcess || error :: "+ ee.getMessage());
			}
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(rcAuthResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setRcAuthRequest(rcAuthRequest);
				fullReqRes.setRcAuthResponse(rcAuthResponse);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							saveRCAuthRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("karzaRestWebService || RCAuthRequestProcess() || There is no data saved in database : "+ e);
						}
					}
				}).start();
			} catch (Exception e) {
				logger.error("karzaRestWebService || RCAuthRequestProcess() || Error while Saving karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("karzaRestWebService || RCAuthRequestProcess() || Error While getting response from service : " + ex);
		} finally {
			NDC.pop();
			NDC.pop();
		}
		return rcAuthResponse;
	}
	
	@RequestMapping(value = "/v2/requestKarzaFSSAILicence", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public FSSAILicenceResponse fssaiLicenceRequestProcess(@RequestBody FSSAILicenceRequest fssaiLicenceRequest) 
	{
		FSSAILicenceResponse fssaiLicenceResponse = null;
		
		MessageInfo msgInfo = null;
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("fssaiLicenceRequest : " + uniqueId);
			NDC.push(fssaiLicenceRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(fssaiLicenceRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaFSSAILicence");
		} 
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || fssaiLicenceRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(fssaiLicenceRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(fssaiLicenceRequest.getHeader().getCorrelationId());
			rq.setAppid(fssaiLicenceRequest.getHeader().getAppId());
			rq.setToken(fssaiLicenceRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || fssaiLicenceRequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			 fssaiLicenceResponse = karzaService.fssaiLicenceRequestService(fssaiLicenceRequest,rq);
			msgInfo = fssaiLicenceResponse.getMsgInfo();
			try
			{
				if(fssaiLicenceResponse.getMsgInfo().getCode() !=null)
				{
				if(fssaiLicenceResponse.getMsgInfo().getCode().equals("101"))
					{
				if(fssaiLicenceResponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayFssaiLicence(fssaiLicenceResponse, fssaiLicenceRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								fssaiLicenceResponse.getPayload().setPdfPath(kDto.getFilePath());
								fssaiLicenceResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || fssaiLicenceRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				rq.setResponse(om.writeValueAsString(fssaiLicenceResponse));
				fullReqRes.setReqRes(rq);
				fullReqRes.setFssaiLicenceRequest(fssaiLicenceRequest);
				fullReqRes.setFssaiLicenceResponse(fssaiLicenceResponse);
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveFSSAILicenceRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("karzaRestWebService || fssaiLicenceRequestProcess() || There is no data saved in database : " + e);
						}
					}

					
				}).start();
			}
			catch(Exception e)
			{
				logger.error("karzaRestWebService || fssaiLicenceRequestProcess() || Error while updating karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("karzaRestWebService || fssaiLicenceRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return fssaiLicenceResponse;
	}

	
	
	
	@RequestMapping(value = "/v2/requestKarzaElec", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public ElectricityResponse electricityProcess(@RequestBody ElectricalRequest elecRequest)
	{
		ElectricityResponse elecRes = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueRequestID = UniqueId.getUniqueId();
		String pdfPath=null;
		try 
		{
			NDC.push("elecRequest : " + UniqueId.getUniqueId());
			NDC.push(elecRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(elecRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaElec");
		} 
		catch (Exception ex)
		{
			logger.error("karzaRestWebService || electricityProcess() || Error While mapping response header : " + ex);
		}
		try
		{
			rq.setUniqueId(uniqueRequestID);
			rq.setRequest(om.writeValueAsString(elecRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(elecRequest.getHeader().getCorrelationId());
			rq.setAppid(elecRequest.getHeader().getAppId());
			rq.setToken(elecRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		}
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || electricityProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			elecRes = karzaService.electricityRequestService(elecRequest,rq);
			msgInfo = elecRes.getMsgInfo();
			if(elecRes.getMsgInfo().getCode()!=null){
			if(elecRes.getMsgInfo().getCode().equals("101"))
			{
			if(elecRes.getPayload() != null)
			{
				KarzaDto dto =pdfConvert.getPdfByteArrayElec(elecRes,elecRequest,service.getPdf_name());

				if (dto != null)
				{
					pdfPath=dto.getFilePath();
					elecRes.getPayload().setPdfPath(dto.getFilePath());
					elecRes.getPayload().setByteArray(dto.getByteArray());
				}
			}
			}
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueRequestID);
				rq.setResponse(om.writeValueAsString(elecRes));
				rq.setPdfPath(pdfPath);
				rq.setCreatedTime(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setElecRequest(elecRequest);
				fullReqRes.setElecRes(elecRes);
				
				
				new Thread(new Runnable() 
				{
					@Override
					public void run() {
						try {
							saveElectricalRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("karzaRestWebService || electricityProcess() || There is no data saved in database : " + e);
						}
					}

				}).start();
			}
			catch(Exception e)
			{
				logger.error("karzaRestWebService || electricityProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || electricityProcess() || Error While getting response from service : " + ex);
		} 
		finally
		{
			NDC.pop();
			NDC.pop();
		}

		return elecRes;
	}
//////////////////////////////////////////////////////////////Dynamically
	
	@RequestMapping(value = "/v2/requestKarzaTele", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public TelephoneResponse teleProcess(@RequestBody TelephoneRequest teleRequest)
	{
		TelephoneResponse teleRes = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		String uniqueRequestID = UniqueId.getUniqueId();
		String pdfPath = null;
		try {
			NDC.push("teleRequest : " + UniqueId.getUniqueId());
			NDC.push(teleRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(teleRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaTele");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try
		{
			rq.setUniqueId(uniqueRequestID);
			rq.setRequest( om.writeValueAsString(teleRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(teleRequest.getHeader().getCorrelationId());
			rq.setAppid(teleRequest.getHeader().getAppId());
			rq.setToken(teleRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		}
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || electricityProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try
		{
			 teleRes = karzaService.teleRequestService(teleRequest,rq);
			msgInfo = teleRes.getMsgInfo();
			
			if(teleRes.getMsgInfo().getCode()!=null){
			if(teleRes.getMsgInfo().getCode().equals("101"))
			{
			if(teleRes.getPayload() != null){
				KarzaDto dto = pdfConvert.getPdfByteArrayTele(teleRes,teleRequest,service.getPdf_name());
				if (dto != null)
				{
					pdfPath=dto.getFilePath();
					teleRes.getPayload().setPdfPath(dto.getFilePath());
					teleRes.getPayload().setByteArray(dto.getByteArray());
				}
			}
			}}
			try
			{
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueRequestID);
				rq.setResponse(om.writeValueAsString(teleRes));
				rq.setPdfPath(pdfPath);
				rq.setUpdated_Time(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setTeleRequest(teleRequest);
				fullReqRes.setTeleRes(teleRes);
				
				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						try
						{
							 saveTeleRequestResponse(fullReqRes);
						}
						catch (Exception e)
						{
							logger.error("karzaRestWebService || electricityProcess() || There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("karzaRestWebService || electricityProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || electricityProcess() || Error While getting response from service : " + ex);
		} 
		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return teleRes;
	}

	
////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

	

	@RequestMapping(value = "/v2/requestKarzaESICIDAuth", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public ESICIdResponse esicIdRequestProcess(@RequestBody ESICIdRequest esicIdRequest) 
	{
		ESICIdResponse esicIdResponse = null;
		ObjectMapper om = new ObjectMapper();
		MessageInfo msgInfo = null;
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("ESICIdRequest : " + uniqueId);
			NDC.push(esicIdRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(esicIdRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaESICIDAuth");
		} 
		catch (Exception ex) 
		{
			logger.error("karzaRestWebService || esicIdRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(esicIdRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(esicIdRequest.getHeader().getCorrelationId());
			rq.setAppid(esicIdRequest.getHeader().getAppId());
			rq.setToken(esicIdRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("karzaRestWebService || esicIdRequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			 esicIdResponse = karzaService.esicIdRequestService(esicIdRequest,rq);
			msgInfo = esicIdResponse.getMsgInfo();
			
			try
			{   
				if(esicIdResponse.getMsgInfo().getCode() !=null)
				{
					
				if(esicIdResponse.getMsgInfo().getCode().equals("101"))
					{
				if(esicIdResponse.getPayload() != null)
						{
						
							KarzaDto kDto = pdfConvert.getPdfByteArrayEsicId(esicIdResponse, esicIdRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								esicIdResponse.getPayload().setPdfPath(kDto.getFilePath());
								esicIdResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || esicIdRequestProcess() || error :: "+ee.getMessage());
			}
			try
			{
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(esicIdResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setEsicIdRequest(esicIdRequest);
				fullReqRes.setEsicIdResponse(esicIdResponse);
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveEsicIdRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || esicIdRequestProcess() || There is no data saved in database : " + e);
						}
					}

					
				}).start();
			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || esicIdRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || esicIdRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return esicIdResponse;
	}
	

	
	
	@RequestMapping(value = "/v2/requestKarzaPng", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public PngResponse pngRequestProcess(@RequestBody PngRequest pngRequest) 
	{
		PngResponse pngResponse = null;
		ObjectMapper om = new ObjectMapper();
		MessageInfo msgInfo = null;
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("pngRequest : " + uniqueId);
			NDC.push(pngRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(pngRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaPng");
		} 
		catch (Exception ex) 
		{
			logger.error("KarzaRestWebService || pngRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(pngRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(pngRequest.getHeader().getCorrelationId());
			rq.setAppid(pngRequest.getHeader().getAppId());
			rq.setToken(pngRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || pngRequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			
			 pngResponse = karzaService.pngRequestService(pngRequest,rq);
		
			msgInfo = pngResponse.getMsgInfo();
			
			try
			{
				if(pngResponse.getMsgInfo().getCode() !=null)
				{
				if(pngResponse.getMsgInfo().getCode().equals("101"))
					{
				if(pngResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayPng(pngResponse, pngRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								pngResponse.getPayload().setPdfPath(kDto.getFilePath());
								pngResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || pngRequestProcess() || error :: "+ee.getMessage());
			}
			try
			{
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(pngResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setPngRequest(pngRequest);
				fullReqRes.setPngResponse(pngResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							savePngRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || pngRequestProcess() || There is no data saved in database : " + e);
						}
					}
					
				}).start();

			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || pngRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || pngRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return pngResponse;
	}


	@RequestMapping(value = "/v2/requestKarzaForm16Quarterly", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public Form16QuatResponse form16QuatRequestProcess(@RequestBody Form16QuatRequest form16QuatRequest) 
	{
		Form16QuatResponse form16QuatResponse = null;
		Calendar cal=Calendar.getInstance();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		KarzaReqRes rq=new KarzaReqRes();
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("Form16QuatRequest : " + uniqueId);
			NDC.push(form16QuatRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(form16QuatRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaForm16Quarterly");
		} 
		catch (Exception ex) 
		{
			logger.error("KarzaRestWebService || form16QuatRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(form16QuatRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(form16QuatRequest.getHeader().getCorrelationId());
			rq.setAppid(form16QuatRequest.getHeader().getAppId());
			rq.setToken(form16QuatRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || form16QuatRequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			
			 form16QuatResponse = karzaService.form16QuatRequestService(form16QuatRequest,rq);
			msgInfo = form16QuatResponse.getMsgInfo();
			
			try
			{    
				if(form16QuatResponse.getMsgInfo().getCode() !=null)
				{
				if(form16QuatResponse.getMsgInfo().getCode().equals("101"))
					{
				if(form16QuatResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayForm16Ouat(form16QuatResponse, form16QuatRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								form16QuatResponse.getPayload().setPdfPath(kDto.getFilePath());
								form16QuatResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || form16QuatRequestProcess() || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(form16QuatResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setForm16QuatRequest(form16QuatRequest);
				fullReqRes.setForm16QuatResponse(form16QuatResponse);
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveForm16QuatRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || form16QuatRequestProcess() || There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || form16QuatRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || form16QuatRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return form16QuatResponse;
	}

	//?????????????????????????????????????????????????????
	
	
	@RequestMapping(value = "/v2/requestKarzaForm16Auth", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public Form16Response form16RequestProcess(@RequestBody Form16Request form16Request ) 
	{
		Form16Response form16Response = null;
		Calendar cal=Calendar.getInstance();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		KarzaReqRes rq=new KarzaReqRes();
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("Form16Request : " + uniqueId);
			NDC.push(form16Request.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(form16Request));
			service=dbKarza.getServiceCredential("/v2/requestKarzaForm16Auth");
		} 
		catch (Exception ex) 
		{
			logger.error("KarzaRestWebService || form16RequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(form16Request));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(form16Request.getHeader().getCorrelationId());
			rq.setAppid(form16Request.getHeader().getAppId());
			rq.setToken(form16Request.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || form16RequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			
			 form16Response = karzaService.form16RequestService(form16Request,rq);
			msgInfo = form16Response.getMsgInfo();
			
			try
			{
				if(form16Response.getMsgInfo().getCode() !=null)
				{
				if(form16Response.getMsgInfo().getCode().equals("101"))
					{
				if(form16Response.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayForm16Auth(form16Response, form16Request,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								form16Response.getPayload().setPdfPath(kDto.getFilePath());
								form16Response.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || form16RequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(form16Response));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setForm16Request(form16Request);
				fullReqRes.setForm16Response(form16Response);
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveForm16RequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || form16RequestProcess() || There is no data saved in database : " + e);
						}
					}

					
				}).start();

			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || form16RequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || form16RequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return form16Response;
	}

	@RequestMapping(value = "/v2/requestKarzaIEC", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public IECResponse IECRequestProcess(@RequestBody IECRequest iecRequest ) 
	{
		IECResponse iecResponse = null;
		ObjectMapper om = new ObjectMapper();
		MessageInfo msgInfo = null;
		Calendar cal=Calendar.getInstance();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		KarzaReqRes rq=new KarzaReqRes();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("IecRequest : " + uniqueId);
			NDC.push(iecRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(iecRequest));
			service=dbKarza.getServiceCredential("/v2/requestKarzaIEC");
		} 
		catch (Exception ex) 
		{
			logger.error("KarzaRestWebService || IECRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(iecRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(iecRequest.getHeader().getCorrelationId());
			rq.setAppid(iecRequest.getHeader().getAppId());
			rq.setToken(iecRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || IECRequestProcess() || Error While saving final req,res from service : " + ex);
		}
		try 
		{
			
			 iecResponse = karzaService.iecRequestService(iecRequest,rq);
			msgInfo = iecResponse.getMsgInfo();
			
			try{
				if(msgInfo.getCode() !=null){
					if(msgInfo.getCode().equals("101"))
						{
						if(iecResponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayIec(iecResponse,iecRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								iecResponse.getPayload().setPdfPath(kDto.getFilePath());
								iecResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}
			catch(Exception ee)
			{
				logger.info("KarzaRestWebService || IECRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(iecResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setIecRequest(iecRequest);
				fullReqRes.setIecResponse(iecResponse);
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							
							saveIECRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || IECRequestProcess() || There is no data saved in database : " + e);
						}
					}

					
				}).start();

			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || IECRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || IECRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return iecResponse;
	}
	
	@RequestMapping(value = "/v2/requestKarzaTan", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
   @ResponseBody	public TanResponse tanRequestProcess(@RequestBody TanRequest tanRequest ) 
	{
		TanResponse tanResponse = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("tanRequest : " + uniqueId);
			NDC.push(tanRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + tanRequest);
			service=dbKarza.getServiceCredential("/v2/requestKarzaTan");
		} 
		catch (Exception ex) 
		{
			logger.error("KarzaRestWebService || tanRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(tanRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(tanRequest.getHeader().getCorrelationId());
			rq.setAppid(tanRequest.getHeader().getAppId());
			rq.setToken(tanRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || tanRequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			
			 tanResponse = karzaService.tanRequestService(tanRequest,rq);
			msgInfo = tanResponse.getMsgInfo();
			
			try
			{
				if(tanResponse.getMsgInfo().getCode() !=null)
				{
				if(tanResponse.getMsgInfo().getCode().equals("101"))
					{
				if(tanResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayTan(tanResponse,tanRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								tanResponse.getPayload().setPdfPath(kDto.getFilePath());
								tanResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || tanRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(tanResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setTanRequest(tanRequest);
				fullReqRes.setTanResponse(tanResponse);
				
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveTanRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || tanRequestProcess() || There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || tanRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || tanRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return tanResponse;
	}
	

	@RequestMapping(value = "/v2/requestKarzaNREGA", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public NREGAResponse NREGARequestProcess(@RequestBody NREGARequest nregaRequest) 
	{
		NREGAResponse nregaResponse = null;
		MessageInfo msgInfo = null;
		String pdfPath = null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		KarzaReqRes rq = new KarzaReqRes();
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service =null;
		Calendar cal=Calendar.getInstance();
		String uniqueId = UniqueId.getUniqueId();
		try {
			NDC.push("NREGARequest : " + uniqueId);
			NDC.push(nregaRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(nregaRequest));
			service= dbKarza.getServiceCredential("/v2/requestKarzaNREGA");
		} catch (Exception ex) {
			logger.error("KarzaRestWebService || NREGARequestProcess() || Error While mapping response header : " + ex);
		}
		try
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(nregaRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(nregaRequest.getHeader().getCorrelationId());
			rq.setAppid(nregaRequest.getHeader().getAppId());
			rq.setToken(nregaRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || NREGARequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			 nregaResponse = karzaService.nregaRequestService(nregaRequest,rq);
			
			msgInfo = nregaResponse.getMsgInfo();
			try{
				
				
				if(msgInfo.getCode() !=null){
					if(msgInfo.getCode().equals("101"))
						{
						if(nregaResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayNrega(nregaResponse,nregaRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								nregaResponse.getPayload().setPdfPath(kDto.getFilePath());
								nregaResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}
			catch(Exception ee)
			{
				logger.info("KarzaRestWebService || NREGARequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setResponse(om.writeValueAsString(nregaResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setNregaRequest(nregaRequest);
				fullReqRes.setNregaResponse(nregaResponse);
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveNregaRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || NREGARequestProcess() || There is no data saved in database : " + e);
						}
					}
	     			}).start();

			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || NREGARequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || NREGARequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return nregaResponse;
	}
	
	@RequestMapping(value = "/v2/requestKarzaShopEstablishment", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public ShopEstablishmentResponse ShopEstablishmentRequestProcess(@RequestBody ShopEstablishmentRequest shopEstablishmentRequest ) 
	{
		ShopEstablishmentResponse shopEstablishmentResponse = null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service =null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("shopEstablishmentRequest : " + uniqueId);
			NDC.push(shopEstablishmentRequest.getHeader().getCorrelationId());
		    logger.info("RequestJson :: " + om.writeValueAsString(shopEstablishmentRequest));
		    service= dbKarza.getServiceCredential("/v2/requestKarzaShopEstablishment");
		} 
		catch (Exception ex) 
		{
			logger.error("KarzaRestWebService || ShopEstablishmentRequestProcess() || Error While mapping response header : " + ex);
		}
		try 
		{
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(shopEstablishmentRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(shopEstablishmentRequest.getHeader().getCorrelationId());
			rq.setAppid(shopEstablishmentRequest.getHeader().getAppId());
			rq.setToken(shopEstablishmentRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || ShopEstablishmentRequestProcess() || Error While saving final req,res from service : " + ex);
		}
		try 
		{
			
			 shopEstablishmentResponse = karzaService.shopEstablishmentRequestService(shopEstablishmentRequest,rq);
			msgInfo = shopEstablishmentResponse.getMsgInfo();
			
			try{
				if(msgInfo.getCode() !=null){
					if(msgInfo.getCode().equals("101"))
						{
						if(shopEstablishmentResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayShopEstabilishmenth(shopEstablishmentResponse,shopEstablishmentRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								shopEstablishmentResponse.getPayload().setPdfPath(kDto.getFilePath());
								shopEstablishmentResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}
			catch(Exception ee)
			{
				logger.info("KarzaRestWebService || ShopEstablishmentRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(shopEstablishmentResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setShopEstablishmentRequest(shopEstablishmentRequest);
				fullReqRes.setShopEstablishmentResponse(shopEstablishmentResponse);
				
				new Thread(new Runnable()
				{
					
					public void run() 
					{
						try
						{
							saveShopEstablishmentReqRes(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || ShopEstablishmentRequestProcess() || There is no data saved in database : " + e);
						}
					}

					
				}).start();
				
			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || ShopEstablishmentRequestProcess() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || ShopEstablishmentRequestProcess() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return shopEstablishmentResponse;
	}
	

	@RequestMapping(value = "/v3/requestKarzaDL", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public DlResponse2 dlRequestProcessQC(@RequestBody DlRequest2 dlRequest) 
	{
		DlResponse2 dlRes = null;
		MessageInfo msgInfo = null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("dlRequest : " + uniqueId);
			NDC.push(dlRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +om.writeValueAsString(dlRequest) );
			 service= dbKarza.getServiceCredential("/v3/requestKarzaDL");
		} 
		catch (Exception ex) 
		{
			logger.error("KarzaRestWebService || dlRequestProcessQC() || Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(dlRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(dlRequest.getHeader().getCorrelationId());
			rq.setAppid(dlRequest.getHeader().getAppId());
			rq.setToken(dlRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("KarzaRestWebService || dlRequestProcessQC() || Error While saving final req,res from service : " + ex);
		}
		try 
		{
			
			 dlRes = karzaService.dlRequestServiceQC(dlRequest,rq);
			msgInfo = dlRes.getMsgInfo();
			
			try{
				if(dlRes.getMsgInfo().getCode() !=null)
				{
				if(dlRes.getMsgInfo().getCode().equals("101"))
					{
					if(dlRes.getPayload() != null)
						{
						
						KarzaDto kDto =pdfConvert.getPdfByteArrayDlQC(dlRes,dlRequest,service.getPdf_name());
	
						if (kDto != null) 
							{
							pdfPath=kDto.getFilePath();
							dlRes.getPayload().setPdfPath(kDto.getFilePath());
							dlRes.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			   }catch(Exception ee){
				   logger.error("karzaRestWebService || dlRequestProcessQC || error:: "+ee);
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(dlRes));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setDlRequest(dlRequest);
				fullReqRes.setDlRes(dlRes);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveDlRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || dlRequestProcessQC() || There is no data saved in database : " + e);
						}
					}

					
				}).start();

			}
			catch(Exception e)
			{
				logger.error("KarzaRestWebService || dlRequestProcessQC() || Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("KarzaRestWebService || dlRequestProcessQC() || Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return dlRes;
	}



	@RequestMapping(value = "/v2/requestKarzaAadhar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public AadharResponse aadharRequestProcess(@RequestBody AadharRequest aadharRequest)
	{
		AadharResponse aadharResponse=null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String pdfPath = null;
		ServiceMaster service=null;
		Calendar cal = Calendar.getInstance();
		KarzaReqRes rq = new KarzaReqRes();
		try {
			
			NDC.push("aadharRequest : " + uniqueId);
			NDC.push(aadharRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +aadharRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaAadhar");
		} catch (Exception ex) {
			logger.error("KarzaRestWebService || aadharRequestProcess() || Error While mapping response header : " + ex);
		}
		try {

			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(aadharRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(aadharRequest.getHeader().getCorrelationId());
			rq.setAppid(aadharRequest.getHeader().getAppId());
			rq.setToken(aadharRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("KarzaRestWebService || aadharRequestProcess() || Error While saving fianl req,res from service : " + ex);
		}
		try {
			 aadharResponse = karzaService.aadharRequestService(aadharRequest,rq);
		   msgInfo = aadharResponse.getMsgInfo();
			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (aadharResponse.getPayload() != null) {
						
							KarzaDto kDto = pdfConvert.getPdfByteArrayAadhar(aadharResponse, aadharRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								aadharResponse.getPayload().setPdfPath(kDto.getFilePath());
								aadharResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || aadharRequestProcess || error :: " + ee.getMessage());
			}

			try {
				
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(aadharResponse));
				rq.setCreatedTime(cal.getTime());
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				
				fullReqRes.setReqRes(rq);
				fullReqRes.setAadharRequest(aadharRequest);
				fullReqRes.setAadharResponse(aadharResponse);
				new Thread(new Runnable() {
	         		@Override
					public void run() {
						try {

							insertsaveAadharResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("KarzaRestWebService || aadharRequestProcess() || There is no data saved in database : " + e);
						}
					}

				}).start();

			} catch (Exception e) {
				logger.error("KarzaRestWebService || aadharRequestProcess() || Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("KarzaRestWebService || aadharRequestProcess() || Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return aadharResponse;
	}

	@RequestMapping(value = "/v2/requestKarzaPassport", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public PassportResponse passportRequestProcess(@RequestBody PassportRequest passportRequest)
	{
		PassportResponse passportResponse = null;
		KarzaReqRes rqres = new KarzaReqRes();
		final KarzaFullReqRes karzaFullReqRes = new KarzaFullReqRes();
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {

			NDC.push("PassportRequest : " + uniqueId);
			NDC.push(passportRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + passportRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaPassport");
		} catch (Exception ex) {
			logger.error("KarzaRestWebService || passportRequestProcess() || Error While mapping response header : " + ex);
		}
		try {

			rqres.setUniqueId(uniqueId);
			rqres.setRequest(om.writeValueAsString(passportRequest));
			rqres.setTag(service.getService_tag());
			rqres.setCreatedTime(cal.getTime());
			rqres.setCorelationid(passportRequest.getHeader().getCorrelationId());
			rqres.setAppid(passportRequest.getHeader().getAppId());
			rqres.setToken(passportRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("KarzaRestWebService || passportRequestProcess() || Error While saving final req,res from service : " + ex);
		}
		try {

			passportResponse = karzaService.passportRequestService(passportRequest, rqres);
			msgInfo = passportResponse.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (passportResponse.getPayload() != null) {
							KarzaDto kDto = pdfConvert.getPdfByteArrayPassport(passportResponse, passportRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								passportResponse.getPayload().setPdfPath(kDto.getFilePath());
								passportResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}

			catch (Exception ee) {
				logger.info("KarzaRestWebService || passportRequestProcess || error :: " + ee.getMessage());
			}
			try {
				rqres.setStatus(msgInfo.getStatus());
				rqres.setUniqueId(uniqueId);
				rqres.setResponse(om.writeValueAsString(passportResponse));
				rqres.setUpdated_Time(cal.getTime());
				rqres.setPdfPath(pdfPath);
				karzaFullReqRes.setReqRes(rqres);
				karzaFullReqRes.setPassportRequest(passportRequest);
				karzaFullReqRes.setPassportResponse(passportResponse);

				new Thread(new Runnable() {
					@Override
					public void run() {
						insertsavepassport(karzaFullReqRes);

					}
				}).start();

			} catch (Exception e) {
				logger.error("KarzaRestWebService || passportRequestProcess() || Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("KarzaRestWebService || passportRequestProcess() || Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}
		return passportResponse;
	}

	@RequestMapping(value = "/v2/requestKarzaCompanyLLPIdentification", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public CompanyLLPIdentificationResponse companyLLPIdentificationRequestProcess(@RequestBody CompanyLLPIdentificationRequest companyLLPIdentificationRequest)
	{
		MessageInfo msgInfo = null;
		CompanyLLPIdentificationResponse companyLLPIdentificationResponse=null;
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String pdfPath = null;
		try {
			NDC.push("CompanyLLPIdentificationRequest : " + uniqueId);
			NDC.push(companyLLPIdentificationRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +companyLLPIdentificationRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaCompanyLLPIdentification");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(companyLLPIdentificationRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(companyLLPIdentificationRequest.getHeader().getCorrelationId());
			rq.setAppid(companyLLPIdentificationRequest.getHeader().getAppId());
			rq.setToken(companyLLPIdentificationRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

		   companyLLPIdentificationResponse = karzaService.companyLLPIdentificationRequestService(companyLLPIdentificationRequest,rq);
			msgInfo = companyLLPIdentificationResponse.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (companyLLPIdentificationResponse.getPayload() != null) {
							KarzaDto kDto = pdfConvert.getPdfByteArrayCompanyLLPIdentification(
									companyLLPIdentificationResponse, companyLLPIdentificationRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								companyLLPIdentificationResponse.getPayload().setPdfPath(kDto.getFilePath());
								companyLLPIdentificationResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info(
						"KarzaRestWebService || companyLLPIdentificationRequestProcess || error :: " + ee.getMessage());
			}
			try {
				
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(companyLLPIdentificationResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setCompanyLLPIdentificationRequest(companyLLPIdentificationRequest);
				fullReqRes.setCompanyLLPIdentificationResponse(companyLLPIdentificationResponse);
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							saveCompanyLLPIdentificationRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();
				
			} catch (Exception e) {
				logger.error("Error while saving karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return companyLLPIdentificationResponse;
	}
	

	@RequestMapping(value = "/v2/requestKarzaITRAuth", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ITRAuthResponse ITRAuthRequestProcess(@RequestBody ITRAuthRequest itrAuthRequest)
	{
		MessageInfo msgInfo = null;
		ITRAuthResponse itrAuthResponse = null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		KarzaReqRes rqres = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		Calendar cal = Calendar.getInstance();

		try {
			NDC.push("ITRAuthRequest : " + uniqueId);
			NDC.push(itrAuthRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + itrAuthRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaITRAuth");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {

			rqres.setUniqueId(uniqueId);
			rqres.setRequest(om.writeValueAsString(itrAuthRequest));
			rqres.setTag(service.getService_tag());
			rqres.setCorelationid(itrAuthRequest.getHeader().getCorrelationId());
			rqres.setAppid(itrAuthRequest.getHeader().getAppId());
			rqres.setToken(itrAuthRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

		    itrAuthResponse = karzaService.itrAuthRequestRequestService(itrAuthRequest, rqres);
			msgInfo = itrAuthResponse.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (itrAuthResponse.getPayload() != null) {
							KarzaDto kDto = pdfConvert.getPdfByteArrayItrAuth(itrAuthResponse, itrAuthRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								itrAuthResponse.getPayload().setPdfPath(kDto.getFilePath());
								itrAuthResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || ITRAuthRequestProcess || error :: " + ee.getMessage());
			}
			try {

				rqres.setStatus(msgInfo.getStatus());
				rqres.setUniqueId(uniqueId);
				rqres.setResponse(om.writeValueAsString(itrAuthResponse));
				rqres.setCreatedTime(cal.getTime());
				rqres.setUpdated_Time(cal.getTime());
				rqres.setPdfPath(pdfPath);

				fullReqRes.setReqRes(rqres);
				fullReqRes.setItrAuthRequest(itrAuthRequest);
				fullReqRes.setItrAuthResponse(itrAuthResponse);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							insertsaveITRAuth(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return itrAuthResponse;
	}
	
	@RequestMapping(value = "/v2/requestKarzaIFSC", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public IFSCResponse IFSCRequestProcess(@RequestBody IFSCRequest ifscRequest) 
	{ 
		IFSCResponse ifscRespons = null;
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		Calendar cal = Calendar.getInstance();
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {

			NDC.push("IFSCRequest : " + uniqueId);
			NDC.push(ifscRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + ifscRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaIFSC");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {

			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(ifscRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(ifscRequest.getHeader().getCorrelationId());
			rq.setAppid(ifscRequest.getHeader().getAppId());
			rq.setToken(ifscRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			 ifscRespons = karzaService.ifscRequestService(ifscRequest, rq);
			msgInfo = ifscRespons.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (ifscRespons.getPayload() != null) {

							KarzaDto kDto = pdfConvert.getPdfByteArrayIFSC(ifscRespons, ifscRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								ifscRespons.getPayload().setPdfPath(kDto.getFilePath());
								ifscRespons.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || IFSCRequestProcess || error :: " + ee.getMessage());
			}
			try {

				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(ifscRespons));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);

				fullReqRes.setReqRes(rq);
				fullReqRes.setIfscRequest(ifscRequest);
				fullReqRes.setIfscResponse(ifscRespons);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							insertsaveIFSC(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();

			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return ifscRespons;
	}


	@RequestMapping(value = "/v2/requestKarzaHSN", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public HSNResponse HSNRequestProcess(@RequestBody HSNRequest hsnRequest)
	{
		HSNResponse hsnResponse =null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {

			NDC.push("HSNRequest : " + uniqueId);
			NDC.push(hsnRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + hsnRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaHSN");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(hsnRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(hsnRequest.getHeader().getCorrelationId());
			rq.setAppid(hsnRequest.getHeader().getAppId());
			rq.setToken(hsnRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			 hsnResponse = karzaService.hsnRequestService(hsnRequest, rq);
			
			msgInfo = hsnResponse.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (hsnResponse.getPayload() != null) {

							KarzaDto kDto = pdfConvert.getPdfByteArrayHsn(hsnResponse, hsnRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								hsnResponse.getPayload().setPdfPath(kDto.getFilePath());
								hsnResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || HSNRequestProcess || error :: " + ee.getMessage());
			}
			try {

				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(hsnResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);

				fullReqRes.setReqRes(rq);
				fullReqRes.setHsnRequest(hsnRequest);
				fullReqRes.setHsnResponse(hsnResponse);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							insertsaveHSNRequest(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();
			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return hsnResponse;
	}


	@RequestMapping(value = "/v2/requestKarzaEmailAuth", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public EmailAuthResponse EmailAuthRequestProcess(@RequestBody EmailAuthRequest emailAuthRequest)
	{
		EmailAuthResponse emailAuthResponse = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {

			NDC.push("EmailAuthRequest : " + uniqueId);
			NDC.push(emailAuthRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + emailAuthRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaEmailAuth");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {

			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(emailAuthRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(emailAuthRequest.getHeader().getCorrelationId());
			rq.setAppid(emailAuthRequest.getHeader().getAppId());
			rq.setToken(emailAuthRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

		    emailAuthResponse = karzaService.emailAuthRequestService(emailAuthRequest, rq);
			msgInfo = emailAuthResponse.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101") || msgInfo.getCode().equals("0")) {
						if (emailAuthResponse.getPayload() != null) {
							KarzaDto kDto = pdfConvert.getPdfByteArrayEmailAuth(emailAuthResponse, emailAuthRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								emailAuthResponse.getPayload().setPdfPath(kDto.getFilePath());
								emailAuthResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || EmailAuthRequestProcess || error :: " + ee.getMessage());
			}
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(emailAuthResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);

				fullReqRes.setReqRes(rq);
				fullReqRes.setEmailAuthRequest(emailAuthRequest);
				fullReqRes.setEmailAuthResponse(emailAuthResponse);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							insertsaveEmailAuthResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return emailAuthResponse;
	}	

	@RequestMapping(value = "/v2/requestKarzaUdyogAadhar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody	public UdyogAadharResponse udyogAadharRequestProcess(@RequestBody UdyogAadharRequest udyogAadharRequest) 
	{
		MessageInfo msgInfo = null;
		UdyogAadharResponse udyogAadharResponse=null;
		ObjectMapper om = new ObjectMapper();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		KarzaReqRes rq = new KarzaReqRes();
		String pdfPath = null;
		try {
			
			NDC.push("UdyogAadharRequest : " + uniqueId);
			NDC.push(udyogAadharRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +udyogAadharRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaUdyogAadhar");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
		
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(udyogAadharRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(udyogAadharRequest.getHeader().getCorrelationId());
			rq.setAppid(udyogAadharRequest.getHeader().getAppId());
			rq.setToken(udyogAadharRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try {

			 udyogAadharResponse = karzaService.udyogAadharRequestService(udyogAadharRequest,rq);
			msgInfo = udyogAadharResponse.getMsgInfo();

			try {
				if (udyogAadharResponse.getMsgInfo().getCode() != null) {
					if (udyogAadharResponse.getMsgInfo().getCode().equals("101")) {
						if (udyogAadharResponse.getPayload() != null) {
				
							KarzaDto kDto = pdfConvert.getPdfByteArrayUdyogAadhar(udyogAadharResponse, udyogAadharRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								udyogAadharResponse.getPayload().setPdfPath(kDto.getFilePath());
								udyogAadharResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || udyogAadharRequestProcess || error :: " + ee.getMessage());
			}
			try {
			
			
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(udyogAadharResponse));
				rq.setCreatedTime(cal.getTime());
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
			    fullReqRes.setReqRes(rq);
			    fullReqRes.setUdyogAadharRequest(udyogAadharRequest);
			    fullReqRes.setUdyogAadharResponse(udyogAadharResponse);

				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveUdyogAadharResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();
			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return udyogAadharResponse;
	}


	@RequestMapping(value = "/v2/requestKarzaGSTAuthentication", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public GSTAuthenticationResponse GSTAuthenticationRequestProcess(@RequestBody GSTAuthenticationRequest authenticationRequest)
	{
	    GSTAuthenticationResponse gstAuthenticationResponse=null;
		MessageInfo msgInfo = null;
		Calendar cal = Calendar.getInstance();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes= new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		ObjectMapper om = new ObjectMapper();
		try {
			NDC.push("GSTAUTH Request : " + uniqueId);
			NDC.push(authenticationRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + authenticationRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaGSTAuthentication");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
		
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(authenticationRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(authenticationRequest.getHeader().getCorrelationId());
			rq.setAppid(authenticationRequest.getHeader().getAppId());
			rq.setToken(authenticationRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			 gstAuthenticationResponse = karzaService.gstAuthenticationRequestService(authenticationRequest,rq);
			msgInfo = gstAuthenticationResponse.getMsgInfo();

			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (gstAuthenticationResponse.getPayload() != null) {
				
							KarzaDto kDto = pdfConvert.getPdfByteArraygstAuth(gstAuthenticationResponse,
									authenticationRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								gstAuthenticationResponse.getPayload().setPdfPath(kDto.getFilePath());
								gstAuthenticationResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || GSTAuthenticationRequestProcess || error :: " + ee.getMessage());
			}
			try {
				
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(gstAuthenticationResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
				
				fullReqRes.setReqRes(rq);
				fullReqRes.setGstAuthenticationRequest(authenticationRequest);
				fullReqRes.setGstAuthenticationResponse(gstAuthenticationResponse);
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveGSTAuthenticationResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();

			} catch (Exception e) {
				logger.error("Error while saving karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return gstAuthenticationResponse;
	}
	
	@RequestMapping(value = "/v2/requestKarzaCAMemberShipAuth", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public CAMemberShipAuthResponse CAMemberShipAuthRequestProcess(@RequestBody CAMemberShipAuthRequest caMemberShipAuthRequest) {
		
        MessageInfo msgInfo = null;
        final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
        CAMemberShipAuthResponse caMemberShipAuthResponse=null;
    	Calendar cal = Calendar.getInstance();
		KarzaReqRes rq = new KarzaReqRes();
        ObjectMapper om = new ObjectMapper();
        ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {
	    	NDC.push("CAMemberShipAuthRequest : " + uniqueId);
			NDC.push(caMemberShipAuthRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +caMemberShipAuthRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaCAMemberShipAuth");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(caMemberShipAuthRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(caMemberShipAuthRequest.getHeader().getCorrelationId());
			rq.setAppid(caMemberShipAuthRequest.getHeader().getAppId());
			rq.setToken(caMemberShipAuthRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			 caMemberShipAuthResponse = karzaService.caMemberShipAuthService(caMemberShipAuthRequest,rq);
			msgInfo = caMemberShipAuthResponse.getMsgInfo();
	
			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (caMemberShipAuthResponse.getPayload() != null) {
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayCaMemberShipAuth(caMemberShipAuthResponse,
									caMemberShipAuthRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								caMemberShipAuthResponse.getPayload().setPdfPath(kDto.getFilePath());
								caMemberShipAuthResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				ee.printStackTrace();
				logger.info("KarzaRestWebService || CAMemberShipAuthRequestProcess || error :: " + ee.getMessage());
			}
			try {
			
			
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(caMemberShipAuthResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setCaMemberShipAuthRequest(caMemberShipAuthRequest);
				fullReqRes.setCaMemberShipAuthResponse(caMemberShipAuthResponse);
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveCAMemberShipAuthResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();

			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return caMemberShipAuthResponse;
	}

	@RequestMapping(value = "/v2/requestKarzaICSIMemberShip", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody   public ICSIMemberShipResponse ICSIMemberShipRequestProcess(@RequestBody ICSIMemberShipRequest icsiMemberShipRequest) {

		ICSIMemberShipResponse icsiMemberShipResponse=null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		Calendar cal = Calendar.getInstance();
		String uniqueId = UniqueId.getUniqueId();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String pdfPath = null;
		try {
			NDC.push("ICSIMemberShipRequest : " + uniqueId);
			NDC.push(icsiMemberShipRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +icsiMemberShipRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaICSIMemberShip");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(icsiMemberShipRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(icsiMemberShipRequest.getHeader().getCorrelationId());
			rq.setAppid(icsiMemberShipRequest.getHeader().getAppId());
			rq.setToken(icsiMemberShipRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			 icsiMemberShipResponse = karzaService.icsiMemberShipRequestService(icsiMemberShipRequest,rq);
			msgInfo = icsiMemberShipResponse.getMsgInfo();
					try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (icsiMemberShipResponse.getPayload() != null) {
								KarzaDto kDto = pdfConvert.getPdfByteArrayICSIMemberShip(icsiMemberShipResponse,
								icsiMemberShipRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								icsiMemberShipResponse.getPayload().setPdfPath(kDto.getFilePath());
								icsiMemberShipResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || ICSIMemberShipRequestProcess || error :: " + ee.getMessage());
			}
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(icsiMemberShipResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);

				 fullReqRes.setReqRes(rq);
				 fullReqRes.setIcsiMemberShipRequest(icsiMemberShipRequest);
				 fullReqRes.setIcsiMemberShipResponse(icsiMemberShipResponse);
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveICSIMemberShipResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();

			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return icsiMemberShipResponse;
	}

		@RequestMapping(value = "/v2/requestKarzaICWAIFirmAuth", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
		@ResponseBody public ICWAIFirmAuthResponse ICWAIFirmAuthRequestProcess(@RequestBody ICWAIFirmAuthRequest icwaiFirmAuthRequest) {
		MessageInfo msgInfo = null;
		ICWAIFirmAuthResponse icwaiFirmAuthResponse=null;
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		Calendar cal = Calendar.getInstance();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String pdfPath = null;
		try {
		
			NDC.push("ICWAIFirmAuthRequest : " + uniqueId);
			NDC.push(icwaiFirmAuthRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +icwaiFirmAuthRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaICWAIFirmAuth");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {

			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(icwaiFirmAuthRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(icwaiFirmAuthRequest.getHeader().getCorrelationId());
			rq.setAppid(icwaiFirmAuthRequest.getHeader().getAppId());
			rq.setToken(icwaiFirmAuthRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			 icwaiFirmAuthResponse = karzaService.icwaiFirmAuthRequestService(icwaiFirmAuthRequest,rq);
			msgInfo = icwaiFirmAuthResponse.getMsgInfo();
			
			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101")) {
						if (icwaiFirmAuthResponse.getPayload() != null) {
					
							KarzaDto kDto = pdfConvert.getPdfByteArrayIcwaiFirmAuth(icwaiFirmAuthResponse,
									icwaiFirmAuthRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								icwaiFirmAuthResponse.getPayload().setPdfPath(kDto.getFilePath());
								icwaiFirmAuthResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || ICWAIFirmAuthRequestProcess || error :: " + ee.getMessage());
			}
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(icwaiFirmAuthResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setIcwaiFirmAuthRequest(icwaiFirmAuthRequest);
				fullReqRes.setIcwaiFirmAuthResponse(icwaiFirmAuthResponse);
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveICWAIFirmAuthResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return icwaiFirmAuthResponse;
	}

	@RequestMapping(value = "/v2/requestKarzaEPFAuthOTP", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public EPFAuthOTPResponse EPFAuthOTPRequestProcess(@RequestBody EPFAuthOTPRequest epfAuthOTPRequest) {
		
		MessageInfo msgInfo = null;
		EPFAuthOTPResponse epfAuthOTPResponse=null;
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		KarzaReqRes rq = new KarzaReqRes();
		String pdfPath = null;
		try {
			NDC.push("epfAuthOTPRequest : " + uniqueId);
			NDC.push(epfAuthOTPRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +epfAuthOTPRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaEPFAuthOTP");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(epfAuthOTPRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(epfAuthOTPRequest.getHeader().getCorrelationId());
			rq.setAppid(epfAuthOTPRequest.getHeader().getAppId());
			rq.setToken(epfAuthOTPRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			 epfAuthOTPResponse = karzaService.epfAuthOTPRequestService(epfAuthOTPRequest,rq);
			msgInfo = epfAuthOTPResponse.getMsgInfo();
			
			try {
				if (msgInfo!=null && msgInfo.getCode() != null && msgInfo.getCode().equals("101"))
				{
						
					if (epfAuthOTPResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayGSTIdentification(epfAuthOTPResponse,
									epfAuthOTPRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								epfAuthOTPResponse.getPayload().setPdfPath(kDto.getFilePath());
								epfAuthOTPResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || gstIdentificationRequestProcess || error :: " + ee.getMessage());
			}
			
			
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(epfAuthOTPResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				rq.setCreatedTime(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setEpfAuthOTPRequest(epfAuthOTPRequest);
				fullReqRes.setEpfAuthOTPResponse(epfAuthOTPResponse);
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveEPFAuthOTPResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();

			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return epfAuthOTPResponse;
	}  

	@RequestMapping(value = "/v2/requestKarzaGstIdentification", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody	public GstIdentificationResponse gstIdentificationRequestProcess(@RequestBody GstIdentificationRequest gstIdentificationRequest) {
	
		MessageInfo msgInfo = null;
        GstIdentificationResponse gstIdentificationResponse=null;
		String uniqueId = UniqueId.getUniqueId();
		ObjectMapper om = new ObjectMapper();
		final KarzaFullReqRes fullReqRes = new KarzaFullReqRes();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		Calendar cal = Calendar.getInstance();
		String pdfPath = null;
		try {
			NDC.push("GstIdentificationRequest : " + uniqueId);
			NDC.push(gstIdentificationRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + gstIdentificationRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaGstIdentification");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(gstIdentificationRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(gstIdentificationRequest.getHeader().getCorrelationId());
			rq.setAppid(gstIdentificationRequest.getHeader().getAppId());
			rq.setToken(gstIdentificationRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try {

			 gstIdentificationResponse = karzaService
					.gstIdentificationRequestService(gstIdentificationRequest,rq);
			msgInfo = gstIdentificationResponse.getMsgInfo();
					try {
				if (gstIdentificationResponse.getMsgInfo().getCode() != null) {
					if (gstIdentificationResponse.getMsgInfo().getCode().equals("101")) {
						if (gstIdentificationResponse.getPayload() != null) {
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayGSTIdentification(gstIdentificationResponse,
									gstIdentificationRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								gstIdentificationResponse.getPayload().setPdfPath(kDto.getFilePath());
								gstIdentificationResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || gstIdentificationRequestProcess || error :: " + ee.getMessage());
			}
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(gstIdentificationResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				rq.setCreatedTime(cal.getTime());
               
                fullReqRes.setReqRes(rq);
                fullReqRes.setGstIdentificationRequest(gstIdentificationRequest);
                fullReqRes.setGstIdentificationResponse(gstIdentificationResponse);
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveGstIdentificationResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();

				
			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return gstIdentificationResponse;
	}
	


//////////////complete
	@RequestMapping(value = "/v2/requestKarzaEPFAuthPassBook", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public EPFAuthPassBookResponse EPFAuthPassBookRequestProcess(@RequestBody EPFAuthPassBookRequest epfAuthPassBookRequest) {
	
	    EPFAuthPassBookResponse epfAuthPassBookResponse=null;
	    KarzaReqRes rq = new KarzaReqRes();
	    MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String pdfPath = null;
		try {
			NDC.push("EPFAuthPassBookRequest : " + uniqueId);
			NDC.push(epfAuthPassBookRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +epfAuthPassBookRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaEPFAuthPassBook");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(epfAuthPassBookRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(epfAuthPassBookRequest.getHeader().getCorrelationId());
			rq.setAppid(epfAuthPassBookRequest.getHeader().getAppId());
			rq.setToken(epfAuthPassBookRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try {

			epfAuthPassBookResponse = karzaService.epfAuthPassBookRequestService(epfAuthPassBookRequest,rq);
			msgInfo = epfAuthPassBookResponse.getMsgInfo();
           
			try {
				if (msgInfo.getCode() != null) {
					if (msgInfo.getCode().equals("101") || msgInfo.getCode().equals("0")) {
						if (epfAuthPassBookResponse.getPayload() != null) {
							KarzaDto kDto = pdfConvert.getPdfByteArrayEpfAuthPassbook(epfAuthPassBookResponse,
									epfAuthPassBookRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								epfAuthPassBookResponse.getPayload().setPdfPath(kDto.getFilePath());
								epfAuthPassBookResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || EPFAuthPassBookRequestProcess || error :: " + ee.getMessage());
			}
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(epfAuthPassBookResponse));
				rq.setCreatedTime(cal.getTime());
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				
				fullReqRes.setReqRes(rq);
				fullReqRes.setEpfAuthPassBookRequest(epfAuthPassBookRequest);
				fullReqRes.setEpfAuthPassBookResponse(epfAuthPassBookResponse);
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							insertsaveEPFAuthPassbookResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();

				

			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return epfAuthPassBookResponse;
	}

	
	
	@RequestMapping(value = "/v2/requestKarzaLPG", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public LpgResponse lpgProcess(@RequestBody LpgRequest lpgReq)
	{
		LpgResponse lpgResponse=null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueRequestID = UniqueId.getUniqueId();
		ServiceMaster service=null;
		String pdfPath=null;
		try
		{
			NDC.push("lpgRequest : " + UniqueId.getUniqueId());
			if (lpgReq.getHeader() != null) 
			{
				NDC.push(lpgReq.getHeader().getCorrelationId());
			}
			logger.info("RequestJson :: " + lpgReq);
			service= dbKarza.getServiceCredential("/v2/requestKarzaLPG");
		} 
		catch (Exception ex)
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueRequestID);
			rq.setRequest(om.writeValueAsString(lpgReq));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(lpgReq.getHeader().getCorrelationId());
			rq.setAppid(lpgReq.getHeader().getAppId());
			rq.setToken(lpgReq.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		}
		catch (Exception ex) 
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			lpgResponse = karzaService.lpgRequestService(lpgReq,rq);
			msgInfo = lpgResponse.getMsgInfo();
			if(lpgResponse.getMsgInfo().getCode()!=null){
			if(lpgResponse.getMsgInfo().getCode().equals("101"))
			{
			if(lpgResponse.getPayload() != null)
			{
				KarzaDto dto =  pdfConvert.getPdfByteArrayLpg(lpgResponse,lpgReq,service.getPdf_name());
				if (dto != null)
				{
					pdfPath=dto.getFilePath();
					lpgResponse.getPayload().setPdfPath(dto.getFilePath());
					lpgResponse.getPayload().setByteArray(dto.getByteArray());
				}
			}
			}}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueRequestID);
				rq.setResponse(om.writeValueAsString(lpgResponse));
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setLpgReq(lpgReq);
				fullReqRes.setLpgResponse(lpgResponse);
				new Thread(new Runnable() 
				{
					@Override
					public void run()
					{
						try 
						{
						   saveLpgRequestResponse(fullReqRes);
						}
						catch (Exception e)
						{
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("Error while updating karza req res:"+e);
			}
		} 
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		} 
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return lpgResponse;
	}
	

	@RequestMapping(value = "/v2/requestKarzaVoter", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public VoterResponse voterRequestProcess(@RequestBody VoterRequest voterRequest) 
	{
		VoterResponse voterResponse = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		ServiceMaster service=null;
		String pdfPath=null;
		try
		{
			NDC.push("VoterRequest : " + uniqueId);
			NDC.push(voterRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + voterRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaVoter");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(voterRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(voterRequest.getHeader().getCorrelationId());
			rq.setAppid(voterRequest.getHeader().getAppId());
			rq.setToken(voterRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			voterResponse = karzaService.voterRequestService(voterRequest,rq);
			msgInfo = voterResponse.getMsgInfo();
			try{
				if(msgInfo.getCode() !=null){
					if(msgInfo.getCode().equals("101"))
						{
						if(voterResponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayVoter(voterResponse,voterRequest,service.getPdf_name());
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								voterResponse.getPayload().setPdfPath(kDto.getFilePath());
								voterResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}
			catch(Exception ee)
			{
				logger.info("KarzaRestWebService || voterRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(voterResponse));
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setVoterRequest(voterRequest);
				fullReqRes.setVoterResponse(voterResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveVoterRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("Error while updating karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return voterResponse;
	}
	
	@RequestMapping(value = "/v3/requestKarzaLPGIdentification", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public LpgIdentificationResponse lpgIdentificationProcess(@RequestBody LpgIdentificationRequest lpgRequest)
	{
		LpgIdentificationResponse lpgRes=null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueRequestID = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("lpgRequest : " + UniqueId.getUniqueId());
			if (lpgRequest.getHeader() != null) 
			{
				NDC.push(lpgRequest.getHeader().getCorrelationId());
			}
			logger.info("RequestJson :: " + lpgRequest);
			service= dbKarza.getServiceCredential("/v3/requestKarzaLPGIdentification");
		} 
		catch (Exception ex)
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueRequestID);
			rq.setRequest(om.writeValueAsString(lpgRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(lpgRequest.getHeader().getCorrelationId());
			rq.setAppid(lpgRequest.getHeader().getAppId());
			rq.setToken(lpgRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		}
		catch (Exception ex) 
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			lpgRes = karzaService.lpgIdentificationRequestService(lpgRequest,rq);
			
			msgInfo = lpgRes.getMsgInfo();

			try{
				if(lpgRes.getMsgInfo().getCode()!=null)
				{
					if(lpgRes.getMsgInfo().getCode().equals("101"))
					{
					if(lpgRes.getPayload() != null)
						{
						KarzaDto dto =  pdfConvert.getPdfByteArrayLpgIdentifier(lpgRes,lpgRequest,service.getPdf_name());
						if (dto != null)
							{
							pdfPath=dto.getFilePath();
							lpgRes.getPayload().setPdfPath(dto.getFilePath());
							lpgRes.getPayload().setByteArray(dto.getByteArray());
							}
						}
					}
				}
			   }catch(Exception ee){
				   logger.error("karzaRestWebService || lpgIdentificationProcess || error:: "+ee);
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueRequestID);
				rq.setResponse(om.writeValueAsString(lpgRes));
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setLpgRequest(lpgRequest);
				fullReqRes.setLpgRes(lpgRes);
				new Thread(new Runnable() 
				{
					@Override
					public void run()
					{
						saveLpgIdentiRequestResponse(fullReqRes);
					}

				}).start();
			}
			catch(Exception e)
			{
				logger.error("Error while updating karza req res:"+e);
			}
		} 
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		} 
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return lpgRes;
	}
	
	@RequestMapping(value = "/v2/requestKarzaNameSimilarity", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public NameSimilarityResponse nameSimilarityRequestProcess(@RequestBody NameSimilarityRequest nameSimilarityRequest) 
	{
		NameSimilarityResponse nameSimilarityResponse = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("NameSimilarityRequest : " + uniqueId);
			NDC.push(nameSimilarityRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + nameSimilarityRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaNameSimilarity");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(nameSimilarityRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(nameSimilarityRequest.getHeader().getCorrelationId());
			rq.setAppid(nameSimilarityRequest.getHeader().getAppId());
			rq.setToken(nameSimilarityRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
		 nameSimilarityResponse = karzaService.nameSimilarityRequestService(nameSimilarityRequest,rq);
		 msgInfo = nameSimilarityResponse.getMsgInfo();
			try
			{
				if(nameSimilarityResponse.getMsgInfo().getCode() !=null)
				{
				if(nameSimilarityResponse.getMsgInfo().getCode().equals("101"))
					{
				if(nameSimilarityResponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayNameSimilarity(nameSimilarityResponse, nameSimilarityRequest,service.getPdf_name());
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								nameSimilarityResponse.setPdfPath(kDto.getFilePath());
								nameSimilarityResponse.setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || nameSimilarityRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(nameSimilarityResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setNameSimilarityRequest(nameSimilarityRequest);
				fullReqRes.setNameSimilarityResponse(nameSimilarityResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							savenameSimilarityRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return nameSimilarityResponse;
	}
	
	@RequestMapping(value = "/v2/requestKarzaCompSearch", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public CompSearchResponse compSearchRequestProcess(@RequestBody CompSearchRequest compSearchRequest) 
	{
		CompSearchResponse compSearchRessponse=null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("CompSearchRequest : " + uniqueId);
			NDC.push(compSearchRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + compSearchRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaCompSearch");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(compSearchRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(compSearchRequest.getHeader().getCorrelationId());
			rq.setAppid(compSearchRequest.getHeader().getAppId());
			rq.setToken(compSearchRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			 compSearchRessponse = karzaService.compSearchRequestService(compSearchRequest,rq);
			msgInfo = compSearchRessponse.getMsgInfo();
			
			try
			{
				if(compSearchRessponse.getMsgInfo().getCode() !=null)
				{
				if(compSearchRessponse.getMsgInfo().getCode().equals("101"))
					{
				if(compSearchRessponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayCompSearch(compSearchRessponse,compSearchRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								compSearchRessponse.getPayload().setPdfPath(kDto.getFilePath());
								compSearchRessponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || compSearchRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(compSearchRessponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setCompSearchRequest(compSearchRequest);
				fullReqRes.setCompSearchRessponse(compSearchRessponse);

				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							savecompSearchRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();

			}
			catch(Exception e)
			{
				logger.error("Error while saving karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return compSearchRessponse;
	}
	
	
	@RequestMapping(value = "/v2/requestKarzaCompanyLLPCINLookUp", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public CompanyLLPCINLookUpResponse companyLLPCINLookUpRequestProcess(@RequestBody CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest) 
	{
		CompanyLLPCINLookUpResponse companyLLPCINLookUpResponse = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("companyLLPCINLookUpRequest : " + uniqueId);
			NDC.push(companyLLPCINLookUpRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + companyLLPCINLookUpRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaCompanyLLPCINLookUp");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(companyLLPCINLookUpRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(companyLLPCINLookUpRequest.getHeader().getCorrelationId());
			rq.setAppid(companyLLPCINLookUpRequest.getHeader().getAppId());
			rq.setToken(companyLLPCINLookUpRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try 
		{
			companyLLPCINLookUpResponse = karzaService.companyLLPCINLookUpRequestService(companyLLPCINLookUpRequest,rq);
			msgInfo = companyLLPCINLookUpResponse.getMsgInfo();
		
			try{
				if(msgInfo.getCode() !=null){
					if(msgInfo.getCode().equals("101"))
						{
						if(companyLLPCINLookUpResponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayCompanyLLPCINLookUp(companyLLPCINLookUpResponse,companyLLPCINLookUpRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								companyLLPCINLookUpResponse.getPayload().setPdfPath(kDto.getFilePath());
								companyLLPCINLookUpResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}
			catch(Exception ee)
			{
				logger.info("KarzaRestWebService || companyLLPCINLookUpRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setResponse(om.writeValueAsString(companyLLPCINLookUpResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setCompanyLLPCINLookUpRequest(companyLLPCINLookUpRequest);
				fullReqRes.setCompanyLLPCINLookUpResponse(companyLLPCINLookUpResponse);
				new Thread(new Runnable()
				{
				@Override
				public void run() 
				{
					try
					{
						savecompanyLLPCINLookUpRequestResponse(fullReqRes);
					} catch (Exception e) {
						logger.error("There is no data saved in database : " + e);
					}
				}
			}).start();
		}
			catch(Exception e)
			{
				logger.error("Error while updating karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return companyLLPCINLookUpResponse;
	}
	
	@RequestMapping(value = "/v2/requestAddessMatching", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public AddressResponse addressRequestProcess(@RequestBody AddressRequest addressRequest) 
	{
		AddressResponse addressResponse = null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		KarzaReqRes rq = new KarzaReqRes();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("AddressRequest : " + uniqueId);
			NDC.push(addressRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + addressRequest);
			service= dbKarza.getServiceCredential("/v2/requestAddessMatching");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(addressRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(addressRequest.getHeader().getCorrelationId());
			rq.setAppid(addressRequest.getHeader().getAppId());
			rq.setToken(addressRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			addressResponse = karzaService.addressRequestService(addressRequest,rq);
			msgInfo = addressResponse.getMsgInfo();
				try
				{
					if(addressResponse.getMsgInfo().getCode() !=null)
					{
					if(addressResponse.getMsgInfo().getCode().equals("101"))
						{
					if(addressResponse.getPayload() != null)
							{
								KarzaDto kDto = pdfConvert.getPdfByteArrayAddressMatch(addressResponse, addressRequest,service.getPdf_name());
								if (kDto != null) 
								{
									pdfPath=kDto.getFilePath();
									addressResponse.getPayload().setPdfPath(kDto.getFilePath());
									addressResponse.getPayload().setByteArray(kDto.getByteArray());
								}
							}
						}
					}
				}catch(Exception ee)
				{
					logger.info("KarzaRestWebService || addressRequestProcess || error :: "+ee.getMessage());
				}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setResponse(om.writeValueAsString(addressResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setAddressRequest(addressRequest);
				fullReqRes.setAddressResponse(addressResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveAddressRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("Error while updating karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return addressResponse;
	}
	@RequestMapping(value = "/v2/requestKarzaMCASignaure", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	   @ResponseBody public MCASignatureResponse MCASignaureRequestProcess(@RequestBody MCASignatureRequest mcaSignatureRequest) 
		{
		MCASignatureResponse mcaSignatureResponse= null;
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		KarzaReqRes rq = new KarzaReqRes();
		String uniqueId = UniqueId.getUniqueId();
		Calendar cal=Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String pdfPath=null;
		try
		{
			NDC.push("mcaSignatureRequest : " + uniqueId);
			NDC.push(mcaSignatureRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + mcaSignatureRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaMCASignaure");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(mcaSignatureRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(mcaSignatureRequest.getHeader().getCorrelationId());
			rq.setAppid(mcaSignatureRequest.getHeader().getAppId());
			rq.setToken(mcaSignatureRequest.getHeader().getToken());
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving final req,res from service : " + ex);
		}
		try 
		{
			 mcaSignatureResponse = karzaService.mcaSignaureRequestService(mcaSignatureRequest,rq);
			msgInfo = mcaSignatureResponse.getMsgInfo();
			try{
				if(msgInfo.getCode() !=null){
					if(msgInfo.getCode().equals("101"))
						{
						if(mcaSignatureResponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayMCASignature(mcaSignatureResponse,mcaSignatureRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								mcaSignatureResponse.getPayload().setPdfPath(kDto.getFilePath());
								mcaSignatureResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}
			catch(Exception ee)
			{
				logger.info("KarzaRestWebService || MCASignaureRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(mcaSignatureResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setMcaSignatureRequest(mcaSignatureRequest);
				fullReqRes.setMcaSignatureResponse(mcaSignatureResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveMCASignatureRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("Error while updating karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}
		finally 
		{
			NDC.pop();
			NDC.pop();
		}
		return mcaSignatureResponse;
	}
	@RequestMapping(value = "/v2/requestKarzaICWAIMember", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public ICWAIMembershipResponse icwaiMembershipRequestProcess(@RequestBody ICWAIMembershipRequest icwaiMembershipRequest) {
	
		ICWAIMembershipResponse icwaiMembershipResponse=null;
		KarzaReqRes rq = new KarzaReqRes();
		MessageInfo msgInfo = null;
		ObjectMapper om = new ObjectMapper();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {
			NDC.push("icwaiMembershipRequest : " + uniqueId);
			NDC.push(icwaiMembershipRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +icwaiMembershipRequest);
			service= dbKarza.getServiceCredential("/v2/requestKarzaICWAIMember");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
		
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(icwaiMembershipRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(icwaiMembershipRequest.getHeader().getCorrelationId());
			rq.setAppid(icwaiMembershipRequest.getHeader().getAppId());
			rq.setToken(icwaiMembershipRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try {

			 icwaiMembershipResponse = karzaService.icwaiMembershipRequestService(icwaiMembershipRequest,rq);
			msgInfo = icwaiMembershipResponse.getMsgInfo();
	
			try {
				if (icwaiMembershipResponse.getMsgInfo().getCode() != null) {
					if (icwaiMembershipResponse.getMsgInfo().getCode().equals("101")) {
						if (icwaiMembershipResponse.getPayload() != null) {
						
							KarzaDto kDto =pdfConvert.getPdfByteArrayIcwaiMembership(icwaiMembershipResponse,
									icwaiMembershipRequest,service.getPdf_name());

							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								icwaiMembershipResponse.getPayload().setPdfPath(kDto.getFilePath());
								icwaiMembershipResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || icwaiMembershipRequestProcess || error :: " + ee.getMessage());
			}
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(icwaiMembershipResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
				
				fullReqRes.setReqRes(rq);
				fullReqRes.setIcwaiMembershipRequest(icwaiMembershipRequest);
				fullReqRes.setIcwaiMembershipResponse(icwaiMembershipResponse);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							saveICWAIMembershipResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();

			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return icwaiMembershipResponse;
	}

	@RequestMapping(value = "/v2/requestRCSearch", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody	public RCSearchResponse rcSearchRequestProcess(@RequestBody RCSearchRequest rcSearchRequest) {

	    RCSearchResponse rcSearchResponse=null;
	    KarzaReqRes rq = new KarzaReqRes();
	    MessageInfo msgInfo = null;
	    final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal = Calendar.getInstance();
		ServiceMaster service=null;
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath = null;
		try {
			NDC.push("RCSearchRequest : " + uniqueId);
			NDC.push(rcSearchRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " +rcSearchRequest);
			service= dbKarza.getServiceCredential("/v2/requestRCSearch");
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(rcSearchRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(rcSearchRequest.getHeader().getCorrelationId());
			rq.setAppid(rcSearchRequest.getHeader().getAppId());
			rq.setToken(rcSearchRequest.getHeader().getToken());
		} catch (Exception ex) {
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try {

			rcSearchResponse = karzaService.rcSearchRequestService(rcSearchRequest,rq);
			msgInfo = rcSearchResponse.getMsgInfo();
			try {
				if (rcSearchResponse.getMsgInfo().getCode() != null) {
					if (rcSearchResponse.getMsgInfo().getCode().equals("101")) {
						if (rcSearchResponse.getPayload() != null) {
							KarzaDto kDto = pdfConvert.getPdfByteArrayRcSearch(rcSearchResponse, rcSearchRequest,service.getPdf_name());
							if (kDto != null) {
								pdfPath = kDto.getFilePath();
								rcSearchResponse.getPayload().setPdfPath(kDto.getFilePath());
								rcSearchResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			} catch (Exception ee) {
				logger.info("KarzaRestWebService || rcSearchRequestProcess || error :: " + ee.getMessage());
			}

		
			try {
				rq.setStatus(msgInfo.getStatus());
				rq.setUniqueId(uniqueId);
				rq.setResponse(om.writeValueAsString(rcSearchResponse));
				rq.setUpdated_Time(cal.getTime());
				rq.setCreatedTime(cal.getTime());
				rq.setPdfPath(pdfPath);
                
				fullReqRes.setReqRes(rq);
                fullReqRes.setRcSearchRequest(rcSearchRequest);
                fullReqRes.setRcSearchResponse(rcSearchResponse);
                
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							insertsaveRCRequestProcess(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}

				}).start();
			} catch (Exception e) {
				logger.error("Error while updating karza req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			NDC.pop();
		}

		return rcSearchResponse;
	}
	  

	@RequestMapping(value = "/v2/requestBankAccount", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public BankAccountResponse bankAccountRequestProcess(@RequestBody BankAccountRequest bankAccountRequest) 
	{
		BankAccountResponse bankAccountResponse = null;
		MessageInfo msgInfo = null;
		KarzaReqRes rq = new KarzaReqRes();
		String uniqueId = UniqueId.getUniqueId();
		ServiceMaster service=null;
		 final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		ObjectMapper om = new ObjectMapper();
		Calendar cal=Calendar.getInstance();
		String pdfPath=null;
		try
		{
			NDC.push("BankAccountRequest : " + uniqueId);
			NDC.push(bankAccountRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(bankAccountRequest));
			service= dbKarza.getServiceCredential("/v2/requestBankAccount");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(bankAccountRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(bankAccountRequest.getHeader().getCorrelationId());
			rq.setAppid(bankAccountRequest.getHeader().getAppId());
			rq.setToken(bankAccountRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			 bankAccountResponse = karzaService.bankAccountRequestService(bankAccountRequest, rq);
			msgInfo = bankAccountResponse.getMsgInfo();

			try
			{
				if(bankAccountResponse.getMsgInfo().getCode() !=null)
				{
				if(bankAccountResponse.getMsgInfo().getCode().equals("101"))
					{
				if(bankAccountResponse.getPayload() != null)
						{
						
							KarzaDto kDto = pdfConvert.getPdfByteArrayBankAccount(bankAccountResponse, bankAccountRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								bankAccountResponse.getPayload().setPdfPath(kDto.getFilePath());
								bankAccountResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || bankAccountRequestProcess || error :: "+ee.getMessage());
			}
			try
			{
				rq.setStatus(msgInfo.getStatus());
				rq.setResponse(om.writeValueAsString(bankAccountResponse));
				rq.setPdfPath(pdfPath);
				rq.setUpdated_Time(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setBankAccountRequest(bankAccountRequest);
				fullReqRes.setBankAccountResponse(bankAccountResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveBankAccountRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();

			}
			catch(Exception e)
			{
				logger.error("Error while inserting karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return bankAccountResponse;
	}

	@RequestMapping(value = "/v3/requestKarzaLPG", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public LpgResponse2 lpgProcessQC(@RequestBody LpgRequest2 lpgRequest)
	{
		
		LpgResponse2 lpgRes = null;
		KarzaReqRes rq = new KarzaReqRes();
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		 final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		String uniqueRequestID = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("lpgRequest : " + UniqueId.getUniqueId());
			if (lpgRequest.getHeader() != null) 
			{

				NDC.push(lpgRequest.getHeader().getCorrelationId());

			}
			logger.info("RequestJson :: " + om.writeValueAsString(lpgRequest));
			service= dbKarza.getServiceCredential("/v3/requestKarzaLPG");

		} 
		catch (Exception ex)
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			
			rq.setUniqueId(uniqueRequestID);
			rq.setRequest(om.writeValueAsString(lpgRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(lpgRequest.getHeader().getCorrelationId());
			rq.setAppid(lpgRequest.getHeader().getAppId());
			rq.setToken(lpgRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());

		}
		catch (Exception ex) 
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			lpgRes = karzaService.lpgRequestServiceQC(lpgRequest,rq);

			try{
				if(lpgRes.getMsgInfo().getCode()!=null)
				{
					if(lpgRes.getMsgInfo().getCode().equals("101"))
					{
					if(lpgRes.getPayload() != null)
						{
					
						KarzaDto dto = pdfConvert.getPdfByteArrayLpgQC(lpgRes,lpgRequest,service.getPdf_name());
						if (dto != null)
							{
							pdfPath=dto.getFilePath();
							lpgRes.getPayload().setPdfPath(dto.getFilePath());
							lpgRes.getPayload().setByteArray(dto.getByteArray());
							}
						}
					}
				}
			   }catch(Exception ee){
				   logger.error("karzaRestWebService || lpgProcessQC || error:: "+ee);
			}
			try
			{
				
				rq.setResponse(om.writeValueAsString(lpgRes));
				rq.setPdfPath(pdfPath);
				rq.setUpdated_Time(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setLpgRequest2(lpgRequest);
				fullReqRes.setLpgRes2(lpgRes);
			
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveLpg2RequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();


			}
			catch(Exception e)
			{
				logger.error("Error while inserting karza req res:"+e);
			}
		} 
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		} 
		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return lpgRes;
	}

	@RequestMapping(value = "/v2/requestEmployerLookup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public EmployerLookupResponse employerLookupRequestProcess(@RequestBody EmolpyerLookupRequest employerLookupRequest) 
	{
		EmployerLookupResponse employerLookupResponse = null;
		MessageInfo msgInfo = null;
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
	    Calendar cal=Calendar.getInstance();
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			NDC.push("EmolpyerLookupRequest : " + uniqueId);
			NDC.push(employerLookupRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(employerLookupRequest));
			service= dbKarza.getServiceCredential("/v2/requestEmployerLookup");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(employerLookupRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(employerLookupRequest.getHeader().getCorrelationId());
			rq.setAppid(employerLookupRequest.getHeader().getAppId());
			rq.setToken(employerLookupRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			 employerLookupResponse = karzaService.employerLookupRequestService(employerLookupRequest, rq);
			msgInfo = employerLookupResponse.getMsgInfo();
		
			try
			{
				if(employerLookupResponse.getMsgInfo().getCode() !=null)
				{
				if(employerLookupResponse.getMsgInfo().getCode().equals("101"))
					{
				if(employerLookupResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayEmployerLookup(employerLookupResponse, employerLookupRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								employerLookupResponse.getPayload().setPdfPath(kDto.getFilePath());
								employerLookupResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || employerLookupRequestProcess || error :: "+ee);
			}
			try
			{
				employerLookupResponse.getPayload().setByteArray("");
				rq.setStatus(msgInfo.getStatus());
				rq.setResponse(om.writeValueAsString(employerLookupResponse));
				rq.setPdfPath(pdfPath);
				rq.setUpdated_Time(cal.getTime());
				fullReqRes.setReqRes(rq);
				fullReqRes.setEmployerLookupRequest(employerLookupRequest);
				fullReqRes.setEmployerLookupResponse(employerLookupResponse);
				
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveEmolpyerLookupRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();

			}
			catch(Exception e)
			{
				logger.error("Error while inserting karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return employerLookupResponse;
	}

	@RequestMapping(value = "/v2/requestUANLookup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public UanLookupResponse uanLookupRequestProcess(@RequestBody UanLookupRequest uanLookupRequest) 
	{
		UanLookupResponse uanLookupResponse = null;
		MessageInfo msgInfo = null;
		KarzaReqRes rq = new KarzaReqRes();
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		String uniqueId = UniqueId.getUniqueId();
	   
		String pdfPath=null;
		try
		{
			NDC.push("UanLookupRequest : " + uniqueId);
			NDC.push(uanLookupRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(uanLookupRequest));
			service= dbKarza.getServiceCredential("/v2/requestUANLookup");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(uanLookupRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(uanLookupRequest.getHeader().getCorrelationId());
			rq.setAppid(uanLookupRequest.getHeader().getAppId());
			rq.setToken(uanLookupRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
			
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			 uanLookupResponse = karzaService.uanLookupRequestService(uanLookupRequest, rq);
			msgInfo = uanLookupResponse.getMsgInfo();
            
			try
			{
				if(uanLookupResponse.getMsgInfo().getCode() !=null)
				{
				if(uanLookupResponse.getMsgInfo().getCode().equals("101"))
					{
				if(uanLookupResponse.getPayload() != null)
						{
							
							KarzaDto kDto = pdfConvert.getPdfByteArrayUanLookup(uanLookupResponse, uanLookupRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								uanLookupResponse.getPayload().setPdfPath(kDto.getFilePath());
								uanLookupResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || uanLookupRequestProcess || error :: "+ee);
			}
			try
			{
				
				uanLookupResponse.getPayload().setByteArray("");
				rq.setStatus(msgInfo.getStatus());
				rq.setResponse(om.writeValueAsString(uanLookupResponse));
				rq.setPdfPath(pdfPath);
				fullReqRes.setReqRes(rq);
				fullReqRes.setUanLookupRequest(uanLookupRequest);
				fullReqRes.setUanLookupResponse(uanLookupResponse);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveUanLookupRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();
			}
			catch(Exception e)
			{
				logger.error("Error while inserting karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return uanLookupResponse;
	}
	
	@RequestMapping(value = "/v2/requestWebsiteDomain", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody public WebsiteDomainResponse websiteDomainRequestProcess(@RequestBody WebsiteDomainRequest websiteDomainRequest) 
	{
		WebsiteDomainResponse websiteDomainResponse = null;
		MessageInfo msgInfo = null;
		KarzaReqRes rq = new KarzaReqRes();
		ObjectMapper om = new ObjectMapper();
		ServiceMaster service=null;
		final KarzaFullReqRes fullReqRes=new KarzaFullReqRes();
		Calendar cal=Calendar.getInstance();
		String uniqueId = UniqueId.getUniqueId();
		String pdfPath=null;
		try
		{
			
			NDC.push("WebsiteDomainRequest : " + uniqueId);
			NDC.push(websiteDomainRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: " + om.writeValueAsString(websiteDomainRequest));
			service= dbKarza.getServiceCredential("/v2/requestWebsiteDomain");
		} 
		catch (Exception ex) 
		{
			logger.error("Error While mapping response header : " + ex);
		}
		try 
		{
			
			rq.setUniqueId(uniqueId);
			rq.setRequest(om.writeValueAsString(websiteDomainRequest));
			rq.setTag(service.getService_tag());
			rq.setCorelationid(websiteDomainRequest.getHeader().getCorrelationId());
			rq.setAppid(websiteDomainRequest.getHeader().getAppId());
			rq.setToken(websiteDomainRequest.getHeader().getToken());
			rq.setCreatedTime(cal.getTime());
		
		} 
		catch (Exception ex)
		{
			logger.info("Error While saving fianl req,res from service : " + ex);
		}
		try 
		{
			
			websiteDomainResponse = karzaService.websiteDomaminRequestService(websiteDomainRequest,rq);
			msgInfo = websiteDomainResponse.getMsgInfo();
            
			try
			{
				if(websiteDomainResponse.getMsgInfo().getCode() !=null)
				{
				if(websiteDomainResponse.getMsgInfo().getCode().equals("101"))
					{
				if(websiteDomainResponse.getPayload() != null)
						{
							KarzaDto kDto = pdfConvert.getPdfByteArrayWebsiteDomain(websiteDomainResponse, websiteDomainRequest,service.getPdf_name());
			
							if (kDto != null) 
							{
								pdfPath=kDto.getFilePath();
								websiteDomainResponse.getPayload().setPdfPath(kDto.getFilePath());
								websiteDomainResponse.getPayload().setByteArray(kDto.getByteArray());
							}
						}
					}
				}
			}catch(Exception ee)
			{
				logger.info("KarzaRestWebService || websiteDomainRequestProcess || error :: "+ee);
			}

			try
			{
				websiteDomainResponse.getPayload().setByteArray("");
				rq.setStatus(msgInfo.getStatus());
		     	rq.setResponse(om.writeValueAsString(websiteDomainResponse));
				rq.setPdfPath(pdfPath);
				rq.setUpdated_Time(cal.getTime());
				fullReqRes.setWebsiteDomainRequest(websiteDomainRequest);;
				fullReqRes.setWebsiteDomainResponse(websiteDomainResponse);
				fullReqRes.setReqRes(rq);
				new Thread(new Runnable()
				{
					@Override
					public void run() 
					{
						try
						{
							saveWebsiteDomainRequestResponse(fullReqRes);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();

			}
			catch(Exception e)
			{
				logger.error("Error while setting karza req res:"+e);
			}
		}
		catch (Exception ex)
		{
			logger.error("Error While getting response from service : " + ex);
		}

		finally 
		{
			NDC.pop();
			NDC.pop();
		}

		return websiteDomainResponse;
}


	
	
	 protected void saveWebsiteDomainRequestResponse(KarzaFullReqRes fullReqRes) {
		 logger.info("Website Domain :: saveWebsiteDomainRequestResponse():: START");
	  	  dbKarza.insertWebsiteDomainRequestResponse(fullReqRes);
	  	  logger.info("WebsiteDomain:: saveWebsiteDomainRequestResponse() :: END"); 
		 
	}


	 protected void saveUanLookupRequestResponse(KarzaFullReqRes fullReqRes) {
		 logger.info("KARZA UANLOOKUP :: saveUanLookupRequestResponse():: START");
	  	  dbKarza.insertUanLookupRequestResponse(fullReqRes);
	  	  logger.info("KARZA UANLOOKUP:: saveUanLookupRequestResponse() :: END"); 
	}

	protected void saveEmolpyerLookupRequestResponse(KarzaFullReqRes fullReqRes) {
		 logger.info("KARZA EmolpyerLookup :: saveEmolpyerLookupRequestResponse():: START");
	  	  dbKarza.insertEmolpyerLookupRequestResponse(fullReqRes);
	  	  logger.info("KARZA EmolpyerLookup:: saveEmolpyerLookupRequestResponse() :: END"); 
	}

	protected void saveLpg2RequestResponse(KarzaFullReqRes fullReqRes) {
		 logger.info("KARZA Lpg2 :: saveLpg2RequestResponse():: START");
	  	  dbKarza.insertLpg2RequestResponse(fullReqRes);
	  	  logger.info("KARZA Lpg2 :: saveLpg2RequestResponse() :: END"); 
	}

	protected void saveBankAccountRequestResponse(KarzaFullReqRes fullReqRes) {
	  	  logger.info("KARZA BANKACC  :: saveBankAccountRequestResponse():: START");
	  	  dbKarza.insertBankAccountRequestResponse(fullReqRes);
	  	  logger.info("KARZA BANKACC :: saveBankAccountRequestResponse() :: END"); 
		}
	private void saveICWAIMembershipResponse(KarzaFullReqRes fullReqRes) {
  	  logger.info("KARZA ICWAIMembership :: insertsaveICWAIMembershipResponse():: START");
  	  dbKarza.insertAllinICWAIMembershipResponse(fullReqRes);
  	  logger.info("KARZA ICWAIMembership:: insertsaveICWAIMembershipResponse() :: END"); 
    }
  
    private void insertsaveRCRequestProcess(KarzaFullReqRes fullReqRes) { 
  	  logger.info("KARZA RC_SEARCH :: saveElectricalRequestResponse():: START"); 
  	  dbKarza.insertAllinRCRequestProcess(fullReqRes);
  	  logger.info("KARZA RC_SEARCH:: saveElectricalRequestResponse() :: END");  
    }
	

	   protected void saveAddressRequestResponse(KarzaFullReqRes fullReqRes) {
		   logger.info("ADDRESS MATCHING :: insertAddressRequestResponse():: START");
		   dbKarza.insertAddressRequestResponse(fullReqRes);
		   logger.info("ADDRESS MATCHING :: insertAddressRequestResponse():: END");
	}
	   protected int savecompanyLLPCINLookUpRequestResponse(KarzaFullReqRes fullReqRes) {
			logger.info("Company LLP/CIN LookUp :: insertLLPCINLookUpRequestResponse():: START");
			int count = dbKarza.insertLLPCINLookUpRequestResponse(fullReqRes);
			logger.info("Company LLP/CIN LookUp :: insertLLPCINLookUpRequestResponse():: END");
			return count;
	}
protected int savecompSearchRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("KARZA COMPSEARCH :: savecompSearchRequestResponse():: START");
	int count = dbKarza.insertcompSearchRequestResponse(fullReqRes);
logger.info("KARZA COMPSEARCH:: savecompSearchRequestResponse :: END");
	   return count;
}
protected int savenameSimilarityRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("NAME SIMILARITY :: insertnameSimilarityRequestResponse():: START");
	int count = dbKarza.insertnameSimilarityRequestResponse(fullReqRes);
	logger.info("NAME SIMILARITY:: insertnameSimilarityRequestResponse() :: END");
	 
	   return count;
}
protected int saveLpgIdentiRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("LPG Identifier :: insertLpgIdentiRequestResponse():: START");
	 int count = dbKarza.insertLpgIdentiRequestResponse(fullReqRes);
	 logger.info("LPG Identifier::insertLpgIdentiRequestResponse() :: END");
	   return count;
}
protected int saveVoterRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("VOTER :: insertVoterRequestResponse():: START");
	 int count = dbKarza.insertVoterRequestResponse(fullReqRes);
	 logger.info("VOTER::insertVoterRequestResponse() :: END");
	   return count;
}

protected int saveLpgRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("LPG :: insertLpgRequestResponse():: START");
	 int count = dbKarza.insertLpgRequestResponse(fullReqRes);
	 logger.info("LPG::insertLpgRequestResponse() :: END");
	   return count;
}
protected int saveMCASignatureRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("MCA Signatories :: insertMCASignatureRequestResponse():: START");
	int count = dbKarza.insertMCASignatureRequestResponse(fullReqRes);
	logger.info("MCA Signatories :: insertMCASignatureRequestResponse():: END");
	return count;
	}


	protected int saveElectrical2RequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA Electrical2 :: saveElectricalRequestResponse():: START");
		int count = dbKarza.insertElectrical2RequestResponse(fullReqRes);
	    logger.info("KARZA Electrical2:: saveElectricalRequestResponse() :: END");
		return count;
	}
	protected int saveRCAuthRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA RCAuth :: saveRCAuthRequestResponse():: START");
		int count = dbKarza.insertRCAuthRequestResponse(fullReqRes);
	    logger.info("KARZA RCAuth:: saveRCAuthRequestResponse :: END");
		return count;
	}
	protected int saveFSSAILicenceRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA FSSAILicence :: saveFSSAILicenceRequestResponse():: START");
		int count = dbKarza.insertFSSAILicenceRequestResponse(fullReqRes);
	    logger.info("KARZA FSSAILicence:: saveFSSAILicenceRequestResponse() :: END");
		return count;
	}

	protected int saveElectricalRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA Electrical :: saveElectricalRequestResponse():: START");
		int count = dbKarza.insertElectricalRequestResponse(fullReqRes);
	    logger.info("KARZA Electrical:: saveElectricalRequestResponse() :: END");
		return count;
	}

	protected int saveTeleRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA Tele :: saveTeleRequestResponse():: START");
		int count = dbKarza.insertTeleRequestResponse(fullReqRes);
	    logger.info("KARZA Tele:: saveTeleRequestResponse() :: END");
		return count;
	}
	protected int saveEsicIdRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA EsicId :: saveEsicIdRequestResponse():: START");
		int count = dbKarza.insertEsicIdRequestResponse(fullReqRes);
	    logger.info("KARZA EsicId:: saveEsicIdRequestResponse() :: END");
		return count;
	}

	protected int savePngRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA PNG :: savePngRequestResponse():: START");
		int count = dbKarza.insertPngRequestResponse(fullReqRes);
	    logger.info("KARZA PNG:: savePngRequestResponse() :: END");
		return count;
	}
	protected int saveForm16QuatRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA Form16Quat :: saveForm16QuatRequestResponse():: START");
		int count = dbKarza.insertForm16QuatRequestResponse(fullReqRes);
	    logger.info("KARZA Form16Quat:: saveForm16QuatRequestResponse() :: END");
		return count;
	}
	protected int saveForm16RequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA Form16 :: saveForm16RequestResponse():: START");
		int count = dbKarza.insertForm16RequestResponse(fullReqRes);
	    logger.info("KARZA Form16:: saveForm16RequestResponse() :: END");
		return count;
		
	}
	protected int  saveIECRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA IEC :: saveIECRequestResponse():: START");
		int count = dbKarza.insertIECRequestResponse(fullReqRes);
	    logger.info("KARZA IEC:: saveIECRequestResponse() :: END");
		return count;
		
	}
	protected int saveTanRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA Tan :: saveTanRequestResponse():: START");
		int count = dbKarza.insertTanRequestResponse(fullReqRes);
	    logger.info("KARZA Tan:: saveTanRequestResponse() :: END");
		return count;
	}

	private int  saveShopEstablishmentReqRes(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA ShopEstablishment :: saveShopEstablishmentReqRes():: START");
		int count = dbKarza.insertShopEstablishmentReqRes(fullReqRes);
	    logger.info("KARZA ShopEstablishment:: saveShopEstablishmentReqRes() :: END");
		return count;
	}
	
	protected int saveDlRequestResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA Dl2 :: saveDlRequestResponse():: START");
		int count = dbKarza.insertDlRequestResponse(fullReqRes);
	    logger.info("KARZA Dl2:: saveDlRequestResponse() :: END");
		return count;
	}
	
		protected int saveDlReqRes(KarzaFullReqRes fullReqRes) {
			logger.info("KARZA Dl :: saveDlReqRes():: START");
			int count = dbKarza.insertDlReqRes(fullReqRes);
		    logger.info("KARZA Dl:: saveDlReqRes() :: END");
			return count;
	}
    private int  saveNregaRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("KARZA Dl :: saveDlRequestResponse():: START");
	int count = dbKarza.insertNregaRequestResponse(fullReqRes);
    logger.info("KARZA Dl:: saveDlRequestResponse() :: END");
	return count;
}

   protected int savePanRequestResponse(KarzaFullReqRes fullReqRes) {
	logger.info("KARZA PAN :: saveDlRequestResponse():: START");
	int count = dbKarza.insertPanRequestResponse(fullReqRes);
    logger.info("KARZA PAN:: saveDlRequestResponse() :: END");
	return count;
	
}
   protected int saveTeleRequestResponse2(KarzaFullReqRes fullReqRes) {
	   logger.info("KARZA TELE2 :: saveDlRequestResponse():: START");
		int count = dbKarza.insertTeleRequestResponse2(fullReqRes);
	    logger.info("KARZA TELE2:: saveDlRequestResponse() :: END");
		return count;
	}
   private void insertsavepassport(KarzaFullReqRes karzaFullReqRes) {
		logger.info("KARZA PASSPORT :: saveElectricalRequestResponse():: START");
		dbKarza.inserAllinDBPassport(karzaFullReqRes);
		logger.info("KARZA PASSPORT:: saveElectricalRequestResponse() :: END");
	}


	private void insertsaveITRAuth(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA ITRAUTH:: saveElectricalRequestResponse():: START");
		dbKarza.inserAllinDBPITRAuth(fullReqRes);
		logger.info("KARZA ITRAUTH:: saveElectricalRequestResponse() :: END");

	}

	private void insertsaveIFSC(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA IFSC :: saveElectricalRequestResponse():: START");
		dbKarza.insertAllinIFSC(fullReqRes);
		logger.info("KARZA IFSC:: saveElectricalRequestResponse() :: END");
	}

	private void insertsaveHSNRequest(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA HSN :: saveElectricalRequestResponse():: START");
		dbKarza.insertHSNRequestProcess(fullReqRes);
		logger.info("KARZA HSN:: saveElectricalRequestResponse() :: END");

	}

	private void insertsaveEmailAuthResponse(KarzaFullReqRes fullReqRes) {
		logger.info("KARZA EMAILAUTH :: saveElectricalRequestResponse():: START");
		dbKarza.insertEmailAuthResponse(fullReqRes);
		logger.info("KARZA EMAILAUTH:: saveElectricalRequestResponse() :: END");

	}
	private void insertsaveAadharResponse(KarzaFullReqRes fullReqRes) { 
		logger.info("KARZA AADHAR:: saveElectricalRequestResponse():: START");
		dbKarza.insertAadharReqResponse(fullReqRes); 
		logger.info("KARZA Aadhar:: saveElectricalRequestResponse() :: END");
		}
	private void insertsaveUdyogAadharResponse(KarzaFullReqRes fullReqRes) { 
		logger.info("KARZA UDYOGAADHAR :: saveElectricalRequestResponse():: START");
		dbKarza.insertAllinUdyogAadharResponse(fullReqRes);
		logger.info("KARZA UDYOGAADHAR:: saveElectricalRequestResponse() :: END");
	 }
	
	 private void insertsaveGSTAuthenticationResponse(KarzaFullReqRes fullReqRes) { 
		 logger.info("KARZA GSTAUTHENTICATION :: saveElectricalRequestResponse():: START");   
		 dbKarza.insertAlinlGSTAuthenticationResponse(fullReqRes );
		 logger.info("KARZA GSTAUTHENTICATION:: saveElectricalRequestResponse() :: END");
		    
	 }
	 
	 private void saveCompanyLLPIdentificationRequestResponse(KarzaFullReqRes fullReqRes) { 
		 logger.info("KARZA COMPANYLLPIDENTIFICATION :: saveElectricalRequestResponse():: START"); 
		 dbKarza.insertAllinLLPIdentificationResponse(fullReqRes); 
		 logger.info("KARZA COMPANYLLPIDENTIFICATION:: saveElectricalRequestResponse() :: END");
		  
		  }
	 private void insertsaveCAMemberShipAuthResponse(KarzaFullReqRes fullReqRes) {
		 logger.info("KARZA CAMEMBERSHIPAUTH :: saveElectricalRequestResponse():: START");
		 dbKarza.insertAllinCAMemberShipAuthResponse(fullReqRes);
		 logger.info("KARZA CAMEMBERSHIPAUTH:: saveElectricalRequestResponse() :: END");    
	 }
	 private void insertsaveICSIMemberShipResponse(KarzaFullReqRes fullReqRes) { 
		 logger.info("KARZA ICSIMemberShip:: saveElectricalRequestResponse():: START");
		 dbKarza.insertAllinICSIMemberShipResponse(fullReqRes);
		 logger.info("KARZA ICSIMemberShip:: saveElectricalRequestResponse() :: END");
			 
		}
	 private void insertsaveICWAIFirmAuthResponse(KarzaFullReqRes fullReqRes) {
		 logger.info("KARZA ICWAIFirmAuth :: saveElectricalRequestResponse():: START");
		 dbKarza.inserAllintICWAIFirmAuthResponse(fullReqRes);
		 logger.info("KARZA ICWAIFirmAuth:: saveElectricalRequestResponse() :: END");	 
	 }
	 private void insertsaveEPFAuthOTPResponse(KarzaFullReqRes fullReqRes) { 
		 logger.info("KARZA EPFAuthOTP :: saveElectricalRequestResponse():: START");
		 dbKarza.insertAllinEPFAuthOTPResponse(fullReqRes); 
		 logger.info("KARZA EPFAuthOTP:: saveElectricalRequestResponse() :: END");
		  }
	 private void insertsaveGstIdentificationResponse(KarzaFullReqRes fullReqRes)
	  {
		 logger.info("KARZA GstIdentification :: saveElectricalRequestResponse():: START");
		 dbKarza.insertAllinGstIdentificationResponse(fullReqRes);
		 logger.info("KARZA GstIdentification:: saveElectricalRequestResponse() :: END");
	  }
     private void insertsaveEPFAuthPassbookResponse(KarzaFullReqRes fullReqRes) {
   	  logger.info("KARZA EPFAuthPassbook :: saveElectricalRequestResponse():: START");
   	  dbKarza.insertAllinEPFAuthPassbookResponse(fullReqRes);			
   	  logger.info("KARZA EPFAuthPassbook:: saveElectricalRequestResponse() :: END"); 
     }
     protected void saveKycOcrRequestResponse(KarzaFullReqRes fullReqRes) {
 		logger.info("KycOcr :: saveKycOcrRequestResponse():: START");
 		dbKarza.insertKycOcrRequestResponse(fullReqRes);
 		logger.info("KycOcr :: saveKycOcrRequestResponse():: END");
 		
 	}

}
