package com.qualtech.crif.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_CRF_SCORE")
public class CriffScore 
{
	 private String	score_comments;
	 private String	score_type;
	 private String score_value;
	 private Long criff_scores_id;
     private CriffDetailLogs crifdetaillogs;
	 
    @OneToOne
    @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
    public CriffDetailLogs getCrifdetaillogs() {
 		return crifdetaillogs;
 	}
 	public void setCrifdetaillogs(CriffDetailLogs crifdetaillogs) {
 		this.crifdetaillogs = crifdetaillogs;	
 	}
	
	@Column(name="SCORE_COMMENTS")
	public String getScore_comments() {
		return score_comments;
	}
	
	public void setScore_comments(String score_comments) {
		this.score_comments = score_comments;
	}
	
	@Column(name="SCORE_TYPE")
	public String getScore_type() {
		return score_type;
	}
	public void setScore_type(String score_type) {
		this.score_type = score_type;
	}
	
	@Column(name="SCORE_VALUE")
	public String getScore_value() {
		return score_value;
	}
	public void setScore_value(String score_value) {
		this.score_value = score_value;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_SATATUS_SQC")
	@SequenceGenerator(name="IB_CRF_SATATUS_SQC", sequenceName = "IB_CRF_SATATUS_SQC", allocationSize = 1)
	@Column(name="CRIFF_SCORES_ID")
	public Long getCriff_scores_id() {
		return criff_scores_id;
	}
	public void setCriff_scores_id(Long criff_scores_id) {
		this.criff_scores_id = criff_scores_id;
	}
	@Override
	public String toString() {
		return "CriffScore [score_comments=" + score_comments + ", score_type=" + score_type + ", score_value="
				+ score_value + ", criff_scores_id=" + criff_scores_id + ", crifdetaillogs=" + crifdetaillogs + "]";
	}

}
