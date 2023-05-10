<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add product</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="Style.css">

<body>
	<div id="header">
		<a href="home.jsp">Bill of Materials</a>
	</div>

	<div id="body">
		<div>
			<form action="/Bom1.0/AddProduct" class="prodData" method="post"
				autocomplete="off">
				<fieldset>
					<legend>Product Details</legend>
					<div class="prodData">
						<label>Product Name:</label> <input type="text" name="proName"
							placeholder="XYZ" value="${name}"> <input type="hidden"
							name="sku" value="${sku}">
					</div>
					<div class="prodData">
						<label>Product Batch Code:</label> <input type="text"
							name="proBatch" placeholder="123" min="1" value="${batch}">
					</div>
					<div class="prodData">
						<label>Product Category:</label> <input type="text" name="proCat"
							placeholder="XYZ" value="${cat}">
					</div>
					<div class="prodData">
						<label>Product Quantity:</label> <input type="number"
							name="proQty" placeholder="123" value="${qty}">
					</div>
					<div class="prodData" id="priceTag">
						<label>Product Price:</label> <input type="number" id="price"
							name="proPrice" placeholder="per piece" min="1" value="${price}">
					</div>
					<div class="prodData">
						<label>Notes:</label> <input type="text" name="proNot"
							placeholder="XYZ" value="${note}">
					</div>

					<div class="prodData">
						<label>Final Product:</label> <input type="checkbox"
							name="proFinal" ${sub}>
					</div>
					<div class="prodData">
						<input type="submit" name="putData" value="ADD" ${dis_add}>
						<input type="submit" name="putData" value="Update Data" ${dis_upd}>
						<!-- <input type="submit" name="putData" value="Verify"> -->
					</div>
				</fieldset>
			</form>
		</div>

		<p style="color: red; text-align: center;">
			<c:out value="${red}"></c:out>
		</p>

		<p style="color: green; size: bold; text-align: center;">
			<c:out value="${green}"></c:out>
		</p>

		<div id="buttons" style="margin-top: 20px" align="center">
			<a href="home.jsp">
				<button>Home</button>
			</a> <a href="/Bom1.0/LinkProduct">
				<button>Link Product</button>
			</a> <a href="/Bom1.0/ShowProduct">
				<button>Show Product</button>
			</a>
		</div>

		<div class="sam" style="margin-top: 20px" align="center">
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
						<th>Finish Product</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${table}" var="row">
						<tr>
							<c:forEach items="${row}" var="col">
								<th>${col}</th>
							</c:forEach>
							<td>
								<form action="/Bom1.0/AddProduct" class="prodData" method="post">
									<input type="hidden" name="proName" value="${row.get(1)}">
									<button type="submit" name="putData" value="Delete">Delete</button>
									&nbsp;&nbsp;<input type="hidden" name="proName"
										value="${row.get(1)}">
									<button type="submit" name="putData" value="Update">Update</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>