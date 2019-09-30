package com.qualtech.crif.api.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.crif.api.service.CriffMVCService;
import com.qualtech.crif.api.utils.Commons;
import com.qualtech.crif.api.utils.XTrustProvider;

@Service
public class CriffMVCServiceImpl implements CriffMVCService 
{
	Logger logger = Logger.getLogger(CriffMVCServiceImpl.class.getName());
	@Autowired
	public static PropertyFile env;
	public void getCrifMFIFile(HttpServletRequest request, HttpServletResponse response) 
	{
		FileInputStream fileIn = null;
		ServletOutputStream out=null;
		try 
		{
			String filepath = env.getString("com.qc.criff.requestXlsFilePathForMFI");
			File file = new File(filepath);
			fileIn=new FileInputStream(file);
			response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=CriffRequestjson For Micro-Finance .xlsx");
			out= response.getOutputStream();
			int i = 0;
			while ((i = fileIn.read()) != -1) {
				out.write(i);
			}
			
		} catch (Exception ec) {
			logger.error(ec);
		}finally {
			try {
				if(fileIn!=null) {
				fileIn.close();
				}
				if(out!=null) {
				out.flush();
				out.close();
				}
			} catch (IOException e) {
				logger.error("CriffMVCServiceImpl || during closing resources "+e);
			}
			
		}
	}

	public void getCriffFile(HttpServletRequest request, HttpServletResponse response)
	{
		FileInputStream fileIn = null;
		ServletOutputStream out=null;
		try 
		{
			String filepath =  env.getString("com.qc.criff.requestXlsFilePath");
			File file = new File(filepath);
			fileIn = new FileInputStream(file);

			response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=CriffRequestjson For Consumer.xlsx");
			response.setContentType("application/vnd.ms-excel");
			out = response.getOutputStream();
			int i = 0;
			while ((i = fileIn.read()) != -1) {
				out.write(i);
			}
		}
		catch (Exception ec) 
		{
			logger.error(ec);
		}finally {
			try {
				if(fileIn!=null) {
				fileIn.close();
				}
				if(out!=null) {
				out.flush();
				out.close();
				}
			} catch (IOException e) {
				logger.error("CriffMVCServiceImpl || during closing resources "+e);
			}
			
		}
	}

