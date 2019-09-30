package com.qualtech.equifax.api.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.equifax.api.common.dto.ERRORSTATUS;
import com.qualtech.equifax.api.common.dto.EquifaxVIDTrackerDTO;
import com.qualtech.equifax.api.common.dto.ErrorInfo;
import com.qualtech.equifax.api.db.DBEquifax;
import com.qualtech.equifax.api.entity.EquifaxVidAllDetails;
import com.qualtech.equifax.api.interfaces.EquifaxUtillInt;
import com.qualtech.equifax.api.request.EquifaxAPI_VIDRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;
import com.qualtech.equifax.api.response.EquifaxApiResponseHeader;
import com.qualtech.equifax.api.response.EquifaxCriffResponsePayload;
import com.qualtech.equifax.api.service.EquifaxVIDService;
import com.qualtech.equifax.api.utils.ConvertUrltoByteArray;
import com.qualtech.equifax.api.utils.EquifaxUtilInt;
@Service
public class EquifaxVIDServiceImpl implements EquifaxVIDService
{
	private static Logger logger = Logger.getLogger(EquifaxVIDServiceImpl.class);
	@Autowired PropertyFile resProp;
	@Autowired DBEquifax dbEquifax;
	@Autowired EquifaxUtillInt equifaxutillint;
	@Autowired EquifaxUtilInt equifaxUtil;
	public EquifaxApiResponse processEquifaxVIDRequest(EquifaxAPI_VIDRequest equifaxApiRequest)
	{                        
		EquifaxApiResponse equifaxApiResponse = new EquifaxApiResponse();
		EquifaxCriffResponsePayload equifaxresponsepayload=new EquifaxCriffResponsePayload();
		final EquifaxVidAllDetails equifaxdetails = new EquifaxVidAllDetails();
		final EquifaxVIDTrackerDTO vidTrackerDto = new EquifaxVIDTrackerDTO();
		vidTrackerDto.setTracker_id(equifaxApiRequest.getHeader().getCorrelationId());
		// Traker id is Correlation id
		try 
		{
			ErrorInfo errorInfo = new ErrorInfo();
			EquifaxApiResponseHeader header = new EquifaxApiResponseHeader();
			try 
			{
				header.setAppId(equifaxApiRequest.getHeader().getAppId());
				header.setCorrelationId(equifaxApiRequest.getHeader().getCorrelationId());
				header.setMsgVersion(equifaxApiRequest.getHeader().getMsgVersion());
			} 
			catch (Exception ec)
			{
				logger.error("EquifaxVIDServiceImpl || processEquifaxVIDRequest() || Exception setting header in response"+ec);
			}
			equifaxApiResponse.setHeader(header);

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = null;
			Map<String, Object> mapResponseHeader = null;
			
			String output;
			JSONObject o;
			String responseJson;
			String ParsedJson;
			JSONObject lowerkeyjson;
			StringBuilder out1 = new StringBuilder();
			String requestxml = "";
			try 
			{
				requestxml = equifaxutillint.requestxmlForVID(equifaxApiRequest);
				
			}
			catch (Exception ec)
			{
				ec.printStackTrace();
				logger.error("EquifaxVIDServiceImpl || processEquifaxVIDRequest() || getting Response from VID" + ec);
				errorInfo.setCode("604");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Invaild request Parameter ");
				errorInfo.setDescription(
				"Error while creating xml for Equifax for VID ,Please consult I.T Department or Check your Request Parameter");
				equifaxApiResponse.setErrorInfo(errorInfo);
				return equifaxApiResponse;
			}
			logger.info("EQUIFAX service: call to EQUIFAX VID: START");
			HttpURLConnection conn = null;
			String DevMode = resProp.getString("spring.enable.proxy.development");
			URL url;
			try
			{
				url = new URL(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.url"));

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
				try
				{
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "text/xml");
					conn.setRequestProperty("SOAPAction","http://services.equifax.com/CreditReportWS/CreditReportWSInquiry/1.0");
					conn.setConnectTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
					conn.setReadTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
					conn.setDoOutput(true);
					conn.setDoInput(true);
					//OutputStream out = conn.getOutputStream();
					/*out.write(requestxml.getBytes());
					out.flush();
					*/
					
				}
				catch (Exception ec)
				{
					logger.error("EquifaxVIDServiceImpl || processEquifaxVIDRequest() || Exception creating connection " + ec);
					errorInfo.setCode("601");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage("Error while creating connection with equifax ");
					errorInfo.setDescription("Error while creating connection with equifax");
					equifaxApiResponse.setErrorInfo(errorInfo);
					return equifaxApiResponse;
				}
			}
			catch (Exception ec)
			{
				logger.error("EquifaxVIDServiceImpl || processEquifaxVIDRequest() || getting Response from VID" + ec);
				errorInfo.setCode("502");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Invaild request Parameter ");
				errorInfo.setDescription(
				"Error while creating xml for Equifax for VID ,Please consult I.T Department or Check your Request Parameter");
				equifaxApiResponse.setErrorInfo(errorInfo);
				return equifaxApiResponse;
			}
			finally
			{
				conn.disconnect();
			}
			try 
			{
				/*BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null)
				{
					logger.info(output);
					if (output != null)
						out1.append(output);
				}*/
				

				String demo = resProp.getString("spring.enable.VID.demo.development");
				if ("Y".equals(demo))    
				{
					out1 = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><sch:InquiryResponse xmlns:sch=\"http://services.equifax.com/eport/ws/schemas/1.0\">  <sch:InquiryResponseHeader>    <sch:CustomerCode>SWAM</sch:CustomerCode>    <sch:ClientID>999AA00007</sch:ClientID>    <sch:CustRefField/>    <sch:ReportOrderNO>18250562</sch:ReportOrderNO>    <sch:ProductCode>VID</sch:ProductCode>    <sch:SuccessCode>1</sch:SuccessCode>    <sch:Date>23-02-2017</sch:Date>    <sch:Time>09:54:23</sch:Time>  </sch:InquiryResponseHeader>  <sch:InquiryRequestInfo>    <sch:InquiryPurpose>V1</sch:InquiryPurpose>    <sch:FirstName>NEERAJ  KUMAR</sch:FirstName>    <sch:LastName>SHARMA</sch:LastName>    <sch:PANId>ATCPS9170Q</sch:PANId>  </sch:InquiryRequestInfo>  <sch:ReportData>    <sch:VerifyIDResponse>      <sch:vidNsdlResponses>        <sch:nsdlRequest>          <sch:source>Inquiry</sch:source>          <sch:PANNumber>ATCPS9170Q</sch:PANNumber>        </sch:nsdlRequest>        <sch:nsdlResponse>          <sch:returnCode>E</sch:returnCode>          <sch:returnCodeDesc>Existing and Valid PAN</sch:returnCodeDesc>          <sch:nsdlRespId>225932</sch:nsdlRespId>          <sch:PAN>ATCPS9170Q</sch:PAN>          <sch:percentMatch>VERY HIGH</sch:percentMatch>          <sch:title>Shri</sch:title>          <sch:firstName>NIRAJ</sch:firstName>          <sch:lastName>SHARMA</sch:lastName>          <sch:lastUpdatedDate>30/04/2015</sch:lastUpdatedDate>        </sch:nsdlResponse>      </sch:vidNsdlResponses>    </sch:VerifyIDResponse>    <sch:Disclaimer>Contact Us:			Phone: 1800 209 3247  Fax: +91-22-6112-7950			Email: ecissupport@equifaxindia.com			This report and information contained herein is to be used subject to the prevalent laws/court orders 			and in compliance with the Membership agreement/terms and conditions entered into between the members/specified users 			and Equifax Credit Information Services Private Limited (ECIS). PAN and Aadhar information authenticated through the 			system (Verify ID) is provided on an 'as is' basis by various agencies such as 'NSDL','UIDAI' which are not controlled 			by ECIS. The voter ID information provided herein is  on an 'as is' transliterated basis from publically available PDF 			Electoral Rolls as maintained by Election Commission of India through various third parties which are not controlled 			by ECIS. ECIS does not guarantee the timeliness, correctness or completeness of the information contained therein, 			except as explicitly stated in the Membership agreement/terms and conditions of ECIS.</sch:Disclaimer>  </sch:ReportData></sch:InquiryResponse></soapenv:Body></soapenv:Envelope>");
				}
				equifaxdetails.setRequest_xml(requestxml);
				equifaxdetails.setResponse_xml(out1.toString());
				equifaxdetails.setResponse_header_info_json(header.toString());
				equifaxdetails.setTracker_id(header.getCorrelationId());


				logger.info("Equifax Output: " + out1);
			}
			catch (Exception ec) 
			{
				logger.error("EquifaxVIDServiceImpl || processEquifaxVIDRequest() || Error at equifax vid" + ec);
				errorInfo.setCode("503");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("The Out put of Equifax VID is not in Proper format ");
				errorInfo.setDescription(
				"Error while getting Output of Equifax VID ,Please consult I.T Department or Check your Request Parameter");
				equifaxApiResponse.setErrorInfo(errorInfo);
				return equifaxApiResponse;
			}
			try 
			{
				o = org.json.XML.toJSONObject(out1.toString());
				logger.info("=======      " + out1.toString());
				responseJson = o.toString().replaceAll("soapenv:", "")
				.replaceFirst(",\"xmlns:soapenv\":\"http://schemas.xmlsoap.org/soap/envelope/\"", "")
				.replaceFirst("\"xmlns:sch\":\"http://services.equifax.com/eport/ws/schemas/1.0\",", "");
				ParsedJson = responseJson.replaceAll("sch:", "");
				logger.info(">>>>>>>> IN JSON format : " + ParsedJson);
				JSONObject parsedjsonObject = new JSONObject(ParsedJson);
				lowerkeyjson = recursiveJsonKeyConverterToLower(parsedjsonObject);
				try
				{
					JSONObject reportedData = lowerkeyjson.getJSONObject("envelope").getJSONObject("body")
					.getJSONObject("inquiryresponse").getJSONObject("reportdata");
					if(reportedData!=null){
						try{
							JSONObject jsonErrorObj=lowerkeyjson.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata");
							if(jsonErrorObj.has("error")){
								String errorCode=jsonErrorObj.getJSONObject("error").get("errorcode").toString();
								String errorMsg=jsonErrorObj.getJSONObject("error").get("errormsg").toString();
								errorInfo.setCode(errorCode);
								errorInfo.setStatus(ERRORSTATUS.FAILURE);
								errorInfo.setMessage(errorMsg);
								errorInfo.setDescription(errorMsg);
								equifaxApiResponse.setErrorInfo(errorInfo);
								logger.error("Error  response msg:  "+errorMsg);
								return equifaxApiResponse;
							}
						}catch(Exception ee){
							logger.error("Error  in parsing jsonObj:  "+ee);
						}
					}
					JSONObject vidNsdlResponses = reportedData.getJSONObject("verifyidresponse")
					.getJSONObject("vidnsdlresponses");
					try 
					{
						JSONObject nsdlrequest = vidNsdlResponses.getJSONObject("nsdlrequest");
						Iterator<String> keyitr = nsdlrequest.keys();
						keyitr.next();
						String identification = nsdlrequest.get(keyitr.next()) + "";
						equifaxdetails.setIdentitification_id(identification);
					} 
					catch (Exception ec)
					{
						logger.error("There is error while saving identification data");
					}

					JSONObject nsdlResponse = vidNsdlResponses.getJSONObject("nsdlresponse");
					try
					{
						equifaxdetails.setTitle(nsdlResponse.get("title").toString());
					}
					catch(Exception ec)
					{
						logger.info("There is Error in title"+ec);
					}
					try
					{
						equifaxdetails.setLast_updated_date(nsdlResponse.get("lastupdateddate").toString());
					}
					catch(Exception ec)
					{
						logger.info("There is Error in lastupdateddate"+ec);
					}
					try
					{
						equifaxdetails.setNsdl_response_id(nsdlResponse.get("nsdlrespid").toString());
					}
					catch(Exception ec)
					{
						logger.info("There is error in nsdlresipd"+ec);
					}
					try
					{
						equifaxdetails.setLast_name(nsdlResponse.get("lastname").toString());
					}
					catch(Exception ec)
					{
						logger.info("There is error in lastname"+ec);
					}
					try
					{
						equifaxdetails.setReturn_code(nsdlResponse.get("returncode").toString());
					}
					catch(Exception  ec)
					{
						logger.info("There is error in returncode"+ec);
					}
					try
					{
						equifaxdetails.setPercentage_match(nsdlResponse.get("percentmatch").toString());
					}
					catch(Exception  ec)
					{
						logger.info("There is error in percentmatch"+ec);
					}
					try {
						equifaxdetails.setReturn_code_description(nsdlResponse.get("returncodedesc").toString());
					} catch (Exception ec) {
						logger.info("There is error in returncodedesc"+ec);
					}
					try
					{
						equifaxdetails.setFirst_name(nsdlResponse.get("firstname").toString());
					}
					catch (Exception ec) {
						logger.info("There is error in firstname"+ec);
					}
					try
					{
						equifaxdetails.setDisclaimer(reportedData.get("disclaimer").toString());
					}
					catch(Exception ec)
					{
						logger.info("There is error in disclaimer"+ec);	
					}
					try
					{
					JSONObject InquiryResponse = lowerkeyjson.getJSONObject("envelope").getJSONObject("body")
					.getJSONObject("inquiryresponse");
					JSONObject InquiryResponseHeader = InquiryResponse.getJSONObject("inquiryresponseheader");
					equifaxdetails.setResponse_time(InquiryResponseHeader.get("time").toString());
					equifaxdetails.setResponse_date(InquiryResponseHeader.get("date").toString());
					equifaxdetails.setResponse_order_no(InquiryResponseHeader.get("reportorderno").toString());
					equifaxdetails.setRequest_info_json(equifaxApiRequest.toString());
					equifaxdetails.setResponse_error_info_json(errorInfo.toString()); // errorInfo
					}
					catch(Exception ec)
					{
						logger.info("There is error in inquiryresponse"+ec);
					}
						
				}
				catch (Exception ec)
				{
					equifaxdetails.setRemarks("ERROR" + ec);
				}

				try 
				{
					map = mapper.readValue(lowerkeyjson.toString(), Map.class);
					mapResponseHeader = ((Map) ((Map) ((Map) ((Map) map.get("envelope")).get("body")).get("inquiryresponse")).get("inquiryresponseheader"));
					mapResponseHeader.remove("customercode");
					mapResponseHeader.remove("clientid");
					mapResponseHeader.remove("productcode");
					mapResponseHeader.remove("successcode");
					map = ((Map) ((Map) ((Map) ((Map) map.get("envelope")).get("body")).get("inquiryresponse"))
						.get("reportdata"));
					map.put("inquiryresponseheader", mapResponseHeader);
					
					try 
					{
					/////// 	CODE TO GENERATE PDF  FROM DATA     											///////////////// 
						Gson json=new Gson();
						//Code For Byte Array Starts
						String tempRequestJson;
						ObjectMapper  omapper=new ObjectMapper();
						String requestjson=omapper.writeValueAsString(equifaxApiRequest);//apiRequest
						/*end*/
						ConvertUrltoByteArray   converturltobytearray=new ConvertUrltoByteArray();
						String url_temp=resProp.getString("com.qc.liveURL") +"/equifaxUserFriendlyOutPutForVID";
						tempRequestJson=requestjson;
						//Added By Rohit Kumar  for  convert two hit into single hit on 8 August 2017 Starts
						equifaxresponsepayload.setTransaction(map);
						equifaxApiResponse.setPayload(equifaxresponsepayload);
						String responseJsontemporarToCreatePdf=json.toJson(equifaxApiResponse);
						String htmlData=converturltobytearray.getingByteArray1(url_temp,tempRequestJson,"requestJson", responseJsontemporarToCreatePdf, "responseFromService","","");
						//String htmlData = converturltobytearray.getingByteArray1(url_temp, tempRequestJson, "requestJson",responseJsontemporarToCreatePdf, "responseFromService");
						//Added By Rohit Kumar  for  convert two hit into single hit on 8 August 2017 Ends 
						
						String pdfByteArr=equifaxUtil.convertHtmlToPDF(htmlData,"EquifaxReportVID",equifaxresponsepayload);
						map.put("pdfByteArray", pdfByteArr);
						equifaxdetails.setHtmlData(htmlData);
						equifaxdetails.setPdf_byte_Array(pdfByteArr);
						equifaxdetails.setRequest_json(requestjson);
						equifaxdetails.setResponse_json(responseJsontemporarToCreatePdf);
						
						//Code For Byte Arrray Ends
						///////          							CODE TO GENERATE PDF  FROM DATA     											/////////////////
					}
					catch (Exception e)
					{
						logger.info("EquifaxVIDServiceImpl || processEquifaxVIDRequest() || Error while generating PDF"+e);
					}
				} 
				catch (Exception ec)
				{
					ec.printStackTrace();
					logger.error("EquifaxVIDServiceImpl || processEquifaxVIDRequest() ||  Error in equifax vid while converting it into equivalent Response " + ec);
					errorInfo.setCode("504");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage("The Out put of Equifax VID is not in Proper format ");
					errorInfo.setDescription(
					"Error while getting Output of Equifax VID ,Please consult I.T Department or Check your Request Parameter");
					equifaxApiResponse.setErrorInfo(errorInfo);
					return equifaxApiResponse;
				}
			}
			catch (JSONException e)
			{
				logger.error("EquifaxVIDServiceImpl || processEquifaxVIDRequest() || Error in equifax vid while converting it into equivalent Response " + e);
				errorInfo.setCode("504");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("The Out put of Equifax VID is not in Proper format ");
				errorInfo.setDescription(
				"Error while getting Output of Equifax VID ,Please consult I.T Department or Check your Request Parameter");
				equifaxApiResponse.setErrorInfo(errorInfo);
				return equifaxApiResponse;
			}
			errorInfo.setCode("200");
			errorInfo.setStatus(ERRORSTATUS.SUCCESS);
			errorInfo.setMessage("Success");
			errorInfo.setDescription("Response Generated Successfully");
			equifaxresponsepayload.setTransaction(map);
			equifaxApiResponse.setErrorInfo(errorInfo);
			equifaxApiResponse.setHeader(header);
			equifaxApiResponse.setPayload(equifaxresponsepayload);
		} 
		finally
		{
			new Thread(new Runnable() {
				public void run() {
					runningBackgroundProcessforVID(equifaxdetails);
				}
			}).start();

		}
		return equifaxApiResponse;
	}
	protected void runningBackgroundProcessforVID(EquifaxVidAllDetails equifaxdetails) {
		int result=0;
		
		try 
		{
			equifaxdetails.setCreated_time(new Date());
			equifaxdetails.setRemarks("success");
			equifaxdetails.setUpdated_time(new Date());
			result = dbEquifax.insertEquiFaxVID(equifaxdetails);
		
		} 
		catch (Exception ec)
		{
			logger.error("EquifaxVIDServiceImpl || runningBackgroundProcessforVID() ||  error while copying the data from one property to other" + ec);
		}
		logger.info("Inserted records are : "+result);
	}


	private JSONObject recursiveJsonKeyConverterToLower(JSONObject jsonObject)
	{
		JSONObject resultJsonObject = new JSONObject();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) 
		{
			String key = keys.next();
			Object value = null;
			try 
			{
				JSONObject nestedJsonObject = jsonObject.getJSONObject(key);
				value = this.recursiveJsonKeyConverterToLower(nestedJsonObject);
			}
			catch (JSONException jsonException)
			{
				try
				{
					JSONArray nestedJsonArray = jsonObject.getJSONArray(key);
					JSONArray resultJsonArray = new JSONArray();
					for (int i = 0; i < nestedJsonArray.length(); i++) {
						JSONObject resultJsonObjectForArray = nestedJsonArray.getJSONObject(i);
						value = this.recursiveJsonKeyConverterToLower(resultJsonObjectForArray);
						resultJsonArray.put(value);
					}
					value = resultJsonArray;
				} 
				catch (JSONException jsonExceptionInner) 
				{
					try 
					{
						value = jsonObject.get(key);
					}
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
				}
			}
			try
			{
				resultJsonObject.put(key.replaceAll("-", "").replace("-", "").toLowerCase(), value);
			} 
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}

		return resultJsonObject;
	}
	private String httpCall(String requestxml, ErrorInfo errorInfo) {
			HttpURLConnection conn = null;
			String responseXml="";
			String DevMode = resProp.getString("spring.enable.proxy.development");
			URL url;
			try
			{
				url = new URL(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.url"));
			
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
				try
				{
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "text/xml");
					conn.setRequestProperty("SOAPAction","http://services.equifax.com/CreditReportWS/CreditReportWSInquiry/1.0");
					conn.setConnectTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
					conn.setReadTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
					conn.setDoOutput(true);
					conn.setDoInput(true);
					OutputStream out = conn.getOutputStream();
					out.write(requestxml.getBytes());
					out.flush();
					
					
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					String output;
					while ((output = br.readLine()) != null)
					{
						logger.info(output);
						if (output != null)
							responseXml+=output;
					}
				}
				catch (Exception ec)
				{
					logger.error("EquifaxVIDServiceImpl || httpCall() || Error while creating connection " + ec);
					errorInfo.setCode("601");
					errorInfo.setStatus(ERRORSTATUS.FAILURE);
					errorInfo.setMessage("Error while creating connection with equifax ");
					errorInfo.setDescription("Error while creating connection with equifax");
					//equifaxApiResponse.setErrorInfo(errorInfo);
					//return equifaxApiResponse;
				}
			}
			catch (Exception ec)
			{
				logger.error("EquifaxVIDServiceImpl || httpCall() || Error getting Response from VID" + ec);
				errorInfo.setCode("502");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Invaild request Parameter ");
				errorInfo.setDescription("Error while creating xml for Equifax for VID ,Please consult I.T Department or Check your Request Parameter");
				//equifaxApiResponse.setErrorInfo(errorInfo);
				//return equifaxApiResponse;
			}
			finally
			{
				conn.disconnect();
			}
			
			return responseXml;
		}

