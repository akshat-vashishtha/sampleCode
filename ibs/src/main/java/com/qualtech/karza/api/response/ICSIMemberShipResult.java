package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_ICSIMEMBERSHIP_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICSIMemberShipResult implements Serializable{

	private static final long serialVersionUID = -577430278817837608L;
	private String uniqueid;
	private String correlationid;
	private String city;
	private String designation;
	private String benevolentMember;
	private String phone;
	private String membershipNumber;
	private String mob;
	private String cpNumber;
	private String address;
	private String email;
	private String organization;
	private String sequence_id;
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="DESIGNATION")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name="BENEVOLENT_MEMBER")
	public String getBenevolentMember() {
		return benevolentMember;
	}
	public void setBenevolentMember(String benevolentMember) {
		this.benevolentMember = benevolentMember;
	}
	@Column(name="PHONE")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="MEMBERSHIP_NUMBER")
	public String getMembershipNumber() {
		return membershipNumber;
	}
	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}
	@Column(name="MOB")
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	@Column(name="CP_NUMBER")
	public String getCpNumber() {
		return cpNumber;
	}
	public void setCpNumber(String cpNumber) {
		this.cpNumber = cpNumber;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="ORGANIZATION")
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ICSIMemberShipResult [city=" + city + ", designation=" + designation + ", benevolentMember="
				+ benevolentMember + ", phone=" + phone + ", membershipNumber=" + membershipNumber + ", mob=" + mob
				+ ", cpNumber=" + cpNumber + ", address=" + address + ", email=" + email + ", organization="
				+ organization + "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	@JsonIgnore
	@Column(name="SEQUENCE_ID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen", sequenceName="IB_K_ICSIMEMBER_FOR_RES_SQC",allocationSize=1)
	public String getSequence_id() {
		return sequence_id;
	}
	public void setSequence_id(String sequence_id) {
		this.sequence_id = sequence_id;
	}
	
	
	
	
}
