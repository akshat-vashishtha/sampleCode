package com.qualtech.icici.api.request;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = false)


@Entity
@Table(name="IB_I_REQ_RES")
public class IciciReqRes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	@GeneratedValue(generator = "I_MAIN_SQC")
	@SequenceGenerator(name = "I_MAIN_SQC", sequenceName = "IB_I_MAIN_SQC")
	private int eid;

	private String uniqueId;
	@Lob
    @Column(name="REQUEST")
	private String request;
	@Lob
    @Column(name="RESPONSE")
	private String response;
	private String tag;
	private String description;
	private String corelationid;
	private String appid;
	/*private String userid;
	private String password;*/
	private String token;
	private String status;
	private Date created;
	private Date updated_time;
	private String pdfPath;
	@Lob
    @Column(name="INTERNAL_REQ")
    private String internal_req;
	@Lob
    @Column(name="INTERNAL_RES")
    private String internal_res;
    private String price;
	
	
	public String getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "KarzaReqRes [uniqueId=" + uniqueId + ", request=" + request + ", response=" + response + ", tag=" + tag
				+ ", description=" + description + ", corelationid=" + corelationid + ", appid=" + appid + ", userid="
				+ /*userid + ", password=" + password + */", token=" + token + ", status=" + status + ", updatedTime="
				+ updated_time + ", pdfPath=" + pdfPath + ", intReq=" + internal_req + ", intRes=" + internal_res + ", price="
				+ price + "]";
	}
	public void setPrice(String price) {
		this.price = price;
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
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}*/
	
	
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
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getInternal_req() {
		return internal_req;
	}
	public void setInternal_req(String internal_req) {
		this.internal_req = internal_req;
	}
	
	public String getInternal_res() {
		return internal_res;
	}
	public void setInternal_res(String internal_res) {
		this.internal_res = internal_res;
	}
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
}
