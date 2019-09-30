package com.qualtech.crif.api.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.axis.encoding.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.crif.api.dto.Address;
import com.qualtech.crif.api.dto.CrifTrackerDTO;
import com.qualtech.crif.api.dto.Phone;
import com.qualtech.crif.api.dto.Relation;
import com.qualtech.crif.api.dto.UniqueId;
import com.qualtech.crif.api.request.CrifRequest;
import com.qualtech.crif.api.request.CriffApiRequest;
import com.qualtech.crif.api.request.RequestPayload;

@Service
public class CrifUtil implements CrifUtilInterface {
	private static Logger logger = LogManager.getLogger(CrifUtil.class);
	@Autowired
	public PropertyFile resProp;
	private static HashMap<String, String> relationMap = new HashMap<String, String>();

	public static void putRelationMap() {
		relationMap.put("Father", "K01");
		relationMap.put("Husband", "K02");
		relationMap.put("Mother", "K03");
		relationMap.put("Son", "K04");
		relationMap.put("Daughter", "K05");
		relationMap.put("Wife", "K06");
		relationMap.put("Brother", "K07");
		relationMap.put("MotherInlaw", "K08");
		relationMap.put("FatherInlaw", "K09");
		relationMap.put("DaughterInlaw", "K10");
		relationMap.put("SisterInLaw", "K11");
		relationMap.put("SonInlaw", "K12");
		relationMap.put("BrotherInlaw", "K13");
		relationMap.put("Other", "K15");
	}

	public static String getGenderCode(String reqGender) {
		logger.info("CRIF service : Getting gender code : START ");
		String gender = "";
		if (reqGender.equalsIgnoreCase("male")) {
			gender = "G01";
		} else if (reqGender.equalsIgnoreCase("female")) {
			gender = "G02";
		} else if (reqGender.equalsIgnoreCase("Transgender")) {
			gender = "G03";
		} else if (reqGender.equalsIgnoreCase("Un-tagged")) {
			gender = "G04";
		}
		logger.debug("CRIF service : Gender Code is = " + gender);
		logger.info("CRIF service : Getting gender code : End ");
		return gender;
	}

	public static String getCurrentDate() {
		logger.info("CRIF service : Getting the current date in prescribed format.");
		logger.info("CRIF service : Current Date : START ");
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		logger.info(strDate);
		logger.debug("CRIF service : Current Date is = " + strDate);
		logger.info("CRIF service : Current Date : End ");
		return strDate;
	}

	public static String getIssueXML(String out1, Map<String, String> respAcknwldge) {

		String Issuexml = "<REQUEST-REQUEST-FILE>" + "<HEADER-SEGMENT>"
				+ "<SUB-MBR-ID>AVIOM INDIA HOUSING FINANCE PRIVATE LIMITED</SUB-MBR-ID>" + "<INQ-DT-TM>"
				+ respAcknwldge.get("REQUEST_DT") + "</INQ-DT-TM>" + "<REQ-VOL-TYP>C01</REQ-VOL-TYP>"
				+ "<REQ-ACTN-TYP>AT02</REQ-ACTN-TYP>" + "<TEST-FLG>Y</TEST-FLG>" + "<AUTH-FLG>Y</AUTH-FLG>"
				+ "<AUTH-TITLE>USER</AUTH-TITLE>" + "<RES-FRMT>XML/HTML</RES-FRMT>"
				+ "<MEMBER-PRE-OVERRIDE>N</MEMBER-PRE-OVERRIDE>" + "<RES-FRMT-EMBD>Y</RES-FRMT-EMBD>"
				+ "</HEADER-SEGMENT>" + "<INQUIRY>" + "<INQUIRY-UNIQUE-REF-NO>" + respAcknwldge.get("INQ_REF-NO")
				+ "</INQUIRY-UNIQUE-REF-NO>" + "<REQUEST-DT-TM>" + respAcknwldge.get("REQUEST_DT") + "</REQUEST-DT-TM>"
				+ "<REPORT-ID>" + respAcknwldge.get("REPORT-ID") + "</REPORT-ID>" + "</INQUIRY>"
				+ "</REQUEST-REQUEST-FILE>";
		logger.info("CRIF service: Traversing the Acknowledgment Response from CRIF : END");

		logger.debug("CRIF service: Issue XML:" + Issuexml);
		logger.debug("CRIF service: Applying delay in second call");
		return Issuexml;
	}

	@Override
	public String convertRequestJsontoXmlService(CriffApiRequest criffApiRequest, String service) {
		putRelationMap();
		RequestPayload payload = criffApiRequest.getPayload();
		List<CrifRequest> transactions = payload.getTransaction();
		String requestxml = "";
		for (int i = 0; i < transactions.size(); i++) {
			CrifRequest transaction = transactions.get(i);
			String fname = transaction.getfName();
			String mname = transaction.getmName();
			String lname = transaction.getlName();
			String dob = transaction.getDob();

			Map<String, String> typeOfIdList = new HashMap<String, String>();
			typeOfIdList.put("Passport", "ID01");
			typeOfIdList.put("VoterID", "ID02");
			typeOfIdList.put("UID", "ID03");
			typeOfIdList.put("Others", "ID04");
			typeOfIdList.put("RationCard", "ID05");
			typeOfIdList.put("DrivingLicenseNo", "ID06");
			typeOfIdList.put("Pan", "ID07");
			// String mobileno="";
			Date start = new Date();
			SimpleDateFormat oFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String sDate = oFormat.format(start);
			requestxml = requestxml + "	<REQUEST-REQUEST-FILE>	" + "	<HEADER-SEGMENT>	" + "	<SUB-MBR-ID>"
					+ resProp.getString("com.qualtech.pan2.resource.CRIF.SUB-MBR-ID") + "</SUB-MBR-ID>	" +
					// "
					// <INQ-DT-TM>"+resProp.getString("com.qualtech.pan2.resource.CRIF.INQ-DT-TM")+"</INQ-DT-TM>
					// "+
					"	<INQ-DT-TM>" + sDate + "</INQ-DT-TM>	" + "	<REQ-ACTN-TYP>"
					+ resProp.getString("com.qualtech.pan2.resource.CRIF.REQ-ACTN-TYP") + "</REQ-ACTN-TYP>	"
					+ "	<TEST-FLG>" + resProp.getString("com.qualtech.pan2.resource.CRIF.TEST-FLG") + "</TEST-FLG>	"
					+ "	<AUTH-FLG>" + resProp.getString("com.qualtech.pan2.resource.CRIF.AUTH-FLG") + "</AUTH-FLG>	"
					+ "   <AUTH-TITLE>USER</AUTH-TITLE>" + "	<RES-FRMT>XML/HTML</RES-FRMT>	"
					+ "   <MEMBER-PRE-OVERRIDE>N</MEMBER-PRE-OVERRIDE>" + "   <RES-FRMT-EMBD>Y</RES-FRMT-EMBD>";
			StringBuilder requestxmlbuilder = new StringBuilder(requestxml);

			if ("MFI".equals(service)) {
				requestxmlbuilder.append("<MFI>");
				requestxmlbuilder.append("<INDV>true</INDV>");
				requestxmlbuilder.append("<SCORE>false</SCORE>");// flag change
																	// as per
																	// Sushil
																	// mail on
																	// 27 jun
				// requestxmlbuilder.append("<SCORE>true</SCORE>");//old
				requestxmlbuilder.append("<GROUP>true</GROUP>");
				requestxmlbuilder.append("</MFI>");
			} else if ("CONSUMER".equals(service)) {
				requestxmlbuilder.append("<CONSUMER>");
				requestxmlbuilder.append("<INDV>true</INDV>");
				requestxmlbuilder.append("<SCORE>true</SCORE>");
				// requestxmlbuilder.append("<GROUP>true</GROUP>");//remove as
				// per Sushil mail on 27 jun
				requestxmlbuilder.append("</CONSUMER>");
			}
			requestxmlbuilder.append("<IOI>true</IOI>");
			requestxmlbuilder.append("	</HEADER-SEGMENT>	");
			requestxmlbuilder.append("	<INQUIRY>	");
			requestxmlbuilder.append("	<APPLICANT-SEGMENT>	");
			requestxmlbuilder.append("<APPLICANT-NAME>");

			requestxml = requestxmlbuilder.toString();
			if (fname != null && !"".equals(fname)) {
				requestxml = requestxml + "<NAME1>" + fname + "</NAME1>";
			} else {
				requestxml = requestxml + "<NAME1/>";
			}
			if (mname != null && !"".equals(mname)) {
				requestxml = requestxml + "<NAME2>" + mname + "</NAME2>";
			} else {
				requestxml = requestxml + "<NAME2/>";
			}
			if (lname != null && !"".equals(lname)) {
				requestxml = requestxml + "<NAME3>" + lname + "</NAME3>";
			} else {
				requestxml = requestxml + "<NAME3/>";
			}

			requestxml = requestxml + "<NAME4 />" + "<NAME5 />" + "</APPLICANT-NAME>" + "<DOB>";

			// getting the age of applicant

			int age = 0;
			Date currentDate = new Date();
			Date date = null;

			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				date = (Date) formatter.parse(dob); // birthDate is a String, in
													// format dd-MM-yyyy
				long diff = currentDate.getTime() - date.getTime();
				long d = (1000l * 60 * 60 * 24 * 365);
				long years = Math.round(diff / d);
				age = (int) years;
				logger.info("age is===" + age);

			} catch (Exception ec) {
				logger.error("CrifUtil || convertRequestJsontoXmlService() || formatting current Date :: "+ec);
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			int currentyear = cal.get(Calendar.YEAR);
			int currentmonth = cal.get(Calendar.MONTH);
			int currentday = cal.get(Calendar.DAY_OF_MONTH);
			cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);

			requestxml = requestxml + "<DOB-DATE>" + day + "/" + month + "/" + year + "</DOB-DATE>" + "<AGE>" + age
					+ "</AGE>" + "<AGE-AS-ON>" + currentday + "/" + currentmonth + "/" + currentyear + "</AGE-AS-ON>"
					+ "</DOB>" + "<IDS>";

			List<UniqueId> ids = transaction.getIds();
			for (int j = 0; j < ids.size(); j++) {
				requestxml = requestxml + "<ID>";
				requestxml = requestxml + "	<TYPE>" + typeOfIdList.get(ids.get(j).getIdName()) + "</TYPE>	"
						+ "	<VALUE>" + ids.get(j).getIdNo() + "</VALUE>	" + "	</ID>	";

			}

			requestxml = requestxml + "</IDS>	" + "<RELATIONS>";

			// Map<String,String> relationMap=new HashMap<String,String>();

			List<Relation> relations = transaction.getRelations();
			for (int j = 0; j < relations.size(); j++) {
				requestxml = requestxml + "<RELATION>" + "<NAME>" + relations.get(j).getName() + "</NAME>" + "<TYPE>"
						+ relationMap.get(relations.get(j).getRelation()) + "</TYPE>" + "</RELATION>";
			}

			requestxml = requestxml + "</RELATIONS>" + "<KEY-PERSON>" + "<NAME>" + transaction.getKeyPersonName()
					+ "</NAME>" + "<TYPE>" + relationMap.get(transaction.getKeyPersonType()) + "</TYPE>"
					+ "</KEY-PERSON>" + "<NOMINEE>" + "<NAME>" + transaction.getNomineeName() + "</NAME>" + "<TYPE>"
					+ relationMap.get(transaction.getNomineeType()) + "</TYPE>" + "</NOMINEE>" + "	<PHONES>	";

			Map<String, String> phoneTypeMap = new HashMap<String, String>();
			phoneTypeMap.put("Residence", "P01");
			phoneTypeMap.put("Company", "P02");
			phoneTypeMap.put("Mobile", "P03");
			phoneTypeMap.put("Permanent", "P04");
			phoneTypeMap.put("Foreign", "P05");
			phoneTypeMap.put("Other", "P07");
			phoneTypeMap.put("Untagged", "P08");

			for (int j = 0; j < transaction.getPhones().size(); j++) {
				Phone phone = transaction.getPhones().get(j);
				requestxml = requestxml + "	<PHONE>	" + "	<TELE-NO-TYPE>" + phoneTypeMap.get(phone.getType())
						+ "</TELE-NO-TYPE>	" + "	<TELE-NO>" + phone.getTeleNo() + "</TELE-NO>	"
						+ "	</PHONE>	";
			}

			requestxml = requestxml + "	</PHONES>	" + "	</APPLICANT-SEGMENT>	" + "   <ADDRESS-SEGMENT>";

			for (int j = 0; j < transaction.getAddresses().size(); j++) {

				Address address = transaction.getAddresses().get(j);
				requestxml = requestxml + "  <ADDRESS>" + "   <TYPE>" + address.getType() + "</TYPE>" + "   <ADDRESS-1>"
						+ address.getAddress() + "</ADDRESS-1>" + "   <CITY>" + address.getCity() + "</CITY>"
						+ "   <STATE>" + address.getState() + "</STATE>" + "   <PIN>" + address.getPin() + "</PIN>"
						+ "   </ADDRESS>";
			}
			String sDate1 = "";
			sDate = sDate.replaceAll("-", "");
			sDate = sDate.replaceAll(" ", "");
			sDate1 = sDate.replaceAll(":", "");
			int randomPIN = (int) (Math.random() * 9000) + 1000;

			requestxml = requestxml + "   </ADDRESS-SEGMENT>" + "	<APPLICATION-SEGMENT>	"
					+ "	<INQUIRY-UNIQUE-REF-NO>" + sDate1 + "a0Yf0000003fL7xEA456" + randomPIN
					+ "</INQUIRY-UNIQUE-REF-NO>	" +
					// "
					// <INQUIRY-UNIQUE-REF-NO>006f000026EGk2342</INQUIRY-UNIQUE-REF-NO>
					// "+
					"	<CREDT-INQ-PURPS-TYP>ACCT-ORIG</CREDT-INQ-PURPS-TYP>"
					+ "   <CREDIT-INQUIRY-STAGE>PRE-DISB</CREDIT-INQUIRY-STAGE>"
					+ "	<CREDT-REQ-TYP>INDV</CREDT-REQ-TYP>	" + "   <BRANCH-ID>3008</BRANCH-ID>"
					+ "  <LOS-APP-ID>a0Yf0000003fL7xEA456</LOS-APP-ID>" +
					// " <LOAN-AMOUNT>200000</LOAN-AMOUNT>"+
					"	</APPLICATION-SEGMENT>	" + "	</INQUIRY>	" + "	</REQUEST-REQUEST-FILE>	";
			;

		}
		logger.debug("CRIF service: Request XML:" + requestxml);
		logger.info("Request XML:" + requestxml);

