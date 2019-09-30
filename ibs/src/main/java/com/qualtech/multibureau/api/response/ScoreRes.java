package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_SCORE_RES")
public class ScoreRes implements Serializable {

	private static final long serialVersionUID = -253021057991052198L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_SCORE_SQC", allocationSize = 1)
	@JsonIgnore
	private int scoreId;

	private String description;
	private String name;
	private String value;
	private String reasoncode;

	@OneToOne
	@JoinColumn(name = "reportDataId", nullable = false)
	@JsonIgnore
	private ReportDataRes reportDataRes;

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getReasoncode() {
		return reasoncode;
	}

	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}

	public ReportDataRes getReportDataRes() {
		return reportDataRes;
	}

	public void setReportDataRes(ReportDataRes reportDataRes) {
		this.reportDataRes = reportDataRes;
	}

	@Override
	public String toString() {
		return "ScoreRes [scoreId=" + scoreId + ", description=" + description + ", name=" + name + ", value=" + value
				+ ", reasoncode=" + reasoncode + "]";
	}

}