<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Xml Report</title>
</head>
<body>
	<div align="center">
		<form method="post" action="GenerateReport">
			<table border="1">
				<tr>
					<td><b>Build Version: <input name="versionType"
							type="text"/></b></td>
				</tr>
				<tr >
					<td align="center">
						<input type="submit" name="Submit" value="Submit">
						<input type="submit" name="Back" value="Back">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>