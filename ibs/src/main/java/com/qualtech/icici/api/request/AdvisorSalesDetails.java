package com.qualtech.icici.api.request;

import java.io.Serializable;

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
@Table(name="IB_ICICI_CST_ADSD") */
public class AdvisorSalesDetails implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	
	@JsonIgnore
	/*@Id
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_ADSD_SEQ")*/
	private int uniqueID;
	

    @JsonIgnore
	/*@OneToOne
    @JoinColumn(name="EID",nullable=false)	*/
    private CustomerOnBoardRequest customerOnBoard;
	
    private String needRiskProfile;
    private String lanNo;
    private String bankBrnch;
    private String channelType;
    private String bankName;
    private String csrLimCode;
    private String source;
    private String spCode;
    private String fscCode;
    private String cusBankAccNo;
    private String subChannel;
    private String cafosCode;
    private String oppId;
    private String selectedTab;

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

	public String getNeedRiskProfile ()
    {
        return needRiskProfile;
    }

    public void setNeedRiskProfile (String needRiskProfile)
    {
        this.needRiskProfile = needRiskProfile;
    }

    public String getLanNo ()
    {
        return lanNo;
    }

    public void setLanNo (String lanNo)
    {
        this.lanNo = lanNo;
    }

    public String getBankBrnch ()
    {
        return bankBrnch;
    }

    public void setBankBrnch (String bankBrnch)
    {
        this.bankBrnch = bankBrnch;
    }

    public String getChannelType ()
    {
        return channelType;
    }

    public void setChannelType (String channelType)
    {
        this.channelType = channelType;
    }

    public String getBankName ()
    {
        return bankName;
    }

    public void setBankName (String bankName)
    {
        this.bankName = bankName;
    }

    public String getCsrLimCode ()
    {
        return csrLimCode;
    }

    public void setCsrLimCode (String csrLimCode)
    {
        this.csrLimCode = csrLimCode;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getSpCode ()
    {
        return spCode;
    }

    public void setSpCode (String spCode)
    {
        this.spCode = spCode;
    }

    public String getFscCode ()
    {
        return fscCode;
    }

    public void setFscCode (String fscCode)
    {
        this.fscCode = fscCode;
    }

    public String getCusBankAccNo ()
    {
        return cusBankAccNo;
    }

    public void setCusBankAccNo (String cusBankAccNo)
    {
        this.cusBankAccNo = cusBankAccNo;
    }

    public String getSubChannel ()
    {
        return subChannel;
    }

    public void setSubChannel (String subChannel)
    {
        this.subChannel = subChannel;
    }

    public String getCafosCode ()
    {
        return cafosCode;
    }

    public void setCafosCode (String cafosCode)
    {
        this.cafosCode = cafosCode;
    }

    public String getOppId ()
    {
        return oppId;
    }

    public void setOppId (String oppId)
    {
        this.oppId = oppId;
    }

    public String getSelectedTab ()
    {
        return selectedTab;
    }

    public void setSelectedTab (String selectedTab)
    {
        this.selectedTab = selectedTab;
    }

	
	@Override
	public String toString() {
		return "AdvisorSalesDetails [needRiskProfile=" + needRiskProfile + ", lanNo=" + lanNo + ", bankBrnch="
				+ bankBrnch + ", channelType=" + channelType + ", bankName=" + bankName + ", csrLimCode=" + csrLimCode
				+ ", source=" + source + ", spCode=" + spCode + ", fscCode=" + fscCode + ", cusBankAccNo="
				+ cusBankAccNo + ", subChannel=" + subChannel + ", cafosCode=" + cafosCode + ", oppId=" + oppId
				+ ", selectedTab=" + selectedTab + "]";
	}
}
