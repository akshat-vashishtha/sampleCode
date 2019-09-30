package com.qualtech.cibilv2.api.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibilv2.api.db.Cibil2FullReqResSaveDaoInt;
import com.qualtech.cibilv2.api.db.Cibil2FullRequestResponse;
import com.qualtech.cibilv2.api.request.CibilReqRes;
import com.qualtech.cibilv2.api.request.CibilRequest2;
import com.qualtech.cibilv2.api.response.CibilAPIResponse2;
import com.qualtech.cibilv2.api.service.CibilApiServiceV2;
import com.qualtech.cibilv2.api.utils.CibilDto;
import com.qualtech.cibilv2.api.utils.PDFCibilConverterInt;
import com.qualtech.cibilv2.api.utils.UniqueId;

@Controller
@RequestMapping("/cibil/service")
public class CibilRestWebService2 
{
	static Logger logger = Logger.getLogger(CibilRestWebService2.class.getName());
	@Autowired PropertyFile env;
	
	@Autowired
	Cibil2FullReqResSaveDaoInt dao;

	@Autowired
	CibilApiServiceV2 cibilapiservice;
	@Autowired
	PDFCibilConverterInt convt;

	
	@RequestMapping(value ="/v2/cibilRequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody	public CibilAPIResponse2 cibilRequestProcess2(@RequestBody CibilRequest2 cibilapirequest ) {
	
		CibilAPIResponse2 apiResponse = null;
		ObjectMapper om = new ObjectMapper();
		CibilReqRes cibilReqRes = new CibilReqRes();
		String path = "";
		final Cibil2FullRequestResponse cibil2FullReqRes = new Cibil2FullRequestResponse();
    	CibilDto cDto = null;
		try 
		{
			NDC.push("cibilRequest : "+UniqueId.getUniqueId());
			NDC.push(cibilapirequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+cibilapirequest);
		}
		catch(Exception ex)
		{
			logger.info("Error While mapping response header : "+ex);
		}
		try 
		{
			
			apiResponse = cibilapiservice.cibilRequestService(cibilapirequest, cibilReqRes);
			
			try{
				if(apiResponse !=null && apiResponse.getErrorInfo() !=null && apiResponse.getErrorInfo().getCode() !=null && apiResponse.getErrorInfo().getCode().equals("200") && apiResponse.getPayload() != null)
				{
				
						
						cDto = convt.getPdfByteArrayCibil(cibilapirequest,apiResponse);
	
						if (cDto != null) 
							{
							apiResponse.getPayload().setPdfPath(cDto.getFilePath());
							apiResponse.getPayload().setByteArray(cDto.getByteArray());
							path = cDto.getFilePath();
							}
				}
			   }catch(Exception ee){
				   logger.error("CibilRestWebService2 || cibilRequestProcess2 || error:: "+ee);
			}
			
			
			try {
			
				String responseJsonData = om.writeValueAsString(apiResponse);

				Calendar cal = Calendar.getInstance();
				String correlationId = cibilapirequest.getHeader().getCorrelationId();
				String appId = cibilapirequest.getHeader().getAppId();
				cibilReqRes.setTag("CIBIL MFI");
				cibilReqRes.setRequest(cibilapirequest.toString());
				cibilReqRes.setResponse(responseJsonData);
				cibilReqRes.setCorelationid(correlationId);
				cibilReqRes.setAppid(appId);
				cibilReqRes.setUpdatedTime(cal.getTime().toString());
				cibilReqRes.setPdfPath(path);
				cibil2FullReqRes.setCibilRequest(cibilapirequest);
				cibil2FullReqRes.setCibilResponse(apiResponse);
				cibil2FullReqRes.setCibilReqRes(cibilReqRes);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				logger.error("Exception occurd : " + e);
			}

			new Thread(new Runnable() 
			{
				@Override
				public void run() 
				{
					dao.saveCibilData(cibil2FullReqRes);
				}
			}).start();
			
			return apiResponse;
		}
		catch (Exception ex) 
		{
			logger.error("we are in exception : " + ex);
		}
		finally
		{
			NDC.pop();
			NDC.pop();
		}
		return apiResponse;
	}
}
