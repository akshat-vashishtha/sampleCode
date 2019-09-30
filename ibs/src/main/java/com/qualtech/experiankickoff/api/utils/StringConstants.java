package com.qualtech.experiankickoff.api.utils;

public enum StringConstants {
	
	SUCCESS("Success"),FAILURE("Failure")
	,C_101("101"),MESSAGE101("Connection Created Successfully.")
	,C_200("200"),MESSAGE200("Connection Created Successfully.")
	,C_400("400"),MESSAGE400("Bad Request")
	,C_500("500"),MESSAGE500("Server Failure")
	,C_600("600"),MESSAGE600("Server Error");
	
	
	
	/*SUCCESS("Success"),FAILURE("Failure")
	,C_200("200"),MESSAGE200("Connection Created Successfully.")
	,C_500("500"),MESSAGE500("Response failure from third party API")
	,C_400("400"),MESSAGE400("Bad Request")
	,C_401("401"),MESSAGE401("Unauthorised Access")
	,C_600("600"),MESSAGE600("Server Error")
	,C_601("601"),MESSAGE601("Mandatory Validation Exception")
	,C_602("602"),MESSAGE602("The server did not respond")
	,C_101("101"),MESSAGE101("Consumer record not found")
	,C_102("102"),MESSAGE102("Email Validation Failed or phone Validation Failed. Please try to invoke CRQ externally")
	,C_103("103"),MESSAGE103("Unauthorised client name")
	,C_104("104"),MESSAGE104("Voucher Code is invalid")
	,C_105("105"),MESSAGE105("Connection Created Successfully.");*/
	
	 private String name;

	    private StringConstants(String stringVal) {
	        name=stringVal;
	    }
	    public String toString(){
	        return name;
	    }

}