		return requestxml;

	}

	public String pdfCreationMFI(CrifTrackerDTO crifTrackerDTO, String fileName) {
		String byteArray = null;
		logger.info("CrifPdfCreator || pdfCreation() || Start");
		logger.info("CrifPdfCreator || pdfCreation() || file Name :: " + fileName);
		fileName += com.qualtech.crif.api.utils.UniqueId.getUniqueId();
		try {
			// System.out.println(crifTrackerDTO.getCrifApiIssueXmlRes());
			JSONObject response = XML.toJSONObject(crifTrackerDTO.getCrifApiIssueXmlRes());
			JSONObject indv_report = response.getJSONObject("INDV-REPORT-FILE").getJSONObject("INDV-REPORTS")
					.getJSONObject("INDV-REPORT");
			// System.out.println(response);
			JSONObject header = indv_report.getJSONObject("HEADER");

			try {
				header.get("DATE-OF-REQUEST");

				//System.out.println(header.get("DATE-OF-REQUEST"));
			} catch (Exception e) {
				e.printStackTrace();
				// logger.error("CrifPdfCreator || pdfCreation() || HEADER ::
				// "+e);
			}
			JSONObject request = indv_report.getJSONObject("REQUEST");
			// JSONObject personalInfoVariation
			// =indv_report.getJSONObject("PERSONAL-INFO-VARIATION");
			JSONObject primary_summary = indv_report.getJSONObject("INDV-RESPONSES").getJSONObject("PRIMARY-SUMMARY");
			JSONObject secondary_summary = indv_report.getJSONObject("INDV-RESPONSES")
					.getJSONObject("SECONDARY-SUMMARY");

			// JSONObject scores =indv_report.getJSONObject("SCORES");

			StringBuffer sb = new StringBuffer();

			if (true/* header!=null && request!=null */) {

				sb.append("<html>");
				sb.append("<head>");
				sb.append("<title>Advanced Overlap Report</title>");
				sb.append("</head>");
				sb.append("<body style='font-family: segoe ui semibold, arial, verdana;'>");

				sb.append(
						"									<table align='center' border='0' cellpadding='0'  style='width:100%'");
				sb.append("										cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr>");
				sb.append("												<td>");
				sb.append(
						"													<table align='center' border='0'  style='width:100%'>");
				sb.append("														<tbody>");
				sb.append("															<tr height='10'>");
				sb.append("																<td></td>");
				sb.append("															</tr>");
				sb.append("															<tr>");
				sb.append(
						"																<td colspan='2' valign='top'><img src='");
				//////// Set here logo path
				try {
					 sb.append(resProp.getString("com.qc.crif.pdf.logo"));
					//sb.append("/home/qualtech/index.gif");
				} catch (Exception e) {
				 logger.error("CrifPdfCreator || pdfCreation() || LOGO :: "+e);
				}
				sb.append(
						"'																	alt='HighMark Credit Information Services Pvt. Ltd1.'");
				sb.append("																	align='left' /></td>");
				sb.append("																<td width='150'></td>");
				sb.append(
						"																<td align='left' width='350' valign='top'>");
				sb.append(
						"																	<table border='0' cellpadding='0' cellspacing='0'>");
				sb.append("																		<tbody>");
				sb.append("																			<tr>");
				sb.append(
						"																				<td align='left' class='reportHead'>Advanced");
				sb.append(
						"																					Overlap Report<br> </br>");
				sb.append("																				</td>");
				sb.append("																			</tr>");
				sb.append("																			<tr valign='top'>");
				sb.append("								<td class='AccValue' align='right' valign='top'>For "
						+ request.get("NAME"));
				sb.append("									</td>");
				sb.append("																			</tr>");
				sb.append("																		</tbody>");
				sb.append("																	</table>");
				sb.append("																</td>");
				sb.append("																<td width='70'></td>");
				sb.append(
						"																<td rowspan='2' align='right' valign='top' width='350'>");
				sb.append("																	<table>");
				sb.append("																		<tbody>");
				sb.append("																			<tr>");
				sb.append(
						"																				<td class='dataHeader1'>Date of Request:</td>");

				sb.append(
						"																				<td class='dataValue1'>");
				sb.append(header.has("DATE-OF-REQUEST") && header.get("DATE-OF-REQUEST") != null
						? header.get("DATE-OF-REQUEST") : "");
				sb.append("																		</td>	</tr>");
				sb.append("																			<tr>");
				sb.append(
						"																				<td class='dataHeader1'>Date of Issue:</td>");
				sb.append("								<td class='dataValue1'>");

				sb.append(header.has("DATE-OF-ISSUE") && header.get("DATE-OF-ISSUE") != null
						? header.get("DATE-OF-ISSUE") : "");

				sb.append("</td>");
				sb.append("																			</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataHeader1'>CHM Ref #:</td>");
				sb.append("								<td class='dataValue1'>");

				sb.append(header.has("REPORT-ID") && header.get("REPORT-ID") != null ? header.get("REPORT-ID") : "");
				sb.append("</td>							</tr>");
				sb.append("																			<tr>");
				sb.append(
						"																				<td class='dataHeader1'>Prepared For:</td>");
				sb.append("								<td class='dataValue1'>");
				if (header.toString().endsWith("PREPARED-FOR")) {
					sb.append(header.has("PREPARED-FOR") && header.get("PREPARED-FOR") != null
							? header.get("PREPARED-FOR") : "");// need to check
				}
				sb.append("</td>");
				sb.append("																			</tr>");
				sb.append("																		</tbody>");
				sb.append("																	</table>");
				sb.append("																</td>");
				sb.append("															</tr>");
				sb.append("														</tbody>");
				sb.append("													</table>");
				sb.append("												</td>");
				sb.append("											</tr>");

				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("	<hr size='1' style='color: #C8C8C8;' />");
				sb.append("	<table align='center' border='0' cellpadding='0' cellspacing='0' style='width:100%'>");
				sb.append("		<tr>");
				sb.append("			<td>");

				sb.append(
						"								<table align='center' bgcolor='#0f3f6b' border='0' style='width:100%'>");
				sb.append("									<tbody>");
				sb.append("										<tr height='20'>");
				// sb.append(" <td width='10'></td>");
				sb.append(
						"											<td style='padding:2px;' class='mainHeader'> Inquiry Input Information</td>");
				sb.append("										</tr>");
				sb.append("									</tbody>");
				sb.append("								</table>");
				sb.append("			</td>");
				sb.append("		</tr>");

				sb.append("	</table>");
				sb.append("	<div align='center' style='padding-left: 8px;'>");
				sb.append("		<table border='0' style='width: 99%; border-collapse: collapse;'>");
				sb.append("			<tbody>");
				sb.append("				<tr>");
				sb.append("					<td height='10'></td>");
				sb.append("				</tr>");
				sb.append("				<tr style='background-color: #eef2f5;'>");
				sb.append("					<td align='left' class='dataHeader'>Name:</td>");
				sb.append("					<td align='left' class='dataValue'>");

				sb.append(request.has("NAME") && request.get("NAME") != null ? request.get("NAME") : "");
				sb.append("</td>");
				sb.append("					<td class='dataHeader'>DOB/Age:</td>");
				sb.append("					<td class='dataValue'>");

				sb.append(request.has("DOB") && request.get("DOB") != null ? request.get("DOB") : "");
				sb.append(" / ");
				sb.append(request.has("AGE") && request.get("AGE") != null ? request.get("AGE") : "");
				sb.append("years");
				sb.append("</td>");
				sb.append("					<td class='dataHeader'>Gender:</td>");// need
																					// to
																					// check
				sb.append("					<td class='dataValue'>Female</td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td align='left' class='dataHeader'>Father:</td>");
				sb.append("					<td align='left' class='dataValue'>");

				sb.append(request.has("FATHER") && request.get("FATHER") != null ? request.get("FATHER") : "");
				sb.append("</td>");
				sb.append("					<td class='dataHeader'>Spouse:</td>");// need
																					// to
																					// check
				sb.append("					<td class='dataValue'>");
				sb.append(request.has("SPOUSE") && request.get("SPOUSE") != null ? request.get("SPOUSE") : "");
				sb.append("					</td><td class='dataHeader'>Mother:</td>");
				sb.append("					<td class='dataValue'>");
				sb.append(request.has("MOTHER") && request.get("MOTHER") != null ? request.get("MOTHER") : "");
				sb.append("				</td></tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr style='background-color: #eef2f5;'>");
				sb.append("					<td class='dataHeader' valign='top'>Phone Numbers:</td>");
				sb.append("					<td valign='top'>");
				ArrayList<String> phones = new ArrayList<String>();

				try {
					if (request.has("PHONES")) {
						JSONArray newObj = request.getJSONObject("PHONES").getJSONArray("PHONE");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								phones.add(newObj.get(i).toString());
							}
						}
					}
				} catch (Exception e) {
					if (request.has("PHONES")) {
						try {
							JSONArray newObj = new JSONArray("[" + request.getJSONObject("PHONES").get("PHONE") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									phones.add(newObj.get(i).toString());
								}
							}
						} catch (Exception e2) {
							if (request.get("PHONES") == null) {
								phones.add("");
							}
						}
					}
				}

				sb.append("						<table cellpadding='0' cellspacing='0'>");
				sb.append("							<tr>");
				sb.append("								<td class='dataValue'>");

				sb.append(phones.size() > 0 && phones.get(0) != null ? phones.get(0) : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataValue'>");

				sb.append(phones.size() > 1 && phones.get(1) != null ? phones.get(1) : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataValue'>");

				sb.append(phones.size() > 2 && phones.get(2) != null ? phones.get(2) : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("					<td class='dataHeader' valign='top'>ID(s):</td>");
				sb.append("					<td valign='top'>");

				Map<String, String> typeOfIdList = new HashMap<String, String>();
				typeOfIdList.put("ID01", "Passport");
				typeOfIdList.put("ID02", "VoterID");
				typeOfIdList.put("ID03", "UID");
				typeOfIdList.put("ID04", "Others");
				typeOfIdList.put("ID05", "RationCard");
				typeOfIdList.put("ID06", "DrivingLicenseNo");
				typeOfIdList.put("ID07", "Pan");

				Map<String, String> ids = new HashMap<String, String>();

				try {
					if (request.has("IDS")) {
						JSONArray newObj = request.getJSONObject("IDS").getJSONArray("ID");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								JSONObject id = newObj.getJSONObject(i);
								ids.put(typeOfIdList.get(id.get("TYPE").toString()), id.get("VALUE").toString());

							}
						}
					}
				} catch (Exception e) {
					if (request.has("IDS")) {

						try {
							JSONArray newObj = new JSONArray("[" + request.getJSONObject("IDS").get("ID") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									JSONObject id = newObj.getJSONObject(i);
									ids.put(typeOfIdList.get(id.get("TYPE").toString()), id.get("VALUE").toString());

								}
							}
						} catch (Exception e2) {
							if (request.get("IDS") == null) {
								ids.put("", "");
							}
						}
					}
				}

				sb.append("						<table cellpadding='0' cellspacing='0'>");

				for (Map.Entry<String, String> entry : ids.entrySet()) {

					sb.append("							<tr>");
					sb.append("								<td class='dataValue'>");

					sb.append((entry.getValue() != null && !entry.getValue().equals("null") ? entry.getValue() : "").toString() + "["
							+ (entry.getKey() != null && !entry.getKey().equals("null")? entry.getKey() : "").toString() + "]");

					sb.append("</td>");
					sb.append("							</tr>");

				}

				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("					<td class='dataHeader' valign='top'>Email ID(s):</td>");
				sb.append("					<td valign='top'>");

				ArrayList<String> emails = new ArrayList<String>();

				try {
					if (request.has("EMAILS")) {
						JSONArray newObj = request.getJSONObject("EMAILS").getJSONArray("EMAIL");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								emails.add(newObj.get(i).toString());
							}
						}
					}
				} catch (Exception e) {
					if (request.has("EMAILS")) {
						try {
							JSONArray newObj = new JSONArray("[" + request.getJSONObject("EMAILS").get("EMAIL") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									emails.add(newObj.get(i).toString());
								}
							}
						} catch (Exception e2) {
							if (request.get("EMAILS") == null) {
								emails.add("");
							}
						}
					}
				}
				sb.append("						<table cellpadding='0' cellspacing='0'>");
				if (emails.size() > 0) {
					for (int i = 0; i < emails.size(); i++) {

						sb.append("							<tr>");
						sb.append("								<td class='dataValue'>");
						sb.append(emails.size() > i && emails.get(i) != null ? emails.get(i) : "");
						sb.append("</td>");
						sb.append("							</tr>");
					}
				} else {
					sb.append("							<tr>");
					sb.append("								<td class='dataValue'></td>");
					sb.append("							</tr>");
				}
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td align='left' class='dataHeader'>Entity Id:</td>");
				sb.append("					<td align='left' class='dataValue' colspan='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr style='background-color: #eef2f5;'>");
				sb.append("					<td align='left' class='dataHeader'>Current Address:</td>");
				JSONObject addresses = request.getJSONObject("ADDRESSES");
				sb.append(addresses.get("ADDRESS") != null ? addresses.get("ADDRESS") : "");
				sb.append("					<td align='left' class='dataValue' colspan='5'>");

				sb.append(addresses.get("ADDRESS") != null ? addresses.get("ADDRESS") : "");

				sb.append("</td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td align='left' class='dataHeader'>Other Address:</td>");
				sb.append("					<td align='left' class='dataValue' colspan='5'></td>");
				sb.append("				</tr>");
				sb.append("			</tbody>");
				sb.append("		</table>");
				sb.append("	</div>");

				sb.append("	<div align='center' style='padding-left: 8px;'>");
				sb.append("		<table border='0' style='width: 100%; border-collapse: collapse;'>");
				sb.append("			<tbody>");
				sb.append("	<tr>");
				sb.append("		<td>");
				sb.append(
						"			<table align='center' border='0' style='width: 100%;' cellpadding='0' cellspacing='0'>");
				sb.append("				<tbody>");
				sb.append("					<tr height='10'>");
				sb.append("						<td align='right' bgcolor='#FFFFFF' class='infoValue'></td>");
				sb.append("					</tr>");
				sb.append("					<tr height='20'>");
				sb.append("						<td align='right' bgcolor='#FFFFFF' class='infoValue'>Tip:");
				sb.append("							All amounts are in INR</td>");
				sb.append("					</tr>");
				sb.append("					<tr></tr>");
				sb.append("					<tr>");
				sb.append("						<td>");
				sb.append(
						"							<table align='center' bgcolor='#0f3f6b' border='0' style='width: 100%;'>");
				sb.append("								<tbody>");
				sb.append("									<tr height='20'>");
				sb.append(
						"										<td style='padding: 2px;' class='mainHeader'>Summary</td>");
				sb.append("									</tr>");
				sb.append("								</tbody>");
				sb.append("							</table>");
				sb.append("						</td>");
				sb.append("					</tr>");
				sb.append("				</tbody>");
				sb.append("			</table>");
				sb.append("		</td>");
				sb.append("	</tr>");
				sb.append("	<tr>");
				sb.append("		<td height='25' bgcolor='#FFFFFF' class='infoValue' align='right'>");
				sb.append("			Tip1: Current Balance, Disbursed Amount & Instalment Amount is");
				sb.append("			considered ONLY for ACTIVE account &nbsp;&nbsp;</td>");
				sb.append("	</tr>");
				sb.append("	<tr>");
				sb.append("		<td height='15'></td>");
				sb.append("	</tr>");
				sb.append("	<tr>");
				sb.append("		<td>");
				sb.append(
						"			<table align='center' border='0' cellpadding='0'style='width: 99%;' cellspacing='0'>");
				sb.append("				<tbody>");
				sb.append("					<tr>");
				sb.append("						<td>");
				sb.append("							<table align='center'");
				sb.append(
						"								style='border-collapse: collapse; border: 2px solid #A7CBE3;width: 100%;'");
				sb.append("								cellspacing='0' cellpadding='2'  >");
				sb.append("								<tbody>");
				sb.append("									<tr height='20'>");
				sb.append("										<td>");
				sb.append(
						"											<table align='center' border='0px' cellspacing='0'");
				sb.append("												cellpadding='0' style='width: 100%;' >");
				sb.append("												<tbody>");
				sb.append("													<tr>");
				sb.append("														<td width='center'>");
				sb.append(
						"															<table align='center' border='0px' cellspacing='0'");
				sb.append(
						"																cellpadding='0' style='width: 100%;' >");
				sb.append("																<thead>");
				sb.append("																	<tr height='25'>");
				sb.append(
						"																		<td width='150' class='subHeader1' rowspan='2'>Type</td>");
				sb.append(
						"																		<td align='center' width='175' scope='colgroup'");
				sb.append(
						"																			colspan='2' class='dataHeader2'>Association</td>");
				sb.append(
						"																		<td align='center' width='175' scope='colgroup'");
				sb.append(
						"																			colspan='3' class='dataHeader2'>Account Summary</td>");
				sb.append(
						"																		<td align='right' width='175' scope='colgroup'");
				sb.append(
						"																			colspan='2' class='dataHeader2'>Disbursed Amount</td>");
				sb.append(
						"																		<td align='right' width='175' scope='colgroup'");
				sb.append(
						"																			colspan='2' class='dataHeader2'>Instalment");
				sb.append("																			Amount</td>");
				sb.append(
						"																		<td align='right' width='175' scope='colgroup'");
				sb.append(
						"																			colspan='2' class='dataHeader2'>Current Balance</td>");
				sb.append(
						"																		<td align='right' width='5'></td>");
				sb.append("																	</tr>");
				sb.append("																	<tr height='20'>");
				sb.append(
						"																		<td align='center' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Own</td>");
				sb.append(
						"																		<td align='center' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Other</td>");
				sb.append(
						"																		<td align='center' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Active</td>");
				sb.append(
						"																		<td align='center' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Closed</td>");
				sb.append(
						"																		<td align='center' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Default</td>");
				sb.append(
						"																		<td align='right' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Own</td>");
				sb.append(
						"																		<td align='right' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Other</td>");
				sb.append(
						"																		<td align='right' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Own</td>");
				sb.append(
						"																		<td align='right' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Other</td>");
				sb.append(
						"																		<td align='right' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Own</td>");
				sb.append(
						"																		<td align='right' width='50' scope='col'");
				sb.append(
						"																			class='subHeader1'>Other</td>");
				sb.append(
						"																		<td align='right' width='5' class='subHeader1'></td>");
				sb.append("																	</tr>");
				sb.append("																</thead>");
				sb.append("																<tbody>");
				sb.append("																	<tr height='20'>");
				sb.append(
						"																		<td align='left' class='dataHeader'>Primary Match</td>");

				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(primary_summary.has("NO-OF-OWN-MFIS") && primary_summary.get("NO-OF-OWN-MFIS") != null
						? primary_summary.get("NO-OF-OWN-MFIS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(primary_summary.has("NO-OF-OTHER-MFIS") && primary_summary.get("NO-OF-OTHER-MFIS") != null
						? primary_summary.get("NO-OF-OTHER-MFIS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(primary_summary.has("NO-OF-ACTIVE-ACCOUNTS")
						&& primary_summary.get("NO-OF-ACTIVE-ACCOUNTS") != null
								? primary_summary.get("NO-OF-ACTIVE-ACCOUNTS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(primary_summary.has("NO-OF-CLOSED-ACCOUNTS")
						&& primary_summary.get("NO-OF-CLOSED-ACCOUNTS") != null
								? primary_summary.get("NO-OF-CLOSED-ACCOUNTS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(primary_summary.has("NO-OF-DEFAULT-ACCOUNTS")
						&& primary_summary.get("NO-OF-DEFAULT-ACCOUNTS") != null
								? primary_summary.get("NO-OF-DEFAULT-ACCOUNTS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(primary_summary.has("TOTAL-OWN-DISBURSED-AMOUNT")
						&& primary_summary.get("TOTAL-OWN-DISBURSED-AMOUNT") != null
								? primary_summary.get("TOTAL-OWN-DISBURSED-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(primary_summary.has("TOTAL-OTHER-DISBURSED-AMOUNT")
						&& primary_summary.get("TOTAL-OTHER-DISBURSED-AMOUNT") != null
								? primary_summary.get("TOTAL-OTHER-DISBURSED-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(primary_summary.has("TOTAL-OWN-INSTALLMENT-AMOUNT")
						&& primary_summary.get("TOTAL-OWN-INSTALLMENT-AMOUNT") != null
								? primary_summary.get("TOTAL-OWN-INSTALLMENT-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(primary_summary.has("TOTAL-OTHER-INSTALLMENT-AMOUNT")
						&& primary_summary.get("TOTAL-OTHER-INSTALLMENT-AMOUNT") != null
								? primary_summary.get("TOTAL-OTHER-INSTALLMENT-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(primary_summary.has("TOTAL-OWN-CURRENT-BALANCE")
						&& primary_summary.get("TOTAL-OWN-CURRENT-BALANCE") != null
								? primary_summary.get("TOTAL-OTHER-CURRENT-BALANCE") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(primary_summary.has("TOTAL-OTHER-CURRENT-BALANCE")
						&& primary_summary.get("TOTAL-OTHER-CURRENT-BALANCE") != null
								? primary_summary.get("TOTAL-OTHER-CURRENT-BALANCE") : "-");
				sb.append("																	</span>	</td>");
				sb.append("																	</tr>");
				sb.append("																	<tr height='20'>");
				sb.append(
						"																		<td align='left' class='dataHeader'>Secondary");
				sb.append("																			Match</td>");

				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(secondary_summary.has("NO-OF-OWN-MFIS") && secondary_summary.get("NO-OF-OWN-MFIS") != null
						? secondary_summary.get("NO-OF-OWN-MFIS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(secondary_summary.has("NO-OF-OTHER-MFIS") && secondary_summary.get("NO-OF-OTHER-MFIS") != null
						? secondary_summary.get("NO-OF-OTHER-MFIS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(secondary_summary.has("NO-OF-ACTIVE-ACCOUNTS")
						&& secondary_summary.get("NO-OF-ACTIVE-ACCOUNTS") != null
								? secondary_summary.get("NO-OF-ACTIVE-ACCOUNTS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(secondary_summary.has("NO-OF-CLOSED-ACCOUNTS")
						&& secondary_summary.get("NO-OF-CLOSED-ACCOUNTS") != null
								? secondary_summary.get("NO-OF-CLOSED-ACCOUNTS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='center'><span class='AccValue'>");
				sb.append(secondary_summary.has("NO-OF-DEFAULT-ACCOUNTS")
						&& secondary_summary.get("NO-OF-DEFAULT-ACCOUNTS") != null
								? secondary_summary.get("NO-OF-DEFAULT-ACCOUNTS") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(secondary_summary.has("TOTAL-OWN-DISBURSED-AMOUNT")
						&& secondary_summary.get("TOTAL-OWN-DISBURSED-AMOUNT") != null
								? secondary_summary.get("TOTAL-OWN-DISBURSED-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(secondary_summary.has("TOTAL-OTHER-DISBURSED-AMOUNT")
						&& secondary_summary.get("TOTAL-OTHER-DISBURSED-AMOUNT") != null
								? secondary_summary.get("TOTAL-OTHER-DISBURSED-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(secondary_summary.has("TOTAL-OWN-INSTALLMENT-AMOUNT")
						&& secondary_summary.get("TOTAL-OWN-INSTALLMENT-AMOUNT") != null
								? secondary_summary.get("TOTAL-OWN-INSTALLMENT-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(secondary_summary.has("TOTAL-OTHER-INSTALLMENT-AMOUNT")
						&& secondary_summary.get("TOTAL-OTHER-INSTALLMENT-AMOUNT") != null
								? secondary_summary.get("TOTAL-OTHER-INSTALLMENT-AMOUNT") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(secondary_summary.has("TOTAL-OWN-CURRENT-BALANCE")
						&& secondary_summary.get("TOTAL-OWN-CURRENT-BALANCE") != null
								? secondary_summary.get("TOTAL-OTHER-CURRENT-BALANCE") : "-");
				sb.append("																	</span>	</td>");
				sb.append(
						"																		<td align='right'><span class='AccValue'>");
				sb.append(secondary_summary.has("TOTAL-OTHER-CURRENT-BALANCE")
						&& secondary_summary.get("TOTAL-OTHER-CURRENT-BALANCE") != null
								? secondary_summary.get("TOTAL-OTHER-CURRENT-BALANCE") : "-");
				sb.append("																	</span>	</td>");
				sb.append("																	</tr>");
				sb.append("																</tbody>");
				sb.append("															</table>");
				sb.append("														</td>");
				sb.append("													</tr>");
				sb.append("													<tr>");
				sb.append("														<td height='10'></td>");
				sb.append("													</tr>");
				sb.append("												</tbody>");
				sb.append("											</table>");
				sb.append("										</td>");
				sb.append("									</tr>");
				sb.append("								</tbody>");
				sb.append("							</table>");
				sb.append("						</td>");
				sb.append("					</tr>");
				sb.append("				</tbody>");
				sb.append("			</table>");
				sb.append("		</td>");
				sb.append("	</tr>");
				sb.append("	<tr>");
				sb.append("		<td height='30px'></td>");
				sb.append("	</tr>");
				sb.append("			</tbody>");
				sb.append("		</table>");
				sb.append("	</div>");

				sb.append(
						"			<table align='center' border='0' cellpadding='0' style='width: 100%;' cellspacing='0'>");
				sb.append("				<tbody>");

				sb.append("	<tr>");
				sb.append("		<td>");
				sb.append(
						"			<table align='center' border='0' style='width: 100%;' cellpadding='0' cellspacing='0'>");
				sb.append("				<tbody>");
				sb.append("					<tr height='10'>");
				sb.append("						<td align='right' bgcolor='#FFFFFF' class='infoValue'></td>");
				sb.append("					</tr>");

				sb.append("					<tr>");
				sb.append("						<td>");
				sb.append(
						"							<table align='center' bgcolor='#0f3f6b' border='0' style='width: 100%;'>");
				sb.append("								<tbody>");
				sb.append("									<tr height='20'>");
				sb.append(
						"										<td style='padding: 2px;' class='mainHeader'>Account Details - Primary</td>");
				sb.append("									</tr>");
				sb.append("								</tbody>");
				sb.append("							</table>");
				sb.append("						</td>");
				sb.append("					</tr>");
				sb.append("				</tbody>");
				sb.append("			</table>");
				sb.append("		</td>");
				sb.append("	</tr>");
				JSONArray indv_response = null;
				try {
					if (indv_report.getJSONObject("INDV-RESPONSES").has("INDV-RESPONSE-LIST") && indv_report
							.getJSONObject("INDV-RESPONSES").getJSONObject("INDV-RESPONSE-LIST").has("INDV-RESPONSE")) {
						indv_response = indv_report.getJSONObject("INDV-RESPONSES").getJSONObject("INDV-RESPONSE-LIST")
								.getJSONArray("INDV-RESPONSE");

					}
				} catch (Exception e) {
					try {
						if (indv_report.getJSONObject("INDV-RESPONSES").has("INDV-RESPONSE-LIST") && indv_report
								.getJSONObject("INDV-RESPONSES").getJSONObject("INDV-RESPONSE-LIST").has("INDV-RESPONSE")) {
							try {
								indv_response = new JSONArray("[" + indv_report.getJSONObject("INDV-RESPONSES")
										.getJSONObject("INDV-RESPONSE-LIST").get("INDV-RESPONSE") + "]");
							} catch (Exception e2) {

							}
						}
					} catch (Exception e2) {
						logger.error("CrifPdfCreator || pdfCreation() || unable to get INDV-RESPONSE-LIST : "+e2);
					}
					
				}
				if (indv_response != null && indv_response.length() > 0) {
					int i = 0;
					while (indv_response.length() > i) {
						JSONObject jsonObj = indv_response.getJSONObject(i++);

						sb.append("	<tr height='20'>");
						sb.append("		<td></td>");
						sb.append("	</tr>");

						sb.append("	<tr>");
						sb.append("		<td>");
						sb.append("			<table style='width:98%' border='0' align='center' cellpadding='0'");
						sb.append("				cellspacing='0'>");
						sb.append("				<tr>");
						sb.append("					<td colspan='2'>");
						sb.append(
								"						<table align='center' border='0'  style='width:100%' cellpadding='0'");
						sb.append("							cellspacing='0'>");
						sb.append("							<tbody>");
						sb.append("								<tr height='20'>");
						sb.append(
								"									<td height='20' align='center' class='mainAccHeader'");
						sb.append("										width='20px'>" + i + "</td>");
						sb.append("									<td height='20' align='center' width='0px'></td>");
						sb.append("									<td align='center'>");
						sb.append(
								"										<table align='left' border='0'  style='width:100%;background-color:#e6e6ff;'");
						sb.append("											cellpadding='2' cellspacing='0'>");
						sb.append("											<tbody>");
						sb.append("												<tr height='20'>");
						sb.append(
								"													<td align='left'  style='width:100%' class='AccHeader'>Member");
						sb.append(
								"														& Account Information with Credit Grantor: <font");
						sb.append("														class='maroonFields'>");
						sb.append(jsonObj.has("MFI") && jsonObj.get("MFI") != null ? jsonObj.get("MFI") : "");
						sb.append(" (Branch: "
								+ (jsonObj.has("BRANCH") && jsonObj.get("BRANCH") != null ? jsonObj.get("BRANCH") : "")
								+ ") ");
						sb.append("												</font>	</td>");
						sb.append("												</tr>");
						sb.append("											</tbody>");
						sb.append("										</table>");
						sb.append("									</td>");
						sb.append("								</tr>");
						sb.append("							</tbody>");
						sb.append("						</table>");
						sb.append("					</td>");
						sb.append("				</tr>");
						sb.append("			</table>");
						sb.append("		</td>");
						sb.append("	</tr>");
						sb.append("	<tr>");
						sb.append("		<td>");

						sb.append("									<table align='center' border='0' cellpadding='0'");
						sb.append("										cellspacing='0' style='width:98%'>");
						sb.append("										<tbody>");
						sb.append("											<tr>");
						sb.append("												<td>");
						sb.append(
								"													<table align='center' border='0' style='width:100%'>");
						sb.append("														<tbody>");
						sb.append("															<tr>");
						sb.append("																<td>");
						sb.append(
								"																	<table border='0' style='width:100%'>");
						sb.append("																		<tbody>");
						sb.append("																			<tr>");
						sb.append(
								"																				<td height='10px'></td>");
						sb.append("																			</tr>");
						sb.append(
								"																			<tr style='background-color: #eef2f5;'>");
						sb.append(
								"																				<td align='left' class='dataHeader'>Member");
						sb.append(
								"																					Name:</td>");
						sb.append(
								"																				<td align='left'  class='dataValue'>");
						sb.append(jsonObj.has("NAME") && jsonObj.get("NAME") != null ? jsonObj.get("NAME") : "");
						sb.append(
								"																				</td><td  class='dataHeader'>DOB/Age:</td>");
						sb.append(
								"																				<td  class='dataValue'>");
						sb.append(jsonObj.has("DOB") && jsonObj.get("DOB") != null ? jsonObj.get("DOB") : "");
						sb.append(jsonObj.has("AGE") && jsonObj.get("AGE") != null ? " / " + jsonObj.get("AGE") : "");
						sb.append(jsonObj.has("AGE-AS-ON") && jsonObj.get("AGE-AS-ON") != null
								? " (" + jsonObj.get("AGE-AS-ON") + ") " : "");
						sb.append(
								"																				</td><td  class='dataHeader'>Info. As");
						sb.append(
								"																					On:</td>");
						sb.append(
								"																				<td  class='dataValue'>");
						sb.append(jsonObj.has("INSERT-DATE") && jsonObj.get("INSERT-DATE") != null
								? jsonObj.get("INSERT-DATE") : "");
						sb.append("																		</td>	</tr>");
						sb.append("																			<tr>");
						sb.append(
								"																				<td height='3px'></td>");
						sb.append("																			</tr>");
						sb.append("																			<tr>");
						sb.append(
								"																				<td class='dataHeader' valign='top' >Relationships:</td>");
						sb.append(
								"																				<td valign='top'>");
						sb.append(
								"																					<table style='width:100%' cellpadding='0'");
						sb.append(
								"																						cellspacing='0'>");
						JSONArray relation = null;
						try {
							if (jsonObj.has("RELATIONS")) {
								relation = jsonObj.getJSONObject("RELATIONS").getJSONArray("RELATION");
							}
						} catch (Exception e) {
							if (jsonObj.has("RELATIONS")) {
								try {
									relation = new JSONArray(
											"[" + jsonObj.getJSONObject("RELATIONS").get("RELATION") + "]");

								} catch (Exception e2) {

								}
							}
						}
						int j = 0;
						while (relation != null && j < relation.length()) {
							sb.append(
									"																						<tr>");
							sb.append(
									"																							<td class='dataValue'>");
							sb.append(relation.getJSONObject(j).has("NAME")
									&& relation.getJSONObject(j).get("NAME") != null
											? relation.getJSONObject(j).get("NAME") : "");
							sb.append(" " + (relation.getJSONObject(j).has("TYPE")
									&& relation.getJSONObject(j).get("TYPE") != null
											? relation.getJSONObject(j).get("TYPE") : ""));
							sb.append(
									"																						</td></tr>");
							j++;
						}
						sb.append(
								"																						<tr>");
						sb.append(
								"																							<td class='dataValue'></td>");
						sb.append(
								"																						</tr>");
						sb.append(
								"																					</table>");
						sb.append("																				</td>");
						sb.append(
								"																				<td class='dataHeader' valign='top'>ID(s):</td>");
						sb.append(
								"																				<td valign='top'>");
						sb.append(
								"																					<table style='width:100%' cellpadding='0'");
						sb.append(
								"																						cellspacing='0'>");
						JSONArray Ids = null;
						try {
							if (jsonObj.has("IDS")) {
								Ids = jsonObj.getJSONObject("IDS").getJSONArray("ID");
							}
						} catch (Exception e) {
							if (jsonObj.has("IDS")) {
								try {
									Ids = new JSONArray("[" + jsonObj.getJSONObject("IDS").get("ID") + "]");

								} catch (Exception e2) {

								}
							}
						}

						j = 0;
						while (Ids != null && j < Ids.length()) {
							sb.append(
									"																						<tr>");
							sb.append(
									"																							<td class='dataValue'>");
							sb.append(Ids.getJSONObject(j).has("VALUE") && Ids.getJSONObject(j).get("VALUE") != null
									? Ids.getJSONObject(j).get("VALUE") : "");
							sb.append(Ids.getJSONObject(j).has("TYPE") && Ids.getJSONObject(j).get("TYPE") != null
									? " " + Ids.getJSONObject(j).get("TYPE") : "");
							sb.append(
									"																						</td></tr>");
							j++;
						}

						sb.append(
								"																						<tr>");
						sb.append(
								"																							<td class='dataValue'></td>");
						sb.append(
								"																						</tr>");
						sb.append(
								"																					</table>");
						sb.append("																				</td>");
						sb.append(
								"																				<td class='dataHeader' valign='top' width='100 px'>Phone");
						sb.append(
								"																					Numbers:</td>");
						sb.append(
								"																				<td valign='top'>");
						sb.append(
								"																					<table style='width:100%' cellpadding='0'");
						sb.append(
								"																						cellspacing='0'>");
						sb.append(
								"																						<tr>");
						sb.append(
								"																							<td class='dataValue'></td>");
						sb.append(
								"																						</tr>");
						sb.append(
								"																					</table>");
						sb.append("																				</td>");
						sb.append("																			</tr>");
						sb.append("																			<tr>");
						sb.append(
								"																				<td height='3px'></td>");
						sb.append("																			</tr>");
						sb.append(
								"																			<tr style='background-color: #eef2f5;'>");
						sb.append(
								"																				<td align='left' class='dataHeader'>Current");
						sb.append(
								"																					Address:</td>");
						sb.append(
								"																				<td align='left'  class='dataValue'");
						sb.append(
								"																					colspan='5'>");
						sb.append(
								"																					<div style='width: 890px; overflow: none'>");
						sb.append(jsonObj.has("ADDRESSES") && jsonObj.getJSONObject("ADDRESSES").has("ADDRESS")
								&& jsonObj.getJSONObject("ADDRESSES").get("ADDRESS") != null
										? jsonObj.getJSONObject("ADDRESSES").get("ADDRESS") : "");
						sb.append(
								"																		</div>	</td>	</tr>");
						sb.append("																			<tr>");
						sb.append(
								"																				<td height='3px'></td>");
						sb.append("																			</tr>");
						sb.append("																			<tr>");
						sb.append(
								"																				<td align='left'  class='dataHeader'>Other");
						sb.append(
								"																					Address:</td>");
						sb.append(
								"																				<td align='left'  class='dataValue'");
						sb.append(
								"																					colspan='5'>");
						sb.append(
								"																					<div style='width: 890px; overflow: none'></div>");
						sb.append("																				</td>");
						sb.append("																			</tr>");
						sb.append("																		</tbody>");
						sb.append("																	</table>");
						sb.append("																</td>");
						sb.append("															</tr>");
						sb.append("														</tbody>");
						sb.append("													</table>");
						sb.append("												</td>");
						sb.append("											</tr>");
						sb.append("										</tbody>");
						sb.append("									</table>");
						sb.append("								</td>");
						sb.append("							</tr>");
						sb.append("							<tr>");
						sb.append("								<td colspan='2'>");
						sb.append(
								"									<table align='center' cellpadding='0'style='width:98%' cellspacing='0'>");
						sb.append("										<tr>");
						sb.append(
								"											<td align='center' colspan='1' height='15' style='width:100%'>");
						sb.append(
								"												<hr size='1' style='color: #A7CBE3;' />");
						sb.append("											</td>");
						sb.append("										</tr>");
						sb.append("									</table>");
						sb.append("								</td>");
						sb.append("							</tr>");
						sb.append("							<tr>");
						sb.append("								<td style='width:100%'>");
						if (jsonObj.has("LOAN-DETAIL") && jsonObj.getJSONObject("LOAN-DETAIL") != null) {

							JSONObject loan_details = jsonObj.getJSONObject("LOAN-DETAIL");

							sb.append(
									"									<table align='center' style='width:98%' cellpadding='0' cellspacing='0'>");
							sb.append("										<tr>");
							if (loan_details.has("STATUS") && loan_details.get("STATUS") != null
									&& loan_details.getString("STATUS").trim().toLowerCase().equals("active")) {

								sb.append("							<td class='container'  width='25px'");
								sb.append(
										"								style='color: Maroon; background-color: #ffe1dc; text-align: center; padding-top: 5px; padding-bottom: 5px;'>");
								sb.append("								A<br /> C<br /> T<br /> I<br /> V<br /> E");
								sb.append("							</td>");

							} else if (loan_details.has("STATUS") && loan_details.get("STATUS") != null
									&& loan_details.getString("STATUS").trim().toLowerCase().equals("closed")) {
								sb.append("							<td class='container'  width='25px'");
								sb.append(
										"								style='color: #415a05; background-color: #e1f0be; text-align: center; padding-top: 5px; padding-bottom: 5px;'>");
								sb.append("								C<br /> L<br /> O<br /> S<br /> E<br /> D");
								sb.append("							</td>");
							}
							sb.append("											<td>");
							sb.append("												<table style='width:100%'>");
							sb.append("													<tr>");
							sb.append("														<td>");
							sb.append(
									"															<table align='center' border='0' cellpadding='0'");
							sb.append(
									"																cellspacing='0' style='width:98%'>");
							sb.append("																<tbody>");
							sb.append(
									"																	<tr height='10'></tr>");
							sb.append(
									"																	<tr height='25'>");
							sb.append(
									"																		<td  class='dataHeader'>&nbsp;&nbsp;Account");
							sb.append(
									"																			Type:</td>");
							sb.append(
									"																		<td  class='dataValue'>");
							sb.append(loan_details.has("ACCT-TYPE") && loan_details.get("ACCT-TYPE") != null
									? loan_details.get("ACCT-TYPE") : "");
							sb.append(
									"																		</td><td  class='dataHeader'>Disbursed Date:</td>");
							sb.append(
									"																		<td  class='dataValue'>");
							sb.append(loan_details.has("DISBURSED-DT") && loan_details.get("DISBURSED-DT") != null
									? loan_details.get("DISBURSED-DT") : "");
							sb.append(
									"																		</td><td ' class='dataHeader'>Amt Disbursed:</td>");
							sb.append(
									"																		<td  align='right' class='dataAmtValue'>");
							sb.append(loan_details.has("DISBURSED-AMT") && loan_details.get("DISBURSED-AMT") != null
									? loan_details.get("DISBURSED-AMT") : "");
							sb.append(
									"																		</td><td  class='dataHeader'>Info. As On:</td>");
							sb.append(
									"																		<td  class='dataValue'>");
							sb.append(loan_details.has("INFO-AS-ON") && loan_details.get("INFO-AS-ON") != null
									? loan_details.get("INFO-AS-ON") : "");
							sb.append("																	</td></tr>");
							sb.append(
									"																	<tr height='25'>");
							sb.append(
									"																		<td  class='dataHeader'>&nbsp;&nbsp;Account");
							sb.append(
									"																			#:</td>");
							sb.append(
									"																		<td  class='dataValue'>");
							sb.append(loan_details.has("ACCT-NUMBER") && loan_details.get("ACCT-NUMBER") != null
									? loan_details.get("ACCT-NUMBER") : "");
							sb.append(
									"																		</td><td  class='dataHeader'>Closed Date:</td>");
							sb.append(
									"																		<td  class='dataValue'>");
							sb.append(loan_details.has("CLOSED-DT") && loan_details.get("CLOSED-DT") != null
									? loan_details.get("CLOSED-DT") : "");
							sb.append(
									"																		</td><td  class='dataHeader'>Current");
							sb.append(
									"																			Balance:</td>");
							sb.append(
									"																		<td  align='right' class='dataAmtValue'>");
							sb.append(loan_details.has("CURRENT-BAL") && loan_details.get("CURRENT-BAL") != null
									? loan_details.get("CURRENT-BAL") : "");
							sb.append(
									"																		  </td><td  class='dataHeader'>Amount");
							sb.append(
									"																			Write-Off:</td>");
							sb.append(
									"																		<td  class='dataValue' align='right'>0");
							sb.append(loan_details.has("WRITE-OFF-AMT") && loan_details.get("WRITE-OFF-AMT") != null
									? loan_details.get("WRITE-OFF-AMT") : "");
							sb.append("																		</td>");
							sb.append("																	</tr>");
							sb.append(
									"																	<tr height='25'>");
							sb.append(
									"																		<td  class='dataHeader'>&nbsp;&nbsp;Instl");
							sb.append(
									"																			Freq:</td>");
							sb.append(
									"																		<td  class='dataValue'>WEEKLY</td>");
							sb.append(loan_details.has("WRITE-OFF-AMT") && loan_details.get("WRITE-OFF-AMT") != null
									? loan_details.get("WRITE-OFF-AMT") : "");
							sb.append(
									"																		<td  class='dataHeader'>Instl Amount:</td>");
							sb.append(
									"																		<td  class='dataValue'>");
							sb.append(loan_details.has("INSTALLMENT-AMT") && loan_details.get("INSTALLMENT-AMT") != null
									? loan_details.get("INSTALLMENT-AMT") : "");
							sb.append(
									"																		</td><td  class='dataHeader'>Amount");
							sb.append(
									"																			Overdue:</td>");
							sb.append(
									"																		<td  align='right' class='dataAmtValue'>");
							sb.append(loan_details.has("OVERDUE-AMT") && loan_details.get("OVERDUE-AMT") != null
									? loan_details.get("OVERDUE-AMT") : "");
							sb.append(
									"																		</td><td  class='dataHeader'>DPD:</td>");
							sb.append(
									"																		<td  class='dataValue'>");
							sb.append(loan_details.has("DPD") && loan_details.get("DPD") != null
									? loan_details.get("DPD") : "");
							sb.append("																	</td></tr>");
							sb.append("																</tbody>");
							sb.append("															</table>");
							sb.append("														</td>");
							sb.append("													</tr>");

							sb.append("													<tr>");
							sb.append("														<td>");
							sb.append("	<div align='center' style='padding-left: 10px;'>");
							sb.append("		<table align='center' border='0' cellpadding='0' cellspacing='0'");
							sb.append("			style='width: 99%'>");
							sb.append("			<tbody>");
							sb.append("				<tr>");
							sb.append("					<td>");
							sb.append("						<table style='width: 100%'>");
							sb.append("							<tbody>");
							sb.append("								<tr>");
							sb.append(
									"									<td class='dataHeader' height='25'>Payment History/Asset");
							sb.append("										Classification:</td>");
							sb.append("								</tr>");
							sb.append("							</tbody>");
							sb.append("						</table>");

							if (loan_details.has("COMBINED-PAYMENT-HISTORY")) {

								TreeSet<String> yearSet = new TreeSet<String>();

								String[] histroy = loan_details.get("COMBINED-PAYMENT-HISTORY").toString().split("\\|");
								StringBuilder s = new StringBuilder(
										loan_details.get("COMBINED-PAYMENT-HISTORY").toString());
								int firstIndex = s.indexOf(":", 0) + 1;
								int temp = 0;
								while (firstIndex < s.length()) {
									int lastIndex = s.indexOf(",", firstIndex);
									yearSet.add(s.substring(firstIndex, lastIndex));
									temp = s.indexOf(":", lastIndex) + 1;
									if (temp > firstIndex) {
										firstIndex = temp;
									} else {
										break;
									}
								}
								HashMap<String, Integer> hm = new HashMap<String, Integer>();
								hm.put("Jan", 0);
								hm.put("Feb", 1);
								hm.put("Mar", 2);
								hm.put("Apr", 3);
								hm.put("May", 4);
								hm.put("Jun", 5);
								hm.put("Jul", 6);
								hm.put("Aug", 7);
								hm.put("Sep", 8);
								hm.put("Oct", 9);
								hm.put("Nov", 10);
								hm.put("Dec", 11);
								String[][] myHistrory = new String[yearSet.size()][12];
								for (String str : histroy) {
									String value = str.split(",")[1];

									int l = 0;
									for (String year : yearSet) {
										if ((str.split(",")[0].split(":")[1]).equals(year)) {
											myHistrory[l][hm.get(str.split(",")[0].split(":")[0])] = value;
										}
										l++;
									}

								}

								sb.append("						<table style='width: 100%'>");
								sb.append("							<tbody>");
								sb.append("								<tr>");
								sb.append("									<td>");
								sb.append("										<table align='center' border='0'");
								sb.append(
										"											style='border-collapse: collapse; table-layout: fixed; width: 100%; border-color: #A7CBE3;'>");
								sb.append("											<tbody>");
								sb.append(
										"												<tr style='height:20px;text-align:center' >");
								sb.append(
										"													<td style='border: 0px solid #fff;'></td>");
								sb.append(
										"													<td class='subAccHeader'>January</td>");
								sb.append(
										"													<td class='subAccHeader'>February</td>");
								sb.append(
										"													<td class='subAccHeader'>March</td>");
								sb.append(
										"													<td class='subAccHeader'>April</td>");
								sb.append(
										"													<td class='subAccHeader'>May</td>");
								sb.append(
										"													<td class='subAccHeader'>June</td>");
								sb.append(
										"													<td class='subAccHeader'>July</td>");
								sb.append(
										"													<td class='subAccHeader'>August</td>");
								sb.append(
										"													<td class='subAccHeader'>September</td>");
								sb.append(
										"													<td class='subAccHeader'>October</td>");
								sb.append(
										"													<td class='subAccHeader'>November</td>");
								sb.append(
										"													<td class='subAccHeader'>December</td>");
								sb.append("												</tr>");
								int size = yearSet.size();
								for (int p = 0; p < size; p++) {

									sb.append(
											"												<tr style='height:20px; text-align:center'>");
									sb.append(
											"													<td class='AccValue1' bgcolor='e6e6ff'>"
													+ (yearSet.first() != null ? yearSet.first() : "").toString()
													+ "</td>");
									try {
										yearSet.remove(yearSet.first());
									} catch (Exception e) {
									}
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][0] != null ? myHistrory[p][0] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][1] != null ? myHistrory[p][1] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][2] != null ? myHistrory[p][2] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][3] != null ? myHistrory[p][3] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][4] != null ? myHistrory[p][4] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][5] != null ? myHistrory[p][5] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][6] != null ? myHistrory[p][6] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][7] != null ? myHistrory[p][7] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][8] != null ? myHistrory[p][8] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][9] != null ? myHistrory[p][9] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][10] != null ? myHistrory[p][10] : "-").toString()
													+ "</td>");
									sb.append(
											"													<td class='AccValue1' bgcolor='#FFFFFF'>"
													+ (myHistrory[p][11] != null ? myHistrory[p][11] : "-").toString()
													+ "</td>");
									sb.append("												</tr>");

								}

								sb.append("											</tbody>");
								sb.append("										</table>");
								sb.append("									</td>");
								sb.append("								</tr>");
								sb.append("							</tbody>");
								sb.append("						</table>");

							}
							sb.append("					</td>");
							sb.append("				</tr>");
							sb.append("			</tbody>");
							sb.append("		</table>");
							sb.append("	</div>");

						}

						sb.append("														</td>");
						sb.append("													</tr>");

						sb.append("												</table>");
						sb.append("											</td>");
						sb.append("										</tr>");
						sb.append("									</table>");

						sb.append("								</td>");
						sb.append("							</tr>");
					}
				}
				sb.append("				</tbody>");
				sb.append("			</table> <br></br>");

				sb.append(
						"			<table align='center' border='0' cellpadding='0' style='width: 100%;' cellspacing='0'>");
				sb.append("				<tbody>");

				sb.append("	<tr>");
				sb.append("		<td>");
				sb.append(
						"			<table align='center' border='0' style='width: 100%;' cellpadding='0' cellspacing='0'>");
				sb.append("				<tbody>");
				sb.append("					<tr height='10'>");
				sb.append("						<td align='right' bgcolor='#FFFFFF' class='infoValue'></td>");
				sb.append("					</tr>");

				sb.append("					<tr>");
				sb.append("						<td>");
				sb.append(
						"							<table align='center' bgcolor='#0f3f6b' border='0' style='width: 100%;'>");
				sb.append("								<tbody>");
				sb.append("									<tr height='20'>");
				sb.append(
						"										<td style='padding: 2px;' class='mainHeader'>Inquiries (reported for past 24");
				sb.append("												months)</td>");
				sb.append("									</tr>");
				sb.append("								</tbody>");
				sb.append("							</table>");
				sb.append("						</td>");
				sb.append("					</tr>");
				sb.append("				</tbody>");
				sb.append("			</table>");
				sb.append("		</td>");
				sb.append("	</tr>");

				sb.append("		<tr>");
				sb.append("			<td height='5'></td>");
				sb.append("		</tr>");
				sb.append("		<tr>");
				sb.append("			<td>");
				sb.append(
						"				<table cellpadding='0'  style='width: 98%'  cellspacing='0' align='center' border='0'>");
				sb.append("					<tbody>");
				sb.append("						<tr>");
				sb.append("							<td>");
				sb.append("								<table class='box1' cellpadding='0' cellspacing='0'");
				sb.append("									 style='width: 100%' align='center' border='0px'>");
				sb.append("									<tbody>");
				sb.append("										<tr height='20'>");
				sb.append(
						"											<td height='20'  class='subHeader' align='left'>Credit");
				sb.append("												Grantor</td>");
				sb.append("											<td  class='subHeader' align='right'>Date of");
				sb.append("												Inquiry</td>");
				sb.append("											<td  class='subHeader' align='left'>Purpose</td>");
				sb.append("											<td  class='subHeader' align='right'>Amount</td>");
				sb.append("											<td  class='subHeader' align='left'>Remark</td>");
				sb.append("										</tr>");
				sb.append("									</tbody>");
				sb.append("								</table>");
				sb.append("					</td>");
				sb.append("					</tr>");
				sb.append("					</tbody>");
				sb.append("				</table>");
				sb.append("		</td>");
				sb.append("		</tr>");
				sb.append("		<tr>");
				sb.append("			<td>");
				sb.append(
						"				<table align='center' border='0'  style='width: 100%'  cellpadding='0' cellspacing='0'>");
				sb.append("					<tbody>");
				sb.append("						<tr>");
				sb.append("							<td>");
				sb.append("								<table  style='width: 100%'>");
				sb.append("									<tbody>");
				sb.append("										<tr>");
				sb.append(
						"											<td align='center' class='AccHeader'>-END OF INDIVIDUAL");
				sb.append("												REPORT-</td>");
				sb.append("										</tr>");
				sb.append("									</tbody>");
				sb.append("								</table>");
				sb.append("					</td>");
				sb.append("					</tr>");
				sb.append("					<tr>");
				sb.append("						<td height='10'></td>");
				sb.append("					</tr>");
				sb.append("					<tr>");
				sb.append("						<td>");
				sb.append(
						"							<table align='center'  style='width: 100%' border='0' cellpadding='0' cellspacing='0'>");
				sb.append("								<tbody>");
				sb.append("									<tr height='10'></tr>");
				sb.append("									<tr>");
				sb.append("										<td>");
				sb.append(
						"											<table align='center' bgcolor='#0f3f6b' border='0'");
				sb.append("												 style='width: 100%'>");
				sb.append("												<tbody>");
				sb.append("									<tr height='20'>");
				sb.append(
						"										<td style='padding: 2px;' class='mainHeader'>Appendix</td>");
				sb.append("									</tr>");
				sb.append("												</tbody>");
				sb.append("											</table>");
				sb.append("								</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='10'></tr>");
				sb.append("								</tbody>");
				sb.append("							</table>");
				sb.append("					</td>");
				sb.append("					</tr>");
				sb.append("					<tr>");
				sb.append("						<td height='5'></td>");
				sb.append("					</tr>");
				sb.append("					<tr>");
				sb.append("						<td>");
				sb.append(
						"							<table align='center' style='width: 100%' border='0' cellpadding='0' cellspacing='0'>");
				sb.append("								<tbody>");
				sb.append("									<tr>");
				sb.append("										<td>");
				sb.append("											<table class='box1' align='center' border='0px'");
				sb.append(
						"												cellpadding='0' cellspacing='0'  style='width: 98%'>");
				sb.append("												<tbody>");
				sb.append("													<tr height='20'>");
				sb.append(
						"														<td align='left' class='subHeader' width='250'>Section</td>");
				sb.append(
						"														<td align='left' class='subHeader' width='220'>Code</td>");
				sb.append(
						"														<td align='left' class='subHeader' width='480'>Description</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Account");
				sb.append("															Summary</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>Number");
				sb.append("															of Delinquent Accounts</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>");
				sb.append(
						"															Indicates number of accounts that the applicant has");
				sb.append(
						"															defaulted on within the last 6 months</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20' bgcolor='#F1F3F5'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Account");
				sb.append("															Information - Credit Grantor</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>XXXX</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Name");
				sb.append(
						"															of grantor undisclosed as credit grantor is different");
				sb.append("															from inquiring institution</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Account");
				sb.append("															Information - Account #</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>xxxx</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>");
				sb.append(
						"															Account Number undisclosed as credit grantor is different");
				sb.append("															from inquiring institution</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20' bgcolor='#F1F3F5'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Payment");
				sb.append(
						"															History / Asset Classification</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>XXX</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Data");
				sb.append("															not reported by institution</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Payment");
				sb.append(
						"															History / Asset Classification</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>&nbsp;&nbsp;&nbsp;&nbsp;-</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Not");
				sb.append("															applicable</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20' bgcolor='#F1F3F5'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Payment");
				sb.append(
						"															History / Asset Classification</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>STD</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Account");
				sb.append("															Reported as STANDARD Asset</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Payment");
				sb.append(
						"															History / Asset Classification</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>SUB</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Account");
				sb.append(
						"															Reported as SUB-STANDARD Asset</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20' bgcolor='#F1F3F5'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Payment");
				sb.append(
						"															History / Asset Classification</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>DBT</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Account");
				sb.append("															Reported as DOUBTFUL Asset</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Payment");
				sb.append(
						"															History / Asset Classification</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>LOS</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Account");
				sb.append("															Reported as LOSS Asset</td>");
				sb.append("													</tr>");
				sb.append("													<tr height='20' bgcolor='#F1F3F5'>");
				sb.append(
						"														<td align='left' class='dataValue1' width='250'>Payment");
				sb.append(
						"															History / Asset Classification</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='220'>SMA</td>");
				sb.append(
						"														<td align='left' class='dataValue1' width='480'>Account");
				sb.append("															Reported as SPECIAL MENTION</td>");
				sb.append("													</tr>");
				sb.append("												</tbody>");
				sb.append("											</table>");
				sb.append("								</td>");
				sb.append("								</tr>");
				sb.append("								</tbody>");
				sb.append("							</table>");
				sb.append("					</td>");
				sb.append("					</tr>");
				sb.append("					<tr>");
				sb.append("						<td height='20'></td>");
				sb.append("					</tr>");
				sb.append("					</tbody>");
				sb.append("				</table>");
				sb.append("");
				sb.append("		</td>");
				sb.append("		</tr>");
				// sb.append(" <tfoot>");
				sb.append("			<tr>");
				sb.append("				<td>");
				sb.append(
						"					<table summary='' align='center'style='width: 100%' border='0' cellpadding='0'");
				sb.append("						cellspacing='0'>");
				sb.append("						<tbody>");
				sb.append("							<tr>");
				sb.append("								<td>");
				sb.append("									<table summary='' border='0' style='width: 100%'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='10'>");
				sb.append("												<td colspan='5'>");
				sb.append("													<hr color='silver'></hr>");
				sb.append("												</td>");
				sb.append("											</tr>");
				sb.append("											<tr>");
				sb.append("												<td color='#CCCCCC' valign='top' width='70px'");
				sb.append("													class='disclaimerValue'>Disclaimer:</td>");
				sb.append(
						"												<td colspan='4' class='disclaimerValue'>This document");
				sb.append(
						"													contains proprietary information to CRIF High Mark and may");
				sb.append(
						"													not be used or disclosed to others, except with the written");
				sb.append(
						"													permission of CRIF High Mark. Any paper copy of this");
				sb.append(
						"													document will be considered uncontrolled. If you are not");
				sb.append(
						"													the intended recipient, you are not authorized to read,");
				sb.append(
						"													print, retain, copy, disseminate, distribute, or use this");
				sb.append("													information or any part thereof.</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='10'>");
				sb.append("												<td colspan='5'>");
				sb.append("													<hr color='silver'></hr>");
				sb.append("												</td>");
				sb.append("											</tr>");
				sb.append("											<tr>");
				sb.append("												<td><br></br> <br></br></td>");
				sb.append("												<td color='#CCCCCC' align='left' width='300'");
				sb.append(
						"													class='disclaimerValue'>Copyrights reserved (c) 2018</td>");
				sb.append(
						"												<td color='#CCCCCC' align='center' width='400'");
				sb.append(
						"													class='disclaimerValue'>CRIF High Mark Credit");
				sb.append("													Information Services Pvt. Ltd</td>");
				sb.append("												<td color='#CCCCCC' align='right' width='300'");
				sb.append(
						"													class='disclaimerValue'>Company Confidential Data</td>");
				sb.append("												<td width='70'><br></br> <br></br></td>");
				sb.append("											</tr>");
				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("						</td>");
				sb.append("						</tr>");
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("		</td>");
				sb.append("		</tr>");
				sb.append("				</tbody>");
				sb.append("			</table> <br></br>");
				sb.append("</body>");
				sb.append("</html>");
			}
			/* byteArray= */htmlToPdf(sb.toString(), fileName, resProp, crifTrackerDTO);
//			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
			// logger.info("CrifPdfCreator || pdfCreation() || EXception ::
			// "+e);
		}
		// logger.info("CrifPdfCreator || pdfCreation() || ByteArray
		// ::"+byteArray);
		// logger.info("CrifPdfCreator || pdfCreation() || END");
		return byteArray;
	}

	public String pdfCreation(CrifTrackerDTO crifTrackerDTO, String fileName) {
		String byteArray = null;
		logger.info("CrifPdfCreator || pdfCreation() || Start");
		logger.info("CrifPdfCreator || pdfCreation() || file Name :: " + fileName);
		fileName += com.qualtech.crif.api.utils.UniqueId.getUniqueId();
		try {
			// System.out.println(crifTrackerDTO.getCrifApiIssueXmlRes());
			JSONObject response = XML.toJSONObject(crifTrackerDTO.getCrifApiIssueXmlRes());
			JSONObject indv_report = response.getJSONObject("INDV-REPORT-FILE").getJSONObject("INDV-REPORTS")
					.getJSONObject("INDV-REPORT");
//			System.out.println(indv_report);
			JSONObject header = indv_report.getJSONObject("HEADER");

			try {
				header.get("DATE-OF-REQUEST");
			} catch (Exception e) {
				logger.error("CrifPdfCreator || pdfCreation() || HEADER :: " + e);
			}
			JSONObject request = indv_report.getJSONObject("REQUEST");
			JSONObject personalInfoVariation = indv_report.getJSONObject("PERSONAL-INFO-VARIATION");
			JSONObject accountsSummary = indv_report.getJSONObject("ACCOUNTS-SUMMARY");

			//JSONObject scores = indv_report.getJSONObject("SCORES");

			StringBuffer sb = new StringBuffer();

			if (header != null && request != null) {
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<title>Consumer Base Report</title>");
				sb.append("</head>");
				sb.append("<body");
				sb.append("	style='font-family: segoe ui semibold, arial, verdana; text-align: center;'>");
				sb.append("	<style>");
				sb.append(".subAccHeader, .AccValue1, .AccValueComm2 {");
				sb.append("	border: 1px solid #A7CBE3;");
				sb.append("	padding: 3px;");
				sb.append("}");
				sb.append("</style>");

				sb.append("	<table align='center' border='0' width='1120'>");
				sb.append("		<tbody>");
				sb.append("			<tr height='10'>");
				sb.append("				<td></td>");
				sb.append("			</tr>");
				sb.append("			<tr>");
				sb.append("				<td colspan='2' valign='top' style='margin-top: -15px;'><img src='");
				//////// Set here logo path
				try {
					sb.append(resProp.getString("com.qc.crif.pdf.logo"));
				} catch (Exception e) {
					logger.error("CrifPdfCreator || pdfCreation() || LOGO :: " + e);
				}

				//////
				sb.append("					' alt='CRIF HighMark Credit Information Services Pvt. Ltd.'");
				sb.append("					align='left' width='153' style='margin-top: -15px;' height='58' /></td>");
				sb.append("				<td width='35'></td>");
				sb.append("				<td align='left' width='400' valign='top'>");
				sb.append("					<table border='0' cellpadding='0' cellspacing='0'>");
				sb.append("						<tbody>");
				sb.append("							<tr>");
				sb.append("								<td height='5' style='color: white;'>h</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td align='left' class='reportHead'>CONSUMER BASE&trade;");
				sb.append("									REPORT <br />");
				sb.append("								</td>");
				sb.append("							</tr>");
				sb.append("							<tr valign='top'>");
				sb.append("								<td class='dataHead' align='right' valign='top'>For "
						+ request.get("NAME"));
				sb.append("									</td>");
				sb.append("							</tr>");
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("				</td>");
				sb.append("				<td width='60'></td>");
				sb.append("				<td rowspan='2' align='right' valign='top' width='400'>");
				sb.append("					<table>");
				sb.append("						<tbody>");
				sb.append("							<tr>");
				sb.append("								<td class='dataHeader1'>CHM Ref #:</td>");
				sb.append("								<td class='dataValue1'>");

				sb.append(header.has("REPORT-ID") && header.get("REPORT-ID") != null ? header.get("REPORT-ID") : "");
				sb.append("</td>							</tr>");

				sb.append("							<tr>");
				sb.append("								<td class='dataHeader1'>Prepared For:</td>");
				sb.append("								<td class='dataValue1'>");
				if (header.toString().endsWith("PREPARED-FOR")) {
					sb.append(header.has("PREPARED-FOR") && header.get("PREPARED-FOR") != null
							? header.get("PREPARED-FOR") : "");// need to check
				}
				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataHeader1'>Application ID:</td>");
				sb.append("								<td class='dataValue1'>");

				sb.append(request.has("LOS-APP-ID") && request.get("LOS-APP-ID") != null
						? request.get("LOS-APP-ID").toString().toUpperCase() : "");
				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataHeader1'>Date of Request:</td>");
				sb.append("								<td class='dataValue1'>");
				sb.append(header.has("DATE-OF-REQUEST") && header.get("DATE-OF-REQUEST") != null
						? header.get("DATE-OF-REQUEST") : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataHeader1'>Date of Issue:</td>");
				sb.append("								<td class='dataValue1'>");

				sb.append(header.has("DATE-OF-ISSUE") && header.get("DATE-OF-ISSUE") != null
						? header.get("DATE-OF-ISSUE") : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("				</td>");
				sb.append("			</tr>");
				sb.append("			<tr>");
				sb.append("				<td height='10'></td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<table align='center' bgcolor='#0f3f6b' border='0' style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr height='20'>");
				sb.append("				<td style='padding: 5px;' class='mainHeader'>Inquiry Input");
				sb.append("					Information</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<hr size='1' style='color: #C8C8C8;' />");
				sb.append("	<div align='center' style='padding-left: 20px;'>");
				sb.append("		<table border='0' style='width: 98%; border-collapse: collapse;'>");
				sb.append("			<tbody>");
				sb.append("				<tr>");
				sb.append("					<td height='10'></td>");
				sb.append("				</tr>");
				sb.append("				<tr style='background-color: #eef2f5;'>");
				sb.append("					<td align='left' class='dataHeader'>Name:</td>");
				sb.append("					<td align='left' class='dataValue'>");

				sb.append(request.has("NAME") && request.get("NAME") != null ? request.get("NAME") : "");
				sb.append("</td>");
				sb.append("					<td class='dataHeader'>DOB/Age:</td>");
				sb.append("					<td class='dataValue'>");

				sb.append(request.has("DOB") && request.get("DOB") != null ? request.get("DOB") : "");
				sb.append(" / ");
				sb.append(request.has("AGE") && request.get("AGE") != null ? request.get("AGE") : "");
				sb.append("years");
				sb.append("</td>");
				sb.append("					<td class='dataHeader'>Gender:</td>");// need
																					// to
																					// check
				sb.append("					<td class='dataValue'>Female</td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td align='left' class='dataHeader'>Father:</td>");
				sb.append("					<td align='left' class='dataValue'>");

				sb.append(request.has("FATHER") && request.get("FATHER") != null ? request.get("FATHER") : "");
				sb.append("</td>");
				sb.append("					<td class='dataHeader'>Spouse:</td>");// need
																					// to
																					// check
				sb.append("					<td class='dataValue'>");
				sb.append(request.has("SPOUSE") && request.get("SPOUSE") != null ? request.get("SPOUSE") : "");
				sb.append("					</td><td class='dataHeader'>Mother:</td>");
				sb.append("					<td class='dataValue'>");
				sb.append(request.has("MOTHER") && request.get("MOTHER") != null ? request.get("MOTHER") : "");
				sb.append("				</td></tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr style='background-color: #eef2f5;'>");
				sb.append("					<td class='dataHeader' valign='top'>Phone Numbers:</td>");
				sb.append("					<td valign='top'>");
				ArrayList<String> phones = new ArrayList<String>();

				try {
					if (request.has("PHONES")) {
						JSONArray newObj = request.getJSONObject("PHONES").getJSONArray("PHONE");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								phones.add(newObj.get(i).toString());
							}
						}
					}
				} catch (Exception e) {
					if (request.has("PHONES")) {
						try {
							JSONArray newObj = new JSONArray("[" + request.getJSONObject("PHONES").get("PHONE") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									phones.add(newObj.get(i).toString());
								}
							}
						} catch (Exception e2) {
							if (request.get("PHONES") == null) {
								phones.add("");
							}
						}
					}
				}

				sb.append("						<table cellpadding='0' cellspacing='0'>");
				sb.append("							<tr>");
				sb.append("								<td class='dataValue'>");

				sb.append(phones.size() > 0 && phones.get(0) != null ? phones.get(0) : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataValue'>");

				sb.append(phones.size() > 1 && phones.get(1) != null ? phones.get(1) : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td class='dataValue'>");

				sb.append(phones.size() > 2 && phones.get(2) != null ? phones.get(2) : "");

				sb.append("</td>");
				sb.append("							</tr>");
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("					<td class='dataHeader' valign='top'>ID(s):</td>");
				sb.append("					<td valign='top'>");

				Map<String, String> typeOfIdList = new HashMap<String, String>();
				typeOfIdList.put("ID01", "Passport");
				typeOfIdList.put("ID02", "VoterID");
				typeOfIdList.put("ID03", "UID");
				typeOfIdList.put("ID04", "Others");
				typeOfIdList.put("ID05", "RationCard");
				typeOfIdList.put("ID06", "DrivingLicenseNo");
				typeOfIdList.put("ID07", "Pan");

				Map<String, String> ids = new HashMap<String, String>();

				try {
					if (request.has("IDS")) {
						JSONArray newObj = request.getJSONObject("IDS").getJSONArray("ID");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								JSONObject id = newObj.getJSONObject(i);
								ids.put(typeOfIdList.get(id.get("TYPE").toString()), id.get("VALUE").toString());

							}
						}
					}
				} catch (Exception e) {
					if (request.has("IDS")) {

						try {
							JSONArray newObj = new JSONArray("[" + request.getJSONObject("IDS").get("ID") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									JSONObject id = newObj.getJSONObject(i);
									ids.put(typeOfIdList.get(id.get("TYPE").toString()), id.get("VALUE").toString());

								}
							}
						} catch (Exception e2) {
							if (request.get("IDS") == null) {
								ids.put("", "");
							}
						}
					}
				}

				sb.append("						<table cellpadding='0' cellspacing='0'>");

				for (Map.Entry<String, String> entry : ids.entrySet()) {

					sb.append("							<tr>");
					sb.append("								<td class='dataValue'>");

					sb.append((entry.getValue() != null && !entry.getValue().equals("null")? entry.getValue() : "").toString() + "["
							+ (entry.getKey() != null && !entry.getKey().equals("null")? entry.getKey() : "").toString() + "]");

					sb.append("</td>");
					sb.append("							</tr>");

				}

				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("					<td class='dataHeader' valign='top'>Email ID(s):</td>");
				sb.append("					<td valign='top'>");

				ArrayList<String> emails = new ArrayList<String>();

				try {
					if (request.has("EMAILS")) {
						JSONArray newObj = request.getJSONObject("EMAILS").getJSONArray("EMAIL");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								emails.add(newObj.get(i).toString());
							}
						}
					}
				} catch (Exception e) {
					if (request.has("EMAILS")) {
						try {
							JSONArray newObj = new JSONArray("[" + request.getJSONObject("EMAILS").get("EMAIL") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									emails.add(newObj.get(i).toString());
								}
							}
						} catch (Exception e2) {
							if (request.get("EMAILS") == null) {
								emails.add("");
							}
						}
					}
				}
				sb.append("						<table cellpadding='0' cellspacing='0'>");
				if (emails.size() > 0) {
					for (int i = 0; i < emails.size(); i++) {

						sb.append("							<tr>");
						sb.append("								<td class='dataValue'>");
						sb.append(emails.size() > i && emails.get(i) != null ? emails.get(i) : "");
						sb.append("</td>");
						sb.append("							</tr>");
					}
				} else {
					sb.append("							<tr>");
					sb.append("								<td class='dataValue'></td>");
					sb.append("							</tr>");
				}
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td align='left' class='dataHeader'>Entity Id:</td>");
				sb.append("					<td align='left' class='dataValue' colspan='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr style='background-color: #eef2f5;'>");
				sb.append("					<td align='left' class='dataHeader'>Current Address:</td>");
				JSONObject addresses = request.getJSONObject("ADDRESSES");
				sb.append(addresses.get("ADDRESS") != null ? addresses.get("ADDRESS") : "");
				sb.append("					<td align='left' class='dataValue' colspan='5'>");

				sb.append(addresses.get("ADDRESS") != null ? addresses.get("ADDRESS") : "");

				sb.append("</td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td align='left' class='dataHeader'>Other Address:</td>");
				sb.append("					<td align='left' class='dataValue' colspan='5'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='10'></td>");
				sb.append("				</tr>");
				sb.append("			</tbody>");
				sb.append("		</table>");
				sb.append("	</div>");

				sb.append("	<table align='center' bgcolor='#0f3f6b' border='0' style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr height='20'>");
				sb.append("				<td style='padding: 5px;' class='mainHeader'>CRIF HM Score(S):</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");

				sb.append("	<table align='left' border='0' cellpadding='0' cellspacing='0'");
				sb.append("		style='width: 100%; text-align: right;'>");
				sb.append("		<tbody>");
				sb.append("			<tr>");
				sb.append("				<td>");
				sb.append("					<table border='0' align='left'");
				sb.append("						style='width: 1028px; padding-left: 8px;'>");
				sb.append("						<tbody>");
				sb.append("							<tr>");
				sb.append("								<td height='10' colspan='2'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td align='left' class='dataHeader'");
				sb.append("									style='padding-left: 20px;'>SCORE NAME</td>");
				sb.append("								<td align='center'  class='dataHeader'");
				sb.append("									style='padding-left: 31px;'>&nbsp;SCORE</td>");
				sb.append("								<td align='left'  class='dataHeader'>DESCRIPTION</td>");
				sb.append("							</tr>");
				JSONObject scores = null;
				try {
					scores = indv_report.getJSONObject("SCORES");
				} catch (Exception e) {
					logger.error("CrifPdfCreator || pdfCreation() || get score :: " + e);
				}
				
				if (scores != null) {
					
					
					Object jsonobjscore= scores.get("SCORE");
					if(jsonobjscore instanceof JSONArray)
					{
						
						JSONArray jsonscoreArray=(JSONArray) jsonobjscore;
						for(int j=0;j<jsonscoreArray.length();j++)
						{
							JSONObject score=jsonscoreArray.getJSONObject(j);
							sb.append("							<tr class='shading'>");
							sb.append("								<td align='left'  class='dataHeaderScore'");
							sb.append("									style='padding-left: 20px;'>");

							sb.append(score.get("SCORE-TYPE") != null ? score.get("SCORE-TYPE") : "");

							sb.append("</td>");

							sb.append("								<td align='center' valign='top' class='dataValueValue'");
							sb.append("									style='padding-left: 33px; '>&nbsp;");
							sb.append(score.get("SCORE-VALUE") != null ? score.get("SCORE-VALUE") : "");
							sb.append("									<span class='dataValuePerform2'");
							sb.append(
									"									style=' vertical-align: middle; padding-left: 5px;'> Score");
							sb.append("										Range : 300-900</span>");
							sb.append("								</td>");
							String comment = (String) (score.get("SCORE-COMMENTS") != null ? score.get("SCORE-COMMENTS") : "");
							String commentF = comment.substring(0, 1);
							String commentR = comment.substring(1);

							sb.append("								<td align='left' style='text-align:left;' >&nbsp;<span");
							sb.append("									style='font-size: 14px; font-weight: bold;'>&nbsp;"
									+ commentF + "</span><span");
							sb.append("									class='dataValuePerform'>" + commentR + "</span></td>");
							sb.append("							</tr>");
						
						}
					}
					else
					{
						JSONObject score=(JSONObject) jsonobjscore;
						sb.append("							<tr class='shading'>");
						sb.append("								<td align='left'  class='dataHeaderScore'");
						sb.append("									style='padding-left: 20px;'>");

						sb.append(score.get("SCORE-TYPE") != null ? score.get("SCORE-TYPE") : "");

						sb.append("</td>");

						sb.append("								<td align='center' valign='top' class='dataValueValue'");
						sb.append("									style='padding-left: 33px; '>&nbsp;");
						sb.append(score.get("SCORE-VALUE") != null ? score.get("SCORE-VALUE") : "");
						sb.append("									<span class='dataValuePerform2'");
						sb.append(
								"									style=' vertical-align: middle; padding-left: 5px;'> Score");
						sb.append("										Range : 300-900</span>");
						sb.append("								</td>");
						String comment = (String) (score.get("SCORE-COMMENTS") != null ? score.get("SCORE-COMMENTS") : "");
						String commentF = comment.substring(0, 1);
						String commentR = comment.substring(1);

						sb.append("								<td align='left' style='text-align:left;' >&nbsp;<span");
						sb.append("									style='font-size: 14px; font-weight: bold;'>&nbsp;"
								+ commentF + "</span><span");
						sb.append("									class='dataValuePerform'>" + commentR + "</span></td>");
						sb.append("							</tr>");
					}
					
				}
				/*sb.append("							<tr class='shading'>");

				if (scores != null && !scores.equals("")) {
					JSONObject score = scores.getJSONObject("SCORE");
					if (scores != null && !scores.equals(""))
						sb.append("								<td align='left'  class='dataHeaderScore'");
					sb.append("									style='padding-left: 20px;'>");

					sb.append(score.get("SCORE-TYPE") != null ? score.get("SCORE-TYPE") : "");

					sb.append("</td>");

					sb.append("								<td align='center' valign='top' class='dataValueValue'");
					sb.append("									style='padding-left: 33px; '>&nbsp;");
					sb.append(score.get("SCORE-VALUE") != null ? score.get("SCORE-VALUE") : "");
					sb.append("									<span class='dataValuePerform2'");
					sb.append(
							"									style=' vertical-align: middle; padding-left: 5px;'> Score");
					sb.append("										Range : 300-900</span>");
					sb.append("								</td>");
					String comment = (String) (score.get("SCORE-COMMENTS") != null ? score.get("SCORE-COMMENTS") : "");
					String commentF = comment.substring(0, 1);
					String commentR = comment.substring(1);

					sb.append("								<td align='left' style='text-align:left;' >&nbsp;<span");
					sb.append("									style='font-size: 14px; font-weight: bold;'>&nbsp;"
							+ commentF + "</span><span");
					sb.append("									class='dataValuePerform'>" + commentR + "</span></td>");
				}

				sb.append("							</tr>");*/
				
				
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("				</td>");
				sb.append("			</tr>");
				sb.append("			<tr height='20'>");
				sb.append("				<td class='infoValueNote' style='padding-right: 9px;' align='right'");
				sb.append("					bgcolor='#FFFFFF'>Tip: A-B: Very Low Risk ; C-D: Low Risk ;");
				sb.append("					E-G: Medium Risk ; H-J: High Risk ; K-M: Very High Risk</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");

				sb.append("	<table border='0' style='width: 100%; text-align: right;'>");
				sb.append("		<tbody>");
				sb.append("			<tr height='20'>");
				sb.append("				<td style='padding: 5px;' class='mainHeader'>Personal");
				sb.append("					Information - Variations</td>");
				sb.append("			</tr>");
				sb.append("			<tr height='20'>");
				sb.append("				<td align='right' bgcolor='#FFFFFF' style='padding-right: 9px;'");
				sb.append("					class='infoValue'>Tip: These are applicant's personal");
				sb.append("					information variations as contributed by various financial");
				sb.append("					institutions.</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<table cellpadding='2' cellspacing='4' border='0' style='width: 100%;'>");
				sb.append("		<tbody>");
				sb.append("			<tr>");
				sb.append("				<td width='670' valign='top'>");
				sb.append("					<table cellpadding='0' cellspacing='4' border='0px' width='670'>");
				sb.append("						<tbody>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'>");
				sb.append("									<table class='box1' border='0px' bordercolor='lightgray'");
				sb.append("										cellpadding='3' cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='20'>");
				sb.append(
						"												<td align='left' style='text-align:left;' bgcolor='#FFFFFF' width='550px'");
				sb.append("													class='subHeader'>Name Variations</td>");
				sb.append(
						"												<td align='center' style='text-align:left;'  bgcolor='#FFFFFF' width='90px'");
				sb.append("													class='subHeader'>Reported On</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='1'>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("											</tr>");
				Map<String, String> name_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("NAME-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("NAME-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								name_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("NAME-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray("["
									+ personalInfoVariation.getJSONObject("NAME-VARIATIONS").get("VARIATION") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									name_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("NAME-VARIATIONS") == null) {
								name_variations.put("", "");
							}
						}
					}
				}

				for (Map.Entry<String, String> entry : name_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString());

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}
				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("								</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td height='5px'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'>");
				sb.append("									<table class='box1' border='0px' bordercolor='lightgray'");
				sb.append("										cellpadding='3' cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='20'>");
				sb.append(
						"												<td align='center' style='text-align:left;'  width='550px' class='subHeader'>Address");
				sb.append("													Variations</td>");
				sb.append(
						"												<td align='center'  style='text-align:left;' width='90px' class='subHeader'>Reported");
				sb.append("													On</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='1'>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("											</tr>");

				Map<String, String> address_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("ADDRESS-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("ADDRESS-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								address_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("ADDRESS-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray("["
									+ personalInfoVariation.getJSONObject("ADDRESS-VARIATIONS").get("VARIATION") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									address_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("ADDRESS-VARIATIONS") == null) {
								address_variations.put("", "");
							}
						}
					}
				}

				for (Map.Entry<String, String> entry : address_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString());

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("								</td>");
				sb.append("							</tr>");

				sb.append("							<tr>");
				sb.append("								<td height='5px'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'>");
				sb.append("									<table class='box1' border='0px' bordercolor='lightgray'");
				sb.append("										cellpadding='3' cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='20'>");
				sb.append(
						"												<td align='center' style='text-align:left;'  width='550px' class='subHeader'>Email");
				sb.append("													Variations</td>");
				sb.append(
						"												<td align='center'  style='text-align:left;' width='90px' class='subHeader'>Reported");
				sb.append("													On</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='1'>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("											</tr>");
				///////////////////////////////////////////////////////////////////////////
				Map<String, String> email_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("EMAIL-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("EMAIL-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								email_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("EMAIL-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray("["
									+ personalInfoVariation.getJSONObject("EMAIL-VARIATIONS").get("VARIATION") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									email_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("EMAIL-VARIATIONS") == null) {
								email_variations.put("", "");
							}
						}

					}

				}

				for (Map.Entry<String, String> entry : email_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString());

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("								</td>");
				sb.append("							</tr>");

				sb.append("							<tr>");
				sb.append("								<td height='5px'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'>");
				sb.append("									<table class='box1' border='0px' bordercolor='lightgray'");
				sb.append("										cellpadding='3' cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='20'>");
				sb.append(
						"												<td align='center' style='text-align:left;'  width='550px' class='subHeader'>Ration Card");
				sb.append("													Variations</td>");
				sb.append(
						"												<td align='center'  style='text-align:left;' width='90px' class='subHeader'>Reported");
				sb.append("													On</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='1'>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("											</tr>");

				Map<String, String> ration_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("RATION-CARD-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("RATION-CARD-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								ration_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("RATION-CARD-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray(
									"[" + personalInfoVariation.getJSONObject("RATION-CARD-VARIATIONS").get("VARIATION")
											+ "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									ration_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("RATION-CARD-VARIATIONS") == null) {
								ration_variations.put("", "");
							}
						}

					}

				}

				for (Map.Entry<String, String> entry : ration_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString());

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("								</td>");
				sb.append("							</tr>");

				/////////////////////////////////////////////////////////////////////////

				sb.append("							<tr>");
				sb.append("								<td height='5px'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'></td>");
				sb.append("							</tr>");
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("				</td>");
				sb.append("				<td width='330' valign='top'>");
				sb.append("					<table cellpadding='0' cellspacing='4' border='0px' width='330'>");
				sb.append("						<tbody>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'>");
				sb.append("									<table class='box1' border='0px' bordercolor='lightgray'");
				sb.append("										cellpadding='3' cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='20'>");
				sb.append(
						"												<td align='center' width='230px' style='text-align:left;'  class='subHeader'>DOB");
				sb.append("													Variations</td>");
				sb.append(
						"												<td align='center' width='90px' style='text-align:left;'  class='subHeader'>Reported");
				sb.append("													On</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='1'>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("											</tr>");

				Map<String, String> dob_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("DATE-OF-BIRTH-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("DATE-OF-BIRTH-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								dob_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("DATE-OF-BIRTH-VARIATIONS")) {

						try {
							JSONArray newObj = new JSONArray("["
									+ personalInfoVariation.getJSONObject("DATE-OF-BIRTH-VARIATIONS").get("VARIATION")
									+ "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									dob_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("DATE-OF-BIRTH-VARIATIONS") == null) {
								dob_variations.put("", "");
							}
						}

					}
				}

				for (Map.Entry<String, String> entry : dob_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString());

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("								</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td height='5px'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'>");
				sb.append("									<table class='box1' border='0px' bordercolor='lightgray'");
				sb.append("										cellpadding='3' cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='20'>");
				sb.append(
						"												<td align='center' width='230px' style='text-align:left;'  class='subHeader'>Phone");
				sb.append("													Variations</td>");
				sb.append(
						"												<td align='center' width='90px' style='text-align:left;'  class='subHeader'>Reported");
				sb.append("													On</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='1'>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("											</tr>");

				Map<String, String> phone_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("PHONE-NUMBER-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("PHONE-NUMBER-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								phone_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("PHONE-NUMBER-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray("["
									+ personalInfoVariation.getJSONObject("PHONE-NUMBER-VARIATIONS").get("VARIATION")
									+ "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									phone_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("PHONE-NUMBER-VARIATIONS") == null) {
								phone_variations.put("", "");
							}
						}

					}
				}

				for (Map.Entry<String, String> entry : phone_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString());

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("								</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td height='5px'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td bgcolor='#FFFFFF'>");
				sb.append("									<table class='box1' border='0px' bordercolor='lightgray'");
				sb.append("										cellpadding='3' cellspacing='0'>");
				sb.append("										<tbody>");
				sb.append("											<tr height='20'>");
				sb.append(
						"												<td align='center' style='text-align:left;'  width='230px' class='subHeader'>ID");
				sb.append("													Variations</td>");
				sb.append(
						"												<td align='center'  style='text-align:left;' width='90px' class='subHeader'>Reported");
				sb.append("													On</td>");
				sb.append("											</tr>");
				sb.append("											<tr height='1'>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("												<td style='background-color: #A7CBE3;'></td>");
				sb.append("											</tr>");

				Map<String, String> pan_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("PAN-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("PAN-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								pan_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("PAN-VARIATIONS")) {
						try {

							JSONArray newObj = new JSONArray(
									"[" + personalInfoVariation.getJSONObject("PAN-VARIATIONS").get("VARIATION") + "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									pan_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("PAN-VARIATIONS") == null) {
								pan_variations.put("", "");
							}
						}
					}
				}

				for (Map.Entry<String, String> entry : pan_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString() + " [PAN]");

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				Map<String, String> voter_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("VOTER-ID-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("VOTER-ID-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								voter_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("VOTER-ID-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray(
									"[" + personalInfoVariation.getJSONObject("VOTER-ID-VARIATIONS").get("VARIATION")
											+ "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									voter_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("VOTER-ID-VARIATIONS") == null) {
								voter_variations.put("", "");
							}
						}
					}
				}

				for (Map.Entry<String, String> entry : voter_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString() + " [Voter ID]");

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				Map<String, String> passport_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("PASSPORT-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("PASSPORT-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								passport_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("PASSPORT-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray(
									"[" + personalInfoVariation.getJSONObject("PASSPORT-VARIATIONS").get("VARIATION")
											+ "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									passport_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("PASSPORT-VARIATIONS") == null) {
								passport_variations.put("", "");
							}
						}
					}
				}

				for (Map.Entry<String, String> entry : passport_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString() + " [Passport]");

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}

				Map<String, String> drivinglic_variations = new HashMap<String, String>();

				try {
					if (personalInfoVariation.has("DRIVING-LICENSE-VARIATIONS")) {
						JSONArray newObj = personalInfoVariation.getJSONObject("DRIVING-LICENSE-VARIATIONS")
								.getJSONArray("VARIATION");
						if (newObj != null) {
							for (int i = 0; i < newObj.length(); i++) {
								drivinglic_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
										newObj.getJSONObject(i).get("REPORTED-DATE").toString());
							}
						}
					}
				} catch (Exception e) {
					if (personalInfoVariation.has("DRIVING-LICENSE-VARIATIONS")) {
						try {
							JSONArray newObj = new JSONArray("["
									+ personalInfoVariation.getJSONObject("DRIVING-LICENSE-VARIATIONS").get("VARIATION")
									+ "]");
							if (newObj != null) {
								for (int i = 0; i < newObj.length(); i++) {
									drivinglic_variations.put(newObj.getJSONObject(i).get("VALUE").toString(),
											newObj.getJSONObject(i).get("REPORTED-DATE").toString());
								}
							}
						} catch (Exception e2) {
							if (personalInfoVariation.get("DRIVING-LICENSE-VARIATIONS") == null) {
								drivinglic_variations.put("", "");
							}
						}
					}
				}
				for (Map.Entry<String, String> entry : drivinglic_variations.entrySet()) {

					sb.append("			<tr height='20' style='background-color: #eef2f5;'>");
					sb.append("												<td class='dataValue'>");

					sb.append((entry.getKey() != null ? entry.getKey() : "").toString() + " [Driving License]");

					sb.append("				</td>");
					sb.append("												<td align='center' class='dataValue'>");
					sb.append((entry.getValue() != null ? entry.getValue() : "").toString());

					sb.append("										</td>	</tr>");
				}
				sb.append("										</tbody>");
				sb.append("									</table>");
				sb.append("								</td>");
				sb.append("							</tr>");
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("				</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<table border='0' style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr height='20'>");
				sb.append("				<td style='padding: 5px;' class='mainHeader'>Account Summary</td>");
				sb.append("			</tr>");
				sb.append("			<tr height='20'>");
				sb.append("				<td align='right' bgcolor='#FFFFFF'");
				sb.append("					style='padding-right: 9px; text-align: right;' class='infoValue'>Tip:");
				sb.append("					Current Balance & Disbursed Amount is considered ONLY for ACTIVE");
				sb.append("					accounts.</td>");
				sb.append("			</tr>");
				sb.append("			<tr>");
				sb.append("				<td align='right' bgcolor='#FFFFFF' class='infoValue' height='20'></td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<div align='center' style='padding-left: 10px;'>");
				sb.append("		<table align='center'");
				sb.append("			style='border-collapse: collapse; width: 99%; border: 2px solid #A7CBE3;'");
				sb.append("			cellspacing='0' cellpadding='2'>");
				sb.append("			<tbody>");
				sb.append("				<tr>");
				sb.append("					<td width='center'>");
				sb.append("						<table align='center' border='0px' cellspacing='0' cellpadding='0'");
				sb.append("							style='width: 100%'>");
				sb.append("							<tbody>");
				sb.append("								<tr height='20'>");
				sb.append("									<td class='subHeader1'>Type</td>");
				sb.append("									<td align='center' class='subHeader1' style=''>Number of");
				sb.append("										Account(s)</td>");
				sb.append(
						"									<td align='center' class='subHeader1'>Active Account(s)</td>");
				sb.append(
						"									<td align='center' class='subHeader1'>Overdue Account(s)</td>");
				sb.append("									<td align='right' class='subHeader1'>Current Balance</td>");
				sb.append("									<td align='right' class='subHeader1'>Amt Disbd/ High");
				sb.append("										Credit</td>");
				sb.append("									<td align='right' width='5' class='subHeader1'></td>");
				sb.append("								</tr>");
				sb.append("								<tr height='1'>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("								</tr>");

				JSONObject primarySummary = accountsSummary.getJSONObject("PRIMARY-ACCOUNTS-SUMMARY");

				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataHeader'>Primary Match</td>");
				sb.append("									<td align='center' class='AccValue'>");
				sb.append((primarySummary.has("PRIMARY-NUMBER-OF-ACCOUNTS")
						&& primarySummary.get("PRIMARY-NUMBER-OF-ACCOUNTS") != null
								? primarySummary.get("PRIMARY-NUMBER-OF-ACCOUNTS") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='center' class='AccValue'>");
				sb.append((primarySummary.has("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS")
						&& primarySummary.get("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS") != null
								? primarySummary.get("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='center' class='AccValue'>");
				sb.append((primarySummary.has("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS")
						&& primarySummary.get("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS") != null
								? primarySummary.get("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='right' class='AccValue'>");
				sb.append((primarySummary.has("PRIMARY-CURRENT-BALANCE")
						&& primarySummary.get("PRIMARY-CURRENT-BALANCE") != null
								? primarySummary.get("PRIMARY-CURRENT-BALANCE") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='right' class='AccValue'>");
				sb.append((primarySummary.has("PRIMARY-DISBURSED-AMOUNT")
						&& primarySummary.get("PRIMARY-DISBURSED-AMOUNT") != null
								? primarySummary.get("PRIMARY-DISBURSED-AMOUNT") : "").toString());
				sb.append("				</td>");
				sb.append("								</tr>");

				JSONObject secondarySummary = accountsSummary.getJSONObject("SECONDARY-ACCOUNTS-SUMMARY");

				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataHeader'>Secondary Match</td>");
				sb.append("									<td align='center' class='AccValue'>");
				sb.append((secondarySummary.has("SECONDARY-NUMBER-OF-ACCOUNTS")
						&& secondarySummary.get("SECONDARY-NUMBER-OF-ACCOUNTS") != null
								? secondarySummary.get("SECONDARY-NUMBER-OF-ACCOUNTS") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='center' class='AccValue'>");
				sb.append((secondarySummary.has("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS")
						&& secondarySummary.get("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS") != null
								? secondarySummary.get("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='center' class='AccValue'>");
				sb.append((secondarySummary.has("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS")
						&& secondarySummary.get("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS") != null
								? secondarySummary.get("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='right' class='AccValue'>");
				sb.append((secondarySummary.has("SECONDARY-CURRENT-BALANCE")
						&& secondarySummary.get("SECONDARY-CURRENT-BALANCE") != null
								? secondarySummary.get("SECONDARY-CURRENT-BALANCE") : "").toString());
				sb.append("				</td>");
				sb.append("									<td align='right' class='AccValue'>");
				sb.append((secondarySummary.has("SECONDARY-DISBURSED-AMOUNT")
						&& secondarySummary.get("SECONDARY-DISBURSED-AMOUNT") != null
								? secondarySummary.get("SECONDARY-DISBURSED-AMOUNT") : "").toString());
				sb.append("				</td>");
				sb.append("								</tr>");

				sb.append("								<tr height='20' style='background-color: #eef2f5;'>");
				sb.append("									<td align='left' class='dataHeader'>Total</td>");
				sb.append("									<td align='center' class='AccValue'>");
				if (primarySummary.has("PRIMARY-NUMBER-OF-ACCOUNTS")
						&& primarySummary.get("PRIMARY-NUMBER-OF-ACCOUNTS") != null
						&& secondarySummary.has("SECONDARY-NUMBER-OF-ACCOUNTS")
						&& secondarySummary.get("SECONDARY-NUMBER-OF-ACCOUNTS") != null) {
					sb.append((Integer) primarySummary.get("PRIMARY-NUMBER-OF-ACCOUNTS")
							+ (Integer) secondarySummary.get("SECONDARY-NUMBER-OF-ACCOUNTS"));
				}

				sb.append("				</td>");
				sb.append("									<td align='center' class='AccValue'>");
				if (primarySummary.has("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS")
						&& primarySummary.get("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS") != null
						&& secondarySummary.has("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS")
						&& secondarySummary.get("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS") != null) {
					sb.append((Integer) primarySummary.get("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS")
							+ (Integer) secondarySummary.get("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS"));
				}
				sb.append("				</td>");
				sb.append("									<td align='center' class='AccValue'>");

				if (primarySummary.has("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS")
						&& primarySummary.get("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS") != null
						&& secondarySummary.has("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS")
						&& secondarySummary.get("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS") != null) {
					sb.append((Integer) primarySummary.get("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS")
							+ (Integer) secondarySummary.get("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS"));
				}
				sb.append("				</td>");
				sb.append("									<td align='right' class='AccValue'>");
				if (primarySummary.has("PRIMARY-CURRENT-BALANCE")
						&& primarySummary.get("PRIMARY-CURRENT-BALANCE") != null
						&& secondarySummary.has("SECONDARY-CURRENT-BALANCE")
						&& secondarySummary.get("SECONDARY-CURRENT-BALANCE") != null) {
					sb.append((Double) primarySummary.get("PRIMARY-CURRENT-BALANCE")
							+ (Double) secondarySummary.get("SECONDARY-CURRENT-BALANCE"));
				}
				sb.append("				</td>");
				sb.append("									<td align='right' class='AccValue'>");

				if (primarySummary.has("PRIMARY-DISBURSED-AMOUNT")
						&& primarySummary.get("PRIMARY-DISBURSED-AMOUNT") != null
						&& secondarySummary.has("SECONDARY-CURRENT-BALANCE")
						&& secondarySummary.get("SECONDARY-CURRENT-BALANCE") != null) {
					sb.append((Double) primarySummary.get("PRIMARY-DISBURSED-AMOUNT")
							+ (Double) secondarySummary.get("SECONDARY-CURRENT-BALANCE"));
				}

				sb.append("				</td>");
				sb.append("								</tr>");
				sb.append("							</tbody>");
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td height='10'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td>");
				sb.append("						<table align='center' border='0px' cellspacing='0'");
				sb.append("							style='width: 100%'>");
				sb.append("							<tbody>");

				JSONObject drivedAttribute = accountsSummary.getJSONObject("DERIVED-ATTRIBUTES");

				sb.append("								<tr bgcolor='#FFFFFF'>");
				sb.append("									<td colspan='2' align='left' bgcolor='#FFFFFF'");
				sb.append("										class='AccHeader'>Inquiries in last 24 Months: <font");///////////////////
				sb.append("										color='#464646'></font>");
				sb.append("									</td>");
				sb.append("									<td colspan='2' align='center' bgcolor='#FFFFFF'");
				sb.append(
						"										class='AccHeader'>New Account(s) in last 6 Months: <font");
				sb.append("										color='#464646'>");
				sb.append((drivedAttribute.has("NEW-DELINQ-ACCOUNT-IN-LAST-SIX-MONTHS")
						&& drivedAttribute.get("NEW-DELINQ-ACCOUNT-IN-LAST-SIX-MONTHS") != null
								? drivedAttribute.get("NEW-DELINQ-ACCOUNT-IN-LAST-SIX-MONTHS") : "").toString());
				sb.append("									</font>");
				sb.append("									</td>");
				sb.append("									<td colspan='2' align='right' bgcolor='#FFFFFF'");
				sb.append("										class='AccHeader'>New Delinquent Account(s) in last 6");
				sb.append("										Months: <font color='#464646'>");
				sb.append((drivedAttribute.has("NEW-ACCOUNTS-IN-LAST-SIX-MONTHS")
						&& drivedAttribute.get("NEW-ACCOUNTS-IN-LAST-SIX-MONTHS") != null
								? drivedAttribute.get("NEW-ACCOUNTS-IN-LAST-SIX-MONTHS") : "").toString());
				sb.append("									</font>");
				sb.append("									</td>");
				sb.append("									<td align='right' width='5' class='AccHeader'></td>");
				sb.append("								</tr>");
				sb.append("							</tbody>");
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("				</tr>");
				sb.append("			</tbody>");
				sb.append("		</table>");
				sb.append("	</div>");
				sb.append("	<table border='0' style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr>");
				sb.append("				<td align='right' bgcolor='#FFFFFF' class='infoValue' height='20'></td>");
				sb.append("			</tr>");
				sb.append("			<tr height='20'>");
				sb.append("				<td style='padding: 5px;' class='mainHeader'>Account");
				sb.append("					Information</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");

				// ArrayList<JSONObject> RESPONSE = new ArrayList<JSONObject>();
				JSONArray RESPONSE = null;
				try {
					if (indv_report.has("RESPONSES")) {
						RESPONSE = indv_report.getJSONObject("RESPONSES").getJSONArray("RESPONSE");
						/*
						 * if (newObj != null) { for (int
						 * i=0;i<newObj.length();i++){
						 * phones.add(newObj.get(i).toString()); } }
						 */
					}
				} catch (Exception e) {
					if (indv_report.has("RESPONSES")) {

						RESPONSE = new JSONArray("[" + indv_report.getJSONObject("RESPONSES").get("RESPONSE") + "]");
						/*
						 * if (newObj != null) { for (int
						 * i=0;i<newObj.length();i++){
						 * phones.add(newObj.get(i).toString()); } }
						 */

					}
				}
				if (RESPONSE != null && RESPONSE.length() > 0) {
					for (int i = 0; i < RESPONSE.length(); i++) {

						JSONObject loan_details = new JSONObject(RESPONSE.get(i).toString())
								.getJSONObject("LOAN-DETAILS");

						sb.append("	<table align='center' cellpadding='0' cellspacing='0'");
						sb.append("		style='width: 100%'>");
						sb.append("		<tbody>");
						sb.append("			<tr>");
						sb.append(
								"				<td align='right' bgcolor='#FFFFFF' class='infoValue' height='20'></td>");
						sb.append("			</tr>");
						sb.append("			<tr>");
						sb.append("				<td>");
						sb.append("					<table align='center' border='0' style='width: 100%'");
						sb.append("						cellpadding='0' cellspacing='0'>");
						sb.append("						<tbody>");
						sb.append("							<tr height='20'>");
						sb.append("								<td align='center' width='25px' class='mainAccHeader'>"
								+ (i + 1) + "</td>");
						sb.append("								<td align='center'>");
						sb.append(
								"									<table align='left' border='0' style='width: 100%'");
						sb.append(
								"										bgcolor='e6e6ff' cellpadding='2' cellspacing='0'>");
						sb.append("										<tbody>");
						sb.append(
								"											<tr height='20' style='background-color: #e6e6ff;'>");
						sb.append(
								"												<td align='left' class='AccHeader' nowrap='true'>Account");
						sb.append(
								"													Type: <font class='maroonFields' nowrap='true'>");
						sb.append((loan_details.has("ACCT-TYPE") && loan_details.get("ACCT-TYPE") != null
								? loan_details.get("ACCT-TYPE") : "").toString());
						sb.append("												</font></td>");
						sb.append(
								"												<td align='left' class='AccHeader' nowrap='nowrap'>Credit");
						sb.append("													Grantor: <font color='#464646'> ");
						sb.append((loan_details.has("CREDIT-GUARANTOR") && loan_details.get("CREDIT-GUARANTOR") != null
								? loan_details.get("CREDIT-GUARANTOR") : "").toString());
						sb.append("												</font></td>");
						sb.append(
								"												<td align='left' nowrap='true' class='AccHeader :'>Account");
						sb.append("													#: <font color='#464646'> ");
						sb.append((loan_details.has("ACCT-NUMBER") && loan_details.get("ACCT-NUMBER") != null
								? loan_details.get("ACCT-NUMBER") : "").toString());
						sb.append("												</font></td>");
						sb.append(
								"												<td align='right' class='AccHeader'>Info. as of: <font");
						sb.append("													color='#464646'> ");
						sb.append((loan_details.has("DATE-REPORTED") && loan_details.get("DATE-REPORTED") != null
								? loan_details.get("DATE-REPORTED") : "").toString());
						sb.append("												</font></td>");
						sb.append("											</tr>");
						sb.append("										</tbody>");
						sb.append("									</table>");
						sb.append("								</td>");
						sb.append("							</tr>");
						sb.append("						</tbody>");
						sb.append("					</table>");
						sb.append("				</td>");
						sb.append("			</tr>");
						sb.append("			<tr>");
						sb.append("				<td>");
						sb.append("					<table align='center' border='0' style='width: 100%'");
						sb.append("						cellpadding='0' cellspacing='0'>");
						sb.append("						<tr>");

						if (loan_details.get("ACCOUNT-STATUS") != null
								&& loan_details.getString("ACCOUNT-STATUS").trim().toLowerCase().equals("active")) {

							sb.append("							<td class='container' width='25px'");
							sb.append(
									"								style='color: Maroon; background-color: #ffe1dc; text-align: center; padding-top: 5px; padding-bottom: 5px;'>");
							sb.append("								A<br /> C<br /> T<br /> I<br /> V<br /> E");
							sb.append("							</td>");

						} else if (loan_details.get("ACCOUNT-STATUS") != null
								&& loan_details.getString("ACCOUNT-STATUS").trim().toLowerCase().equals("closed")) {
							sb.append("							<td class='container' width='25px'");
							sb.append(
									"								style='color: #415a05; background-color: #e1f0be; text-align: center; padding-top: 5px; padding-bottom: 5px;'>");
							sb.append("								C<br /> L<br /> O<br /> S<br /> E<br /> D");
							sb.append("							</td>");
						}
						sb.append("							<td>");
						sb.append(
								"								<table align='center' border='0' cellpadding='0' cellspacing='0'");
						sb.append("									style='width: 100%'>");
						sb.append("									<tbody>");
						sb.append("										<tr height='10'>");
						sb.append("											<td></td>");
						sb.append("										</tr>");
						sb.append("										<tr height='25'>");
						sb.append(
								"											<td class='dataHeader'>&nbsp;&nbsp;Ownership:</td>");
						sb.append("											<td class='dataValue'>"
								+ (loan_details.get("OWNERSHIP-IND") != null ? loan_details.get("OWNERSHIP-IND") : "")
										.toString()
								+ "</td>");
						sb.append("											<td></td>");
						sb.append(
								"											<td class='dataHeader'>Disbursed Date:</td>");
						sb.append("											<td class='dataValue'>"
								+ (loan_details.get("DISBURSED-DATE") != null ? loan_details.get("DISBURSED-DATE") : "")
										.toString()
								+ "</td>");
						sb.append("											<td></td>");
						sb.append(
								"											<td class='dataHeader'>Disbd Amt/High Credit:</td>");
						sb.append("											<td align='right' class='dataAmtValue'>"
								+ (loan_details.get("DISBURSED-AMT") != null ? loan_details.get("DISBURSED-AMT") : "")
										.toString()
								+ "</td>");
						sb.append("											<td width='20'></td>");
						sb.append("										</tr>");
						sb.append("										<tr height='25'>");
						sb.append(
								"											<td class='dataHeader'>&nbsp;&nbsp;Credit Limit:</td>");
						sb.append("											<td class='dataValue'></td>");
						sb.append("											<td ></td>");
						sb.append(
								"											<td class='dataHeader'>Last Payment Date:</td>");
						// try {
						sb.append(
								"		<td class='dataValue'>"
										+ (loan_details.has("LAST-PAYMENT-DATE")
												&& loan_details.get("LAST-PAYMENT-DATE") != null
														? loan_details.get("LAST-PAYMENT-DATE") : "").toString()
										+ "</td>");
						// } catch (Exception e) {
						// sb.append(" <td class='dataValue'></td>");
						// }

						sb.append("											<td></td>");
						sb.append(
								"											<td class='dataHeader'>Current Balance:</td>");
						sb.append("											<td align='right' class='dataAmtValue'>"
								+ (loan_details.get("CURRENT-BAL") != null ? loan_details.get("CURRENT-BAL") : "")
										.toString()
								+ "</td>");
						sb.append("											<td width='20'></td>");
						sb.append("										</tr>");
						sb.append("										<tr height='25'>");
						sb.append(
								"											<td class='dataHeader'>&nbsp;&nbsp;Cash Limit:</td>");
						sb.append("											<td class='dataValue'></td>");
						sb.append("											<td></td>");
						sb.append("											<td class='dataHeader'>Closed Date:</td>");
						try {
							sb.append("	<td class='dataValue'>"
									+ (loan_details.get("CLOSED-DATE") != null ? loan_details.get("CLOSED-DATE") : "")
											.toString()
									+ "</td>");
						} catch (Exception e) {
							sb.append("	<td class='dataValue'></td>");
						}

						sb.append("											<td></td>");
						sb.append(
								"											<td class='dataHeader'>Last Paid Amt:</td>");
						sb.append(
								"											<td align='right' class='dataAmtValue'></td>");
						sb.append("											<td width='20'></td>");
						sb.append("										</tr>");
						sb.append("										<tr height='25'>");
						sb.append(
								"											<td class='dataHeadern'>&nbsp;&nbsp;InstlAmt/Freq:</td>");
						sb.append("											<td class='dataValue'></td>");
						sb.append("											<td></td>");
						sb.append(
								"											<td class='dataHeadern'>Tenure(month):</td>");
						sb.append("											<td class='dataValue'></td>");
						sb.append("											<td></td>");
						sb.append("											<td class='dataHeader'>Overdue Amt:</td>");
						sb.append("											<td align='right' class='dataAmtValue'>"
								+ (loan_details.has("OVERDUE-AMT") && loan_details.get("OVERDUE-AMT") != null
										? loan_details.get("OVERDUE-AMT") : "").toString()
								+ "</td>");
						sb.append("											<td width='20'></td>");
						sb.append("										</tr>");
						sb.append("									</tbody>");
						sb.append("								</table>");
						sb.append("							</td>");
						sb.append("						</tr>");
						sb.append("					</table>");
						sb.append("				</td>");
						sb.append("			</tr>");
						sb.append("			<tr>");
						sb.append("				<td height='20'></td>");
						sb.append("			</tr>");
						sb.append("		</tbody>");
						sb.append("	</table>");
						sb.append("	<div align='center' style='padding-left: 10px;'>");
						sb.append("		<table align='center' border='0' cellpadding='0' cellspacing='0'");
						sb.append("			style='width: 99%'>");
						sb.append("			<tbody>");
						sb.append("				<tr>");
						sb.append("					<td>");
						sb.append("						<table style='width: 100%'>");
						sb.append("							<tbody>");
						sb.append("								<tr>");
						sb.append("									<td width='2'></td>");
						sb.append(
								"									<td class='dataHeader' height='25'>Payment History/Asset");
						sb.append("										Classification:</td>");
						sb.append("								</tr>");
						sb.append("							</tbody>");
						sb.append("						</table>");

						if (loan_details.has("COMBINED-PAYMENT-HISTORY")) {

							TreeSet<String> yearSet = new TreeSet<String>();

							String[] histroy = loan_details.get("COMBINED-PAYMENT-HISTORY").toString().split("\\|");
							StringBuilder s = new StringBuilder(
									loan_details.get("COMBINED-PAYMENT-HISTORY").toString());
							int firstIndex = s.indexOf(":", 0) + 1;
							int temp = 0;
							while (firstIndex < s.length()) {
								int lastIndex = s.indexOf(",", firstIndex);
								yearSet.add(s.substring(firstIndex, lastIndex));
								temp = s.indexOf(":", lastIndex) + 1;
								if (temp > firstIndex) {
									firstIndex = temp;
								} else {
									break;
								}
							}
							HashMap<String, Integer> hm = new HashMap<String, Integer>();
							hm.put("Jan", 0);
							hm.put("Feb", 1);
							hm.put("Mar", 2);
							hm.put("Apr", 3);
							hm.put("May", 4);
							hm.put("Jun", 5);
							hm.put("Jul", 6);
							hm.put("Aug", 7);
							hm.put("Sep", 8);
							hm.put("Oct", 9);
							hm.put("Nov", 10);
							hm.put("Dec", 11);
							String[][] myHistrory = new String[yearSet.size()][12];
							for (String str : histroy) {
								String value = str.split(",")[1];

								int l = 0;
								for (String year : yearSet) {
									if ((str.split(",")[0].split(":")[1]).equals(year)) {
										myHistrory[l][hm.get(str.split(",")[0].split(":")[0])] = value;
									}
									l++;
								}

							}

							sb.append("						<table style='width: 100%'>");
							sb.append("							<tbody>");
							sb.append("								<tr>");
							sb.append("									<td>");
							sb.append("										<table align='left' border='0'");
							sb.append(
									"											style='border-collapse: collapse; table-layout: fixed; width: 100%; border-color: #A7CBE3;'>");
							sb.append("											<tbody>");
							sb.append("												<tr align='center'>");
							sb.append(
									"													<td style='border: 0px solid #fff;'></td>");
							sb.append(
									"													<td class='subAccHeader'>January</td>");
							sb.append(
									"													<td class='subAccHeader'>February</td>");
							sb.append(
									"													<td class='subAccHeader'>March</td>");
							sb.append(
									"													<td class='subAccHeader'>April</td>");
							sb.append(
									"													<td class='subAccHeader'>May</td>");
							sb.append(
									"													<td class='subAccHeader'>June</td>");
							sb.append(
									"													<td class='subAccHeader'>July</td>");
							sb.append(
									"													<td class='subAccHeader'>August</td>");
							sb.append(
									"													<td class='subAccHeader'>September</td>");
							sb.append(
									"													<td class='subAccHeader'>October</td>");
							sb.append(
									"													<td class='subAccHeader'>November</td>");
							sb.append(
									"													<td class='subAccHeader'>December</td>");
							sb.append("												</tr>");
							int size = yearSet.size();
							for (int p = 0; p < size; p++) {

								sb.append("												<tr align='center'>");
								sb.append(
										"													<td class='AccValue1' bgcolor='e6e6ff'>"
												+ (yearSet.first() != null ? yearSet.first() : "").toString()
												+ "</td>");
								try {
									yearSet.remove(yearSet.first());
								} catch (Exception e) {
								}
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][0] != null ? myHistrory[p][0] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][1] != null ? myHistrory[p][1] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][2] != null ? myHistrory[p][2] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][3] != null ? myHistrory[p][3] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][4] != null ? myHistrory[p][4] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][5] != null ? myHistrory[p][5] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][6] != null ? myHistrory[p][6] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][7] != null ? myHistrory[p][7] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][8] != null ? myHistrory[p][8] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][9] != null ? myHistrory[p][9] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][10] != null ? myHistrory[p][10] : "-").toString()
												+ "</td>");
								sb.append(
										"													<td class='AccValue1' bgcolor='#FFFFFF'>"
												+ (myHistrory[p][11] != null ? myHistrory[p][11] : "-").toString()
												+ "</td>");
								sb.append("												</tr>");

							}

							sb.append("											</tbody>");
							sb.append("										</table>");
							sb.append("									</td>");
							sb.append("								</tr>");
							sb.append("							</tbody>");
							sb.append("						</table>");

						}
						sb.append("					</td>");
						sb.append("				</tr>");
						sb.append("			</tbody>");
						sb.append("		</table>");
						sb.append("	</div>");

					}
				}

				sb.append("	<table border='0' style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr>");
				sb.append("				<td align='right' bgcolor='#FFFFFF' class='infoValue' height='20'></td>");
				sb.append("			</tr>");
				sb.append("			<tr height='20'>");
				sb.append("				<td style='padding: 5px;' class='mainHeader'>Inquiries");
				sb.append("					(reported for past 24 months)</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<div align='center' style='padding-left: 10px;'>");
				sb.append("		<table cellpadding='0' cellspacing='0' align='center' border='0'");
				sb.append("			style='width: 100%'>");
				sb.append("			<tbody>");
				sb.append("				<tr>");
				sb.append("					<td align='right' bgcolor='#FFFFFF' class='infoValue' height='20'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td>");
				sb.append("						<table class='box1' cellpadding='0' cellspacing='0' align='center'");
				sb.append("							border='0px' style='width: 100%'>");
				sb.append("							<tbody>");
				sb.append("								<tr height='20'>");
				sb.append("									<td height='20' class='subHeader'>Credit Grantor</td>");
				sb.append("									<td class='subHeader'>Date of Inquiry</td>");
				sb.append("									<td class='subHeader'>Purpose</td>");
				sb.append("									<td class='subHeader'>Amount</td>");
				sb.append("									<td class='subHeader'>Remark</td>");
				sb.append("								</tr>");

				/*
				 * indv_report.getJSONObject("INQUIRY-HISTORY");
				 * 
				 * get the contents from this object and place here , the object
				 * currently null
				 * 
				 * START
				 */

				sb.append("								<tr height='1'>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("								</tr>");
				/*
				 * END
				 */
				sb.append("							</tbody>");
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("				</tr>");
				sb.append("			</tbody>");
				sb.append("		</table>");
				sb.append("	</div>");
				sb.append("	<table align='center' border='0' cellpadding='0' cellspacing='0'");
				sb.append("		style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr>");
				sb.append("				<td>");
				sb.append("					<table style='width: 100%'>");
				sb.append("						<tbody>");
				sb.append("							<tr>");
				sb.append("								<td align='right' bgcolor='#FFFFFF' class='infoValue'");
				sb.append("									height='20'></td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append(
						"								<td align='center' class='AccHeader'>-END OF INDIVIDUAL REPORT-</td>");
				sb.append("							</tr>");
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("				</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<table border='0' style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr>");
				sb.append("				<td align='right' bgcolor='#FFFFFF' class='infoValue' height='20'></td>");
				sb.append("			</tr>");
				sb.append("			<tr height='20'>");
				sb.append("				<td style='padding: 5px;' class='mainHeader'>Appendix</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("	<div align='center' style='padding-left: 10px;'>");
				sb.append("		<table align='center' border='0' cellpadding='0' style='width: 100%'");
				sb.append("			cellspacing='0'>");
				sb.append("			<tbody>");
				sb.append("				<tr>");
				sb.append("					<td align='right' bgcolor='#FFFFFF' class='infoValue' height='20'></td>");
				sb.append("				</tr>");
				sb.append("				<tr>");
				sb.append("					<td>");
				sb.append("						<table class='box1' align='center' border='0px' cellpadding='0'");
				sb.append("							cellspacing='0' style='width: 100%'>");
				sb.append("							<tbody>");
				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='subHeader'>Section</td>");
				sb.append("									<td align='left' class='subHeader'>Code</td>");
				sb.append("									<td align='left' class='subHeader'>Description</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='1'>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("									<td style='background-color: #A7CBE3;'></td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20' style='background-color: #eef2f5;'>");
				sb.append("									<td align='left' class='dataValue1'>Account Summary</td>");
				sb.append("									<td align='left' class='dataValue1'>Number of Delinquent");
				sb.append("										Accounts</td>");
				sb.append("									<td align='left' class='dataValue1'>Indicates number of");
				sb.append(
						"										accounts that the applicant has defaulted on within the last 6");
				sb.append("										months</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataValue1'>Account Information -");
				sb.append("										Credit Grantor</td>");
				sb.append("									<td align='left' class='dataValue1'>XXXX</td>");
				sb.append("									<td align='left' class='dataValue1'>Name of grantor");
				sb.append(
						"										undisclosed as credit grantor is different from inquiring");
				sb.append("										institution</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20' style='background-color: #eef2f5;'>");
				sb.append("									<td align='left' class='dataValue1'>Account Information -");
				sb.append("										Account #</td>");
				sb.append("									<td align='left' class='dataValue1'>xxxx</td>");
				sb.append("									<td align='left' class='dataValue1'>Account Number");
				sb.append(
						"										undisclosed as credit grantor is different from inquiring");
				sb.append("										institution</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataValue1'>Payment History /");
				sb.append("										Asset Classification</td>");
				sb.append("									<td align='left' class='dataValue1'>XXX</td>");
				sb.append("									<td align='left' class='dataValue1'>Data not reported by");
				sb.append("										institution</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20' style='background-color: #eef2f5;'>");
				sb.append("									<td align='left' class='dataValue1'>Payment History /");
				sb.append("										Asset Classification</td>");
				sb.append(
						"									<td align='left' class='dataValue1'>&nbsp;&nbsp;&nbsp;&nbsp;-</td>");
				sb.append("									<td align='left' class='dataValue1'>Not applicable</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataValue1'>Payment History /");
				sb.append("										Asset Classification</td>");
				sb.append("									<td align='left' class='dataValue1'>STD</td>");
				sb.append("									<td align='left' class='dataValue1'>Account Reported as");
				sb.append("										STANDARD Asset</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20' style='background-color: #eef2f5;'>");
				sb.append("									<td align='left' class='dataValue1'>Payment History /");
				sb.append("										Asset Classification</td>");
				sb.append("									<td align='left' class='dataValue1'>SUB</td>");
				sb.append("									<td align='left' class='dataValue1'>Account Reported as");
				sb.append("										SUB-STANDARD Asset</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataValue1'>Payment History /");
				sb.append("										Asset Classification</td>");
				sb.append("									<td align='left' class='dataValue1'>DBT</td>");
				sb.append("									<td align='left' class='dataValue1'>Account Reported as");
				sb.append("										DOUBTFUL Asset</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20' style='background-color: #eef2f5;'>");
				sb.append("									<td align='left' class='dataValue1'>Payment History /");
				sb.append("										Asset Classification</td>");
				sb.append("									<td align='left' class='dataValue1'>LOS</td>");
				sb.append("									<td align='left' class='dataValue1'>Account Reported as");
				sb.append("										LOSS Asset</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataValue1'>Payment History /");
				sb.append("										Asset Classification</td>");
				sb.append("									<td align='left' class='dataValue1'>SMA</td>");
				sb.append("									<td align='left' class='dataValue1'>Account Reported as");
				sb.append("										SPECIAL MENTION</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20' style='background-color: #eef2f5;'>");
				sb.append("									<td align='left' class='dataValue1'>CRIF HIGHMARK SCORE");
				sb.append("										(S)</td>");
				sb.append("									<td align='left' class='dataValue1'>PERFORM-Consumer</td>");
				sb.append("									<td align='left' class='dataValue1'>Score has reckoned");
				sb.append(
						"										from credit history, pursuit of new credit, payment history,");
				sb.append(
						"										type of credit in &nbsp;&nbsp;use and outstanding debt.</td>");
				sb.append("								</tr>");
				sb.append("								<tr height='20'>");
				sb.append("									<td align='left' class='dataValue1'>CRIF HIGHMARK SCORE");
				sb.append("										(S)</td>");
				sb.append("									<td align='left' class='dataValue1'>PERFORM-Consumer</td>");
				sb.append("									<td align='left' class='dataValue1' width='480'>Score of");
				sb.append("										'0' is no hit.</td>");
				sb.append("								</tr>");
				sb.append("							</tbody>");
				sb.append("						</table>");
				sb.append("					</td>");
				sb.append("				</tr>");
				sb.append("			</tbody>");
				sb.append("		</table>");
				sb.append("	</div>");
				sb.append("	<table summary='' align='center' style='width: 100%'>");
				sb.append("		<tbody>");
				sb.append("			<tr>");
				sb.append("				<td>");
				sb.append("					<table summary='' border='0' style='width: 100%'>");
				sb.append("						<tbody>");
				sb.append("							<tr height='10'>");
				sb.append("								<td colspan='5'>");
				sb.append("									<hr color='silver' />");
				sb.append("								</td>");
				sb.append("							</tr>");
				sb.append("							<tr>");
				sb.append("								<td color='#CCCCCC' valign='top' width='70'");
				sb.append("									class='disclaimerValue'>Disclaimer:</td>");
				sb.append(
						"								<td colspan='4' class='disclaimerValue' style='text-align:left;font-size: 10px;  color: #808080;'>This document");
				sb.append(
						"									contains proprietary information to CRIF High Mark and may not");
				sb.append("									be used or disclosed to others, except with the written");
				sb.append(
						"									permission of CRIF High Mark. Any paper copy of this document");
				sb.append(
						"									will be considered uncontrolled. If you are not the intended");
				sb.append(
						"									recipient, you are not authorized to read, print, retain, copy,");
				sb.append(
						"									disseminate, distribute, or use this information or any part");
				sb.append(
						"									thereof. PERFORM score provided in this document is joint work");
				sb.append("									of CRIF SPA (Italy) and CRIF High Mark(India).</td>");
				sb.append("							</tr>");
				sb.append("			<tr>");
				sb.append("				<td align='right' bgcolor='#FFFFFF' class='infoValue' height='10'></td>");
				sb.append("			</tr>");
				sb.append("							<tr>");
				sb.append("								<td><br /> <br /></td>");
				sb.append(
						"								<td style='text-align:left;font-size: 10px;  color: #808080;' width='300'");
				sb.append("									class='disclaimerValue'>Copyrights reserved (c) 2018</td>");
				sb.append(
						"								<td style='text-align:left;font-size: 10px;  color: #808080;' width='400'");
				sb.append("									class='disclaimerValue'>CRIF High Mark Credit Information");
				sb.append("									Services Pvt. Ltd</td>");
				sb.append(
						"								<td style='text-align:left;font-size: 10px;  color: #808080;' width='300'");
				sb.append("									class='disclaimerValue'>Company Confidential Data</td>");
				sb.append("								<td width='70'><br /> <br /></td>");
				sb.append("							</tr>");
				sb.append("						</tbody>");
				sb.append("					</table>");
				sb.append("				</td>");
				sb.append("			</tr>");
				sb.append("		</tbody>");
				sb.append("	</table>");
				sb.append("</body>");
				sb.append("</html>");
			}
			/* byteArray= */htmlToPdf(sb.toString(), fileName, resProp, crifTrackerDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("CrifPdfCreator || pdfCreation() || EXception :: " + e);
		}
		logger.info("CrifPdfCreator || pdfCreation() || ByteArray ::" + byteArray);
		logger.info("CrifPdfCreator || pdfCreation() || END");
		return byteArray;
	}

	public static CrifTrackerDTO htmlToPdf(String html, String fileName, PropertyFile resProp,
			CrifTrackerDTO crifTrackerDTO) {
		logger.info("CrifPdfCreator || htmlToPdf() || Start");
		String returnString = "";
		File file = null;
		byte bar[] = null;
		try {

			// step 1
			Document document = new Document(PageSize.A3, 36, 36, 36, 36);
			// step 2
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(resProp.getString("com.qc.criff.pdf.savePath") + fileName + ".pdf"));
			// step 3
			document.open();
			// step 4
			String cssFile = "<style type='text/css'>@media print { 	table { 		page-break-after: auto; 		-webkit-print-color-adjust: exact; 	} 	thead { 		display: table-header-group; 	} 	tfoot { 		display: table-footer-group; 	} 	body { 		margin-top: 10px; 		margin-bottom: 10px; 		margin-right: 25px; 		margin-left: 30px; 	} }  .shading { 	background-color: #e6e6ff; 	background: #e6e6ff; }  .box { 	background: #FFFFFF; 	border-style: solid; 	border-width: thin; 	border-color: #FFFFFF; 	border-collapse: collapse; 	text-align: left; 	-moz-box-shadow: 0px 0px 30px #DADADA; 	-webkit-box-shadow: 0px 0px 30px #DADADA; 	box-shadow: 0px 0px 30px #DADADA; }  .box1 { 	background: #FFFFFF; 	border-style: solid; 	border-width: 0px; 	border-collapse: collapse; 	text-align: left; }  .tabStyle { 	background: #FFFFFF; 	border-style: inset; 	border-width: thin; 	border-color: black; 	border-collapse: collapse; }  .rowStyle { 	background: #FFFFFF; 	border-style: solid; 	border-width: thin; 	border-color: grey; 	border-collapse: collapse; }  .box1 tr:nt-child(even) { 	background-color: white; }  .box1 tr:nth-child(odd) { 	background-color: #F1F3F5; }  .style14 { 	font-face: segoe ui semibold; 	font-size: 2px; }  .summarytable { 	background: #FFFFFF; 	border-style: solid; 	border-width: 0px; 	border-collapse: collapse; 	text-align: left; 	border-left: none; 	border-right: none; }  .reportHead { 	font-family: segoe ui semibold; 	font-size: 24px; 	color: #0f3f6b; 	font-weight: 600; 	text-align: left; }  .dataHead { 	font-family: segoe ui semibold; 	font-size: 12px; 	font-weight: 600; 	color: #464646; 	text-align: right; 	text-indent: 5px; }  .mainHeader { 	font-family: segoe ui semibold; 	font-size: 16px; 	color: #FFFFFF; 	background: #0f3f6b; 	text-align: left; 	font-weight: 600; 	padding-bottom: 3px; }  .subHeader { 	font-family: segoe ui semibold; 	font-size: 13px; 	color: #0f3f6b; 	text-align: center; 	border-width: thin; 	border-collapse: collapse; 	border-bottom: 1px solid #A7CBE3; 	border-left: 0px; 	border-right: 0px; 	border-top: 0px; 	background: #FFFFFF; 	text-indent: 5px; 	font-weight: 600; }  .subHeader1 { 	font-family: segoe ui semibold; 	font-size: 13px; 	color: #0f3f6b; 	border-width: thin; 	border-collapse: collapse; 	border-bottom: 1px solid #A7CBE3; 	border-left: 0px; 	border-right: 0px; 	border-top: 0px; 	background: #FFFFFF; 	text-indent: 5px; 	font-weight: 600; }  .dataHeaderNone { 	font-family: segoe ui semibold; 	font-size: 14px; 	color: #0f3f6b; 	font-weight: 600; 	text-align: center; 	text-indent: 5px; 	white-space: nowrap; 	height: 23; 	valign: middle }  .subHeader2 { 	font-family: segoe ui semibold; 	border-collapse: collapse; 	border-bottom: 0px; 	border-left: 1px solid #ffffff; 	border-right: 0px; 	border-top: 1px solid #ffffff; 	background: #FFFFFF; 	text-indent: 5px; 	font-weight: 600; 	white-space: nowrap; }  .dataHeader { 	font-family: segoe ui semibold; 	font-size: 13px; 	color: #0f3f6b; 	font-weight: 600; 	text-align: left; 	text-indent: 5px; 	white-space: nowrap; 	padding-left: 5px;padding-top: 2px; 	 }  .dataHeaderScore { 	font-family: segoe ui semibold; 	font-size: 12px; 	color: #464646; 	font-weight: 600; 	text-align: left; 	text-indent: 5px; 	white-space: nowrap; 	padding-top: 2px; }  .dataValueValue { 	font-family: segoe ui semibold; 	font-size: 25px; 	font-weight: 600; 	color: #464646; 	text-align: left; 	padding-left: 7px; 	padding-top: 1px; }  .dataValuePerform { 	font-family: segoe ui semibold; 	font-size: 12px; 	font-weight: 600; 	color: #464646; 	text-align: left; 	padding-left: 7px; 	padding-top: 1px; }  .dataValuePerform2 { 	border-collapse: separate; 	Color: #464646; 	font-family: segoe ui semibold; 	font-size: 12px; 	font-weight: 280; }  .dataHeadern { 	font-family: segoe ui semibold; 	font-size: 13px; 	color: #0f3f6b; 	font-weight: 600; 	text-align: left; 	text-indent: 5px; 	padding-top: 2px; }  .dataValue { 	font-family: segoe ui semibold; 	font-size: 14px; 	font-weight: 600; 	color: #464646; 	text-align: left; 	padding-left: 7px; 	padding-top: 1px; 	 }  .dataAmtValue { 	font-family: segoe ui semibold; 	font-size: 14px; 	font-weight: 600; 	color: #464646; 	text-align: right; 	padding-right: 7px; 	padding-top: 1px; }  .dataHeader1 { 	font-family: segoe ui semibold; 	font-size: 12px; 	color: #0f3f6b; 	font-weight: 600; 	text-align: left; 	text-indent: 5px; }  .dataValue1 { 	font-family: segoe ui semibold; 	font-size: 12px; 	font-weight: 600; 	color: #464646; 	text-align: left; 	text-indent: 5px; }  .mainAccHeader { 	font-family: segoe ui semibold; 	font-size: 13px; 	color: #FFFFFF; 	background: #0f3f6b; 	font-weight: 600; }  .AccHeader { 	font-family: segoe ui semibold; 	font-size: 13px; 	color: #0f3f6b; 	font-weight: 600; 	text-indent: 5px; }  .subAccHeader { 	font-family: segoe ui semibold; 	font-size: 13px; 	color: #0f3f6b; 	background: #e6e6ff; 	font-weight: 600; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }  .AccValue { 	font-family: segoe ui semibold; 	font-size: 14px; 	font-weight: 600; 	color: #464646; 	text-indent: 5px; }  .AccValue1 { 	font-family: segoe ui semibold; 	font-size: 12px; 	font-weight: 600; 	color: #464646; 	text-indent: 5px; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }  .AccSummaryTab { 	border-width: thin; 	border-collapse: collapse; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; 	border-bottom: 0px; 	text-indent: 5px; }  .disclaimerValue { 	font-family: segoe ui semibold; 	font-size: 12px; 	font-weight: 500; 	color: grey; }  .infoValue { 	font-family: segoe ui semibold; 	font-size: 12px; 	font-weight: 500; 	color: grey; 	padding-right: 15px; 	font-style: normal; }  .maroonFields { 	color: Maroon; 	font-family: segoe ui semibold; 	font-size: 15px; 	font-weight: 600; }  .AccValueComm2 { 	font-family: segoe ui semibold; 	font-size: 11px; 	font-weight: 600; 	color: #464646; 	text-indent: 5px; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }  .AccValue2 { 	font-family: segoe ui semibold; 	font-size: 11px; 	font-weight: 600; 	color: #464646; 	text-indent: 5px; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }  .container { 	/* this will give container dimension, because floated child nodes don't give any */ 	/* if your child nodes are inline-blocked, then you don't have to set it */ 	overflow: auto; }  .container .headActive { 	/* float your elements or inline-block them to display side by side */ 	float: left; /* these are height and width dimensions of your header */ 	height: 10em; 	width: 1.5em; 	/* set to hidden so when there's too much vertical text it will be clipped. */ 	overflow: hidden; 	/* these are not relevant and are here to better see the elements */ 	background: #ffe1dc; 	color: #be0000; 	margin-right: 1px; 	font-family: segoe ui; 	font-weight: bold; }  .container .headActive .vertActive { 	/* line height should be equal to header width so text will be middle aligned */ 	line-height: 1.5em; 	/* setting background may yield better results in IE text clear type rendering */ 	background: #ffe1dc; 	color: #be0000; 	display: block; /* this will prevent it from wrapping too much text */ 	white-space: nowrap; /* so it stays off the edge */ 	padding-left: 3px; 	font-family: segoe ui; 	font-weight: bold; /* CSS3 specific totation code */ 	/* translate should have the same negative dimension as head height */ 	transform: rotate(-270deg) translate(1em, 0); 	transform-origin: -5px 30px; 	-moz-transform: rotate(-270deg) translate(1em, 0); 	-moz-transform-origin: -5px 30px; 	-webkit-transform: rotate(-270deg) translate(1em, 0); 	-webkit-transform-origin: -5px 30px; 	-ms-transform-origin: none; 	-ms-transform: none; 	-ms-writing-mode: tb-rl; 	*writing-mode: tb-rl; }  .container .headClosed { 	/* float your elements or inline-block them to display side by side */ 	float: left; /* these are height and width dimensions of your header */ 	height: 10em; 	width: 1.5em; 	/* set to hidden so when there's too much vertical text it will be clipped. */ 	overflow: hidden; 	/* these are not relevant and are here to better see the elements */ 	background: #e1f0be; 	color: #415a05; 	margin-right: 1px; 	font-family: segoe ui; 	font-weight: bold; }  .container .headClosed .vertClosed { 	/* line height should be equal to header width so text will be middle aligned */ 	line-height: 1.5em; 	/* setting background may yield better results in IE text clear type rendering */ 	background: #ffe1dc; 	color: #415a05; 	display: block; /* this will prevent it from wrapping too much text */ 	white-space: nowrap; /* so it stays off the edge */ 	padding-left: 3px; 	font-family: segoe ui; 	font-weight: bold; /* CSS3 specific totation code */ 	/* translate should have the same negative dimension as head height */ 	transform: rotate(-270deg) translate(1em, 0); 	transform-origin: -5px 30px; 	-moz-transform: rotate(-270deg) translate(1em, 0); 	-moz-transform-origin: -5px 30px; 	-webkit-transform: rotate(-270deg) translate(1em, 0); 	-webkit-transform-origin: -5px 30px; 	-ms-transform-origin: none; 	-ms-transform: none; 	-ms-writing-mode: tb-rl; 	*writing-mode: tb-rl; }  .infoValueNote { 	font-family: segoe ui semibold; 	font-size: 11px; 	font-weight: 500; 	color: grey; 	padding-right: 15px; 	font-style: normal; }</style>";
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes()),
					new ByteArrayInputStream(cssFile.getBytes()));
			// step 5
			document.close();
			file = new File(resProp.getString("com.qc.criff.pdf.savePath") + fileName + ".pdf");
			FileInputStream fis = new FileInputStream(file);
			bar = IOUtils.toByteArray(fis);
			returnString = Base64.encode(bar);
			crifTrackerDTO.setPdfPath(resProp.getString("com.qc.criff.pdf.savePath") + fileName + ".pdf");
			crifTrackerDTO.setPdfByteArray(returnString);

		} catch (Exception e) {
			logger.info("CrifPdfCreator || htmlToPdf() || EXception :: " + e);
		}
		logger.info("CrifPdfCreator || htmlToPdf() || END");
		return crifTrackerDTO;
	}

}
