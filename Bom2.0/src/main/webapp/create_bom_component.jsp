<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<title>Add Component</title>
<link rel="stylesheet" href="stylehome.css">
</head>

<body>
	<div id='homeHeader'>
		<a href="home.jsp"> Welcome to BOM Project </a>
	</div>
	<div id='create_bom_body'>
		<div>
			<form action="CreateSubBom" method="post" autocomplete="off">
				<fieldset style="text-align: start; margin: 5px; padding: 5px;">
					<legend>Product Details</legend>
					<div>
						<label>Product Name:&nbsp;</label> <input class="sub_bom_pro"
							type="text" placeholder="Name" name="sub_pro_name"
							value="${prona}" required>
					</div>
					<div>
						<label>Quantity:&nbsp;</label> <input class="sub_bom_pro"
							type="number" placeholder="1" min="1" name="sub_pro_qty">
					</div>
					<div>
						<label>Unit Of Measurement :&nbsp;</label> <input
							class="sub_bom_pro" type="text" placeholder="Qty"
							name="sub_pro_measu" value="QTY">
					</div>
					<div>
						<label>Reference :&nbsp;</label> <select name="sub_pro_assemble">
							<option>
								<c:forEach items="${listData}" var="cat">
									<option value="${cat.getAssemble()}">${cat.getItem_name()}</option>
								</c:forEach>
							</option>
						</select>
					</div>
					<div>
						<label>Vendor:&nbsp;</label> <input class="sub_bom_pro"
							type="text" placeholder="Allis" name="sub_pro_vendor">
					</div>
					<div>
						<label>Notes:&nbsp;</label> <input class="sub_bom_pro" type="text"
							placeholder="Note...." name="sub_pro_note">
					</div>
					<div>
						<label>Sub-BOM:&nbsp;</label> <input class="sub_bom_pro"
							type="checkbox" name="sub_pro_sub_bom">
					</div>
					<div class="btn">
						<br> <input type="submit" name="submit" class="bom_pro_name"
							value="Create Sub Bom"> &nbsp; &nbsp; &nbsp; <input
							type="submit" name="update" class="bom_pro_name"
							value="Update Sub Bom"> &nbsp; &nbsp; &nbsp;


						<button id="createBom">
							<a href="/BOM/CreateBom"> Create BOM</a>
						</button>
					</div>
				</fieldset>
			</form>
		</div>

		<div style="color: maroon; text-align: center;">
			<c:out value="${lineData}"></c:out>
		</div>

		<div>
			<table
				style="border: 1px solid black; max-height: 300px; overflow: auto">
				<thead>
					<tr>
						<th>Part Number</th>
						<th>Part_name</th>
						<th>Quantity</th>
						<th>Unit of Measurement</th>
						<th>Reference</th>
						<th>Vendor</th>
						<th>Note</th>
						<th>It's BOM</th>
						<th>Action</th>

					</tr>
				</thead>
				<c:forEach items="${show}" var="row">
					<tr>
						<c:forEach items="${row}" var="cell">
							<td>${cell}</td>
						</c:forEach>

						<td style="display: flex;"><form action="CreateSubBom"
								method="post">
								<input type="hidden" name="sub_pro_name" value="${row.get(1)}">
								<button type="submit" name="delete" value="Delete Sub Bom">Delete</button>
								&nbsp;&nbsp; <input type="hidden" name="sub_pro_name"
									value="${row.get(1)}"> <input type="hidden"
									name="sub_pro_assemble" value="${row.get(4)}">
								<button type="submit" name="upd" value="Update">Update</button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>