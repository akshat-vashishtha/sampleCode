package com.qualtech.kotak.api.db;

import java.io.Serializable;

import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.request.KotakReqRes;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakReversalResponse;

public class KotakFULLRequestResponse implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3927591038877160409L;
	
	private String uniqueId;
	private KotakReqRes kotkreqres;
	private KotakRequestReversal kotakReversalReq;
	private KotakReversalResponse  kotakReversalRes;
	private KotakRequest kotakPayReq;
	private KotakResponse kotakPayRes;
	private long eid;
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public KotakReqRes getKotkreqres() {
		return kotkreqres;
	}
	public void setKotkreqres(KotakReqRes kotkreqres) {
		this.kotkreqres = kotkreqres;
	}
	public KotakRequestReversal getKotakReversalReq() {
		return kotakReversalReq;
	}
	public void setKotakReversalReq(KotakRequestReversal kotakReversalReq) {
		this.kotakReversalReq = kotakReversalReq;
	}
	public KotakReversalResponse getKotakReversalRes() {
		return kotakReversalRes;
	}
	public void setKotakReversalRes(KotakReversalResponse kotakReversalRes) {
		this.kotakReversalRes = kotakReversalRes;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	
	
	
	public KotakRequest getKotakPayReq() {
		return kotakPayReq;
	}
	public void setKotakPayReq(KotakRequest kotakPayReq) {
		this.kotakPayReq = kotakPayReq;
	}
	public KotakResponse getKotakPayRes() {
		return kotakPayRes;
	}
	public void setKotakPayRes(KotakResponse kotakPayRes) {
		this.kotakPayRes = kotakPayRes;
	}
	@Override
	public String toString() {
		return "KotakFULLRequestResponse [uniqueId=" + uniqueId
				+ ", kotkreqres=" + kotkreqres + ", kotakReversalReq="
				+ kotakReversalReq + ", kotakReversalRes=" + kotakReversalRes
				+ ", kotakPayReq=" + kotakPayReq + ", kotakPayRes="
				+ kotakPayRes + ", eid=" + eid + "]";
	}
	

	
	
	

}
