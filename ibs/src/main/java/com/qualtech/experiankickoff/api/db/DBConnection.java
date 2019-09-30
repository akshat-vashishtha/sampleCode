package com.qualtech.experiankickoff.api.db;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.experiankickoff.api.request.ExperianKickOffFullReqRes;
import com.qualtech.experiankickoff.api.request.Header;

public interface DBConnection {

	public  AuthValidator validateAuth(Header header);
	public Long insertAlltoDB(ExperianKickOffFullReqRes fullReqRes);
	public  String getServiceTagName(String serviceRequestUri);
	String getPdfName(String TagName);
}
