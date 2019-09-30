package com.qualtech.crif.api.utils;

import com.qualtech.crif.api.dto.CrifTrackerDTO;
import com.qualtech.crif.api.request.CriffApiRequest;

public interface CrifUtilInterface {

	public String convertRequestJsontoXmlService(CriffApiRequest criffApiRequest,String service);
	public String pdfCreation(CrifTrackerDTO crifTrackerDTO,String fileName);
	public String pdfCreationMFI(CrifTrackerDTO crifTrackerDTO, String substring);
}
