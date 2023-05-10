<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="stylehome.css">
</head>
<body>

	<div id='homeHeader'>
		<a href="home.jsp" class="anchor"> Welcome to BOM Project </a>
	</div>
	<div id='homeBody'>
		<button>
			<a href="/BOM/CreateBom" style="color: blue">Create Bom</a>
		</button>
		<button>
			<a href="/BOM/CreateSubBom" style="color: blue">Create Sub Bom</a>
		</button>
		<button>
			<a href="/BOM/ShowBom" style="color: blue">Show Table</a>
		</button>
	</div>
</body>
</html>