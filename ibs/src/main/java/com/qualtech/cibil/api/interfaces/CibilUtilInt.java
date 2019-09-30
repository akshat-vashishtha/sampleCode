package com.qualtech.cibil.api.interfaces;

import com.qualtech.cibil.api.response.CibilResponsePayload;

public interface CibilUtilInt {

	void convertHtmlToPDF(String htmlData, String string, CibilResponsePayload cibilresponsepayload);

}
