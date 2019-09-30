package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EPF_AUTH_EST_PSBK")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthPassBook implements Serializable {

	private static final long serialVersionUID = -7271568366793313902L;
	
	@ManyToOne
	@JoinColumn(name="UNIQUEID",nullable=false)
	@JsonIgnore
	private EPFAuthPassBookESTDetails ePFAuthPassBookESTDetails;
	
	
	public EPFAuthPassBookESTDetails getePFAuthPassBookESTDetails() {
		return ePFAuthPassBookESTDetails;
	}
	public void setePFAuthPassBookESTDetails(EPFAuthPassBookESTDetails ePFAuthPassBookESTDetails) {
		this.ePFAuthPassBookESTDetails = ePFAuthPassBookESTDetails;
	}
	//private String uniqueid;
	
	private String cr_pen_bal;
	private String approved_on;
	private String db_cr_flag;
	private String tr_approved;
	private String tr_date_my;
	private String r_order;
	private String cr_ee_share;
	private String cr_er_share;
	private String particular;
	private String trrno;
	private String table_name;
	private String month_year;
	

	@Column(name="CR_PEN_BAL")
	public String getCr_pen_bal() {
		return cr_pen_bal;
	}
	public void setCr_pen_bal(String cr_pen_bal) {
		this.cr_pen_bal = cr_pen_bal;
	}
	@Column(name="APPROVED_ON")
	public String getApproved_on() {
		return approved_on;
	}
	public void setApproved_on(String approved_on) {
		this.approved_on = approved_on;
	}
	@Column(name="DB_CR_FLAG")
	public String getDb_cr_flag() {
		return db_cr_flag;
	}
	public void setDb_cr_flag(String db_cr_flag) {
		this.db_cr_flag = db_cr_flag;
	}
	@Column(name="TR_APPROVED")
	public String getTr_approved() {
		return tr_approved;
	}
	public void setTr_approved(String tr_approved) {
		this.tr_approved = tr_approved;
	}
	@Column(name="TR_DATE_MY")
	public String getTr_date_my() {
		return tr_date_my;
	}
	public void setTr_date_my(String tr_date_my) {
		this.tr_date_my = tr_date_my;
	}
	@Column(name="R_ORDER")
	public String getR_order() {
		return r_order;
	}
	public void setR_order(String r_order) {
		this.r_order = r_order;
	}
	@Column(name="CR_EE_SHARE")
	public String getCr_ee_share() {
		return cr_ee_share;
	}
	public void setCr_ee_share(String cr_ee_share) {
		this.cr_ee_share = cr_ee_share;
	}
	@Column(name="CR_ER_SHARE")
	public String getCr_er_share() {
		return cr_er_share;
	}
	public void setCr_er_share(String cr_er_share) {
		this.cr_er_share = cr_er_share;
	}
	@Column(name="PARTICULAR")
	public String getParticular() {
		return particular;
	}
	public void setParticular(String particular) {
		this.particular = particular;
	}
	@Column(name="TRRNO")
	public String getTrrno() {
		return trrno;
	}
	public void setTrrno(String trrno) {
		this.trrno = trrno;
	}
	@Column(name="TABLE_NAME")
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	@Column(name="MONTH_YEAR")
	public String getMonth_year() {
		return month_year;
	}
	public void setMonth_year(String month_year) {
		this.month_year = month_year;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EPFAuthPassBook [cr_pen_bal=" + cr_pen_bal + ", approved_on=" + approved_on + ", db_cr_flag="
				+ db_cr_flag + ", tr_approved=" + tr_approved + ", tr_date_my=" + tr_date_my + ", r_order=" + r_order
				+ ", cr_ee_share=" + cr_ee_share + ", cr_er_share=" + cr_er_share + ", particular=" + particular
				+ ", trrno=" + trrno + ", table_name=" + table_name + ", month_year=" + month_year + "]";
	}
/*	@JsonIgnore
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}*/
	@Id
	@JsonIgnore
	@Column(name="SEQUENCEID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_PASSBOOKESTDETAIL_SQC")
	private long sequenceid;


	public long getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(long sequenceid) {
		this.sequenceid = sequenceid;
	}
	
	
	
}
