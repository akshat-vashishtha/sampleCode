package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_INDV_REPORT")
public class INDVReportRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_INDV_SQC", allocationSize = 1)
	@JsonIgnore
	private int indvId;

	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvReportRes" ,cascade=CascadeType.ALL)
	@JsonProperty("indvresponses")
	private INDVRes indvresponses;
	
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvReportRes" ,cascade=CascadeType.ALL)
	private AlertsRes alerts;
	
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvReportRes" ,cascade=CascadeType.ALL)
	private RequestRes request;
	
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvReportRes" ,cascade=CascadeType.ALL)
	private PersonalInfoRes personalinfovariation;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvReportRes" ,cascade=CascadeType.ALL)
	private StatusDetailsRes statusdetails;

	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvReportRes" ,cascade=CascadeType.ALL)
	private HeaderRes header;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvReportRes" ,cascade=CascadeType.ALL)
	private AccountSummaryRes accountsummary;
	
	
	@OneToOne
	@JoinColumn(name = "indvRepId", nullable = false)
	@JsonIgnore
	private INDVReportsJsonRes indvBureauRes;


	public int getIndvId() {
		return indvId;
	}


	public void setIndvId(int indvId) {
		this.indvId = indvId;
	}


	public INDVRes getIndvresponses() {
		
		if(this.indvresponses!=null)
		{
			this.indvresponses.setIndvReportRes(this);
		}
		return indvresponses;
	}


	public void setIndvresponses(INDVRes indvresponses) {
		this.indvresponses = indvresponses;
	}


	public AlertsRes getAlerts() {
		if(this.alerts!=null)
		{
			this.alerts.setIndvReportRes(this);
		}
		return alerts;
	}


	public void setAlerts(AlertsRes alerts) {
		this.alerts = alerts;
	}


	public RequestRes getRequest() {
		if(this.request!=null)
		{
			this.request.setIndvReportRes(this);
		}
		return request;
	}


	public void setRequest(RequestRes request) {
		this.request = request;
	}


	public PersonalInfoRes getPersonalinfovariation() {
		if(this.personalinfovariation!=null)
		{
			this.personalinfovariation.setIndvReportRes(this);
		}
		return personalinfovariation;
	}


	public void setPersonalinfovariation(PersonalInfoRes personalinfovariation) {
		this.personalinfovariation = personalinfovariation;
	}


	public StatusDetailsRes getStatusdetails() {
		if(this.statusdetails!=null)
		{
			this.statusdetails.setIndvReportRes(this);
		}
		return statusdetails;
	}


	public void setStatusdetails(StatusDetailsRes statusdetails) {
		this.statusdetails = statusdetails;
	}


	public HeaderRes getHeader() {
		if(this.header!=null)
		{
			this.header.setIndvReportRes(this);
		}
		return header;
	}


	public void setHeader(HeaderRes header) {
		this.header = header;
	}


	public AccountSummaryRes getAccountsummary() {
		if(this.accountsummary!=null)
		{
			this.accountsummary.setIndvReportRes(this);
		}
		return accountsummary;
	}


	public void setAccountsummary(AccountSummaryRes accountsummary) {
		this.accountsummary = accountsummary;
	}


	public INDVReportsJsonRes getIndvBureauRes() {
		return indvBureauRes;
	}


	public void setIndvBureauRes(INDVReportsJsonRes indvBureauRes) {
		this.indvBureauRes = indvBureauRes;
	}


	@Override
	public String toString() {
		return "INDVReportRes [indvId=" + indvId + ", indvresponses=" + indvresponses + ", alerts=" + alerts
				+ ", request=" + request + ", personalinfovariation=" + personalinfovariation + ", statusdetails="
				+ statusdetails + ", header=" + header + ", accountsummary=" + accountsummary + "]";
	}
	
}
