package com.qualtech.multibureau.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.qualtech.multibureau.api.util.HttpInfo;

@SuppressWarnings("deprecation")
public class HttpCallMultiBureau {
	static Logger logger = Logger.getLogger(HttpCallMultiBureau.class.getName());

	public HttpCallMultiBureau() {
		super();
	}

	public HttpInfo httpCallMB(String req, String url, String instituationID, String aggregatorID, String memberID,	String pswd) 
	{
		logger.info("inside httpCall() :: Start");
		HttpInfo HttpInfo = new HttpInfo();
		StringBuffer result = new StringBuffer();
		String output = new String();
		HttpResponse response = null;
		HttpPost httpPost = null;
		HttpEntity entity = null;
		try
		{
			System.setProperty("jsse.enableSNIExtension", "false");
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, new TrustManager[]
					{
							new X509TrustManager()
							{
								@Override
								public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
										throws java.security.cert.CertificateException 
								{	
								}
								@Override
								public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
										throws java.security.cert.CertificateException 
								{ 
								}
								@Override
								public java.security.cert.X509Certificate[] getAcceptedIssuers() 
								{
									return null;
								}
							}}, new SecureRandom());

			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			Scheme httpsScheme = new Scheme("https", 443, socketFactory);
			schemeRegistry.register(httpsScheme);
			ClientConnectionManager cm =  new SingleClientConnManager(schemeRegistry);
			HttpClient httpClient = new DefaultHttpClient(cm);
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("INSTITUTION_ID", instituationID));
			postParameters.add(new BasicNameValuePair("AGGREGATOR_ID", aggregatorID));
			postParameters.add(new BasicNameValuePair("MEMBER_ID", memberID));
			postParameters.add(new BasicNameValuePair("PASSWORD", pswd));
			postParameters.add(new BasicNameValuePair("inputJson_", req));
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			HttpInfo.setResponseCode(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) 
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
			}
			else
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				logger.info("We face error while calling API");
			}
			HttpInfo.setResponse(result.toString());
		}
		catch(Exception e)
		{
			HttpInfo.setResponseCode(500);
			HttpInfo.setResponse(result.toString());
			logger.error("we have exception inside httpCall() :: "+e);
			e.printStackTrace();
		}
		logger.info("Final response : "+result.toString());
		logger.info("inside httpCall() :: END");
		return HttpInfo;
	}

	
