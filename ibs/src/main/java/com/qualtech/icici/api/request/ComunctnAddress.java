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
@Table(name="IB_ICICI_CST_CMADD")*/
public class ComunctnAddress implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	/*@Id
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_CMADD_SEQ")
	@Column(name="PID", unique = true, nullable = false)*/
	private int pid;
	
    private String pincode;
    private String country;
    private String city;
    private String state;
    private String landmark;
    private String line3;
    private String line2;
    private String line1;
    
    @JsonIgnore
    /*@OneToOne
    @JoinColumn(name="UNIQUE_ID",nullable=false)	*/
    private ProposerInfos proposerInfos;

    public String getPincode ()
    {
        return pincode;
    }

    public void setPincode (String pincode)
    {
        this.pincode = pincode;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getLandmark ()
    {
        return landmark;
    }

    public void setLandmark (String landmark)
    {
        this.landmark = landmark;
    }

    public String getLine3 ()
    {
        return line3;
    }

    public void setLine3 (String line3)
    {
        this.line3 = line3;
    }

    public String getLine2 ()
    {
        return line2;
    }

    public void setLine2 (String line2)
    {
        this.line2 = line2;
    }

    public String getLine1 ()
    {
        return line1;
    }

    public void setLine1 (String line1)
    {
        this.line1 = line1;
    }

	public ProposerInfos getProposerInfos() {
		return proposerInfos;
	}

	public void setProposerInfos(ProposerInfos proposerInfos) {
		this.proposerInfos = proposerInfos;
	}

	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	
	@Override
	public String toString() {
		return "ComunctnAddress [pincode=" + pincode + ", country=" + country + ", city=" + city + ", state=" + state
				+ ", landmark=" + landmark + ", line3=" + line3 + ", line2=" + line2 + ", line1=" + line1 + "]";
	}

    
}		
