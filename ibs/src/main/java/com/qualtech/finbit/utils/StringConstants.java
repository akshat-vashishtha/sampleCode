package com.qualtech.finbit.utils;

public enum StringConstants 
{
	SUCCESS("Success"),FAILURE("Failure")
	,C_200("200"),MESSAGE200("Response generated successfully.")
	,C_500("500"),MESSAGE500("Response failure from third party API")
	,C_501("501"),MESSAGE501("Third party API login authentication failure")
	,C_400("400"),MESSAGE400("Bad Request - from IBS Kindly check mandatory fields details")
	,C_401("401"),MESSAGE401("Unauthorized Access - from IBS")
	,C_600("600"),MESSAGE600("Server Error connect with IT Team - from IBS")
	,C_601("601"),MESSAGE601("Mandatory Validation Exception - from IBS");

	 private String name;

	    private StringConstants(String stringVal)
	    {
	        name=stringVal;
	    }
	    public String toString(){
	        return name;
	    }


}
