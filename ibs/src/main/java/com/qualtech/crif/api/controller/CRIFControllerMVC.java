package com.qualtech.crif.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qualtech.crif.api.service.CriffMVCService;

@Controller
public class CRIFControllerMVC {
	
	//private static Logger logger = LogManager.getLogger(CRIFControllerMVC.class);
	//private static Logger logger=Logger.getLogger(CRIFControllerMVC.class.getName());
		
	@Autowired CriffMVCService criffmvcservice;

	@RequestMapping(value="/")
	public String index() {
		return "ShowCrifOutput";
	}

	@RequestMapping(value="/getCrifOutPut", method = RequestMethod.POST)
	public String getCrifOutPut(HttpServletRequest request) {
		criffmvcservice.doPost(request, "CONSUMER");
		return "crifOutput";
	}

	@RequestMapping(value="/criffResponseforMicroFinance", method = RequestMethod.POST)
	public String criffResponseforMicroFinance( HttpServletRequest request) {
		criffmvcservice.doPost(request, "MFI");
		return "crifOutput";
	}

	@RequestMapping(value="/criffMfiServices" ,method = RequestMethod.POST)
	public String getMfiRequestOutPut() {
		return "ShowCrifOutputForMFI";
	}

	@RequestMapping(value="/downloadRequestJsonForMFI",method = RequestMethod.POST)
	public void getCrifMFIFile(HttpServletRequest request,HttpServletResponse response) {
		criffmvcservice.getCrifMFIFile(request, response);
	}

	@RequestMapping(value="/downloadHtml",method = RequestMethod.POST)
	public void downloadHtml(HttpServletRequest request,HttpServletResponse response) {
		criffmvcservice.downloadHTML(request, response);
	}

	@RequestMapping(value="/crifResponse",method = RequestMethod.POST)
	public String crifResponse() {

		return "ShowCrifOutput";
	}

	@RequestMapping(value="/downloadrequestJson",method = RequestMethod.POST)
	public void getCrifFile(HttpServletRequest request,HttpServletResponse response) {
		criffmvcservice.getCriffFile(request, response);
	}

}
