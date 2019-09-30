package com.qualtech.equifax.api.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibil.api.utils.UniqueId;
import com.qualtech.equifax.api.common.dto.ERRORSTATUS;
import com.qualtech.equifax.api.common.dto.EquifaxTrackerDTO;
import com.qualtech.equifax.api.common.dto.ErrorInfo;
import com.qualtech.equifax.api.db.DBEquifax;
import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;
import com.qualtech.equifax.api.interfaces.ConvertMCRResponseToEntityInt;
import com.qualtech.equifax.api.interfaces.EquifaxUtillInt;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.request.EquifaxReqRes;
import com.qualtech.equifax.api.response.EquifaxApiResponse;
import com.qualtech.equifax.api.response.EquifaxApiResponseHeader;
import com.qualtech.equifax.api.response.EquifaxCriffResponsePayload;
import com.qualtech.equifax.api.service.EquifaxService;
import com.qualtech.equifax.api.utils.ConvertPCSResponseToEntitiesInt;
import com.qualtech.equifax.api.utils.ConvertUrltoByteArray;
import com.qualtech.equifax.api.utils.EquifaxUtil;
import com.qualtech.equifax.api.utils.EquifaxUtilInt;
import com.qualtech.equifax.api.utils.EquifaxUtill;
import com.qualtech.equifax.api.utils.XTrustProvider;

@Service
public class EquifaxServiceImpl implements EquifaxService
{
	private static Logger logger = Logger.getLogger(EquifaxServiceImpl.class);
	@Autowired
	PropertyFile env;
	@Autowired
	DBEquifax dbEquifax;
	@Autowired
	EquifaxUtillInt equifaxutillint;
	@Autowired
	EquifaxUtilInt equifaxUtil;
	@Autowired
	ConvertMCRResponseToEntityInt convertMcrResponse;
	@Autowired
	ConvertPCSResponseToEntitiesInt convertPcsResponseint;
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	ObjectMapper om = new ObjectMapper();
	Calendar cal = Calendar.getInstance();
	String uniqueId = UniqueId.getUniqueId();

