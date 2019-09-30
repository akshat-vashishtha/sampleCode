package com.qualtech.equifax.api.entity.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_EQ_P_PREV_NAMES")
@JsonIgnoreProperties(ignoreUnknown=true)

public class PreviousName implements Serializable {

	private static final long serialVersionUID = -143021602622920938L;
	 @Id
	 @JsonIgnore
	private Long	request_unique_id;	
	@Transient
	private String previousName;
	@Column(name="FIRSTNAME")
	private String first_name;
	@Column(name="MIDDLENAME")
	private String middle_name;
	@Column(name="LASTNAME")
	private String last_name;
	@Column
	private String additionalmiddlename;
	@Column
	private String suffix;
	@Transient
	private Long previousName_id;
	
	

	public Long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(Long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	public void setPreviousName_id(Long previousName_id) {
		this.previousName_id = previousName_id;
	}
	public Long getPreviousName_id() {
		return previousName_id;
	}
	

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

public void setAdditionalmiddlename(String additionalmiddlename) {
	this.additionalmiddlename = additionalmiddlename;
}
public String getAdditionalmiddlename() {
	return additionalmiddlename;
}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	public String getPreviousName() {
		return previousName;
	}

	public void setPreviousName(String previousName) {
		this.previousName = previousName;
	}
	
}
