package com.qualtech.cibil.api.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
public class ConvertUrltoByteArray
{
	BufferedReader breader = null;

	private static Logger logger = Logger.getLogger(ConvertUrltoByteArray.class);
	@Autowired PropertyFile env; 
	//private ResourceBundle env = PropertyFile.getInstance().getResourceBundel();
		
	public String getingByteArray1(String url_temp,String requestjson,String key,String reponseJson,String key1)
	{
		StringBuffer htmldata=new StringBuffer();	 
		OutputStream out=null;
		try 
		{
			String urlParameters =key+"=" + URLEncoder.encode(requestjson, "UTF-8")+"&"+key1+"=" + URLEncoder.encode(reponseJson, "UTF-8");
			URL    url            = new URL( url_temp );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();      
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty( "charset", "utf-8");
			conn.setRequestProperty("Content-Length", "" +Integer.toString(urlParameters.getBytes().length));
			conn.setUseCaches( false );
			out=conn.getOutputStream();
			out.write(urlParameters.getBytes());
			out.flush();
			out.close();
			conn.connect();
			InputStream ins=conn.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(ins));
			String line=null;
			while((line =br.readLine())!=null)
			{
				htmldata.append(line);
			} 
		}
		catch(Exception ec)
		{
			logger.error("Exception in ConvertUrltoByteArray || getingByteArray1() ||  creating byte array :: "+ ec);
		}
		return htmldata.toString();
	}
	
	public String getingByteArray(String url_temp,String requestjson,String key)
	{
		StringBuffer htmldata=new StringBuffer();	 
		OutputStream out=null;
		try 
		{
			//----------------------- HTML CREATTION ------------------------
			String urlParameters =key+"=" + URLEncoder.encode(requestjson, "UTF-8");

			//		  String urlParameters  = key+"="+requestjson;
			//		  byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			//		  int    postDataLength = postData.length;
			URL    url            = new URL( url_temp );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();      
			//		  conn.setRequestProperty(key, requestjson);
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty( "charset", "utf-8");
			//		  conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			conn.setRequestProperty("Content-Length", "" +Integer.toString(urlParameters.getBytes().length));
			conn.setUseCaches( false );


			/*CHanged By Anuj */  
			out=conn.getOutputStream();
			out.write(urlParameters.getBytes());
			out.flush();
			out.close();

			/* DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
		     wr.writeBytes(urlParameters );
		     wr.flush();
		     wr.close();
		  }*/
			/*End*/
			conn.connect();
			InputStream ins=conn.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(ins));
			String line=null;
			while((line =br.readLine())!=null){
				htmldata.append(line);

			} 
		}
		catch(Exception ec)
		{
			logger.error("Exception in ConvertUrltoByteArray || getingByteArray() ||  creating byte array :: "+ ec);
		}

		return htmldata.toString();
	}
	
	@SuppressWarnings("static-access")
	public String getingByteArray2(String url_temp,String requestjson,String key,HttpServletRequest request)
	{
		URL url = null;
		HttpURLConnection httpsConnection = null;
		//HttpServletResponse response =null;
		try 
		{
			url = new URL(url_temp);
		} 
		catch (MalformedURLException e1)
		{
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		String param = null;
		String requestJson = requestjson;

		if (null != requestJson && !"".equals(requestJson)) 
		{
			param = requestJson;
		}
		if (param == null) 
		{
			try
			{
				//				request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				InputStream ins = request.getInputStream();
				InputStreamReader insreader = new InputStreamReader(ins);
				BufferedReader breader = new BufferedReader(insreader);
				//			List uriResult = URLEncodedUtils.parse(breader.readLine(), Charset.defaultCharset());
				List<?> uriResult = URLEncodedUtils.parse(new URI(breader.readLine()),"UTF-8");
				String completeRequest = uriResult.get(0).toString();
				StringTokenizer stk = new StringTokenizer(completeRequest, "=");
				stk.nextToken();
				param = stk.nextToken();

			}
			catch (Exception ec) 
			{
				logger.error("Exception in ConvertUrltoByteArray || getingByteArray2() || accessing InputStream :: "+ ec);
			}
		}

		XTrustProvider xTrustProvider = new XTrustProvider();
		xTrustProvider.install();
		try {
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
			logger.info("ConvertUrltoByteArray || getingByteArray2() || content type -->" + httpsConnection.getContentType());
			logger.info("ConvertUrltoByteArray || getingByteArray2() || http status code " + httpsConnection.getResponseCode());

			InputStream inputStream = httpsConnection.getInputStream();
			logger.info("ConvertUrltoByteArray || getingByteArray2() || inputStream ==" + inputStream.toString());
			//byte[] _extData = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception in ConvertUrltoByteArray || getingByteArray2() || Http Connection :: "+ e);
		}
		return null;
	}


}