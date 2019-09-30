package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_HSN_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HSNResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

	private String uniqueid;
	private String policyLink;
	private String chapterNotes;
	private String headingDesc;
	private String sectionDesc;
	private String itemDesc2;
	private String itemDesc1;
	private String chapterNo;
	private String policy;
	private String chapterDesc;
	private String policyConditions;
	private String sectionNo;
	private String corelationid;
	@Column(name="POLICYLINK")
	public String getPolicyLink() {
		return policyLink;
	}
	public void setPolicyLink(String policyLink) {
		this.policyLink = policyLink;
	}
	@Column(name="CHAPTERNOTES")
	public String getChapterNotes() {
		return chapterNotes;
	}
	public void setChapterNotes(String chapterNotes) {
		this.chapterNotes = chapterNotes;
	}
	@Column(name="HEADINGDESC")
	public String getHeadingDesc() {
		return headingDesc;
	}
	public void setHeadingDesc(String headingDesc) {
		this.headingDesc = headingDesc;
	}
	@Column(name="SECTIONDESC")
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}
	@Column(name="ITEMDESC2")
	public String getItemDesc2() {
		return itemDesc2;
	}
	
	public void setItemDesc2(String itemDesc2) {
		this.itemDesc2 = itemDesc2;
	}
	@Column(name="ITEMDESC1")
	public String getItemDesc1() {
		return itemDesc1;
	}
	public void setItemDesc1(String itemDesc1) {
		this.itemDesc1 = itemDesc1;
	}
	@Transient
	public String getChapterNo() {
		return chapterNo;
	}
	
	public void setChapterNo(String chapterNo) {
		this.chapterNo = chapterNo;
	}
	@Column(name="POLICY")
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	@Column(name="CHAPTERDESC")
	public String getChapterDesc() {
		return chapterDesc;
	}
	public void setChapterDesc(String chapterDesc) {
		this.chapterDesc = chapterDesc;
	}
	@Column(name="POLICYCONDITIONS")
	public String getPolicyConditions() {
		return policyConditions;
	}
	public void setPolicyConditions(String policyConditions) {
		this.policyConditions = policyConditions;
	}
	@Column(name="SECTIONNO")
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	
	@Override
	public String toString() {
		return "HSNResult [policyLink=" + policyLink + ", chapterNotes=" + chapterNotes + ", headingDesc=" + headingDesc
				+ ", sectionDesc=" + sectionDesc + ", itemDesc2=" + itemDesc2 + ", itemDesc1=" + itemDesc1
				+ ", chapterNo=" + chapterNo + ", policy=" + policy + ", chapterDesc=" + chapterDesc
				+ ", policyConditions=" + policyConditions + ", sectionNo=" + sectionNo + "]";
	}
	
}
