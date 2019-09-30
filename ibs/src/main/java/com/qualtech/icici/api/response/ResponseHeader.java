package com.qualtech.icici.api.response;

public class ResponseHeader {
	 private String msgVersion;

	    private String appId;

	    private String correlationId;

	    private String token;

	    public void setMsgVersion(String msgVersion){
	        this.msgVersion = msgVersion;
	    }
	    public String getMsgVersion(){
	        return this.msgVersion;
	    }
	    public void setAppId(String appId){
	        this.appId = appId;
	    }
	    public String getAppId(){
	        return this.appId;
	    }
	    public void setCorrelationId(String correlationId){
	        this.correlationId = correlationId;
	    }
	    public String getCorrelationId(){
	        return this.correlationId;
	    }
	    public void setToken(String token){
	        this.token = token;
	    }
	    public String getToken(){
	        return this.token;
	    }
}
