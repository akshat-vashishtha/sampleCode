package com.qualtech.kotak.api.request;

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
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_REQ_RES")
public class KotakReqRes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="UNIQUEID",length=38)
	private String uniqueId;
	
	
	@Id
	@GeneratedValue(generator = "QCIB_KOTAK_REQ_RES")
	@SequenceGenerator(name = "QCIB_KOTAK_REQ_RES", sequenceName = "IB_KOTAK_REQ_RES_SQC")
	private long eid;
	
	@Lob
	@Column(name="REQUEST")
	private String request;
	@Lob
	@Column(name="RESPONSE")
	private String response;
	@Column(name="TAG")
	private String tag;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="CORELATIONID")
	private String corelationid;
	@Column(name="APPID")
	private String appid;
	//private String userid;
	//private String password;
	@Column(name="TOKEN")
	private String token;
	@Column(name="STATUS")
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_TIME")
	private Date updatedTime;
	@Column(name="PDFPATH")
	private String pdfPath;
	@Lob
	@Column(name="INTERNAL_REQ")
    private String intReq;
	@Lob
	@Column(name="INTERNAL_RES")
    private String intRes;
    @Column(name="PRICE")
    private String price;
   
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED")
     private Date created;
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	@Lob
	public String getIntReq() {
		return intReq;
	}
	public void setIntReq(String intReq) {
		this.intReq = intReq;
	}
	@Lob
	public String getIntRes() {
		return intRes;
	}
	public void setIntRes(String intRes) {
		this.intRes = intRes;
	}
	
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	/*public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}*/
	/*public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}*/
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	@Lob
	public String getRequest() {
		return request;
	}
	

	public void setRequest(String request) {
		this.request = request;
	}
	@Lob
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
		return "KotakReqRes [uniqueId=" + uniqueId + ", request=" + request + ", response=" + response + ", tag="
				+ tag + ", description=" + description + ", corelationid="
				+ corelationid + ", appid=" + appid + ", token=" + token
				+ ", status=" + status + ", updatedTime=" + updatedTime
				+ ", pdfPath=" + pdfPath + ", intReq=" + intReq + ", intRes="
				+ intRes + ", price=" + price + ", created=" + created + "]";
	}

	
	
}
