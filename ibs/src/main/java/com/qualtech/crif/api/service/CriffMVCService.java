package com.qualtech.crif.api.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CriffMVCService {
	
	public void getCrifMFIFile(HttpServletRequest request, HttpServletResponse response);
	public void downloadHTML(HttpServletRequest request,HttpServletResponse response);
	public void getCriffFile(HttpServletRequest request,HttpServletResponse response);
	public  void doPost(HttpServletRequest request,String service); 
}
