package com.qualtech.equifax.api.service.impl;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
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
import com.qualtech.cibil.api.utils.ConvertUrltoByteArray;
import com.qualtech.equifax.api.common.dto.ERRORSTATUS;
import com.qualtech.equifax.api.common.dto.EquifaxEVDRTrackerDTO;
import com.qualtech.equifax.api.common.dto.ErrorInfo;
import com.qualtech.equifax.api.db.DBEquifax;
import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;
import com.qualtech.equifax.api.interfaces.EquifaxUtillInt;
import com.qualtech.equifax.api.request.EquifaxAPI_EVDRRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;
import com.qualtech.equifax.api.response.EquifaxApiResponseHeader;
import com.qualtech.equifax.api.response.EquifaxCriffResponsePayload;
import com.qualtech.equifax.api.service.EquifaxEVDRService;
import com.qualtech.equifax.api.utils.ConvertResponseJSONtoEVDREntitiesInt;
import com.qualtech.equifax.api.utils.EquifaxUtilInt;
import com.qualtech.equifax.api.utils.XTrustProvider;
@Service
public class EquifaxEVDRServiceImpl implements EquifaxEVDRService
{
	private static Logger logger = Logger.getLogger(EquifaxEVDRServiceImpl.class);
	@Autowired
	private  PropertyFile resProp;
	@Autowired
	private EquifaxUtilInt util;
	@Autowired
	private EquifaxUtillInt utill;
	
