package com.qualtech.icici.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PremCalcRequest implements Serializable {
	
	private static final long serialVersionUID = -3642044125636839502L;

	private Header header;

	    private PremCalcPayload payload;

	    public void setHeader(Header header){
	        this.header = header;
	    }
	    public Header getHeader(){
	        return this.header;
	    }
	    public void setPayload(PremCalcPayload payload){
	        this.payload = payload;
	    }
	    public PremCalcPayload getPayload(){
	        return this.payload;
	    }
	    
	    
}
