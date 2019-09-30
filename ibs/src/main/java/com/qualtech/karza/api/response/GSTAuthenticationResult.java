package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_GSTAUTH_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GSTAuthenticationResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
    private String uniqueid;
    private String correlationid;
	private String [] mbr;
	private String cmpRt;
	private String canFlag;
	private GSTAuthenticationPRADR pradr;
	private String contacted;
	private String tradeNam;
	private String stj;
	private String ppr;
	private String dty;
	private String rgdt;
	private String ctb;
	private String sts;
	private String gstin;
	private String adadr[];
	private String lgnm;
	private String nba[];
	private String ctj;
	private String cxdt;
	
	private String mb;
	private String adad;
	private String nb;
	
	@Lob
	//@Transient
	@Column(name="MB")
	@JsonIgnore
	public String getMb() {
		return mb;
	}
	public void setMb(String mb) {
		this.mb = mb;
	}
	public void setAdad(String adad) {
		this.adad = adad;
	}
	public void setNb(String nb) {
		this.nb = nb;
	}
	
	
	
	@Transient
	public String[] getMbr() {
		return mbr;
	}
	public void setMbr(String[] mbr) {
		this.mbr = mbr;
	}
	@Column(name="CMPRT")
	public String getCmpRt() {
		return cmpRt;
	}
	public void setCmpRt(String cmpRt) {
		this.cmpRt = cmpRt;
	}
    @Column(name="TRADENAM")
    public String getTradeNam() {
		return tradeNam;
	}
	public void setTradeNam(String tradeNam) {
		this.tradeNam = tradeNam;
	}
	@Column(name="CANFLAG")
	public String getCanFlag() {
		return canFlag;
	}
	public void setCanFlag(String canFlag) {
		this.canFlag = canFlag;
	}
	@Transient
	public GSTAuthenticationPRADR getPradr() {
		return pradr;
	}
	public void setPradr(GSTAuthenticationPRADR pradr) {
		this.pradr = pradr;
	}
	@Column(name="CONTACTED")
	public String getContacted() {
		return contacted;
	}
	public void setContacted(String contacted) {
		this.contacted = contacted;
	}
	@Column(name="STJ")
	public String getStj() {
		return stj;
	}
	public void setStj(String stj) {
		this.stj = stj;
	}
	@Column(name="PPR")
	public String getPpr() {
		return ppr;
	}
	public void setPpr(String ppr) {
		this.ppr = ppr;
	}
	@Column(name="DTY")
	public String getDty() {
		return dty;
	}
	public void setDty(String dty) {
		this.dty = dty;
	}
	@Column(name="RGDT")
	public String getRgdt() {
		return rgdt;
	}
	public void setRgdt(String rgdt) {
		this.rgdt = rgdt;
	}
	@Column(name="CTB")
	public String getCtb() {
		return ctb;
	}
	public void setCtb(String ctb) {
		this.ctb = ctb;
	}
	@Column(name="STS")
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	@Column(name="GSTIN")
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
    @Lob
	@Column(name="ADADR")
    @JsonIgnore
    public String getAdad() {
		return Arrays.toString(adadr);
	}
    @Transient
	public String[] getAdadr() {
		return adadr;
	}
	public void setAdadr(String[] adadr) {
		this.adadr = adadr;
	}
	@Column(name="LGNM")
	public String getLgnm() {
		return lgnm;
	}
	public void setLgnm(String lgnm) {
		this.lgnm = lgnm;
	}
	@Lob
	@Column(name="NBA")
	@JsonIgnore
	public String getNb() {
		return Arrays.toString(nba);
	}
	@Transient
	public String[] getNba() {
		return nba;
	}
	public void setNba(String[] nba) {
		this.nba = nba;
	}
	@Column(name="CTJ")
	public String getCtj() {
		return ctj;
	}
	public void setCtj(String ctj) {
		this.ctj = ctj;
	}
	@Column(name="CXDT")
	public String getCxdt() {
		return cxdt;
	}
	public void setCxdt(String cxdt) {
		this.cxdt = cxdt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "GSTAuthenticationResult [mbr=" + Arrays.toString(mbr) + ", cmpRt=" + cmpRt + ", canFlag=" + canFlag
				+ ", pradr=" + pradr + ", contacted=" + contacted + ", tradeNam=" + tradeNam + ", stj=" + stj + ", ppr="
				+ ppr + ", dty=" + dty + ", rgdt=" + rgdt + ", ctb=" + ctb + ", sts=" + sts + ", gstin=" + gstin
				+ ", adadr=" + Arrays.toString(adadr) + ", lgnm=" + lgnm + ", nba=" + Arrays.toString(nba) + ", ctj="
				+ ctj + ", cxdt=" + cxdt + "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	
}
