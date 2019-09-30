package com.qualtech.karza.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.karza.api.common.dto.AadharRequest;
import com.qualtech.karza.api.common.dto.AddressRequest;
import com.qualtech.karza.api.common.dto.BankAccountRequest;
import com.qualtech.karza.api.common.dto.CAMemberShipAuthRequest;
import com.qualtech.karza.api.common.dto.CompSearchRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPCINLookUpRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPIdentificationRequest;
import com.qualtech.karza.api.common.dto.DlRequest;
import com.qualtech.karza.api.common.dto.DlRequest2;
import com.qualtech.karza.api.common.dto.EPFAuthOTPRequest;
import com.qualtech.karza.api.common.dto.EPFAuthPassBookRequest;
import com.qualtech.karza.api.common.dto.ESICIdRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest2;
import com.qualtech.karza.api.common.dto.EmailAuthRequest;
import com.qualtech.karza.api.common.dto.EmolpyerLookupRequest;
import com.qualtech.karza.api.common.dto.FSSAILicenceRequest;
import com.qualtech.karza.api.common.dto.Form16QuatRequest;
import com.qualtech.karza.api.common.dto.Form16Request;
import com.qualtech.karza.api.common.dto.GSTAuthenticationRequest;
import com.qualtech.karza.api.common.dto.GstIdentificationRequest;
import com.qualtech.karza.api.common.dto.HSNRequest;
import com.qualtech.karza.api.common.dto.ICSIMemberShipRequest;
import com.qualtech.karza.api.common.dto.ICWAIFirmAuthRequest;
import com.qualtech.karza.api.common.dto.ICWAIMembershipRequest;
import com.qualtech.karza.api.common.dto.IECRequest;
import com.qualtech.karza.api.common.dto.IFSCRequest;
import com.qualtech.karza.api.common.dto.ITRAuthRequest;
import com.qualtech.karza.api.common.dto.KarzaDto;
import com.qualtech.karza.api.common.dto.LpgIdentificationRequest;
import com.qualtech.karza.api.common.dto.LpgRequest;
import com.qualtech.karza.api.common.dto.LpgRequest2;
import com.qualtech.karza.api.common.dto.MCASignatureRequest;
import com.qualtech.karza.api.common.dto.NREGARequest;
import com.qualtech.karza.api.common.dto.NameSimilarityRequest;
import com.qualtech.karza.api.common.dto.PanRequest;
import com.qualtech.karza.api.common.dto.PassportRequest;
import com.qualtech.karza.api.common.dto.PngRequest;
import com.qualtech.karza.api.common.dto.RCAuthRequest;
import com.qualtech.karza.api.common.dto.RCSearchRequest;
import com.qualtech.karza.api.common.dto.ShopEstablishmentRequest;
import com.qualtech.karza.api.common.dto.TanRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest2;
import com.qualtech.karza.api.common.dto.UanLookupRequest;
import com.qualtech.karza.api.common.dto.UdyogAadharRequest;
import com.qualtech.karza.api.common.dto.VoterRequest;
import com.qualtech.karza.api.common.dto.WebsiteDomainRequest;
import com.qualtech.karza.api.db.DBKarza;
import com.qualtech.karza.api.request.Form16Payload;
import com.qualtech.karza.api.request.Form16QuatPayload;
import com.qualtech.karza.api.request.PngPayload;
import com.qualtech.karza.api.response.AadharResponse;
import com.qualtech.karza.api.response.AddressResponse;
import com.qualtech.karza.api.response.BankAccountResponse;
import com.qualtech.karza.api.response.CAMemberShipAuthResponse;
import com.qualtech.karza.api.response.CompSearchResponse;
import com.qualtech.karza.api.response.CompSearchResultData;
import com.qualtech.karza.api.response.CompanyLLPCINLookUpResponse;
import com.qualtech.karza.api.response.CompanyLLPCINLookUpResult;
import com.qualtech.karza.api.response.CompanyLLPIdentificationResponse;
import com.qualtech.karza.api.response.DlResponse;
import com.qualtech.karza.api.response.DlResponse2;
import com.qualtech.karza.api.response.EPFAuthOTPResponse;
import com.qualtech.karza.api.response.EPFAuthPassBook;
import com.qualtech.karza.api.response.EPFAuthPassBookESTDetails;
import com.qualtech.karza.api.response.EPFAuthPassBookEmpDetails;
import com.qualtech.karza.api.response.EPFAuthPassBookResponse;
import com.qualtech.karza.api.response.ESICIdResponse;
import com.qualtech.karza.api.response.ESICIdResultContributionDetails;
import com.qualtech.karza.api.response.ElectricityResponse;
import com.qualtech.karza.api.response.ElectricityResponse2;
import com.qualtech.karza.api.response.EmailAuthResponse;
import com.qualtech.karza.api.response.EmployerLookupResponse;
import com.qualtech.karza.api.response.EmployerLookupResult;
import com.qualtech.karza.api.response.FSSAILicenceResponse;
import com.qualtech.karza.api.response.Form16QuatResponse;
import com.qualtech.karza.api.response.Form16QuatResponsePayload;
import com.qualtech.karza.api.response.Form16QuatResult;
import com.qualtech.karza.api.response.Form16Response;
import com.qualtech.karza.api.response.Form16ResponsePayload;
import com.qualtech.karza.api.response.Form16Result;
import com.qualtech.karza.api.response.GSTAuthenticationPRADR;
import com.qualtech.karza.api.response.GSTAuthenticationResponse;
import com.qualtech.karza.api.response.GstIdentificationResponse;
import com.qualtech.karza.api.response.GstIdentificationResult;
import com.qualtech.karza.api.response.HSNResponse;
import com.qualtech.karza.api.response.ICSIMemberShipResponse;
import com.qualtech.karza.api.response.ICSIMemberShipResult;
import com.qualtech.karza.api.response.ICWAIFirmAuthMemberDetails;
import com.qualtech.karza.api.response.ICWAIFirmAuthResponse;
import com.qualtech.karza.api.response.ICWAIMembershipResponse;
import com.qualtech.karza.api.response.IECResponse;
import com.qualtech.karza.api.response.IFSCResponse;
import com.qualtech.karza.api.response.ITRAuthResponse;
import com.qualtech.karza.api.response.LpgIdentificationResponse;
import com.qualtech.karza.api.response.LpgResponse;
import com.qualtech.karza.api.response.LpgResponse2;
import com.qualtech.karza.api.response.MCASignatureResponse;
import com.qualtech.karza.api.response.MCASignatureResult;
import com.qualtech.karza.api.response.NREGAApplicantDetail;
import com.qualtech.karza.api.response.NREGAIncomeDetail;
import com.qualtech.karza.api.response.NREGAResponse;
import com.qualtech.karza.api.response.NameSimilarityResponse;
import com.qualtech.karza.api.response.PanResponse;
import com.qualtech.karza.api.response.PassportResponse;
import com.qualtech.karza.api.response.PngResponse;
import com.qualtech.karza.api.response.PngResponsePayload;
import com.qualtech.karza.api.response.PngResult;
import com.qualtech.karza.api.response.RCAuthResponse;
import com.qualtech.karza.api.response.RCSearchResponse;
import com.qualtech.karza.api.response.ShopEstablishmentResponse;
import com.qualtech.karza.api.response.TanResponse;
import com.qualtech.karza.api.response.TelephoneResponse;
import com.qualtech.karza.api.response.TelephoneResponse2;
import com.qualtech.karza.api.response.UanLookupResponse;
import com.qualtech.karza.api.response.UdyogAadharResponse;
import com.qualtech.karza.api.response.VoterResponse;
import com.qualtech.karza.api.response.WebsiteDomainPayload;
import com.qualtech.karza.api.response.WebsiteDomainResponse;
import com.qualtech.karza.api.response.WebsiteDomainResult;

@Service
@SuppressWarnings("deprecation")
public class KarzaPDFConverter implements PDFConvert
{
	private static Logger logger = Logger.getLogger(KarzaPDFConverter.class.getClass());

	@Autowired DBKarza dbKarza;	
	@Autowired PropertyFile resProp;

