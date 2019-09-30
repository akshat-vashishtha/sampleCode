package com.qualtech.kotak.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.kotak.api.dto.KotakDto;
import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.response.KotakFault;
import com.qualtech.kotak.api.response.KotakFaultList;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakResponsePayload;
import com.qualtech.kotak.api.response.KotakReversalDetail;
import com.qualtech.kotak.api.response.KotakReversalDetails;
import com.qualtech.kotak.api.response.KotakReversalResponse;
import com.qualtech.kotak.api.response.KotakReversalResponsePayload;




@SuppressWarnings("deprecation")
@Component
public class PDFConverterKotak implements PDFConverterInt
{

	private static Logger logger = Logger.getLogger(PDFConverterKotak.class.getClass());
	// static ResourceBundle resProp = ResourceBundle.getBundle("application");
	@Autowired
	PropertyFile resProp;


	private static String getPath(String filePath) {
		String path=null;
		String downloadFolder = filePath;
		Calendar cal=Calendar.getInstance();

		File file = new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
		if (file.exists()) {
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}else{
			new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)).mkdir();
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}
		return path;
	}

	@SuppressWarnings("unused")
	public KotakDto getPdfByteArrayPayment(KotakResponse kotkResponse, KotakRequest kotakRequest) {
		try {
			String logoPath=null;
			KotakDto dto=new KotakDto();
			String filePath=resProp.getString("com.kotak.pdf.save");
			
			try{
				logoPath=resProp.getString("com.kotak.logo.qc");
			}catch(Exception e){

			}
			

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+"KOTAKPAYMENT_"+ kotkResponse.getHeader().getCorrelationId()+".pdf"));
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
			sb.append("<td align='right' width='50%'>");
			sb.append("<img src='"+logoPath+"' width='100' align='right'></td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>Kotak Payment Report</strong></u></font>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(date());sb.append("</font>");
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
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Kotak Report summary</b></font>");
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
			sb.append("<td align='center'><font size='0.5' face='arial'>Payment</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotakRequest.getPayload().getKotakPayment().getPayheader().getMessageId()==null?"NA":kotakRequest.getPayload().getKotakPayment().getPayheader().getMessageId())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotkResponse.getMsgInfo().getStatus()==null?"NA":kotkResponse.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotkResponse.getMsgInfo().getMessage()==null?"NA":kotkResponse.getMsgInfo().getMessage())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='30'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");
			if (kotakRequest!=null && kotakRequest.getPayload()!=null && kotakRequest.getPayload().getKotakPayment()!=null) {
				sb.append("<table width='100%' cellpading='0'>");
				sb.append("<!--Details-->");
				sb.append("<tr>");
				sb.append("<td align='left'>");
				sb.append("<font size='3' face='arial' color='#005580'><b>Input Data</b></font>");
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
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Message Id</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getPayheader().getMessageId()==null?"NA":kotakRequest.getPayload().getKotakPayment().getPayheader().getMessageId()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Message Source</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getPayheader().getMsgSource()==null?"NA":kotakRequest.getPayload().getKotakPayment().getPayheader().getMsgSource()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Client Code</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getPayheader().getClientCode()==null?"NA":kotakRequest.getPayload().getKotakPayment().getPayheader().getClientCode()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Batch Ref.Number</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getPayheader().getBatchRefNmbr()==null?"NA":kotakRequest.getPayload().getKotakPayment().getPayheader().getBatchRefNmbr()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Instrument Ref. Number</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getInstRefNo()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getInstRefNo()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>My Prod. Code</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getMyProdCode()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getMyProdCode()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Payment Mode</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPayMode()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPayMode()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Txn. Amount</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getTxnAmnt()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getTxnAmnt()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Account Number</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getAccountNo()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getAccountNo()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Payment Date</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPaymentDt()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPaymentDt()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>recBrCd</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getRecBrCd()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getRecBrCd()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Bene. Account Number</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getBeneAcctNo()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getBeneAcctNo()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Bene. Name</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getBeneName()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getBeneName()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Bene. Address</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getBeneAddr1()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getBeneAddr1()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Country</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getCountry()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getCountry()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Payment Detail 1</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPaymentDtl1()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPaymentDtl1()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Payment Detail 2</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPaymentDtl2()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getPaymentDtl2()) + "</font></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append(
						"<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Enrichment</strong></font></td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getEnrichmentSet().getEnrichment()==null?"NA":kotakRequest.getPayload().getKotakPayment().getInstrumentList().getInstrument().getEnrichmentSet().getEnrichment()) + "</font></td>");
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
			
			if (kotkResponse!=null && kotkResponse.getPayload()!=null && kotkResponse.getPayload().getAckHeader()!=null) {
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
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Message Id</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotkResponse.getPayload().getAckHeader().getMessageId() == null ? "NA"
								: kotkResponse.getPayload().getAckHeader().getMessageId())
						+ "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Status Code</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotkResponse.getPayload().getAckHeader().getStatusCd() == null ? "NA"
								: kotkResponse.getPayload().getAckHeader().getStatusCd())
						+ "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Status Remark</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotkResponse.getPayload().getAckHeader().getStatusRem() == null ? "NA"
								: kotkResponse.getPayload().getAckHeader().getStatusRem())
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
			
			sb.append("<br>");
			
				if(kotkResponse.getPayload().getFaultList()!=null) {
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
				sb.append("Fault List");
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
				sb.append("<strong>Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Reason</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				if(kotkResponse!=null && !kotakRequest.equals("")){
					KotakResponsePayload kotkpayload= kotkResponse.getPayload();
					if(kotkpayload!=null && !kotkpayload.equals("")){
						KotakFaultList kotkfault= kotkpayload.getFaultList();
						if(kotkfault!=null && !kotkfault.equals("")){
							List<KotakFault> kotkFaultlist= kotkfault.getFault();
							if(kotkFaultlist!=null && (kotkFaultlist.size()>0)){
								for(KotakFault kotkflt:kotkFaultlist){
									
									sb.append("<tr>");
									sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(kotkflt.getCode());sb.append("</font>");sb.append("</td>");
									sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(kotkflt.getReason());sb.append("</font>");sb.append("</td>");
									sb.append("</tr>");
									
								}
							}
						}
					}
				}
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
			}
			
			sb.append("</body>");
			sb.append("</html>");

			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+"KOTAKPAYMENT_"+ kotkResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			logger.error("PDFConverter || getPdfByteArrayPayment || error:: "+e);
			return null;

		}
	
	}

	public KotakDto getPdfByteArrayRevarsal(KotakReversalResponse kotkResponse, KotakRequestReversal kotakRequest) {
		try {
			String logoPath=null;
			KotakDto dto=new KotakDto();
			String filePath=resProp.getString("com.kotak.pdf.save");
			
			try{
				logoPath=resProp.getString("com.kotak.logo.qc");
				
			
			}catch(Exception e){

			}
			

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+"KOTAKREVERSAL_"+ kotkResponse.getHeader().getCorrelationId()+".pdf"));
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
			sb.append("<td align='right' width='50%'>");
			sb.append("<img src='"+logoPath+"' width='100' align='right'></td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<br>");

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'><font size='3' face='arial' color='#005580'><strong><u>Kotak Reversal Report</strong></u></font>");
            sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(date());sb.append("</font>");
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
			sb.append("<td align='left'><font size='3' face='arial' color='#005580'><b>Kotak Report summary</b></font>");
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
			sb.append("<td align='center'><font size='0.5' face='arial'>Reversal</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotakRequest.getPayload().getReversal().getReversalHeader().getRequestId()==null?"NA":kotakRequest.getPayload().getReversal().getReversalHeader().getRequestId())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotkResponse.getMsgInfo().getStatus()==null?"NA":kotkResponse.getMsgInfo().getStatus())+"</font>");
			sb.append("</td>");
			sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotkResponse.getMsgInfo().getMessage()==null?"NA":kotkResponse.getMsgInfo().getMessage())+"</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='30'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<br>");                       
			if (kotakRequest!=null && kotakRequest.getPayload()!=null && kotakRequest.getPayload()!=null) {
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
				sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Message Id</strong></font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Message Source</strong></font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Client Code</strong></font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'><font size='0.5' face='arial'><strong>Payment Date</strong></font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotakRequest.getPayload().getReversal().getDetails().getMessageId()==null?"NA":kotakRequest.getPayload().getReversal().getDetails().getMessageId())+"</font>");
				sb.append("</td>");
				sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotakRequest.getPayload().getReversal().getReversalHeader().getMsgSource()==null?"NA":kotakRequest.getPayload().getReversal().getReversalHeader().getMsgSource())+"</font>");
				sb.append("</td>");
				sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotakRequest.getPayload().getReversal().getReversalHeader().getClientCode()==null?"NA":kotakRequest.getPayload().getReversal().getReversalHeader().getClientCode())+"</font>");
				sb.append("</td>");
				sb.append("<td align='center'><font size='0.5' face='arial'>"+(kotakRequest.getPayload().getReversal().getReversalHeader().getDatePost()==null?"NA":kotakRequest.getPayload().getReversal().getReversalHeader().getDatePost())+"</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td align='center' height='30'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				
			}
			
			sb.append("<br>");		
			if (kotkResponse!=null && kotkResponse.getPayload()!=null && kotkResponse.getPayload().getReversalHeader()!=null) {
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
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Message Id</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotkResponse.getPayload().getReversalHeader().getRequestId() == null ? "NA"
								: kotkResponse.getPayload().getReversalHeader().getRequestId())
						+ "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Status Code</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotkResponse.getPayload().getReversalHeader().getMsgSource() == null ? "NA"
								: kotkResponse.getPayload().getReversalHeader().getMsgSource())
						+ "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Client Code</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotkResponse.getPayload().getReversalHeader().getClientCode() == null ? "NA"
								: kotkResponse.getPayload().getReversalHeader().getClientCode())
						+ "</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'><font size='0.5' face='arial'><strong>Status Remark</strong></font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;</td>");
				sb.append("<td align='left'><font size='0.5' face='arial'>"
						+ (kotkResponse.getPayload().getReversalHeader().getDatePost() == null ? "NA"
								: kotkResponse.getPayload().getReversalHeader().getDatePost())
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
			
			sb.append("<br>");
			
				if(kotkResponse.getPayload().getReversalDetails()!=null) {
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
				sb.append("Reversal Details");
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
				sb.append("<strong>Message Id</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Status Code</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>Status Description</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("<td bgcolor='#b3e6ff' align='center'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append("<strong>UTR</strong>");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				if(kotkResponse!=null && !kotakRequest.equals("")){
					KotakReversalResponsePayload kotkpayload= kotkResponse.getPayload();
					if(kotkpayload!=null && !kotkpayload.equals("")){
						KotakReversalDetails kotkrevdetl= kotkResponse.getPayload().getReversalDetails();
						if(kotkrevdetl!=null && !kotkrevdetl.equals("")){
							List<KotakReversalDetail> kotkrevdetllist= kotkResponse.getPayload().getReversalDetails().getReversalDetail();
							if(kotkrevdetllist!=null && (kotkrevdetllist.size()>0)){
								for(KotakReversalDetail kotkdetail:kotkrevdetllist){	
									sb.append("<tr>");
									sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(kotkdetail.getMessageId());sb.append("</font>");sb.append("</td>");
									sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(kotkdetail.getStatusCode());sb.append("</font>");sb.append("</td>");
									sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(kotkdetail.getStatusDescription());sb.append("</font>");sb.append("</td>");
									sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(kotkdetail.getUtr());sb.append("</font>");sb.append("</td>");
									sb.append("</tr>");
								}
							}
						}
					}
				}
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
			}			
			sb.append("</body>");
			sb.append("</html>");


			
			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf+File.separator+"KOTAKREVERSAL_"+ kotkResponse.getHeader().getCorrelationId()+".pdf");
			
			return dto;


		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PDFConverter || getPdfByteArrayRevarsal || error:: "+e);
			return null;

		}
	}


	 public static String date(){
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String formatted = format.format(cal.getTime());
			return formatted;
	  }


}
