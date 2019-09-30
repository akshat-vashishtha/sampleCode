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
@Table(name="IB_ICICI_CST_PRIN") */
public class ProposerInfos implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	/*@Id
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_PRIN_SEQ")
	@Column(name="UNIQUE_ID", unique = true, nullable = false)*/
	private int uniqueID;
	
	/*@OneToOne (fetch=FetchType.LAZY,mappedBy="proposerInfos", cascade=CascadeType.ALL)*/
    private PrmntAddress prmntAddress;
	
	/*@OneToOne (fetch=FetchType.LAZY,mappedBy="proposerInfos", cascade=CascadeType.ALL)*/
	private ComunctnAddress comunctnAddress;

	@JsonIgnore
	/*@OneToOne
    @JoinColumn(name="EID",nullable=false)	*/
	private CustomerOnBoardRequest customerOnBoard;	
   
	private String mobNo;
    private String gender;
    private String frstNm;
    private String dob;
    private String panNo;
    private String lan;
    private String loanStatus;
    private String lstNm;
    private String dateOfCommencementOfLoan;
    private String email;
    
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

	public String getMobNo ()
    {
        return mobNo;
    }

    public void setMobNo (String mobNo)
    {
        this.mobNo = mobNo;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
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

    public PrmntAddress getPrmntAddress ()
    {
    	prmntAddress.setProposerInfos(this);
        return prmntAddress;
    }

    public void setPrmntAddress (PrmntAddress prmntAddress)
    {
        this.prmntAddress = prmntAddress;
    }

    public String getPanNo ()
    {
        return panNo;
    }

    public void setPanNo (String panNo)
    {
        this.panNo = panNo;
    }

    public String getLan ()
    {
        return lan;
    }

    public void setLan (String lan)
    {
        this.lan = lan;
    }

    public String getLoanStatus ()
    {
        return loanStatus;
    }

    public void setLoanStatus (String loanStatus)
    {
        this.loanStatus = loanStatus;
    }

    public String getLstNm ()
    {
        return lstNm;
    }

    public void setLstNm (String lstNm)
    {
        this.lstNm = lstNm;
    }

    public String getDateOfCommencementOfLoan ()
    {
        return dateOfCommencementOfLoan;
    }

    public void setDateOfCommencementOfLoan (String dateOfCommencementOfLoan)
    {
        this.dateOfCommencementOfLoan = dateOfCommencementOfLoan;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public ComunctnAddress getComunctnAddress ()
    {
    	comunctnAddress.setProposerInfos(this);
        return comunctnAddress;
    }

    public void setComunctnAddress (ComunctnAddress comunctnAddress)
    {
        this.comunctnAddress = comunctnAddress;
    }
    

	@Override
	public String toString() {
		return "ProposerInfos [mobNo=" + mobNo + ", gender=" + gender + ", frstNm=" + frstNm + ", dob=" + dob
				+ ", prmntAddress=" + prmntAddress + ", panNo=" + panNo + ", lan=" + lan + ", loanStatus=" + loanStatus
				+ ", lstNm=" + lstNm + ", dateOfCommencementOfLoan=" + dateOfCommencementOfLoan + ", email=" + email
				+ ", comunctnAddress=" + comunctnAddress + "]";
	}

   
}			
				
					
			
	