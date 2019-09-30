package com.qualtech.equifax.api.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EquifaxMVCService 
{
   public String doPostforEquifaxVID(HttpServletRequest request, HttpServletResponse response);
   public String  doPostforEquifaxMCR(HttpServletRequest request, HttpServletResponse response);
   public String  doPostforEquifaxPCS(HttpServletRequest request, HttpServletResponse response);
   public String  doPostforEquifaxEVDR(HttpServletRequest request, HttpServletResponse response);
   
   public void downloadrequestJsonVID(HttpServletRequest request, HttpServletResponse response);
   public void downloadrequestJsonEVDR(HttpServletRequest request, HttpServletResponse response);
   public void downloadrequestJsonMFI(HttpServletRequest request, HttpServletResponse response);
   public void downloadrequestJsonConsumer(HttpServletRequest request, HttpServletResponse response);
}
