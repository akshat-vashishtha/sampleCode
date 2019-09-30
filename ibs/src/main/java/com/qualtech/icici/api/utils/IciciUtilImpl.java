//package com.qualtech.icici.api.utils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.StringReader;
//import java.util.Calendar;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.html.simpleparser.HTMLWorker;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.codec.Base64;
//import com.qualtech.api.db.PropertyFile;
//import com.qualtech.icici.api.request.IciciPdf;
//import com.qualtech.icici.api.request.PolicyStatusPayload;
//import com.qualtech.icici.api.request.PolicyStatusRequest;
//import com.qualtech.icici.api.response.PolicyStatusResPayloadResult;
//import com.qualtech.icici.api.response.PolicyStatusResponse;
//import com.qualtech.icici.api.response.PolicyStatusResponsePayload;
//import com.qualtech.karza.api.utils.KarzaPDFConverter;
//
//
//@Service
//public class IciciUtilImpl implements IciciUtil{
//
//	private static Logger logger = Logger.getLogger(KarzaPDFConverter.class.getClass());
//	@Autowired PropertyFile resProp;
//	
//	@Override
//	public String policyStatusIntReq(PolicyStatusPayload payload)
//	 {
//		logger.info("IciciUtilImpl || policyStatusIntReq() || STARTS");
//		 StringBuilder sb = new StringBuilder();
//		 sb.append("{");
//		 sb.append("\"source\": \"" + (payload.getSource()!=null?payload.getSource() : "")+ "\",");
//		 sb.append("\"appName\": \"" + (payload.getAppName()!=null?payload.getAppName() : "")+ "\",");
//		 sb.append("\"reqType\": \"" + (payload.getReqType()!=null?payload.getReqType() : "")+ "\",");
//		 sb.append("\"reqSubType\": \"" + (payload.getReqSubType()!=null?payload.getReqSubType() : "")+ "\",");
//		 sb.append("\"paramListMap\": \"");
//		 sb.append("{"); 
//		 		
//		 sb.append("\\\"appNo\\\": \\\"" + (payload.getParamListMap().getAppNo()!=null?payload.getParamListMap().getAppNo() : "")+ "\\\",");
//		 sb.append("\\\"dob\\\": \\\"" + (payload.getParamListMap().getAppNo()!=null?payload.getParamListMap().getDob() : "")+ "\\\"");
//		 sb.append("}\"");
//		 sb.append("}");
//		 logger.info("IciciUtilImpl || policyStatusIntReq() || ENDS");
//		 return sb.toString();
//	 }
//
//	@Override
//	public IciciPdf pdfPolicyStatus(PolicyStatusRequest policyStatusRequest, PolicyStatusResponse policyStatusResponse,
//			String pdfName) {
//		logger.info("IciciUtilImpl || pdfPolicyStatus() || STARTS");
//		try {
//			String logoPath=null;
//			IciciPdf pdf=new IciciPdf();
//			String filePath=resProp.getString("com.karza.pdf.qc.save"/*"com.qc.ibs.success.pdfPath"*/);
//			try{
//				logoPath=resProp.getString("com.qc.ibs.logo");
//			}catch(Exception e){
//				logger.error("IciciUtilImpl || pdfPolicyStatus() || error while getting logo from path :: " +e);
//			}
//
//			String kycPath=null;
//
//			try{
//				kycPath=resProp.getString("com.qc.ibs.logo"/*"com.qc.ibs.other.logo"*/);
//			}catch(Exception e){
//				logger.error("IciciUtilImpl || pdfPolicyStatus() || error while getting other logo from path :: " +e);
//			}
//
//			String finalPathPdf=getPath(filePath);
//			Document document = new Document(PageSize.A4,36,36,36,36);
//			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ policyStatusRequest.getHeader().getCorrelationId()+".pdf"));
//			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
//			PdfWriter writer = PdfWriter.getInstance(document, bStream);
//			document.open();
//			StringBuffer sb = new StringBuffer();
//
//			sb.append("<html>");
//            sb.append("<head>");
//            sb.append("<title>");sb.append("</title>");
//            sb.append("</head>");
//            sb.append("<body>");
//            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
//            sb.append("<tr>");
//            sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
//            sb.append("</td>");
//            sb.append("<td align='right' width='50%'>");sb.append("<img src='"+logoPath+"' width='100' align='right'>");
//            sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("</table>");
//            sb.append("<br>");
//            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
//            sb.append("<tr>");
//            sb.append("<td align='center' height='10'>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td align='center' width='50%'>");
//            sb.append("<font size='3' face='arial' font color=\"#005580\">");
//            sb.append("<strong><u>ICICI Policy Status Report</u></strong>");sb.append("</font>");
//            sb.append("</td>");
//		//	sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(TestSample.date());sb.append("</font>");
//		//	sb.append("</td>");
//            sb.append("</tr>");
//            
//            sb.append("<tr>");
//            sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("</table>");
//            
//           /* sb.append("<table width='100%'>");
//            sb.append("<tr>");
//            sb.append("<td align='center' height='10'>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<br/>");
//            sb.append("<tr>");
//            sb.append("<td align='left'>");
//            sb.append("<font size='3' face='arial' font color=\"#005580\">");
//            sb.append("<b>");sb.append("Report summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td align='center' height='10'>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>");
//            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
//            sb.append("<tr>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Number</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Voter Id");sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(voterRequest.getPayload().getEpic_no());sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(voterResponse.getPayload().getStatus_msg());sb.append("</font>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("</table>");
//            sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td align='center' height='20'>");
//            sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("</table>");*/
//			
//            
//            if(policyStatusRequest.getPayload()!=null)
//            {
//            	PolicyStatusPayload payload = policyStatusRequest.getPayload();
//            sb.append("<table width='100%'>");
//            sb.append("<tr>");
//            sb.append("<td align='center' height='10'>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<br/>");
//            sb.append("<tr>");
//            sb.append("<td align='left'>");
//            sb.append("<font size='3' face='arial' font color=\"#005580\">");
//            sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td align='center' height='10'>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>");
//            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
//            sb.append("<tr>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Source</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>App Name</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Req Type</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Req Sub type</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>App No</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>DOB</strong>");sb.append("</font>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((payload.getSource()==null?"":payload.getSource()));sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((payload.getAppName()==null?"":payload.getAppName()));sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((payload.getReqType()==null?"":payload.getReqType()));sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((payload.getReqSubType()==null?"":payload.getReqSubType()));sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((payload.getParamListMap().getAppNo()==null?"":payload.getParamListMap().getAppNo()));sb.append("</font>");sb.append("</td>");
//            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append((payload.getParamListMap().getDob()==null?"":payload.getParamListMap().getDob()));sb.append("</font>");sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("</table>");
//            sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td align='center' height='20'>");
//            sb.append("</td>");
//            sb.append("</tr>");
//            sb.append("</table>");
//            }
//
//            if(policyStatusResponse.getPayload()!=null && policyStatusResponse.getPayload().getResult()!=null)
//            {
//
//				PolicyStatusResponsePayload payload = policyStatusResponse.getPayload();
//				if (payload.getResult() != null && payload.getResult() != null) {
//					PolicyStatusResPayloadResult result = payload.getResult();
//	            	  
//		            sb.append("<table width='100%' cellpading='0'>");
//		            sb.append("<!--Details-->");
//		            sb.append("<tr>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='3' face='arial' font color='#005580'>");sb.append("<b>");
//		            sb.append("Detailed Report");
//		            sb.append("</b>");sb.append("</font>");sb.append("</td>");
//		            sb.append("</tr>");
//		            sb.append("<tr>");
//		            sb.append("<td align='center' height='10'>");sb.append("</td>");
//		            sb.append("</tr>");
//		            sb.append("<!--Details-->");
//		            sb.append("<tr>");
//		            sb.append("<td>");
//		            
//		            sb.append("<table width='100%'>");
//		            sb.append("<tr>");
//		            sb.append("<td align='center' height='10'>");sb.append("</td>");
//		            sb.append("</tr>");
//		            sb.append("<tr>");
//		            sb.append("<td>");
//		            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  bordercolor='#ccc'>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Status</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getStatus()==null?"":result.getStatus());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Issuance Date</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getIssuanceDate()==null?"":result.getIssuanceDate());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Risk Comm Date</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getRiskCommDate()==null?"":result.getRiskCommDate());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Policy Status</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getPolicyStatus()==null?"":result.getPolicyStatus());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Policy Number</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getPolicyNo()==null?"":result.getPolicyNo());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Premium Amount</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getPremiumAmt()==null?"":result.getPremiumAmt());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Timestamp</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getTimestamp()==null?"":result.getTimestamp());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Message</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getMessage()==null?"":result.getMessage());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Code</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getCode()==null?"":result.getCode());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("<tr>");
//		            sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append("<b>Details</b>");sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
//		            sb.append("</td>");
//		            sb.append("<td align='left'>");
//		            sb.append("<font size='0.5' face='arial'>");sb.append(result.getDetails()==null?"":result.getDetails());sb.append("</font>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            
//		            sb.append("</table>");
//		            
//		            ////////////////////////
//		       
//		            sb.append("</table>");
//		            sb.append("</td>");
//		            sb.append("</tr>");
//
//		          /*  sb.append("</table>");*/
//		            sb.append("</td>");
//		            sb.append("</tr>");
//		            sb.append("<!--Details-->");
//		            sb.append("</table>");
//				}
//            	
//            }
//		   sb.append("</body>");
//		   sb.append("</html>");
//
//			HTMLWorker worker = new HTMLWorker(document);
//			worker.parse(new StringReader(sb.toString()));
//			document.close();
//			String base64 = Base64.encodeBytes(bStream.toByteArray());
//			pdf.setByteArray(base64);
//			pdf.setFilePath(finalPathPdf +File.separator+pdfName+ policyStatusRequest.getHeader().getCorrelationId()+".pdf");
//			logger.info("IciciUtilImpl || pdfPolicyStatus() || ENDS");
//			return pdf;
//		} catch (Exception e) {
//			logger.error("IciciUtilImpl || pdfPolicyStatus() || ENDS with error :: "+e);
//			return null;
//
//		}
//	
//	}
//	
//	
//	private static String getPath(String filePath) {
//		String path=null;
//		String downloadFolder = filePath;
//		Calendar cal=Calendar.getInstance();
//		//		System.out.println(cal.get(Calendar.MONTH)+1);
//
//		File file = new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
//		if (file.exists()) {
//			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
//		}else{
//			new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)).mkdir();
//			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
//		}
//		return path;
//	}
//	
//	
//	
//}
