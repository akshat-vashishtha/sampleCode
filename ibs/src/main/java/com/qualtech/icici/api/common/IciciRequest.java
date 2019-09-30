package com.qualtech.icici.api.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.icici.api.request.CustomerOnBoardRequest;
import com.qualtech.icici.api.request.PolicyStatusRequest;
import com.qualtech.icici.api.request.PremCalc;
import com.qualtech.icici.api.request.WelcomeKitRequest;
@JsonIgnoreProperties(ignoreUnknown=true)
public class IciciRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private WelcomeKitRequest welcomeKit;
	private CustomerOnBoardRequest customerOnBoard;
	private PremCalc premiumCalculation;
	private PolicyStatusRequest policyStatus;

    public CustomerOnBoardRequest getCustomerOnBoard ()
    {
        return customerOnBoard;
    }

    public void setCustomerOnBoard (CustomerOnBoardRequest customerOnBoard)
    {
        this.customerOnBoard = customerOnBoard;
    }

	public PolicyStatusRequest getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(PolicyStatusRequest policyStatus) {
		this.policyStatus = policyStatus;
	}

	public WelcomeKitRequest getWelcomeKit() {
		return welcomeKit;
	}

	public PremCalc getPremiumCalculation() {
		return premiumCalculation;
	}

	public void setPremiumCalculation(PremCalc premiumCalculation) {
		this.premiumCalculation = premiumCalculation;
	}

	public void setWelcomeKit(WelcomeKitRequest welcomeKit) {
		this.welcomeKit = welcomeKit;
	}

	@Override
	public String toString() {
		return "IciciRequest [welcomeKit=" + welcomeKit + ", customerOnBoard=" + customerOnBoard
				+ ", premiumCalculation=" + premiumCalculation + ", policyStatus=" + policyStatus + "]";
	}

	
	
	
}

