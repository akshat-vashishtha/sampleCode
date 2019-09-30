
package com.qualtech.icici.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_IC_CUST_RES")
public class CustomerOnBoardPayload implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	private int eid;
	private String isMedical;
    private String transID;
    private String breAction;
    private String modalPremium;
    private String baseCounterOffer;
    private String url;
    private String responseCode;
    private String responseRemarks;
    private String breDecision;
    private String ciCounterOffer;
    private String lifeOption;
    private String adbrCounterOffer;
    private String showHncPopUp;
    private String breRequirments;
    private String annualPremiumWithTax;
	
    
    
    public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getIsMedical() {
		return isMedical;
	}

	public void setIsMedical(String isMedical) {
		this.isMedical = isMedical;
	}

	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public String getBreAction() {
		return breAction;
	}

	public void setBreAction(String breAction) {
		this.breAction = breAction;
	}

	public String getModalPremium() {
		return modalPremium;
	}

	public void setModalPremium(String modalPremium) {
		this.modalPremium = modalPremium;
	}

	public String getBaseCounterOffer() {
		return baseCounterOffer;
	}

	public void setBaseCounterOffer(String baseCounterOffer) {
		this.baseCounterOffer = baseCounterOffer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getBreDecision() {
		return breDecision;
	}

	public void setBreDecision(String breDecision) {
		this.breDecision = breDecision;
	}

	public String getCiCounterOffer() {
		return ciCounterOffer;
	}

	public void setCiCounterOffer(String ciCounterOffer) {
		this.ciCounterOffer = ciCounterOffer;
	}

	public String getLifeOption() {
		return lifeOption;
	}

	public void setLifeOption(String lifeOption) {
		this.lifeOption = lifeOption;
	}

	public String getAdbrCounterOffer() {
		return adbrCounterOffer;
	}

	public void setAdbrCounterOffer(String adbrCounterOffer) {
		this.adbrCounterOffer = adbrCounterOffer;
	}

	public String getShowHncPopUp() {
		return showHncPopUp;
	}

	public void setShowHncPopUp(String showHncPopUp) {
		this.showHncPopUp = showHncPopUp;
	}

	public String getBreRequirments() {
		return breRequirments;
	}

	public void setBreRequirments(String breRequirments) {
		this.breRequirments = breRequirments;
	}

	public String getAnnualPremiumWithTax() {
		return annualPremiumWithTax;
	}

	public void setAnnualPremiumWithTax(String annualPremiumWithTax) {
		this.annualPremiumWithTax = annualPremiumWithTax;
	}

	@Override
	public String toString() {
		return "CustomerOnBoardPayload [eid=" + eid + ", isMedical=" + isMedical + ", transID=" + transID
				+ ", breAction=" + breAction + ", modalPremium=" + modalPremium + ", baseCounterOffer="
				+ baseCounterOffer + ", url=" + url + ", responseCode=" + responseCode + ", responseRemarks="
				+ responseRemarks + ", breDecision=" + breDecision + ", ciCounterOffer=" + ciCounterOffer
				+ ", lifeOption=" + lifeOption + ", adbrCounterOffer=" + adbrCounterOffer + ", showHncPopUp="
				+ showHncPopUp + ", breRequirments=" + breRequirments + ", annualPremiumWithTax=" + annualPremiumWithTax
				+ "]";
	}

	

}