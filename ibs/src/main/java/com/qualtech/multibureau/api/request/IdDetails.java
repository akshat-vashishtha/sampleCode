//package com.qualtech.multibureau.api.request;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import org.codehaus.jackson.annotate.JsonIgnore;
//
//@Entity
//@Table(name="IB_BUREAU_ID_REQ")
//public class IdDetails implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3939460585870962208L;
//	@Id
//	@GeneratedValue(generator = "my_gen")
//	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ID_SQC", allocationSize = 1)
//	private int id;
//	
//	private String pan_id;
//	private String pan_issue_date;
//	private String pan_expiraton_date;
//	
//	@OneToOne
//	@JoinColumn(name="uniqueid", nullable=false)
//	private BureauPayload bureauPayload;
//	
//	private String passport_id;
//	
//	@Column(name="PSS_ISS_DATE")
//	private String passport_issue_date;
//	
//	@Column(name="PSS_EXP_DATE")
//	private String passport_expiration_date;
//	
//	private String voter_id;
//	
//	@Column(name="VOTER_ID_ISS")
//	private String voter_id_issue_date;
//	
//	@Column(name="VOTER_ID_EXP")
//	private String voter_id_expiration_date;
//	
//	@Column(name="DL_NO")
//	private String driving_license_no;
//
//	@Column(name="DL_ISS_DATE")
//	private String driving_license_issue_date;
//	@Column(name="DL_EXP_DATE")
//	private String driving_license_expiration_date;
//	
//	private String uid_no;
//	
//	@Column(name="UNI_ID_ISS")
//	private String universal_id_issue_data;
//	
//	@Column(name="UNI_ID_EXP")
//	private String universal_id_expiration_date;
//	
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getPan_id() {
//		return pan_id;
//	}
//	public void setPan_id(String pan_id) {
//		this.pan_id = pan_id;
//	}
//	public String getPan_issue_date() {
//		return pan_issue_date;
//	}
//	public void setPan_issue_date(String pan_issue_date) {
//		this.pan_issue_date = pan_issue_date;
//	}
//	
//	public String getPan_expiraton_date() {
//		return pan_expiraton_date;
//	}
//	public void setPan_expiraton_date(String pan_expiraton_date) {
//		this.pan_expiraton_date = pan_expiraton_date;
//	}
//	public String getPassport_id() {
//		return passport_id;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setPassport_id(String passport_id) {
//		this.passport_id = passport_id;
//	}
//	public String getPassport_issue_date() {
//		return passport_issue_date;
//	}
//	public void setPassport_issue_date(String passport_issue_date) {
//		this.passport_issue_date = passport_issue_date;
//	}
//	public String getPassport_expiration_date() {
//		return passport_expiration_date;
//	}
//	public void setPassport_expiration_date(String passport_expiration_date) {
//		this.passport_expiration_date = passport_expiration_date;
//	}
//	public String getVoter_id() {
//		return voter_id;
//	}
//	public void setVoter_id(String voter_id) {
//		this.voter_id = voter_id;
//	}
//	public String getVoter_id_issue_date() {
//		return voter_id_issue_date;
//	}
//	public void setVoter_id_issue_date(String voter_id_issue_date) {
//		this.voter_id_issue_date = voter_id_issue_date;
//	}
//	public String getVoter_id_expiration_date() {
//		return voter_id_expiration_date;
//	}
//	public void setVoter_id_expiration_date(String voter_id_expiration_date) {
//		this.voter_id_expiration_date = voter_id_expiration_date;
//	}
//	public String getDriving_license_no() {
//		return driving_license_no;
//	}
//	public void setDriving_license_no(String driving_license_no) {
//		this.driving_license_no = driving_license_no;
//	}
//	public String getDriving_license_issue_date() {
//		return driving_license_issue_date;
//	}
//	public void setDriving_license_issue_date(String driving_license_issue_date) {
//		this.driving_license_issue_date = driving_license_issue_date;
//	}
//	public String getDriving_license_expiration_date() {
//		return driving_license_expiration_date;
//	}
//	public void setDriving_license_expiration_date(String driving_license_expiration_date) {
//		this.driving_license_expiration_date = driving_license_expiration_date;
//	}
//	public String getUid_no() {
//		return uid_no;
//	}
//	public void setUid_no(String uid_no) {
//		this.uid_no = uid_no;
//	}
//	public String getUniversal_id_issue_data() {
//		return universal_id_issue_data;
//	}
//	public void setUniversal_id_issue_data(String universal_id_issue_data) {
//		this.universal_id_issue_data = universal_id_issue_data;
//	}
//	public String getUniversal_id_expiration_date() {
//		return universal_id_expiration_date;
//	}
//	public void setUniversal_id_expiration_date(String universal_id_expiration_date) {
//		this.universal_id_expiration_date = universal_id_expiration_date;
//	}
//	
//	public BureauPayload getBureauPayload() {
//		return bureauPayload;
//	}
//	public void setBureauPayload(BureauPayload bureauPayload) {
//		this.bureauPayload = bureauPayload;
//	}
//	@Override
//	public String toString() {
//		return "IdDetails [id=" + id + ", pan_id=" + pan_id + ", pan_issue_date=" + pan_issue_date
//				+ ", pan_expiraton_date=" + pan_expiraton_date + ", bureauPayload=" + bureauPayload + ", passport_id="
//				+ passport_id + ", passport_issue_date=" + passport_issue_date + ", passport_expiration_date="
//				+ passport_expiration_date + ", voter_id=" + voter_id + ", voter_id_issue_date=" + voter_id_issue_date
//				+ ", voter_id_expiration_date=" + voter_id_expiration_date + ", driving_license_no="
//				+ driving_license_no + ", driving_license_issue_date=" + driving_license_issue_date
//				+ ", driving_license_expiration_date=" + driving_license_expiration_date + ", uid_no=" + uid_no
//				+ ", universal_id_issue_data=" + universal_id_issue_data + ", universal_id_expiration_date="
//				+ universal_id_expiration_date + "]";
//	}
//	
//	
//}
