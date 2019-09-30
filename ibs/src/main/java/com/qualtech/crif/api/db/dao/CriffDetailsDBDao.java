package com.qualtech.crif.api.db.dao;

import com.qualtech.crif.api.entity.CriffDetailLogs;
import com.qualtech.crif.api.request.CriffReqRes;

public interface CriffDetailsDBDao {

	
	public Long save(CriffDetailLogs criffDetailLogs);
	public void save(CriffReqRes crifreqres);

}
