package com.qualtech.cibil.api.utils;

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
	}
}
