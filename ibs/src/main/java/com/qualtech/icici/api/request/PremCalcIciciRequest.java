package com.qualtech.icici.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PremCalcIciciRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PremCalc premiumCalculation;

    public void setPremiumCalculation(PremCalc premiumCalculation){
        this.premiumCalculation = premiumCalculation;
    }
    public PremCalc getPremiumCalculation(){
        return this.premiumCalculation;
    }
	@Override
	public String toString() {
		return "PremCalcIciciRequest [premiumCalculation=" + premiumCalculation + "]";
	}
    
    

}