	public  KarzaDto getPdfByteArrayDl(DlResponse dlRes,DlRequest dlRequest,String pdfName) 
	{
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ dlRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			try
			{
				if(dlRes.getPayload().getResult().getDl().getImg()!=null 
						&& !dlRes.getPayload().getResult().getDl().getImg().equalsIgnoreCase(""))
				{
					byte[] data = Base64.decode(dlRes.getPayload().getResult().getDl().getImg());
					OutputStream stream = null;
					try{
						stream = new FileOutputStream(filePath+File.separator+dlRes.getPayload().getRequest_id()+".jpeg");
						stream.write(data);
					}finally{
						if(stream!=null){
							stream.close();
						}
					}
					newPath=filePath+File.separator+dlRes.getPayload().getRequest_id()+".jpeg";
				}
			}
			catch(Exception e)
			{
			}

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Driving License Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='7' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(dlRequest.getPayload()==null)
			{
			sb.append("<table border='0.1' width='100%' 'bgcolor='#337BB6'' cellpadding='1'>");
			sb.append("<tr>");
			if(newPath != null){

				sb.append("<td width='11%' align='center' bgcolor='black'>");
				sb.append("<img src='"+newPath+"' width='60' height='70' style='border-radius: 70%;' align='center'>");
				sb.append("</td>");
			}
			sb.append("<td width='80%' valign='center'>");
			sb.append("<table border='0' width='100%' cellpadding='2'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='left'>");
			sb.append("<font face='arial' size='1' font color=\"#337BB6\">");
			sb.append("<b>Customer name :</b>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='80%' align='left'>");
			sb.append("<font face='arial' size='1'>");
			sb.append(dlRequest.getPayload().getName());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='left'>");
			sb.append("<font face='arial' size='1' font color=\"#337BB6\">");
			sb.append("<b>Date of Birth :</b>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='80%' align='left'>");
			sb.append("<font face='arial' size='1'>");sb.append(dlRequest.getPayload().getDob()==null?"NA":dlRequest.getPayload().getDob());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			
			sb.append("<br>");
			if(dlRes.getPayload()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='4'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Driving License");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getDl_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
			
			sb.append("<br>");
			if(dlRes.getPayload()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Driving Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>DOB</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getDl_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getDob());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			
			
			
			
			sb.append("<br>");
			if(dlRes.getPayload()!=null && dlRes.getPayload().getResult()!=null && dlRes.getPayload().getResult().getDl()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Detailed Report");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getName()==null?"NA" : dlRes.getPayload().getResult().getDl().getName());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Father-Husband</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getFather_husband()==null?"NA" :dlRes.getPayload().getResult().getDl().getFather_husband());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>DOB</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getDob()==null?"NA" : dlRes.getPayload().getResult().getDl().getDob());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Blood Group</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getBlood_group()==null?"NA" : dlRes.getPayload().getResult().getDl().getBlood_group());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getAddress()==null?"NA" : dlRes.getPayload().getResult().getDl().getAddress());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");
			sb.append("<b>RTO</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Date of Issue</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getIssue_date()==null ?"Na" : dlRes.getPayload().getResult().getDl().getIssue_date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");

			String nvtf=null;
			String nvtt=null;

			if(dlRes.getPayload().getResult().getDl().getValidity().getNon_transport() != null){
				if(dlRes.getPayload().getResult().getDl().getValidity().getNon_transport().contains("to"))	{
					String[] parts = dlRes.getPayload().getResult().getDl().getValidity().getNon_transport().split("to");
					nvtf = parts[0]; // 004
					nvtt = parts[1];
				}else{
					nvtf=" ";
					nvtt=" ";
				}
			}else{
				nvtf=" ";
				nvtt=" ";
			}


			String vtf=null;
			String vtt=null;

			if(dlRes.getPayload().getResult().getDl().getValidity().getTransport() != null){
				if(dlRes.getPayload().getResult().getDl().getValidity().getTransport().contains("to"))	{
					String[] parts = dlRes.getPayload().getResult().getDl().getValidity().getTransport().split("to");
					vtf = parts[0]; // 004
					vtt = parts[1];
				}else{
					vtf=" ";
					vtt=" ";
				}
			}else{
				vtf=" ";
				vtt=" ";
			}

			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Non Transport Validity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getValidity().getNon_transport()==null?"NA" : dlRes.getPayload().getResult().getDl().getValidity().getNon_transport() );sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Non Transport Valid To</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(nvtt);sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Transport Validity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getValidity().getTransport()==null?"NA" : dlRes.getPayload().getResult().getDl().getValidity().getTransport()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Transport Valid Till</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(vtt); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			}
			
			
			

			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Validity Detail");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Class of Vehicle</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Issue Date/Issuing Authority</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			if(dlRes.getPayload().getResult().getDl().getCov_details() !=null){
				if(dlRes.getPayload().getResult().getDl().getCov_details().length !=0){
					for(int i=0;i<dlRes.getPayload().getResult().getDl().getCov_details().length;i++){	

						sb.append("<tr>");
						sb.append("<td align='center' width='30%'>");sb.append("<font size='0.5' face='arial'>");sb.append("<b>");sb.append(dlRes.getPayload().getResult().getDl().getCov_details()[i].getCov());sb.append("</b>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDl().getCov_details()[i].getIssue_date());sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
					}
				}
			}
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</body>");
			sb.append("</html>");



			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ dlRes.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
				logger.error("error while deleting the img file:"+e);
			}
			return dto;


		} catch (Exception e) {
			logger.error("Error while generating Dl Base64: "+e);
			return null;

		}

	}

	public KarzaDto getPdfByteArrayVoter(VoterResponse voterResponse, VoterRequest voterRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ voterResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
            sb.append("<head>");
            sb.append("<title>");sb.append("</title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<strong><u>Voter ID Authentication Report</u></strong>");sb.append("</font>");
            sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Voter Id");sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(voterRequest.getPayload().getEpic_no());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
			
            
            if(voterRequest.getPayload()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Epic No</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((voterRequest.getPayload().getEpic_no()==null?"":voterRequest.getPayload().getEpic_no()));sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((voterRequest.getPayload().getConsent()==null?"":voterRequest.getPayload().getConsent()));sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            }

            if(voterResponse.getPayload()!=null && voterResponse.getPayload().getResult()!=null)
            {
            sb.append("<table width='100%' cellpading='0'>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color='#005580'>");sb.append("<b>");
            sb.append("Detailed Report");
            sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td>");
            
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Lat Long For Polling Booth</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getPs_lat_long()==null?"":voterResponse.getPayload().getResult().getPs_lat_long());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Relative Name In Vernacular Language 1</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getRln_name_v1()==null?"":voterResponse.getPayload().getResult().getRln_name_v1());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Relative Name In Vernacular Language 2</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getRln_name_v2()==null?"":voterResponse.getPayload().getResult().getRln_name_v2());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Relative Name In Vernacular Language 3</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getRln_name_v3()==null?"":voterResponse.getPayload().getResult().getRln_name_v3());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Part No</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getPart_no()==null?"":voterResponse.getPayload().getResult().getPart_no());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Relationship Type</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getRln_type()==null?"":voterResponse.getPayload().getResult().getRln_type());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Section No</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getSection_no()==null?"":voterResponse.getPayload().getResult().getSection_no());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Id</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getId()==null?"":voterResponse.getPayload().getResult().getId());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name In Vernacular 1</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getName_v1()==null?"":voterResponse.getPayload().getResult().getName_v1());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Relative Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getRln_name()==null?"":voterResponse.getPayload().getResult().getRln_name());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
              sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>District</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getDistrict()==null?"":voterResponse.getPayload().getResult().getDistrict());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Last Update</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getLast_update()==null?"":voterResponse.getPayload().getResult().getLast_update());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>State</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getState()==null?"":voterResponse.getPayload().getResult().getState());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Assembly constituency No</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getAc_no()==null?"":voterResponse.getPayload().getResult().getAc_no());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
             sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>House No</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getHouse_no()==null?"":voterResponse.getPayload().getResult().getHouse_no());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Polling Booth Address</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getPs_name()==null?"":voterResponse.getPayload().getResult().getPs_name());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</table>");
            
            
            
            ////////////////////////
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
            
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Parliamentry Constituency Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getPc_name()==null?"":voterResponse.getPayload().getResult().getPc_name());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Serial Number Inpart</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getSlno_inpart()==null?"":voterResponse.getPayload().getResult().getSlno_inpart());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getName()==null?"":voterResponse.getPayload().getResult().getName());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Part Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getPart_name()==null?"":voterResponse.getPayload().getResult().getPart_name());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>State Code</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getSt_code()==null?"":voterResponse.getPayload().getResult().getSt_code());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Gender</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getGender()==null?"":voterResponse.getPayload().getResult().getGender());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Age</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getAge()==null?"":voterResponse.getPayload().getResult().getAge());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Assembly Constituency Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getAc_name()==null?"":voterResponse.getPayload().getResult().getAc_name());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Epic No</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getEpic_no()==null?"":voterResponse.getPayload().getResult().getEpic_no());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name Vernacular 3</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getName_v3()==null?"":voterResponse.getPayload().getResult().getName_v3());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name Vernacular 2</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getName_v2()==null?"":voterResponse.getPayload().getResult().getName_v2());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>DOB</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getResult().getDob()==null?"":voterResponse.getPayload().getResult().getDob());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            
            
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");

          /*  sb.append("</table>");*/
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("</table>");
            }
		   sb.append("</body>");
		   sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ voterResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayVoter || error:: "+e);
			return null;

		}
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public KarzaDto getPdfByteArraygstAuth(GSTAuthenticationResponse gstAuthenticationResponse,    GSTAuthenticationRequest authenticationRequest,String pdfName) {
		try{
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ gstAuthenticationResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><Strong><u>Gst Authentication Report</u></Strong></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			if(authenticationRequest.getPayload()!=null && gstAuthenticationResponse.getMsgInfo()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='2' face='arial' color='#005580'><b>KYC Document summary</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b<b>Document Name</b></b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Document Number</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Status as Per Source</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Status Message</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>GST</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(authenticationRequest.getPayload().getGstin()==null?"NA" : authenticationRequest.getPayload().getGstin())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getMsgInfo().getStatus()==null?"NA" :gstAuthenticationResponse.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getStatus_msg()==null?"NA" :gstAuthenticationResponse.getPayload().getStatus_msg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
			
			sb.append("<br>");
			if(authenticationRequest.getPayload()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='2' face='arial' color='#005580'><b>Input Data</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='7'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>GSTIN</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Consent</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(authenticationRequest.getPayload().getGstin()==null?"NA" : authenticationRequest.getPayload().getGstin())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(authenticationRequest.getPayload().getConsent()==null?"NA" : authenticationRequest.getPayload().getConsent())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			
			
			
			sb.append("<br>");
					
			if(gstAuthenticationResponse.getPayload()!=null && gstAuthenticationResponse.getPayload().getResult()!=null)
			{
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='2' face='arial' color='#005580'><b>Detailed Report</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
			
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Compliance rating of the business</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getCmpRt()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getCmpRt())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Can Flag</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getCanFlag()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getCanFlag())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Contacted</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getContacted()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getContacted())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Trade Nam</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getTradeNam()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getTradeNam())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>State Jurisdiction of GSTIN</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getStj()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getStj())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>PPR</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getPpr()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getPpr())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Taxpayer Type</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getDty()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getDty())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Registration date under GST</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getRgdt()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getRgdt())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Constitution of Business</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getCtb()==null ?"NA" : gstAuthenticationResponse.getPayload().getResult().getCtb())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Current status of registration under GST</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getSts()==null?"Na" : gstAuthenticationResponse.getPayload().getResult().getSts())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Given GSTIN</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getGstin()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getGstin())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Legal Name of the Business</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getLgnm()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getLgnm())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Central Jurisdiction of the GSTIN</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getCtj()==null?"NA" : gstAuthenticationResponse.getPayload().getResult().getCtj())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
	        sb.append("<tr>");
	        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Date of Cancellation of Registration</b></font>");sb.append("</td>");
	        sb.append("<td width='0.1' >&nbsp;</td>");
	        sb.append("<td align='left'><font size='0.5' face='arial'>"+(gstAuthenticationResponse.getPayload().getResult().getCxdt()==null ?"NA" : gstAuthenticationResponse.getPayload().getResult().getCxdt())+"</font>");sb.append("</td>");
	        sb.append("</tr>");
	        
			sb.append("</table>");
			
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
		}
		
			
			
			sb.append("<br>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='30'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='2' face='arial' color='#005580'><b>The list of Authorized Signatories of the Business</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
			if(gstAuthenticationResponse.getPayload().getResult().getMbr() !=null ){
				if(gstAuthenticationResponse.getPayload().getResult().getMbr().length>0){
						for(String result: gstAuthenticationResponse.getPayload().getResult().getMbr()){
							sb.append("<tr>");
							sb.append("<th bgcolor='#b3e6ff' align='left' >");
							sb.append("<font size='0.5' face='arial'>"+result+"</font>");sb.append("</th>");
							sb.append("</tr>");
						}
				}
			}
			sb.append("</table>");
			
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='30'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='2' face='arial' color='#005580'><b>Primary business contact information including</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
			if(gstAuthenticationResponse.getPayload().getResult().getPradr() !=null ){
				GSTAuthenticationPRADR pradr=gstAuthenticationResponse.getPayload().getResult().getPradr() ;
				   sb.append("<tr>");
			        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Registered Email of the Business</b></font>");sb.append("</td>");
			        sb.append("<td width='0.1' >&nbsp;</td>");
			        sb.append("<td align='left'><font size='0.5' face='arial'>"+(pradr.getEm()==null?"NA" : pradr.getEm())+"</font>");sb.append("</td>");
			        sb.append("</tr>");
			        
			        sb.append("<tr>");
			        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Nature of business carried out at the address</b></font>");sb.append("</td>");
			        sb.append("<td width='0.1' >&nbsp;</td>");
			        sb.append("<td align='left'><font size='0.5' face='arial'>"+(pradr.getNtr()==null?"NA" : pradr.getNtr())+"</font>");sb.append("</td>");
			        sb.append("</tr>");
			        
			        sb.append("<tr>");
			        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Primary Registered Address of the Business</b></font>");sb.append("</td>");
			        sb.append("<td width='0.1' >&nbsp;</td>");
			        sb.append("<td align='left'><font size='0.5' face='arial'>"+(pradr.getAdr()==null?"NA" : pradr.getAdr())+"</font>");sb.append("</td>");
			        sb.append("</tr>"); 
			        
			        sb.append("<tr>");
			        sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Registered Mobile Number of the Business</b></font>");sb.append("</td>");
			        sb.append("<td width='0.1' >&nbsp;</td>");
			        sb.append("<td align='left'><font size='0.5' face='arial'>"+(pradr.getMb()==null?"NA" : pradr.getMb())+"</font>");sb.append("</td>");
			        sb.append("</tr>");
				
			}
			sb.append("</table>");
			
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='2' face='arial' color='#005580'><b>Additional places of business in the state of registration</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
			if(gstAuthenticationResponse.getPayload().getResult().getAdadr() !=null ){
				if(gstAuthenticationResponse.getPayload().getResult().getAdadr().length>0){
					List<GSTAuthAddrBean> gstList=getgstBeanDataList(gstAuthenticationResponse.getPayload().getResult().getAdadr());
					int jCounter=1;
					for(GSTAuthAddrBean bean:gstList){
						
						
							sb.append("<tr>");
							sb.append("<td>");
							sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
							sb.append("<tr>");
							if(jCounter%2!=0){
							sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							}else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><b>Email</b></font>");sb.append("</td>");
							if(jCounter%2!=0){
								sb.append("<td align='left' width='30%'>");
								}else {
								sb.append("<td align='left' width='30%'>");
								}
							sb.append("<font size='0.5' face='arial'>"+(bean.getEmail()==null?"NA" : bean.getEmail())+"</font>");sb.append("</td>");
							sb.append("</tr>");
							
							
							
							 sb.append("<tr>");
							if(jCounter%2!=0){
							sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							}else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><b>Branch Address</b></font>");sb.append("</td>");
							if(jCounter%2!=0){
								sb.append("<td align='left' width='30%'>");
								}else {
								sb.append("<td align='left' width='30%'>");
								}
							sb.append("<font size='0.5' face='arial'>"+(bean.getAddress()==null?"NA" :bean.getAddress())+"</font>");sb.append("</td>");
							sb.append("</tr>");


							sb.append("<tr>");
							if(jCounter%2!=0){
							sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							}else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><b>Second Address</b></font>");sb.append("</td>");
							if(jCounter%2!=0){
								sb.append("<td align='left' width='30%'>");
								}else {
								sb.append("<td align='left' width='30%'>");
								}
							sb.append("<font size='0.5' face='arial'>"+(bean.getAddress2()==null?"NA" : bean.getAddress2())+"</font>");sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if(jCounter%2!=0){
							sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							}else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><b>Mobile</b></font>");sb.append("</td>");
							if(jCounter%2!=0){
								sb.append("<td align='left' width='30%'>");
								}else {
								sb.append("<td align='left' width='30%'>");
								}
							sb.append("<font size='0.5' face='arial'>"+(bean.getMobile()==null ? "NA" : bean.getMobile())+"</font>");sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if(jCounter%2!=0){
							sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							}else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><b>Business Nature</b></font>");sb.append("</td>");
							if(jCounter%2!=0){
								sb.append("<td align='left' width='30%'>");
								}else {
								sb.append("<td align='left' width='30%'>");
								}
							sb.append("<font size='0.5' face='arial'>"+(bean.getNtr()==null?"NA" : bean.getNtr())+"</font>");sb.append("</td>");
							sb.append("</tr>");
							
							

							sb.append("<tr>");
							if(jCounter%2!=0){
							sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							}else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><b>Last Updated Date</b></font>");sb.append("</td>");
							if(jCounter%2!=0){
								sb.append("<td align='left' width='30%'>");
								}else {
								sb.append("<td align='left' width='30%'>");
								}
							sb.append("<font size='0.5' face='arial'>"+(bean.getLastUpdatedDate()==null?"NA" : bean.getLastUpdatedDate())+"</font>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");
							sb.append("</td>");
							sb.append("</tr>");

							
							jCounter++;

					}
					
				/*	for(String result: gstAuthenticationResponse.getPayload().getResult().getAdadr()){
							sb.append("<tr>");
							sb.append("<th bgcolor='#b3e6ff' align='left' >");
							sb.append("<font size='0.5' face='arial'>"+result+"</font>");sb.append("</th>");
							sb.append("</tr>");
						}*/
				}
			}
			sb.append("</table>");
			
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ gstAuthenticationResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArraygstAuth || error:: "+e);
			return null;

		}
	}



	public  KarzaDto getPdfByteArrayElec(ElectricityResponse elecRes, ElectricalRequest request,String pdfname) {
		KarzaDto dto=new KarzaDto();

		try {
			String logoPath=null;

			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}

			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}


			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+ pdfname+elecRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();



			sb.append("<html>");
            sb.append("<head>");
            sb.append("<title>");sb.append("</title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<strong><u>Electricity Bill Report</u></strong>");sb.append("</font>");
            sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Electricity");sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getConsumer_number());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br>");
            
            if(request.getPayload()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consumer Id</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Service Provider</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsumer_id()==null?"":request.getPayload().getConsumer_id());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getService_provider()==null?"":request.getPayload().getService_provider());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()==null?"":request.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getName()==null?"":request.getPayload().getName());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            }
            
           /* if(request.getPayload()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>consumer_id</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>service_provider</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsumer_id()==null?"":request.getPayload().getConsumer_id());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getService_provider()==null?"":request.getPayload().getService_provider());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()==null?"":request.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getName()==null?"":request.getPayload().getName());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            } 
            */
            sb.append("<br>");
            if(elecRes.getPayload()!=null && elecRes.getPayload().getResult()!=null && elecRes.getPayload().getResult().getElec()!=null)
            {
            sb.append("<table width='100%' cellpading='0'>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
            sb.append("Detailed Report");
            sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table width='100%'>");

            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Consumer Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getConsumer_name()==null?"":elecRes.getPayload().getResult().getElec().getConsumer_name());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getAddress()==null?"":elecRes.getPayload().getResult().getElec().getAddress());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Mobile Number</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getMobile_number()==null?"":elecRes.getPayload().getResult().getElec().getMobile_number());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email Address</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getEmail_address()==null?"":elecRes.getPayload().getResult().getElec().getEmail_address());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Consumer Number</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getConsumer_number()==null?"":elecRes.getPayload().getResult().getElec().getConsumer_number());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Number</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getBill_no()==null?"":elecRes.getPayload().getResult().getElec().getBill_no());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Date</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getBill_date()==null?"":elecRes.getPayload().getResult().getElec().getBill_date());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Due Date</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getBill_due_date()==null?"":elecRes.getPayload().getResult().getElec().getBill_due_date());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Amount</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getBill_amount()==null?"":elecRes.getPayload().getResult().getElec().getBill_amount());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Total Amount</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getTotal_amount()==null?"":elecRes.getPayload().getResult().getElec().getTotal_amount());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Amount Payable</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getElec().getAmount_payable()==null?"":elecRes.getPayload().getResult().getElec().getAmount_payable());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            
            

            sb.append("</table>");
            sb.append("</td>");

            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("</table>");
            }
		sb.append("</body>");
		sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+ pdfname+elecRes.getHeader().getCorrelationId()+".pdf");
			return dto;
		} catch (Exception e) {
			logger.error("Error while generating Dl Base64: "+e);
			return dto;

		}
	}

	public  KarzaDto getPdfByteArrayTele(TelephoneResponse teleResponse,TelephoneRequest teleRequest,String pdfName) {
		KarzaDto dto=new KarzaDto();
		try {
			String logoPath=null;
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}

			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ teleResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();


			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>TELEPHONE Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			/*sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name Match/Mismatch</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Confidence Score</strong>");sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Telephone");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRequest.getPayload().getTel_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			/*sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getName().getMatch());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getName().getScore());sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Telephone No</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRequest.getPayload().getTel_no());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(teleResponse.getPayload().getResult()!=null)
			{
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Telephone No</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleResponse.getPayload().getResult().getTele().getTelephoneNo());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleResponse.getPayload().getResult().getTele().getName());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Installation Type</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleResponse.getPayload().getResult().getTele().getInstallation_Type());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Category</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleResponse.getPayload().getResult().getTele().getCategory());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleResponse.getPayload().getResult().getTele().getAddress());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			

			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			}
			
			sb.append("</body>");
			sb.append("</html>");



			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ teleResponse.getHeader().getCorrelationId()+".pdf");
			return dto;
		} catch (Exception e) {
			logger.error("Error while generating Dl Base64: "+e);
			return null;

		}
	}

	public  KarzaDto getPdfByteArrayLpg(LpgResponse lpgRes,LpgRequest lpgRequest,String pdfName) {
		KarzaDto dto=new KarzaDto();
		try {
			String logoPath=null;
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}

			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);

			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+ pdfName+lpgRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");sb.append("<font size='4' face='arial' color='#005580'><strong><u>");sb.append("LPG Bill Report");
			sb.append("</strong></u></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");


			sb.append("<table border='0.1' width='100%' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' width='20%' align='left'>");
			sb.append("<font face='arial' size='1'>");sb.append("<b>");sb.append("Customer name");
			sb.append("</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='80%' align='left'>");
			sb.append("<font face='arial' size='1'>");sb.append(/*lpgRequest.getPayload().getName()*/"");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' width='20%' align='left'>");
			sb.append("<font face='arial' size='1'>");sb.append("<b>");sb.append("LPG Id");sb.append("</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='80%' align='left'>");
			sb.append("<font face='arial' size='1'>");sb.append(lpgRequest.getPayload().getLpg_id()==null?"NA":lpgRequest.getPayload().getLpg_id());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");


			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name Match/Mismatch</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Confidence Score</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("LPG");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRequest.getPayload().getLpg_id()==null?"NA":lpgRequest.getPayload().getLpg_id());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getMsgInfo().getStatus()==null?"NA":lpgRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getStatus_msg()==null?"NA":lpgRes.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getName().getMatch()==null?"NA":lpgRes.getPayload().getResult().getName().getMatch());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getName().getScore()==null?"NA":lpgRes.getPayload().getResult().getName().getScore());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			if (lpgRequest!=null && lpgRequest.getPayload()!=null) {
				
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>LPG Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consent</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRequest.getPayload().getLpg_id() == null ? "NA"
						: lpgRequest.getPayload().getLpg_id());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRequest.getPayload().getConsent() == null ? "NA" : lpgRequest.getPayload().getConsent());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
			if (lpgRes!=null && lpgRes.getPayload()!=null && lpgRes.getPayload().getResult()!=null && lpgRes.getPayload().getResult().getLpg()!=null) {
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getConsumerName()==null?"NA":lpgRes.getPayload().getResult().getLpg().getConsumerName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getConsumerNo()==null?"NA":lpgRes.getPayload().getResult().getLpg().getConsumerNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Email</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getConsumerEmail()==null?"NA":lpgRes.getPayload().getResult().getLpg().getConsumerEmail());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Address</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getConsumerAddress()==null?"NA":lpgRes.getPayload().getResult().getLpg().getConsumerAddress());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Contact Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getConsumerContact()==null?"NA":lpgRes.getPayload().getResult().getLpg().getConsumerContact());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer LPG Status</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getStatus()==null?"NA":lpgRes.getPayload().getResult().getLpg().getStatus());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Bank Account</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getBankAccountNo()==null?"NA":lpgRes.getPayload().getResult().getLpg().getBankAccountNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Subsidized Refill Consumed</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getSubsidizedRefillConsumed()==null?"NA":lpgRes.getPayload().getResult().getLpg().getSubsidizedRefillConsumed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Total Refill Consumed</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getTotalRefillConsumed()==null?"NA":lpgRes.getPayload().getResult().getLpg().getTotalRefillConsumed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>PIN</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getPin()==null?"NA":lpgRes.getPayload().getResult().getLpg().getPin());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Bank Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getBankName()==null?"NA":lpgRes.getPayload().getResult().getLpg().getBankName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>IFSC Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLpg().getIfscCode()==null?"NA":lpgRes.getPayload().getResult().getLpg().getIfscCode());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			
			sb.append("<table width='100%'>");

			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");


			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>City/Town</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getCity()==null?"NA":lpgRes.getPayload().getResult().getLpg().getCity());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Aadhar No.</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getAadhaarNo()==null?"NA":lpgRes.getPayload().getResult().getLpg().getAadhaarNo());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Approximate Subsidy Availed</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getApproximateSubsidyAvailed()==null?"NA":lpgRes.getPayload().getResult().getLpg().getApproximateSubsidyAvailed());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Given Up Subsidy</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getGivenUpSubsidy()==null?"NA":lpgRes.getPayload().getResult().getLpg().getGivenUpSubsidy());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Last Booking Date</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getLastBookingDate()==null?"NA":lpgRes.getPayload().getResult().getLpg().getLastBookingDate());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Distributor Name</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getDistributorName()==null?"NA":lpgRes.getPayload().getResult().getLpg().getDistributorName());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Distributor Address</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getDistributorAddress()==null?"NA":lpgRes.getPayload().getResult().getLpg().getDistributorAddress());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Distributor Code</strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg().getDistributorCode()==null?"NA":lpgRes.getPayload().getResult().getLpg().getDistributorCode());
			sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");


			sb.append("</table>");
		}
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table>");

			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");

			sb.append("</table>");

			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+ pdfName+lpgRes.getHeader().getCorrelationId()+".pdf");
			return dto;
		} catch (Exception e) {
			logger.error("Error while generating Dl Base64: "+e);
			return dto;

		}

	}



	/*public  KarzaDto getPdfByteArrayFusion(CriffApiResponse apiResponse) {
		KarzaDto dto=new KarzaDto();
		try {

			String filePath=resProp.getString("com.karza.pdf.qc.save");


			String finalPathPdf=getPath(filePath);

			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+ "LPG_"+apiResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(apiResponse.getPayload().getBureauResponse().get(0).getHtmlResponse()));
			document.close();
			dto.setByteArray(apiResponse.getPayload().getBureauResponse().get(0).getPdfResponse());
			dto.setFilePath(finalPathPdf);
			return dto;
	} catch (Exception e) {
		logger.error("Error while generating Dl Base64: "+e);
		return dto;

	}
	 */



	private static String getPath(String filePath) {
		String path=null;
		String downloadFolder = filePath;
		Calendar cal=Calendar.getInstance();
		//		System.out.println(cal.get(Calendar.MONTH)+1);

		File file = new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
		if (file.exists()) {
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}else{
			new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)).mkdir();
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}
		return path;
	}


	public KarzaDto getPdfByteArrayPan(PanResponse panRes, PanRequest panRequest,String pdfName) {

		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ panRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			sb.append("<html>");
            sb.append("<head>");
            sb.append("<title>");sb.append("</title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<strong><u>PAN Card Authentication Report</u></strong>");sb.append("</font>");
            sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            
            if(panRequest.getPayload()!=null && panRes.getMsgInfo()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("PAN Card");sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRequest.getPayload().getPan()==null?"":panRequest.getPayload().getPan());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRes.getMsgInfo().getStatus()==null?"":panRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRes.getMsgInfo().getMessage()==null?"":panRes.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            }
            
            if(panRequest.getPayload()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Pan No.</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRequest.getPayload().getPan()==null?"":panRequest.getPayload().getPan());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRequest.getPayload().getConsent()==null?"":panRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            }
            
            
            
		if(panRes.getPayload()!=null && panRes.getPayload().getResult()!=null)
		{
            sb.append("<table width='100%' cellpading='0'>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
            sb.append("Detailed Report");
            sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table width='100%'>");

            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            if(panRes.getPayload()!=null && panRes.getPayload().getResult()!=null)
            {
            sb.append("<font size='0.5' face='arial'>");sb.append(panRes.getPayload().getResult().getName()==null?"":panRes.getPayload().getResult().getName());sb.append("</font>");
            }
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");

            sb.append("</table>");
            sb.append("</td>");

            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("</table>");
		}
		sb.append("</body>");
		sb.append("</html>");



			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ panRes.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PDFConverter || getPdfByteArrayPan || error:: "+e);
			return null;

		}

	}


	public KarzaDto getPdfByteArrayAadhar(AadharResponse aadharResponse, AadharRequest aadharRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
					Document document = new Document(PageSize.A4,36,36,36,36);
					PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ aadharResponse.getHeader().getCorrelationId()+".pdf"));
					ByteArrayOutputStream bStream = new ByteArrayOutputStream();
					PdfWriter writer = PdfWriter.getInstance(document, bStream);
					document.open();
					StringBuffer sb = new StringBuffer();

					String newPath=null;
					
					sb.append("<html>");
					sb.append("<head>");
					sb.append("<title>");sb.append("</title>");
					sb.append("</head>");
					sb.append("<body>");
					sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
					sb.append("<tr>");
					sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
					sb.append("</td>");
					sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
					sb.append("<br>");
					sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' width='50%'>");
					sb.append("<font size='3' face='arial' font color=\"#005580\">");
					sb.append("<strong><u>Aadhar Authentication Report</u></strong>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
					
					sb.append("<table width='100%'>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<br/>");
					sb.append("<tr>");
					sb.append("<td align='left'>");
					sb.append("<font size='3' face='arial' font color=\"#005580\">");
					sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
					sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
					sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
					sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Aadhar");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getAadhaarId());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(aadharResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(aadharResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' height='20'>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
					
					
					sb.append("<table width='100%' cellpading='0'>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td align='left'>");
					sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
					sb.append("Input Data");
					sb.append("</b>");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table width='100%'>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Aadhaar Id</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getAadhaarId()!=null?(aadharRequest.getPayload().getAadhaarId()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Consent</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getConsent()!=null?(aadharRequest.getPayload().getConsent()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					if(aadharRequest.getPayload().getDemographics()!=null) {
						
					if(aadharRequest.getPayload().getDemographics().getName()!=null) {
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name Matching Strategy</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getName().getMatchingStrategy()!=null?(aadharRequest.getPayload().getDemographics().getName().getMatchingStrategy()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getName().getNameValue()!=null?(aadharRequest.getPayload().getDemographics().getName().getNameValue()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					}//name
					if(aadharRequest.getPayload().getDemographics().getDob()!=null) {
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>DOB Format</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getDob().getFormat()!=null?(aadharRequest.getPayload().getDemographics().getDob().getFormat()):"NA");sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>DOB</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getDob().getDobValue()!=null?(aadharRequest.getPayload().getDemographics().getDob().getDobValue()):"NA");sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");
						}//dob
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Phone</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getPhone()!=null?(aadharRequest.getPayload().getDemographics().getPhone()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getEmail()!=null?(aadharRequest.getPayload().getDemographics().getEmail()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Gender</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getGender()!=null?(aadharRequest.getPayload().getDemographics().getGender()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address Format</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getAddressFormat()!=null?(aadharRequest.getPayload().getDemographics().getAddressFormat()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					
					if(aadharRequest.getPayload().getDemographics().getAddressFreetext()!=null) {
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address Matching Strategy</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getAddressFreetext().getMatchingStrategy()!=null?(aadharRequest.getPayload().getDemographics().getAddressFreetext().getMatchingStrategy()):"NA");sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(aadharRequest.getPayload().getDemographics().getAddressFreetext().getAddressValue()!=null?(aadharRequest.getPayload().getDemographics().getAddressFreetext().getAddressValue()):"NA");sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");
						}//address
					
					}//demo
					sb.append("</table>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("</table>");
					//////////////////////////////////
					
					if(aadharResponse.getPayload().getResult()!=null) {
					
					sb.append("<table width='100%' cellpading='0'>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td align='left'>");
					sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
					sb.append("Detailed Report");
					sb.append("</b>");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table width='100%'>");

					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Success</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharResponse.getPayload().getResult().getSuccess()!=null?(aadharResponse.getPayload().getResult().getSuccess()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Aadhar Reference Code</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharResponse.getPayload().getResult().getAadhaar_reference_code()!=null?(aadharResponse.getPayload().getResult().getAadhaar_reference_code()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Aadhar Status Code</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharResponse.getPayload().getResult().getAadhaar_status_code()!=null?(aadharResponse.getPayload().getResult().getAadhaar_status_code()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>PID Timestamp</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(aadharResponse.getPayload().getResult().getPid_timestamp()!=null?(aadharResponse.getPayload().getResult().getPid_timestamp()):"NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("</table>");
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("</table>");
					sb.append("</td>");

					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("</table>");
					
					}//details
					
					
					sb.append("</body>");

					sb.append("</html>");


					HTMLWorker worker = new HTMLWorker(document);
					worker.parse(new StringReader(sb.toString()));
					document.close();
					String base64 = Base64.encodeBytes(bStream.toByteArray());
					dto.setByteArray(base64);
					dto.setFilePath(finalPathPdf +File.separator+pdfName+ aadharResponse.getHeader().getCorrelationId()+".pdf");

					return dto;


				} catch (Exception e) {
					return null;

				}

			

			}


	public KarzaDto getPdfByteArrayPassport(PassportResponse response, PassportRequest request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e)
			{

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;


			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Passport Authentication Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Passport");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getPassportNumber()!=null?request.getPayload().getPassportNumber():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getMsgInfo().getStatus()!=null?response.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getStatus_msg()!=null?response.getPayload().getStatus_msg():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			if(request!=null && request.getPayload()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table  border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Passport Number</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getPassportNumber()!=null?request.getPayload().getPassportNumber():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Date of Birth</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getDob()!=null?request.getPayload().getDob():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");
				sb.append(request.getPayload().getName()!=null?request.getPayload().getName():"");
				sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Surname</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");
				sb.append(request.getPayload().getLastName()!=null?" "+request.getPayload().getLastName():"");
				sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Gender</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getGender()!=null?request.getPayload().getGender():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Country</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getCountry()!=null?request.getPayload().getCountry():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()!=null?request.getPayload().getConsent():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Date of expiry</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getDoe()!=null?request.getPayload().getDoe():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Type</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getType()!=null?request.getPayload().getType():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			if(response!=null && response.getPayload()!=null && response.getPayload().getResult()!=null )
			{
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");

				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>String 1</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getString1()!=null?response.getPayload().getResult().getString1().replaceAll("<", "&lt;"):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>String 2</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getString1()!=null?response.getPayload().getResult().getString2().replaceAll("<", "&lt;"):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");

				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
				sb.append("<br>");
			}

			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){

			}
			return dto;


		} catch (Exception e) {
			logger.error("Exception inside create passport pdf : "+e);
			return null;
		}

	}

	
	public KarzaDto getPdfByteArrayMCASignature(MCASignatureResponse mcaSignatureResponse, MCASignatureRequest mcaSignatureRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ mcaSignatureResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>MCA Signatories Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("MCA Signatories");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(mcaSignatureRequest.getPayload().getCin());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(mcaSignatureResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(mcaSignatureResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Company Identification Number(cin)</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(mcaSignatureRequest.getPayload().getCin());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(mcaSignatureRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(mcaSignatureResponse.getPayload().getResult() !=null ){
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Detailed Report");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Date Of Appointment</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Designation</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Expiry date of Digital Signature Certificate</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Address</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>DIN/DPIN/PAN</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Full Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Whether DSC registered</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			
				if(mcaSignatureResponse.getPayload().getResult().size()>0){
					for(MCASignatureResult result:mcaSignatureResponse.getPayload().getResult()){

						sb.append("<tr>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<i>");sb.append(result.getDate_of_appointment());sb.append("</i>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<i>");sb.append(result.getDesignation());sb.append("</i>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<i>");sb.append(result.getDsc_expiry_date());sb.append("</i>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<i>");sb.append(result.getAddress());sb.append("</i>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<i>");sb.append(result.getDin_dpin_pan());sb.append("</i>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<i>");sb.append(result.getFull_name());sb.append("</i>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<i>");sb.append(result.getWheather_dsc_registered());sb.append("</i>");sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
					}
				}
			}
			
			
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			

			sb.append("</body>");
			sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ mcaSignatureResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayMCASignature || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayIec(IECResponse iecResponse, IECRequest iecRequest,String pdfName) {

		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ iecResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#005580'><strong><u>Import Export Code(IEC) Authentication Report</strong></u></font>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' bgcolor='#f2f2f2' cellpadding='5'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='center'>");sb.append("</td>");
			sb.append("<td width='80%' valign='center'>");
			sb.append("<table width='100%' cellpadding='5'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='right'>");sb.append("</td>");
			sb.append("<td width='80%' align='left'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='right'>");sb.append("</td>");
			sb.append("<td width='80%' align='left'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>KYC Report summary</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><strong>Document Name</strong></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><strong>Document Number</strong></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><strong>Status as per source</strong></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><strong>Status Message</strong></font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>Import Export Code (IEC)</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(iecRequest.getPayload().getIec()==null?"NA":iecRequest.getPayload().getIec())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(iecResponse.getMsgInfo().getStatus()==null?"NA":iecResponse.getMsgInfo().getStatus())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(iecResponse.getPayload().getStatus_msg()==null?"NA":iecResponse.getPayload().getStatus_msg())+"</font>");sb.append("</td>");
	
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if (iecRequest!=null && iecRequest.getPayload()!=null) {
				sb.append("<br>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>IEC Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consent</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(iecRequest.getPayload().getIec() == null ? "NA"
						: iecRequest.getPayload().getIec());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(iecRequest.getPayload().getConsent() == null ? "NA" : iecRequest.getPayload().getConsent());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
			if (iecResponse!=null && iecResponse.getPayload()!=null && iecResponse.getPayload().getResult()!=null) {
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' >");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Name</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (iecResponse.getPayload().getResult().getName()==null?"NA":iecResponse.getPayload().getResult().getName()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>IE Code</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (iecResponse.getPayload().getResult().getIeCode()==null?"NA":iecResponse.getPayload().getResult().getIeCode()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Address</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (iecResponse.getPayload().getResult().getAddress()==null?"NA":iecResponse.getPayload().getResult().getAddress()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>No Of Branches</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (iecResponse.getPayload().getResult().getNoOfBranches()==null?"NA":iecResponse.getPayload().getResult().getNoOfBranches()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>IEC Status</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (iecResponse.getPayload().getResult().getIecStatus()==null?"NA":iecResponse.getPayload().getResult().getIecStatus()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>PAN</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (iecResponse.getPayload().getResult().getPan()==null?"NA":iecResponse.getPayload().getResult().getPan()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Del Status</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (iecResponse.getPayload().getResult().getDel_status()==null?"NA":iecResponse.getPayload().getResult().getDel_status()) + "</font></td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
			}
			sb.append("</body>");

			sb.append("</html>");
			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ iecResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayIec || error:: "+e);
			return null;

		}
	}

	public KarzaDto getPdfByteArrayCompanyLLPCINLookUp(CompanyLLPCINLookUpResponse response, CompanyLLPCINLookUpRequest request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Company / LLP CIN Lookup Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as Per Source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Company / LLP CIN Lookup");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getMsgInfo().getStatus()!=null?response.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getStatus_msg()!=null?response.getPayload().getStatus_msg():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			if(request!=null && request.getPayload()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Company Name</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getCompany_name()!=null?request.getPayload().getCompany_name():"");sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()!=null?request.getPayload().getConsent():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			if(response!=null && response.getPayload()!=null && response.getPayload().getResult()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Detailed Report");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>CIN or LLPIN Number</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Company Name</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				for (CompanyLLPCINLookUpResult result : response.getPayload().getResult()) {
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getCompanyID()!=null?result.getCompanyID():"");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getCompanyName()!=null?result.getCompanyName():"");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
				}
				
				
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
		}

			sb.append("</body>");
			sb.append("</html>");
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;


		} catch (Exception e) {
			logger.error("Exception inside create pdf Company / LLP CIN Lookup : "+e);
			return null;

		}

	}
	

	public KarzaDto getPdfByteArrayCompanyLLPIdentification(CompanyLLPIdentificationResponse response, CompanyLLPIdentificationRequest request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;


			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Company and LLP Identification Number Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Company and LLP Identification Number");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getCin()!=null?request.getPayload().getCin():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getMsgInfo().getStatus()!=null?response.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getStatus_msg()!=null?response.getPayload().getStatus_msg():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			if(request!=null && request.getPayload()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table  border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Company Identification Number </strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getCin()!=null?request.getPayload().getCin():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong> Consent </strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()!=null?request.getPayload().getConsent():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			if(response!=null && response.getPayload()!=null && response.getPayload().getResult()!=null )
			{
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");

				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");

				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Company Name</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getCompanyName()!=null?response.getPayload().getResult().getCompanyName():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Registered Address</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getRegisteredAddress()!=null?response.getPayload().getResult().getRegisteredAddress():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Alternative Address</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getAlternativeAddress()!=null?response.getPayload().getResult().getAlternativeAddress():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Code of the Registrar of Companies</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getRocCode()!=null?response.getPayload().getResult().getRocCode():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Category of the Company</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getCompanyCategory()!=null?response.getPayload().getResult().getCompanyCategory():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Sub-Category of the Company</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getCompanySubCategory()!=null?response.getPayload().getResult().getCompanySubCategory():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Class of the Company</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getClassOfCompany()!=null?response.getPayload().getResult().getClassOfCompany():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Registration Number</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getRegistrationNumber()!=null?response.getPayload().getResult().getRegistrationNumber():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Date of Incorporation</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getDateofIncorporation()!=null?response.getPayload().getResult().getDateofIncorporation():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Number of Members</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getNumberOfMembers()!=null?response.getPayload().getResult().getNumberOfMembers():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Authorised Capital(Rs)</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getAuthorisedCapital()!=null?response.getPayload().getResult().getAuthorisedCapital():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");

				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
				sb.append("<br>");
			}


			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;


		} catch (Exception e) {
			logger.error("Exception inside Company llp identification pdf creation : "+e);
			return null;

		}

	}


	public KarzaDto getPdfByteArrayItrAuth(ITRAuthResponse response, ITRAuthRequest request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>ITR Authentication Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("ITR");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getPan()!=null?request.getPayload().getPan():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getMsgInfo().getStatus()!=null?response.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getStatus_msg()!=null?response.getPayload().getStatus_msg():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			if(request!=null && request.getPayload()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table  border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>PAN Number </strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getPan()!=null?request.getPayload().getPan():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>ITR-V Acknowledgement number </strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getAck()!=null?request.getPayload().getAck():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent </strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()!=null?request.getPayload().getConsent():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			if(response!=null && response.getPayload()!=null && response.getPayload().getResult()!=null )
			{
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Detailed Report");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Validity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getValidity()!=null?response.getPayload().getResult().getValidity():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getStatus()!=null?response.getPayload().getResult().getStatus():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			sb.append("<br>");
		}
			sb.append("</body>");
			sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;


		} catch (Exception e) {
			logger.error("Exception inside create ITR AUTH pdf : "+e);
			return null;

		}

	}

	public KarzaDto getPdfByteArrayEmailAuth(EmailAuthResponse response, EmailAuthRequest request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;
			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;


			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Email Authentication Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Email");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getEmail()!=null?request.getPayload().getEmail():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getMsgInfo().getStatus()!=null?response.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getStatus_msg()!=null?response.getPayload().getStatus_msg():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			if(request!=null && request.getPayload()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table  border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Email</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getEmail()!=null?request.getPayload().getEmail():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			if(response!=null && response.getPayload()!=null && response.getPayload().getResult()!=null )
			{
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");

				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				if(response.getPayload().getResult().getMeta()!=null && response.getPayload().getResult().getMeta().getParams()!=null)
				{
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getMeta().getParams().getEmail()!=null?response.getPayload().getResult().getMeta().getParams().getEmail():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
				}
				if(response.getPayload().getResult().getData()!=null)
				{
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Disposable</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getData().getDisposable()!=null?response.getPayload().getResult().getData().getDisposable():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Webmail</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getData().getWebmail()!=null?response.getPayload().getResult().getData().getWebmail():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Result</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getData().getResult()!=null?response.getPayload().getResult().getData().getResult():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Accept All</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getData().getAccept_all()!=null?response.getPayload().getResult().getData().getAccept_all():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>SMTP Check</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getData().getSmtp_check()!=null?response.getPayload().getResult().getData().getSmtp_check():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Valid Regular Expression</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getData().getRegexp()!=null?response.getPayload().getResult().getData().getRegexp():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Mail Exchanger Records</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getData().getMx_records()!=null?response.getPayload().getResult().getData().getMx_records():"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
				}

				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");

				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
				sb.append("<br>");
			}


			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;


		} catch (Exception e) {
			logger.error("Exception inside create pdf for Email Authentication : "+e);
			return null;

		}

	}

	
	
	public KarzaDto getPdfByteArrayIFSC(IFSCResponse ifscResponse, IFSCRequest ifscRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ ifscResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>IFSC Code Check Report</u></strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("IFSC Code Check");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(ifscRequest.getPayload().getIfsc()==null?"NA":ifscRequest.getPayload().getIfsc());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getMsgInfo().getStatus()==null?"NA":ifscResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getStatus_msg()==null?"NA":ifscResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");

			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Input Data");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>IFSC</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscRequest.getPayload().getIfsc()==null?"NA":ifscRequest.getPayload().getIfsc());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");

			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Detailed Report");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>City</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getCity()==null?"NA":ifscResponse.getPayload().getResult().getCity());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>District</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getDistrict()==null?"NA":ifscResponse.getPayload().getResult().getDistrict());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>IFSC</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getIfsc()==null?"NA":ifscResponse.getPayload().getResult().getIfsc());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>MICR</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getMicr()==null?"NA":ifscResponse.getPayload().getResult().getMicr());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>State</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getState()==null?"NA":ifscResponse.getPayload().getResult().getState());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");
			sb.append("<b>Contact</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getContact()==null?"NA":ifscResponse.getPayload().getResult().getContact());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Branch</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getBranch()==null?"NA":ifscResponse.getPayload().getResult().getBranch());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getAddress()==null?"NA":ifscResponse.getPayload().getResult().getAddress());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bank</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(ifscResponse.getPayload().getResult().getBank()==null?"NA":ifscResponse.getPayload().getResult().getBank());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			sb.append("</body>");
			sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ ifscResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayIFSC || error:: "+e);
			return null;

		}
	}



	public KarzaDto getPdfByteArrayHsn(HSNResponse response, HSNRequest request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;


			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>HSN Code Check Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("HSN Code Check");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getHsCode()!=null?request.getPayload().getHsCode():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getMsgInfo().getStatus()!=null?response.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getStatus_msg()!=null?response.getPayload().getStatus_msg():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			if(request!=null && request.getPayload()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table  border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>HSN code </strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getHsCode()!=null?request.getPayload().getHsCode():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='30%' bgcolor='#b3e6ff' align='left'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong> Consent </strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");sb.append("</td>");
				sb.append("<td >");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()!=null?request.getPayload().getConsent():"");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			if(response!=null && response.getPayload()!=null && response.getPayload().getResult()!=null )
			{
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");

				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");

				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Policy Conditions of this Chapter</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getPolicyConditions()!=null?response.getPayload().getResult().getPolicyConditions():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Policy</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getPolicy()!=null?response.getPayload().getResult().getPolicy():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Policy conditions</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getPolicyConditions()!=null?response.getPayload().getResult().getPolicyConditions():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Heading description</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getHeadingDesc()!=null?response.getPayload().getResult().getHeadingDesc():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Item description 1</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getItemDesc1()!=null?response.getPayload().getResult().getItemDesc1():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Item description 2</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getItemDesc2()!=null?response.getPayload().getResult().getItemDesc2():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Section number</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getSectionNo()!=null?response.getPayload().getResult().getSectionNo():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Section description</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getSectionDesc()!=null?response.getPayload().getResult().getSectionDesc():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Chapter number</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getChapterNo()!=null?response.getPayload().getResult().getChapterNo():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Chapter description</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getChapterDesc()!=null?response.getPayload().getResult().getChapterDesc():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Chapter Notes</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getResult().getChapterNotes()!=null?response.getPayload().getResult().getChapterNotes():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");

				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
				sb.append("<br>");
			}

			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;


		} catch (Exception e) {
			logger.error("Exception inside HSN code check pdf : "+e);
			return null;

		}

	}

	
	public KarzaDto getPdfByteArrayRCAuth(RCAuthResponse rcAuthResponse, RCAuthRequest rcAuthRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ rcAuthResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>RC Auth Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			
			

			
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			/*sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name Match/Mismatch</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Confidence Score</strong>");sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("RC Auth");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			/*sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getName().getMatch());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getName().getScore());sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration No</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			
			if(rcAuthResponse.getPayload().getResult()!=null)
			{
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Engine Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_eng_no());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Vehicle Class</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_vh_class_desc());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Registered Present Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_present_address());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>RTO of Registration</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_registered_at());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Date of Registration</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_regn_dt());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");
			sb.append("<b>RTO</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Insurer of the Vehicle</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_insurance_comp());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Color of the Vehicle</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_color());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Month of Vehicle Manufacture</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_manu_month_yr());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Maximum Sleeper Cap</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_sleeper_cap()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name of Vehicle Manufacturer</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_maker_desc()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Date of RC Status Verification</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_status_as_on()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Validity of RC Insurance</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_insurance_upto()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Cubic Capacity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_cubic_cap()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Serial Number of Owner</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_owner_sr()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

	/*		sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Permanent Add of Vehicle Owner</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_permanent_address()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Vehicle Financer</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_financer()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");*/

		/*	sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name of Owner</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_owner_name()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");*/

			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			
			
			
			
			
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			/*sb.append("Detailed Report");*/
			sb.append("");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Permanent Add of Vehicle Owner</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_permanent_address()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Vehicle Financer</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_financer()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name of Owner</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_owner_name()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Father Name of Registered Owner</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_f_name()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Unladden Weight</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_unld_wt()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Chassis Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_chasi_no()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Vehicle Model and Make</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_maker_model()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Gross Weight</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_gvw()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Duration till the Tax on the Vehicle</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_tax_upto()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Standing Capacity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_stand_cap()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Fitness upto</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_fit_upto()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b> RC Check Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getStautsMessage()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Insurance Policy Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_insurance_policy_no()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Body Type</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_body_type_desc()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Wheelbase</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_wheelbase()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Rc Norms Desc</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_norms_desc()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Registration Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_regn_no()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Vehicle Fuel Type</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_fuel_desc()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>No. of Cylinders</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_no_cyl()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Vehicle Passanger Seating Capacity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthResponse.getPayload().getResult().getRc_seat_cap()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
		
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			sb.append("<br>");
			
			}
			
			
			sb.append("</body>");
			sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ rcAuthResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayRCAuth || error:: "+e);
			return null;
		}
	}


	public KarzaDto getPdfByteArrayShopEstabilishmenth(ShopEstablishmentResponse shopEstablishmentResponse, ShopEstablishmentRequest shopEstablishmentRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ shopEstablishmentResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Shop & Establishment Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Shop & Establishment");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
		////////////////////////////////////////////////
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration No</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Area Code</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentRequest.getPayload().getArea_code());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			//////////////////////////////////////////////////
			if(shopEstablishmentResponse!=null && shopEstablishmentResponse.getPayload()!=null && shopEstablishmentResponse.getPayload().getResult()!=null) {
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Detailed Report");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Category</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getCategory()!=null?(shopEstablishmentResponse.getPayload().getResult().getCategory()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getStatus()==null?"NA":shopEstablishmentResponse.getPayload().getResult().getStatus());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Owner Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getOwner_name());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Entity Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getEntity_name());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Registration Date</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getRegistration_date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Valid To</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getValid_to());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Contact</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getContact());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Commence Date</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getCommence_date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Total Workers</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getTotal_workers());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Father's name of occupier</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getFathers_name_of_occupier());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Nature of business</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getNature_of_business());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getAddress());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Valid from</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getValid_from());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getEmail());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Website url</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(shopEstablishmentResponse.getPayload().getResult().getWebsite_url());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			}
			
			sb.append("</body>");

			sb.append("</html>");



			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ shopEstablishmentResponse.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;


		} catch (Exception e) {
			return null;

		}

	}


	public KarzaDto getPdfByteArrayCaMemberShipAuth(CAMemberShipAuthResponse caMemberShipAuthResponse, CAMemberShipAuthRequest caMemberShipAuthRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ caMemberShipAuthResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>CA Membership</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Membership number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Name</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Type</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("CA Membership Auth");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(caMemberShipAuthRequest.getPayload().getMembership_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(caMemberShipAuthResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(caMemberShipAuthResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse)!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmName()):"");sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse.getPayload().getResult().getFirmType())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmType()):"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Membership No</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(caMemberShipAuthRequest.getPayload().getMembership_no());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(caMemberShipAuthRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(caMemberShipAuthResponse.getPayload().getResult()!=null)
			{
			
			/* for (ICSIMemberShipResult icsiMemberShipResult :icsiMemberShipResponse.getPayload().getResult()) 
			  {	
				*/
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Membership Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((caMemberShipAuthRequest.getPayload().getMembership_no())!=null?(caMemberShipAuthRequest.getPayload().getMembership_no()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((caMemberShipAuthResponse.getPayload().getResult().getAddress())!=null?(caMemberShipAuthResponse.getPayload().getResult().getAddress()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Associate Year</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((caMemberShipAuthResponse.getPayload().getResult().getAssociateYear())!=null?caMemberShipAuthResponse.getPayload().getResult().getAssociateYear():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Cop Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((caMemberShipAuthResponse.getPayload().getResult().getCopStatus())!=null?(caMemberShipAuthResponse.getPayload().getResult().getCopStatus()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Fellow Year</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((caMemberShipAuthResponse.getPayload().getResult().getFellowYear())!=null?(caMemberShipAuthResponse.getPayload().getResult().getFellowYear()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Gender</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((caMemberShipAuthResponse.getPayload().getResult().getGender())!=null?caMemberShipAuthResponse.getPayload().getResult().getGender():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(caMemberShipAuthResponse.getPayload().getResult().getName()!=null?caMemberShipAuthResponse.getPayload().getResult().getName():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Qualification</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(caMemberShipAuthResponse.getPayload().getResult().getQualification()!=null?(caMemberShipAuthResponse.getPayload().getResult().getQualification()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			}
			//}
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ caMemberShipAuthResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayCaMemberShipAuth || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayICSIMemberShip(ICSIMemberShipResponse icsiMemberShipResponse, ICSIMemberShipRequest icsiMemberShipRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ icsiMemberShipResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>ICSI Membership</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("ICSI Membership");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipRequest.getPayload().getCp_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			
			//input
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration No</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Certificate of Practice Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipRequest.getPayload().getMembership_no()!=null?icsiMemberShipRequest.getPayload().getMembership_no():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipRequest.getPayload().getCp_no()!=null?icsiMemberShipRequest.getPayload().getCp_no():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipRequest.getPayload().getConsent()!=null?icsiMemberShipRequest.getPayload().getConsent():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

		
			if(icsiMemberShipResponse.getPayload().getResult()!=null)
			{
			
			 for (ICSIMemberShipResult icsiMemberShipResult :icsiMemberShipResponse.getPayload().getResult()) 
			  {	
				
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Company Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResult.getCpNumber())!=null?(icsiMemberShipResult.getCpNumber()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResult.getAddress())!=null?(icsiMemberShipResult.getAddress()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Benevolent Member</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResult.getBenevolentMember())!=null?icsiMemberShipResult.getBenevolentMember():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>City</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResult.getCity())!=null?(icsiMemberShipResult.getCity()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Designation</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResult.getDesignation())!=null?(icsiMemberShipResult.getDesignation()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResult.getEmail())!=null?icsiMemberShipResult.getEmail():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Membership Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipResult.getMembershipNumber()!=null?icsiMemberShipResult.getMembershipNumber():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Mobile</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipResult.getMob()!=null?(icsiMemberShipResult.getMob()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Organization</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipResult.getOrganization()!=null?icsiMemberShipResult.getOrganization():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Phone</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icsiMemberShipResult.getPhone()!=null?icsiMemberShipResult.getPhone():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			}
			}
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ icsiMemberShipResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayICSIMemberShip || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayIcwaiFirmAuth(ICWAIFirmAuthResponse icwaiFirmAuthResponse, ICWAIFirmAuthRequest icwaiFirmAuthRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ icwaiFirmAuthResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>ICWAI Firm Auth</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("ICWAI Firm Auth");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration No</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			
			
			if(icwaiFirmAuthResponse.getPayload().getResult()!=null)
			{
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Firm Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icwaiFirmAuthResponse.getPayload().getResult().getFirmName())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmName()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Firm Type</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icwaiFirmAuthResponse.getPayload().getResult().getFirmType())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmType()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icwaiFirmAuthResponse.getPayload().getResult().getAddress())!=null?icwaiFirmAuthResponse.getPayload().getResult().getAddress():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Approval Date</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icwaiFirmAuthResponse.getPayload().getResult().getApprovalDate())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getApprovalDate()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>City</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icwaiFirmAuthResponse.getPayload().getResult().getCity())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getCity()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Contact</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((icwaiFirmAuthResponse.getPayload().getResult().getContact())!=null?icwaiFirmAuthResponse.getPayload().getResult().getContact():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Deed Date</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getDeedDt());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>District</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getDist()!=null?(icwaiFirmAuthResponse.getPayload().getResult().getDist()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getEmail());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>L Date</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getLdt());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Mobile</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getMobile());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Pin</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getPin()!=null?(icwaiFirmAuthResponse.getPayload().getResult().getPin()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Re Constitution Date</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getReConDate());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Region</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getRegion()!=null?(icwaiFirmAuthResponse.getPayload().getResult().getRegion()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>State</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(icwaiFirmAuthResponse.getPayload().getResult().getState()!=null?(icwaiFirmAuthResponse.getPayload().getResult().getState()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<br>");
			
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
//			sb.append("<tr>");
//			sb.append("<td  colspan='6' align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			/*sb.append("<b>");*/sb.append("Member Details");/*sb.append("</b>");*/sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			
			sb.append("<tr>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Member No</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Member Name</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Address</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>City</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>State</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Pin</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("</tr>");
			if(icwaiFirmAuthResponse.getPayload().getResult().getMemberDetails().size()>0){
						for(ICWAIFirmAuthMemberDetails result: icwaiFirmAuthResponse.getPayload().getResult().getMemberDetails()){

						sb.append("<tr>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getMemberNo()==null?"NA":result.getMemberNo());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getMemberName()==null?"NA":result.getMemberName());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getAddress()==null?"NA":result.getAddress());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getCity()==null?"NA":result.getCity());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getState()==null?"NA":result.getState());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getPin()==null?"NA":result.getPin());sb.append("");sb.append("</font>");sb.append("</td>");
						
						sb.append("</tr>");
					}
				}else{
				    sb.append("<tr>");
					sb.append("<td colspan='6' align='left'>");
				    sb.append("<font size='0.5' face='arial'>No data Found for Member Details</font>");sb.append("</td>");
				    sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			}
			
			sb.append("</body>");
			sb.append("</html>");
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ icwaiFirmAuthResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayIcwaiFirmAuth || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayEpfAuthPassbook(EPFAuthPassBookResponse epfAuthPassBookResponse,	EPFAuthPassBookRequest epfAuthPassBookRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);    
			Document document = new Document(PageSize.A4,36,36,36,36);							
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ epfAuthPassBookResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>EPF Passbook Authentication Report</strong></u></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>KYC Report summary</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Name</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Number</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status as per source</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status Message</strong></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>EPF Passbook</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(epfAuthPassBookRequest.getPayload().getRequest_id()==null?"NA":epfAuthPassBookRequest.getPayload().getRequest_id())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(epfAuthPassBookResponse.getMsgInfo().getStatus()==null?"NA":epfAuthPassBookResponse.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(epfAuthPassBookResponse.getPayload().getStatus_msg()==null?"NA":epfAuthPassBookResponse.getPayload().getStatus_msg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if (epfAuthPassBookRequest!=null && epfAuthPassBookRequest.getPayload()!=null) {
				sb.append("<br>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Document Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Otp</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(epfAuthPassBookRequest.getPayload().getRequest_id() == null ? "NA"
						: epfAuthPassBookRequest.getPayload().getRequest_id());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(epfAuthPassBookRequest.getPayload().getOtp() == null ? "NA" : epfAuthPassBookRequest.getPayload().getOtp());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
			if (epfAuthPassBookResponse!=null && epfAuthPassBookResponse.getPayload()!=null && epfAuthPassBookResponse.getPayload().getResult()!=null) {
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				int counter = 1;
				List<EPFAuthPassBookESTDetails> authPassBookESTDetails = epfAuthPassBookResponse.getPayload()
						.getResult().getEst_details();
				if (authPassBookESTDetails.size() > 0) {

					sb.append("<table width='100%'>");
					sb.append("<tr>");
					sb.append("<td align='left'><font size='2' face='arial'><b>EPF Employer Accounts detail of  Member along with contribution details</b></font></td>");
					sb.append("</tr>");
					sb.append("</table>");

					for (EPFAuthPassBookESTDetails details : authPassBookESTDetails) {

						sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");

						sb.append("<tr>");
						if (counter % 2 != 0) {
							sb.append("<td bgcolor='#b3e6ff' align='left' width='5%'>");
						} else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='5%'>");
						}
						sb.append("<font size='0.5' face='arial'><strong>Name of the Employer</strong></font>");
						sb.append("</td>");
						sb.append(
								"<td align='left'><font size='0.5' face='arial'>" + (details.getEst_name()==null?"NA":details.getEst_name()) + "</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						if (counter % 2 != 0) {
							sb.append("<td bgcolor='#b3e6ff' align='left' width='5%'>");
						} else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='5%'>");
						}
						sb.append("<font size='0.5' face='arial'><strong>Date of Enquiry of EPF</strong></font>");
						sb.append("</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>" + (details.getDoe_epf()==null?"NA":details.getDoe_epf()) + "</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						if (counter % 2 != 0) {
							sb.append("<td bgcolor='#b3e6ff' align='left' width='5%'>");
						} else {
							sb.append("<td bgcolor='#ffe6cc' align='left' width='5%'>");
						}
						sb.append("<font size='0.5' face='arial'><strong>Office</strong></font>");
						sb.append("</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>" + (details.getOffice()==null?"NA":details.getOffice()) + "</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						if (counter % 2 != 0) {
							sb.append("<td bgcolor='#b3e6ff' align='left' width='5%'>");
						} else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='5%'>");
						}
						sb.append("<font size='0.5' face='arial'><strong>Date of Joining EPF</strong></font>");
						sb.append("</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>" + (details.getDoj_epf()==null?"NA":details.getDoj_epf()) + "</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						if (counter % 2 != 0) {
							sb.append("<td bgcolor='#b3e6ff' align='left' width='5%'>");
						} else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='5%'>");
						}
						sb.append("<font size='0.5' face='arial'><strong>Date of Enquiry of Employee Pension Scheme</strong></font>");
						sb.append("</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>" + (details.getDoe_eps()==null?"NA":details.getDoe_eps()) + "</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						if (counter % 2 != 0) {
							sb.append("<td bgcolor='#b3e6ff' align='left' width='5%'>");
						} else {
							sb.append("<td bgcolor='#80d4ff' align='left' width='5%'>");
						}
						sb.append("<font size='0.5' face='arial'><strong>Member Id</strong></font>");
						sb.append("</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>" + (details.getMember_id()==null?"NA":details.getMember_id()) + "</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td align='center' height='10'></td>");
						sb.append("</tr>");
						sb.append("</table>");

						List<EPFAuthPassBook> authPassBooks = details.getPassbook();
						if (authPassBooks.size() > 0) {
							sb.append("<table width='100%'>");
							sb.append("<tr>");
							sb.append(
									"<td align='left'><font size='2' face='arial'><b>Passbook Details</b></font></td>");
							sb.append("</tr>");
							sb.append("</table>");
							int jCounter = 1;
							for (EPFAuthPassBook book : authPassBooks) 
							{
								sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
								sb.append("<tr>");
								if (jCounter % 2 != 0) 
								{
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								}
								else 
								{
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Amount credited to Pension Account</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getCr_pen_bal()==null?"NA":book.getCr_pen_bal())+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Approved on</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getApproved_on()==null?"NA":book.getApproved_on())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Transaction Type</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getDb_cr_flag()==null?"NA":book.getDb_cr_flag())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Transaction approved on</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getTr_approved()==null?"NA":book.getTr_approved())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Transaction date</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getTr_date_my()==null?"NA":book.getTr_date_my())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>R order</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getR_order()==null?"NA":book.getR_order())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Employees Share of Contribution to EPF for the month</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getCr_ee_share()==null?"NA":book.getCr_ee_share())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Employer's share of contribution to EPF for the month</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getCr_er_share()==null?"NA":book.getCr_er_share())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>particular</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getParticular()==null?"NA":book.getParticular())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Transaction reference number</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getTrrno()==null?"NA":book.getTrrno())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Table name</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getTable_name()==null?"NA":book.getTable_name())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");

								sb.append("<tr>");
								if (jCounter % 2 != 0) {
									sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
								} else {
									sb.append("<td bgcolor='#80d4ff' align='left' width='50%'>");
								}
								sb.append("<font size='0.5' face='arial'><strong>Month and Year for which the contribution is made</strong></font>");
								sb.append("</td>");
								sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (book.getMonth_year()==null?"NA":book.getMonth_year())
										+ "</font>");
								sb.append("</td>");
								sb.append("</tr>");
								sb.append("</table><br>");
								jCounter++;

							}
						}
						counter++;
					}
				}
				EPFAuthPassBookEmpDetails authPassBookEmpDetails = epfAuthPassBookResponse.getPayload().getResult()
						.getEmployee_details();
				if (authPassBookEmpDetails != null) {
					sb.append("<table width='100%'>");
					sb.append("<tr>");
					sb.append("<td align='left'><font size='2' face='arial'><b>Match Schemes</b></font></td>");
					sb.append("</tr>");
					sb.append("</table>");

					sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
					sb.append("<font size='0.5' face='arial'><strong>Date of Birth of the member as per EPFO Records</strong></font>");
					sb.append("</td>");
					sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>" + (authPassBookEmpDetails.getDob()==null?"NA":authPassBookEmpDetails.getDob())
							+ "</font>");
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
					sb.append("<font size='0.5' face='arial'><strong>Father Name</strong></font>");
					sb.append("</td>");
					sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>"
							+ (authPassBookEmpDetails.getFather_name()==null?"NA":authPassBookEmpDetails.getFather_name()) + "</font>");
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='50%'>");
					sb.append("<font size='0.5' face='arial'><strong>Member Name</strong></font>");
					sb.append("</td>");
					sb.append("<td align='left' width='50%'><font size='0.5' face='arial'>"
							+ (authPassBookEmpDetails.getMember_name()==null?"NA":authPassBookEmpDetails.getMember_name()) + "</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

				} 
			}
			sb.append("</body>");

			sb.append("</html>");

			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ epfAuthPassBookResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayEpfAuthPassbook || error:: "+e);
			return null;

		}
	}

	public KarzaDto getPdfByteArrayNrega(NREGAResponse nregaResponse, NREGARequest nregaRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ nregaResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>NREGA Authentication Report</u></strong></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>KYC Report summary</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Name</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Number</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status as per source</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status Message</strong></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>NREGA</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(nregaRequest.getPayload().getJobcardid()==null?"NA":nregaRequest.getPayload().getJobcardid())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(nregaResponse.getMsgInfo().getStatus()==null?"NA":nregaResponse.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(nregaResponse.getPayload().getStatus_msg()==null?"NA":nregaResponse.getPayload().getStatus_msg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			
			if (nregaRequest!=null && nregaRequest.getPayload()!=null) {
				sb.append("<br>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Document Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consent</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(nregaRequest.getPayload().getJobcardid() == null ? "NA"
						: nregaRequest.getPayload().getJobcardid());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(nregaRequest.getPayload().getConsent() == null ? "NA" : nregaRequest.getPayload().getConsent());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
            if (nregaResponse!=null && nregaResponse.getPayload()!=null && nregaResponse.getPayload().getResult()!=null) {
            	sb.append("<table width='100%' cellpading='0'>");
    			sb.append("<!--Details-->");
    			sb.append("<tr>");
    			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
    			sb.append("</td>");
    			sb.append("</tr>");
    			sb.append("<tr>");
    			sb.append("<td align='center' height='10'>");
    			sb.append("</td>");
    			sb.append("</tr>");
    			sb.append("<!--Details-->");
    			sb.append("<tr>");
    			sb.append("<td>");
    			sb.append("<table width='100%'>");
    			sb.append("<tr>");
    			sb.append("<td align='center' height='10'>");
    			sb.append("</td>");
    			sb.append("</tr>");
    			sb.append("<tr>");
    			sb.append("<td>");
    			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
            	
            	sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Family Id1</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getFamilyId1()==null?"NA":nregaResponse.getPayload().getResult().getFamilyId1()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>House Number</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getAddress()==null?"NA":nregaResponse.getPayload().getResult().getAddress()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Name Of Father Or Husband</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getNameOfFatherOrHusband()==null?"NA":nregaResponse.getPayload().getResult().getNameOfFatherOrHusband()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Voter ID</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getVoterId()==null?"NA":nregaResponse.getPayload().getResult().getVoterId()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Village</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getVillage()==null?"NA":nregaResponse.getPayload().getResult().getVillage()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>BPL Family Id</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getBplFamilyId()==null?"NA":nregaResponse.getPayload().getResult().getBplFamilyId()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Family Id</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getFamilyId()==null?"NA":nregaResponse.getPayload().getResult().getFamilyId()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Category</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getCategory()==null?"NA":nregaResponse.getPayload().getResult().getCategory()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>District</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getDistrict()==null?"NA":nregaResponse.getPayload().getResult().getDistrict()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Name Of Head</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getNameOfHead()==null?"NA":nregaResponse.getPayload().getResult().getNameOfHead()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Photo Image Url</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getPhotoImageUrl()==null?"NA":nregaResponse.getPayload().getResult().getPhotoImageUrl()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>BPL Family</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getBplFamily()==null?"NA":nregaResponse.getPayload().getResult().getBplFamily()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Job Card No</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getJobcardno()==null?"NA":nregaResponse.getPayload().getResult().getJobcardno()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Date Of Registration</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getDateOfRegistration()==null?"NA":nregaResponse.getPayload().getResult().getDateOfRegistration()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Panchayat</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getPanchayat()==null?"NA":nregaResponse.getPayload().getResult().getPanchayat()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Block</strong></font>");
				sb.append("</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (nregaResponse.getPayload().getResult().getBlock()==null?"NA":nregaResponse.getPayload().getResult().getBlock()) + "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
			}
            
			
			
			sb.append("<br><br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Income Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Income for 2017-2018</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Income for 2010-2011</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Income for 2013-2014</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Income for 2016-2017</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Income for 2008-2009</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Income for 2009-2010</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Income for 2014-2015</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("</tr>");

			sb.append("</table>");



			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			if(nregaResponse.getPayload().getResult().getIncomeDetail() !=null ){
				if(nregaResponse.getPayload().getResult().getIncomeDetail() .size()>0){
						for(NREGAIncomeDetail result: nregaResponse.getPayload().getResult().getIncomeDetail()){

						sb.append("<tr>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getIncome_for_2017_2018()==null?"NA":result.getIncome_for_2017_2018());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getIncome_for_2010_2011()==null?"NA":result.getIncome_for_2010_2011());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getIncome_for_2013_2014()==null?"NA":result.getIncome_for_2013_2014());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getIncome_for_2016_2017()==null?"NA":result.getIncome_for_2016_2017());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getIncome_for_2008_2009()==null?"NA":result.getIncome_for_2008_2009());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getIncome_for_2009_2010()==null?"NA":result.getIncome_for_2009_2010());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getIncome_for_2014_2015()==null?"NA":result.getIncome_for_2014_2015());sb.append("");sb.append("</font>");sb.append("</td>");
						
						sb.append("</tr>");
					}
				}else{
				    sb.append("<tr>");
					sb.append("<td align='left'>");
				    sb.append("<font size='0.5' face='arial'>No data Found for Income Details</font>");sb.append("</td>");
				    sb.append("</tr>");
			}
			}
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Applicant Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");


			sb.append("</table>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Gender</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Age</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Bank or Postoffice</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Aadhaar No</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Account No</strong>");sb.append("</font>");sb.append("</th>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table border='0.5' width='100%' cellspacing='0' cellpadding='0' bordercolor='#000000'  >");
			sb.append("<tr>");
			sb.append("<td  bgcolor='#000000' height='1'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
				if(nregaResponse.getPayload().getResult().getApplicantDetail() !=null ){
				if(nregaResponse.getPayload().getResult().getApplicantDetail() .size()>0){
						for(NREGAApplicantDetail result: nregaResponse.getPayload().getResult().getApplicantDetail()){

						sb.append("<tr>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getName()==null?"NA":result.getName());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getGender()==null?"NA":result.getGender());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getAge()==null?"NA":result.getAge());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getBankorpostoffice()==null?"NA":result.getBankorpostoffice());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getAadhaarNo()==null?"NA":result.getAadhaarNo());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append(result.getAccountNo()==null?"NA":result.getAccountNo());sb.append("");sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
					}
				}
			}
			sb.append("</table>");
			
			sb.append("</body>");
			sb.append("</html>");

			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ nregaResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayNrega || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayTan(TanResponse tanResponse, TanRequest tanRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ tanResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>TAN Authentication Report</strong></u></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>KYC Report summary</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Name</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Number</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status as per source</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status Message</strong></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>TAN</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(tanRequest.getPayload().getTan()==null?"NA":tanRequest.getPayload().getTan())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(tanResponse.getMsgInfo().getStatus()==null?"NA":tanResponse.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(tanResponse.getPayload().getStatus_msg()==null?"NA":tanResponse.getPayload().getStatus_msg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if (tanRequest!=null && tanRequest.getPayload()!=null) {
				sb.append("<br>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>TAN Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consent</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(tanRequest.getPayload().getTan() == null ? "NA"
						: tanRequest.getPayload().getTan());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(tanRequest.getPayload().getConsent() == null ? "NA" : tanRequest.getPayload().getConsent());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
			if (tanResponse!=null && tanResponse.getPayload()!=null && tanResponse.getPayload().getResult()!=null) {
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Name</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (tanResponse.getPayload().getResult().getName() == null ? "NA"
								: tanResponse.getPayload().getResult().getName())
						+ "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
			}
			sb.append("</body>");

			sb.append("</html>");

			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ tanResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayTan || error:: "+e);
			return null;

		}
	}

	public KarzaDto getPdfByteArrayCompSearch(CompSearchResponse response, CompSearchRequest request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Company Search by Name Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as Per Source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Company Search by Name");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getMsgInfo().getStatus()!=null?response.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(response.getPayload().getStatus_msg()!=null?response.getPayload().getStatus_msg():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			if(request!=null && request.getPayload()!=null)
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Company Name</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getCompany_name()!=null?request.getPayload().getCompany_name():"NA");sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()!=null?request.getPayload().getConsent():"NA");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			if(response!=null && response.getPayload()!=null && response.getPayload().getResult()!=null && response.getPayload().getResult().getResult()!=null )
			{
				sb.append("<table  width='100%'   >");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Detailed Report");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20' >");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Score</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>CIN/LLPIN of The Company/LLP</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Company/LLP Name</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				for (CompSearchResultData result : response.getPayload().getResult().getResult()) {
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getScore()!=null?result.getScore():"");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getCin()!=null?result.getCin():"");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getComapany_name()!=null?result.getComapany_name():"");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
				}
				
				
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
		}

			sb.append("</body>");
			sb.append("</html>");
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ response.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;
		} catch (Exception e) {
			logger.error("Exception inside pdf creation of company search by name : "+e);
			return null;

		}

	}

	
	public KarzaDto getPdfByteArrayUdyogAadhar(UdyogAadharResponse udyogAadharResponse, UdyogAadharRequest udyogAadharRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ udyogAadharResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><Strong><u>Udyog Aadhar Number Report</u></Strong></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			
			if(udyogAadharRequest.getPayload()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' font color=\"#005580\"><b>KYC Document summary</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='3'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Document Name</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Document Number</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Status as per source</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Status Message</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>Udyog Aadhar Number</font>");
			sb.append("</td>");
			String data = (udyogAadharRequest.getPayload().getAadhar()==null?"NA" :udyogAadharRequest.getPayload().getAadhar())+" / "+(udyogAadharRequest.getPayload().getUan()==null?"NA" : udyogAadharRequest.getPayload().getUan());
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(data)+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(udyogAadharResponse.getMsgInfo().getStatus()==null?"NA" : udyogAadharResponse.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getStatus_msg()==null?"NA" : udyogAadharResponse.getPayload().getStatus_msg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			
			if(udyogAadharRequest.getPayload()!=null)
			{
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Input Data</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='3'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Aadhar Number</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Uan</b></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><b>Consent</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(udyogAadharRequest.getPayload().getAadhar()==null?"NA" : udyogAadharRequest.getPayload().getAadhar())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(udyogAadharRequest.getPayload().getUan()==null?"NA" : udyogAadharRequest.getPayload().getUan())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(udyogAadharRequest.getPayload().getConsent()==null?"NA":udyogAadharRequest.getPayload().getConsent())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}

			sb.append("<br>");
			if(udyogAadharResponse.getPayload()!=null && udyogAadharResponse.getPayload().getResult()!=null)
			{
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='3'>");


			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>PIN</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getPin()==null?"NA" : udyogAadharResponse.getPayload().getResult().getPin())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Date of Commencement</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getDateOFCommencement()==null?"NA" : udyogAadharResponse.getPayload().getResult().getDateOFCommencement())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Aadhar</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getAadhar()==null?"NA" : udyogAadharResponse.getPayload().getResult().getAadhar())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>District</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getDistrict()==null?"NA" : udyogAadharResponse.getPayload().getResult().getDistrict())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>District Industry Center</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getDistrictIndustryCentre()==null?"NA" : udyogAadharResponse.getPayload().getResult().getDistrictIndustryCentre())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Name of EnterPrise</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getNameOfEnterPrise()==null?"NA" : udyogAadharResponse.getPayload().getResult().getNameOfEnterPrise())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Number of Emp</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getNumberOfEmp()==null?"NA" : udyogAadharResponse.getPayload().getResult().getNumberOfEmp())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>State</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getState()==null?"NA" : udyogAadharResponse.getPayload().getResult().getState())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Owner Name</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getOwnerName()==null?"NA" : udyogAadharResponse.getPayload().getResult().getOwnerName())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Major Activity</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getMajorActivity()==null?"NA" : udyogAadharResponse.getPayload().getResult().getMajorActivity())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Email</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getEmail()==null ? "NA" : udyogAadharResponse.getPayload().getResult().getEmail())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>PAN</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getPan()==null?"NA" : udyogAadharResponse.getPayload().getResult().getPan())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Ifsc</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getIfsc()==null ? "NA" :udyogAadharResponse.getPayload().getResult().getIfsc())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Mobile</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getMobile()==null?"NA" : udyogAadharResponse.getPayload().getResult().getMobile())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Address</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getAddress()==null?"NA" : udyogAadharResponse.getPayload().getResult().getAddress())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Social Category</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getSocialCategory()==null?"NA" : udyogAadharResponse.getPayload().getResult().getSocialCategory())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Account Number</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getAccountNumber()==null?"NA" : udyogAadharResponse.getPayload().getResult().getAccountNumber())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>EntType</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getEntType()==null?"NA" : udyogAadharResponse.getPayload().getResult().getEntType())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Gender</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getGender()==null?"NA" : udyogAadharResponse.getPayload().getResult().getGender())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Type Of Organization</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getTypeOfOrg()==null?"NA" : udyogAadharResponse.getPayload().getResult().getTypeOfOrg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>Investment</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getInvestment()==null?"NA" : udyogAadharResponse.getPayload().getResult().getInvestment())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><b>NIC Digit Code</b></font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
			sb.append("<td align='left'><font size='0.5' face='arial'>"+(udyogAadharResponse.getPayload().getResult().getNicDigitCode()==null?"NA" : udyogAadharResponse.getPayload().getResult().getNicDigitCode())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			}
			sb.append("</table>");
			
			sb.append("</body>");
			sb.append("</html>");



			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ udyogAadharResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayUdyogAadhar || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayGSTIdentification(GstIdentificationResponse gstIdentificationResponse, GstIdentificationRequest gstIdentificationRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}
			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ gstIdentificationResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");

			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>GST Identification Report</strong></u></font>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' bgcolor='#f2f2f2' cellpadding='5'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='center'>");sb.append("</td>");
			sb.append("<td width='80%' valign='center'>");
			sb.append("<table width='100%'cellpadding='5'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='right'>");sb.append("</td>");
			sb.append("<td width='80%' align='left'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='right'>");sb.append("</td>");
			sb.append("<td width='80%' align='left'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>KYC Report summary</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");sb.append("<table border='0.1' width='100%' cellspacing='0'cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5'face='arial'><strong>Document Name</strong></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5'face='arial'><strong>Document Number</strong></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5'face='arial'><strong>Status as per source</strong></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5'face='arial'><strong>Status Message</strong></font>");sb.append("</td>");
		
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>GST Identification</font></td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(gstIdentificationRequest.getPayload().getGstin()==null?"NA":gstIdentificationRequest.getPayload().getGstin())+"</font></td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(gstIdentificationResponse.getMsgInfo().getStatus()==null?"NA":gstIdentificationResponse.getMsgInfo().getStatus())+"</font></td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(gstIdentificationResponse.getPayload().getStatus_msg()==null?"NA":gstIdentificationResponse.getPayload().getStatus_msg())+"</font></td>");
		
			sb.append("</tr>");
			sb.append("</table>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if (gstIdentificationRequest!=null && gstIdentificationRequest.getPayload()!=null) {
				sb.append("<br>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>TIN / PAN / Service Tax Assessee Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>GSTIN</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>State</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consent</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(gstIdentificationRequest.getPayload().getInput() == null ? "NA"
						: gstIdentificationRequest.getPayload().getInput());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(gstIdentificationRequest.getPayload().getGstin() == null ? "NA"
						: gstIdentificationRequest.getPayload().getGstin());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(gstIdentificationRequest.getPayload().getState() == null ? "NA" : gstIdentificationRequest.getPayload().getState());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(gstIdentificationRequest.getPayload().getConsent() == null ? "NA" : gstIdentificationRequest.getPayload().getConsent());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
			if (gstIdentificationResponse!=null && gstIdentificationResponse.getPayload()!=null) {
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				
				//<!-- loop -->
				int counter = 1;
				if (gstIdentificationResponse.getPayload().getResult() != null) {
					if (gstIdentificationResponse.getPayload().getResult().size() > 0) {
						for (GstIdentificationResult result : gstIdentificationResponse.getPayload().getResult()) {
							sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Email Id</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getEmailId()==null?"NA":result.getEmailId())
									+ "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Application Status</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getApplicationStatus()==null?"NA":result.getApplicationStatus())
									+ "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Mobile Number</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append(
									"<td align='left'><font size='0.5' face='arial'>" + (result.getMobNum()==null?"NA":result.getMobNum()) + "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>PAN</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getPan()==null?"NA":result.getPan()) + "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Gst in Ref Id</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getGstinRefId()==null?"NA":result.getGstinRefId())
									+ "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Reg Type</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getRegType()==null?"NA":result.getRegType())
									+ "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Gst In Id</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getGstinId()==null?"NA":result.getGstinId())
									+ "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Registration Name</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getRegistrationName()==null?"NA":result.getRegistrationName())
									+ "</font>");
							sb.append("</td>");
							sb.append("</tr>");

							sb.append("<tr>");
							if (counter % 2 != 0) {
								sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
							} else {
								sb.append("<td bgcolor='#80d4ff' align='left' width='30%'>");
							}
							sb.append("<font size='0.5' face='arial'><strong>Tin Number</strong></font>");
							sb.append("</td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>" + (result.getTinNumber()==null?"NA":result.getTinNumber())
									+ "</font>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table><br>");
							counter++;
						}
					}
				}
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
			}
			sb.append("</body>");
			sb.append("</html>");
			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ gstIdentificationResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayGSTIdentification || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayFssaiLicence(FSSAILicenceResponse fssaiLicenceResponse, FSSAILicenceRequest fssaiLicenceRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ fssaiLicenceResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>FSSAI License</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("FSSAI License");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			//input data
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Registration No</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceRequest.getPayload().getReg_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			
			if(fssaiLicenceResponse.getPayload().getResult()!=null)
			{
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Firm Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getFirmName());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>License No</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getLicNo());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>License Type</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getLicType());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getAddress());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getStatus());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			

			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			}
			
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ fssaiLicenceResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayFssaiLicence || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayIcwaiMembership(ICWAIMembershipResponse icwaiMembershipResponse, ICWAIMembershipRequest icwaiMembershipRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ icwaiMembershipResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>ICWAI Membership Authentication Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("ICWAI Membership");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipRequest.getPayload().getMembership_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
		
		//input data
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Membership Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipRequest.getPayload().getMembership_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			

			////
			if(icwaiMembershipResponse.getPayload().getResult()!=null) {
				
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table width='100%'>");

				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Membership Date</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getMemberDt()!=null?(icwaiMembershipResponse.getPayload().getResult().getMemberDt()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Chapter</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getChapter()!=null?(icwaiMembershipResponse.getPayload().getResult().getChapter()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Retired</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getRetired()!=null?(icwaiMembershipResponse.getPayload().getResult().getRetired()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>DOB</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getDob()!=null?(icwaiMembershipResponse.getPayload().getResult().getDob()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>First Name</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getfName()!=null?(icwaiMembershipResponse.getPayload().getResult().getfName()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Middle name</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getmName()!=null?(icwaiMembershipResponse.getPayload().getResult().getmName()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Sur Name</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getSrName()!=null?(icwaiMembershipResponse.getPayload().getResult().getSrName()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Prot Firm Name</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getProtFirmName()!=null?(icwaiMembershipResponse.getPayload().getResult().getProtFirmName()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>ValidUpto Date</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getValidUpDt()!=null?(icwaiMembershipResponse.getPayload().getResult().getValidUpDt()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Member Category</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getMenCategory()!=null?(icwaiMembershipResponse.getPayload().getResult().getMenCategory()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Gender</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getGender()!=null?(icwaiMembershipResponse.getPayload().getResult().getGender()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Effective Date</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getEffectiveDt()!=null?(icwaiMembershipResponse.getPayload().getResult().getEffectiveDt()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>MemRegion</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getMemRegion()!=null?(icwaiMembershipResponse.getPayload().getResult().getMemRegion()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Current Employer</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getCrtEmployer()!=null?(icwaiMembershipResponse.getPayload().getResult().getCrtEmployer()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>FirmEft Date</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getFirmEftDt()!=null?(icwaiMembershipResponse.getPayload().getResult().getFirmEftDt()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Cancellation Date</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(icwaiMembershipResponse.getPayload().getResult().getCancellationDt()!=null?(icwaiMembershipResponse.getPayload().getResult().getCancellationDt()):"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");

				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");
				}
			
			sb.append("</body>");

			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ icwaiMembershipResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayIcwaiMembership || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayAddressMatch(AddressResponse addressResponse, AddressRequest addressRequest,String pdfName) {

		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ addressResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Address Matching</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Adress 1</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Adress 2</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Name</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Type</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Address Matching");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressRequest.getPayload().getAddress1());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressRequest.getPayload().getAddress2());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse)!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmName()):"");sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse.getPayload().getResult().getFirmType())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmType()):"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Address 1</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Addess 2</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressRequest.getPayload().getAddress1());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressRequest.getPayload().getAddress2());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			
			if(addressResponse.getPayload().getResult()!=null)
			{
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");sb.append("</b>");
			//sb.append("Address 1");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("Address 1");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Building</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getBuilding())!=null?(addressResponse.getPayload().getResult().getAddress1().getBuilding()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Co</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getCo())!=null?(addressResponse.getPayload().getResult().getAddress1().getCo()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Complex</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getComplex())!=null?addressResponse.getPayload().getResult().getAddress1().getComplex():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>District</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getDistrict())!=null?(addressResponse.getPayload().getResult().getAddress1().getDistrict()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Floor</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getFloor())!=null?(addressResponse.getPayload().getResult().getAddress1().getFloor()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>House</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getHouse())!=null?(addressResponse.getPayload().getResult().getAddress1().getHouse()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Locality</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getLocality())!=null?(addressResponse.getPayload().getResult().getAddress1().getLocality()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Pin</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getPin())!=null?(addressResponse.getPayload().getResult().getAddress1().getPin()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>State</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getState())!=null?(addressResponse.getPayload().getResult().getAddress1().getState()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Street</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getStreet())!=null?(addressResponse.getPayload().getResult().getAddress1().getStreet()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Untagged</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress1().getUntagged())!=null?(addressResponse.getPayload().getResult().getAddress1().getUntagged()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			
		/////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////
		
			

			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			
			sb.append("<br>");
			sb.append("<br>");
			sb.append("<br>");
			sb.append("<br>");
			sb.append("<br>");
			sb.append("<br>");
			sb.append("<br>");
			sb.append("<br>");
			//sb.append("Detailed Report");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");

			sb.append("<td align='center' height='10'>");sb.append("Address 2");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Building</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getBuilding())!=null?(addressResponse.getPayload().getResult().getAddress1().getBuilding()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Co</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getCo())!=null?(addressResponse.getPayload().getResult().getAddress1().getCo()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Complex</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getComplex())!=null?addressResponse.getPayload().getResult().getAddress1().getComplex():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>District</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getDistrict())!=null?(addressResponse.getPayload().getResult().getAddress1().getDistrict()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Floor</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getFloor())!=null?(addressResponse.getPayload().getResult().getAddress1().getFloor()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>House</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getHouse())!=null?(addressResponse.getPayload().getResult().getAddress1().getHouse()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Locality</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getLocality())!=null?(addressResponse.getPayload().getResult().getAddress1().getLocality()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Pin</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getPin())!=null?(addressResponse.getPayload().getResult().getAddress1().getPin()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>State</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getState())!=null?(addressResponse.getPayload().getResult().getAddress1().getState()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Street</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getStreet())!=null?(addressResponse.getPayload().getResult().getAddress1().getStreet()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Untagged</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((addressResponse.getPayload().getResult().getAddress2().getUntagged())!=null?(addressResponse.getPayload().getResult().getAddress1().getUntagged()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			
			////////////////////////////////////////
			//////////////////////////////////////
		
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Address Result");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Match</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Score</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressResponse.getPayload().getResult().getMatch());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(addressResponse.getPayload().getResult().getScore());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			}
			
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ addressResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayAddressMatch || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayNameSimilarity(NameSimilarityResponse nameSimilarityResponse, NameSimilarityRequest nameSimilarityRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ nameSimilarityResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
            sb.append("<head>");
            sb.append("<title>");sb.append("</title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<strong><u>Name Similarity Report</u></strong>");sb.append("</font>");
            sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Name Similarity");sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Name Values");sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(nameSimilarityResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(nameSimilarityResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
			
            if(nameSimilarityRequest.getPayload()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name1</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name2</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Type</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(nameSimilarityRequest.getPayload().getName1()==null?"NA":nameSimilarityRequest.getPayload().getName1());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(nameSimilarityRequest.getPayload().getName2()==null?"NA":nameSimilarityRequest.getPayload().getName2());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(nameSimilarityRequest.getPayload().getType()==null?"NA":nameSimilarityRequest.getPayload().getType());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            }
            
            if(nameSimilarityResponse.getPayload()!=null && nameSimilarityResponse.getPayload().getResult()!=null)
            {
            sb.append("<table width='100%' cellpading='0'>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
            sb.append("Detailed Report");
            sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table width='100%'>");

            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Result</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(nameSimilarityResponse.getPayload().getResult()==null?"":nameSimilarityResponse.getPayload().getResult());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");

            sb.append("</table>");
            sb.append("</td>");

            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("</table>");
            }
		    sb.append("</body>");
		    sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ nameSimilarityResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayNameSimilarity || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayRcSearch(RCSearchResponse rcSearchResponse, RCSearchRequest rcSearchRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
				Document document = new Document(PageSize.A4,36,36,36,36);
				PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ rcSearchResponse.getHeader().getCorrelationId()+".pdf"));
				ByteArrayOutputStream bStream = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, bStream);
				document.open();
				StringBuffer sb = new StringBuffer();
				////////////////
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<title>");sb.append("</title>");
				sb.append("</head>");
				sb.append("<body>");
				sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
				sb.append("<tr>");
				sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
				sb.append("</td>");
				sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<br>");
				sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' width='50%'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<strong><u>Vehicle RC Search Report</u></strong>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Vehicle RC Search");sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchRequest.getPayload().getEngine_no()!=null?(rcSearchRequest.getPayload().getEngine_no()+(rcSearchRequest.getPayload().getChassis_no()!=null && !rcSearchRequest.getPayload().getChassis_no().equals("")?("/"+rcSearchRequest.getPayload().getChassis_no()):"")):(rcSearchRequest.getPayload().getChassis_no()!=null?rcSearchRequest.getPayload().getChassis_no():""));sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				
				//input data
				
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");
				sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Engine Number</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Chassis Number</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>State</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchRequest.getPayload().getEngine_no());sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchRequest.getPayload().getChassis_no());sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchRequest.getPayload().getState());sb.append("</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");

				
				//////
				if(rcSearchResponse!=null && rcSearchResponse.getPayload()!=null && rcSearchResponse.getPayload().getResult()!=null) {
					
					sb.append("<table width='100%' cellpading='0'>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td align='left'>");
					sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
					sb.append("Detailed Report");
					sb.append("</b>");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table width='100%'>");

					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Vehicle RC Number</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcRegnNo()!=null?(rcSearchResponse.getPayload().getResult().getRcRegnNo()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Owner Name</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcOwnerName()!=null?(rcSearchResponse.getPayload().getResult().getRcOwnerName()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Engine Number</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcEngNo()!=null?(rcSearchResponse.getPayload().getResult().getRcEngNo()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Chassis No</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcChasiNo()!=null?(rcSearchResponse.getPayload().getResult().getRcChasiNo()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Maker Desc</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcMakerDesc()!=null?(rcSearchResponse.getPayload().getResult().getRcMakerDesc()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Maker Model</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcMakerModel()!=null?(rcSearchResponse.getPayload().getResult().getRcMakerModel()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Registration Date</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcRegnDt()!=null?(rcSearchResponse.getPayload().getResult().getRcRegnDt()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Vh Class Desc</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcVhClassDesc()!=null?(rcSearchResponse.getPayload().getResult().getRcVhClassDesc()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Color</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcColor()!=null?(rcSearchResponse.getPayload().getResult().getRcColor()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>RC Manufacturing Year</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(rcSearchResponse.getPayload().getResult().getRcManuMonthYr()!=null?(rcSearchResponse.getPayload().getResult().getRcManuMonthYr()):"");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("</table>");
					sb.append("</td>");

					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("</table>");
					}

				sb.append("</body>");
				sb.append("</html>");


				HTMLWorker worker = new HTMLWorker(document);
				worker.parse(new StringReader(sb.toString()));
				document.close();
				String base64 = Base64.encodeBytes(bStream.toByteArray());
				dto.setByteArray(base64);
				dto.setFilePath(finalPathPdf +File.separator+pdfName+ rcSearchResponse.getHeader().getCorrelationId()+".pdf");

				return dto;


			} catch (Exception e) {
				//logger.error("PDFConverter || getPdfByteArrayRcSearch || error:: "+e);
				return null;

			}
		

		}


	public KarzaDto getPdfByteArrayEsicId(ESICIdResponse esicIdResponse, ESICIdRequest esicIdRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ esicIdResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>ESIC ID Authentication Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			/*sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name Match/Mismatch</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Confidence Score</strong>");sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("ESIC ID");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(esicIdRequest.getPayload().getEsic_id());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			/*sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getName().getMatch());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getName().getScore());sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

		
			
			sb.append("<table width='100%'  >");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Esic Id</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(esicIdRequest.getPayload().getEsic_id());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(esicIdRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			
			
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			

			sb.append("Detailed Report");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			//sb.append("</table>");
			//sb.append("</table>");
              
			sb.append("<table width='100%'>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='1' width='100%' cellspacing='0' cellpadding='7'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>UHID</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getUhId()!=null?esicIdResponse.getPayload().getResult().getUhId():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>DOB</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getDob()!=null?esicIdResponse.getPayload().getResult().getDob():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
	
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Adhaar Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getAdhaarStatus()!=null?esicIdResponse.getPayload().getResult().getAdhaarStatus():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Relationship</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getRelationWithIP()!=null?esicIdResponse.getPayload().getResult().getRelationWithIP():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Current Date of Appointment</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getCurrentDateOfAppointment()!=null?esicIdResponse.getPayload().getResult().getCurrentDateOfAppointment():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Nominee Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getNomineeAddress()!=null?esicIdResponse.getPayload().getResult().getNomineeAddress():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Present Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getPresentAddress()!=null?esicIdResponse.getPayload().getResult().getPresentAddress():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Disability Type</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getDisabilityType()!=null?esicIdResponse.getPayload().getResult().getDisabilityType():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Adhaar No</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getAdhaarNo()!=null?esicIdResponse.getPayload().getResult().getAdhaarNo():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Phone No</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getPhoneNo()!=null?esicIdResponse.getPayload().getResult().getPhoneNo():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Maritial Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getMaritialStatus()!=null?esicIdResponse.getPayload().getResult().getMaritialStatus():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getName()!=null?esicIdResponse.getPayload().getResult().getName():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Gender</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getGender()!=null?esicIdResponse.getPayload().getResult().getGender():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Nominee Adhaar No</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getNomineeAdhaarNo()!=null?esicIdResponse.getPayload().getResult().getNomineeAdhaarNo():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Father or Husband</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getFatherOrHusband()!=null?esicIdResponse.getPayload().getResult().getFatherOrHusband():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
    		sb.append("</table>");

			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			
			sb.append("</td>");
			sb.append("</tr>");
			
	   		sb.append("</table>");
			 sb.append("<table width='100%'>");
			 sb.append("<tr>");
		     sb.append("<td align='center' height='10'>");sb.append("</td>");
			 sb.append("</tr>");
			 sb.append("<tr>");
   			 sb.append("<td>");
						
   			sb.append("<table border='1' width='100%' cellspacing='0' cellpadding='7'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Registration Date</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getRegistrationDate()!=null?esicIdResponse.getPayload().getResult().getRegistrationDate():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Permanent Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getPermanentAddress()!=null?esicIdResponse.getPayload().getResult().getPermanentAddress():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Nominee Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getNomineeName()!=null?esicIdResponse.getPayload().getResult().getNomineeName():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			           
						
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Insurance No</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getInsuranceNo()!=null?esicIdResponse.getPayload().getResult().getInsuranceNo():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
	
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>First Date of Appointment</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getFirstDateOfAppointment()!=null?esicIdResponse.getPayload().getResult().getFirstDateOfAppointment():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Dispensary Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(esicIdResponse.getPayload().getResult().getDispensaryName()!=null?esicIdResponse.getPayload().getResult().getDispensaryName():"");sb.append("</font>");
			/*sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");*/

			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			
			

			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			

			sb.append("Contribution Details");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");

            sb.append("<table width='100%'>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			
			sb.append("<table border='1' width='100%' cellspacing='0' cellpadding='7' bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Employee Contribution</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Wage Period</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Total Monthly Wages</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Number Of Days Wages paid</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			if(esicIdResponse.getPayload().getResult().getContributionDetails() !=null ){
				if(esicIdResponse.getPayload().getResult().getContributionDetails().size()>0){
					for(ESICIdResultContributionDetails result: esicIdResponse.getPayload().getResult().getContributionDetails()){
						sb.append("<tr>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getEmployeeConrtibution()!=null?result.getEmployeeConrtibution():"");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getWagePeriod()!=null?result.getWagePeriod():"");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getTotalMonthlyWages()!=null?result.getTotalMonthlyWages():"");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getNumberOfDaysWagesPaid()!=null?result.getNumberOfDaysWagesPaid():"");sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
						

					}
				}
			}
			sb.append("</table>");

			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			
			
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ esicIdResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayEsicId || error:: "+e);
			return null;

		}

	}

	public KarzaDto getPdfByteArrayBankAccount(BankAccountResponse bankAccountResponse,	BankAccountRequest bankAccountRequest) {
		try{
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+"BankAccount_"+ bankAccountResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Bank Account</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Account Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Name</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Type</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Bank Account");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getAccountNumber());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse)!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmName()):"");sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse.getPayload().getResult().getFirmType())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmType()):"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'  >");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Account No</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getAccountNumber());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			

			
			/*sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Account No</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getAccountNumber());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");*/
			
			if(bankAccountResponse.getPayload().getResult()!=null)
			{
			
			/* for (ICSIMemberShipResult icsiMemberShipResult :icsiMemberShipResponse.getPayload().getResult()) 
			  {	
				*/
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Account Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountRequest.getPayload().getAccountNumber())!=null?(bankAccountRequest.getPayload().getAccountNumber()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Account Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getAccountName())!=null?(bankAccountResponse.getPayload().getResult().getAccountName()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bank Response</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getBankResponse())!=null?bankAccountResponse.getPayload().getResult().getBankResponse():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>IFSC Code</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getIfsc())!=null?(bankAccountResponse.getPayload().getResult().getIfsc()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bank Transaction Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getBankTxnStatus())!=null?(bankAccountResponse.getPayload().getResult().getBankTxnStatus()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			}
			//}
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+"BankAccount_"+ bankAccountResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayBankAccount || error:: "+e);
			return null;

		}
	}


	public  KarzaDto getPdfByteArrayElecQC(ElectricityResponse2 elecRes, ElectricalRequest2 request,String pdfName) {
		try{
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ elecRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
            sb.append("<head>");
            sb.append("<title>");sb.append("</title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<strong><u>Electricity Report</u></strong>");sb.append("</font>");
            sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Electricity");sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsumer_id());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
			
			
			if(request.getPayload()!=null)
			{
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consumer Id</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Service Provider</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsumer_id()==null?"NA":request.getPayload().getConsumer_id());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getService_provider()==null?"NA":request.getPayload().getService_provider());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(request.getPayload().getConsent()==null?"NA":request.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
			}
            
			if(elecRes.getPayload()!=null && elecRes.getPayload().getResult()!=null)
			{
            sb.append("<table width='100%' cellpading='0'>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
            sb.append("Detailed Report");
            sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table width='100%'>");

            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Consumer Name</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getConsumer_name()==null?"":elecRes.getPayload().getResult().getConsumer_name());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getAddress()==null?"":elecRes.getPayload().getResult().getAddress());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Mobile Number</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getMobile_number()==null?"":elecRes.getPayload().getResult().getMobile_number());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email Address</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getEmail_address()==null?"":elecRes.getPayload().getResult().getEmail_address());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Consumer Number</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getConsumer_number()==null?"":elecRes.getPayload().getResult().getConsumer_number());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Number</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getBill_no()==null?"":elecRes.getPayload().getResult().getBill_no());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Date</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getBill_date()==null?"":elecRes.getPayload().getResult().getBill_date());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Due Date</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getBill_due_date()==null?"":elecRes.getPayload().getResult().getBill_due_date());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Amount</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getBill_amount()==null?"":elecRes.getPayload().getResult().getBill_amount());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Toatl Amount</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getTotal_amount()==null?"":elecRes.getPayload().getResult().getTotal_amount());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Amount Payable</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            sb.append("<font size='0.5' face='arial'>");sb.append(elecRes.getPayload().getResult().getAmount_payable()==null?"":elecRes.getPayload().getResult().getAmount_payable());sb.append("</font>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            
            

            sb.append("</table>");
            sb.append("</td>");

            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("</table>");
			}
		sb.append("</body>");
		sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ elecRes.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayElecQC || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayDlQC(DlResponse2 dlRes, DlRequest2 dlRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}

			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ dlRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			try
			{
				if(dlRes.getPayload().getResult().getImg()!=null 
						&& !dlRes.getPayload().getResult().getImg().equalsIgnoreCase(""))
				{
					byte[] data = Base64.decode(dlRes.getPayload().getResult().getImg());
					OutputStream stream = null;
					try{
						stream = new FileOutputStream(filePath+File.separator+dlRes.getPayload().getRequest_id()+".jpeg");
						stream.write(data);
					}finally{
						if(stream!=null){
							stream.close();
						}
					}
					newPath=filePath+File.separator+dlRes.getPayload().getRequest_id()+".jpeg";
				}
			}
			catch(Exception e)
			{
			}

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Driving License Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0.1' width='100%' 'bgcolor='#337BB6'' cellpadding='1'>");
			sb.append("<tr>");
			if(newPath != null){

				sb.append("<td width='11%' align='center' bgcolor='black'>");
				sb.append("<img src='"+newPath+"' width='60' height='70' style='border-radius: 70%;' align='center'>");
				sb.append("</td>");
			}
			sb.append("<td width='80%' valign='center'>");
			sb.append("<table border='0' width='100%' cellpadding='2'>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='left'>");
			sb.append("<font face='arial' size='1' font color=\"#337BB6\">");
			sb.append("<b>Customer name :</b>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='80%' align='left'>");
			sb.append("<font face='arial' size='1'>");
			//sb.append(dlRequest.getPayload().getName());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='20%' align='left'>");
			sb.append("<font face='arial' size='1' font color=\"#337BB6\">");
			sb.append("<b>Date of Birth :</b>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='80%' align='left'>");
			sb.append("<font face='arial' size='1'>");sb.append(dlRequest.getPayload().getDob());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name Match/Mismatch</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Confidence Score</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Driving License");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getDl_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
		//	sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getName().getMatch());sb.append("</font>");sb.append("</td>");
			//.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getName().getScore());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Driving License No.</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>DOB</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getDl_no());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getDob());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			
			
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Detailed Report");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getName());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Father-Husband</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getFather_husband());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>DOB</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getDob());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Blood Group</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getBlood_group());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getAddress());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");
			sb.append("<b>RTO</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Date of Issue</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getIssue_date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");

			String nvtf=null;
			String nvtt=null;

			if(dlRes.getPayload().getResult().getValidity().getNon_transport() != null){
				if(dlRes.getPayload().getResult().getValidity().getNon_transport().contains("to"))	{
					String[] parts = dlRes.getPayload().getResult().getValidity().getNon_transport().split("to");
					nvtf = parts[0]; // 004
					nvtt = parts[1];
				}else{
					nvtf=" ";
					nvtt=" ";
				}
			}else{
				nvtf=" ";
				nvtt=" ";
			}


			String vtf=null;
			String vtt=null;

			if(dlRes.getPayload().getResult().getValidity().getTransport() != null){
				if(dlRes.getPayload().getResult().getValidity().getTransport().contains("to"))	{
					String[] parts = dlRes.getPayload().getResult().getValidity().getTransport().split("to");
					vtf = parts[0]; // 004
					vtt = parts[1];
				}else{
					vtf=" ";
					vtt=" ";
				}
			}else{
				vtf=" ";
				vtt=" ";
			}

			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Non Transport Validity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getValidity().getNon_transport());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Non Transport Valid To</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(nvtt);sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Transport Validity</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getValidity().getTransport()); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Transport Valid Till</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(vtt); sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			sb.append("<br>");


			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Validity Details");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<th bgcolor='#b3e6ff' align='center' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Class of Vehicle</b>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<b>Issue Date/Issuing Authority</b>");sb.append("</font>");sb.append("</th>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table border='0.5' width='100%' cellspacing='0' cellpadding='0' bordercolor='#000000'  >");
			sb.append("<tr>");
			sb.append("<td  bgcolor='#000000' height='1'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			if(dlRes.getPayload().getResult().getCov_details() !=null){
				if(dlRes.getPayload().getResult().getCov_details().length !=0){
					for(int i=0;i<dlRes.getPayload().getResult().getCov_details().length;i++){	

						sb.append("<tr>");
						sb.append("<td align='center' width='30%'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getCov_details()[i].getCov());sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRes.getPayload().getResult().getCov_details()[i].getIssue_date());sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
					}
				}
			}
			sb.append("</table>");

			sb.append("</body>");
			sb.append("</html>");



			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+pdfName+ dlRes.getHeader().getCorrelationId()+".pdf");
			try{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}catch(Exception e){
			}
			return dto;


		} catch (Exception e) {
			return null;

		}

	}


	public  KarzaDto getPdfByteArrayTeleQC(TelephoneResponse2 teleRes, TelephoneRequest2 teleRequest,String pdfName) {
		KarzaDto dto=new KarzaDto();
		try {
			String logoPath=null;
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}

			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ teleRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();


			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>TELEPHONE Bill Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			/*sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name Match/Mismatch</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Confidence Score</strong>");sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Telephone");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRequest.getPayload().getTel_no()!=null?teleRequest.getPayload().getTel_no():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRes.getMsgInfo().getStatus()!=null?teleRes.getMsgInfo().getStatus():"");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRes.getMsgInfo().getMessage()!=null?teleRes.getMsgInfo().getMessage():"");sb.append("</font>");sb.append("</td>");
			/*sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getName().getMatch());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(fssaiLicenceResponse.getPayload().getResult().getName().getScore());sb.append("</font>");sb.append("</td>");*/
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			sb.append("<table width='100%'  >");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Telephone No</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRequest.getPayload().getTel_no()!=null?teleRequest.getPayload().getTel_no():"");sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(teleRequest.getPayload().getConsent()!=null?teleRequest.getPayload().getConsent():"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleRes.getPayload().getResult().getName()!=null?teleRes.getPayload().getResult().getName():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Telephone Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleRes.getPayload().getResult().getTelephoneNo()!=null?teleRes.getPayload().getResult().getTelephoneNo():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			
			sb.append("<td  bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Category</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleRes.getPayload().getResult().getCategory()!=null?teleRes.getPayload().getResult().getCategory():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			
			sb.append("<td  bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Installation Type</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleRes.getPayload().getResult().getInstallation_Type()!=null?teleRes.getPayload().getResult().getInstallation_Type():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(teleRes.getPayload().getResult().getAddress()!=null?teleRes.getPayload().getResult().getAddress():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			

			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			
			
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ teleRes.getHeader().getCorrelationId()+".pdf");
			return dto;
		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayTeleQC || error :: "+e);
			return null;

		}
	}


	public  KarzaDto getPdfByteArrayLpgQC(LpgResponse2 lpgRes, LpgRequest2 lpgRequest) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+"LPGV3_"+ lpgRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>LPG Bill Report</strong></u></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Report Summary</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Name</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Number</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Source Status</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status Message</strong></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>LPG</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(lpgRequest.getPayload().getLpg_id()==null?"NA":lpgRequest.getPayload().getLpg_id())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(lpgRes.getMsgInfo().getStatus()==null?"NA":lpgRes.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(lpgRes.getPayload().getStatus_msg()==null?"NA":lpgRes.getPayload().getStatus_msg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			if (lpgRequest!=null && lpgRequest.getPayload()!=null) {
				sb.append("<br>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>LPG Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consent</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRequest.getPayload().getLpg_id() == null ? "NA"
						: lpgRequest.getPayload().getLpg_id());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRequest.getPayload().getConsent() == null ? "NA" : lpgRequest.getPayload().getConsent());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
			if (lpgRes!=null && lpgRes.getPayload()!=null && lpgRes.getPayload().getResult()!=null) {
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>LPG Connection Status</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getStatus()==null?"NA":lpgRes.getPayload().getResult().getStatus());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Approximate Subsidy Amount Availed</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getApproximateSubsidyAvailed()==null?"NA":lpgRes.getPayload().getResult().getApproximateSubsidyAvailed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Subsidized Refill Consumed (Yearly)</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getSubsidizedRefillConsumed()==null?"NA":lpgRes.getPayload().getResult().getSubsidizedRefillConsumed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Pincode</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getPin()==null?"NA":lpgRes.getPayload().getResult().getPin());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Email Id</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerEmail()==null?"NA":lpgRes.getPayload().getResult().getConsumerEmail());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Distributor Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getDistributorCode()==null?"NA":lpgRes.getPayload().getResult().getDistributorCode());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Bank Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getBankName()==null?"NA":lpgRes.getPayload().getResult().getBankName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>IFSC Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getIfscCode()==null?"NA":lpgRes.getPayload().getResult().getIfscCode());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>City/Town</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getCity()==null?"NA":lpgRes.getPayload().getResult().getCity());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Aadhar Number.(Last 4 digit)</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getAadhaarNo()==null?"NA":lpgRes.getPayload().getResult().getAadhaarNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Contact Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerContact()==null?"NA":lpgRes.getPayload().getResult().getConsumerContact());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Distributor Address</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getDistributorAddress()==null?"NA":lpgRes.getPayload().getResult().getDistributorAddress());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer/Owner Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerName()==null?"NA":lpgRes.getPayload().getResult().getConsumerName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerNo()==null?"NA":lpgRes.getPayload().getResult().getConsumerNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Distributor Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getDistributorName()==null?"NA":lpgRes.getPayload().getResult().getDistributorName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Bank Account Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getBankAccountNo()==null?"NA":lpgRes.getPayload().getResult().getBankAccountNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Given Up Subsidy</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getGivenUpSubsidy()==null?"NA":lpgRes.getPayload().getResult().getGivenUpSubsidy());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Address</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerAddress()==null?"NA":lpgRes.getPayload().getResult().getConsumerAddress());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Last Booking Date</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLastBookingDate()==null?"NA":lpgRes.getPayload().getResult().getLastBookingDate());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Total Refill Consumed(Yearly)</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getTotalRefillConsumed()==null?"NA":lpgRes.getPayload().getResult().getTotalRefillConsumed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+"LPGV3_"+ lpgRes.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayLpgQC || error:: "+e);
			return null;

		}
	}

	public  KarzaDto getPdfByteArrayLpgIdentifier(LpgIdentificationResponse lpgRes, LpgIdentificationRequest lpgRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ lpgRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>LPG ID Identifier Report</u></strong>");
			sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("LPG");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRequest.getPayload().getInput()==null?"NA":lpgRequest.getPayload().getInput());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getMsgInfo().getStatus()==null?"NA":lpgRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getStatus_msg()==null?"NA":lpgRes.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");

			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Input Data");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Input</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRequest.getPayload().getInput()==null?"NA":lpgRequest.getPayload().getInput());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Consent</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRequest.getPayload().getConsent()==null?"NA":lpgRequest.getPayload().getConsent());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			

			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");

			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Detailed Report");
			sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Lpg Id</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append(lpgRes.getPayload().getResult().getLpg_id()==null?"NA":lpgRes.getPayload().getResult().getLpg_id());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");
			sb.append("</body>");
			sb.append("</html>");

			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ lpgRes.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayLpgIdentifier || error:: "+e.getMessage());
			return null;

		}
	}


	public static List<GSTAuthAddrBean>  getgstBeanDataList(String s[]){
		logger.info("PDF Converter ||getgstBeanDataList for GST AUTH || START");
		List<GSTAuthAddrBean> gstBeanList=new ArrayList<GSTAuthAddrBean>();
		int counter=2;
		try{
			while(counter<s.length-2){
				GSTAuthAddrBean gstBean=new GSTAuthAddrBean();
				gstBean.setEmail(s[counter]);
				gstBean.setAddress(s[counter+=2]);
				gstBean.setAddress2(s[counter+=2]);
				gstBean.setMobile(s[counter+=2]);
				gstBean.setNtr(s[counter+=2]);
				gstBean.setLastUpdatedDate(s[counter+=2]);
				counter+=4;
				gstBeanList.add(gstBean);
			}

		}catch(Exception ee){
			logger.error("PDF Converter ||getgstBeanDataList for GST AUTH || EXCEPTION :: "+ee.getMessage());
		}
		logger.info("PDF Converter ||getgstBeanDataList for GST AUTH || END");
		return gstBeanList;
	}
	public KarzaDto getPdfByteArrayLpgQC(LpgResponse2 lpgRes, LpgRequest2 lpgRequest, String pdfName)
	{
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}


			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ lpgRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>"); 
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>LPG Bill Report</strong></u></font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Report Summary</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Name</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Document Number</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Source Status</strong></font>");
			sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Status Message</strong></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'><font size='0.5' face='arial'>LPG</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(lpgRequest.getPayload().getLpg_id()==null?"NA":lpgRequest.getPayload().getLpg_id())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(lpgRes.getMsgInfo().getStatus()==null?"NA":lpgRes.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(lpgRes.getPayload().getStatus_msg()==null?"NA":lpgRes.getPayload().getStatus_msg())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			if (lpgRequest!=null && lpgRequest.getPayload()!=null) {
				sb.append("<br>");
				sb.append("<table width='100%'>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<br/>");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'>");
				sb.append("<b>");
				sb.append("Input Data");
				sb.append("</b>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='7'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>LPG Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consent</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRequest.getPayload().getLpg_id() == null ? "NA"
						: lpgRequest.getPayload().getLpg_id());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRequest.getPayload().getConsent() == null ? "NA" : lpgRequest.getPayload().getConsent());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='20'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
			sb.append("<br>");
			
			if (lpgRes!=null && lpgRes.getPayload()!=null && lpgRes.getPayload().getResult()!=null) {
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>LPG Connection Status</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getStatus()==null?"NA":lpgRes.getPayload().getResult().getStatus());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Approximate Subsidy Amount Availed</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getApproximateSubsidyAvailed()==null?"NA":lpgRes.getPayload().getResult().getApproximateSubsidyAvailed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Subsidized Refill Consumed (Yearly)</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getSubsidizedRefillConsumed()==null?"NA":lpgRes.getPayload().getResult().getSubsidizedRefillConsumed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Pincode</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getPin()==null?"NA":lpgRes.getPayload().getResult().getPin());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Email Id</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerEmail()==null?"NA":lpgRes.getPayload().getResult().getConsumerEmail());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Distributor Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getDistributorCode()==null?"NA":lpgRes.getPayload().getResult().getDistributorCode());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Bank Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getBankName()==null?"NA":lpgRes.getPayload().getResult().getBankName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>IFSC Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getIfscCode()==null?"NA":lpgRes.getPayload().getResult().getIfscCode());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>City/Town</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getCity()==null?"NA":lpgRes.getPayload().getResult().getCity());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Aadhar Number.(Last 4 digit)</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getAadhaarNo()==null?"NA":lpgRes.getPayload().getResult().getAadhaarNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Contact Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerContact()==null?"NA":lpgRes.getPayload().getResult().getConsumerContact());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Distributor Address</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getDistributorAddress()==null?"NA":lpgRes.getPayload().getResult().getDistributorAddress());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer/Owner Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerName()==null?"NA":lpgRes.getPayload().getResult().getConsumerName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerNo()==null?"NA":lpgRes.getPayload().getResult().getConsumerNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Distributor Name</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getDistributorName()==null?"NA":lpgRes.getPayload().getResult().getDistributorName());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Bank Account Number</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getBankAccountNo()==null?"NA":lpgRes.getPayload().getResult().getBankAccountNo());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Given Up Subsidy</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getGivenUpSubsidy()==null?"NA":lpgRes.getPayload().getResult().getGivenUpSubsidy());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Consumer Address</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getConsumerAddress()==null?"NA":lpgRes.getPayload().getResult().getConsumerAddress());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Last Booking Date</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getLastBookingDate()==null?"NA":lpgRes.getPayload().getResult().getLastBookingDate());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='40%'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Total Refill Consumed(Yearly)</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(lpgRes.getPayload().getResult().getTotalRefillConsumed()==null?"NA":lpgRes.getPayload().getResult().getTotalRefillConsumed());
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ lpgRes.getHeader().getCorrelationId()+".pdf");

			return dto;

		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayLpgQC || error:: "+e);
			return null;
		}
	}





	public KarzaDto getPdfByteArrayBankAccount(BankAccountResponse bankAccountResponse,	BankAccountRequest bankAccountRequest, String pdfName)
	{
		try{
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}
			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ bankAccountResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Bank Account</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Account Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Name</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Firm Type</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Bank Account");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getAccountNumber());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse)!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmName()):"");sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((icsiMemberShipResponse.getPayload().getResult().getFirmType())!=null?(icwaiFirmAuthResponse.getPayload().getResult().getFirmType()):"");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Account No</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Unique ID</strong>");sb.append("</font>");sb.append("</td>");
			//sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getAccountNumber());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(rcAuthRequest.getPayload().getUniqueId());sb.append("</font>");sb.append("</td>");
			//sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(dlRequest.getPayload().getName());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(bankAccountRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(bankAccountResponse.getPayload().getResult()!=null)
			{
			
			/* for (ICSIMemberShipResult icsiMemberShipResult :icsiMemberShipResponse.getPayload().getResult()) 
			  {	
				*/
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			
			
			sb.append("Detailed Report");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");

			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='1' width='100%' cellspacing='0' cellpadding='7'  bordercolor='#ccc'>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Account Number</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountRequest.getPayload().getAccountNumber())!=null?(bankAccountRequest.getPayload().getAccountNumber()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Account Name</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getAccountName())!=null?(bankAccountResponse.getPayload().getResult().getAccountName()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bank Response</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getBankResponse())!=null?bankAccountResponse.getPayload().getResult().getBankResponse():"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>IFSC Code</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getIfsc())!=null?(bankAccountResponse.getPayload().getResult().getIfsc()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
			sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bank Transaction Status</b>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
			sb.append("</td>");
			sb.append("<td align='left'>");
			sb.append("<font size='0.5' face='arial'>");sb.append((bankAccountResponse.getPayload().getResult().getBankTxnStatus())!=null?(bankAccountResponse.getPayload().getResult().getBankTxnStatus()):"");sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</td>");

			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("</table>");

			}
			//}
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ bankAccountResponse.getHeader().getCorrelationId()+".pdf");

			return dto;

		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayBankAccount || error:: "+e);
			return null;

		}
	}

	public KarzaDto getPdfByteArrayEmployerLookup(EmployerLookupResponse employerLookupResponse, EmolpyerLookupRequest employerLookupRequest, String pdfName) 
	{
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try
			{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}
			catch(Exception e)
			{

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ employerLookupResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#005580'><b><u>Employer Lookup Report</u></b></font>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(employerLookupRequest.getPayload()!=null && employerLookupResponse.getMsgInfo()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Document Summary</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Document Name</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Document Number</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Source Status</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Status Message</b></font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>Employer Lookup</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(employerLookupRequest.getPayload().getUan()==null?"NA": employerLookupRequest.getPayload().getUan())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(employerLookupResponse.getMsgInfo().getStatus()==null?"NA":employerLookupResponse.getMsgInfo().getStatus())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(employerLookupResponse.getPayload().getStatus_msg()==null?"NA" : employerLookupResponse.getPayload().getStatus_msg())+"</font>");sb.append("</td>");
	
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			sb.append("<br>");
			if(employerLookupRequest.getPayload()!=null && employerLookupResponse.getMsgInfo()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Input Data</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>UAN</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Consent</b></font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(employerLookupRequest.getPayload().getUan()==null?"NA": employerLookupRequest.getPayload().getUan())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(employerLookupRequest.getPayload().getConsent()==null?"NA": employerLookupRequest.getPayload().getConsent())+"</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			
			sb.append("<br>");
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
			sb.append("<tr>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append(" <b>Membership ID</b>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<b>Settlement</b>");sb.append("</font>");sb.append("</th>");
			sb.append("<th bgcolor='#b3e6ff' align='center' >");sb.append("<font size='0.5' face='arial'>");sb.append("<b>EMPLOYER ORGANIZATION</b>");sb.append("</font>");sb.append("</th>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table border='0.5' width='100%' cellspacing='0' cellpadding='0' bordercolor='#000000'  >");
			sb.append("<tr>");
			sb.append("<td  bgcolor='#000000' height='1'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				if(employerLookupResponse.getPayload().getResult() !=null )
				{
				if(employerLookupResponse.getPayload().getResult().size()>0)
				{
						for(EmployerLookupResult result: employerLookupResponse.getPayload().getResult())
						{
						sb.append("<tr>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getMembershipId()==null?"NA" : result.getMembershipId());sb.append("</b>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getSettelment()==null?"NA" : result.getSettelment());sb.append("</b>");sb.append("</font>");sb.append("</td>");
						sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(result.getEstName()==null?"NA":result.getEstName());sb.append("</b>");sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
					}
				}
			}
			sb.append("</table>");
		
			sb.append("</body>");
			sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ employerLookupResponse.getHeader().getCorrelationId()+".pdf");

			return dto;

		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayEmployerLookup || error:: "+e);
			return null;
		}
	}

	@Override
	public KarzaDto getPdfByteArrayWebsiteDomain(WebsiteDomainResponse websiteDomainResponse,
			WebsiteDomainRequest websiteDomainRequest, String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try
			{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}
			catch(Exception e)
			{

			}


			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);

			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ websiteDomainResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();	
			PdfWriter.getInstance(document, bStream);document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#005580'><b><u>Website Domain Authentication Report</u></b></font>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");
		
			if(websiteDomainRequest.getPayload()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Document Summary</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Document Name</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Document Number</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Source Status</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Status Message</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>Website Domain</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(websiteDomainRequest.getPayload().getDomain()==null?"NA" : websiteDomainRequest.getPayload().getDomain())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(websiteDomainResponse.getMsgInfo().getStatus()==null?"NA" : websiteDomainResponse.getMsgInfo().getStatus())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(websiteDomainResponse.getPayload().getStatus_msg()==null?"NA" : websiteDomainResponse.getPayload().getStatus_msg())+"</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
			
			sb.append("<br>");

			if(websiteDomainRequest.getPayload().getDomain()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Input Data</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Domain</b></font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+websiteDomainRequest.getPayload().getDomain()+"</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
			
			sb.append("<br>");
			
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
 
			if(websiteDomainResponse!=null && !websiteDomainResponse.equals(""))
			{
				WebsiteDomainPayload websitepayload = websiteDomainResponse.getPayload();
				if(websitepayload!=null && !websitepayload.equals(""))
				{
					WebsiteDomainResult websiteResult = websitepayload.getResult();
					if(websiteResult!=null && !websiteResult.equals(""))
					{
						sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
						
						sb.append("<tr>");
						sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Domain Registry Last Updated Date</b></font></td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getUpdateDate()==null?"NA" : websiteResult.getUpdateDate())+"</font></td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Domain Expiry Date</b></font></td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getExpirationDate()==null?"NA":websiteResult.getExpirationDate())+"</font></td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Domain Creation Date</b></font></td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getCreationDate()==null?"NA":websiteResult.getCreationDate())+"</font></td>");
						sb.append("</tr>");
						sb.append("</table>");
						

						if(websiteResult.getDomain()!=null && !websiteResult.getDomain().equals(""))
						{
							sb.append("<table width='100%'>");
							sb.append("<tr>");
							sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Domain Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td>");

							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='center' height='20'>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");
							
							sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Name</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getDomain().getName()==null?"NA":websiteResult.getDomain().getName())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Id</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getDomain().getId()==null?"NA":websiteResult.getDomain().getId())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("</table>");
						}
						
						if(websiteResult.getAdmin()!=null && !websiteResult.getAdmin().equals(""))
						{
							sb.append("<table width='100%'>");
							sb.append("<tr>");
							sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Domain Administrator Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td>");

							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='center' height='20'>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");
							
							sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>City</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getCity()==null?"NA":websiteResult.getAdmin().getCity())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Fax</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getFax()==null?"NA" :websiteResult.getAdmin().getFax())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Name</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getName()==null?"NA" : websiteResult.getAdmin().getName())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Country</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getCountry()==null?"NA" :websiteResult.getAdmin().getCountry())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>State/Province</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getStateProvince()==null?"NA":websiteResult.getAdmin().getStateProvince())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Phone Number</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getPhone()==null?"NA":websiteResult.getAdmin().getPhone())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Street</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getStreet()==null?"NA":websiteResult.getAdmin().getStreet())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Organization</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getOrganization()==null?"NA":websiteResult.getAdmin().getOrganization())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Postal</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getPostal()==null?"NA":websiteResult.getAdmin().getPostal())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Email Id</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getAdmin().getEmail()==null?"NA":websiteResult.getAdmin().getEmail())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("</table>");
						}
						
						if(websiteResult.getTech()!=null && !websiteResult.getTech().equals(""))
						{
							sb.append("<table width='100%'>");
							sb.append("<tr>");
							sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Domain Tech Contact Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td>");

							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='center' height='20'>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");
							
							sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>City</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getCity()==null?"NA":websiteResult.getTech().getCity())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Fax</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getFax()==null?"NA":websiteResult.getTech().getFax())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Name</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getName()==null?"NA":websiteResult.getTech().getName())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Country</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getCountry()==null?"NA":websiteResult.getTech().getCountry())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>State/Province</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getStateProvince()==null?"NA":websiteResult.getTech().getStateProvince())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Phone Number</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getPhone()==null?"NA" :websiteResult.getTech().getPhone())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Street</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getStreet()==null?"NA" :websiteResult.getTech().getStreet())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Organization</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getOrganization()==null?"NA" :websiteResult.getTech().getOrganization())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Postal</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getPostal()==null?"Na" : websiteResult.getTech().getPostal())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Email Id</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getTech().getEmail()==null?"NA"  :websiteResult.getTech().getEmail())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("</table>");
						}
						
						if(websiteResult.getRegistry()!=null && !websiteResult.getRegistry().equals(""))
						{
							if(!websiteResult.getRegistry().getPhone().equals("") || !websiteResult.getRegistry().getId().equals("") 
									|| !websiteResult.getRegistry().getEmail().equals("") || !websiteResult.getRegistry().getExpiry().equals(""))
								{
								sb.append("<table width='100%'>");
								sb.append("<tr>");
								sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
								sb.append("</tr>");
								sb.append("<tr>");
								sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Domain Registry Contact Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
								sb.append("</tr>");
								sb.append("<tr>");
								sb.append("<td>");

								sb.append("</td>");
								sb.append("</tr>");
								sb.append("<tr>");
								sb.append("<td align='center' height='20'>");sb.append("</td>");
								sb.append("</tr>");
								sb.append("</table>");
								
								sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
								
								sb.append("<tr>");
								sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'>Phone Number</font></td>");
								sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
								sb.append("<td align='left'><font size='0.5' face='arial'>"+websiteResult.getRegistry().getPhone()+"</font></td>");
								sb.append("</tr>");
								
								sb.append("<tr>");
								sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'>Expiry Date</font></td>");
								sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
								sb.append("<td align='left'><font size='0.5' face='arial'>"+websiteResult.getRegistry().getExpiry()+"</font></td>");
								sb.append("</tr>");
								
								sb.append("<tr>");
								sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'>Identification Number</font></td>");
								sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
								sb.append("<td align='left'><font size='0.5' face='arial'>"+websiteResult.getRegistry().getId()+"</font></td>");
								sb.append("</tr>");
								
								sb.append("<tr>");
								sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'>Email Id</font></td>");
								sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
								sb.append("<td align='left'><font size='0.5' face='arial'>"+websiteResult.getRegistry().getEmail()+"</font></td>");
								sb.append("</tr>");
								
								
								sb.append("</table>");
								}
							
							
						}
						
						if(websiteResult.getRegistrar()!=null && !websiteResult.getRegistrar().equals(""))
						{
							if(!websiteResult.getRegistrar().getPhone().equals("") || !websiteResult.getRegistrar().getId().equals("") 
								|| !websiteResult.getRegistrar().getEmail().equals(""))
							{
								sb.append("<table width='100%'>");
								sb.append("<tr>");
								sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
								sb.append("</tr>");
								sb.append("<tr>");
								sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Domain Registrar Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
								sb.append("</tr>");
								sb.append("<tr>");
								sb.append("<td>");

								sb.append("</td>");
								sb.append("</tr>");
								sb.append("<tr>");
								sb.append("<td align='center' height='20'>");sb.append("</td>");
								sb.append("</tr>");
								sb.append("</table>");
								
								sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
								
								sb.append("<tr>");
								sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'>Phone Number</font></td>");
								sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
								sb.append("<td align='left'><font size='0.5' face='arial'>"+websiteResult.getRegistrar().getPhone()+"</font></td>");
								sb.append("</tr>");
								
								sb.append("<tr>");
								sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'>Email Id</font></td>");
								sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
								sb.append("<td align='left'><font size='0.5' face='arial'>"+websiteResult.getRegistrar().getEmail()+"</font></td>");
								sb.append("</tr>");
								
								sb.append("<tr>");
								sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'>Identification Number</font></td>");
								sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
								sb.append("<td align='left'><font size='0.5' face='arial'>"+websiteResult.getRegistrar().getId()+"</font></td>");
								sb.append("</tr>");
								
								sb.append("</table>");
							}
							
						}
						
						if(websiteResult.getNameserver()!=null && !websiteResult.getNameserver().equals(""))
						{
							sb.append("<table width='100%'>");
							sb.append("<tr>");
							sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Domain Host Server DNS Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td>");

							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='center' height='20'>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");
							
							sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Domain DNS or IP</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getNameserver().getName()==null?"NA":websiteResult.getNameserver().getName())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("</table>");
						}
						
						
						if(websiteResult.getRegistrant()!=null && !websiteResult.getRegistrant().equals(""))
						{
							sb.append("<table width='100%'>");
							sb.append("<tr>");
							sb.append("<td align='center' height='10'>");sb.append("</td> <br />");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'>");sb.append("<b>");sb.append("Domain Registrant Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td>");

							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='center' height='20'>");sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");
							
							sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>City</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getCity()==null?"NA" :websiteResult.getRegistrant().getCity())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Fax</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getFax()==null?"NA":websiteResult.getRegistrant().getFax())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Name</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getName()==null?"NA":websiteResult.getRegistrant().getName())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Country</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getCountry()==null?"NA":websiteResult.getRegistrant().getCountry())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>State Province/s</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getStateProvince()==null?"NA":websiteResult.getRegistrant().getStateProvince())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Phone</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getPhone()==null?"NA":websiteResult.getRegistrant().getPhone())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Street</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getPhone()==null?"NA":websiteResult.getRegistrant().getPhone())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Organization</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getOrganization()==null?"NA":websiteResult.getRegistrant().getOrganization())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Postal</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getPostal()==null?"NA":websiteResult.getRegistrant().getPostal())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td bgcolor='b3e6ff' align='left' width='40%'><font size='0.5' face='arial'><b>Email Id</b></font></td>");
							sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(websiteResult.getRegistrant().getEmail()==null?"NA":websiteResult.getRegistrant().getEmail())+"</font></td>");
							sb.append("</tr>");
							
							sb.append("</table>");
						}
					}
				}
			}
			
			sb.append("</body>");
			sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ websiteDomainResponse.getHeader().getCorrelationId()+".pdf");

			return dto;

		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayWebsiteDomain || error:: "+e);
			return null;
		}
	}

	public KarzaDto getPdfByteArrayUanLookup(UanLookupResponse uanLookupResponse, UanLookupRequest uanLookupRequest, String pdfName)
	{
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try
			{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}
			catch(Exception e)
			{

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ uanLookupResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");

			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#005580'><b><u>EPF UAN Lookup Report</u></b></font>");sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>&nbsp;</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(uanLookupRequest.getPayload()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Document Summary</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Document Name</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Document Number</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Source Status</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Status Message</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>EPF UAN Lookup</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(uanLookupRequest.getPayload().getMobile()==null?"NA": uanLookupRequest.getPayload().getMobile())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(uanLookupResponse.getMsgInfo().getStatus()==null?"NA" :uanLookupResponse.getMsgInfo().getStatus())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(uanLookupResponse.getPayload().getStatus_msg()==null?"NA" : uanLookupResponse.getPayload().getStatus_msg())+"</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			
			
			if(uanLookupRequest.getPayload()!=null)
			{
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Input Data</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Mobile</b></font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'><b>Consent</b></font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(uanLookupRequest.getPayload().getMobile()==null?"NA": uanLookupRequest.getPayload().getMobile())+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+(uanLookupRequest.getPayload().getConsent()==null?"NA" : uanLookupRequest.getPayload().getConsent())+"</font>");sb.append("</td>");
	
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			}
			
			
			sb.append("<br>");
			
			
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");sb.append("<font size='3' face='arial' color='#005580'><b>Detailed Report</b></font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			String arr[]=uanLookupResponse.getPayload().getResult().getUan();
			if(arr!=null){
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
				sb.append(" <tr>");
				sb.append("   <th bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial' ><b>UAN</b></font></th>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("  <td align='left'><font size='0.5' face='arial'>&nbsp;&nbsp;"+Arrays.toString(arr)+"</font></td>");
				sb.append("</tr>");
				sb.append("</table>");
				
			}
		
			sb.append("</body>");
			sb.append("</html>");
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ uanLookupResponse.getHeader().getCorrelationId()+".pdf");

			return dto;

		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayRCAuth || error:: "+e);
			return null;
		}
	}
	public KarzaDto getPdfByteArrayForm16Ouat(Form16QuatResponse form16QuatResponse, Form16QuatRequest form16QuatRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}
			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ form16QuatResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Form 16 Quaterly Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
