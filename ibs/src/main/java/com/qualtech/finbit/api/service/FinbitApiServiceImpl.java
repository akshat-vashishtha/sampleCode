package com.qualtech.finbit.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.finbit.api.db.FinbitDB;
import com.qualtech.finbit.api.request.AccntDetailSecondApi;
import com.qualtech.finbit.api.request.AccountDetail;
import com.qualtech.finbit.api.request.AccountList;
import com.qualtech.finbit.api.request.FinbitApiRequest;
import com.qualtech.finbit.api.request.FinbitHeader;
import com.qualtech.finbit.api.request.FinbitReqRes;
import com.qualtech.finbit.api.request.LoginAuthentication;
import com.qualtech.finbit.api.response.ApiResponseHeader;
import com.qualtech.finbit.api.response.ErrorInfo;
import com.qualtech.finbit.api.response.FinbitAPIResponse;
import com.qualtech.finbit.api.response.FinbitResResult;
import com.qualtech.finbit.api.response.FinbitResponsePayload;
import com.qualtech.finbit.utils.FinbitInfo;
import com.qualtech.finbit.utils.HtmlCreator;
import com.qualtech.finbit.utils.StringConstants;
import com.qualtech.finbit.utils.PDFConvertor;

@Service
public class FinbitApiServiceImpl implements FinbitApiService {

	private static Logger logger = Logger.getLogger(FinbitApiServiceImpl.class);

	@Autowired
	private FinbitDB dbConnection;
	@Autowired
	private PropertyFile resProp;

