package com.qualtech.finbit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import org.json.JSONObject;


public class Finbit {
	
	static Logger logger = Logger.getLogger(Finbit.class.getName());

	public String finbitResponse() //throws JSONException, IOException 
	{
		try
		{
			JSONObject firstJson = new JSONObject(firstApi());
			String accessToken = (String) firstJson.get("access_token");
			logger.info("Finbit access token(First Token ):: "+accessToken);
			
			JSONObject accountList = null;
			if(accessToken!=null && !(accessToken).equals(""))
			{
				JSONObject secondJson = new JSONObject(secondApi(accessToken));
			    accountList = (JSONObject) secondJson.getJSONArray("accountList").get(0);
			}
			
			String accountUID = null;
			if(accountList!=null && !(accountList).equals(""))
			{
				accountUID = (String) accountList.get("accountUID");
				logger.info("Bank Account Unique ID accountUID(Second Token ):: "+accountUID);
			}
			
			if((accessToken!=null && !(accessToken).equals("")) && (accountUID!=null && !(accountUID).equals("")))
			{
				JSONObject responseJson = new JSONObject(thirdApi(accessToken,accountUID));	
				return thirdApi(accessToken,accountUID);
			}
			else
			{
				logger.info(" Finbit Check fails accessToken or accountUID Empty or null");
				return null;
			}
			
		}
		catch(Exception exception)
		{
			logger.error("Finbit || finbitResponse() || Exception occurs while calling Finbit API "+exception);
			return null;
		}
		
	
	}

	private String firstApi()// throws ClientProtocolException, IOException 
	{
		StringBuffer result = new StringBuffer();
		BufferedReader rd =null;
		try 
		{
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost("https://www.fin360.in/bank-auth/api/v1/login");
			final String USER_AGENT = "Mozilla/5.0";
			post.setHeader("User-Agent", USER_AGENT);
			List urlParameters = new ArrayList();
			urlParameters.add(new BasicNameValuePair("emailAddress", "arun.dhareshwar@apacfin.com"));
			urlParameters.add(new BasicNameValuePair("password", "D#aF!ntec#56$"));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);
			rd= new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			String line = "";
			while ((line = rd.readLine()) != null) 
			{
				result.append(line);
			}
		}
		catch(Exception exception)
		{
			logger.error(" Finbit || firstApi() || Exception occurs while acces first token :: "+exception);
			return null;
		}
		finally {
			if(rd!=null) {
				try {
					rd.close();
				} catch (IOException e) {
					logger.error(" Finbit || firstApi() || Exception occurs during closing bufferReader:: "+e);
				}
			}
		}
		return result.toString();
	}

	private String secondApi(String accessToken) throws IOException 
	{
		FileInputStream fis = null;
		BufferedReader rd=null;
		StringBuffer result = new StringBuffer();
		try
		{
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost("https://www.fin360.in/bank-connect/api/v1/uploadMultipleStatements?access_token="+accessToken);
			final String USER_AGENT = "Mozilla/5.0";
			post.setHeader("User-Agent", USER_AGENT);
	
			File file = new File("E:\\FinBit\\Logger\\FinbitPdf\\5010XXXXXX4032_f4a41079_07Aug2018_TO_06Sep2018_040050537.pdf");
	
			fis=new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
	
			MultipartEntity entity = new MultipartEntity();
			ByteArrayBody body = new ByteArrayBody(data, "application/pdf", file.getName());
			entity.addPart("statement.0.bankStmt", body);
			entity.addPart("statement.0.bank", new StringBody("HDFC"));
			entity.addPart("statement.0.accountType", new StringBody("SAVING"));
			entity.addPart("statement.0.emailAddress", new StringBody("deepakgill221@gmail.com"));
			entity.addPart("statement.0.password", new StringBody("73341100"));
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			rd= new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null)
			{
				result.append(line);
			}
		}
		catch(Exception exception)
		{
			logger.error(" Finbit || secondApi() || Exception occurs while acces second token :: "+exception);
			return null;
		}
		finally {
			if(rd!=null) {
				try {
					rd.close();
				} catch (IOException e) {
					logger.error(" Finbit || secondApi() || Exception occurs during closing bufferReader:: "+e);
				}
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(" Finbit || secondApi() || Exception occurs during closing FileInputStream:: "+e);
				}
			}
		}
		
		return result.toString();
	}

	private String thirdApi(String accessToken, String accountUID) throws IOException 
	{
		StringBuilder response = new StringBuilder();
		BufferedReader bufferedReader = null;
		try
		{
			//String str = "https://www.fin360.in/bank-account/api/v1/summary/"+accountUID+".json?access_token="+accessToken;
			
			String str = "https://www.fin360.in/bank-account/api/v1/transactionsWithDetails/"+accountUID+".json?access_token="+accessToken;
			
			URL myURL = new URL(str);
			logger.info("Finbit URL for JSON Response - URL : "+ myURL);
			HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setDoOutput(true);
			//int responseCode = conn.getResponseCode();
			bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) 
			{
				response.append(inputLine);
			}
		}
		catch(Exception exception)
		{
			logger.error(" Finbit || thirdApi() || Exception occurs while acces second token :: "+exception);
			return null;
		}
		finally {
			if(bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error(" Finbit || thirdApi() || Exception occurs during closing bufferReader:: "+e);
				}
			}
			
		}
		return response.toString();
	}

}