	public EquifaxApiResponse callEquifaxRequest(final EquifaxApiRequest equifaxapirequest, String servicetype)
	{
		Map<String, Object> map = null;
		EquifaxTrackerDTO equifaxTrackerDTO = new EquifaxTrackerDTO();
		EquifaxApiResponse equifixApiResponse = new EquifaxApiResponse();
		EquifaxIdAndContactSetter beanSetter = new EquifaxIdAndContactSetter();
		EquifaxCriffResponsePayload equifaxResPay = new EquifaxCriffResponsePayload();
		try
		{

			ErrorInfo errorInfo = new ErrorInfo();
			try 
			{
				EquifaxApiResponseHeader apiResponseHeader = new EquifaxApiResponseHeader();
				apiResponseHeader.setAppId(equifaxapirequest.getHeader().getAppId());
				apiResponseHeader.setCorrelationId(equifaxapirequest.getHeader().getCorrelationId());
				apiResponseHeader.setMsgVersion(equifaxapirequest.getHeader().getMsgVersion());
				equifixApiResponse.setHeader(apiResponseHeader);
			}
			catch (Exception ec) 
			{
				logger.error(" EquifaxServiceImpl || callEquifaxRequest() || error while setting header" + ec);
			}
			equifaxTrackerDTO.setTypeofService(servicetype);// it may be MCR or PCS
			Gson gson = new Gson();
			try 
			{
				equifaxTrackerDTO.setEquifaxjsonRequest(gson.toJson(equifaxapirequest));
			} 
			catch (Exception ec)
			{
				logger.error(
						"EquifaxServiceImpl || callEquifaxRequest() || error converting equifax request to json for saving in database"
								+ ec);
			}
			String requestxml = null;
			String responseJson = null;
			String ParsedJson = null;
			JSONObject lowerkeyjson = null;
			StringBuilder out1 = new StringBuilder();
			JSONObject o;

			if ("MCR".equals(servicetype)) 
			{
				try
				{
					requestxml = equifaxutillint.rquestxmlforMCR(equifaxapirequest);
				} 
				catch (Exception ec) 
				{
					logger.error(
							"EquifaxServiceImpl || callEquifaxRequest() || Exception while calling rquestxmlforMCR()"
									+ ec);
					errorInfo.setCode("502");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage("Invaild request Parameter ");
					errorInfo.setDescription(
							"Error while creating xml for Equifax for MCR ,Please consult I.T Department or Check your Request Parameter");
					equifixApiResponse.setErrorInfo(errorInfo);
					EquifaxUtil equifaxUtil = new EquifaxUtil();
					String reportno="NA";
					String memberNumber="";
					if ("PCS".equals(servicetype)) {

						memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");

					} else if ("MCR".equals(servicetype)) {
						memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
					}
					equifaxTrackerDTO.setByteArray(equifaxUtil.blankPdfByteArray(equifaxapirequest, servicetype,reportno,env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber));
					map = new HashMap<String, Object>();
					map.put("pdfByteArray", equifaxTrackerDTO.getByteArray());
					equifaxResPay.setTransaction(map);
					equifixApiResponse.setPayload(equifaxResPay);
					gson = new Gson();
					equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
					return equifixApiResponse;
				}
			}
			else if ("PCS".equals(servicetype))
			{
				try 
				{
					requestxml = equifaxutillint.rquestxmlforPCS(equifaxapirequest);
				} 
				catch (Exception ec) 
				{
					logger.error(
							"EquifaxServiceImpl || callEquifaxRequest() || Exception while calling rquestxmlforPCS()"
									+ ec);
					errorInfo.setCode("503");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage("Invalid Request parameter");
					errorInfo.setDescription(
							"Error while creating xml for Equifax for PCS ,Please consult I.T Department or Check your Request Parameter ");
					equifixApiResponse.setErrorInfo(errorInfo);
					EquifaxUtil equifaxUtil = new EquifaxUtil();
					String reportno="NA";
					String memberNumber="";
					if ("PCS".equals(servicetype)) {

						memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");

					} else if ("MCR".equals(servicetype)) {
						memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
					}
					equifaxTrackerDTO.setByteArray(equifaxUtil.blankPdfByteArray(equifaxapirequest, servicetype,reportno,env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber));
					map = new HashMap<String, Object>();
					map.put("pdfByteArray", equifaxTrackerDTO.getByteArray());
					equifaxResPay.setTransaction(map);
					equifixApiResponse.setPayload(equifaxResPay);
					gson = new Gson();
					equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
					return equifixApiResponse;
				}
			}
			equifaxTrackerDTO.setEquifaxApiReq(requestxml);
			try 
			{
				String demoDev="N";
				ResourceBundle res=null;
				try 
				{
					res = ResourceBundle.getBundle("hardcode");
					demoDev = env.getString("com.equifax.allapi.demo.development");
				}
				catch(Exception ex)
				{
					demoDev="N";
				}

				if (("PCS".equals(servicetype) && "N".equals(demoDev))
						|| ("MCR".equals(servicetype) && "N".equals(demoDev)))
				{
					XTrustProvider xTrustProvider = new XTrustProvider();
					xTrustProvider.install();
					logger.debug("EQUIFAX service: call to EQUIFAX: START");
					HttpURLConnection conn = null;
					String DevMode = env.getString("spring.enable.proxy.development");
					URL url;
					try
					{
						url = new URL(env.getString("com.qualtech.pan2.resource.EQUIFAX.url"));
						if (DevMode != null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y"))
						{
							logger.info("We are running in Development Mode So Proxy Enabled");
							Proxy proxy = new Proxy(Proxy.Type.HTTP,
									new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
							conn = (HttpURLConnection) url.openConnection(proxy);
						}
						else 
						{
							conn = (HttpURLConnection) url.openConnection();
						}
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Type", "text/xml");
						conn.setRequestProperty("SOAPAction",
								"http://services.equifax.com/CreditReportWS/CreditReportWSInquiry/1.0");
						conn.setConnectTimeout(Integer.parseInt(env.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
						conn.setReadTimeout(Integer.parseInt(env.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
						conn.setDoOutput(true);
						conn.setDoInput(true);
						if ("PCS".equals(servicetype) || ("MCR".equals(servicetype)))
						{
							OutputStream out = conn.getOutputStream();
							out.write(requestxml.getBytes());
							out.flush();
						}
						BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						String st;
						StringBuilder output = new StringBuilder();
						while ((st = in.readLine()) != null)
						{
							output.append(st);
						}
						out1 = output;
					}
					catch (Exception ec)
					{
						logger.error("EquifaxServiceImpl || callEquifaxRequest() || Exception while creating url " + ec);
						errorInfo.setCode("601");
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setMessage("Error while creating connection with Equifax Services");
						errorInfo.setDescription("Error while creating connection with Equifax Services");
						equifixApiResponse.setErrorInfo(errorInfo);
						EquifaxUtil equifaxUtil = new EquifaxUtil();
						String reportno="NA";
						String memberNumber="";
						if ("PCS".equals(servicetype))
						{
							memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");
						}
						else if ("MCR".equals(servicetype))
						{
							memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
						}
						equifaxTrackerDTO.setByteArray(equifaxUtil.blankPdfByteArray(equifaxapirequest, servicetype,reportno,env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber));
						map = new HashMap<String, Object>();
						map.put("pdfByteArray", equifaxTrackerDTO.getByteArray());
						equifaxResPay.setTransaction(map);
						equifixApiResponse.setPayload(equifaxResPay);
						gson = new Gson();
						equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
						return equifixApiResponse;
					}
					finally
					{
						try
						{
							conn.disconnect();
						}
						catch(Exception ex)
						{
							logger.info("HTTP Connection close : "+ex);
						}
					}
				} 
				else if ("PCS".equals(servicetype) && "Y".equals(demoDev)) 
				{
					try 
					{
						out1=new StringBuilder(res.getString("EQUIFAX_PCS_RES"));
					}
					catch (Exception e) 
					{
						logger.error("Demo Mode enabled but unable to get hard code data from property file");
					}

				} 
				else if ("MCR".equals(servicetype)  && "Y".equals(demoDev)) 
				{
					try 
					{
						out1=new StringBuilder(res.getString("EQUIFAX_MFI_RES"));
					}
					catch (Exception e) 
					{
						logger.error("Demo Mode enabled but unable to get hard code data from property file");
					}
				}

				logger.info("Equifax Output : Original : Response Data in XML format : " + out1);
				equifaxTrackerDTO.setEquifaxApiRes(out1.toString());
			}
			catch (Exception ec)
			{
				logger.error("EquifaxServiceImpl || callEquifaxRequest() || Exception while accessing Input Stream " + ec);
				errorInfo.setCode("504");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Invalid Request Parameter");
				errorInfo.setDescription("Error while creating Response");
				equifixApiResponse.setErrorInfo(errorInfo);
				logger.error("Error while creating OutPut from calling API");
				EquifaxUtil equifaxUtil = new EquifaxUtil();
				String reportno="NA";
				String memberNumber="";
				if ("PCS".equals(servicetype))
				{

					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");

				}
				else if ("MCR".equals(servicetype))
				{
					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
				}
				equifaxTrackerDTO.setByteArray(equifaxUtil.blankPdfByteArray(equifaxapirequest, servicetype,reportno,env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber));
				map = new HashMap<String, Object>();
				map.put("pdfByteArray", equifaxTrackerDTO.getByteArray());
				equifaxResPay.setTransaction(map);
				equifixApiResponse.setPayload(equifaxResPay);
				gson = new Gson();
				equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
				return equifixApiResponse;
			}
			try 
			{
				o = org.json.XML.toJSONObject(out1.toString());
				responseJson = o.toString().replaceAll("soapenv:", "")
						.replaceFirst(",\"xmlns:soapenv\":\"http://schemas.xmlsoap.org/soap/envelope/\"", "")
						.replaceFirst("\"xmlns:sch\":\"http://services.equifax.com/eport/ws/schemas/1.0\",", "");
				ParsedJson = responseJson.replaceAll("sch:", "");
				logger.info("Response Data in JSON format : " + ParsedJson);
				JSONObject parsedjsonObject = new JSONObject(ParsedJson);
				lowerkeyjson = recursiveJsonKeyConverterToLower(parsedjsonObject);
			} 
			catch (JSONException e)
			{
				logger.error("EquifaxServiceImpl || callEquifaxRequest() || Exception while parsing JSON " + e);
				errorInfo.setCode("505");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Consult IT Department.");
				errorInfo.setDescription("Consult IT Department.");
				equifixApiResponse.setErrorInfo(errorInfo);
				logger.error("Error while creating OutPut from calling API");
				EquifaxUtil equifaxUtil = new EquifaxUtil();
				String reportno="NA";
				String memberNumber="";
				if ("PCS".equals(servicetype))
				{

					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");

				}
				else if ("MCR".equals(servicetype)) 
				{
					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
				}
				equifaxTrackerDTO.setByteArray(equifaxUtil.blankPdfByteArray(equifaxapirequest, servicetype,reportno,env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber));
				map = new HashMap<String, Object>();
				map.put("pdfByteArray", equifaxTrackerDTO.getByteArray());
				equifaxResPay.setTransaction(map);
				equifixApiResponse.setPayload(equifaxResPay);
				gson = new Gson();
				equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
				return equifixApiResponse;
			}
			logger.info("Calling EQUIFAX Service : End");
			try 
			{
				JSONObject jsonObj = new JSONObject(lowerkeyjson.toString());
				equifaxTrackerDTO.setResponseTransactionJson(lowerkeyjson.toString());
				if (jsonObj != null) 
				{
					try 
					{
						JSONObject jsonErrorObj = jsonObj.getJSONObject("envelope").getJSONObject("body")
								.getJSONObject("inquiryresponse").getJSONObject("reportdata");
						if (jsonErrorObj.has("error")) 
						{
							String errorCode = jsonErrorObj.getJSONObject("error").get("errorcode").toString();
							String errorMsg = jsonErrorObj.getJSONObject("error").get("errormsg").toString();
							errorInfo.setCode(errorCode);
							errorInfo.setStatus(ERRORSTATUS.FAILURE);
							errorInfo.setMessage(errorMsg);
							errorInfo.setDescription(errorMsg);
							equifixApiResponse.setErrorInfo(errorInfo);
							logger.error("Error  response msg:  " + errorMsg);
							EquifaxUtil equifaxUtil = new EquifaxUtil();
							JSONObject jsonReportObj = jsonObj.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("inquiryresponseheader");
							String reportno=jsonReportObj.get("reportorderno").toString();
							String memberNumber="";
							if ("PCS".equals(servicetype)) {

								memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");

							} else if ("MCR".equals(servicetype)) {
								memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
							}
							equifaxTrackerDTO.setByteArray(equifaxUtil.blankPdfByteArray(equifaxapirequest, servicetype,reportno,env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber));
							map = new HashMap<String, Object>();
							map.put("pdfByteArray", equifaxTrackerDTO.getByteArray());
							equifaxResPay.setTransaction(map);
							equifixApiResponse.setPayload(equifaxResPay);
							gson = new Gson();
							equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
							return equifixApiResponse;
						}
					} 
					catch (Exception ee)
					{
						logger.error(
								"EquifaxServiceImpl || callEquifaxRequest() || Exception while parsing JSON " + ee);
					}
				}

				Map<String, Object> completeMap = new EquifaxUtill().jsonToMap(jsonObj);
				map = ((Map) ((Map) ((Map) ((Map) completeMap.get("envelope")).get("body")).get("inquiryresponse"))
						.get("reportdata"));
				Map<String, Object> inquiryRequestData = ((Map) ((Map) ((Map) ((Map) completeMap.get("envelope"))
						.get("body")).get("inquiryresponse")).get("inquiryrequestinfo"));
				// Code to get the Report Order No, Time and Date
				Map inquiryResponseHeader = ((Map) ((Map) ((Map) ((Map) completeMap.get("envelope")).get("body"))
						.get("inquiryresponse")).get("inquiryresponseheader"));
				String Date = inquiryResponseHeader.get("date") + "";
				String time = inquiryResponseHeader.get("time") + "";
				String reportOrderNo = inquiryResponseHeader.get("reportorderno") + "";
				map.put("date", Date);
				map.put("time", time);
				map.put("reportorderno", reportOrderNo);
				map.put("inquiryrequestinfo", inquiryRequestData);
				// Code to get the Report Order No, Time and Date

				/////// CODE TO GENERATE PDF FROM DATA /////////////////
				Gson json = new Gson();
				// Code For Byte Array Starts
				/* Changed BY anuj */
				String tempRequestJson;
				ObjectMapper omapper = new ObjectMapper();
				String requestjson = omapper.writeValueAsString(equifaxapirequest);// apiRequest
				/* String requestjson=apiRequest.toString(); */
				/* end */
				ConvertUrltoByteArray converturltobytearray = new ConvertUrltoByteArray();
				String memberNumber = "";
				String url_temp = env.getString("com.qc.liveURL");
				//url_temp = "http://localhost:8080/ibs";
				//////////////////////////////////// ********************************//////////////////////////////////
				if ("PCS".equals(servicetype)) 
				{

					url_temp += "/equifaxPcrHtml";
					equifaxTrackerDTO.setDisclaimer(beanSetter.getDisclaimer(jsonObj));
					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");

				} 
				else if ("MCR".equals(servicetype))
				{
					url_temp += "/equifaxMfiHtml";
					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
					equifaxTrackerDTO.setEquifaxIdentityInfo(beanSetter.equifaxMcrIdentityInfo(jsonObj));
					equifaxTrackerDTO.setEquifaxPersonalInfo(beanSetter.equifaxMCRPersonalInfo(jsonObj));
					equifaxTrackerDTO.setMcrIdAndContactDetails(beanSetter.setMCR_IDAndContactDetails(jsonObj));
					equifaxTrackerDTO.setDisclaimer(beanSetter.getDisclaimer(jsonObj));
				} 
				else 
				{
					logger.info("Invalid ServiceType");
				}
				tempRequestJson = requestjson;
				equifaxResPay.setTransaction(map);
				equifixApiResponse.setPayload(equifaxResPay);
				String responseJsontemporarToCreatePdf = json.toJson(equifixApiResponse);
				logger.info("ResponseData for PDF Creation : " + responseJsontemporarToCreatePdf);
				String htmlData = converturltobytearray.getingByteArray1(url_temp, tempRequestJson, "requestJson",responseJsontemporarToCreatePdf, "responseFromService",env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber);
				String pdfByteArr = equifaxUtil.convertHtmlToPDF(htmlData, "EquifaxReport" + servicetype,
						equifaxResPay);
				map.put("pdfByteArray", pdfByteArr);
				equifaxTrackerDTO.setByteArray(pdfByteArr);
				equifaxTrackerDTO.setFinalHtml(htmlData);
				logger.info("Creating Final API Response : End");
				logger.info("Final response payload Json from Service is : " + json.toJson(map));
				equifaxResPay.setTransaction(map);
				// Code For Byte Arrray Ends
				/////// CODE TO GENERATE PDF FROM DATA /////////////////

			} 
			catch (Exception ec) 
			{
				logger.error(ec);
				logger.error(
						"EquifaxServiceImpl || callEquifaxRequest() || Exception converting response into proper format "
								+ ec);
				errorInfo.setCode("506");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("consult I.T Department");
				errorInfo.setDescription(
						"Error ocured converting response into proper format ,Please consult I.T Department or Check your Request Parameter ");
				equifixApiResponse.setErrorInfo(errorInfo);
				EquifaxUtil equifaxUtil = new EquifaxUtil();
				String reportno="NA";
				String memberNumber="";
				if ("PCS".equals(servicetype)) {

					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber");

				} else if ("MCR".equals(servicetype)) {
					memberNumber = env.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber");
				}
				equifaxTrackerDTO.setByteArray(equifaxUtil.blankPdfByteArray(equifaxapirequest, servicetype,reportno,env.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"), memberNumber));
				map = new HashMap<String, Object>();
				map.put("pdfByteArray", equifaxTrackerDTO.getByteArray());
				equifaxResPay.setTransaction(map);
				equifixApiResponse.setPayload(equifaxResPay);
				gson = new Gson();
				equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
				return equifixApiResponse;
			}

			logger.info("EquifaxServiceImpl || callEquifaxRequest() || Creating Final API Response : End");
			Gson json = new Gson();
			logger.info("Final response payload Json from Service is : " + json.toJson(map));
			equifaxResPay.setTransaction(map);
			equifixApiResponse.setPayload(equifaxResPay);
			errorInfo.setCode("200");
			errorInfo.setStatus(ERRORSTATUS.SUCCESS);
			errorInfo.setMessage("Success");
			errorInfo.setDescription("Response Generated Successfully");
			equifixApiResponse.setErrorInfo(errorInfo);

			try {
				equifaxTrackerDTO.setEquifaxjsonResponse(gson.toJson(equifixApiResponse));
			} catch (Exception ec) {
				logger.error(
						"EquifaxServiceImpl || callEquifaxRequest() || Error converting equifax Response to json for saving in database"
								+ ec);
			}
		} finally {
			try {
				final EquifaxApiResponse equifaxfinalRespone = equifixApiResponse;
				final EquifaxTrackerDTO equifaxTrackerDTO_Copy = equifaxTrackerDTO;
				if ("PCS".equals(servicetype)) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							runningBackgroundProcessToSavePCSData(equifaxapirequest, equifaxfinalRespone,
									equifaxTrackerDTO_Copy);
						}
					}).start();
				} else if ("MCR".equals(servicetype)) {
					new Thread(new Runnable() {
						public void run() {
							runningBackgroundProcessToSaveMCRData(equifaxapirequest, equifaxfinalRespone,
									equifaxTrackerDTO_Copy);
						}
					}).start();
				}
			} catch (Exception e) {
				logger.error(
						"EquifaxServiceImpl || callEquifaxRequest() || Exception in DB Insertion Data Thread for runningBackgroundProcessToSaveMCRData() : "
								+ e);
			}
		}
		return equifixApiResponse;
	}

	private void runningBackgroundProcessToSavePCSData(EquifaxApiRequest equifaxApiRequest,
			EquifaxApiResponse equifaxApiResponse, EquifaxTrackerDTO equifaxTrackerDTO) {

		EquifaxReqRes equifaxPcsReqRes = new EquifaxReqRes();

		equifaxPcsReqRes.setCorelationid(equifaxApiRequest.getHeader().getCorrelationId());
		equifaxPcsReqRes.setRequest(equifaxApiRequest.toString());
		equifaxPcsReqRes.setResponse(equifaxApiResponse.toString());
		equifaxPcsReqRes.setToken(equifaxApiRequest.getHeader().getToken());
		equifaxPcsReqRes.setCreatedTime(cal.getTime());
		equifaxPcsReqRes.setUniqueId(uniqueId);
		equifaxPcsReqRes.setPdfPath(equifaxApiResponse.getPayload().getPdfPath());
		equifaxPcsReqRes.setAppid(equifaxApiResponse.getHeader().getAppId());
		equifaxPcsReqRes.setToken(equifaxApiRequest.getHeader().getToken());
		equifaxPcsReqRes.setUpdated_Time(cal.getTime());
		equifaxPcsReqRes.setStatus(equifaxApiResponse.getErrorInfo().getMessage());

		equifaxPcsReqRes.setTag(equifaxTrackerDTO.getTypeofService());

		try
		{
			EquifaxPcsAllDetails equifaxPcsAllDetails = convertPcsResponseint.convertEquifaxResponseToEntity(equifaxApiResponse, equifaxApiRequest, equifaxTrackerDTO);
			equifaxPcsAllDetails.setPdfpath(equifaxApiResponse.getPayload().getPdfPath());
			equifaxPcsAllDetails.setRequest_xml(equifaxTrackerDTO.getEquifaxApiReq());
			equifaxPcsAllDetails.setResponse_xml(equifaxTrackerDTO.getEquifaxApiRes());
			equifaxPcsAllDetails.setTracker_id(equifaxApiRequest.getHeader().getCorrelationId());
			Long result = dbEquifax.insertEquiFaxPCS(equifaxPcsAllDetails);
			logger.info("Inserted records are : " + result);
		}
		catch (Exception ec)
		{
			logger.info("EquifaxServiceImpl || runningBackgroundProcessToSavePCSData() || Exception while saving the data"+ ec);
		}
		try 
		{
			equifaxPcsReqRes.setIntReq(equifaxTrackerDTO.getEquifaxApiReq());
			equifaxPcsReqRes.setIntRes(equifaxTrackerDTO.getEquifaxApiRes());
			equifaxPcsReqRes.setRequest(om.writeValueAsString(equifaxApiRequest));
			equifaxPcsReqRes.setResponse(om.writeValueAsString(equifaxApiResponse));
			Long count = dbEquifax.insertEquifaxPCSReqRes(equifaxPcsReqRes);
		}
		catch (Exception e) 
		{
			logger.error("Error while setting  PCS data to Equifax Main table");
		}
	}

	private void runningBackgroundProcessToSaveMCRData(EquifaxApiRequest equifaxApiRequest,
			EquifaxApiResponse equifaxApiResponse, EquifaxTrackerDTO equifaxTrackerDTO) 
	{

		EquifaxReqRes equifaxMcrReqRes = new EquifaxReqRes();

		equifaxMcrReqRes.setCorelationid(equifaxApiRequest.getHeader().getCorrelationId());

		equifaxMcrReqRes.setToken(equifaxApiRequest.getHeader().getToken());
		equifaxMcrReqRes.setCreatedTime(cal.getTime());
		equifaxMcrReqRes.setUniqueId(uniqueId);
		equifaxMcrReqRes.setPdfPath(equifaxApiResponse.getPayload().getPdfPath());
		equifaxMcrReqRes.setAppid(equifaxApiResponse.getHeader().getAppId());
		equifaxMcrReqRes.setToken(equifaxApiRequest.getHeader().getToken());
		equifaxMcrReqRes.setUpdated_Time(cal.getTime());
		equifaxMcrReqRes.setTag(equifaxTrackerDTO.getTypeofService());
		try 
		{
			equifaxMcrReqRes.setIntReq(equifaxTrackerDTO.getEquifaxApiReq());
			equifaxMcrReqRes.setIntRes(equifaxTrackerDTO.getEquifaxApiRes());
			equifaxMcrReqRes.setRequest(om.writeValueAsString(equifaxApiRequest));
			equifaxMcrReqRes.setResponse(om.writeValueAsString(equifaxApiResponse));
		} 
		catch (Exception e) 
		{
			logger.error("Error while setting MCR data to Equifax Main table");
		}
		equifaxMcrReqRes.setStatus(equifaxApiResponse.getErrorInfo().getMessage());

		try 
		{
			Long count = dbEquifax.insertEquifaxMCRReqRes(equifaxMcrReqRes);
		} 
		catch (Exception e)
		{
			logger.error("Error while inserting to Database");
		}

		try 
		{

			EquifaxMcrAllDetaills equifaxMcrDetailLogs = convertMcrResponse.convertEquifaxResponseToEntity(equifaxApiResponse, equifaxApiRequest, equifaxTrackerDTO);
			equifaxMcrDetailLogs.setPdfpath(equifaxApiResponse.getPayload().getPdfPath());
			equifaxMcrDetailLogs.setTracker_id(equifaxApiRequest.getHeader().getCorrelationId());
			equifaxMcrDetailLogs.setMiscellaneous_info_json("Currently does not have data");
			int result = dbEquifax.insertEquiFaxMCR(equifaxMcrDetailLogs);
			logger.info("Inserted records are : " + result);
		} catch (Exception ec) {
			logger.error("EquifaxServiceImpl || runningBackgroundProcessToSaveMCRData || Exception occurs " + ec);
		}
	}

	private JSONObject recursiveJsonKeyConverterToLower(JSONObject jsonObject) {
		JSONObject resultJsonObject = new JSONObject();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			Object value = null;
			try {
				JSONObject nestedJsonObject = jsonObject.getJSONObject(key);
				value = this.recursiveJsonKeyConverterToLower(nestedJsonObject);
			} catch (JSONException jsonException) {
				try {
					JSONArray nestedJsonArray = jsonObject.getJSONArray(key);
					JSONArray resultJsonArray = new JSONArray();
					for (int i = 0; i < nestedJsonArray.length(); i++) {
						JSONObject resultJsonObjectForArray = nestedJsonArray.getJSONObject(i);
						value = this.recursiveJsonKeyConverterToLower(resultJsonObjectForArray);
						resultJsonArray.put(value);
					}
					value = resultJsonArray;
				} catch (JSONException jsonExceptionInner) {
					try {
						value = jsonObject.get(key);
					} catch (JSONException e) {

						logger.error("Error in recursiveJsonKeyConverterToLower");
						// TODO Auto-generated catch block
					}
				}
			}
			try {
				resultJsonObject.put(key.replaceAll("-", "").replace("-", "").toLowerCase(), value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("Error in recursiveJsonKeyConverterToLower");
			}
		}

		return resultJsonObject;
	}
}