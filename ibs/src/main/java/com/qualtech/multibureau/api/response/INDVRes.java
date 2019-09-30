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
@Table(name = "IB_BUREAU_INDV_RES")
public class INDVRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_INDV_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int indResId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "indvRes", cascade = CascadeType.ALL)
	private SummaryRes summary;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "indvRes", cascade = CascadeType.ALL)
	@JsonProperty("secsummary")
	private SecSummaryRes secsummary;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "indvRes", cascade = CascadeType.ALL)
	@JsonProperty("primarysummary")
	private PriSummaryRes primarysummary;
	

	@OneToOne
	@JoinColumn(name = "indvId", nullable = false)
	@JsonIgnore
	private INDVReportRes indvReportRes;


	public int getIndResId() {
		return indResId;
	}


	public void setIndResId(int indResId) {
		this.indResId = indResId;
	}


	public SummaryRes getSummary() {
		if(this.summary!=null)
		{
			this.summary.setIndvRes(this);
		}
		return summary;
	}


	public void setSummary(SummaryRes summary) {
		this.summary = summary;
	}


	public SecSummaryRes getSecsummary() {
		if(this.secsummary!=null)
		{
			this.secsummary.setIndvRes(this);
		}
		return secsummary;
	}


	public void setSecsummary(SecSummaryRes secsummary) {
		this.secsummary = secsummary;
	}


	public PriSummaryRes getPrimarysummary() {
		if(this.primarysummary!=null)
		{
			this.primarysummary.setIndvRes(this);
		}
		return primarysummary;
	}


	public void setPrimarysummary(PriSummaryRes primarysummary) {
		this.primarysummary = primarysummary;
	}


	public INDVReportRes getIndvReportRes() {
		return indvReportRes;
	}


	public void setIndvReportRes(INDVReportRes indvReportRes) {
		this.indvReportRes = indvReportRes;
	}


	@Override
	public String toString() {
		return "INDVRes [indResId=" + indResId + ", summary=" + summary + ", secsummary=" + secsummary
				+ ", primarysummary=" + primarysummary + "]";
	}
	
}
