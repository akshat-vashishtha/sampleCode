package com.qualtech.cibilv2.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_C2_Req_Res")
public class CibilReqRes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_C2_SQC")
	@SequenceGenerator(name = "IB_C2_SQC", sequenceName = "IB_C2_SQC", allocationSize = 1)
	@Column(name = "EID")
	private Long eid;
	
	@Column(name="UNIQUEID")
	private String uniqueId;
	@Column(name="REQUEST")
	@Lob
	private String request;
	@Column(name="RESPONSE")
	@Lob
	private String response;
	@Column(name="TAG")
	private String tag;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="CORELATIONID")
	private String corelationid;
	@Column(name="APPID")
	private String appid;
/*	@Column(name="")
	private String userid;
	@Column(name="")
	private String password;*/
	@Column(name="TOKEN")
	private String token;
	@Column(name="STATUS")
	private String status;
	@Transient
	//@Column(name="UPDATED_TIME")
	private String updatedTime;
	@Column(name="PDFPATH")
	private String pdfPath;
	@Column(name="INTERNAL_REQ")
	@Lob
	private String intReq;
	@Column(name="INTERNAL_RES")
	@Lob
	private String intRes;
	@Column(name="PRICE")
    private String price;

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIntReq() {
		return intReq;
	}
	public void setIntReq(String intReq) {
		this.intReq = intReq;
	}
	public String getIntRes() {
		return intRes;
	}
	public void setIntRes(String intRes) {
		this.intRes = intRes;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	@Override
	public String toString() {
		return "CibilReqRes [eid=" + eid + ", uniqueId=" + uniqueId + ", request=" + request + ", response=" + response
				+ ", tag=" + tag + ", description=" + description + ", corelationid=" + corelationid + ", appid="
				+ appid + ", token=" + token + ", status=" + status + ", updatedTime=" + updatedTime + ", pdfPath="
				+ pdfPath + ", intReq=" + intReq + ", intRes=" + intRes + ", price=" + price + "]";
	}
    

}
