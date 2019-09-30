package com.qualtech.hdfc.api.utils;

import java.util.Random;
import java.util.UUID;

public class UniqueId {

	public static String getUniqueId()
	{
		UUID uniqueId = UUID.randomUUID();
		return ""+uniqueId;
	}
	
	public static String prnGenerator()
	{
		Random rnd = new Random();
		int n = 10000000 + rnd.nextInt(90000000);
		return "orix-"+n;
	}
}
