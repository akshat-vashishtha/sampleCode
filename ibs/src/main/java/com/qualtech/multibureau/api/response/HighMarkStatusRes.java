package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_HIGH_STATUS_RES")
public class HighMarkStatusRes implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3450200537815270175L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_HIGH_STATUS_SQC", allocationSize = 1)
	@JsonIgnore
	private int highmarkStatusId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "highMarkStatusRes", cascade = CascadeType.ALL)
	private ErrorObjRes errorobj;
	
	@Column(name="HIGHMARK_STATUS")
	private String optionstatus;
	
	@Column(name="HIGHMARK_OPTION")
	private String option;
	
	@ManyToOne
	@JoinColumn(name = "statusDetailsId", nullable = false)
	@JsonIgnore
	private StatusDetailsRes statusDetailsRes;

	public int getHighmarkStatusId() {
		return highmarkStatusId;
	}

	public void setHighmarkStatusId(int highmarkStatusId) {
		this.highmarkStatusId = highmarkStatusId;
	}

	public ErrorObjRes getErrorobj() {
		if (this.errorobj != null) {
			this.errorobj.setHighMarkStatusRes(this);
		}
		return errorobj;
	}

	public void setErrorobj(ErrorObjRes errorobj) {
		this.errorobj = errorobj;
	}

	public String getOptionstatus() {
		return optionstatus;
	}

	public void setOptionstatus(String optionstatus) {
		this.optionstatus = optionstatus;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public StatusDetailsRes getStatusDetailsRes() {
		return statusDetailsRes;
	}

	public void setStatusDetailsRes(StatusDetailsRes statusDetailsRes) {
		this.statusDetailsRes = statusDetailsRes;
	}

	@Override
	public String toString() {
		return "HighMarkStatusRes [highmarkStatusId=" + highmarkStatusId + ", errorobj=" + errorobj + ", optionstatus="
				+ optionstatus + ", option=" + option + "]";
	}

	
}
