package com.qualtech.icici.api.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

public class TestApi 
{
	public static String makeRestCall(HttpsURLConnection connection, String req, String key) throws Exception 
	{
//		String pan = Configuration.PAN;// Specify the PAN Number Here.Do go to Configuration and Change the Value for PAN
		req = "\"" + req + "\"";
//		String key = Configuration.API_KEY;// Specify your API Key Here.Do go to Configuration and Change the Value for API Key
		key = "\"" + key + "\"";
		String value = "{\"pan\":" + req + "," + "\"key\":" + key + "" + "}";
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.connect();
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(value.getBytes());
		outputStream.flush();
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String output;
		while ((output = br.readLine()) != null) 
		{
//			System.out.println(output);
		}
		connection.disconnect();
		return output;
	}


	public static String test2(String req, String key) 
	{
		String response="";
		HttpsUrlConnectionMessageSender msgSender = new HttpsUrlConnectionMessageSender();
		try 
		{
			HttpsURLConnection conn1 = msgSender.createConnection1(new URI("https://testapi.karza.in/pan"));
			response=makeRestCall(conn1,req,key);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static void main(String[] args) throws ParseException {
		String y="<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">ertdfg";
String s="<?xml ";
String a=y.replaceAll("\\?", "").replaceAll("\\<", "").replaceAll("\\>", "").replaceAll("xml version=\"1.0\" encoding=\"utf-8\"", "").replaceAll("soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"", "");
System.out.println(a);
	}
}
