<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<title>Search</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Search</h1>
				<p>Here all books will be filtered to find only those, which
					suits you best.</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-6">
				<form name="title" action="searchDetails" method="POST">
					<input type='text' name='title' /> 
					<input type='text' name='authors' />

					<!-- 					<label class="radio-inline"><input type="radio" name="status" value='free'>Free</label> -->
					<!-- 					<label class="radio-inline"><input type="radio" name="status" value='loan'>Loan</label> -->
					<!-- 					<label class="radio-inline"><input type="radio" name="status" value='missing'>Missing</label> -->

					<input type='submit' value='Search' class='btn' />
				</form>
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
						<td align="center">Details</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${foundBooks}" var="book">
						<tr>
							<td align="center">${book.title}</td>
							<td align="center">${book.authors}</td>
							<td align="center">${book.status}</td>
							<td align="center"><a
								href="/webstore/books/book?id=${book.id}"
								class="btn btn-default"> <span
									class="glyphicon glyphicon-zoom-in" /></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<section class="container">
		<a href="/webstore/books" class="btn btn-default"> <span
			class="glyphicon glyphicon-chevron-left" /></span> Back
		</a>
	</section>
</body>
</html>
