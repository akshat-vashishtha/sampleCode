<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.qualtech.cibil.api.utils.CibilUtil"%>
<%@page import="java.util.Map,java.util.HashMap"%>
<%@page import="java.util.List,java.util.ArrayList"%>

<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
<title>Consumer Base Report</title>
<style type="text/css">
@media print {
	table {
		page-break-after: auto;
		-webkit-print-color-adjust: exact;
	}
	thead {
		display: table-header-group;
	}
	tfoot {
		display: table-footer-group;
	}
	body {
		margin-top: 10px;
		margin-bottom: 10px;
		margin-right: 25px;
		margin-left: 30px;
	}
}

.shading {
	background-color: #1675a5 background:  #1675a5
}

.box {
	background: #FFFFFF;
	border-style: solid;
	border-width: thin;
	border-color: #FFFFFF;
	border-collapse: collapse;
	text-align: left;
	-moz-box-shadow: 0px 0px 30px #DADADA;
	-webkit-box-shadow: 0px 0px 30px #DADADA;
	box-shadow: 0px 0px 30px #DADADA;
}

.box1 {
	background: #FFFFFF;
	border-style: solid;
	border-width: 0px;
	border-collapse: collapse;
	text-align: left;
}

.tabStyle {
	background: #FFFFFF;
	border-style: inset;
	border-width: thin;
	border-color: black;
	border-collapse: collapse;
}

.rowStyle {
	background: #FFFFFF;
	border-style: solid;
	border-width: thin;
	border-color: grey;
	border-collapse: collapse;
}

.box1 tr:nt-child(even) {
	background-color: white;
}

.box1 tr:nth-child(odd) {
	background-color: #e5e5e5;
}

.style14 {
	
	font-size: 2px;
}

.summarytable {
	background: #FFFFFF;
	border-style: solid;
	border-width: 0px;
	border-collapse: collapse;
	text-align: left;
	border-left: none;
	border-right: none;
}

.reportHead {
	font-family: Arial;
	font-size: 24px;
	color: #1675a5 font-weight:  normal;
	text-align: left;
}

.dataHead {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-align: right;
	text-indent: 5px;
}

.mainHeader {
	font-family: Arial;
	font-size: 14px;
	color: #1675a5;
	/*background: #1675a5;*/
	text-align:  left;
	font-weight: normal;
	padding-bottom: 3px;
	padding-left: 10px;
}

.subHeader {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5 text-align:  left;
	border-width: thin;
	border-collapse: collapse;
	border-bottom: 1px solid #A7CBE3;
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	background: #FFFFFF;
	text-indent: 5px;
	font-weight: normal;
}

.subHeader1 {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5 border-width:  thin;
	border-collapse: collapse;
	border-bottom: 1px solid #A7CBE3;
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	background: #FFFFFF;
	text-indent: 5px;
	font-weight: normal;
}

.dataHeaderNone {
	font-family: Arial;
	font-size: 14px;
	color: #1675a5;
	font-weight: normal;
	text-align: center;
	text-indent: 5px;
	white-space: nowrap;
	height: 23;
	valign: middle
}

.subHeader2 {
	font-family: Arial;
	border-collapse: collapse;
	border-bottom: 0px;
	border-left: 1px solid #ffffff;
	border-right: 0px;
	border-top: 1px solid #ffffff;
	background: #FFFFFF;
	text-indent: 5px;
	font-weight: normal;
	white-space: nowrap;
}

.dataHeader {
background:none;
	font-family: Arial;
	font-size: 13px;
	color: #1675a5;
	font-weight: normal;
	text-align: left;
	text-indent: 5px;
	white-space: nowrap;
	padding: 5px 0;
	text-transform: capitalize;
}

.dataHeaderScore {
	font-family: Arial;
	font-size: 12px;
	color: #464646;
	font-weight: normal;
	text-align: left;
	text-indent: 5px;
	white-space: nowrap;
	padding-top: 2px;
}

.dataValueValue {
	font-family: Arial;
	font-size: 25px;
	font-weight: normal;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
}

.dataValuePerform {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
}

.dataValuePerform2 {
	border-collapse: separate;
	Color: #464646;
	font-family: Arial;
	font-size: 12px;
	font-weight: 280;
}

.dataHeadern {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5 font-weight:  normal;
	text-align: left;
	text-indent: 5px;
	padding-top: 2px;
}

.dataValue {
	font-family: Arial;
	font-size: 13px;
	font-weight: 600;
	color: #464646;
	text-align: left;
	padding: 5px 0;
	text-transform: capitalize;
}

.dataAmtValue {
	font-family: Arial;
	font-size: 14px;
	font-weight: normal;
	color: #464646;
	text-align: right;
	padding-right: 7px;
	padding-top: 1px;
}

.dataHeader1 {
	font-family: Arial;
	font-size: 12px;
	color: #1675a5;
	font-weight: normal;
	text-align: left;
	text-indent: 5px;
}

.dataValue1 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-align: left;
	text-indent: 5px;
}

.mainAccHeader {
	font-family: Arial;
	font-size: 13px;
	color: #FFFFFF;
	background: #1675a5 font-weight:  normal;
}

.AccHeader {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5 font-weight:  normal;
	text-indent: 5px;
}

.subAccHeader {
	font-family: Arial;
	font-size: 13px;
	color: #333;
	background: #f0f0f0;
	font-weight: normal;
	border-width: thin;
	border-bottom: 0.5px solid #ccc;
	border-left: 0.5px solid #ccc;
	border-right: 0.5px solid #ccc;
	border-top: 0.5px solid #ccc;
}

.AccValue {
	font-family: Arial;
	font-size: 14px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
}

.AccValue1 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
	border-width: thin;
	border-bottom: 0.5px solid #ccc;
	border-left: 0.5px solid #ccc;
	border-right: 0.5px solid #ccc;
	border-top: 0.5px solid #ccc;
	height: 20px;
}

.AccSummaryTab {
	border-width: thin;
	border-collapse: collapse;
	border-left: 1px solid #A7CBE3;
	border-right: 1px solid #A7CBE3;
	border-top: 1px solid #A7CBE3;
	border-bottom: 0px;
	text-indent: 5px;
}

.disclaimerValue {
	font-family: Arial;
	font-size: 12px;
	font-weight: 500;
	color: grey;
}

.infoValue {
	font-family: Arial;
	font-size: 12px;
	font-weight: 500;
	color: grey;
	padding-right: 15px;
	font-style: normal;
}

.maroonFields {
	color: Maroon;
	font-family: Arial;
	font-size: 15px;
	font-weight: normal;
}

.AccValueComm2 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
	border-width: thin;
	border-bottom: 0.5px solid #ccc;
	border-left: 0.5px solid #ccc;
	border-right: 0.5px solid #ccc;
	border-top: 0.5px solid #ccc;
	height: 20px;
}

.AccValue2 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
	border-width: thin;
	border-bottom: 1px solid #A7CBE3;
	border-left: 1px solid #A7CBE3;
	border-right: 1px solid #A7CBE3;
	border-top: 1px solid #A7CBE3;
}

.container {
	/* this will give container dimension, because floated child nodes don't give any */
	/* if your child nodes are inline-blocked, then you don't have to set it */
	overflow: auto;
}

.container .headActive {
	/* float your elements or inline-block them to display side by side */
	float: left; /* these are height and width dimensions of your header */
	height: 10em;
	width: 1.5em;
	/* set to hidden so when there's too much vertical text it will be clipped. */
	overflow: hidden;
	/* these are not relevant and are here to better see the elements */
	background: #ffe1dc;
	color: #be0000;
	margin-right: 1px;
	font-family: segoe ui;
	font-weight: normal;
}

.container .headActive .vertActive {
	/* line height should be equal to header width so text will be middle aligned */
	line-height: 1.5em;
	/* setting background may yield better results in IE text clear type rendering */
	background: #ffe1dc;
	color: #be0000;
	display: block; /* this will prevent it from wrapping too much text */
	white-space: nowrap; /* so it stays off the edge */
	padding-left: 3px;
	font-family: segoe ui;
	font-weight: normal; /* CSS3 specific totation code */
	/* translate should have the same negative dimension as head height */
	transform: rotate(-270deg) translate(1em, 0);
	transform-origin: -5px 30px;
	-moz-transform: rotate(-270deg) translate(1em, 0);
	-moz-transform-origin: -5px 30px;
	-webkit-transform: rotate(-270deg) translate(1em, 0);
	-webkit-transform-origin: -5px 30px;
	-ms-transform-origin: none;
	-ms-transform: none;
	-ms-writing-mode: tb-rl;
	*writing-mode: tb-rl;
}

