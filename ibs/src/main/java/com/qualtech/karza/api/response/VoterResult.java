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
@Table(name="IB_K_VOTER_RES")
public class VoterResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	
	@Id
	@JsonIgnore
	@Column(name="UNIQUEID")
	private Long uniqueid;
	@Column(name="PS_LAT_LONG")
	private String ps_lat_long;
	@Column(name="RLN_NAME_V1")
	private String  rln_name_v1;
	@Column(name="RLN_NAME_V2")
	private String  rln_name_v2;
	@Column(name="RLN_NAME_V3")
	private String  rln_name_v3;
	@Column(name="PART_NO")
	private String part_no;
	@Column(name="RLN_TYPE")
	private String  rln_type;
	@Column(name="SECTION_NO")
	private String section_no;
	@Column(name="ID")
	private String  id;
	@Column(name="NAME_V1")
	private String name_v1;
	@Column(name="RLN_NAME")
	private String rln_name;
	@Column(name="DISTRICT")
	private String district;
	@Column(name="LAST_UPDATE")
	private String last_update;
	@Column(name="STATE")
	private String  state;
	@Column(name="AC_NO")
	private String ac_no;
	@Column(name="HOUSE_NO")
	private String house_no;
	@Column(name="PS_NAME")
	private String ps_name;
	@Column(name="PC_NAME")
	private String pc_name;
	@Column(name="SLNO_INPART")
	private String slno_inpart;
	@Column(name="NAME")
	private String name;
	@Column(name="PART_NAME")
	private String part_name;
	@Column(name="ST_CODE")
	private String st_code;
	@Column(name="GENDER")
	private String gender;
	@Column(name="AGE")
	private String age;
	@Column(name="AC_NAME")
	private String  ac_name;
	@Column(name="EPIC_NO")
	private String epic_no;
	@Column(name="NAME_V3")
	private String name_v3;
	@Column(name="NAME_V2")
	private String name_v2;
	@Column(name="DOB")
	private String dob ;
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	private String corelationid;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public Long getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	public String getPs_lat_long() {
		return ps_lat_long;
	}

	public void setPs_lat_long(String ps_lat_long) {
		this.ps_lat_long = ps_lat_long;
	}

	public String getRln_name_v1() {
		return rln_name_v1;
	}

	public void setRln_name_v1(String rln_name_v1) {
		this.rln_name_v1 = rln_name_v1;
	}

	public String getRln_name_v2() {
		return rln_name_v2;
	}

	public void setRln_name_v2(String rln_name_v2) {
		this.rln_name_v2 = rln_name_v2;
	}

	public String getRln_name_v3() {
		return rln_name_v3;
	}

	public void setRln_name_v3(String rln_name_v3) {
		this.rln_name_v3 = rln_name_v3;
	}

	public String getPart_no() {
		return part_no;
	}

	public void setPart_no(String part_no) {
		this.part_no = part_no;
	}

	public String getRln_type() {
		return rln_type;
	}

	public void setRln_type(String rln_type) {
		this.rln_type = rln_type;
	}

	public String getSection_no() {
		return section_no;
	}

	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName_v1() {
		return name_v1;
	}

	public void setName_v1(String name_v1) {
		this.name_v1 = name_v1;
	}

	public String getRln_name() {
		return rln_name;
	}

	public void setRln_name(String rln_name) {
		this.rln_name = rln_name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAc_no() {
		return ac_no;
	}

	public void setAc_no(String ac_no) {
		this.ac_no = ac_no;
	}

	public String getHouse_no() {
		return house_no;
	}

	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}

	public String getPs_name() {
		return ps_name;
	}

	public void setPs_name(String ps_name) {
		this.ps_name = ps_name;
	}

	public String getPc_name() {
		return pc_name;
	}

	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}

	public String getSlno_inpart() {
		return slno_inpart;
	}

	public void setSlno_inpart(String slno_inpart) {
		this.slno_inpart = slno_inpart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public String getSt_code() {
		return st_code;
	}

	public void setSt_code(String st_code) {
		this.st_code = st_code;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAc_name() {
		return ac_name;
	}

	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}

	public String getEpic_no() {
		return epic_no;
	}

	public void setEpic_no(String epic_no) {
		this.epic_no = epic_no;
	}

	public String getName_v3() {
		return name_v3;
	}

	public void setName_v3(String name_v3) {
		this.name_v3 = name_v3;
	}

	public String getName_v2() {
		return name_v2;
	}

	public void setName_v2(String name_v2) {
		this.name_v2 = name_v2;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "VoterResult [ps_lat_long=" + ps_lat_long + ", rln_name_v1=" + rln_name_v1 + ", rln_name_v2="
				+ rln_name_v2 + ", rln_name_v3=" + rln_name_v3 + ", part_no=" + part_no + ", rln_type=" + rln_type
				+ ", section_no=" + section_no + ", id=" + id + ", name_v1=" + name_v1 + ", rln_name=" + rln_name
				+ ", district=" + district + ", last_update=" + last_update + ", state=" + state + ", ac_no=" + ac_no
				+ ", house_no=" + house_no + ", ps_name=" + ps_name + ", pc_name=" + pc_name + ", slno_inpart="
				+ slno_inpart + ", name=" + name + ", part_name=" + part_name + ", st_code=" + st_code + ", gender="
				+ gender + ", age=" + age + ", ac_name=" + ac_name + ", epic_no=" + epic_no + ", name_v3=" + name_v3
				+ ", name_v2=" + name_v2 + ", dob=" + dob + "]";
	}

	
	
}

