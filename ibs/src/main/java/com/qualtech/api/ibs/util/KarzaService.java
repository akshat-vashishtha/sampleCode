package com.qualtech.api.ibs.util;

import java.io.Serializable;


public class KarzaService  implements Serializable{
	private String consent;
	private KarzaElectricity electricity;
	//private KarzaVoter voter;
	//private KarzaTan tan;
	//private KarzaMCASignature mcaSignature;
	private KarzaCompSearchAndllplookup compSearchAndllplookup;
	//private KarzaLlpIdentification llpIdentification;
	private KarzaIec iec;
	//private KarzaGstAuthentication gstAuthentication;
	private KarzaShopEstablishment shopEstablishment;
	//private KarzaUdyogAadhar udyogAadhar;
	private KarzaNrega nrega;
	//private KarzaForm16Quarterly form16Quarterly;
	private KarzaWebsiteDomain websiteDomain;
	private KarzaBankAccount bankAccount;
	private KarzaNameSimilarity nameSimilarity;
	private KarzaAddressMatching addressMatching;
	private KarzaICWAI icwaiMember;
	private KarzaForm16Auth form16;
	private KarzaPNG png;
	private KarzaESICId esicId;
	//private KarzaEmployerlookup employerlookup;
	//private KarzaIfsc ifsc;
	//private KarzaEPFAuthPassbook epfAuthPassbook;
	//private KarzaEPFAuthOtp epfAuthotp;
	private KarzaFSSAILicence fssaiLicence;
	private KarzaRCSearch rcSearch;
	private karzaITRAuth itrAuth;
	private KarzaGstIdentification gstIdenAuth;
	private KarzaIcsiMembership icsiMembership;
	private KarzaIcwaiFirmAuth icwaiFirmAuth;
	private KarzaLpg lpg;
	private KarzaHsn hsn;
	private KarzaPassport passport;
	private KarzaCaMembershipAuth caMembershipAuth;
	
	
	public KarzaHsn getHsn() {
		return hsn;
	}
	public void setHsn(KarzaHsn hsn) {
		this.hsn = hsn;
	}
	public KarzaPassport getPassport() {
		return passport;
	}
	public void setPassport(KarzaPassport passport) {
		this.passport = passport;
	}
	public KarzaLpg getLpg() {
		return lpg;
	}
	public void setLpg(KarzaLpg lpg) {
		this.lpg = lpg;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public KarzaElectricity getElectricity() {
		return electricity;
	}
	public void setElectricity(KarzaElectricity electricity) {
		this.electricity = electricity;
	}
	public KarzaCompSearchAndllplookup getCompSearchAndllplookup() {
		return compSearchAndllplookup;
	}
	public void setCompSearchAndllplookup(KarzaCompSearchAndllplookup compSearchAndllplookup) {
		this.compSearchAndllplookup = compSearchAndllplookup;
	}
	public KarzaIec getIec() {
		return iec;
	}
	public void setIec(KarzaIec iec) {
		this.iec = iec;
	}
	public KarzaShopEstablishment getShopEstablishment() {
		return shopEstablishment;
	}
	public void setShopEstablishment(KarzaShopEstablishment shopEstablishment) {
		this.shopEstablishment = shopEstablishment;
	}
	public KarzaNrega getNrega() {
		return nrega;
	}
	public void setNrega(KarzaNrega nrega) {
		this.nrega = nrega;
	}
	public KarzaWebsiteDomain getWebsiteDomain() {
		return websiteDomain;
	}
	public void setWebsiteDomain(KarzaWebsiteDomain websiteDomain) {
		this.websiteDomain = websiteDomain;
	}
	public KarzaBankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(KarzaBankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	public KarzaNameSimilarity getNameSimilarity() {
		return nameSimilarity;
	}
	public void setNameSimilarity(KarzaNameSimilarity nameSimilarity) {
		this.nameSimilarity = nameSimilarity;
	}
	public KarzaAddressMatching getAddressMatching() {
		return addressMatching;
	}
	public void setAddressMatching(KarzaAddressMatching addressMatching) {
		this.addressMatching = addressMatching;
	}
	
	
	public KarzaICWAI getIcwaiMember() {
		return icwaiMember;
	}
	public void setIcwaiMember(KarzaICWAI icwaiMember) {
		this.icwaiMember = icwaiMember;
	}
	public KarzaForm16Auth getForm16() {
		return form16;
	}
	public void setForm16(KarzaForm16Auth form16) {
		this.form16 = form16;
	}
	public KarzaPNG getPng() {
		return png;
	}
	public void setPng(KarzaPNG png) {
		this.png = png;
	}
	public KarzaESICId getEsicId() {
		return esicId;
	}
	public void setEsicId(KarzaESICId esicId) {
		this.esicId = esicId;
	}
	public KarzaFSSAILicence getFssaiLicence() {
		return fssaiLicence;
	}
	public void setFssaiLicence(KarzaFSSAILicence fssaiLicence) {
		this.fssaiLicence = fssaiLicence;
	}
	public KarzaRCSearch getRcSearch() {
		return rcSearch;
	}
	public void setRcSearch(KarzaRCSearch rcSearch) {
		this.rcSearch = rcSearch;
	}
	public karzaITRAuth getItrAuth() {
		return itrAuth;
	}
	public void setItrAuth(karzaITRAuth itrAuth) {
		this.itrAuth = itrAuth;
	}
	public KarzaGstIdentification getGstIdenAuth() {
		return gstIdenAuth;
	}
	public void setGstIdenAuth(KarzaGstIdentification gstIdenAuth) {
		this.gstIdenAuth = gstIdenAuth;
	}
	public KarzaIcsiMembership getIcsiMembership() {
		return icsiMembership;
	}
	public void setIcsiMembership(KarzaIcsiMembership icsiMembership) {
		this.icsiMembership = icsiMembership;
	}
	public KarzaIcwaiFirmAuth getIcwaiFirmAuth() {
		return icwaiFirmAuth;
	}
	public void setIcwaiFirmAuth(KarzaIcwaiFirmAuth icwaiFirmAuth) {
		this.icwaiFirmAuth = icwaiFirmAuth;
	}
	@Override
	public String toString() {
		return "KarzaService [consent=" + consent + ", electricity=" + electricity + ", compSearchAndllplookup="
				+ compSearchAndllplookup + ", iec=" + iec + ", shopEstablishment=" + shopEstablishment + ", nrega="
				+ nrega + ", websiteDomain=" + websiteDomain + ", bankAccount=" + bankAccount + ", nameSimilarity="
				+ nameSimilarity + ", addressMatching=" + addressMatching + ", icwaiMember=" + icwaiMember + ", form16="
				+ form16 + ", png=" + png + ", esicId=" + esicId + ", fssaiLicence=" + fssaiLicence + ", rcSearch="
				+ rcSearch + ", itrAuth=" + itrAuth + ", gstIdenAuth=" + gstIdenAuth + ", icsiMembership="
				+ icsiMembership + ", icwaiFirmAuth=" + icwaiFirmAuth + "]";
	}
	public KarzaCaMembershipAuth getCaMembershipAuth() {
		return caMembershipAuth;
	}
	public void setCaMembershipAuth(KarzaCaMembershipAuth caMembershipAuth) {
		this.caMembershipAuth = caMembershipAuth;
	}
	
	
	
	
	
}