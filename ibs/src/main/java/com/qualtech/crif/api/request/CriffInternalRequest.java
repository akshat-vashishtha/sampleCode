package com.qualtech.crif.api.request;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.crif.api.crif.response.Indvreportfile;
import com.qualtech.crif.api.dto.CrifTrackerDTO;
import com.qualtech.crif.api.utils.GetXMLValues;
import com.qualtech.crif.api.utils.ResponseHandler;
import com.qualtech.crif.api.utils.XTrustProvider;

@Service
public class CriffInternalRequest implements CriffInternalRequestInterface
{
	private static Logger logger = LogManager.getLogger(CriffInternalRequest.class);
	@Autowired
	public PropertyFile resProp ;
	public static Proxy proxy;
	public  String callFirstRequest(String requestxml)
	{

		StringBuilder out1= new StringBuilder();

		String output=null;
		try
		{
			//XTrustProvider xTrustProvider =new XTrustProvider();
			XTrustProvider.install();
			logger.debug("CriffInternalRequest || callFirstRequest() || CRIF service: First call to CRIF: START");
			//	URL url = new URL(null, resProp.getString("com.qualtech.pan2.resource.CRIF.url"), new sun.net.www.protocol.https.Handler());
			URL url = new URL(resProp.getString("com.qualtech.pan2.resource.CRIF.url"));
			System.setProperty("jsse.enableSNIExtension", "false");


			HttpURLConnection conn = null;
			String DevMode = resProp.getString("spring.enable.proxy.development");
			if(DevMode!=null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y"))
			{
				logger.info("We are running in Development Mode So Proxy Enabled");
				proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
				conn = (HttpURLConnection) url.openConnection(proxy);
			}
			else
			{

				conn = (HttpURLConnection) url.openConnection();
			}
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/xml");	
			conn.addRequestProperty("productVersion", resProp.getString("com.qualtech.pan2.resource.CRIF.PRODUCT-VER"));
			conn.addRequestProperty("mbrid", resProp.getString("com.qualtech.pan2.resource.CRIF.REQ-MBR"));

			conn.addRequestProperty("productType", resProp.getString("com.qualtech.pan2.resource.CRIF.PRODUCT-TYP"));
			conn.addRequestProperty("password",resProp.getString("com.qualtech.pan2.resource.CRIF.PASSWORD"));
			conn.addRequestProperty("reqVolType", resProp.getString("com.qualtech.pan2.resource.CRIF.REQTYP"));
			conn.addRequestProperty("UserId",resProp.getString("com.qualtech.pan2.resource.CRIF.USERID"));
			conn.addRequestProperty("requestXml", requestxml);
			conn.setConnectTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.CRIF.Timeout")));
			conn.setReadTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.CRIF.Timeout")));
			conn.setDoOutput(true);
			conn.setDoInput(true);
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			while ((output = br.readLine()) != null) 
			{
				logger.info(output);
				if(output!=null)
					out1.append(output);
			}
			logger.debug("CRIF service: First call to CRIF: End");
			logger.debug("CRIF service: Received Acknowledge XML :"+out1.toString());
			logger.info("CRIF service: Acknowledge XML :"+ out1.toString());
			logger.info("First Call response : "+ out1.toString());
			conn.disconnect();
		}
		catch(Exception ec)
		{
			logger.error("CriffInternalRequest || callFirstRequest() || CRIF service: First call to CRIF:"+ec.getMessage());
		}

		return out1.toString();
	}

	public CrifTrackerDTO lastResponse(String Issuexml,String service)
	{
		int maxRetry = Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.CRIF.maxRetryCount"));
		Indvreportfile indvreportfile=null; 
		String crifErrorOutput = "";
		CrifTrackerDTO crifTrackerDTO = new CrifTrackerDTO();
		String hardCodedEnv="N";
		ResourceBundle res=null;
		try 
		{
			res = ResourceBundle.getBundle("hardcode");
			hardCodedEnv = resProp.getString("com.crif.pcs.demo.development");	
			logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration");
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		logger.debug("CriffInternalRequest || lastResponse() || CRIF Second Service: Issue XML:"+Issuexml);
		logger.debug("CRIF Second Service: Issue XML : Applying delay in This call");
		//  *****************************DELAY*******************************************************
		try 
		{
			Thread.sleep(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.CRIF.delayTime.outer")));
		} 
		catch(InterruptedException ex) 
		{      
			Thread.currentThread().interrupt();
		}
		//	*****************************************************************************************	
		int issueRetry=0;
		StringBuilder out2= new StringBuilder();
		String output2 = "";
		while(issueRetry <= maxRetry)
		{
			try 
			{
				if(issueRetry > 0)
				{
					logger.info("CRIF ISSUE RETRY NO :: "+issueRetry);
					Thread.sleep(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.CRIF.delayTime.inner")));
				}
				// code to hit crif soap api service
				logger.debug("CRIF service: Second call to CRIF with Issue xml: START");
				//XTrustProvider xTrustProvider =new XTrustProvider();
				XTrustProvider.install();
				HttpURLConnection conn = null;
				URL url = new URL(resProp.getString("com.qualtech.pan2.resource.CRIF.url"));

				//For Dev we need to enable this
				System.setProperty("jsse.enableSNIExtension", "false");
				try
				{
					if(hardCodedEnv.equals("N"))
					{
						String DevMode = resProp.getString("spring.enable.proxy.development");
						if(DevMode!=null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y"))
						{
							logger.info("We are running in Development Mode So Proxy Enabled");
							proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
							conn = (HttpURLConnection) url.openConnection(proxy);
						}
						else
						{
							conn = (HttpURLConnection) url.openConnection();
						}
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Type", "application/xml");	
						conn.addRequestProperty("productVersion",resProp.getString("com.qualtech.pan2.resource.CRIF.PRODUCT-VER"));
						conn.addRequestProperty("mbrid", resProp.getString("com.qualtech.pan2.resource.CRIF.REQ-MBR"));
						conn.addRequestProperty("productType", resProp.getString("com.qualtech.pan2.resource.CRIF.PRODUCT-TYP"));
						conn.addRequestProperty("password", resProp.getString("com.qualtech.pan2.resource.CRIF.PASSWORD"));
						conn.addRequestProperty("reqVolType", resProp.getString("com.qualtech.pan2.resource.CRIF.REQTYP"));
						conn.addRequestProperty("UserID", resProp.getString("com.qualtech.pan2.resource.CRIF.USERID"));

						conn.addRequestProperty("requestXml", Issuexml);

						conn.setConnectTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.CRIF.Timeout")));
						conn.setReadTimeout(Integer.parseInt(resProp.getString("com.qualtech.pan2.resource.CRIF.Timeout")));

						conn.setDoOutput(true);
						conn.setDoInput(true);

						BufferedReader br1 = new BufferedReader(new InputStreamReader((conn.getInputStream())));
						while ((output2 = br1.readLine()) != null) 
						{
							logger.info(output2);
							if(output2!=null)
								out2.append(output2);
						}
					}
					else if(hardCodedEnv.equals("Y") && service.equals("MFI"))
					{
						out2=new StringBuilder(res.getString("CRIF_MFI_RES"));						
					}
					else if(hardCodedEnv.equals("Y") && service.equals("CONSUMER"))
					{
						out2=new StringBuilder(res.getString("CRIF_PCS_RES"));
					}
				}
				catch(Exception ec)
				{
					logger.error("CriffInternalRequest || lastResponse() || Exception in Second call :: "+ec);
				}
				logger.debug("CRIF service: Second call to CRIF with Issue xml: End");
				logger.info("CriffInternalRequest || lastResponse() || CRIF : FinalResponse XML: "+out2.toString());

				crifErrorOutput = out2.toString();
				if(crifErrorOutput != null || !"".equalsIgnoreCase(crifErrorOutput))
				{
					String errorXml=null;
					String errorResponsetype=null;
					String errorCode = "";
					String errorStatus = "";
					boolean errorResponseFlag = false;
					try
					{
						if(crifErrorOutput.contains("<ERROR>"))
						{
							errorXml = (crifErrorOutput.substring(crifErrorOutput.indexOf("<ERROR>"),crifErrorOutput.lastIndexOf("</ERROR>")))+"</ERROR>";
							logger.info("errorXml Crif : "+errorXml);
						}
						if(crifErrorOutput.contains("<REPONSE-TYPE>"))
						{
							errorResponsetype = (crifErrorOutput.substring(crifErrorOutput.indexOf("<REPONSE-TYPE>"),crifErrorOutput.lastIndexOf("</REPONSE-TYPE>")))+"</REPONSE-TYPE>";
							logger.info("errorXML ResponseType Crif  :: "+errorResponsetype);
						}
						try
						{
							if(errorXml != null && !errorXml.equals(""))
							{
								String subTag[] = new String[1];
								subTag[0]="CODE";
								ArrayList<HashMap<String,String>> getXmlMapValue = new GetXMLValues().getListOfXmlValues(errorXml, "ERROR", subTag);
								logger.info("CriffInternalRequest || lastResponse() || getXmlMapValue : ERROR xml VALUE : "+getXmlMapValue);
								errorCode = getXmlMapValue.iterator().next().get("CODE");
							}
							if(errorResponsetype != null && !errorResponsetype.equals(""))
							{
								String subTag[] = new String[1];
								subTag[0]="REPONSE-TYPE";
								String str = "<RESPONSE_INFO>"+ errorResponsetype +"</RESPONSE_INFO>";
								ArrayList<HashMap<String,String>> getXmlMapValue = new GetXMLValues().getListOfXmlValues(str, "RESPONSE_INFO", subTag);
								logger.info("CriffInternalRequest || lastResponse() || getXmlMapValue : RESPONSE_INFO xml VALUE : "+getXmlMapValue);
								errorStatus = getXmlMapValue.iterator().next().get("REPONSE-TYPE");
							}											
						}
						catch (Exception e)
						{
							logger.error("CriffInternalRequest || lastResponse() || Exception Occurs while parsing crif error code :: ACTUAL RESPONSE NOT FOUND FROM CRIF!! "+e);
							errorResponseFlag = true;
						}
						//Final checking for error code
						if(errorCode.equalsIgnoreCase("INPROCESS") || errorStatus.equalsIgnoreCase("ERROR") || errorResponseFlag)
						{
							//Retry counter updated starts
							logger.info("CRIF SERVICE CALLING ATTEMPT NO "+issueRetry+" ENDS :: NOW RETRIYING AGAIN ::");
							issueRetry++;
						}
						else
						{
							// No ERROR FOUND IN RESPONSE (PERFECT RESPONSE FOUND >> PROCEED)
							try
							{
								logger.info("CRIF SERVICE CALLING ATTEMPT NO "+issueRetry+" SUCCEEDED :: NOW TRAVARSING  RESPONSE STARTS");
								try
								{
									indvreportfile = new ResponseHandler().responseTraverseINJsonCRIFService(out2.toString(),crifTrackerDTO);
								}
								catch(Exception ec)
								{
									logger.error("CriffInternalRequest || lastResponse() || Exception in calling responseHandler.responseTraverseINJsonCRIFService "+ec.getMessage());	
								}
								logger.info("CRIF SERVICE CALLING ATTEMPT NO "+issueRetry+" SUCCEEDED :: NOW TRAVARSING  RESPONSE END");
							}
							catch (Exception e) 
							{
								logger.error("CriffInternalRequest || lastResponse() || CRIF service: Error in Final Response : "+e.getMessage());
								logger.error("CriffInternalRequest || lastResponse() || CRIF service : Error : " +e);
								issueRetry = 100;//means no loop will be iterateble
								break;
							}
							issueRetry = 100;//means no loop will be iterateble
							break;
						}
					}
					catch (Exception e) 
					{
						logger.error("CriffInternalRequest || lastResponse() || Exception occured in parsing crif error code :",e);
						issueRetry = 100;//means no loop will be iterateble
						break;
					}
				}
				conn.disconnect();
			}
			catch(InterruptedException ex) 
			{      // For UAT delay should be 6000
				Thread.currentThread().interrupt();
				issueRetry = 100;//means no loop will be iterateble
				break;
			}
			catch(IOException ioec)
			{
				logger.error(ioec.getMessage());
			}
		}
		logger.info("CriffInternalRequest || lastResponse() || Issue XML :"+Issuexml);
		crifTrackerDTO.setCrifApiIssueXmlRes(crifErrorOutput);
		crifTrackerDTO.setIndvreportfile(indvreportfile);
		return crifTrackerDTO;
	}
}
