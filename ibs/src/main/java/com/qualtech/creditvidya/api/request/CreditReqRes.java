package com.qualtech.creditvidya.api.request;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_CREDIT_REQ_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditReqRes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3254579733100694576L;
	private Long eid;
	private String uniqueId;
	private String request;
	private String response;
	private String tag;
	private String description;
	private String corelationid;
	private String userid;
	private String password;
	private String token;
	private String status;
	private String appid;
	private Date updatedTime;
	private Date createdTime;
	private String pdfPath;
	private String intReq;
	private String intRes;
	private String price;
	
	@Column(name="PRICE")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Column(name="INTERNAL_REQ",columnDefinition="CLOB") 
	@Lob 
	public String getIntReq() {
		return intReq;
	}
	public void setIntReq(String intReq) {
		this.intReq = intReq;
	}
	
	@Column(name="INTERNAL_RES",columnDefinition="CLOB") 
	@Lob 
	public String getIntRes() {
		return intRes;
	}
	public void setIntRes(String intRes) {
		this.intRes = intRes;
	}
	
	/*@Column(name="PRICE")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}*/
	
	/*@Column(name="PRICE")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}*/
	
	@Column(name="TOKEN")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name="UNIQUEID")
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Column(name="REQUEST",columnDefinition="CLOB") 
	@Lob
	public String getRequest() {
		return request;
	}
	
	

	public void setRequest(String request) {
		this.request = request;
	}
	@Override
	public String toString() {
		return "CreditReqRes [uniqueId=" + uniqueId + ", request=" + request + ", response=" + response + ", tag=" + tag
				+ ", description=" + description + ", corelationid=" + corelationid + ", userid=" + userid
				+ ", password=" + password + ", token=" + token + ", status=" + status + ", appid=" + appid
				+ ", updatedTime=" + updatedTime + ", pdfPath=" + pdfPath + ", intReq=" + intReq + ", intRes=" + intRes
				+ ", price=" + price + "]";
	}
	
	@Column(name="RESPONSE",columnDefinition="CLOB") 
	@Lob
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Column(name="TAG")
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="APPID")
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_TIME")
	public Date getUpdatedTime() {
		
		return new Date();
 	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="PDFPATH")
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Id
	@GeneratedValue(generator="IB_CREDIT_MAIN_SQC")
	@SequenceGenerator(name="IB_CREDIT_MAIN_SQC",sequenceName="IB_CREDIT_MAIN_SQC",allocationSize=1)
	@Column(name="EID")
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	
	
}
