package com.qualtech.icici.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PremCalcPayload implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PremCalcIciciRequest iciciRequest;

    public void setIciciRequest(PremCalcIciciRequest iciciRequest){
        this.iciciRequest = iciciRequest;
    }
    public PremCalcIciciRequest getIciciRequest(){
        return this.iciciRequest;
    }
	@Override
	public String toString() {
		return "PremCalcPayload [iciciRequest=" + iciciRequest + "]";
	}
    
    
}
