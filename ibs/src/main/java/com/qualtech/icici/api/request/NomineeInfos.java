package com.qualtech.icici.api.request;

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
/*@Entity
@Table(name="IB_ICICI_CST_NMNI")*/
public class NomineeInfos implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	/*@Id
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_NMNI_SEQ")
	@Column(name="UNIQUE_ID", unique = true, nullable = false)*/
	private int uniqueID;
	
	
    @JsonIgnore
   /* @OneToOne
    @JoinColumn(name="EID",nullable=false)	*/
    private CustomerOnBoardRequest customerOnBoard;

	/*@OneToOne (fetch=FetchType.LAZY,mappedBy="nomineeInfos", cascade=CascadeType.ALL)*/
    private ApnteDtls apnteDtls;
    
    private String gender;
    private String frstNm;
    private String dob;
    private String lstNm;
    private String relationship;

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public CustomerOnBoardRequest getCustomerOnBoard() {
		return customerOnBoard;
	}

	public void setCustomerOnBoard(CustomerOnBoardRequest customerOnBoard) {
		this.customerOnBoard = customerOnBoard;
	}
	
	public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public ApnteDtls getApnteDtls ()
    {
    	apnteDtls.setNomineeInfos(this);
        return apnteDtls;
    }

    public void setApnteDtls (ApnteDtls apnteDtls)
    {
        this.apnteDtls = apnteDtls;
    }

    public String getFrstNm ()
    {
        return frstNm;
    }

    public void setFrstNm (String frstNm)
    {
        this.frstNm = frstNm;
    }

    public String getDob ()
    {
        return dob;
    }

    public void setDob (String dob)
    {
        this.dob = dob;
    }

    public String getLstNm ()
    {
        return lstNm;
    }

    public void setLstNm (String lstNm)
    {
        this.lstNm = lstNm;
    }

    public String getRelationship ()
    {
        return relationship;
    }

    public void setRelationship (String relationship)
    {
        this.relationship = relationship;
    }

	@Override
	public String toString() {
		return "NomineeInfos [gender=" + gender + ", apnteDtls=" + apnteDtls + ", frstNm=" + frstNm + ", dob=" + dob
				+ ", lstNm=" + lstNm + ", relationship=" + relationship + "]";
	}

}
			
	