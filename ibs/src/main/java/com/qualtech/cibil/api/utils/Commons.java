package com.qualtech.cibil.api.utils;

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
import com.qualtech.cibil.api.entity.AddressDetails;


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
		return data;
	}
	
	public static String tokenGenerator()
	{
		UUID uniqueKey = UUID.randomUUID();   
		return uniqueKey.toString();
	}

	public static void main(String ar[])
	{
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
	
	public static String getLengthforCibil(String value) {
		if (value.length() > 9) {
			return value.length() + "";
		} else {
			return "0" + value.length();
		}
	}
	
	
	public static String getResponseFromTag(String tag, List<String> tagSeries, String completeResponse) {
		String requiredOutPut = "";
		int tagPosition = tagSeries.indexOf(tag);
		for (int i = (tagPosition + 1); i <= tagSeries.size(); i++) {
			int endPosition=0;
			if(i==tagSeries.size())
			{
				endPosition=completeResponse.length();
			}
			else
			{
				String nextTag = tagSeries.get(i);
				endPosition = completeResponse.indexOf(nextTag);
			}
			if (endPosition > -1) {
				int taginitialPosition = completeResponse.indexOf(tag);
				try {
					requiredOutPut = completeResponse.substring(taginitialPosition, endPosition);
				} catch (Exception ec) {
					requiredOutPut = "";
				}
				break;
			}
		}
		return requiredOutPut;
	}	
	public static String getResponseFromTagCustomForDOB(String tag, List<String> tagSeries, String completeResponse) {
		String requiredOutPut = "";
		int tagPosition = tagSeries.indexOf(tag);
		for (int i = (tagPosition + 1); i <= tagSeries.size(); i++)
		{
			int endPosition=0;
			if(i==tagSeries.size())
			{
				endPosition=completeResponse.length();
			}
			else
			{
				String nextTag = tagSeries.get(i);
				endPosition = completeResponse.lastIndexOf(nextTag);
			}
			if (endPosition > -1) 
			{
				int taginitialPosition = completeResponse.indexOf(tag);
				try 
				{
					requiredOutPut = completeResponse.substring(taginitialPosition, endPosition);
				}
				catch (Exception ec)
				{
					requiredOutPut = "";
				}
				break;
			}
		}
		return requiredOutPut;
	}	
	public static String getResponseFromTag1(String tag, List<String> tagSeries, String completeResponse) {
		String requiredOutPut = "";
		int tagPosition = tagSeries.indexOf(tag);
		for (int i = (tagPosition + 1); i <= tagSeries.size(); i++) {
			int endPosition=0;
			if(i==tagSeries.size())
			{
				endPosition=completeResponse.length();
			}
			else
			{
				String nextTag = tagSeries.get(i);
				endPosition = completeResponse.indexOf(nextTag);
			}
			
			if (endPosition > -1 || i == (tagSeries.size() - 1)) {
				int taginitialPosition = completeResponse.indexOf(tag);
				try 
				{
					if ((tagSeries.size() - 1) == i)
					{
						requiredOutPut = completeResponse.substring(taginitialPosition, completeResponse.length());
					}
					else
					{
						requiredOutPut = completeResponse.substring(taginitialPosition, endPosition);
					}
				} catch (Exception ec) {
					requiredOutPut = "";
				}
				break;
			}
		}
		return requiredOutPut;
	}	
	
	public static AddressDetails addressResponseDTO(String addressTag)
	{

		boolean nullcheck = true;
		AddressDetails addressresponsedto = new AddressDetails();
		try {

			int adressline1length = Integer.parseInt(addressTag.substring(9, 11));
			String addressline1 = addressTag.substring(11, (11 + adressline1length));
			if(addressline1!=null && !addressline1.equalsIgnoreCase(""))
			{
				nullcheck=false;
				addressresponsedto.setAddressline1(addressline1);
			}
			int completelength = 11 + adressline1length;
			if (addressTag.length() > (11 + adressline1length)
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 2) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String addressline2 = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(addressline2!=null && !addressline2.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setAddressline2(addressline2);
				}
				completelength = completelength + addresslength2 + 4;
			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 3) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String addressline3 = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(addressline3!=null && !addressline3.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setAddressline3(addressline3);
				}
				completelength = completelength + addresslength2 + 4;

			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 4) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String addressline4 = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(addressline4!=null && !addressline4.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setAddressline4(addressline4);
				}
				completelength = completelength + addresslength2 + 4;

			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 5) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String addressline5 = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(addressline5!=null && !addressline5.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setAddressline5(addressline5);
				}
				completelength = completelength + addresslength2 + 4;

			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 6) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String satecode = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(satecode!=null && !satecode.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setStateCode(CibilUtil.getApndxCStateCodeMAP(satecode));
				}
				completelength = completelength + addresslength2 + 4;

			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 7) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String pincode = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(pincode!=null && !pincode.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setPinCode(pincode);
				}
				completelength = completelength + addresslength2 + 4;

			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 8) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String addresscategory = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(addresscategory!=null && !addresscategory.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setAddressCategory(addresscategory);
				}
				completelength = completelength + addresslength2 + 4;

			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 9) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String residencedcode = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(residencedcode!=null && !residencedcode.equalsIgnoreCase(""))
				{
					if(residencedcode.equalsIgnoreCase("01"))
					{
						residencedcode="Owned";
					}
					else if(residencedcode.equalsIgnoreCase("02"))
					{
						residencedcode="Rented";
					}
				}
				if(residencedcode!=null && !residencedcode.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setResedenceCode(residencedcode);
				}
				completelength = completelength + addresslength2 + 4;

			}
			if (addressTag.length() > completelength
					&& Integer.parseInt(addressTag.substring(completelength, completelength + 2)) == 10) {
				int addresslength2 = Integer.parseInt(addressTag.substring(completelength + 2, completelength + 4));
				String datereported = addressTag.substring(completelength + 4, completelength + 4 + addresslength2);
				if(datereported!=null && !datereported.equalsIgnoreCase(""))
				{
					nullcheck=false;
					addressresponsedto.setDateReported(datereported);
				}
				completelength = completelength + addresslength2 + 4;
			}
		}
		catch (Exception ec)
		{
			logger.error("There is Error in addressResponseDTO( of commons");
		}
		if(nullcheck)
		{
			return null;
		}
		else
		{
			return addressresponsedto;
		}
	}
	
	public static String convertCibilEnquiryListing(int i)
	{
		if(i<10)
		{
			return "00"+i;
		}
		else if(i<100)
		{
			return "0"+i;
		}
		else
		{
			return ""+i;
		}
	}
}