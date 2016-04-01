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
				<h1>Are you sure you want to remove this book?</h1>
				<p>Please, carefully track the book you want to remove and
					choose operation.</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="component">
				<h3>Title:</h3>
				${book.title}
			</div>
			<div class="component">
				<h3>Authors:</h3>
				${book.authors}
			</div>
			<div class="component">
				<h3>Status:</h3>
				${book.status}
			</div>
			<div class="component">
				<h3>Description:</h3>
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse facilisis, dolor sed placerat porttitor, mi nulla porta lectus, vel malesuada nibh arcu ac tortor. Sed maximus iaculis tempus. Suspendisse lacus lectus, mattis id pellentesque non, accumsan non enim. Mauris tristique eget tellus a fringilla. Nullam mollis at risus in mollis. Ut ut aliquet erat. Fusce ut placerat quam. Praesent mollis mauris massa, eget gravida enim fringilla vel. Nullam orci tellus, tempor in porttitor sed, fermentum sed leo. Maecenas sollicitudin semper orci ut vehicula. Suspendisse nec quam elit. Phasellus lorem lacus, volutpat ut maximus eget, vestibulum sed enim. Donec imperdiet nunc id mattis vulputate. Duis vel justo faucibus, vestibulum urna non, finibus elit. In ultrices, sapien eget imperdiet tincidunt, nibh est congue nibh, in interdum nisl felis eu ipsum. Nulla ut lacus felis.
			</div>
		</div>
	</section>
	<section class="container">
		<a href="/webstore/books" class="btn btn-default">
			<span class="glyphicon glyphicon-chevron-left" /></span> Back
		</a> <a href="/webstore/books/remove/removed?id=${book.id}" class="btn btn-default"> <span
			class="glyphicon glyphicon-remove" /></span> Remove
		</a>
	</section>
</body>
</html>
