package com.qualtech.crif.api.service.impl;

import java.util.Calendar;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.crif.api.crif.response.Indvreportfile;
import com.qualtech.crif.api.db.dao.CriffDetailsDBDao;
import com.qualtech.crif.api.dto.CrifTrackerDTO;
import com.qualtech.crif.api.dto.ERRORSTATUS;
import com.qualtech.crif.api.dto.ErrorInfo;
import com.qualtech.crif.api.entity.CriffDetailLogs;
import com.qualtech.crif.api.request.CriffApiRequest;
import com.qualtech.crif.api.request.CriffInternalRequestInterface;
import com.qualtech.crif.api.request.CriffReqRes;
import com.qualtech.crif.api.response.ApiResponseHeader;
import com.qualtech.crif.api.response.CriffApiResponse;
import com.qualtech.crif.api.response.CriffResponsePayload;
import com.qualtech.crif.api.service.CriffService;
import com.qualtech.crif.api.utils.ConvertCriffResponsetoEntities;
import com.qualtech.crif.api.utils.CrifUtil;
import com.qualtech.crif.api.utils.CrifUtilInterface;
import com.qualtech.crif.api.utils.ResponseHandler;
import com.qualtech.crif.api.utils.Testt;

@Service
public class CriffServiceImpl implements CriffService 
{
	Logger logger = Logger.getLogger(CriffServiceImpl.class.getName());

	@Autowired
	private CriffDetailsDBDao criffDetailDBDao;
	@Autowired
	private CrifUtilInterface criffUtil;
	@Autowired
	private PropertyFile resProp;
	@Autowired
	private CriffInternalRequestInterface criffInternalRequest;
	ObjectMapper om = new ObjectMapper();
	Calendar cal = Calendar.getInstance();

