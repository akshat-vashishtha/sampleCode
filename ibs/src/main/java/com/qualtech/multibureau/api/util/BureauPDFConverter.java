package com.qualtech.multibureau.api.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.multibureau.api.common.dto.AllBureauDto;
import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.response.BureauResponse;
import com.qualtech.multibureau.api.response.ErrorRejectRes;
import com.qualtech.multibureau.api.response.FinishedBureauRes;
import com.qualtech.multibureau.api.response.InProcessBureauRes;
import com.qualtech.multibureau.api.response.MessageInfo;
import com.qualtech.multibureau.api.response.RejectBureauRes;


@Service
@SuppressWarnings("deprecation")
public class BureauPDFConverter implements PDFConvertBureau {

	private static Logger logger = Logger.getLogger(BureauPDFConverter.class.getClass());
	@Autowired PropertyFile resProp;

	@Override
	public AllBureauDto getPdfByteArrayQC(BureauResponse dlRes, BureauRequest dlRequest, String pdfName) {
		AllBureauDto dto=new AllBureauDto();
		FileOutputStream file = null;
		try {

			String logoPath=null;
			String filePath=resProp.getString("com.bureau.pdf.qc.save");
			String mergePdfName=null;
			try{
				logoPath=resProp.getString("com.bureau.logo.qc");
			}catch(Exception e){
				logger.error("Exception while resProp retriving credentials of logopath and filepath"+e);
			}
			////
			String enCodingRes = "";
			int i=0;
			byte[] byteAr=null;
			String pdfNam="";
			List<FinishedBureauRes> valuePDF=null;

			List<String> listCheckByte = new ArrayList<String>();
			List<String> listFile = new ArrayList<String>();
			List<InputStream> list = new ArrayList<InputStream>();
			if(dlRes!=null &&  dlRes.getPayload()!=null) 
			{
			if(dlRes!=null && dlRes.getPayload()!=null &&  dlRes.getPayload().getResult()!=null) {
				valuePDF = dlRes.getPayload().getResult().getFinished();
				if(valuePDF!=null &&!valuePDF.isEmpty()) {
					for(FinishedBureauRes res:valuePDF)
					{
						byteAr=res.getPdf_report();
						pdfNam=res.getBureau();
						Base64 b = new Base64();
						enCodingRes = b.encode(byteAr);
						byte[] decodeRes = b.decode(enCodingRes);


						file = new FileOutputStream(filePath+pdfNam+ dlRes.getHeader().getCorrelationId()+".pdf");
						file.write(decodeRes);
						listCheckByte.add(enCodingRes);
						listFile.add(filePath+pdfNam+ dlRes.getHeader().getCorrelationId()+".pdf");
						//
						list.add(new FileInputStream(new File(filePath+pdfNam+ dlRes.getHeader().getCorrelationId()+".pdf")));
					}
				}
				if(dlRes.getPayload().getResult().getReject()!=null && !dlRes.getPayload().getResult().getReject().isEmpty()) {
					List<RejectBureauRes> reject=dlRes.getPayload().getResult().getReject();
					for (RejectBureauRes rejectBureauRes : reject) {
						/////////////////////////////////////////
						String pdfname=pdfFailure(rejectBureauRes,dlRes.getHeader().getCorrelationId()); 
						listFile.add(pdfname);
						
						///
						list.add(new FileInputStream(new File(pdfname)));
					}
				} 
				if(dlRes.getPayload().getResult().getIn_process()!=null && !dlRes.getPayload().getResult().getIn_process().isEmpty())
				{
					List<InProcessBureauRes> in_process=dlRes.getPayload().getResult().getIn_process();
					for (InProcessBureauRes inprocess : in_process) {
						String pdfname=pdfFailure(inprocess,dlRes.getHeader().getCorrelationId()); 
						listFile.add(pdfname);
						///
						list.add(new FileInputStream(new File(pdfname)));
					}
				}
				if(dlRes.getPayload().getResult().getReject()==null  && valuePDF==null && dlRes.getPayload().getResult().getIn_process()==null)
				{
					String pdfname=pdfFailure(dlRes.getMsgInfo(),dlRes.getHeader().getCorrelationId()); 
					listFile.add(pdfname);
					///
					list.add(new FileInputStream(new File(pdfname)));
				}
			}
			}else {
				String pdfname=pdfFailure(dlRes.getMsgInfo(),dlRes.getHeader().getCorrelationId()); 
				listFile.add(pdfname);
				///
				list.add(new FileInputStream(new File(pdfname)));
			}
			try {
				 mergePdfName=filePath+"MultiBureau"+dlRes.getHeader().getCorrelationId()+".pdf";
				 OutputStream out = new FileOutputStream(new File(mergePdfName));
		         doMerge(list, out);
			} catch (Exception e) {
				logger.error("Exception while merging PDF "+e);
			}
			dto.setByteArray(listCheckByte);
			dto.setFilePath(listFile);
			dto.setPdfPath(mergePdfName);

		}
		catch (Exception e) {
			logger.error("Exception while converting the base64"+e);
		}
		finally {
			if(file!=null) {
				try {
					file.close();
				} catch (IOException e) {
					logger.error("Exception in finally while closing resources "+e);
				}
			}
		}

		return dto;
	}
	
