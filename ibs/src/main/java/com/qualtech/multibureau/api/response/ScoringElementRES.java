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
@Table(name = "IB_BUREAU_SCORE_ELEMENT_RES")
public class ScoringElementRES implements Serializable {

	private static final long serialVersionUID = -253021057991052198L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_SCORE_ELEMENT_SQC", allocationSize = 1)
	@JsonIgnore
	private int scoreElementId;

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "scoringElmRes" ,cascade=CascadeType.ALL)
	private List<ScoringElementListRes> scoringelementlist;

	@OneToOne
	@JoinColumn(name = "reportDataId", nullable = false)
	@JsonIgnore
	private ReportDataRes reportDataRes;

	public int getScoreElementId() {
		return scoreElementId;
	}

	public void setScoreElementId(int scoreElementId) {
		this.scoreElementId = scoreElementId;
	}

	public List<ScoringElementListRes> getScoringelementlist() {
		
		if(scoringelementlist!=null)
		{
			for(ScoringElementListRes res:scoringelementlist)
			{
				res.setScoringElmRes(this);
			}
		}
		
		return scoringelementlist;
	}

	public void setScoringelementlist(List<ScoringElementListRes> scoringelementlist) {
		this.scoringelementlist = scoringelementlist;
	}

	public ReportDataRes getReportDataRes() {
		return reportDataRes;
	}

	public void setReportDataRes(ReportDataRes reportDataRes) {
		this.reportDataRes = reportDataRes;
	}

	@Override
	public String toString() {
		return "ScoringElementRES [scoreElementId=" + scoreElementId + ", scoringelementlist=" + scoringelementlist
				+ "]";
	}
	
}