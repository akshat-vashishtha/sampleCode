package com.qualtech.icici.api.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

public class HttpCalling {

	static Logger logger = Logger.getLogger(HttpCalling.class.getName());
	
	private HttpCalling(){
		
	}
	
	public static Map<String,String> httpPostCall(String req, String url, String contentType) {
		
		StringBuilder resultData = new StringBuilder();
		String output = "";
		Map<String,String> resultMap = new HashMap<>();
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		HttpsURLConnection conn = null;
		try {
			URL url1 = new URL(url);
			conn = (HttpsURLConnection) url1.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(240000);
			conn.setReadTimeout(420000);
			if(contentType.equalsIgnoreCase("xml")) {
			conn.setRequestProperty("Content-Type", "text/xml");
			}else {
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Accept", "application/json");
			}
		
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(req);
			writer.flush();
			writer.close();
			
			int apiResponseCode = conn.getResponseCode();
			resultMap.put("responseCode", String.valueOf(apiResponseCode));
			logger.info("HttpCalling || httpPostCall() || API Response Code :: " + apiResponseCode);
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					resultData.append(output);
				}
				resultMap.put("responseData", resultData.toString());
				logger.info("HttpCalling || httpPostCall() || API Response Data :: " + resultData.toString());
				conn.disconnect();
				br.close();
				logger.info("HttpCalling || httpPostCall() || ENDS :: ");
			}
		}
		catch (Exception e) {
			logger.error("HttpCalling || httpPostCall() || Exception Occurs :: "+e);
		}
		return resultMap;
	}
	
	public static Map<String,String> httpGetCall(String url) {
		
		StringBuilder resultData = new StringBuilder();
		String output = "";
		Map<String,String> resultMap = new HashMap<>();
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		try {
			URL url1 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		conn.setRequestMethod("GET");
		int apiResponseCode = conn.getResponseCode();
		resultMap.put("responseCode", String.valueOf(apiResponseCode));
		if (apiResponseCode != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		while ((output = br.readLine()) != null) {
			resultData.append(output);
		}
		resultMap.put("responseData", resultData.toString());
		logger.info("HttpCalling || httpPostCall() || API Response Data :: " + resultData.toString());
		conn.disconnect();}
		catch (Exception e) {
			logger.error("HttpCalling || httpGetCall() || Exception Occurs :: "+e);
		}
		return resultMap;
	}
}
