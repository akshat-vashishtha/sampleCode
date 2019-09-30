package com.qualtech.equifax.api.db;

import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;
import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;
import com.qualtech.equifax.api.entity.EquifaxVidAllDetails;
import com.qualtech.equifax.api.request.EquifaxReqRes;

public interface DBEquifax {

	int insertEquiFaxEVDR(EquifaxEvdrAllDetails equifaxevdrdetails_logs);

	Long insertEquiFaxPCS(EquifaxPcsAllDetails equifaxPcsAllDetails);


	int insertEquiFaxMCR(EquifaxMcrAllDetaills equifaxMcrDetailLogs);


	int insertEquiFaxVID(EquifaxVidAllDetails equifaxdetails);

	Long insertEquifaxPCSReqRes(EquifaxReqRes equifaxPcsReqRes);
	Long insertEquifaxMCRReqRes(EquifaxReqRes equifaxMcrReqRes);

}