//			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Form 16");sb.append("</font>");sb.append("</td>");
//			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(form16QuatRequest.getPayload()!=null && form16QuatRequest.getPayload().getConsumer_id()!=null && !("").equals(form16QuatRequest.getPayload().getConsumer_id())? form16QuatRequest.getPayload().getConsumer_id() : "NA");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(form16QuatResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(form16QuatResponse.getPayload()!=null && form16QuatResponse.getPayload().getStatus_msg()!=null && !("").equals(form16QuatResponse.getPayload().getStatus_msg())? form16QuatResponse.getPayload().getStatus_msg() : "NA");sb.append("</font>");sb.append("</td>");

			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>PAN number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>TAN number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Fiscal Year</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			
			sb.append("</tr>");
			
			if (form16QuatRequest!=null && !form16QuatRequest.equals(""))
			{
				Form16QuatPayload payload = form16QuatRequest.getPayload();
				if (payload!=null && !payload.equals(""))
				{
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getPan());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getTan());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getFiscal_year());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getConsent());sb.append("</font>");sb.append("</td>");
					
					sb.append("</tr>");
				}
				else
				{
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					
					sb.append("</tr>");
				}
			}
			
	
			
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			if (form16QuatResponse!=null && !form16QuatResponse.equals(""))
			{
				Form16QuatResponsePayload payload = form16QuatResponse.getPayload();
				if (payload!=null && !payload.equals("") && payload.getResult()!=null && !("").equals(payload.getResult()))
				{
					Form16QuatResult result = payload.getResult();
						sb.append("<table width='100%' cellpading='0'>");
						sb.append("<!--Details-->");
						sb.append("<tr>");
						sb.append("<td align='left'>");
						sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
						sb.append("Detailed Report");
						sb.append("</b>");sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td align='center' height='10'>");sb.append("</td>");
						sb.append("</tr>");
						sb.append("<!--Details-->");
						sb.append("<tr>");
						sb.append("<td>");
						
						sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");

						sb.append("<tr>");
						sb.append("<td  align='left' width='5%'><font size='0.5' face='arial'><i></i></font>");sb.append("</td>");
						sb.append("<td width='0.0'>&nbsp;</td>");
						sb.append("<td><font size='0.5' face='arial' color='red'><i>Quaterly Records Count For Next Fiscal :</i></font></td>");
						sb.append("</tr>");
						sb.append("</table>");
						
						
						sb.append("<table width='100%'>");

						sb.append("<tr>");
						sb.append("<td align='center' height='10'>");sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td>");
						sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
						
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>First Quarter</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(result.getQuarterly_records_count_for_next_fiscal().getQ1());sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");
						
						
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>Second Quarter</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(result.getQuarterly_records_count_for_next_fiscal().getQ2());sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>Third Quarter</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(result.getQuarterly_records_count_for_next_fiscal().getQ3());sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>Forth Quarter</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(result.getQuarterly_records_count_for_next_fiscal().getQ4());sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");					

						sb.append("</table>");
						sb.append("</td>");
						sb.append("</tr>");

						sb.append("</table>");
						sb.append("</td>");

						sb.append("</tr>");
						sb.append("<!--Details-->");
						sb.append("</table>");
					
				}
				else
				{sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				
				sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4' bordercolor='#ccc'>");

				sb.append("<tr>");
				sb.append("<td  align='left' width='5%'><font size='0.5' face='arial'><i></i></font>");sb.append("</td>");
				sb.append("<td width='0.0'>&nbsp;</td>");
				sb.append("<td><font size='0.5' face='arial' color='red'><i>Quaterly Records Count For Next Fiscal :</i></font></td>");
				sb.append("</tr>");
				sb.append("</table>");
				
				
				sb.append("<table width='100%'>");

				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>First Quarter</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Second Quarter</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Third Quarter</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Forth Quarter</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");					

				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");

				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");}
			}
			
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ form16QuatResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || etPdfByteArrayForm16Ouat || error:: "+e);
			return null;

		}
	}


	public KarzaDto getPdfByteArrayPng(PngResponse pngResponse, PngRequest pngRequest,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ pngResponse.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>PNG Authentication Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("PNG (Piped Natural Gas)");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pngRequest.getPayload()!=null && pngRequest.getPayload().getConsumer_id()!=null && !("").equals(pngRequest.getPayload().getConsumer_id())? pngRequest.getPayload().getConsumer_id() : "NA");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pngResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pngResponse.getPayload()!=null && pngResponse.getPayload().getStatus_msg()!=null && !("").equals(pngResponse.getPayload().getStatus_msg())? pngResponse.getPayload().getStatus_msg() : "NA");sb.append("</font>");sb.append("</td>");

			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consumer Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Business Partner number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Service Provider code</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			
			sb.append("</tr>");
			
			if (pngRequest!=null && !pngRequest.equals(""))
			{
				PngPayload payload = pngRequest.getPayload();
				if (payload!=null && !payload.equals(""))
				{
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pngRequest.getPayload().getConsumer_id());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pngRequest.getPayload().getBp_no());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pngRequest.getPayload().getService_provider());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pngRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
					
					sb.append("</tr>");
				}
				else
				{
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
				}
			}
			
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			if (pngResponse!=null && !pngResponse.equals(""))
			{
				PngResponsePayload payload = pngResponse.getPayload();
				if (payload!=null && !payload.equals("") && payload.getResult()!=null && !("").equals(payload.getResult()))
				{
					PngResult result = payload.getResult();
					sb.append("<table width='100%' cellpading='0'>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td align='left'>");
					sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
					sb.append("Detailed Report");
					sb.append("</b>");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table width='100%'>");

					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name of The Customer</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getCustomerName()==null?"NA":result.getCustomerName());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Number</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getBillNo()==null?"NA":result.getBillNo());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Due Date of The Bill</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getDueDate()==null?"NA":result.getDueDate());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Amount</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getBillAmount()==null?"NA":result.getBillAmount());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Customer's Mobile Number</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getMobile()==null?"NA":result.getMobile());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getCustomerAddress()==null?"NA":result.getCustomerAddress());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Date</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getBillDate()==null?"NA":result.getBillDate());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email Address</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append(result.getEmail()==null?"NA":result.getEmail());sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");		
				

					sb.append("</table>");
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("</table>");
					sb.append("</td>");

					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("</table>");
				}
				else
				{
					sb.append("<table width='100%' cellpading='0'>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td align='left'>");
					sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
					sb.append("Detailed Report");
					sb.append("</b>");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table width='100%'>");

					sb.append("<tr>");
					sb.append("<td align='center' height='10'>");sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td>");
					sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Name of The Customer</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Number</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Due Date of The Bill</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Amount</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Customer's Mobile Number</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Address</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Bill Date</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");
					
					sb.append("<tr>");
					sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("<b>Email Address</b>");sb.append("</font>");
					sb.append("</td>");
					sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
					sb.append("</td>");
					sb.append("<td align='left'>");
					sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
					sb.append("</td>");
					sb.append("</tr>");		
				

					sb.append("</table>");
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("</table>");
					sb.append("</td>");

					sb.append("</tr>");
					sb.append("<!--Details-->");
					sb.append("</table>");
				}
			}
			
			sb.append("</body>");
			sb.append("</html>");


			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ pngResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayPng || error:: "+e);
			return null;

		}
	}



	public KarzaDto getPdfByteArrayForm16Auth(Form16Response form16Response, Form16Request form16Request,String pdfName) {
		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+form16Response.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>Form 16 Authentication</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
//			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Form 16");sb.append("</font>");sb.append("</td>");
//			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(form16QuatRequest.getPayload()!=null && form16QuatRequest.getPayload().getConsumer_id()!=null && !("").equals(form16QuatRequest.getPayload().getConsumer_id())? form16QuatRequest.getPayload().getConsumer_id() : "NA");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(form16Response.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(form16Response.getPayload()!=null && form16Response.getPayload().getStatus_msg()!=null && !("").equals(form16Response.getPayload().getStatus_msg())? form16Response.getPayload().getStatus_msg() : "NA");sb.append("</font>");sb.append("</td>");

			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Details");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>PAN number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>TAN number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Fiscal Year</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Deduction Amount</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Certificate Number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
			
			sb.append("</tr>");
			
			if (form16Request!=null && !form16Request.equals(""))
			{
				Form16Payload payload = form16Request.getPayload();
				if (payload!=null && !payload.equals(""))
				{
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getPan());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getTan());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getFiscal_year());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getAmount());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getCert_no());sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(payload.getConsent());sb.append("</font>");sb.append("</td>");
					
					sb.append("</tr>");
				}
				else
				{
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");sb.append("</td>");
					sb.append("</tr>");
				}
			}
			
	
			
			sb.append("</table>");
			sb.append("<table border='0' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");
			
			if (form16Response!=null && !form16Response.equals(""))
			{
				Form16ResponsePayload payload = form16Response.getPayload();
				if (payload!=null && !payload.equals("") && payload.getResult()!=null && !("").equals(payload.getResult()))
				{
					Form16Result result = payload.getResult();
						sb.append("<table width='100%' cellpading='0'>");
						sb.append("<!--Details-->");
						sb.append("<tr>");
						sb.append("<td align='left'>");
						sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
						sb.append("Detailed Report");
						sb.append("</b>");sb.append("</font>");sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td align='center' height='10'>");sb.append("</td>");
						sb.append("</tr>");
						sb.append("<!--Details-->");
						sb.append("<tr>");
						sb.append("<td>");
						
						sb.append("<table width='100%'>");

						sb.append("<tr>");
						sb.append("<td align='center' height='10'>");sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td>");
						sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
						
						sb.append("<tr>");
						sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
						sb.append("<font size='0.5' face='arial'>");sb.append("<b>Result</b>");sb.append("</font>");
						sb.append("</td>");
						sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
						sb.append("</td>");
						sb.append("<td align='left'>");
						sb.append("<font size='0.5' face='arial'>");sb.append(result.getStatus());sb.append("</font>");
						sb.append("</td>");
						sb.append("</tr>");

						sb.append("</table>");
						sb.append("</td>");
						sb.append("</tr>");

						sb.append("</table>");
						sb.append("</td>");

						sb.append("</tr>");
						sb.append("<!--Details-->");
						sb.append("</table>");
					
				}
				else
				{sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
				sb.append("Detailed Report");
				sb.append("</b>");sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td>");
				
				sb.append("<table width='100%'>");

				sb.append("<tr>");
				sb.append("<td align='center' height='10'>");sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("<b>Result</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append("NA");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");

				sb.append("</table>");
				sb.append("</td>");

				sb.append("</tr>");
				sb.append("<!--Details-->");
				sb.append("</table>");}
			}
			
			sb.append("</body>");
			sb.append("</html>");




			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+form16Response.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayForm16Auth || error:: "+e);
			return null;

		}
	}

	@Override
	public KarzaDto getPdfByteArrayGSTIdentification(EPFAuthOTPResponse epfAuthOTPResponse,
			EPFAuthOTPRequest epfAuthOTPRequest, String pdfName) {

		try {
			String logoPath=null;
			KarzaDto dto=new KarzaDto();
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.karza.logo.qc");
			}catch(Exception e){

			}


			String kycPath=null;

			try{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}catch(Exception e){

			}

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ epfAuthOTPRequest.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();

			String newPath=null;
			sb.append("<html>");
            sb.append("<head>");
            sb.append("<title>");sb.append("</title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<strong><u>EPF OTP Authentication Report</u></strong>");sb.append("</font>");
            sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
			sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("<tr>");
            sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            
            if(epfAuthOTPRequest.getPayload()!=null && epfAuthOTPResponse.getMsgInfo()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("KYC Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("UAN No.");sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(epfAuthOTPRequest.getPayload().getUanid()==null?"":epfAuthOTPRequest.getPayload().getUanid());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(epfAuthOTPResponse.getMsgInfo().getStatus()==null?"":epfAuthOTPResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(epfAuthOTPResponse.getMsgInfo().getMessage()==null?"":epfAuthOTPResponse.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            }
            
            if(epfAuthOTPRequest.getPayload()!=null)
            {
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<br/>");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");
            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>UAN No.</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Mobile No.</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Consent</strong>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(epfAuthOTPRequest.getPayload().getUanid()==null?"":epfAuthOTPRequest.getPayload().getUanid());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(epfAuthOTPRequest.getPayload().getMobile_no()==null?"":epfAuthOTPRequest.getPayload().getMobile_no());sb.append("</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(epfAuthOTPRequest.getPayload().getConsent()==null?"":epfAuthOTPRequest.getPayload().getConsent());sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            }
            
            
            
		if(epfAuthOTPResponse.getPayload()!=null && epfAuthOTPResponse.getPayload().getResult()!=null)
		{
            sb.append("<table width='100%' cellpading='0'>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td align='left'>");
            sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
            sb.append("Detailed Report");
            sb.append("</b>");sb.append("</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table width='100%'>");

            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
            sb.append("<tr>");
            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
            sb.append("<font size='0.5' face='arial'>");sb.append("<b>OTP Message</b>");sb.append("</font>");
            sb.append("</td>");
            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
            sb.append("</td>");
            sb.append("<td align='left'>");
            if(epfAuthOTPResponse.getPayload()!=null && epfAuthOTPResponse.getPayload().getResult()!=null)
            {
            sb.append("<font size='0.5' face='arial'>");sb.append(epfAuthOTPResponse.getPayload().getResult().getMessage()==null?"":epfAuthOTPResponse.getPayload().getResult().getMessage());sb.append("</font>");
            }
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");

            sb.append("</table>");
            sb.append("</td>");

            sb.append("</tr>");
            sb.append("<!--Details-->");
            sb.append("</table>");
		}
		sb.append("</body>");
		sb.append("</html>");



			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+pdfName+ epfAuthOTPResponse.getHeader().getCorrelationId()+".pdf");

			return dto;


		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PDFConverter || getPdfByteArrayPan || error:: "+e);
			return null;

		}

	}


}
