<%@page import="com.bom.dao.ShowDao"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh">
<link rel="stylesheet" href="stylehome.css">
<title>Create B.O.M.</title>
</head>
<body>
	<div id='create_bom_header'>
		<a href="home.jsp"> Welcome to BOM Project </a>
	</div>

	<div class="create_bom_body" id='create_bom_body'>
		<div style="width: 100%;">
			<form action="CreateBom" method="post" class="formcreate"
				autocomplete="off">
				<fieldset style="margin: 10px;">
					<legend>Product Details</legend>
					<div>
						<label>Product Name:&nbsp;</label> <input class="bom_pro_name"
							id="name" type="text" placeholder="Name" name="pro_name"
							value="${nam}">
					</div>
					<div>
						<label>Quantity:&nbsp;</label> <input class="bom_pro_name"
							type="number" placeholder="1" min="1" name="pro_qty">
					</div>
					<div>
						<label>Vendor:&nbsp;</label> <input class="bom_pro_name"
							type="text" placeholder="Allis" name="pro_vendor">
					</div>
					<div>
						<label>Notes:&nbsp;</label> <input class="bom_pro_name"
							type="text" placeholder="Note...." name="pro_note">
					</div>
					<div class="btn">
						<br> <input type="submit" name="submit" class="bom_pro_name"
							value="Create Bom"> &nbsp; &nbsp;&nbsp; <input
							type="submit" name="update" class="bom_pro_name"
							value="Update Bom"> &nbsp; &nbsp; &nbsp;
						<button id='home_create_component'>
							<a href="/BOM/CreateSubBom">Create Components</a>
						</button>
					</div>

				</fieldset>
			</form>
		</div>

		<div style="color: maroon; text-align: center;">
			<c:out value="${lineData}"></c:out>
		</div>

		<div id="tableDiv" class="table_Div">
			<table style="border: 1px solid black; margin-top: 10px">
				<thead>
					<tr>
						<th>Part Number</th>
						<th>Part_name</th>
						<th>Quantity</th>
						<th>Vendor</th>
						<th>Note</th>
						<th>Action</th>

					</tr>
				</thead>
				<c:forEach items="${show}" var="row">
					<tr>
						<c:forEach items="${row}" var="cell">
							<td>${cell}</td>
						</c:forEach>

						<td style="display: flex;"><form action="CreateBom"
								method="post">
								<input type="hidden" name="proname" value="${row.get(1)}">
								<button type="submit" name="delete" value="DeleteBom">Delete</button>
								&nbsp;&nbsp; <input type="hidden" name="proname"
									value="${row.get(1)}">
								<button type="submit" name="upd" value="updateBom">Update</button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>


	<div id='create_bom_footer'></div>

</body>

</html>