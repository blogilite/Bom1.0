<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="UTF-8">
<title>Show All BOM Data</title>
</head>
<link rel="stylesheet" href="stylehome.css">
<body>
	<div id='homeHeader'>
		<a href="home.jsp"> Welcome to BOM Project </a>
	</div>
	<div id='showBody'>
		<div>
			<table style="border: 1px solid black; margin-top: 20px;">
				<thead>
					<tr>
						<th>Part Number</th>
						<th>Part_name</th>
						<th>Quantity</th>
						<th>Vendor</th>
						<th>Note</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${table}" var="row">
						<tr>
							<c:forEach items="${row}" var="cell">
								<td>${cell}</td>
							</c:forEach>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div style="margin: 10px; display: inline-block;">
			<button name="SubBomTable">
				<a href="/BOM/ShowSubBom">Sub Bom Table</a>
			</button>
			<button id="show_page">
				<a href="/BOM/ShowBomRefer">Show Bom with Reference</a>
			</button>
			<button id="show_page">
				<a href="/BOM/ShowSubBomRefer">Show Sub Bom with Reference </a>
			</button>
		</div>
	</div>


</body>

</html>
