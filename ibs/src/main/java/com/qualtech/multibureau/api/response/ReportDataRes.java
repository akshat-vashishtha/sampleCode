package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_REPORT_DATA_RES")
public class ReportDataRes implements Serializable {

	private static final long serialVersionUID = -487772733524459318L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_REPORT_DATA_SQC", allocationSize = 1)
	@JsonIgnore
	private int reportDataId;

	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "reportDataRes" ,cascade=CascadeType.ALL)
	private ScoreRes score;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "reportDataRes" ,cascade=CascadeType.ALL)
	private List<ErrorListRes> errorlist;

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "reportDataRes" ,cascade=CascadeType.ALL)
	private ScoringElementRES scoringelement;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "reportDataRes" ,cascade=CascadeType.ALL)
	private AccountSumary accountsummary;
	
	
	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getReportDataId() {
		return reportDataId;
	}

	public void setReportDataId(int reportDataId) {
		this.reportDataId = reportDataId;
	}

	public ScoreRes getScore() {
		if(this.score!=null)
		{
			this.score.setReportDataRes(this);
		}
		return score;
	}

	public void setScore(ScoreRes score) {
		this.score = score;
	}
	
	public List<ErrorListRes> getErrorlist() {
		
		if(errorlist!=null)
		{
			for(ErrorListRes res:errorlist)
			{
				res.setReportDataRes(this);
			}
		}
		return errorlist;
	}

	public void setErrorlist(List<ErrorListRes> errorlist) {
		this.errorlist = errorlist;
	}

	public ScoringElementRES getScoringelement() {
		if(this.scoringelement!=null)
		{
			this.scoringelement.setReportDataRes(this);
		}
		return scoringelement;
	}

	public void setScoringelement(ScoringElementRES scoringelement) {
		this.scoringelement = scoringelement;
	}


	public JsonBureauRes getJsonBureauRes() {
		if(this.jsonBureauRes!=null)
		{
			this.jsonBureauRes.setReportdata(this);
		}
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	public AccountSumary getAccountsummary() {
		if(this.accountsummary!=null)
		{
			this.accountsummary.setReportDataRes(this);
		}
		return accountsummary;
	}

	public void setAccountsummary(AccountSumary accountsummary) {
		this.accountsummary = accountsummary;
	}

	@Override
	public String toString() {
		return "ReportDataRes [reportDataId=" + reportDataId + ", score=" + score + ", errorlist=" + errorlist
				+ ", scoringelement=" + scoringelement + ", accountsummary=" + accountsummary + "]";
	}
}
