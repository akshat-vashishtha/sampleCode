package com.qualtech.crif.api.utils;

import java.util.Map;

public class Match
{
	public static boolean matchName(Map<String,Object> requestMap, Map<String, String> responseMap)
	{
		String resFname = responseMap.get("FNAME")!=null?responseMap.get("FNAME").trim():"";
		String resMname = responseMap.get("MNAME")!=null?responseMap.get("MNAME").trim():"";
		String resLname = responseMap.get("LNAME")!=null?responseMap.get("LNAME").trim():"";
		String reqFname = requestMap.get("fname").toString().trim();
		if(reqFname.equalsIgnoreCase(resFname) || reqFname.equalsIgnoreCase(resMname) || reqFname.equalsIgnoreCase(resLname))
		{
			return true;
		}
		return false;
	}
	public static boolean matchPAN(Map<String,Object> requestMap, Map<String, String> responseMap)
	{
		if((responseMap.get("PANCODE")!=null?responseMap.get("PANCODE").trim():"").equalsIgnoreCase("E"))
		{
			String resPAN = responseMap.get("PANNO")!=null?responseMap.get("PANNO").trim():"";
			String reqPAN = requestMap.get("pan").toString().trim();
			if(reqPAN.equalsIgnoreCase(resPAN))
			{
				return true;
			}
		}
		return false;
	}
	public static boolean matchDOB(Map<String,Object> requestMap, Map<String, String> responseMap) throws Exception
	{
		if((responseMap.get("DOBCODE")==null || responseMap.get("DOBCODE").trim().equalsIgnoreCase("")))
		{
		String resDOB = responseMap.get("DOB")!=null?responseMap.get("DOB").toString():"1600-01-01";
		String reqDOB = requestMap.get("dob").toString();
		if(reqDOB.equalsIgnoreCase(resDOB))
		{
			return true;
		}
		return false;
		}
		return false;
	}
}
