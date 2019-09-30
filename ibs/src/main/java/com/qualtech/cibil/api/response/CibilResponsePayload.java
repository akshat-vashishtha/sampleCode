package com.qualtech.cibil.api.response;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.entity.AccountDetails;
import com.qualtech.cibil.api.entity.AccountManualSummary;
import com.qualtech.cibil.api.entity.AccountSummary;
import com.qualtech.cibil.api.entity.AdditionalMatchDetails;
import com.qualtech.cibil.api.entity.AddressDetails;
import com.qualtech.cibil.api.entity.DisputeDetails;
import com.qualtech.cibil.api.entity.EmailContact;
import com.qualtech.cibil.api.entity.EmploymentDetail;
import com.qualtech.cibil.api.entity.EnquiryDetails;
import com.qualtech.cibil.api.entity.EnquirySummary;
import com.qualtech.cibil.api.entity.IDDetails;
import com.qualtech.cibil.api.entity.PaymentHistoryUI;
import com.qualtech.cibil.api.entity.ScoreDetails;
import com.qualtech.cibil.api.entity.TelePhone;
@Entity
@Table(name="IB_CBL_RESPONSEENTITY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CibilResponsePayload 
{
	private int uniqueid;
	private String corelationid;
	private String printableReport;
	private transient String cibilReq;
	private transient String cibilRes;
	private String returncode;                               
	private String enquiryControlNumber;
	private String processedDate;	
	private String timeProceesed;
	private String name1="";
	private String name2="";
	private String name3="";
	private String name4="";
	private String name5="";
	private String dateofBirth;
	private String gender;
	private String pdfByteArray;
	private String pdfPath;
	private String memberId;
	private String memberRefNo;
	private String lenOfTransmission;
	private DisputeDetails disputes;                            
	private AccountManualSummary accountManualSummary;          
	private List<TelePhone> telephones;
	private List<ScoreDetails> scoreDetails;
	
	private List<AddressDetails> addresses;
	private List<PaymentHistoryUI> daysPastDueAssetClassificationData;
	private List<AccountDetails> accountDetails;
	private List<AccountSummary> accountSummary;
	private List<EnquiryDetails> enquiries;
	private List<EnquirySummary> enquiriesSummary;
	private List<EmploymentDetail>  employmentdetail;
	private List<IDDetails>  iddetails;
	private List<EmailContact>  emailcontact; 
	private List<AdditionalMatchDetails> additionalMatchDetails;
	@Column(name="CIBIL_REQUEST")
	@Lob
	public String getCibilReq() {
		return cibilReq;
	}
	public void setCibilReq(String cibilReq) {
		this.cibilReq = cibilReq;
	}
	@Column(name="CIBIL_RESPONSE")
	@Lob
	public String getCibilRes() {
		return cibilRes;
	}
	public void setCibilRes(String cibilRes) {
		this.cibilRes = cibilRes;
	}
	@Column(name="MEMBERID")
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Column(name="MEMBERREFNO")
	public String getMemberRefNo() {
		return memberRefNo;
	}
	public void setMemberRefNo(String memberRefNo) {
		this.memberRefNo = memberRefNo;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<EmailContact> getEmailcontact() {
		return emailcontact;
	}
	public void setEmailcontact(List<EmailContact> emailcontact) {
		this.emailcontact = emailcontact;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<IDDetails> getIddetails() {
		return iddetails;
	}
	public void setIddetails(List<IDDetails> iddetails) {
		this.iddetails = iddetails;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<EmploymentDetail> getEmploymentdetail() {
		return employmentdetail;
	}
	public void setEmploymentdetail(List<EmploymentDetail> employmentdetail) {
		this.employmentdetail = employmentdetail;
	}
	@Column(name="PDFBYTEARRAY")
	@Lob
	public String getPdfByteArray() {
		return pdfByteArray;
	}
	public void setPdfByteArray(String pdfByteArray) {
		this.pdfByteArray = pdfByteArray;
	}
	@Column(name="RETURNCODE")
	public String getReturncode() {
		return returncode;
	}
	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}
	@Column(name="ENQUIRYCUMBER")
	public String getEnquiryControlNumber() {
		return enquiryControlNumber;
	}
	public void setEnquiryControlNumber(String enquiryControlNumber) {
		this.enquiryControlNumber = enquiryControlNumber;
	}
	@Column(name="PROCESSEDDATE")
	public String getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(String processedDate) {
		this.processedDate = processedDate;
	}
	@Column(name="TIMEPROCEESED")
	public String getTimeProceesed() {
		return timeProceesed;
	}
	public void setTimeProceesed(String timeProceesed) {
		this.timeProceesed = timeProceesed;
	}
	@Column(name="NAME1")
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	@Column(name="NAME2")
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	@Column(name="NAME3")
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	@Column(name="NAME4")
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	@Column(name="NAME5")
	public String getName5() {
		return name5;
	}
	public void setName5(String name5) {
		this.name5 = name5;
	}
	@Column(name="DATEOFBIRTH")
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<TelePhone> getTelephones() {
		return telephones;
	}
	public void setTelephones(List<TelePhone> telephones) {
		this.telephones = telephones;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<AddressDetails> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDetails> addresses) {
		this.addresses = addresses;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<AccountDetails> getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(List<AccountDetails> accountDetails) {
		this.accountDetails = accountDetails;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<EnquiryDetails> getEnquiries() {
		return enquiries;
	}
	public void setEnquiries(List<EnquiryDetails> enquiries) {
		this.enquiries = enquiries;
	}
	@Column(name="PRINTABLEREPORT")
	@Lob
	public String getPrintableReport() {
		return printableReport;
	}
	public void setPrintableReport(String printableReport) {
		this.printableReport = printableReport;
	}
	@Column(name="LENOFTRANSMISSION")
	public String getLenOfTransmission() {
		return lenOfTransmission;
	}
	public void setLenOfTransmission(String lenOfTransmission) {
		this.lenOfTransmission = lenOfTransmission;
	}
   @Transient
	public DisputeDetails getDisputes() {
		return disputes;
	}
	public void setDisputes(DisputeDetails disputes) {
		this.disputes = disputes;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<AccountSummary> getAccountSummary() {
		return accountSummary;
	}
	public void setAccountSummary(List<AccountSummary> accountSummary) {
		this.accountSummary = accountSummary;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<EnquirySummary> getEnquiriesSummary() {
		return enquiriesSummary;
	}
	public void setEnquiriesSummary(List<EnquirySummary> enquiriesSummary) {
		this.enquiriesSummary = enquiriesSummary;
	}
	@OneToOne(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public AccountManualSummary getAccountManualSummary() {
		return accountManualSummary;
	}
	public void setAccountManualSummary(AccountManualSummary accountManualSummary) {
		this.accountManualSummary = accountManualSummary;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<ScoreDetails> getScoreDetails() {
		return scoreDetails;
	}
	public void setScoreDetails(List<ScoreDetails> scoreDetails) {
		this.scoreDetails = scoreDetails;
	}
	@Transient
	public List<AdditionalMatchDetails> getAdditionalMatchDetails() {
		return additionalMatchDetails;
	}
	public void setAdditionalMatchDetails(List<AdditionalMatchDetails> additionalMatchDetails) {
		this.additionalMatchDetails = additionalMatchDetails;
	}
	@Override
	public String toString() {
		return "CibilResponsePayload [printableReport=" + printableReport + ", cibilReq=" + cibilReq + ", cibilRes="
				+ cibilRes + ", returncode=" + returncode + ", enquiryControlNumber=" + enquiryControlNumber
				+ ", processedDate=" + processedDate + ", timeProceesed=" + timeProceesed + ", name1=" + name1
				+ ", name2=" + name2 + ", name3=" + name3 + ", name4=" + name4 + ", name5=" + name5 + ", dateofBirth="
				+ dateofBirth + ", gender=" + gender + ", telephones=" + telephones + ", scoreDetails=" + scoreDetails
				+ ", addresses=" + addresses + ", accountDetails=" + accountDetails + ", accountSummary="
				+ accountSummary + ", enquiries=" + enquiries + ", enquiriesSummary=" + enquiriesSummary
				+ ", pdfByteArray=" + pdfByteArray + ", employmentdetail=" + employmentdetail + ", iddetails="
				+ iddetails + ", emailcontact=" + emailcontact + ", memberId=" + memberId + ", memberRefNo="
				+ memberRefNo + ", lenOfTransmission=" + lenOfTransmission + ", disputes=" + disputes
				+ ", accountManualSummary=" + accountManualSummary + ", additionalMatchDetails="
				+ additionalMatchDetails + ", getCibilReq()=" + getCibilReq() + ", getCibilRes()=" + getCibilRes()
				+ ", getMemberId()=" + getMemberId() + ", getMemberRefNo()=" + getMemberRefNo() + ", getEmailcontact()="
				+ getEmailcontact() + ", getIddetails()=" + getIddetails() + ", getEmploymentdetail()="
				+ getEmploymentdetail() + ", getPdfByteArray()=" + getPdfByteArray() + ", getReturncode()="
				+ getReturncode() + ", getEnquiryControlNumber()=" + getEnquiryControlNumber() + ", getProcessedDate()="
				+ getProcessedDate() + ", getTimeProceesed()=" + getTimeProceesed() + ", getName1()=" + getName1()
				+ ", getName2()=" + getName2() + ", getName3()=" + getName3() + ", getName4()=" + getName4()
				+ ", getName5()=" + getName5() + ", getDateofBirth()=" + getDateofBirth() + ", getGender()="
				+ getGender() + ", getTelephones()=" + getTelephones() + ", getAddresses()=" + getAddresses()
				+ ", getAccountDetails()=" + getAccountDetails() + ", getEnquiries()=" + getEnquiries()
				+", daysPastDueAssetClassificationData=" + daysPastDueAssetClassificationData
				+ ", getPrintableReport()=" + getPrintableReport() + ", getLenOfTransmission()="
				+ getLenOfTransmission() + ", getDisputes()=" + getDisputes() + ", getAccountSummary()="
				+ getAccountSummary() + ", getEnquiriesSummary()=" + getEnquiriesSummary()
				+ ", getAccountManualSummary()=" + getAccountManualSummary() + ", getScoreDetails()="
				+ getScoreDetails() + ", getAdditionalMatchDetails()=" + getAdditionalMatchDetails() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	/*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_RESP_SQC")
    @SequenceGenerator(name = "IB_CBL_RESP_SQC", sequenceName = "IB_CBL_RESP_SQC", allocationSize = 1)*/
	@Id
	@Column(name="CIBIL_UNIQUE_ID")
	public int getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Column(name="COREALTIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cibilresponsepayload",cascade=CascadeType.ALL)
	public List<PaymentHistoryUI> getDaysPastDueAssetClassificationData() {
		return daysPastDueAssetClassificationData;
	}
	public void setDaysPastDueAssetClassificationData(List<PaymentHistoryUI> daysPastDueAssetClassificationData) {
		this.daysPastDueAssetClassificationData = daysPastDueAssetClassificationData;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
}
