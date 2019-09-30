package com.qualtech.icici.api.response;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_IC_POL_STS_RES")
public class PolicyStatusResponsePayload implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	private int eid;
	
	@Embedded
	private Result result;
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PolicyStatusResponsePayload [result=" + result + "]";
	}
	
	

}
