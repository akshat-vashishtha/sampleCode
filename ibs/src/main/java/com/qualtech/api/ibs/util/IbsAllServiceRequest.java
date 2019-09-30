package com.qualtech.api.ibs.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.icici.api.common.IciciRequest;
@JsonIgnoreProperties(ignoreUnknown=true)
public class IbsAllServiceRequest implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3101577061357024948L;
	private CommonDataInRequest demographicInfo;
	private CibilIbsRequest cibil;
	private EquifaxEVDRIbsRequest equifaxEVDR;
	private KickoffSingleACtionIbsRequest kickOFFSingleAction;
	private KickoffAUTHIbsRequest kickOFFAuth;
	private KickoffGenQueIbsRequest kickOFFGenQue;
	private KarzaService karza;
	private CreditVidhyaIbsRequest creditVidhya;
	private KotakIbsRequest kotak;
	private FibitBankingRequest banking;
	private MultiBureauRequest multiBureau;
	private HdfcRequest hdfcRequest;
	private IciciRequest iciciRequest;
	
	
	public HdfcRequest getHdfcRequest() {
		return hdfcRequest;
	}
	public void setHdfcRequest(HdfcRequest hdfcRequest) {
		this.hdfcRequest = hdfcRequest;
	}
	public MultiBureauRequest getMultiBureau() {
		return multiBureau;
	}
	public void setMultiBureau(MultiBureauRequest multiBureau) {
		this.multiBureau = multiBureau;
	}
	public FibitBankingRequest getBanking() {
		return banking;
	}
	public void setBanking(FibitBankingRequest banking) {
		this.banking = banking;
	}
	public KotakIbsRequest getKotak() {
		return kotak;
	}
	public void setKotak(KotakIbsRequest kotak) {
		this.kotak = kotak;
	}
	public CreditVidhyaIbsRequest getCreditVidhya() {
		return creditVidhya;
	}
	public void setCreditVidhya(CreditVidhyaIbsRequest creditVidhya) {
		this.creditVidhya = creditVidhya;
	}
	public KarzaService getKarza() {
		return karza;
	}
	public void setKarza(KarzaService karza) {
		this.karza = karza;
	}
	public KickoffGenQueIbsRequest getKickOFFGenQue() {
		return kickOFFGenQue;
	}
	public void setKickOFFGenQue(KickoffGenQueIbsRequest kickOFFGenQue) {
		this.kickOFFGenQue = kickOFFGenQue;
	}
	
	public CommonDataInRequest getDemographicInfo() {
		return demographicInfo;
	}
	public void setDemographicInfo(CommonDataInRequest demographicInfo) {
		this.demographicInfo = demographicInfo;
	}
	public CibilIbsRequest getCibil() {
		return cibil;
	}
	public void setCibil(CibilIbsRequest cibil) {
		this.cibil = cibil;
	}
	
	public KickoffSingleACtionIbsRequest getKickOFFSingleAction() {
		return kickOFFSingleAction;
	}
	public void setKickOFFSingleAction(KickoffSingleACtionIbsRequest kickOFFSingleAction) {
		this.kickOFFSingleAction = kickOFFSingleAction;
	}
	public KickoffAUTHIbsRequest getKickOFFAuth() {
		return kickOFFAuth;
	}
	public void setKickOFFAuth(KickoffAUTHIbsRequest kickOFFAuth) {
		this.kickOFFAuth = kickOFFAuth;
	}
	
	public EquifaxEVDRIbsRequest getEquifaxEVDR() {
		return equifaxEVDR;
	}
	public void setEquifaxEVDR(EquifaxEVDRIbsRequest equifaxEVDR) {
		this.equifaxEVDR = equifaxEVDR;
	}
	
	public IciciRequest getIciciRequest() {
		return iciciRequest;
	}
	public void setIciciRequest(IciciRequest iciciRequest) {
		this.iciciRequest = iciciRequest;
	}
	@Override
	public String toString() {
		return "IbsAllServiceRequest [demographicInfo=" + demographicInfo + ", cibil=" + cibil + ", equifaxEVDR="
				+ equifaxEVDR + ", kickOFFSingleAction=" + kickOFFSingleAction + ", kickOFFAuth=" + kickOFFAuth
				+ ", kickOFFGenQue=" + kickOFFGenQue + ", karza=" + karza + ", creditVidhya=" + creditVidhya
				+ ", kotak=" + kotak + ", banking=" + banking + ", multiBureau=" + multiBureau + ", hdfcRequest="
				+ hdfcRequest +  "]";
	}
	
	
	
}
