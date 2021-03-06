package com.qualtech.equifax.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;


public class Commons 
{
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Commons.class);
	public static String getMethodName()
	{
		String methodName="";
		try
		{
			methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
		}
		catch(Exception e)
		{
			methodName="MethodNameNotFound";
		}
		return methodName;
	}
	
	public String convertList2Map(List<List<String>> listData)
	{		
		Iterator<List<String>> itr=null;
		Iterator<?> subItr=null;
		Iterator<?> headerItr=null;
		LinkedTreeMap<String,String> dataMap=null;
		String message="";
		int counter=0;
		if(listData!=null && listData.size()>1)
		{			
			try
			{
				itr=listData.iterator();
				while(itr.hasNext())
				{
					if(counter!=0)
					{
						try
						{
							headerItr=listData.get(0).iterator();
							subItr=itr.next().iterator();						
							dataMap=new LinkedTreeMap<String,String>();
							while(headerItr.hasNext() && subItr.hasNext())
							{
								dataMap.put(headerItr.next().toString(),subItr.next().toString());
							}
							message=message+"{"+convertMapToString(dataMap)+"},";
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						itr.next();
					}
					counter++;
				}
				if(message!=null && message.endsWith(","))
				{
					message=message.substring(0,message.length()-1);
				}				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
		return message;
	}
	public String convertList2String(List<List<String>> listData)
	{		
		Iterator<List<String>> itr=null;
		Iterator<?> subItr=null;
		Iterator<?> headerItr=null;
		LinkedTreeMap<String,String> dataMap=null;
		String message="";
		int counter=0;
		if(listData!=null && listData.size()>1)
		{			
			try
			{
				itr=listData.iterator();
				while(itr.hasNext())
				{
					if(counter!=0)
					{
						try
						{
							headerItr=listData.get(0).iterator();
							subItr=itr.next().iterator();						
							dataMap=new LinkedTreeMap<String,String>();
							while(headerItr.hasNext() && subItr.hasNext())
							{
								dataMap.put(headerItr.next().toString(),subItr.next().toString());
							}
							message=convertMapToString(dataMap);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						itr.next();
					}
					counter++;
				}
				if(message!=null && message.endsWith(","))
				{
					message=message.substring(0,message.length()-1);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
		return message;
	}
	public String convertMapToString(LinkedTreeMap<String, String> colDataMap)
	{
		StringBuilder stringBuilder = new StringBuilder();
		String value = null;
		try
		{		
			for (String key : colDataMap.keySet()) 
			{
				if (stringBuilder.length() > 0 && (!value.contains("["))) 
				{
					stringBuilder.append(",");
				}
				value = (String)colDataMap.get(key);
				try 
				{
					stringBuilder.append("\""+(key != null ? key : "")+"\"");
					stringBuilder.append(":");
					if(value!=null && (value.contains("[") || value.contains("]")))
					{
						stringBuilder.append(value);
					}
					else
					{
						stringBuilder.append("\""+(value != null ? value :"")+"\"");
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	@SuppressWarnings("unchecked")
	public static Map<String,Object> getGsonData(String jsonData)
	{
		JsonElement jsonElement=new JsonParser().parse(jsonData);
		Map<String,Object> data=new Gson().fromJson(jsonElement,Map.class);
		//System.out.println(data);			
		return data;
	}
	
	public static String tokenGenerator()
	{
		UUID uniqueKey = UUID.randomUUID();   
//		  System.out.println (uniqueKey);  
		return uniqueKey.toString();
	}

	public static void main(String ar[])
	{
//		System.out.println("We Are in Main");
	}
	
	
	public static byte[] getBytesFromInputStream(InputStream _ioInputStream) {
		byte[] bytes = null;
		ByteArrayOutputStream out = null;
		if (_ioInputStream != null) {
			try {
				out = new ByteArrayOutputStream(1024);
				byte[] buffer = new byte[1024];
				int len;
				try {
					while ((len = _ioInputStream.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					_ioInputStream.close();
					out.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			bytes = out.toByteArray();
		}
		return bytes;
	}
	
	
	
	
}