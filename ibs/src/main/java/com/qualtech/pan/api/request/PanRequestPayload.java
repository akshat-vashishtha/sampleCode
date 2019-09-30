package com.qualtech.pan.api.request;

import java.io.Serializable;
import java.util.List;

import com.qualtech.pan.api.entity.Pan;

public class PanRequestPayload implements Serializable
{
	private static final long serialVersionUID = -738211049252681009L;
	private List<Pan> panNo;
	private String uniqueReqId;

	public List<Pan> getPanNo() {
		return panNo;
	}
	public void setPanNo(List<Pan> panNo) {
		this.panNo = panNo;
	}
	public String getUniqueReqId() {
		return uniqueReqId;
	}
	public void setUniqueReqId(String uniqueReqId) {
		this.uniqueReqId = uniqueReqId;
	}
	@Override
	public String toString() {
		return "PanRequestPayload [panNo=" + panNo + ", uniqueReqId=" + uniqueReqId + "]";
	}

}