	public FinbitAPIResponse callFinbitService(FinbitApiRequest apiRequest, FinbitReqRes reqRes) {

		logger.info("FinbitApiServiceImpl || callFinbitService() || STARTS ");

		HtmlCreator htmlCreator = new HtmlCreator();
		ObjectMapper om=new ObjectMapper();
		FinbitAPIResponse response = new FinbitAPIResponse();

		ApiResponseHeader header = new ApiResponseHeader();
		FinbitInfo info=new FinbitInfo();
		ErrorInfo errorInfo = new ErrorInfo();
		try {

			if (apiRequest != null 
					&& apiRequest.getPayload() != null 
					&& apiRequest.getHeader() != null
					&& apiRequest.getPayload().getAccountDetail() !=null
					&& !apiRequest.getPayload().getAccountDetail().isEmpty())
			{
				if (checkValidation(apiRequest.getHeader())) 
				{
					header.setAppId(apiRequest.getHeader().getAppId());
					header.setCorrelationId(apiRequest.getHeader().getCorrelationId());
					header.setMsgVersion(apiRequest.getHeader().getMsgVersion());
					response.setHeader(header);
					logger.info(" Finbit || firstapi starts()");

					info = firstapi(resProp);
					if(info.getFirstApiStatusCode()==200 && info.getLoginAuthentication()!=null && info.getLoginAuthentication().getStatus().equalsIgnoreCase("SUCCESS")) 
					{
						
						LoginAuthentication loginAuthentication =info.getLoginAuthentication();
						logger.info(" Finbit || firstapi ends()");
						if (loginAuthentication != null && loginAuthentication.getToken() != null) 
						{
							logger.info(" Finbit || secondApi() starts");

							info=secondApi(loginAuthentication.getToken(),
									apiRequest.getPayload().getAccountDetail(), resProp);
							try {
								reqRes.setInternal_req(om.writeValueAsString(apiRequest.getPayload().getAccountDetail()));
								reqRes.setInternal_res(om.writeValueAsString(info.getAccntDetailSecondApi()));
							} catch (Exception e) {
								logger.error("Found exception to set second api internal request response : "+e);
							}
							logger.info(" Finbit || secondApi() ends");
							if(info.getSecondApiStatusCode()==200 && info.getAccntDetailSecondApi()!=null && info.getAccntDetailSecondApi().getStatus().equalsIgnoreCase("SUCCESS") ) 
							{
								AccntDetailSecondApi accntDetailSecondApi = info.getAccntDetailSecondApi();
								if (accntDetailSecondApi != null && accntDetailSecondApi.getAccountList() != null
										&& !accntDetailSecondApi.getAccountList().isEmpty()) 
								{
									FinbitResponsePayload payloads = new FinbitResponsePayload();
									List<FinbitResResult> results = new ArrayList<FinbitResResult>();
									
									errorInfo.setStatus(StringConstants.SUCCESS.toString());
									errorInfo.setMessage(StringConstants.MESSAGE200.toString());
									errorInfo.setCode(StringConstants.C_200.toString());
									for (AccountList accountList2 : accntDetailSecondApi.getAccountList()) 
									{
										logger.info(" Finbit || thirdApi() starts ");
										FinbitResResult result = thirdApi(loginAuthentication.getToken(),
												accountList2.getAccountUID(), resProp);
										logger.info(" Finbit || thirdApi() ends ");
										try {
											reqRes.setInternal_req(reqRes.getInternal_req()+"\n|||||\n"+loginAuthentication.getToken()+
													accountList2.getAccountUID());
											reqRes.setInternal_res(reqRes.getInternal_res()+"\n|||||\n"+om.writeValueAsString(result));
										} catch (Exception e) {
											logger.error("Found exception to set third api internal request response : "+e);
										}

										results.add(result);
									}
									if(results!=null && !results.isEmpty()) {
										payloads.setResult(results);
										String htmlCode = htmlCreator.generateHtml(results, resProp);
										String pdfpath = PDFConvertor.generatePdf(htmlCode, resProp);
										if (pdfpath != null) {
											payloads.setPdfPath(pdfpath);
										}else {
											logger.info(" Finbit pdf path found null");
										}
									}else {

										errorInfo.setStatus(StringConstants.FAILURE.toString());
										errorInfo.setMessage(StringConstants.MESSAGE500.toString());
										errorInfo.setDescription("Unable to get transactions with details");
										errorInfo.setCode(StringConstants.C_500.toString());
									}
									logger.info(errorInfo.getMessage());

									response.setPayload(payloads);

								} 
								else 
								{
									logger.error(StringConstants.MESSAGE500.toString());
									errorInfo.setStatus(StringConstants.FAILURE.toString());
									errorInfo.setMessage(StringConstants.MESSAGE500.toString());
									if(accntDetailSecondApi!=null) {
										errorInfo.setDescription(accntDetailSecondApi.getErrors().toString());
									}

									errorInfo.setCode(StringConstants.C_500.toString());
								}
							}else 
							{
								AccntDetailSecondApi accntDetailSecondApi = info.getAccntDetailSecondApi();
								logger.error(StringConstants.MESSAGE500.toString());
								errorInfo.setStatus(StringConstants.FAILURE.toString());
								errorInfo.setMessage(StringConstants.MESSAGE500.toString()+":"+accntDetailSecondApi.getErrors());
								errorInfo.setCode(StringConstants.C_500.toString()); 
							}
						}else
						{
							logger.error(StringConstants.MESSAGE501.toString());
							errorInfo.setStatus(StringConstants.FAILURE.toString());
							errorInfo.setMessage(StringConstants.MESSAGE501.toString());
							errorInfo.setCode(StringConstants.C_501.toString());
						}
					}else
					{
						logger.error(StringConstants.MESSAGE501.toString());
						errorInfo.setStatus(StringConstants.FAILURE.toString());
						errorInfo.setMessage(StringConstants.MESSAGE501.toString());
						errorInfo.setCode(StringConstants.C_501.toString());
					}
				}else 
				{
					logger.error(StringConstants.MESSAGE401.toString());
					errorInfo.setStatus(StringConstants.FAILURE.toString());
					errorInfo.setMessage(StringConstants.MESSAGE401.toString());
					errorInfo.setCode(StringConstants.C_401.toString());
				}
			} 
			else
			{
				logger.error(StringConstants.MESSAGE400.toString());
				errorInfo.setStatus(StringConstants.FAILURE.toString());
				errorInfo.setMessage(StringConstants.MESSAGE400.toString());
				errorInfo.setCode(StringConstants.C_400.toString());
			}
		} catch (Exception e) {
			logger.error("We are in Exception while processing : " + e);
			errorInfo.setStatus(StringConstants.FAILURE.toString());
			errorInfo.setMessage(StringConstants.MESSAGE600.toString());
			errorInfo.setCode(StringConstants.C_600.toString());
		}
		response.setErrorInfo(errorInfo);

		logger.info("FinbitApiServiceImpl || callFinbitService() || ENDS ");
		return response;

	}

