<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="Style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<title>Connection of Part</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<div id="header">
		<a href="home.jsp">Bill of Materials</a>
	</div>

	<div id="body">

		<div style="margin-top: 20px;" align="center">
			<form action="/Bom1.0/ShowProduct" method="post">
				<label>Product Name:</label> <select name="subPro" id="subPro">
					<c:forEach items="${prSList}" var="prnm">
						<option value="${prnm}">${prnm}</option>
					</c:forEach>
				</select> <input type="submit" name="slink" value="Find">
			</form>
		</div>

		<p style="color: black; font-size: 50px; text-align: center;">
			<c:out value="${name}">
			</c:out>
		</p>

		<div style="margin-top: 20px" align="center" class="sam">
			<table>
				<thead>
					<tr>
						<th>SKU</th>
						<th>Product Name</th>
						<th>Main Product</th>
						<th>QTY</th>
						<th>Final Product</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="row">
						<tr>
							<c:forEach items="${row}" var="col">
								<th>${col}</th>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div style="margin-top: 10px" align="center">
			<form action="/Bom1.0/ShowProduct" method="post">
				<button type="submit" name="slink" value="Show">Show
					Product Details</button>
			</form>
			<div style="margin-top: 20px;">
				<a href="home.jsp">
					<button>Home</button>
				</a><a href="/Bom1.0/AddProduct">
					<button>Add Product</button>
				</a> <a href="/Bom1.0/LinkProduct">
					<button>Link Product</button>
				</a>
			</div>
		</div>

	</div>
</body>
</html>