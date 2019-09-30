package com.qualtech.cibil.api.entity;

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
import javax.persistence.Transient;
@Entity
@Table(name="IB_CBL_REQ_RES")
public class CibilReqResVpn {

private String uniqueId;                      
	private int eid;
	
	private String tag;
	private String description;
	private String corelationid;
	private String appid;
	private String userid;
	private String password;
	private String token;
	private String status;
	private Date updated_Time;
	private Date createdTime;
	private String pdfPath;

    private String price;
    private String request;
	private String response;
	private String intReq;
 	private String intRes;
   
	 @Override
	public String toString() {
		return "CibilReqResVpn [uniqueId=" + uniqueId + ", eid=" + eid + ", tag=" + tag + ", description=" + description
				+ ", corelationid=" + corelationid + ", appid=" + appid + ", userid=" + userid + ", password="
				+ password + ", token=" + token + ", status=" + status + ", updated_Time=" + updated_Time
				+ ", createdTime=" + createdTime + ", pdfPath=" + pdfPath + ", price=" + price + ", request=" + request
				+ ", response=" + response + ", intReq=" + intReq + ", intRes=" + intRes + "]";
	}
	@Column(name="PRICE")
   public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_TIME")
	public Date getUpdated_Time() {
		return new Date();
	}
	public void setUpdated_Time(Date updated_Time) {
		this.updated_Time = updated_Time;
	}
	@Column(name="APPID")
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	@Transient
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Transient
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="UNIQUEID")
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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
	@Column(name="TOKEN")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="PDFPATH")
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
/*	@Id
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="QCIB_K_MAIN_SQC")
	@Column(name="EID")
	public long getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}*/
	@Id
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_CBL_SEQ",allocationSize=1)
	@Column(name="EID")
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED")
	public Date getCreatedTime() {
		return  new Date();
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@Column(name="REQUEST",columnDefinition="CLOB") 
	@Lob
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
	
	@Column(name="RESPONSE",columnDefinition="CLOB") 
	@Lob
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Lob
	@Column(name="INTERNAL_REQ")
	public String getIntReq() {
		return intReq;
	}
	public void setIntReq(String intReq) {
		this.intReq = intReq;
	}
	@Lob
	@Column(name="INTERNAL_RES")
	public String getIntRes() {
		return intRes;
	}
	public void setIntRes(String intRes) {
		this.intRes = intRes;
	}
	

	
	
	
}
