package com.qualtech.api.ibs.util;

public enum ResquestStatus {

	SUCCESS("SUCCESS"),FAILURE("FAILURE"),INITIATED("INITIATED");

	 private String name;

	    private ResquestStatus(String stringVal)
	    {
	        name=stringVal;
	    }
	    public String toString(){
	        return name;
	    }



}