public String responseXmlToObject(StringBuilder responseXml, EquifaxVidAllDetails equifaxdetails, ErrorInfo errorInfo) {
	JSONObject lowerkeyjson=new JSONObject();
	try {
		
		
		JSONObject o = org.json.XML.toJSONObject(responseXml.toString());
		logger.info("=======      " + responseXml.toString());
		String responseJson = o.toString().replaceAll("soapenv:", "")
		.replaceFirst(",\"xmlns:soapenv\":\"http://schemas.xmlsoap.org/soap/envelope/\"", "")
		.replaceFirst("\"xmlns:sch\":\"http://services.equifax.com/eport/ws/schemas/1.0\",", "");
		String ParsedJson = responseJson.replaceAll("sch:", "");
		logger.info(">>>>>>>> IN JSON format : " + ParsedJson);
		JSONObject parsedjsonObject = new JSONObject(ParsedJson);
		lowerkeyjson = recursiveJsonKeyConverterToLower(parsedjsonObject);
		try
		{
			JSONObject reportedData = lowerkeyjson.getJSONObject("envelope").getJSONObject("body")
			.getJSONObject("inquiryresponse").getJSONObject("reportdata");
			if(reportedData!=null){
				try{
					JSONObject jsonErrorObj=lowerkeyjson.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata");
					if(jsonErrorObj.has("error")){
						String errorCode=jsonErrorObj.getJSONObject("error").get("errorcode").toString();
						String errorMsg=jsonErrorObj.getJSONObject("error").get("errormsg").toString();
						errorInfo.setCode(errorCode);
						errorInfo.setStatus(ERRORSTATUS.FAILURE);
						errorInfo.setMessage(errorMsg);
						errorInfo.setDescription(errorMsg);
						//equifaxApiResponse.setErrorInfo(errorInfo);
						logger.error("Error  response msg:  "+errorMsg);
						return "";
					}
				}catch(Exception ee){
					logger.error("EquifaxVIDServiceImpl || responseXmlToObject() || Error in parsing jsonObj:  "+ee);
				}
			}
			JSONObject vidNsdlResponses = reportedData.getJSONObject("verifyidresponse")
			.getJSONObject("vidnsdlresponses");
			try 
			{
				JSONObject nsdlrequest = vidNsdlResponses.getJSONObject("nsdlrequest");
				Iterator<String> keyitr = nsdlrequest.keys();
				keyitr.next();
				String identification = nsdlrequest.get(keyitr.next()) + "";
				equifaxdetails.setIdentitification_id(identification);
			} 
			catch (Exception ec)
			{
				logger.error("quifaxVIDServiceImpl || responseXmlToObject() || There is error while saving identification data");
			}
	
			JSONObject nsdlResponse = vidNsdlResponses.getJSONObject("nsdlresponse");
			try
			{
				equifaxdetails.setTitle(nsdlResponse.get("title").toString());
			}
			catch(Exception ec)
			{
				logger.info("There is Error in title"+ec);
			}
			try
			{
				equifaxdetails.setLast_updated_date(nsdlResponse.get("lastupdateddate").toString());
			}
			catch(Exception ec)
			{
				logger.info("There is Error in lastupdateddate"+ec);
			}
			try
			{
				equifaxdetails.setNsdl_response_id(nsdlResponse.get("nsdlrespid").toString());
			}
			catch(Exception ec)
			{
				logger.info("There is error in nsdlresipd"+ec);
			}
			try
			{
				equifaxdetails.setLast_name(nsdlResponse.get("lastname").toString());
			}
			catch(Exception ec)
			{
				logger.info("There is error in lastname"+ec);
			}
			try
			{
				equifaxdetails.setReturn_code(nsdlResponse.get("returncode").toString());
			}
			catch(Exception  ec)
			{
				logger.info("There is error in returncode"+ec);
			}
			try
			{
				equifaxdetails.setPercentage_match(nsdlResponse.get("percentmatch").toString());
			}
			catch(Exception  ec)
			{
				logger.info("There is error in percentmatch"+ec);
			}
			try {
				equifaxdetails.setReturn_code_description(nsdlResponse.get("returncodedesc").toString());
			} catch (Exception ec) {
				logger.info("There is error in returncodedesc"+ec);
			}
			try
			{
				equifaxdetails.setFirst_name(nsdlResponse.get("firstname").toString());
			}
			catch (Exception ec) {
				logger.info("There is error in firstname"+ec);
			}
			try
			{
				equifaxdetails.setDisclaimer(reportedData.get("disclaimer").toString());
			}
			catch(Exception ec)
			{
				logger.info("There is error in disclaimer"+ec);	
			}
			try
			{
			JSONObject InquiryResponse = lowerkeyjson.getJSONObject("envelope").getJSONObject("body")
			.getJSONObject("inquiryresponse");
			JSONObject InquiryResponseHeader = InquiryResponse.getJSONObject("inquiryresponseheader");
			equifaxdetails.setResponse_time(InquiryResponseHeader.get("time").toString());
			equifaxdetails.setResponse_date(InquiryResponseHeader.get("date").toString());
			equifaxdetails.setResponse_order_no(InquiryResponseHeader.get("reportorderno").toString());
			
			equifaxdetails.setResponse_error_info_json(errorInfo.toString()); // errorInfo
			}
			catch(Exception ec)
			{
				logger.error("There is error in inquiryresponse"+ec);
			}
				
		}
		catch (Exception ec)
		{
			equifaxdetails.setRemarks("ERROR" + ec);
		}
	} catch (Exception e) {
		logger.error("quifaxVIDServiceImpl || responseXmlToObject() || Error in xml to json conversion : "+e);
	}
	
	return lowerkeyjson.toString();
}
}