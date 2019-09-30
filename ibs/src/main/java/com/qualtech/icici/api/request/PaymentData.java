package com.qualtech.icici.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="IB_ICICI_CST_PYDT")*/
public class PaymentData implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
/*	@Id
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_PYDT_SEQ")
	@Column(name="UNIQUE_ID", unique = true, nullable = false)*/
	private int uniqueID;

	@JsonIgnore
    /*@OneToOne
    @JoinColumn(name="EID",nullable=false)*/	
    private CustomerOnBoardRequest customerOnBoard;

    private String payAmount;
    private String payFinalPremiumAmnt;
    
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

	public String getPayAmount ()
    {
        return payAmount;
    }

    public void setPayAmount (String payAmount)
    {
        this.payAmount = payAmount;
    }

    public String getPayFinalPremiumAmnt ()
    {
        return payFinalPremiumAmnt;
    }

    public void setPayFinalPremiumAmnt (String payFinalPremiumAmnt)
    {
        this.payFinalPremiumAmnt = payFinalPremiumAmnt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [payAmount = "+payAmount+", payFinalPremiumAmnt = "+payFinalPremiumAmnt+"]";
    }
}