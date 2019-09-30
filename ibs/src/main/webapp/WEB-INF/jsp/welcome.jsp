<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ResourceBundle;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon"
	href="http://www.qualtechedge.com/wp-content/uploads/2017/01/cropped-Logo-1-32x32.png"
	sizes="32x32" />

<!-- <meta http-equiv="refresh" content="15" /> -->
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7" />  
<title>Integration Box Services</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<style>
body {
	font-family: "Arial";
	font-size: 13px;
	line-height: 17px;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.tbl-top {
	background: #519dc0 !important;
	text-transform: capitalize;
	padding: 7px 0px;
	color: #fff;
	line-height: 15px;
	font-size: 14px;
	width: 99%;
}

tr.list_header td, tr.list_header th {
	text-transform: capitalize;
	padding: 7px 0px;
	width: auto;
}

.content-tbl {
	font-size: 13px;
	line-height: 17px;
	width: 99%;
}

.content-tbl td {
	border: 1px solid #ccc;
}

.cstm-btn {
	border: 0 none !important;
	color: #fff !important;
	margin: 0 2px;
	padding: 3px 10px !important;
	margin-right: 5px !important;
	height: 24px;
	background-color: #256380 !important;
	text-transform: uppercase;
	font-weight: bold;
	border-radius: 0px !important;
	text-decoration: none;
	-webkit-transition: width 2s;
	transition: border-top 2s;
}
</style>
<style>
ul.cstm-list {
	margin: 0;
	padding: 0;
	list-style: none;
	overflow-x: auto;
	max-height: 150px;
}

ul.cstm-list li {
	display: inline;
}

.cstm-btns h3.heading {
	background: #256380;
	color: #fff;
	font-size: 16px;
	line-height: 20px;
	margin: 15px 0;
	padding: 10px;
}

.cstm-btns button {
	background: #d5d5d5;
	border: none;
	margin: 5px;
	/* border-radius: 20px; */
	font-size: 12px;
	padding: 8px 20px;
	transition: all 0.3s ease 0s;
	cursor: pointer;
	outline: none;
	width: 290px;
}

.cstm-btns button:hover {
	background: #256380b0;
	color: #fff;
}

.cstm-btns button.active {
	background: #256380;
	color: #fff;
}

@media ( max-width : 767px) {
	.cstm-btns button {
		font-size: 11px;
		padding: 7px 15px;
	}
}

.collapsible {
	padding: 8px;
	margin: 2px;
	background-color: #256380;
	color: white;
	cursor: pointer;
	width: 99%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 14px;
}

.collapsible1 {
	padding: 8px;
	margin: 2px;
	background-color: #25546b;
	color: white;
	cursor: pointer;
	width: 99%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 14px;
}

.active, .collapsible:hover {
	background-color: #003b52;
}

.collapsible1.active, .collapsible1:hover {
	background-color: #25546b;
}

.content {
	padding: 0px 0px 1px 15px;
	display: block;
	overflow: auto;
}

.content1 {
	padding: 0px 0px 1px 15px;
	display: block;
	overflow: auto;
}

.overlay {
	overflow: auto;
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	//background: rgba(0, 0, 0, 0.7);
	text-align:center;
	transition: opacity 500ms;
	display: none;
	opacity: 0;
	background-image: url("../../../images/bedge-grunge.png");
}

.popup {
	margin: 70px auto;
	padding: 20px;
	color: #000;
	text-align: center;
	border-radius: 5px;
	width: 30%;
	position: relative;
	transition: all 5s ease-in-out;
}

.popup .close {
	position: absolute;
	top: 20px;
	right: 30px;
	transition: all 200ms;
	font-size: 30px;
	font-weight: bold;
	text-decoration: none;
	color: #333;
}

.popup .close:hover {
	color: #06D85F;
}

.popup .content {
	max-height: 30%;
	overflow: auto;
}

@media screen and (max-width: 700px) {
	.popup {
		width: 70%;
	}
}

.container {
	width: 75%;
	min-width: 300px;
	padding: 16px;
	text-align: left;
	background-color: white;
}

/* Full-width input fields */
select, input[type=text], input[type=file], input[type=email], input[type=password]
	{
	background-color: rgb(235, 243, 255);
	color: #000 !important;
	width: 95% !important;
	margin: 0px 0px;
	display: block;
	border: 1px solid #c7c7c7;
	border-radius: 3px;
	padding: 0px 8px;
	height: 24px;
	box-sizing: border-box;
	font-family: "Calibri", Arial, sans-serif !important;
	font-size: 14px;
	line-height: inherit;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
	background-color: #256380 !important;
	color: white;
	margin: 0 2px !important;
	padding: 3px 10px !important;
	margin-right: 5px !important;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

/* Add a blue text color to links */
a {
	padding: 5px;
	display: inline-block;
	color: #256380;
	cursor: pointer;
}

.text_footer {
	position: fixed;
	z-index: 1000000000000000;
	bottom: 0;
	margin-left: -8px;
	background-color: #333333 !important;
	width: 100%;
	text-align: center;
	font-weight: bold;
	color: white;
	font-family: "Calibri", Arial, sans-serif !important;
	font-size: 14px !important;
}

.fa {
	padding: 5px;
}
</style>
<script type="text/javascript">
	var checkbox = [];
	var servicesList = [];
	//var hostUrl="http://localhost:8282/integrationbox1.8/ib/ibs/api/";
	var hostUrl = '${pageContext.request.contextPath}' + "/ib/ibs/api/";
	var allServiceName = [];
	$(function() {
		//var testdata="{\"tid\":\"123\",\"servicename\":[\"AUTH DELIVERY\"]}";
		getServiceTagNameList();

	});

	function getServiceTagNameList() {
		var url = hostUrl + "v1/intergationServiceList";
		$.ajax(url,{method: 'POST', data: ''}).then(
		        function success(data) {
		        	document.getElementById("submitBtn").style.visibility = "hidden";
					var checkboxList = "";
					$.each(data,function(i, master) {

										checkboxList = checkboxList
												+ "<li><input type='checkbox'  style='display:none'  id='serviceName"
												+ i
												+ "' value='"
												+ master.service_name
												+ "' name='serviceList' onclick='changeStatus("
												+ i
												+ ")'><button id='serviceNamebutton"
												+ i + "' onclick=\"";
										if (master.service_name == "FINBIT") {
											checkboxList = checkboxList
													+ "openform(this," + i
													+ ");";
										} else {
											checkboxList = checkboxList
													+ "document.getElementById('serviceName"
													+ i
													+ "').checked=!document.getElementById('serviceName"
													+ i
													+ "').checked;changeStatus("
													+ i
													+ ");$(this).toggleClass('active');";
										}
										checkboxList = checkboxList + "\">"
												+ master.product_name
												+ "</button></li>";

										$(checkboxList).appendTo(
												'#checkboxList');
										allServiceName.push(master.service_name);
										checkboxList = "";

									});
	        },

	        function fail(data, status) {
	        	document.getElementById("submitBtn").style.visibility = "hidden";
	           // alert('Request failed.  Returned status of ' + status);
	        }
	    ); 

	}

	function getHistory() {
		
		popup();
		var urll = hostUrl + "v1/ibsHistory";
		var tId = document.getElementById('tId').value;
		var requestData = "{\"tid\":\"" + tId + "\"}";
		
		 $.ajax(urll, {method: 'POST', data: encodeURI('tid=' + tId)
		    }).then(
		        function success(data) {
		        	if (data!=null && data != "") {
			    		showHistory(data);
			    	}
		        },

		        function fail(data, status) {
		            alert('Request failed.  Returned status of test' + status);
		        }
		    );
		
		popup();
	}
	
	function showHistory(historys) {
		
		var url = hostUrl + "v1/intergationServiceList";
		$.ajax(url,{method: 'POST', data: ''}).then(
		        function success(data) {
		        	
		        	var history = "";
					$('#providerList').html('');
					$
							.each(
									data,
									function(i, master) {

										var row = "";
										$
												.each(
														historys,
														function(j, record) {

															if (record.serviceProvider != null
																	&& record.serviceProvider === master.service_name) {
																																
																	row=recordRow(row,record);	
															}
														});

										if (row === "") {

										} else {
											history = history
													+ "<button class='collapsible'  onclick=\"changeIcon(this,'HIB"+i+"');\"><img style='width:11px;' src='../../../images/plus.png'>   "
													+ master.product_name
													+ "</button>"
													+ "<div class='content' id='HIB"+i+"' style='display: none;'>";
													
														history = history
														 + "<table class='tbl-top'>"
														+ "<tr>"
														+ "<td width='20%'>Entity Name</td>"
														+ "<td width='20%'>Request Corelation Id</td>"
// 														 +"<td width='20%'>Service Provider</td>" 
														+ "<td width='15%'>Request Date & Time</td>"
														+ "<td width='15%'>Request Service Status</td>"
														+ "<td width='10%'>Download Report</td>"
														+ "</tr>"
														+ "</table>"
														+ "<div style='max-height: 300px; overflow-x: auto;'>"
														+ "<table id='history' class='content-tbl'>"
														+ row + "</table>"
														+ "</div>" + "</div>";
											
											row = "";
										}

										$(history)
												.appendTo('#providerList');
										history = "";

									});
	        },
	        function fail(data, status) {
	            alert('Request failed.  Returned status of ' + status);
	        }
	    );
		
	}
	function recordRow(row,record)
	{
		row = row
		+ "<tr>";
row = row
		+ "<td  width='20%'>"
		+ (record.fName != null ? record.fName
				: "")
		+ (record.mName != null ? " "
				+ record.mName
				: " ")
		+ (record.lName != null ? " "
				+ record.lName
				: " ")
		+ "</td>";
row = row
		+ "<td width='20%'>"
		+ (record.correlationid != null ? record.correlationid
				: "")
		+ "</td>";
/* row = row+"<td  width='20%'>"
				+ (record.serviceProvider != null ? record.serviceProvider
						: "") + "</td>"; */

row = row
		+ "<td  width='15%'>"
		+ (record.crdate != null ? record.crdate
				: "")
		+ "</td>";

if (record.status != null
		&& record.status === "PROCESSING") {
	row = row
			+ "<td  width='15%' style='text-align:center;'><img src='../../../images/loading.gif' style='width:25px'></td>";
} else if (record.status != null
		&& record.status === "SUCCESS") {
	row = row
			+ "<td  width='15%' style='text-align:center;'><img src='../../../images/Check.png' style='width:25px'></td>";
} else if (record.status != null
		&& record.status === "FAILURE") {
	row = row
			+ "<td  width='15%' style='text-align:center;'><img src='../../../images/cross.png' style='width:25px'></td>";
} else {
	row = row
			+ "<td  width='15%'>"
			+ (record.status != null ? record.status
					: "")
			+ "</td>";
}

row = row
		+ "<td  width='10%'  style='text-align:center;'>"
		+ (record.pdfpath != null ? "<a href='"
				+ hostUrl
				+ "pdfdownload?dc1Servers="
				+ record.pdfpath
				+ "' target='_blank'><img src='../../../images/download.png' style='width:25px'></a>"
				: "NA")
		+ "</td>";
row = row
		+ "</tr>";
		
		return row;
	}
	function getServiceStatus(requestData) {
		urll = hostUrl + "v1/ibs";
		// document.getElementById("submitBtn").style.visibility = "hidden";
		
		
		$.ajax(urll, {method: 'POST', data: encodeURI('requestData=' + requestData)
		    }).then(
		        function success(data) {
		        	if (data!=null && data != "") {
		        		//document.getElementById("sbmtBttn").innerHTML = "<font color='green'>"
						//	+ data
					// + "</font><br /><Strong>Note : </Strong>Page will refresh after 15 second for updated status." ;
				    	if (data==="false") {
				    		window.location.href = '${pageContext.request.contextPath}' + "/ib/ibs/api/SessionClosed";
				    	}
			    	}
		        },
		        function fail(data, status) {
		            alert('Request failed.  Returned status of test' + status);
		        }
		    );
	}

	function changeStatus(serviceId) {
		/* if (document.getElementById("serviceName" + serviceId).checked == false) {
			document.getElementById("selectAll").value = "Select All";
		} */
		var $boxes = $('input[name=serviceList]:checked');
		if ($boxes.length > 0) {
			var checkboxid = "serviceName" + serviceId;
			var checkBox = document.getElementById(checkboxid);
			if (checkBox.checked == true) {
				$('#serviceStatus' + serviceId).html("READY TO GO");
			} else {
				$('#serviceStatus' + serviceId).html("NEED TO INITIATE");
			}
			document.getElementById("submitBtn").style.visibility = "visible";
		} else {
			document.getElementById("submitBtn").style.visibility = "hidden";
		}

	}

	function selectedCheckbBoxesArray() {
		var data = [];
		var cboxes = document.getElementsByName('serviceList');
		var tId = document.getElementById('tId').value;
		var len = cboxes.length;
		for (var i = 0; i < len; i++) {
			if (cboxes[i].checked) {
				data.push("\"" + cboxes[i].value + "\"");
			}
		}
		checkbox = data;
		var requestData = "{\"tid\":\"" + tId + "\",\"servicename\":["
				+ checkbox + "]}";
		//alert(requestData);
		getServiceStatus(requestData);

		popup();
		setTimeout(popup, 1000);
		setTimeout(getHistory, 2000);
	}
	//setInterval(getHistory, 15000);
	function changeIcon(element,id) {
		
		if (element.innerHTML.match("minus")) {
			element.innerHTML = element.innerHTML.replace("minus", "plus");

		} else {
			element.innerHTML = element.innerHTML.replace("plus", "minus");

		}
		

		if(element.classList!=null && element.classList.toggle!=null){
			element.classList.toggle("active");
		}
		var content = document.getElementById(id);/* element.nextElementSibling; */
		if (content.style.display === "none") {
			content.style.display = "block";
		} else {
			content.style.display = "none";
		}
		
		
	}

	function popup() {

		if (document.getElementById("popup1").style.display === "block") {
			document.getElementById("popup1").style.display = "none";
			document.getElementById("popup1").style.opacity = "0";
		} else {
			document.getElementById("popup1").style.display = "block";
			document.getElementById("popup1").style.opacity = "1";
		}
	}
	function getReportPopup() {
		popup();
		sleep_second(2);
		popup();
	}

	function selectAll() {
		var button = document.getElementById("selectAll").value;
		for (var i = 0;; i++) {
			if (button == "Select All"
					&& document.getElementById("checkboxList").contains(
							document.getElementById("serviceName" + i))) {
				if (!document.getElementById("serviceName" + i).checked) {
					document.getElementById("serviceName" + i).checked = true;
					document.getElementById("selectAll").value = "Unselect All";
					changeStatus(i);
					$('#serviceNamebutton' + i).toggleClass('active');
				}
			} else if (button == "Unselect All"
					&& document.getElementById("checkboxList").contains(
							document.getElementById("serviceName" + i))) {
				if (document.getElementById("serviceName" + i).checked) {
					document.getElementById("serviceName" + i).checked = false;
					document.getElementById("selectAll").value = "Select All";
					changeStatus(i);
					$('#serviceNamebutton' + i).toggleClass('active');
				}
			} else {
				break;
			}
		}
	}

	var finbitEle = "";
	var finbitId = "";
	function openform(element, id) {

		if (document.getElementById("openform").style.display === "block") {
			document.getElementById("openform").style.display = "none";
			document.getElementById("openform").style.opacity = "0";

		} else {
			document.getElementById("openform").style.display = "block";
			document.getElementById("openform").style.opacity = "1";
			finbitEle = element;
			finbitId = id;
		}
	}
	var formId = 1;
	var formList = [ "", "" ];

	function createElementFromHTML() {
		var div = document.createElement('div');
		div.id = "div" + formId;
		formList[formId] = div;
		div.innerHTML = "<h3 style='width:95%'>Account Detail "
				+ (formId + 1)
				+ " <a style='float: right;' onclick='deleteform("
				+ formId
				+ ");'>&#10006;</a></h3> <label for='bankStatement["+formId+"]'><b>Bank Statement</b></label> <input type='file'  id='bankStatement'  accept='application/pdf' name='bankStatement["+formId+"]' required> <label for='bankName["+formId+"]'><b>Bank Name</b></label> <select style='display:inline;'  name='bankName["
				+ formId
				+ "]' required> <option value=''>Select Bank</option><option value='ICICI'> ICICI </option> <option value='SBI'> SBI </option> <option value='KOTAK'> KOTAK </option> <option value='YES'> YES </option> <option value='HDFC'> HDFC </option> <option value='AXIS'> AXIS </option> <option value='SYNDICATE'> SYNDICATE </option> <option value='CORPORATION'> CORPORATION </option> <option value='PNB'> PNB </option> <option value='UNITED_BANK'> UNITED BANK </option> <option value='INDUSIND'> INDUSIND </option> <option value='CENTRAL_BANK'> CENTRAL BANK </option> <option value='CITI'> CITI </option> <option value='DBS'> DBS </option> <option value='CANARA'> CANARA </option> <option value='STANDARD_CHARTERED'> STANDARD CHARTERED </option> <option value='RBL'> RBL </option> <option value='UNION_BANK'> UNION BANK </option> <option value='ALLAHABAD'> ALLAHABAD </option> <option value='BANK_OF_BARODA'> BANK OF BARODA </option> <option value='STATE_BANK_PATIALA'> STATE BANK PATIALA </option> <option value='STATE_BANK_MYSORE'> STATE BANK MYSORE </option> <option value='STATE_BANK_TRAVANCORE'> STATE BANK TRAVANCORE </option> <option value='TAMILNAD_MERCANTILE'> TAMILNAD MERCANTILE </option> <option value='STATE_BANK_BIKANER_JAIPUR'> STATE BANK BIKANER JAIPUR </option> <option value='STATE_BANK_HYDERABAD'> STATE BANK HYDERABAD </option> <option value='BANK_OF_INDIA'> BANK OF INDIA </option> <option value='BANK_OF_MAHARASHTRA'> BANK OF MAHARASHTRA </option> <option value='DENA_BANK'> DENA BANK </option> <option value='INDIAN_BANK'> INDIAN BANK </option> <option value='INDIAN_OVERSEAS'> INDIAN OVERSEAS </option> <option value='ORIENTAL_BANK'> ORIENTAL BANK </option> <option value='PUNJAB_SIND'> PUNJAB SIND </option> <option value='UCO'> UCO </option> <option value='VIJAYA'> VIJAYA </option> <option value='IDBI'> IDBI </option> <option value='BANDHAN'> BANDHAN </option> <option value='CATHOLIC_SYRIAN'> CATHOLIC SYRIAN </option> <option value='CITY_UNION'> CITY UNION </option> <option value='DHANLAXMI'> DHANLAXMI </option> <option value='DCB'> DCB </option> <option value='FEDERAL'> FEDERAL </option> <option value='IDFC'> IDFC </option> <option value='KARNATAKA'> KARNATAKA </option> <option value='JAMMU_KASHMIR'> JAMMU KASHMIR </option> <option value='KARUR_VYASA'> KARUR VYASA </option> <option value='LAKSHMI_VILAS'> LAKSHMI VILAS </option> <option value='NAINITAL'> NAINITAL </option> <option value='SOUTH_INDIAN'> SOUTH INDIAN </option> <option value='RBS'> RBS </option> <option value='SARASWAT_BANK'> SARASWAT BANK </option> <option value='TJSB'> TJSB </option> <option value='DNB'> DNB </option> <option value='DNS'> DNS </option> <option value='DEUTSCHE_BANK'> DEUTSCHE BANK </option> <option value='GREATER_BOMBAY'> GREATER BOMBAY </option> <option value='APNA_SAHAKARI_BANK'> APNA SAHAKARI BANK </option> <option value='AKHAND_ANAND'> AKHAND ANAND </option> <option value='BASSEIN_CATHOLIC'> BASSEIN CATHOLIC </option> <option value='ANDHRA_BANK'> ANDHRA BANK </option> <option value='AMBERNATH_JAI_HIND_CO_OP_BANK'> AMBERNATH JAI HIND CO OP BANK </option> <option value='PUNJAB_MAHARASHTRA_CO_OP_BANK'> PUNJAB MAHARASHTRA CO OP BANK </option> <option value='HSBC'> HSBC </option> <option value='BHARAT'> BHARAT </option> <option value='SVC_BANK'> SVC BANK </option> <option value='SDC'> SDC </option> <option value='SREENIDHI_SOUHARDA_SAHAKARI_BANK'> SREENIDHI SOUHARDA SAHAKARI BANK </option> <option value='OCEAN_FIRST'> OCEAN FIRST </option> <option value='CAPITAL_SMALL_FINANCE_BANK_LTD'> CAPITAL SMALL FINANCE BANK LTD </option> <option value='KALUPUR_COMMERCIAL_CO_OP_BANK'> KALUPUR COMMERCIAL CO OP BANK </option> <option value='KANGRA_CO_OP_BANK'> KANGRA CO OP BANK </option> <option value='BHARTIYA_MAHILA_BANK'> BHARTIYA MAHILA BANK </option> <option value='SARDAR_BHILADWALA_PARDI_PEOPLES_CO_OP_BANK'> SARDAR BHILADWALA PARDI PEOPLES CO OP BANK </option> <option value='MODEL_CO_OPERATIVE_BANK'> MODEL CO OPERATIVE BANK </option> <option value='MEHSANA_URBAN_CO_OP_BANK'> MEHSANA URBAN CO OP BANK </option> <option value='NEW_INDIA_CO_OP_BANK'> NEW INDIA CO OP BANK </option> <option value='THE_NARODA_NAGRIK_CO_OP_BANK'> THE NARODA NAGRIK CO OP BANK </option> <option value='COSMOS_BANK'> COSMOS BANK </option> <option value='JANAKALYAN_SAHAKARI_BANK'> JANAKALYAN SAHAKARI BANK </option> <option value='JANATA_SAHAKARI_BANK'> JANATA SAHAKARI BANK </option> <option value='MJALGAON_JANATA_SAHAKARI_BANK'> MJALGAON JANATA SAHAKARI BANK </option> <option value='MANVI_PATTANA_SOUHARADA_SAHAKARI_BANK'> MANVI PATTANA SOUHARADA SAHAKARI BANK </option> <option value='CENTRAL_CO_OPERATIVE_BANK'> CENTRAL CO OPERATIVE BANK </option> <option value='NUTAN_NAGARIK_SAHAKARI_BANK'> NUTAN NAGARIK SAHAKARI BANK </option> <option value='KALYAN_JANATA_SAHAKARI_BANK'> KALYAN JANATA SAHAKARI BANK </option> <option value='ABHYUDAYA_CO_OP_BANK'> ABHYUDAYA CO OP BANK </option> <option value='AIRTEL_BANK'> AIRTEL BANK </option> <option value='EQUITAS_SMALL_FINANCE_BANK'> EQUITAS SMALL FINANCE BANK </option> <option value='AU_SMALL_FINANCE_BANK'> AU SMALL FINANCE BANK </option> <option value='NAGPUR_NAGARIK_SAHAKARI_BANK'> NAGPUR NAGARIK SAHAKARI BANK </option> <option value='TEXTILE_CO_OPERATIVE_BANK'> TEXTILE CO OPERATIVE BANK </option> <option value='JANASEVA_SAHAKARI_BANK'> JANASEVA SAHAKARI BANK </option> <option value='SARASPUR_NAGARIK_CO_OP_BANK'> SARASPUR NAGARIK CO OP BANK </option> <option value='GP_PARSIK_BANK'> GP PARSIK BANK </option> <option value='TGMC_BANK'> TGMC BANK </option> <option value='SARVODAYA_SAHAKARI_BANK'> SARVODAYA SAHAKARI BANK </option> <option value='SHREE_KADI_NAGARIK_SAHAKARI_BANK'> SHREE KADI NAGARIK SAHAKARI BANK </option> <option value='SAURASHTRA_GRAMIN_BANK'> SAURASHTRA GRAMIN BANK </option> <option value='ASSOCIATE_CO_OP_BANK'> ASSOCIATE CO OP BANK </option> <option value='SOLAPUR_JANATA_SAHAKARI_BANK'> SOLAPUR JANATA SAHAKARI BANK </option> <option value='INDRAPRASTHA_SEHKARI_BANK'> INDRAPRASTHA SEHKARI BANK </option> <option value='MAHESH_SAHAKARI_BANK_LTD'> MAHESH SAHAKARI BANK LTD </option> <option value='PUNE_PEOPLES_CO_OP_BANK'> PUNE PEOPLES CO OP BANK </option> <option value='VASAI_VIKAS_SAH_BANK_LTD'> VASAI VIKAS SAH BANK LTD </option> <option value='MAHESH_BANK'> MAHESH BANK </option> <option value='UNION_CO_OP_BANK_LTD'> UNION CO OP BANK LTD </option> <option value='JANATHA_SEVA_CO_OP_BANK'> JANATHA SEVA CO OP BANK </option> <option value='The_Wai_Urban_Co_Operative_Bank_Limited'> THE WAI URBAN CO OPERATIVE BANK LIMITED </option> <option value='The_Vallabh_VidyaNagar_Commercial_Co_Operative_Bank_Ltd'> THE VALLABH VIDYANAGAR COMMERCIAL CO OPERATIVE BANK LTD </option> <option value='THE_VIJAY_CO_OP_BANK'> THE VIJAY CO OP BANK </option> <option value='ZOROASTRIAN_CO_OP_BANK'> ZOROASTRIAN CO OP BANK </option> <option value='VAIJAPUR_MARCHANTS_CO_OP_BANK'> VAIJAPUR MARCHANTS CO OP BANK </option> <option value='NKGSB_CO_OP_BANK'> NKGSB CO OP BANK </option> <option value='THE_SUTEX_CO_OP_BANK'> THE SUTEX CO OP BANK </option> <option value='THE_SURAT_PEOPLE_CO_OP_BANK_LTD'> THE SURAT PEOPLE CO OP BANK LTD </option> <option value='GUJARAT_AMBUJA_CO_OP_BANK_LTD'> GUJARAT AMBUJA CO OP BANK LTD </option> <option value='KANKARIAA_MANINAGAR_NAG_SAH_BANK_LTD'> KANKARIAA MANINAGAR NAG SAH BANK LTD </option> <option value='UJJIVAN_SMALL_FINANCE_BANK'> UJJIVAN SMALL FINANCE BANK </option> <option value='CITIZEN_CREDIT_CO_OP_BANK'> CITIZEN CREDIT CO OP BANK </option> <option value='PADMAVATHI_CO_OP_URBAN_BANK'> PADMAVATHI CO OP URBAN BANK </option> <option value='THE_SATARA_SAHAKARI_BANK_LTD'> THE SATARA SAHAKARI BANK LTD </option> <option value='MAHANAGAR_CO_OP_BANK_LTD'> MAHANAGAR CO OP BANK LTD </option> <option value='SURYODAY_SMALL_FINANCE_BANK_LTD'> SURYODAY SMALL FINANCE BANK LTD </option> <option value='SHREE_CO_OP_BANK_LTD'> SHREE CO OP BANK LTD </option> <option value='MANINAGAR_CO_OP_BANK_LTD'> MANINAGAR CO OP BANK LTD </option> <option value='THE_VARACHHA_CO_OP_BANK_LTD'> THE VARACHHA CO OP BANK LTD </option> <option value='SURAT_NATIONAL_CO_OP_BANK_LTD'> SURAT NATIONAL CO OP BANK LTD </option> <option value='VIDYA_SAHKARI_BANK_LTD'> VIDYA SAHKARI BANK LTD </option> <option value='THE_KARAD_URBAN_CO_OP_BANK_LTD'> THE KARAD URBAN CO OP BANK LTD </option> <option value='THE_PANCHSHEEL_MERCANTILE_CO_OP_BANK_LTD'> THE PANCHSHEEL MERCANTILE CO OP BANK LTD </option> <option value='MAGAVEERA_CO_OP_BANK_LTD'> MAGAVEERA CO OP BANK LTD </option> <option value='THANE_BHARAT_SAHAKARI_BANK_LTD'> THANE BHARAT SAHAKARI BANK LTD </option> <option value='SREE_MAHAYOGI_LAKSHMAMMA_CO_OP_BANK_LTD'> SREE MAHAYOGI LAKSHMAMMA CO OP BANK LTD </option> <option value='VIDARBHA_MERCHANTS_URBAN_CO_OP_BANK_LTD'> VIDARBHA MERCHANTS URBAN CO OP BANK LTD </option> <option value='PRATHAMA_BANK'> PRATHAMA BANK </option> <option value='PRIME_BANK'> PRIME BANK </option> <option value='DR_BABASAHEB_AMBEDKAR_MULTISTATE_CO_OP_BANK'> DR BABASAHEB AMBEDKAR MULTISTATE CO OP BANK </option> </select> <label for='accountType["+formId+"]'><b>Account Type</b></label> <select style='display:inline;'  name='accountType["
				+ formId
				+ "]' required><option  value=''>Select Account Type</option> <option value='SAVING'>SAVING</option> <option value='CURRENT'>CURRENT</option> <option value='CREDIT_CARD'>CREDIT CARD</option></select><br> <label for='email'><b>Email</b></label> <input autocomplete='false'  type='email' placeholder='Enter Email' name='email["+formId+"]' required> <label for='psw'><b>Statement Password</b></label> <input  autocomplete='false'  type='password' placeholder='Enter Password' name='psw["+formId+"]' > <hr>";//htmlString.trim();

		document.getElementById("container").appendChild(div);
		formId++;
	}
	function deleteform(index) {
		while (formList[index].firstChild) {
			formList[index].removeChild(formList[index].firstChild);
		}
	}

	$(document).ready(function() {
		$("#my_form").submit(function(event) {
			formSubmit(event);
		});
	});
	var inFormSubmit = 1;
	function formSubmit(event) {
		event.preventDefault(); //prevent default action
		var temp=document.getElementById("bankStatement").value.slice(-3).toUpperCase();
		if(temp==="PDF"){
		var post_url = hostUrl + "finbitRequest";//$(this).attr("action"); //get form action url
		var request_method = "POST";//$(this).attr("method"); //get form GET/POST method
		var form_data = new FormData(document.getElementById("my_form")); //Creates new FormData object
		//var form_data = $("#my_form").serialize();
		//console.log("form_data: ", form_data);
		$.ajax({
			url : post_url,
			type : request_method,
			data : form_data,
			contentType : false,
			cache : false,
			processData : false
		}).done(function(response) { //
			//$("#server-results").html(response);
		});
		if (inFormSubmit == 1) {
			document.getElementById('serviceName' + finbitId).checked = true;
			changeStatus(finbitId);
			$(finbitEle).toggleClass('active');
		}
		inFormSubmit = 2;
		openform();
		popup();
		setTimeout(popup, 1000);
	}else{
		alert("Please upload valid pdf");
	}
	}

	function clearform() {
		event.preventDefault(); //prevent default action 
		var post_url = hostUrl + "finbitRequest";//$(this).attr("action"); //get form action url
		var request_method = "POST";//$(this).attr("method"); //get form GET/POST method
		var form_data = new FormData(/* document.getElementById("my_form") */); //Creates new FormData object
		$.ajax({
			url : post_url,
			type : request_method,
			data : form_data,
			contentType : false,
			cache : false,
			processData : false
		}).done(function(response) { //
			//$("#server-results").html(response);
		});
		if (inFormSubmit == 2) {
			document.getElementById('serviceName' + finbitId).checked = false;
			changeStatus(finbitId);
			$(finbitEle).toggleClass('active');
		}
		inFormSubmit = 1;
		openform();
		popup();
		while (document.getElementById("container").firstChild) {
			document.getElementById("container").removeChild(
					document.getElementById("container").firstChild);
		}
		formId = 1;
		formList = [ "", "" ];
		setTimeout(popup, 1000);
	}


	
</script>

</head>
<body onload="getHistory();">
	<img
		style='width: 33%; max-width: 200px; display: inline; position: absolute;'
		src='../../../images/qc.png'>
	<div style='width: 98%; text-align: center; padding: 10px;'>
		<h1 style='display: inline; font-size: 50px; color: #2475a4;'>IBS</h1>
	</div>
	<div class="cstm-btns">
		<h3 class="heading">
			Service Name(s)
			<!-- <input id="selectAll" type="button"
				style='float: right; margin-right: 50px' class="cstm-btn"
				value="Select All" onclick="selectAll();" /> -->
		</h3>
		<ul class="cstm-list" id="checkboxList"></ul>
	</div>
	<table width="100%;">
		<tr>
			<td><%-- <label style="color: #003b52;"><b>Your Applicant
						Code </b></label><label>${transactionId}</label> --%><input type="hidden"
				name="transactionId" id="tId" value="${transactionId}"></td>
		</tr>
		<tr>
			<td><label style="color: #003b52;"><b>Your Applicant
						Code </b></label><label>${transactionId}</label><input type="button" id="submitBtn" class="cstm-btn" style="float: right;"
				value="Initiate" onclick="selectedCheckbBoxesArray();" /></td>
		</tr>
		<tr>
			<td id="sbmtBttn"></td>
		</tr>
	</table>



	<div class="legend legendFloat col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			History
			<input type="button" style='float: right;    margin-top: -13px; margin-right: 50px'
				class="cstm-btn" value="Refresh" onclick="getHistory();" />
		</div>
		<!-- <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8  main_body text-right"></div> -->
	</div>


	<style>
.text-right {
	float: right;
}

.legend {
	font-size: 18px;
	border-bottom: 1px solid #ddd;
	display: block;
	float: none !important;
	line-height: 7px;
	padding: 18px 7px 5px 0px;
	color: #000;
	margin-bottom: 1%;
	font-weight: 800;
}

.legendFloat {
	float: left;
}
</style>
	<div style='max-height: 500px; overflow-x: auto;' id="providerList"></div>
	<div id="popup1" class="overlay">
		<div class="popup">
			<img src='../../../images/loading.gif'
				style="width: 35%; min-width: 50px;">
			<h2>Please wait...</h2>
		</div>
	</div>


	<div id="openform" class="overlay">
		<div class="openFinbitform"
			style="text-align: -webkit-center; margin-top: 10px;">
			<form method="post" id="my_form" enctype='multipart/form-data'>
				<div class="container">
					<div style="float: right;">
						<button
							style="background-color: transparent; border: none; color: #256380; cursor: pointer; font-size: 14px;"
							type="reset" onmouseup="clearform();">Clear All</button>
						&nbsp;&nbsp;&nbsp; <a style="font-size: 21px;"
							onclick="openform();">&#10006;</a>
					</div>
					<h2 style="display: inline-block;">Fill Account Detail</h2>

					<hr>
					<h3>Account Detail 1</h3>
					<label for="bankStatement[0]"><b>Bank Statement</b></label> <input 
						type="file" accept='application/pdf' id='bankStatement'
						name="bankStatement[0]" required> <label for="bankName[0]"><b>Bank
							Name</b></label> <select style="display: inline;" name="bankName[0]" required>
						<option value=''>Select Bank</option>
						<option value="ICICI">ICICI</option>
						<option value="SBI">SBI</option>
						<option value="KOTAK">KOTAK</option>
						<option value="YES">YES</option>
						<option value="HDFC">HDFC</option>
						<option value="AXIS">AXIS</option>
						<option value="SYNDICATE">SYNDICATE</option>
						<option value="CORPORATION">CORPORATION</option>
						<option value="PNB">PNB</option>
						<option value="UNITED_BANK">UNITED BANK</option>
						<option value="INDUSIND">INDUSIND</option>
						<option value="CENTRAL_BANK">CENTRAL BANK</option>
						<option value="CITI">CITI</option>
						<option value="DBS">DBS</option>
						<option value="CANARA">CANARA</option>
						<option value="STANDARD_CHARTERED">STANDARD CHARTERED</option>
						<option value="RBL">RBL</option>
						<option value="UNION_BANK">UNION BANK</option>
						<option value="ALLAHABAD">ALLAHABAD</option>
						<option value="BANK_OF_BARODA">BANK OF BARODA</option>
						<option value="STATE_BANK_PATIALA">STATE BANK PATIALA</option>
						<option value="STATE_BANK_MYSORE">STATE BANK MYSORE</option>
						<option value="STATE_BANK_TRAVANCORE">STATE BANK
							TRAVANCORE</option>
						<option value="TAMILNAD_MERCANTILE">TAMILNAD MERCANTILE</option>
						<option value="STATE_BANK_BIKANER_JAIPUR">STATE BANK
							BIKANER JAIPUR</option>
						<option value="STATE_BANK_HYDERABAD">STATE BANK HYDERABAD
						</option>
						<option value="BANK_OF_INDIA">BANK OF INDIA</option>
						<option value="BANK_OF_MAHARASHTRA">BANK OF MAHARASHTRA</option>
						<option value="DENA_BANK">DENA BANK</option>
						<option value="INDIAN_BANK">INDIAN BANK</option>
						<option value="INDIAN_OVERSEAS">INDIAN OVERSEAS</option>
						<option value="ORIENTAL_BANK">ORIENTAL BANK</option>
						<option value="PUNJAB_SIND">PUNJAB SIND</option>
						<option value="UCO">UCO</option>
						<option value="VIJAYA">VIJAYA</option>
						<option value="IDBI">IDBI</option>
						<option value="BANDHAN">BANDHAN</option>
						<option value="CATHOLIC_SYRIAN">CATHOLIC SYRIAN</option>
						<option value="CITY_UNION">CITY UNION</option>
						<option value="DHANLAXMI">DHANLAXMI</option>
						<option value="DCB">DCB</option>
						<option value="FEDERAL">FEDERAL</option>
						<option value="IDFC">IDFC</option>
						<option value="KARNATAKA">KARNATAKA</option>
						<option value="JAMMU_KASHMIR">JAMMU KASHMIR</option>
						<option value="KARUR_VYASA">KARUR VYASA</option>
						<option value="LAKSHMI_VILAS">LAKSHMI VILAS</option>
						<option value="NAINITAL">NAINITAL</option>
						<option value="SOUTH_INDIAN">SOUTH INDIAN</option>
						<option value="RBS">RBS</option>
						<option value="SARASWAT_BANK">SARASWAT BANK</option>
						<option value="TJSB">TJSB</option>
						<option value="DNB">DNB</option>
						<option value="DNS">DNS</option>
						<option value="DEUTSCHE_BANK">DEUTSCHE BANK</option>
						<option value="GREATER_BOMBAY">GREATER BOMBAY</option>
						<option value="APNA_SAHAKARI_BANK">APNA SAHAKARI BANK</option>
						<option value="AKHAND_ANAND">AKHAND ANAND</option>
						<option value="BASSEIN_CATHOLIC">BASSEIN CATHOLIC</option>
						<option value="ANDHRA_BANK">ANDHRA BANK</option>
						<option value="AMBERNATH_JAI_HIND_CO_OP_BANK">AMBERNATH
							JAI HIND CO OP BANK</option>
						<option value="PUNJAB_MAHARASHTRA_CO_OP_BANK">PUNJAB
							MAHARASHTRA CO OP BANK</option>
						<option value="HSBC">HSBC</option>
						<option value="BHARAT">BHARAT</option>
						<option value="SVC_BANK">SVC BANK</option>
						<option value="SDC">SDC</option>
						<option value="SREENIDHI_SOUHARDA_SAHAKARI_BANK">
							SREENIDHI SOUHARDA SAHAKARI BANK</option>
						<option value="OCEAN_FIRST">OCEAN FIRST</option>
						<option value="CAPITAL_SMALL_FINANCE_BANK_LTD">CAPITAL
							SMALL FINANCE BANK LTD</option>
						<option value="KALUPUR_COMMERCIAL_CO_OP_BANK">KALUPUR
							COMMERCIAL CO OP BANK</option>
						<option value="KANGRA_CO_OP_BANK">KANGRA CO OP BANK</option>
						<option value="BHARTIYA_MAHILA_BANK">BHARTIYA MAHILA BANK
						</option>
						<option value="SARDAR_BHILADWALA_PARDI_PEOPLES_CO_OP_BANK">
							SARDAR BHILADWALA PARDI PEOPLES CO OP BANK</option>
						<option value="MODEL_CO_OPERATIVE_BANK">MODEL CO
							OPERATIVE BANK</option>
						<option value="MEHSANA_URBAN_CO_OP_BANK">MEHSANA URBAN CO
							OP BANK</option>
						<option value="NEW_INDIA_CO_OP_BANK">NEW INDIA CO OP BANK
						</option>
						<option value="THE_NARODA_NAGRIK_CO_OP_BANK">THE NARODA
							NAGRIK CO OP BANK</option>
						<option value="COSMOS_BANK">COSMOS BANK</option>
						<option value="JANAKALYAN_SAHAKARI_BANK">JANAKALYAN
							SAHAKARI BANK</option>
						<option value="JANATA_SAHAKARI_BANK">JANATA SAHAKARI BANK
						</option>
						<option value="MJALGAON_JANATA_SAHAKARI_BANK">MJALGAON
							JANATA SAHAKARI BANK</option>
						<option value="MANVI_PATTANA_SOUHARADA_SAHAKARI_BANK">
							MANVI PATTANA SOUHARADA SAHAKARI BANK</option>
						<option value="CENTRAL_CO_OPERATIVE_BANK">CENTRAL CO
							OPERATIVE BANK</option>
						<option value="NUTAN_NAGARIK_SAHAKARI_BANK">NUTAN NAGARIK
							SAHAKARI BANK</option>
						<option value="KALYAN_JANATA_SAHAKARI_BANK">KALYAN JANATA
							SAHAKARI BANK</option>
						<option value="ABHYUDAYA_CO_OP_BANK">ABHYUDAYA CO OP BANK
						</option>
						<option value="AIRTEL_BANK">AIRTEL BANK</option>
						<option value="EQUITAS_SMALL_FINANCE_BANK">EQUITAS SMALL
							FINANCE BANK</option>
						<option value="AU_SMALL_FINANCE_BANK">AU SMALL FINANCE
							BANK</option>
						<option value="NAGPUR_NAGARIK_SAHAKARI_BANK">NAGPUR
							NAGARIK SAHAKARI BANK</option>
						<option value="TEXTILE_CO_OPERATIVE_BANK">TEXTILE CO
							OPERATIVE BANK</option>
						<option value="JANASEVA_SAHAKARI_BANK">JANASEVA SAHAKARI
							BANK</option>
						<option value="SARASPUR_NAGARIK_CO_OP_BANK">SARASPUR
							NAGARIK CO OP BANK</option>
						<option value="GP_PARSIK_BANK">GP PARSIK BANK</option>
						<option value="TGMC_BANK">TGMC BANK</option>
						<option value="SARVODAYA_SAHAKARI_BANK">SARVODAYA
							SAHAKARI BANK</option>
						<option value="SHREE_KADI_NAGARIK_SAHAKARI_BANK">SHREE
							KADI NAGARIK SAHAKARI BANK</option>
						<option value="SAURASHTRA_GRAMIN_BANK">SAURASHTRA GRAMIN
							BANK</option>
						<option value="ASSOCIATE_CO_OP_BANK">ASSOCIATE CO OP BANK
						</option>
						<option value="SOLAPUR_JANATA_SAHAKARI_BANK">SOLAPUR
							JANATA SAHAKARI BANK</option>
						<option value="INDRAPRASTHA_SEHKARI_BANK">INDRAPRASTHA
							SEHKARI BANK</option>
						<option value="MAHESH_SAHAKARI_BANK_LTD">MAHESH SAHAKARI
							BANK LTD</option>
						<option value="PUNE_PEOPLES_CO_OP_BANK">PUNE PEOPLES CO
							OP BANK</option>
						<option value="VASAI_VIKAS_SAH_BANK_LTD">VASAI VIKAS SAH
							BANK LTD</option>
						<option value="MAHESH_BANK">MAHESH BANK</option>
						<option value="UNION_CO_OP_BANK_LTD">UNION CO OP BANK LTD
						</option>
						<option value="JANATHA_SEVA_CO_OP_BANK">JANATHA SEVA CO
							OP BANK</option>
						<option value="The_Wai_Urban_Co_Operative_Bank_Limited">
							THE WAI URBAN CO OPERATIVE BANK LIMITED</option>
						<option
							value="The_Vallabh_VidyaNagar_Commercial_Co_Operative_Bank_Ltd">
							THE VALLABH VIDYANAGAR COMMERCIAL CO OPERATIVE BANK LTD</option>
						<option value="THE_VIJAY_CO_OP_BANK">THE VIJAY CO OP BANK
						</option>
						<option value="ZOROASTRIAN_CO_OP_BANK">ZOROASTRIAN CO OP
							BANK</option>
						<option value="VAIJAPUR_MARCHANTS_CO_OP_BANK">VAIJAPUR
							MARCHANTS CO OP BANK</option>
						<option value="NKGSB_CO_OP_BANK">NKGSB CO OP BANK</option>
						<option value="THE_SUTEX_CO_OP_BANK">THE SUTEX CO OP BANK
						</option>
						<option value="THE_SURAT_PEOPLE_CO_OP_BANK_LTD">THE SURAT
							PEOPLE CO OP BANK LTD</option>
						<option value="GUJARAT_AMBUJA_CO_OP_BANK_LTD">GUJARAT
							AMBUJA CO OP BANK LTD</option>
						<option value="KANKARIAA_MANINAGAR_NAG_SAH_BANK_LTD">
							KANKARIAA MANINAGAR NAG SAH BANK LTD</option>
						<option value="UJJIVAN_SMALL_FINANCE_BANK">UJJIVAN SMALL
							FINANCE BANK</option>
						<option value="CITIZEN_CREDIT_CO_OP_BANK">CITIZEN CREDIT
							CO OP BANK</option>
						<option value="PADMAVATHI_CO_OP_URBAN_BANK">PADMAVATHI CO
							OP URBAN BANK</option>
						<option value="THE_SATARA_SAHAKARI_BANK_LTD">THE SATARA
							SAHAKARI BANK LTD</option>
						<option value="MAHANAGAR_CO_OP_BANK_LTD">MAHANAGAR CO OP
							BANK LTD</option>
						<option value="SURYODAY_SMALL_FINANCE_BANK_LTD">SURYODAY
							SMALL FINANCE BANK LTD</option>
						<option value="SHREE_CO_OP_BANK_LTD">SHREE CO OP BANK LTD
						</option>
						<option value="MANINAGAR_CO_OP_BANK_LTD">MANINAGAR CO OP
							BANK LTD</option>
						<option value="THE_VARACHHA_CO_OP_BANK_LTD">THE VARACHHA
							CO OP BANK LTD</option>
						<option value="SURAT_NATIONAL_CO_OP_BANK_LTD">SURAT
							NATIONAL CO OP BANK LTD</option>
						<option value="VIDYA_SAHKARI_BANK_LTD">VIDYA SAHKARI BANK
							LTD</option>
						<option value="THE_KARAD_URBAN_CO_OP_BANK_LTD">THE KARAD
							URBAN CO OP BANK LTD</option>
						<option value="THE_PANCHSHEEL_MERCANTILE_CO_OP_BANK_LTD">
							THE PANCHSHEEL MERCANTILE CO OP BANK LTD</option>
						<option value="MAGAVEERA_CO_OP_BANK_LTD">MAGAVEERA CO OP
							BANK LTD</option>
						<option value="THANE_BHARAT_SAHAKARI_BANK_LTD">THANE
							BHARAT SAHAKARI BANK LTD</option>
						<option value="SREE_MAHAYOGI_LAKSHMAMMA_CO_OP_BANK_LTD">
							SREE MAHAYOGI LAKSHMAMMA CO OP BANK LTD</option>
						<option value="VIDARBHA_MERCHANTS_URBAN_CO_OP_BANK_LTD">
							VIDARBHA MERCHANTS URBAN CO OP BANK LTD</option>
						<option value="PRATHAMA_BANK">PRATHAMA BANK</option>
						<option value="PRIME_BANK">PRIME BANK</option>
						<option value="DR_BABASAHEB_AMBEDKAR_MULTISTATE_CO_OP_BANK">
							DR BABASAHEB AMBEDKAR MULTISTATE CO OP BANK</option>
					</select> <label for="accountType[0]"><b>Account Type</b></label> <select
						style="display: inline;" name="accountType[0]" required>
						<option value=''>Select Account Type</option>
						<option value="SAVING">SAVING</option>
						<option value="CURRENT">CURRENT</option>
						<option value="CREDIT_CARD">CREDIT CARD</option>
					</select><br> <label for="email[0]"><b>Email</b></label> <input
						type="email" placeholder="Enter Email" name="email[0]"  autocomplete="false"  required>
					<label for="psw[0]"><b>Statement Password</b></label> <input
						type="password" placeholder="Enter Password"  autocomplete="false"  name="psw[0]">
					<hr>
					<div id="container"></div>
					<a onclick="createElementFromHTML()">Add &#10010;</a> <input
						type="submit" id="submitButton" class="registerbtn">
				</div>
			</form>

		</div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<% ResourceBundle res=ResourceBundle.getBundle("application"); %>
	<% 	String url=res.getString("mifin.app.version.url");%>
	<% 	String ibsVersion=res.getString("ibs.app.integration.version");%>
	<% 	request.setAttribute("url", url);%>
	<% 	request.setAttribute("ibsVersion", ibsVersion);%>
	<c:set var="url" scope="request" value="${url}" />
	<div class="text_footer">
	<% 
	
		 try 
		 {
			 %><c:import url="${url}" /> <%
		 }
		 catch (Exception e)
		 {
		 } %>
		
		IBS version
		<c:out value="${ibsVersion}" />
	</div>
</body>
</html>
