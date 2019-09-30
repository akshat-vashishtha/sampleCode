package com.qualtech.icici.api.request;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_I_PS_REQ")
public class PolicyStatusPayload implements Serializable {

	private static final long serialVersionUID = 1967711269513368218L;

	@JsonIgnore
	@Id
	private int eid;
	private String source;
	private String appName;
	private String reqType;
	private String reqSubType;
	@OneToOne (fetch=FetchType.LAZY,mappedBy="policyStatusPayload", cascade=CascadeType.ALL)
	private PolicyStatusParamListMap paramListMap;

	public PolicyStatusParamListMap getParamListMap() {
		paramListMap.setPolicyStatusPayload(this);
		return paramListMap;
	}

	public void setParamListMap(PolicyStatusParamListMap paramListMap) {
		this.paramListMap = paramListMap;
	}
	
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getReqSubType() {
		return reqSubType;
	}

	public void setReqSubType(String reqSubType) {
		this.reqSubType = reqSubType;
	}

	

	@Override
	public String toString() {
		return "PolicyStatusPayload [eid=" + eid + ", source=" + source + ", appName=" + appName + ", reqType="
				+ reqType + ", reqSubType=" + reqSubType + ", paramListMap=" + paramListMap + "]";
	}

}
