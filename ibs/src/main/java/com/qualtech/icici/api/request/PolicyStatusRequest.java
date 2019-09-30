
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
@Table(name="IB_IC_POL_STS_REQ")
public class PolicyStatusRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	private int eid;
	private String source;
	private String appName;
	private String reqType;
	private String reqSubType;
	@OneToOne (fetch=FetchType.LAZY,mappedBy="policyStatusRequest", cascade=CascadeType.ALL)
	private ParamListRequest paramListMap;
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
	public ParamListRequest getParamListMap() {
		paramListMap.setPolicyStatusRequest(this);
		return paramListMap;
	}
	public void setParamListMap(ParamListRequest paramListMap) {
		this.paramListMap = paramListMap;
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	@Override
	public String toString() {
		return "PolicyStatusRequest [source=" + source + ", appName=" + appName + ", reqType=" + reqType
				+ ", reqSubType=" + reqSubType + ", paramListMap=" + paramListMap + "]";
	}
}
