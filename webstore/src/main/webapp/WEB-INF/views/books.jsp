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
				<h1>${greeting}</h1>
				<p>${info}</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<td align="center">Title</td>
						<td align="center">Author</td>
						<td align="center">Status</td>
						<td align="center">Operations</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bookList}" var="book">
						<tr>
							<td align="center">${book.title}</td>
							<td align="center">${book.authors}</td>
							<td align="center">${book.status}</td>
							<td align="center"><a
								href="/webstore/books/book?id=${book.id}"
								class="btn btn-default"> <span
									class="glyphicon glyphicon-zoom-in" /></span>
							</a> <a href="/webstore/books/remove?id=${book.id}"
								class="btn btn-default"> <span
									class="glyphicon glyphicon-remove" /></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<section class="container">
		<a href="/webstore" class="btn btn-default"> <span
			class="glyphicon glyphicon-chevron-left" /></span> Back
		</a> <a href="/webstore/books/search" class="btn btn-default"> <span
			class="glyphicon glyphicon-filter" /></span> Search
		</a>
	</section>
</body>
</html>
