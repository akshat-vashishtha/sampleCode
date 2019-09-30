package com.qualtech.api.crons;

import java.util.List;
import java.util.Map;

import com.qualtech.api.db.ServiceMaster;
import com.qualtech.api.ibs.util.FibitRequest;
import com.qualtech.api.ibs.util.IbsAllServiceRequest;
import com.qualtech.api.ibs.util.NomineeDetails;

public interface CronsDBConnection {

	public List<IbRequestMaster> loadRequest();

	public void insertRecord(IbRequestMaster result);
	public void updateRecord(IbRequestMaster result);
	Boolean retriveRequest(List<String> servicelist, String service, Map<String, String> hm, IbsAllServiceRequest ibsAllServiceRequest);

	List<ServiceMaster> getServiceList();
	

	Map<String, String> getSaveInitialRequest(List<String> servicelist, String uniqueId);

	String retriveCredential(String tid);

	public List<HistoryCridential> retriveHistory(String tid);

	public List<FibitRequest> getIbsAccountDetail(String correlationid);
	public List<NomineeDetails> getIbsNomineeDetail(String correlationid);
}