	@Autowired
	private DBEquifax dbConnectionn;
	@Autowired
	private ConvertResponseJSONtoEVDREntitiesInt jSONtoEVDREntities;
	@SuppressWarnings({ "static-access", "unused", "unchecked", "rawtypes" })
	@Override
	public EquifaxApiResponse processEquifaxVIDRequest(EquifaxAPI_EVDRRequest equifaxApiRequest) {
		EquifaxApiResponse equifaxApiResponse = new EquifaxApiResponse();
		JSONObject lowerkeyjson = new JSONObject();
		final EquifaxEVDRTrackerDTO equifaxevdrtracker = new EquifaxEVDRTrackerDTO();
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
				logger.error("EquifaxEVDRServiceImpl || processEquifaxVIDRequest() || Exception setting header in response");
			}
			equifaxApiResponse.setHeader(header);
			XTrustProvider xTrustProvider =new XTrustProvider();
			xTrustProvider.install();
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
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "text/xml");
				conn.setRequestProperty("SOAPAction",
				"http://services.equifax.com/CreditReportWS/CreditReportWSInquiry/1.0");
				conn.setConnectTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
				conn.setReadTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Timeout")));
				conn.setDoOutput(true);
				conn.setDoInput(true);
				//OutputStream out = conn.getOutputStream();
				String requestxml = utill.requestxmlForEVDR(equifaxApiRequest);
				equifaxevdrtracker.setRequestXML(requestxml);
				/*out.write(requestxml.getBytes());
				out.flush();*/

			}
			catch (Exception ec)
			{
				logger.error("EquifaxEVDRServiceImpl || processEquifaxVIDRequest() || getting Response from EVDR" + ec);
				errorInfo.setCode("502");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("Invaild request Parameter ");
				errorInfo.setDescription(
				"Error while creating xml for Equifax for EVDR ,Please consult I.T Department or Check your Request Parameter");
				equifaxApiResponse.setErrorInfo(errorInfo);
				return equifaxApiResponse;
			} 
			finally 
			{
				conn.disconnect();
			}
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = null;
			Map<String, Object> mapResponseHeader = null;
			String output;
			StringBuilder out1 = new StringBuilder();
			JSONObject o;
			String responseJson;
			String ParsedJson;

			try 
			{
				/*BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null)
				{
					logger.info(output);
					if (output != null)
						out1.append(output);
				}*/
				logger.info("Equifax Output: " + out1);

				String demo = resProp.getString("spring.enable.EVDR.demo.development");
				if ("Y".equals(demo))
				{
					out1 = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><sch:InquiryResponse xmlns:sch=\"http://services.equifax.com/eport/ws/schemas/1.0\">  <sch:InquiryResponseHeader>    <sch:CustomerCode>SWAM</sch:CustomerCode>    <sch:ClientID>999AA00007</sch:ClientID>    <sch:CustRefField/>    <sch:ReportOrderNO>18253960</sch:ReportOrderNO>    <sch:ProductCode>EVDR</sch:ProductCode>    <sch:SuccessCode>1</sch:SuccessCode>    <sch:Date>27-02-2017</sch:Date>    <sch:Time>12:10:19</sch:Time>    <sch:HitCode>11</sch:HitCode>  </sch:InquiryResponseHeader>  <sch:InquiryRequestInfo>    <sch:InquiryPurpose>01</sch:InquiryPurpose>    <sch:TransactionAmount>750000.0</sch:TransactionAmount>    <sch:FirstName>NEERAJ  KUMAR</sch:FirstName>    <sch:LastName>SHARMA</sch:LastName>    <sch:AddrLine1>HOUSE NO 296 WARD  NO 8 NIWALI ROAD  SENDHWA BADWANI DIST  MADHYA PRADESH</sch:AddrLine1>    <sch:State>MP</sch:State>    <sch:Postal>451666</sch:Postal>    <sch:InquiryAddresses>      <sch:InquiryAddress seq=\"1\">        <sch:AddressLine>HOUSE NO 296 WARD  NO 8 NIWALI ROAD  SENDHWA BADWANI DIST  MADHYA PRADESH</sch:AddressLine>        <sch:State>MP</sch:State>        <sch:Postal>451666</sch:Postal>      </sch:InquiryAddress>    </sch:InquiryAddresses>    <sch:InquiryPhones>      <sch:InquiryPhone seq=\"1\">        <sch:Number>9893688111</sch:Number>        <sch:PhoneType>H</sch:PhoneType>      </sch:InquiryPhone>      <sch:InquiryPhone seq=\"2\">        <sch:Number>9893688111</sch:Number>        <sch:PhoneType>M</sch:PhoneType>      </sch:InquiryPhone>    </sch:InquiryPhones>    <sch:DOB>1977-02-11</sch:DOB>    <sch:PANId>ATCPS9170Q</sch:PANId>    <sch:HomePhone seq=\"1\">      <sch:PhoneNumber>9893688111</sch:PhoneNumber>    </sch:HomePhone>    <sch:MobilePhone>9893688111</sch:MobilePhone>  </sch:InquiryRequestInfo>  <sch:ReportData>    <sch:VerifyIDResponse>      <sch:vidNsdlResponses>        <sch:nsdlRequest>          <sch:source>Inquiry</sch:source>          <sch:PANNumber>ATCPS9170Q</sch:PANNumber>        </sch:nsdlRequest>        <sch:nsdlResponse>          <sch:returnCode>E</sch:returnCode>          <sch:returnCodeDesc>Existing and Valid PAN</sch:returnCodeDesc>          <sch:nsdlRespId>0</sch:nsdlRespId>          <sch:PAN>ATCPS9170Q</sch:PAN>          <sch:percentMatch>VERY HIGH</sch:percentMatch>          <sch:PANStatus>Success</sch:PANStatus>          <sch:firstName>NIRAJ</sch:firstName>          <sch:lastName>SHARMA</sch:lastName>          <sch:lastUpdatedDate>30/04/2015</sch:lastUpdatedDate>        </sch:nsdlResponse>      </sch:vidNsdlResponses>      <sch:vidVoterResponses>        <sch:voterRequest>          <sch:source>Bureau</sch:source>          <sch:voterId>MFX1106145</sch:voterId>        </sch:voterRequest>        <sch:voterResponse>          <sch:returnCode>E</sch:returnCode>          <sch:Voter>MFX1106145</sch:Voter>          <sch:title>Voter ID could not be found</sch:title>        </sch:voterResponse>      </sch:vidVoterResponses>    </sch:VerifyIDResponse>    <sch:IDAndContactInfo>      <sch:PersonalInfo>        <sch:Name>          <sch:FirstName>NEERAJ</sch:FirstName>          <sch:MiddleName>KUMAR</sch:MiddleName>          <sch:LastName>SHARMA</sch:LastName>        </sch:Name>        <sch:DateOfBirth>1977-02-11</sch:DateOfBirth>        <sch:Gender>Male</sch:Gender>        <sch:Age>          <sch:Age>40</sch:Age>        </sch:Age>        <sch:Occupation/>      </sch:PersonalInfo>      <sch:IdentityInfo>        <sch:PANId seq=\"1\" ReportedDate=\"2010-06-30\">          <sch:IdNumber>ATCPS9170Q</sch:IdNumber>        </sch:PANId>        <sch:VoterID seq=\"1\" ReportedDate=\"2011-05-31\">          <sch:IdNumber>MFX1106145</sch:IdNumber>        </sch:VoterID>      </sch:IdentityInfo>      <sch:AddressInfo seq=\"1\" ReportedDate=\"2014-08-31\">        <sch:Address>NIWALI ROAD SENDHWA BARWANI</sch:Address>        <sch:State>MP</sch:State>        <sch:Postal>451666</sch:Postal>        <sch:Type>Primary</sch:Type>      </sch:AddressInfo>      <sch:AddressInfo seq=\"2\" ReportedDate=\"2014-08-31\">        <sch:Address>HOUSE NO 296 WARD,NO 8 NIWALI ROAD,SENDHWA BARWANI</sch:Address>        <sch:State>MP</sch:State>        <sch:Postal>451666</sch:Postal>        <sch:Type>N/A</sch:Type>      </sch:AddressInfo>      <sch:AddressInfo seq=\"3\" ReportedDate=\"2012-06-30\">        <sch:Address>HOUSE NO 296 WARD,NO 8 NIWALI ROAD,BARWANI</sch:Address>        <sch:State>MP</sch:State>        <sch:Postal>451666</sch:Postal>        <sch:Type>Permanent</sch:Type>      </sch:AddressInfo>      <sch:AddressInfo seq=\"4\" ReportedDate=\"2010-11-30\">        <sch:Address>NIWALI ROAD SENDHWA SENDHWA</sch:Address>        <sch:State>MP</sch:State>        <sch:Postal>451666</sch:Postal>        <sch:Type>N/A</sch:Type>      </sch:AddressInfo>      <sch:AddressInfo seq=\"5\" ReportedDate=\"2010-10-31\">        <sch:Address>NIWALI ROAD SENDHWA SENDHWA BADWANI DIST,MADHYA PRADESH SENDHWA</sch:Address>        <sch:State>MP</sch:State>        <sch:Postal>451666</sch:Postal>        <sch:Type>N/A</sch:Type>      </sch:AddressInfo>      <sch:PhoneInfo typeCode=\"H\" seq=\"1\" ReportedDate=\"2011-04-30\">        <sch:Number>93688111</sch:Number>      </sch:PhoneInfo>      <sch:PhoneInfo typeCode=\"M\" seq=\"1\" ReportedDate=\"2014-08-31\">        <sch:Number>9893688111</sch:Number>      </sch:PhoneInfo>    </sch:IDAndContactInfo>    <sch:Disclaimer>Contact Us:			Phone: 1800 209 3247  Fax: +91-22-6112-7950			Email: ecissupport@equifaxindia.com			This report and information contained herein is to be used subject to the prevalent laws/court orders 			and in compliance with the Membership agreement/terms and conditions entered into between the members/specified users 			and Equifax Credit Information Services Private Limited (ECIS). PAN and Aadhar information authenticated through the 			system (Verify ID) is provided on an 'as is' basis by various agencies such as 'NSDL','UIDAI' which are not controlled 			by ECIS. The voter ID information provided herein is  on an 'as is' transliterated basis from publically available PDF 			Electoral Rolls as maintained by Election Commission of India through various third parties which are not controlled 			by ECIS. ECIS does not guarantee the timeliness, correctness or completeness of the information contained therein, 			except as explicitly stated in the Membership agreement/terms and conditions of ECIS.</sch:Disclaimer>  </sch:ReportData></sch:InquiryResponse></soapenv:Body></soapenv:Envelope>");
				}

				equifaxevdrtracker.setResponseXML(out1.toString());
			} 
			catch (Exception ec) 
			{
				logger.error("EquifaxEVDRServiceImpl || processEquifaxVIDRequest() || There is Error at equifax EVDR" + ec);
				errorInfo.setCode("503");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("The Out put of Equifax VID is not in Proper format ");
				errorInfo.setDescription(
				"Error while getting Output of Equifax EVDR ,Please consult I.T Department or Check your Request Parameter");
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
				
				JSONObject reportedData = lowerkeyjson.getJSONObject("envelope").getJSONObject("body").getJSONObject("inquiryresponse").getJSONObject("reportdata");
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
			}
			catch (Exception ec) 
			{
				logger.error("EquifaxEVDRServiceImpl || processEquifaxVIDRequest() || Error at equifax EVDR while converting it into equivalent Response " + ec);
				errorInfo.setCode("505");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("The Out put of Equifax EVDR is not in Proper format ");
				errorInfo.setDescription(
				"Error while getting Output of Equifax EVDR ,Please consult I.T Department or Check your Request Parameter");
				equifaxApiResponse.setErrorInfo(errorInfo);
				return equifaxApiResponse;
			}
			try
			{
				map = mapper.readValue(lowerkeyjson.toString(), Map.class);
				EquifaxCriffResponsePayload equifaxresponsepayload = new EquifaxCriffResponsePayload();
				errorInfo.setCode("200");
				errorInfo.setStatus(ERRORSTATUS.SUCCESS);
				errorInfo.setMessage("Success");
				errorInfo.setDescription("Response Generated Successfully");
				Map<String, Object> transactionMap = ((Map) ((Map) ((Map) map.get("envelope")).get("body"))
						.get("inquiryresponse"));
				
				
				
				try 
				{
					///////          							CODE TO GENERATE PDF  FROM DATA     											///////////////// 
					Gson json=new Gson();
					//Code For Byte Array Starts
					String tempRequestJson;
					ObjectMapper  omapper=new ObjectMapper();
					String requestjson=omapper.writeValueAsString(equifaxApiRequest);//apiRequest
					/*String requestjson=apiRequest.toString();*/
					ConvertUrltoByteArray   converturltobytearray=new ConvertUrltoByteArray();
					String url_temp=resProp.getString("com.qc.liveURL") +"/equifaxuserfriendlyoutputforEVDR";
					tempRequestJson=requestjson;
					//Added By Rohit Kumar on 19-August-2017 Starts
					equifaxresponsepayload.setTransaction(transactionMap);
					equifaxApiResponse.setPayload(equifaxresponsepayload);
					String responseJsontemporarToCreatePdf=json.toJson(equifaxApiResponse);
					String htmlData=converturltobytearray.getingByteArray1(url_temp,tempRequestJson,"requestJson", responseJsontemporarToCreatePdf, "responseFromService");
					//Added By Rohit Kumar on 19-August-2017 Ends
					String pdfByteArr=util.convertHtmlToPDF(htmlData,"EquifaxReportEVDR",equifaxresponsepayload);
					transactionMap.put("pdfByteArray", pdfByteArr);
					equifaxevdrtracker.setRequest_json(requestjson);
					equifaxevdrtracker.setResponse_json(responseJsontemporarToCreatePdf);
					equifaxevdrtracker.setHtmlData(htmlData);
					equifaxevdrtracker.setPdf_byte_Array(pdfByteArr);
					//Code For Byte Arrray Ends
					///////          							CODE TO GENERATE PDF  FROM DATA     											/////////////////
				}
				catch (Exception e)
				{
					logger.info("EquifaxEVDRServiceImpl || processEquifaxVIDRequest() || Error while generating PDF"+e);
				}
				
				
				
				equifaxresponsepayload.setTransaction(transactionMap);
				equifaxApiResponse.setErrorInfo(errorInfo);
				equifaxApiResponse.setHeader(header);
				equifaxApiResponse.setPayload(equifaxresponsepayload);
			} 
			catch (Exception ec)
			{

				logger.error("EquifaxEVDRServiceImpl || processEquifaxVIDRequest() || There is Error at equifax EVDR while converting it into equivalent Response " + ec);
				errorInfo.setCode("504");
				errorInfo.setStatus(ERRORSTATUS.FAILURE);
				errorInfo.setMessage("The Out put of Equifax EVDR is not in Proper format ");
				errorInfo.setDescription(
				"Error while getting Output of Equifax EVDR ,Please consult I.T Department or Check your Request Parameter");
				equifaxApiResponse.setErrorInfo(errorInfo);
				return equifaxApiResponse;
			}

		}
		catch (Exception ec)
		{
			logger.error("EquifaxEVDRServiceImpl || processEquifaxVIDRequest() || getting Response from EVDR" + ec);
		} 
		finally 
		{
			final EquifaxApiResponse equifaxAPIResponse = equifaxApiResponse;
			final EquifaxAPI_EVDRRequest equifaxAPIRequest = equifaxApiRequest;
			final String lowerjson = lowerkeyjson.toString();
			new Thread(new Runnable() {
				@Override
				public void run() 
				{
					saveEVDRDatainDatabase(lowerjson,
							equifaxAPIResponse,
							equifaxAPIRequest,
							equifaxevdrtracker);
				}
			}).start();

		}
		return equifaxApiResponse;
	}

	private void saveEVDRDatainDatabase(String lowerkeyjson,
			EquifaxApiResponse equifaxApiResponse,
			EquifaxAPI_EVDRRequest equifaxevdrApiRequest,
			EquifaxEVDRTrackerDTO equifaxevdrtracker) 
	{
		EquifaxEvdrAllDetails equifaxevdrdetails_logs = jSONtoEVDREntities.equifaxEvdrdetailLogs(
				lowerkeyjson,
				equifaxApiResponse,
				equifaxevdrApiRequest,
				equifaxevdrtracker);
		equifaxevdrdetails_logs.setTracker_id(equifaxevdrApiRequest.getHeader().getCorrelationId());
		int result = dbConnectionn.insertEquiFaxEVDR(equifaxevdrdetails_logs);
		logger.info("data saving done : "+result);
	}

	private JSONObject recursiveJsonKeyConverterToLower(JSONObject jsonObject) 
	{
		JSONObject resultJsonObject = new JSONObject();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
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
					for (int i = 0; i < nestedJsonArray.length(); i++)
					{
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
						logger.info("exception while getting recursiveJsonKeyConverterToLower::"+e.getMessage());
					}
				}
			}
			try 
			{
				resultJsonObject.put(key.replaceAll("-", "").replace("-", "").toLowerCase(), value);
			} 
			catch (JSONException e) 
			{
				logger.error("We are in exception : "+e);
			}
		}
		return resultJsonObject;
	}

}