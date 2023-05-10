<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>Details of Product</title>
</head>
<body>
	<div id="header">
		<a href="home.jsp">Bill of Materials</a>
	</div>

	<div id="body">
		<div style="margin-top: 50px" align="center" class="sam">
			<table>
				<thead>
					<tr>
						<th>SKU</th>
						<th>Product Name</th>
						<th>Quantity</th>
						<th>Batch Code</th>
						<th>Category</th>
						<th>Price</th>
						<th>Note</th>
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


		<div style="margin-top: 50px" align="center">
			<form action="/Bom1.0/ShowProduct" method="post">
				<button type="submit" name="slink" value="ShowLink">Show
					All the linking Product</button>
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