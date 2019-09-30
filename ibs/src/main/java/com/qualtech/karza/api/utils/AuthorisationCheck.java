package com.qualtech.karza.api.utils;

import java.util.HashMap;

public class AuthorisationCheck {
	
	public static HashMap<String,String> checkAuthenticationCriteria(){
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("APPID", "KARZA@1234");
		hm.put("TOKEN", "987654321");
		hm.put("APPID", "KARZA@9871");
		hm.put("TOKEN", "123456789");
		
		return hm;
	}

}
