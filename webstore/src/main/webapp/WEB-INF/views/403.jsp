<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<title>List of books</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Nope.</h1>
				<p>Sorry, puny ${userName}, you are unauthorized to see content of this page.
					Shame on you.</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<img src="https://pbs.twimg.com/media/CLb6KlyWoAAK2cb.jpg"
				alt="Unauthorized" style="width: 600px; height: 714px;">
		</div>
	</section>
	<section class="container">
		<a href="/webstore/" class="btn btn-default"> <span
			class="glyphicon glyphicon-chevron-left" /></span> Back
		</a>
	</section>
</body>
</html>