	public void downloadHTML(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String ServiceType = request.getParameter("serviceType");
			String strURL = "";
			String param = null;
			String requestJson = request.getParameter("requestJsonforHtmlDownload");
			if (null != requestJson && !"".equals(requestJson)) {
				param = requestJson;
			}

			if ("MFI".equals(ServiceType)) {
				strURL =  env.getString("com.qc.criff.liveURL") + "/crif/api/v1/crifrequestMFI";
			} else if ("CONSUMER".equals(ServiceType)) {
				strURL =  env.getString("com.qc.criff.liveURL") + "/crif/api/v1/crifrequest";
			}
			// String
			// param="{\"header\":{\"msgVersion\":\"\",\"appId\":\"\",\"correlationId\":\"\",\"userId\":\"\",\"password\":\"9999\",\"rollId\":\"\"},\"payload\":{\"transaction\":[{\"fName\":\"Rabiya
			// P\",\"mName\":\"\",\"lName\":\"\",\"dob\":\"05-03-1981\",\"keyPersonName\":\"Vasu
			// Kumar\",\"keyPersonType\":\"Father\",\"nomineeName\":\"Vasu
			// Kumar\",\"nomineeType\":\"Father\",\"ids\":[{\"idName\":\"Passport\",\"idNo\":\"DYQPS2000P\"}],\"relations\":[{\"name\":\"Vasu
			// Kumar\",\"relation\":\"Father\"}],\"phones\":[{\"teleNo\":\"9551542844\",\"type\":\"Mobile\"}],\"addresses\":[{\"city\":\"BANGALORE\",\"state\":\"KA\",\"pin\":\"600053\",\"address\":\"165049,1128,KFC,BANU
			// NAGAR 29TH AVUNUE
			// PUDUR,Silkboard,BANGALORE,KARNATAKA,600053\",\"type\":\"D01\"}]}]}}";

			XTrustProvider.install();
			url = new URL(strURL);
			httpsConnection = (HttpURLConnection) url.openConnection();
			HttpURLConnection conn = null;

			// String DevMode =
			// env.getProperty("spring.enable.proxy.development");
			// Proxy proxy;
			// if(DevMode!=null && !DevMode.equalsIgnoreCase("") &&
			// DevMode.equalsIgnoreCase("Y"))
			// {
			// logger.info("We are running in Development Mode So Proxy
			// Enabled");
			// proxy = new Proxy(Proxy.Type.HTTP, new
			// InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			// conn = (HttpURLConnection) url.openConnection(proxy);
			// }
			// else
			// {
			// conn = (HttpURLConnection) url.openConnection();
			// }

			HttpsURLConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try {
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				_extData = Commons.getBytesFromInputStream(inputStream);
				String responseFromService = new String(_extData, "UTF-8");
				try {
					Map json = Commons.getGsonData(responseFromService);
					String printablereport = ((Map) ((Map) ((Map) json.get("payload")).get("transaction"))
							.get("printablereport")).get("content").toString();
					InputStream fileInputStream = new ByteArrayInputStream(printablereport.getBytes());
					response.setContentType("application/html");
					response.addHeader("content-disposition", "attachment; filename=CriffResponse.html");
					// response.setContentType("application/vnd.ms-excel");
					ServletOutputStream out = response.getOutputStream();
					byte[] outputByte = new byte[4096];
					// copy binary contect to output stream
					while (fileInputStream.read(outputByte, 0, 4096) != -1) {
						out.write(outputByte, 0, 4096);
					}
					fileInputStream.close();
					out.flush();
					out.close();
				} catch (Exception e) {
					logger.error(e);
				}
			} catch (IOException io) {
				io.printStackTrace();
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());

			}
			httpsConnection.disconnect();
		} catch (Exception ec) {
			logger.error(ec);
		}

	}

	public void doPost(HttpServletRequest request, String service) {
		try {
			URL url = null;
			HttpURLConnection httpsConnection = null;
			String servername = request.getParameter("ServerPath");

			String strURL = "";
			if ("MFI".equals(service)) {
				strURL =  env.getString("com.qc.criff.liveURL") + "/ib/crif/api/v1/crifrequestMFI";
			} else if ("CONSUMER".equals(service)) {
				strURL =  env.getString("com.qc.criff.liveURL") + "/ib/crif/api/v1/crifrequest";
			}

			// String
			// param="{\"header\":{\"msgVersion\":\"\",\"appId\":\"\",\"correlationId\":\"\",\"userId\":\"\",\"password\":\"9999\",\"rollId\":\"\"},\"payload\":{\"transaction\":[{\"fName\":\"Rabiya
			// P\",\"mName\":\"\",\"lName\":\"\",\"dob\":\"05-03-1981\",\"keyPersonName\":\"Vasu
			// Kumar\",\"keyPersonType\":\"Father\",\"nomineeName\":\"Vasu
			// Kumar\",\"nomineeType\":\"Father\",\"ids\":[{\"idName\":\"Passport\",\"idNo\":\"DYQPS2000P\"}],\"relations\":[{\"name\":\"Vasu
			// Kumar\",\"relation\":\"Father\"}],\"phones\":[{\"teleNo\":\"9551542844\",\"type\":\"Mobile\"}],\"addresses\":[{\"city\":\"BANGALORE\",\"state\":\"KA\",\"pin\":\"600053\",\"address\":\"165049,1128,KFC,BANU
			// NAGAR 29TH AVUNUE
			// PUDUR,Silkboard,BANGALORE,KARNATAKA,600053\",\"type\":\"D01\"}]}]}}";

			String param = null;
			String requestJson = request.getParameter("requestJson");

			if (null != requestJson && !"".equals(requestJson)) {
				param = requestJson;
			}
			if (param == null) {
				try {
					InputStream ins = request.getInputStream();
					InputStreamReader insreader = new InputStreamReader(ins);
					BufferedReader breader = new BufferedReader(insreader);
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					//					List uriResult = URLEncodedUtils.parse(breader.readLine(), Charset.defaultCharset());
					String completeRequest = uriResult.get(0).toString();
					StringTokenizer stk = new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param = stk.nextToken();

				} catch (Exception ec) {
					logger.error("Error in Exception " + ec);
				}
			}

			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			// String DevMode =
			// env.getProperty("spring.enable.proxy.development");
			// Proxy proxy;
			// if(DevMode!=null && !DevMode.equalsIgnoreCase("") &&
			// DevMode.equalsIgnoreCase("Y"))
			// {
			// logger.info("We are running in Development Mode So Proxy
			// Enabled");
			// proxy = new Proxy(Proxy.Type.HTTP, new
			// InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			// conn = (HttpURLConnection) url.openConnection(proxy);
			// }
			// else
			// {
			// conn = (HttpURLConnection) url.openConnection();
			// }
			httpsConnection = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try {
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				_extData = Commons.getBytesFromInputStream(inputStream);
				String responseFromService = new String(_extData, "UTF-8");
				try {
					Map json = Commons.getGsonData(responseFromService);
					String printablereport = ((Map) ((Map) ((Map) json.get("payload")).get("transaction"))
							.get("printablereport")).get("content").toString();
					request.setAttribute("htmlformat", printablereport);
				} catch (Exception e) {
					request.setAttribute("Error-description", e.getMessage());
					e.printStackTrace();
				}
			} catch (IOException io) {
				request.setAttribute("Error-description", io.getMessage());
				io.printStackTrace();
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());

			}
			httpsConnection.disconnect();
		} catch (Exception ec) {
			logger.error(ec);
		}
	}

}