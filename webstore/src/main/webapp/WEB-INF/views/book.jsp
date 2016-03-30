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
				<h1>${book.title}</h1>
				<p>${book.authors}</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="component">
				<h3>Status:</h3>
				${book.status}
			</div>
			<div class="component">
				<h3>Description:</h3>
			</div>
		</div>
	</section>
	<section class="container">
		<a href="/webstore/books" class="btn btn-default"> <span
			class="glyphicon glyphicon-chevron-left" /></span> Back
		</a>
	</section>
</body>
</html>
