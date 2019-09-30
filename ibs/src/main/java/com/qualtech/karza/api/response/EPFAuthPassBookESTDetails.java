package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EPF_AUTH_ESTDTL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthPassBookESTDetails implements Serializable {

	private static final long serialVersionUID = -7859734780477391564L;
	
	
	@ManyToOne
	@JoinColumn(name="UNIQUEID",nullable=false)
	@JsonIgnore
	private EPFAuthPassBookResult ePFAuthPassBookResult;
	
	
	
	
	
	public EPFAuthPassBookResult getePFAuthPassBookResult() {
		return ePFAuthPassBookResult;
	}
	public void setePFAuthPassBookResult(EPFAuthPassBookResult ePFAuthPassBookResult) {
		this.ePFAuthPassBookResult = ePFAuthPassBookResult;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="ePFAuthPassBookESTDetails", cascade=CascadeType.ALL)
	private  List<EPFAuthPassBook> passbook;
	
	
	private String est_name;
	private String doe_epf;
	private String office;
	private String doj_epf;
	private String doe_eps;
	private String member_id;
	
	@Column(name="EST_NAME")
	public String getEst_name() {
		return est_name;
	}
	public void setEst_name(String est_name) {
		this.est_name = est_name;
	}
	@Column(name="DOE_EPF")
	public String getDoe_epf() {
		return doe_epf;
	}
	public void setDoe_epf(String doe_epf) {
		this.doe_epf = doe_epf;
	}
	@Column(name="OFFICE")
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	@Column(name="DOJ_EPF")
	public String getDoj_epf() {
		return doj_epf;
	}
	public void setDoj_epf(String doj_epf) {
		this.doj_epf = doj_epf;
	}
	@Column(name="DOE_EPS")
	public String getDoe_eps() {
		return doe_eps;
	}
	public void setDoe_eps(String doe_eps) {
		this.doe_eps = doe_eps;
	}
	@Column(name="MEMBER_ID")
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Transient
	public List<EPFAuthPassBook> getPassbook() {
		if(passbook!=null) {
			for (EPFAuthPassBook epfAuthPassBook : passbook) {
				epfAuthPassBook.setePFAuthPassBookESTDetails(this);
			}
		}
		
		return passbook;
	}
	public void setPassbook(List<EPFAuthPassBook> passbook) {
		this.passbook = passbook;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EPFAuthPassBookESTDetails [passbook=" + passbook + ", est_name=" + est_name + ", doe_epf=" + doe_epf
				+ ", office=" + office + ", doj_epf=" + doj_epf + ", doe_eps=" + doe_eps + ", member_id=" + member_id
				+ "]";
	}
	
	@Id
	@JsonIgnore
	@Column(name="SEQUENCEID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_EPF_AUTH_PASS_REQ_SQC")
	private long sequenceid;

	public long getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(long sequenceid) {
		this.sequenceid = sequenceid;
	}

	
	

}
