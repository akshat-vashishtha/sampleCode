<html>
<head>
<title>Cibil finance service</title>
<style type="text/css">
table {
	padding-left: 60px;
}

.button_style1 {
	border: 1px solid #8ed058;
	-webkit-box-shadow: #474747 0px 0px 5px;
	-moz-box-shadow: #474747 0px 0px 5px;
	box-shadow: #474747 0px 0px 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	font-size: 12px;
	font-family: arial, helvetica, sans-serif;
	padding: 7px 10px 7px 10px;
	text-decoration: none;
	display: inline-block;
	text-shadow: 0px 1px 0 rgba(255, 255, 255, 0.4);
	font-weight: bold;
	color: #4D7D32;
	background-color: #A9DB80;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#A9DB80),
		to(#96C56F));
	background-image: -webkit-linear-gradient(top, #A9DB80, #96C56F);
	background-image: -moz-linear-gradient(top, #A9DB80, #96C56F);
	background-image: -ms-linear-gradient(top, #A9DB80, #96C56F);
	background-image: -o-linear-gradient(top, #A9DB80, #96C56F);
	background-image: linear-gradient(to bottom, #A9DB80, #96C56F);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,
		startColorstr=#A9DB80, endColorstr=#96C56F);
}

.button_style1:hover {
	border: 1px solid #8ed058;
	background-color: #8ED058;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#8ED058),
		to(#7BB64B));
	background-image: -webkit-linear-gradient(top, #8ED058, #7BB64B);
	background-image: -moz-linear-gradient(top, #8ED058, #7BB64B);
	background-image: -ms-linear-gradient(top, #8ED058, #7BB64B);
	background-image: -o-linear-gradient(top, #8ED058, #7BB64B);
	background-image: linear-gradient(to bottom, #8ED058, #7BB64B);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,
		startColorstr=#8ED058, endColorstr=#7BB64B);
}

input[type=text] {
	border: 1px dotted #999;
	border-radius: 0;
	-webkit-appearance: none;
}

textarea {
	font-family: inherit;
	font-size: 100%;
}
</style>
<script>
	function onEnterRequestJson() {
		if (document.getElementById("requestJson").value == '') {
			alert("Request JSON can not be blank");
			return false;
		} else {
			document.cibilOutPut.submit();
		}

	}
	function onEnterRequestJsonforhtmlfile() {
		alert("This Service is Under Development");
	}
</script>
</head>
<body style="background-color: #EEEDE7">
	<div style="background-color: #D4EFDF; text-align: center;">
		<h1>Cibil finance Service</h1>
	</div>
	<form action="downloadRequestJSONForCibil" method="post">

		<table>
			<tr height="40px">
				<td><input type="submit" class="button_style1"
					value="Download Request json for Cibil Consumer Service"">
				</td>
		</table>
	</form>

	<form name="cibilOutPut" action="getCibilConsumerReport" method="post">
		<table>
			<tbody>
				<tr height="40px">
					<td colspan="3"><b>Please enter the request Json</b></td>
				</tr>
				<tr height="40px">
					<td colspan="3"><textarea rows="4" cols="50"
							name="requestJson" id="requestJson"></textarea></td>
				</tr>
				<tr height="50px">
					<td><input type="button" class="button_style1" value="Submit"
						onclick="onEnterRequestJson()"></td>
				</tr>
			</tbody>
		</table>
	</form>

</body>
</html>