//	public HttpInfo httpSecondCall(String req, String url, String instituationID, String aggregatorID, String memberID,	String pswd)
//	{
//		logger.info("inside httpSecondCall() :: Start" );
//		HttpInfo HttpInfo = new HttpInfo();
//		StringBuffer result = new StringBuffer();
//		String output = new String();
//		HttpResponse response = null;
//		HttpPost httpPost = null;
//		HttpEntity entity = null;
//		try
//		{
//			System.setProperty("jsse.enableSNIExtension", "false");
//			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
//			sslContext.init(null, new TrustManager[]
//					{
//							new X509TrustManager()
//							{
//								public void checkClientTrusted(X509Certificate[] certs, String authType) 
//								{
//								}
//								public void checkServerTrusted(X509Certificate[] certs, String authType) 
//								{
//								}
//								@Override
//								public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
//										throws java.security.cert.CertificateException 
//								{	
//								}
//								@Override
//								public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
//										throws java.security.cert.CertificateException 
//								{ 
//								}
//								@Override
//								public java.security.cert.X509Certificate[] getAcceptedIssuers() 
//								{
//									return null;
//								}
//							}}, new SecureRandom());
//
//			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//			SchemeRegistry schemeRegistry = new SchemeRegistry();
//			Scheme httpsScheme = new Scheme("https", 443, socketFactory);
//			schemeRegistry.register(httpsScheme);
//			ClientConnectionManager cm =  new SingleClientConnManager(schemeRegistry);
//			HttpClient httpClient = new DefaultHttpClient(cm);
//			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
//			postParameters.add(new BasicNameValuePair("INSTITUTION_ID", instituationID));
//			postParameters.add(new BasicNameValuePair("AGGREGATOR_ID", aggregatorID));
//			postParameters.add(new BasicNameValuePair("MEMBER_ID", memberID));
//			postParameters.add(new BasicNameValuePair("PASSWORD", pswd));
//			postParameters.add(new BasicNameValuePair("inputJson_", req));
//			httpPost = new HttpPost(url);
//			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
//			response = httpClient.execute(httpPost);
//			entity = response.getEntity();
//			HttpInfo.setResponseCode(response.getStatusLine().getStatusCode());
//			if (response.getStatusLine().getStatusCode() == 200) 
//			{
//				BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
//				while ((output = br.readLine()) != null) 
//				{
//					result.append(output);
//				}
//			}
//			else
//			{
//				BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
//				while ((output = br.readLine()) != null) 
//				{
//					result.append(output);
//				}
//				logger.info("We face error while calling API : "+result.toString());
//			}
//			HttpInfo.setResponse(result.toString());
//		}
//		catch(Exception e)
//		{
//			HttpInfo.setResponseCode(500);
//			HttpInfo.setResponse(result.toString());
//			logger.error("we have exception inside httpCall() :: "+e);
//			e.printStackTrace();
//		}
//		logger.info("Final response : "+result.toString());
//		logger.info("inside httpSecondCall() :: END" );
//		return HttpInfo;
//	}


	public HttpInfo httpCall(String req, String url, String instituationID, String aggregatorID, String memberID,
			String pswd) {
		logger.info("inside httpCall() :: Start");
		HttpInfo HttpInfo = new HttpInfo();
		HttpsUrlConnectionMessageSender msgSender = new HttpsUrlConnectionMessageSender();
		try {
			HttpsURLConnection conn1 = msgSender.createConnection1(new URI(url));
			HttpInfo = makeRestCall(conn1, req, instituationID, aggregatorID, memberID, pswd);
		} catch (Exception e) {
			logger.error("we have exception inside httpCall() :: "+e);
			e.printStackTrace();
		}
		logger.info("inside httpCall() :: END");
		return HttpInfo;
	}

	public HttpInfo httpSecond(String req, String url, String instituationID, String aggregatorID, String memberID,
			String pswd) {
		logger.info("inside httpSecondCall() :: Start" );
		HttpInfo HttpInfo = new HttpInfo();
		HttpsUrlConnectionMessageSender msgSender = new HttpsUrlConnectionMessageSender();
		try {
			HttpsURLConnection conn1 = msgSender.createConnection1(new URI(url));
			HttpInfo = makeSecondRestCall(conn1, req, instituationID, aggregatorID, memberID, pswd);
		} catch (Exception e) {
			logger.error("Exception inside httpSecondCall() :: "+e );
			e.printStackTrace();
		}
		logger.info("inside httpSecondCall() :: END" );
		return HttpInfo;
	}

	public HttpInfo makeRestCall(HttpsURLConnection connection, String req, String instituationID, String aggregatorID,
			String memberID, String pswd) 
	{
		logger.info("inside makeRestCall() :: Start");
		HttpInfo info = new HttpInfo();
		String output = new String();
		StringBuffer result = new StringBuffer();
		try {
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("INSTITUTION_ID", instituationID);
			connection.setRequestProperty("AGGREGATOR_ID", aggregatorID);
			connection.setRequestProperty("MEMBER_ID", memberID);
			connection.setRequestProperty("PASSWORD", pswd);
			connection.setRequestProperty("inputJson_", req);
			logger.info("Going to connect with API URL with all details");
			connection.connect();
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(req.getBytes());
			outputStream.flush();
			info.setResponseCode(connection.getResponseCode());
			logger.info("Response code from Server :: " + connection.getResponseCode());
			if (info.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
			} else {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
					while ((output = br.readLine()) != null) {
						result.append(output);
					}
					br.close();
				} catch (Exception ex) {
					logger.error("We are not getting Error stream : " + ex);
					ex.printStackTrace();
				}
			}
			connection.disconnect();
			logger.info("Service Response from Third Party :: " + result.toString());
			info.setResponse(result.toString());
		} catch (Exception e) {
			logger.error("we have exception inside makerest call :: " + e);
			e.printStackTrace();
		}
		logger.info("inside makeRestCall() :: END");
		return info;
	}

	public HttpInfo makeSecondRestCall(HttpsURLConnection connection, String req, String instituationID,
			String aggregatorID, String memberID, String pswd) {
		logger.info("inside makeSecondRestCall() :: Start" );
		HttpInfo info = new HttpInfo();
		String output = new String();
		StringBuffer result = new StringBuffer();
		try {
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("INSTITUTION_ID", instituationID);
			connection.setRequestProperty("AGGREGATOR_ID", aggregatorID);
			connection.setRequestProperty("MEMBER_ID", memberID);
			connection.setRequestProperty("PASSWORD", pswd);
			connection.setRequestProperty("inputJson_", req);

			connection.connect();
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(req.getBytes());
			outputStream.flush();

			info.setResponseCode(connection.getResponseCode());
			logger.info("Response code from Server :: " + connection.getResponseCode());
			if (info.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
			}
			else
			{
				try
				{
					BufferedReader br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
					while ((output = br.readLine()) != null)
					{
						result.append(output);
					}
					br.close();
				}
				catch (Exception ex)
				{
					logger.info("We are not getting Error stream : " + ex);
					ex.printStackTrace();
				}
			}
			try
			{
				outputStream.close();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			connection.disconnect();
			logger.info("Service Response from Third Party :: " + result.toString());
			info.setResponse(result.toString());
		} catch (Exception e) {
			logger.error("exception inside makeSecondRestCall()  :: " +e);
			e.printStackTrace();
		}
		logger.info("inside makeSecondRestCall() :: END" );
		return info;
	}

}
