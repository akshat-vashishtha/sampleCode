package com.qualtech.icici.api.request;

import java.io.Serializable;
import java.util.List;

public class CustOnBoardResuestPayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3442914853708363402L;
	
	private String advisorCode;
	private String source;
	private String sourceKey;
	private String salesDataReqd;
	private String dependentFlag;
	private String sourceTransactionId;
	private String sourceOfFund;
	private String sourceOfFundDesc;
	private String buyersPinCode;
	private String sellersPinCode;
	private String clientId;
	private String uidId;
	private ProposerInfos proposerInfos;
	private List<HealthDetails> healthDetails; 
	private ProductSelection productSelection;
	private NomineeInfos nomineeInfos;
	private AdvisorSalesDetails advisorSalesDetails;
	private PaymentData paymentData;
	public String getAdvisorCode() {
		return advisorCode;
	}
	public void setAdvisorCode(String advisorCode) {
		this.advisorCode = advisorCode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceKey() {
		return sourceKey;
	}
	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}
	public String getSalesDataReqd() {
		return salesDataReqd;
	}
	public void setSalesDataReqd(String salesDataReqd) {
		this.salesDataReqd = salesDataReqd;
	}
	public String getDependentFlag() {
		return dependentFlag;
	}
	public void setDependentFlag(String dependentFlag) {
		this.dependentFlag = dependentFlag;
	}
	public String getSourceTransactionId() {
		return sourceTransactionId;
	}
	public void setSourceTransactionId(String sourceTransactionId) {
		this.sourceTransactionId = sourceTransactionId;
	}
	public String getSourceOfFund() {
		return sourceOfFund;
	}
	public void setSourceOfFund(String sourceOfFund) {
		this.sourceOfFund = sourceOfFund;
	}
	public String getSourceOfFundDesc() {
		return sourceOfFundDesc;
	}
	public void setSourceOfFundDesc(String sourceOfFundDesc) {
		this.sourceOfFundDesc = sourceOfFundDesc;
	}
	public String getBuyersPinCode() {
		return buyersPinCode;
	}
	public void setBuyersPinCode(String buyersPinCode) {
		this.buyersPinCode = buyersPinCode;
	}
	public String getSellersPinCode() {
		return sellersPinCode;
	}
	public void setSellersPinCode(String sellersPinCode) {
		this.sellersPinCode = sellersPinCode;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getUidId() {
		return uidId;
	}
	public void setUidId(String uidId) {
		this.uidId = uidId;
	}
	public ProposerInfos getProposerInfos() {
		return proposerInfos;
	}
	public void setProposerInfos(ProposerInfos proposerInfos) {
		this.proposerInfos = proposerInfos;
	}
	public List<HealthDetails> getHealthDetails() {
		return healthDetails;
	}
	public void setHealthDetails(List<HealthDetails> healthDetails) {
		this.healthDetails = healthDetails;
	}
	public ProductSelection getProductSelection() {
		return productSelection;
	}
	public void setProductSelection(ProductSelection productSelection) {
		this.productSelection = productSelection;
	}
	public NomineeInfos getNomineeInfos() {
		return nomineeInfos;
	}
	public void setNomineeInfos(NomineeInfos nomineeInfos) {
		this.nomineeInfos = nomineeInfos;
	}
	public AdvisorSalesDetails getAdvisorSalesDetails() {
		return advisorSalesDetails;
	}
	public void setAdvisorSalesDetails(AdvisorSalesDetails advisorSalesDetails) {
		this.advisorSalesDetails = advisorSalesDetails;
	}
	public PaymentData getPaymentData() {
		return paymentData;
	}
	public void setPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
	}
	@Override
	public String toString() {
		return "CustOnBoardResuestPayload [advisorCode=" + advisorCode + ", source=" + source + ", sourceKey="
				+ sourceKey + ", salesDataReqd=" + salesDataReqd + ", dependentFlag=" + dependentFlag
				+ ", sourceTransactionId=" + sourceTransactionId + ", sourceOfFund=" + sourceOfFund
				+ ", sourceOfFundDesc=" + sourceOfFundDesc + ", buyersPinCode=" + buyersPinCode + ", sellersPinCode="
				+ sellersPinCode + ", clientId=" + clientId + ", uidId=" + uidId + ", proposerInfos=" + proposerInfos
				+ ", healthDetails=" + healthDetails + ", productSelection=" + productSelection + ", nomineeInfos="
				+ nomineeInfos + ", advisorSalesDetails=" + advisorSalesDetails + ", paymentData=" + paymentData + "]";
	}
	
	
	
	
	
	

}
