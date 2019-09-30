package com.qualtech.kotak.api.utils;

public enum StringConstants 
{
	SUCCESS("Success"),FAILURE("Failure")
	,C_200("200"),MESSAGE200("Response generated successfully.")
	,C_500("500"),MESSAGE500("Response failure from third party API")
	,C_400("400"),MESSAGE400("Bad Request")
	,C_401("401"),MESSAGE401("Unauthorized Access")
	,C_600("600"),MESSAGE600("Server Error")
	,C_601("601"),MESSAGE601("Mandatory Validation Exception");

	 private String name;

	    private StringConstants(String stringVal)
	    {
	        name=stringVal;
	    }
	    public String toString(){
	        return name;
	    }


}