	private boolean checkValidation(FinbitHeader header) {
		if (header != null) {
			AuthValidator auth = dbConnection.validateAuth(header);
			if (auth.getAppid() == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	private FinbitResResult thirdApi(String accessToken, String accountUID, PropertyFile resProp)	throws IOException 
	{
		logger.info("FinbitApiServiceImpl || thirdApi() || START ");
		FinbitResResult result = null;
		BufferedReader bufferedReader =null;
		try
		{
			ObjectMapper om = new ObjectMapper();
			// "https://www.fin360.in/bank-account/api/v1/transactionsWithDetails/"+accountUID+".json?access_token="+accessToken;
			URL myURL = new URL(resProp.getString("com.qualtech.finbit.thirdapi.url") + accountUID	+ ".json?access_token=" + accessToken);
			logger.info("Finbit URL for JSON Response - URL : " + myURL);
			HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setDoOutput(true);
			bufferedReader= new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = bufferedReader.readLine()) != null) 
			{
				response.append(inputLine);
			}
			logger.info("FinbitApiServiceImpl || thirdApi() || response :  "+response.toString());
			result = om.readValue(response.toString(), FinbitResResult.class);
		}
		catch (Exception exception) 
		{
			logger.error(" FinbitApiServiceImpl || thirdApi() || Exception occurs while acces JSON Response :: " + exception);
		}
		finally
		{
			if(bufferedReader!=null) 
			{
				bufferedReader.close();
			}
		}
		logger.info("FinbitApiServiceImpl || thirdApi() || ENDS ");
		return result;
	}

	private static FinbitInfo secondApi(String accessToken, List<AccountDetail> accountDetails,
			PropertyFile resProp) throws IOException 
	{
		AccntDetailSecondApi accntDetailSecondApi = null;
		FinbitInfo info=new FinbitInfo();

		BufferedReader bufferedReader = null;
		try 
		{
			HttpClient client = HttpClientBuilder.create().build();
			// "https://www.fin360.in/bank-connect/api/v1/uploadMultipleStatements?access_token="
			HttpPost post = new HttpPost(resProp.getString("com.qualtech.finbit.secondapi.url") + accessToken);
			post.setHeader("User-Agent", "Mozilla/5.0");
			logger.info(" url for second api with token : "+resProp.getString("com.qualtech.finbit.secondapi.url") + accessToken);
			int i = 0;
			MultipartEntity entity = new MultipartEntity();
			for (AccountDetail accountDetail : accountDetails) 
			{
				ByteArrayBody body = new ByteArrayBody(Base64.decode(accountDetail.getBankstatement()), "application/pdf",
						accountDetail.getEmailaddress());
				entity.addPart("statement." + i + ".bankStmt", body);
				entity.addPart("statement." + i + ".bank", new StringBody(accountDetail.getBankname()));
				entity.addPart("statement." + i + ".accountType", new StringBody(accountDetail.getAccounttype()));
				entity.addPart("statement." + i + ".emailAddress", new StringBody(accountDetail.getEmailaddress()));
				entity.addPart("statement." + i + ".password", new StringBody(accountDetail.getPassword()));
				i++;
			}
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			info.setSecondApiStatusCode(response.getStatusLine().getStatusCode());
			bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder result = new StringBuilder();
			ObjectMapper om = new ObjectMapper();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) 
			{
				result.append(line);
			}
			logger.info(" response from second api  : "+result.toString());
			accntDetailSecondApi = om.readValue(result.toString(), AccntDetailSecondApi.class);
			info.setAccntDetailSecondApi(accntDetailSecondApi);
			logger.info(" FinbitApiServiceImpl || secondApi() || info: " +info);
		}
		catch (Exception exception)
		{
			logger.error(" FinbitApiServiceImpl || secondApi() || Exception occurs while acces second token :: "+ exception);
		}
		finally 
		{
			if (bufferedReader != null) 
			{
				bufferedReader.close();
			}
		}
		return info;
	}

	private static FinbitInfo firstapi(PropertyFile resProp) throws IOException 
	{
		logger.info(" FinbitApiServiceImpl || firstApi() || firstApi invoked");
		StringBuilder result = new StringBuilder();
		BufferedReader bufferedReader = null;
		FinbitInfo info=new FinbitInfo();
		LoginAuthentication loginAuthentication = new LoginAuthentication();
		ObjectMapper om = new ObjectMapper();
		try
		{
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(resProp.getString("com.qualtech.finbit.firstapi.url"));
			post.setHeader("User-Agent", "Mozilla/5.0");
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair("emailAddress", resProp.getString("com.qualtech.finbit.firstapi.email")));
			urlParameters.add(new BasicNameValuePair("password", resProp.getString("com.qualtech.finbit.firstapi.password")));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);
			info.setFirstApiStatusCode(response.getStatusLine().getStatusCode());
			bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) 
			{
				result.append(line);
			}
			logger.info(" FinbitApiServiceImpl || firstApi() || response: "+result.toString());
			loginAuthentication = om.readValue(result.toString(), LoginAuthentication.class);
			info.setLoginAuthentication(loginAuthentication);
			logger.info(" FinbitApiServiceImpl || firstApi() || info: " +info);
		}
		catch (Exception exception) 
		{
			logger.error(" FinbitApiServiceImpl || firstApi() || Exception occurs while acces first token :: " + exception);
		} 
		finally
		{
			if (bufferedReader != null) 
			{
				bufferedReader.close();
			}
		}
		return info;
	}

	public static boolean isValidUrl(String url)
	{
		try {
			new URL(url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
