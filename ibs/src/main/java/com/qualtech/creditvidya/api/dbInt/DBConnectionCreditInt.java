package com.qualtech.creditvidya.api.dbInt;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.creditvidya.api.request.CreditFullReqRes;
import com.qualtech.creditvidya.api.request.Header;

public interface DBConnectionCreditInt {

	public  AuthValidator validateAuth(Header header);
	public void insertAlltoDB(CreditFullReqRes creditFullReqRes);

}
