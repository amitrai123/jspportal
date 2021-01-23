<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Files</title>
</head>
<body>
	<div align="center">
		<form action="UploadServlet" method="post"
			enctype="multipart/form-data">
			<table border="1">
				<tr>
					<th colspan="2" style="text-align: center"><b>Multiple
							Files Upload</b></th>
				</tr>
				<tr>
					<td><b>Specify Files</b></td>
					<td><input name="file" type="file" id="file"
						multiple="multiple"></td>
				</tr>
				<tr>
					<td><b>Build Version</b></td>
					<td><input name="versionType" type="text" id="text3" /></td>
				</tr>
				<tr>
					<td><b>GameName</b></td>
					<td><select name="gameName">
							<option>--Select--</option>
							<option>50Dragons</option>
							<option>5Frogs</option>
							<option>DiamondStorm</option>
							<option>FooShangFoo</option>
							<option>GoldenPeach</option>
							<option>HeavenAndEarth</option>
							<option>WalkingDead</option>
							<option>HappyLantern</option>
					</select></td>
				</tr>
				<tr>
					<td><b>Result Type</b></td>
					<td><select name="resultType">
							<option>--Select--</option>
							<option>API</option>
							<option>Recovery</option>
					</select></td>
				</tr>

				<tr>
					<th colspan="2" style="text-align: center"><input
						type="submit" name="Submit" value="Submit Files"></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>