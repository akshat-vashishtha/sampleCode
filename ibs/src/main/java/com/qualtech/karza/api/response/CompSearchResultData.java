package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name="IB_K_COMPSEARCH_RES")
public class CompSearchResultData implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;
	
	@JsonIgnore
	@Column(name="EID")
	private Long eid;
	@Column(name="SCORE")
	private String score;
	@Column(name="CIN")
	private String cin;
	@Column(name="COMAPANY_NAME")
	private String comapany_name;
	@JsonIgnore
	@Column(name="CORELATIONID")
	private String corelationid;
	@Id
	@JsonIgnore
	@Column(name="SEQUENCE_ID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_COMPSEARCH_FOR_SQC") 
	private Long sequenceid;
	
	
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public Long getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(Long sequenceid) {
		this.sequenceid = sequenceid;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getComapany_name() {
		return comapany_name;
	}
	public void setComapany_name(String comapany_name) {
		this.comapany_name = comapany_name;
	}
	@Override
	public String toString() {
		return "CompSearchResultData [score=" + score + ", cin=" + cin + ", comapany_name=" + comapany_name + "]";
	}
	
	
}
