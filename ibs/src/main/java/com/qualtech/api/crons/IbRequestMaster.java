package com.qualtech.api.crons;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "IBS_REQUEST_MASTER")
//@Table(name="IB_REQUEST_MASTER")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IbRequestMaster implements Cloneable{

	private String service_provider;
	@Id
	@GeneratedValue(generator = "IBS_SQC")
	@SequenceGenerator(name = "IBS_SQC", sequenceName = "IBS_SEQ_ID_SQC")
	private long seq_id;
	private String transaction;
	private String request_status;
	private String f_name;
	private String m_name;
	private String l_name;
	private String dob;
	private String key_person_name;
	private String key_person_type;
	private String nominee_name;
	private String nominee_type;
	private String idname1;
	private String idno1;
	private String idname2;
	private String idno2;
	private String idname3;
	private String idno3;
	private String idname4;
	private String idno4;
	private String idname5;
	private String idno5;
	private String idname6;
	private String idno6;
	private String relation_name1;
	private String relation1;
	private String relation_name2;
	private String relation2;
	private String relation_name3;
	private String relation3;
	private String relation_name4;
	private String relation4;
	private String relation_name5;
	private String relation5;
	private String tele_no1;
	private String tele_type1;
	private String tele_no2;
	private String tele_type2;
	private String tele_no3;
	private String tele_type3;
	private String tele_no4;
	private String tele_type4;
	private String tele_no5;
	private String tele_type5;
	private String address1;
	private String pin1;
	private String address_type1;
	private String state1;
	private String city1;
	private String address2;
	private String pin2;
	private String address_type2;
	private String state2;
	private String city2;
	private String address3;
	private String pin3;
	private String address_type3;
	private String state3;
	private String city3;
	private String address4;
	private String pin4;
	private String address_type4;
	private String state4;
	private String city4;
	private String address5;
	private String pin5;
	private String address_type5;
	private String state5;
	private String city5;
	private String amount;
	private String inquiry_purpose;
	private String scoretype;
	private String gender;
	private String tele_extn1;
	private String tele_extn2;
	private String tele_extn3;
	private String tele_extn4;
	private String tele_extn5;
	private String stg_one_hit_id;
	private String stg_two_hit_id;
	private String actual_email_addr;
	private String single_action_session_id;
	private String captch_code;
	private String residence_code1;
	private String residence_code2;
	private String residence_code3;
	private String residence_code4;
	private String residence_code5;

	private String clientName;
	private String allowInput;
	private String allowEdit;
	private String allowCaptcha;
	private String allowConsent;
	private String allowConsent_additional;
	private String allowEmailVerify;
	private String allowVoucher;
	private String voucherCode;
	private String noValidationByPass;
	private String emailConditionalByPass;

	private String flatno;
	private String buildingName;
	private String roadName;
	private String consumer_id;
	private String lpg_id;
	private String karza_service_provider;
	private String leadId;
	private String transactionId;
	private String losId;
	private String uniqueId;
	private String companyName;


	private String officeAddressLine1;
	private String officeAddressLine2;
	private String officeAddressLine3;
	private String officeCity;
	private String officeState;
	private String officePinCode;

	private String MessageId;
	private String MsgSource;
	private String ClientCode;
	private String BatchRefNmbr;
	private String HeaderChecksum;
	private String ReqRF1;
	private String ReqRF2;
	private String ReqRF3;
	private String ReqRF4;
	private String ReqRF5;
	private String InstRefNo;
	private String CompanyId;
	private String compBatchId;
	private String confidentialInd;
	private String myProdCode;
	private String compTransNo;
	private String payMode;
	private String txnAmnt;
	private String accountNo;
	private String drRefNmbr;
	private String drDesc;
	private String paymentDt;
	private String bankCdInd;
	private String beneBnkCd;
	private String recBrCd;
	private String beneAcctNo;
	private String beneName;
	private String beneCode;
	private String beneEmail;
	private String beneFax;
	private String beneMb;
	private String beneAddr1;
	private String beneAddr2;
	private String beneAddr3;
	private String beneAddr4;
	private String beneAddr5;
	private String city;
	private String zip;
	private String country;
	private String state;
	private String telephoneNo;
	private String beneId;
	private String beneTaxId;
	private String authPerson;
	private String authPersonId;
	private String deliveryMode;
	private String payoutLoc;
	private String pickupBr;
	private String paymentRef;
	private String chgBorneBy;
	private String instDt;
	private String micrNo;
	private String creditRefNo;
	private String paymentDtl;
	private String paymentDtl1;
	private String paymentDtl2;
	private String paymentDtl3;
	private String mailToAddr1;
	private String mailToAddr2;
	private String mailToAddr3;
	private String mailToAddr4;
	private String mailTo;
	private String exchDoc;
	private String instChecksum;
	private String instRF1;
	private String instRF2;
	private String instRF3;
	private String instRF4;
	private String instRF5;
	private String instRF6;
	private String instRF7;
	private String instRF8;
	private String instRF9;
	private String instRF10;
	private String instRF11;
	private String instRF12;
	private String instRF13;
	private String instRF14;
	private String instRF15;
	private String enrichment;

	//private String epic_no;

	private String tan;

	private String cin;
	private String iec;
	private String gstin;
	private String companyNameKarza;
	private String reg_no;
	private String area_code;
	private String aadhar;
	private String uan;
	private String jobcardid;
	private String fullname;
	private String shortname;
	private String ifsc;
	private String domain;
	private String pan;
	private String fiscal_year;
	private String nameType;
	private String cert_no;
	private String esic_id;
	private String bp_no;
	private String membership_no;
	private String engineNo;
	private String chassisNo;
	private String request_id;
	private String otp;
	private String ack;
	private String input;
	private String cp_no;
	private String hsCode;
	private String doe;
	private String passportType;
	private String addressMatch1;
	private String addressMatch2;


	private String bankstatement;
	private String bankname;
	private String password;

	private String maritalStatus;
	private String priority;
	private String productType;
	private String loanType;
	private String loanAmount;
	private String jointInd;
	private String inquirySubmitted_by;
	private String sourceSystemName;
	private String sourceSystemVersion;
	private String sourceSystemVender;
	private String sourceSystem_instance_id;
	private String loanPurposeDesc;
	private String branchIfsccode;
	private String kendra;
	private String inquiryStage;
	private String authrizationflag;
	private String authroizedBy;
	private String individualCorporate_flag;
	private String constitution;
	private String noOfDependents;
	private String alias;
	private String actOpeningDt;
	private String accountNumber1;
	private String accountNumber2;
	private String accountNumber3;
	private String accountNumber4;
	private String tanure;
	private String groupId;
	private String numberCreditCards;
	private String creditCardNo;
	private String monthlyIncome;
	private String soaEmployerNameC;
	private String timeWithEmploy;
	private String companyCategory;
	private String natureOfBusiness;
	private String assetCost;
	private String collateral1;
	private String collateral1Valuation1;
	private String collateral1Valuation2;
	private String collateral2Valuation1;
	private String collateral2Valuation2;
	private String collateral2;

	private String title;
	private String partnerId;
	private String prn;
	private String plan;
	private String ipAddress;
	private String sumAssuredType;
	private String sumAssured;
	private String loanDisbursement_date;
	private String basicPremium;
	private String policyTerm;
	private String loanTenure;
	private String premiumPayable;
	private String isAgreementGenerated;
	private String street;
	private String encryptedAuthSalt;
	private String house;

	private String sendMIFToEmail;
	private String sendMIFToSms;
	private String sendCOIToEmail;
	private String sendCOIUrlToSms;
	private String sendCOIToSms;
	private String fundingOption;
	
	
	private String questionnaireId;
	private String hdfcQueOne;
    private String hdfcQueTwo;
    private String hdfcQueThree;
    private String hdfcQueFour;
    private String hdfcQueFive;
    private String hdfcQueSix;
    private String hdfcQueSeven;
    private String hdfcQueEight;
    private String hdfcQueNine;
    
    private String rcAuthRegNumber;


    public String getRcAuthRegNumber() {
    	return rcAuthRegNumber;
    }

    public void setRcAuthRegNumber(String rcAuthRegNumber) {
    	this.rcAuthRegNumber = rcAuthRegNumber;
    }

    
    
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getHdfcQueOne() {
		return hdfcQueOne;
	}
	public void setHdfcQueOne(String hdfcQueOne) {
		this.hdfcQueOne = hdfcQueOne;
	}
	public String getHdfcQueTwo() {
		return hdfcQueTwo;
	}
	public void setHdfcQueTwo(String hdfcQueTwo) {
		this.hdfcQueTwo = hdfcQueTwo;
	}
	public String getHdfcQueThree() {
		return hdfcQueThree;
	}
	public void setHdfcQueThree(String hdfcQueThree) {
		this.hdfcQueThree = hdfcQueThree;
	}
	public String getHdfcQueFour() {
		return hdfcQueFour;
	}
	public void setHdfcQueFour(String hdfcQueFour) {
		this.hdfcQueFour = hdfcQueFour;
	}
	public String getHdfcQueFive() {
		return hdfcQueFive;
	}
	public void setHdfcQueFive(String hdfcQueFive) {
		this.hdfcQueFive = hdfcQueFive;
	}
	public String getHdfcQueSix() {
		return hdfcQueSix;
	}
	public void setHdfcQueSix(String hdfcQueSix) {
		this.hdfcQueSix = hdfcQueSix;
	}
	public String getHdfcQueSeven() {
		return hdfcQueSeven;
	}
	public void setHdfcQueSeven(String hdfcQueSeven) {
		this.hdfcQueSeven = hdfcQueSeven;
	}
	public String getHdfcQueEight() {
		return hdfcQueEight;
	}
	public void setHdfcQueEight(String hdfcQueEight) {
		this.hdfcQueEight = hdfcQueEight;
	}
	public String getHdfcQueNine() {
		return hdfcQueNine;
	}
	public void setHdfcQueNine(String hdfcQueNine) {
		this.hdfcQueNine = hdfcQueNine;
	}
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSumAssuredType() {
		return sumAssuredType;
	}

	public void setSumAssuredType(String sumAssuredType) {
		this.sumAssuredType = sumAssuredType;
	}

	public String getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}

	public String getLoanDisbursement_date() {
		return loanDisbursement_date;
	}

	public void setLoanDisbursement_date(String loanDisbursement_date) {
		this.loanDisbursement_date = loanDisbursement_date;
	}

	public String getBasicPremium() {
		return basicPremium;
	}

	public void setBasicPremium(String basicPremium) {
		this.basicPremium = basicPremium;
	}

	public String getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(String loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getPremiumPayable() {
		return premiumPayable;
	}

	public void setPremiumPayable(String premiumPayable) {
		this.premiumPayable = premiumPayable;
	}

	public String getIsAgreementGenerated() {
		return isAgreementGenerated;
	}

	public void setIsAgreementGenerated(String isAgreementGenerated) {
		this.isAgreementGenerated = isAgreementGenerated;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getEncryptedAuthSalt() {
		return encryptedAuthSalt;
	}

	public void setEncryptedAuthSalt(String encryptedAuthSalt) {
		this.encryptedAuthSalt = encryptedAuthSalt;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getSendMIFToEmail() {
		return sendMIFToEmail;
	}

	public void setSendMIFToEmail(String sendMIFToEmail) {
		this.sendMIFToEmail = sendMIFToEmail;
	}

	public String getSendMIFToSms() {
		return sendMIFToSms;
	}

	public void setSendMIFToSms(String sendMIFToSms) {
		this.sendMIFToSms = sendMIFToSms;
	}

	public String getSendCOIToEmail() {
		return sendCOIToEmail;
	}

	public void setSendCOIToEmail(String sendCOIToEmail) {
		this.sendCOIToEmail = sendCOIToEmail;
	}

	public String getSendCOIUrlToSms() {
		return sendCOIUrlToSms;
	}

	public void setSendCOIUrlToSms(String sendCOIUrlToSms) {
		this.sendCOIUrlToSms = sendCOIUrlToSms;
	}

	public String getSendCOIToSms() {
		return sendCOIToSms;
	}

	public void setSendCOIToSms(String sendCOIToSms) {
		this.sendCOIToSms = sendCOIToSms;
	}

	public String getFundingOption() {
		return fundingOption;
	}

	public void setFundingOption(String fundingOption) {
		this.fundingOption = fundingOption;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getJointInd() {
		return jointInd;
	}
	public void setJointInd(String jointInd) {
		this.jointInd = jointInd;
	}
	public String getInquirySubmitted_by() {
		return inquirySubmitted_by;
	}
	public void setInquirySubmitted_by(String inquirySubmitted_by) {
		this.inquirySubmitted_by = inquirySubmitted_by;
	}
	public String getSourceSystemName() {
		return sourceSystemName;
	}
	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}
	public String getSourceSystemVersion() {
		return sourceSystemVersion;
	}
	public void setSourceSystemVersion(String sourceSystemVersion) {
		this.sourceSystemVersion = sourceSystemVersion;
	}
	public String getSourceSystemVender() {
		return sourceSystemVender;
	}
	public void setSourceSystemVender(String sourceSystemVender) {
		this.sourceSystemVender = sourceSystemVender;
	}
	public String getSourceSystem_instance_id() {
		return sourceSystem_instance_id;
	}
	public void setSourceSystem_instance_id(String sourceSystem_instance_id) {
		this.sourceSystem_instance_id = sourceSystem_instance_id;
	}
	public String getLoanPurposeDesc() {
		return loanPurposeDesc;
	}
	public void setLoanPurposeDesc(String loanPurposeDesc) {
		this.loanPurposeDesc = loanPurposeDesc;
	}
	public String getBranchIfsccode() {
		return branchIfsccode;
	}
	public void setBranchIfsccode(String branchIfsccode) {
		this.branchIfsccode = branchIfsccode;
	}
	public String getKendra() {
		return kendra;
	}
	public void setKendra(String kendra) {
		this.kendra = kendra;
	}
	public String getInquiryStage() {
		return inquiryStage;
	}
	public void setInquiryStage(String inquiryStage) {
		this.inquiryStage = inquiryStage;
	}
	public String getAuthrizationflag() {
		return authrizationflag;
	}
	public void setAuthrizationflag(String authrizationflag) {
		this.authrizationflag = authrizationflag;
	}
	public String getAuthroizedBy() {
		return authroizedBy;
	}
	public void setAuthroizedBy(String authroizedBy) {
		this.authroizedBy = authroizedBy;
	}
	public String getIndividualCorporate_flag() {
		return individualCorporate_flag;
	}
	public void setIndividualCorporate_flag(String individualCorporate_flag) {
		this.individualCorporate_flag = individualCorporate_flag;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public String getNoOfDependents() {
		return noOfDependents;
	}
	public void setNoOfDependents(String noOfDependents) {
		this.noOfDependents = noOfDependents;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getActOpeningDt() {
		return actOpeningDt;
	}
	public void setActOpeningDt(String actOpeningDt) {
		this.actOpeningDt = actOpeningDt;
	}
	public String getAccountNumber1() {
		return accountNumber1;
	}
	public void setAccountNumber1(String accountNumber1) {
		this.accountNumber1 = accountNumber1;
	}
	public String getAccountNumber2() {
		return accountNumber2;
	}
	public void setAccountNumber2(String accountNumber2) {
		this.accountNumber2 = accountNumber2;
	}
	public String getAccountNumber3() {
		return accountNumber3;
	}
	public void setAccountNumber3(String accountNumber3) {
		this.accountNumber3 = accountNumber3;
	}
	public String getAccountNumber4() {
		return accountNumber4;
	}
	public void setAccountNumber4(String accountNumber4) {
		this.accountNumber4 = accountNumber4;
	}
	public String getTanure() {
		return tanure;
	}
	public void setTanure(String tanure) {
		this.tanure = tanure;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getNumberCreditCards() {
		return numberCreditCards;
	}
	public void setNumberCreditCards(String numberCreditCards) {
		this.numberCreditCards = numberCreditCards;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getSoaEmployerNameC() {
		return soaEmployerNameC;
	}
	public void setSoaEmployerNameC(String soaEmployerNameC) {
		this.soaEmployerNameC = soaEmployerNameC;
	}
	public String getTimeWithEmploy() {
		return timeWithEmploy;
	}
	public void setTimeWithEmploy(String timeWithEmploy) {
		this.timeWithEmploy = timeWithEmploy;
	}
	public String getCompanyCategory() {
		return companyCategory;
	}
	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}
	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}
	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}
	public String getAssetCost() {
		return assetCost;
	}
	public void setAssetCost(String assetCost) {
		this.assetCost = assetCost;
	}
	public String getCollateral1() {
		return collateral1;
	}
	public void setCollateral1(String collateral1) {
		this.collateral1 = collateral1;
	}
	public String getCollateral1Valuation1() {
		return collateral1Valuation1;
	}
	public void setCollateral1Valuation1(String collateral1Valuation1) {
		this.collateral1Valuation1 = collateral1Valuation1;
	}
	public String getCollateral1Valuation2() {
		return collateral1Valuation2;
	}
	public void setCollateral1Valuation2(String collateral1Valuation2) {
		this.collateral1Valuation2 = collateral1Valuation2;
	}
	public String getCollateral2Valuation1() {
		return collateral2Valuation1;
	}
	public void setCollateral2Valuation1(String collateral2Valuation1) {
		this.collateral2Valuation1 = collateral2Valuation1;
	}
	public String getCollateral2Valuation2() {
		return collateral2Valuation2;
	}
	public void setCollateral2Valuation2(String collateral2Valuation2) {
		this.collateral2Valuation2 = collateral2Valuation2;
	}
	public String getCollateral2() {
		return collateral2;
	}
	public void setCollateral2(String collateral2) {
		this.collateral2 = collateral2;
	}

	public String getBankstatement() {
		return bankstatement;
	}
	public void setBankstatement(String bankstatement) {
		this.bankstatement = bankstatement;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	private String correlationid;



	public String getCorrelationid() {
		return correlationid;
	}



	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	public String getAddressMatch1() {
		return addressMatch1;
	}
	public void setAddressMatch1(String addressMatch1) {
		this.addressMatch1 = addressMatch1;
	}
	public String getAddressMatch2() {
		return addressMatch2;
	}
	public void setAddressMatch2(String addressMatch2) {
		this.addressMatch2 = addressMatch2;
	}



	public String getHsCode() {
		return hsCode;
	}



	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}



	public String getDoe() {
		return doe;
	}



	public void setDoe(String doe) {
		this.doe = doe;
	}



	public String getPassportType() {
		return passportType;
	}



	public void setPassportType(String passportType) {
		this.passportType = passportType;
	}



	public String getCp_no() {
		return cp_no;
	}



	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}



	public String getInput() {
		return input;
	}



	public void setInput(String input) {
		this.input = input;
	}





	public String getAck() {
		return ack;
	}



	public void setAck(String ack) {
		this.ack = ack;
	}


	public String getRequest_id() {
		return request_id;
	}



	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}



	public String getOtp() {
		return otp;
	}



	public void setOtp(String otp) {
		this.otp = otp;
	}



	public String getEngineNo() {
		return engineNo;
	}



	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}



	public String getChassisNo() {
		return chassisNo;
	}



	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}













	public String getMembership_no() {
		return membership_no;
	}



	public void setMembership_no(String membership_no) {
		this.membership_no = membership_no;
	}



	public String getBp_no() {
		return bp_no;
	}



	public void setBp_no(String bp_no) {
		this.bp_no = bp_no;
	}



	public String getEsic_id() {
		return esic_id;
	}



	public void setEsic_id(String esic_id) {
		this.esic_id = esic_id;
	}



	public String getCert_no() {
		return cert_no;
	}



	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}




	public String getNameType() {
		return nameType;
	}



	public void setNameType(String nameType) {
		this.nameType = nameType;
	}








	public String getPan() {
		return pan;
	}



	public void setPan(String pan) {
		this.pan = pan;
	}



	public String getFiscal_year() {
		return fiscal_year;
	}



	public void setFiscal_year(String fiscal_year) {
		this.fiscal_year = fiscal_year;
	}



	public String getDomain() {
		return domain;
	}



	public void setDomain(String domain) {
		this.domain = domain;
	}



	public String getIfsc() {
		return ifsc;
	}



	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}



	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getShortname() {
		return shortname;
	}



	public void setShortname(String shortname) {
		this.shortname = shortname;
	}








	public String getJobcardid() {
		return jobcardid;
	}



	public void setJobcardid(String jobcardid) {
		this.jobcardid = jobcardid;
	}



	public String getAadhar() {
		return aadhar;
	}



	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}



	public String getUan() {
		return uan;
	}



	public void setUan(String uan) {
		this.uan = uan;
	}



	public String getReg_no() {
		return reg_no;
	}



	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}



	public String getArea_code() {
		return area_code;
	}



	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}



	public String getCin() {
		return cin;
	}



	public void setCin(String cin) {
		this.cin = cin;
	}



	public String getIec() {
		return iec;
	}



	public void setIec(String iec) {
		this.iec = iec;
	}



	public String getGstin() {
		return gstin;
	}



	public void setGstin(String gstin) {
		this.gstin = gstin;
	}



	public String getCompanyNameKarza() {
		return companyNameKarza;
	}



	public void setCompanyNameKarza(String companyNameKarza) {
		this.companyNameKarza = companyNameKarza;
	}



	public String getTan() {
		return tan;
	}



	public void setTan(String tan) {
		this.tan = tan;
	}

	public String getMessageId() {
		return MessageId;
	}



	public void setMessageId(String messageId) {
		MessageId = messageId;
	}



	public String getMsgSource() {
		return MsgSource;
	}



	public void setMsgSource(String msgSource) {
		MsgSource = msgSource;
	}



	public String getClientCode() {
		return ClientCode;
	}



	public void setClientCode(String clientCode) {
		ClientCode = clientCode;
	}



	public String getBatchRefNmbr() {
		return BatchRefNmbr;
	}



	public void setBatchRefNmbr(String batchRefNmbr) {
		BatchRefNmbr = batchRefNmbr;
	}



	public String getHeaderChecksum() {
		return HeaderChecksum;
	}



	public void setHeaderChecksum(String headerChecksum) {
		HeaderChecksum = headerChecksum;
	}



	public String getReqRF1() {
		return ReqRF1;
	}



	public void setReqRF1(String reqRF1) {
		ReqRF1 = reqRF1;
	}



	public String getReqRF2() {
		return ReqRF2;
	}



	public void setReqRF2(String reqRF2) {
		ReqRF2 = reqRF2;
	}



	public String getReqRF3() {
		return ReqRF3;
	}



	public void setReqRF3(String reqRF3) {
		ReqRF3 = reqRF3;
	}



	public String getReqRF4() {
		return ReqRF4;
	}



	public void setReqRF4(String reqRF4) {
		ReqRF4 = reqRF4;
	}



	public String getReqRF5() {
		return ReqRF5;
	}



	public void setReqRF5(String reqRF5) {
		ReqRF5 = reqRF5;
	}



	public String getInstRefNo() {
		return InstRefNo;
	}



	public void setInstRefNo(String instRefNo) {
		InstRefNo = instRefNo;
	}



	public String getCompanyId() {
		return CompanyId;
	}



	public void setCompanyId(String companyId) {
		CompanyId = companyId;
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



	public String getBeneTaxId() {
		return beneTaxId;
	}



	public void setBeneTaxId(String beneTaxId) {
		this.beneTaxId = beneTaxId;
	}



	public String getAuthPerson() {
		return authPerson;
	}



	public void setAuthPerson(String authPerson) {
		this.authPerson = authPerson;
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



	public String getEnrichment() {
		return enrichment;
	}



	public void setEnrichment(String enrichment) {
		this.enrichment = enrichment;
	}



	public String getOfficeAddressLine1() {
		return officeAddressLine1;
	}



	public void setOfficeAddressLine1(String officeAddressLine1) {
		this.officeAddressLine1 = officeAddressLine1;
	}



	public String getOfficeAddressLine2() {
		return officeAddressLine2;
	}



	public void setOfficeAddressLine2(String officeAddressLine2) {
		this.officeAddressLine2 = officeAddressLine2;
	}



	public String getOfficeAddressLine3() {
		return officeAddressLine3;
	}



	public void setOfficeAddressLine3(String officeAddressLine3) {
		this.officeAddressLine3 = officeAddressLine3;
	}



	public String getOfficeCity() {
		return officeCity;
	}



	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}



	public String getOfficeState() {
		return officeState;
	}



	public void setOfficeState(String officeState) {
		this.officeState = officeState;
	}



	public String getOfficePinCode() {
		return officePinCode;
	}



	public void setOfficePinCode(String officePinCode) {
		this.officePinCode = officePinCode;
	}



	private String reason;
	private String pdf_path;
	@Lob
	private String final_response;

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}



	public String getConsumer_id() {
		return consumer_id;
	}



	public void setConsumer_id(String consumer_id) {
		this.consumer_id = consumer_id;
	}



	public String getLpg_id() {
		return lpg_id;
	}



	public void setLpg_id(String lpg_id) {
		this.lpg_id = lpg_id;
	}



	public String getKarza_service_provider() {
		return karza_service_provider;
	}



	public void setKarza_service_provider(String karza_service_provider) {
		this.karza_service_provider = karza_service_provider;
	}



	public String getLeadId() {
		return leadId;
	}



	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}



	public String getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}



	public String getLosId() {
		return losId;
	}



	public void setLosId(String losId) {
		this.losId = losId;
	}



	public String getUniqueId() {
		return uniqueId;
	}



	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getIdname6() {
		return idname6;
	}

	public void setIdname6(String idname6) {
		this.idname6 = idname6;
	}

	public String getIdno6() {
		return idno6;
	}

	public void setIdno6(String idno6) {
		this.idno6 = idno6;
	}

	public long getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(long seq_id) {
		this.seq_id = seq_id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAllowInput() {
		return allowInput;
	}

	public void setAllowInput(String allowInput) {
		this.allowInput = allowInput;
	}

	public String getAllowEdit() {
		return allowEdit;
	}

	public void setAllowEdit(String allowEdit) {
		this.allowEdit = allowEdit;
	}

	public String getAllowCaptcha() {
		return allowCaptcha;
	}

	public void setAllowCaptcha(String allowCaptcha) {
		this.allowCaptcha = allowCaptcha;
	}

	public String getAllowConsent() {
		return allowConsent;
	}

	public void setAllowConsent(String allowConsent) {
		this.allowConsent = allowConsent;
	}

	public String getAllowConsent_additional() {
		return allowConsent_additional;
	}

	public void setAllowConsent_additional(String allowConsent_additional) {
		this.allowConsent_additional = allowConsent_additional;
	}

	public String getAllowEmailVerify() {
		return allowEmailVerify;
	}

	public void setAllowEmailVerify(String allowEmailVerify) {
		this.allowEmailVerify = allowEmailVerify;
	}

	public String getAllowVoucher() {
		return allowVoucher;
	}

	public void setAllowVoucher(String allowVoucher) {
		this.allowVoucher = allowVoucher;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getNoValidationByPass() {
		return noValidationByPass;
	}

	public void setNoValidationByPass(String noValidationByPass) {
		this.noValidationByPass = noValidationByPass;
	}

	public String getEmailConditionalByPass() {
		return emailConditionalByPass;
	}

	public void setEmailConditionalByPass(String emailConditionalByPass) {
		this.emailConditionalByPass = emailConditionalByPass;
	}

	public String getFlatno() {
		return flatno;
	}

	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPdf_path() {
		return pdf_path;
	}

	public void setPdf_path(String pdf_path) {
		this.pdf_path = pdf_path;
	}

	public String getFinal_response() {
		return final_response;
	}

	public void setFinal_response(String final_response) {
		this.final_response = final_response;
	}

	public String getService_provider() {
		return service_provider;
	}

	public String getResidence_code1() {
		return residence_code1;
	}

	public void setResidence_code1(String residence_code1) {
		this.residence_code1 = residence_code1;
	}

	public String getResidence_code2() {
		return residence_code2;
	}

	public void setResidence_code2(String residence_code2) {
		this.residence_code2 = residence_code2;
	}

	public String getResidence_code3() {
		return residence_code3;
	}

	public void setResidence_code3(String residence_code3) {
		this.residence_code3 = residence_code3;
	}

	public String getResidence_code4() {
		return residence_code4;
	}

	public void setResidence_code4(String residence_code4) {
		this.residence_code4 = residence_code4;
	}

	public String getResidence_code5() {
		return residence_code5;
	}

	public void setResidence_code5(String residence_code5) {
		this.residence_code5 = residence_code5;
	}

	public void setService_provider(String service_provider) {
		this.service_provider = service_provider;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getKey_person_name() {
		return key_person_name;
	}

	public void setKey_person_name(String key_person_name) {
		this.key_person_name = key_person_name;
	}

	public String getKey_person_type() {
		return key_person_type;
	}

	public void setKey_person_type(String key_person_type) {
		this.key_person_type = key_person_type;
	}

	public String getNominee_name() {
		return nominee_name;
	}

	public void setNominee_name(String nominee_name) {
		this.nominee_name = nominee_name;
	}

	public String getNominee_type() {
		return nominee_type;
	}

	public void setNominee_type(String nominee_type) {
		this.nominee_type = nominee_type;
	}

	public String getIdname1() {
		return idname1;
	}

	public void setIdname1(String idname1) {
		this.idname1 = idname1;
	}

	public String getIdno1() {
		return idno1;
	}

	public void setIdno1(String idno1) {
		this.idno1 = idno1;
	}

	public String getIdname2() {
		return idname2;
	}

	public void setIdname2(String idname2) {
		this.idname2 = idname2;
	}

	public String getIdno2() {
		return idno2;
	}

	public void setIdno2(String idno2) {
		this.idno2 = idno2;
	}

	public String getIdname3() {
		return idname3;
	}

	public void setIdname3(String idname3) {
		this.idname3 = idname3;
	}

	public String getIdno3() {
		return idno3;
	}

	public void setIdno3(String idno3) {
		this.idno3 = idno3;
	}

	public String getIdname4() {
		return idname4;
	}

	public void setIdname4(String idname4) {
		this.idname4 = idname4;
	}

	public String getIdno4() {
		return idno4;
	}

	public void setIdno4(String idno4) {
		this.idno4 = idno4;
	}

	public String getIdname5() {
		return idname5;
	}

	public void setIdname5(String idname5) {
		this.idname5 = idname5;
	}

	public String getIdno5() {
		return idno5;
	}

	public void setIdno5(String idno5) {
		this.idno5 = idno5;
	}

	public String getRelation_name1() {
		return relation_name1;
	}

	public void setRelation_name1(String relation_name1) {
		this.relation_name1 = relation_name1;
	}

	public String getRelation1() {
		return relation1;
	}

	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}

	public String getRelation_name2() {
		return relation_name2;
	}

	public void setRelation_name2(String relation_name2) {
		this.relation_name2 = relation_name2;
	}

	public String getRelation2() {
		return relation2;
	}

	public void setRelation2(String relation2) {
		this.relation2 = relation2;
	}

	public String getRelation_name3() {
		return relation_name3;
	}

	public void setRelation_name3(String relation_name3) {
		this.relation_name3 = relation_name3;
	}

	public String getRelation3() {
		return relation3;
	}

	public void setRelation3(String relation3) {
		this.relation3 = relation3;
	}

	public String getRelation_name4() {
		return relation_name4;
	}

	public void setRelation_name4(String relation_name4) {
		this.relation_name4 = relation_name4;
	}

	public String getRelation4() {
		return relation4;
	}

	public void setRelation4(String relation4) {
		this.relation4 = relation4;
	}

	public String getRelation_name5() {
		return relation_name5;
	}

	public void setRelation_name5(String relation_name5) {
		this.relation_name5 = relation_name5;
	}

	public String getRelation5() {
		return relation5;
	}

	public void setRelation5(String relation5) {
		this.relation5 = relation5;
	}

	public String getTele_no1() {
		return tele_no1;
	}

	public void setTele_no1(String tele_no1) {
		this.tele_no1 = tele_no1;
	}

	public String getTele_type1() {
		return tele_type1;
	}

	public void setTele_type1(String tele_type1) {
		this.tele_type1 = tele_type1;
	}

	public String getTele_no2() {
		return tele_no2;
	}

	public void setTele_no2(String tele_no2) {
		this.tele_no2 = tele_no2;
	}

	public String getTele_type2() {
		return tele_type2;
	}

	public void setTele_type2(String tele_type2) {
		this.tele_type2 = tele_type2;
	}

	public String getTele_no3() {
		return tele_no3;
	}

	public void setTele_no3(String tele_no3) {
		this.tele_no3 = tele_no3;
	}

	public String getTele_type3() {
		return tele_type3;
	}

	public void setTele_type3(String tele_type3) {
		this.tele_type3 = tele_type3;
	}

	public String getTele_no4() {
		return tele_no4;
	}

	public void setTele_no4(String tele_no4) {
		this.tele_no4 = tele_no4;
	}

	public String getTele_type4() {
		return tele_type4;
	}

	public void setTele_type4(String tele_type4) {
		this.tele_type4 = tele_type4;
	}

	public String getTele_no5() {
		return tele_no5;
	}

	public void setTele_no5(String tele_no5) {
		this.tele_no5 = tele_no5;
	}

	public String getTele_type5() {
		return tele_type5;
	}

	public void setTele_type5(String tele_type5) {
		this.tele_type5 = tele_type5;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getPin1() {
		return pin1;
	}

	public void setPin1(String pin1) {
		this.pin1 = pin1;
	}

	public String getAddress_type1() {
		return address_type1;
	}

	public void setAddress_type1(String address_type1) {
		this.address_type1 = address_type1;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPin2() {
		return pin2;
	}

	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}

	public String getAddress_type2() {
		return address_type2;
	}

	public void setAddress_type2(String address_type2) {
		this.address_type2 = address_type2;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPin3() {
		return pin3;
	}

	public void setPin3(String pin3) {
		this.pin3 = pin3;
	}

	public String getAddress_type3() {
		return address_type3;
	}

	public void setAddress_type3(String address_type3) {
		this.address_type3 = address_type3;
	}

	public String getState3() {
		return state3;
	}

	public void setState3(String state3) {
		this.state3 = state3;
	}

	public String getCity3() {
		return city3;
	}

	public void setCity3(String city3) {
		this.city3 = city3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getPin4() {
		return pin4;
	}

	public void setPin4(String pin4) {
		this.pin4 = pin4;
	}

	public String getAddress_type4() {
		return address_type4;
	}

	public void setAddress_type4(String address_type4) {
		this.address_type4 = address_type4;
	}

	public String getState4() {
		return state4;
	}

	public void setState4(String state4) {
		this.state4 = state4;
	}

	public String getCity4() {
		return city4;
	}

	public void setCity4(String city4) {
		this.city4 = city4;
	}

	public String getAddress5() {
		return address5;
	}

	public void setAddress5(String address5) {
		this.address5 = address5;
	}

	public String getPin5() {
		return pin5;
	}

	public void setPin5(String pin5) {
		this.pin5 = pin5;
	}

	public String getAddress_type5() {
		return address_type5;
	}

	public void setAddress_type5(String address_type5) {
		this.address_type5 = address_type5;
	}

	public String getState5() {
		return state5;
	}

	public void setState5(String state5) {
		this.state5 = state5;
	}

	public String getCity5() {
		return city5;
	}

	public void setCity5(String city5) {
		this.city5 = city5;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInquiry_purpose() {
		return inquiry_purpose;
	}

	public void setInquiry_purpose(String inquiry_purpose) {
		this.inquiry_purpose = inquiry_purpose;
	}

	public String getScoretype() {
		return scoretype;
	}

	public void setScoretype(String scoretype) {
		this.scoretype = scoretype;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTele_extn1() {
		return tele_extn1;
	}

	public void setTele_extn1(String tele_extn1) {
		this.tele_extn1 = tele_extn1;
	}

	public String getTele_extn2() {
		return tele_extn2;
	}

	public void setTele_extn2(String tele_extn2) {
		this.tele_extn2 = tele_extn2;
	}

	public String getTele_extn3() {
		return tele_extn3;
	}

	public void setTele_extn3(String tele_extn3) {
		this.tele_extn3 = tele_extn3;
	}

	public String getTele_extn4() {
		return tele_extn4;
	}

	public void setTele_extn4(String tele_extn4) {
		this.tele_extn4 = tele_extn4;
	}

	public String getTele_extn5() {
		return tele_extn5;
	}

	public void setTele_extn5(String tele_extn5) {
		this.tele_extn5 = tele_extn5;
	}

	public String getStg_one_hit_id() {
		return stg_one_hit_id;
	}

	public void setStg_one_hit_id(String stg_one_hit_id) {
		this.stg_one_hit_id = stg_one_hit_id;
	}

	public String getStg_two_hit_id() {
		return stg_two_hit_id;
	}

	public void setStg_two_hit_id(String stg_two_hit_id) {
		this.stg_two_hit_id = stg_two_hit_id;
	}

	public String getActual_email_addr() {
		return actual_email_addr;
	}

	public void setActual_email_addr(String actual_email_addr) {
		this.actual_email_addr = actual_email_addr;
	}

	public String getSingle_action_session_id() {
		return single_action_session_id;
	}

	public void setSingle_action_session_id(String single_action_session_id) {
		this.single_action_session_id = single_action_session_id;
	}

	public String getCaptch_code() {
		return captch_code;
	}

	public void setCaptch_code(String captch_code) {
		this.captch_code = captch_code;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


}
