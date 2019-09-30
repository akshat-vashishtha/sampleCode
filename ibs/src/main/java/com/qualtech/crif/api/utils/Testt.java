package com.qualtech.crif.api.utils;

public class Testt {

	public static void main(String[] args) {
		String xml="<REPORT-FILE>  <INQUIRY-STATUS>    <INQUIRY>    "
				+ "  <INQUIRY-UNIQUE-REF-NO>040720171446a0Yf0000003fL7xEA4563750</INQUIRY-UNIQUE-REF-NO>      "
				+ "<RESPONSE-DT-TM>04-07-2017 14:35:59</RESPONSE-DT-TM>      <RESPONSE-TYPE>ERROR</RESPONSE-TYPE> "
				+ "     <ERRORS>        <ERROR>          <CODE>EF013</CODE>      "
				+ "    <DESCRIPTION>Consumer Name should contain atleast one word</DESCRIPTION>  "
				+ "      </ERROR>      </ERRORS>    </INQUIRY>  </INQUIRY-STATUS></REPORT-FILE>";
		String desc=xml.substring(xml.indexOf("DESCRIPTION>"), xml.indexOf("</DESCRIPTION"));
		desc=desc.substring(12, desc.length());
//		System.out.println(desc);
	}
	
	public String getErrorDescription(String xml){
		if(xml.equals("")||xml==null){
			return "Connection Refused";
		}
		String desc=xml.substring(xml.indexOf("DESCRIPTION>"), xml.indexOf("</DESCRIPTION"));
		desc=desc.substring(12, desc.length());
//		System.out.println(desc);
		return desc;
	}

}
