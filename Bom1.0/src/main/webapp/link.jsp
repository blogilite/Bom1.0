<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>Link Product</title>
<link rel="stylesheet" href="Style.css">
</head>
<script defer type="text/javascript">
			var myData = [
	   			<%List<List<String>> ls = (List<List<String>>) request.getAttribute("prNList");%>
				<%for (List<String> row : ls) {%>
				[
	        			<%for (String item : row) {%>
					'<%=item%>',
	        			<%}%>
	      			],
	    		<%}%>
	  		];
			var arr = [
				<%List<List<String>> dt = (List<List<String>>) request.getAttribute("list");%>
				<%for (List<String> s : dt) {%>
				'<%=s.get(1)%>',
				<%}%>
			];
			function check() {
				var data = document.getElementById("subPro").value;
				for (i = 0; i < arr.length; i++) {
					if (arr[i] == data) {
						if (!confirm("Do you sure to Update Data")) {
							text = "Please, Change the Sub-Bom Product Name";
							document.getElementById("my").innerHTML = text;
							document.getElementById("add").disabled = true;
							change();
							return;
						} else {
							document.getElementById("add").disabled = false;
							document.getElementById("add").value = "Update";
						}

					} else {
						document.getElementById("my").innerHTML = null;
						document.getElementById("add").disabled = false;
					}
				}

			}
			console.log(arr)
			function change() {
				
				var sel = null;
				sel = document.getElementById("subPro");
				var dt = null;
				var data = document.getElementById("name").value;
				for (i = 0; i < myData.length; i++) {
					if (myData[i][0] == data) {
						dt = myData[i][1] + " pcs";
						document.getElementById("myText").innerHTML = dt;
					}
				}
				for (var i = 0; i < sel.options.length; i) {
					sel.remove(i);
				}
				for (i = 0; i < myData.length; i++) {
					var opt = document.createElement("option");
					if (myData[i][0] != data) {
						opt.value = myData[i][0];
						opt.text = myData[i][0];
						sel.appendChild(opt);
					}
				}
				document.getElementById("add").value = "Link";
			}
		</script>

<body>
	<div id="header">
		<a href="home.jsp">Bill of Materials</a>
	</div>

	<div id="body">
		<div>
			<form action="/Bom1.0/LinkProduct" method="post" autocomplete="off">
				<fieldset>
					<legend>Product Linking</legend>
					<div class="fr">
						<label>Bom Product :</label> <select name="proName" id="name"
							onclick="change()">
							<c:forEach items="${prNList}" var="row">
								<option value="${row.get(0)}">${row.get(0)}</option>
							</c:forEach>
						</select>&nbsp; <span id="myText"></span>

					</div>
					<div class="fr">
						<label>Sub-Bom Product Name:</label><select name="subPro"
							id="subPro" onclick="check()">

						</select>&nbsp; <span id="my"></span>
					</div>
					<div class="fr">
						<label>Add Qty :</label><input type="number" name="qty" min="1"
							placeholder="123">
					</div>
					<div class="fro">
						<input type="submit" id="add" name="addPro" value="Link">
					</div>
				</fieldset>
			</form>
		</div>
		<div style="margin-top: 10px" align="center">
			<p>
				<c:out value="${message }"></c:out>
			</p>
		</div>
		<div id="buttons" style="margin-top: 10px" align="center">
			<a href="home.jsp">
				<button>Home</button>
			</a> <a href="/Bom1.0/AddProduct">
				<button>Add Product</button>
			</a><a href="/Bom1.0/ShowProduct">
				<button>Show Product</button>
			</a>
		</div>
		<div style="margin-top: 10px" align="center" class="sam">
			<table>
				<thead>
					<tr>
						<th>SKU</th>
						<th>Product Name</th>
						<th>Main Product</th>
						<th>Quantity</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="row">
						<tr>
							<c:forEach items="${row}" var="col">
								<th>${col}</th>
							</c:forEach>
							<td>
								<form action="/Bom1.0/LinkProduct" class="prodData"
									method="post">
									<input type="hidden" name="proName" value="${row.get(1)}">
									<button type="submit" name="addPro" value="Delete">Unlink</button>
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