package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_ACCOUNT_LIST_RES")
public class AccountListRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ACCOUNTLIST_SQC", allocationSize = 1)
	@JsonIgnore
	private int accountId;

	private String datereportedandcertified;
	private String rateofinterest;
	private String creditlimit;
	private String paymenthistoryenddate;
	private String reportingmembershortname;
	private String paymenthistory1;
	private String ownershipindicator;
	private String currentbalance;
	private String accounttype;
	private String dateopenedordisbursed;
	private String paymenthistorystartdate;
	private String paymentfrequence;
	private String highcreditorsanctionedamount;
	private String actualpaymentamount;
	private String typeofcollateral;
	private String valueofcollateral;
	private String emiamount;
	private String dateoflastpayment;
	private String repaymenttenure;
	private String dateclosed;
	private String cashlimit;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getDatereportedandcertified() {
		return datereportedandcertified;
	}

	public void setDatereportedandcertified(String datereportedandcertified) {
		this.datereportedandcertified = datereportedandcertified;
	}

	public String getRateofinterest() {
		return rateofinterest;
	}

	public void setRateofinterest(String rateofinterest) {
		this.rateofinterest = rateofinterest;
	}

	public String getCreditlimit() {
		return creditlimit;
	}

	public void setCreditlimit(String creditlimit) {
		this.creditlimit = creditlimit;
	}

	public String getPaymenthistoryenddate() {
		return paymenthistoryenddate;
	}

	public void setPaymenthistoryenddate(String paymenthistoryenddate) {
		this.paymenthistoryenddate = paymenthistoryenddate;
	}

	public String getReportingmembershortname() {
		return reportingmembershortname;
	}

	public void setReportingmembershortname(String reportingmembershortname) {
		this.reportingmembershortname = reportingmembershortname;
	}

	public String getPaymenthistory1() {
		return paymenthistory1;
	}

	public void setPaymenthistory1(String paymenthistory1) {
		this.paymenthistory1 = paymenthistory1;
	}

	public String getOwnershipindicator() {
		return ownershipindicator;
	}

	public void setOwnershipindicator(String ownershipindicator) {
		this.ownershipindicator = ownershipindicator;
	}

	public String getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(String currentbalance) {
		this.currentbalance = currentbalance;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getDateopenedordisbursed() {
		return dateopenedordisbursed;
	}

	public void setDateopenedordisbursed(String dateopenedordisbursed) {
		this.dateopenedordisbursed = dateopenedordisbursed;
	}

	public String getPaymenthistorystartdate() {
		return paymenthistorystartdate;
	}

	public void setPaymenthistorystartdate(String paymenthistorystartdate) {
		this.paymenthistorystartdate = paymenthistorystartdate;
	}

	public String getPaymentfrequence() {
		return paymentfrequence;
	}

	public void setPaymentfrequence(String paymentfrequence) {
		this.paymentfrequence = paymentfrequence;
	}

	public String getHighcreditorsanctionedamount() {
		return highcreditorsanctionedamount;
	}

	public void setHighcreditorsanctionedamount(String highcreditorsanctionedamount) {
		this.highcreditorsanctionedamount = highcreditorsanctionedamount;
	}

	public String getActualpaymentamount() {
		return actualpaymentamount;
	}

	public void setActualpaymentamount(String actualpaymentamount) {
		this.actualpaymentamount = actualpaymentamount;
	}

	public String getTypeofcollateral() {
		return typeofcollateral;
	}

	public void setTypeofcollateral(String typeofcollateral) {
		this.typeofcollateral = typeofcollateral;
	}

	public String getValueofcollateral() {
		return valueofcollateral;
	}

	public void setValueofcollateral(String valueofcollateral) {
		this.valueofcollateral = valueofcollateral;
	}

	public String getEmiamount() {
		return emiamount;
	}

	public void setEmiamount(String emiamount) {
		this.emiamount = emiamount;
	}

	public String getDateoflastpayment() {
		return dateoflastpayment;
	}

	public void setDateoflastpayment(String dateoflastpayment) {
		this.dateoflastpayment = dateoflastpayment;
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

	public String getCashlimit() {
		return cashlimit;
	}

	public void setCashlimit(String cashlimit) {
		this.cashlimit = cashlimit;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "AccountListRes [accountId=" + accountId + ", datereportedandcertified=" + datereportedandcertified
				+ ", rateofinterest=" + rateofinterest + ", creditlimit=" + creditlimit + ", paymenthistoryenddate="
				+ paymenthistoryenddate + ", reportingmembershortname=" + reportingmembershortname
				+ ", paymenthistory1=" + paymenthistory1 + ", ownershipindicator=" + ownershipindicator
				+ ", currentbalance=" + currentbalance + ", accounttype=" + accounttype + ", dateopenedordisbursed="
				+ dateopenedordisbursed + ", paymenthistorystartdate=" + paymenthistorystartdate + ", paymentfrequence="
				+ paymentfrequence + ", highcreditorsanctionedamount=" + highcreditorsanctionedamount
				+ ", actualpaymentamount=" + actualpaymentamount + ", typeofcollateral=" + typeofcollateral
				+ ", valueofcollateral=" + valueofcollateral + ", emiamount=" + emiamount + ", dateoflastpayment="
				+ dateoflastpayment + ", repaymenttenure=" + repaymenttenure + ", dateclosed=" + dateclosed
				+ ", cashlimit=" + cashlimit + "]";
	}

}
