package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class KotakPayInstrument implements Serializable {

	private static final long serialVersionUID = -4475043506344265084L;
	/*@Id
	private long request_unique_id;
	
	public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}*/
	@Column(name="INST_REF_NO")		
	private String instRefNo;
	@Column(name="COMPANY_ID") 
	private String companyId;
	@Column(name="COMP_BATCH_ID")
	private String compBatchId;
	@Column(name="CONFIDENTIAL_IND")
	private String confidentialInd;
	@Column(name="MY_PROD_CODE")
	private String myProdCode;
	@Column(name="COMP_TRANS_NO")
	private String compTransNo;
	@Column(name="PAY_MODE")
	private String payMode;
	@Column(name="TXN_AMNT")
	private String txnAmnt;
	@Column(name="ACCOUNT_NO")
	private String accountNo;
	@Column(name="DR_REF_NMBR")
	private String drRefNmbr;
	@Column(name="DR_DESC")
	private String drDesc;
	@Column(name="PAYMENT_DT") 
	private String paymentDt;
	@Column(name="BANK_CD_IND")
	private String bankCdInd;
	@Column(name="BENE_BNK_CD") 
	private String beneBnkCd;
	@Column(name="REC_BR_CD") 
	private String recBrCd;
	@Column(name="BENE_ACCT_NO")
	private String beneAcctNo;
	@Column(name="BENE_NAME")
	private String beneName;
	@Column(name="BENE_CODE")
	private String beneCode;
	@Column(name="BENE_EMAIL")
	private String beneEmail;
	@Column(name="BENE_FAX")
	private String beneFax;
	@Column(name="BENE_MB") 
	private String beneMb;
	@Column(name="BENE_ADDR1")
	private String beneAddr1;
	@Column(name="BENE_ADDR2") 
	private String beneAddr2;
	@Column(name="BENE_ADDR3")
	private String beneAddr3;
	@Column(name="BENE_ADDR4") 
	private String beneAddr4;
	@Column(name="BENE_ADDR5")
	private String beneAddr5;
	@Column(name="CITY") 
	private String city;
	@Column(name="ZIP") 
	private String zip;
	@Column(name="COUNTRY") 
	private String country;
	@Column(name="STATE")
	private String state;
	@Column(name="TELEPHONE_NO")
	private String telephoneNo;
	@Column(name="BENE_ID")
	private String beneId;
	@Column(name="BENE_TAX_ID") 
	private String beneTaxId;
	@Column(name="AUTH_PERSON")
	private String authPerson;
	@Column(name="AUTH_PERSON_ID")
	private String authPersonId;
	@Column(name="DELIVERY_MODE")
	private String deliveryMode;
	@Column(name="PAY_OUT_LOC")
	private String payoutLoc;
	@Column(name="PICK_UP_BR")
	private String pickupBr;
	@Column(name="PAYMENT_REF")
	private String paymentRef;
	@Column(name="CHG_BORNE_BYV") 
	private String chgBorneBy;
	@Column(name="INST_DT")
	private String instDt;
	@Column(name="MICR_NO")
	private String micrNo;
	@Column(name="CREDIT_REF_NO")
	private String creditRefNo;
	@Column(name="PAYMENT_DTL") 
	private String paymentDtl;
	@Column(name="PAYMENT_DTL1")
	private String paymentDtl1;
	@Column(name="PAYMENT_DTL2")
	private String paymentDtl2;
	@Column(name="PAYMENT_DTL3") 
	private String paymentDtl3;
	@Column(name="MAIL_TO_ADDR1")
	private String mailToAddr1;
	@Column(name="MAIL_TO_ADDR2") 
	private String mailToAddr2;
	@Column(name="MAIL_TO_ADDR3")
	private String mailToAddr3;
	@Column(name="MAIL_TO_ADDR4") 
	private String mailToAddr4;
	@Column(name="MAIL_TO") 
	private String mailTo;
	@Column(name="EXCH_DOC")
	private String exchDoc;
	@Column(name="INST_CHECK_SUM")
	private String instChecksum;
	@Column(name="INST_RF1")
	private String instRF1;
	@Column(name="INST_RF2")
	private String instRF2;
	@Column(name="INST_RF3") 
	private String instRF3;
	@Column(name="INST_RF4")
	private String instRF4;
	@Column(name="INST_RF5")
	private String instRF5;
	@Column(name="INST_RF6")
	private String instRF6;
	@Column(name="INST_RF7")
	private String instRF7;
	@Column(name="INST_RF8")
	private String instRF8;
	@Column(name="INST_RF9")
	private String instRF9;
	@Column(name="INST_RF10")
	private String instRF10;
	@Column(name="INST_RF11")
	private String instRF11;
	@Column(name="INST_RF12")
	private String instRF12;
	@Column(name="INST_RF13")
	private String instRF13;
	@Column(name="INST_RF14") 
	private String instRF14;
	@Column(name="INST_RF15")
	private String instRF15;
	@Transient
	private KotakPayEnrichmentSet enrichmentSet;
	public String getInstRefNo() {
		return instRefNo;
	}
	public void setInstRefNo(String instRefNo) {
		this.instRefNo = instRefNo;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompBatchId() {
		return compBatchId;
	}
	public void setCompBatchId(String compBatchId) {
		this.compBatchId = compBatchId;
	}
	public String getConfidentialInd() {
		return confidentialInd;
	}
	public void setConfidentialInd(String confidentialInd) {
		this.confidentialInd = confidentialInd;
	}
	public String getMyProdCode() {
		return myProdCode;
	}
	public void setMyProdCode(String myProdCode) {
		this.myProdCode = myProdCode;
	}
	public String getCompTransNo() {
		return compTransNo;
	}
	public void setCompTransNo(String compTransNo) {
		this.compTransNo = compTransNo;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getTxnAmnt() {
		return txnAmnt;
	}
	public void setTxnAmnt(String txnAmnt) {
		this.txnAmnt = txnAmnt;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getDrRefNmbr() {
		return drRefNmbr;
	}
	public void setDrRefNmbr(String drRefNmbr) {
		this.drRefNmbr = drRefNmbr;
	}
	public String getDrDesc() {
		return drDesc;
	}
	public void setDrDesc(String drDesc) {
		this.drDesc = drDesc;
	}
	public String getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}
	public String getBankCdInd() {
		return bankCdInd;
	}
	public void setBankCdInd(String bankCdInd) {
		this.bankCdInd = bankCdInd;
	}
	public String getBeneBnkCd() {
		return beneBnkCd;
	}
	public void setBeneBnkCd(String beneBnkCd) {
		this.beneBnkCd = beneBnkCd;
	}
	public String getRecBrCd() {
		return recBrCd;
	}
	public void setRecBrCd(String recBrCd) {
		this.recBrCd = recBrCd;
	}
	public String getBeneAcctNo() {
		return beneAcctNo;
	}
	public void setBeneAcctNo(String beneAcctNo) {
		this.beneAcctNo = beneAcctNo;
	}
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public String getBeneCode() {
		return beneCode;
	}
	public void setBeneCode(String beneCode) {
		this.beneCode = beneCode;
	}
	public String getBeneEmail() {
		return beneEmail;
	}
	public void setBeneEmail(String beneEmail) {
		this.beneEmail = beneEmail;
	}
	public String getBeneFax() {
		return beneFax;
	}
	public void setBeneFax(String beneFax) {
		this.beneFax = beneFax;
	}
	public String getBeneMb() {
		return beneMb;
	}
	public void setBeneMb(String beneMb) {
		this.beneMb = beneMb;
	}
	public String getBeneAddr1() {
		return beneAddr1;
	}
	public void setBeneAddr1(String beneAddr1) {
		this.beneAddr1 = beneAddr1;
	}
	public String getBeneAddr2() {
		return beneAddr2;
	}
	public void setBeneAddr2(String beneAddr2) {
		this.beneAddr2 = beneAddr2;
	}
	public String getBeneAddr3() {
		return beneAddr3;
	}
	public void setBeneAddr3(String beneAddr3) {
		this.beneAddr3 = beneAddr3;
	}
	public String getBeneAddr4() {
		return beneAddr4;
	}
	public void setBeneAddr4(String beneAddr4) {
		this.beneAddr4 = beneAddr4;
	}
	public String getBeneAddr5() {
		return beneAddr5;
	}
	public void setBeneAddr5(String beneAddr5) {
		this.beneAddr5 = beneAddr5;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getBeneId() {
		return beneId;
	}
	public void setBeneId(String beneId) {
		this.beneId = beneId;
	}
	public String getAuthPersonId() {
		return authPersonId;
	}
	public void setAuthPersonId(String authPersonId) {
		this.authPersonId = authPersonId;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public String getPayoutLoc() {
		return payoutLoc;
	}
	public void setPayoutLoc(String payoutLoc) {
		this.payoutLoc = payoutLoc;
	}
	public String getPickupBr() {
		return pickupBr;
	}
	public void setPickupBr(String pickupBr) {
		this.pickupBr = pickupBr;
	}
	public String getPaymentRef() {
		return paymentRef;
	}
	public void setPaymentRef(String paymentRef) {
		this.paymentRef = paymentRef;
	}
	public String getChgBorneBy() {
		return chgBorneBy;
	}
	public void setChgBorneBy(String chgBorneBy) {
		this.chgBorneBy = chgBorneBy;
	}
	public String getInstDt() {
		return instDt;
	}
	public void setInstDt(String instDt) {
		this.instDt = instDt;
	}
	public String getMicrNo() {
		return micrNo;
	}
	public void setMicrNo(String micrNo) {
		this.micrNo = micrNo;
	}
	public String getCreditRefNo() {
		return creditRefNo;
	}
	public void setCreditRefNo(String creditRefNo) {
		this.creditRefNo = creditRefNo;
	}
	public String getPaymentDtl() {
		return paymentDtl;
	}
	public void setPaymentDtl(String paymentDtl) {
		this.paymentDtl = paymentDtl;
	}
	public String getPaymentDtl1() {
		return paymentDtl1;
	}
	public void setPaymentDtl1(String paymentDtl1) {
		this.paymentDtl1 = paymentDtl1;
	}
	public String getPaymentDtl2() {
		return paymentDtl2;
	}
	public void setPaymentDtl2(String paymentDtl2) {
		this.paymentDtl2 = paymentDtl2;
	}
	public String getPaymentDtl3() {
		return paymentDtl3;
	}
	public void setPaymentDtl3(String paymentDtl3) {
		this.paymentDtl3 = paymentDtl3;
	}
	public String getMailToAddr1() {
		return mailToAddr1;
	}
	public void setMailToAddr1(String mailToAddr1) {
		this.mailToAddr1 = mailToAddr1;
	}
	public String getMailToAddr2() {
		return mailToAddr2;
	}
	public void setMailToAddr2(String mailToAddr2) {
		this.mailToAddr2 = mailToAddr2;
	}
	public String getMailToAddr3() {
		return mailToAddr3;
	}
	public void setMailToAddr3(String mailToAddr3) {
		this.mailToAddr3 = mailToAddr3;
	}
	public String getMailToAddr4() {
		return mailToAddr4;
	}
	public void setMailToAddr4(String mailToAddr4) {
		this.mailToAddr4 = mailToAddr4;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getExchDoc() {
		return exchDoc;
	}
	public void setExchDoc(String exchDoc) {
		this.exchDoc = exchDoc;
	}
	public String getInstChecksum() {
		return instChecksum;
	}
	public void setInstChecksum(String instChecksum) {
		this.instChecksum = instChecksum;
	}
	public String getInstRF1() {
		return instRF1;
	}
	public void setInstRF1(String instRF1) {
		this.instRF1 = instRF1;
	}
	public String getInstRF2() {
		return instRF2;
	}
	public void setInstRF2(String instRF2) {
		this.instRF2 = instRF2;
	}
	public String getInstRF3() {
		return instRF3;
	}
	public void setInstRF3(String instRF3) {
		this.instRF3 = instRF3;
	}
	public String getInstRF4() {
		return instRF4;
	}
	public void setInstRF4(String instRF4) {
		this.instRF4 = instRF4;
	}
	public String getInstRF5() {
		return instRF5;
	}
	public void setInstRF5(String instRF5) {
		this.instRF5 = instRF5;
	}
	public String getInstRF6() {
		return instRF6;
	}
	public void setInstRF6(String instRF6) {
		this.instRF6 = instRF6;
	}
	public String getInstRF7() {
		return instRF7;
	}
	public void setInstRF7(String instRF7) {
		this.instRF7 = instRF7;
	}
	public String getInstRF8() {
		return instRF8;
	}
	public void setInstRF8(String instRF8) {
		this.instRF8 = instRF8;
	}
	public String getInstRF9() {
		return instRF9;
	}
	public void setInstRF9(String instRF9) {
		this.instRF9 = instRF9;
	}
	public String getInstRF10() {
		return instRF10;
	}
	public void setInstRF10(String instRF10) {
		this.instRF10 = instRF10;
	}
	public String getInstRF11() {
		return instRF11;
	}
	public void setInstRF11(String instRF11) {
		this.instRF11 = instRF11;
	}
	public String getInstRF12() {
		return instRF12;
	}
	public void setInstRF12(String instRF12) {
		this.instRF12 = instRF12;
	}
	public String getInstRF13() {
		return instRF13;
	}
	public void setInstRF13(String instRF13) {
		this.instRF13 = instRF13;
	}
	public String getInstRF14() {
		return instRF14;
	}
	public void setInstRF14(String instRF14) {
		this.instRF14 = instRF14;
	}
	public String getInstRF15() {
		return instRF15;
	}
	public void setInstRF15(String instRF15) {
		this.instRF15 = instRF15;
	}
	public KotakPayEnrichmentSet getEnrichmentSet() {
		return enrichmentSet;
	}
	public void setEnrichmentSet(KotakPayEnrichmentSet enrichmentSet) {
		this.enrichmentSet = enrichmentSet;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
public String getAuthPerson() {
	return authPerson;
}
public String getBeneTaxId() {
	return beneTaxId;
}
public void setAuthPerson(String authPerson) {
	this.authPerson = authPerson;
}
public void setBeneTaxId(String beneTaxId) {
	this.beneTaxId = beneTaxId;
}
@Override
public String toString() {
	return "KotakPayInstrument [ instRefNo=" + instRefNo + ", companyId=" + companyId
			+ ", compBatchId=" + compBatchId + ", confidentialInd="
			+ confidentialInd + ", myProdCode=" + myProdCode + ", compTransNo="
			+ compTransNo + ", payMode=" + payMode + ", txnAmnt=" + txnAmnt
			+ ", accountNo=" + accountNo + ", drRefNmbr=" + drRefNmbr
			+ ", drDesc=" + drDesc + ", paymentDt=" + paymentDt
			+ ", bankCdInd=" + bankCdInd + ", beneBnkCd=" + beneBnkCd
			+ ", recBrCd=" + recBrCd + ", beneAcctNo=" + beneAcctNo
			+ ", beneName=" + beneName + ", beneCode=" + beneCode
			+ ", beneEmail=" + beneEmail + ", beneFax=" + beneFax + ", beneMb="
			+ beneMb + ", beneAddr1=" + beneAddr1 + ", beneAddr2=" + beneAddr2
			+ ", beneAddr3=" + beneAddr3 + ", beneAddr4=" + beneAddr4
			+ ", beneAddr5=" + beneAddr5 + ", city=" + city + ", zip=" + zip
			+ ", country=" + country + ", state=" + state + ", telephoneNo="
			+ telephoneNo + ", beneId=" + beneId + ", beneTaxId=" + beneTaxId
			+ ", authPerson=" + authPerson + ", authPersonId=" + authPersonId
			+ ", deliveryMode=" + deliveryMode + ", payoutLoc=" + payoutLoc
			+ ", pickupBr=" + pickupBr + ", paymentRef=" + paymentRef
			+ ", chgBorneBy=" + chgBorneBy + ", instDt=" + instDt + ", micrNo="
			+ micrNo + ", creditRefNo=" + creditRefNo + ", paymentDtl="
			+ paymentDtl + ", paymentDtl1=" + paymentDtl1 + ", paymentDtl2="
			+ paymentDtl2 + ", paymentDtl3=" + paymentDtl3 + ", mailToAddr1="
			+ mailToAddr1 + ", mailToAddr2=" + mailToAddr2 + ", mailToAddr3="
			+ mailToAddr3 + ", mailToAddr4=" + mailToAddr4 + ", mailTo="
			+ mailTo + ", exchDoc=" + exchDoc + ", instChecksum="
			+ instChecksum + ", instRF1=" + instRF1 + ", instRF2=" + instRF2
			+ ", instRF3=" + instRF3 + ", instRF4=" + instRF4 + ", instRF5="
			+ instRF5 + ", instRF6=" + instRF6 + ", instRF7=" + instRF7
			+ ", instRF8=" + instRF8 + ", instRF9=" + instRF9 + ", instRF10="
			+ instRF10 + ", instRF11=" + instRF11 + ", instRF12=" + instRF12
			+ ", instRF13=" + instRF13 + ", instRF14=" + instRF14
			+ ", instRF15=" + instRF15 + ", enrichmentSet=" + enrichmentSet
			+ "]";
}



	

}
