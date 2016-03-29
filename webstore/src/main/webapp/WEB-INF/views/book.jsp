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
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./">Home</a> <a class="navbar-brand">Books</a>
				<a class="navbar-brand" href="add/">Add</a>
			</div>
		</div>
	</nav>
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
