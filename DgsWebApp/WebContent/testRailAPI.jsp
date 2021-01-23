<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test Rail Report</title>
</head>
<body>
	<div align="center">
		<form action="testrailReport" method="post">
			<table border="1">
				<tr>
					<th colspan="2" style="text-align: center"><b>TestRail Report</b></th>
				</tr>
				<tr>
					<td><b>From Date</b></td>
					<td><input type="date" name="fromDate" id="fromDate" /></td>
				</tr>
				<tr>
					<td><b>To Date</b></td>
					<td><input type="date" name="toDate" id="toDate" /></td>
				</tr>
				<tr>
					<td><b>TestRailUrl</b></td>
					<td><input name="testRailUrl" id="testRailUrl" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="submit" name="Submit" value="Submit">
						<input type="submit" name="Back" value="Back">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>