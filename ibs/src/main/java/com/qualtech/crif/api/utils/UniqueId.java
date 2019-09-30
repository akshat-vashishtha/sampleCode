package com.qualtech.crif.api.utils;

import java.util.UUID;

public class UniqueId 
{
	public static String getUniqueId()
	{
		UUID uniqueId = UUID.randomUUID();
		return ""+uniqueId;
	}

	public static void main(String... arr)
	{
//		String requestUrl = "ASDF/qwer/asdjkgfdfjgjf";
//		System.out.println("qwer".indexOf(requestUrl)!=-1);
//		System.out.println(requestUrl.indexOf("qwer")!=-1);
		
//		EquifaxControllerMVC equifaxControllerMVC = new EquifaxControllerMVC();
//		equifaxControllerMVC.enterRequestJSONGUIPage();
	}
}
