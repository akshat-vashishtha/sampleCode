package com.qualtech.icici.api.response;

public class MsgInfo {
	 private String code;

	    private String status;

	    private String message;

	    private String description;

	    public void setCode(String code){
	        this.code = code;
	    }
	    public String getCode(){
	        return this.code;
	    }
	    public void setStatus(String status){
	        this.status = status;
	    }
	    public String getStatus(){
	        return this.status;
	    }
	    public void setMessage(String message){
	        this.message = message;
	    }
	    public String getMessage(){
	        return this.message;
	    }
	    public void setDescription(String description){
	        this.description = description;
	    }
	    public String getDescription(){
	        return this.description;
	    }
}