	public CriffApiResponse criffService(final CriffApiRequest criffApiRequest, final String service) {

		ErrorInfo errorInfo = new ErrorInfo();
		CriffApiResponse crifapiResponse = new CriffApiResponse();
		CriffResponsePayload payload = new CriffResponsePayload();
		ApiResponseHeader header = new ApiResponseHeader();
		CrifTrackerDTO crifTrackerDTO = new CrifTrackerDTO();

		String hardCodedEnv="N";
		ResourceBundle res=null;
		try 
		{
			res = ResourceBundle.getBundle("hardcode");
			hardCodedEnv = resProp.getString("com.crif.pcs.demo.development");	
			logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		crifTrackerDTO.setService(service);
		try
		{
			try 
			{
				header.setAppId(criffApiRequest.getHeader().getAppId());
				header.setCorrelationId(criffApiRequest.getHeader().getCorrelationId());
				header.setMsgVersion(criffApiRequest.getHeader().getMsgVersion());
				crifapiResponse.setHeader(header);
			} 
			catch (Exception ex) 
			{
				logger.error("CriffServiceImpl || criffService() || Exception in setting Headers :: " + ex);
			}

			String requestxml = "";
			boolean requestxmlFlag = false;

			try 
			{
				requestxml = criffUtil.convertRequestJsontoXmlService(criffApiRequest, service);
				requestxmlFlag = true;
				crifTrackerDTO.setCrifApiReq(requestxml);
				logger.info(
						"CriffServiceImpl || criffService() || Request xml for calling API from convertRequestJsontoXmlService() ");
			} 
			catch (Exception ex)
			{
				errorInfo.setCode("501");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Invalid Request Parameter");
				errorInfo.setDescription("Error while creating Request xml for calling API");
				crifapiResponse.setErrorInfo(errorInfo);
				logger.error("CriffServiceImpl || criffService() || Exception in XML request creation from convertRequestJsontoXmlService() "+ ex);
				return crifapiResponse;
			}

			String firstResponse = "";
			boolean firstCallFlag = false;
			if (requestxmlFlag) 
			{
				try 
				{
					if(hardCodedEnv.equals("Y") && service.equals("MFI"))
					{
						firstResponse=res.getString("CRIF_MFI_FIRST_RESPONSE");	
					}
					else if(hardCodedEnv.equals("Y") && service.equals("CONSUMER"))
					{
						firstResponse=res.getString("CRIF_PCS_FIRST_RESPONSE");
					}
					else 
					{
						firstResponse = criffInternalRequest.callFirstRequest(requestxml);
					}
					firstCallFlag = true;
					crifTrackerDTO.setCrifApiRes(firstResponse);
					logger.info("CriffServiceImpl || criffService() || First Request OF API : Success");
				} 
				catch (Exception ex) 
				{
					errorInfo.setCode("502");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage("Error While Calling API - Request");
					errorInfo.setDescription("Error while calling API - First Request ");
					crifapiResponse.setErrorInfo(errorInfo);
					logger.error("CriffServiceImpl || criffService() || Exception while calling API - First Request");
					return crifapiResponse;
				}
			}

			Map<String, String> respAcknwldge = null;
			ResponseHandler responseHandler = new ResponseHandler();
			boolean ackFlag = false;
			if (firstCallFlag) 
			{
				try 
				{
					respAcknwldge = responseHandler.responseTraverseNeoAcknwldge(firstResponse);
					ackFlag = true;
					logger.info("CriffServiceImpl || criffService() || ACK Request OF API : Success");
				} 
				catch (Exception ex)
				{
					logger.error("CriffServiceImpl || criffService() || error while calling crif highmark:  " + ex);
					errorInfo.setCode("200");
					errorInfo.setStatus(ERRORSTATUS.SUCCESS);
					String errorDesc = new Testt().getErrorDescription(firstResponse);
					errorInfo.setMessage(errorDesc);
					errorInfo.setDescription(errorDesc);
					crifapiResponse.setErrorInfo(errorInfo);
					logger.error(errorDesc);
					return crifapiResponse;
				}
			}

			boolean finalFlag = false;
			if (ackFlag) {
				try {
					if (respAcknwldge.get("ERROR_CODE").equalsIgnoreCase("")
							&& !respAcknwldge.get("ERROR_CODE").equals(null)) {
						logger.info("CRIF service : Request Successfully processed : No Error in Ackwledge Xml");
						if (respAcknwldge != null && respAcknwldge.get("INQ_REF-NO") != null
								&& !respAcknwldge.get("INQ_REF-NO").equalsIgnoreCase("")
								&& respAcknwldge.get("REPORT-ID") != null
								&& !respAcknwldge.get("REPORT-ID").equalsIgnoreCase("")
								&& respAcknwldge.get("REQUEST_DT") != null
								&& !respAcknwldge.get("REQUEST_DT").equalsIgnoreCase("")) {

							String issueXml = CrifUtil.getIssueXML(firstResponse, respAcknwldge);
							crifTrackerDTO.setCrifApiIssueXmlReq(issueXml);
							logger.info("Getting Final Response through IssueXml : Start");
							CrifTrackerDTO crifTrackerDTOTemp = criffInternalRequest.lastResponse(issueXml,service);
							crifTrackerDTO.setCrifApiIssueXmlRes(crifTrackerDTOTemp.getCrifApiIssueXmlRes());
							crifTrackerDTO.setIndvreportfile(crifTrackerDTOTemp.getIndvreportfile());
							logger.info("Getting Final Response through IssueXml : End");
							finalFlag = true;
						}
					}
				} catch (Exception ex) {
					errorInfo.setCode("504");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage("Error While Calling API - Final");
					errorInfo.setDescription("Error while calling API - Final Through IssueXml Request ");
					crifapiResponse.setErrorInfo(errorInfo);
					logger.error(
							"CriffServiceImpl || criffService() || Exception while calling API - Final Through IssueXml Request"
									+ ex);
					return crifapiResponse;
				}
			}

			if (finalFlag) {
				try {
					logger.info("Creating Final API Response : Start");
					Indvreportfile indvreportfile = crifTrackerDTO.getIndvreportfile();
					@SuppressWarnings({ "unchecked" })
					Map<String, Object> additionalProperties = (Map<String, Object>) ((Map<?, ?>) ((Map<?, ?>) indvreportfile
							.getAdditionalProperties().get("indvreportfile")).get("indvreports")).get("indvreport");
					additionalProperties.remove("header");
					additionalProperties.remove("request");
					additionalProperties.remove("alerts");
					String htmldata = ((Map<?, ?>) additionalProperties.get("printablereport")).get("content")
							.toString();
					String fileName = ((Map<?, ?>) additionalProperties.get("printablereport")).get("filename")
							.toString();

					///////////////////////////////// CODE FOR BYTE
					///////////////////////////////// DATA////////////////////////////////////////
					if ("MFI".equals(service)) {
						/* String bary= */criffUtil.pdfCreationMFI(crifTrackerDTO,
								fileName.substring(0, fileName.length() - 5));
					} else {
						/* String bary= */criffUtil.pdfCreation(crifTrackerDTO,
								fileName.substring(0, fileName.length() - 5));
					}

					// String bary="xyz";
					// crifTrackerDTO.setPdfByteArray(bary);
					additionalProperties.put("pdfByteArray", crifTrackerDTO.getPdfByteArray());
					///////////////////////////////// CODE FOR BYTE
					///////////////////////////////// DATA////////////////////////////////////////
					crifTrackerDTO.setHtmlResponse(htmldata);
					payload.setTransaction(additionalProperties);
					payload.setPdfPath(crifTrackerDTO.getPdfPath());
					crifapiResponse.setPayload(payload);
					errorInfo.setCode("200");
					errorInfo.setStatus(ERRORSTATUS.SUCCESS);
					errorInfo.setMessage("Success");
					errorInfo.setDescription("Response Generated Successfully");
					crifapiResponse.setErrorInfo(errorInfo);
					crifTrackerDTO.setApiResponse(new Gson().toJson(crifapiResponse));
					logger.info("Creating Final API Response : End");
				} catch (Exception ex) {
					errorInfo.setCode("200");
					errorInfo.setStatus(ERRORSTATUS.SUCCESS);
					errorInfo.setMessage("No record found for this user.");
					errorInfo.setDescription("No record found");
					crifapiResponse.setErrorInfo(errorInfo);
					logger.error("CriffServiceImpl || criffService() || No record found :: " + ex);
					return crifapiResponse;
				}
			}
		} catch (Exception e) {
			logger.error("CriffServiceImpl || criffService() || Exception Occurs " + e);

		} finally {
			final CriffApiResponse crifApiResponse_temp = crifapiResponse;
			try {
				final CrifTrackerDTO insertCrifTrackerDTO = crifTrackerDTO;
				new Thread(new Runnable() {
					public void run() {
						runningBackgroundProcessForSaving(criffApiRequest, crifApiResponse_temp, insertCrifTrackerDTO);
					}
				}).start();
			} catch (Exception ex) {
				logger.error(
						"CriffServiceImpl || criffService() || Error while setting records in DB by using Threads : "
								+ ex);
			}
		}
		return crifapiResponse;
	}

	private void runningBackgroundProcessForSaving(CriffApiRequest criffApiRequest, CriffApiResponse crifApiResponse,
			CrifTrackerDTO crifTrackerDTO) {
		try {
			CriffDetailLogs criffDetailLog = new CriffDetailLogs();

			CriffReqRes crifreqres = new CriffReqRes();

			crifreqres.setCorelationid(criffApiRequest.getHeader().getCorrelationId());
			crifreqres.setAppid(crifApiResponse.getHeader().getAppId());
			crifreqres.setToken(criffApiRequest.getHeader().getToken());
			crifreqres.setToken(criffApiRequest.getHeader().getToken());

			crifreqres.setCreatedTime(cal.getTime());
			// crifreqres.setUniqueId(uniqueId);


			crifreqres.setUpdated_Time(cal.getTime());

			crifreqres.setTag(crifTrackerDTO.getService());
			crifreqres.setIntReq(crifTrackerDTO.getCrifApiIssueXmlReq());
			crifreqres.setIntRes(crifTrackerDTO.getCrifApiIssueXmlRes());
			try {

				if(criffApiRequest!=null) 
				{
					crifreqres.setRequest(om.writeValueAsString(criffApiRequest));

				}
				if(crifApiResponse!=null)
				{
					crifreqres.setResponse(om.writeValueAsString(crifApiResponse));
					if(crifApiResponse.getPayload()!=null) {
						crifreqres.setPdfPath(crifApiResponse.getPayload().getPdfPath());
					}

				}

				String price=null;
				if(crifApiResponse.getPayload()!=null && crifApiResponse.getPayload().getTransaction()!=null) 
				{
					price=resProp.getString("com.qc.crif.success.price");
				}else {
					price=resProp.getString("com.qc.crif.failure.price");
				}
				crifreqres.setPrice(price);

				crifreqres.setStatus(crifApiResponse.getErrorInfo().getStatus().toString());
			} catch (Exception e) {
				logger.error("Error while setting CRIFF data to Main table");
			}

			criffDetailDBDao.save(crifreqres);

			criffDetailLog = new ConvertCriffResponsetoEntities().processCriffResponse(criffApiRequest, crifApiResponse,
					crifTrackerDTO, criffDetailDBDao);

			criffDetailDBDao.save(criffDetailLog);
			logger.info("Backgroung DB insert Successfully");
		} catch (Exception ec) {
			logger.error("CriffServiceImpl || runningBackgroundProcessForSaving() || Exception Occurs " + ec);
		}
	}

}