package com.qualtech.finbit.api.db;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.finbit.api.request.FinbitFullReqRes;
import com.qualtech.finbit.api.request.FinbitHeader;

public interface FinbitDB {

	AuthValidator validateAuth(FinbitHeader header);

	public int insertToDB(FinbitFullReqRes fullReqRes);

}
