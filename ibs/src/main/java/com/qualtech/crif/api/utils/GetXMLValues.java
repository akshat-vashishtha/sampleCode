package com.qualtech.crif.api.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GetXMLValues 
{
	private static final Logger logger = LogManager.getLogger(GetXMLValues.class);
	public HashMap<String,String> getXmlValues(String xml,String rootNOde,String[] tags)
	{
		logger.info("Comes in getXmlValues Method of class GetXMLValues");  
		HashMap<String,String> returnMap=new HashMap<String,String>();
		DocumentBuilder dBuilder;
		try 
		{
			dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try 
			{
				
				InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xml));
		        Document doc = dBuilder.parse(is);
		        
		        NodeList nodes = doc.getElementsByTagName(rootNOde);
		        for (int i = 0; i < nodes.getLength(); i++) 
		        {
		            Element element = (Element) nodes.item(i);
                    
		            for(int j=0;j<tags.length;j++)
		            {
		            	NodeList tag = element.getElementsByTagName(tags[j]);
			            Element line = (Element) tag.item(0);
			            logger.info(tags[j]+" :: " + getCharacterDataFromElement(line));
			            returnMap.put(tags[j], getCharacterDataFromElement(line));
		            }    
		         }
				
			} 
			catch (SAXException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e)
			{				
				e.printStackTrace();
			}
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
	    
		logger.info("End of getXmlValues Method: ");
		return returnMap;
	}
	
	public ArrayList<HashMap<String,String>> getListOfXmlValues(String xml,String rootNOde,String[] tags)
	{
		logger.info("Comes in getXmlValues Method of class getXmlValuesForPolicyDEtail");  
		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>> ();
		DocumentBuilder dBuilder;
		try 
		{
			dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try 
			{
				
				InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xml));
		        Document doc = dBuilder.parse(is);
		        
		        NodeList nodes = doc.getElementsByTagName(rootNOde);
		        for (int i = 0; i < nodes.getLength(); i++) 
		        {
		            Element element = (Element) nodes.item(i);
		            HashMap<String,String> returnMap=new HashMap<String,String>();
		            for(int j=0;j<tags.length;j++)
		            {		            	
		            	NodeList tag = element.getElementsByTagName(tags[j]);
			            Element line = (Element) tag.item(0);
			            logger.info(tags[j]+" :: " + getCharacterDataFromElement(line));			            
			            returnMap.put(tags[j], getCharacterDataFromElement(line));			            
		            }    
		            list.add(returnMap);
		         }
				
			} 
			catch (SAXException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e)
			{				
				e.printStackTrace();
			}
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
	    
		logger.info("End of getXmlValuesForPolicyDEtail Method: ");
		return list;
	}
	public static String getCharacterDataFromElement(Element e)
	{
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) 
	    {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return "";
	}
}
