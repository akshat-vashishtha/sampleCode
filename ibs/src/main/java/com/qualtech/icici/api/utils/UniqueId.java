package com.qualtech.icici.api.utils;

import java.util.UUID;

public class UniqueId 
{
	
	private UniqueId(){}
	
	public static String getUniqueId()
	{
		UUID uniqueId = UUID.randomUUID();
		return ""+uniqueId;
	}

	
}
