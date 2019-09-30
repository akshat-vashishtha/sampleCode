package com.qualtech.icici.api.response;

import java.io.Serializable;

public class CustOnBoardResponseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1602036150634283895L;
	
	
	private String responseCode;
	private String responseRemarks;
	private String transID;
	private String breaction;
	private String bredecision;
	private String modalpremium;
	private String annualpremiumwithtax;
	private String ismedical;
	private String showhncpopup;
	private String bre_requirments;
	private String cicounteroffer;
	private String basecounteroffer;
	private String lifeoption;
	private String adbrcounteroffer;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseRemarks() {
		return responseRemarks;
	}
	public void setResponseRemarks(String responseRemarks) {
		this.responseRemarks = responseRemarks;
	}
	public String getTransID() {
		return transID;
	}
	public void setTransID(String transID) {
		this.transID = transID;
	}
	public String getBreaction() {
		return breaction;
	}
	public void setBreaction(String breaction) {
		this.breaction = breaction;
	}
	public String getBredecision() {
		return bredecision;
	}
	public void setBredecision(String bredecision) {
		this.bredecision = bredecision;
	}
	public String getModalpremium() {
		return modalpremium;
	}
	public void setModalpremium(String modalpremium) {
		this.modalpremium = modalpremium;
	}
	public String getAnnualpremiumwithtax() {
		return annualpremiumwithtax;
	}
	public void setAnnualpremiumwithtax(String annualpremiumwithtax) {
		this.annualpremiumwithtax = annualpremiumwithtax;
	}
	public String getIsmedical() {
		return ismedical;
	}
	public void setIsmedical(String ismedical) {
		this.ismedical = ismedical;
	}
	public String getShowhncpopup() {
		return showhncpopup;
	}
	public void setShowhncpopup(String showhncpopup) {
		this.showhncpopup = showhncpopup;
	}
	public String getBre_requirments() {
		return bre_requirments;
	}
	public void setBre_requirments(String bre_requirments) {
		this.bre_requirments = bre_requirments;
	}
	public String getCicounteroffer() {
		return cicounteroffer;
	}
	public void setCicounteroffer(String cicounteroffer) {
		this.cicounteroffer = cicounteroffer;
	}
	public String getBasecounteroffer() {
		return basecounteroffer;
	}
	public void setBasecounteroffer(String basecounteroffer) {
		this.basecounteroffer = basecounteroffer;
	}
	public String getLifeoption() {
		return lifeoption;
	}
	public void setLifeoption(String lifeoption) {
		this.lifeoption = lifeoption;
	}
	public String getAdbrcounteroffer() {
		return adbrcounteroffer;
	}
	public void setAdbrcounteroffer(String adbrcounteroffer) {
		this.adbrcounteroffer = adbrcounteroffer;
	}
	@Override
	public String toString() {
		return "CustOnBoardResponseResult [responseCode=" + responseCode + ", responseRemarks=" + responseRemarks
				+ ", transID=" + transID + ", breaction=" + breaction + ", bredecision=" + bredecision
				+ ", modalpremium=" + modalpremium + ", annualpremiumwithtax=" + annualpremiumwithtax + ", ismedical="
				+ ismedical + ", showhncpopup=" + showhncpopup + ", bre_requirments=" + bre_requirments
				+ ", cicounteroffer=" + cicounteroffer + ", basecounteroffer=" + basecounteroffer + ", lifeoption="
				+ lifeoption + ", adbrcounteroffer=" + adbrcounteroffer + "]";
	}
	
    
}