.container .headClosed {
	/* float your elements or inline-block them to display side by side */
	float: left; /* these are height and width dimensions of your header */
	height: 10em;
	width: 1.5em;
	/* set to hidden so when there's too much vertical text it will be clipped. */
	overflow: hidden;
	/* these are not relevant and are here to better see the elements */
	background: #e1f0be;
	color: #415a05;
	margin-right: 1px;
	font-family: segoe ui;
	font-weight: normal;
}

.container .headClosed .vertClosed {
	/* line height should be equal to header width so text will be middle aligned */
	line-height: 1.5em;
	/* setting background may yield better results in IE text clear type rendering */
	background: #ffe1dc;
	color: #415a05;
	display: block; /* this will prevent it from wrapping too much text */
	white-space: nowrap; /* so it stays off the edge */
	padding-left: 3px;
	font-family: segoe ui;
	font-weight: normal; /* CSS3 specific totation code */
	/* translate should have the same negative dimension as head height */
	transform: rotate(-270deg) translate(1em, 0);
	transform-origin: -5px 30px;
	-moz-transform: rotate(-270deg) translate(1em, 0);
	-moz-transform-origin: -5px 30px;
	-webkit-transform: rotate(-270deg) translate(1em, 0);
	-webkit-transform-origin: -5px 30px;
	-ms-transform-origin: none;
	-ms-transform: none;
	-ms-writing-mode: tb-rl;
	*writing-mode: tb-rl;
}

.infoValueNote {
	font-family: Arial;
	font-size: 12px;
	font-weight: 500;
	color: grey;
	padding-right: 15px;
	font-style: normal;
}
</style>


<style type="text/css">
@media print {
	table {
		page-break-after: auto;
		-webkit-print-color-adjust: exact;
	}
	thead {
		display: table-header-group;
	}
	tfoot {
		display: table-footer-group;
	}
	body {
		margin-top: 10px;
		margin-bottom: 10px;
		margin-right: 25px;
		margin-left: 30px;
	}
}

.shading {
	background-color: #1675a5 background:  #1675a5
}

.box {
	background: #FFFFFF;
	border-style: solid;
	border-width: thin;
	border-color: #FFFFFF;
	border-collapse: collapse;
	text-align: left;
	-moz-box-shadow: 0px 0px 30px #DADADA;
	-webkit-box-shadow: 0px 0px 30px #DADADA;
	box-shadow: 0px 0px 30px #DADADA;
}

.box1 {
	background: #FFFFFF;
	border-style: solid;
	border-width: 0px;
	border-collapse: collapse;
	text-align: left;
}

.tabStyle {
	background: #FFFFFF;
	border-style: inset;
	border-width: thin;
	border-color: black;
	border-collapse: collapse;
}

.rowStyle {
	background: #FFFFFF;
	border-style: solid;
	border-width: thin;
	border-color: grey;
	border-collapse: collapse;
}

.box1 tr:nt-child(even) {
	background-color: white;
}

.box1 tr:nth-child(odd) {
	background-color: #e5e5e5;
}

.style14 {
	font-size: 2px;
}

.summarytable {
	background: #FFFFFF;
	border-style: solid;
	border-width: 0px;
	border-collapse: collapse;
	text-align: left;
	border-left: none;
	border-right: none;
}

.reportHead {
	font-family: Arial;
	font-size: 24px;
	color: #1675a5 font-weight:  normal;
	text-align: left;
}

.dataHead {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-align: right;
	text-indent: 5px;
}

.mainHeader {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5;
	background: #fff;;
	text-align: left;
	font-weight: normal;
	padding-bottom: 3px;
	padding: 5px 0;
}

hr.mainHeader {
	padding: 0;
	background: #1c82d0;
	height: 2px;
	border: none;
}

.subHeader {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5 text-align:  left;
	border-width: thin;
	border-collapse: collapse;
	border-bottom: 1px solid #A7CBE3;
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	background: #FFFFFF;
	text-indent: 5px;
	font-weight: normal;
}

.subHeader1 {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5 border-width:  thin;
	border-collapse: collapse;
	border-bottom: 1px solid #A7CBE3;
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	background: #FFFFFF;
	text-indent: 5px;
	font-weight: normal;
}

.dataHeaderNone {
	font-family: Arial;
	font-size: 14px;
	color: #1675a5;
	font-weight: normal;
	text-align: center;
	text-indent: 5px;
	white-space: nowrap;
	height: 23;
	valign: middle
}

.subHeader2 {
	font-family: Arial;
	border-collapse: collapse;
	border-bottom: 0px;
	border-left: 1px solid #ffffff;
	border-right: 0px;
	border-top: 1px solid #ffffff;
	background: #FFFFFF;
	text-indent: 5px;
	font-weight: normal;
	white-space: nowrap;
}

/*.dataHeader {
	font-family: Arial;
	font-size: 16px;
	color: #1675a3;
	font-weight: normal;
	text-align: left;
	text-indent: 5px;
	white-space: nowrap;
	padding-top: 2px;
}*/

.dataHeaderScore {
	font-family: Arial;
	font-size: 12px;
	color: #464646;
	font-weight: normal;
	text-align: left;
	text-indent: 5px;
	white-space: nowrap;
	padding-top: 2px;
}

.dataValueValue {
	font-family: Arial;
	font-size: 25px;
	font-weight: normal;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
}

.dataValuePerform {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
}

.dataValuePerform2 {
	border-collapse: separate;
	Color: #464646;
	font-family: Arial;
	font-size: 12px;
	font-weight: 280;
}

.dataHeadern {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5 font-weight:  normal;
	text-align: left;
	text-indent: 5px;
	padding-top: 2px;
}

/*.dataValue {
	font-family: Arial;
	font-size: 14px;
	font-weight: normal;
	color: #464646;
	text-align: left;
	padding-left: 0;
	padding-top: 1px;
}*/

.dataAmtValue {
	font-family: Arial;
	font-size: 14px;
	font-weight: normal;
	color: #464646;
	text-align: right;
	padding-right: 7px;
	padding-top: 1px;
}

.dataHeader1 {
	font-family: Arial;
	font-size: 12px;
	color: #1675a5;
	font-weight: normal;
	text-align: left;
	text-indent: 5px;
}

.dataValue1 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-align: left;
	text-indent: 5px;
}

.mainAccHeader {
	font-family: Arial;
	font-size: 13px;
	color: #FFFFFF;
	background: #1675a5 font-weight:  normal;
}

.AccHeader {
	font-family: Arial;
	font-size: 13px;
	color: #1675a5;
	font-weight: normal;
	text-indent: 5px;
}

/*.subAccHeader {
	font-family: Arial;
	font-size: 13px;
	    color: #ffffff;
    background: #1675a5;
	font-weight: normal;
	border-width: thin;
	border-bottom: 1px solid #A7CBE3;
	border-left: 1px solid #A7CBE3;
	border-right: 1px solid #A7CBE3;
	border-top: 1px solid #A7CBE3;
}*/
.AccValue {
	font-family: Arial;
	font-size: 14px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
}

/*.AccValue1 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
	border-width: thin;
	border-bottom: 1px solid #A7CBE3;
	border-left: 1px solid #A7CBE3;
	border-right: 1px solid #A7CBE3;
	border-top: 1px solid #A7CBE3;
}*/
.AccSummaryTab {
	border-width: thin;
	border-collapse: collapse;
	border-left: 1px solid #A7CBE3;
	border-right: 1px solid #A7CBE3;
	border-top: 1px solid #A7CBE3;
	border-bottom: 0px;
	text-indent: 5px;
}

.disclaimerValue {
	font-family: Arial;
	font-size: 12px;
	font-weight: 500;
	color: grey;
}

.infoValue {
	font-family: Arial;
	font-size: 12px;
	font-weight: 500;
	color: grey;
	padding-right: 15px;
	font-style: normal;
}

.maroonFields {
	color: Maroon;
	font-family: Arial;
	font-size: 15px;
	font-weight: normal;
}

/*.AccValueComm2 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
	border-width: thin;
	border-bottom: 1px solid #A7CBE3;
	border-left: 1px solid #A7CBE3;
	border-right: 1px solid #A7CBE3;
	border-top: 1px solid #A7CBE3;
}*/
.AccValue2 {
	font-family: Arial;
	font-size: 12px;
	font-weight: normal;
	color: #464646;
	text-indent: 5px;
	border-width: thin;
	border-bottom: 1px solid #A7CBE3;
	border-left: 1px solid #A7CBE3;
	border-right: 1px solid #A7CBE3;
	border-top: 1px solid #A7CBE3;
}

