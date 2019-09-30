package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class KotakPayRequestHeader implements Serializable{


	private static final long serialVersionUID = 8319813643574537118L;
	
	
	
	@Column(name="MESSAGE_ID")
	private String messageId;
	@Column(name="MSG_SOURCE")
	private String msgSource;
	@Column(name="CLIENT_CODE")
	private String clientCode;
	@Column(name="BATCH_REF_NMBR")
	private String batchRefNmbr;
	@Column(name="HEADER_CHECKSUM")
	private String headerChecksum;
	@Column(name="REQ_RF1")
	private String reqRF1;
	@Column(name="REQ_RF2")
	private String reqRF2;
	@Column(name="REQ_RF3")
	private String reqRF3;
	@Column(name="REQ_RF4")
	private String reqRF4;
	@Column(name="REQ_RF5")
	private String reqRF5;
	
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMsgSource() {
		return msgSource;
	}
	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getBatchRefNmbr() {
		return batchRefNmbr;
	}
	public void setBatchRefNmbr(String batchRefNmbr) {
		this.batchRefNmbr = batchRefNmbr;
	}
	public String getHeaderChecksum() {
		return headerChecksum;
	}
	public void setHeaderChecksum(String headerChecksum) {
		this.headerChecksum = headerChecksum;
	}
	public String getReqRF1() {
		return reqRF1;
	}
	public void setReqRF1(String reqRF1) {
		this.reqRF1 = reqRF1;
	}
	public String getReqRF2() {
		return reqRF2;
	}
	public void setReqRF2(String reqRF2) {
		this.reqRF2 = reqRF2;
	}
	public String getReqRF3() {
		return reqRF3;
	}
	public void setReqRF3(String reqRF3) {
		this.reqRF3 = reqRF3;
	}
	public String getReqRF4() {
		return reqRF4;
	}
	public void setReqRF4(String reqRF4) {
		this.reqRF4 = reqRF4;
	}
	public String getReqRF5() {
		return reqRF5;
	}
	public void setReqRF5(String reqRF5) {
		this.reqRF5 = reqRF5;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakPayRequestHeader [ messageId=" + messageId + ", msgSource=" + msgSource
				+ ", clientCode=" + clientCode + ", batchRefNmbr="
				+ batchRefNmbr + ", headerChecksum=" + headerChecksum
				+ ", reqRF1=" + reqRF1 + ", reqRF2=" + reqRF2 + ", reqRF3="
				+ reqRF3 + ", reqRF4=" + reqRF4 + ", reqRF5=" + reqRF5 + "]";
	}
	
	
	
}
