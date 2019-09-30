package com.qualtech.icici.api.request;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/*@Entity
@Table(name="IB_ICICI_CST_REQ")*/
public class CustomerOnBoardRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	/*@Id
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_REQ_SEQ")
	@Column(name="EID", unique = true, nullable = false)*/
	private int eid;
	
	/*@Transient
	@OneToOne (fetch=FetchType.LAZY,mappedBy="customerOnBoard", cascade=CascadeType.ALL)*/
	private AdvisorSalesDetails advisorSalesDetails;
	
	/*@Transient
	@OneToOne (fetch=FetchType.LAZY,mappedBy="customerOnBoard", cascade=CascadeType.ALL)*/
	private NomineeInfos nomineeInfos;
	
	/*@Transient
	@OneToOne (fetch=FetchType.LAZY,mappedBy="customerOnBoard", cascade=CascadeType.ALL)*/
	private ProductSelection productSelection;
	
	/*@Transient
	@OneToOne (fetch=FetchType.LAZY,mappedBy="customerOnBoard", cascade=CascadeType.ALL)*/
	private PaymentData paymentData;
	
	/*@Transient
	@OneToOne (fetch=FetchType.LAZY,mappedBy="customerOnBoard", cascade=CascadeType.ALL)*/
	private ProposerInfos proposerInfos;
	
	/*@Transient
	@OneToMany(fetch=FetchType.LAZY,mappedBy="customerOnBoard", cascade=CascadeType.ALL)*/
	private List<HealthDetails> healthDetails;
	
	private String advisorCode;
	private String buyersPinCode;
	private String clientId;
	private String dependentFlag;
	private String salesDataReqd;
	private String sellersPinCode;
	private String source;
	private String sourceKey;
	private String sourceOfFund;
	private String sourceOfFundDesc;
	private String sourceTransactionId;
	private String uidId;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}

	public AdvisorSalesDetails getAdvisorSalesDetails() {
		advisorSalesDetails.setCustomerOnBoard(this);
		return advisorSalesDetails;
	}

	public void setAdvisorSalesDetails(AdvisorSalesDetails advisorSalesDetails) {
		this.advisorSalesDetails = advisorSalesDetails;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public List<HealthDetails> getHealthDetails() {
		for(HealthDetails eachHealthDetail : healthDetails){
			eachHealthDetail.setCustomerOnBoard(this);
		}
		return healthDetails;
	}

	public void setHealthDetails(List<HealthDetails> healthDetails) {
		this.healthDetails = healthDetails;
	}

	public String getSalesDataReqd() {
		return salesDataReqd;
	}

	public void setSalesDataReqd(String salesDataReqd) {
		this.salesDataReqd = salesDataReqd;
	}

	public String getSellersPinCode() {
		return sellersPinCode;
	}

	public void setSellersPinCode(String sellersPinCode) {
		this.sellersPinCode = sellersPinCode;
	}

	public NomineeInfos getNomineeInfos() {
		nomineeInfos.setCustomerOnBoard(this);
		return nomineeInfos;
	}

	public void setNomineeInfos(NomineeInfos nomineeInfos) {
		this.nomineeInfos = nomineeInfos;
	}

	public String getUidId() {
		return uidId;
	}

	public void setUidId(String uidId) {
		this.uidId = uidId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public ProductSelection getProductSelection() {
		productSelection.setCustomerOnBoard(this);
		return productSelection;
	}

	public void setProductSelection(ProductSelection productSelection) {
		this.productSelection = productSelection;
	}

	public String getDependentFlag() {
		return dependentFlag;
	}

	public void setDependentFlag(String dependentFlag) {
		this.dependentFlag = dependentFlag;
	}

	public ProposerInfos getProposerInfos() {
		proposerInfos.setCustomerOnBoard(this);
		return proposerInfos;
	}

	public void setProposerInfos(ProposerInfos proposerInfos) {
		this.proposerInfos = proposerInfos;
	}

	public String getSourceOfFundDesc() {
		return sourceOfFundDesc;
	}

	public void setSourceOfFundDesc(String sourceOfFundDesc) {
		this.sourceOfFundDesc = sourceOfFundDesc;
	}

	public String getAdvisorCode() {
		return advisorCode;
	}

	public void setAdvisorCode(String advisorCode) {
		this.advisorCode = advisorCode;
	}

	public PaymentData getPaymentData() {
		paymentData.setCustomerOnBoard(this);
		return paymentData;
	}

	public void setPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
	}

	public String getBuyersPinCode() {
		return buyersPinCode;
	}

	public void setBuyersPinCode(String buyersPinCode) {
		this.buyersPinCode = buyersPinCode;
	}

	@Override
	public String toString() {
		return "CustomerOnBoardRequest [eid=" + eid + ", sourceKey=" + sourceKey + ", advisorSalesDetails="
				+ advisorSalesDetails + ", clientId=" + clientId + ", healthDetails=" + healthDetails
				+ ", salesDataReqd=" + salesDataReqd + ", sellersPinCode=" + sellersPinCode + ", nomineeInfos="
				+ nomineeInfos + ", uidId=" + uidId + ", source=" + source + ", sourceTransactionId="
				+ sourceTransactionId + ", sourceOfFund=" + sourceOfFund + ", productSelection=" + productSelection
				+ ", dependentFlag=" + dependentFlag + ", proposerInfos=" + proposerInfos + ", sourceOfFundDesc="
				+ sourceOfFundDesc + ", advisorCode=" + advisorCode + ", paymentData=" + paymentData
				+ ", buyersPinCode=" + buyersPinCode + "]";
	}

}