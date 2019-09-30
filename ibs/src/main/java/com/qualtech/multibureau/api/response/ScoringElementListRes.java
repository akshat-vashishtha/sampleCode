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
@Table(name = "IB_BUREAU_SCOR_LIST_RES")
public class ScoringElementListRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_SCOR_LIST_SQC", allocationSize = 1)
	@JsonIgnore
	private int scoringListId;

	private String description;
	private String code;

	@ManyToOne
	@JoinColumn(name = "scoreElementId", nullable = false)
	@JsonIgnore
	private ScoringElementRES scoringElmRes;

	public int getScoringListId() {
		return scoringListId;
	}

	public void setScoringListId(int scoringListId) {
		this.scoringListId = scoringListId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ScoringElementRES getScoringElmRes() {
		return scoringElmRes;
	}

	public void setScoringElmRes(ScoringElementRES scoringElmRes) {
		this.scoringElmRes = scoringElmRes;
	}

	@Override
	public String toString() {
		return "ScoringElementListRes [scoringListId=" + scoringListId + ", description=" + description + ", code="
				+ code + "]";
	}
	
}