.container {
	/* this will give container dimension, because floated child nodes don't give any */
	/* if your child nodes are inline-blocked, then you don't have to set it */
	overflow: auto;
}

.container .headActive {
	/* float your elements or inline-block them to display side by side */
	float: left; /* these are height and width dimensions of your header */
	height: 10em;
	width: 1.5em;
	/* set to hidden so when there's too much vertical text it will be clipped. */
	overflow: hidden;
	/* these are not relevant and are here to better see the elements */
	background: #ffe1dc;
	color: #be0000;
	margin-right: 1px;
	font-family: segoe ui;
	font-weight: normal;
}

.container .headActive .vertActive {
	/* line height should be equal to header width so text will be middle aligned */
	line-height: 1.5em;
	/* setting background may yield better results in IE text clear type rendering */
	background: #ffe1dc;
	color: #be0000;
	display: block; /* this will prevent it from wrapping too much text */
	white-space: nowrap; /* so it stays off the edge */
	padding-left: 3px;
	font-family: segoe ui;
	font-weight: normal; /* CSS3 specific totation code */
	/* translate should have the same negative dimension as head height */
	transform: rotate(-270deg) translate(1em, 0);
	transform-origin: -5px 30px;
	-moz-transform: rotate(-270deg) translate(1em, 0);
	-moz-transform-origin: -5px 30px;
	-webkit-transform: rotate(-270deg) translate(1em, 0);
	-webkit-transform-origin: -5px 30px;
	-ms-transform-origin: none;
	-ms-transform: none;
	-ms-writing-mode: tb-rl;
	*writing-mode: tb-rl;
}

.container .headClosed {
	/* float your elements or inline-block them to display side by side */
	float: left; /* these are height and width dimensions of your header */
	height: 10em;
	width: 1.5em;
	/* set to hidden so when there's too much vertical text it will be clipped. */
	overflow: hidden;
	/* these are not relevant and are here to better see the elements */
	background: #e1f0be;
	color: #415a05;
	margin-right: 1px;
	font-family: segoe ui;
	font-weight: normal;
}

.container .headClosed .vertClosed {
	/* line height should be equal to header width so text will be middle aligned */
	line-height: 1.5em;
	/* setting background may yield better results in IE text clear type rendering */
	background: #ffe1dc;
	color: #415a05;
	display: block; /* this will prevent it from wrapping too much text */
	white-space: nowrap; /* so it stays off the edge */
	padding-left: 3px;
	font-family: segoe ui;
	font-weight: normal; /* CSS3 specific totation code */
	/* translate should have the same negative dimension as head height */
	transform: rotate(-270deg) translate(1em, 0);
	transform-origin: -5px 30px;
	-moz-transform: rotate(-270deg) translate(1em, 0);
	-moz-transform-origin: -5px 30px;
	-webkit-transform: rotate(-270deg) translate(1em, 0);
	-webkit-transform-origin: -5px 30px;
	-ms-transform-origin: none;
	-ms-transform: none;
	-ms-writing-mode: tb-rl;
	*writing-mode: tb-rl;
}

.infoValueNote {
	font-family: Arial;
	font-size: 12px;
	font-weight: 500;
	color: grey;
	padding-right: 15px;
	font-style: normal;
}

