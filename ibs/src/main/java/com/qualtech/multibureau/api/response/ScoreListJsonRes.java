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
@Table(name = "IB_BUREAU_SCORE_JSON_RES")
public class ScoreListJsonRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_SCORE_JSON_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int scoreId;

	private String score;
	
	private String scorecardversion;
	
	private String scoredate;
	
	private String scorename;
	
	private String scorecardname;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScorecardversion() {
		return scorecardversion;
	}

	public void setScorecardversion(String scorecardversion) {
		this.scorecardversion = scorecardversion;
	}

	public String getScoredate() {
		return scoredate;
	}

	public void setScoredate(String scoredate) {
		this.scoredate = scoredate;
	}

	public String getScorename() {
		return scorename;
	}

	public void setScorename(String scorename) {
		this.scorename = scorename;
	}

	public String getScorecardname() {
		return scorecardname;
	}

	public void setScorecardname(String scorecardname) {
		this.scorecardname = scorecardname;
	}

	@Override
	public String toString() {
		return "ScoreListJsonRes [scoreId=" + scoreId + ", score=" + score + ", scorecardversion=" + scorecardversion
				+ ", scoredate=" + scoredate + ", scorename=" + scorename + ", scorecardname=" + scorecardname + "]";
	}

}
