package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CAIS_ACC_DETAILS_RES")
public class CaisAccountDetailsRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_ACC_DT_SQC", allocationSize = 1)
	@JsonIgnore
	private int caisAccDetailsId;

	private String rateofinterest;
	private String settelmentamount;
	@JsonProperty("scheduledmonthlypaymentamount")
	private String scheduledmonthlyPayAmt;
	private String consumercomments;
	private String occupationcode;
	private String accountholdertypecode;

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "caisAccountDetailsRes" ,cascade=CascadeType.ALL)
	private List<CaisHolderPhoneDetailsListRes> caisholderphonedetailslist;
	
	private String accountstatus;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "caisAccountDetailsRes" ,cascade=CascadeType.ALL)
	private List<CaisHolderDetailsListRes> caisholderdetailslist;
	
	private String subscribercomments;
	private String termsfrequency;
	private String amountpastdue;
	private String identificationnumber;
	private String portfoliotype;
	private String repaymenttenure;
	private String dateclosed;
	private String incomefrequencyindicator;
	private String income;
	
	@JsonProperty("highestcreditororignalloanamount")
	private String highestCrOrignalLoanAmt;
	private String writtenoffamounttotal;
	private String accounttype;
	private String subscribername;
	private String typeofcollateral;
	private String termsduration;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "caisAccountDetailsRes" ,cascade=CascadeType.ALL)
	private CaisHolderidDetailsRes caisholderiddetails;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "caisAccountDetailsRes" ,cascade=CascadeType.ALL)
	private List<CaisAccountHistoryListRes> caisaccounthistorylist;
	
	private String datereported;
	private String dateoflastpayment;
	private String valueofcreditslastmonth;
	private String dateofaddition;
	private String specialcomment;
	private String opendate;
	private String currentbalance;
	private String paymenthistoryprofile;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "caisAccountDetailsRes" ,cascade=CascadeType.ALL)
	private List<CaisHolderAddressDetailsListRes> caisholderaddressdetailslist;
	
	private String paymentrating;
	private String litigationstatusdate;
	private String incomeindicator;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "caisAccountDetailsRes" ,cascade=CascadeType.ALL)
	private HistoryMatrixRes historymatrix;
	
	private String accountnumber;
	private String pramotionalrateflag;
	private String currencycode;
	private String suitfiledwilfuldefault;
	private String valueofcollateral;
	private String writtenoffamountprincipal;
	
	@JsonProperty("suitfiledwillfuldefaultwrittenoffstatus")
	private String suitfiledwllfldefultWtnofSt;
	private String writeoffstatusdate;
	private String writtenoffsettledstatus;
	private String defaultstatusdate;
	private String creditlimitamount;
	
	@ManyToOne
	@JoinColumn(name = "caisAccountId", nullable = false)
	@JsonIgnore
	private CaisAccountRes caisAccountRes;

	public String getDatereported() {
		return datereported;
	}

	public void setDatereported(String datereported) {
		this.datereported = datereported;
	}

	public String getDateoflastpayment() {
		return dateoflastpayment;
	}

	public void setDateoflastpayment(String dateoflastpayment) {
		this.dateoflastpayment = dateoflastpayment;
	}

	public String getValueofcreditslastmonth() {
		return valueofcreditslastmonth;
	}

	public void setValueofcreditslastmonth(String valueofcreditslastmonth) {
		this.valueofcreditslastmonth = valueofcreditslastmonth;
	}

	public String getDateofaddition() {
		return dateofaddition;
	}

	public void setDateofaddition(String dateofaddition) {
		this.dateofaddition = dateofaddition;
	}

	public String getSpecialcomment() {
		return specialcomment;
	}

	public void setSpecialcomment(String specialcomment) {
		this.specialcomment = specialcomment;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public String getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(String currentbalance) {
		this.currentbalance = currentbalance;
	}

	public String getPaymenthistoryprofile() {
		return paymenthistoryprofile;
	}

	public void setPaymenthistoryprofile(String paymenthistoryprofile) {
		this.paymenthistoryprofile = paymenthistoryprofile;
	}

	public List<CaisHolderAddressDetailsListRes> getCaisholderaddressdetailslist() {
		
		if(caisholderaddressdetailslist!=null)
		{
			for(CaisHolderAddressDetailsListRes res:caisholderaddressdetailslist)
			{
				res.setCaisAccountDetailsRes(this);
			}
		}
		return caisholderaddressdetailslist;
	}

	public void setCaisholderaddressdetailslist(List<CaisHolderAddressDetailsListRes> caisholderaddressdetailslist) {
		this.caisholderaddressdetailslist = caisholderaddressdetailslist;
	}

	public String getPaymentrating() {
		return paymentrating;
	}

	public void setPaymentrating(String paymentrating) {
		this.paymentrating = paymentrating;
	}

	public String getLitigationstatusdate() {
		return litigationstatusdate;
	}

	public void setLitigationstatusdate(String litigationstatusdate) {
		this.litigationstatusdate = litigationstatusdate;
	}

	public String getIncomeindicator() {
		return incomeindicator;
	}

	public void setIncomeindicator(String incomeindicator) {
		this.incomeindicator = incomeindicator;
	}

	public HistoryMatrixRes getHistorymatrix() {
		if (this.historymatrix != null) {
			this.historymatrix.setCaisAccountDetailsRes(this);
		}
		return historymatrix;
	}

	public void setHistorymatrix(HistoryMatrixRes historymatrix) {
		this.historymatrix = historymatrix;
	}
	
	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getPramotionalrateflag() {
		return pramotionalrateflag;
	}

	public void setPramotionalrateflag(String pramotionalrateflag) {
		this.pramotionalrateflag = pramotionalrateflag;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getSuitfiledwilfuldefault() {
		return suitfiledwilfuldefault;
	}

	public void setSuitfiledwilfuldefault(String suitfiledwilfuldefault) {
		this.suitfiledwilfuldefault = suitfiledwilfuldefault;
	}

	public String getValueofcollateral() {
		return valueofcollateral;
	}

	public void setValueofcollateral(String valueofcollateral) {
		this.valueofcollateral = valueofcollateral;
	}

	public String getWrittenoffamountprincipal() {
		return writtenoffamountprincipal;
	}

	public void setWrittenoffamountprincipal(String writtenoffamountprincipal) {
		this.writtenoffamountprincipal = writtenoffamountprincipal;
	}


	public String getWriteoffstatusdate() {
		return writeoffstatusdate;
	}

	public void setWriteoffstatusdate(String writeoffstatusdate) {
		this.writeoffstatusdate = writeoffstatusdate;
	}

	public String getWrittenoffsettledstatus() {
		return writtenoffsettledstatus;
	}

	public void setWrittenoffsettledstatus(String writtenoffsettledstatus) {
		this.writtenoffsettledstatus = writtenoffsettledstatus;
	}

	public String getDefaultstatusdate() {
		return defaultstatusdate;
	}

	public void setDefaultstatusdate(String defaultstatusdate) {
		this.defaultstatusdate = defaultstatusdate;
	}

	public String getCreditlimitamount() {
		return creditlimitamount;
	}

	public void setCreditlimitamount(String creditlimitamount) {
		this.creditlimitamount = creditlimitamount;
	}

	public String getSubscribercomments() {
		return subscribercomments;
	}

	public void setSubscribercomments(String subscribercomments) {
		this.subscribercomments = subscribercomments;
	}

	public String getTermsfrequency() {
		return termsfrequency;
	}

	public void setTermsfrequency(String termsfrequency) {
		this.termsfrequency = termsfrequency;
	}

	public String getAmountpastdue() {
		return amountpastdue;
	}

	public void setAmountpastdue(String amountpastdue) {
		this.amountpastdue = amountpastdue;
	}

	public String getIdentificationnumber() {
		return identificationnumber;
	}

	public void setIdentificationnumber(String identificationnumber) {
		this.identificationnumber = identificationnumber;
	}

	public String getPortfoliotype() {
		return portfoliotype;
	}

	public void setPortfoliotype(String portfoliotype) {
		this.portfoliotype = portfoliotype;
	}

	public String getRepaymenttenure() {
		return repaymenttenure;
	}

	public void setRepaymenttenure(String repaymenttenure) {
		this.repaymenttenure = repaymenttenure;
	}

	public String getDateclosed() {
		return dateclosed;
	}

	public void setDateclosed(String dateclosed) {
		this.dateclosed = dateclosed;
	}

	public String getIncomefrequencyindicator() {
		return incomefrequencyindicator;
	}

	public void setIncomefrequencyindicator(String incomefrequencyindicator) {
		this.incomefrequencyindicator = incomefrequencyindicator;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}


	public String getWrittenoffamounttotal() {
		return writtenoffamounttotal;
	}

	public void setWrittenoffamounttotal(String writtenoffamounttotal) {
		this.writtenoffamounttotal = writtenoffamounttotal;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getSubscribername() {
		return subscribername;
	}

	public void setSubscribername(String subscribername) {
		this.subscribername = subscribername;
	}

	public String getTypeofcollateral() {
		return typeofcollateral;
	}

	public void setTypeofcollateral(String typeofcollateral) {
		this.typeofcollateral = typeofcollateral;
	}

	public String getTermsduration() {
		return termsduration;
	}

	public void setTermsduration(String termsduration) {
		this.termsduration = termsduration;
	}

	public CaisHolderidDetailsRes getCaisholderiddetails() {
		if (this.caisholderiddetails != null) {
			this.caisholderiddetails.setCaisAccountDetailsRes(this);
		}
		return caisholderiddetails;
	}

	public void setCaisholderiddetails(CaisHolderidDetailsRes caisholderiddetails) {
		this.caisholderiddetails = caisholderiddetails;
	}

	public List<CaisAccountHistoryListRes> getCaisaccounthistorylist() {
		
		if(caisaccounthistorylist!=null)
		{
			for(CaisAccountHistoryListRes res:caisaccounthistorylist)
			{
				res.setCaisAccountDetailsRes(this);
			}
		}
		return caisaccounthistorylist;
	}

	public void setCaisaccounthistorylist(List<CaisAccountHistoryListRes> caisaccounthistorylist) {
		this.caisaccounthistorylist = caisaccounthistorylist;
	}

	public int getCaisAccDetailsId() {
		return caisAccDetailsId;
	}

	public void setCaisAccDetailsId(int caisAccDetailsId) {
		this.caisAccDetailsId = caisAccDetailsId;
	}

	public String getRateofinterest() {
		return rateofinterest;
	}

	public void setRateofinterest(String rateofinterest) {
		this.rateofinterest = rateofinterest;
	}

	public String getSettelmentamount() {
		return settelmentamount;
	}

	public void setSettelmentamount(String settelmentamount) {
		this.settelmentamount = settelmentamount;
	}


	public String getConsumercomments() {
		return consumercomments;
	}

	public void setConsumercomments(String consumercomments) {
		this.consumercomments = consumercomments;
	}

	public String getOccupationcode() {
		return occupationcode;
	}

	public void setOccupationcode(String occupationcode) {
		this.occupationcode = occupationcode;
	}

	public String getAccountholdertypecode() {
		return accountholdertypecode;
	}

	public void setAccountholdertypecode(String accountholdertypecode) {
		this.accountholdertypecode = accountholdertypecode;
	}

	public List<CaisHolderPhoneDetailsListRes> getCaisholderphonedetailslist() {
		
		if(caisholderphonedetailslist!=null)
		{
			for(CaisHolderPhoneDetailsListRes res:caisholderphonedetailslist)
			{
				res.setCaisAccountDetailsRes(this);
			}
		}
		return caisholderphonedetailslist;
	}

	public void setCaisholderphonedetailslist(List<CaisHolderPhoneDetailsListRes> caisholderphonedetailslist) {
		this.caisholderphonedetailslist = caisholderphonedetailslist;
	}

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}

	public List<CaisHolderDetailsListRes> getCaisholderdetailslist() {
		if(caisholderdetailslist!=null)
		{
			for(CaisHolderDetailsListRes res:caisholderdetailslist)
			{
				res.setCaisAccountDetailsRes(this);
			}
		}
		return caisholderdetailslist;
	}

	public void setCaisholderdetailslist(List<CaisHolderDetailsListRes> caisholderdetailslist) {
		this.caisholderdetailslist = caisholderdetailslist;
	}

	public CaisAccountRes getCaisAccountRes() {
		return caisAccountRes;
	}

	public void setCaisAccountRes(CaisAccountRes caisAccountRes) {
		this.caisAccountRes = caisAccountRes;
	}

	public String getScheduledmonthlyPayAmt() {
		return scheduledmonthlyPayAmt;
	}

	public void setScheduledmonthlyPayAmt(String scheduledmonthlyPayAmt) {
		this.scheduledmonthlyPayAmt = scheduledmonthlyPayAmt;
	}

	public String getHighestCrOrignalLoanAmt() {
		return highestCrOrignalLoanAmt;
	}

	public void setHighestCrOrignalLoanAmt(String highestCrOrignalLoanAmt) {
		this.highestCrOrignalLoanAmt = highestCrOrignalLoanAmt;
	}

	public String getSuitfiledwllfldefultWtnofSt() {
		return suitfiledwllfldefultWtnofSt;
	}

	public void setSuitfiledwllfldefultWtnofSt(String suitfiledwllfldefultWtnofSt) {
		this.suitfiledwllfldefultWtnofSt = suitfiledwllfldefultWtnofSt;
	}

	@Override
	public String toString() {
		return "CaisAccountDetailsRes [caisAccDetailsId=" + caisAccDetailsId + ", rateofinterest=" + rateofinterest
				+ ", settelmentamount=" + settelmentamount + ", scheduledmonthlyPayAmt=" + scheduledmonthlyPayAmt
				+ ", consumercomments=" + consumercomments + ", occupationcode=" + occupationcode
				+ ", accountholdertypecode=" + accountholdertypecode + ", caisholderphonedetailslist="
				+ caisholderphonedetailslist + ", accountstatus=" + accountstatus + ", caisholderdetailslist="
				+ caisholderdetailslist + ", subscribercomments=" + subscribercomments + ", termsfrequency="
				+ termsfrequency + ", amountpastdue=" + amountpastdue + ", identificationnumber=" + identificationnumber
				+ ", portfoliotype=" + portfoliotype + ", repaymenttenure=" + repaymenttenure + ", dateclosed="
				+ dateclosed + ", incomefrequencyindicator=" + incomefrequencyindicator + ", income=" + income
				+ ", highestCrOrignalLoanAmt=" + highestCrOrignalLoanAmt + ", writtenoffamounttotal="
				+ writtenoffamounttotal + ", accounttype=" + accounttype + ", subscribername=" + subscribername
				+ ", typeofcollateral=" + typeofcollateral + ", termsduration=" + termsduration
				+ ", caisholderiddetails=" + caisholderiddetails + ", caisaccounthistorylist=" + caisaccounthistorylist
				+ ", datereported=" + datereported + ", dateoflastpayment=" + dateoflastpayment
				+ ", valueofcreditslastmonth=" + valueofcreditslastmonth + ", dateofaddition=" + dateofaddition
				+ ", specialcomment=" + specialcomment + ", opendate=" + opendate + ", currentbalance=" + currentbalance
				+ ", paymenthistoryprofile=" + paymenthistoryprofile + ", caisholderaddressdetailslist="
				+ caisholderaddressdetailslist + ", paymentrating=" + paymentrating + ", litigationstatusdate="
				+ litigationstatusdate + ", incomeindicator=" + incomeindicator + ", historymatrix=" + historymatrix
				+ ", accountnumber=" + accountnumber + ", pramotionalrateflag=" + pramotionalrateflag
				+ ", currencycode=" + currencycode + ", suitfiledwilfuldefault=" + suitfiledwilfuldefault
				+ ", valueofcollateral=" + valueofcollateral + ", writtenoffamountprincipal="
				+ writtenoffamountprincipal + ", suitfiledwllfldefultWtnofSt=" + suitfiledwllfldefultWtnofSt
				+ ", writeoffstatusdate=" + writeoffstatusdate + ", writtenoffsettledstatus=" + writtenoffsettledstatus
				+ ", defaultstatusdate=" + defaultstatusdate + ", creditlimitamount=" + creditlimitamount + "]";
	}
	
}
