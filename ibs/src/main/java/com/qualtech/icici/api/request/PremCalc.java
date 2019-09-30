package com.qualtech.icici.api.request;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_IC_PRECALC_REQ_PAYLOAD")
public class PremCalc implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 4458315654728418974L;

	@JsonIgnore
	@Id
	@Column(name="EID", unique = true, nullable = false)
	private int eid;

	@JsonIgnore
	@OneToOne (fetch=FetchType.LAZY,mappedBy="premCalcOne", cascade=CascadeType.ALL)
	private Product product;

	@Transient
	private ProductDetails productDetails;
	private String dateOfBirth;

	private String firstName;

	private String gender;

	private String lastName;

	private String maritalStatus;
	
	public Product getProduct() {
		product.setPremCalcOne(this);
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public ProductDetails getProductDetails() {
		return this.productDetails;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	@Override
	public String toString() {
		return "PremCalc [dateOfBirth=" + dateOfBirth + ", firstName=" + firstName + ", gender=" + gender
				+ ", lastName=" + lastName + ", maritalStatus=" + maritalStatus + ", productDetails=" + productDetails
				+ "]";
	}

}
