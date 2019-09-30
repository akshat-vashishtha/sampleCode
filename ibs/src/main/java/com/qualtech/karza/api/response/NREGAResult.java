package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_NREGA_RES")
public class NREGAResult implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String correlation_Id;
	@Column(name = "FAMILY_ID_1")
	private String familyId1;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "NAME_OF_FATHER_OR_HUSBAND")
	private String nameOfFatherOrHusband;
	@Column(name = "VOTER_ID")
	private String voterId;
	@Column(name = "VILLAGE")
	private String village;
	@Column(name = "BPL_FAMILY_ID")
	private String bplFamilyId;
	
	@OrderColumn(name="sequence_Id")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="nregaResult", cascade=CascadeType.ALL)
	private List<NREGAIncomeDetail> incomeDetail;
	
	@Column(name = "FAMILY_ID")
	private String familyId;
	@Column(name = "CATEGORY")
	private String category;
	
	@OrderColumn(name="SEQUENCE_ID")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="nregaResultApplicant", cascade=CascadeType.ALL)
	private List<NREGAApplicantDetail> applicantDetail;
	
	@Column(name = "DISTRICT")
	private String district;
	@Column(name = "NAME_OF_HEAD")
	private String nameOfHead;
	@Column(name = "PHOTO_IMAGE_URL")
	private String photoImageUrl;
	@Column(name = "BPL_FAMILY")
	private String bplFamily;
	@Column(name = "JOB_CARD_NO")
	private String jobcardno;
	@Column(name = "DATE_OF_REGISTRATION")
	private String dateOfRegistration;
	@Column(name = "PANCHAYAT")
	private String panchayat;
	@Column(name = "BLOCK")
	private String block;
	public String getFamilyId1() {
		return familyId1;
	}
	public void setFamilyId1(String familyId1) {
		this.familyId1 = familyId1;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNameOfFatherOrHusband() {
		return nameOfFatherOrHusband;
	}
	public void setNameOfFatherOrHusband(String nameOfFatherOrHusband) {
		this.nameOfFatherOrHusband = nameOfFatherOrHusband;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getBplFamilyId() {
		return bplFamilyId;
	}
	public void setBplFamilyId(String bplFamilyId) {
		this.bplFamilyId = bplFamilyId;
	}
	public List<NREGAIncomeDetail> getIncomeDetail() {
		for (NREGAIncomeDetail detail : incomeDetail) {
			detail.setNregaResult(this);
		}
		return incomeDetail;
	}
	public void setIncomeDetail(List<NREGAIncomeDetail> incomeDetail) {
		this.incomeDetail = incomeDetail;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<NREGAApplicantDetail> getApplicantDetail() {
		for (NREGAApplicantDetail nregaApplicantDetail : applicantDetail) {
			nregaApplicantDetail.setNregaResultApplicant(this);
		}
		return applicantDetail;
	}
	public void setApplicantDetail(List<NREGAApplicantDetail> applicantDetail) {
		this.applicantDetail = applicantDetail;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getNameOfHead() {
		return nameOfHead;
	}
	public void setNameOfHead(String nameOfHead) {
		this.nameOfHead = nameOfHead;
	}
	public String getPhotoImageUrl() {
		return photoImageUrl;
	}
	public void setPhotoImageUrl(String photoImageUrl) {
		this.photoImageUrl = photoImageUrl;
	}
	public String getBplFamily() {
		return bplFamily;
	}
	public void setBplFamily(String bplFamily) {
		this.bplFamily = bplFamily;
	}
	public String getJobcardno() {
		return jobcardno;
	}
	public void setJobcardno(String jobcardno) {
		this.jobcardno = jobcardno;
	}
	public String getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getCorrelation_Id() {
		return correlation_Id;
	}
	public void setCorrelation_Id(String correlation_Id) {
		this.correlation_Id = correlation_Id;
	}
	@Override
	public String toString() {
		return "NREGAResult [familyId1=" + familyId1 + ", address=" + address + ", nameOfFatherOrHusband="
				+ nameOfFatherOrHusband + ", voterId=" + voterId + ", village=" + village + ", bplFamilyId="
				+ bplFamilyId + ", incomeDetail=" + incomeDetail + ", familyId=" + familyId + ", category=" + category
				+ ", applicantDetail=" + applicantDetail + ", district=" + district + ", nameOfHead=" + nameOfHead
				+ ", photoImageUrl=" + photoImageUrl + ", bplFamily=" + bplFamily + ", jobcardno=" + jobcardno
				+ ", dateOfRegistration=" + dateOfRegistration + ", panchayat=" + panchayat + ", block=" + block + "]";
	}
	
	
	
}
