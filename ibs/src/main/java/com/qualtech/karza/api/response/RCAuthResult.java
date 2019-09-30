package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_RCAUTH_RES")
public class RCAuthResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String	    uniqueId;
	@Column
	@JsonIgnore
	private String correlation_Id;
	@Column
	private String rc_eng_no;
	@Column
	private String rc_vh_class_desc;
	@Column
	private String rc_present_address;
	@Column
	private String rc_registered_at;
	@Column
	private String rc_regn_dt;
	@Column
	private String rc_insurance_comp;
	@Column
	private String rc_color;
	@Column
	private String rc_manu_month_yr;
	@Column
	private String rc_sleeper_cap;
	@Column
	private String rc_maker_desc;
	@Column
	private String rc_status_as_on;
	@Column
	private String rc_insurance_upto;
	@Column
	private String rc_cubic_cap;
	@Column
	private String rc_owner_sr;
	@Column
	private String rc_permanent_address;
	@Column
	private String rc_financer;
	@Column
	private String rc_owner_name;
	@Column
	private String rc_f_name;
	@Column
	private String rc_unld_wt;
	@Column
	private String rc_chasi_no;
	@Column
	private String rc_maker_model;
	@Column
	private String rc_gvw;
	@Column
	private String rc_tax_upto;
	@Column
	private String rc_stand_cap;
	@Column
	private String rc_fit_upto;
	@Column
	private String stautsMessage;
	@Column
	private String rc_insurance_policy_no;
	@Column
	private String rc_body_type_desc;
	@Column
	private String rc_wheelbase;
	@Column
	private String rc_norms_desc;
	@Column
	private String rc_regn_no;
	@Column
	private String rc_fuel_desc;
	@Column
	private String rc_no_cyl;
	@Column
	private String rc_seat_cap;
	
	public String getRc_eng_no() {
		return rc_eng_no;
	}
	public void setRc_eng_no(String rc_eng_no) {
		this.rc_eng_no = rc_eng_no;
	}
	public String getRc_vh_class_desc() {
		return rc_vh_class_desc;
	}
	public void setRc_vh_class_desc(String rc_vh_class_desc) {
		this.rc_vh_class_desc = rc_vh_class_desc;
	}
	public String getRc_present_address() {
		return rc_present_address;
	}
	public void setRc_present_address(String rc_present_address) {
		this.rc_present_address = rc_present_address;
	}
	public String getRc_registered_at() {
		return rc_registered_at;
	}
	public void setRc_registered_at(String rc_registered_at) {
		this.rc_registered_at = rc_registered_at;
	}
	public String getRc_regn_dt() {
		return rc_regn_dt;
	}
	public void setRc_regn_dt(String rc_regn_dt) {
		this.rc_regn_dt = rc_regn_dt;
	}
	public String getRc_insurance_comp() {
		return rc_insurance_comp;
	}
	public void setRc_insurance_comp(String rc_insurance_comp) {
		this.rc_insurance_comp = rc_insurance_comp;
	}
	public String getRc_color() {
		return rc_color;
	}
	public void setRc_color(String rc_color) {
		this.rc_color = rc_color;
	}
	public String getRc_manu_month_yr() {
		return rc_manu_month_yr;
	}
	public void setRc_manu_month_yr(String rc_manu_month_yr) {
		this.rc_manu_month_yr = rc_manu_month_yr;
	}
	public String getRc_sleeper_cap() {
		return rc_sleeper_cap;
	}
	public void setRc_sleeper_cap(String rc_sleeper_cap) {
		this.rc_sleeper_cap = rc_sleeper_cap;
	}
	public String getRc_maker_desc() {
		return rc_maker_desc;
	}
	public void setRc_maker_desc(String rc_maker_desc) {
		this.rc_maker_desc = rc_maker_desc;
	}
	public String getRc_status_as_on() {
		return rc_status_as_on;
	}
	public void setRc_status_as_on(String rc_status_as_on) {
		this.rc_status_as_on = rc_status_as_on;
	}
	public String getRc_insurance_upto() {
		return rc_insurance_upto;
	}
	public void setRc_insurance_upto(String rc_insurance_upto) {
		this.rc_insurance_upto = rc_insurance_upto;
	}
	public String getRc_cubic_cap() {
		return rc_cubic_cap;
	}
	public void setRc_cubic_cap(String rc_cubic_cap) {
		this.rc_cubic_cap = rc_cubic_cap;
	}
	public String getRc_owner_sr() {
		return rc_owner_sr;
	}
	public void setRc_owner_sr(String rc_owner_sr) {
		this.rc_owner_sr = rc_owner_sr;
	}
	public String getRc_permanent_address() {
		return rc_permanent_address;
	}
	public void setRc_permanent_address(String rc_permanent_address) {
		this.rc_permanent_address = rc_permanent_address;
	}
	public String getRc_financer() {
		return rc_financer;
	}
	public void setRc_financer(String rc_financer) {
		this.rc_financer = rc_financer;
	}
	public String getRc_owner_name() {
		return rc_owner_name;
	}
	public void setRc_owner_name(String rc_owner_name) {
		this.rc_owner_name = rc_owner_name;
	}
	public String getRc_f_name() {
		return rc_f_name;
	}
	public void setRc_f_name(String rc_f_name) {
		this.rc_f_name = rc_f_name;
	}
	public String getRc_unld_wt() {
		return rc_unld_wt;
	}
	public void setRc_unld_wt(String rc_unld_wt) {
		this.rc_unld_wt = rc_unld_wt;
	}
	public String getRc_chasi_no() {
		return rc_chasi_no;
	}
	public void setRc_chasi_no(String rc_chasi_no) {
		this.rc_chasi_no = rc_chasi_no;
	}
	public String getRc_maker_model() {
		return rc_maker_model;
	}
	public void setRc_maker_model(String rc_maker_model) {
		this.rc_maker_model = rc_maker_model;
	}
	public String getRc_gvw() {
		return rc_gvw;
	}
	public void setRc_gvw(String rc_gvw) {
		this.rc_gvw = rc_gvw;
	}
	public String getRc_tax_upto() {
		return rc_tax_upto;
	}
	public void setRc_tax_upto(String rc_tax_upto) {
		this.rc_tax_upto = rc_tax_upto;
	}
	public String getRc_stand_cap() {
		return rc_stand_cap;
	}
	public void setRc_stand_cap(String rc_stand_cap) {
		this.rc_stand_cap = rc_stand_cap;
	}
	public String getRc_fit_upto() {
		return rc_fit_upto;
	}
	public void setRc_fit_upto(String rc_fit_upto) {
		this.rc_fit_upto = rc_fit_upto;
	}
	public String getStautsMessage() {
		return stautsMessage;
	}
	public void setStautsMessage(String stautsMessage) {
		this.stautsMessage = stautsMessage;
	}
	public String getRc_insurance_policy_no() {
		return rc_insurance_policy_no;
	}
	public void setRc_insurance_policy_no(String rc_insurance_policy_no) {
		this.rc_insurance_policy_no = rc_insurance_policy_no;
	}
	public String getRc_body_type_desc() {
		return rc_body_type_desc;
	}
	public void setRc_body_type_desc(String rc_body_type_desc) {
		this.rc_body_type_desc = rc_body_type_desc;
	}
	public String getRc_wheelbase() {
		return rc_wheelbase;
	}
	public void setRc_wheelbase(String rc_wheelbase) {
		this.rc_wheelbase = rc_wheelbase;
	}
	public String getRc_norms_desc() {
		return rc_norms_desc;
	}
	public void setRc_norms_desc(String rc_norms_desc) {
		this.rc_norms_desc = rc_norms_desc;
	}
	public String getRc_regn_no() {
		return rc_regn_no;
	}
	public void setRc_regn_no(String rc_regn_no) {
		this.rc_regn_no = rc_regn_no;
	}
	public String getRc_fuel_desc() {
		return rc_fuel_desc;
	}
	public void setRc_fuel_desc(String rc_fuel_desc) {
		this.rc_fuel_desc = rc_fuel_desc;
	}
	public String getRc_no_cyl() {
		return rc_no_cyl;
	}
	public void setRc_no_cyl(String rc_no_cyl) {
		this.rc_no_cyl = rc_no_cyl;
	}
	public String getRc_seat_cap() {
		return rc_seat_cap;
	}
	public void setRc_seat_cap(String rc_seat_cap) {
		this.rc_seat_cap = rc_seat_cap;
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
		return "RCAuthResult [rc_eng_no=" + rc_eng_no + ", rc_vh_class_desc=" + rc_vh_class_desc
				+ ", rc_present_address=" + rc_present_address + ", rc_registered_at=" + rc_registered_at
				+ ", rc_regn_dt=" + rc_regn_dt + ", rc_insurance_comp=" + rc_insurance_comp + ", rc_color=" + rc_color
				+ ", rc_manu_month_yr=" + rc_manu_month_yr + ", rc_sleeper_cap=" + rc_sleeper_cap + ", rc_maker_desc="
				+ rc_maker_desc + ", rc_status_as_on=" + rc_status_as_on + ", rc_insurance_upto=" + rc_insurance_upto
				+ ", rc_cubic_cap=" + rc_cubic_cap + ", rc_owner_sr=" + rc_owner_sr + ", rc_permanent_address="
				+ rc_permanent_address + ", rc_financer=" + rc_financer + ", rc_owner_name=" + rc_owner_name
				+ ", rc_f_name=" + rc_f_name + ", rc_unld_wt=" + rc_unld_wt + ", rc_chasi_no=" + rc_chasi_no
				+ ", rc_maker_model=" + rc_maker_model + ", rc_gvw=" + rc_gvw + ", rc_tax_upto=" + rc_tax_upto
				+ ", rc_stand_cap=" + rc_stand_cap + ", rc_fit_upto=" + rc_fit_upto + ", stautsMessage=" + stautsMessage
				+ ", rc_insurance_policy_no=" + rc_insurance_policy_no + ", rc_body_type_desc=" + rc_body_type_desc
				+ ", rc_wheelbase=" + rc_wheelbase + ", rc_norms_desc=" + rc_norms_desc + ", rc_regn_no=" + rc_regn_no
				+ ", rc_fuel_desc=" + rc_fuel_desc + ", rc_no_cyl=" + rc_no_cyl + ", rc_seat_cap=" + rc_seat_cap + "]";
	}
	
	
	

	
}
