package com.qualtech.cibil.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_ENQUIRYSUMMARY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EnquirySummary implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -864630697449231203L;
	private Long summaryId;
	private String enquiryPurpose="All Enquiries";
	private String total="";
	private String past30Days="";
	private String past90Days="";
	private String past12Months="";
	private String past24Months="";
	private String recent="";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_EnquirySummary_SQC")
    @SequenceGenerator(name = "IB_CBL_EnquirySummary_SQC", sequenceName = "IB_CBL_EnquirySummary_SQC")
	public Long getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(Long summaryId) {
		this.summaryId = summaryId;
	}
	@Column(name="ENQUIRYPURPOSE")
	public String getEnquiryPurpose() {
		return enquiryPurpose;
	}
	public void setEnquiryPurpose(String enquiryPurpose) {
		this.enquiryPurpose = enquiryPurpose;
	}
	@Column(name="TOTAL")
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@Column(name="PAST30DAYS")
	public String getPast30Days() {
		return past30Days;
	}
	public void setPast30Days(String past30Days) {
		this.past30Days = past30Days;
	}
	@Column(name="PAST12MONTHS")
	public String getPast12Months() {
		return past12Months;
	}
	public void setPast12Months(String past12Months) {
		this.past12Months = past12Months;
	}
	@Column(name="PAST24MONTHS")
	public String getPast24Months() {
		return past24Months;
	}
	public void setPast24Months(String past24Months) {
		this.past24Months = past24Months;
	}
	@Column(name="RECENT")
	public String getRecent() {
		return recent;
	}
	public void setRecent(String recent) {
		this.recent = recent;
	}
	@Column(name="PAST90DAYS")
	public String getPast90Days() {
		return past90Days;
	}
	public void setPast90Days(String past90Days) {
		this.past90Days = past90Days;
	}
	@Override
	public String toString() {
		return "EnquirySummary [summaryId=" + summaryId + ", enquiryPurpose="
				+ enquiryPurpose + ", total=" + total + ", past30Days="
				+ past30Days + ", past90Days=" + past90Days + ", past12Months="
				+ past12Months + ", past24Months=" + past24Months + ", recent="
				+ recent + "]";
	}
	@ManyToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}
	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}
}
