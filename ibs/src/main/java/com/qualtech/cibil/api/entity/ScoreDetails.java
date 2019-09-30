package com.qualtech.cibil.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_SCORE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ScoreDetails implements Serializable
{
	private static final long serialVersionUID = 7406273010133888675L;
	private Long scoreId;
	private String score="";
	private String scoredate="";
	private String scorecardName="";
	
	private List<ScoringFactors> scoringFactors;
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
	private String scoringfactorbkp;
	
	@Column(name="SCORE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_SCORE_SQC")
    @SequenceGenerator(name = "IB_CBL_SCORE_SQC", sequenceName = "IB_CBL_SCORE_SQC")
    public Long getScoreId() {
		return scoreId;
	}
	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}
	@Column(name="SCORE")
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Column(name="SCORE_DATE")
	public String getScoredate() {
		return scoredate;
	}
	public void setScoredate(String scoredate) {
		this.scoredate = scoredate;
	}
	@Column(name="SCORE_NAME")
	public String getScorecardName() {
		return scorecardName;
	}
	public void setScorecardName(String scorecardName) {
		this.scorecardName = scorecardName;
	}
	@Transient
	public List<ScoringFactors> getScoringFactors() {
		return scoringFactors;
	}
	public void setScoringFactors(List<ScoringFactors> scoringFactors) {
		this.scoringFactors = scoringFactors;
	}
	@Override
	public String toString() {
		return "ScoreDetails [scoreId=" + scoreId + ", score=" + score
				+ ", scoredate=" + scoredate + ", scorecardName="
				+ scorecardName + ", scoringFactors=" + scoringFactors + "]";
	}
	
	@Column(name="SCORE_FACTORS")
	public String getScoringfactorbkp() {
		
		return scoringfactorbkp;
	}
	public void setScoringfactorbkp(String scoringfactorbkp) {
		this.scoringfactorbkp = scoringfactorbkp;
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
