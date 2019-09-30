
package com.qualtech.hdfc.api.request;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.multibureau.api.request.RelationDetails;


@Entity
@Table(name="IB_HDFC_PERSONALDETAILS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PersonalDetails implements Serializable{

	
	@OneToOne
	@JoinColumn(name="request_unique_id",nullable=false)
	@JsonIgnore
	private CustomerDetails customeDtl;

	
	@Id
	@GeneratedValue(generator = "IBS_HDFC_PER_DETAIL_SQC")
	@SequenceGenerator(name = "IBS_HDFC_PER_DETAIL_SQC", sequenceName = "IBS_HDFC_PER_DETAIL_SQC")
	@JsonIgnore
	private int eid;
	
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public CustomerDetails getCustomeDtl() {
		return customeDtl;
	}

	public void setCustomeDtl(CustomerDetails customeDtl) {
		this.customeDtl = customeDtl;
	}










	@Column(name="PERSONAL_title")
	//@JsonProperty("title")
    private String title;
    @Column(name="PERSONAL_gender")
    //@JsonProperty("gender")
    private String gender;
    @Column(name="PERSONAL_firstName")
    //@JsonProperty("firstName")
    private String firstName;
    //@JsonProperty("lastName")
    @Column(name="PERSONAL_lastName")
    private String lastName;
    //@JsonProperty("dob")
    @Column(name="PERSONAL_DOB")
    private String dob;
    //@JsonProperty("mobile")
    @Column(name="PERSONAL_mobile")
    private String mobile;
    //@JsonProperty("email")
    @Column(name="PERSONAL_email")
    private String email;
    //@JsonProperty("address")
    @Embedded
    private Address address;
    //@JsonProperty("nomineeDetails")
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "personalDetails" ,cascade=CascadeType.ALL)
    private List<NomineeDetails> nomineeDetails;
 
    //@JsonProperty("title")
    public String getTitle() {
        return title;
    }

    //@JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    //@JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    //@JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    //@JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    //@JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //@JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    //@JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //@JsonProperty("dob")
    public String getDob() {
        return dob;
    }

    //@JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

    //@JsonProperty("mobile")
    public String getMobile() {
        return mobile;
    }

    //@JsonProperty("mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    //@JsonProperty("email")
    public String getEmail() {
        return email;
    }

    //@JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    //@JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    //@JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    //@JsonProperty("nomineeDetails")
    public List<NomineeDetails> getNomineeDetails() {
    	if(nomineeDetails!=null){
			for(NomineeDetails re:nomineeDetails) {
				re.setPersonalDetails(this);
			}
		}
        return nomineeDetails;
    }

    //@JsonProperty("nomineeDetails")
    public void setNomineeDetails(List<NomineeDetails> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

 

}