	public static void doMerge(List<InputStream> list, OutputStream outputStream)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        
        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
                cb.addTemplate(page, 0, 0);
            }
        }
        
        outputStream.flush();
        document.close();
        outputStream.close();
    }
	
	
	public  String pdfFailure(InProcessBureauRes inprocess,String corelationid) {

		String finalPathPdf="";
		try {
			String logoPath=null;
			finalPathPdf=resProp.getString("com.bureau.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.bureau.logo.qc");
			}catch(Exception e){
				logger.error("Exception raised in to retrive logo "+e);
			}
			finalPathPdf=finalPathPdf +inprocess.getBureau()+"failure"+corelationid+".pdf";
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf));
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
			sb.append("<td align='right' width='50%'>");
			sb.append("<img src='"+logoPath+"' width='100' align='left'>");
			//sb.append("<h1 align='right' style='display: inline; font-size: 20px; color: #2475a4;'>IBS</h1>");
			
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
			sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#2475a4'><b>IBS MULTIBUREAU("+inprocess.getBureau()+") FAILURE REPORT</b></font>");sb.append("</td>");
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
			sb.append("<td align='center'>");sb.append("<font size='3' face='arial' color='#c00000'>FAILURE REASON</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>TRACKING ID</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>CODE</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>STATUS</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>PRODUCT</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>DESCRIPTION</font>");sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+inprocess.getTracking_id()+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>NA</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+inprocess.getStatus()+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+inprocess.getProduct()+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>IN-PROCESS</font>");sb.append("</td>");
			sb.append("</tr>");
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

		} catch (Exception e) {
			logger.error("Exception raised in to write failure pdf "+e);
		}
		return finalPathPdf;
	}
	
	
	public  String pdfFailure(MessageInfo error,String corelationid) {

		String finalPathPdf="";
		try {
			String logoPath=null;
			finalPathPdf=resProp.getString("com.bureau.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.bureau.logo.qc");
			}catch(Exception e){
				logger.error("Exception raised in to retrive logo "+e);
			}
			finalPathPdf=finalPathPdf +"MultiBureaufailure"+corelationid+".pdf";
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf));
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
			sb.append("<td align='right' width='50%'>");
			sb.append("<img src='"+logoPath+"' width='100' align='left'>");
			//sb.append("<h1 align='right' style='display: inline; font-size: 20px; color: #2475a4;'>IBS</h1>");
			
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
			sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#2475a4'><b>IBS MULTIBUREAU FAILURE REPORT</b></font>");sb.append("</td>");
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
			sb.append("<td align='center'>");sb.append("<font size='3' face='arial' color='#c00000'>FAILURE REASON</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>CODE</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>STATUS</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>MESSAGE</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>DESCRIPTION</font>");sb.append("</td>");
			
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+error.getCode()+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+error.getStatus()+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+error.getMessage()+"</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+/*error.getDescription()*/"NA"+"</font>");sb.append("</td>");
	
			sb.append("</tr>");
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

		} catch (Exception e) {
			logger.error("Exception raised in to write failure pdf "+e);
		}
		return finalPathPdf;
	}

	private String pdfFailure(RejectBureauRes rejectBureauRes,String correlation) {
		String pdfname=null;
		try {
			String logoPath=null;
			String filePath=resProp.getString("com.bureau.pdf.qc.save");
			try{
				logoPath=resProp.getString("com.bureau.logo.qc");
			}catch(Exception e){
				logger.error("Exception raised in to retrive logo "+e);
			}
			String finalPathPdf=filePath;
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +rejectBureauRes.getBureau()+"failure"+correlation+".pdf"));
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
			sb.append("<td align='right' width='50%'>");
			sb.append("<img src='"+logoPath+"' width='100' align='left'>");
			//sb.append("<h1 align='right' style='display: inline; font-size: 20px; color: #2475a4;'>IBS</h1>");

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
			sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#2475a4'><b>IBS MULTIBUREAU("+rejectBureauRes.getBureau()+") FAILURE REPORT</b></font>");sb.append("</td>");
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
			sb.append("<td align='center'>");sb.append("<font size='3' face='arial' color='#c00000'>FAILURE REASON</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>TRACKING ID</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>CODE</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>STATUS</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>PRODUCT</font>");sb.append("</td>");
			sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>DESCRIPTION</font>");sb.append("</td>");


			sb.append("</tr>");

			List<ErrorRejectRes> errors=rejectBureauRes.getErrors();
			if(errors!=null && !errors.isEmpty()) 
			{
				for (ErrorRejectRes errorRejectRes : errors) 
				{
					sb.append("<tr>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+rejectBureauRes.getTracking_id()+"</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+errorRejectRes.getCode()+"</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>FAILURE</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+rejectBureauRes.getProduct()+"</font>");sb.append("</td>");
					sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+errorRejectRes.getDescription()+"</font>");sb.append("</td>");
					sb.append("</tr>");
				}
			}
			else
			{
				sb.append("<tr>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+rejectBureauRes.getTracking_id()+"</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>NA</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>FAILURE</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+rejectBureauRes.getProduct()+"</font>");sb.append("</td>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+rejectBureauRes.getStatus()+"</font>");sb.append("</td>");
				sb.append("</tr>");
			}

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
			pdfname=finalPathPdf+rejectBureauRes.getBureau()+"failure"+correlation+".pdf";
		} catch (Exception e) {
			logger.error("Exception while Creating Multibureau Failure Report "+e);
		}
		return pdfname;
	}

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
}