.grey-bg {background: #f0f2f4;}
.subHeading {color: #333; font-size: 13px;}
.italic-font {font-style: italic;}
.fontWeight {font-weight: normal;}
</style>

</head>
<body style="font-family: Arial, arial, verdana;">
	<table class="box" align="center" border="0px" cellpadding="0"
		cellspacing="0" width="100%">
		<thead>
			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="100%">
										<tbody>
											<tr height="10">
												<td></td>
											</tr>
											<tr>
												<td width="30%">
													<table>
														<tbody>
															<tr>
																<td align="left" class="reportHead"
																	style="text-align: left">
																</td>
															</tr>
															<tr>
																<td
																	style="font-size: 9px; text-align: left; color: grey;">
																</td>
															</tr>

														</tbody>
													</table>
												</td>

												<td rowspan="1" align="center" valign="top" width="40%">
													<table width="100%">
														<tbody>
															<tr>
																<td class="dataValue"
																	style="font-size: 35px; text-align: center;">
																	CIBIL</td>
															</tr>
															<tr>
																<td class="dataHeader"
																	style="color: #333; text-align: center; font-size: 14px;">
																	CREDIT INFORMATION REPORT</td>
															</tr>

														</tbody>
													</table>
												</td>
												<td width="30%">
													<table width="100%">
														<tbody>
														<tr><td></td></tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td height="10">
									<hr size="1" style="color: #C8C8C8;"/>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</thead>
		<tbody>
			<!-- Fateh Code Starts Here -->
			<tr>
				<td>
					<table align="left" border="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td>
									<table align="left" border="0"
										cellspacing="0" width="100%">
										<tbody>
											<tr>
												<td style=" border-bottom: 2px solid #1675a5; color: #1675a5;">
													<table align="left"
														style="border-radius: 0px;" border="0"
														width="100%;">
														<tbody>
															<tr height="20">
																<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Consumer
																	CIR</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" cellpadding="0"
										cellspacing="0" width="100%">
										<tbody>
											<tr>
												<td>
													<table align="center" border="0" width="100%">
														<tbody>
															<tr>
																<td>
																	<table border="0" width="100%">
																		<tbody>
																			<%
																				try {
																					String payload = "" + request.getAttribute("payload");
																					JSONObject payloadJson = new JSONObject(payload);
																			%>


																			<tr style="background: #e5e5e5;">
																				<td align="left" width="144 px"
																					style="font-size: 12px; padding-left: 10px; padding-bottom: 5px;padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">Consumer
																						:</span> 
																							<%
																							 	try {
																							 %> <%=payloadJson.get("name1")+" "+payloadJson.get("name2")+" "+payloadJson.get("name3")+" "+payloadJson.get("name4")+" "+payloadJson.get("name5")%> <%
																							 	} catch (Exception ec) {
																							 		}
																							 %>
																				</td>
																				<td align="left" width="144 px"
																					style="font-size: 12px; padding-left: 10px;padding-bottom: 5px;padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">Date
																						:</span> <%
																							 	try {
																							 %> <%=payloadJson.get("processedDate")%> <%
																							 	} catch (Exception ec) {
																							 		}
																							 %>
																				</td>
																			</tr>
																			<tr style="background: #e5e5e5;">
																				<td align="left" width="171px"
																					style="font-size: 12px; padding-left: 10px; padding-bottom: 5px;padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">Member
																						Id :</span> <%
																						 	try {
																						 %> <%=payloadJson.get("memberId")%> <%
																						 	} catch (Exception ec) {
																						 		}
																						 %>
																				</td>
																				<td align="left" width="171px"
																					style="font-size: 12px; padding-left: 10px; padding-bottom: 5px;padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">Time
																						: </span> <%
																							 	try {
																							 %> <%=payloadJson.get("timeProceesed")%> <%
																							 	} catch (Exception ec) {
																							 		}
																							 %>
																				</td>
																			</tr>
																			<tr style="background: #e5e5e5;">
																				<td align="left" width="114px"
																					style="font-size: 12px; padding-left: 10px; padding-bottom: 5px;padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">Member
																						Reference Number :</span> <%
																				 	try {
																				 %> <%=payloadJson.get("memberRefNo")%> <%
																						} catch (Exception ec) {
																							}
																					%>
																				</td>
																				<td align="left" width="114px"
																					style="font-size: 12px; padding-left: 10px; padding-bottom: 5px;padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">Control
																						Number :</span> <%
																							 	try {
																							 %> <%=payloadJson.get("enquiryControlNumber")%> <%
																						} catch (Exception ec) {
																							}
																					%>
																				</td>
																			</tr>

																			<%
																				} catch (Exception e) {
																				}
																			%>

																			<tr>
																				<td height="20px"></td>
																			</tr>
																		</tbody>
																	</table>

																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<!-- Fateh Code Ends Here -->
			<tr>
				<td>
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>

							<tr>
								<td>
									<table
										style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
										align="left" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td height="15px"></td>
											</tr>
											<tr>
												<td class="mainHeader" style="padding-left: 10px;">Consumer
													Score Details</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td>
													<table align="center" border="0" width="100%" cellspacing="0">
														<tbody>
															<tr style="height: 20px;">
																<td class="dataHeader" width="25%"
																	style="padding-left: 10px;">Score Name</td>
																<td class="dataHeader" width="10%">Score</td>
																<td class="dataHeader" width="10%">Score Date</td>
																<td class="dataHeader" width="55%">Scoring Factors</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<%
								try {
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadJson = new JSONObject(payload);
									JSONArray scoreDetails = payloadJson.getJSONArray("scoreDetails");
									for (int i = 0; i < scoreDetails.length(); i++) {
										JSONObject score = scoreDetails.getJSONObject(i);
							%>
							<tr>
								<td>
									<table align="left" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="background: #e5e5e5;">
												<td width="25%" style="text-align: left; padding-left: 10px;" align="left" class="dataValue">
													<%=score.getString("scorecardName")%>
												</td>
												<td width="10%" style="text-align: left; font-size: 40px; font-weight: 500;" class="dataValue">
													<%=score.getString("score")%>
												</td>
												<td width="10%" style="text-align: left;" class="dataValue">
													<%=score.getString("scoredate")%>
												</td>
												<td class="dataValue" width="55%">
													<table align="left" border="0" width="100%" cellspacing="0">
														<tbody>
															<%
																try
																{
																	JSONArray scoringFactors = score.getJSONArray("scoringFactors");
																	if(scoringFactors!=null && scoringFactors.length()>0)
																	{
																		for (int j = 0; j < scoringFactors.length(); j++)
																		{
																			JSONObject factors = scoringFactors.getJSONObject(j);
																%>
															<tr>
																<td class="dataValue">
																	<%try {%> <span class="fontWeight"><%=j+1%>: </span> <%=factors.get("factor")%>
																	<%} catch (Exception ec) {}%>
																</td>
															</tr>
															<%} } } catch (Exception ec) {} %>
															<tr>
																<td ></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
								<%		
									}} catch (Exception ec) {
									}
								%>
								
								<%
								try {
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadJson = new JSONObject(payload);
									JSONArray scoreDetails = payloadJson.getJSONArray("scoreDetails");
									for (int i = 0; i < scoreDetails.length(); i++)
									{
										JSONObject score = scoreDetails.getJSONObject(i);
							%>
							<tr>
								<td>
									<table
										style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
										align="left" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td height="15px"></td>
											</tr>
											<tr>
												<td class="mainHeader" style="padding-left: 10px;">Possible Range For <%=score.get("scorecardName")%></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>	
								<tr>
								<td>
									<table align="left" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="background: #e5e5e5;">
												<td class="dataValue" width="55%">
													<table width="100%" cellspacing="0">
														<tbody>
															<%
																String scoreName = score.get("scorecardName")+"";
																if(scoreName!=null && scoreName.equalsIgnoreCase("CIBIL TransUnion Score Version 1.0"))
																{
																%>
															<tr>
																<td height="1px"></td>
															</tr>
															<tr>
																<td class="dataValue" style="padding-left: 10px;">
																	<span class="fontWeight">Consumers with more than 6 months credit history* : </span>300 (high risk) to 900 (low risk)
																</td>
															</tr>
															<tr>
																<td class="dataValue" style="padding-left: 10px;">
																	<span class="fontWeight">Consumers not in CIBIL database or with insufficient information for scoring* : </span>-1
																</td>
															</tr>
															<tr>
																<td class="dataValue" style="padding-left: 10px;">
																	<span class="fontWeight">* At least one tradeline with information updated in last 24 months is required.In case of error in scoring a value of '0' is returned.</span>
																</td>
															</tr>
															<%} else if(scoreName!=null && scoreName.equalsIgnoreCase("CIBIL TransUnion Score Version 2.0")){ %>
															<tr>
																<td height="1px"></td>
															</tr>
															<tr>
																<td class="dataValue" style="padding-left: 10px;">
																	<span class="fontWeight">Consumers with more than 6 months credit history* : </span>300 (high risk) to 900 (low risk)
																</td>
															</tr>
															<tr>
																<td class="dataValue" style="padding-left: 10px;">
																	<span class="fontWeight">Consumers having less than 6 months credit history* : </span>1 (high risk) to 5 (low risk)
																</td>
															</tr>
															<tr>
																<td class="dataValue" style="padding-left: 10px;">
																	<span class="fontWeight">Consumers not in CIBIL database or with insufficient information for scoring* : </span>-1
																</td>
															</tr>
															<tr>
																<td class="dataValue" style="padding-left: 10px;">
																	<span class="fontWeight">* At least one tradeline with information updated in last 24 months is required.In case of error in scoring a value of '0' is returned.</span>
																</td>
															</tr>
															<%}%>
															<tr>
																<td height="1px"></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
								<%	break;	
									}} catch (Exception ec) {
									}
								%>
						</tbody>
					</table>
				</td>
			</tr>
			<tr><td height="10px"></td></tr>
			<tr>
				<td>
					<table style="border-radius: 0px; border-bottom: 2px solid #1675a5;" align="left"
						width="100%" cellspacing="0">
						<tbody>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Personal
									Information</td>
							</tr>
						</tbody>
					</table>
					<table border="0" width="100%" align="center">
						<tbody>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td align="left"
									style="text-align: left; padding-left: 10px;"
									width="200 px" class="dataHeader">Name</td>
								<td width="171 px"
									style="text-align: left; "
									class="dataHeader">Dob</td>
								<td width="114 px" class="dataHeader"
									>Gender</td>
								<td width="200 px" class="dataHeader"></td>
							</tr>
							<tr>
								<td height="1px"></td>
							</tr>

							<%
								try {
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadJson = new JSONObject(payload);
									String dateofBirth = payloadJson.get("dateofBirth") + "";
									String gendercode = new JSONObject(payload).get("gender") + "";
									String gender = "";
									if ("1".equals(gendercode)) {
										gender = "Female";
									} else if ("2".equals(gendercode)) {
										gender = "Male";
									} else {
										gender = "Transgender";
									}
							%>
							<tr style="background: #e5e5e5">
								<td
									style="text-align: left; padding-left: 10px;"
									align="left" width="200 px" class="dataValue">
									<%=payloadJson.get("name1")+" "+payloadJson.get("name2")+" "+payloadJson.get("name3")+" "+payloadJson.get("name4")+" "+payloadJson.get("name5")%></td>
								<td width="171 px"
									style="text-align: left; "
									class="dataValue"><%=dateofBirth%></td>
								<td width="114 px" class="dataValue"
									style=""><%=gender%></td>
								<td width="200 px" class="dataValue"></td>
							</tr>
							<%
								} catch (Exception ec) {
								}
							%>
							
						</tbody>
					</table> 
					<table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="center"
						width="100%" cellspacing="0">
						<tbody>
							<tr>
								<td height="15px"></td>
							</tr>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Identification(s)
								</td>
							</tr>
						</tbody>
					</table>
					
					<!--Identefication Segment Added 10-Aug-2017 starts-->
					<table border="0" width="100%" align="center">
						<tbody>
							<tr>
								<td align="left" width="200 px" class="dataHeader"
									style="padding-left: 10px;">Identification
									Type</td>
								<td width="171 px" class="dataHeader"
									>Number</td>
								<td width="114 px" class="dataHeader"
									>Issue Date</td>
								<td width="200px" class="dataHeader"
									>Expiration Date</td>
							</tr>
							<tr>
								<td height="1px" colspan="4"></td>
							</tr>

							<%
								try {
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadJson = new JSONObject(payload);
									JSONArray iddetails = payloadJson.getJSONArray("iddetails");
									for (int i = 0; i < iddetails.length(); i++) {
										JSONObject iddetail = iddetails.getJSONObject(i);
							%>
							<%
							if(i%2==0)
							{
							%>
							<tr style="background: #e5e5e5">
							<%
							}
							else
							{
							%>
							<tr>
							<%
							} 
							%>
								<td align="left" width="200 px" class="dataValue"
									style="padding: 5px 10px;">
									<%
										try {
									%><%=iddetail.get("idType")%> <%
										} catch (Exception ec) {
												}
									%>
								</td>
								<td style="padding: 5px 0; "
									width="171 px" class="dataValue"
									>
									<%try {%> <%=iddetail.get("idNumber")%> <%} catch (Exception ec) {}%>
								</td>
								<td style="padding: 5px 10px; "
									width="114 px" class="dataValue"
									>
									<%try {%> <%=iddetail.get("issueDate")%> <%} catch (Exception ec) {}%>
								</td>
								<td style="padding: 5px 10px; "
									width="200px" class="dataValue"
									>
									<%try {%> <%=iddetail.get("expirationDate")%> <%} catch (Exception ec) {}%>
								</td>
							</tr>
							<%
								}
								} catch (Exception ec) {
								}
							%>

						</tbody>
					</table> <!--Identefication Segment Added 10-Aug-2017 Ends--> 
					<!--TELEPHONE Segment Added 16-Aug-2017 starts-->
					<table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="center"
						width="100%" cellspacing="0">
						<tbody>
							<tr>
								<td height="10px"></td>
							</tr>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Telephone(s)
								</td>
							</tr>
						</tbody>
					</table>
					<table border="0" width="100%" align="center">
						<tbody>
							<tr>
								<td align="left" width="200 px" class="dataHeader"
									style="padding-left: 10px;">Telephone
									Type</td>
								<td width="171 px" class="dataHeader"
									>Telephone Number</td>
								<td width="114 px" class="dataHeader"
									>Telephone Extension</td>
									<td width="200 px"></td>
							</tr>
							<tr>
								<td height="1px"></td>
							</tr>

							<%
								try
								{
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadJson = new JSONObject(payload);
									JSONArray telephones = payloadJson.getJSONArray("telephones");
									for (int i = 0; i < telephones.length(); i++)
									{
										JSONObject telephone = telephones.getJSONObject(i);
							%>
							<%
							if(i%2==0)
							{
							%>
							<tr style="background: #e5e5e5">
							<%
							}
							else
							{
							%>
							<tr>
							<%
							} 
							%>
								<td align="left" width="200 px" class="dataValue"
									style=" padding-left: 10px;">
									<%try {%> <%=telephone.get("telephoneType")%> <%} catch (Exception ec) {}%>
								</td>
								<td style="padding: 5px 0; "
									width="171 px" class="dataValue"
									>
									<%try {%> <%=telephone.get("telephoneNumber")%> <%} catch (Exception ec) {}%>
								</td>
								<td style="padding: 5px 10px; "
									width="114 px" class="dataValue"
									>
									<%try {%> <%=telephone.get("telephoneExtn")%> <%} catch (Exception ec) {}%>
								</td>
								<td width="200 px"></td> 
							</tr>
							<%}
								} catch (Exception ec) {}
							%>

						</tbody>
					</table> <!--TELEPHONE Segment Added 16-Aug-2017 Ends--> <!--Email Contact Segment Added 10-Aug-2017 starts-->
					
					<table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="center"
						width="100%" cellspacing="0">
						<tbody>
							<tr><td height="10px"></td></tr>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Email Contact(s)
								</td>
							</tr>
						</tbody>
					</table>
					<table border="0" width="100%" align="center">
						<tbody>
							<tr>
								<td align="left" width="200 px" class="dataHeader"
									style=" padding-left: 10px;">Email
									Id</td>
								<td width="171 px" class="dataHeader"
									></td>
								<td width="114 px" class="dataHeader"
									></td>
								<td width="200px" class="dataHeader"
									></td>
							</tr>
						
							<%
								try {
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadJson = new JSONObject(payload);
									JSONArray emailcontacts = payloadJson
											.getJSONArray("emailcontact");
									for (int i = 0; i < emailcontacts.length(); i++) {
										JSONObject emailcontact = emailcontacts.getJSONObject(i);
							%>
							<%
							if(i%2==0)
							{
							%>
							<tr style="background: #e5e5e5">
							<%
							}
							else
							{
							%>
							<tr>
							<%
							} 
							%>
								<td align="left" width="200 px" class="dataValue"
									style="padding-left: 10px;">
									<%
										try {
									%> <%=emailcontact.get("emailId")%> 
									<%} catch (Exception ec) {}%>
								</td>
								<td width="171 px" class="dataValue"
									></td>
								<td width="114 px" class="dataValue"
									></td>
								<td width="200px" class="dataValue"
									></td>
							</tr>
							<%
								}
								} catch (Exception ec) {
								}
							%>
						
						</tbody>
					</table> <!--Email Contact Segment Added 10-Aug-2017 Ends-->
				</td>
			</tr>
			<tr>
				<td>
					<table style="border-radius: 0px; border-bottom: 2px solid #1675a5;" align="left"
						width="100%" cellspacing="0">
						<tbody>
							<tr><td height="20px"></td></tr>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Address(es)
								</td>
							</tr>
						</tbody>
					</table>
					<table border="0" align="left"
						style="width: 100%;">
						<tbody>
							<tr>
								<td>
									<%
										try {
											String payload = request.getAttribute("payload") + "";
											JSONObject payloadjson = new JSONObject(payload);
											JSONArray addresses = payloadjson.getJSONArray("addresses");

											for (int i = 0; i < addresses.length(); i++) {
												JSONObject address = addresses.getJSONObject(i);
												String addressline1 = address.get("addressline1") + "";
												if ("null".equals(addressline1)) {
													addressline1 = "";
												}
												String addresscategory=""+address.get("addressCategory");
												String resedenceCode=""+address.get("resedenceCode");
												String dateReported=""+address.get("dateReported");
												if(dateReported.length()>=8)
												{
													dateReported=dateReported.substring(0,2)+"-"+dateReported.substring(2,4)+"-"+dateReported.substring(4,8);
												}
												if ("null".equals(addresscategory)) {
													addresscategory = "";
												}
												if ("01".equals(addresscategory)) {
													addresscategory = "Permanent";
												} else if ("02".equals(addresscategory)) {
													addresscategory = "Residence";
												} else if ("03".equals(addresscategory)) {
													addresscategory = "Office";
												} else if ("04".equals(addresscategory)) {
													addresscategory = "Not Categorized";
												}
									%>
									<table border="0" width="100%">
										<tbody>
										<%		
										if(i%2==0)
										{
										%>
										<tr style="background: #e5e5e5">
										<%
										}
										else
										{
										%>
										<tr>
										<%
										} 
										%>
									<td align="left" width="100%" colspan="3"
										style="text-align: left; padding-left: 10px;"
										class="dataHeader">Address : <span align="left"
										style=" padding-left: 10px;"
										class="dataValue"> <%=addressline1%></span></td>
										
								</tr>
											<%	
							if(i%2==0)
							{
							%>
							<tr style="background: #e5e5e5">
							<%
							}
							else
							{
							%>
							<tr>
							<%
							} 
							%>
								<td width="33%" style=" padding-left:10px;"
								class="dataHeader">Category :<span width="171 px" 
								class="dataValue"> <%=addresscategory%></span></td>
								
								<td width="33%" 
								class="dataHeader">Residence Code :<span width="114 px" 
										class="dataValue"> <%=resedenceCode%></span></td>
									
									<td width="33%" 
										class="dataHeader">Date Reported :<span width="100%" 
										class="dataValue"> <%=dateReported%></span> </td>
									
								</tr>
															
											

										</tbody>
									</table>
									
										<%
											}

											} catch (Exception ec) {
											}
										%>
								
								</td>
							</tr>
							<tr>
								<td height="20px"></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="border-bottom: 2px solid #1675a5; border-radius: 0px; width: 100%;" align="left">
						<tbody>
							<tr height="20">
								<td style=" padding-left:10px;" class="mainHeader">Employment
									Information</td>
							</tr>
						</tbody>
					</table>
					<table border="0" width="100%">
						<tbody>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td align="left" width="25%" class="dataHeader"
									style=" padding-left: 10px;">Account Info</td>
								<td width="25%" class="dataHeader"
									>Dates</td>
								<td width="25%" class="dataHeader"
									>Amounts</td>
								<td width="25%" class="dataHeader"
									>Status</td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<%
								try 
								{
									Map accountTypes = (Map) request.getAttribute("accountTypes");
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadjson = new JSONObject(payload);
									JSONArray employmentdetails = payloadjson.getJSONArray("employmentdetail");
									JSONObject employmentdetail = employmentdetails.getJSONObject(0);
									String accountType = employmentdetail.get("accountType")+"";
									String occupationCode = employmentdetail.get("occupationCode")+"";
									String dateReported = employmentdetail.get("dateReportedandCertified") + "";
									String income = employmentdetail.get("income") + "";
									String grosssIncomeindicator = employmentdetail.get("grosssIncomeindicator") + "";
									String monthlyIncomeIndicator = employmentdetail.get("monthlyIncomeIndicator") + "";
									String errordateCode=employmentdetail.get("errordateCode")+"";
									String errorcode=employmentdetail.get("errorcode")+"";
									String dateOfEntryforremarksCode=employmentdetail.get("dateOfEntryforremarksCode")+"";
									String cibilremarkscode=employmentdetail.get("cibilremarkscode")+"";
									String datedisputeRemarksCode=employmentdetail.get("datedisputeRemarksCode")+"";
									String remarkscode1=employmentdetail.get("remarkscode1")+"";
									String remarkscode2=employmentdetail.get("remarkscode2")+"";
							%>
							
							<tr style="background: #e5e5e5">
								<td align="left" width="144 px" class="dataValue"
									style=" padding-left: 10px;"><span class="fontWeight">Account Type  : </span><%=accountType%>
								</td>
								<td width="171 px" class="dataValue"><span class="fontWeight">Reported : </span> <%=dateReported%>
								</td>
								<td width="114 px" class="dataValue"><span class="fontWeight">Income : </span><%=income%>
								</td>
								<td width="200 px" class="dataValue"><span class="fontWeight">Error Code : </span><%=errorcode%>
								</td>
							</tr>
							
							<tr>
								<td align="left" width="144 px" class="dataValue"
									style=" padding-left: 10px;"><span class="fontWeight">Occupation : </span><%=occupationCode%>
								</td>
								<td width="171 px" class="dataValue"><span class="fontWeight">Error : </span><%=errordateCode%>
								</td>
								<td width="114 px" class="dataValue"><span class="fontWeight">Net/Gross : </span><%=grosssIncomeindicator%>
								</td>
								<td width="200 px" class="dataValue"><span class="fontWeight">Cibil Remarks Code</span> : 
									<%=cibilremarkscode%>
								</td>
							</tr>
							
							<tr style="background: #e5e5e5">
								<td align="left" width="144 px" class="dataValue"
									style=" padding-left: 10px;">
								</td>
								<td width="171 px" class="dataValue"><span class="fontWeight">Cibil Remarks : </span><%=dateOfEntryforremarksCode%>
								</td>
								<td width="114 px" class="dataValue"><span class="fontWeight">Monthly/Annual : </span> <%=monthlyIncomeIndicator%>
								</td>
								<td width="200 px" class="dataValue"><span class="fontWeight">Remark 1 : </span><%=remarkscode1%>
								</td>
							</tr>
							<tr>
								<td align="left" width="144 px" class="dataValue"
									style=" padding-left: 10px;">
								</td>
								<td width="171 px" class="dataValue"><span class="fontWeight">Error/Dispute Remarks : </span><%=datedisputeRemarksCode%>
								</td>
								<td width="114 px" class="dataValue">
								</td>
								<td width="200 px" class="dataValue"><span class="fontWeight">Remark 2 : </span> <%=remarkscode2%>
								</td>
							</tr>
							<%
								} catch (Exception ec) {
								}
							%>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			
			<tr>
				<td>
					<table style="width: 100%; border-bottom: 2px solid #1675a5;" align="left"
						style="border-radius: 0px;">
						<tbody>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Account Summary</td>
							</tr>
						</tbody>
					</table>
					
					
								<%
									try
									 {
										String payload = request.getAttribute("payload") + "";
										JSONObject payloadjson = new JSONObject(payload);
										JSONObject account = payloadjson.getJSONObject("accountManualSummary");
								%>
								<table width="100%">
								<tbody>
									<tr style="background: #e5e5e5;">
										<td class="dataHeader" width="20%" style="padding-left: 10px;">Accounts</td>
										<td class="dataHeader" width="20%">Balances</td>
										<td class="dataHeader" width="20%">Amounts</td>
										<td class="dataHeader" width="20%">Dates</td>
										<td class="dataHeader" width="20%">Counts</td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Total : </span><%try {%> <%=account.get("accountTotal")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Current : </span><%try {%> <%=account.get("currentBalance")!=null &&!account.get("currentBalance").equals("NA")?CibilUtil.commaSeprated(account.get("currentBalance").toString()):account.get("currentBalance") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">High Cr/Sanc. : </span><%try {%> <%=account.get("advanceHighCRSancAmt")!=null &&!account.get("advanceHighCRSancAmt").equals("NA")?CibilUtil.commaSeprated(account.get("advanceHighCRSancAmt").toString()):account.get("advanceHighCRSancAmt") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Recent Opened : </span> <%try {%><%=account.get("recentDate")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Suit Filed/Wilful : </span><%try {%> <%=account.get("suitfiled_wilfulCount")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Overdue : </span><%try {%> <%=account.get("accountOverdue")!=null &&!account.get("accountOverdue").equals("NA")?CibilUtil.commaSeprated(account.get("accountOverdue").toString()):account.get("accountOverdue") %> <%} catch (Exception ec) {}%> </td>
										<td class="dataValue" width="20%"><span class="fontWeight">Overdue : </span><%try {%> <%=account.get("overdueBalance")!=null &&!account.get("overdueBalance").equals("NA")?CibilUtil.commaSeprated(account.get("overdueBalance").toString()):account.get("overdueBalance") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight"></span> </td>
										<td class="dataValue" width="20%"><span class="fontWeight">Oldest Opened : </span><%try {%> <%=account.get("oldestDate")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Written-off & settled : </span><%try {%> <%=account.get("writtenOffAndSettleCount")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Zero-Balance : </span><%try {%> <%=account.get("accountZeroBalance")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight"></span></td>
										<td class="dataValue" width="20%"><span class="fontWeight"></span></td>
										<td class="dataValue" width="20%"><span class="fontWeight"></span></td>
										<td class="dataValue" width="20%"><span class="fontWeight"></span></td>
									</tr>
								</tbody>
							</table>
								<%}catch(Exception ec){} %>
		
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			
			<tr>
				<td>
					<table cellspacing="0" align="left" style="border-bottom:2px solid  #1675a5;" width="100%">
						<tbody>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Summary For All  Enquirys</td>
							</tr>
						</tbody>
					</table>
					<table border="0" width="100%">
						<tbody>
							<tr style="background: #e5e5e5;">
								<td height="20" width="30%" style=" padding-left: 10px;text-align: left;"
									class="dataHeader">Enquiry Purpose</td>
								<td width="11%" style="text-align: center;" class="dataHeader"
									align="left">Total</td>
								<td width="11%" style="text-align: center;" class="dataHeader"
									align="left">Past 30 Days</td>
								<td width="11%" style="text-align: center;" class="dataHeader"
									align="left">Past 90 Days</td>
								<td width="11%" style="text-align: center;" class="dataHeader"
									align="left">Past 12 Months</td>
								<td width="11%" style="text-align: center;" class="dataHeader"
									align="left">Past 24 Months</td>
								<td width="15%" style="text-align: center;" class="dataHeader"
									align="left">Recent</td>	
							</tr>
							<%
								try
								 {
									String payload = request.getAttribute("payload") + "";
									JSONObject payloadjson = new JSONObject(payload);
									JSONArray enquiriesArr = payloadjson.getJSONArray("enquiriesSummary");
									for(int i=0; i<enquiriesArr.length();i++)
									{
									JSONObject enquiries = enquiriesArr.getJSONObject(i);
							%>
							
							<tr style="background: #e5e5e5">
						
								<td height="20" width="30%" style="text-align: left; padding-left: 10px;" class="dataValue">
									<%try {%> <%=enquiries.get("enquiryPurpose")%> <%} catch (Exception ec) {}%>
								</td>
								<td height="20" width="11%" style="text-align: center;" class="dataValue">
									<%try {%> <%=enquiries.get("total")%>
									<%} catch (Exception ec) {}%>
								</td>
								<td height="20" style="text-align: center;" width="11%"  class="dataValue">
									<%try {%> <%=enquiries.get("past30Days")%> <%} catch (Exception ec) {}%>
								</td>
								<td height="20" style="text-align: center;" width="11%"  class="dataValue">
									<%try {%> <%=enquiries.get("past90Days")%> <%} catch (Exception ec) {}%>
								</td>
								<td height="20" width="11%" style="text-align: center;" class="dataValue">
									<%try {%> <%=enquiries.get("past12Months")%> <%} catch (Exception ec) {}%>
								</td>
								<td height="20" width="11%" style="text-align:center;" class="dataValue">
									<%try {%> <%=enquiries.get("past24Months")%> <%} catch (Exception ec) {}%>
								</td>
								<td height="20" width="15%" style="text-align:center;" class="dataValue">
									<%try {%> <%=enquiries.get("recent")%> <%} catch (Exception ec) {}%>
								</td>
							</tr>
							<%}}catch(Exception ec){} %>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			
			<tr>
				<td>
					<table width="100%" align="left" cellspacing="0" style="border-radius: 0px;border-bottom: 2px solid #1675a5;" >
						<tbody>
							<tr height="20">
								<td class="mainHeader" style="text-align:left; padding-left:10px;">Account(s)
									Detail Segment </td>
							</tr>
						</tbody>
					</table>
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
					<%
						try
						 {
							String payload = request.getAttribute("payload") + "";
							JSONObject payloadjson = new JSONObject(payload);
							JSONArray accountArray = payloadjson.getJSONArray("accountDetails");
							for (int i = 0; i < accountArray.length(); i++)
							 {
								JSONObject account = accountArray.getJSONObject(i);
					%>
					<%
							if(i%2==0)
							{
							%>
							<tr style="background: #e5e5e5;">
							<%
							}
							else
							{
							%>
							<tr>
							<%
							} 
							%>
					<td>
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td>
									<table style="border-radius: 0px;" align="left"
										border="0" width="100%">
										<tbody>
											<tr><td height="10px"></td></tr>
											<!--<tr height="20">
												<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Account
													: <%=i+1%></td>
											</tr>-->
										</tbody>
									</table>
								</td>
							</tr>
						
						<tr>
						<td colspan="3">
							<table width="100%" cellspacing="0">
								<tbody>
									<tr>
										<td class="dataHeader" width="25%" style="padding-left: 10px;">Account</td>
										<td class="dataHeader" width="25%">Dates</td>
										<td class="dataHeader" width="25%">Amounts</td>
										<td class="dataHeader" width="20%">Status</td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight">Member Name : </span><%try {%> <%=account.get("reportingMemberName")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Opened : </span><%try {%> <%=account.get("dateOpened")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Sanctioned : </span><%try {%> <%=account.get("sanctionAmount")!=null &&!account.get("sanctionAmount").equals("NA")?CibilUtil.commaSeprated(account.get("sanctionAmount").toString()):account.get("sanctionAmount") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Suit Filed/Wilful : </span><%try {%> <%=account.get("suitfilledDefault")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight">Account Number : </span><%try {%> <%=account.get("accountNumber")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Last Payment : </span><%try {%> <%=account.get("dateOfLastPayment")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Current : </span><%try {%> <%=account.get("currntBalance")!=null &&!account.get("currntBalance").equals("NA")?CibilUtil.commaSeprated(account.get("currntBalance").toString()):account.get("currntBalance") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Written-Off And Settled : </span><%try {%> <%=account.get("writtenOffStatus")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight">Type : </span><%try {%> <%=account.get("accountType")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Closed : </span><%try {%> <%=account.get("dateClose")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Overdue : </span><%try {%> <%=account.get("amountOverdue")!=null &&!account.get("amountOverdue").equals("NA")?CibilUtil.commaSeprated(account.get("amountOverdue").toString()):account.get("amountOverdue") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Collateral Value : </span><%try {%> <%=account.get("valueOfColleteral")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight">Ownership : </span><%try {%> <%=account.get("ownershipIndicator")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Reported And Cerrtified : </span><%try {%> <%=account.get("dateReportedVerified")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Credit Limit : </span><%try {%> <%=account.get("creditLimit")!=null &&!account.get("creditLimit").equals("NA")?CibilUtil.commaSeprated(account.get("creditLimit").toString()):account.get("creditLimit") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">CollateralL Type : </span><%try {%> <%=account.get("typeOfColleteral")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight">Interest Rate : </span><%try {%> <%=account.get("rateOfInterest")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Entry Error Code : </span><%try {%> <%=account.get("dateOfEntryForErrorcode")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Cash Limit : </span><%try {%> <%=account.get("cashLimit")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Error Code : </span><%try {%> <%=account.get("errorCode")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight">Repayment Tenure : </span><%try {%> <%=account.get("repaymentTenure")!=null &&!account.get("repaymentTenure").equals("NA")?CibilUtil.commaSeprated(account.get("repaymentTenure").toString()):account.get("repaymentTenure") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Entry Cibil Remarks Code : </span><%try {%> <%=account.get("dateOfEntryForCibilRemarkscode")%><%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">EMI : </span><%try {%> <%=account.get("emiAmount")!=null &&!account.get("emiAmount").equals("NA")?CibilUtil.commaSeprated(account.get("emiAmount").toString()):account.get("emiAmount") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Cibil Remarks Code : </span><%try {%> <%=account.get("cibilRemarksCode")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight">Payment Frequency : </span><%try {%> <%=account.get("paymentfrequency")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Entry Error/Dispute Remarks Code : </span><%try {%> <%=account.get("dateOfERemarksCode")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Written-Off Total : </span><%try {%> <%=account.get("writtenOffAmountTotal")!=null && !account.get("writtenOffAmountTotal").equals("NA")?CibilUtil.commaSeprated(account.get("writtenOffAmountTotal").toString()):account.get("writtenOffAmountTotal") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Dispute Remarks Code1 : </span><%try {%> <%=account.get("errordisputecode1")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight"></span></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Payment History Start : </span><%try {%> <%=account.get("paymentHstrStartDate")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Written-Off Principal : </span><%try {%> <%=account.get("writtenOffAmountPrincipal")!=null && !account.get("writtenOffAmountPrincipal").equals("NA")?CibilUtil.commaSeprated(account.get("writtenOffAmountPrincipal").toString()):account.get("writtenOffAmountPrincipal") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight">Dispute Remarks Code2 : </span><%try {%> <%=account.get("errordisputcode2")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight"></span></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Payment History End : </span><%try {%> <%=account.get("paymentHistoryEndDate")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Settlement : </span><%try {%> <%=account.get("settlementAmount")!=null && !account.get("settlementAmount").equals("NA")?CibilUtil.commaSeprated(account.get("settlementAmount").toString()):account.get("settlementAmount") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight"></span></td>
									</tr>
									<tr>
										<td class="dataValue" width="25%" style="padding-left: 10px;"><span class="fontWeight"></span></td>
										<td class="dataValue" width="25%"><span class="fontWeight"></span></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Actual Payment : </span><%try {%> <%=account.get("actualPaymentAmount")!=null && !account.get("actualPaymentAmount").equals("NA")?CibilUtil.commaSeprated(account.get("actualPaymentAmount").toString()):account.get("actualPaymentAmount") %> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="20%"><span class="fontWeight"></span></td>
									</tr>
									
									<tr><td colspan="4"><hr size="1" color="#c8c8c8"/></td></tr>
									<tr></tr>
									</tbody>
								</table>
										<table width="100%">
											<tbody>
												<tr>
													<td class="dataHeader" width="50%" style="padding-left: 10px;">Payment History 1</td>
													<td class="dataHeader" width="50%">Payment History 2</td>
												</tr>
												<tr>
													<td class="dataValue" width="50%" style="padding-left: 10px;"><%try {%> <%=account.get("paymentHistory1")%> <%} catch (Exception ec) {}%></td>
													<td class="dataValue" width="50%"><%try {%> <%=account.get("paymentHistory2")%> <%} catch (Exception ec) {}%></td>
												</tr>
												<tr><td height="5px" colspan="4"><hr size="1" color="#c8c8c8"/></td></tr>
												<tr><td height="10px"></td></tr>
											</tbody>
										</table>
									</td>
								</tr>
							
								<tr>
									<td class="dataHeader" colspan="3" style="padding-left: 10px;">Summary Of Payment History :: Days Past Due/Asset Classification (Up To 36 	Months : Left To Right)</td>
								</tr>
								<tr>
								<td colspan="3">	
									<table width="100%">
										<tbody>
											<tr><td height="10px;"></td></tr>
											<tr>
												<td  style="padding-left: 10px;"><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(0).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(0).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(1).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(1).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(2).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(2).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(3).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(3).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(4).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(4).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(5).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(5).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(6).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(6).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(7).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(7).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(8).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(8).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(9).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(9).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(10).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(10).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(11).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(11).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(12).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(12).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(13).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(13).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(14).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(14).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(15).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(15).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(16).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(16).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(17).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(17).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
											</tr>
										</tbody>
									</table>
									<table  width="100%">
										<tbody>
										<tr><td height="10px"></td></tr>
											<tr>
												<td style="padding-left: 10px;"><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(18).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(18).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(19).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(19).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(20).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(20).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(21).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(21).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(22).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(22).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(23).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(23).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(24).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(24).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(25).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(25).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(26).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(26).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(27).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(27).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(28).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(28).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(29).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(29).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(30).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(30).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(31).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(31).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(32).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(32).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(33).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(33).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(34).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(34).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
												<td><span class="dataHeader"><%try{ %><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(35).get("ndpdac") %><%}catch(Exception e){} %></span><br/><span class="dataValue"><%try {%><%=account.getJSONArray("daysPastDueAssetClassificationData").getJSONObject(35).get("ndpdacMonYear")%><%} catch (Exception ec) {}%></span></td>
											</tr>
										</tbody>
									</table>
									<% if(i==accountArray.length()-1){ %>
									<table width="100%">
										<tbody>
										<tr><td height="10px"></td></tr>
											<tr>
												<td width="33%" class="dataHeader" style="padding-left: 10px;">STD = <span class="dataValue">Standard</span></td>
												<td width="33%" class="dataHeader">SMA = <span class="dataValue">Special Mention Account</span></td>
												<td width="33%" class="dataHeader">SUB = <span class="dataValue">Substandard</span></td>
											</tr>
											<tr>
												<td width="33%" class="dataHeader" style="padding-left: 10px;">DBT = <span class="dataValue">Doubtful</span></td>
												<td width="33%" class="dataHeader">LSS = <span class="dataValue">Loss</span></td>
												<td width="33%" class="dataHeader">XXX = <span class="dataValue">Not Reported</span></td>
											</tr>
											<tr><td height="5px"></td></tr>							
										</tbody>
									</table>
									<% }%>
	
								</td>	
							</tr>
					</tbody>
					</table>
					</td>					
					</tr>
					<%}
							} catch (Exception ec) {}
					%>
					</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			<tr>
				<td>
					<table cellspacing="0" align="left" width="100%"
						style="border-radius: 0px;border-bottom: 2px solid #1675a5;">
						<tbody>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Enquiry
									Information</td>
							</tr>
						</tbody>
					</table> 
					<table cellpadding="0" cellspacing="0" align="center" border="0" width="100%">
						<tbody>
							<tr>
								<td>
									<table border="0" width="100%">
										<tbody>
											<tr><td height="10px"></td></tr>
											<tr height="20">
												<td height="20" width="25%" style="padding-left:10px;"
													class="dataHeader">Member Name</td>
												<td width="25%" class="dataHeader"
													align="left">Date Of Enquiry</td>
												<td width="30%" class="dataHeader"
													align="left">Enquiry Purpose</td>
												<td width="20%" class="dataHeader"
													style="text-align:right;">Enquiry Amount</td>
											</tr>
											<tr>
												<td height="5"></td>
											</tr>
																			
											<%
												try {
													String payload = request.getAttribute("payload") + "";
													JSONObject payloadjson = new JSONObject(payload);
													//Map accountType = (Map) request.getAttribute("enquiries");
													JSONArray enquiries = payloadjson.getJSONArray("enquiries");

													for (int i = 0; i < enquiries.length(); i++) {
														JSONObject enquiry = enquiries.getJSONObject(i);
														String memberName = ""+enquiry.get("enquiryShortName");
														String dateOfEnquiry = ""+enquiry.get("dateOfEnquiry");
														String enquiryPurpose = ""+enquiry.get("enquiryPurpose");
														String enquiryAmount =""+enquiry.get("enquiryAmount");
											%>
											<%		
										if(i%2==0)
										{
										%>
										<tr style="background: #e5e5e5">
										<%
										}
										else
										{
										%>
										<tr>
										<%
										} 
										%>
								
												<td align="left" height="20" width="25%"
													style="padding-left:10px;" class="dataValue">
													<%try {%> <%=memberName%> <%} catch (Exception ec) {}%>
												</td>
												<td align="right" height="20" width="25%"
													 class="dataValue">
													<%try {%> <%=dateOfEnquiry%>
													<%} catch (Exception ec) {}%>
												</td>
												<td align="left" height="20" width="30%"
													 class="dataValue"
													style="padding-right: 30px;">
													<%try {%> <%=enquiryPurpose%> <%} catch (Exception ec) {}%>
												</td>

												<td style="text-align:right;" class="AccValue" height="20" width="20%"
													>
													<%try {%> <%=enquiryAmount!=null &&!enquiryAmount.equalsIgnoreCase("NA")?CibilUtil.commaSeprated(enquiryAmount):enquiryAmount %> <%} catch (Exception ec) {}%>
												</td>
											</tr>
											<%}
										}catch(Exception ec){} %>

										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>

			<tr>
				<td>
					<table style="border-radius: 0px;border-bottom: 2px solid #1675a5" align="left"
					width="100%">
						<tbody>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">Consumer
									Dispute Remarks</td>
							</tr>
						</tbody>
					</table>
					<table border="0" width="100%">
						<tbody>
							<tr><td height="5px"></td></tr>
							<tr height="20">
								<td align="left" style="padding-left: 10px;"
									class="dataHeader" width="250">Dispute Remarks Date</td>
								<td align="left" 
									class="dataHeader" width="220">Dispute Remarks</td>
								<td align="left" 
									class="dataHeader" width="220"></td>
								<td align="left" 
									class="dataHeader" width="220"></td>
							</tr>
<tr>
<td height="5"></td>
</tr>

							<%try
							{ 
							String payload = request.getAttribute("payload") + "";
					        JSONObject payloadjson = new JSONObject(payload);
					        JSONObject disputes = payloadjson.getJSONObject("disputes");
					        String disputeDate = disputes.get("disputeDate")+"";
					        JSONArray disputeRemark=disputes.getJSONArray("disputeRemark");
							if(disputeRemark!=null)
							{
							for(int i=0;i<disputeRemark.length();i++)
							{
							JSONObject remark=disputeRemark.getJSONObject(i);
							%>
							<%		
							if(i%2==0)
							{
							%>
							<tr style="background: #e5e5e5">
							<%
							}
							else
							{
							%>
							<tr>
							<%
							} 
							%>
								<td align="left" style="padding-left: 10px;"
									class="dataValue1" width="250">
									<%try{ %> <%=disputeDate.substring(0, 2) + "-"+ disputeDate.substring(2, 4) + "-"+ disputeDate.substring(4, 8)%>
									<%}catch(Exception ec){} %>
								</td>
								<td align="left" 
									class="dataValue1" width="220">
									<%try{ %> <%=remark.get("remark")+"" %> <%}catch(Exception ec){} %>
								</td>
								<td align="left" 
									class="dataValue1" width="220">
									<%try{ %> <%}catch(Exception ec){} %>
								</td>
								<td align="left" 
									class="dataValue1" width="220">
									<%try{ %> <%}catch(Exception ec){} %>
								</td>
							</tr>
							<tr>
<td height="5"></td>
</tr>
							
							<%}
                              }
                              }catch(Exception ec){} %>
							<!-- <tr height="20"  bgcolor="#e5e5e5">																		<td align="left" class="dataValue1" width="250">CRIF HIGHMARK SCORE (S)</td>																		<td align="left" class="dataValue1" width="220">PERFORM-Consumer</td>																		<td align="left" class="dataValue1" width="480">Score of "0" is no hit.</td>																	</tr> -->
						</tbody>
					</table>

				</td>
			</tr>
			<tr>
				<td>
					<table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="left"
					width="100%">
						<tbody>
							<tr height="20">
								<td class="mainHeader" style="font-size: 14px; padding-left:10px;">End
									Segment</td>
							</tr>
							
						</tbody>
					</table>
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
							<tr><td height="5px"></td></tr>
							<tr height="20">
								<td align="left" style="padding-left: 10px;"
									class="dataHeader" width="250">Length Of
									Transmission</td>
								<td align="left" 
									class="dataHeader" width="220"></td>
								<td align="left" 
									class="dataHeader" width="480"></td>
							</tr>

							<%try
							{ 
							String payload = request.getAttribute("payload") + "";
					        JSONObject payloadjson = new JSONObject(payload);
							%>
							<tr height="20" style="background: #e5e5e5;">
								<td align="left" style="padding-left: 10px;"
									class="dataValue1" width="250">
									<%try{ %> <%=payloadjson.get("lenOfTransmission")+"" %> <%}catch(Exception ec){} %>
								</td>
								<td align="left" 
									class="dataValue1" width="220">
									<%try{ %> <%}catch(Exception ec){} %>
								</td>
								<td align="left" 
									class="dataValue1" width="220">
									<%try{ %> <%}catch(Exception ec){} %>
								</td>
							</tr>
							<%}catch(Exception ec){} %>
							<tr><td height="20px"></td></tr>
						</tbody>
					</table>
				</td>
			</tr>					
		</tbody>
		<tfoot>
			<tr>
				<td>
					<table summary="" align="center" border="0"	width="100%"cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table summary="" style="border-top:2px solid #1675a5;" width="100%">
										<tbody>
											<tr height="30px;" class="mainHeader"
												style="background: #f0f0f0;">
												<td
													style="text-align: center; color: #333; ">You
													Now have access to Cibil Market place</td>
											</tr>

											<tr height="30px;" class="mainHeader"
												style="background: #e5e5e5;">
												<td
													style="color: #333; text-align: center;">Now
													Apply for loan and Credit cards Offer.Now check your credit
													eligibility from participating bank and financial
													institution in just a click</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
				
			</tr>
		</tfoot>
	</table>
</body>
</html>