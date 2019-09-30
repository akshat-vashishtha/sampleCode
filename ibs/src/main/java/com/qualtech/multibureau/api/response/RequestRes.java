package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_REQUEST_REPO")
public class RequestRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_REQUEST_SQC", allocationSize = 1)
	@JsonIgnore
	private int reqId;

	private String ageason;
	private String loanamount;
	private String aka;
	private String creditrequesttype;

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "requestRes" ,cascade=CascadeType.ALL)
	private IDsRes ids;
		
	private String mfiscore;
	private String creditinquirystage;
	private String creditinquirypurposetype;
	private String email1;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "requestRes" ,cascade=CascadeType.ALL)
	private PhoneRes phones;

	private String cnsscore;
	private String mfiInd;
	@Column(name="CREDIT_INQ_TYPE_DESC")
	private String creditinquirypurposetypedescription;
	private String mfigroup;
	private String name;
	private String dob;
	private String gender;
	@Column(name="CREDIT_REPO_TRANS_DATE_TIME")
	private String creditreporttransectiondatetime;
	private String branch;
	private String memberid;
	
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "requestRes" ,cascade=CascadeType.ALL)
	private AddressRes addresses;
	
	private String cnsind;
	private String losapplicationid;
	
	private String kendra;
	
	@OneToOne
	@JoinColumn(name = "indvId", nullable = false)
	@JsonIgnore
	private INDVReportRes indvReportRes;

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getAgeason() {
		return ageason;
	}

	public void setAgeason(String ageason) {
		this.ageason = ageason;
	}

	public String getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public String getCreditrequesttype() {
		return creditrequesttype;
	}

	public void setCreditrequesttype(String creditrequesttype) {
		this.creditrequesttype = creditrequesttype;
	}

	public IDsRes getIds() {
		if(this.ids!=null)
		{
			this.ids.setRequestRes(this);
		}
		return ids;
	}

	public void setIds(IDsRes ids) {
		this.ids = ids;
	}

	public String getMfiscore() {
		return mfiscore;
	}

	public void setMfiscore(String mfiscore) {
		this.mfiscore = mfiscore;
	}

	public String getCreditinquirystage() {
		return creditinquirystage;
	}

	public void setCreditinquirystage(String creditinquirystage) {
		this.creditinquirystage = creditinquirystage;
	}

	public String getCreditinquirypurposetype() {
		return creditinquirypurposetype;
	}

	public void setCreditinquirypurposetype(String creditinquirypurposetype) {
		this.creditinquirypurposetype = creditinquirypurposetype;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public PhoneRes getPhones() {
		if(this.phones!=null) {
			this.phones.setRequestRes(this);
		}
		return phones;
	}

	public void setPhones(PhoneRes phones) {
		this.phones = phones;
	}

	public String getCnsscore() {
		return cnsscore;
	}

	public void setCnsscore(String cnsscore) {
		this.cnsscore = cnsscore;
	}

	public String getMfiInd() {
		return mfiInd;
	}

	public void setMfiInd(String mfiInd) {
		this.mfiInd = mfiInd;
	}

	public String getCreditinquirypurposetypedescription() {
		return creditinquirypurposetypedescription;
	}

	public void setCreditinquirypurposetypedescription(String creditinquirypurposetypedescription) {
		this.creditinquirypurposetypedescription = creditinquirypurposetypedescription;
	}

	public String getMfigroup() {
		return mfigroup;
	}

	public void setMfigroup(String mfigroup) {
		this.mfigroup = mfigroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCreditreporttransectiondatetime() {
		return creditreporttransectiondatetime;
	}

	public void setCreditreporttransectiondatetime(String creditreporttransectiondatetime) {
		this.creditreporttransectiondatetime = creditreporttransectiondatetime;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public AddressRes getAddresses() {
		if(this.addresses!=null) {
			this.addresses.setRequestRes(this);
		}
		return addresses;
	}

	public void setAddresses(AddressRes addresses) {
		this.addresses = addresses;
	}

	public String getCnsind() {
		return cnsind;
	}

	public void setCnsind(String cnsind) {
		this.cnsind = cnsind;
	}

	public String getLosapplicationid() {
		return losapplicationid;
	}

	public void setLosapplicationid(String losapplicationid) {
		this.losapplicationid = losapplicationid;
	}

	public String getKendra() {
		return kendra;
	}

	public void setKendra(String kendra) {
		this.kendra = kendra;
	}

	public INDVReportRes getIndvReportRes() {
		return indvReportRes;
	}

	public void setIndvReportRes(INDVReportRes indvReportRes) {
		this.indvReportRes = indvReportRes;
	}

	@Override
	public String toString() {
		return "RequestRes [reqId=" + reqId + ", ageason=" + ageason + ", loanamount=" + loanamount + ", aka=" + aka
				+ ", creditrequesttype=" + creditrequesttype + ", ids=" + ids + ", mfiscore=" + mfiscore
				+ ", creditinquirystage=" + creditinquirystage + ", creditinquirypurposetype="
				+ creditinquirypurposetype + ", email1=" + email1 + ", phones=" + phones + ", cnsscore=" + cnsscore
				+ ", mfiInd=" + mfiInd + ", creditinquirypurposetypedescription=" + creditinquirypurposetypedescription
				+ ", mfigroup=" + mfigroup + ", name=" + name + ", dob=" + dob + ", gender=" + gender
				+ ", creditreporttransectiondatetime=" + creditreporttransectiondatetime + ", branch=" + branch
				+ ", memberid=" + memberid + ", addresses=" + addresses + ", cnsind=" + cnsind + ", losapplicationid="
				+ losapplicationid + ", kendra=" + kendra + "]";
	}

}
