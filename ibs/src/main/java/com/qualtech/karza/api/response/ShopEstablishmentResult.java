package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_SHOPESTABLISHMENT_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopEstablishmentResult implements Serializable{
	
	
	private static final long serialVersionUID = -8251784410816419882L;
    @Column
	private String category;
    @Column
	private String status;
    @Column
	private String owner_name;
	@Column
	private String entity_name;
	@Column
	private String registration_date;
	@Column
	private String valid_to;
	@Column
	private String contact;
	@Column
	private String commence_date;
	@Column
	private String total_workers;
	@Column
	private String fathers_name_of_occupier;
	@Column
	private String nature_of_business;
	@Column
	private String address;
	@Column
	private String valid_from;
	@Column
	private String email;
	@Column
	private String website_url;
	@Id
	@JsonIgnore
	private String uniqueid;
	@Column
	@JsonIgnore
	private String correlation_id;
	
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	
	public String getCorrelation_id() {
		return correlation_id;
	}
	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public String getValid_to() {
		return valid_to;
	}
	public void setValid_to(String valid_to) {
		this.valid_to = valid_to;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCommence_date() {
		return commence_date;
	}
	public void setCommence_date(String commence_date) {
		this.commence_date = commence_date;
	}
	public String getTotal_workers() {
		return total_workers;
	}
	public void setTotal_workers(String total_workers) {
		this.total_workers = total_workers;
	}
	public String getFathers_name_of_occupier() {
		return fathers_name_of_occupier;
	}
	public void setFathers_name_of_occupier(String fathers_name_of_occupier) {
		this.fathers_name_of_occupier = fathers_name_of_occupier;
	}
	public String getNature_of_business() {
		return nature_of_business;
	}
	public void setNature_of_business(String nature_of_business) {
		this.nature_of_business = nature_of_business;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getValid_from() {
		return valid_from;
	}
	public void setValid_from(String valid_from) {
		this.valid_from = valid_from;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite_url() {
		return website_url;
	}
	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ShopEstablishmentResult [category=" + category + ", status=" + status + ", owner_name=" + owner_name
				+ ", entity_name=" + entity_name + ", registration_date=" + registration_date + ", valid_to=" + valid_to
				+ ", contact=" + contact + ", commence_date=" + commence_date + ", total_workers=" + total_workers
				+ ", fathers_name_of_occupier=" + fathers_name_of_occupier + ", nature_of_business="
				+ nature_of_business + ", address=" + address + ", valid_from=" + valid_from + ", email=" + email
				+ ", website_url=" + website_url + "]";
	}
	
	

	
	
	
	
}
