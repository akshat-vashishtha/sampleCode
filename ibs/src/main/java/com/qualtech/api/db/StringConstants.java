package com.qualtech.api.db;

public class StringConstants
{
	final public static String _200C="200"; 
	final public static String _200S="SUCCESS";
	final public static String _200M="Response generated successfully";
	final public static String _200D="Response generated successfully";
	final public static String _200D1="Fusion ID generated and Report received successfully";
	
	
	final public static String _500C="500"; 
	final public static String _500S="FAILURE";
	final public static String _500M="Something went wrong while processing. Please try after some time";
	final public static String _500D="Something went wrong while processing. Please try after some time";
	
	final public static String _600C="600"; 
	final public static String _600S="FAILURE";
	final public static String _600M="Something went wrong while processing. Please connect IT Team";
	final public static String _600D="Something went wrong while processing. Please connect IT Team";
	
	//Rishi for auth check
	final public static String _401C="401"; 
	final public static String _401S="FAILURE";
	final public static String _401M="Authorisation failure";
	final public static String _401D="Authorisation failure";
	
	///Retrive Report Format
	final public static String _601C="601"; 
	final public static String _601S="FAILURE";
	final public static String _601M="Mandatory field required";
	final public static String _601D="Invalid Report Format, Kindly try with valid one for getting data";
	final public static String _601D1="Mandatory fields are missing";
	final public static String _601D2="Invalid Destination. Must be one of netbankingFetch, or statement, bankAccountDetailsFetch";
	
	
	final public static String _601M1="Fusion ID not generated";
	final public static String _601M1D1="Error while creating Request xml for calling API";
	final public static String _601M1D2="Error while calling API - First Request : ACk request for getting Fusion id";
	final public static String _601M1D3="Error while reading response data of first api call";
	
	final public static String _601M2="Fusion ID generated, Report not received";
	final public static String _601M2D1="Error while calling API - Final Through fusionId Request";
	
}
