<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="stylehome.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<div id='homeHeader'>
		<a href="home.jsp"> Welcome to BOM Project </a>
	</div>

	<div id='showBody'>

		<div>
			<form action="ShowSubBomRefer" method="post" autocomplete="off">
				<label>Name of part:</label> <input type="text" id="part_name" name="part-name"
					placeholder="Part_Name">
				<button type="submit" name="find">Find</button>
			</form>
		</div>

		<div>
			<table style="border: 1px solid black; margin-top: 20px;">
				<thead>
					<tr>
						<th>Part Number</th>
						<th>Part_name</th>
						<th>Reference</th>
						<th>It's BOM</th>
						<th>Connected Part</th>
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

		<br> <br>
		<div style="margin: 10px; display: inline-block;">
			<button id="show_page">
				<a href="/BOM/ShowBom">Show Bom</a>
			</button>
			<button name="SubBomTable">
				<a href="/BOM/ShowSubBom">Sub Bom Table</a>
			</button>
			<button id="show_page">
				<a href="/BOM/ShowBomRefer">Show Bom with Reference</a>
			</button>
		</div>
	</div>
</body>
</html>