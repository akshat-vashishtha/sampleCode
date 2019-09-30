package com.qualtech.crif.api.utils;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.qualtech.crif.api.crif.response.Indvreportfile;
import com.qualtech.crif.api.dto.CrifTrackerDTO;

public class ResponseHandler implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5133590941298961976L;

	@Override
	public String toString() {
		return "ResponseHandler []";
	}


	private static Logger logger = LogManager.getLogger(ResponseHandler.class);
	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}





	//############### Credit Bureau 2.0 #################################################

	public Map<String, String> responseTraverseNeoAcknwldge(String responseXml) throws ParserConfigurationException, SAXException, IOException
	{

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		StringReader sr=new StringReader(responseXml);
		is.setCharacterStream(sr);
		Map<String, String> resMap = new HashMap<String,String>();
//		sr.reset();
		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("INQUIRY");
		for (int i = 0; i < nodes.getLength(); i++) 
		{
			Element element = (Element) nodes.item(i);
			NodeList title = element.getElementsByTagName("INQUIRY-UNIQUE-REF-NO");
			Element line = (Element) title.item(0);
			resMap.put("INQ_REF-NO",getCharacterDataFromElement(line));

			NodeList name = element.getElementsByTagName("REPORT-ID");
			line = (Element) name.item(0);
			resMap.put("REPORT-ID",getCharacterDataFromElement(line));

			NodeList date = element.getElementsByTagName("RESPONSE-DT-TM");
			line = (Element) date.item(0);
			resMap.put("REQUEST_DT",getCharacterDataFromElement(line));
			try
			{
				NodeList code = element.getElementsByTagName("CODE");
				line = (Element) code.item(0);
				resMap.put("ERROR_CODE",getCharacterDataFromElement(line));
			}
			catch(Exception e)
			{
				resMap.put("ERROR_CODE","");
			}

		}

		Map<String, String> finalResponse = new HashMap<String, String>();
		if(!resMap.get("INQ_REF-NO").equalsIgnoreCase("") 
				&& !resMap.get("INQ_REF-NO").equals(null)
				&& !resMap.get("REPORT-ID").equalsIgnoreCase("")
				&& !resMap.get("REPORT-ID").equals(null)
				&& !resMap.get("REQUEST_DT").equalsIgnoreCase("")
				&& !resMap.get("REQUEST_DT").equals(null)
				)
		{
			finalResponse.put("INQ_REF-NO",resMap.get("INQ_REF-NO").toString());
			finalResponse.put("REPORT-ID",resMap.get("REPORT-ID").toString());
			finalResponse.put("REQUEST_DT",resMap.get("REQUEST_DT").toString());
			finalResponse.put("ERROR_CODE",resMap.get("ERROR_CODE").toString());
		}
		else
		{
			finalResponse = null;
		}
		return finalResponse;
	}

	public Map<String, String> responseTraverseNeoCRIFService2(String responseXml) throws ParserConfigurationException, SAXException, IOException
	{

		try{
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			String refResponse = responseXml.replace("(<", "(Less than ").replace("(>=", "(Greater than ");
			logger.info("ResponseHandler || responseTraverseNeoCRIFService2() || Parsed XML: "+refResponse);
			is.setCharacterStream(new StringReader(refResponse));
			Map<String, String> resMap = new HashMap<String,String>();
			Map<String, String> finalResponse = new HashMap<String, String>();
			Document doc = db.parse(is);

			//			         NodeList nList = doc.getElementsByTagName("INDV-REPORT-FILE");
			NodeList nList = doc.getElementsByTagName("INDV-REPORT");
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				logger.info("ResponseHandler || responseTraverseNeoCRIFService2() || Node Length"+nList.getLength());
				Node nNode = nList.item(temp);
				//			            System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					logger.info("NAME : " + eElement.getElementsByTagName("NAME").item(0).getTextContent());		               
					logger.info("FATHER : " + eElement.getElementsByTagName("FATHER").item(0).getTextContent());
					logger.info("DOB : " + eElement.getElementsByTagName("DOB").item(0).getTextContent());
					logger.info("AGE : " + eElement.getElementsByTagName("AGE").item(0).getTextContent());
					logger.info("VALUE : " + eElement.getElementsByTagName("VALUE").item(0).getTextContent());
					logger.info("ADDRESS : " + eElement.getElementsByTagName("ADDRESS").item(0).getTextContent());
					logger.info("PHONE : " + eElement.getElementsByTagName("PHONE").item(0).getTextContent());
					logger.info("VALUE : " + eElement.getElementsByTagName("VALUE").item(0).getTextContent());
					logger.info("SCORE-VALUE : " + eElement.getElementsByTagName("SCORE-VALUE").item(0).getTextContent()); 
					logger.info("NAME-VARIATIONS : " + eElement.getElementsByTagName("NAME-VARIATIONS").item(0).getTextContent());
					logger.info("ADDRESS-VARIATIONS : " + eElement.getElementsByTagName("ADDRESS-VARIATIONS").item(0).getTextContent());
					logger.info("DATE-OF-BIRTH-VARIATIONS : " + eElement.getElementsByTagName("DATE-OF-BIRTH-VARIATIONS").item(0).getNodeValue());
					logger.info("DATE-OF-BIRTH-VARIATIONS : " + eElement.getElementsByTagName("DATE-OF-BIRTH-VARIATIONS").item(0).getFirstChild());
					logger.info("VOTER-ID-VARIATIONS : " + eElement.getElementsByTagName("VOTER-ID-VARIATIONS").item(0).getFirstChild().getNodeValue());
					logger.info("PHONE-NUMBER-VARIATIONS : " + eElement.getElementsByTagName("PHONE-NUMBER-VARIATIONS").item(0).getTextContent());

					if(!eElement.getElementsByTagName("ADDRESS-VARIATIONS").item(0).getTextContent().equalsIgnoreCase("") && eElement.getElementsByTagName("ADDRESS-VARIATIONS").item(0).getTextContent()!=null)
					{
						logger.info("Inside the condition : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						//				            		   genericElementBasedParser(tagName , responseXml);
					}
					else{

					}
					//			        Storing data in a map

					//			               resMap.put("REQ-SERVICE-TYPE-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent());
					//			               resMap.put("STATUS-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("STATUS").item(0).getTextContent());
					//			               resMap.put("SCORE-NAME-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("SCORE-NAME").item(0).getTextContent());

					//				               resMap.put("REMARKS-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("REMARKS").item(0).getTextContent());
				}
				logger.info("-------------");
			}

			//			         NodeList nl = doc.getElementsByTagName("*");
			//
			//			         for (int i = 0; i < nl.getLength(); i++)
			//			         {
			//			           System.out.println("name is : "+nl.item(i).getNodeName());
			//			         }
			//			         
			//############################### FINAL RESPONSE STARTED ###############################


			//			########       FOR PAN & NAME

			if(resMap.get("STATUS-PAN").equalsIgnoreCase("Y"))
			{
				//				    	Spliting the remarks content of PAN.  added 21-07-2016 Rishabh Sharma
				String line = resMap.get("REMARKS-PAN");
				String[] rmrkPanName = line.split(",");

				finalResponse.put("PAN", rmrkPanName[0].trim());
				finalResponse.put("NAME", rmrkPanName[1].trim());

				if((resMap.get("SCORE-VALUE-PAN").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-PAN"))>=80))
				{
					finalResponse.put("PAN_STATUS","Matched");
					finalResponse.put("NAME_STATUS","Matched");
				}
				else
				{
					finalResponse.put("PAN_STATUS","Not Matched");
					finalResponse.put("NAME_STATUS","Not Matched");
				}

			}  
			else
			{
				finalResponse.put("PAN", "");
				finalResponse.put("NAME", "");
				finalResponse.put("PAN_STATUS","Not Matched");
				finalResponse.put("NAME_STATUS","Not Matched");
			}


			//			########       FOR DOB

			if(resMap.get("STATUS-DOB").equalsIgnoreCase("Y"))
			{
				finalResponse.put("DOB", resMap.get("REMARKS-DOB").trim());

				if((resMap.get("SCORE-VALUE-DOB").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-DOB"))>=80))
				{
					finalResponse.put("DOB_STATUS","Matched");
				}
				else
				{
					finalResponse.put("DOB_STATUS","Not Matched");
				}
			}  
			else
			{
				finalResponse.put("DOB","");
				finalResponse.put("DOB_STATUS","Not Matched");
			}

			//			        ###### FOR ADDRESS & PINCODE

			if(resMap.get("STATUS-ADDRESS").equalsIgnoreCase("Y"))
			{
				finalResponse.put("ADDRESS", resMap.get("REMARKS-ADDRESS").trim());
				//						Splitting the PIN code from the address added 05-08-2016 Rishabh Sharma
				String pincode = resMap.get("REMARKS-ADDRESS");
				String rmrkPincode = pincode.substring((pincode.length()-6), pincode.length());
				finalResponse.put("PINCODE",rmrkPincode.trim());

				if((resMap.get("SCORE-VALUE-ADDRESS").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-ADDRESS"))>=75))
				{
					finalResponse.put("ADDRESS_STATUS","Matched");
					finalResponse.put("PINCODE_STATUS","Matched");
				}
				else
				{
					finalResponse.put("ADDRESS_STATUS","Not Matched");
					finalResponse.put("PINCODE_STATUS","Not Matched");
				}
			}  
			else
			{
				finalResponse.put("ADDRESS","");
				finalResponse.put("ADDRESS_STATUS","Not Matched");
				finalResponse.put("PINCODE","");
				finalResponse.put("PINCODE_STATUS","Not Matched");
			}

			//			     ###### FOR MOBILE & EMAIL

			if(resMap.get("STATUS-BUREAU").equalsIgnoreCase("Y"))
			{
				String mobStatus = "";
				String emailStatus="";
				String mobEmail = resMap.get("REMARKS-BUREAU").trim();
				if(mobEmail.contains("Yes"))
				{
					//			    MOBILE NUMBER //
					if(!mobEmail.contains("No-Phone"))
					{
						try
						{
							String phone= mobEmail.substring(mobEmail.indexOf("Yes", 0), mobEmail.length());
							if(phone.contains("Yes"))
							{
								mobStatus = phone.subSequence(phone.indexOf("-", 4)+1, phone.indexOf("%", phone.indexOf("-", 4)+1)).toString();
								finalResponse.put("MOBILE",phone.subSequence(phone.indexOf("%", phone.indexOf("-", 4)+1)+2, phone.indexOf("-", phone.indexOf("%", phone.indexOf("-", 4)+1)+2)).toString());
							}
							else
							{
								finalResponse.put("MOBILE","");
							}

							if((resMap.get("SCORE-VALUE-BUREAU").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-BUREAU"))>=80))
							{
								if(mobStatus.equalsIgnoreCase("100") || Integer.parseInt(mobStatus)>=80)
								{
									finalResponse.put("MOBILE_STATUS","Matched");
								}
								else
								{
									finalResponse.put("MOBILE_STATUS","Not Matched");
								}
							}
							else
							{
								finalResponse.put("MOBILE_STATUS","Not Matched");
							}

						}
						catch(Exception e)
						{
							finalResponse.put("MOBILE","");
							finalResponse.put("MOBILE_STATUS","Not Matched");
						}

						//			EMAIL ADDRESS //	
						try
						{
							String phone1 =mobEmail.substring(mobEmail.indexOf("Yes", 0), mobEmail.length());
							String email= mobEmail.substring(mobEmail.indexOf("from Bureau", 0)+11, mobEmail.length());
							if(email.contains("Yes"))
							{
								emailStatus = email.subSequence(phone1.indexOf("-", 4)+1, email.indexOf("%", phone1.indexOf("-", 4)+1)).toString();
								finalResponse.put("EMAIL",email.subSequence(email.indexOf("%", phone1.indexOf("-", 4)+1)+2, email.indexOf("-", email.indexOf("%", phone1.indexOf("-", 4)+1)+2)).toString());
							}
							else
							{
								finalResponse.put("EMAIL","");
							}

							if((resMap.get("SCORE-VALUE-BUREAU").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-BUREAU"))>=80))
							{
								if(emailStatus.equalsIgnoreCase("100") || Integer.parseInt(emailStatus)>=80)
								{
									finalResponse.put("EMAIL_STATUS","Matched");
								}
								else
								{
									finalResponse.put("EMAIL_STATUS","Not Matched");
								}
							}
							else
							{
								finalResponse.put("EMAIL_STATUS","Not Matched");
							}
						}
						catch(Exception e)
						{
							finalResponse.put("EMAIL","");
							finalResponse.put("EMAIL_STATUS","Not Matched");
						}
					}
					else
					{
						finalResponse.put("MOBILE","");
						finalResponse.put("MOBILE_STATUS","Not Matched");
						try
						{
							String phone1 =mobEmail.substring(mobEmail.indexOf("Yes", 0), mobEmail.length());
							String email =mobEmail.substring(mobEmail.indexOf("Yes", 0), mobEmail.length());
							if(email.contains("Yes"))
							{
								emailStatus = email.subSequence(email.indexOf("-", 4)+1, email.indexOf("%", email.indexOf("-", 4)+1)).toString();
								finalResponse.put("EMAIL",email.subSequence(email.indexOf("%", phone1.indexOf("-", 4)+1)+2, email.indexOf("-", email.indexOf("%", phone1.indexOf("-", 4)+1)+2)).toString());
							}
							else
							{
								finalResponse.put("EMAIL","");
							}

							if((resMap.get("SCORE-VALUE-BUREAU").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-BUREAU"))>=80))
							{
								if(emailStatus.equalsIgnoreCase("100") || Integer.parseInt(emailStatus)>=80)
								{
									finalResponse.put("EMAIL_STATUS","Matched");
								}
								else
								{
									finalResponse.put("EMAIL_STATUS","Not Matched");
								}
							}
							else
							{
								finalResponse.put("EMAIL_STATUS","Not Matched");
							}
						}
						catch(Exception e)
						{
							finalResponse.put("EMAIL","");
							finalResponse.put("EMAIL_STATUS","Not Matched");
						}
					}
				}  
				else
				{
					finalResponse.put("MOBILE","");
					finalResponse.put("MOBILE_STATUS","Not Matched");
					finalResponse.put("EMAIL","");
					finalResponse.put("EMAIL_STATUS","Not Matched");
				}

			}
			else
			{
				finalResponse.put("MOBILE","");
				finalResponse.put("MOBILE_STATUS","Not Matched");
				finalResponse.put("EMAIL","");
				finalResponse.put("EMAIL_STATUS","Not Matched");
			}



			//			 	     ###### FOR OCCUPATION CLASS
			if(resMap.get("STATUS-OCCUPATION CLASS").equalsIgnoreCase("Y"))
			{
				finalResponse.put("OCCPTNCLS", resMap.get("SCORE-DESCRIPTION-OCCUPATION CLASS").trim());
			}  
			else
			{
				finalResponse.put("OCCPTNCLS","");
			}

			//			 	     ###### FOR CREDIT SCORE
			if(resMap.get("STATUS-CB SCORE").equalsIgnoreCase("Y"))
			{
				finalResponse.put("CREDITSCR", resMap.get("SCORE-VALUE-CB SCORE").trim());
			}  
			else
			{
				finalResponse.put("CREDITSCR","");
			}

			//			 	     ###### FOR INCOME SEGMENT
			if(resMap.get("STATUS-INCOME SEGMENT").equalsIgnoreCase("Y"))
			{
				String esIncm = resMap.get("SCORE-DESCRIPTION-INCOME SEGMENT").trim();

				if(esIncm.contains("(")||esIncm.contains(")"))
				{
					int i = esIncm.indexOf("(");
					int j = esIncm.indexOf(")");
					finalResponse.put("ESTMTDINCM", esIncm.substring(i+1,j));
				}
				else
				{
					finalResponse.put("ESTMTDINCM","");
				}
			}  
			else
			{
				finalResponse.put("ESTMTDINCM","");
			}

			return finalResponse;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error("ResponseHandler || responseTraverseNeoCRIFService2() || Exception occurs "+ e);
			return null;
		}
	}


	public Map<String, String> responseTraverseNeoCRIFService1(String responseXml) throws ParserConfigurationException, SAXException, IOException
	{
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			String refResponse = responseXml.replace("(<", "(Less than ").replace("(>=", "(Greater than ");
			is.setCharacterStream(new StringReader(refResponse));
			Map<String, String> resMap = new HashMap<String,String>();
			Map<String, String> finalResponse = new HashMap<String, String>();

			Document doc = db.parse(is);

			NodeList nList = doc.getElementsByTagName("REQ-SERVICE");

			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if(eElement.getElementsByTagName("STATUS").item(0).getTextContent().equalsIgnoreCase("Y"))
					{
						if(temp<4)
						{
							logger.info("REMARKS : " + eElement.getElementsByTagName("REMARKS").item(0).getTextContent());
						}
						else
						{
							logger.info("SCORE-DESCRIPTION : " + eElement.getElementsByTagName("SCORE-DESCRIPTION").item(0).getTextContent()); 
						}
					}
					//			        Storing data in a map
					resMap.put("REQ-SERVICE-TYPE-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent());
					resMap.put("STATUS-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("STATUS").item(0).getTextContent());
					if(eElement.getElementsByTagName("STATUS").item(0).getTextContent().equalsIgnoreCase("Y"))
					{
						resMap.put("SCORE-VALUE-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("SCORE-VALUE").item(0).getTextContent());
						if(temp<4)
						{
							resMap.put("REMARKS-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("REMARKS").item(0).getTextContent());
						}
						else
						{
							resMap.put("SCORE-DESCRIPTION-"+eElement.getElementsByTagName("REQ-SERVICE-TYPE").item(0).getTextContent()+"",eElement.getElementsByTagName("SCORE-DESCRIPTION").item(0).getTextContent());
						}
					}
				}
			}


			//############################### FINAL RESPONSE STARTED ###############################

			//			########       FOR PAN & NAME

			if(resMap.get("STATUS-PAN").equalsIgnoreCase("Y"))
			{
				//				    	Spliting the remarks content of PAN.  added 21-07-2016 Rishabh Sharma
				String line = resMap.get("REMARKS-PAN");
				String[] rmrkPanName = line.split(",");

				finalResponse.put("PAN", rmrkPanName[0].trim());
				finalResponse.put("NAME", rmrkPanName[1].trim());

				if((resMap.get("SCORE-VALUE-PAN").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-PAN"))>=80))
				{
					finalResponse.put("PAN_STATUS","Matched");
					finalResponse.put("NAME_STATUS","Matched");
				}
				else
				{
					finalResponse.put("PAN_STATUS","Not Matched");
					finalResponse.put("NAME_STATUS","Not Matched");
				}

			}  
			else
			{
				finalResponse.put("PAN", "");
				finalResponse.put("NAME", "");
				finalResponse.put("PAN_STATUS","Not Matched");
				finalResponse.put("NAME_STATUS","Not Matched");
			}


			//			########       FOR DOB

			if(resMap.get("STATUS-DOB").equalsIgnoreCase("Y"))
			{
				finalResponse.put("DOB", resMap.get("REMARKS-DOB").trim());

				if((resMap.get("SCORE-VALUE-DOB").equalsIgnoreCase("100")|| Integer.parseInt(resMap.get("SCORE-VALUE-DOB"))>=80))
				{
					finalResponse.put("DOB_STATUS","Matched");
				}
				else
				{
					finalResponse.put("DOB_STATUS","Not Matched");
				}
			}  
			else
			{
				finalResponse.put("DOB","");
				finalResponse.put("DOB_STATUS","Not Matched");
			}

			return finalResponse;
		
	}

	public static void genericElementBasedParser(String tagName, String responseXml){

		try{

			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			String refResponse = responseXml.replace("(<", "(Less than ").replace("(>=", "(Greater than ");
			logger.info("Parsed XML: "+refResponse);
			is.setCharacterStream(new StringReader(refResponse));
			//Map<String, String> resMap = new HashMap<String,String>();
		//	Map<String, String> finalResponse = new HashMap<String, String>();

			Document doc = db.parse(is);
            NodeList nList = doc.getElementsByTagName(""+tagName+"-VARIATIONS");
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
                  if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					for (int i = 0; i < nList.getLength(); i++)
					{
						logger.info("Length : "+nList.getLength());
						logger.info("name is : "+nList.item(i).getNodeName());
						int j =0;
						try{
							while(eElement.getElementsByTagName("VARIATION").item(j).getTextContent() != null)
							{
								logger.info("VARIATION : " + eElement.getElementsByTagName("VARIATION").item(j).getTextContent());
								j++;
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
							logger.error("ResponseHandler || genericElementBasedParser() || Exception Occurs while calling getElementsByTagName()"+ e);
						}
					}

					
				}

			}
		}
		catch(Exception e)
		{
			logger.error("ResponseHandler || genericElementBasedParser() || Exception Occurs "+ e);
		}

	}

	@SuppressWarnings("unchecked")
	public Indvreportfile responseTraverseINJsonCRIFService(String responseXml,CrifTrackerDTO criftrackerdto)
	{
        JSONObject o=null;
		JSONObject lowerKeyJson=null;
		Indvreportfile indvreportfile=new Indvreportfile();
		Map<String, Object> map = new HashMap<String, Object>();
		try 
		{
         	o = org.json.XML.toJSONObject(responseXml);
         	logger.info("Xml in Json : "+o.toString());
            ObjectMapper mapper = new ObjectMapper();
//			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//			mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(Visibility.ANY));
			mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.ANY));
		try
			{
				lowerKeyJson=recursiveJsonKeyConverterToLower(o);
				logger.info("Lower Key : "+lowerKeyJson);
			}
			catch(Exception ex)
			{
				logger.error(">>>>>>>> : responseTraverseINJsonCRIFService in Response Handler "+ex.getMessage());
	        }
			try
			{
//				indvreportfile = mapper.readValue(lowerKeyJson.toString(), Indvreportfile.class);
				 map = mapper.readValue(lowerKeyJson.toString(),  Map.class);
				 indvreportfile.additionalProperties=map;
			}
			catch(Exception ec)
			{
				logger.error(">>>>>>>> responseTraverseINJsonCRIFService in Response Handler : "+ec.getMessage());
            }
			
			logger.error(">>>>>>>> : responseTraverseINJsonCRIFService in Response Handler "+lowerKeyJson);
		} 

		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return indvreportfile;
	}
	
    public JSONObject recursiveJsonKeyConverterToLower(JSONObject jsonObject) throws JSONException
	{
		JSONObject resultJsonObject = new JSONObject();
		@SuppressWarnings("unchecked") Iterator<String> keys = jsonObject.keys();
		while(keys.hasNext())
		{
			String key = keys.next();
			Object value = null;
			try
			{
				JSONObject nestedJsonObject = jsonObject.getJSONObject(key);
				value = this.recursiveJsonKeyConverterToLower(nestedJsonObject);
			}
			catch(JSONException jsonException)
			{
				try
				{
					JSONArray nestedJsonArray=jsonObject.getJSONArray(key);
					JSONArray resultJsonArray = new JSONArray();
					for(int i=0;i<nestedJsonArray.length();i++)
					{
						JSONObject resultJsonObjectForArray = nestedJsonArray.getJSONObject(i);
						value = this.recursiveJsonKeyConverterToLower(resultJsonObjectForArray);
						resultJsonArray.put(value);
					}
					value=resultJsonArray;
				}
				catch(JSONException jsonExceptionInner)
				{
					value=jsonObject.get(key);
				}
			}
			resultJsonObject.put(key.replaceAll("-","").replace("-","").toLowerCase(), value);
		}

		return resultJsonObject;
	}
}
