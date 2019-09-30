package com.qualtech.creditvidya.api.utils;

import java.util.HashMap;

public class AuthorisationCheck {
	
	public static HashMap<String,String> checkAuthenticationCriteria(){
		HashMap<String,String> hm=new HashMap<String, String>();
		hm.put("APPID", "CREDIT@1234");
		hm.put("TOKEN", "987654321");
		return hm;
	}

}
